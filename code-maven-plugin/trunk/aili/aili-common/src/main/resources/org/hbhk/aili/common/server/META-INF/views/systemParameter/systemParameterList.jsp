<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css-ui.jsp" />
</head>
<body style="overflow-y: none">
	<div id="search" class="easyui-panel"
		style="padding: 2px; width: 100%;">
		<a class="easyui-linkbutton query-btn"  
			iconCls="icon-search">查询</a> <a class="easyui-linkbutton add-btn"
			  iconCls="icon-add">新增</a> <a
			class="expand-collapse easyui-linkbutton" data-href="#query-form"
			data-tbl="#dg" style="float: right;" iconCls="accordion-collapse"></a>
	</div>
	<div class="easyui-panel"
		style="width: 100%;" id="query-form">
		<form class="query-form">
			<div class="sfwl-panel-context" style="float: left;">
				<div class="sfwl-form-field">
					<label class="sfwl-label">参数</label>
					<div class="sfwl-control">
						<input id="sourceHq" name="q_str_sysKey"
								class="easyui-textbox">
					</div>
				</div>
				<div class="sfwl-form-field">
					<label class="sfwl-label">参数值</label>
					<div class="sfwl-control">
						<input id="destHq" name="q_str_sysValue"
								class="easyui-textbox">
					</div>
				</div>
			</div>
		</form>
	</div>

	<div id="dlg" style="display: none;">
		  <form id="company-form">
		     <input type="hidden" name="id">
				 <div class="sfwl-row1 sfwl-col">
					<div class="sfwl-form-field">
						<label class="sfwl-label">参数</label>
						<div class="BetweenInput">
							<input type="text" name="sysKey"  required="required" style="width: 150px" class="easyui-textbox">
						</div>
					</div>
				</div>
			    <div class="sfwl-row1 sfwl-col">
					<div class="sfwl-form-field">
						<label class="sfwl-label">参数值</label>
						<div class="BetweenInput">
							<input type="text" name="sysValue"  required="required" style="width: 150px" class="easyui-textbox">
						</div>
					</div>
				</div>
		</form>
	</div>
	<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<span id="title">新增参数</span>
					</h4>
				</div>
				<div class="modal-body">
					<form role="form" id="sp-form" class="form-horizontal adminex-form">
						<input type="hidden" name="id">
						<div class="form-group ">
							<label class="col-xs-3 control-label">参数关键字</label>
							<div class="col-xs-8">
								<input type="text" name="sysKey" class="form-control">
							</div>
						</div>
						<div class="form-group ">
							<label class="col-xs-3 control-label">参数值</label>
							<div class="col-xs-8">
								<input type="text" name="sysValue" class="form-control">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="addSysParam" class="btn btn-primary save">保存</button>
					<button type="button" id="hideSysParam" class="btn btn-primary cancel">取消</button>
				</div>
			</div>
		</div>
	</div> -->
	
	<div id="tblDiv">
		<table id="dg" class="easyui-datagrid"
			data-options="singleSelect:true,autoRowHeight:false,showFooter:true"
			style="width: 100%;">
		</table>
	</div>
	<c:import url="../../commons/common-script-ui.jsp" />
	<script type="text/javascript"
		src="${base}/scripts/common/systemParameter/sysParamList.js?${version}"></script>
</body>
</html>
