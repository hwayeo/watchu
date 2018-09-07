<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 모바일 환경의 상단 -->
<div class="visible-xs">
		<form>
			<div class="input-group input-group-lg" id="md-search">
				<input type="text" class="form-control" placeholder="작품 제목,배우,감독 검색">
				<span class="input-group-btn">
        			<button class="btn btn-default" type="button"><span id="search-icon" class="glyphicon glyphicon-search"></span></button>
    	  		</span>
			</div>
		</form>
</div>
<!-- 모바일 환경에서 사라짐 -->
<nav class="navbar hidden-xs" id="etc-navbar">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/main/main.do" id="brand_text"><b>WATCHU</b></a>
    </div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="navbar-main">
      <form class="navbar-form" role="search">
        <div id="search-field" class="input-group input-group-lg">
      		<input type="text" class="form-control" placeholder="작품 제목,배우,감독 검색">
      		<span class="input-group-btn">
        		<button class="btn btn-default" type="button"><span id="search-icon" class="glyphicon glyphicon-search"></span></button>
      		</span>
      </div>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">영화<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
		        <li><a href="${pageContext.request.contextPath}/movie/movieHome.do">홈</a></li>
		        <li><a href="${pageContext.request.contextPath}/movie/movieList.do">카테고리</a></li>
		        <li><a href="${pageContext.request.contextPath}/movie/movieEva.do">평가하기</a></li>
          </ul>
         </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">내정보<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
	        <c:if test="${empty user_id}">
		        <li><a href="${pageContext.request.contextPath}/user/login.do">로그인</a></li>
	    	    <li><a href="${pageContext.request.contextPath}/user/write.do">회원가입</a></li>
	        </c:if>
	        <c:if test="${!empty user_id}">
	    	    <li><a href="${pageContext.request.contextPath}/user/logout.do">로그아웃</a></li>
	    	    <li><a href="${pageContext.request.contextPath}/user/userMypage.do">마이페이지</a></li>
	        </c:if>
          </ul>
         </li>
      </ul>
    </div><!-- /.navbar-collapse -->
</nav>
