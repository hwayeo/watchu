<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/movieEva.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/movieEva.js"></script>

<!-- 모바일 환경 -->
<select class="visible-xs visible-sm form-control">
  <option selected>모든 장르</option>
  <option>드라마</option>
  <option>판타지</option>
  <option>재난</option>
  <option>공포</option>
  <option>로맨스</option>
  <option>모험</option>
  <option>스릴러</option>
  <option>다큐멘터리</option>
  <option>코미디</option>
  <option>가족</option>
  <option>미스터리</option>
  <option>애니메이션</option>
  <option>범죄</option>
  <option>뮤지컬</option>
  <option>SF</option>
  <option>액션</option>
  <option>히어로</option>
</select>
<select id="keyfield" name="keyfield" class="visible-xs visible-sm form-control">
  <option selected>모든 국가</option>
  <option>한국</option>
  <option>미국</option>
  <option>일본</option>
  <option>중국</option>
  <option>독일</option>
  <option>대만</option>
  <option>인도</option>
  <option>영국</option>
  <option>프랑스</option>
  <option>이탈리아</option>
  <option>캐나다</option>
  <option>스페인</option>
</select>
<select class="visible-xs visible-sm form-control"> 
  <option selected>최신 순</option>
  <option>추천 순</option>
  <option>별점 순</option>
</select>
<!-- 웹환경 -->
<select  class="hidden-xs hidden-sm rate-menu">
  <option selected>모든 장르</option>
  <option>드라마</option>
  <option>판타지</option>
  <option>재난</option>
  <option>공포</option>
  <option>로맨스</option>
  <option>모험</option>
  <option>스릴러</option>
  <option>다큐멘터리</option>
  <option>코미디</option>
  <option>가족</option>
  <option>미스터리</option>
  <option>애니메이션</option>
  <option>범죄</option>
  <option>뮤지컬</option>
  <option>SF</option>
  <option>액션</option>
  <option>히어로</option>
</select>
<select id="evafield" name="keyfield" class="hidden-xs hidden-sm rate-menu">
  <option value="" selected>모든 국가</option>
  <option value="한국">한국</option>
  <option value="미국">미국</option>
  <option>일본</option>
  <option>중국</option>
  <option>독일</option>
  <option>대만</option>
  <option>인도</option>
  <option>영국</option>
  <option>프랑스</option>
  <option>이탈리아</option>
  <option>캐나다</option>
  <option>스페인</option>
</select>
<select  class="hidden-xs hidden-sm rate-menu category-right"> 
  <option selected>최신 순</option>
  <option>추천 순</option>
  <option>별점 순</option>
</select>
<input type="hidden" id="user_id" value="${user_id}">
<div class="row">
<div id="elist"></div>
</div>