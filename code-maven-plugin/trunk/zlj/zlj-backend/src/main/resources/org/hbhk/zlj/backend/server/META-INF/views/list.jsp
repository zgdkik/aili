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
	<div id="search" class="easyui-panel"
		style="padding: 2px; width: 100%;">
		<a class="easyui-linkbutton query-btn" plain="true"
			iconCls="icon-search">查询</a> <a class="easyui-linkbutton add-btn"
			plain="true" iconCls="icon-add">新增</a> <a
			class="expand-collapse easyui-linkbutton" data-href="#query-form"
			data-tbl="#dg" style="float: right;" iconCls="accordion-collapse"></a>
	</div>
	<div class="easyui-panel"
		style="width: 100%; height: auto; padding: 5px 0px 5px 5px"
		id="query-form">
		<form class="query-form">
			<div class="sfwl-panel-context"
				style="float: left;">
				<div class="sfwl-form-field">
					<label class="sfwl-label">名称</label>
					<div class="sfwl-control">
						<input id="sourceHq" name="q_str_name" class="easyui-textbox">
					</div>
				</div>
				<div class="sfwl-form-field">
					<label class="sfwl-label">编码</label>
					<div class="sfwl-control">
						<input id="sourceHq" name="q_str_code" class="easyui-textbox">
					</div>
				</div>
			</div>
		</form>
	</div>

	<div id="dlg" style="display: none;">
		<form id="edit-form">
			<input type="hidden" name="id" id="edit-id">
			<div class="sfwl-row1 sfwl-col3">
				<div class="sfwl-form-field">
					<label class="sfwl-label">名称</label>
					<div class="BetweenInput">
						<input type="text" name="name" required="required"
							style="width: 150px" class="easyui-textbox">
					</div>
				</div>
				<div class="sfwl-form-field">
					<label class="sfwl-label">编码</label>
					<div class="BetweenInput">
						<input type="text" name="code" required="required"
							style="width: 150px" class="easyui-textbox code">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col3">
				<div class="sfwl-form-field">
					<label class="sfwl-label">上级菜单</label>
					<div class="BetweenInput">
						<select class="easyui-combobox" name="parentMenuCode"
							style="width: 150px;" required="required">
							<c:forEach items="${menus}" var="m">
								<option value="${m.privilegeCode}">${m.privilegeName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<div class="sfwl-row1 sfwl-col" style="height: 230px;">
				<div class="sfwl-form-field">
					<label class="sfwl-label">sql</label>
					<div class="BetweenInput" style="height: 210px;">
						<textarea name="sql" required="required"
							style="width: 390px; height: 200px"></textarea>
					</div>
				</div>
			</div>
			
			<div class="sfwl-row1 sfwl-col2" >
				<div class="sfwl-form-field">
					<label class="sfwl-label">条件类型</label>
					<div class="BetweenInput" >
						<select class="easyui-combobox" name="parentMenuCode"
							style="width: 150px;" required="required">
							<c:forEach items="${menus}" var="m">
								<option value="${m.privilegeCode}">${m.privilegeName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="sfwl-form-field">
					<label class="sfwl-label">列名称</label>
					<div class="BetweenInput" >
						<input type="text" name="code" required="required"
							style="width: 150px" class="easyui-textbox code">
					</div>
				</div>
			</div>

		</form>
	</div>

	<div id="tblDiv">
		<table id="dg" class="easyui-datagrid"
			data-options="singleSelect:true,autoRowHeight:false,showFooter:true"
			style="width: 100%;">
		</table>
	</div>
	<c:import url="../commons/common-script-ui.jsp" />
	<script type="text/javascript"
		src="${base}/scripts/crud/list.js?${version}"></script>
</body>
</html>
