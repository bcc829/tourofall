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
			<th colspan="2">리뷰</th>
		</tr>
		<c:forEach var="review" items="${reviews}">
			<tr>
				<td>
					<h3>${review.title}</h3>
					<p>${review.user.username}</p>
					<p>평점 : ${review.score}</p>
					<p>${review.content}</p>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>