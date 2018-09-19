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
	@Select("SELECT count(*) FROM movie_comment where id=#{id}")
	public Integer selectMyCommentCnt(String id);
	public List<CommentCommand> selectMyCommentList(Map<String, Object> map);
	// �� �ڸ�Ʈ
	@Select("SELECT i.title,i.poster_img,i.released FROM movie_info i RIGHT OUTER JOIN (SELECT * FROM movie_comment WHERE id=#{id})c ON i.movie_num=c.movie_num WHERE i.movie_num=#{movie_num}")
	public CommentCommand commentDetail(Map<String, Object> map);
	
	@Select("SELECT likes FROM movie_comment WHERE comment_num=#{comment_num}")
	public Integer selectLikes(Integer comment_num);
	@Update("UPDATE movie_comment SET likes=#{likes} WHERE comment_num=#{comment_num}")
	public void updateCommentWithLike(Map<String,Object> map);
	//���ƿ� ���
	@Insert("INSERT INTO movie_comment_like (comment_num,id,reg_date) VALUES (#{comment_num},#{id},sysdate)")
	public void insertCommentLike(Map<String,Object> map);
	@Select("SELECT id FROM movie_comment_like WHERE comment_num=#{comment_num} AND id=#{id}")
	public String selectCommentLike(Map<String,Object>map);
	//���ƿ� ����
	@Delete("DELETE FROM movie_comment_like WHERE comment_num=#{comment_num} AND id=#{id}")
	public void deleteCommentLike(Map<String,Object> map);
	
	/*//follow ���ؿ���
	@Select("SELECT follow FROM user_relation WHERE id=#{id}")
	public String selectMyFollow(String id);*/
	public Integer selectTimelineCnt(Map<String,Object> map);
	public List<TimelineCommand> selectTimeline(Map<String,Object> map);
	
	//���ƿ��� �ڸ�Ʈ �ҷ�����
	@Select("SELECT i.comment_num, i.id id, c.id comment_id, i.reg_date, c.movie_num, c.content, c.likes, a.title, a.released,c.reg_date,a.poster_img FROM movie_comment_like i JOIN movie_comment c ON i.comment_num=c.comment_num JOIN movie_info a on c.movie_num=a.movie_num WHERE i.id=#{id}")
	public List<CommentCommand> likeComment(Map<String,Object> map);
	@Select("SELECT count(*) FROM movie_comment_like i JOIN movie_comment c ON i.comment_num=c.comment_num WHERE i.id=#{id}")
	public Integer likeCommentCnt(String id); 	
}
