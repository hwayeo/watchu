<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 모바일 환경의 상단 -->
<div class="visible-xs">
		<!-- submit 이벤트를 위해서 다시 추가했습니다. -->
		<input type="hidden" id="ajx_keyfield" value="${param.keyfield}">
		<input type="hidden" id="ajx_keyword" value="${param.keyword}">
		
		
		<!-- 모바일 환경 이벤트 -->
		<form class="navbar-form" role="search" action="movieList.do" method="get" id="movieSearch">
			<div class="input-group input-group-lg" id="md-search">
				<!-- submit 이벤트에 keyfield값을 전달하기 위해 hidden값으로 추가했습니다. -->
				<input type="hidden" name="keyfield" value="all">
				<input type="text" name="keyword" id="movie-search-keyword" class="form-control" placeholder="작품 제목,배우,감독 검색">
				<span class="input-group-btn">
        			<button class="btn btn-default gbutton" type="button"><span id="search-icon" class="glyphicon glyphicon-search"></span></button>
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
      <form class="navbar-form" role="search" action="movieList.do" method="get" id="movieSearch2">
        <div id="search-field" class="input-group input-group-lg">
        	<!-- submit 이벤트에 keyfield값을 전달하기 위해 hidden값으로 추가했습니다. -->
        	<input type="hidden" name="keyfield" value="all">
      		<input type="text" name="keyword" id="movie-search-keyword2" class="form-control" placeholder="작품 제목,배우,감독 검색">
      		<span class="input-group-btn">
        		<button class="btn btn-default gbutton2" type="button"><span id="search-icon" class="glyphicon glyphicon-search"></span></button>
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
