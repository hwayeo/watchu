<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="detail" style="width:90%">
	<h2>회원 상세 정보/수정</h2>
	<form:form commandName="user_command" action="userDetail.do" id="modify_form">
	    <form:hidden path="id"/>
		<form:errors element="div" cssClass="error-color"/>
	<table>
		<tr>
			<td>
			<ul>
				<li><b>아이디 </b>
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
				<li><b>가입일 </b>${user.reg_date}</li>
				<hr size="1" noshade>
				<li><b>누적 좋아요 수</b>${user.total_likes}</li>
				<li><b>누적 신고 수</b>${user.total_likes}</li>				
				<li><label for="auth">회원 등급</label>
				<form:input path="auth"/>
				<form:errors path="auth" cssClass="error-color"/></li>
				<%-- <li><b>아이디 </b>
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
				<li><b>가입일 </b>${user.reg_date}</li>
				<hr size="1" noshade>
				<li><b>누적 좋아요 수</b>${user.total_likes}</li>
				<li><b>누적 신고 수</b>${user.total_likes}</li>				
				<li><label for="auth">회원 등급</label>
				<form:input path="auth"/>
				<form:errors path="auth" cssClass="error-color"/></li>	 --%>	
			</ul>
			</td>
		</tr>
	</table>
	<div class="edit_btn" align="right">
		<input type="button" onclick="location.href='userList.do'" value="목록" class="btn btn-default">
		<input type="submit" value="수정" class="confirm_mod btn btn-primary">
	</div>
	</form:form>
</div>