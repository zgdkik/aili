<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="/aili" prefix="aili" %>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />
<link href="${base}/styles/home/custom.css?${version}" rel="stylesheet">
</head>
<body class="sticky-header">
	<section>
		<!--body wrapper start-->
		<div class="wrapper">
			<div class="row">
				<div class="col-xs-12">
					<div class="col-xs-3">
						<div class="row ym-border"
							style="max-height: 700px; height: 500px;">
							<div class="panel">
								<div class="panel-heading">
									<div class="panel-title">权限树</div>
								</div>
								<div class="panel-body" style="overflow:scroll;overflow-y: auto; overflow-x:hidden;height: 430px">
									<div class="row" >
										<div class="col-xs-12">
											<ul id="treeDemo" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-9">
						<div class="row ym-border"
							style="margin-left: 1%; max-height: 700px; height: 500px;">
							<div class="panel">
								<div class="panel-heading">
									<div class="panel-title">权限信息</div>
								</div>
								<div class="panel-body">
										<div class="col-xs-12">
											<form role="form" id="privilege-info"
												class="form-horizontal adminex-form">
												<div class="form-group ">
													<div class="col-xs-12">
														<input type="button" class="btn btn-primary addnew" value="新增">
														 <input type="button" class="btn btn-primary update" value="修改"> 
														 <input type="button" class="btn btn-primary remove" value="删除">
													</div>
												</div>
												<div class="form-group ">
													<label class="col-xs-1 control-label">系统类型</label>
													<div class="col-xs-2">
														<select name="appType" disabled="disabled"
															class="form-control selectpicker">
															<option value=""></option>
															<aili:dictSelect dictType="system_type"/>
														</select>
													</div>
													<label class="col-xs-1 control-label">权限类型</label>
													<div class="col-xs-2">
														<select name="moduleType" disabled="disabled"
															class="form-control selectpicker">
															<option value=""></option>
															<aili:dictSelect dictType="module_type"/>
														</select>
													</div>
													<label class="col-xs-1 control-label">权限编码</label>
													<div class="col-xs-2">
														<input type="text" name="privilegeCode"
															readonly="readonly" class="form-control">
													</div>
													<label class="col-xs-1 control-label">权限URL</label>
													<div class="col-xs-2">
														<input type="text" name="url" readonly="readonly"
															class="form-control">
													</div>

												</div>

												<div class="form-group ">

													<label class="col-xs-1 control-label">权限名称</label>
													<div class="col-xs-2">
														<input type="text" name="functionName" readonly="readonly"
															class="form-control">
													</div>
													<label class="col-xs-1 control-label">图标</label>
													<div class="col-xs-2">
														<input type="text" name="iconCls" readonly="readonly"
															class="form-control">
													</div>
													<label class="col-xs-1 control-label">显示顺序</label>
													<div class="col-xs-2">
														<input type="text" name="displayOrder" readonly="readonly"
															class="form-control">
													</div>
													<label class="col-xs-1 control-label">快捷键</label>
													<div class="col-xs-2">
														<input type="text" name="shoutcutKeys" readonly="readonly"
															class="form-control">
													</div>
												</div>
												<div class="form-group ">

													<label class="col-xs-1 control-label">权限描述</label>
													<div class="col-xs-5">
														<textarea class="col-xs-12" disabled="disabled"
															name="functionDesc" rows="" cols=""></textarea>
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
		<!--body wrapper end-->

		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 1200px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<span id="title">新增权限</span>
						</h4>
					</div>
					<div class="modal-body">
						<form role="form" id="privilege-form"
							class="form-horizontal adminex-form"
							action="${base}/privilege/addMenu">
							<input type="hidden" name="parentCode">
							<input type="hidden" name="id">
							<div class="form-group ">
								<label class="col-xs-1 control-label">系统类型</label>
								<div class="col-xs-3">
									<select name="appType" style="width: 100%; height: 100%;"
										class="form-control selectpicker">
										<aili:dictSelect dictType="system_type"/>
									</select>
								</div>
								<label class="col-xs-1 control-label">权限类型</label>
								<div class="col-xs-3">
									<select name="type" style="width: 100%; height: 100%;"
										class="form-control selectpicker">
										<aili:dictSelect dictType="module_type"/>
									</select>
								</div>
								<label class="col-xs-1 control-label">权限编码</label>
								<div class="col-xs-3">
									<input type="text" name="privilegeCode" class="form-control">
								</div>

							</div>
							<div class="form-group ">
								<label class="col-xs-1 control-label">权限名称</label>
								<div class="col-xs-3">
									<input type="text" name="functionName" class="form-control">
								</div>
								<label class="col-xs-1 control-label">权限URL</label>
								<div class="col-xs-3">
									<input type="text" name="url" class="form-control">
								</div>
								<label class="col-xs-1 control-label">图标</label>
								<div class="col-xs-3">
									<input type="text" name="iconCls" class="form-control">
								</div>

							</div>
							<div class="form-group ">
								<label class="col-xs-1 control-label">显示顺序</label>
								<div class="col-xs-3">
									<input type="text" name="displayOrder" class="form-control">
								</div>
								<label class="col-xs-1 control-label">快捷键</label>
								<div class="col-xs-3">
									<input type="text" name="shoutcutKeys" class="form-control">
								</div>
							</div>
							<div class="form-group ">

								<label class="col-xs-1 control-label">权限描述</label>
								<div class="col-xs-5">
									<textarea class="col-xs-12" name="functionDesc" rows="" cols=""></textarea>
								</div>

							</div>
						</form>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary save">保存</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</section>
	<c:import url="../../commons/common-script.jsp" />
	<script type="text/javascript"
		src="${base}/scripts/user/privilege/privilegelist.js?${version}"></script>
</body>
</html>
