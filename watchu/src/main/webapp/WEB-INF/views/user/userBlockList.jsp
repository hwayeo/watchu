<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블락</title>
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
    <div class="row" style="margin-top:100px;">
   		 <div class="col-xs-12 col-md-12" ><!-- 시작 -->
   		 	
   		 	<div class="col-md-2">
     		</div>
     		
     		<div class="col-md-7">
     		<!-- 검색폼 시작 -->
  			<form class="navbar-form" role="search" id="search_form" action="" method="get" >
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search" name="keyword" id="keyword">
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                    </div>
                </div>
            </form>
            <!-- 검색폼 끝 -->
            
				<div class="panel panel-default">
                <div class="panel-heading">블락 목록</div>
                
                
                
                 <ul class="list-group">
                 	
                 	<c:if test="${count == 0}">
							<div class="align-center">없음</div>
					</c:if>
					
					<c:if test="${count > 0}">
                    <c:forEach var="article" items="${list}">
                    <!-- 본인,관리자제외 -->
                    <c:if test="${article.auth==1 && article.id != user.id }">
                    
                    <!-- 내가 블락한 친구만 -->
                    <c:if test="${blockList.contains(article.id) == true}">
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
										<input type="button" class="btn btn-secondary active block" data-id="${article.id}" name="block" value="BLOCK" style="display: none;"> 
										<input type="button" class="btn btn-secondary unblock" data-id="${article.id}" name="unblock" value="BLOCK 해제" >	
									</div>
								
						</li>
						</c:if>
						</c:if>
						
						</c:forEach>
                    	</c:if>
                </ul>
                <div class="text-center">${pagingHtml}</div>
            </div>
            
            </div>
            
            <div class="col-md-3">
     		</div>
            
            
        </div><!-- 끝 -->
    </div>
</div>
</body>
</html>