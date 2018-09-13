package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;


import kr.watchu.movie.domain.MovieCommand;

public interface RecommendService {
	public Integer selectTotalRated();
	//사용자가 평가한 영화 갯수를 반환
	public Integer selectRatedCntById(String id);

	//사용자가 평가한 모든 영화의 평균 점수
	public float selectAvgTotalMovie(String id);

	//사용자가 평가한 영화 리스트
	public List<MovieCommand> selectRatedMovieList(Map<String,Object> map);

	//무작위 추천영화
	public Integer selectRandomBanner();
	public MovieCommand selectRandomMovie();

	//사용자의 장르 선호도
	public String selectFavoriteGenre(Map<String,Object> map);
	//사용자의 특정 장르에 대한 예상 별점
	public float selectPredictionByGenre(Map<String,Object> map);
}
