package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;


import kr.watchu.movie.domain.AnalysisOffCommand;

public interface AnalysisOffService {
	public void insertOffRate(AnalysisOffCommand rate);
	public void updateOffRate(AnalysisOffCommand rate);
	
	public AnalysisOffCommand selectInfo(Map<String, Object> map);
	
	//관리자가 영화를 삭제할때
	public void deleteByMovie();
	//유저가 회원탈퇴를 할때
	public void deleteById();
	
	public Integer selectCnt(Map<String, Object> map);
	public List<AnalysisOffCommand> selectList(Map<String, Object> map);
}
