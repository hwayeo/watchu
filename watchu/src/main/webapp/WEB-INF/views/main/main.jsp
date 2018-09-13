<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/watchu-main.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/boxOffice.js"></script>
<div id="main-page">
	<div id="main_banner">
		<div id="img-test">
			<div id="banner-back">
				<h1 id="banner-text"><b>WATCHU</b></h1>
			</div>
		</div>
	</div>
</div>
<div class="container" id="main-page-content">
	<c:if test="${empty user_id}">
	<div class="row custom-row">
		<div class="text-center">
			<h3><b>왓츄에 함께 하셔서 다양한 영화를 추천 받아보세요!</b></h3>
		</div>
	</div>
	</c:if>
	<div class="row custom-row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h4>일일 박스 오피스</h4>		
			<div class="items-box">
				<div id="boxOffice-output">
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h4>오늘은 이거다!</h4>
			<div class="items-box" onclick="location.href='${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${randomMovie.movie_num}'" style="cursor:pointer;">
				<div class="img-box">
					<c:if test="${empty randomMovie.poster_img}">
						<img src="${pageContext.request.contextPath}/resources/images/default-banner.jpg" class="img-responsive main-img">
					</c:if>
					<c:if test="${!empty randomMovie.poster_img}">
						<img src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${randomMovie.movie_num}&type=banner" class="img-responsive main-img">
					</c:if>
				</div>
				<div class="text-box">	
					<p class="movie-title"><a href="#" class="movie-link">${randomMovie.title}</a>&nbsp;<p>
					<p class="sub-title">${randomMovie.main_genre}&nbsp;&middot;&nbsp;<fmt:formatDate value="${randomMovie.released}" pattern="YYYY"/></p>
					<span id="ratedCnt"><span class="glyphicon glyphicon-star"><span id="rated">${randomMovie.rate}</span></span></span>
				</div>
			</div>
		</div>
	</div>
	<div class="row custom-row">
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
			<h4>이런 작품은 어때요?</h4>		
			<div class="items-box-col3">
				<img src="${pageContext.request.contextPath}/resources/images/main-banner-test.jpg" class="img-responsive main-img-col3">
				<div class="text-box-col3">	
					<p class="movie-title"><a href="#" class="movie-link">영화제목</a><p>
					<p class="sub-title">슈퍼히어로 2018</p>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
			<h4>이런 작품은 어때요?</h4>		
			<div class="items-box-col3">
				<img src="${pageContext.request.contextPath}/resources/images/main-banner-test.jpg" class="img-responsive main-img-col3">
				<div class="text-box-col3">	
					<p class="movie-title"><a href="#" class="movie-link">영화제목</a><p>
					<p class="sub-title">슈퍼히어로 2018</p>
				</div>
			</div>
		</div>
		<div class="col-xs-12 hidden-sm col-md-4 col-lg-4">
			<h4>이런 작품은 어때요?</h4>		
			<div class="items-box-col3">
				<img src="${pageContext.request.contextPath}/resources/images/main-banner-test.jpg" class="img-responsive main-img-col3">
				<div class="text-box-col3">	
					<p class="movie-title"><a href="#" class="movie-link">영화제목</a><p>
					<p class="sub-title">슈퍼히어로 2018</p>
				</div>
			</div>
		</div>
	</div>
</div>