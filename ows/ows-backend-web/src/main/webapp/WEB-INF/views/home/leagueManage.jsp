<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<c:import url="../commons/common-css-ui.jsp" />
<script type="text/javascript" src="${base}/resources/statics/js/jquery-1.10.2.min.js"></script>
<c:import url="../commons/common-meta.jsp" />
<c:import url="../commons/common-script-ui.jsp" />
</head>
<body style="overflow-y: none">
<div id="search" class="easyui-panel" style="padding: 2px; width: 100%;">
<a class="easyui-linkbutton query" id="query" iconCls="icon-search" onclick="queryData()">查询</a>
<a class="easyui-linkbutton" id="addDept" iconCls="icon-add">新增</a>
<a class="easyui-linkbutton" id="updateDept" iconCls="icon-edit">修改</a>
<a class="easyui-linkbutton" id="deleteDept" iconCls="icon-remove">删除</a>

<a class="expand-collapse easyui-linkbutton" data-href="#query-form" data-tbl="#list_data" style="float: right;" iconCls="accordion-collapse"></a>
</div>
	<div class="easyui-panel"  style="width: 100%;" id="query-form">
		<form class="query-form">
			<div class="sfwl-panel-context" style="float: left">
			   <div class="sfwl-row1 sfwl-col5">
			       
			       	<div class="sfwl-form-field">
			            <label class="sfwl-label">网点名称</label>
						<div class="sfwl-control" style="width: 100px;">
						  	<input type="text" class="easyui-textbox" id="q_sl_deptName" name="q_sl_deptName" />
						</div>
			       </div>
			       <div class="sfwl-form-field">
			            <label class="sfwl-label">省份</label>
						<div class="sfwl-control" style="width: 100px;">
						  	<input type="text" class="easyui-combobox"  data-options="    
							        valueField: 'code',    
							        textField: 'name',    
							        url: '${base}/customer/getProvice',
							        onSelect: function(rec){
							        	 $('#city').combobox('clear');
							        	 $('#district').combobox('clear');    
							            var url = '${base}/customer/getCity?provinceCode='+rec.code ; 
							            $('#q_str_city').combobox('reload', url);    
							        }     
							       "id="q_str_province" name="q_str_province" />
						</div>
			       </div>
			       <div class="sfwl-form-field">
			            <label class="sfwl-label">城市</label>
						<div class="sfwl-control" style="width: 100px;">
						  	<input type="text" class="easyui-combobox" id="q_str_city" name="q_str_city"  data-options="valueField:'code',
						  	textField:'name',
						  	onSelect: function(rec){
						  				 $('#district').combobox('clear');
							            var url = '${base}/customer/getArea?cityCode='+rec.code    
							            $('#q_str_district').combobox('reload', url);    
							 }  " />
						</div>
			       </div>
			       <div class="sfwl-form-field">
			            <label class="sfwl-label">区/县</label>
						<div class="sfwl-control" style="width: 100px;">
						  	<input type="text" class="easyui-combobox" id="q_str_district" name="q_str_district" data-options="valueField:'code',
						  	textField:'name'
						  	 " />
						</div>
			       </div>
	          </div>
         </div>
     </form>
 </div>
 <!-- tblDiv css self-adaption At the bottom in function -->
 <div id="tblDiv">
 	<table id="list_data"></table> 
 </div>
 <!-- show table -->
</body>
<script type="text/javascript">
$(function(){
	//Close Windows
	$('#opreateHtml').window('close');
	init();
	queryData();
})
//query All
function queryData(){
   	var params = $('.query-form').getFormObj();
    ym.initPage(base + "/customer/getLeaguePage",{},params,'#list_data');	
};
function init(){
	$('#list_data').datagrid({
	    pagination : true, //开启分页
		fitColumns : true, //自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动
		nowrap:false, //是否关闭自动换行(true关闭，false开启)
		border : false, //边框
		remoteSort:false,
		singleSelect:true,
		rownumbers:true,
	 	columns : [ [
	 	  {
			title : "<b>主键</b>",
			field : "id",
			width : 80,
			hidden:'hidden',
			align : 'center',
		},{
			title : "<b>网点名称</b>",
			field : "name",
			width : 80,
			align : 'center',
		}, {
			title : "<b>联系人</b>",
			field : "linkMan",
			align : 'center',
			width : 80,
		},{
			title : "<b>联系电话</b>",
			field : "linkPhone",
			align : 'center',
			width : 100,
		}, {
			title : "<b>网点地址</b>",
			field : "address",
			align : 'center',
			width : 200
		}, {
			title : "<b>修改时间</b>",
			field : "changeDate",
			align : 'center',
			width : 80,
			formatter : function(date){
	        	return ym.formatTime(date);
			}
		}, {
			title : "<b>操作用户</b>",
			field : "changeUser",
			align : 'center',
			width : 80,
		}] ],
	});
}



//save-edit

//delete
 $("#addDept").click(function(){
	 var iHeight = 600;
     var iWidth = 1085;
     var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
     var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
     var win = window.open(base+"/ddwl/league_edit", "_", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
 });
 $("#updateDept").click(function(){
	 var allCheck=$("#list_data").datagrid('getSelected');
	 if (allCheck==null) {
	     $.messager.alert("提示", "请选择要修改的行！", "info");
	     return;
	 }
	 var iHeight = 600;
     var iWidth = 1085;
     var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
     var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
     var win = window.open(base+"/ddwl/league_edit?leagueId="+allCheck.id, "_", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
 });
 $("#deleteDept").click(function(){
	 var allCheck=$("#list_data").datagrid('getSelected');
		if (allCheck==null) {
         $.messager.alert("提示", "请选择要删除的行！", "info");
         return;
     }
	$.messager.confirm("提示","您确定要删除该条信息吗？",function(data){
		if(!data){
			return;
		}
		var id = allCheck.id;
		$.ajax({
			type:"post",
			url:"${base}/customer/deleteDept",
			data:{
				"id":id,
			},
			success:function(data){
				if(data.success){
					$.messager.alert("提示", "删除成功！", "info");
					$("#list_data").datagrid('reload');
				}else{
					 $.messager.alert("提示", data.msg, "info");
				}
			}
		});
	});
 });
//让分页自适应让其在最下行
$(function(){
	var  tblh =  $(window).height() - $('#tblDiv').offset().top;
	$('#list_data').datagrid('resize', {
		//定义表格高度
		height : tblh
	});
})
</script>
</html>