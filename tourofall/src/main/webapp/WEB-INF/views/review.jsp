<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div>
	<sf:form commandName="review" action="${pageContext.request.contextPath}/dest/info/review">
		<table>
			<tr>
				<td>작성자</td>
				<td>${username}</td>
				
				<sf:hidden path="itemId"/>
			</tr>
			<tr>
				<td>제목</td>
				<td><sf:input path="title"/></td>
			</tr>
			<tr>
				<td>평점</td>
				<td><sf:input path="score"/></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><sf:textarea cols="15" rows="15" path="content"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="저장" /></td>
			</tr>
		</table>
	</sf:form>
</div>