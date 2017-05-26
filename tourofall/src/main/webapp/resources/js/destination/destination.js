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
    // Basic options for a simple Google Map
    // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
    var mapOptions = {
        // How zoomed in you want the map to start at (always required)
        zoom: 15,

        // The latitude and longitude to center the map (always required)
        center: new google.maps.LatLng(mapy,mapx), // New York
    };

    // Get the HTML DOM element that will contain your map
    // We are using a div with id="map" seen below in the <body>
    var mapElement = document.getElementById('map');

    // Create the Google Map using out element and options defined above
    map = new google.maps.Map(mapElement, mapOptions);
    
    // Custom Map Marker Icon - Customize the map-marker.png file to customize your icon
    var image = 'http://www.google.com/intl/en_us/mapfiles/ms/icons/blue-dot.png';
    var myLatLng = new google.maps.LatLng(mapy,mapx);
    var beachMarker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        icon: image
    });
   
}

function showMap(){
	//Google Maps Scripts
	mapx = $('#mapx').attr("title");
	mapy = $('#mapy').attr("title");
	var map = null;
	// When the window has finished loading create our google map below
	google.maps.event.addDomListener(window, 'load', init);
	
//	google.maps.event.addDomListener(window, 'resize', function() {
//	    map.setCenter(new google.maps.LatLng(mapy,mapx));
//	});
}
$(document).ready(showMap());