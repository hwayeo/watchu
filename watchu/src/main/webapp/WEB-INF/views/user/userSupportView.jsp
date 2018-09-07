<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/support.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/contact.reply.js"></script>
<div id="main-content">
<div class="container">
   <h2 class="title">고객센터</h2>
   <p class="subTitle">Watchu의 고객센터입니다.</p>
<div class="page-main-style container">
	<table class="Viewtable">
		<tr>
			<th>제목</th>
			<td>${contact.title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${contact.id}</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${contact.reg_date}</td>
		</tr>
		<tr>	
			<th>첨부파일</th>
			<td>
				<c:if test="${!empty contact.filename}">
					<a href="file.do?num=${contact.contact_num}">${contact.filename}</a>
				</c:if>
			</td>
		</tr>
	</table>
	<hr size="1" width="100%">
	
	<table class="Viewtable">
		<th>내용</th>
		<td width="300px">
			<c:if test="${fn:endsWith(contact.filename,'.jpg') || 
			              fn:endsWith(contact.filename,'.JPG') ||
			              fn:endsWith(contact.filename,'.gif') ||
			              fn:endsWith(contact.filename,'.GIF') ||
			              fn:endsWith(contact.filename,'.png') ||
			              fn:endsWith(contact.filename,'.PNG')}">
			<img src="imageView.do?contact_num=${contact.contact_num}">
			</c:if>
			<p><br>
				${contact.content}
			</p>
		</td>
	</table>		
	<hr size="1" width="100%">
	<div class="align-right text-center">
		<c:if test="${!empty user_id && user_id == contact.id}">
			<input type="button" class="btn btn-default" value="수정" onclick="location.href='userSupportUpdate.do?contact_num=${contact.contact_num}'">	
			<input type="button" class="btn btn-default" value="삭제"  onclick="location.href='userSupportDelete.do?contact_num=${contact.contact_num}'">	  
			<input type="button" class="btn btn-default" value="목록" onclick="location.href='userSupportList.do'"/>
		</c:if>
	</div>
</div>
</div>
</div>
