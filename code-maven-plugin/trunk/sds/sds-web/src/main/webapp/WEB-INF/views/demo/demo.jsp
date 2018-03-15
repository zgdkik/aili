<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>智送叫车-首页</title>
<%@include file="../common/common-meta.jsp"%>
<%@include file="../common/common-css.jsp"%>
<%@include file="../common/common-icon.jsp"%>
</head>
<body class="fixed-left" locale="${lang}">
	<%@include file="../common/common-layer.jsp"%>
	<!-- Begin page -->
	<div id="wrapper">

		<%@include file="../common/common-top.jsp"%>


		<jsp:include page="../common/common-left.jsp">
			<jsp:param name="pagecode" value="index" />
		</jsp:include>

		<!-- Start right content -->
		<div class="content-page">
			<ol class="breadcrumb">
				<li><a href="${base}">智送系统</a></li>
				<li class="active">列表</li>
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
														for="input-text">名称</label>
													<div class="col-md-2">
														<input type="text" id="input-text"
															class="form-control typeahead q_str_name" required />
													</div>
													<label class="col-md-1 control-label" for="input-text">222</label>
													<div class="col-md-2">
														<input type="text" id="input-text"
															class="form-control name" required />
													</div>
													<label class="col-md-1 control-label" for="input-text">时间</label>
													<div class="input-group date form_datetime col-md-2"
														data-link-field="dtp_input">
														<input class="form-control" size="16" type="text" value=""
															readonly> <span class="input-group-addon"><span
															class="glyphicon glyphicon-remove"></span></span> <span
															class="input-group-addon"><span
															class="glyphicon glyphicon-th"></span></span>
													</div>

													<input type="hidden" id="dtp_input" value="" /><br />
												</div>
												<div class="form-group">
												  <label class="col-md-1 col-md-offset-1 control-label" for="input-text">222</label>
														<div class="col-md-2">
															<select class="form-control selectpicker-ajax">
																<option disabled="disabled">请选择</option>
															</select>
														</div>
													<label class="col-md-1 control-label" for="input-text">222</label>
													<div class="col-md-2">
														<select class="form-control selectpicker">
															<option>hbh1</option>
															<option>hbh2</option>
															<option>hbh3</option>
															<option>hbh4</option>
															<option>hbh5</option>
															<option>hbh6</option>
														</select>
													</div>
													<label class="col-md-1 control-label" for="input-text">日期</label>
													<div class="input-group date form_date col-md-2"
														data-link-field="dtp_input">
														<input class="form-control" size="16" type="text" value=""
															readonly> <span class="input-group-addon"><span
															class="glyphicon glyphicon-remove"></span></span> <span
															class="input-group-addon"><span
															class="glyphicon glyphicon-th"></span></span>
													</div>
												</div>
												<div class="form-group">
													<div class="col-md-12">
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
										href="#query-list">实例列表</a>
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
													<button type="submit" class="btn btn-default">批量删除</button>
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
			<%@include file="../common/common-footer.jsp"%>
		</div>
		<!-- End right content -->

	</div>

	<!-- End of page -->
	<!-- the overlay modal element -->

	<%@include file="../common/common-modal-footer.jsp"%>
	<!-- End of eoverlay modal -->
	<script>
		var resizefunc = [];
	</script>
	<%@include file="../common/common-script.jsp"%>

	<!-- Page Specific JS Libraries -->
	<script src="${base}/scripts/demo/demo.js?${version}"></script>
</body>
</html>