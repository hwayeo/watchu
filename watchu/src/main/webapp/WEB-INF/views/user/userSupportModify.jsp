<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/support.js"></script>
<div id="main-content">
<div class="container">
<table class="table table-bordered">
    <thead>
        <caption> 글수정 </caption>
    </thead>
    <tbody>
        <form:form commandName="contactCommand" action="userSupportUpdate.do" id="register_form" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${user_id}">
		<form:hidden path="contact_num"/>
		<form:hidden path="filename"/>
		<form:errors element="div" cssClass="error-color"/>	
            <tr>
                <th>제목 </th>
                <td>
					<form:input path="title" cssClass="form-control"/>
				</td>
            </tr>
            <tr>
                <th>내용 </th>
				<form:errors path="content" cssClass="error-color"/>
                <td>
                	<form:input path="content" style="width:100%; height:250px;" name="content" class="form-control"/>
                </td>
            </tr>
            <tr>
                <th>첨부파일 </th>
                <td>
                	<input type="file" placeholder="파일을 선택하세요. " name="upload" id="upload"/>
	                <c:if test="${!empty contactCommand.filename}">
						<br>
						<span>(${contactCommand.filename})파일이 등록되어 있습니다.
						다시 업로드하면 기존 파일은 삭제됩니다.</span>
					</c:if>    
                </td>
            </tr>
            <tr>
                <td colspan="2" class="text-center">
                    <input type="submit" class="btn btn-default" value="수정">
                    <input type="button" class="btn btn-default" value="목록" onclick="location.href='userSupportList.do'"/>
                </td>
            </tr>
        </form:form>
    </tbody>
</table>
</div>
</div>