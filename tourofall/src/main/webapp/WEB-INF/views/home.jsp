<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header/slide-intro.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/home/home.css"/>">
<script type="text/javascript" src="<c:url value="/resources/js/header/big-header.js"/>"></script>
<div>
	<header class="masthead masthead-normal">
			<div class="slideshow slide-fade">	
				<div class="slideshow-images">
					<c:forEach var ="todayDestinationRenderingModel" items="${todayDestinationRenderingModels}" varStatus="status">
						<div id="bg${status.index+1}" class="slideshow-slide" style="background-image: url('${todayDestinationRenderingModel.imageUrl}');">
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="masthead-text-wrapper">
				<div class="masthead-text masthead-responsive-mask">
					
					<div class="masthead-straplines">
						<c:forEach var ="todayDestinationRenderingModel" items="${todayDestinationRenderingModels}" varStatus="status">
							<div id="ms${status.index+1}" class="masthead-strapline">
								<div class="masthead-title">
									오늘의 ${todayDestinationRenderingModel.destinationType}
								</div>
								<div class="masthead-subtitle">
									<a href="<c:url value="/dest/info/basic/${todayDestinationRenderingModel.itemId}"/>" >${todayDestinationRenderingModel.title}</a>
								</div>
								<div class="dest-content">
									주소 : ${todayDestinationRenderingModel.address}<br/>
									평균 평점 : ${todayDestinationRenderingModel.meanScore}
								</div>
							</div>
						</c:forEach>
					</div>	
				</div>
			</div>
	</header>
	<section class="page-container page-container-ready" >
		<div class="segment">
			<div class="container">
				<article class="intro">
					<h2 class="segment-heading">인기 베스트 4</h2>
					<div class="rank-container">
						<div class="rank-list-container">
							<ul class="rank-list" style="margin-top: 0px;transform: translate3d(0px, 0px, 0px);">
								<c:forEach var="bestDestinationRenderingModel" items="${bestDestinationRenderingModels}" varStatus="status">
									<li class="rank-item">
										<a href="<c:url value="/dest/info/basic/${bestDestinationRenderingModel.itemId}"/>" class="rank-item-imagecard">
											<div class="rank-item-imagecard-image" style="background-image: url('${bestDestinationRenderingModel.imageUrl}');">
												<div class="rank-item-imagecard-content">
													<div class="rank-item-imagecard-marker">${status.index + 1}</div>
														<p class="rank-item-imagecard-subtitle">
															${bestDestinationRenderingModel.description}
														</p>
														<h3 class="rank-item-imagecard-title">
															${bestDestinationRenderingModel.title}
														</h3>
												</div>
											</div>
										</a>
									</li>								
								</c:forEach>
							</ul>
						</div>
						<div class="rank-navigation">
							<button class="rank-less" disabled>
								<i class="rank-less-icon rank-icon-left">&lt</i>
							</button>
							<button class="rank-more">
								<i class="rank-more-icon rank-icon-right">&gt</i>
							</button>
						</div>
					</div>						
				</article>
			</div>
		</div>
	</section>
</div>