<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div>
	<table>
		<tr>
			<td>
				<h3>${question.title}</h3>
				<p>${question.user.lastName} ${question.user.firstName}님 |${question.createdDate} | 조회수 : ${question.visitor}</p>
			</td>
		</tr>
		<tr>
			<td>
				<p>${question.content}</p>
			</td>
		</tr>
		
	</table>
	
	<table>
		<tr>
			<td>댓글</td>
		</tr>
		<c:forEach var="answer" items="${question.answers}">
			<tr>
				<td>${answer.user.lastName} ${answer.user.firstName}님<br/>${answer.createdDate}</td>
				<td>${answer.content}</td>
			</tr>
		</c:forEach>
		<tr>
			<td>
			<sf:form commandName="answerRegistrationForm" action="${pageContext.request.contextPath}/qna/answer/write/${question.id}">
				<sf:textarea path="content" row="3" col="20"/> <input type="submit" value="등록" /><br/>
				<sf:errors path="content" />
			</sf:form>
			</td>
		</tr>
	</table>
</div>