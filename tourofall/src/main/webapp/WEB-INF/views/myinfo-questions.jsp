<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h3>${lastName}&nbsp;${firstName}님의 질문들</h3>
	<table>
		<c:forEach var="question" items="${questions}">
			<tr>
				<td>${question.id}</td>
				<td><a href="<c:url value="/qna/question/${question.id}"/>">${question.title}</a></td>
				<td>${question.visitor}</td>
			</tr>
		</c:forEach>
	</table>
</div>