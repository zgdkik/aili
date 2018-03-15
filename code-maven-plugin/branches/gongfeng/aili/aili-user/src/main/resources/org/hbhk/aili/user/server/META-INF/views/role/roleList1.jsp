<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
													<input type="text" placeholder="" name="roleName"
														class="form-control">
												</div>
												<label class="col-xs-1 control-label">编码</label>
												<div class="col-xs-3">
													<input type="text" placeholder="" name="roleCode"
														class="form-control">
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
								<div class="col-xs-4 ym-border">
									<table id="table" data-pagination="true" data-height="500"
										data-toolbar="#custom-toolbar">
									</table>
									<div id="custom-toolbar">
										<div class="form-inline" role="form">
											<button type="button" class="btn btn-default addnew">新增</button>
											<button type="button" class="btn btn-default save-pri">保存</button>
										</div>
									</div>
								</div>
								<div class="col-xs-8 ym-border" style="height: 500px">
									<ul id="myTab" class="nav nav-tabs">
										<li class="active"><a href="#auth" data-toggle="tab">
												权限 </a></li>
									</ul>
									<div id="myTabContent" class="tab-content">
										<div class="tab-pane fade in active" id="auth">
											<div class="col-xs-12"
												style="overflow: scroll; overflow-y: auto; overflow-x: hidden; height: 430px">
												<ul id="treeDemo" class="ztree"></ul>
											</div>
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
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 500px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span id="title">新增角色</span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<form role="form" id="edit-form" class="form-horizontal">
							<input type="hidden" name="id">
							<div class="form-group ">
								<label class="col-xs-3 control-label">角色名称</label>
								<div class="col-xs-8">
									<input type="text" name="roleName" class="form-control">
								</div>
							</div>
							<div class="form-group ">
								<label class="col-xs-3 control-label">角色编码</label>
								<div class="col-xs-8">
									<input type="text" name="roleCode" class="form-control">
								</div>
							</div>
							<div class="form-group ">
								<label class="col-xs-3 control-label">角色公司</label>
								<div class="col-xs-8">
									<select name="compCode" style="width: 100%; height: 100%;"
										class="form-control selectpicker">
										<c:forEach items="${compList }" var="c">
											<option value="${c.id}">${c.name}</option>
										</c:forEach>
									</select>
								</div>

							</div>
							<div class="form-group ">
								<label class="col-xs-3 control-label">角色描述</label>
								<div class="col-xs-8">
									<textarea name="roleDesc" rows="" cols="" style="width: 100%"></textarea>
								</div>
							</div>
						</form>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary save">保存</button>
					<button type="button" class="btn btn-primary cancel">取消</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<%-- <jsp:include page="../../../../commons/common-script.jsp" /> --%>
	<c:import url="../../commons/common-script.jsp" />
	<script type="text/javascript">
		var tree = '${tree}';
		tree = eval('(' + tree + ')');
	</script>
	<script type="text/javascript"
		src="${base}/scripts/user/role/rolelist.js?${version}"></script>
</body>
</html>
