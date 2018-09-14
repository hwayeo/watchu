package kr.watchu.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.watchu.user.domain.AdminRecontactCommand;
import kr.watchu.user.domain.ContactCommand;

public interface ContactMapper {
	//문의글 등록
	@Insert("INSERT INTO user_contact (contact_num,id,content,filename,upload_file,reg_date,title,category) VALUES (contact_seq.nextval,#{id},#{content},#{filename},#{upload_file},SYSDATE,#{title},#{category})")
	public void insertContact(ContactCommand contact);
	//상세정보
	@Select("SELECT * FROM user_contact WHERE contact_num=#{contact_num}")
	public ContactCommand selectContact(Integer contact_num);
	//수정
	@Update("UPDATE user_contact SET title=#{title},content=#{content},upload_file=#{upload_file},filename=#{filename},category=#{category} WHERE contact_num=#{contact_num}")

	public void updateContact(ContactCommand contact);
	//삭제
	@Delete("DELETE FROM user_contact WHERE contact_num=#{contact_num}")
	public void deleteContact(Integer contact_num);
	
	//목록
	//갯수
	public int selectContactCnt(Map<String, Object> map);
	//리스트
	public List<ContactCommand> selectContactList(Map<String,Object> map);
	
	//======답변=====//
	//@Select("SELECT * FROM admin_recontact WHERE contact_num=${contact_num} ORDER BY reg_date DESC")
	public List<AdminRecontactCommand> selectListReply(Map<String, Object> map);
	@Select("SELECT COUNT(*) FROM admin_recontact WHERE contact_num=#{contact_num}")
	public int selectRowCountReply(Map<String, Object> map);
	@Insert("INSERT INTO admin_recontact (recontact_num, contact_num, recontent, reg_date) VALUES (recontact_seq.nextval, #{contact_num}, #{recontent}, SYSDATE)")
	public void insertReply(AdminRecontactCommand adminRecontactCommand);
	@Update("UPDATE admin_recontact SET recontent=#{recontent} WHERE recontact_num=#{recontact_num}")
	public void updateReply(AdminRecontactCommand adminRecontactCommand);
	@Delete("DELETE FROM admin_recontact WHERE recontact_num=#{recontact_num}")
	public void deleteReply(Integer recontact_num);
	@Delete("DELETE FROM admin_recontact WHERE contact_num=#{contact_num}")
	public void deleteReplyByNum(Integer contact_num);
}
