<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<a href="<c:url value="/dest/info/basic?contentId=${itemId}&contentTypeId=${itemTypeId}"/>">개요</a>
	<a href="<c:url value="/dest/info/intro?contentId=${itemId}&contentTypeId=${itemTypeId}"/>">소개</a>
	<a href="<c:url value="/dest/info/detail?contentId=${itemId}&contentTypeId=${itemTypeId}"/>">상세 정보</a>
	<a href="<c:url value="/dest/info/image?contentId=${itemId}&contentTypeId=${itemTypeId}"/>">이미지</a>
	
</div>