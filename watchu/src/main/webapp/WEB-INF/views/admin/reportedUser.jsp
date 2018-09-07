<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div class="admin-main">
	<div class="tab-pane" id="reported_user">
		<h2>회원 신고 내역</h2>
		<br>
		<div class="content-header">
			<!-- 처리여부 -->
			<input type="radio" name="status" value="all" checked> 전체 <input
				type="radio" name="status" value="incomplete"> 미처리<br>
			<Br> 
			<!-- 검색 -->
			<!-- <form action="user.do" id="user_search" method="get" class="search">
			<select name="keyfield">
				<option value="name">ID</option>
				<option value="id">신고내용</option>
			</select>
			<input type="text" name="keyword" id="keyword"> 
			<input type="submit" value="검색">
			</form> -->
		</div>

		<div class="content-body">
			<!-- 회원 신고 내역 -->
			<table class="table table-hover table-condensed">
				<tr>
					<th class="col-md-2">번호</th>
					<th class="col-md-6">신고내용</th>
					<th class="">작성자</th>
					<th class="">처리상태</th>
				</tr>
				<c:forEach var="report" items="${list}">
				<tr>
					<td onclick="location.href='reportDetail.do?num=${report.report_num}'" style="cursor:pointer;">${report.report_num}</td>
					<td>${report.report_content}</td>
					<td>${report.id}</td>
					<td>완료/미완료</td>
				</tr>
				</c:forEach>
			</table>
			<br>

			<!-- 페이지버튼 -->
			<div align="center">${pagingHtml}</div>
		
			<br>
		</div>
	</div>
</div>