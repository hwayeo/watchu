package kr.watchu.util;

import java.util.List;

import kr.watchu.movie.domain.MovieCommand;

public class RecommendUtil {
	
	private List<MovieCommand> recommendedList;
	
	/*
	 * 들어가야 할 인자값
	 * 
	 * 결과값의 수
	 * 
	 * 
	 */
	public RecommendUtil() {
		
	}
	
	public List<MovieCommand> getRecommendList(){
		return recommendedList;
	}
}
