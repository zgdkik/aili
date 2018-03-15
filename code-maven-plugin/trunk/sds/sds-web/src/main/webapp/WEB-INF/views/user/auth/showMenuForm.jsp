<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../common/common-meta.jsp"%>
<%@include file="../../common/common-css.jsp"%>
<%@include file="../../common/common-icon.jsp"%>
<style type="text/css">
</style>
<title>权限管理-菜单管理</title>
</head>
<body class="fixed-left" locale="zh-cn">
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
				<li class="active">菜单管理</li>
			</ol>
			<div class="content">
				<!--主要内容开始部分 START-->
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default" style="">
							<div class="panel-heading">
								<h3 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion">菜单管理</a>
								</h3>
							</div>
							<div class="panel-body">
								<div class="row" style="padding-left:5%">
									<div class="col-md-3"
										style="border: 2px solid #ccc; height: 600px; box-shadow: 0px 0px 20px #ccc;">
										<ul id="treeDemo" class="ztree"></ul>
									</div>
									<div class="col-md-7"
										style="border: 2px solid #ccc; box-shadow: 0px 0px 20px #ccc; height: 600px; margin-left: 5%;">
										<div class="row panel-body">
											<div class="col-md-12">
												<form class="form-horizontal query-form" role="form"
													data-toggle="form-validator" id="role-form">
													<input type="hidden" id="id">
													<div class="form-group">
														<label class="col-sm-2" for="input-text">功能编码:</label>
														<div class="col-sm-2">
															<span id="functionCodeSpan"></span>
														</div>
														<label class="col-sm-2" for="input-text">功能名称:</label>
														<div class="col-sm-2">
															<span id="functionNameSpan"></span>
														</div>
														<label class="col-sm-2" for="input-text">功能入口URI:</label>
														<div class="col-sm-2">
															<span id="uriSpan"></span>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2" for="input-text">父功能名称:</label>
														<div class="col-sm-2">
															<span id="parentCodeSpan"></span>
														</div>
														<label class="col-sm-2" for="input-text">是否有效:</label>
														<div class="col-sm-2">
															<span id="activeSpan"></span>
														</div>
														<label class="col-sm-2" for="input-text">显示顺序:</label>
														<div class="col-sm-2">
															<span id="displayOrderSpan"></span>
														</div>
													</div>
													<div class="form-group">
														<!-- <label class="col-sm-2" for="input-text">是否权限检查:</label>
													<div class="col-sm-2">
														<span id="checkableSpan"></span>
													</div> -->
														<label class="col-sm-2" for="input-text">功能类型:</label>
														<div class="col-sm-2">
															<span id="functionTypeSpan"></span>
														</div>
														<!-- <label class="col-sm-2" for="input-text" >是否子节点:</label>
													<div class="col-sm-2">
														<span id="leafSpan"></span>
												    </div> -->
														<label class="col-sm-2" for="input-text">功能描述:</label>
														<div class="col-sm-2">
															<span id="functionDescSpan"></span>
														</div>
													</div>

													<div class="form-group">
														<div class="col-md-12">
															<button type="button" class="btn btn-primary" name="add"
																id="add" onclick="showAdd()">新增</button>
															<button type="button" class="btn btn-primary"
																name="update" id="update" onclick="showUpdate()"
																disabled="disabled">修改</button>
															<button type="button" class="btn btn-primary"
																name="delete" id="delete" disabled="disabled">删除</button>
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
				</div>

				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>

								<h4 class="modal-title" id="myModalLabel">
									<span id="title"></span>
								</h4>
							</div>
							<div class="modal-body">
								<div id="query-form" class="panel-collapse collapse in">
									<div class="panel-body">
										<form id="menuForm" method="post" class="form-horizontal"
											action="">
											<div class="row">
												<input id="authId" type="hidden" value="0" />
												<div class="form-group">
													<label class="col-md-3 control-label">功能编码:</label>
													<div class="col-md-5">
														<input type="text" maxlength="30" class="form-control"
															id="functionCode" name="functionCode" />
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-3 control-label">功能名称:</label>
													<div class="col-md-5">
														<input type="text" maxlength="30" class="form-control"
															id="functionName" name="functionName" />
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-3 control-label">功能入口URI:</label>
													<div class="col-md-5">
														<input type="text" maxlength="80" class="form-control"
															id="uri" name="uri" />
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-3 control-label">父菜单:</label>
													<div class="col-md-5">
														<div class="select">
															<select name="parentCode" style="width: 208px"
																class="form-control selectpicker" id="parentCode">

															</select>
														</div>
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-3 control-label">是否有效:</label>
													<div class="col-md-5">
														<div class="select">
															<select name="active" style="width: 208px"
																class="form-control selectpicker" id="active">
																<option value="Y">有效</option>
																<option value="N">无效</option>
															</select>
														</div>
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-3 control-label">排序:</label>
													<div class="col-md-5">
														<input maxlength="20" type="number" class="form-control"
															name="displayOrder" id="displayOrder" />
													</div>
												</div>

												<!-- <div class="form-group">
													<label class="col-md-3 control-label">是否权限检查:</label>
													<div class="col-md-5">
														<div class="radio">
															<label> <input type="radio" name="checkable" id="checkable1" checked="checked"
																value="Y" /> 是
															</label>
															<label><input type="radio" name="checkable" id="checkable2"
																value="N" /> 否
															</label>
														</div>
													</div>
												</div> -->

												<div class="form-group">
													<label class="col-md-3 control-label">功能类型:</label>
													<div class="col-md-5">
														<div class="select">
															<select name="functionType"
																class="form-control selectpicker" style="width: 208px"
																id="functionType">
																<option value="N">菜单</option>
																<option value="Y">模块</option>
																<option value="F">功能</option>
															</select>
														</div>
													</div>
												</div>

												<!-- <div class="form-group">
													<label class="col-md-3 control-label">是否叶子节点:</label>
													<div class="col-md-5">
														<div class="radio">
															<label><input type="radio" name="leaf" id="leaf1" checked="checked"
																value="Y" /> 是
															</label>
															<label><input type="radio" name="leaf" id="leaf2"
																value="N" /> 否
															</label>
														</div>
													</div>
												</div> -->

												<div class="form-group">
													<label class="col-md-3 control-label">图标样式:</label>
													<div class="col-md-5">
														<input type="text" maxlength="80" class="form-control"
															name="iconCls" id="iconCls" />
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-3 control-label">节点样式:</label>
													<div class="col-md-5">
														<input type="text" maxlength="80" class="form-control"
															name="cls" id="cls" />
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-3 control-label">功能描述:</label>
													<div class="col-md-5">
														<textarea rows="5" cols="50" name="functionDesc"
															id="functionDesc"></textarea>
													</div>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary" name="save"
									id="save">保存</button>
								<button type="submit" class="btn btn-primary" name="revise"
									id="revise">修改</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal -->
				</div>
				<!--主要内容开始部分 END-->
			</div>
		</div>
	</div>
	<%@include file="../../common/common-footer.jsp"%>

	<%@include file="../../common/common-modal-footer.jsp"%>
	<!-- End of eoverlay modal -->
	<script>
		var resizefunc = [];
	</script>
	<%@include file="../../common/common-script.jsp"%>

	<!-- Page Specific JS Libraries -->

	<script src="${base}/scripts/auth/showMenuForm.js"></script>

</body>
</html>