<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 인증</title>
</head>
<body>
	<div class="container">
		<h2>Watchu<br>회원가입을 환영합니다.</h2>
		
		<div class="mail">
			<p>안녕하세요. <span>${use_id}</span> 회원님</p>
			<p>Watchu 회원가입을 환영합니다. 아래의 인증코드를 복사하셔서 회원가입의 인증코드 입력란에 넣어주시면 Watchu 가입이 완료됩니다.</p>
			
			<br><br><br><br><br>
			<p>${sb}</p>
		</div>
	</div>
</body>
</html>