$j(document).ready(function() {
	$j("#main").scroll(function() {
		$j("#bottom_top").css("marginTop",$j('#main')
			.css("scrollTop")+ document.body.offsetHeight - 180);
	});
	$j("#sendComment").click(function(){
		if(UserContext.user==null || UserContext.user==""){
			$j.toast("你需要登陆才能发表评论!");
			return ;
		}
		var editorText  = $j("#editorText").val();
		var blogId = $j("#blogId").val();
		$j.ajax({
			url : base + "user/sendComment.htm",
			type : "POST",
			data:{'commentConcent':editorText,'blogId':blogId},
			success : function(data, textStatus) {
				$j.toast("评论成功");
				var userComment =$j("#userComment");
				var li = '<li>';
				var head ='<div class="vline"><img height="50px" width="50px" src="'+base+UserContext.head+'">'+
				UserContext.user+'时间:'+current(new Date())+'</div>';
				var content ='<div class="vline"><div class="context">'+editorText+'</div></div> </li>'
				var l='<h1 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h1>';
				userComment.append(li+head+content+l);
				userComment.trigger("create"); 
				updateHeight();
				window.location.reload();
			},
			exception : function(data, textStatus) {
				$j.toast("评论失败");
			}
			});
	});
	loadComment();
	//动态加载数据
	$j("#main").scroll( function() { 
		loadComment();
	}); 
	//updateHeight();
	$j("body").on('click',"#collect",function(){
		if(UserContext.user==null || UserContext.user==""){
			$j.toast("你需要登陆才能收藏!");
			return ;
		}
		var blogId = $j("#blogId").val();
		$j.ajax({
			url : base + "user/collectComment.htm",
			type : "POST",
			data:{'url':url,'name':name,'blogId':blogId},
			success : function(data, textStatus) {
				$j.toast("评论成功");
			},
			exception : function(data, textStatus) {
				$j.toast(data.msg);
			}
		});
		
	});
});

function current(d){ 
	var str=''; 
	str +=d.getFullYear()+'-'; //获当前年份 
	str +=d.getMonth()+1+'-'; //获取当前月份（0——11） 
	str +=d.getDate()+' '; 
	str +=d.getHours()+':'; 
	str +=d.getMinutes()+':'; 
	str +=d.getSeconds(); 
	return str;
} 
 //定义一个总的高度变量
var totalheight = 0;
//设置加载最多次数  
var maxnum = 20;
var num = 1;  
function loadComment() {
	//浏览器的高度加上滚动条的高度 
	totalheight = parseFloat($j("#main").height()) + parseFloat($j("#main").scrollTop());
	//当文档的高度小于或者等于总的高度的时候，开始动态加载数据
	if ($j(document).height() <= totalheight && num <= maxnum){
		var blogId = $j("#blogId").val();
		$j.ajax({
			url : base + "user/loadComment.htm",
			type : "POST",
			data:{'pageNum':num,'blogId':blogId},
			success : function(data, textStatus) {
				//updateHeight();
				var userComment =$j("#userComment");
				var result = data.result;
				for ( var i = 0; i < result.length; i++) {
					var comment = result[i];
					var li = '<li>';
					var head ='<div class="vline"><img height="50px" width="50px" src="'+base+comment.commentHeadImg+'">'+comment.commentUser
					+'<span style=" float: right;">时间:'+current(new Date(comment.createTime))+'</span></div>';
					var content ='<div class="vline"><div class="context">'+comment.commentConcent+'</div></div> </li>'
					var l='<h1 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h1>';
					userComment.append(li+head+content+l);
					userComment.trigger("create"); 
				}
				num++;
				if(result!=null && result.length>0){
					updateHeight();
				}
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
