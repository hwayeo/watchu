package kr.watchu.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.watchu.movie.domain.CommentCommand;
import kr.watchu.user.domain.UserCommand;
 
public interface UserMapper {
	//회원등록
	@Insert("INSERT INTO user_basic (id,auth,permit) VALUES (#{id},1,#{permit})")
	public void insertUser(UserCommand user);
	//상세정보등록
	@Insert("INSERT INTO user_info (id,passwd,name,phone,email,profile_img,reg_date) VALUES (#{id},#{passwd},#{name},#{phone},#{email},#{profile_img},SYSDATE)")
	public void insertUserDetail(UserCommand user);
	
	//회원상세정보확인
	@Select("select * from user_basic b left outer join user_info i on b.id=i.id left outer join user_relation c on b.id=c.id where i.id=#{id}")
	public UserCommand selectUser(String id);
	//수정
	@Update("update user_info set passwd=#{passwd},name=#{name},phone=#{phone},email=#{email},profile_img=#{profile_img} where id=#{id}")
	public void updateUser(UserCommand user);
	
	//삭제
	@Delete("delete from user_basic where id=#{id}")
	public void deleteUser(String id);
	@Delete("delete from user_info where id=#{id}")
	public void deleteUserDetail(String id);
	@Delete("delete from user_relation where id=#{id}")
	public void deleteUserRelation(String id);
	
	//친구목록
	public int selectUserCnt(Map<String, Object> map);	
	public List<UserCommand> selectUserList(Map<String,Object> map);
	
	//친구관계(팔로우,팔로워,블락)
	//회원가입시 user_relation 테이블에 등록
	@Insert("INSERT INTO user_relation (id) VALUES (#{id})")
	public void insertRelation(String id);
	//내 팔로우에 상대방 추가
	@Update("update user_relation set follow=#{follow} where id=#{id}")
	public void insertFollow(UserCommand user);
	//상대방 팔로워에 나 추가
	@Update("update user_relation set follower=#{follower} where id=#{id}")
	public void insertFollower(UserCommand user);
	//블락 추가
	@Update("update user_relation set block=#{block} where id=#{id}")
	public void insertBlock(UserCommand user);
	
	
	// 목록
	@Select("SELECT * FROM movie_comment b left outer join user_info i on b.id=i.id WHERE i.id=#{id}")
	public List<CommentCommand> CommentList(String id);
}
