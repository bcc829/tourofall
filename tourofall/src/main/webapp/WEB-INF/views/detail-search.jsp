<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<form action="<c:url value="/search/detail"/>" method="get">
		<input type="text" name="s" value="${query}"/> <input type="submit" value="search" />
		<table>
			<tr>
				<td>
					여행지
					<select name="ta">
						<option value="">타입선택</option>
						<c:forEach var="tourType" items="${tourTypes}">
							<option value="${tourType.itemTypeNum}">${tourType.itemTypeName}</option>
						</c:forEach>
					</select>
				<td/>
				<td>
					서비스 분류
					<select name="sc">
						<option value="">분류</option>
						<c:forEach var="service" items="${services}">
							<option value="${service.categoryCode}">${service.categoryName}</option>
						</c:forEach>
					</select>
				</td>
				<td>
					지역
					<select name="a">
						<option value="">지역선택</option>	
						<c:forEach var="area" items="${areas}">
							<option value="${area.areaNum}">${area.areaName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
	</form>
	
	<c:choose>
			<c:when test="${not empty items}">
				<table>
					<c:forEach var="item" items="${items}">
						<tr>
							<td><a href="<c:url value="/dest/info/basic/${item.contenttypeid}/${item.contentid}"/>"><img
									style="width: 100px; height: 100px" alt="이미지 없음"
									src="${item.firstimage}"><br/>${item.title}</a></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="2">
							<c:if test="${pageNo-5 >= 1}">
								<a href="<c:url value="/search/detail?s=${query}&p=${pageNo-5}"/>">&lt;&lt;</a>
							</c:if>
							&nbsp;
							<c:if test="${pageNo-1 >= 1}">
								<a href="<c:url value="/search/detail?s=${query}&p=${pageNo-1}"/>">&lt;</a>
							</c:if>
							&nbsp;
							<c:forEach var="rowNum" items="${rowNumList}">
								<a href="<c:url value="/search/detail?s=${query}&p=${rowNum}"/>">${rowNum}</a>&nbsp;&nbsp;
							</c:forEach>
							
							
							<c:if test="${pageNo + 1 <= totalPage}">
								<a href="<c:url value="/search/detail?s=${query}&p=${pageNo+1}"/>">&gt;</a>
							</c:if>
							&nbsp;
							<c:if test="${pageNo + 5 <= totalPage}">
								<a href="<c:url value="/search/detail?s=${query}&p=${pageNo+5}"/>">&gt;&gt;</a>	
							</c:if>
								
						</td>
					</tr>
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
							<a href="<c:url value="/dest/info/basic/${item.contenttypeid}/${item.contentid}"/>">
								<img style="width: 100px; height: 100px" alt="이미지 없음"src="${item.firstimage}">
							</a>
						</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<c:if test="${empty items && empty item}">
					검색된 결과가 없습니다.	
				</c:if>
			</c:otherwise>
		</c:choose>
</div>