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

	$j("#uploadImg").uploadify({
		'swf' : base + 'uploadify/uploadify.swf',
		'uploader' : base + 'core/upload.htm;jsessionid='+seesionid,
		'auto' : true,
		'simUploadLimit':5,
		'buttonImage' : base + 'images/user/homeblog/file-upload.png',
		'buttonText' : '浏览',
		'fileTypeDesc' : '图片',
		// 允许上传的文件后缀
		'fileTypeExts' : '*.gif; *.jpg; *.png',
		'multi' : true,
		'width' : 60,
		'height' : 20,
		'uploadLimit':5,
		'queueSizeLimit':5,
		'onUploadStart':function(){
			var img = $j('.imgurl').val();
			var imgs = img.split(",");
			if(imgs.length>=5){
				$j.toast("一个主题最多五张图片");
				return false;
			}else{
				return true;
			}
		},
		'onSelectError' : function() {
			$j.toast("支持的文件格式:*.gif; *.jpg; *.png");
		},
		'onUploadSuccess' : function(file, data, response) {
			var json = $j.parseJSON(data);
			if (json.success) {
				$j.toast("上传成功");
				var img = $j('.imgurl').val();
				if(img!=null &&img!=""){
					$j('.imgurl').val(img+","+json.result)
				}else{
					$j('.imgurl').val(json.result)
				}
				
			} else {
				$j.toast("上传失败");
			}
		}
	});
	loadTheme();
	//显示或隐藏主题 消息收藏
	$j("#home").click(function() {
		$j("#home_blog").show();
		$j("#ct_center_message").hide();
		$j("#ct_center_collect").hide();
	});
	//显示或隐藏主题 消息收藏
	$j("#message").click(function() {
		$j("#home_blog").hide();
		$j("#ct_center_message").show();
		$j("#ct_center_collect").hide();
	});
	//显示或隐藏主题 消息收藏
	$j("#collect").click(function() {
		$j("#home_blog").hide();
		$j("#ct_center_message").hide();
		$j("#ct_center_collect").show();
	});
	//发布主题
	$j(".sendTheme").click(function() {
		sendTheme();
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
		$j(this).css("background-color","#E6E6E6")
	});
	$j(".blog_contenttop").mouseout(function() {
		$j(this).css("background-color","")
	});
	updateHeight();
	//显示放大图片
	$j('.fancybox').fancybox();
});

function sendTheme(){
	var title = $j('.blogTitle').val();
	var context = $j('.blogText').val();
	var url = $j('.imgurl').val();
	//发布主题$j('.imgurl').val()
	var data={
			'blogTitle':title,
			'blogContent':context,
			'blogLink':url
		};
	
	$j.ajax({
		url: base+"user/sendTheme.htm",
		type:"POST",
		data:data,
		success: function(data, textStatus){
			var url = $j('.imgurl').val(null);
			loadTheme();
			$j.toast(data.msg);
		},
		exception:function(data, textStatus){
			$j.toast(data.msg);
		}
	});
};

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
				var burl = base+"user/"+item.blogUrl;
				var title='<div class="vline"><div class="context"><a href="'+burl+'">'+item.blogTitle+'</a></div>';
      			var context='<div class="vline"><div class="context">'+item.blogContent+'</div><div class="context_imgs">';
      			var imggroup= item.blogUrl;
      			if(imgurl!=null && imgurl!=""){
      				var imgs = imgurl.split(",");
      				for ( var j = 0; j < imgs.length; j++) {
      					var imgurl = base + imgs[j];
      					var preImg = '<a class="fancybox" href="'+imgurl+'" data-fancybox-group="'+imggroup+'">'
      					var img ='<img id="context_img" height="100px" width="100px" src="'+imgurl+'"></a>';
      					context =context +preImg+img;
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
				 
				var li='<li class="theme" style="border:#666 1px solid;height:230px; border-left:0;border-right:0;">'
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
      					var preImg = '<a class="fancybox" href="'+imgurl+'" data-fancybox-group="'+imggroup+'">'
      					var img ='<img id="context_img" height="100px" width="100px" src="'+imgurl+'"></a>';
      					context =context +preImg+img;
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
// 动态改变div高度
var preHiget;// 上次的高度
var difference;
function updateHeight(a) {
	var height = get("main").scrollHeight;
	if (a == 1) {
		preHiget = height - difference - 160;
		$j("#center").css("height", preHiget);
		$j("#ct_left").css("height", preHiget - 10);
		$j("#ct_right").css("height", preHiget);
		$j("#ct_bottom").css("margin-top", preHiget + 30);
	} else if (a == 2) {
		$j("#center").css("height",800);
		$j("#ct_left").css("height", 800 - 10);
		$j("#ct_right").css("height", 800);
		$j("#ct_bottom").css("marginTop", 800);
	} else {
		$j("#center").css("height" , height);
		$j("#ct_left").css("height ",height - 10);
		$j("#ct_right").css("height" , height);
		$j("#ct_bottom").css("marginTop" , height + 30);
		difference = height - preHiget;
	}
}

function get(id){
	return document.getElementById(id);
}