<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header/intro.css"/>">
<script type="text/javascript" src="<c:url value="/resources/js/header/middle-header.js"/>"></script>
<header class="myinfo-masthead masthead-normal">
	<div class = "image-container" style="background-image: url('http://tong.visitkorea.or.kr/cms/resource/81/1949681_image2_1.jpg');">
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
    	<li><a href="<c:url value="/myinfo/setting"/>">내 정보설정</a></li>
    </ul>
  </div>
</nav>