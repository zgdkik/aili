$(document).ready(function() {
	
	$("body").on("click",'.blog_del',function(){
		var tid = $(this).attr("tid");
		var me = $(this);
		var blog = me.parents(".blog");
		$.ailiAjax({
			url : base + "user/delTheme.htm",
			type : "POST",
			data:{'tid':tid},
			success : function(data, textStatus) {
				ctips(me,"删除成功");
				setTimeout(function(){blog.remove();},3000);
			},
			exception : function(data, textStatus) {
				ctips(me,"删除失败");
			}
		});
	});
});

