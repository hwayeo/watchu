package kr.watchu.user.domain;

public class NaverUserCommand {
	private String id;
	private String profile_image;
	private int auth;
	private String permit;
	private String age;
	private String email;
	private String name;
	private String type;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPermit() {
		return permit;
	}
	public void setPermit(String permit) {
		this.permit = permit;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "NaverUserCommand [id=" + id + ", profile_image=" + profile_image + ", auth=" + auth + ", permit="
				+ permit + ", age=" + age + ", email=" + email + ", name=" + name + ", type=" + type + "]";
	}
}
