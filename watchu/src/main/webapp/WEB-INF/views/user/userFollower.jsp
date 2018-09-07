<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팔로워</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/follow.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin-main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/my_follow_unfollow.js"></script>


</head>
<body>


<div class="container">
    <div class="row" style="margin: 80px;">
     <div class="col-md-8 col-md-offset-4" style="margin: 80px;" >
     		<!-- 검색폼 시작 -->
  			<form class="navbar-form" role="search" id="search_form" action="" method="get" >
                <div class="input-group">
                	<input type="hidden" id="id" name="id" value="${user.id}">
                    <input type="text" class="form-control" placeholder="Search" name="keyword" id="keyword">
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                    </div>
                </div>
            </form>
            <!-- 검색폼 끝 -->
            
				<div class="panel panel-default">
                <div class="panel-heading">팔로워 목록</div>
                
                
                <input type="hidden" id="loginUser_id" name="loginUser_id" value="${loginUser.id}">
                
                 <ul class="list-group">
                 	
                 	<c:if test="${count == 0}">
							<div class="align-center">없음</div>
					</c:if>
					
					<c:if test="${count > 0}">
                    <c:forEach var="article" items="${list}">
                    <!-- 본인,관리자제외 -->
                    <c:if test="${article.auth==1 && article.id != user.id }">
                    
                    <!-- 내 팔로워만 -->
                    <c:if test="${follower.contains(article.id) == true}">
						<li class="list-group-item">
						<a href="userPage.do?id=${article.id}" class="following_profile_img"> 
							<c:if test="${empty article.profile_img}">
								<img src="${pageContext.request.contextPath}/resources/images/default-profile.jpg"
											class="img-circle" id="following_profile_img"
											style="width: 50px; height: 50px;">
							</c:if> 
							<c:if test="${!empty article.profile_img}">
								<img src="${pageContext.request.contextPath}/main/imageView.do?id=${article.id}" width="50" height="50" class="img-circle">
							</c:if>
						</a> 
						<span class="name_span"><label class="name"><a href="userPage.do?id=${article.id}">${article.name}</a></label></span>
								<!-- get으로 넘겨받은 아이디랑 로그인한 아이디가 불일치하면 버튼 숨김 -->
								<c:if test="${loginUser.id == user.id }">
									
									<!-- 내친구면 팔로잉버튼위로,아니면 팔로우버튼이 위로 -->
									<c:if test="${follow.contains(article.id) == true}">
									<div class="pull-right">
										<div class="follow_unfollow" > 
											<input type="button" class="btn btn-primary active follow" data-id="${article.id}" name="follow" value="팔로우" style="display: none;">
											<input type="button" class="btn btn-success unfollow" data-id="${article.id}" name="unfollow" value="팔로잉">
										</div> 
									</div>
									
									<c:if test="${follow.contains(article.id) == false}">
									<div class="pull-right">
										<div class="follow_unfollow" > 
											<input type="button" class="btn btn-primary follow" data-id="${article.id}" name="follow" value="팔로우" >
											<input type="button" class="btn btn-success active unfollow" data-id="${article.id}" name="unfollow" value="팔로잉" style="display: none;">
										</div> 
									</div>
									</c:if>
									
									</c:if>
									
								</c:if>
						</li>
						</c:if>
						</c:if>
						
						</c:forEach>
                    	</c:if>
                </ul>
                <div class="align-center">${pagingHtml}</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

