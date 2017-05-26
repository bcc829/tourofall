<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.5.1/fotorama.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common/page-structure.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/destination/destination.css"/>">

<div>
	<div class="segment">
	<div class="container">
		<h2 class="segment-heading">${basicInfo.title}</h2>
		
		<div id="basicinfo" class="tabcontent">
			<div class="row">
				<div class="col-sm-6">
					<div class="image-box">
						<img alt="" src="${basicInfo.firstimage}">
					</div>
				</div>
				
				<div class="col-sm-6">
					<div class="panel panel-blue">
						<div class="panel-heading">
							기본정보
						</div>
						<div class="panel-body">
							<p>
								우편 번호 : ${basicInfo.zipcode}<br/>
								주소 : ${basicInfo.addr1}${basicInfo.addr2}<br/>
								홈페이지 : ${basicInfo.homepage}
							</p>
						</div>
					</div>
				</div>
				
			</div>
			<hr />
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-green">
                        <div class="panel-heading">
							개요
                        </div>
                        <div class="panel-body">
                            <p>
                           		${basicInfo.overview}
                            </p>
                        </div>
                        
                    </div>
				</div>
			</div>
			<hr />
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-yellow">
                        <div class="panel-heading">
							지도
							<!-- 
							<div class="pull-right"><button onclick="resetMap()" class="btn btn-default btn-xs">새로고침</button></div>
							 -->
                        </div>
                        <div class="panel-body">
                            <div id="map">
                            	<p id="mapx" title="${basicInfo.mapx}"></p>
                            	<p id="mapy" title="${basicInfo.mapy}"></p>
                            </div>
                        </div>
                    </div>
				</div>
			</div>
			
		</div>
		<div id="introinfo" class="tabcontent">
			<div class="row">
				<table class="table">
					<tbody>
						<!-- 여행지  -->
						<c:if test="${not empty introInfo.infocenter}">
							<tr>
								<td>문의 및 안내</td>
								<td>${introInfo.infocenter}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.expguide}">
							<tr>
								<td>체험 안내</td>
								<td>${introInfo.expguide}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.restdate}">
							<tr>
								<td>휴일</td>
								<td>${introInfo.restdate}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.expagerange}">
							<tr>
								<td>체험가능 연령</td>
								<td>${introInfo.expagerange}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.accomcount}">
							<tr>
								<td>수용인원</td>
								<td>${introInfo.accomcount}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.usetime}">
							<tr>
								<td>이용시간</td>
								<td>${introInfo.usetime}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.parking}">
							<tr>
								<td>주차시설</td>
								<td>${introInfo.parking}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.chkbabycarriage}">
							<tr>
								<td>유모차 대여여부</td>
								<td>${introInfo.chkbabycarriage}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.chkpet}">
							<tr>
								<td>애완동물 동반 여부</td>
								<td>${introInfo.chkpet}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.chkcreditcard}">
							<tr>
								<td>신용카드 사용 여부</td>
								<td>${introInfo.chkcreditcard}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.useseason}">
							<tr>
								<td>이용시기</td>
								<td>${introInfo.useseason}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.opendate}">
							<tr>
								<td>개장일</td>
								<td>${introInfo.opendate}</td>
							</tr>
						</c:if>
						<c:if test="${introInfo.heritage1 != 0 && not empty introInfo.heritage1}">
							<tr>
								<td>세계문화유산 등록여부</td>
								<td>등록됨</td>
							</tr>
						</c:if>
						<c:if test="${introInfo.heritage2 != 0 && not empty introInfo.heritage2}">
							<tr>
								<td>세계자연유산 등록여부</td>
								<td>등록됨</td>
							</tr>
						</c:if>
						<c:if test="${introInfo.heritage3 != 0 && not empty introInfo.heritage3}">
							<tr>
								<td>세계기록유산 등록여부</td>
								<td>등록됨</td>
							</tr>
						</c:if>
						
						<!-- 문화시설  -->
						
						<c:if test="${not empty introInfo.infocenterculture}">
							<tr>
								<td>문의 및 안내</td>
								<td>${introInfo.infocenterculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.scale}">
							<tr>
								<td>규모</td>
								<td>${introInfo.scale}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.usetimeculture}">
							<tr>
								<td>이용시간</td>
								<td>${introInfo.usetimeculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.restdateculture}">
							<tr>
								<td>쉬는날</td>
								<td>${introInfo.restdateculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.usefee}">
							<tr>
								<td>이용요금</td>
								<td>${introInfo.usefee}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.parkingculture}">
							<tr>
								<td>주차시설</td>
								<td>${introInfo.parkingculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.parkingfee}">
							<tr>
								<td>주차요금</td>
								<td>${introInfo.parkingfee}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.chkbabycarriageculture}">
							<tr>
								<td>유모차 대여 여부</td>
								<td>${introInfo.chkbabycarriageculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.chkcreditcardculture}">
							<tr>
								<td>신용카드 가능 여부</td>
								<td>${introInfo.chkcreditcardculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.chkpetculture}">
							<tr>
								<td>애완동물 가능 여부</td>
								<td>${introInfo.chkpetculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.accomcountculture}">
							<tr>
								<td>수용인원</td>
								<td>${introInfo.accomcountculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.discountinfo}">
							<tr>
								<td>할인정보</td>
								<td>${introInfo.discountinfo}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.spendtime}">
							<tr>
								<td>관람 소요시간</td>
								<td>${introInfo.spendtime}</td>
							</tr>
						</c:if>
						
						<!-- 레포츠  -->
						<c:if test="${not empty introInfo.infocenterleports}">
							<tr>
								<td>문의 및 안내</td>
								<td>${introInfo.infocenterleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.scaleleports}">
							<tr>
								<td>규모</td>
								<td>${introInfo.scaleleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.accomcountleports}">
							<tr>
								<td>수용인원</td>
								<td>${introInfo.accomcountleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.openperiod}">
							<tr>
								<td>개장기간</td>
								<td>${introInfo.openperiod}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.chkbabycarriageleports}">
							<tr>
								<td>유모차 대여 여부</td>
								<td>${introInfo.chkbabycarriageleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.chkcreditcardleports}">
							<tr>
								<td>신용카드 가능 여부</td>
								<td>${introInfo.chkcreditcardleports}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.chkpetleports}">
							<tr>
								<td>애완동물 가능 여부</td>
								<td>${introInfo.chkpetleports}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.expagerangeleports}">
							<tr>
								<td>체험 가능연령</td>
								<td>${introInfo.expagerangeleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.parkingfeeleports}">
							<tr>
								<td>주차요금</td>
								<td>${introInfo.parkingfeeleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.parkingleports}">
							<tr>
								<td>주차시설</td>
								<td>${introInfo.parkingleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.reservation}">
							<tr>
								<td>예약안내</td>
								<td>${introInfo.reservation}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.restdateleports}">
							<tr>
								<td>쉬는날</td>
								<td>${introInfo.restdateleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.usefeeleports}">
							<tr>
								<td>입장료</td>
								<td>${introInfo.usefeeleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.usetimeleports}">
							<tr>
								<td>이용시간</td>
								<td>${introInfo.usetimeleports}</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<hr/>
			
		</div>
		<div id= "detailinfo" class="tabcontent">
			<div class="row">
				<c:choose>
					<c:when test="${not empty detailInfoes}">
						<c:forEach var="info" items="${detailInfoes}">
							<div class="col-lg-3 col-md-4 col-sm-6">
								<div class="panel panel-green">
									<div class="panel-heading">${info.infoname}</div>
									<div class="panel-body">${info.infotext}</div>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:when test="${not empty detailInfo}">
						<div class="col-lg-3 col-md-4 col-sm-6">
							<div class="panel panel-green">
								<div class="panel-heading">
									${detailInfo.infoname}
								</div>
								<div class="panel-body">
									${detailInfo.infotext}
								</div>
							</div>
						</div>
					</c:when>
				</c:choose>
			</div>
		</div>
		<div id= "imageinfo" class="tabcontent">
			
			<div class="fotorama" data-arrows="true" data-width="60%" data-ratio="700/467" data-allowfullscreen="true" data-nav="thumbs">
				<c:choose>
					<c:when test="${not empty imageInfoes}">
						<c:forEach var="image" items="${imageInfoes}">
							<a href="${image.originimgurl}"><img src="${image.smallimageurl}" data-caption="호수공원" /></a>
						</c:forEach>
					</c:when>
					<c:when test="${not empty imageInfo}">
						<a href="${imageInfo.originimgurl}"><img src="${imageInfo.smallimageurl}" data-caption="호수공원" /></a>
					</c:when>
				</c:choose>                  
             </div> 
		</div>
		
	</div>
	</div>
</div>
<script src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.5.1/fotorama.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA-UDsWJx5dkJpLf4HitN6Uy4-JWADLu14&sensor=true"></script>
<script type="text/javascript" src="<c:url value="/resources/js/destination/destination.js"/>"></script>