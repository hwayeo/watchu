<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/watchu-main.js"></script>
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
			<div class="items-box" onclick="location.href='${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${randomMovie.movie_num}'">
				<div class="img-box">
					<img src="${pageContext.request.contextPath}/resources/images/main-banner-test.jpg" class="img-responsive main-img">
				</div>
				<div class="text-box">	
					<p class="movie-title"><a href="#" class="movie-link">${randomMovie.title}</a><p>
					<p class="sub-title">슈퍼히어로 . 2018</p>
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