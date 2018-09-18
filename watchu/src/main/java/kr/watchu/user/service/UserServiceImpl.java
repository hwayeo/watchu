package kr.watchu.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.watchu.movie.dao.CommentMapper;
import kr.watchu.movie.domain.CommentCommand;
import kr.watchu.user.dao.UserMapper;
import kr.watchu.user.domain.NaverUserCommand;
import kr.watchu.user.domain.UserCommand;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;

	@Override
	public void insertUser(UserCommand user) {
		userMapper.insertUser(user);
		userMapper.insertUserDetail(user);	
		userMapper.insertRelation(user.getId());
	}

	@Override
	public UserCommand selectUser(String id) {
		return userMapper.selectUser(id);
	}

	@Override
	public void updateUser(UserCommand user) {
		userMapper.updateUser(user);
		
	}

	@Override
	public void deleteUser(String id) {
		userMapper.deleteUserRelation(id);
		userMapper.deleteUserDetail(id);
		userMapper.deleteUser(id);
	}

	@Override
	public int selectUserCnt(Map<String, Object> map) {
		return userMapper.selectUserCnt(map);
	}

	@Override
	public List<UserCommand> selectUserList(Map<String, Object> map) {
		return userMapper.selectUserList(map);
	}

	@Override
	public void insertFollow(UserCommand user) {
		userMapper.insertFollow(user);
		
	}

	@Override
	public void insertFollower(UserCommand user) {
		userMapper.insertFollower(user);
		
	}

	@Override
	public void insertBlock(UserCommand user) {
		userMapper.insertBlock(user);
	}

	@Override
	public List<CommentCommand> CommentList(String id) {
		return userMapper.CommentList(id);
	}

	@Override
	public void insertsocialUser(NaverUserCommand nuser) {
		userMapper.insertsocialUser(nuser);
		userMapper.insertsocialUserDetail(nuser);
		userMapper.insertsocialRelation(nuser.getId());
	}

	@Override
	public NaverUserCommand selectsocialUser(String id) {
		return userMapper.selectsocialUser(id);
	}

	//관리자 회원정보 수정
	@Override
	public void adminUpdate(UserCommand user) {
		userMapper.adminUpdate(user);	//회원 정보 수정(이름, 연락처, 이메일)
	}
	@Override
	public void adminUpdate2(UserCommand user) {
		userMapper.adminUpdate2(user);	//회원 등급 수정	
	}

	@Override
	public List<UserCommand> selectfollowList(Map<String, Object> map) {
		return userMapper.selectfollowList(map);
	}

	@Override
	public int selectfollowCnt(Map<String, Object> map) {
		return userMapper.selectfollowCnt(map);
	}

	@Override
	public List<String> selectUserId() {
		return userMapper.selectUserId();
	}

}