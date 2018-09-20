package kr.watchu.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.service.MovieService;
import kr.watchu.movie.service.MovieratedService;
import kr.watchu.movie.service.RecommendService;
import kr.watchu.user.domain.UserCommand;
import kr.watchu.user.service.UserService;
import kr.watchu.util.CipherTemplate;

@Controller
public class MainController {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private UserService userService;

	@Resource
	private MovieService movieService;
	
	@Resource
	private RecommendService recommendService;
	
	@Resource
	private MovieratedService movieratedService;
	@Resource
	private CipherTemplate cipherAES;
	
	//자바빈 초기화
	@ModelAttribute("command")
	public UserCommand initCommand() {
		return new UserCommand();
	}
	@ModelAttribute("movieCommand")
	public MovieCommand initMovie() {
		return new MovieCommand();
	}
	
	@RequestMapping("/main/main.do")
	public ModelAndView process(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		//랜덤 영화 추천
		MovieCommand randomMovie = recommendService.selectRandomMovie();
		//무작위 배너
		int ranBanner = recommendService.selectRandomBanner();
		//전체 평가 갯수
		int totalRated = recommendService.selectTotalRated();
		session.setAttribute("totalRated", totalRated);
		//전체 평가 갯수
		
		String id = (String)session.getAttribute("user_id");
		
		if(log.isDebugEnabled()) {
			log.debug("<<User_id>> : " + id);
		}
		List<MovieCommand> ranGenreMovie= null;
		MovieCommand ranActorMovie= null;
		MovieCommand ranActorMovie2= null;
		MovieCommand ranActorMovie3= null;
		//로그인 상태일때
		Integer rateCnt = 0;	
		if(id!=null) {
			rateCnt = movieratedService.selectCheckRated(id);
		}
		
		if(id!=null && rateCnt > 0) {
			Map<String,Object> f_genre = new HashMap<String, Object>();
			float tendency = recommendService.selectAvgTotalMovie(id);
			if(log.isDebugEnabled()) {
				log.debug("<<tendency>> : " + tendency);
			}
			
			f_genre.put("tendency", tendency);
			f_genre.put("id",id);
			String favorite_genre = recommendService.selectFavoriteGenre(f_genre);
			
			if(favorite_genre !=null) {
				f_genre.put("count", 3);
				f_genre.put("keyfield", "genre");
				f_genre.put("keyword", favorite_genre);
				List<MovieCommand> recGenreMovie = recommendService.selectRecommendList(f_genre);
				mav.addObject("recGenre",favorite_genre);
				mav.addObject("recGenreMovie",recGenreMovie);
			}
			/*선호하는 장르 추천*/
			/*선호하는 배우 추천*/
			Map<String,Object> f_actor = new HashMap<String,Object>();
			f_actor.put("jobs", "ACTOR");
			f_actor.put("id", id);
			f_actor.put("count", 1);
			String favorite_actor = recommendService.selectRecommendOff(f_actor);
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("id", id);
			data.put("keyfield", "actors");
			data.put("keyword", favorite_actor);
			data.put("count", 3);
			List<MovieCommand> f_actor_list = recommendService.selectRecommendList(data);
			
			if(log.isDebugEnabled()) {
				log.debug("<<<추천된 영화 수 >>> : " + f_actor_list.size()); 
			}
			if(f_actor_list.size() < 3) {
				Map<String,Object> reData = new HashMap<String,Object>();
				reData.put("id",id);
				reData.put("count", 3);
				List<MovieCommand> reDataList = recommendService.selectRecommendList(reData);
				mav.addObject("recActor","reData");
				mav.addObject("recActorMovie",reDataList);
				if(log.isDebugEnabled()) {
					log.debug("[[----reDataMovie----]] : " + reDataList);
				}
			}else {
				mav.addObject("recActor",favorite_actor);
				mav.addObject("recActorMovie",f_actor_list);
				if(log.isDebugEnabled()) {
					log.debug("[[----선호배우영화----]] : " + favorite_actor);
					log.debug("[[----recActorMovie----]] : " + f_actor_list);
				}
			}
		}else {
			if(log.isDebugEnabled()) {
				log.debug("[[-----비로그인------]] : ");
			}
			//로그인이 아닐떄
			Map<String,Object> map = new HashMap<String,Object>();
			String ranGenre = recommendService.selectRanGenre();
			if(log.isDebugEnabled()) {
				log.debug("<<<<ranGenre>>>> : " + ranGenre);
			}
			map.put("genre", ranGenre);
			map.put("count", 3);
			ranGenreMovie = recommendService.selectRanGenreMovieList(map);
			if(log.isDebugEnabled()) {
				log.debug("<<<<ranGenreMovie>>>> : " + ranGenreMovie);
			}
			mav.addObject("ranGenre",ranGenre);
			
			Map<String,Object> map2 = new HashMap<String,Object>();
			map2.put("jobs", "ACTOR");
			map2.put("rate", 3.0);
			
			String actor = recommendService.selectRanOff(map2);
			if(log.isDebugEnabled()) {
				log.debug("<<<<actor1>>>> : " + actor);
			}
			mav.addObject("ranActor1",actor);
			
			ranActorMovie = recommendService.selectRanOffMovie(actor);
			if(log.isDebugEnabled()) {
				log.debug("<<<<ranActorMovie1>>>> : " + ranActorMovie);
			}
			
			Map<String,Object> map3 = new HashMap<String,Object>();
			map3.put("jobs", "ACTOR");
			map3.put("rate", 3.0);
			
			String actor2 = recommendService.selectRanOff(map3);
			if(log.isDebugEnabled()) {
				log.debug("<<<<actor>>>> : " + actor2);
			}
			mav.addObject("ranActor2",actor2);
			
			ranActorMovie2 = recommendService.selectRanOffMovie(actor2);
			
			if(log.isDebugEnabled()) {
				log.debug("<<<<ranActorMovie2>>>> : " + ranActorMovie2);
			}
			
			Map<String,Object> map4 = new HashMap<String,Object>();
			map4.put("jobs", "ACTOR");
			map4.put("rate", 1.5);
			String actor3 = recommendService.selectRanOff(map4);
			if(log.isDebugEnabled()) {
				log.debug("<<<<actor3>>>> : " + actor3);
			}
			mav.addObject("ranActor3",actor3);
			
			ranActorMovie3 = recommendService.selectRanOffMovie(actor3);
			if(log.isDebugEnabled()) {
				log.debug("<<<<ranActorMovie3>>>> : " + ranActorMovie3);
			}
			
			Map<String,Object> direc = new HashMap<String,Object>();
			direc.put("jobs", "DIRECTOR");
			direc.put("rate", 2.5);
			String director1 = recommendService.selectRanOff(direc);
			if(log.isDebugEnabled()) {
				log.debug("<<<<director1>>>> : " + director1);
			}
			mav.addObject("ranDirector1",director1);
			
			MovieCommand ranDirectorMovie1 = recommendService.selectRanOffMovie(director1);
			if(log.isDebugEnabled()) {
				log.debug("<<<<ranDirectorMovie1>>>> : " + ranDirectorMovie1);
			}
			mav.addObject("ranDirectorMovie1",ranDirectorMovie1);
			
			Map<String,Object> direc2 = new HashMap<String,Object>();
			direc2.put("jobs", "DIRECTOR");
			direc2.put("rate", 2.0);
			String director2 = recommendService.selectRanOff(direc2);
			if(log.isDebugEnabled()) {
				log.debug("<<<<director2>>>> : " + director2);
			}
			mav.addObject("ranDirector2",director2);
			
			MovieCommand ranDirectorMovie2 = recommendService.selectRanOffMovie(director2);
			if(log.isDebugEnabled()) {
				log.debug("<<<<ranDirectorMovie2>>>> : " + ranDirectorMovie2);
			}
			mav.addObject("ranDirectorMovie2",ranDirectorMovie2);
			
			Map<String,Object> direc3 = new HashMap<String,Object>();
			direc3.put("jobs", "DIRECTOR");
			direc3.put("rate", 1.5);
			String director3 = recommendService.selectRanOff(direc3);
			if(log.isDebugEnabled()) {
				log.debug("<<<<director3>>>> : " + director3);
			}
			mav.addObject("ranDirector3",director3);
			
			MovieCommand ranDirectorMovie3 = recommendService.selectRanOffMovie(director3);
			if(log.isDebugEnabled()) {
				log.debug("<<<<ranDirectorMovie3>>>> : " + ranDirectorMovie3);
			}
			mav.addObject("ranDirectorMovie3",ranDirectorMovie3);
			
		}
		mav.addObject("ranGenreMovie",ranGenreMovie);
		mav.addObject("ranActorMovie",ranActorMovie);
		mav.addObject("ranActorMovie2",ranActorMovie2);
		mav.addObject("ranActorMovie3",ranActorMovie3);
		mav.addObject("totalRated",totalRated);
		mav.addObject("randomMovie",randomMovie);
		mav.addObject("ranBanner",ranBanner);
		
		return mav;
	}

	//로그인 폼 호출
	@RequestMapping(value="/user/login.do",method=RequestMethod.GET)
	public String loginForm() {
		return "userLogin";
	}
	//로그인
	@RequestMapping(value="/user/login.do",method=RequestMethod.POST)
	public String submitLogin(@ModelAttribute("command") 
							  @Valid UserCommand userCommand, 
							  BindingResult result, HttpSession session) {
	    
		if(log.isDebugEnabled()) {
			log.debug("<<userCommand>> : " + userCommand);
		}
		
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return loginForm();
		}
		
		try {
			UserCommand user = userService.selectUser(userCommand.getId());
			
			boolean check = false;
			if(user!=null) {
				//비밀번호 일치 여부 체크
				check = user.isCheckedPasswd(cipherAES.encrypt(userCommand.getPasswd()));
				if(log.isDebugEnabled()) {
					log.debug("<<일치여부>> : " + check);
				}
			}
				
			if(check) {
				//인증 성공 로그인
				session.setAttribute("user_id", user.getId());
				session.setAttribute("user_auth", user.getAuth());
				if(user.getProfile_img() == null) {
					session.setAttribute("profile", null);
				}else if(user.getProfile_img() != null) {
					session.setAttribute("profile", "found");
				}
				if(log.isDebugEnabled()) {
					log.debug("<<인증 성공>>");
					log.debug("<<user_id>> : " + user.getId());
					log.debug("<<user_auth>> : " + user.getAuth());
				}
				
				if(user.getAuth() == 6) {
					return "admin";
				}
				return "redirect:/main/main.do";
			}else {
				//인증실패 
				throw new Exception();
			}
		}catch(Exception e){
			//인증 실패
			result.reject("invalidIdOrPasswd");
			
			if(log.isDebugEnabled()) {
				log.debug("<<인증 실패>>");
			}
			
			return loginForm();
		}
	}
	//로그아웃
	@RequestMapping("/user/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main/main.do";
	}

	//=========이미지 뷰========//
	@RequestMapping("/main/imageView.do")
	public ModelAndView viewImage(@RequestParam("id") String id) {
		
		UserCommand user = userService.selectUser(id);
		if(log.isDebugEnabled()) {
			log.debug("<<profile_img>> : " + user.getProfile_img());
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("filename","profile.jpg");
		mav.addObject("imageFile", user.getProfile_img());
		return mav; 
	}
	
	@RequestMapping("/main/bannerView.do")
	public ModelAndView viewImage(@RequestParam("movie_num") Integer movie_num) {
		ModelAndView mav = new ModelAndView();
		
		MovieCommand movie = movieService.selectMovie(movie_num);
		
		mav.setViewName("imageView");
		mav.addObject("filename","banner.jpg");
		mav.addObject("imageFile",movie.getBanner_img());
		
		return mav; 
	}
	
	@RequestMapping("/main/search.do")
	public ModelAndView search(@RequestParam(value="keyword",defaultValue="") String keyword) {
		ModelAndView mav = new ModelAndView();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("start", 1);
		map.put("end", 4);
		map.put("keyfield", "title");
		
		//검색된 영화
		
		List<MovieCommand> movieList = movieService.selectMovieAjaxList2(map);
		mav.setViewName("result");
		mav.addObject("movieList", movieList);
		return mav;
	}
	
	@RequestMapping("/main/autoComplete.do")
	@ResponseBody
	public Map<String,Object> autoSearch(@RequestParam(value="keyword",defaultValue="") String keyword,
								   @RequestParam("keyfield") String keyfield) {
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("keyword", keyword);
		data.put("keyfield", keyfield);
		data.put("start", 1);
		data.put("end", 20);
		
		if(!keyword.equals("")) {
			List<MovieCommand> list = movieService.selectMovieAjaxList2(data);
			jsonMap.put("list", list);
		}
		
		return jsonMap;
	}
	
	
}
