package kr.watchu.user.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.watchu.movie.service.CommentService;
import kr.watchu.user.domain.ContactCommand;
import kr.watchu.user.domain.ReportCommand;
import kr.watchu.user.domain.UserCommand;
import kr.watchu.user.service.ContactService;
import kr.watchu.user.service.ReportService;
import kr.watchu.user.service.UserService;
import kr.watchu.util.CipherTemplate;
import kr.watchu.util.PagingUtil;
import kr.watchu.util.SplitUtil;
import kr.watchu.util.StringUtil;

@Controller
public class UserController {
	
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private UserService userService;
	
	@Resource
	private ContactService contactService;
	
	@Resource
	private ReportService reportService;
	
	@Resource
	private CipherTemplate cipherAES;
	
	@Resource
	private CommentService commentService;
	
	
	/*@Resource
	private SplitUtil split;*/
	//자바빈 초기화
	@ModelAttribute("command")
	public UserCommand initCommand() {
		return new UserCommand();
	}
	@ModelAttribute("contactCommand")
	public ContactCommand initContactCommand() {
		return new ContactCommand();
	}
	@ModelAttribute("reportCommand")
	public ReportCommand initReportCommand() {
		return new ReportCommand();
	}
	//==========================================회원가입========================================
	//회원가입 폼 호출
	@RequestMapping(value="/user/write.do",method=RequestMethod.GET)
	public String insertForm() {
		return "userWrite";
	}
	//회원가입 데이터 전송
	@RequestMapping(value="/user/write.do",method=RequestMethod.POST)
	public String insertSubmit(@ModelAttribute("command") @Valid UserCommand userCommand, BindingResult result, HttpSession session) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<userCommand>> : "+userCommand);
		}
		
		if(result.hasErrors()) {
			return insertForm();
		}
		//암호화 처리
		userCommand.setPasswd(cipherAES.encrypt(userCommand.getPasswd()));
		
		userService.insertUser(userCommand);
		
	    //===회원가입 성공시 바로 로그인 처리===///
		session.setAttribute("user_id", userCommand.getId());
		//회원가입시 초기 auth 값 1을 등록해준다
		session.setAttribute("user_auth", 1);
		
		return "redirect:/main/main.do";
	}
	//==========================================회원수정========================================
	//회원수정 폼 호출
	@RequestMapping(value="/user/updateUser.do",method=RequestMethod.GET)
	public String modifyForm(HttpSession session,Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		UserCommand user = userService.selectUser(id);
		model.addAttribute("command",user);
		
		return "userModify";
	}
	//회원수정 데이터 전송
	@RequestMapping(value="/user/updateUser.do",method=RequestMethod.POST)
	public String updateSubmit(@ModelAttribute("command") @Valid UserCommand userCommand, BindingResult result) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> :" + userCommand);
		}
		if(result.hasErrors()) {
			return "userModify";
		}
		
		//암호화 처리
		userCommand.setPasswd(cipherAES.encrypt(userCommand.getPasswd()));
		
		//회원정보수정
		userService.updateUser(userCommand);
		
		return "redirect:/user/userMypage.do";
		
	}
	
	//==========================================회원삭제========================================
	//회원삭제 폼 호출
	@RequestMapping(value="/user/deleteUser.do",method=RequestMethod.GET)
	public String deleteForm(HttpSession session,Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		UserCommand user = new UserCommand();
		user.setId(id);
		
		model.addAttribute("command",user);
		
		return "userDelete";
	}
	//회원데이터 삭제
	@RequestMapping(value="/user/deleteUser.do",method=RequestMethod.POST)
	public String deleteSubmit(@ModelAttribute("command") @Valid UserCommand userCommand,BindingResult result,HttpSession session) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<userCommand>>:"+ userCommand);
		}
		
		
		if(result.hasFieldErrors("passwd")) {
			return "userDelete";
		}
		
		//비밀번호 일치 여부 체크
				try {
					UserCommand user = userService.selectUser(userCommand.getId());
					boolean check = false;
					
					if(user!=null) {
						//비밀번호 일치 여부 체크
						check = user.isCheckedPasswd(cipherAES.encrypt(userCommand.getPasswd()));
					}
					if(check) {
						//인증성공, 회원정보 삭제
						userService.deleteUser(userCommand.getId());
						//로그아웃
						session.invalidate();
						return "redirect:/main/main.do";
					}else {
						//인증실패
						throw new Exception();
					}
				
				}catch(Exception e) {
					result.rejectValue("passwd", "invalidPassword");
					return "userDelete";
				}
	}
	
	//==========================================추천친구목록========================================
	@RequestMapping("/user/follow.do")
	public ModelAndView follow(@RequestParam(value="id") String id,
							   @RequestParam(value="pageNum",defaultValue="1") int currentPage,
							   @RequestParam(value="keyfield",defaultValue="") String keyfield,
			                   @RequestParam(value="keyword",defaultValue="") String keyword) {		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//총글의 갯수 또는 검색된 글의 갯수
		int count = userService.selectUserCnt(map);
		if(log.isDebugEnabled()) {
			log.debug("<<count>>:" + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"follow.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		//--------------------------------------------
		
		UserCommand user = userService.selectUser(id);
		ModelAndView mav = new ModelAndView();
		
		//시작
		List<String> follow_id3 = new ArrayList<String>();
		
		if(user.getFollow() != null) {
			String follow_id = user.getFollow();
			String[] follow_id2 = SplitUtil.splitByComma(follow_id);//쉼표제거
		
			//for문 돌려서 String배열요소 Array리스트에 넣기
			for(int i=0;i<follow_id2.length; i++) {
				follow_id3.add(follow_id2[i]);
			}
			
		}else {
			follow_id3.clear();
		}
		//로그확인
		if(log.isDebugEnabled()) {
			log.debug("<<☆★follow_id3~~~>>:" + follow_id3);
		}
		mav.addObject("follow",follow_id3);
		
		//끝
		
		//추천친구 전부or검색한친구 리스트목록
		List<UserCommand> list = null;
		list = userService.selectUserList(map);
		//----------------------------------------------
	
		
		mav.setViewName("userfollow");
		mav.addObject("list",list);
		mav.addObject("count",count);
		mav.addObject("pagingHtml",page.getPagingHtml());
		mav.addObject("user",user);
		
		
		return mav;
	}
	
	//팔로우버튼 처리
	@RequestMapping("/user/following.do")
	@ResponseBody
	public Map<String,String> follwing(@RequestParam(value="follow_id") String follow_id,@RequestParam(value="user_id") String user_id){

		UserCommand user = userService.selectUser(user_id);//내 커맨드
		UserCommand follow_user = userService.selectUser(follow_id);//내가 팔로우한사람의 커맨드
		
		//------------------내 커맨드 follow에 추가
		if(user.getFollow() == null) {//기존 팔로우한사람 없으면 그냥 추가
			user.setFollow(follow_id);
		}else{//있으면 쉼표찍고 새로 팔로우한 사람 추가
			String origin_follow = user.getFollow();
			String new_follow = origin_follow+","+follow_id;
			user.setFollow(new_follow);
		}
		//내 follow update
		userService.insertFollow(user);
		
		//-----------------내가 팔로우한 사람의 커맨드 follower에 나를 추가
		if(follow_user.getFollower() == null) {//팔로워 비어있으면 그냥 추가
			follow_user.setFollower(user_id);
		}else {//있으면 쉽표찍고 추가
			String origin_follower = follow_user.getFollower();
			String new_follow = origin_follower+","+user_id;
			follow_user.setFollower(new_follow);
		}
		//상대방 follower update
		userService.insertFollower(follow_user);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");

		return map;
	}
	
	//언팔로우버튼 처리
	@RequestMapping("/user/unfollow.do")
	@ResponseBody
	public Map<String,String> unfollow(@RequestParam(value="unfollow_id") String unfollow_id,@RequestParam(value="user_id") String user_id){
		
		ModelAndView mav = new ModelAndView();
		UserCommand user = userService.selectUser(user_id);//내 커맨드
		UserCommand unfollow_user = userService.selectUser(unfollow_id);//내가 언팔한사람의 커맨드
		
		//------------------------------------------------
		//내 follow에 있는 친구들 쉼표제거해서 arrayList로 만들기 시작
		List<String> user_follow3 = new ArrayList<String>();

		if(user.getFollow() != null) {
			String user_follow = user.getFollow();
			String[] user_follow2 = SplitUtil.splitByComma(user_follow);//쉼표제거

			//for문 돌려서 String배열요소 Array리스트에 넣기
			for(int i=0;i<user_follow2.length; i++) {
				user_follow3.add(user_follow2[i]);
			}

		}else {
			user_follow3.clear();
		}
		
		mav.addObject("follow",user_follow3);
		//내 follow에 있는 친구들 쉼표제거해서 arrayList로 만들기 끝
		
		//내 follow에서 언팔한 친구 지우기
		if(user_follow3.contains(unfollow_id)==true) {//언팔누르는 아이디가 내 친구목록에 있으면
			user_follow3.remove(unfollow_id);
		}

		//언팔제거한 follow_id3요소들을 다시 쉽표붙여서 String문자열로 만들기
		String followback = "";
		if(user_follow3.isEmpty()) {//모두 언팔해서 follow 비어있으면
			followback = "";
		}else {//follow에 한명이상 있을경우

			for(int i=0; i<user_follow3.size(); i++) {
				followback += user_follow3.get(i) + "," ;
			}
			//마지막 쉼표는 제거해야댐
			followback = followback.substring(0,followback.length()-1);//인덱스0~마지막전까지 문자만 가져옴
		}
		
		user.setFollow(followback);

		//내 follow update
		userService.insertFollow(user);
		//-------------------------------------------------
		
		//내가 언팔한사람의 follwer를 쉼표제거해서 arrayList로 만들기 시작
		List<String> unfollow_follower3 = new ArrayList<String>();
		if(unfollow_user.getFollower() != null) {
			String unfollow_follower = unfollow_user.getFollower();
			String[] unfollow_follower2 = SplitUtil.splitByComma(unfollow_follower);//쉼표제거
			
			//for문 돌려서 String배열요소 Array리스트에 넣기
			for(int i=0;i<unfollow_follower2.length; i++) {
				unfollow_follower3.add(unfollow_follower2[i]);
			}
		}else {
			unfollow_follower3.clear();
		}
		//내가 언팔한사람의 follwer를 쉼표제거해서 arrayList로 만들기 끝
		
		//상대방 follower에서 나 지우기
		if(unfollow_follower3.contains(user_id)==true) {
			unfollow_follower3.remove(user_id);
		}
		//unfollow_follower3요소들을 다시 쉼표붙여서 String문자열로 만들기
		String followerback ="";
		if(unfollow_follower3.isEmpty()) {
			followerback="";
		}else {
			for(int i =0;i<unfollow_follower3.size();i++) {
				followerback += unfollow_follower3.get(i) + ",";
			}
			//마지막 쉼표는 제거
			followerback= followerback.substring(0,followerback.length()-1);//인덱스0~마지막전까지 문자만 가져옴
		}
		
		unfollow_user.setFollower(followerback);
		
		//상대방 follower update
		userService.insertFollower(unfollow_user);
		//----------------------------------------------------
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");

		return map;
	}
	
	// ========================================타임라인================================================
	@RequestMapping("/user/userTimeline.do")
	public String timeline() {
		
		return "userTimeline";
	}
	
	// ========================================고객센터================================================
	//등록 폼
	@RequestMapping(value="/user/userSupportWrite.do",method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {
		String id = (String)session.getAttribute("user_id");
		
		ContactCommand contactCommand = new ContactCommand();
		contactCommand.setId(id);
		
		model.addAttribute("command",contactCommand);
		
		return "userSupportWrite";
	}
	//전송된 데이터 처리
	@RequestMapping(value="/user/userSupportWrite.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("contactCommand") @Valid ContactCommand contactCommand,BindingResult result) {
		if(log.isDebugEnabled()) {
			log.debug("<<contactCommand>> : " + contactCommand);
		}
		if(result.hasErrors()) {
			return "userSupportWrite";
		}
		
		contactService.insertContact(contactCommand);
		
		return "redirect:/user/userSupportList.do";
	}
	
	//글 목록
	@RequestMapping("/user/userSupportList.do")
	public ModelAndView process(
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword
			) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//총 글의 갯수 또는 검색된 글의 갯수
		int count = contactService.selectContactCnt(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"userSupportList.do");

		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<ContactCommand> list = null;
		if(count > 0) {
			list = contactService.selectContactList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<list>> : " + list);
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("userSupportList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml",page.getPagingHtml());
		
		return mav;
	}
	//게시판 글 상세
	@RequestMapping("/user/userSupportView.do")
	public ModelAndView process(@RequestParam(value="contact_num",required=true) int contact_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<contact_num>> : " + contact_num);
		}
		
		ContactCommand contact = contactService.selectContact(contact_num);
		//줄바꿈 처리 
		contact.setContent(StringUtil.useBrNoHtml(contact.getContent()));
		
		return new ModelAndView("userSupportView","contact",contact);
	}
	
	//파일 다운로드
	@RequestMapping("/user/file.do")
	public ModelAndView download(@RequestParam("contact_num")int contact_num) {
		ContactCommand contact = contactService.selectContact(contact_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("downloadView");
		mav.addObject("downloadFile",contact.getUpload_file());
		mav.addObject("filename",contact.getFilename());
		
		return mav;
	}
	
	//이미지 출력
	@RequestMapping("/user/imageView.do")
	public ModelAndView viewImage(@RequestParam("contact_num") int contact_num) {
		ContactCommand contact = contactService.selectContact(contact_num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",contact.getUpload_file());
		mav.addObject("filename",contact.getFilename());
		
		return mav; 
	}
	
	//게시판 글 수정
	//수정 폼
	@RequestMapping(value="/user/userSupportUpdate.do",method=RequestMethod.GET)
	public String form(@RequestParam("contact_num") int contact_num, Model model) {
		ContactCommand contactCommand = contactService.selectContact(contact_num);
		model.addAttribute("contactCommand", contactCommand);
		
		return "userSupportModify";
	}
	//수정 폼에서 전송된 데이터 처리
	@RequestMapping(value="/user/userSupportUpdate.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("contactCommand") 
						 ContactCommand contactCommand, 
						 BindingResult result,
						 HttpSession session, 
						 HttpServletRequest request) {
		if(log.isDebugEnabled()) {
			log.debug("<<contactCommand>> : " + contactCommand);
		}
		
		ContactCommand contact = contactService.selectContact(contactCommand.getContact_num());
		
		if(result.hasErrors()) {
			contactCommand.setFilename(contact.getFilename());
			return "userSupportModify";
		}
		
		if(contactCommand.getUpload().isEmpty()) {
			contactCommand.setUpload_file(contact.getUpload_file());
			contactCommand.setFilename(contact.getFilename());
		}
		
		contactService.updateContact(contactCommand);

		return "redirect:/user/userSupportList.do"; 
	}
	
	//게시판 글 삭제
	@RequestMapping("/user/userSupportDelete.do")
	public String submit(@RequestParam("contact_num")int contact_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<contact_num>> : " + contact_num);
		}
		contactService.deleteContact(contact_num);
		
		return "redirect:/user/userSupportList.do";
	}
	
	
	
	//===============================================다른유저 페이지===============================================
	@RequestMapping("/user/userPage.do")
	public ModelAndView anotherUserpage(HttpSession session,@RequestParam("id") String id) {
		
		ModelAndView mav = new ModelAndView();
		
		String my_id = (String)session.getAttribute("user_id");
		UserCommand anotheruser = userService.selectUser(id);//다른유저 커맨드
		UserCommand user = userService.selectUser(my_id);//내 커맨드
		
		//다른유저 팔로잉 숫자 표시하기위해 친구 arraylist만듬
		List<String> follow3 = new ArrayList<String>();

		if(anotheruser.getFollow() != null) {
			String follow = anotheruser.getFollow();
			String[] follow2 = SplitUtil.splitByComma(follow);//쉼표제거

			//for문 돌려서 String배열요소 Array리스트에 넣기
			for(int i=0;i<follow2.length; i++) {
				follow3.add(follow2[i]);
			}

		}else {
			follow3.clear();//null값도 없애버림
		}

		//팔로워 숫자
		List<String> follower3 = new ArrayList<String>();
		if(anotheruser.getFollower() != null) {
			String follower = anotheruser.getFollower();
			String[] follower2 = SplitUtil.splitByComma(follower);//쉼표제거

			//for문 돌려서 String배열요소 Array리스트에 넣기
			for(int i=0;i<follower2.length; i++) {
				follower3.add(follower2[i]);
			}
		}else {
			follower3.clear();
		}
		
		//내 친구목록 구하기 
		//내 follow에 있는 친구들 쉼표제거해서 arrayList로 만들기 시작
		List<String> user_follow3 = new ArrayList<String>();

		if(user.getFollow() != null) {
			String user_follow = user.getFollow();
			String[] user_follow2 = SplitUtil.splitByComma(user_follow);//쉼표제거

			//for문 돌려서 String배열요소 Array리스트에 넣기
			for(int i=0;i<user_follow2.length; i++) {
				user_follow3.add(user_follow2[i]);
			}

		}else {
			user_follow3.clear();
		}
		//내 follow에 있는 친구들 쉼표제거해서 arrayList로 만들기 끝
		
		//내 커맨드에 블락 쉼표빼고 arrayList로 만들기
		List<String> blockList = new ArrayList<String>();
		if(user.getBlock() != null) {
			String block = user.getBlock();
			String[] block2 = SplitUtil.splitByComma(block);//쉼표제거

			//for문 돌려서 String배열요소 Array리스트에 넣기
			for(int i=0;i<block2.length; i++) {
				blockList.add(block2[i]);
			}
		}else {
			blockList.clear();
		}
		
		//코맨트 숫자
		Integer comment_count = commentService.selectMyCommentCnt(id);
				
		mav.setViewName("userPage");
		mav.addObject("anotheruser",anotheruser);
		mav.addObject("user",user);
		mav.addObject("list",follow3);
		mav.addObject("list2",follower3);
		mav.addObject("mylist",user_follow3);
		mav.addObject("blockList",blockList);
		mav.addObject("comment_count",comment_count);
		
		return mav;
	}
	
	
	//block처리
	@RequestMapping("/user/block.do")
	@ResponseBody
	public Map<String,String> block(HttpSession session,@RequestParam("block_id") String block_id) {
		
		ModelAndView mav = new ModelAndView();
		
		String my_id = (String)session.getAttribute("user_id");
		UserCommand user = userService.selectUser(my_id);//내 커맨드
		
		//내 커맨드에 블락 추가
		if(user.getBlock() ==null) {
			user.setBlock(block_id);
		}else {
			String origin_block = user.getBlock();
			String new_block = origin_block +","+ block_id;
			user.setBlock(new_block);
		}
		//DB에 블락 추가
		userService.insertBlock(user);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");
		
		return map;
		
	}
	
	//unblock처리
	@RequestMapping("/user/unblock.do")
	@ResponseBody
	public Map<String,String> unblock(HttpSession session,@RequestParam("unblock_id") String unblock_id) {

		ModelAndView mav = new ModelAndView();

		String my_id = (String)session.getAttribute("user_id");
		UserCommand user = userService.selectUser(my_id);//내 커맨드
		
		//내 커맨드에 블락 쉼표빼고 arrayList로 만들기
		List<String> blockList = new ArrayList<String>();
		if(user.getBlock() != null) {
			String block = user.getBlock();
			String[] block2 = SplitUtil.splitByComma(block);//쉼표제거
			
			//for문 돌려서 String배열요소 Array리스트에 넣기
			for(int i=0;i<block2.length; i++) {
				blockList.add(block2[i]);
			}
		}else {
			blockList.clear();
		}
		mav.addObject("blockList",blockList);
		//내 block에서 블락해제한 친구 지우기
		if(blockList.contains(unblock_id)==true) {
			blockList.remove(unblock_id);
		}
		//블락해제한친구 지운 arraylist를 다시 String문자열로 만듬
		String blockList2 = "";
		if(blockList.isEmpty()) {
			blockList2 = "";
		}else {
			for(int i=0; i<blockList.size(); i++) {
				blockList2 += blockList.get(i) + "," ;
			}
			//마지막 쉼표는 제거해야댐
			blockList2 = blockList2.substring(0,blockList2.length()-1);//인덱스0~마지막전까지 문자만 가져옴
		}
		
		user.setBlock(blockList2);
		//내 block update
		userService.insertBlock(user);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");

		return map;

	}
	
	//신고하기
	//form
	@RequestMapping(value="/user/insertReport.do",method=RequestMethod.GET)
	public ModelAndView userReportForm(HttpSession session,@RequestParam("id") String id) {
		
		ModelAndView mav = new ModelAndView();
		
		String user_id = (String)session.getAttribute("user_id");
		
		UserCommand user = userService.selectUser(user_id);//내 커맨드
		UserCommand user2 = userService.selectUser(id);//신고한애 커맨드
		
		mav.setViewName("userReport");
		mav.addObject("user",user);
		mav.addObject("user2",user2);
		
		
		return mav;
	}
	//데이터보내기
	@RequestMapping(value="/user/insertReport.do",method=RequestMethod.POST)
	public String userReport(@ModelAttribute("reportCommand") ReportCommand report,@RequestParam("id") String id,HttpSession session) {
		
		String user_id = (String)session.getAttribute("user_id");
		
		report.setId(user_id);
		report.setReport_user(id);
		
		if(log.isDebugEnabled()) {
			log.debug("<<report>> : " + report);
		}
		reportService.insertReport(report);
		
		return "redirect:userPage.do?id="+id;
	}
}	