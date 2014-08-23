$j(document).ready(function(){
	$j('.non-empty').focus(function(){
		$j(this).removeClass('notNull'); 
	});
	$j('.non-empty').blur(function(){
		var val= $j(this).val();
		if(val==null || val==""){
			$j(this).addClass('notNull'); 
		}
	});
});
function validate(){
	var  flag = true;
	$j('.non-empty').each(function(){
		var val= $j(this).val();
		if(val==null || val==""){
			$j(this).addClass('notNull'); 
			flag = false;
		}
	});
	return flag;
};

function loadThemes(items,theme_list){
	for ( var i = 0; i < items.length; i++) {
		var item = items[i];
		var userHeadImg = item.userHeadImg;
		if(userHeadImg==null || userHeadImg==""){
			userHeadImg ="images/security/default_head.png";
		}
		// 设置头像
		var headimg = base + userHeadImg;
		var imgurl =item.blogLink;
		 
		var li='<li class="theme" style="border:#666 1px solid;width:535px;height:230px; border-left:0;border-right:0;">';
		var head ='<div class="vline"><img id="head_portrait" height="50px" width="50px" '+
		'src="'+headimg+'"></div>';
		var burl = base+"user/"+item.blogUrl;
		var title='<div class="vline" style="margin-left: 10px;"><div class="context"><a  href="'+burl+'" title="'+item.blogTitle+'">'+item.blogTitle+'</a></div>';
			var context='<div class="vline" ><div class="context">'+item.blogContent+'</div><div class="context_imgs">';
			var imggroup= item.blogUrl;
			if(imgurl!=null && imgurl!=""){
				var imgs = imgurl.split(",");
				for ( var j = 0; j < imgs.length; j++) {
					var imgurl = base + imgs[j];
					var preImg = '<a class="fancybox" href="'+imgurl+'" data-fancybox-group="'+imggroup+'">';
					var img ='<img id="context_img" height="100px" width="100px" src="'+imgurl+'"></a>';
					context =context +preImg+img;
			}
			}
			context=context+"</div></div>";
			var oper = '<a tid ="'+item.id+'" class="blog_del" style="margin-left: 10px;float: right;text-decoration: none;" href="javascript:void(0)">收藏</a>';
			var coper = '<a tid ="'+item.id+'" class="blog_del" style="float: right;text-decoration: none;" href="javascript:void(0)">关注</a>';
		li=li+head+oper+coper+title+context+'</li>';
		theme_list.append(li);
		theme_list.trigger("create");
	}
};