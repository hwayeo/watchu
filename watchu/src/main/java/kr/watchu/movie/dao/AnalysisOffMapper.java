package kr.watchu.movie.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import kr.watchu.movie.domain.AnalysisOffCommand;

public interface AnalysisOffMapper {

	@Insert("INSERT INTO analysis_officials (movie_num,id,name,rate,reg_date) VALUES(#{movie_num},#{id},#{name},#{rate},sysdate)")
	public void insertOffRate(AnalysisOffCommand rate);
	@Update("UPDATE analysis_officials SET rate=#{rate} WHERE movie_num=#{movie_num} AND id=#{id}")
	public void updateOffRate(AnalysisOffCommand rate);
	
	public AnalysisOffCommand selectInfo(Map<String, Object> map);
	
	//관리자가 영화를 삭제할때
	public void deleteByMovie();
	//유저가 회원탈퇴를 할때
	public void deleteById();
	
	public Integer selectCnt(Map<String, Object> map);
	public List<AnalysisOffCommand> selectList(Map<String, Object> map);
}
