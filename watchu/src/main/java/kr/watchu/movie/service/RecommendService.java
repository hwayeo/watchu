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
}
