<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추천친구폼</title>
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
  			<form class="navbar-form" role="search" id="search_form" action="follow.do" method="get" >
                <div class="input-group">
              	    <input type="hidden" name="keyfield" id="keyfield">
                    <input type="text" class="form-control" placeholder="Search" name="keyword" id="keyword">
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                    </div>
                </div>
            </form>
            <!-- 검색폼 끝 -->
            
				<div class="panel panel-default">
                <div class="panel-heading">추천친구 목록</div>
                
                <input type="hidden" id="user_id" name="user_id" value="${user.id}">
                
                <ul class="list-group">
                
						<c:if test="${count == 0}">
							<div class="align-center">검색하신 친구가 없습니다.</div>
						</c:if>
						
						<c:if test="${count > 0}">
						<c:forEach var="article" items="${list}">
                    <!-- 본인,관리자제외 -->
                    <c:if test="${article.auth==1 && article.id != user.id }">
                    
                    <!-- 내가 팔로잉한 친구 제외 -->
                    <c:if test="${follow.contains(article.id) == false}">
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
									
									<div class="pull-right">
										<div class="follow_unfollow" > 
											<input type="button" class="btn btn-primary follow" data-id="${article.id}" name="follow" value="팔로우" >
											<input type="button" class="btn btn-success active unfollow" data-id="${article.id}" name="unfollow" value="팔로잉" style="display: none;">
										</div> 
									</div>
									
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

