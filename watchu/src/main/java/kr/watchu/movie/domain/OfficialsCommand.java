package kr.watchu.movie.domain;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class OfficialsCommand {
	 
	private Integer off_num;
	private String name;
	private String jobs;
	private String filmograp;
	private MultipartFile upload;
	private byte[] off_photo;
	private float rate;
	private Integer cnt;
	public Integer getOff_num() {
		return off_num;
	}
	public void setOff_num(Integer off_num) {
		this.off_num = off_num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
	}
	public String getFilmograp() {
		return filmograp;
	}
	public void setFilmograp(String filmograp) {
		this.filmograp = filmograp;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) throws IOException {
		this.upload = upload;
		setOff_photo(upload.getBytes());
	}
	public byte[] getOff_photo() {
		return off_photo;
	}
	public void setOff_photo(byte[] off_photo) {
		this.off_photo = off_photo;
	}
	
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "OfficialsCommand [off_num=" + off_num + ", name=" + name + ", jobs=" + jobs + ", filmograp=" + filmograp
				+ ", upload=" + upload + ", off_photo=" + Arrays.toString(off_photo) + ", rate=" + rate + "]";
	}
	
}
