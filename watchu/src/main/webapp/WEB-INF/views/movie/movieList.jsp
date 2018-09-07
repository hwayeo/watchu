<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 검색하면 값 남기기 -->
<form action="movieList.do" method="get">
    <input type="hidden" id="ajx_keyfield" value="${param.keyfield}">
    <input type="hidden" id="ajx_keyword" value="${param.keyword}">
	<ul class="search">
		<li>
			<select name="keyfield">
				<option value="title" <c:if test="${param.keyfield == 'title'}">selected</c:if>>제목</option>
				<option value="country" <c:if test="${param.keyfield == 'country'}">selected</c:if>>나라</option>
				<option value="genre" <c:if test="${param.keyfield == 'gnere'}">selected</c:if>>장르</option>
			</select>
		</li>
		<li>
			<input type="text" name="keyword" id="keyword" value="${param.keyword}">
		</li>
		<li>
			<input type="submit" value="찾기">
		</li>
	</ul>
</form>

<!-- for:each문으로 데이터를 가져와 리스트를 생성할것 -->
<!-- 모바일 환경 -->
<form action="movieList.do" method="get">
<div id="categoryHr">
<select class="visible-xs visible-sm form-control glist" name="keyfield">
	<c:forEach var="cl" items="${list2}">
		<option value="genre">${cl.genre}</option>
	</c:forEach>
</select>
<select class="visible-xs visible-sm form-control">
  <option selected>모든 국가</option>
  <option>한국</option>
</select>
<select class="visible-xs visible-sm form-control">
	<option selected>모든 특징</option>
	<option>연애</option>
	<option>학생</option>
	<option>생존</option>
	<option>멸망</option>
	<option>귀신</option>
	<option>살인마</option>
	<option>괴물</option>
	<option>멜로</option>
	<option>여행</option>
	<option>생존</option>
	<option>패러디</option>
	<option>부부</option>
	<option>법</option>
	<option>우주</option>
	<option>이세계</option>
	<option>먼치킨</option>
	<option>마블</option>
	<option>DC</option>
</select>
<select class="visible-xs visible-sm form-control"> 
  <option selected>최신 순</option>
  <option>추천 순</option>
  <option>별점 순</option>
</select>
<!-- 웹환경 -->
<select id="category-menu" class="hidden-xs hidden-sm">
  <option selected>모든 장르</option>
  <option>판타지</option>
  <option>재난</option>
  <option>공포</option>
  <option>로맨스</option>
  <option>모험</option>
  <option>스릴러</option>
  <option>다큐멘터리</option>
  <option>코미디</option>
  <option>가족</option>
  <option>미스터리</option>
  <option>애니메이션</option>
  <option>범죄</option>
  <option>뮤지컬</option>
  <option>SF</option>
  <option>액션</option>
  <option>히어로</option>
</select>
<select id="category-menu" class="hidden-xs hidden-sm">
  <option selected>모든 국가</option>
  <option>한국</option>
  <option>미국</option>
  <option>일본</option>
  <option>중국</option>
  <option>독일</option>
  <option>대만</option>
  <option>인도</option>
  <option>영국</option>
  <option>프랑스</option>
  <option>이탈리아</option>
  <option>캐나다</option>
  <option>스페인</option>
</select>
<select id="category-menu" class="hidden-xs hidden-sm">
	<option selected>모든 특징</option>
	<option>연애</option>
	<option>학생</option>
	<option>생존</option>
	<option>멸망</option>
	<option>귀신</option>
	<option>살인마</option>
	<option>괴물</option>
	<option>멜로</option>
	<option>여행</option>
	<option>생존</option>
	<option>패러디</option>
	<option>부부</option>
	<option>법</option>
	<option>우주</option>
	<option>이세계</option>
	<option>먼치킨</option>
	<option>마블</option>
	<option>DC</option>
</select>
<select id="category-menu" class="hidden-xs hidden-sm category-right"> 
  <option selected>최신 순</option>
  <option>추천 순</option>
  <option>별점 순</option>
</select>
</div>
</form>

<div class="row">
	<div class="slist"></div>
	<%-- <div class="col-sm-6 col-md-3 col-xs-6" id="main-category">
		<div class="thumbnail" onclick="location.href='movieDetail.do?movie_num=${ml.movie_num}'"><img src="${pageContext.request.contextPath}/resources/images/img4.jpg"></div>
		<div class="sub-category caption">
			<p class="ptitle">${ml.title}</p>
			<p class="pgeren">${ml.country}</p>
		</div>
	</div> --%>
</div>
<c:forEach var="ml" items="${list}">
	<input type="hidden" value="${ml.movie_num}">
	<input type="hidden" value="${ml.country}">
</c:forEach>