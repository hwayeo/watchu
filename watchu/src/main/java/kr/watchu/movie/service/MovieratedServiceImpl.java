package kr.watchu.movie.service;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;


import kr.watchu.movie.dao.MovieratedMapper;
import kr.watchu.movie.domain.MovieratedCommand;

@Service("movieratedService")
public class MovieratedServiceImpl implements MovieratedService {
	@Resource
	private MovieratedMapper mapper;
	
	@Override
	public void insertMovierated(MovieratedCommand im) {
		mapper.insertMovierated(im);
	}

	@Override
	public MovieratedCommand selectMovierated(Map<String, Object> map) {
		return mapper.selectMovierated(map);
	}

	@Override
	public void updateMovierated(MovieratedCommand im) {
		mapper.updateMovierated(im);
	}
	
	@Override
	public void deleteRatedByMovie(Integer movie_num) {
		mapper.deleteRatedByMovie(movie_num);
	}
}