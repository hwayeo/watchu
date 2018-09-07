<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!-- 영화 관계자 등록 및 수정 -->
<div class="admin_main">
	<div id="official_list">
		<h2>영화 관계자 목록</h2>
		<br>
		<div class="content-header">
			<!-- 구분 -->
			<span class="division">
				<input type="radio" name="jobs" value="all" checked> 전체
				<input type="radio" name="jobs" value="director"> 감독
				<input type="radio" name="jobs" value="actor"> 배우
			</span>
			
			<!-- 검색 -->
			<form action="officialList.do" id="official_search" method="get" class="search">
			<select name="keyfield">
				<option value="name">이름</option>
				<option value="filmograp">작품명</option>
			</select>
			<input type="text" name="keyword" id="keyword"> 
			<input type="submit" value="검색">
			</form>
		</div>

		<div class="content-body">
			<!-- 영화 목록 -->
			<table class="table table-hover table-condensed">
				<tr>
					<th class="col-md-2">관계자코드</th>
					<th class="col-md-5">이름</th>
					<th class="col-md-2">구분</th>
					<th class="col-md-1">선택</th>
				</tr>
				<c:forEach var="official" items="${official_list}">
				<tr>
					<td>${official.off_num}</td>
					<td onclick="location.href='offcialDetail.do?off_num=${official.off_num}'" style="cursor:pointer;">${official.name}</td>
					<td>${official.jobs}</td>
					<td><input type="checkbox" name="checked"></td>
				</tr>
				</c:forEach>
			</table>
			<br>

			<!-- 영화 관계자 등록 및 삭제버튼 -->
			<div class="edit_btn" align="right">
				<input type="button" value="영화관계자등록" id="officials_movie"
					data-toggle="modal" data-target="#officialsModal">
				<!-- <input type="button" value="영화 등록" id="register_movie" data-toggle="modal" data-target="#registerModal"> -->
				<input type="submit" value="선택 삭제" id="delete_movie">
			</div>
			<br>

			<!-- 페이지버튼 -->
			<div align="center">${pagingHtml}</div>
			<!-- <nav align="center">
				<ul class="pagination pagination-sm">
					<li class="disabled"><a href="#" aria-label="Previous"><span
							aria-hidden="true">&laquo;</span></a></li>
					<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
				</ul>
			</nav> -->
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
			<div class="modal-body">
				<div class="form-group">
					<label for="name">이름</label>
	         		<form:input path="name"/>
	         		<form:errors path="name" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="jobs">구분</label>
	         		<form:input path="jobs"/>
	         		<form:errors path="jobs" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="filmograp">필모그래피</label>
	         		<form:input path="filmograp"/>
	         		<form:errors path="filmograp" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="upload">사진</label>
	         		<input type="file" name="upload" id="upload"/>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				<button type="submit" class="btn btn-default">등록</button>
			</div>
			</form:form>
		</div>
	</div>
</div>