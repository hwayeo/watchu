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
	private CipherTemplate cipherAES;
	
	//�ڹٺ� �ʱ�ȭ
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
		//���� ��ȭ ��õ
		MovieCommand randomMovie = recommendService.selectRandomMovie();
		//������ ���
		int ranBanner = recommendService.selectRandomBanner();
		//��ü �� ����
		int totalRated = recommendService.selectTotalRated();
		session.setAttribute("totalRated", totalRated);
		//��ü �� ����
		
		String id = (String)session.getAttribute("user_id");
		
		if(log.isDebugEnabled()) {
			log.debug("<<User_id>> : " + id);
		}
		List<MovieCommand> ranGenreMovie= null;
		MovieCommand ranActorMovie= null;
		MovieCommand ranActorMovie2= null;
		MovieCommand ranActorMovie3= null;
		//�α��� �����϶�
		if(id!=null) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", id);
			map.put("genre", "�׼�");
			if(log.isDebugEnabled()) {
				log.debug("[[-----����------]] : ");
			}
			float prediction = recommendService.selectPredictionByGenre(map);
			if(log.isDebugEnabled()) {
				log.debug("[[-----prediction------]] : " + prediction);
			}
		}else  {
			if(log.isDebugEnabled()) {
				log.debug("[[-----��α���------]] : ");
			}
			//�α����� �ƴҋ�
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
			map4.put("rate", 2.0);
			String actor3 = recommendService.selectRanOff(map4);
			if(log.isDebugEnabled()) {
				log.debug("<<<<actor3>>>> : " + actor3);
			}
			mav.addObject("ranActor3",actor3);
			
			ranActorMovie3 = recommendService.selectRanOffMovie(actor3);
			if(log.isDebugEnabled()) {
				log.debug("<<<<ranActorMovie3>>>> : " + ranActorMovie3);
			}
			
		}
		mav.addObject("ranGenreMovie",ranGenreMovie);
		mav.addObject("ranActorMovie",ranActorMovie);
		mav.addObject("ranActorMovie2",ranActorMovie3);
		mav.addObject("ranActorMovie2",ranActorMovie3);
		mav.addObject("totalRated",totalRated);
		mav.addObject("randomMovie",randomMovie);
		mav.addObject("ranBanner",ranBanner);
		
		return mav;
	}

	//�α��� �� ȣ��
	@RequestMapping(value="/user/login.do",method=RequestMethod.GET)
	public String loginForm() {
		return "userLogin";
	}
	//�α���
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
				//��й�ȣ ��ġ ���� üũ
				check = user.isCheckedPasswd(cipherAES.encrypt(userCommand.getPasswd()));
				if(log.isDebugEnabled()) {
					log.debug("<<��ġ����>> : " + check);
				}
			}
				
			if(check) {
				//���� ���� �α���
				session.setAttribute("user_id", user.getId());
				session.setAttribute("user_auth", user.getAuth());
				if(user.getProfile_img() == null) {
					session.setAttribute("profile", null);
				}else if(user.getProfile_img() != null) {
					session.setAttribute("profile", "found");
				}
				if(log.isDebugEnabled()) {
					log.debug("<<���� ����>>");
					log.debug("<<user_id>> : " + user.getId());
					log.debug("<<user_auth>> : " + user.getAuth());
				}
				
				if(user.getAuth() == 6) {
					return "admin";
				}
				return "redirect:/main/main.do";
			}else {
				//�������� 
				throw new Exception();
			}
		}catch(Exception e){
			//���� ����
			result.reject("invalidIdOrPasswd");
			
			if(log.isDebugEnabled()) {
				log.debug("<<���� ����>>");
			}
			
			return loginForm();
		}
	}
	//�α׾ƿ�
	@RequestMapping("/user/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main/main.do";
	}

	//=========�̹��� ��========//
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
		
		//�˻��� ��ȭ
		
		List<MovieCommand> movieList = movieService.selectMovieList(map);
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
			List<MovieCommand> list = movieService.selectMovieList(data);
			jsonMap.put("list", list);
		}
		
		return jsonMap;
	}
	
	
}
