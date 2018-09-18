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
	//�ڹٺ� �ʱ�ȭ
	@ModelAttribute("commentCommand")
	public CommentCommand initCommentCommand() {
		return new CommentCommand();
	}
	@ModelAttribute("commentRated")
	public MovieratedCommand initCommandRate() {
		return new MovieratedCommand();
	}

	//�� �� �����
	@RequestMapping("/movie/movieDetail.do")
	public ModelAndView movieDetail(@RequestParam("movie_num") Integer movie_num, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		
		//��ȭ �⺻ ����
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
		//�ش� ��ȭ�� �ۼ��� ����� �ִ��� Ȯ��
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
		//����� �帣 ��ȭ ��õ
		List<MovieCommand> movieList = new ArrayList<MovieCommand>();
		
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("keyfield","genre");
		map2.put("keyword", movie.getMain_genre());
		map2.put("start" , 1);
		map2.put("end", 16);
		
		movieList = movieService.selectMovieAjaxList2(map2);
	
		mav.addObject("movie",movie);
		mav.addObject("commentList",commentList);
		mav.addObject("commentCnt",commentCnt);
		mav.addObject("actorList",actorList);
		mav.addObject("movieList",movieList);
		
		return mav;
		
	}
	//�ڸ�Ʈ ��� �� 
	@RequestMapping(value="/movie/commentWrite.do",method=RequestMethod.GET)
	public String commentForm() {
		return "movieDetail";
	}
	//�ڸ�Ʈ ������ ���� 
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
	//==================================�ڸ�Ʈ ����=============================
	
	//�ڸ�Ʈ���� ������ ����
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
	
	//ȸ�� ������ ���� 
	@RequestMapping("/movie/deleteComment.do")
	public String deleteComment(@RequestParam("comment_num") Integer comment_num, 
			 					@RequestParam("movie_num") Integer movie_num) {
		
		
		commentService.deleteComment(comment_num);
		
		return "redirect:/movie/movieDetail.do?movie_num="+movie_num;
	}
	
	//�ڸ�Ʈ ���� Ȯ�� 
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
}