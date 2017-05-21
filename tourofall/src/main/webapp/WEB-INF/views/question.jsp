<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div>
	<table>
		<tr>
			<td>
				<h3>${questionRenderingModel.title}</h3>
				<p>${questionRenderingModel.lastName} ${questionRenderingModel.firstName}님 |${questionRenderingModel.createdDate} | 조회수 : ${questionRenderingModel.visitor}</p>
			</td>
		</tr>
		<tr>
			<td>
				<p>${questionRenderingModel.content}</p>
			</td>
		</tr>
		
	</table>
	
	<table>
		<tr>
			<td>댓글</td>
		</tr>
		<c:forEach var="answerRenderingModel" items="${answerRenderingModels}">
			<tr>
				<td><a href="<c:url value="/users/${answerRenderingModel.userId}"/>">${answerRenderingModel.lastName} ${answerRenderingModel.firstName}님</a><br/>${answerRenderingModel.createdDate}</td>
				<td>${answerRenderingModel.content}</td>
			</tr>
		</c:forEach>
		<tr>
			<td>
			<sf:form commandName="answerRegistrationForm" action="${pageContext.request.contextPath}/qna/answer/write/${questionRenderingModel.id}">
				<sf:textarea path="content" row="3" col="20"/> <input type="submit" value="등록" /><br/>
				<sf:errors path="content" />
			</sf:form>
			</td>
		</tr>
	</table>
</div>