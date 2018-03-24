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
	init();
	queryData();
})
//query All
function queryData(){
	var opts={};
	var param = {};
    ym.initPage("${base}/imgManage/getAllImgManage",opts,param,'#list_data');	
    
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
	 	columns : [[{
	 		title : "<b>主键</b>",
			field : "id",
			hidden:'hidden'
		},{
			title : "<b>类型</b>",
			field : "type",
			align : 'center',
			width : 100,
			hidden:'hidden'
		},{
			title : "<b>首页展示顺序</b>",
			field : "sort",
			width : 100,
			align : 'center',
			formatter:function(data){
				return data+"号核心产品位置";
			}
		},{
			title : "<b>主标题</b>",
			field : "mainTitle",
			align : 'center',
			width : 100  
		},{
			title : "<b>子标题</b>",
			field : "subTitle",
			align : 'center',
			width : 100
		},{
			title : "<b>图片地址url</b>",
			field : "imgUrl",
			align : 'center',
			width : 150,
			hidden:'hidden'
		},{
			title : "<b>默认图片</b>",
			field : "defaultImg",
			align : 'center',
			width : 200   
		},{
			title : "<b>鼠标放置动图</b>",
			field : "clickImg",
			align : 'center',
			width : 200   
		},{
			title : "<b>状态</b>",
			field : "status",
			align : 'center',
			width : 30,
			formatter:function(data){
				if(data==0){
					return "停用";
				}else if(data==1){
					return "启用";
				}
			}
		},{
			title : "<b>创建时间</b>",
			field : "createDate",
			align : 'center',
			width : 80,
			formatter : function(date){
	        	return ym.formatTime(date);
			}
		},{
			title : "<b>创建用户</b>",
			field : "createUser",
			align : 'center',
			width : 80,
		},{
			title : "<b>修改时间</b>",
			field : "changeDate",
			align : 'center',
			width : 80,
			formatter : function(date){
	        	return ym.formatTime(date);
			}
		},{
			title : "<b>操作用户</b>",
			field : "changeUser",
			align : 'center',
			width : 80,
		}] ],
		 toolbar: [/* { 
		        text: '添加', 
		        iconCls: 'icon-add', 
		        handler: function(){
		        	var iHeight = 600;
		            var iWidth = 400;
		            var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
		            var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
			            var win = window.open("${base}/ddwl/imgManage_edit",'newwindow','height='+iHeight+', width='+iWidth+', top='+iTop+', left='+iLeft+', toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
	        	}
		    }, '-', */ { 
		        text: '修改', 
		        iconCls: 'icon-edit', 
		        handler: function(){
		        	//请选择一行
					var allCheck=$("#list_data").datagrid('getSelected');
		        	if (allCheck==null) {
			            $.messager.alert("提示", "请选择要修改的行！", "info");
			            return;
			        }
		        	var status = allCheck.status;
		        	var iHeight = 600;
		            var iWidth = 400;
		            var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
		            var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
		            //toolbar  第一个参数表示去Controller中的ddwl中找noticeCapacity 是否显示工具条,menubar、scrollbars是否显示菜单和滚动栏,resizable是否允许改变窗体大小,localion是否显示地址栏,status是否显示状态栏内的信息,alwaysRaised窗口浮动于浏览器之上,depended是否和父窗口同时关闭
		            var win = window.open("${base}/ddwl/imgManage_edit?status="+status+"&imgId="+allCheck.id+"",' 弹出窗口', "width=390, height=388,top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
		        }
		    }, /* '-',{ 
		        text: '删除', 
		        iconCls: 'icon-remove', 
		        handler: function(){
		        	//请选择一行
					var allCheck=$("#list_data").datagrid('getSelected');
		        	var id = allCheck.id;
					if (allCheck==null) {
			            $.messager.alert("提示", "请选择要删除的行！", "info");
			            return;
			        }
					$.messager.confirm("提示","您确定要删除该行数据吗！",function(data){
						if(!data){
							return;
						}
						 $.ajax({
							type:"POST",
							url:"${base}/imgManage/deleteById",
							data:{"id":id},
							success:function(data){
								if(data.data=1){
									$("#list_data").datagrid('reload');
								}else{
									$.messager.alert("友情提示","删除失败","info");
								}
							}
						}); 
					});
				}
		     }, */'-',{ 
		         text: '停用', 
		         iconCls: 'icon-remove', 
		         handler: function(){ 
		         	var allCheck=$("#list_data").datagrid('getSelected');
		         	if (allCheck==null) {
		 	            $.messager.alert("提示", "请选择要停用的行！", "info");
		 	            return;
		 	        }else{
		 	        	if(allCheck.status==0){
		 	        		$.messager.alert("友情提示","停用失败，该数据已经停用","error");
		 	        		return;
		 	        	}
		 	        	var id = allCheck.id;
		 	            
		          		$.messager.confirm('提示', '是否停用选中数据?', function (r) {
		             		if (!r) {
		                  		return;
		                   	}
		             	 	$.ajax({
		                 		type: "POST",
		                 		url: "${base}/imgManage/blockStatusByid",
		                 		data: {'id':id},
		                  		success: function (data) {
		                  			if(data.data==1){
		                  				$("#list_data").datagrid('reload');
		                  			}else{
		                  			$.messager.alert("友情提示","停用失败","info");
		                  			}
		                  		}
		           		  	}); 
		         		})
		 	        }
		         }
		     }, '-',{
		     	//增加启动状态
		         text: '启动', 
		         iconCls: 'icon-remove', 
		         handler: function(){ 
		         	var allCheck=$("#list_data").datagrid('getSelected');
		         	if (allCheck==null) {
		 	            $.messager.alert("提示", "请选择要修改的新闻状态！", "info");
		 	            return;
		 	        }else{
		 	        	if(allCheck.status==1){
		         		$.messager.alert("友情提示","启动失败，该数据已经启动","error");
		         		return;
		         	}
		 	        	var id = allCheck.id;
		          		$.messager.confirm('提示', '是否将选中数据修改成启用状态?', function (r) {
		             		if (!r) {
		                  		return;
		                   	}
		                 	$.ajax({
		                     	type: "POST",
		                     	url: "${base}/imgManage/updateStatusByid",
		                     	data: {'id':id},
		                      	success: function (data) {
		                      		if(data.data==1){
		                      			$("#list_data").datagrid('reload');
		                      		}else{
		                      		$.messager.alert("友情提示","启用失败","info");
		                      		}
		                      	}
		               		  }); 
		         		})
		 	        }
		         }
		     }],
	});
};
//让分页自适应让其在最下行
$(function(){
	var  tblh =  $(window).height() - $('#tblDiv').offset().top;
	$('#list_data').datagrid('resize', {
		//定义表格高度
		height : tblh
	});
});
</script>
</html>