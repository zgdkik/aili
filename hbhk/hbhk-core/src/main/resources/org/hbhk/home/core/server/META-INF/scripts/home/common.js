$j(document).ready(function() {

	$j(".navbar-fixed-top").on("click", "li", function() {
		var me = $j(this);
		me.parents("ul").find("li").removeClass("active");
		me.addClass("active");
	});

	var elem = document.querySelector(".container-header");
	var headroom = new Headroom(elem, {
		"tolerance" : 5,
		"offset" : 205,
		"classes" : {
			"initial" : "animated",
			"pinned" : "flipInX",
			"unpinned" : "flipOutX"
		}
	});
	headroom.init();
	// to destroy
	//headroom.destroy();
});