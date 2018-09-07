package kr.watchu.movie.domain;
 
public class GenreCommand {
	private Integer genre_num;
	private String genre;
	
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
	
	@Override
	public String toString() {
		return "GenreCommand [genre_num=" + genre_num + ", genre=" + genre + "]";
	}	
}
