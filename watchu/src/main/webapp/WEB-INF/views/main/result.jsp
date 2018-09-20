<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="main-content">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-12 col-lg-12">
				<h3>영화 검색 결과</h3>
				<div class="text-right">
					<a href="#">더보기</a>
				</div>
				<div class="results">
					<div class="row">
						<c:forEach var="list" items="${movieList}" varStatus="status">
							<c:if test="${status.index < 3}">
								<div class="col-xs-4 col-md-3" onclick="location.href='${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${list.movie_num}'" style="cursor:pointer;">
									<c:if test="${empty item.poster_img}">
										<img src="${pageContext.request.contextPath}/resources/images/default-poster.jpg" class="img-responsive">
									</c:if>
									<c:if test="${!empty item.poster_img}">
										<img src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${list.movie_num}&type=poster" class="img-responsive">
									</c:if>
									<p>${list.title}</p>
								</div>
							</c:if>
							<c:if test="${status.index == 3}">
								<div class="hidden-xs visible-md visible-lg col-md-3" onclick="location.href='${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${list.movie_num}'" style="cursor:pointer;">
									<c:if test="${empty item.poster_img}">
										<img src="${pageContext.request.contextPath}/resources/images/default-poster.jpg" class="img-responsive">
									</c:if>
									<c:if test="${!empty item.poster_img}">
										<img src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${list.movie_num}&type=poster" class="img-responsive">
									</c:if>
									<p>${list.title}</p>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
