<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	.hero-spacer {
    	margin-top: 50px;
	}

	.hero-feature {
    	margin-bottom: 30px;
	}
</style>
<div>
	<div class="container">
		<div class="row text-center">
		
			<c:forEach var="review" items="${reviews}">
				<div class="col-md-4 col-sm-6 hero-feature">
					<div class="thumbnail">
						<p>
							여행지 이름 : ${review.itemTitle}
						</p>
						<img src="http://placehold.it/800x500">
						<div class="caption">
							<p class="lead">${review.title}</p>
							<div class="ratings">
                    	        <p>
									평점&nbsp;
									<span class="glyphicon glyphicon-star"></span>
									<span class="glyphicon glyphicon-star"></span>
									<span class="glyphicon glyphicon-star"></span>
									<span class="glyphicon glyphicon-star"></span>
									<span class="glyphicon glyphicon-star"></span>
									&nbsp;&nbsp;${review.createdDate.year+1900}년${review.createdDate.month+1}월${review.createdDate.date}일
								</p>        
                            </div>
							<p>${review.content}</p>
                        </div>
					</div>
				</div>
			</c:forEach>	
		</div>
	</div>
</div>