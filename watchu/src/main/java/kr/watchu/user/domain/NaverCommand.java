package kr.watchu.user.domain;

public class NaverCommand {
	private String resultcode;
	private String message;
	private NaverUserCommand response;
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public NaverUserCommand getResponse() {
		return response;
	}
	public void setResponse(NaverUserCommand response) {
		this.response = response;
	}
	@Override
	public String toString() {
		return "NaverCommand [resultcode=" + resultcode + ", message=" + message + ", response=" + response + "]";
	}
}
