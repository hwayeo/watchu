<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/setup.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="container text-center">
		<h2>코멘트</h2>
		<hr />
	</div>


	<div class="container classpadding">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<div class="col-md-1"></div>
					<div class="row">
						<div class="container">
							<div class="col-xs-6 col-md-5">
								<div class="container">
									<a onclick="location.href='userMypage.do'" class="profile_img"> 
										<c:if test="${empty user.profile_img}">
											<img src="${pageContext.request.contextPath}/resources/images/default-profile.jpg" class="img-circle" id="profile_img" style="width: 50px; height: 50px;"> ${user.name}
										</c:if> 
										<c:if test="${!empty user.profile_img}">
											<img src="${pageContext.request.contextPath}/main/imageView.do?id=${user.id}" width="50" height="50" class="img-circle"> ${user.name}
										</c:if>
									</a><br>
									<span style="color: gray; font-size: 7px">${user.reg_date}</span><br>
									<span style="font-weight: bold; font-size: 11px">가디언즈 오브 갤럭시</span> 
									<span style="color: gray; font-size: 6px"> 2018</span><br> 
									<span class="glyphicon glyphicon-star"></span> 
									<span class="glyphicon glyphicon-star"></span> 
									<span class="glyphicon glyphicon-star"></span> 
									<span class="glyphicon glyphicon-star"></span> 
									<span class="glyphicon glyphicon-star-empty"></span>
								</div>
							</div>
	
							<!-- 영화 사진 - 누르면 영화 상세 정보로 이동 -->
							<div class="col-xs-6 col-md-5 text-right">
								<a href="#"><img src="resources/images/gad.jpg" width="110" height="150"></a>
							</div>
						</div>
					</div>
	
					<div class="container">
						<div class="col-xs-12 col-md-12 text-left">
							<div class="col-md-1"></div>
								<div class="container col-md-10">
									<span class="reviewm">리뷰가 쓰여지는 공간</span><hr>
								</div>
							<div class="col-md-2"></div>
						</div>
					</div>
	
					<div class="container text-center">
						<a class="like" href="#"><span class="glyphicon glyphicon-thumbs-up "></span> 좋아요</a>
						<span style="color: #d3d3d3"> | </span>
						<a class="like" href="#"><span class="glyphicon glyphicon-comment "></span> 댓글</a>
						<span style="color: #d3d3d3"> | </span>
						<a class="like" href="#"><span class="glyphicon glyphicon-share-alt "></span> 공유</a>
					</div><hr/>
					
					
					<div class="container">
		                <div class="col-xs-12 col-md-12">
		                	<div class="col-md-1"></div>
			                  <div class="page-header col-md-10"> 
			                    <span class="commmm">45 comments</span> 
			                   <div class="comments-list">
			                       <div class="media container">
			                           <p class="days">4 days ago</p>
			                            <a class="media-left" href="#">
			                              <img src="resources/images/img3.png" width="50" height="50">
			                            </a>
			                            <div class="media-body">
			                              <span class="media-heading user_name">홍길자</span>
			                              <span class="detailcomm">잘보고가요~!</span> 
			                              <p><a class="like" href="#">좋아요 &emsp;<span class="glyphicon glyphicon-thumbs-up"> 11</span></a></small></p>
			                            </div>
			                       </div>
		                  		 </div>
		                  		</div>
		                  	<div class="col-md-1"></div>
	               		 </div>
	       			 </div>
	       		<div class="col-md-1"></div>
			</div>
		</div>
	</div>