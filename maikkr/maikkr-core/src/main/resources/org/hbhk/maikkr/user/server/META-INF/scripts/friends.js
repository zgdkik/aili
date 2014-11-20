$(document).ready(function() {
	$('.receive-user').chosen({no_results_text: "没有匹配结果"});
	$(".msg-send").click(function() {
		var me = $(this);
		var msg = $(".msg-content").val();
		if (msg == null || msg == "") {
			ctips(me, "请输入发送内容");
			return;
		}
		var user = $(".receive-user").val();
		$.ailiAjax({
			url : base + "user/sendMsg.htm",
			type : "POST",
			data : {
				'msg' : msg,
				'user' : user
			},
			success : function(data, textStatus) {
				ctips(me, "发送成功");
				$(".msg-content").val(null);
			},
			exception : function(data, textStatus) {
				ctips(me, "发送失败");
			}
		});
	});
	
	$(".del-msg").click(function() {
		var me = $(this);
		var id = me.attr("msgid");
		$.ailiAjax({
			url : base + "user/editMsg.htm",
			type : "POST",
			data : {
				'id' : id
			},
			success : function(data, textStatus) {
				ctips(me, "删除成功");
				setTimeout(function(){me.parents("tr").remove();},2000);
			},
			exception : function(data, textStatus) {
				ctips(me, "删除失败");
			}
		});
	});
	$("body").on('click', ".mymsg", function() {
		var mymsg = $(".my-msg").show();
		var sendmsg = $(".send-msg").hide();
		
	});
	$("body").on('click', ".sendmsg", function() {
		var mymsg = $(".my-msg").hide();
		var sendmsg = $(".send-msg").show();
		 
	});
});

function current(d) {
	var str = '';
	str += d.getFullYear() + '-'; // 获当前年份
	str += d.getMonth() + 1 + '-'; // 获取当前月份（0——11）
	str += d.getDate() + ' ';
	str += d.getHours() + ':';
	str += d.getMinutes() + ':';
	str += d.getSeconds();
	return str;
}
function currentd(d) {
	var str = '';
	str += d.getFullYear() + '年'; // 获当前年份
	str += d.getMonth() + 1 + '月'; // 获取当前月份（0——11）
	str += d.getDate() + '日 ';
	return str;
}
// 定义一个总的高度变量
var totalheight = 0;
// 设置加载最多次数
var maxnum = 20;
var num = 1;
function loadComment() {
	// 浏览器的高度加上滚动条的高度
	totalheight = parseFloat($("#main").height())
			+ parseFloat($("#main").scrollTop());
	// 当文档的高度小于或者等于总的高度的时候，开始动态加载数据
	if ($(document).height() <= totalheight && num <= maxnum) {
		var blogId = $("#blogId").val();
		$
				.ajax({
					url : base + "user/loadComment.htm",
					type : "POST",
					data : {
						'pageNum' : num,
						'blogId' : blogId
					},
					success : function(data, textStatus) {
						// updateHeight();
						var userComment = $("#userComment");
						var result = data.result;
						for ( var i = 0; i < result.length; i++) {
							var comment = result[i];
							var li = '<li>';
							var head = '<div class="vline"><img height="50px" width="50px" src="'
									+ base
									+ comment.commentHeadImg
									+ '">'
									+ comment.commentUser
									+ '<span style=" float: right;">时间:'
									+ current(new Date(comment.createTime))
									+ '</span></div>';
							var content = '<div class="vline"><div class="context">'
									+ comment.commentConcent
									+ '</div></div> </li>'
							var l = '<h1 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h1>';
							userComment.append(li + head + content + l);
							userComment.trigger("create");
						}
						num++;
						if (result != null && result.length > 0) {
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
	$("#center").css("height", height);
	$("#ct_left").css("height ", height - 10);
	$("#ct_right").css("height", height);
	// $("#ct_bottom").css("marginTop" , height + 30);
};
function getbdText() {
	var text = "我想在" + area + "买车[" + name + "]";
	text = text + "  计划时间是:" + currentd(new Date(context));
	return text;
}

function getbdPic() {
	return $("#head_portrait").attr("src");
}
window._bd_share_config = {
	common : {
		bdText : getbdText(),
		bdDesc : name,
		bdUrl : url,
		bdPic : bdPic
	},
	share : [ {
		"bdSize" : 16
	} ],
	slide : [ {
		bdImg : 0,
		bdPos : "right",
		bdTop : 100
	} ],
	image : [ {
		viewType : 'list',
		viewPos : 'top',
		viewColor : 'black',
		viewSize : '16',
		viewList : [ 'qzone', 'tsina', 'huaban', 'tqq', 'renren' ]
	} ],
	selectShare : [ {
		"bdselectMiniList" : [ 'qzone', 'tqq', 'kaixin001', 'bdxc', 'tqf' ]
	} ]
}
