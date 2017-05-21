<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="margin-left: 30px; margin-right: 30px;">

	<c:forEach var="answer" items="${answers}">
		<div class="well">
			<div class="row">
				<p>질문 : ${answer.questionTitle}</p>
				<p>댓글: ${answer.content}</p>
			</div>
		</div>
	</c:forEach>


</div>