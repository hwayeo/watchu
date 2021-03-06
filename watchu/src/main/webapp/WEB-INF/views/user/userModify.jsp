<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/confirmId.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/profile_img.js"></script>


<div id="page-register">
	<div id="form-register">
		<div class="text-center">
			<h3><b>프로필 수정</b></h3>			
		</div>				
	</div>

<div class="container">  
         <div class="row"> 
            <div class="col-xs-12 col-md-12"><!-- 시작 -->
    
                     <form:form commandName="command" action="updateUser.do" id="updateUser_Form" enctype="multipart/form-data">
                     
                        	 <div class="col-md-6 text-center">
                                    <div class="form-group row">
                                           <label>프로필 사진 설정</label>
                                    </div>
                                    <hr>
											
											<div class="profile">
										
												<div>
													<a href="#" class="profile_img" data-toggle="dropdown">
														<!-- 선택한이미지 -->
														<img id="MyProfileImg" src="#" class="img-circle" style="width:100px;height:100px;display: none;"/>
														<!-- 기본이미지 -->
														<c:if test="${!empty command.profile_img}">
															<img src="${pageContext.request.contextPath}/main/imageView.do?id=${command.id}" id="profile_img" class="img-circle" style="width:100px;height:100px;" >
														</c:if>	
														<c:if test="${empty command.profile_img}">
															<img src="${pageContext.request.contextPath}/resources/images/default-profile.jpg"
														class="img-circle" id="profile_img" style="width:100px;height:100px;">
														</c:if>
														<img src="${pageContext.request.contextPath}/resources/images/default-profile.jpg"
														class="img-circle" id="profile_img2" style="width:100px;height:100px;display: none;"> 
													</a>
															
													<ul class="dropdown-menu">
														<li><a class="rollbackImage" style="cursor:pointer;">
															<b>기본이미지</b>
														</a></li>
														<li><a href="#" class="modifyImage">
															<b>앨범에서 선택</b>
															<input type="file" name="upload" id="upload" >
														</a></li>
													</ul>
												</div>

											</div>

							
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
											<form:password path="passwd" cssClass="form-control"/>
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
                                        <div class="col-md-4">
                                            <label for="phone">Phone</label>
                                        </div>
                                        <div class="col-md-8">
                                        	<form:input path="phone" cssClass="form-control" placeholder="ex)010-1111-1111"/>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <div class="col-md-4">
                                            <label for="email">E-mail</label>
                                        </div>
                                        <div class="col-md-8">
                                        	<form:input path="email" cssClass="form-control" placeholder="ex)email@email.com"/>
                                        </div>
                                    </div>
                                    

									<div class="form-group row">
                                        <!-- <p class="text-center">welcome! </p> -->
                                    	<!-- <div class="form-group row"> -->
                                        <div class="col-md-4">
                                        
                                        </div>
                                        <div class="col-md-8">
                                        	<input type="submit" class="btn btn-primary" value="수정">
                                        	<input type="button" class="btn btn-primary" value="취소" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
                                        </div>
                                    	<!-- </div>  -->   
                                    
                                    </div>
                                    <br>
                                    <br>
                                    <br>
                                                               
                            </div>  
                    </form:form>
                    
                </div>
            </div>
        </div>
    </div>