<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/evaluation/evaluation.css"/>">
<script type="text/javascript" src="<c:url value="/resources/js/header/header.js"/>"></script>
<style>
	.center-container{
		text-align:center;
	}
</style>
<div>
	<div class="empty-header">
	</div>
	<div class="center-container">
		<h1>Please evaluate Tourist Attraction more</h1>
		<c:if test="${not empty fin}">
			<p class="lead">${fin}</p>
			<a href="<c:url value="/"/>" class="btn btn-default">그만하기</a>
		</c:if>
	</div>
	<div class="container">
		<div class="row">
		<!-- Example row of columns -->
			<div class="col-sm-2">
            	    <p class="copy-menu-title text-center">여행지 타입</p>
                	<div class="list-group">
                    	<a href="<c:url value="/eval/evalmore?itemTypeId=12"/>" class="list-group-item">관광지</a>
                   		<a href="<c:url value="/eval/evalmore?itemTypeId=14"/>" class="list-group-item">문화시설</a>
                   	 	<a href="<c:url value="/eval/evalmore?itemTypeId=15"/>" class="list-group-item">축제/공연/행사</a>
                   	 	<a href="<c:url value="/eval/evalmore?itemTypeId=25"/>" class="list-group-item">여행코스</a>
                   	 	<a href="<c:url value="/eval/evalmore?itemTypeId=28"/>" class="list-group-item">레포츠</a>
                   	 	<a href="<c:url value="/eval/evalmore?itemTypeId=32"/>" class="list-group-item">숙박</a>
                   	 	<a href="<c:url value="/eval/evalmore?itemTypeId=38"/>" class="list-group-item">쇼핑</a>
                   	 	<a href="<c:url value="/eval/evalmore?itemTypeId=39"/>" class="list-group-item">음식점</a>
                	</div>
        	</div>
        	<div class="col-sm-10">
				<sf:form  action = "${pageContext.request.contextPath}/eval/evalmore" method = "post" commandName="evaluationRegistrationsForm">
					<c:forEach var="evaluationRegistration" items="${evaluationRegistrationsForm.evaluationRegistrations}" varStatus="status">
						<div class="col-md-4 col-sm-6">
							<sf:hidden path="evaluationRegistrations[${status.index}].itemId"/>
							<div class="eval-unit">
								<article class="eval-unit-card eval-unit-card-fixed eval-unit-card-has-img eval-unit-card-article">
									<div class="eval-unit-card-mask">
										<a href="#">
											<figure class="eval-unit-card-figure">
												<img alt="이미지 없음" src="${evaluationRegistration.imageUrl}" class="img-rounded" >
												<sf:hidden path="evaluationRegistrations[${status.index}].imageUrl"/>
											</figure>
										</a>
										<div class="eval-unit-card-content">
											<div class="eval-unit-card-content-title">
												${evaluationRegistration.title}
											</div>
											<div class="eval-unit-card-content-context">
												평점
											</div>
											<div class="rating">
												<input type="radio" id="star5-${status.index}" name="evaluationRegistrations[${status.index}].score" value="5" /><label class = "full" for="star5-${status.index}" title="Awesome - 5 stars"></label>
										    	<input type="radio" id="star4half-${status.index}" name="evaluationRegistrations[${status.index}].score" value="4.5" /><label class="half" for="star4half-${status.index}" title="Pretty good - 4.5 stars"></label>
    											<input type="radio" id="star4-${status.index}" name="evaluationRegistrations[${status.index}].score" value="4" /><label class = "full" for="star4-${status.index}" title="Pretty good - 4 stars"></label>
    											<input type="radio" id="star3half-${status.index}" name="evaluationRegistrations[${status.index}].score" value="3.5" /><label class="half" for="star3half-${status.index}" title="Meh - 3.5 stars"></label>
    											<input type="radio" id="star3-${status.index}" name="evaluationRegistrations[${status.index}].score" value="3" /><label class = "full" for="star3-${status.index}" title="Meh - 3 stars"></label>
    											<input type="radio" id="star2half-${status.index}" name="evaluationRegistrations[${status.index}].score" value="2.5" /><label class="half" for="star2half-${status.index}" title="Kinda bad - 2.5 stars"></label>
    											<input type="radio" id="star2-${status.index}" name="evaluationRegistrations[${status.index}].score" value="2" /><label class = "full" for="star2-${status.index}" title="Kinda bad - 2 stars"></label>
    											<input type="radio" id="star1half-${status.index}" name="evaluationRegistrations[${status.index}].score" value="1.5" /><label class="half" for="star1half-${status.index}" title="Meh - 1.5 stars"></label>
    											<input type="radio" id="star1-${status.index}" name="evaluationRegistrations[${status.index}].score" value="1" /><label class = "full" for="star1-${status.index}" title="Sucks big time - 1 star"></label>
    											<input type="radio" id="starhalf-${status.index}" name="evaluationRegistrations[${status.index}].score" value="0.5" /><label class="half" for="starhalf-${status.index}" title="Sucks big time - 0.5 stars"></label>
    										</div>
										</div>
									</div>
								</article>
							</div>						
						</div>
					</c:forEach>
					<div class="col-sm-12 center-container">
						<input type="submit" value="제출하기" class="btn btn-primary">
						<a href="<c:url value="/"/>" class="btn btn-default">그만하기</a>
					</div>
				</sf:form>
				<div class="col-sm-12 center-container">
					<ul class="pager">
						<c:if test="${currentPageNo != 1}">
							<li class="previous"><a href="<c:url value="/eval/evalmore?itemTypeId=12&pageNo=${currentPageNo-1}"/>">Previous</a></li>
						</c:if>
						<c:if test="${currentPageNo == 1}">
							<li class="previous"><a href="#">Previous</a></li>
						</c:if>
						<c:if test="${currentPageNo != evaluationRegistrationsForm.totalPage}">
    						<li class="next"><a href="<c:url value="/eval/evalmore?itemTypeId=12&pageNo=${currentPageNo+1}"/>">Next</a></li>
    					</c:if>
    					<c:if test="${currentPageNo == evaluationRegistrationsForm.totalPage}">
    						<li class="next"><a href="#">Next</a></li>
    					</c:if>
  					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>