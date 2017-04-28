<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h2>${destInfo.title}</h2>
	<a href="<c:url value="/dest/info/review/${destInfo.contentid}"/>">리뷰 쓰기</a>
	<table >
		<tr>
			<td ><img alt="이미지 없음" src="${destInfo.firstimage2}"></td>
			<td>${destInfo.addr1}${destInfo.addr2}</td>
		</tr>
		<tr>
			<th colspan="2">개요</th>
		</tr>
		<tr>
			<td colspan="2">${destInfo.overview}</td>
		</tr>
		<tr>
			<th colspan="2"><h2>리뷰</h2></th>
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