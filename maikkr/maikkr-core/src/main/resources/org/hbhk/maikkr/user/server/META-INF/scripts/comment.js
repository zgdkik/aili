$j(document).ready(function() {
	$j("#main").scroll(function() {
		$j("#bottom_top").css("marginTop",$j('#main')
			.css("scrollTop")+ document.body.offsetHeight - 180);
	});
	$j("#sendComment").click(function() {
		var editorText  = $j("#editorText").val();
		var blogId = $j("#blogId").val();
		$j.ajax({
			url : base + "user/sendComment.htm",
			type : "POST",
			data:{'commentConcent':editorText,'blogId':blogId},
			success : function(data, textStatus) {
				$j.toast("评论成功");
				updateHeight();
			},
			exception : function(data, textStatus) {
				$j.toast("评论失败");
			}
	});
	//动态加载数据
	$j(window).scroll( function() { 
		loadComment();
	}); 
	updateHeight();
});
 //定义一个总的高度变量
var totalheight = 0;
//设置加载最多次数  
var maxnum = 5;
var num = 1;  
function loadComment() {
	//浏览器的高度加上滚动条的高度 
	totalheight = parseFloat($j(window).height()) + parseFloat($j(window).scrollTop());
	//当文档的高度小于或者等于总的高度的时候，开始动态加载数据
	if ($j(document).height() <= totalheight && num <= maxnum){
		$j.ajax({
			url : base + "user/loadComment.htm",
			type : "POST",
			data:{'pageNum':num},
			success : function(data, textStatus) {
				updateHeight();
				maxnum++;
			},
			exception : function(data, textStatus) {
				$j.toast("加载评论失败,请重新刷新!");
			}
		});
	}
	
};
function updateHeight() {
	var height = document.getElementById("main").scrollHeight;
	$j("#center").css("height" , height);
	$j("#ct_left").css("height ",height - 10);
	$j("#ct_right").css("height" , height);
	//$j("#ct_bottom").css("marginTop" , height + 30);
};
