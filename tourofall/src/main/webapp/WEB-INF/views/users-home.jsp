<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>
	<h3>welcome My Info home</h3>
	<p>
		<sec:authentication var="user" property="principal"/>
		
	</p>
</div>
