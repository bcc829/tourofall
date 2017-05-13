<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap-theme.min.css"/>">
<style>
	.center-container{
		text-align:center;
	}
</style>
<div>
	<div class="center-container">
		<h1>Please evaluate Tourist Attraction more</h1>
		<c:if test="${not empty fin}">
			<p class="lead">${fin}</p>
			<a href="<c:url value="/"/>" class="btn btn-default">그만하기</a>
		</c:if>
	</div>
	<div class="container">
		<!-- Example row of columns -->

		<sf:form  action = "${pageContext.request.contextPath}/eval/evalmore" method = "post" commandName="evaluationRegistrationsForm">
			<div class="row">
				<c:forEach var="evaluationRegistration" items="${evaluationRegistrationsForm.evaluationRegistrations}" varStatus="status">
					<div class="col-sm-4">
						<sf:hidden path="evaluationRegistrations[${status.index}].itemId"/>
						<p class="lead">${evaluationRegistration.title}</p>
						<sf:hidden path="evaluationRegistrations[${status.index}].title"/>
						<img alt="이미지 없음" src="${evaluationRegistration.imageUrl}" width="100%" >
						<sf:hidden path="evaluationRegistrations[${status.index}].imageUrl"/>
						<p>
							평점<br />
							<input type = "radio" name="evaluationRegistrations[${status.index}].score" value="1"/>1&nbsp;
							<input type = "radio" name="evaluationRegistrations[${status.index}].score" value="2"/>2&nbsp;
							<input type = "radio" name="evaluationRegistrations[${status.index}].score" value="3"/>3&nbsp;
							<input type = "radio" name="evaluationRegistrations[${status.index}].score" value="4"/>4&nbsp;
							<input type = "radio" name="evaluationRegistrations[${status.index}].score" value="5"/>5
						</p>
					</div>
				</c:forEach>
			</div>
			<div class="row">
				<div class="col-sm-12 center-container">
					<input type="submit" value="제출하기" class="btn btn-primary">
					<a href="<c:url value="/"/>" class="btn btn-default">그만하기</a>
				</div>
			</div>
		</sf:form>
	</div>
	
</div>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>