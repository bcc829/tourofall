function selectCurrentListItem(){
	var urlTemp = window.location.search;
	if(urlTemp == ""){
    	$("div.container .row .col-sm-2 .list-group #sub-navbar-1").addClass("active");
    }
	
	
	var sPageURL = window.location.search.substring(1);
	
    var sURLVariables = sPageURL.split('&');
    var sParameterName1 = sURLVariables[0].split('=');
    var sParameterName2 = sURLVariables[1].split('=');
    
    
    if(sParameterName1[1] == "A01"){
    	switch(sParameterName2[1]){
    	case "A0101":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-1").addClass("active");
    		break;
    	}
    }else if(sParameterName1[1] == "A02"){
    	switch(sParameterName2[1]){
    	case "A0201":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-2").addClass("active");
    		break;
    	case "A0202":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-3").addClass("active");
    		break;
    	case "A0203":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-4").addClass("active");
    		break;
    	case "A0204":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-5").addClass("active");
    		break;
    	case "A0205":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-6").addClass("active");
    		break;
    	case "A0206":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-7").addClass("active");
    		break;
    	}
    }else if(sParameterName1[1] == "A03"){
    	switch(sParameterName2[1]){
    	case "A0302":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-8").addClass("active");
    		break;
    	case "A0303":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-9").addClass("active");
    		break;
    	case "A0304":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-10").addClass("active");
    		break;
    	}
    }
}

$(document).ready(selectCurrentListItem);