<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/support.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/support.js"></script>
<div id="main-content"> 
<div class="container">
   <h2 class="title">������</h2> 
   <p class="subTitle">Watchu�� �������Դϴ�.</p>

   <div class="content-body">
      <div class="tab-pane active text-center" id="support_list">
         <div class="col-md-12 col-xs-12 content-header form-inline">
            <!-- �˻� -->
	        <form action="userSupportList.do" id="support_search" method="get" class="form-group">
				<select name="keyfield" class="form-control">
					<option value="all">��ü</option>
					<option value="category">����</option>
					<option value="content">����</option>
					<option value="title">����</option>
				</select>
			<input type="text" name="keyword" id="keyword" class="form-control" size="15"> 
			<input type="submit" value="�˻�" class="btn btn-default">
			</form>
         </div>

         <!-- ��� ��� -->
        <c:if test="${count == 0}">
			<div class="align-center">��ϵ� �Խù��� �����ϴ�.</div>
		</c:if>
		<c:if test="${count > 0}">
         <table class="table table-hover table-condensed">
         <thead>
            <tr class="sup_title">
               <th class="col-md-1 text-center">��ȣ</th>
               <th class="col-md-2 text-center">����</th> 
               <th class="col-md-4 text-center">����</th>
               <!-- <th class="col-md-1 text-center">����</th> -->
               <th class="col-md-3 text-center">�ۼ���</th>
               <th class="col-md-2 text-center">�����</th>
            </tr>
           </thead>
            <c:forEach var="contact" items="${list}">
            <tr class="sup_content">
               <td>${contact.contact_num}</td>
               <td>${contact.category}</td>
               <td onclick="location.href='userSupportView.do?contact_num=${contact.contact_num}'" style="cursor:pointer">${contact.title}</td>
               <%-- <td class="recontentable">
               	<c:if test="${contact.recotentable == 0}">
               		<a style="color:red">�亯���</a>
               	</c:if>
               	<c:if test="${contact.recotentable == 1}">
               		<a style="color:blue">�亯�Ϸ�</a>
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
		<input type="button" class="btn btn-primary" value="�۾���" onclick="location.href='userSupportWrite.do'">
      </div>
   </div>
</div>
</div>