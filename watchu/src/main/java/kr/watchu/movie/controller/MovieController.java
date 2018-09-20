package kr.watchu.movie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.watchu.movie.domain.GenreCommand;
import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.domain.OfficialsCommand;
import kr.watchu.movie.service.GenreService;
import kr.watchu.movie.service.MovieService;
import kr.watchu.movie.service.OfficialsService;
import kr.watchu.movie.service.RecommendService;
import kr.watchu.util.PagingUtil;

@Controller
public class MovieController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private MovieService movieService;

	@Resource
	private GenreService genreService;  

	@Resource
	private RecommendService recommendService;

	@Resource
	private OfficialsService officialsService;
	@RequestMapping("/movie/movieHome.do")
	public ModelAndView movieHome() {

		String recomment = recommendService.selectRanGenre();

		ModelAndView mav = new ModelAndView();	
		mav.setViewName("movieHome");
		mav.addObject("recomment",recomment);
		return mav;
	}

	@RequestMapping("/movie/movieList.do")
	public ModelAndView mlist2(
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="" ) String keyword,
			HttpSession session){

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

		List<MovieCommand> movieInfo = null;
		List<GenreCommand> movieGenre = null;

		movieInfo = movieService.selectMovieList(map);
		movieGenre = genreService.selectGenreList(map);

		String id = (String)session.getAttribute("user_id");

		ModelAndView mav = new ModelAndView();
		if(id != null) {
			mav.setViewName("movieList");
			mav.addObject("count",count);
			mav.addObject("movieInfo",movieInfo);
			mav.addObject("movieGenre",movieGenre);
			mav.addObject("pagingHtml",page.getPagingHtml());
		}else {
			mav.setViewName("redirect:/user/login.do");
		}

		return mav;
	}

	@RequestMapping("/movie/movieEva.do")
	public ModelAndView mlist3(
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="" ) String keyword,
			HttpSession session) {

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

		List<MovieCommand> movieInfo = null;
		List<GenreCommand> movieGenre = null;

		movieInfo = movieService.selectMovieList(map);
		movieGenre = genreService.selectGenreList(map);

		String id = (String)session.getAttribute("user_id");

		ModelAndView mav = new ModelAndView();
		if(id != null) {
			mav.setViewName("movieEva");
			mav.addObject("count",count);
			mav.addObject("movieInfo",movieInfo);
			mav.addObject("movieGenre",movieGenre);
			mav.addObject("pagingHtml",page.getPagingHtml());
		}else {
			mav.setViewName("redirect:/user/login.do");
		}

		return mav;
	}

	@RequestMapping("/movie/imageView.do")
	public ModelAndView viewImage2(@RequestParam("movie_num") int movie_num,
			@RequestParam("type") String type) {
		//한 건의 데이터를 받아 객체 생성
		MovieCommand movie = movieService.selectMovie(movie_num);
		if(log.isDebugEnabled()) {
			log.debug("[[movie_num]] : " + movie_num);
		}
		//데이터를 ModelAndView객체에 차곡차곡 저장
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		//속성명		속성값(byte[]의 데이터)
		mav.addObject("filename","poster.jpg");
		if(type.equals("banner")) {
			mav.addObject("imageFile", movie.getBanner_img());
		}else {
			mav.addObject("imageFile", movie.getPoster_img());
		}

		return mav;
	}
	@RequestMapping("/movie/actorView.do")
	public ModelAndView viewImage3(@RequestParam("off_num") Integer off_num) {
		OfficialsCommand officials = officialsService.detailOfficials(off_num);
		if(log.isDebugEnabled()) {
			log.debug("[[off_num]] : " + off_num);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("filename","actor.jpg");
		mav.addObject("imageFile",officials.getOff_photo());
		return mav;
	}
}
