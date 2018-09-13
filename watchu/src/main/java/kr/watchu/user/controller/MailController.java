package kr.watchu.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.watchu.user.domain.UserCommand;
import kr.watchu.user.service.MailService;
import kr.watchu.user.service.UserService;

@Controller
public class MailController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private UserService userService;
	@Resource
	private MailService mailService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	// 회원가입 이메일 인증
	@RequestMapping(value="/user/mail.do")
	@ResponseBody
	public Map<String,Object> sendMailAuth(HttpSession session, @RequestParam("id") String id, @RequestParam("email") String email) {
		int ran = new Random().nextInt(100000) + 10000; // 인증코드 랜덤값 : 10000 ~ 99999
		
		//인증코드
		String randomCode = String.valueOf(ran);

		//session에 인증코드 저장
		session.setAttribute("joinCode",randomCode);
		
		//메일 양식
		String subject = "Watchu 회원가입 인증 코드 발급 안내 입니다.";
		StringBuilder sb = new StringBuilder();
		sb.append("안녕하세요 " + id + " 회원님, 귀하의 인증 코드는 " + randomCode + " 입니다.");
		
		//메일 보내기
		mailService.send(subject, sb.toString(),"kth19930527@gmail.com",email,null);

		//json값이 넘어오는 것을 선언
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		map.put("email",email);
		
		
		return map;
	}
	
	@RequestMapping(value="/user/checkPermit.do")
	@ResponseBody
	public Map<String,Object> sendPermit(HttpSession session, @RequestParam("permit") String permit){
		Map<String,Object> resultMap = new HashMap<String,Object>();	
		
		//session값 가져오기
		String joinCode = (String)session.getAttribute("joinCode");
		
		//가져온 인증코드 값과 메일로 보낸 인증코드 값 비교
		if(permit.equals(joinCode)){
			resultMap.put("result","success");
		}else {
			resultMap.put("result","failure");
		}
		
		if(log.isDebugEnabled()) {
			log.debug("<<permit>> : " + permit);
			log.debug("joinCode : " + joinCode);
		}

		return resultMap;
	}
}
