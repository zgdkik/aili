<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../commons/common-meta.jsp" />
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
	height: 250px;
	margin-left: -200px;
	margin-top: -200px;
	filter: alpha(opacity = 85);
	opacity: 0.85;
	position: absolute;
	top: 0px;
	left: 0px;
}
.col-xs-offset-1{
 margin-left: 13%
}
.col-xs-10{
	width: 74%
}
.btn-success {
    color: #000;
    background-color: #00B400;
    border-color: #00B400;
}
.btn {
    display: inline-block;
    padding: 6px 12px;
    margin-bottom: 0;
    font-size: 14px;
    font-weight: 400;
    line-height: 1.42857143;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 4px;
}
</style>
<title>HBHK-用户登录</title>
</head>
<body class="fixed-left login-page"
	<%-- style="background: url('${base}/images/home/icon/login.png') top center no-repeat ;background-size:cover;" --%>>
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
									src="${base}/images/home/logo1.png">
							</div>
						</div>
						<div class="form-group" style="margin-top: 20px;">
							<div class="col-xs-offset-1 col-xs-10 ">
								<input type="text" name="userName" class="form-control"
									placeholder="请输入你的账号" style="width: 100% ; height: 27px;" >
							</div>
						</div>

						<div class="form-group" style="margin-top: 5px;">
							<div class="col-xs-offset-1 col-xs-10  ">
								<input type="password" name="password" class="form-control"
									placeholder="请输入你的密码 " style="width: 100%;height: 27px;" >
							</div>
						</div>

						<div class="form-group " style="margin-top: 10px;">
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
	<script src="${staticbase}/easyui/jquery.min.js?${version}"></script>
</body>
</html>
