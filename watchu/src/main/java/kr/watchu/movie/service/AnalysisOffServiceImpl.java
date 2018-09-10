package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.watchu.movie.dao.AnalysisOffMapper;
import kr.watchu.movie.domain.AnalysisOffCommand;

@Service("analysisOffService")
public class AnalysisOffServiceImpl implements AnalysisOffService {

	@Resource
	private AnalysisOffMapper mapper;
	
	@Override
	public void insertOffRate(AnalysisOffCommand rate) {
		mapper.insertOffRate(rate);
	}

	@Override
	public void updateOffRate(AnalysisOffCommand rate) {
		mapper.updateOffRate(rate);
	}

	@Override
	public AnalysisOffCommand selectInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByMovie() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer selectCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnalysisOffCommand> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
