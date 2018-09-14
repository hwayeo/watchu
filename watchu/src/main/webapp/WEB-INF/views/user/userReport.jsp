<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<div id="page-register">
	<div id="form-register">
		<div class="text-center">
			<h3><b>신고페이지</b></h3>			
		</div>				
	</div> 

<div class="container">
         <div class="row"> 

            <div class="col-xs-12 col-md-12"><!-- 시작 -->
            
            	<div class="col-md-3">
            	</div>
            	
	            	<div class="col-md-6">
	            		
	            		<form:form id="report_Form" commandName="reportCommand">
	
							<div style="padding:50px; background:#eee;">
							
							<div>
	            				<label>신고아이디　　</label>
	            					<span>${user2.id}</span>
	            			</div><br>
	            			<div class="form-group" text-align="center">
								<label>신고 구분</label>
								<form:select path="report_category" class="form-control">
									<form:option value="" disabled="true" selected="true">------ 구분을 선택하세요 ------</form:option>
									<form:option value="욕설사용">욕설 사용</form:option>
									<form:option value="부적절한 내용">부적절한 내용기재</form:option>
									<form:option value="회원비방">회원 비방</form:option>
									<form:option value="스포일러">스포일러</form:option>
									<form:option value="기타">기타</form:option>
								</form:select>
							</div>
							<div>
		            			<label>제목</label>
		            			<div>
		            				<form:input path="report_title" class="form-control"/>
		            			</div>
	            			</div>
	            			<div>
		            			<label>내용</label>
		            			<div>
		            				<form:textarea path="report_content" cols="50" rows="5" class="form-control"/>
		            			</div>
	            			</div>
	            			
	            			<div class="text-center" style="margin-top:30px;">
	                           <input type="submit" class="btn btn-primary" value="신고하기" id="report_submit">
	                           <input type="button" class="btn btn-default" value="취소" onclick="location.href='userPage.do?id=${user2.id}'">
	                       </div>
	            			
	            			</div>
	            		</form:form>
	            	</div>
            	
            	<div class="col-md-3">
            	</div>
            
            
            </div><!-- 끝 -->
            
         </div>
         
</div>
</div>
