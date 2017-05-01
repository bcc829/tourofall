<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<table>
		<tr>
			<th colspan="2"><h3>리뷰</h3></th>
		</tr>
		<c:forEach var="review" items="${reviews}">
			<tr>
				<td>
					<h3>제목 : ${review.title}</h3>
					<p>작성자 : ${review.user.username}</p>
					<p>작성일자 : ${review.createdDate.year+1900}년${review.createdDate.month+1}월${review.createdDate.date}일</p>
					<p>평점 : ${review.score}</p>
					<p>내용 : ${review.content}</p>
				</td>
			</tr>
	</c:forEach>	
	</table>
</div>