<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/evaluation/evaluation.css"/>">
<script type="text/javascript" src="<c:url value="/resources/js/header/small-header.js"/>"></script>
<div>
	
	<div class="empty-header">
	</div>
	<!--  <h1>${result}</h1> -->
	
	<div class="container">
		<div class="row">
			<sf:form  action = "${pageContext.request.contextPath}/eval/evalmore" method = "post" commandName="recommendationRenderingModelForm">
				<c:forEach var="recommendationRenderingModel" items="${recommendationRenderingModelForm.recommendationRenderingModels}" varStatus="status">
					<div class="col-lg-3 col-md-4 col-sm-6">
						<sf:hidden path="recommendationRenderingModels[${status.index}].itemId"/>
						<div class="eval-unit">
							<article class="eval-unit-card eval-unit-card-fixed eval-unit-card-has-img eval-unit-card-article">
								<div class="eval-unit-card-mask">
									<a href="#">
										<figure class="eval-unit-card-figure">
											<img alt="이미지 없음" src="${recommendationRenderingModel.imageUrl}" class="img-rounded" >
											<sf:hidden path="recommendationRenderingModels[${status.index}].imageUrl"/>
											<div class="eval-unit-card-score" style="color:red">
												예상 점수   ${recommendationRenderingModel.recommendScore}
											</div>
										</figure>
										
									</a>
									<div class="eval-unit-card-content">
										<div class="eval-unit-card-content-title">
											${recommendationRenderingModel.title}
										</div>
										<div class="eval-unit-card-content-context">
											평점
										</div>
										<div class="rating">
											<input type="radio" id="star5-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="5" /><label class = "full" for="star5-${status.index}" title="Awesome - 5 stars"></label>
									    	<input type="radio" id="star4half-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="4.5" /><label class="half" for="star4half-${status.index}" title="Pretty good - 4.5 stars"></label>
    										<input type="radio" id="star4-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="4" /><label class = "full" for="star4-${status.index}" title="Pretty good - 4 stars"></label>
    										<input type="radio" id="star3half-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="3.5" /><label class="half" for="star3half-${status.index}" title="Meh - 3.5 stars"></label>
    										<input type="radio" id="star3-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="3" /><label class = "full" for="star3-${status.index}" title="Meh - 3 stars"></label>
    										<input type="radio" id="star2half-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="2.5" /><label class="half" for="star2half-${status.index}" title="Kinda bad - 2.5 stars"></label>
    										<input type="radio" id="star2-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="2" /><label class = "full" for="star2-${status.index}" title="Kinda bad - 2 stars"></label>
    										<input type="radio" id="star1half-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="1.5" /><label class="half" for="star1half-${status.index}" title="Meh - 1.5 stars"></label>
    										<input type="radio" id="star1-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="1" /><label class = "full" for="star1-${status.index}" title="Sucks big time - 1 star"></label>
    										<input type="radio" id="starhalf-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="0.5" /><label class="half" for="starhalf-${status.index}" title="Sucks big time - 0.5 stars"></label>
    									</div>
									</div>
								</div>
							</article>
						</div>						
					</div>
				</c:forEach>
			</sf:form>
		</div>
	</div>
	
</div>