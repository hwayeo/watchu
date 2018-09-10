<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/setup.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<div class="container text-center">
		<h2>코멘트</h2><hr/>
	</div>
<div class="tab-content container">
	<form:form commandName="commentCommand"> 
		<input type="hidden" name="comment_num" value="${list.comment_num}">
		<input type="hidden" name="id" value="${user_id}">
		<input type="hidden" name="content" value="${list.content}">
		<input type="hidden" name="movie_num" value="${list.movie_num}">
	</form:form>
	<div id="home" class="tab-pane fade in active">
		<div class="nav-collapse">
				<ul class="nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><b class="caret"></b>&nbsp;작성 순</a>
						<ul class="dropdown-menu">
							<li class="dropdown-header">작성 순</li>
							<li><a href="#">나의 별점 높은 순</a></li>
							<li><a href="#">나의 별점 낮은 순</a></li>
							<li><a href="#">평균 별점 순</a></li>
							<li><a href="#">신작 순</a></li>
						</ul></li>
				</ul><hr>
		</div> 
	<div class="container">
		<c:forEach var="list" items="${commentList}">
		<div class="well"> 
			<div class="media">
				<div class="media-body">
					<div class="container">
						<a onclick="location.href='userMypage.do'" class="profile_img"> 
							<c:if test="${empty user.profile_img}">
								<img src="${pageContext.request.contextPath}/resources/images/default-profile.jpg" class="img-circle" id="profile_img"> ${user_id}
							</c:if> 
							<c:if test="${!empty user.profile_img}">
								<img src="${pageContext.request.contextPath}/main/imageView.do?id=${user.id}" class="img-circle review" style="width: 50px; height: 50px;"> ${user_id}
								<img src="${pageContext.request.contextPath}/main/imageView.do?id=${user.id}" class="img-circle"> ${user_id}
							</c:if>
						</a>	
					</div><br>
					
					<div class="container" onclick="location.href='userComment_detail.do'" style="cursor:pointer;">  
						<div class="col-md-2">
							<a href="#"> 
								<img class="media-object" src="${pageContext.request.contextPath}/resources/images/gad.jpg">
							</a>
						</div>
						<div class="col-md-9">
						
							<span class="media-heading">${list.title}</span>
							<p class="ptag"> &emsp; &emsp;${list.released}</p>
							<span class="commentspan">&emsp;&emsp;${list.content}</span> 
							<br><br><br><br><br> 
							<ul class="list-inline list-unstyled">
								&emsp;<li><span><i class="glyphicon glyphicon-calendar"></i> ${list.reg_date} </span></li>
								<li>|</li>
								<span><i class="glyphicon glyphicon-thumbs-up"></i> ${list.likes}likes</span>
								<li>|</li>
								<span><i class="glyphicon glyphicon-comment"></i> 2comments</span>
								<li>|</li>
								<span><i class="glyphicon glyphicon-star"></i> 4.5</span>
								<li>|</li>
							</ul>
					</div>
					</div>
				</div><br>
				<a href="#">좋아요</a>&emsp;
				<a href="userCommentWrite.do" data-toggle="modal" data-target="#CommentWrite">댓글</a>&emsp;
				
				<a href="#">공유</a>
				<a href="#" style="text-align:right"><span class="glyphicon glyphicon-option-horizontal"></span></a>
			</div>
		</div>
		</c:forEach>
	</div><br>
	
	<!-- 댓글 쓰기 모달창 -->
	<div class="modal fade" id="CommentWrite" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span> 
					</button>
					<h2 class="modal-title text-center" id="myModalLabel">댓글 쓰기</h2>
				</div>
				<div class="modal-body">
					<form class="pb-modalreglog-form-reg">
						<div class="">
							<textarea placeholder="코멘트에 댓글을 남겨보세요" style="outline: none; margin: 0px; width: 270px; height: 349px;"></textarea>
						</div>
						<button class="btn btn-md btn-primary active" type="submit">전송</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div></div>