<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<form action="<c:url value="/search"/>" method="get">
		<input type="text" name="s" /> <input type="submit" value="search" />
	</form>
	<div>

		<c:choose>
			<c:when test="${not empty items}">
				<table>
					<tr>
						<th>여행지 이름</th>
						<th></th>
					</tr>

					<c:forEach var="item" items="${items}">
						<tr>
							<td>${item.title}</td>
							<td><a href="<c:url value="/dest/${item.contentid}"/>"><img
									style="width: 100px; height: 100px" alt="이미지 없음"
									src="${item.firstimage}"></a></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:when test="${not empty item}">
				<table>
					<tr>
						<th>여행지 이름</th>
						<th></th>
					</tr>
					<tr>
						<td>${item.title}</td>
						<td>
							<a href="<c:url value="/dest/${item.contentid}"/>">
								<img style="width: 100px; height: 100px" alt="이미지 없음"src="${item.firstimage}">
							</a>
						</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				검색된 결과가 없습니다.
			</c:otherwise>
		</c:choose>
			<!-- 
			<c:if test="${pageNo/5 > 1}">&lt;&lt;</c:if>
			<c:if test="${pageNo >'1'}">&lt;</c:if>
			<c:choose>
				<c:when test="${pageNo - pageNo/5+5 < totalCount/5}">
					<c:forEach var="i" begin="${(pageNo+1)/5}" end="${(pageNo+1)/5+4}">
						<c:out value="${i}" /> 
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="i" begin="${(pageNo+1)/5}" end="${totalCount}">
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
			<c:if test="${pageNo+1 <=totalCount}">&gt;</c:if>
			<c:if test="${pageNo + 5 < totalCount}">&gt;&gt;</c:if>
			 -->
	</div>
</div>