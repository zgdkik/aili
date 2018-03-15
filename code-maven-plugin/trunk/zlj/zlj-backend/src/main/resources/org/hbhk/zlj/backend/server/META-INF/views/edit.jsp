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
	<div id="search" class="easyui-panel"
		style="padding: 2px; width: 100%;">
		<a class="easyui-linkbutton add-btn" plain="true" iconCls="icon-save">保存</a>
		<a class="easyui-linkbutton  validateSql" plain="true"
			iconCls="icon-save">校验sql</a>
	</div>
	<div id="edit-form">
		<div class="easyui-panel" title="基本信息"
			style="width: 100%; height: auto; padding: 5px 0px 5px 5px"
			data-options="collapsible:true">
			<div>
				<input type="hidden" name="id" value="${crud.id}" id="edit-id">
				<div class="sfwl-row1 sfwl-col4">
					<div class="sfwl-form-field">
						<label class="sfwl-label">名称</label>
						<div class="BetweenInput">
							<input type="text" name="name" value="${crud.name}"
								required="required" style="width: 150px" class="easyui-textbox">
						</div>
					</div>
					<div class="sfwl-form-field">
						<label class="sfwl-label">编码</label>
						<div class="BetweenInput">
							<input type="text" name="code" value="${crud.code}"
								required="required" style="width: 150px"
								class="easyui-textbox code">
						</div>
					</div>
					<div class="sfwl-form-field">
						<label class="sfwl-label">上级菜单</label>
						<div class="BetweenInput">
							<select class="easyui-combobox" name="parentMenuCode"
								style="width: 150px;" required="required">
								<c:forEach items="${menus}" var="m">
									<option
										<c:if test="${crud.parentMenuCode == m.privilegeCode}">selected="selected"</c:if>
										value="${m.privilegeCode}">${m.privilegeName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>

				<div class="sfwl-row1 sfwl-col" style="height: 160px;">
					<div class="sfwl-form-field">
						<label class="sfwl-label">sql</label>
						<div class="BetweenInput" style="height: 150px;">
							<textarea name="sql" id="sql" required="required"
								style="width: 630px; height: 150px">${crud.sql}</textarea>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="easyui-panel" title="查询列头"
			style="width: 100%; height: auto;" data-options="collapsible:true">
			<div id="col-form">
				<c:forEach items="${cols}" var="col">
					<div class="sfwl-row1 sfwl-col4 del">
						<div class="sfwl-form-field">
							<label class="sfwl-label">列名</label>
							<div class="BetweenInput">
								<input type="text" name="col.field" required="required"
									readonly="readonly" value="${col.field}" style="width: 150px"
									class="field easyui-textbox">
							</div>
						</div>
						<div class="sfwl-form-field">
							<label class="sfwl-label">列类型</label>
							<div class="BetweenInput">
								<select class="easyui-combobox" name="col.type"
									style="width: 120px;" required="required"
									data-selected="${col.type}">
									<option value="text">文本</option>
									<option value="time">时间</option>
								</select>
							</div>
						</div>
						<div class="sfwl-form-field">
							<label class="sfwl-label">列标题</label>
							<div class="BetweenInput">
								<input type="text" name="col.title" required="required"
									style="width: 150px" value="${col.title}"
									class="title easyui-textbox">
							</div>
						</div>
						<div class="sfwl-form-field">
							<a class="easyui-linkbutton remove" plain="true"
								iconCls="icon-remove" style="margin-top: 4px;">删除</a>
						</div>
					</div>

				</c:forEach>
			</div>
		</div>

		<div class="easyui-panel" title="查询条件"
			style="width: 100%; height: auto;" id="c-form"
			data-options="collapsible:true">

			<div id="search" class="easyui-panel"
				style="padding: 2px; width: 100%;">
				<a class="easyui-linkbutton add-btn-c" iconCls="icon-add">添加</a>
			</div>

			<div id="condition-form">
				<c:forEach items="${cds}" var="cdt">
					<div class="sfwl-row1 sfwl-col4 del">
						<div class="sfwl-form-field">
							<label class="sfwl-label">条件类型</label>
							<div class="BetweenInput">
								<select name="cdt.conditionType"
									class="conditionType easyui-combobox" style="width: 150px;">
									<aili:dictSelect dictType="condition" />
								</select>
							</div>
						</div>
						<div class="sfwl-form-field">
							<label class="sfwl-label">查询标签</label>
							<div class="BetweenInput">
								<input type="text" name="cdt.conditionLabel"
									class="conditionLabel easyui-textbox" required="required"
									value="${cdt.conditionLabel}" style="width: 150px">
							</div>

						</div>
						<div class="sfwl-form-field">
							<label class="sfwl-label">查询列</label>
							<div class="BetweenInput">
								<input type="text" name="cdt.conditionName"
									class="conditionName easyui-textbox" required="required"
									value="${cdt.conditionName}" style="width: 150px">
							</div>

						</div>

						<div class="sfwl-form-field">
							<a class="easyui-linkbutton remove" plain="true"
								iconCls="icon-remove" style="margin-top: 4px;">删除</a>
						</div>
					</div>
				</c:forEach>

			</div>


		</div>
	</div>

	<div class="sfwl-row1 sfwl-col4 del" id="col-temp"
		style="display: none;">
		<div class="sfwl-form-field">
			<label class="sfwl-label">列名</label>
			<div class="BetweenInput">
				<input type="text" name="col.field" required="required"
					readonly="readonly" style="width: 150px" class="field">
			</div>
		</div>
		<div class="sfwl-form-field">
			<label class="sfwl-label">列类型</label>
			<div class="BetweenInput">
				<select class="easyui-combobox" name="col.type"
					style="width: 120px;" required="required">
					<option value="text" selected="selected">文本</option>
					<option value="time">时间</option>
				</select>
			</div>
		</div>
		<div class="sfwl-form-field">
			<label class="sfwl-label">列标题</label>
			<div class="BetweenInput">
				<input type="text" name="col.title" required="required"
					style="width: 150px" class="title">
			</div>
		</div>
		<div class="sfwl-form-field">
			<a class="easyui-linkbutton remove" plain="true"
				iconCls="icon-remove" style="margin-top: 4px;">删除</a>
		</div>
	</div>
	<div class="sfwl-row1 sfwl-col4 del" id="condition-temp"
		style="display: none;">
		<div class="sfwl-form-field">
			<label class="sfwl-label">条件类型</label>
			<div class="BetweenInput">
				<select name="cdt.conditionType" class="conditionType"
					style="width: 150px;">
					<aili:dictSelect dictType="condition" />
				</select>
			</div>
		</div>
		<div class="sfwl-form-field">
			<label class="sfwl-label">查询标签</label>
			<div class="BetweenInput">
				<input type="text" name="cdt.conditionLabel" class="conditionLabel"
					required="required" style="width: 150px">
			</div>

		</div>
		<div class="sfwl-form-field">
			<label class="sfwl-label">查询列</label>
			<div class="BetweenInput">
				<input type="text" name="cdt.conditionName" class="conditionName"
					required="required" style="width: 150px">
			</div>

		</div>
		<div class="sfwl-form-field">
			<a class="easyui-linkbutton remove" plain="true"
				iconCls="icon-remove" style="margin-top: 4px;">删除</a>
		</div>
	</div>
	<c:import url="../commons/common-script-ui.jsp" />
	<script type="text/javascript"
		src="${base}/scripts/crud/edit.js?${version}"></script>
</body>
</html>
