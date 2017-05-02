<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h2>${basicInfo.title}</h2>
	<table >
		<tr>
			<td ><img alt="이미지 없음" src="${basicInfo.firstimage2}"></td>
			<td>${basicInfo.addr1}${basicInfo.addr2}</td>
		</tr>
		<tr>
			<th colspan="2">개요</th>
		</tr>
		<tr>
			<td colspan="2">${basicInfo.overview}</td>
		</tr>
	</table>
</div>