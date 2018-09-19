package kr.watchu.movie.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.watchu.movie.domain.CommentCommand;
import kr.watchu.movie.domain.MovieCommand;
import kr.watchu.movie.domain.MovieratedCommand;
import kr.watchu.movie.domain.OfficialsCommand;
import kr.watchu.movie.service.CommentService;
import kr.watchu.movie.service.MovieService;
import kr.watchu.movie.service.MovieratedService;
import kr.watchu.movie.service.OfficialsService;
import kr.watchu.user.controller.userConfirmIdAjaxController;
import kr.watchu.user.service.UserService;
import kr.watchu.util.PagingUtil;
import kr.watchu.util.PagingUtil2;
import kr.watchu.util.SplitUtil;
import kr.watchu.util.StringUtil;

@Controller
public class MovieViewController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private MovieService movieService;
	@Resource
	private CommentService commentService;
	@Resource
	private OfficialsService officialsService;
	@Resource
	private MovieratedService movieratedService;
	//자바빈 초기화
	@ModelAttribute("commentCommand")
	public CommentCommand initCommentCommand() {
		return new CommentCommand();
	}
	@ModelAttribute("commentRated")
	public MovieratedCommand initCommandRate() {
		return new MovieratedCommand();
	}

	//글 상세 보기용
	@RequestMapping("/movie/movieDetail.do")
	public ModelAndView movieDetail(@RequestParam("movie_num") Integer movie_num, 
									@RequestParam(value="pageNum",defaultValue="1") int currentPage ,HttpSession session) {

		ModelAndView mav = new ModelAndView();
		
		//영화 기본 정보
		MovieCommand movie = movieService.selectMovie(movie_num);
		
		List<OfficialsCommand> actorList = new ArrayList<OfficialsCommand>();
		
		String[] actors = SplitUtil.splitByComma(movie.getActors());
		String[] directors = SplitUtil.splitByComma(movie.getDirector());
		
		for(int i=0;i<actors.length;i++) {
			actorList.add(officialsService.selectOfficials(actors[i]));
		}
		for(int i=0;i<directors.length;i++) {
			actorList.add(officialsService.selectOfficials(directors[i]));
		}

		List<CommentCommand> commentList = new ArrayList<CommentCommand>();

		commentList = commentService.selectCommentList(movie_num);

		int commentCnt = commentService.selectCommentCnt(movie_num);

		mav.setViewName("movieDetail");
		//해당 영화에 작성한 댓글이 있는지 확인
		String id = (String)session.getAttribute("user_id");
		
		if(id!=null) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", id);
			map.put("movie_num", movie_num);
			CommentCommand comment = commentService.selectComment(map);
			
			if(log.isDebugEnabled()){
				log.debug("<<id>> : " + id);
				log.debug("<<movie_num>> : " + movie_num);
				log.debug("<<comment>> : " + comment);
			}
			if(comment!=null) {
				mav.addObject("comment",comment);
			}else {
				mav.addObject("comment",null);
			}
		}
		//비슷한 장르 영화 추천
		List<MovieCommand> movieList = new ArrayList<MovieCommand>();
		
		int rowCount = 4;
		int pageCount = 5;
		
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("keyfield","genre");
		map2.put("keyword", movie.getMain_genre());
		
		Integer count = movieService.selectMovieAjaxCnt2(map2);
		PagingUtil2 page = new PagingUtil2(currentPage,count,rowCount,pageCount,"movieDetail.do","&movie_num="+movie_num);
		
		map2.put("start" , page.getStartCount());
		map2.put("end", page.getEndCount());
		
		if(count > 0) {
			movieList = movieService.selectMovieAjaxList2(map2);
		}
	
		mav.addObject("movie",movie);
		mav.addObject("commentList",commentList);
		mav.addObject("commentCnt",commentCnt);
		mav.addObject("actorList",actorList);
		mav.addObject("movieList",movieList);
		mav.addObject("count",count);
		mav.addObject("pagingHtml",page.getPagingHtml());
		return mav;
		
	}
	//코멘트 등록 폼 
	@RequestMapping(value="/movie/commentWrite.do",method=RequestMethod.GET)
	public String commentForm() {
		return "movieDetail";
	}
	//코멘트 데이터 전송 
	@RequestMapping(value="/movie/commentWrite.do",method=RequestMethod.POST)
	public String insertComment(@ModelAttribute("comentCommand") @Valid CommentCommand commentCommand, BindingResult result, HttpSession session) {

		if(log.isDebugEnabled()) {
			log.debug("<<commentCommand>> : "+commentCommand);
		}
		if(result.hasErrors()) {
			return commentForm();
		}

		String id = (String)session.getAttribute("user_id");

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("movie_num", commentCommand.getMovie_num());

		CommentCommand comment = commentService.selectComment(map);
		if(comment!=null) {
			return "redirect:/movie/movieDetail.do?movie_num="+commentCommand.getMovie_num();
		}else {
			commentService.insertComment(commentCommand);
		}
		return "redirect:/movie/movieDetail.do?movie_num="+commentCommand.getMovie_num();
	}
	//==================================코멘트 수정=============================
	
	//코멘트수정 데이터 전송
	@RequestMapping(value="/movie/updateCommentWrite.do",method=RequestMethod.POST)
	public String updateComment(@ModelAttribute("commentCommand") @Valid CommentCommand commentCommand, BindingResult result, HttpSession session) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<commentCommand>> :" + commentCommand);
		}
		if(result.hasErrors()) {
			return "redirect:/movie/movieDetail.do?movie_num="+commentCommand.getMovie_num();
		}
		
		commentService.updateComment(commentCommand);
		
		return "redirect:/movie/movieDetail.do?movie_num="+commentCommand.getMovie_num();
		}
	
	//회원 데이터 삭제 
	@RequestMapping("/movie/deleteComment.do")
	public String deleteComment(@RequestParam("comment_num") Integer comment_num, 
			 					@RequestParam("movie_num") Integer movie_num) {
		
		
		commentService.deleteComment(comment_num);
		
		return "redirect:/movie/movieDetail.do?movie_num="+movie_num;
	}
	
	//코멘트 별점 확인 
	@RequestMapping("/movie/commentRated.do")
	@ResponseBody
	public Map<String,Object> commentRated(@RequestParam("movie_num") Integer movie_num,
										   @RequestParam("id") String id) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<======movie_num======> : " + movie_num);
			log.debug("<<======id======> : " + id);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("movie_num", movie_num);
		map.put("id", id);
		
		MovieratedCommand ratedcomment = movieratedService.selectMovierated(map);
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		if(ratedcomment!=null) {
			jsonMap.put("result","submit");
		}else {
			jsonMap.put("result","failure");
		}
		return jsonMap;
	}
	
	//좋아요 기능
	//기존 데이터 확인
	@RequestMapping("/movie/likeComment.do")
	@ResponseBody
	public Map<String,Object> likeComment(@RequestParam("comment_num") Integer comment_num,
										  @RequestParam("id") String id){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("comment_num", comment_num);
		map.put("id", id);
		
		String origin = commentService.selectCommentLike(map);
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		if(origin!=null) {
			//좋아요 취소
			jsonMap.put("result", "submit");
		}else {
			//좋아요 누적
			jsonMap.put("result", "failure");
		}
		return jsonMap;
	}
	
	//좋아요 누적 -> insert, 좋아요 수 +1
	@RequestMapping("/movie/insertLike.do")
	@ResponseBody
	public Map<String,Object> insertLike(@RequestParam("comment_num")Integer comment_num,
										 @RequestParam("id") String id){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("comment_num", comment_num);
		map.put("id", id);
		
		commentService.insertCommentLike(map);
		
		int originlikes = commentService.selectLikes(comment_num);
		
		map.put("likes", originlikes+1);
		
		commentService.updateCommentWithLike(map);
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		
		jsonMap.put("result", "submit");
		
		return jsonMap;
	}
	//좋아요 취소 -> 기존 데이터 삭제, movie_comment에 해당 행의 좋아요수 차감
	@RequestMapping("/movie/deleteLike.do")
	@ResponseBody
	public Map<String,Object> deleteLike(@RequestParam("comment_num")Integer comment_num,
										 @RequestParam("id") String id){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("comment_num", comment_num);
		map.put("id", id);
		
		int originlikes = commentService.selectLikes(comment_num);
		
		map.put("likes", originlikes-1);
		
		commentService.deleteCommentLike(map);
		
		commentService.updateCommentWithLike(map);
		
		map.put("comment_num", comment_num);
		map.put("id", id);
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		
		jsonMap.put("result", "submit");
		
		return jsonMap;
	}
}