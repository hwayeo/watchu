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
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;

import kr.watchu.user.domain.NaverCommand;
import kr.watchu.user.domain.NaverLoginBO;
import kr.watchu.user.domain.UserCommand;
import kr.watchu.user.service.UserService;
import kr.watchu.util.CipherTemplate;


@Controller
public class SocialLoginController {
	private Logger log = Logger.getLogger(this.getClass());

	private UserService userService;
	private UserCommand userCommand;
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	//로그인 첫 화면 요청 메소드
	@RequestMapping(value = "/user/socialLogin.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session) {

		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125

		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=mrKzzkQV1xr_Wer32lEt&
		//redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fwatchu%2Fuser%2FloginSuccess.do&state=fea195f7-5847-428b-871b-56b108f130e5
		if(log.isDebugEnabled()) {
			log.debug("로그인 확인:" + naverAuthUrl);
		}

		//네이버 
		model.addAttribute("url", naverAuthUrl);

		/* 생성한 인증 URL을 View로 전달 */
		return "socialLogin";
	}

	//네이버 로그인 성공시 callback 호출 메소드
	@RequestMapping(value = "/user/loginSuccess.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException {
		OAuth2AccessToken oauthToken;
		/* 네이버 아이디로 로그인 인증이 끝나면 callback처리과정에서 AccessToken을 발급받을 수 있다. */
		oauthToken = naverLoginBO.getAccessToken(session, code, state);

		if(log.isDebugEnabled()) {
			log.debug("token 확인:" + session + "," + code + "," + state);
		}

		System.out.println("네이버 로그인이 성공하였습니다."); 

		//로그인 사용자 정보를 읽어온다.
		String apiResult = naverLoginBO.getUserProfile(oauthToken);

		//result로 가져온 값을 json 파싱 후 필요한 값만 가져오게 빈 객체에서 설정
		ObjectMapper mapper = new ObjectMapper();
		NaverCommand naverCommand = mapper.readValue(apiResult,NaverCommand.class);
		
		//가져온 값을 확인
		if(log.isDebugEnabled()) {
			log.debug("<<naverCommand>> : " + naverCommand);
		}
		
		/*
		//id값의 일치 여부를 확인 후 db에 저장
		if() {
			//id가 일치하지 않을 시 
			//id가 일치하지만 auth가 다른 id인지 판별
			
		}else {
			
		}*/
		
		//회원가입이 성공 시 바로 로그인 처리
		session.setAttribute("user_id", naverCommand);
		

		/* 네이버 로그인 성공 페이지 View 호출 */
		return "loginSuccess";
	}
}