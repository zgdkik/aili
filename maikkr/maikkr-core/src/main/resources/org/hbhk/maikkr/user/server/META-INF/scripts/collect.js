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
	$j("body").on("click",".collect_blog_del",function(){
		var me = $j(this);
		var bid = me.attr("tid");
		$j.ajax({
			url : base + "user/delCollect.htm",
			type : "POST",
			data:{'id':bid},
			success : function(data, textStatus) {
				$j.toast("删除成功");
				me.parent().remove();
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
			url : base + "user/loadCollects.htm",
			type : "POST",
			data:{'pageNum':num},
			success : function(data, textStatus) {
				num++;
				var items = data.result;
				if(items==null ||items.length==0 ){
					num=20;
					return;
				}
				var theme_list = $j("#theme_list");
				loadColletThemes(items,theme_list);
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
			var theme_list = $j("#theme_list");
			theme_list.empty();
			var items = data.result;
			if(items==null ||items.length==0 ){
				updateHeight();
				return;
			}
			loadThemes(items,theme_list);
			if(items!=null && items.length!=0 ){
				updateHeight();
			}
		
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
function loadColletThemes(items,theme_list){
	for ( var i = 0; i < items.length; i++) {
		var item = items[i];
		var li='<li class="theme" style="border-bottom:#666 1px solid;width:535px;height:50px; border-left:0;border-right:0;">';
		var burl = item.url;
		var title='<div class="vline" style="margin-left: 10px;"><div class="context"><a  href="'+burl+'" title="'+item.name+'">'+item.name+'</a></div>';
		var oper = '<a tid ="'+item.blogId+'" class="collect_blog_del" style="margin-left: 10px;float: right;text-decoration: none;" href="javascript:void(0)">删除</a>';
		li=li+oper+title+'</li>';
		theme_list.append(li);
		theme_list.trigger("create");
	}
};