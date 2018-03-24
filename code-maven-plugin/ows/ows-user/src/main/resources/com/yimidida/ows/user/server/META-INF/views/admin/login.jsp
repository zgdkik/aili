<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />
<link href="${base}/styles/home/login.css?${version}" rel="stylesheet">
<style type="text/css">
.row {
	width: 0;
	height: 0;
	position: fixed;
	left: 50%;
	rigth: 50%;
	top: 50%;
	bottom: 50%;
}

.backBg {
	width: 400px;
	height: 320px;
	margin-left: -200px;
	margin-top: -200px;
	filter: alpha(opacity = 85);
	opacity: 0.85;
	position: absolute;
	top: 0px;
	left: 0px;
}
</style>
<title>壹米滴答-用户登录</title>
</head>
<body class="fixed-left login-page"
	style="background: url('${base}/images/home/icon/login.png') top center no-repeat ;background-size:cover;">
	<!-- Begin page -->
	<div class="container">
		<div class="row">
			<div class="col-xs-4 col-xs-offset-4">
				<div class="backBg">
					<form role="form" class="form-horizontal" method="post"
						action="${base}/user/login" id="login-form">
						<div class="form-group">
							<div class="col-xs-12 " style="text-align: center;">
								<img alt="用户登录" title="用户登录"
									src="${base}/images/home/icon/login-logo.png">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-offset-1 col-xs-10 ">
								<input type="text" name="userName" class="form-control"
									placeholder="请输入你的账号">
							</div>
						</div>

						<div class="form-group">
							<div class="col-xs-offset-1 col-xs-10  ">
								<input type="password" name="password" class="form-control"
									placeholder="请输入你的密码 ">
							</div>
						</div>

						<div class="form-group ">
							<div class="col-xs-4 col-xs-offset-4" style="text-align: center;">
								<!-- <button type="reset" class="btn btn-success"  style="float: left;">重置</button> -->
								<button type="submit" class="btn btn-success login"
									style="margin-left:auto; margin-left: auto;">登录</button>
							</div>
						</div>
						<%-- 	<div class="form-group">
								<div class="col-xs-4 col-xs-offset-8">
									<a href="${base}/user/forget">忘记密码</a>
								</div>
							</div> --%>
						<div class="col-xs-12"
							style="color: red; text-align: center; margin-top: 10px; margin-bottom: 10px; font-size: 16px;">
							${msg}</div>

					</form>

				</div>
			</div>
		</div>
	</div>
	<c:import url="../../commons/common-script.jsp" />
	<script type="text/javascript"
		src="${base}/scripts/user/user/login.js?${version}"></script>
</body>
</html>
