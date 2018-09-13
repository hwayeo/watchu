<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="detail" style="width:90%">
	<h2>신고 상세 정보/수정</h2>
	<div class="detail-content">
		<ul>
			<li>
				<label>작성자</label>${report.id}
			</li>
			<li>
				<label>작성일</label>${report.reg_date}
			</li>
			<hr size="1" noshade>
			<li>
				<label>신고아이디</label>${report.report_user }
			</li>
			<li>
				<label>신고내용</label>
				<div class="report_content">
					<p>${report.report_content}</p>
				</div>
			</li>
		</ul>
		<div class="edit_btn" align="right">
			<input type="button" id="processing" class="btn btn-md btn-primary" value="처리완료(삭제)" onclick="location.href='reportDelete.do?num=${report.report_num}'">
			<input type="button" class="btn btn-default" value="목록" onclick="location.href='reportedUser.do'">
		</div>
	</div>
	<%-- <div class="tab-pane" id="reported_user">
		<h2>신고 상세정보</h2>
		<br>
		<div class="content-header">
		</div>
	
		<div class="content-body">
			<div class="col-xs-12 col-md-12" ><!-- 시작 -->
			
			<div class="col-xs-6 col-md-2" >
			</div>
			
			<div class="col-md-7" style="border:1px solid#e5e3e3;">
				       
						<div class="form group">
							<div>
								<label>작성자:</label>${report.id}
							</div>
							<div>
								<label>신고아이디:</label>${report.report_user }
							</div>
							<div>
								<label>신고내용</label> 
								<div>
									<p>${report.report_content}</p>				
								</div>
							</div>
							<div>
								<label>신고날짜</label> 
								<div>
									<p>${report.reg_date}</p>				
								</div>
							</div>
							
						</div>
							
						<div class="text-center">
							<input type="button" id="processing" class="btn btn-md btn-primary" value="처리완료(삭제)" onclick="location.href='reportDelete.do?num=${report.report_num}'">
							<input type="button" class="btn btn-primary" value="취소" onclick="location.href='reportedUser.do'">
							
							
						</div>
						
			</div>
			
			<div class="col-xs-6 col-md-3" >
			</div>
			
		</div><!-- 끝 -->
		</div>
	</div> --%>
</div>