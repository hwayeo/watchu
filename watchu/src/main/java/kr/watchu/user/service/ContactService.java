package kr.watchu.user.service;

import java.util.List;
import java.util.Map;

import kr.watchu.user.domain.AdminRecontactCommand;
import kr.watchu.user.domain.ContactCommand;

public interface ContactService {
	
	//���Ǳ� ���
	public void insertContact(ContactCommand contact);
	//������
	public ContactCommand selectContact(Integer contact_num);
	//����
	public void updateContact(ContactCommand contact);
	//����
	public void deleteContact(Integer contact_num);

	//���
	//����
	public int selectContactCnt(Map<String, Object> map);
	//����Ʈ
	public List<ContactCommand> selectContactList(Map<String,Object> map);
	
	//�亯
	public List<AdminRecontactCommand> selectListReply(Map<String, Object> map);
	public int selectRowCountReply(Map<String, Object> map);
	public void insertReply(AdminRecontactCommand adminRecontactCommand);
	public void updateReply(AdminRecontactCommand adminRecontactCommand);
	public void deleteReply(Integer recontact_num);	
}
