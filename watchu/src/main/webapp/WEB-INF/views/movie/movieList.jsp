<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/movieListAjax.js"></script>
<input type="hidden" class="page-type" value="movieList">

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

<!---------------------------- 모바일 환경 ------------------------->
<form action="movieList.do" method="get" id="listForm" name="listForm">
<div id="categoryHr">
	<select class="visible-xs col-xs-4 form-control genre-category all-category" name="keyword">
    	  <option value="" selected>모든 장르</option>
	   <c:forEach var="cl" items="${movieGenre}">
	      <option value="${cl.genre}">${cl.genre}</option>
	   </c:forEach>
	</select>
	<select class="visible-xs col-xs-4  form-control country-category all-category" name="keyword2">
    	  <option value="" selected>모든 국가</option>
	      <option value="미국">미국</option>
	      <option value="한국">한국</option>
	      <option value="프랑스">프랑스</option>
	      <option value="영국">영국</option>
	      <option value="캐나다">캐나다</option>
	      <option value="중국">중국</option>
	      <option value="일본">일본</option>
	      <option value="홍콩">홍콩</option>
	      <option value="대만">대만</option>
	      <option value="인도">인도</option>
	      <option value="독일">독일</option>
	      <option value="스페인">스페인</option>
	      <option value="오스트레일리아">오스트레일리아</option>
	</select>
	<select class="visible-xs col-xs-4  form-control order-category all-category" name="keyword3"> 
    	  <option value="" selected>최신 순</option>
    	  <option value="rate">별점 순</option>
	</select>
<!---------------------- 웹환경 ----------------------------->
	<select id="category-menu" class="hidden-xs genre-category2 all-category2" name="keyword">
    	  <option value="" selected>모든 장르</option>
	   <c:forEach var="cl" items="${movieGenre}">
    	  <option value="${cl.genre}">${cl.genre}</option>
	   </c:forEach>
	</select>
	<select id="category-menu" class="hidden-xs country-category2 all-category2" name="keyword2">
    	  <option value="" selected>모든 국가</option>
	      <option value="미국">미국</option>
	      <option value="한국">한국</option>
	      <option value="프랑스">프랑스</option>
	      <option value="영국">영국</option>
	      <option value="캐나다">캐나다</option>
	      <option value="중국">중국</option>
	      <option value="일본">일본</option>
	      <option value="홍콩">홍콩</option>
	      <option value="대만">대만</option>
	      <option value="인도">인도</option>
	      <option value="독일">독일</option>
	      <option value="스페인">스페인</option>
	      <option value="오스트레일리아">오스트레일리아</option>
	</select>
	<select id="category-menu" class="hidden-xs category-right order-category2 all-category2" name="keyword3"> 
    	  <option value="" selected>최신 순</option>
    	  <option value="rate">별점 순</option>
	</select>
</div>
</form>

<div class="row">
   <div id="slist"></div>
  	 <div class="movieListButton" style="display:none;">
   		<input class="form-control btn btn-info" type="button" value="영화 더보기">
   	 </div>
</div>