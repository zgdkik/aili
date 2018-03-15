<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../common/common-meta.jsp"%>
<%@include file="../../common/common-css.jsp"%>
<%@include file="../../common/common-icon.jsp"%>
<title>权限管理-用户管理</title>
</head>
<body class="fixed-left" locale="${lang}">
	<%@include file="../../common/common-layer.jsp"%>
	<!-- Begin page -->
	<div id="wrapper">

		<%@include file="../../common/common-top.jsp"%>


		<jsp:include page="../../common/common-left.jsp">
			<jsp:param name="pagecode" value="index" />
		</jsp:include>
		<div class="content-page">
			<ol class="breadcrumb">
				<li><a href="${base}">权限管理</a></li>
				<li class="active">用户管理</li>
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
											<form class="form-horizontal query-form" role="form" data-toggle="form-validator" id="role-form">
												<div class="form-group">
													<label class="col-sm-1 control-label" for="input-text">用户名</label>
													<div class="col-sm-2">
														<input type="text" id="userName" name="q_sl_userName" class="form-control" required />
													</div>
													<!-- <label class="col-sm-1 control-label" for="input-text">开始时间</label>
													<div class="input-group date form_datetime1 col-md-2" data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_input1">
														<input class="form-control" size="16" name="q_date_beginDate" type="text" value="" readonly>
														<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
														<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
													</div> -->
												</div>
												
												<div class="form-group">
													<div class="col-md-12">
															<button class="btn  btn-primary pull-right btn-search" type="button"><i class="icon-search"></i>查询</button>
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
										href="#query-list">用户列表</a>
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
													<!-- <button type="submit" id="deletebatch" class="btn btn-default">批量删除</button> -->
													<sds:security code="1022">
													<button type="button" id="adduser" class="btn btn-default">添加用户</button>
													</sds:security>
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
	
	</div>
	<%@include file="../../common/common-footer.jsp"%>
	<%@include file="../../common/common-modal-footer.jsp"%>
	<script>
		var resizefunc = [];
	</script>
	<%@include file="../../common/common-script.jsp"%>
	<script src="${base}/scripts/user/user.js?${version}"></script>
</body>
</html>