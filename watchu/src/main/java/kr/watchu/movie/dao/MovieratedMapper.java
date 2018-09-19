package kr.watchu.movie.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import kr.watchu.movie.domain.MovieratedCommand;

public interface MovieratedMapper {
   //��ȭ ���� ���
   @Insert("INSERT INTO movie_rated(movie_num,id,rate,reg_date) VALUES(#{movie_num},#{id},#{rate},sysdate)")
   public void insertMovierated(MovieratedCommand im);
   
   //���� ȣ��
   @Select("SELECT * FROM movie_rated WHERE movie_num=#{movie_num} AND id=#{id}")
   public MovieratedCommand selectMovierated(Map<String,Object> map);
   
   @Select("SELECT COUNT(*) FROM movie_rated WHERE id=#{id}")
   public Integer selectCheckRated(String id);
   //��ȭ ���� ����(update) -> ������ ��ȭ�� ���ߴ��� Ȯ�� �� ������ ������Ʈ
   @Update("UPDATE movie_rated SET rate=#{rate}, reg_date=sysdate WHERE movie_num=#{movie_num} and id=#{id}")
   public void updateMovierated(MovieratedCommand im);
   
   //��ȭ ���� �� �� ����
   @Delete("DELETE FROM movie_rated WHERE movie_num=#{movie_num}")
   public void deleteRatedByMovie(Integer movie_num);
}