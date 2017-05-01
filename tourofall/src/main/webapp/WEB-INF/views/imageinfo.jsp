<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<table>
		<c:choose>
			<c:when test="${not empty imageInfoes}">
				<c:forEach var="image" items="${imageInfoes}">
					<tr>
						<td><img alt="이미지 없음" src="${image.smallimageurl}"></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${not empty imageInfo}">
				<tr>
					<td><img alt="이미지 없음" src="${imageInfo.smallimageurl}"></td>
				</tr>
			</c:when>
		</c:choose>
	</table>
</div>