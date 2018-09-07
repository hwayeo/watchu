package kr.watchu.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.watchu.user.domain.UserCommand;
import kr.watchu.user.service.UserService;

@Controller
public class userConfirmIdAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/user/confirmId.do")
	@ResponseBody
	public Map<String,String> process(@RequestParam("id") String id){
		
		if(log.isDebugEnabled()) {
			log.debug("<<id>>:" + id);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		UserCommand user = userService.selectUser(id);
		if(user!=null) {
			//아이디 중복
			map.put("result", "idDuplicated");		
		}else {
			//아이디 미중복
			map.put("result", "idNotFound");
		}
		
		return map;
	}
}
