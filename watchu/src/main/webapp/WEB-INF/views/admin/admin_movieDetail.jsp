<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="detail" style="width:90%">
	<h2>영화 상세 정보/수정</h2>    
	<div class="detail_content form-inline">
	<form:form commandName="movie_command" action="admin_movieDetail.do" id="modify_form" enctype="multipart/form-data" class="form-horizontal">
	    <form:hidden path="movie_num"/>
		<form:errors element="div" cssClass="error-color"/>
	<table style="width:100%">
		<tr>
			<td style="width:200px" align="center"> 
			<div class="poster_img">
        	<c:if test="${!empty movie.poster_img}">
        		<img src="image_View.do?movie_num=${movie.movie_num}&type=poster" width="200px">
        	</c:if>
        	<c:if test="${empty movie.poster_img}">
        	등록된 포스터 없음
        	</c:if>
        	</div>
        	</td>    
        	
        	<td>
			<ul class="list">
				<li><b>영화 코드　</b>${movie.movie_num}　|　<b>등록일　</b>${movie.reg_date}</li>
				<li><h3><label for="title">영화명　</label>
					<form:input path="title" class="form-control" size="30"/>
					<form:errors path="title" cssClass="error-color" /> </h3></li>
				<li><label for="released">개봉일자　</label>
					<form:input path="released" class="form-control"/>
					<form:errors path="released" cssClass="error-color" />　|　
        			<label for="country">제작국가　</label>
        			<form:input path="country" class="form-control"/>
        			<form:errors path="country" cssClass="error-color" /></li>
				<li><label for="main_genre">메인장르　</label>
        			<form:input path="main_genre" class="auto_genre form-control"/>
        			<form:errors path="main_genre" cssClass="error-color" />　|　
        			<label for="sub_genre">서브장르　</label>
        			<form:input path="sub_genre" class="auto_genre form-control"/>
        			<form:errors path="sub_genre" cssClass="error-color" /></li>
        		<li><label for="trailer">예고편 코드　</label>
					<form:input path="trailer" class="form-control"/>
					<form:errors path="trailer" cssClass="error-color" /></li>
				<li><label for="director">감독　</label>
					<input id="director" name="director" value="${movie_command.director}" size="30" class="input_director form-control" readonly="readonly"/>　|　수정입력　<input class="auto_director form-control" type="text"/><br>
					<span>※ 수정 시 기존 데이터는 삭제됩니다 ※</span>
			
			</ul>
        	</td>
		</tr>
		<tr>
			<td colspan="2">
			<hr size="1" noshade>
			<ul class="list">
				<li>
					<label for="actors">출연배우</label>
					<div class="actors">
					<c:if test="${!empty movie.actors}">
					<input type="text" id="actors" name="actors" value="${movie_command.actors}" size="70" class="input_actor form-control" readonly="readonly"/>
				<li>수정 입력　<input class="auto_actor form-control" type="text"/>
					<span>　※ 수정 시 기존 데이터는 삭제됩니다 ※</span></li>
					</c:if>
					<c:if test="${movie.actors == null}">
					등록된 출연배우 없음
					<input type="text" id="actors" name="actors" value="${movie_command.actors}" size="70" class="input_actor form-control" readonly="readonly"/>
					<br>배우 입력: <input class="auto_actor form-control" type="text"/>
					</c:if>
					</div>
				</li>
				<hr size="1" noshade>
				<li>
					<label for="summary">줄거리</label>
					<div class="summary">
					<c:if test="${!empty movie.summary}">
					<form:textarea path="summary" cols="150" rows="8" class="form-control"/>
					<form:errors path="summary" cssClass="error-color" />
					</c:if>
					<c:if test="${movie.summary == null}">
					등록된 줄거리 없음<br>
					　<div class="form-group" style="padding-top:10px">
					<form:textarea path="summary" cols="150" rows="8" class="form-control"/>
					<form:errors path="summary" cssClass="error-color" />
					</div>
					</c:if>
					</div>
				</li>
				<hr size="1" noshade>
				<li>
				<label for="banner_img">배너사진</label>
				<div class="banner_img">
				<c:if test="${!empty movie.banner_img}">
    			<img src="image_View.do?movie_num=${movie.movie_num}&type=banner" width="500px">
    			</c:if>
				<c:if test="${empty movie.banner_img}">
				등록된 배너 이미지 없음
				</c:if>
				</div>
				</li>
				<hr size="1" noshade>
				<li>
					<div class="form-group">
					<label for="uploadPoster">포스터 사진</label>
	         		<input type="file" name="uploadPoster" id="uploadPoster" />
					</div></li>
				<li>
					<div class="form-group">
					<label for="uploadBanner">배너 사진</label>
	         		<input type="file" name="uploadBanner" id="uploadBanner"/>
					</div>
					<span>※ 새 파일 업로드 시 기존 파일은 삭제됩니다 ※</span>
				</li>
			</ul>
			</td>
		</tr>
	</table>	
	</div>
	<div class="edit_btn" align="right">
		<a href="location.href='admin_movieDelete.do?movie_num=${movie.movie_num}'" class="btn btn-danger confirm_del">삭제</a>
		<input type="button" onclick="location.href='admin_movieList.do'" value="목록" class="btn btn-default">
		<input type="submit" value="수정" class="confirm_mod btn btn-primary">
	</div>
	</form:form>
	</div>
</div> 