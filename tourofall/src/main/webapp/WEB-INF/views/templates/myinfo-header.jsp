<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/sub-header/myinfo.css"/>">
<header class="myinfo-intro">
	<div class="myinfo-body">
	</div>
</header>
<nav class="navbar navbar-default">
  <div class="container">
    <ul class="nav navbar-nav">
    	<li><a href="<c:url value="/myinfo/questions"/>">질문 (${questionCount})</a></li>
    	<li><a href="<c:url value="/myinfo/answers"/>">댓글  (${answerCount})</a></li>
    	<li><a href="<c:url value="/myinfo/reviews"/>">리뷰 (${reviewCount})</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    	<li><a href="<c:url value="/myinfo/detail"/>">내 정보설정</a></li>
    </ul>
  </div>
</nav>