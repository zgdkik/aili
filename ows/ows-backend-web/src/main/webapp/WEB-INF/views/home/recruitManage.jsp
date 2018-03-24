<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CKEditor</title>
<script src="${base}/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${base}/resources/statics/js/jquery-1.10.2.min.js"></script>
<c:import url="../commons/common-meta.jsp" />
<c:import url="../commons/common-css-ui.jsp" />
<c:import url="../commons/common-script-ui.jsp" />
<script type="text/javascript">

$(function(){
	init();
	queryData();
})
function queryData(){
   	var opts={};
	ym.initPage("${base}/recruit/getAllrecruit",opts,null,'#list_data')
   };
function init(){
	$('#list_data').datagrid({ 
		pagination : true, //开启分页
		pageSize : 20, //每页显示数目
		pageList : [ 20, 30, 40, 50 ], //选择每页显示数目(与pageSize对应)
		fit : true, //适应大小
		fitColumns : true, //自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动
		nowrap:false, //是否关闭自动换行(true关闭，false开启)
		border : false, //边框
		remoteSort:false,
		singleSelect:true,
		rownumbers:true,
	    toolbar: [{ 
	        text: '添加', 
	        iconCls: 'icon-add', 
	        handler: fn_add
	    }, '-', { 
	        text: '修改', 
	        iconCls: 'icon-edit', 
	        handler: fn_upd
	    }, '-',{ 
	        text: '删除', 
	        iconCls: 'icon-remove', 
	        handler: fn_del
	    }],
		columns : [ [{
						title : "<b>主键</b>",
						field : "id",
						hidden:'hidden'
					},{
		    			title : "<b>招聘标题</b>",
		    			field : "recruitTitle",
		    			align : 'center',
		    			width : 150
		    		},{
		    			title : "<b>工作地点</b>",
		    			field : "recruitArea",
		    			width : 100,
		    			align : 'center'
		    		}, {
		    			title : "<b>职业类别</b>",
		    			field : "recruitCategory",
		    			align : 'center',
		    			width : 100
		    		}, {
		    			title : "<b>招聘人数</b>",
		    			field : "recruitCount",
		    			align : 'center',
		    			width : 80
		    		},  {
		    			title : "<b>联系邮箱</b>",
		    			field : "recruitMail",
		    			align : 'center',
		    			width : 200
		    		},  {
		    			title : "<b>招聘开始日期</b>",
		    			field : "recruitBegindate",
		    			align : 'center',
		    			width : 80,
		    			formatter:function(value,row,index){  
		                    var unixTimestamp = new Date(value);  
		                    return unixTimestamp.toLocaleDateString();
		                }  
		    		}, {
		    			title : "<b>招聘结束日期</b>",
		    			field : "recruitEnddate",
		    			align : 'center',
		    			width : 80,
		    			formatter:function(value,row,index){  
		                    var unixTimestamp = new Date(value);  
		                    return unixTimestamp.toLocaleDateString();
		                }  
		    		}, {
		    			title : "<b>发布时间</b>",
		    			field : "createDate",
		    			align : 'center',
		    			width : 80,
		    			formatter:function(value,row,index){  
		                    var unixTimestamp = new Date(value);  
		                    return unixTimestamp.toLocaleDateString();
		                }  
		    		}] ]
	});
}
//设置分页控件 
var p = $('#list_data').datagrid('getPager'); 
$(p).pagination({ 
    pageSize: 10,//每页显示的记录条数，默认为10 
    pageList: [5,10,15],//可以设置每页记录条数的列表 
    beforePageText: '第',//页数文本框前显示的汉字 
    afterPageText: '页    共 {pages} 页', 
    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录' 
});

function fn_add(){
	$("#ff").form('clear');
	$('#w').window('open');
	url='${base}/recruit/insertOrUpdateRecruit';
}

function fn_upd(){
	  var row = $("#list_data").treegrid("getSelected");
    if(row){
  	  $('#ff').form('load',row);
  	  $('#w').window('open');
  	  url='${base}/recruit/insertOrUpdateRecruit';
    }else{
  	  $.messager.alert({title: '提示',msg: '请选择需要修改的数据'},'info');
    }
}

function fn_del(){
	var row = $("#list_data").treegrid("getSelected");
    if(row){
  		  $.messager.confirm('Confirm','删除后数据将无法恢复，确定删除当前选中数据?',function(r){
                if (r){
                	$.ajax({
               	    type: 'POST',
               	    url: '${base}/recruit/deleteRecruit',
               	    data: {
               	    	 'recruitId': row.id
               	    	 },
               	 dataType:	'JSON',
               	 success: function (data) {
               	    	if(data.data==1){
               	    		$.messager.alert("成功","删除成功","info", function () {
                       			$('#w').window('close');
                       		 	$('#list_data').datagrid("reload"); 
                   			});
               	    	}else{
               	    		$.messager.alert("失败","删除失败");
               	    	}
               		} ,
               	});

                }
            });
  	  }else{
  	  $.messager.alert({title: '提示',msg: '请选择需要删除的信息'},'info');
    } 
}
function fn_save(){
	if($("#recruitTitle").val()==null||$("#recruitTitle").val()==''){
		$.messager.alert("提示","招聘标题不能为空");
		return;
	}
	if($("#recruitArea").val()==null||$("#recruitArea").val()==''){
		$.messager.alert("提示","工作地点不能为空");
		return;
	}
	if($("#recruitCategory").val()==null||$("#recruitCategory").val()==''){
		$.messager.alert("提示","职业类别不能为空");
		return;
	}
	if($("#recruitCount").val()==null||$("#recruitCount").val()==''){
		$.messager.alert("提示","招聘人数不能为空");
		return;
	}
	if($("#recruitMail").val()==null||$("#recruitMail").val()==''){
		$.messager.alert("提示","邮箱不能为空");
		return;
	}
	if($("#recruitBegindate").datebox('getValue')==null||$("#recruitBegindate").datebox('getValue')==''){
		$.messager.alert("提示","开始时间不能为空");
		return;
	}
	if($("#recruitEnddate").datebox('getValue')==null||$("#recruitEnddate").datebox('getValue')==''){
		$.messager.alert("提示","结束时间不能为空");
		return;
	}
	if($("#recruitJob").val()==null||$("#recruitJob").val()==''){
		$.messager.alert("提示","职位描述不能为空");
		return;
	}
	if($("#recruitRequirement").val()==null||$("#recruitRequirement").val()==''){
		$.messager.alert("提示","任职要求不能为空");
		return;
	}
	if($("#recruitWelfare").val()==null||$("#recruitWelfare").val()==''){
		$.messager.alert("提示","福利待遇不能为空");
		return;
	}
	if($("#recruitContact").val()==null||$("#recruitContact").val()==''){
		$.messager.alert("提示","联系不能为空");
		return;
	}
	var param=$("#ff").getFormObj();
	$.ajax({
	     type: 'POST',
	     url: '${base}/recruit/insertOrUpdateRecruit',
	     data: param,
	   dataType:	'JSON',
	  success: function (data) {
		  var data=data.data;
	    	if(data==1){
	    		$.messager.alert("成功","保存成功","info", function () {
        			$('#w').window('close');
        		 	$('#list_data').datagrid("reload"); 
    			});
	    	}else if(data==2){
	    		$.messager.alert("失败","保存失败");
	    	}else if(data==3){
	    		$.messager.alert("失败","系统异常");
	    	} 
		} ,
	});
}

function fn_cancel(){
	  $('#w').window('close');
}


</script>


<div id="w" class="easyui-window" title="招聘信息" data-options="modal:true,closed:true,iconCls:'icon-edit'" style="width:500px;height:650px;padding:10px;">
		<form id="ff" method="post">
			<input  type="hidden" id="id" name="id"></input>
			<input  type="hidden" id="recruitStatus" name="recruitStatus"></input>
	    	<table cellpadding="5">
	    		<tr>
	    			<td>招聘标题:</td>
	    			
	    			<td colspan="2"><input class="easyui-textbox" type="text" id="recruitTitle" name="recruitTitle" data-options="required:true" style="width:250px"></input></td>
	    		</tr>
	    		<tr>
	    			<td>工作地点:</td>
	    			<td colspan="2"><input class="easyui-textbox" type="text" id="recruitArea" name="recruitArea" data-options="required:true" style="width:250px"></input></td>
	    		</tr>
	    		<tr>
	    			<td>职业类别:</td>
	    			<td colspan="2"><input class="easyui-textbox" id="recruitCategory" name="recruitCategory" data-options="required:true" style="width:250px"/></td>
	    		</tr>
    			<tr>
	    			<td>招聘人数:</td>
	    			<td colspan="2"><input class="easyui-textbox" id="recruitCount" name="recruitCount" data-options="required:true" style="width:250px"/></td>
	    		</tr>
	    		<tr>
	    			<td>邮箱:</td>
	    			<td colspan="2"><input class="easyui-textbox" id="recruitMail" name="recruitMail" data-options="required:true" style="width:250px"/></td>
	    		</tr>
	    		<tr>
	    			<td>招聘开始时间:</td>
	    			<td colspan="2"><input class="easyui-datebox"  type="text" name="recruitBegindate" id="recruitBegindate" data-options="required:true,editable:false" style="width:250px"></input></td>
	    		</tr>
	    		<tr>
	    			<td>招聘结束时间:</td>
	    			<td colspan="2"><input class="easyui-datebox" type="text" id="recruitEnddate" name="recruitEnddate" data-options="required:true,editable:false" style="width:250px" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>职位描述:</td>
	    			<td colspan="2"><input class="easyui-textbox" type="text"  id="recruitJob" name="recruitJob" data-options="required:true,multiline:true" style="width:350px;height:65px" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>任职要求:</td>
	    			<td colspan="2"><input class="easyui-textbox" type="text" id="recruitRequirement" name="recruitRequirement" data-options="required:true,multiline:true" style="width:350px;height:65px"></input></td>
	    		</tr>
	    		<tr>
	    			<td>福利待遇:</td>
	    			<td colspan="2"><input class="easyui-textbox" type="text" id="recruitWelfare" name="recruitWelfare" data-options="required:true,multiline:true" style="width:350px;height:65px"></input></td>
	    		</tr>
	    		<tr>
	    			<td>联系方式:</td>
	    			<td colspan="2"><input class="easyui-textbox" type="text" id="recruitContact" name="recruitContact" data-options="required:true,multiline:true" style="width:350px;height:65px"></input></td>
	    		</tr>
	    		<tr>
	    			<td colspan="3" align="middle" style="padding-left:20px">
	    			  <a href="javascript:void(0)" data-options="iconCls:'icon-save'" class="easyui-linkbutton" onclick="fn_save()">保存</a>
	    			  <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="fn_cancel()">取消</a>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
		<table id="list_data"></table>
</body>
</html>
