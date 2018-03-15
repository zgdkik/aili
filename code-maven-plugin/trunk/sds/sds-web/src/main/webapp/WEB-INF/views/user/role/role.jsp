<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../common/common-meta.jsp"%>
<%@include file="../../common/common-css.jsp"%>
<%@include file="../../common/common-icon.jsp"%>
<title>权限管理-角色管理</title>
</head>
<body class="fixed-left" locale="${lang}">
	<%@include file="../../common/common-layer.jsp"%>
	<!-- Begin page -->
	<div id="wrapper">

		<%@include file="../../common/common-top.jsp"%>

		<jsp:include page="../../common/common-left.jsp">
			<jsp:param name="pagecode" value="index" />
		</jsp:include>

		<!-- Start right content -->
		<div class="content-page">
			<ol class="breadcrumb">
				<li><a href="${base}">权限管理</a></li>
				<li class="active">角色管理</li>
			</ol>
			<div class="content">
				<!--主要内容开始部分 START-->
				<div class="row">
					<div class="col-md-12 ">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#query-form">查询条件</a>
								</h3>
							</div>
							<div id="query-form" class="panel-collapse collapse in">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12">
											<form class="form-horizontal query-form" role="form"
												data-toggle="form-validator" id="role-form">
												<div class="form-group">

													<label class="col-md-1 col-md-offset-1 control-label"
														for="input-text">角色名称</label>
													<div class="col-md-3">
														<input type="text" id="roleName" name="roleName"
															class="form-control name" required />
													</div>
													<label class="col-md-1 control-label" for="input-text">角色类型</label>
													<div class="col-md-3">
														<select id="roleType" name="roleType"
															class="form-control selectpicker">
															<option value="">全部</option>
															<sds:dictSelect dictType="role_type" />
														</select>
													</div>
												</div>

												<div class="form-group">
													<div class="col-md-12">
														<button class="btn btn-primary pull-right btn-resetBtn"
															type="reset" id="resetBtn" name="reset">
															<i class="icon"></i>x&nbsp;&nbsp;清空
														</button>
														<button class="btn  btn-primary pull-right btn-search"
															type="button">
															<i class="icon-search"></i>查询
														</button>
													</div>
												</div>

											</form>
										</div>


									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 ">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#query-list">角色列表</a>
								</h3>
							</div>
							<div id="query-list" class="panel-collapse collapse in">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12">
											<table id="table" data-toggle="table"
												data-show-columns="true" data-show-toggle="true"
												data-pagination="true" data-height="500"
												data-toolbar="#custom-toolbar">
											</table>
											<div id="custom-toolbar">
												<div class="form-inline" role="form">
													<!-- <button type="submit" id="deletebatch"
														class="btn btn-default">批量删除</button> -->
													<button type="button" class="btn btn-default">
														<a href="${base}/role/showRoleForm"><font
															color="#FFFFFF">新增角色</font></a>
													</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--主要内容开始部分 END-->
			</div>
			<!-- ============================================================== -->
			<!-- End content here -->
			<!-- ============================================================== -->

		</div>

		<!-- End right content -->

	</div>
	<%@include file="../../common/common-footer.jsp"%>
	<!-- End of page -->
	<!-- the overlay modal element -->

	<%@include file="../../common/common-modal-footer.jsp"%>
	<!-- End of eoverlay modal -->
	<script>
		var resizefunc = [];
	</script>
	<%@include file="../../common/common-script.jsp"%>

	<!-- Page Specific JS Libraries -->
	<script src="${base}/scripts/role/role.js?${version}"></script>
</body>
</html>