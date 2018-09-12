<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="detail">
	<h2>회원 상세 정보/수정</h2>
	<form:form commandName="user_command" action="userDetail.do" id="modify_form">
	    <form:hidden path="id"/>
		<form:errors element="div" cssClass="error-color"/>
	<table>
		<tr>
			<td>
			<ul>
				<li><label for="id">아이디</label>
				${user.id}
				<li><label for="name">이름</label>
				<form:input path="name"/>
				<form:errors path="name" cssClass="error-color"/></li>
				<li><label for="phone">연락처</label>
				<form:input path="phone"/>
				<form:errors path="phone" cssClass="error-color"/></li>
				<li><label for="email">이메일</label>
				<form:input path="email"/>
				<form:errors path="email" cssClass="error-color"/></li>
				<li><label for="reg_date">가입일</label>${user.reg_date}</li>
				<hr size="1" noshade>
				<li><label for="total_likes">누적 좋아요 수</label>${user.total_likes}</li>
				<li><label for="total_reported">누적 신고 수</label>${user.total_likes}</li>				
				<li><label for="auth">누적 신고 수</label>${user.total_likes}</li>				
			</ul>
			</td>
		</tr>
	</table>
	</form:form>
</div>