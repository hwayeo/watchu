package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;


import kr.watchu.movie.domain.CommentCommand;
import kr.watchu.movie.domain.TimelineCommand;

public interface CommentService {
	  
	//======= �ڸ�Ʈ
	//�ڸ�Ʈ ����
	public void insertComment(CommentCommand comment);
	//������
	public CommentCommand selectComment(Map<String,Object> map);
	//����
	public void updateComment(CommentCommand comment);
	//����
	public void deleteComment(Integer comment_num);

	public void deleteCommentByMovie(Integer movie_num);
	//���
	public List<CommentCommand> selectCommentList(Integer movie_num);
	//ī��Ʈ
	public int selectCommentCnt(Integer movie_num);

	//�������������� ���� �� �ڸ�Ʈ�� ��ȯ�ϴ� �޼���
	public List<CommentCommand> selectMyCommentList(String id);

	public int selectMyCommentCnt(String id);
	//�� �ڸ�Ʈ
	public CommentCommand commentDetail(Map<String, Object> map);
	public void updateCommentWithLike(Map<String,Object> map);
	public void insertCommentLike(Map<String,Object> map);
	public void deleteCommentLike(Map<String,Object> map);
	
	public String selectMyFollow(String id);
	public Integer selectTimelineCnt(Map<String,Object> map);
	public List<TimelineCommand> selectTimeline(Map<String,Object> map);
}
