package kr.watchu.user.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.watchu.movie.domain.CommentCommand;
import kr.watchu.user.domain.NaverUserCommand;
import kr.watchu.user.domain.UserCommand;

public interface UserService {
	//ȸ�����
	public void insertUser(UserCommand user);
	//����������
	public UserCommand selectUser(String id); 
	
	//social ȸ�����
	public void insertsocialUser(NaverUserCommand nuser);
	//social ����������
	public NaverUserCommand selectsocialUser(String id);
	
	//����
	public void updateUser(UserCommand user);
	//����
	public void deleteUser(String id);
	//��õģ�����	
	public List<String> selectUserId();//�ܼ�ȸ����ü���(���̵�,auth��)	
	public int selectUserCnt(Map<String, Object> map);
	public List<UserCommand> selectUserList(Map<String,Object> map);
	
	//�ȷο�,�ȷο�,��� ���
	public int selectfollowCnt(Map<String, Object> map);
	public List<UserCommand> selectfollowList(Map<String,Object> map);
	
	//������ ȸ�� ���� ����
	public void adminUpdate(UserCommand user);
	public void adminUpdate2(UserCommand user);
	
	//�ȷο�,�ȷο�,���
	public void insertFollow(UserCommand user);
	public void insertFollower(UserCommand user);
	public void insertBlock(UserCommand user);
	
	// ���
	public List<CommentCommand> CommentList(String id);

}
