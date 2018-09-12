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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertReply(AdminRecontactCommand adminRecontactCommand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReply(AdminRecontactCommand adminRecontactCommand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReply(Integer recontact_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReplyByNum(Integer contact_num) {
		// TODO Auto-generated method stub
		
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
