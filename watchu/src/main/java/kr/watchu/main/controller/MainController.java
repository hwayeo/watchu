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
	public ModelAndView process() {
		ModelAndView mav = new ModelAndView();
		
		//랜덤 영화 추천 
		Map<String,Object> map = new HashMap<String,Object>();
		int totalMovieCnt = movieService.selectMovieCnt(map);
		
		int ranMovie = (int) ((Math.random()*totalMovieCnt)+1);
		
		if(log.isDebugEnabled()) {
			log.debug("<<totalMovieCnt>> : " + totalMovieCnt);
			log.debug("<<ranMovie>> : " + ranMovie);
		}
		
		MovieCommand randomMovie = movieService.selectMovie(ranMovie);
		
		mav.setViewName("main");
		mav.addObject("randomMovie",randomMovie);
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
	
/*	@RequestMapping("/main/imageView.do")
	public ModelAndView viewImage(@RequestParam("movie_num") Integer movie_num) {
		
		if(log.isDebugEnabled()) {
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("filename","profile.jpg");
		mav.addObject("imageFile");
		return mav; 
	}*/
	
	/*@RequestMapping("/main/search.do")
	public ModelAndView search(@RequestParam(value="keyword",defaultValue="") String keyword) {
		ModelAndView mav = new ModelAndView();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword",keyword);
		
		List<MovieCommand> movieList = movieService.selectMovieList(map);
		
		mav.setViewName("result");
		mav.addObject("movieList", movieList);
		return mav;
	}*/
	
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
