<%-- <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../commons/common-meta.jsp"%>
<%@include file="../../commons/common-css.jsp"%>
<style type="text/css">
</style>
<title>用户管理-忘记密码</title>
</head>
<body class="" style="background-color: white;">
	<!-- Begin page -->
	<div class="container">
		<%@include file="../common/common-top.jsp"%>
		<div class="row" style="margin-top: 5%;">
			<div class="col-xs-8 col-xs-offset-2"
				style="border: solid 1px #CCCCCC;">
				<div class="row">
					<div class="col-xs-12"
						style="min-height: 70px; line-height: 70px; border-bottom: solid 1px #CCCCCC;">
						<i class="icon-info"></i> <span>请输入您需要重置登录密码的账户名</span>
					</div>
				</div>
				<div class="row" style="margin-top: 8%;">
					<div class="col-xs-11">
						<form role="form" class="form-horizontal forget-form"
							id="forget-form">
							<div class="form-group ">
								<label class="col-xs-3 control-label" for="input-text">你的账号</label>
								<div class="col-xs-9 ">
									<input type="text" name="userName" placeholder="请输入你的账号后获取验证码"
										class="form-control input-text userName">
								</div>
							</div>
							<div class="form-group ">
								<label class="col-xs-3  control-label" for="input-text">验证码</label>
								<div class="col-xs-9">
									<div class="col-xs-8"
										style="padding-right: 0px; padding-left: 0px;">
										<input type="text" name="captcha"
											class="form-control input-text">
									</div>
									<div class="col-xs-4" style="padding-right: 0px; padding-left: 0px;">
										<button type="button" class="btn btn-primary get-captcha"
											style="margin-left: 10px;" disabled="disabled">获取验证码</button>
									</div>
								</div>

							</div>
							<div class="form-group ">
								<label class="col-xs-3  control-label" for="input-text">新的密码</label>

								<div class="col-xs-9 ">
									<input type="password" name="newPassword" class="form-control ">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-3  control-label" for="input-text">确认输入</label>
								<div class="col-xs-9 ">
									<input type="password" name="confirmPassword"
										class="form-control input-text">
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-2 col-xs-offset-5">
									<button type="button"  class="btn btn-success btn-block">提交</button>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>

		</div>
	</div>
	<script>
		var resizefunc = [];
	</script>
	<%@include file="../common/common-script2.jsp"%>
	<script src="${base}/scripts/user/forget.js?${version}"></script>
</body>
</html>
 --%>