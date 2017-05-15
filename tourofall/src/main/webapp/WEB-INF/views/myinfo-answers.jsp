<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<table>
		<c:forEach var="answer" items="${answers}">
			<tr>
				<td>
					<p>
						댓글 번호: ${answer.id}<br/>
						질문 제목: ${answer.questionTitle}<br/>
						댓글 내용: ${answer.content}
					</p>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>