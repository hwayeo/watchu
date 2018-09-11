<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/success.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/success.js"></script>
</head>
<body>
	<div class="container">
		<div class="top_log">
			<h3>Login Success</h3>
		</div>

		<h2 style="text-align: center" id="name"></h2>
		<h4 style="text-align: center" id="email"></h4>
	</div>
</body>
</html>