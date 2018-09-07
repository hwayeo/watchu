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
            	
            	<div class="col-md-6" style="border:1px solid #e5e3e3;">
            		
            		<form:form id="report_Form" commandName="reportCommand">

						
						<div>
            				<label>신고아이디</label>
            				<div>
            					<span>${user2.id}</span>
            				</div>
            			</div>
            			<hr>
            			<div class="form-group">
							<label>신고내용</label> 
							<select class="form-control">
								<option>욕설사용</option>
								<option>부적절한내용기재</option>
								<option>ㅇㅇ</option>
								<option>기타</option>
							</select>
						</div>
            			<div>
	            			<label>신고내용</label>
	            			<div>
	            				<form:textarea path="report_content" cols="70" rows="5"/>
	            			</div>
            			</div>
            			
            			<div class="text-center">
                           <input type="submit" class="btn btn-primary" value="신고하기">
                           <input type="button" class="btn btn-primary" value="취소" onclick="location.href='userPage.do?id=${user2.id}'">
                       </div>
            		
            		</form:form>
            	</div>
            	
            	
            	<div class="col-md-3">
            	</div>
            
            
            </div><!-- 끝 -->
            
         </div>
         
</div>
</div>
