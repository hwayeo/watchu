<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mypagemovie.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="main-content">
	<div class="container-fluid">
		<div class="container">
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#home" class="fa fa-clock-o">전체</a></li>
				<li><a data-toggle="tab" href="#menu1" class="fa fa-gavel">별점순</a></li>
			</ul>
		
			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<div class="nav-collapse">
						<ul class="nav">
							<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><b class="caret"></b>&nbsp;정렬</a>
								<ul class="dropdown-menu">
									<li><a href="${pageContext.request.contextPath}/user/userMypage_movielist.do?sort=rate">평점 순</a></li>
									<li><a href="${pageContext.request.contextPath}/user/userMypage_movielist.do?sort=title">가나다 순</a></li>
									<li><a href="${pageContext.request.contextPath}/user/userMypage_movielist.do?sort=reg_date">평가 순</a></li>
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
					<c:if test="${empty recommendList}">
						<div class="text-center">
							<img src="../resources/images/ap.jpg"><br>
							<span style="font-size: 23px;color: #f74788;">평가한 영화가 없어요!</span><br>
							<span>평가하러가기</span>
							<a onclick="location.href='/watchu/movie/movieEva.do'" style="color:#f74788;" class="glyphicon glyphicon-hand-right"></a>
				    	</div>
					</c:if>
					
					<c:if test="${!empty recommendList}">
						<div class="container row"><br>
							<!-- 5.0 -->
							<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span class="rate_pont">5.0 평가함</span>&emsp;<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button" onclick="location.href='analysis.do'">더보기</button><br><br>
									</div>
								</div>
								<c:forEach var="recommendList" items="${recommendList}" varStatus="status">
									<div class="col-xs-6 col-md-2">
										<c:if test="${recommendList.rate == 5.0}">
											<c:if test="${status.index < 4}">
												<c:if test="${!empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${recommendList.movie_num}&type=poster">
												</c:if>	
												<c:if test="${empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/resources/images/default-poster.jpg">
												</c:if>
												<br><p class="movie_title3">${recommendList.title}</p>
											</c:if>
										</c:if>
									</div> 
								</c:forEach><hr/>
							<!-- 5.0 -->
							<!-- 4.5 -->
							<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span class="rate_pont">4.5 평가함</span>&emsp;58<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
								<c:forEach var="recommendList" items="${recommendList}" varStatus="status">
									<div class="col-xs-6 col-md-2">
										<c:if test="${recommendList.rate == 4.5 }">
											<c:if test="${status.index < 4 }">
												<c:if test="${!empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${recommendList.movie_num}&type=poster">
												</c:if>	
												<c:if test="${empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/resources/images/default-poster.jpg">
												</c:if>
												<br><p class="movie_title3">${recommendList.title}</p>
											</c:if>
										</c:if>
									</div>
								</c:forEach><hr/>
							<!-- 5.0 -->
							<!-- 4.0 -->
							<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span class="rate_pont">4.0 평가함</span>&emsp;58<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
								<c:forEach var="recommendList" items="${recommendList}" varStatus="status">
									<div class="col-xs-6 col-md-2">
										<c:if test="${recommendList.rate == 4.0}">
											<c:if test="${status.index < 4}">
												<c:if test="${!empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${recommendList.movie_num}&type=poster">
												</c:if>	
												<c:if test="${empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/resources/images/default-poster.jpg">
												</c:if>
												<br><p class="movie_title3">${recommendList.title}</p>
											</c:if>
										</c:if>
									</div>
								</c:forEach><hr/>
							<!-- 5.0 -->
							<!-- 3.5 -->
							<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span class="rate_pont">3.5 평가함</span>&emsp;58<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
								<c:forEach var="recommendList" items="${recommendList}" varStatus="status">
									<div class="col-xs-6 col-md-2">
										<c:if test="${recommendList.rate == 3.5}">
											<c:if test="${status.index < 4}">
												<c:if test="${!empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${recommendList.movie_num}&type=poster">
												</c:if>	
												<c:if test="${empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/resources/images/default-poster.jpg">
												</c:if>
												<br><p class="movie_title3">${recommendList.title}</p>
											</c:if>
										</c:if>
									</div>
								</c:forEach><hr/>
							<!-- 5.0 -->
							<!-- 3.0 -->
							<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span class="rate_pont">3.0 평가함</span>&emsp;58<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
								<c:forEach var="recommendList" items="${recommendList}" varStatus="status">
									<div class="col-xs-6 col-md-2">
										<c:if test="${recommendList.rate == 3.0}">
											<c:if test="${status.index < 4}">
												<c:if test="${!empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${recommendList.movie_num}&type=poster">
												</c:if>	
												<c:if test="${empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/resources/images/default-poster.jpg">
												</c:if>
												<br><p class="movie_title3">${recommendList.title}</p>
											</c:if>
										</c:if>
									</div>
								</c:forEach><hr/>
							<!-- 5.0 -->
							<!-- 2.5 -->
							<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span class="rate_pont">2.5 평가함</span>&emsp;58<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
								<c:forEach var="recommendList" items="${recommendList}" varStatus="status">
									<div class="col-xs-6 col-md-2">
										<c:if test="${recommendList.rate == 2.5}">
											<c:if test="${status.index < 4}">
												<c:if test="${!empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${recommendList.movie_num}&type=poster">
												</c:if>	
												<c:if test="${empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/resources/images/default-poster.jpg">
												</c:if>
												<br><p class="movie_title3">${recommendList.title}</p>
											</c:if>
										</c:if>
									</div>
								</c:forEach><hr/>
							<!-- 5.0 -->
							<!-- 2.0 -->
							<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span class="rate_pont">2.0 평가함</span>&emsp;58<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
								<c:forEach var="recommendList" items="${recommendList}" varStatus="status">
									<div class="col-xs-6 col-md-2">
										<c:if test="${recommendList.rate == 2.0}">
											<c:if test="${status.index < 4}">
												<c:if test="${!empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${recommendList.movie_num}&type=poster">
												</c:if>	
												<c:if test="${empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/resources/images/default-poster.jpg">
												</c:if>
												<br><p class="movie_title3">${recommendList.title}</p>
											</c:if>
										</c:if>
									</div>
								</c:forEach><hr/>
							<!-- 5.0 -->
							<!-- 1.5 -->
							<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span class="rate_pont">1.5 평가함</span>&emsp;58<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
								<c:forEach var="recommendList" items="${recommendList}" varStatus="status">
									<div class="col-xs-6 col-md-2">
										<c:if test="${recommendList.rate == 1.5}">
											<c:if test="${status.index < 4}">
												<c:if test="${!empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${recommendList.movie_num}&type=poster">
												</c:if>	
												<c:if test="${empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/resources/images/default-poster.jpg">
												</c:if>
												<br><p class="movie_title3">${recommendList.title}</p>
											</c:if>
										</c:if>
									</div>
								</c:forEach><hr/>
							<!-- 5.0 -->
							<!-- 1.0 -->
							<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span class="rate_pont">1.0 평가함</span>&emsp;58<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
								<c:forEach var="recommendList" items="${recommendList}" varStatus="status">
									<div class="col-xs-6 col-md-2">
										<c:if test="${recommendList.rate == 1.0}">
											<c:if test="${status.index < 4}">
												<c:if test="${!empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${recommendList.movie_num}&type=poster">
												</c:if>	
												<c:if test="${empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/resources/images/default-poster.jpg">
												</c:if>
												<br><p class="movie_title3">${recommendList.title}</p>
											</c:if>
										</c:if>
									</div>
								</c:forEach><hr/>
							<!-- 1.0 -->	
							<!-- 0.5 -->
							<div class="container row">
									<div class="col-xs-7 col-md-7">
										<span class="rate_pont">0.5 평가함</span>&emsp;58<br><br>
									</div>
									<div class="col-xs-1 col-md-3"></div>
									<div class="col-xs-4 col-md-2">
										<button class="btn btn-md btn-primary active" type="button">더보기</button><br><br>
									</div>
								</div>
								<c:forEach var="recommendList" items="${recommendList}" varStatus="status">
									<div class="col-xs-6 col-md-2">
										<c:if test="${recommendList.rate == 0.5}">
											<c:if test="${status.index < 4}">
												<c:if test="${!empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${recommendList.movie_num}&type=poster">
												</c:if>	
												<c:if test="${empty recommendList.poster_img}">
													<img class="movie_poster3" src="${pageContext.request.contextPath}/resources/images/default-poster.jpg">
												</c:if>
												<br><p class="movie_title3">${recommendList.title}</p>
											</c:if>
										</c:if>
									</div>
								</c:forEach><hr/>
							<!-- 0.5 -->	
							
							</div> <hr/>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
