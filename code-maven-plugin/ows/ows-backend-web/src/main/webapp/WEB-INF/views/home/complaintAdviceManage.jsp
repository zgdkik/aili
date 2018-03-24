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
<script type="text/javascript" src="${base}/resources/statics/js/jquery-1.10.2.min.js"></script>
<c:import url="../commons/common-meta.jsp" />
<c:import url="../commons/common-css-ui.jsp" />
<c:import url="../commons/common-script-ui.jsp" />
</head>
<body style="overflow-y: none">
<div id="search" class="easyui-panel" style="padding: 2px; width: 100%;">
<a class="easyui-linkbutton query" id="query" iconCls="icon-search" onclick="queryData()">查询</a>
<a class="easyui-linkbutton" id="deleteAdivce" iconCls="icon-remove">删除</a>

<a class="expand-collapse easyui-linkbutton" data-href="#query-form" data-tbl="#list_data" style="float: right;" iconCls="accordion-collapse"></a>
</div>
	<div class="easyui-panel"  style="width: 100%;" id="query-form">
		<form class="query-form">
			<div class="sfwl-panel-context" style="float: left">
			   <div class="sfwl-row1 sfwl-col5">
			   		<div class="sfwl-form-field">
			            <label class="sfwl-label">手机号码</label>
						<div class="sfwl-control" style="width: 100px;">
						       <input type="text" class="easyui-textbox" id="phone" name="phone" />
						</div>
			       </div>
			       
			       	<div class="sfwl-form-field">
			            <label class="sfwl-label">运单号</label>
						<div class="sfwl-control" style="width: 100px;">
						  	<input type="text" class="easyui-textbox" id="waybillNo" name="waybillNo" />
						</div>
			       </div>
			       
			       	<div class="sfwl-form-field">
			            <label class="sfwl-label">回复状态</label>
						<div class="sfwl-control" style="width: 100px;">
						  	<select id="statusType" class="easyui-combobox">
								<option value="">全部</option>
								<option value="0">未回复</option>
								<option value="1">已回复</option>
							</select>
						</div>
			       </div>
			       
			       <!-- <div class="sfwl-form-field">
			            <label class="sfwl-label">首页显示</label>
						<div class="sfwl-control" style="width: 100px;">
						  	<select id="websiteShow" class="easyui-combobox">
								<option value="">全部</option>
								<option value="0">正常</option>
								<option value="1">关闭</option>
							</select>
						</div>
			       </div> -->
	          </div>
         </div>
     </form>
 </div>
 <!-- tblDiv css self-adaption At the bottom in function -->
 <div id="tblDiv">
 	<table id="list_data"></table> 
 </div>
 <!-- show table -->
<div id="opreateHtml" class="easyui-window" title=""  style="width:600px; height:200px;text-align:center; background: #fafafa;">
  <div class="easyui-layout" fit="true">
    <div region="center" border="false" style="background:#fff;border:1px solid #ccc;">
      <form id="complaintAdviceTemplate" method="post">
            <input type="hidden" id="hiddenId" value=""/>
            <table>
            <tr>
            <td valign="top">
	            <table cellpadding="5">
	                <tr>
	                    <td>客户姓名:</td>
	                    <td><input class="easyui-textbox" id="username" name="username" readonly="readonly" data-options="required:true" style="width:100px"></input></td>
	                </tr>
	                <tr>
	                    <td>投诉建议:</td>
	                    <td><input class="easyui-textbox" id="problemDescription" name="problemDescription" readonly="readonly" data-options="multiline:true" style="height:135px;width:400px;"></input></td>
	                </tr>
	                <tr>
	                    <td>客服回复:</td>
	                    <td><input class="easyui-textbox" id="customerReply" name="customerReply" data-options="multiline:true" style="height:130px;width:400px"></input></td>
	                </tr>
	            </table>
            </td>
            </tr>
            </table>
        </form>
    </div>
  <div region="south" border="false" style="text-align:center;height:30px;line-height:30px;">
      <a href="javascript:void(0)" class="easyui-linkbutton" id="cSave" >保存</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#opreateHtml').window('close');">取消</a>
  </div>
</div>
</div>
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
   	var param={};//The ginseng parameters
   	param.q_str_mobilePhone=$("#phone").val();     
   	param.q_str_singleNumber=$("#waybillNo").val();
   	param.q_str_customerReplyType=$("#statusType").combobox("getValue");
   	var opts={};
	ym.initPage("${base}/complaintAdvice/getAllComplaintAdvice",opts,param,'#list_data');
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
		sortName:'createTime',
		sortOrder:'desc',
	 	columns : [ [
	 	  {
			title : "<b>问题类型</b>",
			field : "problemTypes",
			align : 'center',
			width : 60,
			formatter:function(data){
				if(data==1){
					return "咨询";
				}else if(data==2){
					return "建议";
				}else if(data==3){
					return "投诉";
				}
			}
		},{
			title : "<b>主键</b>",
			field : "id",
			width : 80,
			hidden:'hidden',
			align : 'center',
		},{
			title : "<b>客户姓名</b>",
			field : "name",
			width : 80,
			align : 'center',
		}, {
			title : "<b>手机号</b>",
			field : "mobilePhone",
			align : 'center',
			width : 80,
		},{
			title : "<b>运单号</b>",
			field : "singleNumber",
			align : 'center',
			width : 100,
		}, {
			title : "<b>邮箱</b>",
			field : "email",
			align : 'center',
			width : 100
		}, {
			title : "<b>问题描述</b>",
			field : "problemDescription",
			align : 'center',
			width : 200
		},{
			title : "<b>客户回复</b>",
			field : "customerReply",
			align : 'center',
			width : 200
		}, {
			title : "<b>操作用户</b>",
			field : "updateUser",
			align : 'center',
			width : 80,
		}, {
			title : "<b>创建时间</b>",
			field : "createTime",
			align : 'center',
			sortable:true,
			width : 100,
			formatter : function(date){
	        	return ym.formatTime(date);
			}
		},{
			title : "<b>回复时间</b>",
			field : "updateTime",
			align : 'center',
			width : 100,
			formatter : function(date){
	        	return ym.formatTime(date);
			}
		},{
			title : "<b>首页是否显示</b>",
			field : "status",
			align : 'center',
			width : 80,
			formatter:function(data){
				if(data==1){
					return "<font color='red'>正常</font>";
				}else{
					return "<font color='blue'>已关闭</font>";
				}
			}
		},{
			title : "<b>操作</b>",
			field : "opt",
			align : 'center',
			width : 80,
			formatter:function(value,data,index){
				//edit & delete  String concatenation
				return "<a href='#' onclick='jvascript:show(\""+data.id+"\")'>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='jvascript:deleteComplaint(\""+data.id+"\")'>关闭</a>"; 
			}
		}] ],
	});
}
//echo
function show(id){
	  $('#hiddenId').val(id);// assignment give hiddenId
	  var iconStr="icon-edit";
	  if(id!=null){
	     $.post(
	    		"${base}/complaintAdvice/echoProblem?id="+id, 
	        {}, 
	        function(data){
	        	data=data.data;
				name=data.name;
				problemDescription=data.problemDescription;
				customerReply=data.customerReply;
				$("#username").textbox('setValue',name);//customer name
				$("#problemDescription").textbox('setValue',problemDescription);// complaint and advice
				$("#customerReply").textbox('setValue',customerReply);//The response of the service
	        },"json");
	  }
	  
	  //initialize window 
	  $('#opreateHtml').window({
	    title:'客服回复',
	    iconCls:iconStr,
	    width:600,
	    height:400,
	    left:200,
	    modal: true,
	    shadow: true,
	    collapsible:false,
	    minimizable:false,
	    maximizable:false,
	  });
	  $('#opreateHtml').window('move',{top:100});
	  $('#opreateHtml').window('open');
	}

//delete compaint and advice
function deleteComplaint(id){
	$.messager.confirm("提示","您确定把该条信息从首页上关闭吗！",function(data){
		if(!data){
			return;
		}
		 $.ajax({
			type:"POST",
			url:"${base}/complaintAdvice/deleteComplaintAdvice",
			data:{'id':id},
			success:function(data){
				data = data.data;
				if(data == 1){
					$("#list_data").datagrid('reload');
				}else{
					alert("删除失败");
				}
			}
		}); 
	});
}
//save-edit
$("#cSave").click(function(){
	$.ajax({
		type:"POST",
		url:"${base}/complaintAdvice/customerReplySave",
		data:{ 
			"id":$('#hiddenId').val(),
			"problemTypes":$('#problemTypes').val(),
			"name":$('#name').val(),
			"mobilePhone":$('#mobilePhone').val(),
			"email":$('#email').val(),
			"singleNumber":$('#singleNumber').val(),
			"problemDescription":$('#problemDescription').val(),
			"customerReply":$('#customerReply').val(),
			"updateUser":$('#updateUser').val(),
			"status":$('#status').val(),
		},
		success: function(data) {
			data = data.data;
			if(data==1){
				$('#opreateHtml').window('close');
				$("#list_data").datagrid('reload');
			}else{
				 alert('回复失败!');
				}
			}
		});
	});
//delete	
 $("#deleteAdivce").click(function(){
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
			url:"${base}/complaintAdvice/deleteAdice",
			data:{
				"id":id,
			},
			success:function(data){
				data = data.data;
				if(data==1){
					$("#list_data").datagrid('reload');
				}else{
					alert("删除失败");
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