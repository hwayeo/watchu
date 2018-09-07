package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;

import kr.watchu.movie.domain.GenreCommand;

public interface GenreService {
	//장르추가
	public void insertGenre(GenreCommand genre);
	//상세정보 
	public GenreCommand selectGenre(Integer genre_num);
	//수정
	public void updateGenre(GenreCommand genre);
	//삭제
	public void deleteGenre(Integer genre_num);
	//목록
	public int selectGenreCnt(Map<String, Object> map);
	public List<GenreCommand> selectGenreList(Map<String,Object> map);
	
	//자동완성 ajax
	public List<GenreCommand> selectGenreAjaxList(Map<String, Object> map);
}
