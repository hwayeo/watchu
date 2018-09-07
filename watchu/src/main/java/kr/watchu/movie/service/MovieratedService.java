package kr.watchu.movie.service;

import java.util.Map;
import kr.watchu.movie.domain.MovieratedCommand;

public interface MovieratedService {
	//영화 점수 등록
	public void insertMovierated(MovieratedCommand im);
	
	//점수 호출
	public MovieratedCommand selectMovierated(Map<String,Object> map);
	
	//영화 점수 재평가(update) -> 동일한 영화를 평가했는지 확인 후 있으면 업데이트
	public void updateMovierated(MovieratedCommand im);
	
	//영화 삭제 전 평가 삭제
	public void deleteRatedByMovie(Integer movie_num);
}