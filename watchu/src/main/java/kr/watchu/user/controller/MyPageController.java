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
import kr.watchu.movie.domain.GenreCommand;
import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.domain.OfficialsCommand;
import kr.watchu.movie.domain.TimelineCommand;
import kr.watchu.movie.service.CommentService;
import kr.watchu.movie.service.MovieService;
import kr.watchu.movie.service.RecommendService;
import kr.watchu.user.domain.UserCommand;
import kr.watchu.user.service.UserService;
import kr.watchu.util.PagingUtil;
import kr.watchu.util.SplitUtil;

@Controller
public class MyPageController {
	//�α�
	private Logger log = Logger.getLogger(this.getClass());
	
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private UserService userService;
	@Resource
	private CommentService commentService;
	@Resource
	private MovieService movieService;
	@Resource
	private RecommendService recommendService;
	
	//�ڹٺ� �ʱ�ȭ
	@ModelAttribute("userCommand")
	public UserCommand initCommand() {
		return new UserCommand();
	}
	
	//���������� ����
		@RequestMapping("/user/userMypage.do")
		public ModelAndView mypage(HttpSession session,Model model) {
			String id = (String)session.getAttribute("user_id");
			UserCommand user = userService.selectUser(id);
			
			if(log.isDebugEnabled()) {
				log.debug("<<userCommand>> : " + user);
			}
			
			//�ȷ��� ���� ǥ���ϱ����� ģ�� arraylist����
			List<String> follow3 = new ArrayList<String>();
			
			if(user.getFollow() != null) {
				String follow = user.getFollow();
				String[] follow2 = SplitUtil.splitByComma(follow);//��ǥ����
			
				//for�� ������ String�迭��� Array����Ʈ�� �ֱ�
				for(int i=0;i<follow2.length; i++) {
					follow3.add(follow2[i]);
				}
				
			}else {
				follow3.clear();//null���� ���ֹ���
			}
			
			//�ȷο� ����
			List<String> follower3 = new ArrayList<String>();
			if(user.getFollower() != null) {
				String follower = user.getFollower();
				String[] follower2 = SplitUtil.splitByComma(follower);//��ǥ����
				
				//for�� ������ String�迭��� Array����Ʈ�� �ֱ�
				for(int i=0;i<follower2.length; i++) {
					follower3.add(follower2[i]);
				}
			}else {
				follower3.clear();
			}
			
			//�������
			List<String> blockList = new ArrayList<String>();
			if(user.getBlock() != null) {
				String block = user.getBlock();
				String[] block2 = SplitUtil.splitByComma(block);//��ǥ����

				//for�� ������ String�迭��� Array����Ʈ�� �ֱ�
				for(int i=0;i<block2.length; i++) {
					blockList.add(block2[i]);
				}
			}else {
				blockList.clear();
			}
			
			
			//�ڸ�Ʈ ����
			Integer comment_count = commentService.selectMyCommentCnt(id);
			Integer likecomment_count = commentService.selectMyCommentCnt(id); //�ٲ��ߴ�
			Integer mypage_movielist_count = recommendService.selectRatedCntById(id);
			
			model.addAttribute("user", user);
			model.addAttribute("list",follow3);
			model.addAttribute("list2",follower3);
			model.addAttribute("blockList",blockList);
			model.addAttribute("comment_count",comment_count);	
			model.addAttribute("likecomment_count",likecomment_count);	
			model.addAttribute("mypage_movielist_count",mypage_movielist_count);	
			
			
			//�ְ��� ��ǰ
			int rowCount = 0;
			int pageCount = 4;
			
			Map<String,Object> map = new HashMap<String,Object>();
			int count = recommendService.selectRatedCntById(id);  
			PagingUtil page = new PagingUtil(count,rowCount,pageCount,count, "userMypage.do");
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			map.put("id", id);
			
			List<MovieCommand> recommendList = recommendService.selectRatedMovieList(map);
			if(log.isDebugEnabled()) {
				log.debug("<<<<recoomendList>>>> : " + recommendList);
			}
			ModelAndView mav = new ModelAndView();
			mav.setViewName("userMypage");
			mav.addObject("count",count);
			mav.addObject("recommendList",recommendList);
			mav.addObject("pagingHtml",page.getPagingHtml());
			
			return mav;
		}
		
		/*//���� ��ȭ ���
		@RequestMapping("/user/userMypage_movie.do")
		public String mypage_movie(@RequestParam(value="id") String id) {
			return "userMypage_movie";
		}*/
		
		//���� ��ȭ ��� ������
		@RequestMapping("/user/userMypage_movielist.do")
		public ModelAndView mypage_movielist(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
				@RequestParam(value="sort",defaultValue="rate") String sort,
											 HttpSession session) {

			int rowCount = 999;
			int pageCount = 999;
			
			String id = (String)session.getAttribute("user_id");
			
			Map<String,Object> map = new HashMap<String,Object>();
			int count = recommendService.selectRatedCntById(id);  
			
			if(log.isDebugEnabled()) {
				log.debug("<<����Ʈ �Ѱ�>> : " + count);
			}
			
			
			PagingUtil page = new PagingUtil(currentPage,count,rowCount,pageCount,"userMypage_movielist.do");
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			map.put("id", id);
			map.put("sort", sort);
			
			List<MovieCommand> recommendList = recommendService.selectRatedMovieList(map);
			if(log.isDebugEnabled()) {
				log.debug("<<<<recoomendList>>>> : " + recommendList);
			}
			ModelAndView mav = new ModelAndView();
			mav.setViewName("userMypage_movielist");
			mav.addObject("count",count);
			mav.addObject("recommendList",recommendList);
			mav.addObject("pagingHtml",page.getPagingHtml());
			
			return mav;
		}
	
	//����м�
	@RequestMapping("/user/analysis.do")
	public ModelAndView analysis(HttpSession session) {
		//������ ��ǥ�� session���� ��������
		String id = (String) session.getAttribute("user_id");
		Integer count = recommendService.selectRatedCntById(id);

		if(log.isDebugEnabled()) {
			log.debug("<<id��>> : " + id);
			log.debug("<<����Ʈ �Ѱ�>> : " + count);
		}

		//��ȣ �±� 5���� ������ index ���� �־� index���� ����Ǵ� ȿ���� �ٸ��� ��
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		map.put("start",1);
		map.put("end",5);

		List<GenreCommand> genreList = recommendService.selectRatedGenre(map);

		//��ȣ ��� List ���� ȭ�鿡�� 3���� �����ָ� �����⸦ Ŭ�� �� �ø�(3����)
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("id",id);
		map1.put("jobs","ACTOR");
		map1.put("start",1);
		map1.put("end",3);

		List<OfficialsCommand> offList1 = recommendService.selectRatedOff(map1);

		//��ȣ ��� List ���� ȭ�鿡�� 3���� �����ָ� �����⸦ Ŭ�� �� �ø�(3����)
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("id",id);
		map2.put("jobs","DIRECTOR");
		map2.put("start",1);
		map2.put("end",3);

		List<OfficialsCommand> offList2 = recommendService.selectRatedOff(map2);

		//��ȭ ��ȣ�帣 3��(�帣�� �������, �� �� Ƚ��)
		Map<String,Object> map3 = new HashMap<String,Object>();
		map3.put("id",id);
		map3.put("start",1);
		map3.put("end",6);
		
		List<GenreCommand> genreList2 = recommendService.selectRatedGenre(map3);

		//mav ��ȯ
		ModelAndView mav = new ModelAndView();
		mav.setViewName("analysis");

		mav.addObject("user_id",id);
		mav.addObject("count",count);
		mav.addObject("genreList",genreList);
		mav.addObject("genreList2",genreList2);
		mav.addObject("offList1",offList1);
		mav.addObject("offList2",offList2);

		return mav;
	}
	
	// �ڸ�Ʈ
	@RequestMapping("/user/userComment.do")
	public ModelAndView comment(HttpSession session) {

		ModelAndView mav = new ModelAndView();
		String id = (String) session.getAttribute("user_id");

		if (log.isDebugEnabled()) {
			log.debug("<<user_id>> : " + id);
		}

		int count = commentService.selectMyCommentCnt(id);

		Map<String, Object> map = new HashMap<String, Object>();

		List<CommentCommand> list = null;

		if (count > 0) {
			list = commentService.selectMyCommentList(id);
		}
		UserCommand user = userService.selectUser(id);

		mav.setViewName("userComment");
		mav.addObject("commentList", list);
		mav.addObject("count", count);
		mav.addObject("user", user);

		return mav;
	}
	
	/*//�ڸ�Ʈ ��������
	@RequestMapping("/user/userComment_detail.do") 
	public ModelAndView comment_detail(@RequestParam("movie_num") Integer movie_num,
									   @RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("movie_num", movie_num);
		map.put("id", id);
		
		CommentCommand comment = commentService.commentDetail(map);
		 
		if (log.isDebugEnabled()) {
			log.debug("<<comment>> : " + comment);
		}
		
		UserCommand user = userService.selectUser(id);
		
		mav.setViewName("userComment_detail");
		mav.addObject("comment",comment);
		mav.addObject("user",user);
		return mav;
	}*/
	
	//���ƿ��� �ڸ�Ʈ
	@RequestMapping("/user/userLikeComment.do")
	public ModelAndView likeComment(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String id = (String) session.getAttribute("user_id");

		if (log.isDebugEnabled()) {
			log.debug("<<user_id>> : " + id);
		}

		int count = commentService.selectMyCommentCnt(id);

		List<CommentCommand> list = null;

		if (count > 0) {
			list = commentService.selectMyCommentList(id);
		}
		UserCommand user = userService.selectUser(id);
		
		mav.setViewName("userLikeComment");
		mav.addObject("commentList", list);
		mav.addObject("count", count);

		return mav;
	}
	
	/*//����;��
	@RequestMapping("/user/userWish.do")
	public String wish(@RequestParam(value="id") String id) {
		return "userWish";
	}
	
	//������
	@RequestMapping("/user/userWatching.do")
	public String watching() {
		return "userWatching";
	}*/
	
	/*//��� ����
	@RequestMapping("/user/userCommentWrite.do")
	public String commentWrite() {
		return "user/userCommentWrite";
	}*/
	
	//��Ϲ���(�������â)
	@RequestMapping("/user/setup.do")
	public String setup() {
		return "user/userSetup";
	}
	
	//===============================�� �ȷ��� ��� ����====================================
	//�ȷ��׸��
	@RequestMapping("/user/myfollowing.do")
	public ModelAndView myfollowing(HttpSession session,
									@RequestParam(value="id") String id,
			   					    @RequestParam(value="pageNum",defaultValue="1") int currentPage,
			   					    @RequestParam(value="keyfield",defaultValue="") String keyfield,
			   					    @RequestParam(value="keyword",defaultValue="") String keyword) {		
		
		String loginUserId = (String)session.getAttribute("user_id");
		
		UserCommand loginUser = userService.selectUser(loginUserId);//���� �α����� ���̵��� Ŀ�ǵ�
		UserCommand user = userService.selectUser(id);//get������� �Ѱܹ��� ���̵��� Ŀ�ǵ�
		ModelAndView mav = new ModelAndView();
		//����(��ǥ����)
		List<String> follow3 = new ArrayList<String>();

		if(user.getFollow() != null) {
			String follow1 = user.getFollow();
			String[] follow2 = SplitUtil.splitByComma(follow1);//��ǥ����

			//for�� ������ �迭��� ����Ʈ�� �ֱ�
			for(int i=0;i<follow2.length; i++) {
				follow3.add(follow2[i]);
			}

		}else {
			follow3.add(null);
		}
		
		mav.addObject("follow",follow3);
		//------------------------------------------------
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", follow3);
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//�ѱ��� ���� �Ǵ� �˻��� ���� ����
		int count = userService.selectfollowCnt(map);
		if(log.isDebugEnabled()) {
			log.debug("<<----------------------------------------------------count>>:" + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"myfollowing.do","&id="+id);
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		//��
		
		//�ȷο��� ������� command
		List<UserCommand> list = null;
		list = userService.selectfollowList(map);
		//-------------------------------------------
		
		mav.setViewName("userFollowing");
		mav.addObject("list",list);
		mav.addObject("count",count);
		mav.addObject("pagingHtml",page.getPagingHtml());
		mav.addObject("user",user);
		mav.addObject("loginUser",loginUser);
		

		return mav;
	}
	
	//===============================�� �ȷο� ��� ����====================================
	//�ȷο����
	@RequestMapping("/user/myfollower.do")
	public ModelAndView myfollower(HttpSession session,
								   @RequestParam(value="id") String id,
			   				       @RequestParam(value="pageNum",defaultValue="1") int currentPage,
			   				       @RequestParam(value="keyfield",defaultValue="") String keyfield,
			   				       @RequestParam(value="keyword",defaultValue="") String keyword) {		

		
		String loginUserId = (String)session.getAttribute("user_id");
		
		UserCommand loginUser = userService.selectUser(loginUserId);//���� �α����� ���̵��� Ŀ�ǵ�
		UserCommand user = userService.selectUser(id);//get������� �Ѱܹ��� ���̵��� Ŀ�ǵ�	
		ModelAndView mav = new ModelAndView();
		//�� �ȷο� arrayList����
		List<String> follower3 = new ArrayList<String>();

		if(user.getFollower() != null) {
			String follower = user.getFollower();
			String[] follower2 = SplitUtil.splitByComma(follower);//��ǥ����

			//for�� ������ �迭��� ����Ʈ�� �ֱ�
			for(int i=0;i<follower2.length; i++) {
				follower3.add(follower2[i]);
			}

		}else {
			follower3.add(null);
		}
		mav.addObject("follower",follower3);
		//�� �ȷο� arrayList��
		
		//------------------------------------------------
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", follower3);
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//�ѱ��� ���� �Ǵ� �˻��� ���� ����
		int count = userService.selectfollowCnt(map);
		if(log.isDebugEnabled()) {
			log.debug("<<count>>:" + count);
		}

		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"myfollowing.do","&id="+id);

		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		//------------------------------------------------
		
		//�� �ȷ��� arrayList ���� (�ȷ���,�ȷο���ư ��ġ ������ �ʿ���,c:if����üũ�Ϸ���)
		List<String> follow3 = new ArrayList<String>();

		if(user.getFollow() != null) {
			String follow1 = user.getFollow();
			String[] follow2 = SplitUtil.splitByComma(follow1);//��ǥ����

			//for�� ������ �迭��� ����Ʈ�� �ֱ�
			for(int i=0;i<follow2.length; i++) {
				follow3.add(follow2[i]);
			}

		}else {
			follow3.add(null);
		}

		mav.addObject("follow",follow3);
		//�� �ȷ��� arrayList ��
		
		

		//�ȷο� ������� command
		List<UserCommand> list = null;
		list = userService.selectfollowList(map);
		//-------------------------------------------

		mav.setViewName("userFollower");
		mav.addObject("list",list);
		mav.addObject("count",count);
		mav.addObject("pagingHtml",page.getPagingHtml());
		mav.addObject("user",user);
		mav.addObject("loginUser",loginUser);


		return mav;
	}
	
	//===============================�� ��� ��� ����====================================
		//�ȷ��׸��
		@RequestMapping("/user/myBlock.do")
		public ModelAndView myBlock(HttpSession session,
				   					    @RequestParam(value="pageNum",defaultValue="1") int currentPage,
				   					    @RequestParam(value="keyfield",defaultValue="") String keyfield,
				   					    @RequestParam(value="keyword",defaultValue="") String keyword) {		
			
			String user_id = (String)session.getAttribute("user_id");
			UserCommand user = userService.selectUser(user_id);//���� �α����� ���̵��� Ŀ�ǵ�
			
			ModelAndView mav = new ModelAndView();
			//�� Ŀ�ǵ忡 ��� ��ǥ���� arrayList�� �����
			List<String> blockList = new ArrayList<String>();
			if(user.getBlock() != null) {
				String block = user.getBlock();
				String[] block2 = SplitUtil.splitByComma(block);//��ǥ����

				//for�� ������ String�迭��� Array����Ʈ�� �ֱ�
				for(int i=0;i<block2.length; i++) {
					blockList.add(block2[i]);
				}
			}else {
				blockList.clear();
			}
			
			
			//����¡,�˻�
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list",blockList);
			map.put("keyfield", keyfield);
			map.put("keyword", keyword);
			
			//�ѱ��� ���� �Ǵ� �˻��� ���� ����
			int count = userService.selectfollowCnt(map);
			if(log.isDebugEnabled()) {
				log.debug("<<---------------------------------------------------count>>:" + count);
			}
			
			PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"myBlock.do","&id="+user_id);
			
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			//��
			
			//���� ����� ������� Ŀ�ǵ�
			List<UserCommand> list = null;
			list = userService.selectfollowList(map);
			if(log.isDebugEnabled()) {
				log.debug("<<---------------------------------------------------list>>:" + list);
			}
			
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
