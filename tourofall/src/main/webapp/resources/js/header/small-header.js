function collapseNavbar() {
    if ($(".navbar").offset().top > 50) {
        $(".navbar-fixed-top").addClass("top-nav-collapse");
    } else {
        $(".navbar-fixed-top").removeClass("top-nav-collapse");
    }
}

$(window).scroll(collapseNavbar);
$(document).ready(collapseNavbar);




function getUrlPath() {
	var a = location.pathname.split("/");
	//alert(a[2]);
	if(a[2] == "recommend"){
		$("div.collapse ul.nav li #recommend").addClass("selected");
	}
	if(a[2] == "eval" && a[3] == "evalmore"){
		$("div.collapse ul.nav li #evalmore").addClass("selected");
	}
}
$(document).ready(getUrlPath);