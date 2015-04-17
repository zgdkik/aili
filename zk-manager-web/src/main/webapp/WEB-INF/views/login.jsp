<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="common.jsp"></jsp:include>
<html lang="zh-CN">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
* {
	margin: 0;
	padding: 0;
}

body {
	
}

.loginBox {
	width: 300px;
	height: 300px;
	padding: 0 20px;
	border: 1px solid #fff;
	color: #000;
	margin-top: 40px;
	border-radius: 8px;
	background: white;
	box-shadow: 0 0 15px #222;
	background: -moz-linear-gradient(top, #fff, #efefef 8%);
	background: -webkit-gradient(linear, 0 0, 0 100%, from(#f6f6f6),
		to(#f4f4f4));
	font: 11px/1.5em 'Microsoft YaHei';
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -150px;
	margin-top: -315px;
}

.loginBox h2 {
	height: 45px;
	font-size: 30px;
	font-weight: normal;
}

.loginBox input {

	height: 28px;
	margin-left: 40px;
	
}

.loginBox .left {

	height: 100%;
	padding-right: 30px;
}
</style>

<title>用户登录</title>

<script src="${base}/scripts/zk/login.js"></script>
</head>

<body>
	<div class="container">
		<section class="loginBox row-fluid">
			<section><h2 style="text-align: center;margin-top: 50px">zookooper管理</h2></section>
			<section class="span7 left">
				<!-- <h2>用户登录</h2> -->
				<div style="float: left;">
					<label style="float: left;">用户</label><input style="float: left;" type="text" name="user" class="user"/>
				</div>
				<div style="float: left;margin-top: 10px;">
					<label style="float: left;">密码</label><input style="float: left;" type="password" name="pwd" class="pwd" />
				</div>
				<section class="row-fluid">
					<section class="span1">
						<input  style="float: left;margin-top: 10px;margin-left: 40%" type="button" value=" 登录 " class="btn btn-primary login">
					</section>
				</section>
				<div style="float: left;margin-top: 10px;width: 100%;margin-left:33% ">
					<label style="float: left;color: red;" class="error-info"></label>
				</div>
			</section>

		</section>
		<!-- /loginBox -->
	</div>
	<!-- /container -->
</body>
</html>
