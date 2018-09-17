package kr.watchu.movie.domain;

import java.sql.Date;
import java.util.Arrays;

public class TimelineCommand {
	private Integer movie_num;
	private String title;
	private String country;
	private String main_genre;
	private String sub_genre;
	private byte[] poster_img;
	private String id;
	private String name;
	private float rate;
	private Date reg_date;
	public Integer getMovie_num() {
		return movie_num;
	}
	public void setMovie_num(Integer movie_num) {
		this.movie_num = movie_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMain_genre() {
		return main_genre;
	}
	public void setMain_genre(String main_genre) {
		this.main_genre = main_genre;
	}
	public String getSub_genre() {
		return sub_genre;
	}
	public void setSub_genre(String sub_genre) {
		this.sub_genre = sub_genre;
	}
	public byte[] getPoster_img() {
		return poster_img;
	}
	public void setPoster_img(byte[] poster_img) {
		this.poster_img = poster_img;
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
	@Override
	public String toString() {
		return "TimelineCommand [movie_num=" + movie_num + ", title=" + title + ", country=" + country + ", main_genre="
				+ main_genre + ", sub_genre=" + sub_genre + ", poster_img=" + Arrays.toString(poster_img) + ", id=" + id
				+ ", name=" + name + ", rate=" + rate + ", reg_date=" + reg_date + "]";
	}
	
}
