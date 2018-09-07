package kr.watchu.user.domain;

public class NaverUserCommand {
	
	private String id;
	private String profile_image;
	private String age;
	private String email;
	private String name;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
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
	@Override
	public String toString() {
		return "NaverUserCommand [id=" + id + ", profile_image=" + profile_image + ", age=" + age + ", email=" + email
				+ ", name=" + name + "]";
	}
}
