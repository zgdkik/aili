$j(document).ready(function(){

	$j(".navbar-fixed-top").on("click","li",function(){
		var me = $j(this);
		me.parents("ul").find("li").removeClass("active");
		me.addClass("active");
	});
});