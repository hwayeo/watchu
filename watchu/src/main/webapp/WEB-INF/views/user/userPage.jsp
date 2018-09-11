<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/setup.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mypage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/my_follow_unfollow.js"></script>

<div id="main-content"><!-- 1 -->
<div class="container-fluid"><!-- 2 -->
	<div class="row" style="margin-top:80px;"><!-- 3 -->
		
		<div class="col-xs-12 col-md-12"> <!-- 프로필,팔로잉부분 시작-->
			
			<div class="col-md-3 text-center">
			</div>
			<div class="col-xs-6 col-md-2 text-center"> <!-- 왼쪽 -->
				
				<div style="margin:20px;"> <!-- 프로필사진영역 시작-->
					<a href="#" class="following_profile_img"> 
						<c:if test="${empty anotheruser.profile_img}">
								<img src="${pageContext.request.contextPath}/resources/images/default-profile.jpg"
												class="img-circle" id="following_profile_img"
												style="width: 70px; height: 70px;">
						</c:if> 
						<c:if test="${!empty anotheruser.profile_img}">
								<img src="${pageContext.request.contextPath}/main/imageView.do?id=${anotheruser.id}" class="img-circle" style="width: 70px; height: 70px;" >
						</c:if>
					</a> 
				</div><!-- 프로필사진영역 끝-->
				
				<div style="margin:20px"> <!-- 이름,톱니바퀴 시작 -->
				<p style="font-size:22px"> ${anotheruser.name}<span><a href="setup.do" class="glyphicon glyphicon-cog" data-toggle="modal" data-target="#myModal2"></a></span></p>
				</div><!-- 이름,톱니바퀴 끝 -->
				
			</div><!-- 왼쪽 -->
			
			<div class="col-xs-6 col-md-4 text-center" style="margin-top:40px;"> <!-- 오른쪽 -->
			
			
				<div class="col-md-2">
				</div>
				
				<div>	
					<div class="col-xs-6 col-md-4">
						<div>
							<a href="myfollower.do?id=${anotheruser.id}">${list2.size()}</a>
						</div>
						<span>Followers</span>
					</div>
					<div class="col-xs-6 col-md-4">
						<div>
							<a href="myfollowing.do?id=${anotheruser.id}">${list.size()}</a>
						</div>
						<span>Following</span>
					</div>
				</div>	
				
				<div class="col-md-2">
				</div>
			
			<input type="hidden" id="user_id" name="user_id" value="${user.id}">
					<!-- 내친구면 팔로잉버튼위로,아니면 팔로우버튼이 위로 -->
					<c:if test="${mylist.contains(anotheruser.id) == true}">
						
							<div class="follow_unfollow">
								<input type="button" class="btn btn-primary active follow" data-id="${anotheruser.id}" name="follow" value="팔로우" style="display: none; width:250px;">
								<input type="button" class="btn btn-success unfollow" data-id="${anotheruser.id}" name="unfollow" value="팔로잉" style="width:250px;">
							</div>
					</c:if>
					<c:if test="${mylist.contains(anotheruser.id) == false}">
							<div class="follow_unfollow">
								<input type="button" class="btn btn-primary follow" data-id="${anotheruser.id}" name="follow" value="팔로우" style="width:250px;"> 
								<input type="button" class="btn btn-success active unfollow" data-id="${anotheruser.id}" name="unfollow" value="팔로잉" style="display: none; width:250px;">
							</div>
					</c:if> 
			
		</div><!-- 오른쪽 -->
		
		<div class="col-md-3">
		</div>
	
		</div><!-- 프로필,팔로잉부분 끝-->
		
		
		
		<!-- 겟방식으로 id 넘기는걸로 고쳐야댐 시작-->
		<div class="col-xs-12 col-md-12"> 
		
		<div class="col-md-2">
		</div>
		
		<div class="col-xs-3 col-md-2 text-center" style="border:1px solid #e5e3e3;">
			<div style="margin-top:20px;">	
				<div>
					<img src="${pageContext.request.contextPath}/resources/images/movie.png" id="movie_img" style="width: 50px; height: 50px;">
				</div>	
				<span>영화</span>
				<div>
					<a onclick="location.href='userMypage_movie.do?id=${anotheruser.id}'">0</a>
				</div>
			</div>
		</div>
		
		<div class="col-xs-3 col-md-2 text-center" style="border:1px solid #e5e3e3;">
			<div style="margin-top:20px;">	
				<div>
					<img src="${pageContext.request.contextPath}/resources/images/like2.png" id="like2_img" style="width: 50px; height: 50px;">
				</div>
				<span>보고싶어요</span>
				<div>
					<a onclick="location.href='userWish.do?id=${anotheruser.id}'">0</a>
				</div>
			</div>
		</div>
		
		<div class="col-xs-3 col-md-2 text-center" style="border:1px solid #e5e3e3;">
			<div style="margin-top:20px;">
				<div>
					<img src="${pageContext.request.contextPath}/resources/images/comment.png" id="comment_img" style="width: 50px; height: 50px;">
				</div>			
				<span>코멘트</span>
				<div>
					<a onclick="location.href='userComment.do?id=${anotheruser.id}'">${comment_count}</a>
				</div>
			</div>
		</div>
		
		<div class="col-xs-3 col-md-2 text-center" style="border:1px solid #e5e3e3;">
			<div style="margin-top:20px;">	
				<div>
					<img src="${pageContext.request.contextPath}/resources/images/like.png" id="like_img" style="width: 50px; height: 50px;">
				</div>												
				<span>좋아요</span>
				<div>
					<a onclick="location.href='userLikeComment.do?id=${anotheruser.id}'">0</a>
				</div>
			</div>	
		</div>
		
		<div class="col-md-2">
		</div>
		
		</div>
		<!-- 겟방식으로 id 넘기는걸로 고쳐야댐 끝-->
		
		
		<div class="col-xs-12 col-md-12 text-center"> 
			<div class="col-xs-7 col-md-6"><br>
				<p style="font-size:22px">최고의 작품</p><br>
			</div>
			<div class="col-xs-1 col-md-2"></div>
			
		</div>
			
		<div class="col-xs-12 col-md-12 text-center"> 
			
			<div class="col-md-3"></div>
			<div class="col-xs-4 col-md-2">
				<img class="image" src="${pageContext.request.contextPath}/resources/images/gad.jpg" ><br>
				<p>가디언즈 오브 갤럭시</p>
			</div>
			<div class="col-xs-4 col-md-2">
				<img class="image" src="${pageContext.request.contextPath}/resources/images/gad2.jpg" ><br>
				<p>수어사이드 스쿼드</p>
			</div>
			<div class="col-xs-4 col-md-2">
				<img class="image" src="${pageContext.request.contextPath}/resources/images/gad3.jpg" ><br>
				<p>앤트맨</p>
			</div>
			<div class="col-md-3"></div>
		</div>
		
	</div><!-- 3 -->
</div> <!-- 2 -->


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

						<li class="list-group-item"></li>

						<li class="list-group-item">
							<!-- 내가 블락한 친구면 블락해제버튼이 위로-->
							<c:if test="${blockList.contains(anotheruser.id) == false}">								
							<div class="block_unblock">
								<input type="button" class="btn btn-secondary block" data-id="${anotheruser.id}" name="block" value="BLOCK" > 
								<input type="button" class="btn btn-secondary active unblock" data-id="${anotheruser.id}" name="unblock" value="BLOCK 해제" style="display: none;">
							</div>
							</c:if>
							<c:if test="${blockList.contains(anotheruser.id) == true}">								
							<div class="block_unblock">
								<input type="button" class="btn btn-secondary active block" data-id="${anotheruser.id}" name="block" value="BLOCK" style="display: none;"> 
								<input type="button" class="btn btn-secondary unblock" data-id="${anotheruser.id}" name="unblock" value="BLOCK 해제" >
							</div>
							</c:if>
						</li>
						
						<li class="list-group-item">
							<div class="report">
								<button class="btn btn-md btn-primary active" type="button" onclick="location.href='insertReport.do?id=${anotheruser.id}'">신고하기</button>							
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
	
</div>   <!-- 1 -->