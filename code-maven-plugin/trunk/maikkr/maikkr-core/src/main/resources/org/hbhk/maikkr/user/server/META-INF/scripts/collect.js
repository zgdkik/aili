$(document).ready(function() {
	
	$("body").on("click",".collect_blog_del",function(){
		var me = $(this);
		var bid = me.attr("tid");
		var p = me.parent().parent().parent();
		$.ailiAjax({
			url : base + "user/delCollect.htm",
			type : "POST",
			data:{'id':bid},
			success : function(data, textStatus) {
				ctips(me,"删除成功");
				setTimeout(function(){p.remove();},3000);
			},
			exception : function(data, textStatus) {
				ctips(me,"删除失败");
			}
		});
	});
	
});

function loadColletThemes(items,theme_list){
	
};