<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/support.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/support.js"></script>
<div id="main-content">
<div class="container">
   <h2 class="title">고객센터</h2>
   <p class="subTitle">Watchu의 고객센터입니다.</p>
<div class="container">
<table class="table table-bordered">
    <thead>
        <caption><h4>문의글 작성</h4></caption>
    </thead>
    <tbody>
        <form:form commandName="contactCommand" action="userSupportWrite.do" id="register_form" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${user_id}">
		<form:errors element="div" cssClass="error-color"/>	
			<tr>
				<th>구분</th>
				<td>
				<form:select path="category" class="form-control">
					<form:option value="" disabled="true" selected="true">-------- 구분을 선택하세요 --------</form:option>
					<form:option value="app이용">app이용</form:option>
					<form:option value="PC이용">PC이용</form:option>
					<form:option value="개인정보">개인정보</form:option>
					<form:option value="영화등록요청">영화등록요청</form:option>
					<form:option value="기타">기타</form:option>
				</form:select>
				</td>
			</tr>
            <tr>
                <th>제목 </th>
                <td><input type="text" placeholder="제목을 입력하세요. " name="title" class="form-control"/></td>
            </tr>
            <tr>
                <th>내용 </th>
				<form:errors path="content" cssClass="error-color"/>
                <td><textarea cols="10" rows="15" placeholder="내용을 입력하세요. " name="content" class="form-control"></textarea></td>
            </tr>
            <tr>
                <th>첨부파일 </th>
                <td><input type="file" placeholder="파일을 선택하세요. " name="upload" id="upload"/></td>
            </tr>
            <tr>
                <td colspan="2" class="text-center">
                    <input type="submit" class="btn btn-primary" value="등록">
                    <input type="button" class="btn btn-default" value="목록" onclick="location.href='userSupportList.do'"/>
                </td>
            </tr>
        </form:form>
    </tbody>
</table>
</div>
</div></div>