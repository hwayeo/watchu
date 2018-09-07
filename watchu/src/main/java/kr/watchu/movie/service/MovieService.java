package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;

import kr.watchu.movie.domain.MovieCommand;

public interface MovieService {
	//영화등록
	public void insertMovie(MovieCommand movie);
	//영화 상세정보
	public MovieCommand selectMovie(Integer movie_num);
	//영화 수정
	public void updateMovie(MovieCommand movie);
	//영화 삭제
	//영화 삭제시 관련 평가 테이블도 삭제해야 함
	public void deleteMovie(Integer movie_num);

	//영화 목록작업
	public int selectMovieCnt(Map<String,Object> map);
	public List<MovieCommand> selectMovieList(Map<String,Object> map);
	public int selectMovieAjaxCnt(Map<String,Object> map);
	public List<MovieCommand> selectMovieAjaxList(Map<String,Object> map);
	public int selectMovieAjaxCnt2(Map<String,Object> map);
	public List<MovieCommand> selectMovieAjaxList2(Map<String,Object> map);
}
