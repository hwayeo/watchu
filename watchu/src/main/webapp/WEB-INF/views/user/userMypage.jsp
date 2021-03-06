<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/setup.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mypagemovie.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mypage.js"></script>
<div id="main-content">
<div class="container-fluid"> 
		<div class="col-xs-12 col-md-12"><br><br>
			<div class="col-xs-12 col-md-12"> 
				<div class="col-xs-8 col-md-8">
				</div>
				<div class="col-xs-4 col-md-2">
					<a href="follow.do?id=${user_id}" class="glyphicon glyphicon-user" style="font-size:20px; color:gray;"></a> 
					<a href="setup.do?id=${user_id}" class="glyphicon glyphicon-cog" style="font-size:20px; color:gray;" data-toggle="modal" data-target="#myModal2"></a>
				</div> 
			</div>
			
			<div class="col-xs-7 col-md-6 text-center">
				<a href="#" class="following_profile_img"> 
					<c:if test="${empty user.profile_img}">
							<img src="${pageContext.request.contextPath}/resources/images/default-profile.jpg" class="img-circle2" id="following_profile_img" style="width: 60px; height: 60px;">
					</c:if> 
					<c:if test="${!empty user.profile_img}">
							<img src="${pageContext.request.contextPath}/main/imageView.do?id=${user_id}" class="img-circle2" style="width: 60px; height: 60px;">
					</c:if>
				</a> 
				<p style="font-size:22px"> ${user.name}</p>
				<div>
					<ul class="wcPc-Arrange">
						<li class="wcPc-ArrangeSizeFit" style="list-style:none;">
								<a class="mylike" href="myfollowing.do?id=${user_id}">Following&emsp;</a>
								<a class="num" href="myfollowing.do?id=${user_id}">${list.size()}</a>
						</li>
						<li class="wcPc-ArrangeSizeFit" style="list-style:none;">
								<a class="mylike" href="myfollower.do?id=${user_id}">Followers&emsp;</a>
								<a class="num" href="myfollower.do?id=${user_id}">${list2.size()}</a>
						</li>
						<li class="wcPc-ArrangeSizeFit" style="list-style:none;">
								<a class="mylike" href="myBlock.do?id=${user_id}">Block&emsp;</a>
								<a class="num" href="myBlock.do">${blockList.size()}</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
		
		<div class="col-xs-12 col-md-12"> 
		<div class="container" style="border:1px solid #c1c1c1"><br>
				<div class="col-xs-4 col-md-4 text-center">
					<span class="wc-StatLabel2 wcPc-block"><a class="mylike" onclick="location.href='userMypage_movielist.do?id=${user_id}'">영화</a></span> ${mypage_movielist_count}<br>
				</div>
				<div class="col-xs-4 col-md-4 text-center">
					<span class="wc-StatLabel wcPc-block"><a class="mylike" onclick="location.href='userComment.do?id=${user_id}'">코멘트</a></span> ${comment_count}<br>
				</div>
				<div class="col-xs-4 col-md-4">
					<span class="wc-StatLabel wcPc-block"><a class="mylike" onclick="location.href='userLikeComment.do?id=${user_id}'">좋아요</a></span> ${likecomment_count}<br><br>
				</div>
		</div>
		</div>
		<br><br><hr>
	
		<div class="col-xs-12 col-md-12 text-center"> 
			<div class="col-xs-7 col-md-6"><br>
				<p class="best">최고의 작품</p><br>
			</div>
			<div class="col-xs-1 col-md-2"></div>
			<div class="col-xs-2 col-md-2"><br><br>
				<button class="btn btn-md btn-primary active" type="button" style="background-color:#f74788;border-color:#f74788;" onclick="location.href='analysis.do'">취향 분석</button> 
			</div>
		</div>
		
		<div class="text-center"> 
			<div class="row container">
				<div class="col-md-12 bestmovie">
					 <c:forEach var="recommendList" items="${recommendList}"> 	
						<c:if test="${recommendList.rate == 5.0}">
								<div class="col-xs-6 col-md-2">
									<c:if test="${!empty recommendList.poster_img}">
										<a href="${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${recommendList.movie_num}&type=poster"><img class="movie_poster2" src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${recommendList.movie_num}&type=poster" width="100%" height="100%"></a>
									</c:if>	
									<c:if test="${empty recommendList.poster_img}">
										<a href="${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${recommendList.movie_num}&type=poster"><img class="movie_poster2" src="${pageContext.request.contextPath}/resources/images/default-poster.jpg" width="100%" height="100%"></a>
									</c:if>
									<p class="movie_title2">${recommendList.title}</p>
								</div>
						</c:if>
					</c:forEach> 
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 톱니바퀴 누르면 나오는 모달 -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">설정</h4>
				</div>
				<div class="modal-body">
					<form class="pb-modalreglog-form-reg">
					<!--     여기부터 넣어라 -->
					<ul class="list-group">

						<li class="list-group-item">
							<div class="">
								<a href="updateUser.do"><label>프로필 수정</label></a>
							</div>
						</li>

						<li class="list-group-item">
							<div class="">
								<a onclick="location.href='userSupportList.do'"><label>고객센터</label></a>
							</div>

						</li>

						<li class="list-group-item"></li>

						<li class="list-group-item">
							<div class="">
								<a href="logout.do"><label>로그아웃</label></a>
							</div>

						</li>

						<li class="list-group-item">
							<div class="">
								<a href="deleteUser.do"><label>회원탈퇴</label></a>
							</div>

						</li>
					</ul>

					<!--     여기까지다 -->
					</form>
				</div>
				<div class="modal-footer text-center">Watchu♥</div>
			</div>
		</div>
	</div>
</div>   