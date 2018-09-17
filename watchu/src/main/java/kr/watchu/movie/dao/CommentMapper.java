package kr.watchu.movie.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.watchu.movie.domain.CommentCommand;
import kr.watchu.movie.domain.RecommentCommand;
import kr.watchu.movie.domain.TimelineCommand;

public interface CommentMapper {
	/*
	 * CommentCommand, RecommentCommant �Ѵ� ���
	 */
	
	//======= �ڸ�Ʈ
	//�ڸ�Ʈ ����
	@Insert("INSERT INTO movie_comment (comment_num, movie_num, id, content, reg_date) "
			+ "VALUES (comment_seq.nextval, #{movie_num}, #{id}, #{content}, SYSDATE)") 
	public void insertComment(CommentCommand comment);
	//������
	@Select("SELECT * FROM movie_comment WHERE movie_num=#{movie_num} AND id=#{id}")
	public CommentCommand selectComment(Map<String,Object> map);
	//����
	@Update("UPDATE movie_comment SET content=#{content} WHERE movie_num=#{movie_num} AND id=#{id}")
	public void updateComment(CommentCommand comment);
	//����
	@Delete("DELETE FROM movie_comment WHERE comment_num=#{comment_num}")
	public void deleteComment(Integer comment_num);
	//��ȭ ���� �� �ڸ�Ʈ ����
	@Delete("DELETE FROM movie_comment WHERE movie_num=#{movie_num}")
	public void deleteCommentByMovie(Integer movie_num);
	
	//���
	@Select("SELECT c.*,u.profile_img FROM movie_comment c JOIN user_info u ON c.id=u.id WHERE movie_num=#{movie_num} ORDER BY c.reg_date DESC ")
	public List<CommentCommand> selectCommentList(Integer movie_num);
	//ī��Ʈ
	@Select("SELECT COUNT(*) FROM movie_comment WHERE movie_num=#{movie_num}")
	public int selectCommentCnt(Integer movie_num);
	
	//�������������� ���� �� �ڸ�Ʈ�� ��ȯ�ϴ� �޼���
	/*@Select("SELECT * FROM movie_comment WHERE id=#{id} ORDER BY comment_num DESC")*/
	@Select("SELECT mr.movie_num, mr.id, mr.rate, rr.* FROM movie_rated mr JOIN (SELECT a.*,b.title,b.poster_img,b.released FROM movie_comment a JOIN movie_info b ON a.movie_num=b.movie_num ORDER BY a.reg_date DESC)rr ON mr.movie_num=rr.movie_num WHERE mr.id=#{id}")
	public List<CommentCommand> selectMyCommentList(String id);
	@Select("SELECT COUNT(*) FROM movie_comment WHERE id=#{id}")
	public int selectMyCommentCnt(String id);
	// �� �ڸ�Ʈ
	@Select("SELECT i.title,i.poster_img,i.released FROM movie_info i RIGHT OUTER JOIN (SELECT * FROM movie_comment WHERE id=#{id})c ON i.movie_num=c.movie_num WHERE i.movie_num=#{movie_num}")
	public CommentCommand commentDetail(Map<String, Object> map);
	 
	//======= �ڸ�Ʈ ���
	//�ڸ�Ʈ ����
	public void insertRecomment(RecommentCommand recomment);
	//������
	public RecommentCommand selectRecomment(Integer recomment_num);
	//����
	public void updateRecomment(RecommentCommand recomment);
	//����
	public void deleteRecomment(Integer recomment_num);
	
	//���
	public List<RecommentCommand> selectRecommentList(Map<String, Object> map);
	//ī��Ʈ
	public int selectRecommentCnt(Map<String, Object> map);
	
	public List<TimelineCommand> selectTimeline(Map<String,Object> map);
}
