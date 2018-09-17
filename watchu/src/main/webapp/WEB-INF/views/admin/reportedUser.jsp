<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div class="admin-main" style="width:90%">
	<div class="tab-pane" id="reported_user">
		<h2>회원 신고 내역</h2>
		<br> 
		<div class="content-header">
			<!-- 처리여부
			<input type="radio" name="status" value="all" checked> 전체 <input
				type="radio" name="status" value="incomplete"> 미처리<br>
			<Br> 
			검색
			<form action="user.do" id="user_search" method="get" class="search">
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
				<thead>
				<tr>
					<th class="col-md-1">번호</th>
					<th class="col-md-1">구분</th>
					<th class="col-md-2">신고ID</th>
					<th class="col-md-4">제목</th>
					<th class="col-md-2">작성자</th>
					<th class="col-md-1">작성일</th>
				</tr>
				</thead>
				<c:forEach var="report" items="${list}">
				<tr>
					<td align="center">${report.report_num}</td>
					<td align="center">${report.report_category}</td>
					<td align="center" onclick="location.href='reportDetail.do?num=${report.report_num}'" style="cursor:pointer;">${report.report_user}</td>
					<td align="center" onclick="location.href='reportDetail.do?num=${report.report_num}'" style="cursor:pointer;">${report.report_title}</td>
					<td align="center" >${report.id}</td>
					<td align="center" >${report.reg_date}</td>
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