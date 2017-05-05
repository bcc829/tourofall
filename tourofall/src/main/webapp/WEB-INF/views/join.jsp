<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<sf:form commandName="registrationUserForm">
		<table>
			<tr>
				<td>아이디</td>
				<td>
					<c:if test="${not empty username}">
						<sf:input id="in_username" path="username" maxlength="45" value="${username}"/>
					</c:if>
					<c:if test="${empty username}">
						<sf:input id="in_username" path="username" maxlength="45"/>
					</c:if>
					<a id="duplicate" href="<c:url value="/join/duplicate?username=${user.username}"/>">중복확인</a>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<sf:errors path="username" /> 
						<c:choose>
							<c:when test="${checkResult == 1}">
								사용 가능한 아이디입니다.
							</c:when>
							<c:when test="${checkResult == 0}">
								중복되는 아이디입니다.
							</c:when>
							<c:when test="${checkResult == -1}">
								값을 입력해주세요.
							</c:when>
							<c:when test="${checkResult == -2}">
								중복확인을 해주세요.
							</c:when>
						</c:choose>
					<sf:hidden path="checked" value="${checkResult}"/>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><sf:password path="password" maxlength="15" /></td>
			</tr>
			<tr>
				<td></td>
				<td><sf:errors path="password" /></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><sf:input path="name" maxlength="45" /></td>
			</tr>
			<tr>
				<td></td>
				<td><sf:errors path="name" /></td>
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
					</sf:select> <sf:select path="month">
						<c:forEach var="month" items="${months}">
							<sf:option value="${month}" />
						</c:forEach>
					</sf:select> <sf:select path="date">
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
<script type="text/javascript">
	$("#in_username").keyup(function() {
		var value = $("#in_username").val();
		$("#duplicate").attr("href",'<c:url value="/join/duplicate?username=' + value + '"/>');
//		val = $("#duplicate").attr('href');
//		alert(val);
	});
</script>
