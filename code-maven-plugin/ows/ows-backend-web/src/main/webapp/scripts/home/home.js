var h1 = 105;
$(document).ready(function() {
	
	$("body").on("click",".logout",function(){
//		bootbox.confirm("确定要退出吗?", function(result) {
//			if(result){
//                 window.location.href=base+"/user/logout";
//			}
//		});
		layer.confirm("确定要退出吗?",null,function(){
			 window.location.href=base+"/user/logout";
		});
	});
	var mainHeight = window.innerHeight - h1;
	$(".wrapper").css("height",mainHeight);
	$(window).resize(function() {
		mainHeight = window.innerHeight - h1;
		$(".wrapper").css("height",mainHeight);
	});
	var sessionInterval = setInterval(function(){
		if ($.cookie('SESSION_COOKIE_KYE') == null
				|| $.cookie('SESSION_COOKIE_KYE') == undefined) {
			clearInterval(sessionInterval);
			layer.alert("会话已失效,请重新登录", function(result) {
					location.href = base + "/user/login";
			});
		}
	}, 5000);
});
