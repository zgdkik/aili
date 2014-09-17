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
	$j('body').on("click",".collect_t",function(){
		if(UserContext.user==null || UserContext.user==""){
			$j.toast("你需要登陆才能收藏!");
			return ;
		}
		var blogId= $j(this).attr("tid");
		var name = $j(this).attr("tname");
		var url =  $j(this).attr("turl");
		$j.ajax({
			url : base + "user/collectComment.htm",
			type : "POST",
			data:{'url':url,'name':name,'blogId':blogId},
			success : function(data, textStatus) {
				$j.toast("收藏成功");
			},
			exception : function(data, textStatus) {
				$j.toast(data.msg);
			}
		});
	});
	
	$j('body').on("click",".care_user",function(){
		if(UserContext.user==null || UserContext.user==""){
			$j.toast("你需要登陆才能关注!");
			return ;
		}
		var user= $j(this).attr("tuser");
		$j.ajax({
			url : base + "user/care.htm",
			type : "POST",
			data:{'user':user},
			success : function(data, textStatus) {
				$j.toast("关注成功");
			},
			exception : function(data, textStatus) {
				$j.toast(data.msg);
			}
		});
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
		 
		var li='<li class="theme" style="border-bottom:#666 1px solid;width:535px;height:100px; border-left:0;border-right:0;">';
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
			var oper = '<a tid ="'+item.id+'" tname="'+item.blogTitle+'" turl ="'+"http://"+host+burl+'"   tuser ="'+item.blogUser+'"  class="blog_del collect_t" style="margin-left: 10px;float: right;text-decoration: none;" href="javascript:void(0)">收藏</a>';
			var coper = '<a tid ="'+item.id+'" tuser ="'+item.blogUser+'" class="blog_del care_user" style="float: right;text-decoration: none;" href="javascript:void(0)">关注</a>';
		li=li+head+oper+coper+title+context+'</li>';
		theme_list.append(li);
		theme_list.trigger("create");
	}
};

