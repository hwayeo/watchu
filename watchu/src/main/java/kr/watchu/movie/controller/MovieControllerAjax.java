package kr.watchu.movie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.service.MovieService;
import kr.watchu.util.PagingUtil;

	@Controller
	public class MovieControllerAjax {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource 
	private MovieService movieService;
	
	@ModelAttribute("command")
	public MovieCommand initCommand() {
		return new MovieCommand();
	}
	
	@RequestMapping("/movie/movieMlist.do") 
	@ResponseBody
	public Map<String,Object> getMovieList(
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="" ) String keyword){
		
		int rowCount = 4;
		int pageCount = 10;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		if(log.isDebugEnabled()) {
			log.debug("<<ajaxpage>> : " + currentPage);
		}
		
		int count = movieService.selectMovieAjaxCnt(map);
		
		PagingUtil page = new PagingUtil(currentPage,count,rowCount,pageCount,null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MovieCommand> list = null;
		
		if(log.isDebugEnabled()) {
			log.debug("<<ajaxcount>> : " + count);
		}
		
		if(count > 0) {
			list = movieService.selectMovieAjaxList(map);
		}
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);
		
		return mapJson;
	}
	
	@RequestMapping("/movie/movieMlist2.do") 
	@ResponseBody
	public Map<String,Object> getMovieList2(
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="" ) String keyword
			){
		
		int rowCount = 24;
		int pageCount = 10;
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		if(log.isDebugEnabled()) {
			log.debug("<<ajax2page>> : " + currentPage);
		}
		
		int count = movieService.selectMovieAjaxCnt2(map);
		
		PagingUtil page = new PagingUtil(currentPage,count,rowCount,pageCount,null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MovieCommand> list = null;
		
		if(log.isDebugEnabled()) {
			log.debug("<<ajax2count>> : " + count);
		}
		
		if(count > 0) {
			list = movieService.selectMovieAjaxList2(map);
		}
		 
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);
		return mapJson;
	}
}
