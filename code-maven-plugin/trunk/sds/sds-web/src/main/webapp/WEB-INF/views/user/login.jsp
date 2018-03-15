<%@include file="../common/common.jsp"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/common-meta.jsp"%>
<%@include file="../common/common-css2.jsp"%>
<%@include file="../common/common-icon.jsp"%>
<link href="${base}/styles/home/main.min.css?${version}"
	rel="stylesheet" />
<link href="${base}/styles/home/custom.css?${version}" rel="stylesheet" />
<style type="text/css">
@media only screen and (min-width:1361px) and (max-width:1600px) {
	.col-md-offset-3 {
		margin-left: 20%;
	}
	.col-md-6 {
		width: 60%;
	}
}

@media only screen and (min-width:980px) and (max-width:1360px) {
	.col-md-offset-3 {
		margin-left: 15%;
	}
	.col-md-6 {
		width: 70%;
	}
}
</style>
<title>壹米滴答-用户登录</title>
</head>
<body class="fixed-left login-page"
	style="background: url('${base}/images/home/icon/login.png') top center no-repeat ;background-size:cover;">
	<!-- Begin page -->
	<div class="container">
		<div class="row" style="margin-top: 10%;">
			<div class="col-md-6 col-md-offset-4">

				<div class="row" style="">
					<div class="col-md-5 backBg">
						<form role="form" class="form-horizontal forget-form"
							method="post" action="${base}/user/login" id="forget-form">
							<div class="form-group ">
								<div class="col-md-12 " style="text-align: center;">
									<img alt="用户登录" title="用户登录"
										src="${base}/images/home/icon/logo.png">
								</div>
							</div>
							<div class="form-group marT">

								<div class="col-md-offset-1 col-md-10 ">
									<input type="text" name="userName"
										class="form-control input-text userName" placeholder="请输入你的账号">
								</div>
							</div>

							<div class="form-group marT40">

								<div class="col-md-offset-1 col-md-10  ">
									<input type="password" name="password"
										class="form-control input-text password"
										placeholder="请输入你的密码 ">
								</div>
							</div>

							<div class="form-group marT80">
								<div class="col-md-10 col-md-offset-1">
									<button type="submit" class="btn btn-success btn-block">登录</button>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4 col-md-offset-8">
									<a href="${base}/user/forget">忘记密码</a>
								</div>
							</div>
							<div class="col-md-12"
								style="color: red; text-align: center; margin-top: 10px; margin-bottom: 10px; font-size: 16px;">
								${msg}</div>

						</form>


					</div>
				</div>
			</div>

		</div>
	</div>
	<%@include file="../common/common-script2.jsp"%>
</body>
</html>
