<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="description" content="D3.js v4, d3.scaleOrdinal(d3.schemeCategory10) categorical ordinal colors" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/analysis.css">
<script src='https://cdnjs.cloudflare.com/ajax/libs/d3/4.2.2/d3.min.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/analysis.js"></script>

<div id="main-content">
	<div class="bg">
		<img src="${pageContext.request.contextPath}/resources/images/abcd.jpg" alt="유저가지정한사진">
		<p class="userName">유저네임</p>
		<p class="movieCount">평가한 영화 수</p>
	</div>
	
	<div class="selectTag">
		<p class="subTitle1">영화 선호태그</p>
		
		<div class="center">
			<div class="txtEffect container">
				<span class="select1">블록버스터</span>
				<span class="select2">스릴있는</span>
				<span class="select3">강렬한</span>
				<span class="select4">마블</span>
				<span class="select5">영웅</span>
			</div>
		</div>
	</div>
	
	<div class="preference">
		<p class="subTitle3">선호 배우</p>
		
		<div class="actor1">
			<div class="w_img">
				<img src="${pageContext.request.contextPath}/resources/images/1.jpg" alt="배우1">
			
				<div class="txt">
					<p class="actor_Name">배우명</p>
					<p class="actor_Moviename">출연작</p>
				</div>
			</div>
			
			<p class="score"><span class="im">00</span>점/00편</p>
		</div>
		
		<div class="button">
			<p class="clickChange"><span class="glyphicon glyphicon-chevron-down"></span> 더 보기</p>
		</div>
	</div>
	
	<div class="preference">
		<p class="subTitle4">선호 감독</p>
		
		<div class="director1">
			<div class="w_img">
				<img src="${pageContext.request.contextPath}/resources/images/1.jpg" alt="감독1">
				
				<div class="txt">
					<p class="director_Name">감독명</p>
					<p class="director_Moviename">출연작</p>
				</div>
			</div>
			
			<p class="score"><span class="im">00</span>점/00편</p>
		</div>
		
		<div class="button">
			<p class="clickChange"><span class="glyphicon glyphicon-chevron-down"></span> 더 보기</p>
		</div>
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