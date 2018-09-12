<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/movieListAjax.js"></script>
<nav class="navbar navbar-default navWatch">
      <div class="navbar-header">
         <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-movie">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
         </button>
         <a href="#" class="navbar-brand">영화메뉴</a>
      </div>
      <div class="collapse navbar-collapse navbar-movie">
      <ul class="nav navbar-nav">
         <li><a href="movieHome.do">홈</a></li>
         <li><a href="movieList.do">카테고리</a></li>
         <li><a href="movieEva.do">평가하기</a></li>
      </ul>
      </div>
   </nav>

<!-- 검색하면 값 남기기 -->
<input type="hidden" class="page-type" value="movieList">
<!-- for:each문으로 데이터를 가져와 리스트를 생성할것 -->
<!-- 모바일 환경 -->
<form action="movieList.do" method="get" id="listForm" name="listForm">
<div id="categoryHr">
<!-- <input type="hidden" id="ajx_genre" value="list" name="genre"> -->
<select class="visible-xs visible-sm form-control genre-category all-category" name="keyword">
      <option value="">모든 장르</option>
   <c:forEach var="cl" items="${movieGenre}">
      <option value="${cl.genre}">${cl.genre}</option>
   </c:forEach>
</select>
<select class="visible-xs visible-sm form-control country-category all-category" name="keyword2">
        <option value="">모든 국가</option>
    <c:forEach var="gl" items="${movieInfo}">
      <option value="${gl.country}">${gl.country}</option>
   </c:forEach>
</select>
<select class="visible-xs visible-sm form-control order-category all-category" name="keyword"> 
  <option value="" selected>최신 순</option>
  <option value="">추천 순</option>
  <option value="rate">별점 순</option>
</select>
<!-- 웹환경 -->
<select id="category-menu" class="hidden-xs hidden-sm genre-category2 all-category2" name="keyword">
      <option value="">모든 장르</option>
   <c:forEach var="cl" items="${movieGenre}">
      <option value="${cl.genre}">${cl.genre}</option>
   </c:forEach>
</select>
<select id="category-menu" class="hidden-xs hidden-sm country-category2 all-category2" name="keyword2">
        <option value="">모든 국가</option>
    <c:forEach var="gl" items="${movieInfo}">
      <option value="${gl.country}">${gl.country}</option>
   </c:forEach>
</select>
<select id="category-menu" class="hidden-xs hidden-sm category-right order-category2 all-category2" name="keyword"> 
  <option value="" selected>최신 순</option>
  <option value="">추천 순</option>
  <option value="rate">별점 순</option>
</select>
</div>
</form>

<div class="row">
   <div id="slist"></div>
   <div class="movieListButton">
   	<input type="button" class="form-control" value="목록 더보기">
   </div>
</div>