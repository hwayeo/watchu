package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.watchu.movie.dao.CommentMapper;
import kr.watchu.movie.domain.CommentCommand;
import kr.watchu.movie.domain.TimelineCommand;

@Service("commentService")
public class CommentServiceImpl implements CommentService{
	 
	@Resource
	private CommentMapper commentMapper;
	
	@Override
	public void insertComment(CommentCommand comment) {
		commentMapper.insertComment(comment);
	}

	@Override
	public void deleteCommentByMovie(Integer movie_num) {
		commentMapper.deleteCommentByMovie(movie_num);
	}

	@Override
	public CommentCommand selectComment(Map<String,Object> map) {
		return commentMapper.selectComment(map);
	}

	@Override
	public void updateComment(CommentCommand comment) {
		commentMapper.updateComment(comment);
		
	}

	@Override
	public void deleteComment(Integer comment_num) {
		commentMapper.deleteComment(comment_num);
		
	}

	@Override
	public List<CommentCommand> selectMyCommentList(String id) {
		return commentMapper.selectMyCommentList(id);
	}

	@Override
	public int selectMyCommentCnt(String id) {
		return commentMapper.selectMyCommentCnt(id);
	}

	@Override
	public List<CommentCommand> selectCommentList(Integer movie_num) {
		return commentMapper.selectCommentList(movie_num);
	}

	@Override
	public int selectCommentCnt(Integer movie_num) {
		return commentMapper.selectCommentCnt(movie_num);
	}

	
	@Override
	public Integer selectLikes(Integer comment_num) {
		return commentMapper.selectLikes(comment_num);
	}

	@Override
	public void updateCommentWithLike(Map<String, Object> map) {
		commentMapper.updateCommentWithLike(map);
	}

	@Override
	public void insertCommentLike(Map<String, Object> map) {
		commentMapper.insertCommentLike(map);
	}

	@Override
	public void deleteCommentLike(Map<String, Object> map) {
		commentMapper.deleteCommentLike(map);
	}

	@Override
	public CommentCommand commentDetail(Map<String, Object> map) {
		return commentMapper.commentDetail(map);
	}
	
	@Override
	public String selectMyFollow(String id) {
		return commentMapper.selectMyFollow(id);
	}

	@Override
	public Integer selectTimelineCnt(Map<String, Object> map) {
		return commentMapper.selectTimelineCnt(map);
	}

	@Override
	public List<TimelineCommand> selectTimeline(Map<String, Object> map) {
		return commentMapper.selectTimeline(map); 
	}
}
