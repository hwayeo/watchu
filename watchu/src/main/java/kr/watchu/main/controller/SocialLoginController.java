package kr.watchu.main.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;

import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.user.domain.NaverCommand;
import kr.watchu.user.domain.NaverLoginBO;
import kr.watchu.user.domain.NaverUserCommand;
import kr.watchu.user.domain.UserCommand;
import kr.watchu.user.service.UserService;
import kr.watchu.util.CipherTemplate;

@Controller
public class SocialLoginController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private UserService userService;
	private UserCommand userCommand;
	private NaverCommand naverCommand;
	private NaverUserCommand naveruserCommand;
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	//자바빈 초기화
	@ModelAttribute("userCommand")
	public NaverCommand initCommand() {
		return new NaverCommand();
	}
	@ModelAttribute("naveruserCommand")
	public NaverUserCommand initMovie() {
		return new NaverUserCommand();
	}

	//로그인 첫 화면 요청 메소드
	@RequestMapping(value = "/user/socialLogin.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session) {
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

		if(log.isDebugEnabled()) {
			log.debug("로그인 확인:" + naverAuthUrl);
		}

		//네이버 
		model.addAttribute("url", naverAuthUrl);

		/* 생성한 인증 URL을 View로 전달 */ 
		return "socialLogin";
	}

	//네이버 로그인 성공시 callback 호출 메소드 -> 팝업창을 기준으로 만들어서 f5를 하거나 하면 값의 호출이 유지되지 않는다. (인터넷 익스플로어 기준이고 크롬, 사파리에서는 팝업 x)
	@RequestMapping("/user/loginSuccess.do")
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException {
		OAuth2AccessToken oauthToken;
		/* 네이버 아이디로 로그인 인증이 끝나면 callback처리과정에서 AccessToken을 발급받을 수 있다. */
		oauthToken = naverLoginBO.getAccessToken(session, code, state);

		if(log.isDebugEnabled()) {
			log.debug("token 확인:" + session + "," + code + "," + state);
		}
		
		//로그인 사용자 정보를 읽어온다.
		String apiResult = naverLoginBO.getUserProfile(oauthToken);
		model.addAttribute("result", apiResult);
		
		//result로 가져온 값을 json 파싱 후 필요한 값만 가져오게 빈 객체에서 설정
		ObjectMapper mapper = new ObjectMapper();
		NaverCommand naverCommand = mapper.readValue(apiResult,NaverCommand.class);
		
		//가져온 값을 확인
		if(log.isDebugEnabled()) {
			log.debug("<<naverCommand>> : " + naverCommand);
			log.debug("아이디 확인 : " + naverCommand.getResponse().getId());
		}

		//db연동 후 아이디 값 가져오기
		String select_id = naverCommand.getResponse().getId();
		UserCommand user = userService.selectUser(select_id);

		//네이버 로그인 실행 -> db 저장
		if(naverCommand.getResponse().getId() != null && !naverCommand.getResponse().getId().equals("")) {
			//가져온 값을 확인
			if(log.isDebugEnabled()) {
				log.debug("<<user>> : " + user);
			}
			
			//기존 데이터 db에 네이버 로그인 id값이 없으면 db 저장
			if(user != null) {
				session.setAttribute("user_id", naverCommand.getResponse().getId());
				
				return "redirect:/main/main.do";
			}else {
				//id가 없다면 db에 저장
				userService.insertsocialUser(naverCommand.getResponse());
			}
		}else {
			//만약 로그인한 값의 id값이 비어있거나 null일 경우 -> 다시 로그인
			return "redirect:/user/login.do";
		}

		//회원가입이 성공 시 바로 로그인 처리
		session.setAttribute("user_id", naverCommand.getResponse().getId());
		session.setAttribute("user_auth", 1);

		return "redirect:/main/main.do";
	}
}