<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h3>${lastName}&nbsp;${firstName}님의 평가 목록</h3>
	<table>
		<c:forEach var="evaluation" items="${evaluations}">
			<tr>
				<td>
					<p>
						Content Id: ${evaluation.itemId}<br/>
						점수: ${evaluation.score}<br/>
					</p>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
