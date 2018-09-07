package kr.watchu.movie.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.watchu.movie.domain.GenreCommand;
import kr.watchu.movie.domain.OfficialsCommand;

public interface GenreMapper {
	//장르추가
	@Insert("INSERT INTO movie_genre (genre_num, genre) VALUES (genre_seq.nextval, #{genre})")
	public void insertGenre(GenreCommand genre);
	//상세정보
	@Select("SELECT * FROM movie_genre WHERE genre_num=#{genre_num}")
	public GenreCommand selectGenre(Integer genre_num);
	//수정
	@Update("UPDATE movie_genre SET genre=#{genre} WHERE genre_num=#{genre_num}")
	public void updateGenre(GenreCommand genre);
	//삭제
	@Delete("DELETE FROM movie_genre WHERE genre_num=#{genre_num}")
	public void deleteGenre(Integer genre_num);
	//목록
	public int selectGenreCnt(Map<String, Object> map);
	public List<GenreCommand> selectGenreList(Map<String,Object> map);
	
	//자동완성 ajax
	public List<GenreCommand> selectGenreAjaxList(Map<String, Object> map);
}
