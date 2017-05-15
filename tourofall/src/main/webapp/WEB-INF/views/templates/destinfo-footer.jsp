<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<table>
		<tr>
			<th><h3>리뷰</h3></th>
			<c:if test="${reviewWrite}">
				<td><a href="<c:url value="/review/write/${itemTypeId}/${itemId}"/>">리뷰 작성하기</a></td>
			</c:if>
		</tr>
		<c:forEach var="review" items="${reviews}">
			<tr>
				<td>
					<h3>제목 : ${review.title}</h3>
					<p>작성자 : ${review.user.lastName} ${review.user.firstName}님</p>
					<p>작성일자 :
						${review.createdDate.year+1900}년${review.createdDate.month+1}월${review.createdDate.date}일</p>
					<p>평점 : ${review.evaluation.score}</p>
					<p>내용 : ${review.content}</p>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<th><h3>QnA</h3></th>
			<td><a href="<c:url value="/qna/question/write/${itemTypeId}/${itemId}"/>">질문 작성하기</a></td>
		</tr>
		<tr>
			<c:forEach var="questionInfo" items="${questionInfoes}">
				<tr>
					<td>${questionInfo.id}</td>
					<td><a href="<c:url value="/qna/question/${questionInfo.id}"/>">${questionInfo.title}</a></td>
					<td>${questionInfo.lastName} ${questionInfo.firstName}님</td>
					<td>${questionInfo.visitor}</td>
				</tr>
			</c:forEach>
		</tr>
	</table>
</div>	