$j(document).ready(function() {
	$j("#main").scroll(function() {
		$j("#bottom_top").css("marginTop",$j('#main')
			.css("scrollTop")+ document.body.offsetHeight - 180);
	});
	updateHeight();
});

function loadTheme() {
	$j.ajax({
		url : base + "user/getPageTheme.htm",
		type : "POST",
		success : function(data, textStatus) {
			var items = data.result.items;
			var theme_list = $j("#theme_list");
			theme_list.empty();
			for ( var i = 0; i < items.length; i++) {
				var item = items[i];
				// 设置头像
				var headimg = base + item.userHeadImg;
				var imgurl =item.blogLink;
				 
				var li='<li class="theme" style="border:#666 1px solid;height:230px; border-left:0;border-right:0;">'
				var head ='<div class="vline"><img id="head_portrait" height="50px" width="50px" '+
				'src="'+headimg+'"></div>';
				var title='<div class="vline"><div class="context"><a href="">'+item.blogTitle+'</a></div>';
      			var context='<div class="vline"><div class="context">'+item.blogContent+'</div><div class="context_imgs">';
      			if(imgurl!=null && imgurl!=""){
      				var imgs = imgurl.split(",");
      				for ( var i = 0; i < imgs.length; i++) {
      					var img ='<img id="context_img" height="100px" width="100px" src="'+ base + imgs[i]+'">';
      					context =context +img;
					}
      			}
      			context=context+"</div></div>";
				li=li+head+title+context+'</li>'
				theme_list.append(li);
				theme_list.trigger("create");
			}
			updateHeight();
		},
		exception : function(data, textStatus) {
			$j.toast("加载主题失败,请重新刷新!");
		}
	});
};
function updateHeight() {
	var height = get("main").scrollHeight;
	var ct_center = $j("#ct_center").outerHeight();
	if(height < ct_center){
		height = ct_center;
	}
	$j("#center").css("height" , height);
	$j("#ct_left").css("height ",height - 10);
	$j("#ct_right").css("height" , height);
	//$j("#ct_bottom").css("marginTop" , height + 30);
}

function get(id){
	return document.getElementById(id);
}