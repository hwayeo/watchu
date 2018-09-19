package kr.watchu.movie.domain;

import java.sql.Date;


public class CommentCommand {
	
	private Integer comment_num;  
	private Integer movie_num;
	private String id;
	private String comment_id;
	private String content;
	private Integer likes;
	private Date reg_date;
	private int recomment_cnt;
	private String title;
	private Date released;
	private byte[] poster_img;
	private byte[] profile_img;
	private float rate;
	
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public byte[] getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(byte[] profile_img) {
		this.profile_img = profile_img;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getReleased() {
		return released;
	}
	public void setReleased(Date released) {
		this.released = released;
	}
	public byte[] getPoster_img() {
		return poster_img;
	}
	public void setPoster_img(byte[] poster_img) {
		this.poster_img = poster_img;
	}
	public Integer getComment_num() {
		return comment_num;
	}
	public void setComment_num(Integer comment_num) {
		this.comment_num = comment_num;
	}
	public Integer getMovie_num() {
		return movie_num;
	}
	public void setMovie_num(Integer moive_num) {
		this.movie_num = moive_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getRecomment_cnt() {
		return recomment_cnt;
	}
	public void setRecomment_cnt(int recomment_cnt) {
		this.recomment_cnt = recomment_cnt;
	}
	@Override
	public String toString() {
		return "CommentCommand [comment_num=" + comment_num + ", moive_num=" + movie_num + ", id=" + id + ", content="
				+ content + ", likes=" + likes + ", reg_date=" + reg_date + ", recomment_cnt=" + recomment_cnt + "]";
	}
}
