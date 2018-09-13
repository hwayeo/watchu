package kr.watchu.movie.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.watchu.movie.domain.MovieCommand;

public interface RecommendMapper {
	//������������ �� �� ����
	@Select("SELECT count(*) FROM movie_rated")
	public Integer selectTotalRated();
	
	//����ڰ� ���� ��ȭ ������ ��ȯ
	@Select("SELECT COUNT(*) FROM movie_rated GROUP BY id HAVING id=#{id}")
	public Integer selectRatedCntById(String id);
	
	//����ڰ� ���� ��� ��ȭ�� ��� ����
	@Select("SELECT id, ROUND(AVG(rate),1) rate FROM movie_rated GROUP BY id HAVING id =#{id}")
	public float selectAvgTotalMovie(String id);
	
	//����ڰ� ���� ��ȭ ����Ʈ -> ���������������� ��� ���� 
	//parameter -> id(����� ���� or ģ�� id), start, end
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM movie_info i JOIN (SELECT movie_num, id, rate FROM movie_rated WHERE id=#{id})r ON i.movie_num=r.movie_num ORDER BY r.rate DESC)a) WHERE rnum >=#{start} AND rnum <=#{end}")
	public List<MovieCommand> selectRatedMovieList(Map<String,Object> map);

	//���� ����̹����� ������ ��ȭ
	@Select("SELECT a.movie_num, rownum FROM (SELECT * FROM movie_info WHERE banner_img is not null ORDER BY DBMS_RANDOM.VALUE)a WHERE rownum <=1")
	public Integer selectRandomBanner();
	//������ ��õ��ȭ
	@Select("SELECT a.*, rownum FROM (SELECT * FROM movie_info i JOIN (SELECT movie_num, ROUND(AVG(rate),1) rate FROM movie_rated GROUP BY movie_num)r ON i.movie_num=r.movie_num ORDER BY DBMS_RANDOM.VALUE)a WHERE rownum <=1")
	public MovieCommand selectRandomMovie();
	
	//������� �帣 ��ȣ��
	public String selectFavoriteGenre(Map<String,Object> map);
	//������� Ư�� �帣�� ���� ���� ����
	public float selectPredictionByGenre(Map<String,Object> map);
}	
