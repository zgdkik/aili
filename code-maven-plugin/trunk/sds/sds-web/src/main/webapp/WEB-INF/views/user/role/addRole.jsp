<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../common/common-meta.jsp"%>
<%@include file="../../common/common-css.jsp"%>
<%@include file="../../common/common-icon.jsp"%>
<title>权限管理-角色管理-${title}</title>
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
				<li><a href="${base}/role/roleList">角色列表</a></li>
				<li class="active">角色编辑</li>
			</ol>
			<div class="content">
				<!--主要内容开始部分 START-->
				<div class="row">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#form">
								${title}
								</a>
							</h3>
						</div>
						<div id="form" class="panel-collapse collapse in">
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
										<form id="addRole" method="post" class="form-horizontal"
											action="${base}/role/addRole">
											<input id="roleId" type="hidden" value="${role.id}" />
											<div class="form-group">
												<label class="col-md-3 control-label">角色编码</label>
												<div class="col-md-6">
													<input type="text" id="roleCode" name="roleCode"
														value="${role.roleCode}" <c:if test="${role.id!=null}"> readonly="readonly" </c:if> class="form-control" />
												</div>
												
											</div>
											
											<div class="form-group">
												<label class="col-md-3 control-label">角色名称</label>
												<div class="col-md-6">
													<input type="text" id="roleName" name="roleName"
														value="${role.roleName }" class="form-control" />
												</div>
											</div>

											<div class="form-group">
												<label class="col-md-3 control-label">角色类型</label>
												<div class="col-md-6">
												 <select
													id="roleType" name="roleType"
													class="form-control selectpicker roleType" data-selected="${role.roleType}">
													<sds:dictSelect dictType="role_type"/>
												<%-- 	<c:if test="${role.roleType=='common' || role== null}">
														<option value="common" selected="selected">通用角色</option>
														<option value="dept">部门角色</option>
													</c:if>
													<c:if test="${role.roleType=='dept'}">
														<option value="common">通用角色</option>
														<option value="dept" selected="selected">部门角色</option>
													</c:if> --%>
												</select>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-3 control-label">是否有效</label>
												<div class="col-md-6">
													<div class="radio">
														<input type="radio" id="status1" name="status"
															<c:if test="${role.status==1 || role==null}">checked="checked"</c:if> value="1" /> 是
															 <input type="radio"
															id="status2" <c:if test="${role.status==0}">checked="checked"</c:if> name="status" value="0" /> 否
													</div>
												</div>
											</div>

											<!-- <div class="form-group dept-group">
												<label class="col-md-3 control-label">归属部门</label> 
												<div class="col-md-6">
												<select
													id="deptCode" name="deptCode"
													class="selectpicker deptCode form-control">
													<option selected="true" disabled="disabled" value="">--请选择--</option>
												</select>
												</div>
											</div> -->
											<div class="form-group dept-group">
												<label class="col-md-3 control-label">角色描述</label>
												<div class="col-md-6">
													<textarea id="roleDesc" class="form-control" rows="3" name="roleDesc" class="col-md-12">${role.roleDesc}</textarea>
												</div>
											</div>
											<div class="form-group dept-group">
												<label class="col-md-3 control-label">分配权限</label>
												<div class="col-md-6" style="overflow:auto;max-height: 300px;">
													<ul id="fun-tree" class="ztree"></ul>
												</div>
											</div>
											<div class="form-group">
											</div>
											<div class="form-group">
												<div class="col-md-9 col-md-offset-3">
													<button type="button" class="btn btn-primary" id="save"
														name="save" >提交</button>
													<!-- <button type="reset" class="btn btn-info" id="resetBtn"
														name="reset">重置</button> -->
													<button type="button" class="btn btn-info" id="validateBtn"
														onClick="javascript:location.href='${base}/role/roleList';">返回</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--主要内容开始部分 END-->
			</div>
			<!-- ==============================
			================================ -->
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
		var funs='${funs}';
		funs = eval('(' + funs + ')');
	</script>
	<%@include file="../../common/common-script.jsp"%>

	<!-- Page Specific JS Libraries -->
	<script src="${base}/scripts/role/addRole.js?${version}"></script>

	<script type="text/javascript">
		var maxPage = "${param.pageCount}";
		var curPage = "${param.currentPage}";
	</script>
</body>
</html>