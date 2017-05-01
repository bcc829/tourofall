<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<table>
		<tr>
			<td>관람 가능연령</td>
			<td>${introInfo.agelimit}</td>
		</tr>
		<tr>
			<td>예매처</td>
			<td>${introInfo.bookingplace}</td>
		</tr>
		<tr>
			<td>할인정보</td>
			<td>${introInfo.discountinfofestival}</td>
		</tr>
		<tr>
			<td>행사 종료일</td>
			<td>${introInfo.eventenddate}</td>
		</tr>
		<tr>
			<td>행사 홈페이지</td>
			<td>${introInfo.eventhomepage}</td>
		</tr>
		<tr>
			<td>행사 장소</td>
			<td>${introInfo.eventplace}</td>
		</tr>
		<tr>
			<td>행사 시작일</td>
			<td>${introInfo.eventstartdate}</td>
		</tr>
		<tr>
			<td>축제등급</td>
			<td>${introInfo.festivalgrade}</td>
		</tr>
		<tr>
			<td>행사장 위치안내</td>
			<td>${introInfo.placeinfo}</td>
		</tr>
		<tr>
			<td>공연시간</td>
			<td>${introInfo.playtime}</td>
		</tr>
		<tr>
			<td>행사 프로그램</td>
			<td>${introInfo.program}</td>
		</tr>
		<tr>
			<td>관람 소요시간</td>
			<td>${introInfo.spendtimefestival}</td>
		</tr>
		<tr>
			<td>주최자 정보</td>
			<td>${introInfo.sponsor1}</td>
		</tr>
		<tr>
			<td>주최자 연락처</td>
			<td>${introInfo.sponsor1tel}</td>
		</tr>
		<tr>
			<td>주관사 정보</td>
			<td>${introInfo.sponsor2}</td>
		</tr>
		<tr>
			<td>주관사 연락처</td>
			<td>${introInfo.sponsor2tel}</td>
		</tr>
		<tr>
			<td>부대행사</td>
			<td>${introInfo.subevent}</td>
		</tr>
		<tr>
			<td>이용요금</td>
			<td>${introInfo.usetimefestival}</td>
		</tr>
	</table>
</div>