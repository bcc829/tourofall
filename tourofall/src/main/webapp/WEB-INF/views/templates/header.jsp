<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<a href="<c:url value="/search/simple"/>">간편검색</a>
	<a href="<c:url value="/search/detail"/>">상세검색</a>
	<a href="<c:url value="/recommendation"/>">추천여행지</a>
	<a href="<c:url value="/evalmore"/>">추천더하기</a>
	 <c:if test="${pageContext.request.userPrincipal.name == null}">
	 	<a href="<c:url value="/login"/>">로그인</a>
	 	<a href="<c:url value="/signup"/>">회원가입</a>
	 </c:if>
	 <c:if test="${pageContext.request.userPrincipal.name != null}">
	 	<a href="<c:url value="/myinfo"/>">내정보</a>
	 	<a href="<c:url value="/logout"/>">로그아웃</a>
	 </c:if>
</div>