<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 영화 목록(관리자 메인) -->
<div class="admin_main">
	<div id="movie_list" style="width:90%">
		<h2>영화 목록</h2>
		<div class="content-header" align="right">
			<!-- 검색 -->
			<form action="admin_movieList.do" class="confirm_search" method="get">
			<select name="keyfield">
				<option value="title">영화명</option>
				<option value="director">감독명</option>
				<option value="actors">배우명</option>
			</select>
			<input type="text" name="keyword" id="keyword"> 
			<input type="submit" value="검색">
			</form>
		</div>

		<div class="content-body">
			<!-- 영화 목록 -->
			<table class="table table-hover table-condensed">
				<thead>
				<tr>
					<th class="col-md-2">영화 코드</th>
					<th class="col-md-8">영화명</th>
					<th class="col-md-2">선택</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="movie" items="${movie_list}">
				<tr>
					<td align="center">${movie.movie_num}</td>
					<td onclick="location.href='admin_movieDetail.do?movie_num=${movie.movie_num}'" style="cursor:pointer;">${movie.title}</td>
					<td align="center"><input type="checkbox" name="movieChecked" value="${movie.movie_num}"/></td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			<br>

			<!-- 영화 등록 및 삭제버튼 -->
			<div class="edit_btn" align="right">
				<input type="button" value="영화 등록" onclick="location.href='admin_movieWrite.do'" class="btn btn-primary">
				<input type="button" value="선택 삭제" id="check_movieDel" class="btn btn-danger">
			</div>

			<!-- 페이지버튼 -->
			<div align="center">${pagingHtml}</div>
			<br>
		</div>
	</div>
</div>
