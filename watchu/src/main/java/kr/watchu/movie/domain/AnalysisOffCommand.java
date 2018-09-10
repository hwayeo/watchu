package kr.watchu.movie.domain;

import java.sql.Date;

public class AnalysisOffCommand {
	private Integer movie_num;
	private String id;
	private String name;
	private float rate;
	private Date reg_date;
	
	@Override
	public String toString() {
		return "AnalysisOffCommand [movie_num=" + movie_num + ", id=" + id + ", name=" + name + ", rate=" + rate
				+ ", reg_date=" + reg_date + "]";
	}
	public Integer getMovie_num() {
		return movie_num;
	}
	public void setMovie_num(Integer movie_num) {
		this.movie_num = movie_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}
