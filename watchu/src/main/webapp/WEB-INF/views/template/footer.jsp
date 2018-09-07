<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="footer">
	<div class="container hidden-xs">
		<div id="footer-menu">
			<p id="rated">지금까지 <span id="ratedCnt"><span class="glyphicon glyphicon-star"></span> 000,000,000개의 평가</span>가 쌓였어요.</p>
			<ul id="footer-list">
				<li><a href="#">서비스 이용약관</a></li>
				<li><a href="#">개인정보 취급 방침</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/login.do">관리자</a></li>
			</ul>
			<div>
				<p>&copy;2018 by Watchu. Inc</p>
			</div>
		</div>
	</div>
</div>
	<!-- 모바일 환경의 푸터 -->
<div class="container visible-xs navbar-fixed-bottom">
		<div id="xs-footer" class="row">
			<div class="col-xs-3 text-center items-area">
				<a href="${pageContext.request.contextPath}/main/main.do" class="footer-item">
				<span class="glyphicon glyphicon-home"></span><br><span class="item-font">홈</span></a>
			</div>		
			<div class="col-xs-3 text-center items-area">
				<a href="${pageContext.request.contextPath}/movie/movieHome.do" class="footer-item">
				<span class="glyphicon glyphicon-th-list"></span><br><span class="item-font">영화</span></a>
			</div>
		<c:if test="${!empty user_id}">
			<div class="col-xs-3 text-center items-area">
				<a href="${pageContext.request.contextPath}/user/userTimeline.do" class="footer-item">
				<span class="glyphicon glyphicon-comment"></span><br><span class="item-font">소식</span></a>
			</div>		
			<div class="col-xs-3 text-center items-area">
				<a href="${pageContext.request.contextPath}/user/userMypage.do" class="footer-item">
				<span class="glyphicon glyphicon-user"></span><br><span class="item-font">내정보</span></a>
			</div>		
		</c:if>
		<c:if test="${empty user_id}">
			<div class="col-xs-3 text-center items-area">
				<a href="${pageContext.request.contextPath}/user/write.do" class="footer-item">
				<span class="glyphicon glyphicon-pencil"></span><br><span class="item-font">가입</span></a>
			</div>		
			<div class="col-xs-3 text-center items-area">
				<a href="${pageContext.request.contextPath}/user/login.do" class="footer-item">
				<span class="glyphicon glyphicon-user"></span><br><span class="item-font">로그인</span></a>
			</div>		
		</c:if>			
		</div>
</div>