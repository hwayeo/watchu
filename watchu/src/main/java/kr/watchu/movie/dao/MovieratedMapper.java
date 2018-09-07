package kr.watchu.movie.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import kr.watchu.movie.domain.MovieratedCommand;

public interface MovieratedMapper {
   //영화 점수 등록
   @Insert("INSERT INTO movie_rated(movie_num,id,rate) VALUES(#{movie_num},#{id},#{rate})")
   public void insertMovierated(MovieratedCommand im);
   
   //점수 호출
   @Select("SELECT * FROM movie_rated WHERE movie_num=#{movie_num} and id=#{id}")
   public MovieratedCommand selectMovierated(Map<String,Object> map);
   
   //영화 점수 재평가(update) -> 동일한 영화를 평가했는지 확인 후 있으면 업데이트
   @Update("UPDATE movie_rated SET rate=#{rate} WHERE movie_num=#{movie_num} and id=#{id}")
   public void updateMovierated(MovieratedCommand im);
   
   //영화 삭제 전 평가 삭제
   @Delete("DELETE FROM movie_rated WHERE movie_num=#{movie_num}")
   public void deleteRatedByMovie(Integer movie_num);
}