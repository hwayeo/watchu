<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 영화등록 -->
<div class="register_movie">
	<h2>영화 등록</h2>
	<form:form commandName="movie_command" action="admin_movieWrite.do" id="movie_form" enctype="multipart/form-data">
	<form:errors element="div" cssClass="error-color"/>
	
	<ul>
		<li>
			<label for="title">영화명</label>
			<form:input path="title" />
			<form:errors path="title" cssClass="error-color" />
		</li>
		<li>
			<label for="country">제작 국가</label>
			<form:input path="country" />
			<form:errors path="country" cssClass="error-color" />
		</li>
		<li>
			<label for="released">개봉연도</label>
			<form:input path="released" placeholder="YYYY-MM-DD"/>
			<form:errors path="released" cssClass="error-color" />
		</li>
		<li>
			<label for="main_genre">메인 장르</label>
			<form:input path="main_genre" class="auto_genre"/>
			<form:errors path="main_genre" cssClass="error-color" />
		</li>
		<li>
			<label for="sub_genre">서브 장르</label>
			<form:input path="sub_genre" class="auto_genre" />
			<form:errors path="sub_genre" cssClass="error-color" />
		</li>
		<li>
			<label for="trailer">예고편 코드</label>
			<form:input path="trailer" />
			<form:errors path="trailer" cssClass="error-color" />
		</li>
		<hr size="1" noshade>
		<li>
			<label for="director">감독</label>
			<input class="auto_director" type="text" name="director" >
			<form:input path="director" class="input_director"/>
			<form:errors path="director" cssClass="error-color" />
			<%-- <form:input path="director" class="auto_director"/> --%>
		</li>
		<li>
			<label for="actors">배우</label>
			<input class="auto_actor" type="text" name="actors"/>
			<form:input path="actors" size="80" class="input_actor"/>
			<form:errors path="actors" cssClass="error-color" />
		</li>
		<li>
			<label for="summary">줄거리</label>
			<form:textarea path="summary" cols="80" rows="10" />
			<form:errors path="summary" cssClass="error-color" />
		</li>
		<hr size="1" noshade>
		<li>
			<label for="uploadPoster">포스터 사진</label>
			<input type="file" name="uploadPoster" id="uploadPoster" />
		</li>
		<li>
			<label for="uploadBanner">배너 사진</label>
			<input type="file" name="uploadBanner" id="uploadBanner" />
		</li>
	</ul>

	<div class="edit_btn" align="right">
		<input type="button" value="목록" onclick="location.href='admin_movieList.do'">
		<input type="submit" value="등록">
	</div>
	</form:form>
</div>