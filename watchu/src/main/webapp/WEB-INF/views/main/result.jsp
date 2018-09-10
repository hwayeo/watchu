<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="main-content">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-12 col-lg-12">
				<h3>영화 검색 결과</h3>
				<div class="text-right">
					<a href="#">더보기</a>
				</div>
				<div class="results">
					<div class="row">
						<c:forEach var="list" items="${movieList}" varStatus="status">
							<c:if test="${status.index < 3}">
								<div class="col-xs-4 col-md-3">
									<img src="${pageContext.request.contextPath}/resources/images/billy.jpg" class="img-responsive">
									<p>${list.title}</p>
									<span>별점</span>
								</div>
							</c:if>
							<c:if test="${status.index == 3}">
								<div class="hidden-xs visible-md visible-lg col-md-3">
									<img src="${pageContext.request.contextPath}/resources/images/billy.jpg" class="img-responsive">
									<p>${list.title}</p>
									<span>별점</span>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
