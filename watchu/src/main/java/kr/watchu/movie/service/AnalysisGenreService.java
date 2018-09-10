package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;

import kr.watchu.movie.domain.AnalysisGenreCommand;

public interface AnalysisGenreService {
	
	public void insertGenreRate(AnalysisGenreCommand rate);
	public void updateGenreRate(AnalysisGenreCommand rate);
	
	public AnalysisGenreCommand selectInfo(Map<String,Object> map);	
	//���������������� ��ȭ�� �����Ҷ�
	public void deleteByMovie(Integer movie_num);
	//������ ȸ��Ż�� �Ҷ�
	public void deleteById(String id);
	
	public Integer selectCnt(Map<String,Object> map);
	public List<AnalysisGenreCommand> selectList(Map<String,Object> map);
}
