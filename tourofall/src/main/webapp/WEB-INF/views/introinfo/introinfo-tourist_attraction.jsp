<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<table>
		<tr>
			<td>수용인원</td>
			<td>${introInfo.accomcount}</td>
		</tr>
		<tr>
			<td>세계문화유산 등록여부</td>
			<td>${introInfo.heritage1}</td>
		</tr>
		<tr>
			<td>세계자연유산 등록여부</td>
			<td>${introInfo.heritage2}</td>
		</tr>
		<tr>
			<td>세계기록유산 등록여부</td>
			<td>${introInfo.heritage3}</td>
		</tr>
		<tr>
			<td>체험 안내</td>
			<td>${introInfo.expguide}</td>
		</tr>
		<tr>
			<td>체험 체험가능 연령</td>
			<td>${introInfo.expagerange}</td>
		</tr>
		<tr>
			<td>문의 및 안내</td>
			<td>${introInfo.infocenter}</td>
		</tr>
		<tr>
			<td>휴일</td>
			<td>${introInfo.restdate}</td>
		</tr>
		<tr>
			<td>이용시간</td>
			<td>${introInfo.usetime}</td>
		</tr>
		<tr>
			<td>이용시기</td>
			<td>${introInfo.useseason}</td>
		</tr>
		<tr>
			<td>개장일</td>
			<td>${introInfo.opendate}</td>
		</tr>
		<tr>
			<td>주차시설</td>
			<td>${introInfo.parking}</td>
		</tr>
		<tr>
			<td>유모차 대여여부</td>
			<td>${introInfo.chkbabycarriage}</td>
		</tr>
		<tr>
			<td>애완동물 동반 여부</td>
			<td>${introInfo.chkpet}</td>
		</tr>
		<tr>
			<td>신용카드 사용 여부</td>
			<td>${introInfo.chkcreditcard}</td>
		</tr>
	</table>
</div>