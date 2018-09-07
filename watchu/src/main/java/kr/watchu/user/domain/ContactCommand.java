package kr.watchu.user.domain;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class ContactCommand {
	private Integer contact_num; //글번호
	private String id;
	private String content; //글내용
	private MultipartFile upload; //업로드파일
	private String filename; //파일명
	private byte[] upload_file; //DB에 저장된 파일
	private Date reg_date;
	private Integer recotentable;
	private String title;
	
	public Integer getContact_num() {
		return contact_num;
	}
	public void setContact_num(Integer contact_num) {
		this.contact_num = contact_num;
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
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) throws IOException {
		this.upload = upload;
		setFilename(upload.getOriginalFilename());
		setUpload_file(upload.getBytes());
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public byte[] getUpload_file() {
		return upload_file;
	}
	public void setUpload_file(byte[] upload_file) {
		this.upload_file = upload_file;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Integer getRecotentable() {
		return recotentable;
	}
	public void setRecotentable(Integer recotentable) {
		this.recotentable = recotentable;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "ContactCommand [contact_num=" + contact_num + ", id=" + id + ", content=" + content + ", upload="
				+ upload + ", filename=" + filename + ", reg_date=" + reg_date + ", recotentable=" + recotentable
				+ ", title=" + title + "]";
	}
}
