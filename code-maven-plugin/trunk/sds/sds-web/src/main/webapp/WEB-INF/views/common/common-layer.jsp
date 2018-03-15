<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal Start -->
<!-- Modal Logout -->
<div class="md-modal md-3d-slit" id="logout-modal">
	<div class="md-content" style="height: 120px;">
		<div>
			<p class="text-center">你确定退出么?</p>
			<p class="text-center">
				<!-- <button class="btn btn-default md-close">取消</button> -->
				<a href="${base}/user/logout" class="btn btn-danger md-close">确定退出</a>
				<a href="javascript:void(0)" class="btn btn-default md-close logout-close">取消</a>
			</p>
		</div>
	</div>
</div>

<div class="modal fade" id="pwd-change" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">修改密码</h4>
			</div>
			<div class="modal-body">
				<form role="form" class="form-horizontal forget-form"
					id="pwd-change-form">
					<div class="form-group">
						<label class="col-md-3 control-label" for="input-text">密码</label>
						<div class="col-md-9 ">
							<input type="password" name="password"
								class="form-control input-text password">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="input-text">新密码</label>
						<div class="col-md-9 ">
							<input type="password" name="newPassword"
								class="form-control input-text password">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="input-text">确认密码</label>
						<div class="col-md-9 ">
							<input type="password" name="confirmPassword"
								class="form-control input-text password">
						</div>
					</div>

				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default " data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary submit-pwd">提交更改</button>
			</div>
		</div>
	</div>
</div>
<!-- Modal End -->

<!-- <div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h3>登录</h3>
			<p>请输入E-mail和密码</p>
		</div>
		<div class="modal-body">
			<form action="admin/user/login" method="post" accept-charset="utf-8">
				<table class="table">
					<tr>
						<td>Email</td>
						<td><input type="text" name="email" value=""
							class="form-control" placeholder="请输入你的E-mail" /></td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input type="password" name="密码" value=""
							class="form-control" placeholder="请输入你的密码" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" name="login" value="登入"
							class="btn btn-primary btn-lg btn-block" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="modal-footer">© xiaobin_hlj80</div>
	</div>
</div>
 -->