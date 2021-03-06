<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<p class="subTitle2">선호 배우</p>

		<div class="actors">
		<c:forEach var="offList1" items="${offList1}" varStatus="status">
			<div class="actor${status.count}">
				<div class="w_img1">
					<!-- 사진 출력 -->
					<c:if test="${!empty offList1.off_photo}">
						<img class="noEmpty" src="${pageContext.request.contextPath}/movie/actorView.do?off_num=${offList1.off_num}" alt="배우${status.count}">
					</c:if>
					<c:if test="${empty offList1.off_photo}">
						<img class="Empty" src="${pageContext.request.contextPath}/resources/images/default-profile.jpg" alt="기본 이미지">
					</c:if>
					
					<div class="txt1">
						<c:if test="${offList1.jobs.equals('ACTOR')}">
							<p class="actor_Name">${offList1.name} / 배우</p>
						</c:if>
					</div>
					
					<p class="score1">
						<span class="im">${offList1.rate * 20}</span>점 / ${offList1.cnt}편
					</p>
				</div>
			</div>
		</c:forEach>
		</div>
	</div>
	
	<div class="preference">
		<p class="subTitle3">선호 감독</p>
		
		<div class="directors">
			<c:forEach var="offList2" items="${offList2}" varStatus="status">
				<div class="director${status.count}">
					<div class="w_img2">
						<!-- 사진 출력 -->
						<c:if test="${!empty offList2.off_photo}">
							<img class="noEmpty" src="${pageContext.request.contextPath}/movie/actorView.do?off_num=${offList2.off_num}" alt="감독${status.count}">
						</c:if>
						<c:if test="${empty offList2.off_photo}">
							<img class="Empty" src="${pageContext.request.contextPath}/resources/images/default-profile.jpg" alt="기본 이미지">
						</c:if>
						
						<div class="txt2">
							<c:if test="${offList2.jobs.equals('DIRECTOR')}">
								<p class="actor_Name">${offList2.name} / 감독</p>
							</c:if>
						</div>
	
						<p class="score2"><span class="im">${offList2.rate * 20}</span>점 / ${offList2.cnt}편</p>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<div class="genreContext"> 
		<p class="subTitle5">영화 선호장르</p>
		<p class="subText2">본인이 선호하는 영화의 장르를 확인하세요.</p>
		
		<div class="content">
		  <svg id="donut-chart"></svg>
		</div>
		
		<div class="box">
			<c:forEach var="genreList2" items="${genreList2}" begin="0" end="2" varStatus="status">
				<div class="genre">
					<span class="glyphicon glyphicon-heart-empty color${status.count}"></span>
					<p class="genre_name1" id="label${status.count}">${genreList2.genre}</p>
					<span class="stat1">
						<strong id="value${status.count}">${genreList2.rate}</strong>
						<span>/</span>
						<span>${genreList2.cnt}</span>
					</span>
				</div>
			</c:forEach>
		</div>
		
		<div class="rowBox">
			<c:forEach var="genreList2" items="${genreList2}" begin="3" end="5" varStatus="status">	
				<div class="row">
					<div class="col-2 left">
						<span class="name">${genreList2.genre}</span>
					</div>
					<div class="col-2 middle">
						<span class="genre_score">${genreList2.rate}</span>
					</div>
					<div class="col-2 right">
						<span class="genre_total">${genreList2.cnt}</span>
					</div>
				</div>
			</c:forEach>
		</div>
		
	</div>
</div>