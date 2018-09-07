package kr.watchu.user.service;

import java.util.List;
import java.util.Map;

import kr.watchu.movie.domain.CommentCommand;
import kr.watchu.user.domain.UserCommand;

public interface UserService {
	//회원등록
	public void insertUser(UserCommand user);
	//상세정보보기
	public UserCommand selectUser(String id); 
	//수정
	public void updateUser(UserCommand user);
	//삭제
	public void deleteUser(String id);
	//목록
	public int selectUserCnt(Map<String, Object> map);
	public List<UserCommand> selectUserList(Map<String,Object> map);
	//팔로우,팔로워,블락
	public void insertFollow(UserCommand user);
	public void insertFollower(UserCommand user);
	public void insertBlock(UserCommand user);
	
	// 목록
	public List<CommentCommand> CommentList(String id);

}
