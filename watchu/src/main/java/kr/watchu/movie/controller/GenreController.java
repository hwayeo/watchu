package kr.watchu.movie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.watchu.movie.domain.GenreCommand;
import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.service.GenreService;
import kr.watchu.movie.service.MovieService;
import kr.watchu.util.PagingUtil;
import kr.watchu.util.StringUtil;

	@Controller
	public class GenreController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private GenreService genreService;

	@ModelAttribute("command")
	public MovieCommand initCommand() {
		return new MovieCommand();
	}

	//장르 추가
	@RequestMapping("/movie/genreList.do")
	@ResponseBody
	public Map<String,Object> getGenreList(
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="" ) String keyword){
		
		int rowCount = 50;
		int pageCount = 10;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		if(log.isDebugEnabled()) {
			log.debug("<<genrePage>> : " + currentPage);
		}
		
		int count = genreService.selectGenreCnt(map);
		
		PagingUtil page = new PagingUtil(currentPage,count,rowCount,pageCount,null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<GenreCommand> list = null;
		
		if(log.isDebugEnabled()) {
			log.debug("<<genreCount>> : " + count);
		}
		
		if(count > 0) {
			list = genreService.selectGenreList(map);
		}
		
		Map<String,Object> genJson = new HashMap<String,Object>();
		genJson.put("count", count);
		genJson.put("rowCount", rowCount);
		genJson.put("list", list);
		
		return genJson;
	}
}
