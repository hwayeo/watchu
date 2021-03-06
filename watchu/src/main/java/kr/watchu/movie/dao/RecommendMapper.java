package kr.watchu.movie.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.watchu.movie.domain.GenreCommand;
import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.domain.OfficialsCommand;

public interface RecommendMapper {
	//메인페이지에 총 평가 갯수
	@Select("SELECT count(*) FROM movie_rated")
	public Integer selectTotalRated();
	
	//사용자가 평가한 영화 갯수를 반환
	@Select("SELECT COUNT(*) FROM movie_rated GROUP BY id HAVING id=#{id}")
	public Integer selectRatedCntById(String id);
	
	//사용자가 평가한 모든 영화의 평균 점수
	@Select("SELECT ROUND(AVG(rate),1) rate FROM movie_rated GROUP BY id HAVING id =#{id}")
	public float selectAvgTotalMovie(String id);
	
	//사용자가 평가한 영화 리스트 -> 마이페이지에서도 사용 가능 
	//parameter -> id(사용자 본인 or 친구 id), start, end
	public List<MovieCommand> selectRatedMovieList(Map<String,Object> map);

	//메인 배너이미지용 무작위 영화
	@Select("SELECT a.movie_num, rownum FROM (SELECT * FROM movie_info WHERE banner_img is not null ORDER BY DBMS_RANDOM.VALUE)a WHERE rownum <=1")
	public Integer selectRandomBanner();	
	//무작위 추천영화
	@Select("SELECT a.*, rownum FROM (SELECT * FROM movie_info i JOIN (SELECT movie_num, ROUND(AVG(rate),1) rate FROM movie_rated GROUP BY movie_num)r ON i.movie_num=r.movie_num ORDER BY DBMS_RANDOM.VALUE)a WHERE rownum <=1")
	public MovieCommand selectRandomMovie();
	
	//전체 장르 무작위 반환
	@Select("SELECT genre FROM (SELECT a.*, rownum FROM (SELECT * FROM movie_genre ORDER BY DBMS_RANDOM.VALUE)a WHERE rownum=1)")
	public String selectRanGenre();
	
	//무작위로 선택된 장르에서 무작위로 영화 선택
	public List<MovieCommand> selectRanGenreMovieList(Map<String,Object> map);
	
	//전체 영화인(배우,감독)중 어느정도 평가가 진행된 사람 1명의 데이터를 반환
	@Select("SELECT * FROM (SELECT o.name FROM officials o JOIN (SELECT name, ROUND(AVG(rate),1) rate,COUNT(rate) cnt FROM analysis_officials GROUP BY name)a ON o.name=a.name WHERE o.jobs=#{jobs} AND a.rate>=#{rate} ORDER BY DBMS_RANDOM.VALUE) WHERE rownum <=1")
	public String selectRanOff(Map<String,Object> map);
	
	public MovieCommand selectRanOffMovie(String name);
	public List<MovieCommand> selectRanOffMovieList(Map<String,Object> map);
	
	/*===로그인한 사용자 맞춤형===*/
	
	//사용자가 평가한 장르 순위 반환
	@Select("SELECT * FROM (SELECT a.*,rownum rnum FROM (SELECT id, genre, ROUND(AVG(rate),1) rate, count(genre) cnt FROM analysis_genre GROUP BY genre,id HAVING id=#{id} ORDER BY rate DESC)a) WHERE rnum >=#{start} AND rnum <=#{end}")
	public List<GenreCommand> selectRatedGenre(Map<String,Object> map);
	//직업별 평가
	@Select("SELECT r.* FROM (SELECT o.off_num, o.name, o.jobs, o.off_photo, a.rate,a.cnt FROM officials o JOIN (SELECT name,avg(rate) rate,count(name) cnt FROM analysis_officials GROUP BY name, id HAVING id=#{id})a ON o.name=a.name WHERE jobs=#{jobs} ORDER BY a.rate DESC)r WHERE rownum >=#{start} AND rownum <=#{end}")
	public List<OfficialsCommand> selectRatedOff(Map<String,Object> map);
	
	//추천 영화 목록
	public List<MovieCommand> selectRecommendList(Map<String,Object> map);
	//사용자 취향 기반 영화인(감독,배우) 1명 추출
	public String selectRecommendOff(Map<String,Object> map);

	//사용자의 장르 선호도
	public String selectFavoriteGenre(Map<String,Object> map);
	//사용자의 특정 장르에 대한 예상 별점
	public float selectPredictionByGenre(Map<String,Object> map);
	
}	
