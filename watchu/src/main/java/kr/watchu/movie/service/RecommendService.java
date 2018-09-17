package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;

import kr.watchu.movie.domain.GenreCommand;
import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.domain.OfficialsCommand;

public interface RecommendService {
	public Integer selectTotalRated();
	//����ڰ� ���� ��ȭ ������ ��ȯ
	public Integer selectRatedCntById(String id);

	//����ڰ� ���� ��� ��ȭ�� ��� ����
	public float selectAvgTotalMovie(String id);

	//����ڰ� ���� ��ȭ ����Ʈ
	public List<MovieCommand> selectRatedMovieList(Map<String,Object> map);

	//������ ��õ��ȭ
	public Integer selectRandomBanner();
	public MovieCommand selectRandomMovie();

	//������� �帣 ��ȣ��
	public String selectFavoriteGenre(Map<String,Object> map);
	//������� Ư�� �帣�� ���� ���� ����
	public float selectPredictionByGenre(Map<String,Object> map);

	//��ü �帣 ������ ��ȯ
	public String selectRanGenre();
	//�������� ���õ� �帣���� �������� ��ȭ ����
	public List<MovieCommand> selectRanGenreMovieList(Map<String,Object> map);

	//��ü ��ȭ��(���,����)�� ������� �򰡰� ����� ��� 1���� �����͸� ��ȯ
	public String selectRanOff(Map<String,Object> map);
	public MovieCommand selectRanOffMovie(String name);
	public List<MovieCommand> selectRanOffMovieList(Map<String,Object> map);

	public List<MovieCommand> selectRecommendList(Map<String,Object> map);
	//����� ���� ��� ��ȭ��(����,���) 1�� ����
	public String selectRecommendOff(Map<String,Object> map);
	public List<GenreCommand> selectRatedGenre(Map<String,Object> map);
	public List<OfficialsCommand> selectRatedOff(Map<String,Object> map);
}
