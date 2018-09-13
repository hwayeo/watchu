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
	
	//����ڰ� ���� ��ȭ ����Ʈ
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM movie_info i JOIN (SELECT movie_num, id, rate FROM movie_rated WHERE id=#{item})r ON i.movie_num=r.movie_num ORDER BY r.rate DESC)a) WHERE rnum >=#{start} AND rnum <=#{end}")
	public List<MovieCommand> selectRatedMovieList(Map<String,Object> map);
	
	//������
	public float selectPrediction();
	
}	
