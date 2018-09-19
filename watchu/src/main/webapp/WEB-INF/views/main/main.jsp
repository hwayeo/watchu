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
<input type="hidden" value="${ranBanner}" id="ranBanner">
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
			<h4><b>일일 박스 오피스</b></h4>		
			<div class="items-box">
				<div id="boxOffice-output">
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h4><b>오늘은 이거다!</b></h4>
			<div class="items-box" onclick="location.href='${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${randomMovie.movie_num}'" style="cursor:pointer;">
				<div class="img-box" id="rec-random-banner">
					<input type="hidden" value="${randomMovie.movie_num}" id="ranMovie">
					<c:if test="${empty randomMovie.poster_img}">
						<img src="${pageContext.request.contextPath}/resources/images/default-banner.jpg" class="img-responsive main-img">
					</c:if>
					<c:if test="${!empty randomMovie.poster_img}">
						<img src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${randomMovie.movie_num}&type=banner" class="img-responsive main-img">
					</c:if>
				</div>
				<div class="text-box">	
					<p class="movie-title" >${randomMovie.title}<p>
					<p class="sub-title" style="float:left;">${randomMovie.main_genre}&nbsp;&middot;&nbsp;<fmt:formatDate value="${randomMovie.released}" pattern="YYYY"/></p>
					<div class="text-right" style="margin-bottom:10px;">
						<span id="ratedCnt"><span class="glyphicon glyphicon-star"><span id="rated">${randomMovie.rate}</span></span></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row custom-row">
		<c:if test="${empty user_id}">
		<c:if test="${!empty ranGenreMovie}">
			<c:forEach var="list" items="${ranGenreMovie}" varStatus="status">
				<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
					<c:if test="${status.first}">
						<h4><b>${ranGenre} 영화는 어떠세요?</b></h4>		
					</c:if>
					<c:if test="${!status.first}">
						<h4 class="hidden-xs"><b>&nbsp;</b></h4>		
					</c:if>
					<div class="items-box-col3" onclick="location.href='${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${list.movie_num}'" style="cursor:pointer;">
					<c:if test="${empty list.banner_img}">
						<img src="${pageContext.request.contextPath}/resources/images/default-banner.jpg" class="img-responsive main-img-col3">
					</c:if>
					<c:if test="${!empty list.banner_img}">
						<img src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${list.movie_num}&type=banner" class="img-responsive main-img-col3">
					</c:if>
						<div class="text-box-col3">	
							<p class="movie-title" style="font-size:1.1em;">${list.title}<p>
							<p class="sub-title">${list.main_genre}&nbsp;&middot;&nbsp;${list.sub_genre}&nbsp;&middot;&nbsp;<fmt:formatDate value="${list.released}" pattern="YYYY"/>&nbsp;&nbsp;
								<span id="ratedCnt"><span class="glyphicon glyphicon-star"><span id="rated">${list.rate}</span></span></span>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
		</c:if>
		<c:if test="${!empty user_id}">
			<c:forEach var="list" items="${recGenreMovie}" varStatus="status">
				<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
					<c:if test="${status.first}">
						<h4><b>${recGenre} 영화를 좋아 하시는군요!</b></h4>		
					</c:if>
					<c:if test="${!status.first}">
						<h4 class="hidden-xs"><b>&nbsp;</b></h4>		
					</c:if>
					<div class="items-box-col3" onclick="location.href='${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${list.movie_num}'" style="cursor:pointer;">
					<c:if test="${empty list.banner_img}">
						<img src="${pageContext.request.contextPath}/resources/images/default-banner.jpg" class="img-responsive main-img-col3">
					</c:if>
					<c:if test="${!empty list.banner_img}">
						<img src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${list.movie_num}&type=banner" class="img-responsive main-img-col3">
					</c:if>
						<div class="text-box-col3">	
							<p class="movie-title" style="font-size:1.1em;">${list.title}<p>
							<p class="sub-title">${list.main_genre}&nbsp;&middot;&nbsp;${list.sub_genre}&nbsp;&middot;&nbsp;<fmt:formatDate value="${list.released}" pattern="YYYY"/>&nbsp;&nbsp;
								<span id="ratedCnt"><span class="glyphicon glyphicon-star"><span id="rated">${list.rate}</span></span></span>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if> 
	</div>
	<div class="row custom-row">
		<c:if test="${empty user_id}">
				<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
					<h4><b>${ranActor1}의 영화는 어때요?</b></h4>		
					<div class="items-box-col3" onclick="location.href='${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${ranActorMovie.movie_num}'" style="cursor:pointer;">
					<c:if test="${empty ranActorMovie.banner_img}">
						<img src="${pageContext.request.contextPath}/resources/images/default-banner.jpg" class="img-responsive main-img-col3">
					</c:if>
					<c:if test="${!empty ranActorMovie.banner_img}">
						<img src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${ranActorMovie.movie_num}&type=banner" class="img-responsive main-img-col3">
					</c:if>
						<div class="text-box-col3">	
							<p class="movie-title" style="font-size:1.1em;">${ranActorMovie.title}<p>
							<p class="sub-title">${ranActorMovie.main_genre}&nbsp;&middot;&nbsp;${ranActorMovie.sub_genre}&nbsp;&middot;&nbsp;<fmt:formatDate value="${ranActorMovie.released}" pattern="YYYY"/>&nbsp;&nbsp;
								<span id="ratedCnt"><span class="glyphicon glyphicon-star"><span id="rated">${ranActorMovie.rate}</span></span></span>
							</p>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
					<h4><b>${ranActor2}도 괜찮나요?</b></h4>		
					<div class="items-box-col3" onclick="location.href='${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${ranActorMovie2.movie_num}'" style="cursor:pointer;">
					<c:if test="${empty ranActorMovie2.banner_img}">
						<img src="${pageContext.request.contextPath}/resources/images/default-banner.jpg" class="img-responsive main-img-col3">
					</c:if>
					<c:if test="${!empty ranActorMovie2.banner_img}">
						<img src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${ranActorMovie2.movie_num}&type=banner" class="img-responsive main-img-col3">
					</c:if>
						<div class="text-box-col3">	
							<p class="movie-title" style="font-size:1.1em;">${ranActorMovie2.title}<p>
							<p class="sub-title">${ranActorMovie2.main_genre}&nbsp;&middot;&nbsp;${ranActorMovie2.sub_genre}&nbsp;&middot;&nbsp;<fmt:formatDate value="${ranActorMovie2.released}" pattern="YYYY"/>&nbsp;&nbsp;
								<span id="ratedCnt"><span class="glyphicon glyphicon-star"><span id="rated">${ranActorMovie2.rate}</span></span></span>
							</p>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
					<h4><b>${ranActor3}도 놓칠 수 없죠!</b></h4>		
					<div class="items-box-col3" onclick="location.href='${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${ranActorMovie3.movie_num}'" style="cursor:pointer;">
					<c:if test="${empty ranActorMovie3.banner_img}">
						<img src="${pageContext.request.contextPath}/resources/images/default-banner.jpg" class="img-responsive main-img-col3">
					</c:if>
					<c:if test="${!empty ranActorMovie3.banner_img}">
						<img src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${ranActorMovie3.movie_num}&type=banner" class="img-responsive main-img-col3">
					</c:if>
						<div class="text-box-col3">	
							<p class="movie-title" style="font-size:1.1em;">${ranActorMovie3.title}<p>
							<p class="sub-title">${ranActorMovie3.main_genre}&nbsp;&middot;&nbsp;${ranActorMovie3.sub_genre}&nbsp;&middot;&nbsp;<fmt:formatDate value="${ranActorMovie3.released}" pattern="YYYY"/>&nbsp;&nbsp;
								<span id="ratedCnt"><span class="glyphicon glyphicon-star"><span id="rated">${ranActorMovie3.rate}</span></span></span>
							</p>
						</div>
					</div>
				</div>
		</c:if>
		<c:if test="${!empty user_id}">
			<c:forEach var="list" items="${recActorMovie}" varStatus="status">
				<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
					<c:if test="${status.first}">
						<c:if test="${recActor eq 'reData'}">
							<h4><b>${user_id}님 이런 영화는 어때요?</b></h4>		
						</c:if>
						<c:if test="${recActor ne 'reData'}">
							<h4><b>${recActor}의 다른 영화</b></h4>		
						</c:if>
					</c:if>
					<c:if test="${!status.first}">
						<h4 class="hidden-xs"><b>&nbsp;</b></h4>		
					</c:if>
					<div class="items-box-col3" onclick="location.href='${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${list.movie_num}'" style="cursor:pointer;">
					<c:if test="${empty list.banner_img}">
						<img src="${pageContext.request.contextPath}/resources/images/default-banner.jpg" class="img-responsive main-img-col3">
					</c:if>
					<c:if test="${!empty list.banner_img}">
						<img src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${list.movie_num}&type=banner" class="img-responsive main-img-col3">
					</c:if>
						<div class="text-box-col3">	
							<p class="movie-title" style="font-size:1.1em;">${list.title}<p>
							<p class="sub-title">${list.main_genre}&nbsp;&middot;&nbsp;${list.sub_genre}&nbsp;&middot;&nbsp;<fmt:formatDate value="${list.released}" pattern="YYYY"/>&nbsp;&nbsp;
								<span id="ratedCnt"><span class="glyphicon glyphicon-star"><span id="rated">${list.rate}</span></span></span>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>
	<div class="row custom-row">
		<c:if test="${empty user_id}">
				<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
					<h4><b>${ranDirector1} 감독의 영화는 어때요?</b></h4>		
					<div class="items-box-col3" onclick="location.href='${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${ranDirectorMovie1.movie_num}'" style="cursor:pointer;">
					<c:if test="${empty ranDirectorMovie1.banner_img}">
						<img src="${pageContext.request.contextPath}/resources/images/default-banner.jpg" class="img-responsive main-img-col3">
					</c:if>
					<c:if test="${!empty ranDirectorMovie1.banner_img}">
						<img src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${ranDirectorMovie1.movie_num}&type=banner" class="img-responsive main-img-col3">
					</c:if>
						<div class="text-box-col3">	
							<p class="movie-title" style="font-size:1.1em;">${ranDirectorMovie1.title}<p>
							<p class="sub-title">${ranDirectorMovie1.main_genre}&nbsp;&middot;&nbsp;${ranDirectorMovie1.sub_genre}&nbsp;&middot;&nbsp;<fmt:formatDate value="${ranDirectorMovie1.released}" pattern="YYYY"/>&nbsp;&nbsp;
								<span id="ratedCnt"><span class="glyphicon glyphicon-star"><span id="rated">${ranDirectorMovie1.rate}</span></span></span>
							</p>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
					<h4><b>${ranDirector2} 감독의 영화는 어때요?</b></h4>		
					<div class="items-box-col3" onclick="location.href='${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${ranDirectorMovie2.movie_num}'" style="cursor:pointer;">
					<c:if test="${empty ranDirectorMovie2.banner_img}">
						<img src="${pageContext.request.contextPath}/resources/images/default-banner.jpg" class="img-responsive main-img-col3">
					</c:if>
					<c:if test="${!empty ranDirectorMovie2.banner_img}">
						<img src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${ranDirectorMovie2.movie_num}&type=banner" class="img-responsive main-img-col3">
					</c:if>
						<div class="text-box-col3">	
							<p class="movie-title" style="font-size:1.1em;">${ranDirectorMovie2.title}<p>
							<p class="sub-title">${ranDirectorMovie2.main_genre}&nbsp;&middot;&nbsp;${ranDirectorMovie2.sub_genre}&nbsp;&middot;&nbsp;<fmt:formatDate value="${ranDirectorMovie2.released}" pattern="YYYY"/>&nbsp;&nbsp;
								<span id="ratedCnt"><span class="glyphicon glyphicon-star"><span id="rated">${ranDirectorMovie2.rate}</span></span></span>
							</p>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
					<h4><b>${ranDirector3} 감독의 영화는 어때요?</b></h4>		
					<div class="items-box-col3" onclick="location.href='${pageContext.request.contextPath}/movie/movieDetail.do?movie_num=${ranDirectorMovie3.movie_num}'" style="cursor:pointer;">
					<c:if test="${empty ranDirectorMovie3.banner_img}">
						<img src="${pageContext.request.contextPath}/resources/images/default-banner.jpg" class="img-responsive main-img-col3">
					</c:if>
					<c:if test="${!empty ranDirectorMovie3.banner_img}">
						<img src="${pageContext.request.contextPath}/movie/imageView.do?movie_num=${ranDirectorMovie3.movie_num}&type=banner" class="img-responsive main-img-col3">
					</c:if>
						<div class="text-box-col3">	
							<p class="movie-title" style="font-size:1.1em;">${ranDirectorMovie3.title}<p>
							<p class="sub-title">${ranDirectorMovie3.main_genre}&nbsp;&middot;&nbsp;${ranDirectorMovie3.sub_genre}&nbsp;&middot;&nbsp;<fmt:formatDate value="${ranDirectorMovie3.released}" pattern="YYYY"/>&nbsp;&nbsp;
								<span id="ratedCnt"><span class="glyphicon glyphicon-star"><span id="rated">${ranDirectorMovie3.rate}</span></span></span>
							</p>
						</div>
					</div>
				</div>
		</c:if>
	</div>
</div>