<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common_style.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="resources/css/signup_style.css"/>">
</head>
<body>
<h2 class="sign-title"><a href="#">Tour Of All</a></h2>
<div class="form-container">
<sf:form action="${pageContext.request.contextPath}/signup" method="post" commandName="userRegistrationForm">
  <div class="container">
    <c:if test="${registrationUserForm.signInProvider == null}">
    	<h3>일반 가입자</h3>
  	 	<sf:input path="username" placeholder="New ID" class="input"/>
   		<sf:errors path="username" />
   	
   		<sf:password path="password"  placeholder="New Password"/>    
   		<sf:errors path="password" />
   	
   		<sf:password path="confirmPassword" placeholder="Repeat Password"/>
		<sf:errors path="confirmPassword" />
	
		<sf:input path="lastName" placeholder="Last Name" class="input name"/>
		<sf:errors path="lastName" />
	
		<sf:input path="firstName" placeholder="First Name" class="input name"/>
		<sf:errors path="firstName" />

    	<div class="check-container">
        	<label style="display:block;"><b>성별</b></label>
        	<span><sf:radiobutton path="gender" value="0"/>남성</span>
        	<span><sf:radiobutton path="gender" value="1"/>여성</span>
    	</div>
    </c:if>
    <c:if test="${registrationUserForm.signInProvider == 'facebook'}">
    	<h3>페이스북 가입자</h3>
    </c:if>
    <c:if test="${registrationUserForm.signInProvider == 'twitter'}">
    	<h3>트위터 가입자</h3>
    </c:if>
    <c:if test="${registrationUserForm.signInProvider != null}">
    	<sf:hidden path="username"/>
    	<sf:hidden path="password"/>
    	<sf:hidden path="confirmPassword"/>
    	<sf:hidden path="lastName"/>
    	<sf:hidden path="firstName"/>
    	<sf:hidden path="gender"/>
    	<sf:hidden path="signInProvider"/>
    </c:if>
    <div class="check-container">
        <label style="display:block;"><b>생일</b></label>   
        <sf:select path="year">
        	<c:forEach var="year" items="${years}">
        		<sf:option value="${year}" />
        	</c:forEach>
        </sf:select>년 
        		
        <sf:select path="month">
        	<c:forEach var="month" items="${months}">
        		<sf:option value="${month}" />
        	</c:forEach>
        </sf:select>월
        		
        <sf:select path="date">
        	<c:forEach var="date" items="${dates}">
        		<sf:option value="${date}" />
        	</c:forEach>
        </sf:select>일
        		
    </div>
  </div>

    <div class="clearfix">
      <button type="button" class="cancelbtn">Cancel</button>
      <button type="submit" class="signupbtn">Sign Up</button>
    </div>
  
</sf:form>
</div>
</body>
</html>