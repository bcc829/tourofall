function openDestInfo(evt, contentCategoryName) {
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("menu-item");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" selected", "");
	}
	document.getElementById(contentCategoryName).style.display = "block";
	evt.currentTarget.className += " selected";
}

document.getElementById("default").click();

function init() {


    var mapOptions = {

        zoom: 15,


        center: new google.maps.LatLng(mapy,mapx), // New York
    };


    var mapElement = document.getElementById('map');


    map = new google.maps.Map(mapElement, mapOptions);
    

    var image = 'http://www.google.com/intl/en_us/mapfiles/ms/icons/blue-dot.png';
    var myLatLng = new google.maps.LatLng(mapy,mapx);
    var beachMarker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        icon: image
    });
   
}

function showMap(){

	mapx = $('#mapx').attr("title");
	mapy = $('#mapy').attr("title");
	var map = null;

	google.maps.event.addDomListener(window, 'load', init);
	
//	google.maps.event.addDomListener(window, 'resize', function() {
//	    map.setCenter(new google.maps.LatLng(mapy,mapx));
//	});
}
$(document).ready(showMap());

function getReviewRenderingModels(){
	$('#getReviewMore').click(function(){
		var clickedForm = $('form[name="reviewmore"]');
		var itemId = clickedForm.find('input[name="itemId"]').val();
		var index = clickedForm.find('input[name="index"]').val();
		var formUrl = clickedForm.attr("action");
		
		var parameter = new Object();
		
		parameter.itemId = itemId;
		parameter.index = index;
		
		clickedForm.find("input[name=index]").attr("value",parseInt(index,10)+1);
	
		var pData = $.param(parameter);
		
		
		var request = $.ajax({
			type:"GET",
			url:formUrl,
			contentType:"text/plain",
			data:pData,
			dataType:"json",
			success: function(result,status,xhr){
				var reviewRenderingModels = result.reviewRenderingModels;
				if(result.nextIndex == false){
					$('#getReviewMore').css('display','none');
				}
								$.each( reviewRenderingModels , function(index, value){
									var iScore = parseInt(value.score, 10);
									
									var rating = $('<div>').attr('class','rating');
									for(var i = 0; i < iScore; i++){
										rating.append($('<i>')
											.attr('class','fa fa-star')	
										)
									}
										
									if(value.score - iScore > 0){
										rating.append($('<i>')
												.attr('class','fa fa-star-half-empty')	
										)
									}
									
									
									$("#reviewline")
									.append($('<li>')
										.append($('<div>')
											.attr('class','reviewline-userbadge magenta')
											.append($('<i>')
												.attr('class','fa fa-user')
											)
										)
										.append($('<div>')
											.attr('class',"reviewline-username")
											.append(value.lastName+value.firstName)
										)
										.append($('<div>')
											.attr('class',"reviewline-panel")
											.append(rating
											)
											.append($('<div>')
												.attr('class',"reviewline-heading")
												.append($('<h4>')
													.attr('class',"reviewline-title")
													.append(value.title)
												)
											)
											.append($('<div>')
												.attr('class',"reviewline-body")
												.append($('<p>')
													.append(value.content)
												)
											)
										)
									);
				});
				
							
					
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
	});
}
$(document).ready(getReviewRenderingModels);

function getQuestionRenderingModels(){
	$('ul.pagination li a').click(function(){
		var pageNo;
		var pIndex = $('#qna').find('input[name="currentPageNo"]').attr('value');
		switch($(this).text()){
		case '<':
			pageNo = parseInt(pIndex,10)-1;
			if(pageNo < 1)
				pageNo = 1;
			break;
		case '<<':
			pageNo = parseInt(pIndex,10)-5;
			if(pageNo < 1)
				pageNo = 1;
			break;
		case '>':
			pageNo = parseInt(pIndex,10)+1;
			break;
		case '>>':
			pageNo = parseInt(pIndex,10)+5;
			break;
		default:
			pageNo = parseInt($(this).text(),10);
			break;
		}
		$('#qna').find('input[name="currentPageNo"]').attr('value', pageNo);
		
		var itemId = $('#qna').find('input[name="itemId"]').val();
		var formUrl = "/tourofall/dest/info/questions";
		
		var parameter = new Object();
		
		parameter.itemId = itemId;
		parameter.pageNo = pageNo;
		
	
		var pData = $.param(parameter);
		
		
		var request = $.ajax({
			type:"GET",
			url:formUrl,
			contentType:"text/plain",
			data:pData,
			dataType:"json",
			success: function(result,status,xhr){
				$('.table-responsive tbody').empty();
				
				$('.pagination').empty();
				
				var questionRenderingModels = result.questionRenderingModels;
				$.each( questionRenderingModels , function(index, value){
									var date = new Date(value.createdDate);
									$(".table-responsive tbody")
										.append($('<tr>')
											.append($('<td>')
												.append(index+1)
											)
											.append($('<td>')
												.append($('<a>')
													.attr('href','#')
													.append(value.title)
												)
											)
											.append($('<td>')
												.append((date.getFullYear())+'-'+(date.getMonth()+1)+'-'+(date.getDate()))
											)
											.append($('<td>')
												.append(value.lastName+value.firstName)
											)
										);
				});
				
				var indexList = result.indexList;
				
				if(result.pageNo - 5 >= 1){
					$('.pagination')
						.append($('<li>')
							.append($('<a>')
								.attr('onclick','getQuestionRenderingModels()')
								.attr('href','#qna')
								.append("<<")
							)
						);
				} 
				if(result.pageNo - 1 >= 1){
					$('.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('onclick','getQuestionRenderingModels()')
							.attr('href','#qna')
							.append("<")
						)
					);
				} 
				
				$.each( indexList, function(index, value){
					$('.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('onclick','getQuestionRenderingModels()')
							.attr('href','#qna')
							.append(value)
						)
					);
				});
				
				if(result.pageNo + 1 <= result.totalPage){
					$('.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('onclick','getQuestionRenderingModels()')
							.attr('href','#qna')
							.append(">")
						)
					);
				}
				if(result.pageNo + 5 <= result.totalPage){
					$('.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('href','#qna')
							.attr('onclick','getQuestionRenderingModels()')
							.append(">>")
						)
					);
				}
				
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
	});
};

$(document).ready(getQuestionRenderingModels);
function sendQuestionRegistration(){
	$('#register-review').click(function(){
		var form = $('form[name="review-register"]');
		var title = form.find('input[name="title"]').val();
		var score = form.find('input[name="score"]:checked').val();
		var content = form.find('textarea[name="content"]').val();
		var itemId = $('#qna').find('input[name="itemId"]').val();
		
		var formUrl = form.attr("action");
		
		var formData = {"title":title,"content":content,"score":score,"itemId":itemId};
		
		var jsonData = JSON.stringify(formData);
		
		
		
		var csrf_token = form.find(':first-child').val();
		
		alert(formUrl +", "+jsonData+", "+ csrf_token);
		
		
		$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
			if (!options.crossDomain) {
			  return jqXHR.setRequestHeader('X-CSRF-Token', csrf_token);
			}
		});
		
		var request = $.ajax({
			url:formUrl,
			type:"POST",
			contentType:"application/json",
			data:jsonData,
			dataType:"json",
			success: function(result,status,xhr){
				alert('OK');
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
		
	});
}

$(document).ready(sendQuestionRegistration);