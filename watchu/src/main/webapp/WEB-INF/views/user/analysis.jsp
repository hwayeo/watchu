<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/analysis.css">


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
	
	<div class="starGraph">
		<p class="subTitle2">영화 별점분포</p>
		<p class="subText1">가나다라마바사아자차카타파하</p>
		
		<div class="graph">
			
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
			<p class="clickChange">∨ 더 보기</p>
		</div>
	</div>
	
	<div class="preference">
		<p class="subTitle4">선호 감독</p>
		
		<div class="director1">
			<img src="${pageContext.request.contextPath}/resources/images/1.jpg" alt="감독1">
			
			<div class="txt">
				<p class="director_Name">감독명</p>
				<p class="director_Moviename">출연작</p>
			</div>
			
			<span>00점/00편</span>
		</div>
		
		<div class="button">
			<p class="clickChange">∨ 더 보기</p>
		</div>
	</div>
	
	<div class="genre">
		<p class="subTitle5">영화 선호장르</p>
		<p class="subText2">영화장르영화장르영화장르</p>
		
		<div class="">
			
		</div>
	</div>
</div>
