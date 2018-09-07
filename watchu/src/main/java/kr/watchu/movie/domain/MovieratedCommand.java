package kr.watchu.movie.domain;

public class MovieratedCommand {
	private Integer movie_num;
	private String id;
	private float rate;
	
	public Integer getMovie_num() {
		return movie_num;
	}
	public void setMovie_num(Integer movie_num) {
		this.movie_num = movie_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	
	@Override
	public String toString() {
		return "MovieratedCommand [movie_num=" + movie_num + ", id=" + id + ", rate=" + rate + "]";
	}
}