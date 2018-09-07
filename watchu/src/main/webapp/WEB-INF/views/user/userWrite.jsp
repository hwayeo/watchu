<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/confirmId.js"></script>

<div id="page-register">
	<div id="form-register">
		<div class="text-center">
			<h3><b>회원가입</b></h3>			
		</div>				
	</div>

<div class="container">  
         <div class="row"> 
            <div class="col-xs-12 col-md-12"><!-- 시작 -->
            <div class="col-md-10 col-md-offset-2">
    
                     <form:form commandName="command" action="write.do" id="insert_Form" enctype="multipart/form-data">
                     <form:errors element="div" cssClass="error-color"/>	
                        	 <div class="col-md-6">
                                
                                    <div class="form-group row">
                                            <label>프로필 사진 설정</label>
                                    </div>
                                    <hr>
                           
											
									<div class="profile">
												
										<a href="#" class="profile_img" data-toggle="dropdown"><img
										src="${pageContext.request.contextPath}/resources/images/default-profile.jpg"
										class="img-size img-circle" id="profile_img" style="width:100px;height:100px;"></a>
												
										<ul class="dropdown-menu">
											<li><a href="#" class="rollbackImage">기본이미지</a></li>
											<li><a href="#" class="modifyImage" >앨범에서 선택
											<input type="file" name="upload" id="upload" >
											</a></li>
										</ul>
												<ul class="dropdown-menu">
													<li><a href="#" class="rollbackImage">기본이미지</a></li>
													<li><a href="#" class="modifyImage" >앨범에서 선택
													<input type="file" name="upload" id="upload">
													</a></li>
												</ul>
											</div>

										</div>
									</div>

							
                            </div>
                            <div class="col-md-6">
                                    <div class="form-group row">
                                        <div class="col-md-4">
                                            <label for="id">아이디</label>
                                        </div>
                                        <div class="col-md-8">
											<form:input path="id" cssClass="form-control"/>
											<form:errors path="id" id="error_id"/>
                                            <input type="button" value="ID중복체크" id="confirmId"
												class="btn btn-default"> <span id="message_id">
												</span> <img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif" width="16" height="16"
												id="loading" style="display: none;">
											
												
										</div>
                                       
                                    </div>
                                    
                                    <div class="form-group row">
                                        <div class="col-md-4">
                                            <label for="name">이름</label>
                                        </div>
                                        <div class="col-md-8">
 											<form:input path="name" cssClass="form-control"/>
 											<form:errors path="name"/>
                                        </div>
                                    </div>
  
                                    
                                    <div class="form-group row">
                                    	<div class="col-md-4">
										<label for="passwd">비밀번호</label>
										</div>
										<div class="col-md-8">
										<div class="input-group pb-modalreglog-input-group">
											<form:password path="passwd" cssClass="form-control" placeholder="4~12자리 영문,숫자 조합"/>
											 <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
										</div>
										<form:errors path="passwd" cssClass="error-color"/>
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
                                        <div class="col-md-4">
                                            <label for="phone">Phone</label>
                                        </div>
                                        <div class="col-md-8">
                                        	<form:input path="phone" cssClass="form-control" placeholder="ex)010-1111-1111"/>
                                        	<form:errors path="phone"/>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <div class="col-md-4">
                                            <label for="email">E-mail</label>
                                        </div>
                                        <div class="col-md-8">
                                        	<form:input path="email" cssClass="form-control" placeholder="ex)email@email.com"/>
                                        	<form:errors path="email"/>
                                        </div>
                                    </div>
                                    		<!-- 임시 -->
                                    		<input type="hidden" name="permit">

									<div class="form-group row text-center">
                                        <div class="col-md-4">
                                        
                                        </div>
                                        <div class="col-md-8 text-center">
                                        	<input type="submit" class="btn btn-primary" value="가입">
                                        	<input type="button" class="btn btn-primary" value="취소" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
                                        </div>
                                    	<!-- </div>  -->   
                                    
                                    </div>
                                    <br>
                                    <br>
                                    <br>
                                                               
                    </form:form>
                            </div>  
                    
                </div>
            </div>
        </div>
    </div>