<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header/header.css"/>">
<div>
<!-- Navigation -->
    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="<c:url value="/"/>">
                    <i class="fa fa-play-circle"></i> Tour&nbsp;<span class="light">of</span>&nbsp;All
                </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
                <ul class="nav navbar-nav">
                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="<c:url value="/search/simple"/>">간편검색</a>
                    </li>
                    <li>
                    	<a class="page-scroll" href="<c:url value="/search/detail"/>">상세검색</a>
                    </li>
                    <li>
                    	<a class="page-scroll" href="<c:url value="/recommend"/>">추천여행지</a>
                    </li>
                    <li>
                    	<a class="page-scroll" href="<c:url value="/eval/evalmore"/>">추천더하기</a>
                    </li>
                    <c:if test="${pageContext.request.userPrincipal.name == null}">
                    	<li>
	 						<a class="page-scroll" href="<c:url value="/signin"/>">로그인</a>
	 					</li>
	 					<li>
	 						<a class="page-scroll" href="<c:url value="/signup"/>">회원가입</a>
	 					</li>
	 	
	 				</c:if>
	 				<c:if test="${pageContext.request.userPrincipal.name != null}">
	 					<li>
	 						<a class="page-scroll" href="<c:url value="/myinfo"/>">내정보</a>
	 					</li>
	 					<li>
	 						<a class="page-scroll" href="<c:url value="/signout"/>">로그아웃</a>
	 					</li>
	 				</c:if>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    <script type="text/javascript" src="<c:url value="/resources/js/header/header.js"/>"></script>
</div>