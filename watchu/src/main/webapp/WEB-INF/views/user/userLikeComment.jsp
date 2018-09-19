<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/setup.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="main-content">
	<div class="container text-center">
		<h2>좋아요</h2><hr/>
	</div>
	
	<c:if test="${empty commentList}">
		<div class="text-center">
			<img src="../resources/images/ap.jpg"><br>
			<span style="font-size: 23px;color: #f74788;">좋아요한 코멘트가 없어요!</span><br>
			<span>구경하고 좋아요 누르러 가기</span>
			<a onclick="location.href='/watchu/movie/movieEva.do'" style="color:#f74788;" class="glyphicon glyphicon-hand-right"></a>
    	</div>
	</c:if>
	
	<c:if test="${!empty commentList}">	
		<div>
			<div class="container"> 
				<c:forEach var="list" items="${commentList}">
				<div class="well"> 
					<div class="media">
						<div class="media-body">
							<div class="container">
								<a onclick="location.href='userPage.do?id=${list.comment_id}'" class="profile_img"> 
									<c:if test="${empty list.profile_img}">
										<img src="${pageContext.request.contextPath}/resources/images/default-profile.jpg" class="img-circle" id="profile_img">&emsp;${list.comment_id}
									</c:if> 
									<c:if test="${!empty list.profile_img}">
										<img src="${pageContext.request.contextPath}/main/imageView.do?id=${list.comment_id}" class="img-circle review" style="width: 50px; height: 50px;">&emsp;${list.comment_id}
									</c:if>
								</a>	
							</div><br>
							
							<%-- <div class="container" onclick="location.href='userComment_detail.do?movie_num=${list.movie_num}&id=${list.id}'" style="cursor:pointer;"> --%>  
							<div class="container">  
								<div class="col-md-2">
									<c:if test="${!empty list.poster_img}">
										<a href="${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${list.movie_num}&type=poster"><img class="movie_poster" src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${list.movie_num}&type=poster" width="100%" height="100%"></a>
									</c:if>	
									<c:if test="${empty list.poster_img}"> 
										<a href="${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${list.movie_num}&type=poster"><img class="movie_poster" src="${pageContext.request.contextPath}/resources/images/default-poster.jpg" width="100%" height="100%"></a>
									</c:if>
								</div>
								<div class="col-md-9">
								
									<span class="media-heading">${list.title}</span>
									<p class="ptag"> &emsp; &emsp; ${list.released} 개봉</p>
									<span class="commentspan">&emsp;&emsp;${list.content}</span> 
									<br><br><br><br><br> 
									<ul class="list-inline list-unstyled">
										&emsp;<li><span><i class="glyphicon glyphicon-calendar"></i> ${list.reg_date} </span></li>
										<li>|</li>
										<span><i class="glyphicon glyphicon-thumbs-up"></i> ${list.likes}</span>
										<li>|</li>
										<%-- <span><i class="glyphicon glyphicon-star"></i> ${list.rate}</span>
										<li>|</li> --%>
									</ul>
							</div>
							</div>
						</div><br>
						<a class="like2" href="#" style="padding-left:30px">좋아요</a> 
						<!-- <a class="like2" href="userCommentWrite.do" data-toggle="modal" data-target="#CommentWrite">댓글</a>&emsp;
						<a class="like2" href="#">공유</a> -->
					</div>
				</div>
				</c:forEach>
			</div><br>
		</div>
	</c:if>
</div>