package kr.watchu.user.service;

import java.util.List;
import java.util.Map;

import kr.watchu.user.domain.AdminRecontactCommand;
import kr.watchu.user.domain.ContactCommand;

public interface ContactService {
	
	//문의글 등록
	public void insertContact(ContactCommand contact);
	//상세정보
	public ContactCommand selectContact(Integer contact_num);
	//수정
	public void updateContact(ContactCommand contact);
	//삭제
	public void deleteContact(Integer contact_num);

	//목록
	//갯수
	public int selectContactCnt(Map<String, Object> map);
	//리스트
	public List<ContactCommand> selectContactList(Map<String,Object> map);
	
	//답변
	public List<AdminRecontactCommand> selectListReply(Map<String, Object> map);
	public int selectRowCountReply(Map<String, Object> map);
	public void insertReply(AdminRecontactCommand adminRecontactCommand);
	public void updateReply(AdminRecontactCommand adminRecontactCommand);
	public void deleteReply(Integer recontact_num);	
}
