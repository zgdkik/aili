<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css-ui.jsp" />

<style type="text/css">

</style>
</head>
<body style="overflow-y: none">
	<div id="search" class="easyui-panel"
		style="width: 100%;">
		<a class="easyui-linkbutton query-btn"  
			iconCls="icon-search">查询</a> <a class="easyui-linkbutton add-btn"
			  iconCls="icon-add">新增</a> <a
			class="expand-collapse easyui-linkbutton" data-href="#query-form"
			data-tbl="#dg" style="float: right;" iconCls="accordion-collapse"></a>
	</div>
	<div class="easyui-panel"
		style="width: 100%;"
		id="query-form">
		<form class="query-form">
			<div class="sfwl-panel-context"
				style="float: left;">
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
			<input type="hidden" name="id" id="edit-id">
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">应用编码</label>
					<div class="BetweenInput">
						<input type="text" name="appKey" required="required"
							style="width: 150px" class="easyui-textbox appKey">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">应用名称</label>
					<div class="BetweenInput">
						<input type="text" name="appName" required="required"
							style="width: 150px" class="easyui-textbox">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">版本号</label>
					<div class="BetweenInput">
						<input type="text" name="version" required="required"
							style="width: 150px" class="easyui-numberbox">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">版本名称</label>
					<div class="BetweenInput">
						<input type="text" name="versionName" required="required"
							style="width: 150px" class="easyui-textbox">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">强制更新</label>
					<div class="BetweenInput">
						<!-- <input type="text" name="forceUpdate" required="required"
							style="width: 150px" class="easyui-textbox"> -->
						<div class="easyui-radio" id="radio" style="width: 100%">
							<input type="radio" name="forceUpdate" value="1" 
								label="是"> <input type="radio"
								name="forceUpdate" value="0" checked label="否">
						</div>
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">发布时间</label>
					<div class="BetweenInput">
						<input type="text" name="releaseTime" required="required"
							style="width: 150px" class="easyui-datetimebox">
					</div>
				</div>
			</div>

			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">下载链接</label>
					<div class="BetweenInput">
						<input type="text" name="downloadUrl" required="required"
							style="width: 150px" class="easyui-textbox">
					</div>
				</div>
			</div>
			<div class="sfwl-row1 sfwl-col">
				<div class="sfwl-form-field">
					<label class="sfwl-label">文件路径</label>
					<div class="BetweenInput">
						<input type="text" name="filePath" style="width: 150px"
							class="easyui-textbox">
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
						<input id="fileupload"  name="barFile" style="width:300px">
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
	<c:import url="../../commons/common-script-ui.jsp" />
	<script type="text/javascript"
		src="${staticbase}/upload/ajaxfileupload.js?${version}"></script>
	<script type="text/javascript"
		src="${base}/scripts/common/client/list.js?${version}"></script>
</body>
</html>
