<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
	<div class="container text-center">
		<h2>좋아요</h2><hr/>
	</div>

	<div class="container">
		<div class="well"> 
			<div class="media">
				<div class="media-body">
					<div class="container">
						
						<a href="#" class="profile_img"> 
							<c:if test="${empty user.profile_img}">
								<img src="${pageContext.request.contextPath}/resources/images/default-profile.jpg" class="img-circle" id="profile_img" style="width: 50px; height: 50px;"> ${user.name}
							</c:if> 
							<c:if test="${!empty user.profile_img}">
								<img src="${pageContext.request.contextPath}/main/imageView.do?id=${user.id}" width="50" height="50" class="img-circle"> ${user.name}
							</c:if>
						</a>
						
						<p align="right"> 
							<li>
								<span class="glyphicon glyphicon-star"></span> 
								<span class="glyphicon glyphicon-star"></span> 
								<span class="glyphicon glyphicon-star"></span> 
								<span class="glyphicon glyphicon-star"></span> 
								<span class="glyphicon glyphicon-star-empty"></span>
							</li>
						</p>
					</div>
					<div class="container">  
						<a class="pull-left" href="#"> 
							<img class="media-object" src="resources/images/gad.jpg" width="170" height="250">
						</a>
						<h2 class="media-heading">&emsp;가디언즈 오브 갤럭시</h2>
						<p> &emsp; &emsp;2014</p>
						<h4>&emsp;&emsp;We are groot!</h4> 
						<br><br><br><br><br><br>
						<ul class="list-inline list-unstyled">
							&emsp;<li><span><i class="glyphicon glyphicon-calendar"></i> 2days, 8 hours </span></li>
							<li>|</li>
							<span><i class="glyphicon glyphicon-thumbs-up"></i> 12likes</span>
							<li>|</li>
							<span><i class="glyphicon glyphicon-comment"></i> 2comments</span>
							<li>|</li>
						</ul>
					</div>
				</div><hr/>
				<a href="#">좋아요</a>&emsp;
				<a href="#">댓글</a>&emsp;
				<a href="#">공유</a>
				<a href="#" style="text-align:right"><span class="glyphicon glyphicon-option-horizontal"></span></a>
			</div>
		</div>
	</div><br>
