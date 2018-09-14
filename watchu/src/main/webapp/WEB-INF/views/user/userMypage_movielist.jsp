<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mypagemovie.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<div class="container-fluid">
		<div class="container text-center">
			<div class="col-xs-12 col-md-12">
				<h2>평가한 작품</h2><br>
			</div>
		</div>

		<div class="container">
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#home" class="fa fa-clock-o">전체</a></li>
				<li><a data-toggle="tab" href="#menu1" class="fa fa-gavel">별점순</a></li>
			</ul>
		
			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<div class="nav-collapse">
						<ul class="nav">
							<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><b class="caret"></b>&nbsp;담은순</a>
								<ul class="dropdown-menu">
									<li class="dropdown-header">담은순</li>
									<li><a href="#">평점순</a></li>
									<li><a href="#">가나다순</a></li>
									<li><a href="#">신작순</a></li>
								</ul></li>
						</ul>
						<hr>
					</div>
					
					
					<c:if test="${empty recommendList}">
						<div class="text-center">
							<img src="../resources/images/ap.jpg"><br>
							<span style="font-size: 23px;color: #f74788;">평가된 영화가 없어요!</span><br>
							<span>평가하러가기</span>
							<a onclick="location.href='/watchu/movie/movieEva.do'" style="color:#f74788;" class="glyphicon glyphicon-hand-right"></a>
				    	</div>
					</c:if>
					<c:if test="${!empty recommendList}">
						<div class="row container">
							<div class="col-xs-12 col-md-12">
								<c:forEach var="recommendList" items="${recommendList}">	
									 <div class="col-xs-6 col-md-3">
									 	<c:if test="${!empty recommendList.poster_img}">
											<img class="movie_poster" src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${recommendList.movie_num}&type=poster" width="100%" height="100%">
										</c:if>	
										<c:if test="${empty recommendList.poster_img}">
											<img class="movie_poster" src="${pageContext.request.contextPath}/resources/images/default-poster.jpg" width="100%" height="100%">
										</c:if>
											<br><p class="movie_title">${recommendList.title}</p>
											<p class="movie_rate">★${recommendList.rate}</p>
									</div>
								</c:forEach>
							</div> 
						</div>
					</c:if>
				</div>
				
				<div id="menu1" class="tab-pane fade">
					<c:if test="${empty movieList}">
						<div class="text-center">
							<img src="../resources/images/ap.jpg"><br>
							<span style="font-size: 23px;color: #f74788;">평가한 영화가 없어요!</span><br>
							<span>평가하러가기</span>
							<a onclick="location.href='/watchu/movie/movieEva.do'" style="color:#f74788;" class="glyphicon glyphicon-hand-right"></a>
				    	</div>
					</c:if>
					
					<c:if test="${!empty movieList}">
						<div class="container">
							<div class="col-xs-12 col-md-12">
								<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span style="font-size: 30px">5.0 평가함</span>&emsp;32<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
							</div>
							
							<div class="row container">
								<div class="col-xs-12 col-md-12">
									<div class="col-xs-4 col-md-3">
										<img src="${pageContext.request.contextPath}/resources/images/gad.jpg" width="100%" height="100%">
										<br><span>가디언즈 오브 갤럭시</span>
										<br><span style="color: #ffbf00">★4.5</span><br><br>
									</div>
									<div class="col-xs-4 col-md-3">
										<img src="${pageContext.request.contextPath}/resources/images/gad2.jpg" width="100%" height="100%">
										<br><span>수어사이드 스쿼드</span>
										<br><span style="color: #ffbf00">★1.5</span><br><br>
		
									</div>
									<div class="col-xs-4 col-md-3">
										<img src="${pageContext.request.contextPath}/resources/images/gad3.jpg" width="100%" height="100%">
										<br><span>앤트맨</span>
										<br><span style="color: #ffbf00">★5.0</span><br><br>
									</div> 
									<div class="col-xs-4 col-md-3">
										<img src="${pageContext.request.contextPath}/resources/images/gad.jpg" width="100%" height="100%">
										<br><span>가디언즈 오브 갤럭시</span>
										<br><span style="color: #ffbf00">★4.5</span><br><br>
									</div>
								</div> 
							</div> <hr/>
							 
							 <div class="col-xs-12 col-md-12">
								<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span style="font-size: 30px">4.5 평가함</span>&emsp;58<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
							</div>
							
							<div class="row container">
								<div class="col-xs-12 col-md-12">
								</div>  
							</div> <hr/>
							 
							 <div class="col-xs-12 col-md-12">
								<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span style="font-size: 30px">4.0 평가함</span>&emsp;98<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
							</div>
							
							<div class="row container">
								<div class="col-xs-12 col-md-12">
								</div> 
							</div> <hr/>
							 
							 <div class="col-xs-12 col-md-12">
								<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span style="font-size: 30px">3.5 평가함</span>&emsp;32<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
							</div>
							
							<div class="row container">
								<div class="col-xs-12 col-md-12">
								</div> 
							</div> <hr/>
							
							<div class="col-xs-12 col-md-12">
								<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span style="font-size: 30px">3.0 평가함</span>&emsp;32<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
							</div>
							
							<div class="row container">
								<div class="col-xs-12 col-md-12">
								</div> 
							</div> <hr/>
							
							<div class="col-xs-12 col-md-12">
								<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span style="font-size: 30px">2.5 평가함</span>&emsp;32<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
							</div>
							
							<div class="row container">
								<div class="col-xs-12 col-md-12">
								</div> 
							</div> <hr/>
							
							<div class="col-xs-12 col-md-12">
								<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span style="font-size: 30px">2.0 평가함</span>&emsp;32<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
							</div>
							
							<div class="row container">
								<div class="col-xs-12 col-md-12">
								</div> 
							</div> <hr/>
							
							<div class="col-xs-12 col-md-12">
								<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span style="font-size: 30px">1.5 평가함</span>&emsp;32<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
							</div>
							
							<div class="row container">
								<div class="col-xs-12 col-md-12">
								</div> 
							</div> <hr/>
							
							<div class="col-xs-12 col-md-12">
								<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span style="font-size: 30px">1.0 평가함</span>&emsp;32<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
							</div>
							
							<div class="row container">
								<div class="col-xs-12 col-md-12">
								</div> 
							</div> <hr/>
							
							<div class="col-xs-12 col-md-12">
								<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span style="font-size: 30px">0.5 평가함</span>&emsp;32<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
							</div>
							
							<div class="row container">
								<div class="col-xs-12 col-md-12">
								</div>  
							</div> <hr/>
						</div> 
					</c:if>
				</div>
			</div>
		</div>
	</div>
