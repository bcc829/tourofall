<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/evaluation/evaluation.css"/>">
<script type="text/javascript" src="<c:url value="/resources/js/evaluation/eval-reco-common.js"/>"></script>
<div>
	<sec:authentication var="user" property="principal"/>
	<div class="js-result-section result-section result-section-hide">
		<div class="result-section-text">
		</div>
	</div>
	<div class="tab-content">
		<div id="user-evaluation" class="tab-pane fade in active">
			<div class="segment">
				<div class="container">
					<div id="eval-list" class="row">
						<c:forEach var = "evaluationRenderingModel" items="${evaluationRenderingModelsSet.evaluationRenderingModels}" varStatus="status">
							<div class="col-lg-3 col-md-4 col-sm-6">
								<sf:form name = "request-eval" action="${pageContext.request.contextPath}/eval/evalmore" commandName="evaluationRenderingModel${status.index}">
									<input type="hidden" name="${_csrf.parameterName}" value="${ _csrf.token}" />
									<input type="hidden" name="itemId" value="${evaluationRenderingModel.itemId}">
									<div class="eval-unit">
										<article class="eval-unit-card eval-unit-card-fixed eval-unit-card-has-img eval-unit-card-article">
											<div class="eval-unit-card-mask">
												<a href="<c:url value="/dest/info/${evaluationRenderingModel.itemId}"/>">
													<figure class="eval-unit-card-figure">
														<img alt="이미지 없음" src="${evaluationRenderingModel.imageUrl}" class="img-rounded"  onerror="this.style.display='none'">
													</figure>
												</a>
												<div class="eval-unit-card-content">
													<div class="eval-unit-card-content-title">
														${evaluationRenderingModel.title}
													</div>
													<div class="eval-unit-card-content-context">
														평점
													</div>
													<div class="rating">
														<sf:radiobutton path="score" id="star5-${status.index}" value="5"/><label class = "full" for="star5-${status.index}" title="Awesome - 5 stars"></label>
														<sf:radiobutton path="score" id="star4half-${status.index}" value="4.5"/><label class="half" for="star4half-${status.index}" title="Pretty good - 4.5 stars"></label>
														<sf:radiobutton path="score" id="star4-${status.index}" value="4"/><label class = "full" for="star4-${status.index}" title="Pretty good - 4 stars"></label>
														<sf:radiobutton path="score" id="star3half-${status.index}" value="3.5"/><label class="half" for="star3half-${status.index}" title="Meh - 3.5 stars"></label>
														<sf:radiobutton path="score" id="star3-${status.index}" value="3"/><label class = "full" for="star3-${status.index}" title="Meh - 3 stars"></label>
														<sf:radiobutton path="score" id="star2half-${status.index}" value="2.5"/><label class="half" for="star2half-${status.index}" title="Kinda bad - 2.5 stars"></label>
														<sf:radiobutton path="score" id="star2-${status.index}" value="2"/><label class = "full" for="star2-${status.index}" title="Kinda bad - 2 stars"></label>
														<sf:radiobutton path="score" id="star1half-${status.index}" value="1.5"/><label class="half" for="star1half-${status.index}" title="Meh - 1.5 stars"></label>
														<sf:radiobutton path="score" id="star1-${status.index}" value="1"/><label class = "full" for="star1-${status.index}" title="Sucks big time - 1 star"></label>
														<sf:radiobutton path="score"  id="starhalf-${status.index}" value="0.5"/><label class="half" for="starhalf-${status.index}" title="Sucks big time - 0.5 stars"></label>
    												</div>
    											</div>
    										</div>
    									</article>
    								</div>
    								<c:if test="${evaluationRenderingModelsSet.userScoreList[status.index] != 0}">
    									<div class="text-center">
    										평점 : ${evaluationRenderingModelsSet.userScoreList[status.index]}
    									</div>
    								</c:if>
    							</sf:form>
							</div>
						</c:forEach>
					</div>
					<div class="row">
						<div id="page-index" class="text-center">
							<input type="hidden" name="currentPageNo" value="1" />
							<input type="hidden" name="userId" value="${userId}" />
							<ul class="pagination pagination-lg">
								<c:if test="${evaluationRenderingModelsSet.pageNo-5 >= 1}">
									<li><a href="#">&lt;&lt;</a></li>
								</c:if>
								<c:if test="${evaluationRenderingModelsSet.pageNo-1 >= 1}">
									<li><a href="#">&lt;</a></li>
								</c:if>
								<c:forEach var="num" items="${evaluationRenderingModelsSet.indexList}">
									<li><a href="#">${num}</a></li>
								</c:forEach>
  									
  								<c:if test="${evaluationRenderingModelsSet.pageNo + 1 <= evaluationRenderingModelsSet.totalPage}">
									<li><a href="#">&gt;</a></li>
								</c:if>
								<c:if test="${evaluationRenderingModelsSet.pageNo + 5 <= evaluationRenderingModelsSet.totalPage}">
									<li><a href="#">&gt;&gt;</a></li>	
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="user-review" class="tab-pane fade">
			<div class="segment">
				<div class="container">
					<div class="row">
					
					</div>
				</div>
			</div>
		</div>
		<c:if test="${user.id == userId}">
			<div id="user-question" class="tab-pane fade">
				<h3>Menu 2</h3>
				<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem
					accusantium doloremque laudantium, totam rem aperiam.</p>
			</div>
			<div id="user-answer" class="tab-pane fade">
				<h3>Menu 3</h3>
				<p>Eaque ipsa quae ab illo inventore veritatis et quasi
					architecto beatae vitae dicta sunt explicabo.</p>
			</div>
		</c:if>
	</div>
</div>