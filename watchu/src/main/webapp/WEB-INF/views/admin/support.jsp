<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 고객지원 -->
<div class="admin_main">
	<div id="support">
		<h2>고객 문의 관리</h2>
		<br>
		<div class="content-header">
			<!-- 처리여부 -->
			<input type="radio" name="status" value="all" checked> 전체 <input
				type="radio" name="status" value="incomplete"> 미처리<br>
			<Br>
		</div> 

		<div class="content-body">
			<!-- 고객 문의 내역 -->
			<table class="table table-hover table-condensed">
				<tr>
					<th class="col-md-4">
						<div class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">카테고리<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">영화 등록 및 수정 요청</a></li>
								<li><a href="#">이용 관련 문의</a></li>
								<li><a href="#">회원 신고</a></li>
								<li><a href="#">기타</a></li>
							</ul>
						</div>
					</th>
					<th class="col-md-8">글 제목</th>
				</tr>
				<tr>
					<td>개인정보</td>
					<td>비밀번호 변경 <br>홍길동 | 2018-08-21
					</td>
				</tr>
			</table>
			<br>

			<!-- 페이지버튼 -->
			<nav align="center">
				<ul class="pagination pagination-sm">
					<li class="disabled"><a href="#" aria-label="Previous"><span
							aria-hidden="true">&laquo;</span></a></li>
					<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
				</ul>
			</nav>
			<br>
		</div>
	</div>
</div>