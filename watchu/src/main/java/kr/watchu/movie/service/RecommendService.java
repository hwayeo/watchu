package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;


import kr.watchu.movie.domain.MovieCommand;

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
}
