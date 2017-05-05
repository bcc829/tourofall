<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h3>${lastName}&nbsp;${firstName}님의 개인정보</h3>
	<table>
		<tr>
			<th>아이디</th>
			<td>${user.username}</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>
				<c:if test="${user.gender}">여성</c:if>
				<c:if test="${!user.gender}">남성</c:if>
			</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>${user.birth.year+1900}년${user.birth.month+1}월${user.birth.date}일</td>
		</tr>
	</table>
</div>