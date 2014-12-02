<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}-用户登陆</title>
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
	height: 60px;
	line-height: 60px;
}

.setting-font1 input {
	height: 30px;
	float: left;
	width: 30%;
	margin-top: 5px;
}
</style>
<script src="${scripts}/login/jquery.cookie.js"></script>
<script src="${scripts}/login/data.js"></script>
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
						style="margin-left: 3px; background-color: #F2F2F2; width: 30%">欢迎登录</div>
				</div>
				<div class="setting-font1"
					style="font-weight: 700; margin-top: 100px;">
					<div style="height: 40px; width: 100%; margin-top: 5px;">
						<div
							style="width: 80px; float: left; height: 40px; line-height: 40px; margin-left: 25%;">用户名:</div>
						<input type="text" class="email" title="请输入用户名" style="margin-right: 25%;">
					</div>
				</div>

				<div class="setting-font1" style="font-weight: 700;">
					<div style="height: 40px; width: 100%; margin-top: 5px;">
						<div
							style="width: 80px; float: left; height: 40px; line-height: 40px; margin-left: 25%;">密码:</div>
						<input type="password" class="password" title="请输入密码" style="margin-right: 25%;">
					</div>
				</div>
				<div class="" style="font-weight: 700;">
					<div style="height: 40px; width: 100%; margin-top: 5px;">
						<div class="checkbox"  style="float: left;margin-left: 35%;">
							<label> <input type="checkbox" class="rememberme" > 记住密码
							</label>
						</div>
						<div style="float: left;margin-left: 5%;text-decoration: underline;">
							<a href="javascript:void(0)" class="forget-pwd">忘记密码?</a>
						</div>
					</div>
				</div>
				
				<div class="" style="font-weight: 700;float: left;margin-left: 35%;">
					<div  style="float: left;">
						<input type="button" class="login" value="登录" style="background-image: url('/images/user/reg/u26.png');
						border: 0;width:180px;height: 40px;font-size: 20px;color: #FFFFFF;"> 
					</div>
					<div style="margin-left: 20%;float: left;margin-bottom: 100px;text-decoration: underline;margin-top: 10px;">
						<a href="${base}user/register.htm" >我要注册</a>
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
