<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="description" content="D3.js v4, d3.scaleOrdinal(d3.schemeCategory10) categorical ordinal colors" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/analysis.css">
<script src='https://cdnjs.cloudflare.com/ajax/libs/d3/4.2.2/d3.min.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/analysis.js"></script>

<div id="main-content">
	<div class="bg">
		<!-- 프로필 이미지 등록 여부 확인 -->
		<c:if test="${empty profile}">
			<img src="${pageContext.request.contextPath}/resources/images/default-profile.jpg" width="70" height="70" class="img-circle">
		</c:if>
		<c:if test="${!empty profile}">
			<img src="${pageContext.request.contextPath}/main/imageView.do?id=${user_id}" width="70" height="70" class="img-circle">
		</c:if>
		
		<p class="userName">${user_id}</p>
		<p class="movieCount">${count}편</p>
	</div>
	
	<div class="selectTag">
		<p class="subTitle1">영화 선호태그</p>
		
		<div class="center">
			<div class="txtEffect container">
				<c:forEach var="genreList" items="${genreList}" varStatus="status">
					<span class="select${status.count}">${genreList.genre}</span>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<div class="preference">
		<p class="subTitle3">선호 배우</p>

		<c:forEach var="offList1" items="${offList1}" varStatus="status">
			<div class="actor${status.count}">
				<div class="w_img">
					<img src="${pageContext.request.contextPath}/resources/images/1.jpg" alt="배우${status.count}">

					<div class="txt">
						<p class="actor_Name">${offList1.name}</p>
						<p class="actor_Moviename">${offList1.jobs}</p>
					</div>
				</div>
			</div>

			<p class="score"><span class="im">${offList1.rate}</span>점</p>
		</c:forEach>
	</div>
		
	<div class="button">
		<p class="clickChange"><span class="glyphicon glyphicon-chevron-down"></span> 더 보기</p>
	</div>
	
	<div class="preference">
		<p class="subTitle4">선호 감독</p>

		<c:forEach var="offList2" items="${offList2}" varStatus="status">
			<div class="director${status.count}">
				<div class="w_img">
					<img src="${pageContext.request.contextPath}/resources/images/1.jpg" alt="감독${status.count}">

					<div class="txt">
						<p class="director_Name">${offList2.name}</p>
						<p class="director_Moviename">${offList2.jobs}</p>
					</div>
				</div>
			</div>

			<p class="score"><span class="im">${offList2.rate}</span>점</p>
		</c:forEach>
	</div>
		
	<div class="button">
		<p class="clickChange"><span class="glyphicon glyphicon-chevron-down"></span> 더 보기</p>
	</div>
	
	<div class="genreContext">
		<p class="subTitle5">영화 선호장르</p>
		<p class="subText2">영화장르영화장르영화장르</p>
		
		<div class="content">
		  <svg id="donut-chart"></svg>
		</div>
		
		<div class="box">
			<div class="genre">
				<span class="glyphicon glyphicon-heart-empty red"></span>
				<p class="genre_name1">장르1</p>
				<span class="stat1">
					<strong>00</strong>
					<span>/</span>
					<span>00</span>
				</span>
			</div>
			
			<div class="genre">
				<span class="glyphicon glyphicon-heart-empty blue"></span>
				<p class="genre_name2">장르2</p>
				<span class="stat2">
					<strong>00</strong>
					<span>/</span>
					<span>00</span>
				</span>
			</div>
			
			<div class="genre">
				<span class="glyphicon glyphicon-heart-empty green"></span>
				<p class="genre_name3">장르3</p>
				<span class="stat3">
					<strong>00</strong>
					<span>/</span>
					<span>00</span>
				</span>
			</div>
		</div>
		
		<div class="rowBox">
			<div class="row">
				<div class="col-2 left">
					<span class="name">장르1</span>
				</div>
				<div class="col-2 middle">
					<span class="genre_score">점수1</span>
				</div>
				<div class="col-2 right">
					<span class="genre_total">총편1</span>
				</div>
			</div>
			
			<div class="row">
				<div class="col-2 left">
					<span class="name">장르2</span>
				</div>
				<div class="col-2 middle">
					<span class="genre_score">점수2</span>
				</div>
				<div class="col-2 right">
					<span class="genre_total">총편2</span>
				</div>
			</div>
			
			<div class="row">
				<div class="col-2 left">
					<span class="name">장르3</span>
				</div>
				<div class="col-2 middle">
					<span class="genre_score">점수3</span>
				</div>
				<div class="col-2 right">
					<span class="genre_total">총편3</span>
				</div>
			</div>
		</div>
	</div>
</div>