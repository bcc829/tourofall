<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<table>
		<c:choose>
			<c:when test="${not empty detailInfoes}">
				<c:forEach var="info" items="${detailInfoes}">
					<tr>
						<td>${info.subdetailimg}<br />${info.subdetailalt}</td>
					</tr>
					<tr>
						<td>코스명</td>
						<td>${info.subname}</td>
					</tr>
					<tr>
						<td>개요</td>
						<td>${info.subdetailoverview}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${not empty detailinfo}">
				<tr>
					<td>${detailinfo.subdetailimg}<br />${detailinfo.subdetailalt}</td>
				</tr>
				<tr>
					<td>코스명</td>
					<td>${detailinfo.subname}</td>
				</tr>
				<tr>
					<td>개요</td>
					<td>${detailinfo.subdetailoverview}</td>
				</tr>
			</c:when>
		</c:choose>
	</table>
</div>