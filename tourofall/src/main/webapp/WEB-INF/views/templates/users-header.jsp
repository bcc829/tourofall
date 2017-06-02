<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header/intro.css"/>">
<script type="text/javascript" src="<c:url value="/resources/js/header/middle-header.js"/>"></script>
<sec:authentication var="user" property="principal"/>
<header class="myinfo-masthead masthead-normal">
	<div class = "image-container" style="background-image: url('${imageUrl}');">
		<div class="masthead-text-wrapper">		
			<div class="masthead-capation masthead-capation-left">
				
			</div>
			<div class="masthead-capation masthead-capation-right">
				
			</div>
			<div class="masthead-capation masthead-capation-bottom">
				유저 이름 : ${user.lastName}${user.firstName}
			</div>
		</div>
	</div>
</header>
<nav class="container">
	<ul class="nav nav-tabs">
		<sec:authentication var="user" property="principal"/>
		<li class="active"><a href="#user-evaluation">가본 곳<span class="badge">${evaluationCount}</span></a></li>
    	<li><a href="#user-review">리뷰<span class="badge">${reviewCount}</span></a></li>
    	<c:if test="${user.id == userId}">
    		<li><a href="#user-question">질문<span class="badge">${questionCount}</span></a></li>
    		<li><a href="#user-answer">댓글<span id="answer-badge" class="badge">${answerCount}</span></a></li>
    		<li><a href="#mysetting">내 정보설정</a></li>
    	</c:if>
	</ul>
</nav>