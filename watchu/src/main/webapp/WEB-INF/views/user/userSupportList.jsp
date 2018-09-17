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
         <div class="col-md-12 col-xs-12 content-header form-inline">
            <!-- 검색 -->
	        <form action="userSupportList.do" id="support_search" method="get" class="form-group">
				<select name="keyfield" class="form-control">
					<option value="all">전체</option>
					<option value="category">구분</option>
					<option value="content">제목</option>
					<option value="title">내용</option>
				</select>
			<input type="text" name="keyword" id="keyword" class="form-control" size="15"> 
			<input type="submit" value="검색" class="btn btn-default">
			</form>
         </div>

         <!-- 상담 목록 -->
        <c:if test="${count == 0}">
			<div class="align-center">등록된 게시물이 없습니다.</div>
		</c:if>
		<c:if test="${count > 0}">
         <table class="table table-hover table-condensed">
         <thead>
            <tr class="sup_title">
               <th class="col-md-1 text-center">번호</th>
               <th class="col-md-2 text-center">구분</th> 
               <th class="col-md-4 text-center">제목</th>
               <!-- <th class="col-md-1 text-center">상태</th> -->
               <th class="col-md-3 text-center">작성자</th>
               <th class="col-md-2 text-center">등록일</th>
            </tr>
           </thead>
            <c:forEach var="contact" items="${list}">
            <tr class="sup_content">
               <td>${contact.contact_num}</td>
               <td>${contact.category}</td>
               <td onclick="location.href='userSupportView.do?contact_num=${contact.contact_num}'" style="cursor:pointer">${contact.title}</td>
               <%-- <td class="recontentable">
               	<c:if test="${contact.recotentable == 0}">
               		<a style="color:red">답변대기</a>
               	</c:if>
               	<c:if test="${contact.recotentable == 1}">
               		<a style="color:blue">답변완료</a>
               	</c:if>
               </td> --%>
               <td>${contact.id}</td>
               <td>${contact.reg_date}</td>
            </tr>
            </c:forEach>
         </table>
         </c:if>
      </div>
      <div class="etc text-right">
		<input type="button" class="btn btn-primary" value="글쓰기" onclick="location.href='userSupportWrite.do'">
      </div>
   </div>
</div>
</div>