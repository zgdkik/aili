<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<c:import url="../commons/common-meta.jsp" />
<c:import url="../commons/common-css.jsp" />
<link href="${base}/styles/home/custom.css?${version}" rel="stylesheet">
</head>
<body class="sticky-header">
	<section>
		<!--body wrapper start-->
		<div class="wrapper">
			<div class="row">
				<div class="col-sm-12">
					<div class="panel">
						<div class="panel-heading">
							<div class="panel-title">查询条件</div>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-12">
									<form role="form" class="form-horizontal adminex-form">
										<div class="form-group">
											<div class="col-xs-6">
												<label class="col-xs-1 control-label">名称</label>
												<div class="col-xs-3">
													<input type="text" placeholder="" name="roleName" class="form-control">
												</div>
												<label class="col-xs-1 control-label">编码</label>
												<div class="col-xs-3">
													<input type="text" placeholder="" name="roleCode" class="form-control">
												</div>
												<div class="col-xs-4">
													<button class="btn btn-primary" type="button">查询</button>
													<button class="btn btn-primary" type="reset">重置</button>
												</div>
											</div>

										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!--用户列表  -->
					<div class="panel">
						<div class="panel-heading">
							<div class="panel-title">角色列表</div>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-12">
									<table id="table" data-toggle="table" data-show-columns="true"
										data-show-toggle="true" data-pagination="true"
										data-height="500" data-toolbar="#custom-toolbar">
									</table>
									<div id="custom-toolbar">
										<div class="form-inline" role="form">
											<button type="button" class="btn btn-default addnew">新增</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--body wrapper end-->
	</section>
	<c:import url="../commons/common-script.jsp" />
	<script type="text/javascript"
		src="${base}/scripts/user/role/userlist.js?${version}"></script>
</body>
</html>
