<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title><tiles:getAsString name="title"></tiles:getAsString></title>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/watchu-login.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="box">
			<h2>로그인</h2>
			<form:form commandName="command">
				<div class="input">
					<div class="inputbox">
						<input type="text" name="id" id="id" required>
						<form:errors path="id"/>
						<label class="text-center">아이디</label>
					</div>
					
					<div class="inputbox">
						<input type="password" name="passwd" id="passwd" required>
						<form:errors path="passwd"/>
						<label class="text-center">비밀번호</label>
					</div>
				</div>
				
				<div class="loginBtn">
					<input type="submit" value="로그인">
					<input type="button" value="취소" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
				</div>
				
				<div id="socialLogin">
					<a href="socialLogin.do">소셜 아이디로 로그인</a>
				</div>
				
			</form:form>
		</div>
	</div>
</body>
</html>






