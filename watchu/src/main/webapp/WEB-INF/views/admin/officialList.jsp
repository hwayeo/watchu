<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!-- 영화 관계자 등록 및 수정 -->
<div class="admin_main">
	<div id="official_list" style="width:90%">
		<h2>영화 관계자 목록</h2>
		<div class="content-header form-inline" align="right">
			<!-- 구분 -->
			<%-- <div class="division" style="padding-bottom:5px">
				<input type="radio" name="jobs" value="ALL" <c:if test="${param.jobs=='ALL'}">checked</c:if>> 전체
				<input type="radio" name="jobs" value="DIRECTOR" <c:if test="${param.jobs=='DIRECTOR'}">checked</c:if>> 감독
				<input type="radio" name="jobs" value="ACTOR" <c:if test="${param.jobs=='ACTOR'}">checked</c:if>> 배우
			</div> --%>
			<!-- 검색 -->
			<form action="officialList.do" class="confirm_search form-group" method="get" class="search">
			<select name="keyfield" class="form-control">
				<option value="name">이름</option>
				<option value="filmograp">작품명</option>
			</select>
			<input type="text" name="keyword" id="keyword" class="form-control" size="15"> 
			<input type="submit" value="검색" class="btn btn-default">
			</form>
		</div>
		

		<div class="content-body">
			<!-- 영화 목록 -->
			<table class="table table-hover table-condensed">
				<thead>
				<tr>
					<th class="col-md-1">관계자코드</th>
					<th class="col-md-5">이름</th>
					<th class="col-md-3">구분</th>
					<th class="col-md-1">선택</th>
				</tr>
				</thead>
				<c:forEach var="official" items="${official_list}">
				<tr>
					<td align="center">${official.off_num}</td>
					<td align="center" onclick="location.href='offcialDetail.do?off_num=${official.off_num}'" style="cursor:pointer;">${official.name}</td>
					<td align="center">${official.jobs}</td>
					<td align="center"><input type="checkbox" name="offChecked" value="${official.off_num}"/></td>
				</tr>
				</c:forEach>
			</table>
			<br>

			<!-- 영화 관계자 등록 및 삭제버튼 -->
			<div class="edit_btn" align="right">
				<input type="button" value="영화관계자등록" id="officials_movie"
					data-toggle="modal" data-target="#officialsModal" class="btn btn-primary">
				<input type="button" value="선택 삭제" id="check_offDel" class="btn btn-danger">
			</div>
			<!-- 페이지버튼 -->
			<div align="center">${pagingHtml}</div>
			<br>
		</div>
	</div>
</div>

<!-- 관계자 등록 모달 -->
<div class="modal fade" id="officialsModal" tabindex="-1" role="dialog" aria-labelledby="officialsModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form:form commandName="official_command" action="officialList.do" id="official_form" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="officialsModalLabel">영화 관계자 등록</h4>
			</div>
			<div class="modal-body form-inline">
				<div class="form-group">
					<span style="display:inline-block; width:100px">
					<label for="name">이름</label></span>
	         		<form:input path="name" class="form-control"/>
	         		<form:errors path="name" cssClass="error-color"/>
				</div><br><hr size="1" noshade>
				<div class="form-group">
					<span style="display:inline-block; width:100px">
					<label for="jobs">구분</label></span>
					<form:radiobutton path="jobs" value="ACTOR" label="　ACTOR"/>　
					<form:radiobutton path="jobs" value="DIRECTOR" label="　DIRECTOR"/>
	         		<form:errors path="jobs" cssClass="error-color"/>
				</div><br>
				<div class="form-group">
					<span style="display:inline-block; width:100px">
					<label for="filmograp">필모그래피</label></span>
	         		<form:textarea path="filmograp" cols="50" rows="3" class="form-control"/>
	         		<form:errors path="filmograp" cssClass="error-color"/>
				</div>
				<hr size="1" noshade>
				<div class="form-group form-inline">
					<span style="display:inline-block; width:100px">
					<label for="upload">사진</label></span>
	         		<input type="file" name="upload" id="upload"/>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				<button type="submit" class="btn btn-primary">등록</button> 
			</div>
			</form:form>
		</div>
	</div>
	</div>
</div>