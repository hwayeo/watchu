package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.watchu.movie.dao.RecommendMapper;
import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.domain.OfficialsCommand;

@Service("recommendService")
public class RecommendServiceImpl implements RecommendService{

	@Resource
	private RecommendMapper mapper;
	@Override
	public Integer selectTotalRated() {
		return mapper.selectTotalRated();
	}
	@Override
	public Integer selectRatedCntById(String id) {
		return mapper.selectRatedCntById(id);
	}
	@Override
	public float selectAvgTotalMovie(String id) {
		return mapper.selectAvgTotalMovie(id);
	}
	@Override
	public List<MovieCommand> selectRatedMovieList(Map<String, Object> map) {
		return mapper.selectRatedMovieList(map);
	}
	
	@Override
	public Integer selectRandomBanner() {
		return mapper.selectRandomBanner();
	}
	@Override
	public MovieCommand selectRandomMovie() {
		return mapper.selectRandomMovie();
	}
	
	@Override
	public String selectFavoriteGenre(Map<String, Object> map) {
		return mapper.selectFavoriteGenre(map);
	}
	@Override
	public float selectPredictionByGenre(Map<String, Object> map) {
		return mapper.selectPredictionByGenre(map);
	}
	@Override
	public String selectRanGenre() {
		return mapper.selectRanGenre();
	}
	@Override
	public List<MovieCommand> selectRanGenreMovieList(Map<String, Object> map) {
		return mapper.selectRanGenreMovieList(map);
	}
	@Override
	public String selectRanOff(Map<String, Object> map) {
		return mapper.selectRanOff(map);
	}
	
	@Override
	public MovieCommand selectRanOffMovie(String name) {
		return mapper.selectRanOffMovie(name);
	}
	@Override
	public List<MovieCommand> selectRanOffMovieList(Map<String, Object> map) {
		return mapper.selectRanOffMovieList(map);
	}
	
	
}
