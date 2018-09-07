<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ include file="menu.jsp" %> --%>
<!-- 영화 목록(관리자 메인) -->
<div class="admin_main">
	<div id="movie_list">
		<h2>영화 목록</h2>
		<br>
		<div class="content-header">
			<!-- 검색 -->
			<select name="category"> 
				<option value="movie_name">영화명</option>
				<option value="movie_director">감독명</option>
				<option value="movie_actor">배우명</option>
				<option value="movie_genre">장르</option>
			</select> <input type="text"> <input type="button" value="검색"><br>
			<Br>
		</div>

		<div class="content-body">
			<!-- 영화 목록 -->
			<table class="table table-hover table-condensed">
				<tr>
					<th class="col-md-2">번호</th>
					<th class="col-md-8">영화명</th>
					<th class="col-md-2">선택</th>
				</tr>
				<tr>
					<td>1</td>
					<td>제목</td>
					<td><input type="checkbox" name="checked"></td>
				</tr>
			</table>
			<br>

			<!-- 영화 등록 및 삭제버튼 -->
			<div class="edit_btn" align="right">
				<input type="button" value="영화 등록" id="register_movie"
					data-toggle="modal" data-target="#registerModal"> <input
					type="submit" value="선택 삭제" id="delete_movie">
			</div>
			<br>

			<!-- 페이지버튼 -->
			<nav align="center">
				<ul class="pagination pagination-sm paging">
					<li class="disabled"><a href="#" aria-label="Previous"> <spanaria-hidden="true">&laquo;</span></a></li>
					<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
				</ul>
			</nav>
			<br>
		</div>
	</div>
</div>


<!-- 영화등록 모달창 -->
<div class="modal fade" id="registerModal" tabindex="-1" role="dialog"
	aria-labelledby="registerModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="registerModalLabel">영화 등록</h4>
			</div>

			<div class="modal-body">
				<form id="register_form">
					<div class="form-group">
						<label for="genre1">장르1</label> <select name="main_category">
							<option value="crime">범죄</option>
							<option value="drama">드라마</option>
							<option value="SF">SF</option>
							<option value="romance">로맨스</option>
						</select> <label for="genre2">장르2</label> <select name="sub_category">
							<option value="action">액션</option>
							<option value="comedy">코미디</option>
							<option value="thriller">스릴러</option>
							<option value="adventure">모험</option>
						</select>
					</div>
					<div class="form-group">
						<label for="movie_name">영화명</label> <input type="text"
							name="movie_name" class="form-control"> <input
							type="button" value="중복체크">
					</div>
					<div class="form-group">
						<label for="movie_country">제작 국가</label> <input type="text"
							name="movie_country" class="form-control">
					</div>
					<div class="form-group">
						<label for="movie_year">개봉연도</label> <input type="number"
							name="movie_year" class="form-control">
					</div>
					<div class="form-group">
						<label for="movie_director">감독</label> <input type="text"
							name="movie_director" class="form-control">
					</div>
					<div class="form-group">
						<label for="movie_actor">주연배우</label> <input type="text"
							name="movie_actor" class="form-control">
					</div>
					<div class="form-group">
						<label for="movie_summary">줄거리</label> <input type="text"
							name="movie_summary" class="form-control">
					</div>
					<div class="form-group">
						<label for="movie_poster_img">포스터사진</label> <input type="file"
							name="movie_poster_img" id="movie_poster_img" />
					</div>
					<div class="form-group">
						<label for="movie_banner_img">배너사진</label> <input type="file"
							name="movie_banner_img" id="movie_banner_img" />
					</div>
				</form>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-primary">등록</button>
			</div>
		</div>
	</div>
</div>