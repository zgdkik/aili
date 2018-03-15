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
		<div data-options="region:'west',title:'权限树',split:true"
			style="width: 200px;">
			<div class="easyui-panel" style="width: 100%; height: 100%;">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		</div>
		<div data-options="region:'center'">
			<div id="search" class="easyui-panel"
				style="padding: 2px; width: 100%;">
				<a class="easyui-linkbutton addnew"   iconCls="icon-add">新增</a>
				<a class="easyui-linkbutton update"   iconCls="icon-edit">修改</a>
				<a class="easyui-linkbutton remove"  
					iconCls="icon-remove">删除</a>
			</div>
			<div class="easyui-panel" style="width: 100%; height: auto;">
				<form class="query-form">
					<div class="sfwl-panel-context" style="float: left;">
						<div class="sfwl-form-field">
							<label class="sfwl-label">权限类型</label>
							<div class="sfwl-control">
								<select name="type" style="width: 120px;"
									class="easyui-combobox" disabled="disabled">
									<aili:dictSelect dictType="module_type" />
								</select>
							</div>
						</div>
						<div class="sfwl-form-field">
							<label class="sfwl-label">权限编码</label>
							<div class="BetweenInput">
								<input type="text" name="privilegeCode" class="easyui-textbox"  disabled="disabled">
							</div>
						</div>
						<div class="sfwl-form-field">
							<label class="sfwl-label">权限URL</label>
							<div class="BetweenInput">
								<input type="text" name="url" class="easyui-textbox"  disabled="disabled">
							</div>
						</div>
						<div class="sfwl-form-field">
							<label class="sfwl-label">权限名称</label>
							<div class="BetweenInput">
								<input type="text" name="privilegeName" class="easyui-textbox"  disabled="disabled">
							</div>
						</div>
						<div class="sfwl-form-field">
							<label class="sfwl-label">显示顺序</label>
							<div class="BetweenInput">
								<input type="text" name="displayOrder" class="easyui-textbox"  disabled="disabled">
							</div>
						</div>
						<div class="sfwl-form-field">
							<label class="sfwl-label">权限描述</label>
							<div class="BetweenInput">
								<input type="text" name="functionDesc" class="easyui-textbox"  disabled="disabled">
							</div>
						</div>
					</div>
				</form>
			</div>

			<div id="dlg" style="display: none;">
				<form id="edit-form">
					<input type="hidden" name="id" id="edit-id">
					<div class="sfwl-row1 sfwl-col2">
						<div class="sfwl-form-field">
							<label class="sfwl-label">上级菜单</label>
							<div class="sfwl-control">
								<select name="parentCode" style="width: 120px;"
									class="easyui-combobox" required="required">
									<c:forEach items="${menus}" var="m">
										<option value="${m.privilegeCode}">${m.privilegeName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="sfwl-row1 sfwl-col2">
						<div class="sfwl-form-field">
							<label class="sfwl-label">权限类型</label>
							<div class="sfwl-control">
								<select name="type" style="width: 120px;"
									class="easyui-combobox" required="required">
									<aili:dictSelect dictType="module_type" />
								</select>
							</div>
						</div>
						<div class="sfwl-form-field">
							<label class="sfwl-label">权限编码</label>
							<div class="BetweenInput">
								<input type="text" name="privilegeCode" required="required" class="easyui-textbox privilegeCode">
							</div>
						</div>
					</div>
					<div class="sfwl-row1 sfwl-col2">
						<div class="sfwl-form-field">
							<label class="sfwl-label">权限URL</label>
							<div class="BetweenInput">
								<input type="text" name="url" required="required" class="easyui-textbox">
							</div>
						</div>
						<div class="sfwl-form-field">
							<label class="sfwl-label">权限名称</label>
							<div class="BetweenInput">
								<input type="text" name="privilegeName" required="required" class="easyui-textbox">
							</div>
						</div>
					</div>
					<div class="sfwl-row1 sfwl-col2">
						<div class="sfwl-form-field">
							<label class="sfwl-label">显示顺序</label>
							<div class="BetweenInput">
								<input type="text" name="displayOrder" required="required" class="easyui-numberbox">
							</div>
						</div>
						<div class="sfwl-form-field">
							<label class="sfwl-label">权限描述</label>
							<div class="BetweenInput">
								<input type="text" name="functionDesc"  class="easyui-textbox">
							</div>
						</div>
					</div>

				</form>
			</div>

		</div>
	</div>

	<c:import url="../../commons/common-script-ui.jsp" />
	<script type="text/javascript"
		src="${base}/scripts/user/privilege/privilegelist.js?${version}"></script>
</body>

</html>
