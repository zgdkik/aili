<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />
<style>
	.label{color:red}
</style>
		
</head>
<body style="width: 100%;" class="home-bg">
		<c:import url="../../commons/common-top.jsp" />
		<div class="clear"></div>
	<div class="container mg">
		<div class="zhucedenglu">
			<div class="ub">
				<div class="left">
				  <!-- Nav tabs -->
				  <ul class="nav nav-tabs" role="tablist">
				    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">手机注册</a></li>
<!-- 				    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">邮箱注册</a></li>
 -->				  </ul>
				
				  <!-- Tab panes -->
				  <div class="tab-content">
				    <div role="tabpanel" class="tab-pane active" id="home">
				   	 <form method="post" id="phone_form" style="margin: 0px">
				    	<table>
				    		<tbody>
				    			<tr><td width="80" align="right">手机号码：</td><td>
				    			<input type="text" id="phone" name="userPhone" class="form-control" placeholder="11位数字手机号" onblur="ckeck_phone()" style="width: 182px"/>
				    			<span class="label" id="err_phone"></span></td></tr>
				    			<tr><td align="right">密码：</td><td>
				    			<input type="password" id="phonepwd" name="password" class="form-control" placeholder="6-16位字符" style="width: 182px" maxlength="16" onblur="check_phonepwd()"/>
				    			<span class="label" id="err_phonepwd"></span></td></tr>
				    			<tr><td align="right">确认密码：</td><td>
				    			<input type="password" id="cphonepwd" class="form-control" placeholder="确认密码" style="width: 182px" maxlength="16" onblur="check_cphonepwd()"/>
				    			<span class="label" id="err_cphonepwd"></span></td></tr>
				    			
				    			<tr><td align="right">验证码：</td><td>
				    			<input type="text" id="phonecode" name="phonecode" class="form-control yzm" placeholder="验证码" style="width: 96px" onblur="check_phonecode()"/>
				    			<span class="img"><img id="imgphonecode" src="${base}/register/createCode/phonecertpic" alt="" width="76" height="34" title="看不清？换一张" style="cursor: pointer" onclick="reloadphonecode()"/></span>
				    			<span class="label" id="err_phonecode"></span></td></tr>
				    			<tr><td align="right">短信验证码：</td><td>
				    			<input type="text" name="msgcode" id="msgcode" class="form-control" placeholder="短信验证码" style="width: 96px;" maxlength="6" onblur="check_msgcode()"/>&nbsp;&nbsp;
				    			<input type="button" id="sendMsg" value="发送短信" disabled=true class="btn" style="width:75px;height: 34px;background-color:gray;border: 1px solid #ccc;line-height:34px;color: black;" onclick="send_msg()" />
				    			<span class="label" id="err_msgcode" style="margin-left:-33px"></span></td></tr>
				    			<tr><td align="right"></td><td><input type="button" value="注册" class="btn" onclick="phone_reg()" style="width: 182px"/></td></tr>
				    		</tbody>
				    	</table>
				    	</form>
				    </div>
				    <div role="tabpanel" class="tab-pane" id="profile">
				    	<form method="post" id="mail_form" style="margin: 0px">
					    	<table>
					    		<tbody>
					    			<tr><td width="80" align="right">用户名：</td><td>
					    			<input id="uname" name="vipName" style="width: 182px" type="text" class="form-control" placeholder="英文开头6-16位字符" onblur="ckeck_username()"/>
					    			<span class="label" id="err_username"></span></td></tr>
					    			<tr><td align="right">密码：</td><td>
					    			<input type="password" name="password" id="mailpwd" style="width: 182px"  class="form-control" placeholder="6-16位字符" onblur="check_mailpwd()"/>
					    			<span class="label" id="err_mailpwd"></span></td></tr>
					    			<tr><td align="right">确认密码：</td><td>
					    			<input type="password" class="form-control" id="cmailpwd" placeholder="确认密码" onblur="check_cmailpwd()" style="width: 182px"/>
					    			<span class="label" id="err_cmailpwd"></span></td></tr>
					    			<tr><td align="right">邮箱地址：</td><td>
					    			<input id="mail" name="mail" type="text" class="form-control" placeholder="找回密码凭证"  style="width: 182px" onblur="check_mail()"/>
					    			<span class="label" id="err_mail"></span></td></tr>
					    			<tr><td align="right">验证码：</td><td>
					    			<input id="mailcode" name="mailcode" type="text" class="form-control yzm" placeholder="验证码" style="width: 96px" onblur="check_mailcode()"/>
					    			<span class="img"><img id="imgmailcode" src="${base}/register/createCode/mailcertpic" alt="" width="76" height="34" title="看不清？换一张" style="cursor: pointer" onclick="reloadmailcode()"/></span>
					    			<span class="label" id="err_mailcode"></span></td></tr>
					    			<tr><td align="right"></td><td><input type="button" value="注册" class="btn" onclick="mail_reg()" style="width: 182px"/></td></tr>
					    		</tbody>
					    	</table>
				    	</form>
				    </div>
				  </div>
				</div>
				<div class="right">
					<div class="title ub"><span>会员登录</span></div>
					<form method="post" id="loginInput">
						<table>
				    		<tbody>
				    			<tr><td width="80" align="right">会员名：</td><td><input name="userName" id="loginName" type="text" class="form-control" placeholder="用户名/手机号码/邮箱地址" /></td></tr>
				    			<tr><td align="right">密码：</td><td><input type="password" name="password" id="password" class="form-control" placeholder="密码" /></td></tr>
				    			<tr><td align="right"></td><td><a href="${base}/ddwlGw/forgetPassPord">忘记密码?</a></td></tr>
				    			<tr><td align="right"></td><td><input type="button"  value="登录" onclick="login()"  class="btn" /></td></tr>
				    		</tbody>
				    	</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!--焦点图滚动-->
    
	
	<c:import url="../../commons/common-script.jsp" />
	<script type="text/javascript" src="${base}/scripts/user.js"></script>
	<c:import url="../../commons/common-footer.jsp" />
	<script type="text/javascript">
		var marginTop=$(window).height()-$(".footer").offset().top-$(".footer").height()-50-20;
		if(marginTop>0){
			$(".footer").css({"marginTop":marginTop});
		}
	</script>
	
</body>
</html>
