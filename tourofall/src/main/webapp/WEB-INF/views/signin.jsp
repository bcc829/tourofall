<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/sign-common.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/signin.css"/>">
<title>Tour of all에 로그인</title>
</head>
<body>
	<h2 class="sign-title"><a href="#">Tour Of All</a></h2>
	<div class="sign-form-container">
        <form action="<c:url value="/signin/authenticate"/>" method="post" class="form-horizontal">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        	<c:if test="${not empty signoutMsg}">
        			<p style="color:blue">${signoutMsg}</p>
        		</c:if>
            <div class="image-container">
                <img src="<c:url value="/resources/images/default_profile.jpg"/>" alt="Avatar" class="avatar">
            </div>

            <div class="sign-container">
                <div class="form-group">
                	<input type="text" class="form-control" placeholder="ID" name="username" required>
				</div>
                
                <div class="form-group">
                	<input type="password" class="form-control" placeholder="Password" name="password" required>
                </div>
        		<c:if test="${not empty errorMsg}">
        			<p style="color:red">${errorMsg}</p>
        		</c:if>
                <button type="submit">Login</button>
                
            </div>
		</form>
		<div class="facebook-button-container">
        	<form action="<c:url value="/signin/facebook"/>" method="post">
        		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        		<div class="sign-container">
					<button type="submit"><i class="fa fa-facebook-square" style="font-size:2rem"></i>&nbsp;&nbsp;&nbsp;Sign in with Facebook</button>
				</div>
			</form>
		</div>
        <div class="bottom-container" style="background-color:#f1f1f1">
			<span class="psw"><a href="#">비밀번호 잃어버리셨나요?</a></span>
			<span class="component-right"><input type="checkbox" checked="checked"> Remember me</span>
        </div>
        
    </div>
</body>
</html>