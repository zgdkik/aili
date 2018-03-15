<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<c:import url="../commons/common-meta.jsp" />
<c:import url="../commons/common-css-ui.jsp" />
<title>${report.name}</title>
<style type="text/css">
</style>
</head>
<body style="overflow-y: none">
	<div id="search" class="easyui-panel"
		style="padding: 2px; width: 100%;">
		<a class="easyui-linkbutton query-btn" plain="true"
			iconCls="icon-search">查询</a> <a class="easyui-linkbutton add-btn"
			plain="true" iconCls="icon-download">导出</a> <a
			class="expand-collapse easyui-linkbutton" data-href="#query-form"
			data-tbl="#dg" style="float: right;" iconCls="accordion-collapse"></a>
	</div>
	<div class="easyui-panel"
		style="width: 100%; height: auto;"
		id="query-form">
		<form class="query-form">

			<div class="sfwl-row1 sfwl-col4">
				<c:forEach items="${cdts}" var="cdt">
					<div class="sfwl-form-field">
						<label class="sfwl-label">${cdt.conditionLabel}</label>
						<div class="sfwl-control">
							<input name="q_${cdt.conditionType}_${cdt.conditionName}" class="easyui-${cdt.conditionType}">
						</div>
					</div>
				</c:forEach>
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

	<script type="text/javascript">
		var code = "${code}";
		var columns = "${columns}";
		columns = eval('(' + columns + ')');
	</script>
	<script type="text/javascript"
		src="${base}/scripts/crud/crud.js?${version}"></script>
</body>
</html>
