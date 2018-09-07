<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 최신 등록 영화 -->
<div class="row">
	<div class="home-text1">최신영화
	<a href="movieList.do" class="home-text2">모두보기</a></div>
	<div class="mlist"></div>
</div>  

<!-- 추천 영화 태그1 -->
<div class="row">
	<div class="home-text1">미국영화
	<a href="movieList.do?country=미국" class="home-text2">American</a></div>
	<div class="mlist2"></div>
</div>

<!-- 추천 영화 태그2 -->
<div class="row">
	<div class="home-text1">공포영화
	<a href="movieList.do?keyfield=genre&keyword=공포" class="home-text2">무섭다</a></div>
	<div class="mlist3"></div>
</div>

<c:forEach var="ml" items="${list}">
	<input type="hidden" id="movie_num" name="movie_num" value="${ml.movie_num}">
	<input type="hidden" id="country" name="country" value="${ml.country}">
</c:forEach>