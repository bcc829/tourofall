<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<h1>${destInfo.title}</h1>
	<table >
		<tr>
			<td ><img alt="이미지 없음" src="${destInfo.firstimage2}"></td>
			<td>${destInfo.addr1}${destInfo.addr2}</td>
		</tr>
		<tr>
			<td colspan="2">개요</td>
		</tr>
		<tr>
			<td colspan="2">${destInfo.overview}</td>
		</tr>
		
	</table>
</div>