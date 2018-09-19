<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="detail" style="width:90%">
	<h2>신고 상세 정보</h2><br> 
	<div class="detail-content form-inline">
	<table style="width:100%">
	<tr>
		<td width="10%">
		<label>작성자</label>
		</td>
		<td width="90%">
		${report.id}
		</td>
	</tr>
	<tr>
		<td width="10%">
		<label>작성일</label>
		</td>
		<td width="90%">
		${report.reg_date}
		</td>
	</tr>
	<tr>
	</table>
	<hr size="1" noshade>
	<table style="width:100%">
	<tr>
		<td width="10%">
		<label>신고 아이디</label>
		</td>
		<td width="90%">
		<b>${report.report_user}</b> &nbsp; &nbsp;
		<a class="report_modify btn btn-sm btn-default" data-toggle="modal" data-target="#reportModify" data-whatever="${report.report_user}" href="reportModify.do?id=${report.report_user}">회원등급변경</a>
		<%-- <a href="#" onClick="window.open('userDetail.do?id=${report.report_user}','회원 등급 변경','width=950, height=700, toolbar=no, menubar=no, scrollbars=no, resizable=yes');return false;" class="btn btn-sm btn-default">회원등급변경</a> --%>   
		</td>
	</tr>
	<tr>
		<td width="10%">
		<label>신고 구분</label>
		</td>
		<td width="90%">
		${report.report_category}
		</td>
	</tr>
	<tr>
		<td width="10%">
		<label>제목</label>
		</td>
		<td width="90%">
		${report.report_title}
		</td>
	</tr>
	</table>
	<hr size="1" noshade>
	<table style="width:100%">
		<tr>
		<td width="10%">
		<label>내용</label>
		</td>
		<td width="90%">
		<p>
		${report.report_content}
		</p>
		</td>
	</tr>
	</table>
</div>
<br>
		<div class="edit_btn" align="right">
			<input type="button" id="processing" class="btn btn-md btn-primary" value="처리완료(삭제)" onclick="location.href='reportDelete.do?num=${report.report_num}'">
			<input type="button" class="btn btn-default" value="목록" onclick="location.href='reportedUser.do'">
		</div>
	</div>

<!-- 회원 등급 수정 모달창 -->
<div class="modal fade" id="reportModify" tabindex="-1" role="dialog" aria-labelledby="reportModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content form-inline">
      <form:form commandName="reportCommand" action="reportModify.do" id="modify_form">
      <form:hidden path="id" id="report_user"/>
      <form:errors element="div" cssClass="error-color"/>
        <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title" id="reportModalLabel">회원 등급 변경</h4>
        </div>
        <div class="modal-body">
        	<div class="form-group">
        		<span style="display:inline-block; width:150px">
        		<label>신고받은 회원 ID</label></span>
        		<span id ="show-id"></span>
        	</div><br>
        	<div class="form-group">
        		<span style="display:inline-block; width:150px">
        		<label for="auth">회원 등급</label></span>
        		<form:input path="auth" class="form-control"/>
        		<form:errors path="auth" cssClass="error-color"/>
         	</div>
        </div>
        
        <div class="modal-footer">
	        <div class="edit_btn">
	       		<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				<input type="submit" value="수정" class="confirm_mod btn btn-primary">
			</div>
        </div>     
		</form:form>
		</div>
	</div>
</div>