package kr.watchu.movie.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.watchu.movie.domain.GenreCommand;
import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.domain.OfficialsCommand;

public interface RecommendMapper {
	//������������ �� �� ����
	@Select("SELECT count(*) FROM movie_rated")
	public Integer selectTotalRated();
	
	//����ڰ� ���� ��ȭ ������ ��ȯ
	@Select("SELECT COUNT(*) FROM movie_rated GROUP BY id HAVING id=#{id}")
	public Integer selectRatedCntById(String id);
	
	//����ڰ� ���� ��� ��ȭ�� ��� ����
	@Select("SELECT ROUND(AVG(rate),1) rate FROM movie_rated GROUP BY id HAVING id =#{id}")
	public float selectAvgTotalMovie(String id);
	
	//����ڰ� ���� ��ȭ ����Ʈ -> ���������������� ��� ���� 
	//parameter -> id(����� ���� or ģ�� id), start, end
	public List<MovieCommand> selectRatedMovieList(Map<String,Object> map);

	//���� ����̹����� ������ ��ȭ
	@Select("SELECT a.movie_num, rownum FROM (SELECT * FROM movie_info WHERE banner_img is not null ORDER BY DBMS_RANDOM.VALUE)a WHERE rownum <=1")
	public Integer selectRandomBanner();	
	//������ ��õ��ȭ
	@Select("SELECT a.*, rownum FROM (SELECT * FROM movie_info i JOIN (SELECT movie_num, ROUND(AVG(rate),1) rate FROM movie_rated GROUP BY movie_num)r ON i.movie_num=r.movie_num ORDER BY DBMS_RANDOM.VALUE)a WHERE rownum <=1")
	public MovieCommand selectRandomMovie();
	
	//��ü �帣 ������ ��ȯ
	@Select("SELECT genre FROM (SELECT a.*, rownum FROM (SELECT * FROM movie_genre ORDER BY DBMS_RANDOM.VALUE)a WHERE rownum=1)")
	public String selectRanGenre();
	
	//�������� ���õ� �帣���� �������� ��ȭ ����
	public List<MovieCommand> selectRanGenreMovieList(Map<String,Object> map);
	
	//��ü ��ȭ��(���,����)�� ������� �򰡰� ����� ��� 1���� �����͸� ��ȯ
	@Select("SELECT * FROM (SELECT o.name FROM officials o JOIN (SELECT name, ROUND(AVG(rate),1) rate,COUNT(rate) cnt FROM analysis_officials GROUP BY name)a ON o.name=a.name WHERE o.jobs=#{jobs} AND a.rate>=#{rate} ORDER BY DBMS_RANDOM.VALUE) WHERE rownum <=1")
	public String selectRanOff(Map<String,Object> map);
	
	public MovieCommand selectRanOffMovie(String name);
	public List<MovieCommand> selectRanOffMovieList(Map<String,Object> map);
	
	/*===�α����� ����� ������===*/
	
	//����ڰ� ���� �帣 ���� ��ȯ
	@Select("SELECT * FROM (SELECT a.*,rownum rnum FROM (SELECT id, genre, ROUND(AVG(rate),1) rate, count(genre) cnt FROM analysis_genre GROUP BY genre,id HAVING id=#{id} ORDER BY rate DESC)a) WHERE rnum >=#{start} AND rnum <=#{end}")
	public List<GenreCommand> selectRatedGenre(Map<String,Object> map);
	//������ ��
	@Select("SELECT r.* FROM (SELECT o.off_num, o.name, o.jobs, a.rate,a.cnt FROM officials o JOIN (SELECT name,avg(rate) rate,count(name) cnt FROM analysis_officials GROUP BY name, id HAVING id=#{id})a ON o.name=a.name WHERE jobs=#{jobs} ORDER BY a.rate DESC)r WHERE rownum >=#{start} AND rownum <=#{end}")
	public List<OfficialsCommand> selectRatedOff(Map<String,Object> map);
	
	//��õ ��ȭ ���
	public List<MovieCommand> selectRecommendList(Map<String,Object> map);
	//����� ���� ��� ��ȭ��(����,���) 1�� ����
	public String selectRecommendOff(Map<String,Object> map);

	//������� �帣 ��ȣ��
	public String selectFavoriteGenre(Map<String,Object> map);
	//������� Ư�� �帣�� ���� ���� ����
	public float selectPredictionByGenre(Map<String,Object> map);
	
}	
