package kr.watchu.user.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.watchu.movie.domain.CommentCommand;
import kr.watchu.movie.service.CommentService;
import kr.watchu.user.domain.UserCommand;
import kr.watchu.user.service.UserService;
import kr.watchu.util.PagingUtil;
import kr.watchu.util.SplitUtil;

@Controller
public class MyPageController {
	
	//로그
	private Logger log = Logger.getLogger(this.getClass());
	
	private int rowCount = 10;
	private int pageCount = 10;
	@Resource
	private UserService userService;
	@Resource
	private CommentService commentService;
	
	//자바빈 초기화
	@ModelAttribute("userCommand")
	public UserCommand initCommand() {
		return new UserCommand();
	}
	
	//마이페이지 메인
	@RequestMapping("/user/userMypage.do")
	public String mypage(HttpSession session,Model model) {
		String id = (String)session.getAttribute("user_id");
		UserCommand user = userService.selectUser(id);
		
		if(log.isDebugEnabled()) {
			log.debug("<<userCommand>> : " + user);
		}
		
		//팔로잉 숫자 표시하기위해 친구 arraylist만듬
		List<String> follow3 = new ArrayList<String>();
		
		if(user.getFollow() != null) {
			String follow = user.getFollow();
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
		if(user.getFollower() != null) {
			String follower = user.getFollower();
			String[] follower2 = SplitUtil.splitByComma(follower);//쉼표제거
			
			//for문 돌려서 String배열요소 Array리스트에 넣기
			for(int i=0;i<follower2.length; i++) {
				follower3.add(follower2[i]);
			}
		}else {
			follower3.clear();
		}
		
		//블락숫자
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
		
		model.addAttribute("user", user);
		model.addAttribute("list",follow3);
		model.addAttribute("list2",follower3);
		model.addAttribute("blockList",blockList);
				
				
		
		return "userMypage";
	}
	
	//평가한 영화 목록
	@RequestMapping("/user/userMypage_movie.do")
	public String mypage_movie(@RequestParam(value="id") String id) {
		return "userMypage_movie";
	}
	
	//평가한 영화 목록 더보기
	@RequestMapping("/user/userMypage_movielist.do")
	public String mypage_movielist() {
		return "userMypage_movielist";
	}
	
	//코멘트
	@RequestMapping("/user/userComment.do")
	public ModelAndView comment(HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		String id = (String)session.getAttribute("user_id");
		
		if(log.isDebugEnabled()) {
			log.debug("<<user_id>> : "+id);
		}
		
		int count = commentService.selectMyCommentCnt(id);
		
		List<CommentCommand> list = null;
		if(count >0) {
			list = commentService.selectMyCommentList(id);
		}
		
		mav.setViewName("userComment");
		mav.addObject("commentList",list);
		mav.addObject("count",count);
		return mav;
	}
	
	//코멘트 상세페이지
	@RequestMapping("/user/userComment_detail.do")
	public String comment_detail(HttpSession session,Model model) {
		String id = (String)session.getAttribute("user_id");
		UserCommand user = userService.selectUser(id);
		
		if(log.isDebugEnabled()) {
			log.debug("<<userCommand>> : " + user);
		}
		
		model.addAttribute("user", user);
		
		return "userComment_detail";
	}
	
	//좋아요한 코멘트
	@RequestMapping("/user/userLikeComment.do")
	public String likeComment() {
		return "userLikeComment";
	}
	
	//보고싶어요
	@RequestMapping("/user/userWish.do")
	public String wish(@RequestParam(value="id") String id) {
		return "userWish";
	}
	
	//보는중
	@RequestMapping("/user/userWatching.do")
	public String watching() {
		return "userWatching";
	}
	
	//댓글 쓰기
	@RequestMapping("/user/userCommentWrite.do")
	public String commentWrite() {
		return "user/userCommentWrite";
	}
	
	//톱니바퀴(설정모달창)
	@RequestMapping("/user/setup.do")
	public String setup() {
		return "user/userSetup";
	}
	
	//===============================내 팔로잉 목록 보기====================================
	//팔로잉목록
	@RequestMapping("/user/myfollowing.do")
	public ModelAndView myfollowing(HttpSession session,
									@RequestParam(value="id") String id,
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
		
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"myfollowing.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		//------------------------------------------------
		String loginUserId = (String)session.getAttribute("user_id");
		
		UserCommand loginUser = userService.selectUser(loginUserId);//현재 로그인한 아이디의 커맨드
		UserCommand user = userService.selectUser(id);//get방식으로 넘겨받은 아이디의 커맨드
		ModelAndView mav = new ModelAndView();
		//시작(쉼표제거)
		List<String> follow3 = new ArrayList<String>();

		if(user.getFollow() != null) {
			String follow1 = user.getFollow();
			String[] follow2 = SplitUtil.splitByComma(follow1);//쉼표제거

			//for문 돌려서 배열요소 리스트에 넣기
			for(int i=0;i<follow2.length; i++) {
				follow3.add(follow2[i]);
			}

		}else {
			follow3.add(null);
		}
		
		mav.addObject("follow",follow3);
		//끝
		
		//팔로우한 사람들의 command필요하므로 가입한 모든 사람 리스트목록 불러옴
		List<UserCommand> list = null;
		list = userService.selectUserList(map);
		//-------------------------------------------
		
		mav.setViewName("userFollowing");
		mav.addObject("list",list);
		mav.addObject("count",count);
		mav.addObject("pagingHtml",page.getPagingHtml());
		mav.addObject("user",user);
		mav.addObject("loginUser",loginUser);
		

		return mav;
	}
	
	//===============================내 팔로워 목록 보기====================================
	//팔로워목록
	@RequestMapping("/user/myfollower.do")
	public ModelAndView myfollower(HttpSession session,
								   @RequestParam(value="id") String id,
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

		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"myfollowing.do");

		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		//------------------------------------------------
		String loginUserId = (String)session.getAttribute("user_id");
		
		UserCommand loginUser = userService.selectUser(loginUserId);//현재 로그인한 아이디의 커맨드
		UserCommand user = userService.selectUser(id);//get방식으로 넘겨받은 아이디의 커맨드	
		ModelAndView mav = new ModelAndView();
		//내 팔로워 arrayList시작
		List<String> follower3 = new ArrayList<String>();

		if(user.getFollower() != null) {
			String follower = user.getFollower();
			String[] follower2 = SplitUtil.splitByComma(follower);//쉼표제거

			//for문 돌려서 배열요소 리스트에 넣기
			for(int i=0;i<follower2.length; i++) {
				follower3.add(follower2[i]);
			}

		}else {
			follower3.add(null);
		}
		mav.addObject("follower",follower3);
		//내 팔로워 arrayList끝
		
		//내 팔로잉 arrayList 시작 (팔로잉,팔로워버튼 위치 때문에 필요함,c:if조건체크하려고)
		List<String> follow3 = new ArrayList<String>();

		if(user.getFollow() != null) {
			String follow1 = user.getFollow();
			String[] follow2 = SplitUtil.splitByComma(follow1);//쉼표제거

			//for문 돌려서 배열요소 리스트에 넣기
			for(int i=0;i<follow2.length; i++) {
				follow3.add(follow2[i]);
			}

		}else {
			follow3.add(null);
		}

		mav.addObject("follow",follow3);
		//내 팔로잉 arrayList 끝
		
		

		//팔로워 사람들의 command필요하므로 가입한 모든 사람 리스트목록 불러옴
		List<UserCommand> list = null;
		list = userService.selectUserList(map);
		//-------------------------------------------

		mav.setViewName("userFollower");
		mav.addObject("list",list);
		mav.addObject("count",count);
		mav.addObject("pagingHtml",page.getPagingHtml());
		mav.addObject("user",user);
		mav.addObject("loginUser",loginUser);


		return mav;
	}
	
	//===============================내 블락 목록 보기====================================
		//팔로잉목록
		@RequestMapping("/user/myBlock.do")
		public ModelAndView myBlock(HttpSession session,
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
			
			PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"myfollowing.do");
			
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			//------------------------------------------------
			String user_id = (String)session.getAttribute("user_id");
			UserCommand user = userService.selectUser(user_id);//현재 로그인한 아이디의 커맨드
			
			ModelAndView mav = new ModelAndView();
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
			
			//끝
			
			//블락한 사람들의 command필요하므로 가입한 모든 사람 리스트목록 불러옴
			List<UserCommand> list = null;
			list = userService.selectUserList(map);
			//-------------------------------------------
			
			mav.setViewName("userBlockList");
			mav.addObject("list",list);
			mav.addObject("count",count);
			mav.addObject("pagingHtml",page.getPagingHtml());
			mav.addObject("user",user);
			mav.addObject("blockList",blockList);

			return mav;
		}

}
