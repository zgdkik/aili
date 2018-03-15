<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>

<%@include file="../../common/common-meta.jsp"%>
<%@include file="../../common/common-css.jsp"%>
<%@include file="../../common/common-icon.jsp"%>
<style type="text/css">
 .select2-container{width:100% !important;}
</style>
<title>权限管理-用户管理-添加用户</title>
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
				<li><a href="/sds-web">权限管理</a></li>
				<li><a href="/sds-web/user/userlist">用户管理</a></li>
				<li class="active">添加用户</li>
			</ol>
			<div class="content">
				<!--主要内容开始部分 START-->
				<div class="row">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#form">
									添加用户 </a>
							</h3>
						</div>
						<div id="form" class="panel-collapse collapse in">
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
										<form id="defaultForm" method="post" class="form-horizontal"
											action="${base}/user/adduser">
											<input type="hidden" id="type" class="form-control"
												name="type" value="1" />
											<div class="form-group">
												<label class="col-md-2 control-label">用户名</label>
												<div class="col-md-8">
													<!-- <input type="text" id="userName" class="form-control selectpicker-ajax"
														name="userName" placeholder="" /> -->
													<select  id="userName"	name="userName"  class="form-control selectpicker-ajax">
															<option >请选择</option>
													</select>
												</div>
											</div>

											<div class="form-group" id="roleids">
												<label class="col-md-2 control-label">分配角色</label>
												<div class="col-md-3">
													<label class="col-md-7 control-label">未有角色</label> <select
														id="keepRenderingSort" class="form-control" size="8"
														multiple="multiple">
														<c:forEach items="${list}" var="role" varStatus="status">
															<option value="${role.roleCode }">${role.roleName }</option>
														</c:forEach>
													</select>
												</div>
												<div class="col-md-2">
													<label class="col-md-5 control-label">&nbsp;&nbsp;&nbsp;&nbsp;</label>
													<button type="button" id="keepRenderingSort_rightAll"
														class="btn btn-block">
														<i class="glyphicon glyphicon-forward"></i>
													</button>
													<button type="button" id="keepRenderingSort_rightSelected"
														class="btn btn-block">
														<i class="glyphicon glyphicon-chevron-right"></i>
													</button>
													<button type="button" id="keepRenderingSort_leftSelected"
														class="btn btn-block">
														<i class="glyphicon glyphicon-chevron-left"></i>
													</button>
													<button type="button" id="keepRenderingSort_leftAll"
														class="btn btn-block">
														<i class="glyphicon glyphicon-backward"></i>
													</button>
												</div>
												<div class="col-md-3">
													<label class="col-md-7 control-label">已有角色</label> <select
														name="roleids" id="keepRenderingSort_to"
														class="form-control" size="8" multiple="multiple"></select>
												</div>
											</div>
											<div class="form-group">
												<div class="col-md-9 col-md-offset-5">
													<button type="button" class="btn btn-primary" id="adduser"
														value="Sign up">保存</button>
													<!-- <button type="button" class="btn btn-info" id="validateBtn">校验</button> 
													<button type="button" class="btn btn-info" id="resetBtn">重置</button>-->
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
		</div>
	</div>
	<%@include file="../../common/common-footer.jsp"%>
	<%@include file="../../common/common-script.jsp"%>
	<!-- Page Specific JS Libraries -->
	<script src="${base}/scripts/user/user-add.js?${version}"></script>
</body>
</html>