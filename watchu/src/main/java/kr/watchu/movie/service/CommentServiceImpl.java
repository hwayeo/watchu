package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.watchu.movie.dao.CommentMapper;
import kr.watchu.movie.domain.CommentCommand;
import kr.watchu.movie.domain.RecommentCommand;

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
	public void insertRecomment(RecommentCommand recomment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RecommentCommand selectRecomment(Integer recomment_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecomment(RecommentCommand recomment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRecomment(Integer recomment_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RecommentCommand> selectRecommentList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectRecommentCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

}
