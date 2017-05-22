<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header/intro.css"/>">
<script type="text/javascript" src="<c:url value="/resources/js/header/middle-header.js"/>"></script>
<header class="myinfo-masthead masthead-normal">
	<div class = "image-container" style="background-image: url('${imageUrl}');">
	</div>
</header>
<nav class="navbar navbar-default">
  <div class="container">
    <ul class="nav navbar-nav">
    	<li><a href="<c:url value="/users/${userId}/questions"/>">질문 (${questionCount})</a></li>
    	<li><a href="<c:url value="/users/${userId}/answers"/>">댓글  (${answerCount})</a></li>
    	<li><a href="<c:url value="/users/${userId}/reviews"/>">리뷰 (${reviewCount})</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    	<sec:authentication var="user" property="principal"/>
    	<c:if test="${user.id == userId}">
    		<li><a href="<c:url value="/users/${userId}/setting"/>">내 정보설정</a></li>
    	</c:if>
    </ul>
  </div>
</nav>