<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h3>${userAuthenticationDetails.lastName}&nbsp;${userAuthenticationDetails.firstName}님의 개인정보</h3>
	<table>
		<tr>
			<th>성별</th>
			<td>
				<c:if test="${userAuthenticationDetails.gender}">여성</c:if>
				<c:if test="${!userAuthenticationDetails.gender}">남성</c:if>
			</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>${userAuthenticationDetails.birth.year+1900}년${userAuthenticationDetails.birth.month+1}월${userAuthenticationDetails.birth.date}일</td>
		</tr>
	</table>
</div>