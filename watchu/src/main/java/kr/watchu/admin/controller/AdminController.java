package kr.watchu.admin.controller;

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

import kr.watchu.movie.domain.GenreCommand;
import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.domain.OfficialsCommand;
import kr.watchu.movie.service.CommentService;
import kr.watchu.movie.service.GenreService;
import kr.watchu.movie.service.MovieService;
import kr.watchu.movie.service.MovieratedService;
import kr.watchu.movie.service.OfficialsService;
import kr.watchu.user.domain.UserCommand;
import kr.watchu.user.service.UserService;
import kr.watchu.util.CipherTemplate;
import kr.watchu.util.PagingUtil;

@Controller
public class AdminController {
	//로그 설정
	private Logger log = Logger.getLogger(this.getClass());

	//자원 주입 받음	
	@Resource
	private GenreService genreService;
	@Resource
	private OfficialsService officialsService;
	@Resource
	private MovieService movieService;
	@Resource
	private UserService userService;
	@Resource
	private CommentService commentService;
	@Resource
	private MovieratedService movieRatedService;
	
	@Resource
	private CipherTemplate cipherAES;

	//자바빈 초기화
	@ModelAttribute("movie_command")
	public MovieCommand initCommand3() {
		return new MovieCommand();
	}
	@ModelAttribute("official_command")
	public OfficialsCommand initCommand2() {
		return new OfficialsCommand();
	}
	@ModelAttribute("genre_command")
	public GenreCommand initCommand() {
		return new GenreCommand();
	}
	@ModelAttribute("userCommand")
	public UserCommand initUserCommand() {
		return new UserCommand();
	}

	//페이징을 위한 변수 선언
	private int rowCount = 50;
	private int pageCount = 10;

	//관리자 메인	
	@RequestMapping("/admin/admin_movieList.do")
	public ModelAndView mainProcess(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
			@RequestParam(value="keyfield", defaultValue="") String keyfield,
			@RequestParam(value="keyword", defaultValue="") String keyword) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//총 글의 갯수 또는 검색된 글의 갯수
		int movie_count = movieService.selectMovieCnt(map);

		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<<movie_count>>: " + movie_count);
		}

		//페이징 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, movie_count, rowCount, pageCount, "admin_movieList.do");

		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<MovieCommand> movie_list = null;
		if(movie_count > 0) {
			movie_list = movieService.selectMovieList(map);

			//로그 출력
			if(log.isDebugEnabled()) {
				log.debug("<movie_list>: " + movie_list);
			} 
		}

		//ModelAndView객체 생성, 데이터 저장
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_movieList");
		mav.addObject("movie_count", movie_count);
		mav.addObject("movie_list", movie_list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}

	//====================관리자 로그인======================//
	//로그인 폼 호출
	@RequestMapping(value="/admin/login.do",method=RequestMethod.GET)
	public String loginForm(HttpSession session) {
		session.invalidate();
		return "adminLogin";
	}
	//로그인
	@RequestMapping(value="/admin/login.do",method=RequestMethod.POST)
	public String submitLogin(@ModelAttribute("userCommand") 
	@Valid UserCommand userCommand, 
	BindingResult result, HttpSession session) {

		if(log.isDebugEnabled()) {
			log.debug("<<userCommand>> : " + userCommand);
		}

		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return loginForm(session);
		}

		try {
			UserCommand user = userService.selectUser(userCommand.getId());

			boolean check = false;

			if(user!=null) {
				//비밀번호 일치 여부 체크
				check = user.isCheckedPasswd(cipherAES.encrypt(userCommand.getPasswd()));
				if(log.isDebugEnabled()) {
					log.debug("<<user>> : "+user);
					log.debug("<<일치여부>> : " + check);
				}
			}

			if(user.getAuth() == 6) {
				if(check) {
					//인증 성공 로그인
					session.setAttribute("user_id", user.getId());
					session.setAttribute("user_auth", user.getAuth());

					if(log.isDebugEnabled()) {
						log.debug("<<인증 성공>>");
						log.debug("<<user_id>> : " + user.getId());
						log.debug("<<user_auth>> : " + user.getAuth());
					}

					return "redirect:/admin/admin_movieList.do";
				}else {
					//인증실패 
					throw new Exception();
				}
			}else {
				//권한 없음
				throw new Exception();
			}
		}catch(Exception e){
			//인증 실패
			result.reject("invalidAdmin");

			if(log.isDebugEnabled()) {
				log.debug("<<인증 실패>>");
			}

			return loginForm(session);
		}
	}

	//====================01_영화 관리_영화목록====================//
	//01_1_등록 폼 호출
	@RequestMapping(value="/admin/admin_movieWrite.do", method=RequestMethod.GET)
	public String admin_movieForm(HttpSession session, Model model) {
		//자바빈 생성
		MovieCommand movie = new MovieCommand();
		model.addAttribute("movie_command", movie);
		
		return "admin_movieWrite";
	}
	
	//01_2_전송된 데이터 처리
	@RequestMapping(value="/admin/admin_movieWrite.do", method=RequestMethod.POST)
	public String movie_submit(@ModelAttribute("movie_command") @Valid MovieCommand movieCommand, BindingResult result, HttpServletRequest request) {
		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<<MovieCommand>>: " + movieCommand);
		}
		
		movieService.insertMovie(movieCommand);

		return "redirect:/admin/admin_movieList.do";
	}

	//01_3_영화 상세&수정
	//상세보기&수정 폼 호출
	@RequestMapping(value="/admin/admin_movieDetail.do", method=RequestMethod.GET)
	public ModelAndView admin_movieDetail(@RequestParam("movie_num") int movie_num, Model model) {
		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<<movie_num>>: " + movie_num);
		}
		
		MovieCommand movie = movieService.selectMovie(movie_num);
		model.addAttribute("movie_command", movie);
		
		return new ModelAndView("admin_movieDetail", "movie", movie);
	}
	
	//이미지 출력
	@RequestMapping("/admin/image_View.do")
	public ModelAndView viewImage2(@RequestParam("movie_num") int movie_num,
								   @RequestParam("type") String type) {
		//한 건의 데이터를 받아 객체 생성
		MovieCommand movie = movieService.selectMovie(movie_num);
		if(log.isDebugEnabled()) {
			log.debug("[[movie_num]] : " + movie_num);
		}
		//데이터를 ModelAndView객체에 차곡차곡 저장
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
						//속성명		속성값(byte[]의 데이터)
		mav.addObject("filename","poster.jpg");
		if(type.equals("banner")) {
			mav.addObject("imageFile", movie.getBanner_img());
		}else {
			mav.addObject("imageFile", movie.getPoster_img());
		}
				
		return mav;
	}
	
	//수정 폼에서 전송된 데이터 처리
	@RequestMapping(value="/admin/admin_movieDetail.do", method=RequestMethod.POST)
	public String adminMovie_submit(@ModelAttribute("movie_command") @Valid MovieCommand movieCommand, BindingResult result, HttpSession session, HttpServletRequest request) {
		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<<movieCommand>>: " + movieCommand);
		}
		
		//영화 정보 수정
		movieService.updateMovie(movieCommand);
		
		return "redirect:/admin/admin_movieList.do";
	}
	
	//01_4_영화 정보 삭제
	@RequestMapping("/admin/admin_movieDelete.do")
	public String adminMovie_delete(@RequestParam("movie_num") int movie_num) {
		//로그출력
		if(log.isDebugEnabled()) {
			log.debug("<<movie_num>>: " + movie_num);
		}
		
		//영화 삭제 전에 해당 영화에 달린 코멘트 삭제
		commentService.deleteCommentByMovie(movie_num);
		//영화 삭제 전에 해당 영화에 달린 평가 삭제
		movieRatedService.deleteRatedByMovie(movie_num);
		//글 삭제
		movieService.deleteMovie(movie_num);
		
		return "redirect:/admin/admin_movieList.do";
	}
	//====================02_영화 관리_영화 관계자====================//
	//02_1_목록, 등록 폼
	@RequestMapping("/admin/officialList.do")
	public ModelAndView official_list(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
			@RequestParam(value="keyfield", defaultValue="") String keyfield,
			@RequestParam(value="keyword", defaultValue="") String keyword) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//총 글의 갯수 또는 검색된 글의 갯수
		int official_count = officialsService.selectOffCnt(map);

		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<offical_count>>: " + official_count);
		}

		//페이징 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, official_count, rowCount, pageCount, "officialList.do");

		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<OfficialsCommand> official_list = null;
		if(official_count > 0) {
			official_list = officialsService.selectOffList(map);

			//로그 출력
			if(log.isDebugEnabled()) {
				log.debug("<official_list>: " + official_list);
			} 
		}

		//ModelAndView객체 생성, 데이터 저장
		ModelAndView mav = new ModelAndView();
		mav.setViewName("officialList");
		mav.addObject("official_count", official_count);
		mav.addObject("official_list", official_list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}

	//02_2_전송된 데이터 처리
	@RequestMapping(value="/admin/officialList.do", method=RequestMethod.POST)
	public String official_submit(@ModelAttribute("official_command") @Valid OfficialsCommand officialsCommand, BindingResult result, HttpServletRequest request) {
		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<<OfficialsCommand>>: " + officialsCommand);
		}

		officialsService.insert(officialsCommand);

		return "redirect:/admin/officialList.do";
	}

	//02_3_관계자 상세&수정
	//상세보기&수정폼 호출
	@RequestMapping(value="/admin/offcialDetail.do", method=RequestMethod.GET)
	public ModelAndView off_detail(@RequestParam("off_num") int off_num, Model model) {
		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<<off_num>>: " + off_num);
		}

		OfficialsCommand officials = officialsService.selectOfficials(off_num);
		model.addAttribute("official_command", officials);

		return new ModelAndView("officialDetail", "officials", officials);
	}
	
	//이미지 출력
	@RequestMapping("/admin/off_imgView.do")
	public ModelAndView viewImage(@RequestParam("off_num") int off_num) {
		//한 건의 데이터를 받아 객체 생성
		OfficialsCommand officials = officialsService.selectOfficials(off_num);
				
		//데이터를 ModelAndView객체에 차곡차곡 저장
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
						//속성명		속성값(byte[]의 데이터)
		mav.addObject("filename", "officials.jpg");
		mav.addObject("imageFile", officials.getOff_photo());
				
		return mav;
	}
	
	//수정 폼에서 전송된 데이터 처리
	@RequestMapping(value="/admin/offcialDetail.do", method=RequestMethod.POST)
	public String officials_submit(@ModelAttribute("official_command") @Valid OfficialsCommand officialsCommand, BindingResult result, HttpSession session, HttpServletRequest request) {
		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<<officialsCommand>>: " + officialsCommand);
		}
		
		//관계자 수정
		officialsService.update(officialsCommand);
		
		return "redirect:/admin/officialList.do";
	}

	//02_4_관계자 삭제
	@RequestMapping("/admin/officialDelete.do")
	public String officials_delete(@RequestParam("off_num") int off_num) {
		//로그출력
		if(log.isDebugEnabled()) {
			log.debug("<<off_num>>: " + off_num);
		}
		
		//글 삭제
		officialsService.delete(off_num);
		
		return "redirect:/admin/officialList.do";
	}

	//====================03_영화 관리_영화 장르====================//
	//03_1_장르 목록(페이징&검색)
	@RequestMapping("/admin/genreList.do")
	public ModelAndView genre_list(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
			@RequestParam(value="keyfield", defaultValue="") String keyfield,
			@RequestParam(value="keyword", defaultValue="") String keyword) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//총 글의 갯수 또는 검색된 글의 갯수
		int genre_count = genreService.selectGenreCnt(map);

		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<<genre_count>>: " + genre_count);
		}

		//페이징 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, genre_count, rowCount, pageCount, "genreList.do");

		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<GenreCommand> genre_list = null;
		if(genre_count > 0) {
			genre_list = genreService.selectGenreList(map);

			//로그 출력
			if(log.isDebugEnabled()) {
				log.debug("<genre_list>: " + genre_list);
			} 
		}

		//ModelAndView객체 생성, 데이터 저장
		ModelAndView mav = new ModelAndView();
		mav.setViewName("genreList");
		mav.addObject("genre_count", genre_count);
		mav.addObject("genre_list", genre_list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}


	//03_2_전송된 데이터 처리
	@RequestMapping(value="/admin/genreList.do", method=RequestMethod.POST)
	public String genre_submit(@ModelAttribute("genre_command") @Valid GenreCommand genreCommand, BindingResult result, HttpServletRequest request) {
		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<<genreCommand>>: " + genreCommand);
		}

		genreService.insertGenre(genreCommand);

		return "redirect:/admin/genreList.do";
	}
	
	//03_3_장르 수정
	//상세보기&수정폼 호출
	@RequestMapping(value="/admin/genreDetail.do", method=RequestMethod.GET)
	public ModelAndView genre_detail(@RequestParam("genre_num") int genre_num, Model model) {
		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<<genre_num>>: " + genre_num);
		}

		GenreCommand genre = genreService.selectGenre(genre_num);
		model.addAttribute("genre_command", genre);

		return new ModelAndView("genreDetail", "genre", genre);
	}
	
	//수정 폼에서 전송된 데이터 처리
	@RequestMapping(value="/admin/genreDetail.do", method=RequestMethod.POST)
	public String genre_submit(@ModelAttribute("genre_command") @Valid GenreCommand genreCommand, BindingResult result, HttpSession session, HttpServletRequest request) {
		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<<genreCommand>>: " + genreCommand);
		}
		
		//글 수정
		genreService.updateGenre(genreCommand);
		
		return "redirect:/admin/genreList.do";
	}
	
	//03_4_장르 삭제
	@RequestMapping("/admin/genreDelete.do")
	public String genre_delete(@RequestParam("genre_num") int genre_num) {
		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<<genre_num>>: " + genre_num);
		}
		
		genreService.deleteGenre(genre_num);
		
		return "redirect:/admin/genreList.do";
	}
	
	//장르 선택 삭제
/*	@ResponseBody
	@RequestMapping("check_genreDel.do")
	public c_genreDel(){
		
	}*/

	
	//====================04_영화 관리_영화 별점====================//
	@RequestMapping("/admin/movieRating.do")
	public String process3() {

		return "movieRating";
	}

	//====================05_회원 관리_회원 목록====================//
	//05_1_회원목록&검색
	@RequestMapping("/admin/userList.do")
	public ModelAndView user_list(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
								  @RequestParam(value="keyfield", defaultValue="") String keyfield,
								  @RequestParam(value="keyword", defaultValue="") String keyword) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//총 글의 갯수 또는 검색된 글의 갯수
		int user_count = userService.selectUserCnt(map);

		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<<user_count>>: " + user_count);
		}

		//페이징 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, user_count, rowCount, pageCount, "userList.do");

		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<UserCommand> user_list = null;
		if(user_count > 0) {
			user_list = userService.selectUserList(map);

			//로그 출력
			if(log.isDebugEnabled()) {
				log.debug("<user_list>: " + user_list);
			} 
		}

		//ModelAndView객체 생성, 데이터 저장
		ModelAndView mav = new ModelAndView();
		mav.setViewName("userList");
		mav.addObject("user_count", user_count);
		mav.addObject("user_list", user_list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	
	//====================06_회원 관리_신고 회원====================//
	@RequestMapping("/admin/reportedUser.do")
	public String process5() {

		return "reportedUser";
	}

	//====================07_고객 지원_고객 문의====================//
	@RequestMapping("/admin/support.do")
	public String process6() {

		return "support";
	}
	
	//======이미지 뷰====///
}
