<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.yimidida.ows.home.share.entity.OwsUser"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String phone="";
	String email="";
	if(session.getAttribute("owsUser")!=null){
		OwsUser userInfo=(OwsUser)session.getAttribute("owsUser");
		phone=userInfo.getMobile()!=null&&!userInfo.getMobile().equals("")?userInfo.getMobile().substring(0,3)+"****"+userInfo.getMobile().substring(7):null;
		email=userInfo.getEmail();
		if(email!=null&&!email.equals("")){
			String tmp=email.substring(email.indexOf("@"));
			email=email.substring(0,3)+"****"+tmp;
		}
	}else{
		response.sendRedirect("/ddwlGw/forgetPassPord");
	}
%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />
<c:import url="../../commons/common-script.jsp" />
<title>首页</title>

<style type="text/css">
.label{line-height: 30px;padding-left:5px;color: red;font-size: 12px}
.finddiv{width: 100%;text-align: center;}
</style>

</head>
<body class="home-bg">
	<c:import url="../../commons/common-top.jsp" />
	
	<div class="clear"></div>
	
	<div class="container mg">
		<div class="zhucedenglu">
			<div style="font-size:18px; padding-bottom:10px">找回密码</div>
			<div style="height:2px; background-color:#FFCC00; margin-bottom:10px"></div>
			
			<div style="height:40px">
			  <div style="background-image:url(${base}/images/1.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">1:验证会员帐号</div>
			  <div style="background-image:url(${base}/images/2.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">2:选择找回方式</div>
			  <div style="background-image:url(${base}/images/2.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">3:校验帐号身份</div>
			  <div style="background-image:url(${base}/images/33.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">4:重新设置密码</div>
			</div>
			<br>
			<br>
			<div style="height: 325px;padding-top:70px">
			<div style="text-align: center;" id="checktype">
				<p style="padding-bottom: 20px;">您当前会员帐号有两种找回方式,请任意选择一项进行验证：</p>
				<p><input type="radio" style="position: relative; top:2px;" name="type" id="phonebtn" onclick="phonebtn()"> 手机找回</p>
				<p style="padding-top: 10px;"><input type="radio" style="position: relative; top:2px;" name="type" id="mailbtn" onclick="mailbtn()"> 邮箱找回</p>
			</div>
			<div id="phonediv" class="finddiv" style="display: none">
				<p>您正在使用 "手机验证码" 验证身份。验证码已发送至您的手机， 如您未收到请60秒后重新获取，请填写并完成下一步操作：</p>
				<div style="padding-top: 20px;">
				<table style="margin:0 auto">
					<tr style="height: 50px;">
						<td>注册手机号：</td>
						<td align="left">${owsUser.userName}</td>
					</tr>
					<tr style="height: 50px;">
						<td>短信校验码：</td>
						<td><input type="text" id="code" class="form-control" placeholder="短信验证码" style="width: 96px;float: left;"/>
						<input type="button" id="sendMsg" value="发送短信" class="btn" style="width: 75px;height: 34px;background-color: white;border: 1px solid #ccc;line-height:34px;color: black;font-size: 12px;float: left;margin: 0px 0px 0px 10px;" onclick="find_code()"/>
						</td>
					</tr>
				</table>
				</div>
				<div style="width: 100%;text-align: center;">
					<input type="button" value="下一步" class="btn" onclick="next()" style="width: 100px;height: 30px;line-height: 30px;font-size: 12px"/>
				</div>
			</div>
			<div id="maildiv" class="finddiv" style="display: none">
				<p>您正在使用 "邮箱注册" 验证身份。邮件已发送至您的注册邮箱，请进入邮箱完成下一步操作。如未收到请在60秒后点击重新发送：</p>
				<p style="padding-top: 20px;">注册邮箱：<%=email%></p>
				<div style="width: 100%;text-align: center;">
					<input id="send_mail" type="button" value="发送邮件" class="btn" onclick="send_mail()" style="width: 100px;height: 30px;line-height: 30px;font-size: 12px"/>
					<input type="button" value="查看邮箱" class="btn" onclick="search_mail()" style="width: 100px;height: 30px;line-height: 30px;font-size: 12px"/>
				</div>
			</div>
			</div>
		</div>
	</div>
	
	<c:import url="../../commons/common-script.jsp" />
	<script type="text/javascript" src="${base}/scripts/user.js"></script>
	<c:import url="../../commons/common-footer.jsp" />
	
	
	
<script type="text/javascript">
	var u = '<%=phone==null?"":phone%>';
	var m = '<%=email==null?"":email%>';
	
	if((u!=null && u.length!=0) && ( m!=null && m.length!=0)){
		$("#checktype").show();
	}else if(u!=null && u.length!=0){
		$("#phonediv").show();
		$("#checktype").hide();
	}else if(m!=null && m.length!=0){
		$("#maildiv").show();
		$("#checktype").hide();
	}

function phonebtn(){
	$("#checktype").hide();
	$("#phonediv").show();
	$("#maildiv").hide();
}

function mailbtn(){
	$("#checktype").hide();
	$("#phonediv").hide();
	$("#maildiv").show();
}
var mail;
function send_mail(){
	 var user_mail = '${owsUser.email}';
	 var username = '${owsUser.userName}';
		 $("#send_mail").attr("disabled","disabled");
		 clearInterval(mail);
		 var i = 60;
		 mail = setInterval(function() {  
			 if(i -- > 0) {  
				 $("#send_mail").val(i+"秒后重发"); 
			 }else{
				 $("#send_mail").attr("disabled",false);
				 $("#send_mail").val("重新发送");
			 }
		 }, 1000);
			$.post(base+"/register/sendMail",function(){}); 
}

function search_mail(){
	var mail = '${owsUser.email}';
	var index = mail.indexOf("@")+1;
	var subMail = mail.substring(index,mail.lenth);
	window.open("http://mail."+subMail);
} 

var dx;
function find_code(){
	$("#sendMsg").attr("disabled","disabled");
	clearInterval(dx);
	var i = 60;
	dx = setInterval(function() {  
		if(i -- > 0) {  
			$("#sendMsg").val("稍等"+i+"秒"); 
		}else{
			$("#sendMsg").attr("disabled",false);
			$("#sendMsg").val("发送短信");  
		}
	}, 1000);
	$.ajax({  
		url:base+"/register/getFindCode",
		type:"post",
		dataType:"json",
		success:function(data){ 
			if(data=="1"){
				alert("短信已发送");
			}else if(data=='2'){
				alert("短信发送失败");
			}
		}            
	});
}

function next(){
	$.ajax({  
		url:base+"/register/checkFindCode",
		type:"post",
		data:{'code':$("#code").val()},
		dataType:"json",
		success:function(data){ 
			if(data=="1"){
				location.href=base+'/ddwlGw/forgetPassPord_t?i=1';
			}else if(data=='2'){
				alert("校验码不正确");
			}
		}            
	});
}
</script>
</body>
</html>