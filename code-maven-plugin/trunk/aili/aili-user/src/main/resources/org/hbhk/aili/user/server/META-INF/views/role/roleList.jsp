<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="/aili" prefix="aili"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css-ui.jsp" />

<style type="text/css">
</style>
</head>
<body style="overflow-y: none">
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<div id="cc" class="easyui-layout" style="width: 100%; height: 100%;">
			<div data-options="region:'east',title:'权限树',split:true"
				style="width: 30%;">
				<div class="easyui-panel" style="width: 100%; height: 100%;">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
			</div>
			<div data-options="region:'center'">
				<div id="search" class="easyui-panel"
					style="padding: 2px; width: 100%;">
					<a class="easyui-linkbutton add-btn"  
						iconCls="icon-add">新增</a> <a class="easyui-linkbutton save-pri"
						  iconCls="icon-save">保存</a> <a
						class="easyui-linkbutton query-btn"  
						iconCls="icon-search">查询</a>
				</div>
				<div class="easyui-panel" style="width: 100%; height: auto;"
					id="query-form">

					<form class="query-form">
						<div class="sfwl-panel-context" style="float: left;">
							<div class="sfwl-form-field">
								<label class="sfwl-label">名称</label>
								<div class="sfwl-control">
									<input id="sourceHq" name="q_str_roleName"
										class="easyui-textbox">
								</div>
							</div>
							<div class="sfwl-form-field">
								<label class="sfwl-label">编码</label>
								<div class="sfwl-control">
									<input type="text" name="q_str_roleCode" style="width: 150px"
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

	</div>
	<div id="dlg" style="display: none;">
		<form id="edit-form">
			<input type="hidden" name="id" id="edit-id"> <input
				type="hidden" name="compCode" value="hbhk">
			<div class="sfwl-row1 sfwl-col">

				<div class="sfwl-form-field">
					<label class="sfwl-label">编码</label>
					<div class="sfwl-control">
						<input type="text" name="roleCode" required="required"
							class="easyui-textbox roleCode">
					</div>
				</div>

			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">名称</label>
					<div class="sfwl-control">
						<input name="roleName" required="required" class="easyui-textbox">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<div class="sfwl-form-field">
						<label class="sfwl-label">描述</label>
						<div class="sfwl-control">
							<input type="text" name="roleDesc" class="easyui-textbox">
						</div>
					</div>
				</div>
			</div>

		</form>
	</div>
	<c:import url="../../commons/common-script-ui.jsp" />
	<script type="text/javascript">
		var tree = '${tree}';
		tree = eval('(' + tree + ')');
	</script>
	<script type="text/javascript"
		src="${base}/scripts/user/role/rolelist.js?${version}"></script>
</body>

</html>
