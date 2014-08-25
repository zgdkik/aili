$j(document).ready(function() {
	$j("#main").scroll(function() {
		$j("#bottom_top").css("marginTop",$j('#main')
			.css("scrollTop")+ document.body.offsetHeight - 180);
	});
	$j(".left_class").mouseout(function() {
		$j(this).css("background-color", "");
	});
	$j(".left_class").mouseover(function() {
		$j(this).css("background-color", "#E6E6E6");
	});

	$j(".returnTop").click(function() {
		$j("#mian").scrollTop(0);
	});
	$j("#bottom_top").mouseout(function() {
		$j("#bottom_top1").attr("src",base + "images/user/homeblog/bottom_top2.png");
	});
	$j("#bottom_top").mouseover(function() {
		$j("#bottom_top1").attr("src",base + "images/user/homeblog/bottom_top1.png");
	});

	$j("#bottom_top").click(function() {
		$j("#main").css("scroll-top",0);
	});
	$j("#addImg").mouseover(function() {
		$j("#picture").css("display", 'inline');
	});

	$j("#addImg").mouseout(function() {
		$j("#picture").css("display", 'none');
	});

	$j("#picture").mouseover(function() {
		$j(this).css("display", 'inline');
	});

	$j("#picture").mouseout(function() {
		$j(this).css("display", 'none');
	});

	loadTheme();
	$j("#main").scroll( function() { 
		loadTheme();
	});
	
	$j(".btnSearch").click(function() {
		var q = $j('.search').val();
		if(q==null || q==""){
			return;
		}
		search(q);
	});
	
	//发布主题
	$j(".blog_contenttop").mouseover(function() {
		$j(this).css("background-color","#E6E6E6");
	});
	$j(".blog_contenttop").mouseout(function() {
		$j(this).css("background-color","");
	});
	updateHeight();
	//显示放大图片
	$j('.fancybox').fancybox();
	
	$j("body").on("click",'.blog_del',function(){ 
		var tid = $j(this).attr("tid");
		$j.ajax({
			url : base + "user/delTheme.htm",
			type : "POST",
			data:{'tid':tid},
			success : function(data, textStatus) {
				$j.toast("删除成功");
				window.location.reload();
			},
			exception : function(data, textStatus) {
				$j.toast("删除失败");
			}
		});
	});
	
});
//定义一个总的高度变量
var totalheight = 0;
//设置加载最多次数  
var maxnum = 20;
var num = 1;  
function loadTheme() {
	//浏览器的高度加上滚动条的高度 
	totalheight = parseFloat($j("#main").height());
	//当文档的高度小于或者等于总的高度的时候，开始动态加载数据
	if ($j(document).height() <= totalheight && num < maxnum){
		$j.ajax({
			url : base + "user/myTheme.htm",
			type : "POST",
			data:{'pageNum':num},
			success : function(data, textStatus) {
				num++;
				var items = data.result.items;
				if(items==null ||items.length==0 ){
					num=20;
					return;
				}
				var theme_list = $j("#theme_list");
				//theme_list.empty();
				for ( var i = 0; i < items.length; i++) {
					var item = items[i];
					var userHeadImg = item.userHeadImg;
					if(userHeadImg==null || userHeadImg==""){
						userHeadImg ="images/security/default_head.png";
					}
					// 设置头像
					var headimg = base + userHeadImg;
					var imgurl =item.blogLink;
					 
					var li='<li class="theme" style="border-bottom:#666 1px solid;width:535px;height:230px; border-left:0;border-right:0;">';
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
	      			var oper = '<a tid ="'+item.id+'" class="blog_del" style="float: right;text-decoration: none;" href="javascript:void(0)">删除</a>';
					li=li+head+oper+title+context+'</li>';
					theme_list.append(li);
					theme_list.trigger("create");
				}
				if(items!=null && items.length!=0 ){
					updateHeight();
				}
			},
			exception : function(data, textStatus) {
				$j.toast("加载主题失败,请重新刷新!");
			}
		});
	}
};

function search(q) {
	$j.ajax({
		url : base + "user/search.htm",
		type : "POST",
		data:{'q':q},
		success : function(data, textStatus) {
			var items = data.result;
			var theme_list = $j("#theme_list");
			theme_list.empty();
			if(items== null ||items.length==0){
				updateHeight();
				return;
			}
			for ( var i = 0; i < items.length; i++) {
				var item = items[i];
				// 设置头像
				var headimg = base + item.userHeadImg;
				var imgurl =item.blogLink;
				 
				var li='<li class="theme" style="border:#666 1px solid;height:230px; border-left:0;border-right:0;">';
				var head ='<div class="vline"><img id="head_portrait" height="50px" width="50px" '+
				'src="'+headimg+'"></div>';
				var burl = base+"user/"+item.blogUrl;
				var title='<div class="vline"><div class="context"><a href="'+burl+'">'+item.blogTitle+'</a></div>';
      			var context='<div class="vline"><div class="context">'+item.blogContent+'</div><div class="context_imgs">';
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
				li=li+head+title+context+'</li>';
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
// 动态改变div高度
function updateHeight() {
	var height = get("main").scrollHeight;
	$j("#center").css("height" , height);
	$j("#ct_left").css("height ",height - 10);
	$j("#ct_right").css("height" , height);
	//$j("#ct_bottom").css("marginTop" , height + 30);
}

function get(id){
	return document.getElementById(id);
}