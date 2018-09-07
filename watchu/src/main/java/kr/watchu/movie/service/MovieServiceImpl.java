package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.watchu.movie.dao.MovieMapper;
import kr.watchu.movie.domain.MovieCommand;


@Service("movieService") 
public class MovieServiceImpl implements MovieService {
	@Resource
	private MovieMapper movieMapper;
	
	@Override
	public void insertMovie(MovieCommand movie) {
		movieMapper.insertMovie(movie);
	}

	@Override
	public MovieCommand selectMovie(Integer movie_num) {
		return movieMapper.selectMovie(movie_num);
	}

	@Override
	public void updateMovie(MovieCommand movie) {
		movieMapper.updateMovie(movie);
		
	}

	@Override
	public void deleteMovie(Integer movie_num) {
		movieMapper.deleteMovie(movie_num);
	}
	
	@Override
	public int selectMovieCnt(Map<String, Object> map) {
		return movieMapper.selectMovieCnt(map);
	}

	@Override
	public List<MovieCommand> selectMovieList(Map<String, Object> map) {
		return movieMapper.selectMovieList(map);
	}

	@Override
	public int selectMovieAjaxCnt(Map<String, Object> map) {
		return movieMapper.selectMovieAjaxCnt(map);
	}

	@Override
	public List<MovieCommand> selectMovieAjaxList(Map<String, Object> map) {
		return movieMapper.selectMovieAjaxList(map);
	}

	@Override
	public int selectMovieAjaxCnt2(Map<String, Object> map) {
		return movieMapper.selectMovieAjaxCnt2(map);
	}

	@Override
	public List<MovieCommand> selectMovieAjaxList2(Map<String, Object> map) {
		return movieMapper.selectMovieAjaxList2(map);
	}

}
