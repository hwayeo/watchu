<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/support.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/support.reply.js"></script>
<div id="main-content"> 
<div class="container">
   <h2 class="title">고객센터</h2>
   <p class="subTitle">Watchu의 고객센터입니다.</p><br>
   <input type="hidden" name="contact_num" value="${contact.contact_num}" id="contact_num">
	<table style="width:100%">
		<tr>
			<td style="width:20%"  class="text-center">
			작성자
			</td>
			<td style="width:80%">
			${contact.id}
			</td>
		</tr>
		<tr>
			<td style="width:20%"  class="text-center">
			작성일
			</td>
			<td style="width:80%">
			${contact.reg_date}
			</td>
		</tr>
	</table>
	<table style="width:100%">
	<hr size="1" noshade>
		<tr>
			<td style="width:20%"  class="text-center">
			구분
			</td>
			<td style="width:80%">
			${contact.category}
			</td>
		</tr>
		<tr>
			<td style="width:20%"  class="text-center">
			제목
			</td>
			<td style="width:80%">
			${contact.title}
			</td>
		</tr>
	</table>
	<table style="width:100%">
	<hr size="1" noshade>
		<tr>
			<td style="width:20%"  class="text-center">
			내용
			</td>
			<td style="width:80%">
			<c:if test="${fn:endsWith(contact.filename,'.jpg') || 
			              fn:endsWith(contact.filename,'.JPG') ||
			              fn:endsWith(contact.filename,'.gif') ||
			              fn:endsWith(contact.filename,'.GIF') ||
			              fn:endsWith(contact.filename,'.png') ||
			              fn:endsWith(contact.filename,'.PNG')}">
			<img src="imageView.do?contact_num=${contact.contact_num}" style="width:300px">
			</c:if>
			<p><br>
				${contact.content}
			</p>
			</td>
		</tr>
	</table>
	<table style="width:100%">
	<hr size="1" noshade>
		<tr>
			<td style="width:20%" class="text-center">
			첨부파일
			</td>
			<td style="width:80%">
			<c:if test="${!empty contact.filename}">
				<a href="file.do?num=${contact.contact_num}">${contact.filename}</a>
			</c:if>
			</td>
		</tr>
	</table>
	
	<hr size="1" width="100%">
	<div class="align-right text-right">
		<c:if test="${!empty user_id && user_id == contact.id}">
			<input type="button" class="btn btn-primary" value="수정" onclick="location.href='userSupportUpdate.do?contact_num=${contact.contact_num}'">	
			<input type="button" class="btn btn-default" value="목록" onclick="location.href='userSupportList.do'"/>
			<input type="button" class="btn btn-danger" value="삭제"  onclick="location.href='userSupportDelete.do?contact_num=${contact.contact_num}'">	  
		</c:if>
		<c:if test="${!empty user_id && user_id == 'admin'}">
			<input type="button" class="btn btn-danger" value="삭제"  onclick="location.href='userSupportDelete.do?contact_num=${contact.contact_num}'">
		</c:if>
		<c:if test="${!empty user_id && user_id != contact.id}">
			<input type="button" class="btn btn-default" value="목록" onclick="location.href='userSupportList.do'"/>
		</c:if> 
	</div>
	 
	<!-- 답변 영역 -->
	<div id="reply_div" style="background-color:#f8f8f8;">
	<c:if test="${!empty user_id && user_id == 'admin'}">
	<hr size="1" width="100%">
		<div class="reply_content" style="margin:20px auto;width:80%;">
		<span class="reply-title""><b>답변 등록</b></span>
		<form id="re_form" class="form-group">        
			
			<textarea rows="5" cols="50" style="margin-bottom:10px;margin-top:10px" name="recontent" id="recontent" placeholder="답변내용을 입력하세요." class="rep-content form-control"<c:if test="${empty user_id}">disabled="disabled"</c:if>><c:if
					test="${empty user_id}">로그인해야 작성할 수 있습니다.</c:if></textarea> 
			<c:if test="${!empty user_id}">
			<div id="re_btn" align="right" style="padding-bottom:20px;">
			<input type="submit" value="답변 등록" class="btn btn-primary btn-sm" class="btnReply" >
			</div>
			</c:if>
		</form>
		</div>
	</c:if> 
	</div>
	
	<!-- 답변 출력 -->
	<div id="output"></div>
	<div class="paging-button" style="display:none;">
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
	</div>
</div>
</div>
</div>