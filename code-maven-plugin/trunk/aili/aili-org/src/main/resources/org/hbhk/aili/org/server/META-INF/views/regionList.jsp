<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<c:import url="../commons/common-meta.jsp" />
<c:import url="../commons/common-css-ui.jsp" />

<style type="text/css">
</style>
</head>
<body style="overflow-y: none">
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<div data-options="region:'west',title:'部门架构',split:true"
			style="width: 200px;">
			<div class="easyui-panel" style="width: 100%; height: 100%;">
				<ul id="depttree" class="ztree"></ul>
			</div>
		</div>
		<div data-options="region:'center'">
			<div id="search" class="easyui-panel" style="width: 100%;">
				<a class="easyui-linkbutton add-btn"   iconCls="icon-add">新增</a><a
					class="easyui-linkbutton query-btn"  
					iconCls="icon-search">查询</a> <a
					class="expand-collapse easyui-linkbutton" data-href="#query-form"
					data-tbl="#dg" style="float: right;" iconCls="accordion-collapse"></a>
			</div>
			<div class="easyui-panel" style="width: 100%; height: auto;"
				id="query-form">
				<form class="query-form">
					<div class="sfwl-panel-context" style="float: left;">
						<div class="sfwl-form-field">
							<label class="sfwl-label">区域编码</label>
							<div class="sfwl-control">
								<input id="sourceHq" name="q_str_deptCode"
									class="easyui-textbox">
							</div>
						</div>
						<div class="sfwl-form-field">
							<label class="sfwl-label">区域名称</label>
							<div class="sfwl-control">
								<input id="sourceHq" name="q_str_deptName"
									class="easyui-textbox">
							</div>
						</div>
					</div>
				</form>
			</div>
			<table id="dg" class="easyui-datagrid"
				data-options="singleSelect:true,autoRowHeight:false,showFooter:true"
				style="width: 100%;">
			</table>
		</div>
	</div>


	<div id="dlg" style="display: none;">
		<form id="edit-form">
			<input type="hidden" name="id" id="edit-id">
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">上级部门</label>
					<div class="BetweenInput">
						<input type="text" name="parentDeptCode" id="parentDeptCode"
							required="required" style="width: 150px" class="easyui-textbox">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">区域编码</label>
					<div class="BetweenInput">
						<input type="text" name="deptCode" required="required"
							style="width: 150px" class="easyui-textbox deptCode">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">区域名称</label>
					<div class="BetweenInput">
						<input type="text" name="deptName" required="required"
							style="width: 150px" class="easyui-textbox">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">负责人</label>
					<div class="BetweenInput">
						<input type="text" name="leader" id="leader" required="required"
							style="width: 150px" class="easyui-textbox">
					</div>
				</div>
			</div>

			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">电话</label>
					<div class="BetweenInput">
						<input type="text" name="deptTelephone" style="width: 150px"
							class="easyui-textbox">
					</div>
				</div>
			</div>

			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">地址</label>
					<div class="BetweenInput">
						<input type="text" name="address" style="width: 150px"
							class="easyui-textbox">
					</div>
				</div>
			</div>
		</form>
	</div>

	<c:import url="../commons/common-script-ui.jsp" />
	<script type="text/javascript"
		src="${base}/scripts/org/regionList.js?${version}"></script>
</body>

</html>
