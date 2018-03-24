<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.yimidida.ows.home.share.entity.OwsUser"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<%
	String sid = request.getParameter("sid");
	String username= request.getParameter("username");
	String i = request.getParameter("i");
	OwsUser user = (OwsUser)session.getAttribute("owsUserTwo");
	int uu = 0;
	if(user==null){
		uu=1;
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
</style>
<script type="text/javascript">
$(function(){
	var i ='<%=i%>';
	if(i==2){
		var sid ='<%=sid%>';
		var username ='<%=username%>';
		$.ajax({  
			url:base+"/register/sendMailCheckLink",
			type:"post",
			data:{'username':username,'sid':sid},
			dataType:"json",
			success:function(arr){ 
				if(arr=="1"){
					$("#update").attr("onclick","on_mail()");
					$("#input_form").show();
				}else if(arr=='2'){
					alert("很抱歉，您访问的链接已经失效，请您重新操作！");
					back_home();
				}
			}            
		});
	}else if(i==1){
		if(<%=uu%>==1){
			back_home();
		}
		$("#update").attr("onclick","on_phone()");
		$("#input_form").show();
	}else{
		back_home();
	}
})
function back_home(){
	location.href=base;
}

function on_phone(){
	var password = $("#password").val();
	var rePassword = $("#rePassword").val();
	if(password.length<6 ||password.length>16){
		alert("密码长度为6-16位字符");
		return;
	}
	if(rePassword!=password){
		alert("两次密码输入不一致");
		return;
	}
	$.ajax({  
		url: base+"/register/modifyPass",
		type:"post",
		data:{'newpassword':password},
		dataType:"json",
		success:function(arr){ 
			if(arr=="1"){
				alert("密码修改成功");
				location.href=base+'/ddwlGw/register';
			}else if(arr=='2'){
				alert("密码修改,请重新尝试操作！")
			  //location.href=base+'/ddwlGw/forgetPassPord';
			}
		}            
	}); 
}

function on_mail(){
	var sid ='<%=sid%>';
	var username ='<%=username%>';
	$.ajax({  
		url: base+"/register/sendMailCheckLink",
		type:"post",
		data:{'username':username,'sid':sid},
		dataType:"json",
		success:function(arr){ 
			if(arr=="1"){
				var password = $("#password").val();
				var rePassword = $("#rePassword").val();
				if(password.length<6 ||password.length>16){
					alert("密码长度为6-16位字符");
					return;
				}
				if(rePassword!=password){
					alert("两次密码输入不一致");
					return;
				}
				$.ajax({  
					url:base+"/register/resetPass",
					type:"post",
					data:{'username':username,'newpassword':password,'sid':sid},
					dataType:"json",
					success:function(arr){ 
						if(arr=="1"){
							alert("恭喜你,密码重置成功,请使用新密码登录");
							location.href=base+'/ddwlGw/register';
						}else{
							alert("密码重置失败,请重新尝试操作！")
							location.href=base+'/ddwlGw/forgetPassPord';
						}
					}            
				});
			}else{
				location.href=base;
			}
			
		}            
	});
}
</script>
</head>
<body class="home-bg">
	 <c:import url="../../commons/common-top.jsp" />
	
	<div class="clear"></div>
	
	<div class="container mg">
		<div class="zhucedenglu">
			<div style="font-size:18px; padding-bottom:10px">重置密码</div>
			<div style="height:2px; background-color:#FFCC00; margin-bottom:10px"></div>
			
			<div style="height:40px">
			  <div style="background-image:url(${base}/images/1.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">1:验证会员帐号</div>
			  <div style="background-image:url(${base}/images/2.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">2:选择找回方式</div>
			  <div style="background-image:url(${base}/images/2.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">3:校验帐号身份</div>
			  <div style="background-image:url(${base}/images/3.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">4:重新设置密码</div>
			</div>
			<br>
			<br>
			<div style="height: 325px;padding-top:70px">
			<table style="margin: 0 auto;display: none;" id="input_form">
				<tr style="height: 50px">
					<td align="right">新密码：</td>
					<td><input id="password" type="password" class="form-control" placeholder="6-16位字符新密码" style="width:200px;" maxlength="16"/></td>
				</tr>
				<tr style="height: 50px">
					<td>确认密码：</td>
					<td><input id="rePassword" type="password" class="form-control" placeholder="确认密码" style="width:200px;" maxlength="16"/></td>
				</tr>
				<tr style="height: 50px">
					<td></td>
					<td><input id="update" type="button" value="确定重置" class="btn" onclick="on_mail()" style="width: 95px;height:34px;line-height:34px;font-size: 12px;"/>
						<input id="update" type="button" value="返回登录" class="btn" onclick="back_home()" style="width: 95px;height:34px;line-height:34px;font-size: 12px;"/>
					</td>
				</tr>
			</table>
			</div>
		</div>
	</div>
	
   <c:import url="../../commons/common-script.jsp" />
   <script type="text/javascript" src="${base}/scripts/user.js"></script>
   <c:import url="../../commons/common-footer.jsp" />
</body>
</html>