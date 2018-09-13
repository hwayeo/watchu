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
import org.springframework.web.bind.annotation.RequestBody;
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
		
		//���� ��ȭ ��õ
		MovieCommand randomMovie = recommendService.selectRandomMovie();
		//������ ���
		int ranBanner = recommendService.selectRandomBanner();
		//��ü �� ����
		int totalRated = recommendService.selectTotalRated();
		//��ü �� ����
		
		String id = (String)session.getAttribute("user_id");
		if(log.isDebugEnabled()) {
			log.debug("<<User_id>> : " + id);
		}
		
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
		}else {
			//�α����� �ƴҋ�
		}
		mav.setViewName("main");
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
