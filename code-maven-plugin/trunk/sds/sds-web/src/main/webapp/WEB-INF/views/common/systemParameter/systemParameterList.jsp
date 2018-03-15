<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>系统参数-列表</title>
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
				<li class="active">系统参数</li>
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
														for="input-text">键:</label>
													<div class="col-md-3">
														<input type="text" id="sysKey" name="sysKey"
															class="form-control name" required />
													</div>
													<!-- <label class="col-md-1 control-label" for="input-text">角色类型</label>
													<div class="col-md-3">
														<select id="roleType" name="roleType"
															class="form-control selectpicker">
															<option value="common">通用角色</option>
														</select>
													</div> -->
													<label class="col-md-1 col-md-offset-1 control-label"
														for="input-text">值:</label>
													<div class="col-md-3">
														<input type="text" id="sysValue" name="sysValue"
															class="form-control name" required />
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
										href="#query-list">系统参数配置</a>
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
												<div class="form-inline" >
													<!-- <button type="submit" id="deletebatch"
														class="btn btn-default">批量删除</button> -->
													<%-- <button type="button" class="btn btn-default">
														<a href="${base}/role/showRoleForm"><font
															color="#FFFFFF">新增参数</font></a>
													</button> --%>
													<button type="button" class="btn btn-primary" name="add" id="add" onclick="showAdd()">新增参数</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				
				     <div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
						        aria-labelledby="myModalLabel" aria-hidden="true">
						   <div class="modal-dialog">
						      <div class="modal-content">
						         <div class="modal-header">
						            <button type="button" class="close" 
						               data-dismiss="modal" aria-hidden="true">
						                  &times;
						            </button>
						           
						            <h4 class="modal-title" id="myModalLabel">
						              <!--  <span id="title"></span> -->
						              <a data-toggle="collapse" id="updatetitle" data-parent="#accordion" href="#form">
								${title}
								</a>
						            </h4>
						         </div>
						         <div class="modal-body">
										<form id="addForm" method="post" class="form-horizontal" action="">
				                            <div class="row">
												<input id="sysId" name="id"type="hidden" value="${systemParameter.id}" />
												<div class="form-group">
													<label class="col-md-3 control-label">键:</label>
													<div class="col-md-5">
														<input type="text" maxlength="30" class="form-control" id="sysKey1" name="sysKey"/>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">值:</label>
													<div class="col-md-5">
														<input type="text" maxlength="30" class="form-control" id="sysValue1" name="sysValue" />
													</div>
												</div>
												
												
												
												
				
												 <!-- <div class="form-group">
													<label class="col-md-3 control-label">是否有效:</label>
													<div class="col-md-5">
													   <div class="select" >
															<select  name="status" style="width:230px;height: 25px;" id="status" >
															  <option value="Y">有效</option>
															  <option value="N">无效</option>
															</select>
														</div>
													</div>
												</div>  -->
											</div>
										</form>
						          </div>
						         <div class="modal-footer">
										<button type="submit" class="btn btn-primary" name="save" id="save">提交</button>
									   <!--  <button type="submit" class="btn btn-primary" name="revise" id="revise">修改</button> -->
										<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								 </div>
						      </div><!-- /.modal-content -->
						</div><!-- /.modal -->
			</div>
				<!--主要内容开始部分 END-->
			</div>
			<!-- ============================================================== -->
			<!-- End content here -->
			<!-- ============================================================== -->
			<%@include file="../../common/common-footer.jsp"%>
		</div>
		<!-- End right content -->

	</div>

	<!-- End of page -->
	<!-- the overlay modal element -->

	<%@include file="../../common/common-modal-footer.jsp"%>
	<!-- End of eoverlay modal -->
	<script>
		var resizefunc = [];
	</script>
	<%@include file="../../common/common-script.jsp"%>

	<!-- Page Specific JS Libraries -->
	 <script src="${base}/scripts/common/systemParameter/systemParameter.js?${version}"></script> 
	<%-- <script src="${base}/scripts/auth/showMenuForm.js"></script> --%>
</body>
</html>