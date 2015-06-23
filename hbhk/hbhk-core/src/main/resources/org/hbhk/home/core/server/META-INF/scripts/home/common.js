$j(document).ready(function() {

	$j(".navbar-fixed-top").on("click", "li", function() {
		var me = $j(this);
		me.parents("ul").find("li").removeClass("active");
		me.addClass("active");
	});

	$j(".container-header").headroom({
		"tolerance" : 5,
		"offset" : 300,
		"classes" : {
			"initial" : "animated",
			"pinned" : "slideDown",
			"unpinned" : "slideUp"
		}
	});

	// to destroy
	//$("#header").headroom("destroy");
});