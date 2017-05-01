<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<table>
		<c:choose>
			<c:when test="${not empty detailInfoes}">
				<c:forEach var="info" items="${detailInfoes}">
					<tr>
						<th>${info.infoname}</th>
						<td>${info.infotext}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${not empty detailInfo}">
				<tr>
					<th>${detailInfo.infoname}</th>
					<td>${detailInfo.infotext}</td>
				</tr>
			</c:when>
		</c:choose>
	</table>
</div>