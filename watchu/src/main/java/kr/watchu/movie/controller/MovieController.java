package kr.watchu.movie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.watchu.movie.domain.GenreCommand;
import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.service.GenreService;
import kr.watchu.movie.service.MovieService;
import kr.watchu.util.PagingUtil;
import kr.watchu.util.StringUtil;

@Controller
public class MovieController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private MovieService movieService;
	
	@Resource
	private GenreService genreService; 
	
	@RequestMapping("/movie/movieHome.do")
	public String movieHome() {
		return "movieHome";
	}
	/*@RequestMapping("/movie/movieList.do")
	public String movieList() {
		return "movieList";
	}*/
	
	@RequestMapping("/movie/movieList.do")
	public ModelAndView mlist2(
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="" ) String keyword){
		
		int rowCount = 24;
		int pageCount = 10;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//ÃÑ ±Û Ä«¿îÆ®
		int count = movieService.selectMovieCnt(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<¸®½ºÆ® ÃÑ°ª>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"movieList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MovieCommand> movieInfo = null;
		List<GenreCommand> movieGenre = null;
		
		movieInfo = movieService.selectMovieList(map);
		movieGenre = genreService.selectGenreList(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movieList");
		mav.addObject("count",count);
		mav.addObject("movieInfo",movieInfo);
		mav.addObject("movieGenre",movieGenre);
		mav.addObject("pagingHtml",page.getPagingHtml());
		
		return mav;
	}

	@RequestMapping("/movie/movieEva.do")
	public ModelAndView mlist3(
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="" ) String keyword) {
		
		int rowCount = 24;
		int pageCount = 10;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//ÃÑ ±Û Ä«¿îÆ®
		int count = movieService.selectMovieCnt(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"movieList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MovieCommand> movieInfo = null;
		List<GenreCommand> movieGenre = null;
		
		movieInfo = movieService.selectMovieList(map);
		movieGenre = genreService.selectGenreList(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movieEva");
		mav.addObject("count",count);
		mav.addObject("movieInfo",movieInfo);
		mav.addObject("movieGenre",movieGenre);
		mav.addObject("pagingHtml",page.getPagingHtml());
		
		return mav;
	}
}
