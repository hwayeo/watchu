package kr.watchu.movie.domain;
 
public class GenreCommand {
	private Integer genre_num;
	private String genre;
	private float rate;
	private Integer cnt;
	
	public Integer getGenre_num() {
		return genre_num;
	}
	public void setGenre_num(Integer genre_num) {
		this.genre_num = genre_num;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
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
		return "GenreCommand [genre_num=" + genre_num + ", genre=" + genre + ", rate=" + rate + ", cnt=" + cnt + "]";
	}
}
