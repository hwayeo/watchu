package kr.watchu.user.domain;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class UserCommand {
	@NotEmpty
	private String id;
	private int auth;
	@Pattern(regexp="^[A-Za-z0-9+]{4,12}$")
	private String passwd;
	@NotEmpty
	private String name;
	@NotEmpty
	private String phone;
	@NotEmpty
	private String email;
	private String follow;
	private String follower;
	private String block;
	private MultipartFile upload;
	private byte[] profile_img;
	private Date reg_date;
	private String permit;
	
	public String getPermit() {
		return permit;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}

	//비밀번호 체크
	public boolean isCheckedPasswd(String userPasswd) {
		if(auth > 0 && passwd.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	
	public void setUpload(MultipartFile upload) throws IOException{
		this.upload = upload;
		setProfile_img(upload.getBytes()); 
	}
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	
	public byte[] getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(byte[] profile_img) {
		this.profile_img = profile_img;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getFollow() {
		return follow;
	}

	public void setFollow(String follow) {
		this.follow = follow;
	}

	public String getFollower() {
		return follower;
	}

	public void setFollower(String follower) {
		this.follower = follower;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	@Override
	public String toString() {
		return "UserCommand [id=" + id + ", auth=" + auth + ", passwd=" + passwd + ", name=" + name + ", phone=" + phone
				+ ", email=" + email + ", follow=" + follow + ", follower=" + follower + ", block=" + block
				+ ", upload=" + upload + ", reg_date=" + reg_date + ", permit=" + permit + "]";
	}
}
