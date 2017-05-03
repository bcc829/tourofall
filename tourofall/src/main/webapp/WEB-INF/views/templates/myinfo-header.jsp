<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<a href="<c:url value="/myinfo/detail"/>">상세정보</a>
	<a href="<c:url value="/myinfo/reviews"/>">내 리뷰</a>
	<a href="<c:url value="/myinfo/questions"/>">내 질문</a>
	<a href="<c:url value="/myinfo/answers"/>">내 댓글</a>
</div>