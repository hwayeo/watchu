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
	 * CommentCommand, RecommentCommant 둘다 사용
	 */
	
	//======= 코멘트
	//코멘트 쓰기
	@Insert("INSERT INTO movie_comment (comment_num, movie_num, id, content, reg_date) "
			+ "VALUES (comment_seq.nextval, #{movie_num}, #{id}, #{content}, SYSDATE)") 
	public void insertComment(CommentCommand comment);
	//상세정보
	@Select("SELECT * FROM movie_comment WHERE movie_num=#{movie_num} AND id=#{id}")
	public CommentCommand selectComment(Map<String,Object> map);
	//수정
	@Update("UPDATE movie_comment SET content=#{content} WHERE movie_num=#{movie_num} AND id=#{id}")
	public void updateComment(CommentCommand comment);
	//삭제
	@Delete("DELETE FROM movie_comment WHERE comment_num=#{comment_num}")
	public void deleteComment(Integer comment_num);
	//영화 삭제 전 코멘트 삭제
	@Delete("DELETE FROM movie_comment WHERE movie_num=#{movie_num}")
	public void deleteCommentByMovie(Integer movie_num);
	
	//목록
	@Select("SELECT c.*,u.profile_img FROM movie_comment c JOIN user_info u ON c.id=u.id WHERE movie_num=#{movie_num} ORDER BY c.reg_date DESC ")
	public List<CommentCommand> selectCommentList(Integer movie_num);
	//카운트
	@Select("SELECT COUNT(*) FROM movie_comment WHERE movie_num=#{movie_num}")
	public int selectCommentCnt(Integer movie_num);
	
	//마이페이지에서 내가 쓴 코멘트를 반환하는 메서드
	@Select("SELECT count(*) FROM movie_comment where id=#{id}")
	public Integer selectMyCommentCnt(String id);
	public List<CommentCommand> selectMyCommentList(Map<String, Object> map);
	// 상세 코멘트
	@Select("SELECT i.title,i.poster_img,i.released FROM movie_info i RIGHT OUTER JOIN (SELECT * FROM movie_comment WHERE id=#{id})c ON i.movie_num=c.movie_num WHERE i.movie_num=#{movie_num}")
	public CommentCommand commentDetail(Map<String, Object> map);
	
	@Select("SELECT likes FROM movie_comment WHERE comment_num=#{comment_num}")
	public Integer selectLikes(Integer comment_num);
	@Update("UPDATE movie_comment SET likes=#{likes} WHERE comment_num=#{comment_num}")
	public void updateCommentWithLike(Map<String,Object> map);
	//좋아요 기록
	@Insert("INSERT INTO movie_comment_like (comment_num,id,reg_date) VALUES (#{comment_num},#{id},sysdate)")
	public void insertCommentLike(Map<String,Object> map);
	@Select("SELECT id FROM movie_comment_like WHERE comment_num=#{comment_num} AND id=#{id}")
	public String selectCommentLike(Map<String,Object>map);
	//좋아요 삭제
	@Delete("DELETE FROM movie_comment_like WHERE comment_num=#{comment_num} AND id=#{id}")
	public void deleteCommentLike(Map<String,Object> map);
	
	/*//follow 구해오기
	@Select("SELECT follow FROM user_relation WHERE id=#{id}")
	public String selectMyFollow(String id);*/
	public Integer selectTimelineCnt(Map<String,Object> map);
	public List<TimelineCommand> selectTimeline(Map<String,Object> map);
	
	//좋아요한 코멘트 불러오기
	@Select("SELECT i.comment_num, i.id id, c.id comment_id, i.reg_date, c.movie_num, c.content, c.likes, a.title, a.released,c.reg_date,a.poster_img FROM movie_comment_like i JOIN movie_comment c ON i.comment_num=c.comment_num JOIN movie_info a on c.movie_num=a.movie_num WHERE i.id=#{id}")
	public List<CommentCommand> likeComment(Map<String,Object> map);
	@Select("SELECT count(*) FROM movie_comment_like i JOIN movie_comment c ON i.comment_num=c.comment_num WHERE i.id=#{id}")
	public Integer likeCommentCnt(String id); 	
}
