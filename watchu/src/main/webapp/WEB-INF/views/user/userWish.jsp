<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<div class="container-fluid">
		<div class="container text-center">
			<div class="col-xs-12 col-md-12">
				<h2>보고싶어요</h2><br><hr>
			</div>
		</div>

		<div class="container">
			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<div class="nav-collapse">
						<ul class="nav">
							<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><b class="caret"></b>&nbsp;담은순</a>
								<ul class="dropdown-menu">
									<li class="dropdown-header">담은순</li>
									<li><a href="#">평점순</a></li>
									<li><a href="#">가나다순</a></li>
									<li><a href="#">신작순</a></li>
								</ul></li>
						</ul>
						<hr>
					</div>

					<div class="row container">
						<div class="col-xs-12 col-md-12">
							<div class="col-xs-4 col-md-3">
								<img src="${pageContext.request.contextPath}/resources/images/gad.jpg" width="100%" height="100%">
								<br><span>가디언즈 오브 갤럭시</span>
								<br><span style="color: #ffbf00">★4.5</span><br><br>
							</div>
							<div class="col-xs-4 col-md-3">
								<img src="${pageContext.request.contextPath}/resources/images/gad2.jpg" width="100%" height="100%">
								<br><span>수어사이드 스쿼드</span>
								<br><span style="color: #ffbf00">★1.5</span><br><br>

							</div>
							<div class="col-xs-4 col-md-3">
								<img src="${pageContext.request.contextPath}/resources/images/gad3.jpg" width="100%" height="100%">
								<br><span>앤트맨</span>
								<br><span style="color: #ffbf00">★5.0</span><br><br>
							</div> 
							<div class="col-xs-4 col-md-3">
								<img src="${pageContext.request.contextPath}/resources/images/gad.jpg" width="100%" height="100%">
								<br><span>가디언즈 오브 갤럭시</span>
								<br><span style="color: #ffbf00">★4.5</span><br><br>
							</div>
						</div> 
					</div>
				</div>
			</div>
		</div>
	</div>
