<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="/aili" prefix="aili"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<c:import url="../commons/common-meta.jsp" />
<c:import url="../commons/common-css-ui.jsp" />

<style type="text/css">
</style>
</head>
<body style="overflow-y: none">
	<div id="search" class="easyui-panel" style="width: 100%">
		<a class="easyui-linkbutton query-btn"  
			iconCls="icon-search">查询</a> <a class="easyui-linkbutton add-btn"
			  iconCls="icon-add">申请</a> <a
			class="expand-collapse easyui-linkbutton" data-href="#query-form"
			data-tbl="#dg" style="float: right;" iconCls="accordion-collapse"></a>
	</div>
	<div class="easyui-panel" id="query-form" style="width: 100%">
		<form class="query-form">
			<div class="sfwl-panel-context">
				<div class="sfwl-form-field">
					<label class="sfwl-label">应用编码</label>
					<div class="sfwl-control">
						<input id="sourceHq" name="q_str_appKey" class="easyui-textbox">
					</div>
				</div>
			</div>
		</form>
	</div>

	<div id="dlg" style="display: none;">
		<form id="edit-form">
			<input type="hidden" name="id" id="edit-id"> <input
				type="hidden" name="workflowType" value="leave">
		
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">请假类型</label>
					<div class="BetweenInput">
						<select class="easyui-combobox" style="width:150px;">
							<aili:dictSelect dictType="leave_type" />
						</select>
					</div>
				</div>
			</div>
				<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">开始时间</label>
					<div class="BetweenInput">
						<input type="text" name="startTime" required="required"
							style="width: 150px" class="easyui-datetimebox">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">结束时间</label>
					<div class="BetweenInput">
						<input type="text" name="endTime" required="required"
							style="width: 150px" class="easyui-datetimebox">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">天数</label>
					<div class="BetweenInput">
						<input type="text" name="days" required="required"
							style="width: 150px" class="easyui-numberbox">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col" style="height: 65px;">
				<div class="sfwl-form-field">
					<label class="sfwl-label">申请原因</label>
					<div class="BetweenInput">
						<textarea style="width: 150px; height: 50px;"></textarea>
					</div>
				</div>
			</div>
				<div class="sfwl-row1 sfwl-col" style="height: 65px;">
				<div class="sfwl-form-field">
					<label class="sfwl-label">附件</label>
					<div class="BetweenInput">
						<input id="fileupload"  name="file" style="width:150px">
					</div>
				</div>
			</div>
		</form>
	</div>

	<div id="dlg-upload" style="display: none;">
		<form id="edit-form-upload">
			<input type="hidden" name="id" id="upload-edit-id">
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">应用</label>
					<div class="BetweenInput">
						<input id="fileupload" name="barFile" style="width: 300px">
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
		src="${base}/scripts/workflow/leaveList.js?${version}"></script>
</body>
</html>
