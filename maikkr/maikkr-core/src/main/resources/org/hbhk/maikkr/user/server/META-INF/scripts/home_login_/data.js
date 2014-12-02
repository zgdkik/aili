$(document).ready(function() {
	 $.datepicker.regional['zh-CN'] = {
        clearText: '清除', 
        clearStatus: '清除已选日期', 
        closeText: '关闭', 
        closeStatus: '不改变当前选择', 
        prevText: '<上月', 
        prevStatus: '显示上月', 
        prevBigText: '<<', 
        prevBigStatus: '显示上一年', 
        nextText: '下月>', 
        nextStatus: '显示下月', 
        nextBigText: '>>', 
        nextBigStatus: '显示下一年', 
        currentText: '今天', 
        currentStatus: '显示本月', 
        monthNames: ['一月','二月','三月','四月','五月','六月', '七月','八月','九月','十月','十一月','十二月'], 
        monthNamesShort: ['一','二','三','四','五','六', '七','八','九','十','十一','十二'], 
        monthStatus: '选择月份', 
        yearStatus: '选择年份', 
        weekHeader: '周', 
        weekStatus: '年内周次', 
        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'], 
        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'], 
        dayNamesMin: ['日','一','二','三','四','五','六'], 
        dayStatus: '设置 DD 为一周起始', 
        dateStatus: '选择 m月 d日, DD', 
        dateFormat: 'yy-mm-dd', 
        firstDay: 1, 
        initStatus: '请选择日期', 
        isRTL: false
        }; 
	$.datepicker.setDefaults($.datepicker.regional['zh-CN']); 

	$('#u186_input').datepicker();
	$(".carType-select").change(function(){
		var me = $(this);
		var type = me.parent().find("input");
		type.val(me.find("option:selected").text());
	});
	$('#u99_input').chosen({no_results_text: "没有匹配结果"});
	//$('#u94_input').chosen({no_results_text: "没有匹配结果"});
	
	var ph = "";
	for(var i = 0; i < 6; i++) {
		var num = parseInt(pageNum)+i;
		if(parseInt(pageNum)>=3){
			num = parseInt(pageNum)-3+i;
		}else{
			 num = parseInt(pageNum)+i;
		}
		var page = "<a style='margin-right: 5px;' href="+base+"?pageNum="+num+">"+num+"</a>"
		ph = ph+page;
	}
	$("body").find(".page").html(ph);
	
	//u314_img 上一页
	$("body").on("click","#u314",function(){
		var num = parseInt(pageNum)-1;
		if(num>0){
			window.location.href = base+"?pageNum="+num;
		}
	});
	
	//u314_img 下一页一页
	$("body").on("click","#u316",function(){
		var num = parseInt(pageNum)+1;
		if(num<100){
			window.location.href = base+"?pageNum="+num;
		}
	});
	
	$("body").on("click",".sendTheme",function(){
		 var me = $(this);
		 if(UserContext.user==null || UserContext.user==""){
			ctips(me,"你需要登陆才能发表主题");
			return ;
		 }
		 var car =  $("#u94_input").val();
		 var date =  $("#u186_input").val()
		 var area =  $("#u99_input option:selected").text();
		 if(checkEmpty("#u94_input","请选择或输入您需要的车型")==false){
			 return;
		 }
		 if(checkEmpty("#u186_input","请选择计划时间")==false){
			 return;
		 }
		 if(checkEmpty("#u99_input","请选择地区")==false){
			 return;
		 }
		 var url = base+"user/sendTheme.htm";
	   	 var data = {
	       		"carType":car,
	       		"plannTime":date,
	       		"area":area
	       	};
	   	 $.ailiAjax({
	   		url: url,
	   		type:"POST",
	   		data:data,
	   		success: function(data, textStatus){
	   			tips("#u297","发布成功");
	   			window.location.reload();
	   		},
	   		exception:function(data, textStatus){
	   			tips("#u297","发布失败");
	   		}
	   	 });
	 });
	 //关注
	//加载主题
	$(window).scroll( function() { 
		loadTheme();
	});
	
});

//定义一个总的高度变量
var totalheight = 0;
//设置加载最多次数  
var maxnum = 8;
var num = 3;  
function loadTheme() {
	//浏览器的高度加上滚动条的高度 
	totalheight = 200+parseFloat($(window).height())+ parseFloat($(window).scrollTop());
	//当文档的高度小于或者等于总的高度的时候，开始动态加载数据
	if ($(document).height() <= totalheight && num <= maxnum){
		$.ailiAjax({
			url : base + "user/getPageTheme.htm",
			type : "POST",
			data:{'pageNum':num},
			success : function(data, textStatus) {
				num++;
				var items = data.result;
				loadThemes(items);
			},
			exception : function(data, textStatus) {
				$.toast("加载主题失败,请重新刷新!");
			}
		});
	}
};

function loadThemes(items){
	if(items==null){
		return;
	}
	var themes = $(".dxxh:last");
	for ( var i = 0; i < items.length; i++) {
		var b = items[i];
		var tmp = $(".dxxh-tmp");
		var theme = tmp.clone(true).addClass("dxxh").removeClass("dxxh-tmp").show();
		theme.find(".ttitle").attr("href",base+"user/"+b.blogUrl);
		theme.find(".ttitle").html(b.blogUser+"的主题");
		theme.find(".ttype").html("喜欢车型:"+b.carType);
		theme.find(".ttime").html("计划时间:"+current(b.plannTime));
		theme.find(".tarea").html("所在地区:"+b.area);
		theme.find(".gz").html("关注("+b.careCount+")");
		theme.find(".care_user").attr("tuser",b.blogUser);
		theme.find(".user-head").attr("src",file_server+b.userHeadImg);
		var gzh = b.careList;
		if(gzh!=null){
			var gzTmp =theme.find(".gz-h");
			for ( var j = 0; j < gzh.length; j++) {
				var user = gzh[j];
				if(j==0){
					gzTmp.attr("src",file_server+user.userHeadImg)
				}else{
					var c = gzTmp.clone(true);
					c.attr("src",file_server+user.userHeadImg)
					gzTmp.after(c);
				}
			}
		}
		theme.find(".pl-user").html("评论("+b.blogReview+")");
		theme.find(".user-commemt").attr("burl",base+"user/"+b.blogUrl)
		
		var commentList = b.commentList;
		if(commentList!=null){
			for ( var k = 0; k < commentList.length; k++) {
				var comm = commentList[k];
				theme.find(".comm-user").html(comm.commentUser+"用户说:");
				theme.find(".comm-text").html(comm.commentConcent);
			}
		}
		
		themes.after(theme);
	}
};

function current(time){ 
	var d = new Date(time);
	var str=''; 
	str +=d.getFullYear()+'-'; //获当前年份 
	str +=d.getMonth()+1+'-'; //获取当前月份（0——11） 
	str +=d.getDate()+' '; 
	return str;
} 