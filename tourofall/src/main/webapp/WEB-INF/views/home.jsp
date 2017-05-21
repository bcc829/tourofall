<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header/slide-intro.css"/>">
<script type="text/javascript" src="<c:url value="/resources/js/header/big-header.js"/>"></script>
<div>
	<header class="masthead masthead-normal">
			<div class="slideshow slide-fade">	
				<div class="slideshow-images">
					<c:forEach var ="bestDestinationRederingModel" items="${bestDestinationRederingModels}" varStatus="status">
						<div id="bg${status.index+1}" class="slideshow-slide" style="background-image: url('${bestDestinationRederingModel.imageUrl}');">
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="masthead-text-wrapper">
				<div class="masthead-text masthead-responsive-mask">
					
					<div class="masthead-straplines">
						<c:forEach var ="bestDestinationRederingModel" items="${bestDestinationRederingModels}" varStatus="status">
							<div id="ms${status.index+1}" class="masthead-strapline">
								<div class="masthead-title">
									오늘의 ${bestDestinationRederingModel.destinationType}
								</div>
								<div class="masthead-subtitle">
									<a href="#" >${bestDestinationRederingModel.title}</a>
								</div>
								<div class="dest-content">
									주소 : ${bestDestinationRederingModel.address}<br/>
									평균 평점 : ${bestDestinationRederingModel.meanScore}
								</div>
							</div>
						</c:forEach>
					</div>	
				</div>
			</div>
	</header>
</div>