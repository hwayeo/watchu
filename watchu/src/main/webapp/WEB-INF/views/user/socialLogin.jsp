<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SocialLogin</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/SocialLogin.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>
	<div class="top_log">
		<h3>Watchu Social Login</h3>
	</div>
	
	<div class="container">
		<h2>소셜 아이디로 로그인하기</h2>
		
		<!-- 네이버 로그인 화면으로 이동 시키는 URL -->
		<!-- 네이버 로그인 화면에서 ID, PW를 올바르게 입력하면 callback 메소드 실행 요청 -->
		<div id="naver_id_login" style="text-align:center">
			<a href="${url}"><img width="223" src="${pageContext.request.contextPath}/resources/images/loginIcon.PNG"/></a>
		</div>
	</div>
</body>
</html>