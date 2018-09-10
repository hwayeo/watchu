package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;


import kr.watchu.movie.domain.AnalysisOffCommand;

public interface AnalysisOffService {
	public void insertOffRate(AnalysisOffCommand rate);
	public void updateOffRate(AnalysisOffCommand rate);
	
	public AnalysisOffCommand selectInfo(Map<String, Object> map);
	
	//�����ڰ� ��ȭ�� �����Ҷ�
	public void deleteByMovie(Integer movie_num);
	//������ ȸ��Ż�� �Ҷ�
	public void deleteById(String id);
	
	public Integer selectCnt(Map<String, Object> map);
	public List<AnalysisOffCommand> selectList(Map<String, Object> map);
}
