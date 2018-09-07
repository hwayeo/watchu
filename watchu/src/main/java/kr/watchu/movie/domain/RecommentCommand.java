package kr.watchu.movie.domain;

import java.util.Date;

public class RecommentCommand {
	
	private Integer recomment_num;
	private Integer comment_num;
	private String content;
	private Date reg_date;
	
	public Integer getRecomment_num() {
		return recomment_num;
	}
	public void setRecomment_num(Integer recomment_num) {
		this.recomment_num = recomment_num;
	}
	public Integer getComment_num() {
		return comment_num;
	}
	public void setComment_num(Integer comment_num) {
		this.comment_num = comment_num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "RecommentCommand [recomment_num=" + recomment_num + ", comment_num=" + comment_num + ", content="
				+ content + ", reg_date=" + reg_date + "]";
	}
	
	
}
