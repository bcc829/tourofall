<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<sf:form commandName="newUser">
		<table>
			<tr>
				<td>아이디</td>
				<td><sf:input path="username" /></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><sf:password path="password" /></td>
			</tr>

			<tr>
				<td>이름</td>
				<td><sf:input path="name" /></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><sf:radiobutton path="gender" value="0" />남<sf:radiobutton
						path="gender" value="1" />여</td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><sf:select path="year">
						<c:forEach var="year" items="${years}">
							<sf:option value="${year}" />
						</c:forEach>
					</sf:select> 
					<sf:select path="month">
						<c:forEach var="month" items="${months}">
							<sf:option value="${month}" />
						</c:forEach>
					</sf:select> 
					<sf:select path="date">
						<c:forEach var="date" items="${dates}">
							<sf:option value="${date}" />
						</c:forEach>
					</sf:select></td>
			</tr>
			<tr>
				<td><input type="submit" value="회원가입" /></td>
			</tr>
		</table>
	</sf:form>
</div>