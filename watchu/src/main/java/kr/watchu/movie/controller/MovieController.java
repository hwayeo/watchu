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
	
	//===영화 메인 목록===//
	@RequestMapping("/movie/movieHome.do")
	public ModelAndView mlist1(
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="" ) String keyword) {
		
		int rowCount = 4;
		int pageCount = 10;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//총 글 카운트
		int count = movieService.selectMovieCnt(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"movieHome.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MovieCommand> list = null;
		
		list = movieService.selectMovieList(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<list>> : " + list);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movieHome");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml",page.getPagingHtml());
		
		return mav;
	}
	
	@RequestMapping("/movie/movieList.do")
	public ModelAndView mlist2(
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="" ) String keyword) {
		
		int rowCount = 24;
		int pageCount = 10;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//총 글 카운트
		int count = movieService.selectMovieCnt(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<리스트 총값>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"movieList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MovieCommand> list = null;
		List<GenreCommand> list2 = null;
		
		list = movieService.selectMovieList(map);
		list2 = genreService.selectGenreList(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<리스트 내용값>> : " + list);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movieList");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("list2",list2);
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
		
		//총 글 카운트
		int count = movieService.selectMovieCnt(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"movieEva.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MovieCommand> list = null;
		
		list = movieService.selectMovieList(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<list>> : " + list);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movieEva");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml",page.getPagingHtml());
		
		return mav;
	}
}
