<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}-用户注册</title>
<style type="text/css">
.lrborder {
	border: 1px solid #999999;
	margin-top: 30px;
}

.setting-font {
	font-family: '宋体 Bold', '宋体';
	font-weight: 700;
	font-style: normal;
	height: 40px;
	line-height: 40px;
	border-bottom: 2px solid #FF9900;
}

.setting-font1 {
	font-family: '宋体 Bold', '宋体';
	font-style: normal;
	line-height: 60px;
}

.setting-font1 input {
	height: 30px;
	float: left;
	width: 30%;
}
</style>
<script src="${scripts}/reg/data.js"></script>
<script type="text/javascript">
function refreshimg(){
    document.getElementById("virfyCode").src="${base}core/getRandcode.htm?code="+Math.random();
    return true;
  }
</script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- 中间部分 -->
	<div class="row-fluid" style="min-height: 500px">
		<div class="span1" style="margin-left: 60px;"></div>

		<div class="span2" style="margin-left: 0px;"></div>
		<div class="span6" style="margin-left: 0px;">
			<div class="lrborder" style="width: 100%; float: left;">
				<div class="setting-font" style="">
					<div
						style="margin-left: 3px; background-color: #F2F2F2; width: 30%;float: left;">欢迎注册</div>
					<div
						style="float: right;margin-right: 5px;">
						<span style="font-weight: normal;">我已注册,</span>
						<a href="${base}user/loginpage.htm" style="text-decoration: underline;">登录</a>
						</div>
				</div>
				<div class="setting-font1"
					style="font-weight: 700;margin-top: 50px;">
					<div style="height: 40px; width: 100%;">
						<div
							style="width: 80px; float: left; height: 40px; line-height: 40px; margin-left: 25%;">用户名:</div>
						<input type="text" class="username required" title="请输入用户名" style="margin-right: 25%;">
					</div>
				</div>
				
				<div class="setting-font1"
					style="font-weight: 700;">
					<div style="height: 40px; width: 100%;">
						<div
							style="width: 80px; float: left; height: 40px; line-height: 40px; margin-left: 25%;">邮箱:</div>
						<input type="text" class="email required" title="请输入邮箱" style="margin-right: 25%;">
					</div>
				</div>

				<div class="setting-font1" style="font-weight: 700;">
					<div style="height: 40px; width: 100%;">
						<div
							style="width: 80px; float: left; height: 40px; line-height: 40px; margin-left: 25%;">密码:</div>
						<input type="password" class="pwd1 required" title="请输入密码" style="margin-right: 25%;">
					</div>
				</div>
				
				<div class="setting-font1" style="font-weight: 700;">
					<div style="height: 40px; width: 100%;">
						<div
							style="width: 80px; float: left; height: 40px; line-height: 40px; margin-left: 25%;">确认密码:</div>
						<input type="password" class="pwd2 required" title="请输入确认密码" style="margin-right: 25%;">
					</div>
				</div>
				
				<div class="setting-font1"
					style="font-weight: 700;margin-top: 10px;">
					<div style="height: 40px; width: 100%;">
						<div
							style="width: 80px; float: left; height: 40px; line-height: 40px; margin-left: 25%;">验证码:</div>
						<input type="text" class="code required" title="请输入验证码" style="width: 30%">
						<div style="float: left;">
						<img style="height: 30px;" id="virfyCode" onclick="javascript:refreshimg()" src="${base}core/getRandcode.htm">
						</div>
					</div>
				</div>
				
				<div class="" style="font-weight: 700;float: left;margin-left: 35%;">
					<div  style="float: left;margin-bottom: 50px;margin-top: 20px;">
						<input type="button" class="reg" value="注册" style="background-image: url('/images/user/reg/u26.png');
						border: 0;width:180px;height: 40px;font-size: 20px;color: #FFFFFF;"> 
					</div>
				</div>
				
			</div>
		</div>

		<div class="span2" style="margin-left: 0px;"></div>

		<div class="span1"></div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
