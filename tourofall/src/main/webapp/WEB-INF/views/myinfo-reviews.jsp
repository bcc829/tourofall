<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h3>${name}님의 리뷰들</h3>
	<table>
		<c:forEach var="review" items="${reviews}">
			<tr>
				<td>
					<h4>${review.title}</h4>
					<p>
						작성일 : ${review.createdDate.year+1900}년${review.createdDate.month+1}월${review.createdDate.date}일<br/>
						평점 : ${review.score}
						여행지 코드 : ${review.itemId}
					</p>
					<p>${review.content}</p>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>