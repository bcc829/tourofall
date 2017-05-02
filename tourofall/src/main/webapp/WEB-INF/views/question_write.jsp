<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div>
	<sf:form commandName="question" action="${pageContext.request.contextPath}/dest/info/qna/question/write">
		<h3>질문 작성하기</h3>
		<table>
			<tr>
				<td>작성자</td>
				<td>${username}</td>
				<sf:hidden path="itemId"/>
				<sf:hidden path="itemTypeId"/>
			</tr>
			<tr>
				<td>제목</td>
				<td><sf:input path="title"/></td>
			</tr>
			<tr>
				<td></td>
				<td><sf:errors path="title"/></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><sf:textarea cols="15" rows="15" path="content"/></td>
			</tr>
			<tr>
				<td></td>
				<td><sf:errors path="content" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="등록하기" /></td>
			</tr>
		</table>
	</sf:form>
</div>