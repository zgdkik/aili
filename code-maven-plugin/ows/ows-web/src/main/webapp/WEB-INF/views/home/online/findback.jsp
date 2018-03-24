<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />




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
			  <div style="background-image:url(${base}/images/22.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">2:选择找回方式</div>
			  <div style="background-image:url(${base}/images/22.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">3:校验帐号身份</div>
			  <div style="background-image:url(${base}/images/33.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">4:重新设置密码</div>
			</div>
			<br>
			<!-- <div style="height:40px">
			  <div style="background-image:url(../images/11.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">1:输入用户名</div>
			  <div style="background-image:url(../images/22.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">2:找回方式</div>
			  <div style="background-image:url(../images/22.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">3:输入身份</div>
			  <div style="background-image:url(../images/33.png); width:252px; height:40px; float:left; line-height:40px; color:#FFFFFF; padding-left:80px">4:重置密码</div>
			</div> -->
			<br>
			<div style="height: 325px;padding-top:70px">
				<table>
				  <tr>
					  <td style="width:400px;" align="right">会员名：</td>
					  <td colspan="2"><input id="username" type="text" class="form-control" placeholder="会员名/手机号码/注册邮箱" style="width:200px;"/></td>
				  </tr>
				  <tr>
					  <td style="width:400px;padding-top:10px" align="right">验证码：</td>
					  <td style="padding:10px 14px 0px 0px"><input id="findcode" type="text" class="form-control" placeholder="验证码" style="width:110px;"/></td>
					  <td style="padding-top:10px"><img id="imgphonecode" src="${base}/register/createCode/phonecertpic" alt="" width="76" height="34" title="看不清？换一张" style="cursor: pointer" onclick="reloadphonecode()"/></td>
				  </tr>
				</table>
				<div style="width: 100%;text-align: center;">
				
					<input type="button" value="找回密码" class="btn" onclick="find_1()" style="width: 120px;font-size: 12px;"/>
				</div>
			</div>
		</div>
	</div>
	
	<c:import url="../../commons/common-script.jsp" />
	<script type="text/javascript" src="${base}/scripts/user.js"></script>
	<c:import url="../../commons/common-footer.jsp" />
<script type="text/javascript">

function reloadphonecode(){
    var verify=document.getElementById('imgphonecode');
    verify.setAttribute('src',base+'/register/createCode/phonecertpic?it='+Math.random());
}

function find_1(){
	var username = $("#username").val();
	if(username.length==0){
		alert("请输入会员帐号");
		$("#username").focus();
		return;
	}
	
	var code = $("#findcode").val();
	if(code==''){
		alert("请输入验证码");
		$("#findcode").focus();
		return;
	}
	
	$.ajax({  
		url:base+"/register/findone",
		type:"post",
		data:{"findcode":code,"username":username},
		dataType:"json",
		success:function(arr){ 
			if(arr=="1"){
				location.href='${base}/ddwlGw/forgetPassPord_0'
			}else if(arr=='2'){
				alert("该会员不存在");
				reloadphonecode();
			}else if(arr=='3'){
				alert("验证码不正确");
				reloadphonecode();
			}
		}            
	});
}

</script>
</body>
</html>