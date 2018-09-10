package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.watchu.movie.dao.AnalysisGenreMapper;
import kr.watchu.movie.domain.AnalysisGenreCommand;

@Service("analysisGenreService")
public class AnalysisGenreServiceImpl implements AnalysisGenreService {

	@Resource
	private AnalysisGenreMapper mapper;
	
	@Override
	public void insertGenreRate(AnalysisGenreCommand rate) {
		mapper.insertGenreRate(rate);
	}

	@Override
	public void updateGenreRate(AnalysisGenreCommand rate) {
		mapper.updateGenreRate(rate);
	}

	@Override
	public AnalysisGenreCommand selectInfo(Map<String, Object> map) {
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
	public List<AnalysisGenreCommand> selectList(Map<String, Object> map) {
		return mapper.selectList(map);
	}

}
