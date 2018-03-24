<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="/ymdd" prefix="ymdd" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css-ui.jsp" />
<c:import url="../../commons/common-script-ui.jsp" />
<link rel="stylesheet" type="text/css" href="${base}/resources/statics/easyui/css/formBigInput.css">
<script type="text/javascript" src="${base}/scripts/user/privilege/privilegelist.js?${version}"></script>
<title>壹米滴答</title>
</head>
<body class="easyui-layout">

<!-- 西部树型div  -->
<div  region="west"  split="true"  style="width:220px;padding:10px;" id="div1">
	<ul id="treeDemo" class="ztree"></ul>
</div>

<!-- 中部表单div -->
<div region="center" split="true"  style="padding:10px;" id="div2">
	 <a class="easyui-linkbutton addnew"  iconCls="icon-add">新增</a> 
	 <a class="easyui-linkbutton update"  iconCls="icon-edit">修改</a> 
	 <a class="easyui-linkbutton remove"  iconCls="icon-cancel">删除</a> 
	 <div class="easyui-panel" style="width: 100%; height: auto;" id="query-form">
		 <form class="query-form" id="privilege-info">
		 	<div class="sfwl-row1 sfwl-col5">
		 		<div class="sfwl-form-field">
					<label class="sfwl-label">所属公司</label>
					<div class="sfwl-control">
						<select class="easyui-combobox"  name="compCode" disabled="disabled">
						<option value=""></option>
						<option value="common">通用</option>
						<option value="common">大道物流</option>
						</select>
					</div>
				</div>
				<div class="sfwl-form-field">
					<label class="sfwl-label">是否官网菜单</label>
					<div class="sfwl-control">
						<select class="easyui-combobox"  name="isWebMenu" disabled="disabled">
						<option value="0">否</option>
						<option value="1">是</option>
						</select>
					</div>
				</div>
		 	</div>
		     <div class="sfwl-row1 sfwl-col5">
					<div class="sfwl-form-field">
						<label class="sfwl-label">系统类型</label>
						<div class="sfwl-control">
							<select class="easyui-combobox"  name="appType" disabled="disabled">
							<option value=""></option>
							<ymdd:dictSelect dictType="system_type"/>
							</select>
						</div>
					</div>
					<div class="sfwl-form-field">
						<label class="sfwl-label">权限类型</label>
						<div class="sfwl-control">
							<select class="easyui-combobox"  name="moduleType" disabled="disabled">
							<option value=""></option>
							<ymdd:dictSelect dictType="module_type"/>
							</select>
						</div>
					</div>
					<div class="sfwl-form-field">
						<label class="sfwl-label">权限编码</label>
						<div class="sfwl-control">
							<input name="privilegeCode" class="easyui-textbox" readonly="readonly">
						</div>
					</div>
					<div class="sfwl-form-field">
						<label class="sfwl-label">权限URL</label>
						<div class="sfwl-control">
							<input name="url" class="easyui-textbox" readonly="readonly">
						</div>
					</div>
					<div class="sfwl-form-field">
						<label class="sfwl-label">官网url</label>
						<div class="sfwl-control">
							<input name="gwUrl" class="easyui-textbox" readonly="readonly">
						</div>
					</div>
				</div>
		 
		       <div class="sfwl-row1 sfwl-col5">
					<div class="sfwl-form-field">
						<label class="sfwl-label">权限名称</label>
						<div class="sfwl-control">
							<input name="functionName" class="easyui-textbox" readonly="readonly">
						</div>
					</div>
					<div class="sfwl-form-field">
						<label class="sfwl-label">图标</label>
						<div class="sfwl-control">
							<input name="iconCls" class="easyui-textbox" readonly="readonly">
						</div>
					</div>
					<div class="sfwl-form-field">
						<label class="sfwl-label">显示顺序</label>
						<div class="sfwl-control">
							<input name="displayOrder" class="easyui-textbox" readonly="readonly">
						</div>
					</div>
					<div class="sfwl-form-field">
						<label class="sfwl-label">快捷键</label>
						<div class="sfwl-control">
							<input name="shoutcutKeys"  class="easyui-textbox" readonly="readonly">
						</div>
					</div>
					<div class="sfwl-form-field">
						<label class="sfwl-label">权限描述</label>
						<div class="sfwl-control">
							<input name="functionDesc"  class="easyui-textbox" readonly="readonly">
						</div>
					</div>
				</div>
				
		 </form>
	 </div>
	 
	 <div id="dlg" style="display: none;">
	 	<form id="privilege-form" >
	 		<div class="sfwl-row1 sfwl-col5">
		 		<div class="sfwl-form-field">
					<label class="sfwl-label">所属公司</label>
					<div class="sfwl-control">
						<select class="easyui-combobox"  name="compCode" required="required">
							<option value=""></option>
							<option value="common">通用</option>
							<option value="ddwl">大道物流</option>
						</select>
					</div>
				</div>
				<div class="sfwl-form-field">
					<label class="sfwl-label">是否官网菜单</label>
					<div class="sfwl-control">
						<select class="easyui-combobox"  name="isWebMenu"  required="required">
						<option value="0">否</option>
						<option value="1">是</option>
						</select>
					</div>
				</div>
		 	</div>
			<div class="sfwl-row1 sfwl-col5" >
				<div class="sfwl-form-field">
					<input type="hidden" name="parentCode">
					<input type="hidden" name="id">
					<label class="sfwl-label">系统类型</label>
					<div class="sfwl-control">
						<select class="easyui-combobox"  required="required" editable="false"  name="appType">
						<option value=""></option>
						<ymdd:dictSelect dictType="system_type"/>
						</select>
					</div>
				</div>
				<div class="sfwl-form-field">
					<label class="sfwl-label">权限类型</label>
					<div class="sfwl-control">
						<select class="easyui-combobox" required="required" editable="false"  name="moduleType">
						<option value=""></option>
						<ymdd:dictSelect dictType="module_type"/>
						</select>
					</div>
				</div>
				<div class="sfwl-form-field">
					<label class="sfwl-label">权限编码</label>
					<div class="sfwl-control">
						<input name="privilegeCode" required="required" class="easyui-textbox" id="code">
					</div>
				</div>
				<div class="sfwl-form-field">
					<label class="sfwl-label">权限URL</label>
					<div class="sfwl-control">
						<input name="url" required="required" class="easyui-textbox">
					</div>
				</div>
				<div class="sfwl-form-field">
					<label class="sfwl-label">官网URL</label>
					<div class="sfwl-control">
						<input name="gwUrl" required="required" class="easyui-textbox">
					</div>
				</div>
			</div>
	 
	       <div class="sfwl-row1 sfwl-col5">
				<div class="sfwl-form-field">
					<label class="sfwl-label">权限名称</label>
					<div class="sfwl-control">
						<input name="functionName" id="functionName" required="required" class="easyui-textbox">
					</div>
				</div>
				<div class="sfwl-form-field">
					<label class="sfwl-label">图标</label>
					<div class="sfwl-control">
						<input name="iconCls"  class="easyui-textbox">
					</div>
				</div>
				<div class="sfwl-form-field">
					<label class="sfwl-label">显示顺序</label>
					<div class="sfwl-control">
						<input name="displayOrder" required="required" class="easyui-numberbox" missingMessage="必须为数字" precision="0">
					</div>
				</div>
				<div class="sfwl-form-field">
					<label class="sfwl-label">快捷键</label>
					<div class="sfwl-control">
						<input name="shoutcutKeys"  class="easyui-textbox">
					</div>
				</div>
				<div class="sfwl-form-field">
					<label class="sfwl-label">权限描述</label>
					<div class="sfwl-control">
						 <input name="functionDesc"  class="easyui-textbox">
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

</body>
</html>
