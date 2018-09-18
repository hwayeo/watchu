<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/timeline.css">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="main-content">    
	<div class="container">
	  <div class="row">
	    <div class="col-xs-12 col-md-12">
	      <h2 class="page-header text-center">소식</h2>
	      
	      <c:if test="${empty recommendList}">
			<div class="text-center">
				<img src="../resources/images/ap.jpg"><br>
				<span style="font-size: 23px;color: #f74788;">등록된 평가가 없어요!</span><br>
				<span>평가하러 가기</span>
				<a onclick="location.href='/watchu/movie/movieEva.do'" style="color:#f74788;" class="glyphicon glyphicon-hand-right"></a>
	    	</div>
		  </c:if> 
		
		<c:if test="${!empty recommendList}">	
			<c:forEach var="recommendList" items="${recommendList}">
	          <!-- First Comment -->
	          <article class="row"><br>
	            <div class="col-xs-2 col-md-2">
	              <figure class="thumbnail">
					<a onclick="location.href='userMypage.do'" class="profile_img"> 
						<c:if test="${empty user.profile_img}">
							<img src="${pageContext.request.contextPath}/resources/images/default-profile.jpg" class="img-circle" id="profile_img">
						</c:if> 
						<c:if test="${!empty user.profile_img}">
							<img src="${pageContext.request.contextPath}/main/imageView.do?id=${user_id}" class="img-circle review">
						</c:if>
					</a>	
	              </figure>
	            </div>
	            <div class="col-xs-10 col-md-10">
	              <div class="panel panel-default arrow left"> 
	                <div class="panel-body">
	                  <header class="text-left">
	                    <div class="comment-user"><i class="fa fa-user"></i> 
	                    	<span class="userid">${user_id}</span><span class="userrate">님이 '${recommendList.title}'에 ${recommendList.rate}점을 줬어요!</span></div>  
	                    <span class="date">${recommendList.reg_date}</span><br><br>
	                  </header>
	                  <div class="comment-post">
	                  	<div class="container-fluid">
							<div class="row">
								<div class="col-xs-12 col-md-12">
									<div class="row">
										<div class="col-xs-4 col-md-2">
											<c:if test="${!empty recommendList.poster_img}">
												<a href="${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${recommendList.movie_num}&type=poster"><img class="movie_poster" src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${recommendList.movie_num}&type=poster" width="100%" height="100%"></a>
											</c:if>	
											<c:if test="${empty recommendList.poster_img}"> 
												<a href="${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${recommendList.movie_num}&type=poster"><img class="movie_poster" src="${pageContext.request.contextPath}/resources/images/default-poster.jpg" width="100%" height="100%"></a>
											</c:if>
										</div>
										<div class="col-xs-8 col-md-10">
											<br>
											<span class="star"><i class="glyphicon glyphicon-star"></i> ${recommendList.rate}</span>
											<br>
											<span class="title">${recommendList.title}</span><br>
											<!-- <span style="color: #ff00c7; font-size: 14px">예상 ★3.8</span> 평가 안한 영화일 때 -->
										</div>
									</div>
								</div>
							</div>
						</div>
	                  </div>
	                </div>
	              </div>
	            </div>
	          </article>
	          </c:forEach>
	        </c:if>
	    </div>
	  </div>
	</div>
	<div class="text-center">${pagingHtml}</div>
</div>