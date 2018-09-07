<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="detail">
	<h2>영화 상세 정보/수정</h2>
	<form:form commandName="movie_command" action="admin_movieDetail.do" id="modify_form" enctype="multipart/form-data">
	    <form:hidden path="movie_num"/>
		<form:errors element="div" cssClass="error-color"/>
	<div class="banner_img">
	<c:if test="${!empty movie.banner_img}">
    	<img src="image_View.do?movie_num=${movie.movie_num}&type=banner" width="100%">
    </c:if>
	<c:if test="${empty movie.banner_img}">
	등록된 배너 이미지 없음
	</c:if>
	</div>
	
	<table>
		<tr>
			<td>
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
			<ul>
				<li><b>영화 코드: </b> ${movie.movie_num} | <b>등록일: </b> ${movie.reg_date}</li>
				<li><label for="title">영화명: </label>
					<form:input path="title" />
					<form:errors path="title" cssClass="error-color" /> |
					<label for="released">개봉연도: </label>
					<form:input path="released" />
					<form:errors path="released" cssClass="error-color" /></li>
				<li><label for="main_genre">메인 장르: </label>
        			<form:input path="main_genre" class="auto_genre"/>
        			<form:errors path="main_genre" cssClass="error-color" /> |
        			<label for="sub_genre">서브 장르: </label>
        			<form:input path="sub_genre" class="auto_genre"/>
        			<form:errors path="sub_genre" cssClass="error-color" /> |
        			<label for="country">제작국가: </label>
        			<form:input path="country" />
        			<form:errors path="country" cssClass="error-color" /></li>
				<li><label for="director">감독: </label>
        			<input class="auto_director" type="text" name="actors"/>
					<input id="director" name="director" value="${movie_command.director}" size="50" class="input_director" readonly="readonly"/>
					<span>※ 수정 시 기존 데이터는 삭제됩니다 ※</span>
				<li><label for="trailer">예고편 코드: </label>
					<form:input path="trailer" />
					<form:errors path="trailer" cssClass="error-color" /></li>
			</ul>
        	</td>
		</tr>
		<tr>
			<td colspan="2">
			<hr size="1" noshade>
			<ul>
				<li>
					<label for="actors">출연배우</label>
					<div class="actors">
					<c:if test="${!empty movie.actors}">
					<input type="text" id="actors" name="actors" value="${movie_command.actors}" size="70" class="input_actor" readonly="readonly"/>
					<br>수정 입력: <input class="auto_actor" type="text" name="actors"/>
					<span>※ 수정 시 기존 데이터는 삭제됩니다 ※</span>
					</c:if>
					<c:if test="${movie.actors == null}">
					등록된 출연배우 없음
					<input type="text" id="actors" name="actors" value="${movie_command.actors}" size="70" class="input_actor" readonly="readonly"/>
					<br>배우 입력: <input class="auto_actor" type="text" name="actors"/>
					</c:if>
					</div>
				</li>
				<hr size="1" noshade>
				<li>
					<label for="summary">줄거리</label>
					<div class="summary">
					<c:if test="${!empty movie.summary}">
					<form:textarea path="summary" cols="100" rows="5" />
					<form:errors path="summary" cssClass="error-color" />
					</c:if>
					<c:if test="${movie.summary == null}">
					등록된 줄거리 없음
					<div class="form-group">
					<form:textarea path="summary" cols="100" rows="5" />
					<form:errors path="summary" cssClass="error-color" />
					</div>
					</c:if>
					</div>
				</li>
				<hr size="1" noshade>
				<li>
					<div class="form-group">
					<label for="uploadPoster">포스터 사진</label>
	         		<input type="file" name="uploadPoster" id="uploadPoster"/>
					</div>
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
	
	<%-- <table>
		<tr>
        	<td width="40%">
        	<div class="poster_img">
        	${movie.uploadPoster}
        	<c:if test="${movie.uploadPoster == null }">
        	등록된 포스터 없음
        	</c:if>
        	</div>
        	</td>
        	<td>
        		<ul>
        			<li><b>등록번호 </b>${movie.movie_num} | <b>등록일 </b>${movie.reg_date}</li>
        			<li><b>영화명 </b>${movie.title} | <b>개봉연도 </b>${movie.released}</li>
        			<li><b>메인 장르 </b>${movie.main_genre} | <b>서브 장르 </b>${movie.sub_genre} | <b>제작 국가 </b>${movie.country}</li>
        			<li><b>감독 </b>${movie.director}</li>
        			<li><b>예고편 </b>${movie.trailer}</li>
        			<label for="title">영화명</label>
        			<form:input path="title" />
					<form:errors path="title" cssClass="error-color" /> | 
					<label for="released">개봉연도</label>
					<input type="date" name="released" id="released"></li>
        			<li><label for="main_genre">메인 장르</label>
        			<form:input path="main_genre" />
        			<form:errors path="main_genre" cssClass="error-color" /> | 
        			<label for="sub_genre">서브 장르</label>
        			<form:input path="sub_genre" />
        			<form:errors path="sub_genre" cssClass="error-color" /> | 
        			<label for="country">제작 국가</label>
        			<form:input path="country" />
        			<form:errors path="country" cssClass="error-color" /></li>
        			<li><label for="director">감독</label>
        			<input type="text" name="director" class="auto" id="auto_director"></li>
        			<li><label for="trailer">예고편 코드</label>
        			<form:input path="trailer" />
        			<form:errors path="trailer" cssClass="error-color" /></li>
        		</ul>
        	</td>
        </tr>
        
		<tr>
			<td colspan="2">출연배우</td>
		</tr>
		<tr>
			<td colspan="2">
			<div class="actors">
			<c:if test="${!empty movie.actors}">
			${movie.actors} 
			</c:if>
			<c:if test="${movie.actors == null}">
			등록된 출연배우 없음
			</c:if>
			</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">줄거리</td>
		</tr>
		<tr>
			<td colspan="2">
			<div class="summary">
			<c:if test="${!empty movie.summary}">
			${movie.summary}
			</c:if>
			<c:if test="${movie.summary == null}">
			등록된 줄거리 없음
			</c:if>
			</div>
			</td>
		</tr>
	</table> --%>
	
	</div>
	<div class="edit_btn" align="right">
		<input type="button" value="삭제" onclick="location.href='admin_movieDelete.do?movie_num=${movie.movie_num}'" id="del_movie">
		<input type="submit" value="수정">
		<input type="button" onclick="location.href='admin_movieList.do'" value="목록">
	</div>
	</form:form>
</div> 