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
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<div data-options="region:'west',title:'部门架构',split:true"
			style="width: 200px;">
			<div class="easyui-panel" style="width: 100%; height: 100%;">
				<ul id="depttree" class="ztree"></ul>
			</div>
		</div>
		<div data-options="region:'center'">
			<div id="search" class="easyui-panel"
				style="padding: 2px; width: 100%;">
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
							<label class="sfwl-label">工号</label>
							<div class="sfwl-control">
								<input id="sourceHq" name="q_str_empCode" class="easyui-textbox">
							</div>
						</div>
						<div class="sfwl-form-field">
							<label class="sfwl-label">姓名</label>
							<div class="BetweenInput">
								<input type="text" name="q_str_empName" style="width: 150px"
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
			<div id="dlg" style="display: none;">
				<form id="edit-form">
					<input type="hidden" name="id" id="edit-id">
					<div class="sfwl-row1 sfwl-col">
						<div class="sfwl-form-field">
							<label class="sfwl-label">工号</label>
							<div class="BetweenInput">
								<input type="text" name="empCode" required="required"
									style="width: 150px" class="easyui-textbox empCode">
							</div>
						</div>
					</div>
					<div class="sfwl-row1 sfwl-col">
						<div class="sfwl-form-field">
							<label class="sfwl-label">姓名</label>
							<div class="BetweenInput">
								<input type="text" name="empName" required="required"
									style="width: 150px" class="easyui-textbox">
							</div>
						</div>
					</div>
					<div class="sfwl-row1 sfwl-col">
						<div class="sfwl-form-field">
							<label class="sfwl-label">部门</label>
							<div class="BetweenInput">
								<input type="text" name="deptCode" id="deptCode"
									required="required" style="width: 150px" class="easyui-textbox">
							</div>
						</div>
					</div>
					<div class="sfwl-row1 sfwl-col">
						<div class="sfwl-form-field">
							<label class="sfwl-label">性别</label>
							<div class="BetweenInput">
								<div class="easyui-radio" id="radio" style="width: 100%">
									<input type="radio" name="gender" value="1" label="男">
									<input type="radio" name="gender" value="0" checked label="女">
								</div>
							</div>
						</div>
					</div>
					<div class="sfwl-row1 sfwl-col">
						<div class="sfwl-form-field">
							<label class="sfwl-label">手机号码</label>
							<div class="BetweenInput">
								<input type="text" name="mobileNumber" required="required"
									style="width: 150px" class="easyui-textbox">
							</div>
						</div>
					</div>
					<div class="sfwl-row1 sfwl-col">
						<div class="sfwl-form-field">
							<label class="sfwl-label">职位</label>
							<div class="BetweenInput">
								<select name="position" style="width: 150px;"
									class="easyui-combobox">
									<aili:dictSelect dictType="position_type" />
								</select>
							</div>
						</div>
					</div>

					<div class="sfwl-row1 sfwl-col">
						<div class="sfwl-form-field">
							<label class="sfwl-label">入职日期</label>
							<div class="BetweenInput">
								<input type="text" name="inDate" required="required"
									style="width: 150px" class="easyui-datebox">
							</div>
						</div>
					</div>
					<div class="sfwl-row1 sfwl-col">
						<div class="sfwl-form-field">
							<label class="sfwl-label">出生日期</label>
							<div class="BetweenInput">
								<input type="text" name="birthDate" required="required"
									style="width: 150px" class="easyui-datebox">
							</div>
						</div>
					</div>

				</form>
			</div>

		</div>
	</div>
	
	<c:import url="../commons/common-script-ui.jsp" />
	<script type="text/javascript"
		src="${base}/scripts/org/empList.js?${version}"></script>
</body>

</html>
