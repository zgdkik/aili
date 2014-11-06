$(document).ready(function() {
	$("#sendComment").click(function(){
		var me =$(this);
		if(UserContext.user==null || UserContext.user==""){
			ctips(me,"你需要登陆才能发表评论!");
			return ;
		}
		var editorText  = $("#editorText").val();
		if(editorText==null || editorText==""){
			ctips(me,"请输入评论内容");
			return ;
		}
		var blogId = $("#blogId").val();
		$.ailiAjax({
			url : base + "user/sendComment.htm",
			type : "POST",
			data:{'commentConcent':editorText,'blogId':blogId},
			success : function(data, textStatus) {
				ctips(me,"评论成功");
				window.location.reload();
			},
			exception : function(data, textStatus) {
				ctips(me,"评论失败");
			}
			});
	});
//	loadComment();
//	//动态加载数据
//	$("#main").scroll( function() { 
//		loadComment();
//	}); 
	$("body").on('click',".collect",function(){
		var me =$(this);
		if(UserContext.user==null || UserContext.user==""){
			ctips(me,"你需要登陆才能收藏!");
			return ;
		}
		var blogId = me.attr("tid");
		$.ailiAjax({
			url : base + "user/collectComment.htm",
			type : "POST",
			data:{'url':url,'name':name,'blogId':blogId},
			success : function(data, textStatus) {
				ctips(me,"收藏成功");
			},
			exception : function(data, textStatus) {
				ctips(me,data.msg);
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
function currentd(d){ 
	var str=''; 
	str +=d.getFullYear()+'年'; //获当前年份 
	str +=d.getMonth()+1+'月'; //获取当前月份（0——11） 
	str +=d.getDate()+'日 '; 
	return str;
} 
 //定义一个总的高度变量
var totalheight = 0;
//设置加载最多次数  
var maxnum = 20;
var num = 1;  
function loadComment() {
	//浏览器的高度加上滚动条的高度 
	totalheight = parseFloat($("#main").height()) + parseFloat($("#main").scrollTop());
	//当文档的高度小于或者等于总的高度的时候，开始动态加载数据
	if ($(document).height() <= totalheight && num <= maxnum){
		var blogId = $("#blogId").val();
		$.ajax({
			url : base + "user/loadComment.htm",
			type : "POST",
			data:{'pageNum':num,'blogId':blogId},
			success : function(data, textStatus) {
				//updateHeight();
				var userComment =$("#userComment");
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
				$.toast("加载评论失败,请重新刷新!");
			}
		});
	}
	
};
function updateHeight() {
	var height = document.getElementById("main").scrollHeight;
	$("#center").css("height" , height);
	$("#ct_left").css("height ",height - 10);
	$("#ct_right").css("height" , height);
	//$("#ct_bottom").css("marginTop" , height + 30);
};
function  getbdText(){
	 var text = "我想在"+area+"买车["+name+"]";
	 text = text+"  计划时间是:"+currentd(new Date(context));
	 return text;
}

function  getbdPic(){
	return $("#head_portrait").attr("src");
}
window._bd_share_config = {
		common : {
			bdText : getbdText(),	
			bdDesc : name,	
			bdUrl : url, 	
			bdPic : bdPic
		},
		share : [{
			"bdSize" : 16
		}],
		slide : [{	   
			bdImg : 0,
			bdPos : "right",
			bdTop : 100
		}],
		image : [{
			viewType : 'list',
			viewPos : 'top',
			viewColor : 'black',
			viewSize : '16',
			viewList : ['qzone','tsina','huaban','tqq','renren']
		}],
		selectShare : [{
			"bdselectMiniList" : ['qzone','tqq','kaixin001','bdxc','tqf']
		}]
	}
