package kr.watchu.movie.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.watchu.movie.domain.AnalysisGenreCommand;

public interface AnalysisGenreMapper {
	
	@Insert("INSERT INTO analysis_genre (movie_num,id,genre,rate,reg_date) VALUES (#{movie_num},#{id},#{genre},#{rate},sysdate)")
	public void insertGenreRate(AnalysisGenreCommand rate); 
	@Update("UPDATE analysis_genre SET rate=#{rate}, reg_date=sysdate WHERE movie_num=#{movie_num} AND id=#{id}")
	public void updateGenreRate(AnalysisGenreCommand rate);
	
	@Select("SELECT * FROM analysis_genre WHERE movie_num=#{movie_num} AND id=#{id}")
	public AnalysisGenreCommand selectInfo(Map<String,Object> map);	
	//관리자페이지에서 영화를 삭제할때
	@Delete("DELETE FROM analysis_genre WHERE movie_num=#{movie_num}")
	public void deleteByMovie(Integer movie_num);
	//유저가 회원탈퇴를 할때
	@Delete("DELETE FROM analysis_genre WHERE id=#{id}")
	public void deleteById(String id);
	
	public Integer selectCnt(Map<String,Object> map);
	public List<AnalysisGenreCommand> selectList(Map<String,Object> map);
}
