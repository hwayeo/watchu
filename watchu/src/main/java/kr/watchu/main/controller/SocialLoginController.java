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

	//�ڹٺ� �ʱ�ȭ
	@ModelAttribute("userCommand")
	public NaverCommand initCommand() {
		return new NaverCommand();
	}
	@ModelAttribute("naveruserCommand")
	public NaverUserCommand initMovie() {
		return new NaverUserCommand();
	}

	//�α��� ù ȭ�� ��û �޼ҵ�
	@RequestMapping(value = "/user/socialLogin.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session) {
		/* ���̹����̵�� ���� URL�� �����ϱ� ���Ͽ� naverLoginBOŬ������ getAuthorizationUrl�޼ҵ� ȣ�� */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

		if(log.isDebugEnabled()) {
			log.debug("�α��� Ȯ��:" + naverAuthUrl);
		}

		//���̹� 
		model.addAttribute("url", naverAuthUrl);

		/* ������ ���� URL�� View�� ���� */ 
		return "socialLogin";
	}

	//���̹� �α��� ������ callback ȣ�� �޼ҵ� -> �˾�â�� �������� ���� f5�� �ϰų� �ϸ� ���� ȣ���� �������� �ʴ´�. (���ͳ� �ͽ��÷ξ� �����̰� ũ��, ���ĸ������� �˾� x)
	@RequestMapping("/user/loginSuccess.do")
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException {
		OAuth2AccessToken oauthToken;
		/* ���̹� ���̵�� �α��� ������ ������ callbackó���������� AccessToken�� �߱޹��� �� �ִ�. */
		oauthToken = naverLoginBO.getAccessToken(session, code, state);

		if(log.isDebugEnabled()) {
			log.debug("token Ȯ��:" + session + "," + code + "," + state);
		}
		
		//�α��� ����� ������ �о�´�.
		String apiResult = naverLoginBO.getUserProfile(oauthToken);
		model.addAttribute("result", apiResult);
		
		//result�� ������ ���� json �Ľ� �� �ʿ��� ���� �������� �� ��ü���� ����
		ObjectMapper mapper = new ObjectMapper();
		NaverCommand naverCommand = mapper.readValue(apiResult,NaverCommand.class);
		
		//������ ���� Ȯ��
		if(log.isDebugEnabled()) {
			log.debug("<<naverCommand>> : " + naverCommand);
			log.debug("���̵� Ȯ�� : " + naverCommand.getResponse().getId());
		}

		//db���� �� ���̵� �� ��������
		String select_id = naverCommand.getResponse().getId();
		UserCommand user = userService.selectUser(select_id);

		//���̹� �α��� ���� -> db ����
		if(naverCommand.getResponse().getId() != null && !naverCommand.getResponse().getId().equals("")) {
			//������ ���� Ȯ��
			if(log.isDebugEnabled()) {
				log.debug("<<user>> : " + user);
			}
			
			//���� ������ db�� ���̹� �α��� id���� ������ db ����
			if(user != null) {
				session.setAttribute("user_id", naverCommand.getResponse().getId());
				
				return "redirect:/main/main.do";
			}else {
				//id�� ���ٸ� db�� ����
				userService.insertsocialUser(naverCommand.getResponse());
			}
		}else {
			//���� �α����� ���� id���� ����ְų� null�� ��� -> �ٽ� �α���
			return "redirect:/user/login.do";
		}

		//ȸ�������� ���� �� �ٷ� �α��� ó��
		session.setAttribute("user_id", naverCommand.getResponse().getId());
		session.setAttribute("user_auth", 1);

		return "redirect:/main/main.do";
	}
}