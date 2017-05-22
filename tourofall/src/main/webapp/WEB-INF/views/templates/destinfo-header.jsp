<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<a href="<c:url value="/dest/info/basic//${itemId}"/>">개요</a>
	<a href="<c:url value="/dest/info/intro/${itemTypeId}/${itemId}"/>">소개</a>
	<a href="<c:url value="/dest/info/detail/${itemTypeId}/${itemId}"/>">상세 정보</a>
	<a href="<c:url value="/dest/info/image/${itemTypeId}/${itemId}"/>">이미지</a>
</div>