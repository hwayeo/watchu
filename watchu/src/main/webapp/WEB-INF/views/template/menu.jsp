<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 관리자 메뉴 -->
<div class="admin_menu">
<h3>관리자 메뉴</h3>
<!-- 아코디언 시작 -->
<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          	영화 관리 ▼
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
      <ul>
         <li class='last'><a href='${pageContext.request.contextPath}/admin/admin_movieList.do'><span>영화 등록 및 수정</span></a></li>
         <li class='last'><a href='${pageContext.request.contextPath}/admin/officialList.do'><span>관계자 등록 및 수정</span></a></li>
         <li class='last'><a href='${pageContext.request.contextPath}/admin/genreList.do'><span>장르 등록 및 수정</span></a></li>
         <li class='last'><a href='${pageContext.request.contextPath}/admin/movieRating.do'><span>영화 별점 관리</span></a></li>
      </ul>
      </div>
    </div>
  </div>
  
  <div class="panel">
    <div class="panel-heading" role="tab" id="headingTwo">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          	회원 관리 ▼
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
      <div class="panel-body">
      <ul>
         <li class='last'><a href='${pageContext.request.contextPath}/admin/userList.do'><span>회원 목록</span></a></li>
         <li class='last'><a href='${pageContext.request.contextPath}/admin/reportedUser.do'><span>신고 회원 관리</span></a></li>
      </ul>
      </div>
    </div>
  </div>
  
   <div class="panel">
    <div class="panel-heading" role="tab" id="headingThree">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          	고객 지원 ▼
        </a>
      </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="panel-body">
      <ul>
         <li class='last'><a href='${pageContext.request.contextPath}/user/userSupportList.do'><span>고객 문의 관리</span></a></li>
      </ul>
      </div>
    </div>
    </div>
    
  </div>
  <!-- 아코디언 끝 -->
  
</div>