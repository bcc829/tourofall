<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<div class="table-responsive">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>방문수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="question" items="${questions}">
					<tr>
						<td>${question.questionId}</td>
						<td><a href="<c:url value="/qna/question/${question.questionId}"/>">${question.title}</a></td>
						<td>${question.visitor}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>