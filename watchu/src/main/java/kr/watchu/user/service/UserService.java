package kr.watchu.user.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.watchu.movie.domain.CommentCommand;
import kr.watchu.user.domain.NaverUserCommand;
import kr.watchu.user.domain.UserCommand;

public interface UserService {
	//회원등록
	public void insertUser(UserCommand user);
	//상세정보보기
	public UserCommand selectUser(String id); 
	
	//social 회원등록
	public void insertsocialUser(NaverUserCommand nuser);
	//social 상세정보보기
	public NaverUserCommand selectsocialUser(String id);
	
	//수정
	public void updateUser(UserCommand user);
	//삭제
	public void deleteUser(String id);
	//추천친구목록	
	public List<String> selectUserId();//단순회원전체목록(아이디,auth만)	
	public int selectUserCnt(Map<String, Object> map);
	public List<UserCommand> selectUserList(Map<String,Object> map);
	
	//팔로우,팔로워,블락 목록
	public int selectfollowCnt(Map<String, Object> map);
	public List<UserCommand> selectfollowList(Map<String,Object> map);
	
	//관리자
	public List<UserCommand> selectAdminUser(Map<String,Object> map); //회원목록
	public int selectAdminCnt(Map<String, Object> map); //회원 카운트
	public void adminUpdate(UserCommand user);	//회원 정보 수정(user_info)
	public void adminUpdate2(UserCommand user);	//회원 등급 수정(user_basic)
	
	//팔로우,팔로워,블락
	public void insertFollow(UserCommand user);
	public void insertFollower(UserCommand user);
	public void insertBlock(UserCommand user);
	
	// 목록
	public List<CommentCommand> CommentList(String id);

}
