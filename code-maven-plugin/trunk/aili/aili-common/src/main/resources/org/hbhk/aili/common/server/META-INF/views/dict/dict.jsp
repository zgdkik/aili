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
		<div data-options="region:'west',split:true" style="width: 300px;">
			<div class="easyui-panel" style="padding: 2px; width: 100%;">
				<a class="easyui-linkbutton addDict" id="addDict"  
					iconCls="icon-add">新增</a> <a class="easyui-linkbutton updateDict"
					id="updateDict"   iconCls="icon-edit">修改</a> <a
					class="easyui-linkbutton deleteDict" id="deleteDict"  
					iconCls="icon-remove">删除</a>
			</div>
			<div class="easyui-panel" style="width: 100%; height: auto;">
				<ul id="treeDict" class="ztree"></ul>
			</div>
		</div>
		<div data-options="region:'center'">
			<div id="search" class="easyui-panel" style="width: 100%;">
				<a class="easyui-linkbutton addDictValue"   iconCls="icon-add">新增</a>
			</div>
			<table id="dg" class="easyui-datagrid"
				data-options="singleSelect:true,autoRowHeight:false,showFooter:true"
				style="width: 100%;">
			</table>
		</div>
	</div>

	<div id="myDictModal" style="display: none;">
		<form id="dict-form">
			<input type="hidden" name="id" id="edit-id"> <input
				type="hidden" name="parentDictCode" id="parentDictCode">
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">类型编码</label>
					<div class="BetweenInput">
						<input type="text" name="dictCode" required="required"
							style="width: 150px" class="easyui-textbox dictCode">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">类型名称</label>
					<div class="BetweenInput">
						<input type="text" name="dictName" required="required"
							style="width: 150px" class="easyui-textbox">
					</div>
				</div>
			</div>
		</form>
	</div>

	<div id="myDictValueModal" style="display: none;">
		<form id="dict-value-form">
			<input type="hidden" name="id" id="edit-id"> <input
				type="hidden" name="dictCode" id="dictCode">
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">键</label>
					<div class="BetweenInput">
						<input type="text" name="key" required="required"
							style="width: 150px" class="easyui-textbox key">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">值</label>
					<div class="BetweenInput">
						<input type="text" name="value" required="required"
							style="width: 150px" class="easyui-textbox">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">序号</label>
					<div class="BetweenInput">
						<input type="text" name="orderNo" required="required"
							style="width: 150px" class="easyui-numberbox">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">类型名称</label>
					<div class="BetweenInput">
						<input type="text" name="remark"
							style="width: 150px" class="easyui-textbox">
					</div>
				</div>
			</div>
		</form>
	</div>

	<c:import url="../../commons/common-script-ui.jsp" />
	<script type="text/javascript"
		src="${base}/scripts/common/dict/dict.js?${version}"></script>
</body>

</html>
