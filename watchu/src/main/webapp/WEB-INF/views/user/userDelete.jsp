<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/confirmId.js"></script>


<div id="page-register">
	<div id="form-register">
		<div class="text-center">
			<h3><b>회원탈퇴</b></h3>			
		</div>				
	</div>

<div class="container">  
         <div class="row"> 
            <div class="col-md-10 col-md-offset-2">
    
                     <form:form commandName="command" action="deleteUser.do" id="deleteUser_Form" enctype="multipart/form-data">
                     
                        	 <div class="col-md-2">
                            </div>
                            <div class="col-md-6">
                                    <div class="form-group row">
                                        <div class="col-md-4">
                                            <label for="id">아이디</label>
                                        </div>
                                        <div class="col-md-8">
											<form:hidden path="id"/>${command.id} 
											<form:errors element="div" cssClass="error-color"/>
											<form:errors path="id"/>
                                   
										</div>
                                       
                                    </div>
                                    
                                    <div class="form-group row">
                                    	<div class="col-md-4">
										<label for="passwd">비밀번호</label>
										</div>
										<div class="col-md-8">
										<div class="input-group pb-modalreglog-input-group">
											<form:password path="passwd" cssClass="form-control" placeholder=""/>
											 <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
										</div>
										</div>
									</div>
									
      
                                    <div class="form-group row">
                                    	<div class="col-md-4">
										<label for="cpasswd">비밀번호확인</label>
										</div>
										<div class="col-md-8">
										<div class="input-group pb-modalreglog-input-group">
											<input type="password" class="form-control" id="cpasswd" placeholder="비밀번호를 다시 입력해주세요">
											 <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
										</div>
										</div>
									</div>
                                    
									<div class="form-group row">
                                        <!-- <p class="text-center">welcome! </p> -->
                                    	<!-- <div class="form-group row"> -->
                                        <div class="col-md-4">                                   
                                        </div>
                                        
                                        
                                        <div class="col-md-8">
                                        	<input type="submit" class="btn btn-primary" value="탈퇴">
                                        	<input type="button" class="btn btn-primary" value="취소" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
                                        </div>
                                    	
                                    
                                    </div>
                                  
                                                               
                            </div> 
                            <div class="col-md-4">
                            </div> 
                    </form:form>
                    
                </div>
            </div>
        </div>
    </div>