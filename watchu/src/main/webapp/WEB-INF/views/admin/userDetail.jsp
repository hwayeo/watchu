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
				<input type="text" name="id" id="id" readonly></li>
				<li><label for="name">이름</label>
				<form:input path="name"/>
				<form:errors path="name" cssClass="error-color"/></li>
				<li></li>
				<hr size="1" noshade>
				<li></li>
			</ul>
			</td>
		</tr>
	</table>
	</form:form>
</div>