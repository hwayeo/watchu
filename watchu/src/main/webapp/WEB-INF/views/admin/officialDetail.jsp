<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="detail" style="width:90%">
	<h2>관계자 상세 정보/수정</h2> 
	<div class="detail_content form-inline">
	<form:form commandName="official_command" action="offcialDetail.do" id="modify_form" enctype="multipart/form-data">
	    <form:hidden path="off_num"/>
		<form:errors element="div" cssClass="error-color"/>
	<table  style="width:100%">
		<tr>
			<td class="col-md-2">
				<div class="off_photo" align="center">
				<c:if test="${empty officials.off_photo}">
				<img src="${pageContext.request.contextPath}/resources/images/no_img.png">
				</c:if>
				<c:if test="${!empty officials.off_photo}">
					<img src="off_imgView.do?off_num=${officials.off_num}" style="max-width: 200px">
				</c:if>
				</div>
			</td>
			<td>
				<ul class="list">
					<li><b>관계자 코드　</b> ${officials.off_num}</li>
					<li>
					<label for="jobs">구분　　</label>
					<form:radiobutton path="jobs" value="ACTOR" label="　ACTOR"/>　
					<form:radiobutton path="jobs" value="DIRECTOR" label="　DIRECTOR"/>
					<%-- <form:input path="jobs" class="form-control"/>
					<form:errors path="jobs" cssClass="error-color"/> --%>
					</li>
					<li>   
					<label for="name">이름　　</label>
					<form:input path="name" class="form-control"/>
					<form:errors path="name" cssClass="error-color"/>
					</li>
					<hr size="1" noshade>
					<li>
					<label for="filmograp">필모그래피</label>
					<Br>
					<form:textarea path="filmograp" cols="100" rows="3" class="form-control"/>
					<form:errors path="filmograp" cssClass="error-color"/>
					</li>
					<li>
					<label for="upload">사진</label>
				    <input type="file" name="upload" id="upload"/>
				    <span>※ 새 파일 업로드 시 기존 파일은 삭제됩니다 ※</span>
					</li>
				</ul>
			</td>
		</tr>
	</table>
	</div>
	<br>
	<div class="edit_btn" align="right">
		<a href="location.href='officialDelete.do?off_num=${officials.off_num}'" class="btn btn-danger confirm_del">삭제</a>
		<input type="button" onclick="location.href='officialList.do'" value="목록" class="btn btn-default">
		<input type="submit" value="수정" class="confirm_mod btn btn-primary">
	</div>
	</form:form>
</div>