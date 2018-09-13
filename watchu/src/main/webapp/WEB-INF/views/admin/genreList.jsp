<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!-- 장르 등록 및 수정 -->
<div class="admin_main">
	<div id="genre_list" style="width:90%">
		<h2>영화 장르 목록</h2>
		<div class="content-header" align="right">
			<!-- 검색 -->
			<form action="genreList.do" class="confirm_search" method="get">
			<select name="keyfield">
				<option value="genre_num">장르 코드</option>
				<option value="genre">장르명</option>
			</select>
			<input type="text" name="keyword" id="keyword"> 
			<input type="submit" value="검색">
			</form>
		</div>

		<div class="content-body">
			<!-- 장르 목록 -->
			<table class="table table-hover table-condensed" >
				<thead>
				<tr>
					<th class="col-md-2">장르 코드</th>
					<th class="col-md-8">장르명</th>
					<th class="col-md-2">선택</th>
				</tr>
				</thead>
				<c:forEach var="genre" items="${genre_list}">
				<tr>
					<td align="center">${genre.genre_num}</td>
					<td align="center" class="modify_btn" style="cursor:pointer;" data-toggle="modal" data-target="#genreModify" data-whatever="${genre.genre_num}">${genre.genre}</td>
					<td align="center"><input type="checkbox" name="genreChecked" value="${genre.genre_num}"/></td>
				</tr>
				</c:forEach>
			</table>
			<br>

			<!-- 장르 등록 및 삭제버튼 -->
			<div class="edit_btn" align="right">
				<input type="button" value="장르 등록" id="register_genre" data-toggle="modal" data-target="#genreModal" class="btn btn-primary">
				<input type="button" value="선택 삭제" id="check_genreDel" class="btn btn-danger">
			</div>

			<!-- 페이지버튼 -->
			<div align="center">${pagingHtml}</div>
			<br>
		</div>
	</div>
</div>

<!-- 장르 등록 모달창 -->
<div class="modal fade" id="genreModal" tabindex="-1" role="dialog" aria-labelledby="genreModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
		<form:form commandName="genre_command" action="genreList.do" id="genre_form">
        <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title" id="genreModalLabel">장르 등록</h4>
        </div>
        <div class="modal-body">
        
         	<div class="form-group">
         		<span style="display:inline-block; width:100px">
         		<label for="genre">장르명</label></span>
         		<form:input path="genre"/>
         		<form:errors path="genre" cssClass="error-color"/>
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


<!-- 장르 수정 모달창 -->
<div class="modal fade" id="genreModify" tabindex="-1" role="dialog" aria-labelledby="genreModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
		<form:form commandName="genre_command" action="genreDetail.do" id="modify_form">
		<input type="hidden" name="genre_num" id="genre_num">
		<form:errors element="div" cssClass="error-color"/>
        <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title" id="genreModalLabel">장르 상세/수정</h4>
        </div>
        <div class="modal-body">
        	<input type="hidden" class="form-control" id="genre_num">
        	<div class="form-group">
        	<span style="display:inline-block; width:100px">
        	<label for="genre_num">장르 코드</label></span>
        	<span id="show-num"></span>
        	</div>
        	<div class="form-group">
        		<span style="display:inline-block; width:100px">
         		<label for="genre">장르명</label></span>
         		<form:input path="genre" id="name"/>
         		<form:errors path="genre" cssClass="error-color"/>
         	</div>
        </div>
         	
        <div class="modal-footer">
        <div class="edit_btn">
			<a href="location.href='genreDelete.do?genre_num='" class="btn btn-danger confirm_del">삭제</a>
			<input type="button" onclick="location.href='genreList.do'" value="목록" class="btn btn-default">
			<input type="submit" value="수정" class="confirm_mod btn btn-primary">
		</div>
        </div>
        </form:form>
   </div>
   </div>
</div>