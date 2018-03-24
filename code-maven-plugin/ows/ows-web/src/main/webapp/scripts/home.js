$(document).ready(function(){
//	if(!IsPC()){
//		location.href='../phone/home.jsp';
//	}
	fn_get_menu('ddwl_gwsy');
	
	
	if($('#hd_username').val()==''){
		$('#li_onlineorder').click(function(){
			alert('您还未登录，请前往登录后操作');
		});
	}else{
		$('#li_onlineorder').click(function(){
		   location.href='../online/dskzk.jsp';	
		});
	}

	$('#btn_search').bind('click',fn_waybill_search);
	$('#cod_btn_search').bind('click',fn_cod_search);
	$('#feight_btn_search').bind('click',fn_feight_search);
	$('#dept_btn_search').bind('click',fn_dept_search);
	
	$('.contact-us').mouseover(function(){
		$(".contact-us").attr({"style":"background-color:#B22923;"});
	});
	$('.contact-us').mouseout(function(){
		$(".contact-us").removeAttr("style");
	});
	showHomeNews();
	getUserInfo();
	setTimeout("LinkWx()", 3000 );

	
});
function LinkWx(){
	$.ajax({
		url : base+'/customer/getSearchSourceCity/',
		async : false,
		success : function(data){
			 $('#sourceDistCode').typeahead(
            {
                source:data.data.list ,
                items:50,
                itemSelected: displayTxt_s
            });
		},
		error : function() {
			alert('错误11');
		},
		dataType : "json"
	});
 $.ajax({
		url : base+'/customer/getSearchDestCity/',
		async : false,
		success : function(data){
			$('#destDistCode').typeahead(
            {
                source:data.data.list ,
                items:50,
                itemSelected: displayTxt_d
            });
		},
		error : function() {
			alert('错误22');
		},
		dataType : "json"
	}); 
}
 function displayTxt_s(item, val, text) {
	 $('#sourceDistValue').val(val);
}
 function displayTxt_d(item, val, text) {
	 $('#destDistValue').val(val);
}
function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    if($("#hd_t").val()=='pc'){
    	flag=true;
    }
    return flag;
}
function fn_waybill_search(){
	var waybillNos = $('#orderNum').val();
	window.location.href=base+'/ddwlGw/waybillTrack?title=货物追踪&waybillNos='+waybillNos;
}
function fn_cod_search(){
	var codWaybillNo = $('#codWaybillNo').val();
	window.location.href=base+'/ddwlGw/queryCOD?title=代收款查询&waybillNo='+codWaybillNo;
}
function fn_feight_search(){
	var sourceDistCode=$("#sourceDistCode").val();
	var sourceDistValue=$("#sourceDistValue").val();
	var destDistCode=$("#destDistCode").val();
	var destDistValue=$("#destDistValue").val();
	if(!sourceDistValue){
		alert("起运站不存在,请输入起运站");
		return;
	}
	if(!destDistValue){
		alert("到达站不存在,请输入起运站");
		return;
	}
	var param=sourceDistCode+","+sourceDistValue+","+destDistCode+","+destDistValue;
	window.location.href=base+'/ddwlGw/queryFreight?title=运价查询&sourceDistCode='+sourceDistCode+'&sourceDistValue='+sourceDistValue+'&destDistCode='+destDistCode+'&destDistValue='+destDistValue+'';
	
}
function fn_dept_search(){
	var city=$("#cityName").val();
	window.location.href=base+'/ddwlGw/deptSearch?title=网点查询&city='+city;
}

function getUserInfo(){
	if($("#userNameFlag").text()){
		$.ajax({
			url : base+'/customer/getUserInfo/',
			async : false,
			success : function(data){
				if(data.success){
					data=data.data;
					$("#history").text(data[0]);
					$("#today").text(data[1]);
					$("#ji").text(data[2]);
					$("#shou").text(data[3]);
				}
			},
			error : function() {
				alert('错误22');
			},
			dataType : "json"
		}); 
	}
}

function fn_search_over(index){
	for(var i=1;i<5;i++){
		if(index!=i){
			$('.home-seach'+i).hide();
		}
	}
	$('.home-seach'+index).show();
	
}

function fn_search_leave(index){
	if(index=="all"){
		$('.home-seach1').hide();
		$('.home-seach2').hide();
		$('.home-seach3').hide();
		$('.home-seach4').hide();
		return;
	}
	$('.home-seach'+index).hide();
}


function login(){
	if($("#homeUserName").val().length<1 ||
			$("#homePassword").val().length<1){
		alert("请填写会员帐号/密码");
		return;
	}
	var userName=$("#homeUserName").val();
	var password=$("#homePassword").val();
	$.ajax({  
		url:base+"/register/login",
		type:"post",
		data:{userName:userName,password:password},
		dataType:"json",
		success:function(data){ 
			if(data.data=="1"){
				if(title=="登录注册"){
					location.href=base+"/ddwlGw/home";
				}else{
					location.reload();
				}
			}else if(data.data=='0'){
				alert("用户名或密码输入不正确");
				$("#password").val("").focus();
			}
		}            
	});
}

	
function reloadhomecode(){
    var verify=document.getElementById('imghomecode');
    verify.setAttribute('src',base+'/register/createCode/phonecertpic?it='+Math.random());
}
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0]; 
    if(e && e.keyCode==13){ // enter 键
         login();
    }
};
//去后台查询数据供给前台显示-首页的新闻咨询
function showHomeNews(){
	$.ajax({
		url:base+'/notice/newNoticeList',
		type:'post',
		success:function(data){
			var html = '';
			var rowsId='';
			$(data.data).each(function(i,cm){
	        	if(i==0){
	        		rowsId+=cm['id'];
	        	}else{
	        		rowsId+=","+cm['id'];
	        	}
	        });
			$(data.data).each(function(i,cm){
				var noticeId=cm['id'];
				var noticeType=cm['noticeType'];
				var noticeTitle=cm['noticeTitle'];
				var noticeImg = cm['noticeImg'];
				var noticeSummary = cm['noticeContent'].split(";split;")[0];
				//var noticeSummary="想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶"
				var changeDate = ym.formatTime(cm['changeDate']);
				var releaseTime = ym.formatDate(cm['releaseTime']);
				var url=base+"/ddwlGw/queryNoticeDetail?title=公司新闻&noticeId="+noticeId+"&rowsId="+rowsId ;
				html+=" <li style=\"width:32% ;\" onclick=\"location.href='"+url+"'\">"+
        		"<div class=\"thumbnail\" style=\"overflow: auto;\">"+
	            "<img src=\""+noticeImg+"\" >"+
	            "<div class=\"caption home-news-font\" >"+
	                "<div class=\"home-news-title\">"+
	                	"<strong>"+noticeTitle+"</strong>"+
	                "</div>"+
	                "<p>"+
	                noticeSummary+
					"</p>"+
					"<div style=\"text-align: right;\">"+
						"<a style=\"margin-right:0;color:#959595;margin-top: 5px\">"+releaseTime+"</a>"+
					"</div>"+
	            "</div>"+
	        "</div>"+
	        "</li>";
			});
			$("#ul_XWZX").html(html);
			replaceSrc();
		}
	});
}
function recoverLi(){
	$(".home-product li").each(function(){
		$(this).children().css({background:'#fff'});
		var imgsrc=$(this).children().find("img").attr("src");
		if(imgsrc.indexOf("2.")>0){
			imgsrc=imgsrc.replace('2.','.');
			$(this).children().find("img").attr("src",imgsrc);
		}
		$(this).children().find("h4").css({color: '#717171'});
		$(this).children().find("p").css({color: '#A9ADB1'});
	});
}


//首页核心产品的展示-只显示最新的四张  
function loadImg(status){   //传入状态为已经启动的才给它显示在页面上
	$.ajax({
		url:base+'/imgManage/getShowImgById',
		type:"post",
		data:{"status":status},
		dataType:"json",
		success:function(data){   //后台状态给它限制,最高只能启动4个,超过4个给他提示
			var html="";
			data = data.data;
			for(var i=0;i<data.length;i++){
				//onclick=\"location.href='"+url+"'\"
				html+="<li onclick=\"location.href='"+data[i].imgUrl+"'\">"+
		        		"<div class=\"thumbnail\" style=\"padding: 50px 0 80px 0;\">"+
			            	"<img src="+data[i].defaultImg+" >"+
					            "<div class=\"caption home-product-font\">"+
					                "<h4 class=\"home-product-title\">"+data[i].mainTitle+"</h4>"+
					                "<p  class=\"home-product-font-5\">"+data[i].subTitle+"</p>"+
					            "</div>"+
					    "</div>"+
					  "</li>";
			}
			$('#ul_ZYCP').append(html);
			replaceSrc();  //替换路径
		}
	});
}


var img1 = loadImg(1);

$(document).on("mouseenter", ".home-product li", function () {
	recoverLi();
	$(this).children().css({background:'#FD2B39'});
	var imgsrc=$(this).children().find("img").attr("src");
	imgsrc=imgsrc.replace('.','2.');
	$(this).children().find("img").attr("src",imgsrc);
	$(this).children().find("h4").css({color: '#fff'});
	$(this).children().find("p").css({color: '#fff'});
});

$(document).on("mouseleave", ".home-product li", function () {
	recoverLi();
});

