<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/movie-detail.css">	
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/watchu-view.css">	
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.bxslider.min.css">	
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/starRated.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modal-comment.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.bxslider.min.js"></script>
 
<div id="main-page"> 
	<div id="main_banner">
		<div id="img-banner">
			<input type="hidden" id="movie_num2" value="${movie.movie_num}">
			<input type="hidden" value="${movie.trailer}">
			<div class="preview">
				<c:if test="${!empty movie.trailer}">
				<span class="glyphicon glyphicon-play-circle" onclick="location.href='https://www.youtube.com/embed/${movie.trailer}'" style="cursor:pointer; color: rgba(255,255,255,0.15)"></span>
				</c:if>
				<c:if test="${empty movie.trailer}">
				<span></span>
				</c:if>
			</div>
		</div> 
	</div>
</div>
<div class="container-fluid">
	<!-- 상단부분 시작 -->
	<div class="row">
		<!-- 영화 기본정보 pc 화면 -->
	<div class="col-sm-12 col-md-12 col-xs-12 hidden-xs">
		 <div class="col-sm-6 col-md-6 col-xs-6" id="poster3">
			<div class="hidden-xs"><img src="imageView.do?movie_num=${movie.movie_num}&type=poster" id="image2"></div> 
		</div>
	
	<div class="col-sm-6 col-md-6 col-xs-6" id="poster2">
		 <h3><b style="font-size:30px;">${movie.title}</b></h3>
		 	<p style="font-size:18px;"><fmt:formatDate value="${movie.released}" pattern="yyyy"/>&nbsp;&middot;&nbsp;${movie.main_genre}&nbsp;&middot;&nbsp;${movie.country}</p>
		 		<hr width="65%" align="left">	
					<p style="font-size:18px;">평점★  ${movie.rate }</p>
				<hr width="65%" align="left">
			<div class="starRating" data-num="${movie.movie_num}">
			<input type="hidden" id="user_id" value="${user_id}">
				<fieldset class="rating">
					<input type="radio" id="star5" name="rating" value="5">
					<label class="full" for="star5"></label>
					<input type="radio" id="star4half" name="rating" value="4.5">
					<label class="half" for="star4half"></label>
					<input type="radio" id="star4" name="rating" value="4">
					<label class="full" for="star4"></label>
					<input type="radio" id="star3half" name="rating" value="3.5">
					<label class="half" for="star3half"></label>
					<input type="radio" id="star3" name="rating" value="3">
					<label class="full" for="star3"></label>
					<input type="radio" id="star2half" name="rating" value="2.5">
					<label class="half" for="star2half"></label>
					<input type="radio" id="star2" name="rating" value="2">
					<label class="full" for="star2"></label>
					<input type="radio" id="star1half" name="rating" value="1.5">
					<label class="half"for="star1half"></label>
					<input type="radio" id="star1" name="rating" value="1">
					<label class="full" for="star1"></label>
					<input type="radio" id="half" name="rating" value="0.5">
					<label class="half"for="half"></label>
				</fieldset>
			</div>

		</div>
	</div>
		<!-- 영화 기본정보 pc화면 끝 -->
		
		<!-- 영화 기본정보 모바일 화면 -->
		<div class="col-sm-12 col-md-12 col-xs-12 hidden-md hidden-lg hidden-sm" style="text-align:center;"> 
		<h3>${movie.title}</h3>
		 	<p style="font-size:18px;"><fmt:formatDate value="${movie.released}" pattern="yyyy"/>&nbsp;&middot;&nbsp;${movie.main_genre}&nbsp;&middot;&nbsp;${movie.country}</p>
		 		<hr width="98%" align="left">	
					<p>평점★  ${movie.rate }</p>
				<hr width="98%" align="left">
		
			<div class="starRating" data-num="${movie.movie_num}" style="width:183px; display:inline-block">
			<input type="hidden" id="user_id" value="${user_id}">
				<fieldset class="rating">
					<input type="radio" id="star5" name="rating" value="5">
					<label class="full" for="star5"></label>
					<input type="radio" id="star4half" name="rating" value="4.5">
					<label class="half" for="star4half"></label>
					<input type="radio" id="star4" name="rating" value="4">
					<label class="full" for="star4"></label>
					<input type="radio" id="star3half" name="rating" value="3.5">
					<label class="half" for="star3half"></label>
					<input type="radio" id="star3" name="rating" value="3">
					<label class="full" for="star3"></label>
					<input type="radio" id="star2half" name="rating" value="2.5">
					<label class="half" for="star2half"></label>
					<input type="radio" id="star2" name="rating" value="2">
					<label class="full" for="star2"></label>
					<input type="radio" id="star1half" name="rating" value="1.5">
					<label class="half"for="star1half"></label>
					<input type="radio" id="star1" name="rating" value="1">
					<label class="full" for="star1"></label>
					<input type="radio" id="half" name="rating" value="0.5">
					<label class="half"for="half"></label>
				</fieldset>
			</div>
			

		<hr width="98%" align="left">
		
		<c:if test="${empty user_id}">
		<div class="visible-xs"><b>코멘트를 남기시려면 로그인 하세요</b><br> 
			<button type="button" style="width: 200px;" 
			 class="btn btn-info" data-toggle="modal" data-target="#myModal" disabled>코멘트 남기기</button>
		</div>
		</c:if>
		
		<c:if test="${!empty user_id && empty comment}">
		<div class="visible-xs"><b>대단한 작품이군요 회원님의 코멘트를 남겨주세요</b>
			<button type="button" style="width: 200px;" 
			 class="btn btn-info" data-toggle="modal" data-target="#myModal">코멘트 남기기</button>
		</div>
		</c:if>
		
		<c:if test="${!empty user_id && !empty comment}">
		<div class="visible-xs">
			<div class="commentContent">${comment.content}</div>
			<div class="commentBtn0">
					<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal2">수정</button>
					<button type="button" class="btn btn-danger btn-xs">삭제</button>
			</div>
		</div>
		</c:if>
	</div>
	<!-- 영화 기본정보 모바일 화면 끝-->
	</div>
</div>
	<!-- 상단 부분 끝 -->
	
	<!-- 상세정보 시작 -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12 col-xs-12 col-lg-12" id="middle">
				<!-- 코멘트 -->
				<c:if test="${empty user_id}">
				<div class="comment hidden-xs"><b>코멘트를 남기시려면 로그인을 하세요</b>
					<button 
						class="btn btn-info" data-toggle="modal" data-target="#myModal" disabled>코멘트 남기기
					</button>
				</div>
				</c:if>
				<c:if test="${!empty user_id && empty comment}">
				<div class="comment hidden-xs"><b>대단한 작품이군요 회원님의 코멘트를 남겨주세요</b>
					<button 
						class="btn btn-info" data-toggle="modal" data-target="#myModal">코멘트 남기기
					</button>
				</div>
				</c:if>
				<c:if test="${!empty user_id && !empty comment}">
					<div class="comment hidden-xs">
					<div class="commentContent">${comment.content}</div>
					<div class="commentBtn0">
					<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal2">수정</button>
					<button type="button" class="btn btn-danger btn-xs" onclick="location.href='${pageContext.request.contextPath}/movie/deleteComment.do?comment_num=${comment.comment_num}&movie_num=${movie.movie_num}'">삭제</button>
					</div>
					</div>
					
				</c:if>
				<!-- 코멘트 끝 -->
				
				<!-- pc상세정보 -->
				<div class="hidden-xs" id="box">
				<c:if test="${!empty user_id}">
				<div>
					<h4><b>내 예상 별점</b></h4>
				<br>
				</div>

				<div>
					<h5>본 친구</h5>
					
				</div>
				<hr>

				<div>
					<h5>선호하는 감독</h5>
				</div>
				<hr>

				<div>
					<h5>선호하는 배우</h5>
				</div>
				<hr>

				<div>
					<h5>재밌게 본 비슷한 작품</h5>
				</div>
				<hr>

				<div>
					<h5>선호하는 태그</h5>
				</div>
				<hr>
				</c:if>
				
				<div class="basic-information"><h4><b>기본정보</b></h4></div>
				<div class="more-view"><a style="cursor:pointer; color: #ffc0d3;" data-toggle="modal" data-target="#moreinfo"><b>더보기</b></a></div>	
				<br>
			
				<div class="summary">${movie.summary}</div>
				
				<hr>
				
				<div>
					<h4 style="margin-bottom:20px"><b>출연제작</b></h4>
				</div>
				
				
				<div id = slide_actors>
					<ul class="slide_actors">
				<c:forEach var="list" items="${actorList}">
						<div class="actor_box">
							<div class="actor_photo">
						<c:if test="${!empty list.off_photo}">
							<img src="../movie/actorView.do?off_num=${list.off_num}" 
								width="55" height="55" class="img-circle" style="float: left; margin-right: 10px;">
						</c:if>		
						<c:if test="${empty list.off_photo}">
							<img src="${pageContext.request.contextPath}/resources/images/default-profile.jpg" 
								width="55" height="55" class="img-circle" style="float: left; margin-right: 10px;">
						</c:if>	
							</div>
					<div class="name_box">
						<div class="actor_name">${list.name}</div>
							<c:if test="${list.jobs eq 'DIRECTOR'}">
								<div class="actor_jobs">감독</div>
							</c:if>
					<c:if test="${list.jobs eq 'ACTOR'}">
						<div class="actor_jobs">배우</div>
					</c:if>
						</div>
						</div>
					</c:forEach>
					</ul>
				</div>
			
				<hr>

				<div>
					<h4><b>별점 그래프</b></h4>
				</div>
				<hr>

				<div>
					<h4><b>본 친구</b></h4>
				</div>
				<hr>    

				<div>
					<h4><b>코멘트</b></h4>
				</div>
				
				
			<div id="comment-box">
				<div id = slidercomment>
					<ul class="slide_comment">
				<c:forEach var="list" items="${commentList}">
					<div class="division1">
					<div class=inner-box1>
					<div class="comment-title"><img src="../main/imageView.do?id=${list.id}" 
					width="35" height="35" class="img-circle" style="float: left; margin-right: 10px;"></div>
					<div><h4 class="comment-id">${list.id}</h4></div>
					<div class="more-view2"><a style="cursor:pointer; color: #ffc0d3;" data-toggle="modal" data-target="#morecomment" class="modal-comment">
					<b style="float: right; margin-top: -30px; margin-right: 20px; font-size: 15px;">더보기</b></a></div>	
					</div>
					<hr>
					<div class="inner-box2">${list.content}</div>
					<hr>
					<div class="inner-box3">좋아요 : ${list.likes}</div>  
					<hr>
					<div class="inner-box4">작성날짜 : ${list.reg_date}</div>                
					</div>
				</c:forEach>
					</ul>
					</div>
				</div>
			

				
				<hr>
				<div>
					<h4><b>비슷한 작품</b></h4>
				</div>
				
				<div class="row">
				<div class="othermovie">
					<div class="col-sm-3 col-md-3 col-xs-6">
					<a href="#"><img src="${pageContext.request.contextPath}/resources/images/img1.jpg" id="image3"></a>
					<div class="poster-name">어벤져어어어어어어어어스 이ㅇ이이인피니이이이티</div>
					</div>
					<div class="col-sm-3 col-md-3 col-xs-6">
					<a href="#"><img src="${pageContext.request.contextPath}/resources/images/img1.jpg" id="image3"></a>
					<div class="poster-name">어벤져스</div>
					</div>
					<div class="col-sm-3 col-md-3 col-xs-6">
					<a href="#"><img src="${pageContext.request.contextPath}/resources/images/img1.jpg" id="image3"></a>
					<div class="poster-name">어벤져스</div>
					</div>
					<div class="col-sm-3 col-md-3 col-xs-6">
					<a href="#"><img src="${pageContext.request.contextPath}/resources/images/img1.jpg" id="image3"></a>
					<div class="poster-name">어벤져스</div>
					</div>
					<div class="col-sm-3 col-md-3 col-xs-6">
					<a href="#"><img src="${pageContext.request.contextPath}/resources/images/img1.jpg" id="image3"></a>
					<div class="poster-name">어벤져스</div>
					</div>
					<div class="col-sm-3 col-md-3 col-xs-6">
					<a href="#"><img src="${pageContext.request.contextPath}/resources/images/img1.jpg" id="image3"></a>
					<div class="poster-name">어벤져스</div>
					</div>
					</div>
				</div>	
				<div class="more">더보기</div>
			</div>	
			
		</div>
	</div>
</div>
				<!-- pc상세정보 끝 -->
				
				<!-- 모바일 상세정보 시작-->
				<div class="container-fluid">
				<div class="row">
				<div class="col-sm-12 col-md-12 col-xs-12 hidden-md hidden-lg hidden-sm" id="box2">
				
				<c:if test="${!empty user_id}">
				<div>
					<h4><b>내 예상 별점</b></h4>
				<br>
				</div>

				<div>
					<h5>본 친구</h5>
				</div>
				<hr>

				<div>
					<h5>선호하는 감독</h5>
				</div>
				<hr>

				<div>
					<h5>선호하는 배우</h5>
				</div>
				<hr>

				<div>
					<h5>재밌게 본 비슷한 작품</h5>
				</div>
				<hr>

				<div>
					<h5>선호하는 태그</h5>
				</div>
				<hr>
				</c:if>

				<div>
					<div class="basic-information"><h4><b>기본정보</b></h4></div>
				</div>
				<div class="more-view"><a style="cursor:pointer; color: #ffc0d3;" data-toggle="modal" data-target="#moreinfo"><b>더보기</b></a></div>	
				
				<div class="mobile-summary">${movie.summary}</div>
				<hr>
				<div>
					<h4><b>출연제작</b></h4>
				</div>
				
				<div id = slide_actors>
					<ul class="slide_actors">
				<c:forEach var="list" items="${actorList}">
					<div class="actor_box">
					<div class="actor_photo">
						<c:if test="${!empty list.off_photo}">
							<img src="../movie/actorView.do?off_num=${list.off_num}" 
								width="55" height="55" class="img-circle" style="float: left; margin-right: 10px;">
						</c:if>		
						<c:if test="${empty list.off_photo}">
							<img src="${pageContext.request.contextPath}/resources/images/default-profile.jpg" 
								width="55" height="55" class="img-circle" style="float: left; margin-right: 10px;">
						</c:if>	
							</div>
					<div class="name_box">
						<div class="actor_name">${list.name}</div>
							<c:if test="${list.jobs eq 'DIRECTOR'}">
								<div class="actor_jobs">감독</div>
							</c:if>
					<c:if test="${list.jobs eq 'ACTOR'}">
						<div class="actor_jobs">배우</div>
					</c:if>
						</div>
						</div>
					</c:forEach>
					</ul>
				</div>
			<hr>

				<div>
					<h4><b>별점 그래프</b></h4>
				</div>
				<hr>

				<div>
					<h4><b>본 친구</b></h4>
				</div>
				<hr>

				<div>
					<h4><b>코멘트</b></h4>
				</div>
				<div class="row">
					<div id="comment-box">
				<div id = slidercomment>
					<ul class="slide_comment">
				<c:forEach var="list" items="${commentList}">
					<div class="mobile-division1">
					<div class=inner-box1>
					<div class="comment-title"><img src="../main/imageView.do?id=${list.id}" 
					width="35" height="35" class="img-circle" style="float: left; margin-right: 10px;"></div>
					<div><h4 class="comment-id">${list.id}</h4></div>
					<div class="more-view2"><a style="cursor:pointer; color: #ffc0d3;" data-toggle="modal" data-target="#morecomment" class="modal-comment">
					<b style="float: right; margin-top: -30px; margin-right: 20px; font-size: 15px;">더보기</b></a></div>	
					</div>
					<hr>
					<div class="inner-box2">${list.content}</div>
					<hr>
					<div class="inner-box3">좋아요 : ${list.likes}</div>  
					<hr>
					<div class="inner-box4">작성날짜 : ${list.reg_date}</div>                
					</div>
				</c:forEach>
					</ul>
					</div>
				</div>
			
				</div>
				
				<br>
				
				
				<h4><b>비슷한 작품</b></h4>
				<div class="row">
					<div class="col-xs-4">
					<a href="#"><img src="${pageContext.request.contextPath}/resources/images/img1.jpg" id="image4"></a>
					<div class="poster-name">어벤져어어어어어어어어스 이ㅇ이이인피니이이이티</div>
					</div>
					<div class="col-xs-4">
					<a href="#"><img src="${pageContext.request.contextPath}/resources/images/img1.jpg" id="image4"></a>
					<div class="poster-name">어벤져어어어어어어어어스 이ㅇ이이인피니이이이티</div>
					</div>
					<div class="col-xs-4">
					<a href="#"><img src="${pageContext.request.contextPath}/resources/images/img1.jpg" id="image4"></a>
					<div class="poster-name">어벤져어어어어어어어어스 이ㅇ이이인피니이이이티</div>
					</div>
				</div>
				<!-- 모바일 상세정보 끝-->
			</div>
		</div>
		</div>
<!-- 상세정보 끝 -->

<!-- modal -->
<!-- 코멘트 등록 모달 -->
<div class="modal fade" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content" id="modal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">x</button>
					<h4 style="text-align:center"><b>${movie.title}</b></h4>
				</div>
				<div class="modal-body">
					<form:form commandName="commentCommand" action="commentWrite.do" id="commentRegisterForm"> 
					<input type="hidden" name="movie_num" value="${movie.movie_num}">
					<input type="hidden" name="id" value="${user_id}">
					<textarea name="content" id="text" placeholder="이 작품에 대한 생각을 자유롭게 표현해 주세요" class="form-control" rows="5"></textarea><br>
					<div id = "re_first">
						<div class="letter-count" style="text-align:center;">1000/1000</div>
					</div>
					<div class="text-right">
						<button class="btn" data-dismiss="modal">닫기</button>
						<button class="btn btn-primary" value="submit">코멘트 작성</button>
					</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

<!-- 코멘트 수정 모달 -->	
<div class="modal fade" id="myModal2">
		<div class="modal-dialog">
			<div class="modal-content" id="modal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">x</button>
					<h4 style="text-align:center"><b>${movie.title}</b></h4>
				</div>
				<div class="modal-body">
					<form:form commandName="commentCommand" action="updateCommentWrite.do" id="commentRegisterForm"> 
					<input type="hidden" name="movie_num" value="${movie.movie_num}">
					<input type="hidden" name="id" value="${user_id}">
					<textarea name="content" id="text" class="form-control" rows="5">${comment.content}</textarea><br>
					<div id = "mre_first">
						<div class="letter-count" style="text-align:center">1000/1000</div>
					</div>
					<div class="text-right">
						<button class="btn" data-dismiss="modal">닫기</button>
						<button class="btn btn-primary" value="submit">코멘트 작성</button>
					</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	
<!-- 기본정보 더보기 모달 -->	
<div class="modal fade" id="moreinfo">
		<div class="modal-dialog modal-lg">
			<div class="modal-content" id="modal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">x</button>
					<h2 style="text-align:center"><b>기본정보</b></h2>
				</div>
				<div class="modal-body">
					<input type="hidden" name="movie_num" value="${movie.movie_num}">
					<div>
					<div class="more-t">제목</div>
					<div class="more-t2">${movie.title}</div>
					</div>
					<hr style="width:80%" align="left">
					<div>
					<div class="more-t">개봉일</div>
					<div class="more-t2">${movie.released}</div>
					</div>
					<hr style="width:80%" align="left">
					<div>
					<div class="more-t">국가</div>
					<div class="more-t2">${movie.country}</div>
					</div>
					<hr style="width:80%" align="left">
					<div>
					<div class="more-t">장르</div>
					<div class="more-t2">${movie.main_genre}/${movie.sub_genre }</div>
					</div>
				
					<hr style="width:80%" align="left">
					<div class="more-summary1">내용</div>
					<div class="more-summary2">${movie.summary}</div>
					
					<div class="text-right">
						<button class="btn" data-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>
	</div>	
	
<!-- 코멘트 더보기 모달 -->
<div class="modal fade" id="morecomment">
		<div class="modal-dialog">
			<div class="modal-content" id="modal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">x</button>
					<h2 style="text-align:center"><b>코멘트</b></h2>
				</div>
				<div class="modal-body"> 
					<input type="hidden" name="movie_num" value="${movie.movie_num}">
					<input type="hidden" name="id" value="${comment.id}">
					<div id="modal-commentbox">
				
					<div class="comment-division">
					<div class=inner-box1>
					<div class="comment-title" id="modal-output-title"></div>
					<div><h4 id="modal-output-id"></h4></div>
					</div>
					<hr>
					<div class="modal-contentbox" id="modal-output-content" style="margin-left:20px; font-size:15px; height:100%;">${comment.content}</div>
					<hr>
					<div class="inner-box3" id="modal-output-likes">좋아요 : ${comment.likes}</div>  
					<hr>
					<div class="inner-box4" id="modal-output-reg_date">작성날짜 : ${comment.reg_date}</div>                
					</div>
				
				</div>
				</div>
			</div>
		</div>
	</div>


<input type="hidden" value="${movie.movie_num}">

