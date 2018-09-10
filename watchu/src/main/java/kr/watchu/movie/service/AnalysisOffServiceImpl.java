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
		return mapper.selectInfo(map);
	}

	@Override
	public void deleteByMovie(Integer movie_num) {
		mapper.deleteByMovie(movie_num);
	}

	@Override
	public void deleteById(String id) {
		mapper.deleteById(id);
	}

	@Override
	public Integer selectCnt(Map<String, Object> map) {
		return mapper.selectCnt(map);
	}

	@Override
	public List<AnalysisOffCommand> selectList(Map<String, Object> map) {
		return mapper.selectList(map);
	}

}
