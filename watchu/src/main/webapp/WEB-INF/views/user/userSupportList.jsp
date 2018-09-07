<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/support.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/support.js"></script>
<div id="main-content">
<div class="container">
   <h2 class="title">고객센터</h2>
   <p class="subTitle">Watchu의 고객센터입니다.</p>

   <div class="content-body">
      <div class="tab-pane active text-center" id="support_list">
         <div class="col-md-12 col-xs-12 content-header">
            <!-- 검색 -->
	        <form action="userSupportList.do" id="search_form" method="get">
				<ul class="search">
					<li style="none">
						<p class="search-title">검색 구분 </p>
	            <select name="keyfield">
	               <option value="title">제목</option>
					<option value="id">ID</option>
					<option value="content">내용</option>
					<option value="all">전체</option>
	            </select>
					<input type="text" name="keyword" id="keyword" style="width:110px">
					<input type="submit" value="검색" class="btnSearch">
				</ul>
			</form>
         </div>
         
         <p class="total">총 <span class="watchucolor">${contact.count}</span>건의 상담내역이 있습니다.</p>

         <!-- 상담 목록 -->
        <c:if test="${count == 0}">
			<div class="align-center">등록된 게시물이 없습니다.</div>
		</c:if>
		<c:if test="${count > 0}">
         <table class="table table-hover table-condensed">
            <tr class="sup_title">
               <th class="col-md-1">번호</th>
               <th class="col-md-2">아이디</th>
               <th class="col-md-3">제목</th>
               <th class="col-md-2">분류</th>
               <th class="col-md-2">상태</th>
               <th class="col-md-2">등록일</th>
            </tr>
            
            <c:forEach var="contact" items="${list}">
            <tr class="sup_content" a onclick="location.href='userSupportView.do?contact_num=${contact.contact_num}'" style="cursor:pointer">
               <td>${contact.contact_num}</td>
               <td>${contact.id}</td>
               <td>${contact.title}</td>
               <td>App 오류</td>
               <td>
               	<c:if test="${contact.recotentable == 0}">
               		<a style="color:red">답변대기</a>
               	</c:if>
               	<c:if test="${contact.recotentable == 1}">
               		<a style="color:blue">답변완료</a>
               	</c:if>
               </td>
               <td>${contact.reg_date}</td>
            </tr>
            </c:forEach>
         </table>
         </c:if>
      </div>
      <div class="etc text-center">
		<input type="button" class="btn btn-default" value="글쓰기" onclick="location.href='userSupportWrite.do'">
      </div>
   </div>
</div>
</div>