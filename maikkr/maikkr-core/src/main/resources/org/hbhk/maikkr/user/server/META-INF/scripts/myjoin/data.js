$(document).ready(function() {
	$("body").on("click",'.blog_del',function(){
		var user = $(this).attr("tid");
		var me = $(this);
		$.ailiAjax({
			url : base + "user/editCare.htm",
			type : "POST",
			data:{'user':user},
			success : function(data, textStatus) {
				window.location.reload();
			},
			exception : function(data, textStatus) {
				ctips(me,"删除失败");
			}
		});
	});
});