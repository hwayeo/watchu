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

	//�α��� ù ȭ�� ��û �޼ҵ�
	@RequestMapping(value = "/user/socialLogin.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session) {

		/* ���̹����̵�� ���� URL�� �����ϱ� ���Ͽ� naverLoginBOŬ������ getAuthorizationUrl�޼ҵ� ȣ�� */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125

		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=mrKzzkQV1xr_Wer32lEt&
		//redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fwatchu%2Fuser%2FloginSuccess.do&state=fea195f7-5847-428b-871b-56b108f130e5
		if(log.isDebugEnabled()) {
			log.debug("�α��� Ȯ��:" + naverAuthUrl);
		}

		//���̹� 
		model.addAttribute("url", naverAuthUrl);

		/* ������ ���� URL�� View�� ���� */
		return "socialLogin";
	}

	//���̹� �α��� ������ callback ȣ�� �޼ҵ�
	@RequestMapping(value = "/user/loginSuccess.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException {
		OAuth2AccessToken oauthToken;
		/* ���̹� ���̵�� �α��� ������ ������ callbackó���������� AccessToken�� �߱޹��� �� �ִ�. */
		oauthToken = naverLoginBO.getAccessToken(session, code, state);

		if(log.isDebugEnabled()) {
			log.debug("token Ȯ��:" + session + "," + code + "," + state);
		}

		System.out.println("���̹� �α����� �����Ͽ����ϴ�."); 

		//�α��� ����� ������ �о�´�.
		String apiResult = naverLoginBO.getUserProfile(oauthToken);

		//result�� ������ ���� json �Ľ� �� �ʿ��� ���� �������� �� ��ü���� ����
		ObjectMapper mapper = new ObjectMapper();
		NaverCommand naverCommand = mapper.readValue(apiResult,NaverCommand.class);
		
		//������ ���� Ȯ��
		if(log.isDebugEnabled()) {
			log.debug("<<naverCommand>> : " + naverCommand);
		}
		
		/*
		//id���� ��ġ ���θ� Ȯ�� �� db�� ����
		if() {
			//id�� ��ġ���� ���� �� 
			//id�� ��ġ������ auth�� �ٸ� id���� �Ǻ�
			
		}else {
			
		}*/
		
		//ȸ�������� ���� �� �ٷ� �α��� ó��
		session.setAttribute("user_id", naverCommand);
		

		/* ���̹� �α��� ���� ������ View ȣ�� */
		return "loginSuccess";
	}
}