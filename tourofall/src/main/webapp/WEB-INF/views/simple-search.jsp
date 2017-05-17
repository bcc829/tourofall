<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/search/search.css"/>">
<script type="text/javascript" src="<c:url value="/resources/js/header/search-header.js"/>"></script>
<div>
	
	<div class="empty-header">
	</div>
	<div class="row-content">
		<div class="row-inner">
			<div class="search-results">
				<form class="search-form" action="<c:url value="/search/simple"/>" method="get">
					<label class="search-label search-heading copy-h5" >여행지를 입력하세요</label>
					<input class="search-query" name= "s" value="${query}"/>
					<button class="search-submit icon-search"><i class="fa fa-search" style="font-size:3.6rem"></i></button>
				</form>
				<h2 class="search-heading copy-h5">
					총 결과(
					<c:choose>
						<c:when test="${not empty items}">
							${totalCount}
						</c:when>
						<c:when test="${not empty item}">
							1
						</c:when>
						<c:otherwise>
							0
						</c:otherwise>
					</c:choose>	
					)
				</h2>
				<div class="search-result-list">
					<c:choose>
						<c:when test="${not empty items}">
							<c:forEach var="item" items="${items}">	
								<div class="card search-result copy-body">
									<a class="link-wrapper" href="<c:url value="/dest/info/basic/${item.contenttypeid}/${item.contentid}"/>">
										<div class="search-result-wrapper">
											<div class="media-img search-result-figure">
												<img class= "search-result-figure-image is-wider" alt="" src="${item.firstimage}">
											</div>
											<div class="search-result-meta search-result-meta-overflow media-body">
												<span class="search-result-type copy-caption">여행지</span>
												<h3 class="search-result-title copy-h1">${item.title}</h3>
												<p>${item.addr1}${item.addr2}</p>
											</div>
										</div>
									</a>
								</div>
							</c:forEach>
							<div class="text-center">
							<ul class="pagination pagination-lg">
								<c:if test="${pageNo-5 >= 1}">
									<li><a href="<c:url value="/search/simple?s=${query}&p=${pageNo-5}"/>">&lt;&lt;</a></li>
								</c:if>
								<c:if test="${pageNo-1 >= 1}">
									<li><a href="<c:url value="/search/simple?s=${query}&p=${pageNo-1}"/>">&lt;</a></li>
								</c:if>
								<c:forEach var="rowNum" items="${rowNumList}">
									<li><a href="<c:url value="/search/simple?s=${query}&p=${rowNum}"/>">${rowNum}</a></li>
								</c:forEach>
  								
  								<c:if test="${pageNo + 1 <= totalPage}">
									<li><a href="<c:url value="/search/simple?s=${query}&p=${pageNo+1}"/>">&gt;</a></li>
								</c:if>
								<c:if test="${pageNo + 5 <= totalPage}">
									<li><a href="<c:url value="/search/simple?s=${query}&p=${pageNo+5}"/>">&gt;&gt;</a></li>	
								</c:if>
							</ul>
							</div>
						</c:when>
						<c:when test="${not empty item}">
							<div class="card search-result copy-body">
								<a class="link-wrapper" href="<c:url value="/dest/info/basic/${item.contenttypeid}/${item.contentid}"/>">
									<div class="search-result-wrapper">
										<div class="media-img search-result-figure">
											<img class= "search-result-figure-image is-wider" alt="" src="${item.firstimage}">
										</div>
										<div class="search-result-meta search-result-meta-overflow media-body">
											<span class="search-result-type copy-caption">여행지</span>
											<h3 class="search-result-title copy-h1">${item.title}</h3>
											<p>${item.addr1}${item.addr2}</p>
										</div>
									</div>
								</a>
							</div>
							<ul class="pagination pagination-lg">
								<li><a href="#">1</a></li>
							</ul>
						</c:when>
						<c:otherwise>
							<c:if test="${empty items && empty item}">
								검색된 결과가 없습니다.	
							</c:if>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>