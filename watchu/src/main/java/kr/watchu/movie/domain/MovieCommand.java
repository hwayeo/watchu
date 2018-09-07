package kr.watchu.movie.domain;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class MovieCommand {
	private Integer movie_num;
	private String title;
	private String country;
	private Date released;
	private String director;
	private String actors;
	private String summary;
	private MultipartFile uploadPoster;
	private byte[] poster_img;
	private MultipartFile uploadBanner;
	private byte[] banner_img;
	private String main_genre;
	private String sub_genre;
	private String trailer;
	private Date reg_date;
	private float rate;
	private Integer rated_cnt;
	
	
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
	public Date getReleased() {
		return released;
	}
	public void setReleased(Date released) {
		this.released = released;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public MultipartFile getUploadPoster() {
		return uploadPoster;
	}
	public void setUploadPoster(MultipartFile uploadPoster) throws IOException{
		this.uploadPoster = uploadPoster;
		setPoster_img(uploadPoster.getBytes());
	}
	public byte[] getPoster_img() {
		return poster_img;
	}
	public void setPoster_img(byte[] poster_img) {
		this.poster_img = poster_img;
	}
	public MultipartFile getUploadBanner() {
		return uploadBanner;
	}
	public void setUploadBanner(MultipartFile uploadBanner) throws IOException {
		this.uploadBanner = uploadBanner;
		setBanner_img(uploadBanner.getBytes());
	}
	public byte[] getBanner_img() {
		return banner_img;
	}
	public void setBanner_img(byte[] banner_img) {
		this.banner_img = banner_img;
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
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public Integer getRated_cnt() {
		return rated_cnt;
	}
	public void setRated_cnt(Integer rated_cnt) {
		this.rated_cnt = rated_cnt;
	}
		
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "MovieCommand [movie_num=" + movie_num + ", title=" + title + ", country=" + country + ", released="
				+ released + ", director=" + director + ", actors=" + actors + ", summary=" + summary
				+ ", uploadPoster=" + uploadPoster + ", uploadBanner=" + uploadBanner + ", main_genre=" + main_genre
				+ ", sub_genre=" + sub_genre + ", trailer=" + trailer + ", reg_date=" + reg_date + ", rate=" + rate
				+ ", rated_cnt=" + rated_cnt + "]";
	}
}
