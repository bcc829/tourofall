<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<a href="<c:url value="/myinfo/questions"/>">질문 (${questionCount})</a>
	<a href="<c:url value="/myinfo/answers"/>">댓글  (${answerCount})</a>
	<a href="<c:url value="/myinfo/reviews"/>">리뷰 (${reviewCount})</a>
	<a href="<c:url value="/myinfo/evaluations"/>">평가 (${evaluationCount})</a>
	<a href="<c:url value="/myinfo/detail"/>">내 정보 </a>
</div>