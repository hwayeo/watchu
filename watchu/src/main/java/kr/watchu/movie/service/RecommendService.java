package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;

import kr.watchu.movie.domain.GenreCommand;
import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.domain.OfficialsCommand;

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

	//전체 장르 무작위 반환
	public String selectRanGenre();
	//무작위로 선택된 장르에서 무작위로 영화 선택
	public List<MovieCommand> selectRanGenreMovieList(Map<String,Object> map);

	//전체 영화인(배우,감독)중 어느정도 평가가 진행된 사람 1명의 데이터를 반환
	public String selectRanOff(Map<String,Object> map);
	public MovieCommand selectRanOffMovie(String name);
	public List<MovieCommand> selectRanOffMovieList(Map<String,Object> map);

	public List<MovieCommand> selectRecommendList(Map<String,Object> map);
	//사용자 취향 기반 영화인(감독,배우) 1명 추출
	public String selectRecommendOff(Map<String,Object> map);
	public List<GenreCommand> selectRatedGenre(Map<String,Object> map);
	public List<OfficialsCommand> selectRatedOff(Map<String,Object> map);
}
