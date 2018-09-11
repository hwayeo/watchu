package kr.watchu.movie.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.watchu.movie.domain.AnalysisGenreCommand;
import kr.watchu.movie.domain.AnalysisOffCommand;
import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.domain.MovieratedCommand;
import kr.watchu.movie.service.AnalysisGenreService;
import kr.watchu.movie.service.AnalysisOffService;
import kr.watchu.movie.service.MovieService;
import kr.watchu.movie.service.MovieratedService;
import kr.watchu.util.SplitUtil;

@Controller
public class MovieratedController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource 
	private MovieratedService ms;
	
	@Resource 
	private MovieService movieService;
	
	@Resource
	private AnalysisGenreService analysisGenreService;
	@Resource 
	private AnalysisOffService analysisOffService;
	@ModelAttribute("rateCommand")
	public MovieratedCommand initCommandRate() {
		return new MovieratedCommand();
	}
	
	@ModelAttribute("movieCommand")
	public MovieCommand initMovieCommand() {
		return new MovieCommand();
	}
	
	
	@ModelAttribute("analysisGenreCommand")
	public AnalysisGenreCommand initAnalysisGenreCommand() {
		return new AnalysisGenreCommand();
	}
	
	@ModelAttribute("analysisOffCommand")
	public AnalysisOffCommand initAnalysisOffCommand() {
		return new AnalysisOffCommand();
	}
	//영화 평가목록 호출
	@RequestMapping("/movie/rating.do")
	@ResponseBody
	public Map<String,String> insertRated(@ModelAttribute("rateCommand") MovieratedCommand im) {
		
		
		Map<String,String> map = new HashMap<String, String>();
	
		//기존 영화 평가 데이터를 받아오기 위해 파라미터값을 넣어주는 맵
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("id", im.getId());
		data.put("movie_num", im.getMovie_num());
		
		MovieCommand movie = movieService.selectMovie(im.getMovie_num());

		String main_genre = movie.getMain_genre();
		
		AnalysisGenreCommand main = new AnalysisGenreCommand();
		if(main_genre!=null) {
			main.setId(im.getId());
			main.setMovie_num(im.getMovie_num());
			main.setGenre(main_genre);
			main.setRate(im.getRate());
		}
		
		String[] directors = SplitUtil.splitByComma(movie.getDirector());
		String[] actors = SplitUtil.splitByComma(movie.getActors());
		
		String sub_genre = movie.getSub_genre();
		AnalysisGenreCommand sub = null;
		sub = new AnalysisGenreCommand();
		if(sub_genre!=null) {
			sub.setId(im.getId());
			sub.setMovie_num(im.getMovie_num());
			sub.setGenre(sub_genre);
			sub.setRate(im.getRate());
		}
		MovieratedCommand origin = ms.selectMovierated(data);
		
		if(log.isDebugEnabled()) {
			log.debug("<<origin>> : " + origin);
		}
		
		if(origin == null) {
			//insert
			
				ms.insertMovierated(im);
				if(log.isDebugEnabled()) {
					log.debug("<<++동작++>>");
				}
				if(main_genre!=null) {
					analysisGenreService.insertGenreRate(main);
				}
				if(sub_genre!=null) {
					analysisGenreService.insertGenreRate(sub);
				}
				if(directors != null) {
					AnalysisOffCommand director = null;
					for(int i=0;i<directors.length;i++) {
						director = new AnalysisOffCommand();
						director.setMovie_num(im.getMovie_num());
						director.setId(im.getId());
						director.setName(directors[i]);
						director.setRate(im.getRate());
						analysisOffService.insertOffRate(director);
					}
				}
				if(actors != null) {
					AnalysisOffCommand actor = null;
					for(int i=0;i<actors.length;i++) {
						actor = new AnalysisOffCommand();
						actor.setMovie_num(im.getMovie_num());
						actor.setId(im.getId());
						actor.setName(actors[i]);
						actor.setRate(im.getRate());
						analysisOffService.insertOffRate(actor);
					}
				}
				map.put("result", "insert");
		}else {
			//update
			try {
				ms.updateMovierated(im);
				if(log.isDebugEnabled()) {
					log.debug("<<main>> : " + main);
					log.debug("<<sub>> : " + sub);
				}
				if(main_genre!=null) {
					analysisGenreService.updateGenreRate(main);
				}
				if(sub_genre!=null) {
					analysisGenreService.updateGenreRate(sub);
				}
				if(directors != null) {
					AnalysisOffCommand director = null;
					for(int i=0;i<directors.length;i++) {
						director = new AnalysisOffCommand();
						director.setMovie_num(im.getMovie_num());
						director.setId(im.getId());
						director.setName(directors[i]);
						director.setRate(im.getRate());
						analysisOffService.updateOffRate(director);
					}
				}
				if(actors != null) {
					AnalysisOffCommand actor = null;
					for(int i=0;i<actors.length;i++) {
						actor = new AnalysisOffCommand();
						actor.setMovie_num(im.getMovie_num());
						actor.setId(im.getId());
						actor.setName(actors[i]);
						actor.setRate(im.getRate());
						analysisOffService.updateOffRate(actor);
					}
				}
				map.put("result", "update");
			}catch(Exception e){
				map.put("result", "failure");
			}
		}
		
		return map;		
	}
}