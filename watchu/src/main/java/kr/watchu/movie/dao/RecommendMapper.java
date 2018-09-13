package kr.watchu.movie.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.watchu.movie.domain.MovieCommand;

public interface RecommendMapper {
	//메인페이지에 총 평가 갯수
	@Select("SELECT count(*) FROM movie_rated")
	public Integer selectTotalRated();
	
	//사용자가 평가한 영화 갯수를 반환
	@Select("SELECT COUNT(*) FROM movie_rated GROUP BY id HAVING id=#{id}")
	public Integer selectRatedCntById(String id);
	
	//사용자가 평가한 모든 영화의 평균 점수
	@Select("SELECT id, ROUND(AVG(rate),1) rate FROM movie_rated GROUP BY id HAVING id =#{id}")
	public float selectAvgTotalMovie(String id);
	
	//사용자가 평가한 영화 리스트
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM movie_info i JOIN (SELECT movie_num, id, rate FROM movie_rated WHERE id=#{item})r ON i.movie_num=r.movie_num ORDER BY r.rate DESC)a) WHERE rnum >=#{start} AND rnum <=#{end}")
	public List<MovieCommand> selectRatedMovieList(Map<String,Object> map);
	
	//예상별점
	public float selectPrediction();
	
}	
