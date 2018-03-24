<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css-ui.jsp" />
<title>壹米滴答</title>
</head>
<body style="overflow-y: none">

	<div id="search" class="easyui-panel" style="padding: 2px; width: 100%;">
		<a class="easyui-linkbutton query-btn"  iconCls="icon-search">查询</a>  
	    <a class="expand-collapse easyui-linkbutton" data-href="#query-form"
			data-tbl="#dg" style="float: right;" iconCls="accordion-collapse"></a>
	</div>
		
	 <div class="easyui-panel" id="query-form" style="width: 100%;">
		<form class="query-form">
			<div class="sfwl-panel-context" style="float: left">
				<div class="sfwl-row1 sfwl-col5">
					<div class="sfwl-form-field">
						<label class="sfwl-label">用户名</label>
						<div class="sfwl-control">
							<input type="text" name="q_str_userName" class="easyui-textbox">
						</div>
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
				
	<div id="dlg" style="display: none;">
		<input type="hidden" class="userName" name="userName">
		<table id="dg-role" class="easyui-datagrid"
			data-options="singleSelect:false,autoRowHeight:false,showFooter:true,pagination:false"
			style="width: 100%;height: 270px">
		</table>
	</div>
					
	<c:import url="../../commons/common-script-ui.jsp" />
	<script type="text/javascript"
		src="${base}/scripts/user/user/userlist.js?${version}"></script>
</body>
</html>
