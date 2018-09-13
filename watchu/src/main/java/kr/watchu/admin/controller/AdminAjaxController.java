package kr.watchu.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.watchu.movie.domain.GenreCommand;
import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.domain.OfficialsCommand;
import kr.watchu.movie.service.CommentService;
import kr.watchu.movie.service.GenreService;
import kr.watchu.movie.service.MovieService;
import kr.watchu.movie.service.MovieratedService;
import kr.watchu.movie.service.OfficialsService;
import kr.watchu.util.PagingUtil;

@Controller
public class AdminAjaxController {	
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private OfficialsService officialsService;
	@Resource
	private GenreService genreService;
	@Resource
	private MovieService movieService;
	@Resource
	private CommentService commentService;
	@Resource
	private MovieratedService movieRatedService;
	
	@ModelAttribute("command")
	public OfficialsCommand initCommand() {
		return new OfficialsCommand();
	}
	@ModelAttribute("command2")
	public GenreCommand initCommand2() {
		return new GenreCommand();
	}
	@ModelAttribute("command3")
	public MovieCommand initCommand3() {
		return new MovieCommand();
	}
	
	//======================감독/배우 자동완성======================//
	@RequestMapping("/admin/auto_offList.do")
	@ResponseBody
	public Map<String, Object> getAuto_offList(@RequestParam(value="keyfield",defaultValue="") String keyfield,
											   @RequestParam(value="keyword",defaultValue="" ) String keyword){
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> mapJson = new HashMap<String, Object>();
		data.put("keyfield", keyfield);
		data.put("keyword", keyword);
		
		List<OfficialsCommand> list = officialsService.selectOffAjaxList(data);
		mapJson.put("list", list);
		if(log.isDebugEnabled()) {
			log.debug("<<list>> : " + list);
		}
		
		return mapJson;
	}
	
	//======================장르 자동완성======================//
	@RequestMapping("/admin/auto_genreList.do")
	@ResponseBody
	public Map<String, Object> getAuto_genreList(@RequestParam(value="keyfield",defaultValue="") String keyfield,
											     @RequestParam(value="keyword",defaultValue="" ) String keyword){
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> mapJson = new HashMap<String, Object>();
		data.put("keyfield", keyfield);
		data.put("keyword", keyword);
		
		List<GenreCommand> genre_list = genreService.selectGenreAjaxList(data);
		mapJson.put("genre_list", genre_list);
		if(log.isDebugEnabled()) {
			log.debug("<<genre_list>> : " + genre_list);
		}
		
		return mapJson;
	}
	
	//======================영화 선택 삭제======================//
	@RequestMapping(value="/admin/check_movieDel.do", method=RequestMethod.POST)
	@ResponseBody
	public void check_movieDel(@RequestParam(value="c_movieTest[]") List<String> c_movie) {
		for(int k = 0; k < c_movie.size(); k++) {
			int movie_num = Integer.parseInt(c_movie.get(k));
			movieRatedService.deleteRatedByMovie(movie_num);
			commentService.deleteCommentByMovie(movie_num);
			movieService.deleteMovie(movie_num);
		}
	}
	
	//======================관계자 선택 삭제======================//
	@RequestMapping(value="/admin/check_offDel.do", method=RequestMethod.POST)
	@ResponseBody
	public void check_offDel(@RequestParam(value="c_offTest[]") List<String> c_off) {
		for(int j = 0; j < c_off.size(); j++) {
			int off_num = Integer.parseInt(c_off.get(j));
			officialsService.delete(off_num);
		}
	}
	
	//======================장르 선택 삭제======================//
	@RequestMapping(value="/admin/check_genreDel.do", method=RequestMethod.POST)
	@ResponseBody
	public void check_genreDel(@RequestParam(value="c_genreTest[]") List<String> c_genre) {
		for(int i = 0; i < c_genre.size(); i++) {
			int genre_num = Integer.parseInt(c_genre.get(i));
			genreService.deleteGenre(genre_num);
		}
	}
	
	//======================관계자 구분======================//

}
