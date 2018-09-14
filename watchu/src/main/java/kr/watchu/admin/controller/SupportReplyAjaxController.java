package kr.watchu.admin.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.watchu.user.domain.AdminRecontactCommand;
import kr.watchu.user.service.ContactService;
import kr.watchu.util.PagingUtil;

@Controller
public class SupportReplyAjaxController {
	//로그 설정
	private Logger log = Logger.getLogger(this.getClass());
		
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private ContactService contactService;
	
	//=============== 답변 등록 ===============//
	@RequestMapping("/admin/writeReply.do")
	@ResponseBody
	public Map<String, String> writeReply(AdminRecontactCommand adminRecontactCommand, HttpSession session, HttpServletRequest request){
		if(log.isDebugEnabled()) {
			log.debug("<<AdminRecontactCommand>>: " + adminRecontactCommand);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		
		String user_id = (String)session.getAttribute("user_id");
		if(user_id == null) {
			map.put("result", "logout");
		}else {
			contactService.insertReply(adminRecontactCommand);
			map.put("result", "success");
		}
		return map;
	}
	 
	//=============== 답변 목록 ===============//
	@RequestMapping("/admin/listReply.do")
	@ResponseBody
	public Map<String, Object> getList(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
									   @RequestParam("contact_num") int contact_num){
		//로그 출력
		if(log.isDebugEnabled()) {
			log.debug("<<currentPage>>: " + currentPage);
			log.debug("<<contact_num>>: " + contact_num); 
		}
		
		//Map객체 생성
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contact_num", contact_num);
		
		//총 글의 갯수
		int count = contactService.selectRowCountReply(map);
		
		//페이징 유틸을 이용하여 startCount, endCount구함
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List <AdminRecontactCommand> list = null;
		if(count > 0) {
			list = contactService.selectListReply(map);
			if(log.isDebugEnabled()) {
				log.debug("<<list>>: " + list);
			}
		}else {
			list = Collections.emptyList();
		}
		
		//json 문자열 생성
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);
				
		return mapJson;
	}
	
	//=============== 답변 삭제 ===============//
	@RequestMapping("/admin/deleteReply.do")
	@ResponseBody
	public Map<String, String> deleteReply(@RequestParam("recontact_num") int recontact_num, HttpSession session){
		if(log.isDebugEnabled()) {
			log.debug("<<recontact_num>>: " + recontact_num);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		
		String user_id = (String)session.getAttribute("user_id");
		if(user_id == null) {
			map.put("result", "logout");
		}else if(user_id != null) {
			contactService.deleteReply(recontact_num);
			map.put("result", "success");
		}
		
		return map;
	}
	
	//=============== 답변 수정 ===============//
	@RequestMapping("/admin/updateReply.do")
	@ResponseBody
	public Map<String, String> modifyReply(AdminRecontactCommand adminRecontactCommand, HttpSession session, HttpServletRequest request){
		if(log.isDebugEnabled()) {
			log.debug("<<adminRecontactCommand>>: " + adminRecontactCommand);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		String user_id = (String)session.getAttribute("user_id");
		if(user_id == null) {
			//로그인이 안되어 있는 경우
			map.put("result", "logout");
		}else if(user_id != null){
			contactService.updateReply(adminRecontactCommand);
			map.put("result", "success");
		}
		
		return map;
	}
}
