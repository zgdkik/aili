<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>智送叫车-客服管理</title>
<%@include file="../../common/common-meta.jsp"%>
<%@include file="../../common/common-css.jsp"%>
<%@include file="../../common/common-icon.jsp"%>
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
			<li><a href="${base}">系统配置</a></li>
				<li class="active">客服管理</li>
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
														for="input-text">城市</label>
													<div class="col-md-2">
														<div class="select">
															<select class="form-control selectpicker" name="city"
																id="city">
																<option value="">请选择</option>
																<c:forEach items="${citylist}" var="city">
																	<option value="${city.name}">${city.name}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="col-md-1">
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
					<div class="col-md-12 ">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#query-list">客服列表</a>
								</h3>
							</div>
							<div id="query-list" class="panel-collapse collapse in">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12">
											<table id="adminDemandTable" data-toggle="table"
												data-show-columns="true" data-show-toggle="true"
												data-pagination="true" data-height="500"
												data-toolbar="#custom-toolbar">
											</table>
											<div id="custom-toolbar">
												<div class="form-inline" role="form">
													<button type="button" id="add" class="btn btn-default">新增客服</button>
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
		</div>
	</div>
	<div class="modal fade" id="my-modal"  role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">新增客服</h4>
				</div>
				<div class="modal-body">
					<form role="form" class="form-horizontal cs-form" id="cs-form">
						<div class="form-group">
							<label class="col-md-2 control-label" for="input-text">城市</label>
							<div class="col-md-8 ">
								<select class="form-control selectpicker city" name="city"
									style="width: 100%">
									<option value="">请选择</option>
									<c:forEach items="${citylist}" var="city">
										<option value="${city.name}">${city.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="input-text">客服</label>
							<div class="col-md-8 ">
								<select class="form-control selectpicker-ajax workCode" name="workCode"
									style="width: 100%">
									<option value="">请选择</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="input-text">电话</label>
							<div class="col-md-8 ">
								<input type="text" name="phone" class="form-control input-text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="input-text">备注</label>
							<div class="col-md-8 ">
								<textarea rows="" cols="" class="form-control input-text "
									name="descp"></textarea>
							</div>
						</div>

					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary save">保存</button>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../../common/common-footer.jsp"%>
	<%@include file="../../common/common-modal-footer.jsp"%>
	<!-- End of eoverlay modal -->
	<%@include file="../../common/common-script.jsp"%>

	<!-- Page Specific JS Libraries -->

	<script src="${base}/scripts/common/cs.js?${version}"></script>

</body>
</html>