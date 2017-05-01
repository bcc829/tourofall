<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<table>
		<tr>
			<td>코스 총거리</td>
			<td>${introInfo.distance}</td>
		</tr>
		<tr>
			<td>문의 및 안내</td>
			<td>${introInfo.infocentertourcourse}</td>
		</tr>
		<tr>
			<td>코스 일정</td>
			<td>${introInfo.schedule}</td>
		</tr>
		<tr>
			<td>코스 총 소요시간</td>
			<td>${introInfo.taketime}</td>
		</tr>
		<tr>
			<td>코스 테마</td>
			<td>${introInfo.theme}</td>
		</tr>
	</table>
</div>