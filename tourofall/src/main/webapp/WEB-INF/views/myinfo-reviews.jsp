<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h3>${lastName}&nbsp;${firstName}님의 리뷰들</h3>
	<table>
		<c:forEach var="review" items="${reviews}">
			<tr>
				<td>
					<h4>${review.title}</h4>
					<p>
						작성일 : ${review.createdDate.year+1900}년${review.createdDate.month+1}월${review.createdDate.date}일<br/>
						평점 : ${review.score}<br/>
						여행지 이름 : ${review.itemTitle}
					</p>
					<p>${review.content}</p>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>