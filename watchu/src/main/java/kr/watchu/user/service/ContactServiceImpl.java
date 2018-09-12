package kr.watchu.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.watchu.user.dao.ContactMapper;
import kr.watchu.user.domain.AdminRecontactCommand;
import kr.watchu.user.domain.ContactCommand;

@Service("contactService")
public class ContactServiceImpl implements ContactService {
	
	@Override
	public List<AdminRecontactCommand> selectListReply(Map<String, Object> map) {
		return contactMapper.selectListReply(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return contactMapper.selectRowCountReply(map);
	}

	@Override
	public void insertReply(AdminRecontactCommand adminRecontactCommand) {
		contactMapper.insertReply(adminRecontactCommand);
	}

	@Override
	public void updateReply(AdminRecontactCommand adminRecontactCommand) {
		contactMapper.updateReply(adminRecontactCommand);
	}

	@Override
	public void deleteReply(Integer recontact_num) {
		contactMapper.deleteReply(recontact_num);
	}

	@Resource
	private ContactMapper contactMapper;

	@Override
	public void insertContact(ContactCommand contact) {
		contactMapper.insertContact(contact);
	}

	@Override
	public ContactCommand selectContact(Integer contact_num) {
		return contactMapper.selectContact(contact_num);
	}

	@Override
	public void updateContact(ContactCommand contact) {
		contactMapper.updateContact(contact);
	}

	@Override
	public void deleteContact(Integer contact_num) {
		contactMapper.deleteReplyByNum(contact_num); //글 삭제 전 답변 삭제
		contactMapper.deleteContact(contact_num);
	}

	@Override
	public int selectContactCnt(Map<String, Object> map) {
		return contactMapper.selectContactCnt(map);
	}

	@Override
	public List<ContactCommand> selectContactList(Map<String, Object> map) {
		return contactMapper.selectContactList(map);
	}

}
