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
<body class="easyui-layout">
	<table id="list_data" style="text-align: center;" data-options="url:'${base}/ddwl/noticeCapacity', method:'get'"/> 
	
<script type="text/javascript">
//auth zb
//time 12.30 15:37
//显示活动状态
/* function fmt_running(val){
	return val=='0'||val==null||val.trim()==''?'已完成':'进行中';
} */
//
var noticeTyp='${type}';
$(function(){
	queryData();
})
//查询数据
function queryData(){
   	var opts={};
   	param={"q_str_noticeType":noticeTyp};
	ym.initPage("${base}/notice/getAllNotice",opts,param,'#list_data')
   };
//加载表


var columns;
if(noticeTyp==1){
	columns=[ [{
		title : "<b>新闻标题</b>",
		field : "noticeTitle",
		align : 'center',
		width : 60
	},{
		title : "<b>id</b>",
		field : "id",
		align : 'center',
		hidden:'hidden',
		width : 60
	},{
		title : "<b>新闻简介</b>",
		field : "noticeSummary",
		align : 'center',
		hidden:'hidden',
		width : 60
	}
	,{
		title : "<b>类型</b>",
		field : "noticeType",
		align : 'center',
		width : 60,
		formatter:function(data){
			
				return "公司新闻";
			/*if(data==1){
			}else if(data==2){
				return "通知公告";
			}else if(data==3){
				return "行业动态";
			}*/
		} 
	}
	,{
		title : "<b>图片</b>",
		field : "noticeImg",
		align : 'center',
		hidden:'hidden',
		width : 60
	}
	,{
		title : "<b>内容</b>",
		field : "noticeContent",
		align : 'center',
		hidden:'hidden',
		width : 60
	}
	,{
		title : "<b>状态</b>",
		field : "noticeStatus",
		align : 'center',
		width : 60,
		formatter:function(data){
			if(data==0){
				return "停用";
			}else{
				return "启用";
			}
		}
	},{
		title : "<b>创建时间</b>",
		field : "createDate",
		align : 'center',
		width : 60,
		formatter : function(date){
        	return ym.formatTime(date);
		}
	},{
		title : "<b>发布时间</b>",
		field : "releaseTime",
		align : 'center',
		width : 60,
		sortable:true,
		formatter : function(date){
        	return ym.formatDate(date);
		}
	},{
		title : "<b>创建用户</b>",
		field : "createUser",
		align : 'center',
		hidden:'hidden',
		width : 60
	},{
		title : "<b>修改时间</b>",
		field : "changeDate",
		align : 'center',
		width : 60,
		formatter : function(date){
        	return ym.formatTime(date);
		}
	},{
		title : "<b>操作用户</b>",
		field : "changeUser",
		align : 'center',
		width : 60
	}
	] ]
}else if(noticeTyp==2){
	columns=[ [{
		title : "<b>通知公告标题</b>",
		field : "noticeTitle",
		align : 'center',
		width : 60
	},{
		title : "<b>id</b>",
		field : "id",
		align : 'center',
		hidden:'hidden',
		width : 60
	},{
		title : "<b>通知公告简介</b>",
		field : "noticeSummary",
		align : 'center',
		hidden:'hidden',
		width : 60
	}
	,{
		title : "<b>类型</b>",
		field : "noticeType",
		align : 'center',
		width : 60,
		formatter:function(data){
			return "通知公告";
		}
	}
	,{
		title : "<b>图片</b>",
		field : "noticeImg",
		align : 'center',
		hidden:'hidden',
		width : 60
	}
	,{
		title : "<b>内容</b>",
		field : "noticeContent",
		align : 'center',
		hidden:'hidden',
		width : 60
	}
	,{
		title : "<b>状态</b>",
		field : "noticeStatus",
		align : 'center',
		width : 60,
		formatter:function(data){
			if(data==0){
				return "停用";
			}else{
				return "启用";
			}
		}
	},{
		title : "<b>创建时间</b>",
		field : "createDate",
		align : 'center',
		width : 60,
		formatter : function(date){
        	return ym.formatTime(date);
		}
	},{
		title : "<b>发布时间</b>",
		field : "releaseTime",
		align : 'center',
		width : 60,
		formatter : function(date){
        	return ym.formatDate(date);
		}
	},{
		title : "<b>创建用户</b>",
		field : "createUser",
		align : 'center',
		hidden:'hidden',
		width : 60
	},{
		title : "<b>修改时间</b>",
		field : "changeDate",
		align : 'center',
		width : 60,
		formatter : function(date){
        	return ym.formatTime(date);
		}
	},{
		title : "<b>操作用户</b>",
		field : "changeUser",
		align : 'center',
		width : 60
	}
	] ]
}else if(noticeTyp==3){
	columns=[ [{
		title : "<b>行业动态标题</b>",
		field : "noticeTitle",
		align : 'center',
		width : 60
	},{
		title : "<b>id</b>",
		field : "id",
		align : 'center',
		hidden:'hidden',
		width : 60
	},{
		title : "<b>行业动态简介</b>",
		field : "noticeSummary",
		align : 'center',
		hidden:'hidden',
		width : 60
	}
	,{
		title : "<b>类型</b>",
		field : "noticeType",
		align : 'center',
		width : 60,
		formatter:function(data){
			return "行业动态";
		}
	}
	,{
		title : "<b>图片</b>",
		field : "noticeImg",
		align : 'center',
		hidden:'hidden',
		width : 60
	}
	,{
		title : "<b>内容</b>",
		field : "noticeContent",
		align : 'center',
		hidden:'hidden',
		width : 60
	}
	,{
		title : "<b>状态</b>",
		field : "noticeStatus",
		align : 'center',
		width : 60,
		formatter:function(data){
			if(data==0){
				return "停用";
			}else{
				return "启用";
			}
		}
	},{
		title : "<b>创建时间</b>",
		field : "createDate",
		align : 'center',
		width : 60,
		formatter : function(date){
        	return ym.formatTime(date);
		}
	},{
		title : "<b>发布时间</b>",
		field : "releaseTime",
		align : 'center',
		width : 60,
		formatter : function(date){
        	return ym.formatDate(date);
		}
	},{
		title : "<b>创建用户</b>",
		field : "createUser",
		align : 'center',
		hidden:'hidden',
		width : 60
	},{
		title : "<b>修改时间</b>",
		field : "changeDate",
		align : 'center',
		width : 60,
		formatter : function(date){
        	return ym.formatTime(date);
		}
	},{
		title : "<b>操作用户</b>",
		field : "changeUser",
		align : 'center',
		width : 60
	}
	] ]
}else if(noticeTyp==4){
	columns=[ [{
		title : "<b>文化生活标题</b>",
		field : "noticeTitle",
		align : 'center',
		width : 60
	},{
		title : "<b>id</b>",
		field : "id",
		align : 'center',
		hidden:'hidden',
		width : 60
	},{
		title : "<b>文化生活简介</b>",
		field : "noticeSummary",
		align : 'center',
		width : 60,
		hidden:'hidden'
	}
	,{
		title : "<b>类型</b>",
		field : "noticeType",
		align : 'center',
		width : 60,
		formatter:function(data){
			return "文化生活";
		}
	}
	,{
		title : "<b>图片</b>",
		field : "noticeImg",
		align : 'center',
		hidden:'hidden',
		width : 60
	}
	,{
		title : "<b>内容</b>",
		field : "noticeContent",
		align : 'center',
		hidden:'hidden',
		width : 60
	}
	,{
		title : "<b>状态</b>",
		field : "noticeStatus",
		align : 'center',
		width : 60,
		formatter:function(data){
			if(data==0){
				return "停用";
			}else{
				return "启用";
			}
		}
	},{
		title : "<b>创建时间</b>",
		field : "createDate",
		align : 'center',
		width : 60,
		formatter : function(date){
        	return ym.formatTime(date);
		}
	},{
		title : "<b>发布时间</b>",
		field : "releaseTime",
		align : 'center',
		width : 60,
		formatter : function(date){
        	return ym.formatDate(date);
		}
	},{
		title : "<b>创建用户</b>",
		field : "createUser",
		align : 'center',
		hidden:'hidden',
		width : 60
	},{
		title : "<b>修改时间</b>",
		field : "changeDate",
		align : 'center',
		width : 60,
		formatter : function(date){
        	return ym.formatTime(date);
		}
	},{
		title : "<b>操作用户</b>",
		field : "changeUser",
		align : 'center',
		width : 60
	}
	] ]
}else if(noticeTyp==5){
	columns=[ [{
		title : "<b>优秀员工标题</b>",
		field : "noticeTitle",
		align : 'center',
		width : 60
	},{
		title : "<b>id</b>",
		field : "id",
		align : 'center',
		hidden:'hidden',
		width : 60
	},{
		title : "<b>优秀员工简介</b>",
		field : "noticeSummary",
		align : 'center',
		width : 60,
		hidden:'hidden'
	}
	,{
		title : "<b>类型</b>",
		field : "noticeType",
		align : 'center',
		width : 60,
		formatter:function(data){
			return "优秀员工";
		}
	}
	,{
		title : "<b>图片</b>",
		field : "noticeImg",
		align : 'center',
		hidden:'hidden',
		width : 60
	}
	,{
		title : "<b>内容</b>",
		field : "noticeContent",
		align : 'center',
		hidden:'hidden',
		width : 60
	}
	,{
		title : "<b>状态</b>",
		field : "noticeStatus",
		align : 'center',
		width : 60,
		formatter:function(data){
			if(data==0){
				return "停用";
			}else{
				return "启用";
			}
		}
	},{
		title : "<b>创建时间</b>",
		field : "createDate",
		align : 'center',
		width : 60,
		formatter : function(date){
        	return ym.formatTime(date);
		}
	},{
		title : "<b>发布时间</b>",
		field : "releaseTime",
		align : 'center',
		width : 60,
		formatter : function(date){
        	return ym.formatDate(date);
		}
	},{
		title : "<b>创建用户</b>",
		field : "createUser",
		align : 'center',
		hidden:'hidden',
		width : 60
	},{
		title : "<b>修改时间</b>",
		field : "changeDate",
		align : 'center',
		width : 60,
		formatter : function(date){
        	return ym.formatTime(date);
		}
	},{
		title : "<b>操作用户</b>",
		field : "changeUser",
		align : 'center',
		width : 60
	}
	] ]
}
$('#list_data').datagrid({ 
    iconCls:'icon-edit',//图标 
    border: false, //1
    fit: true,//自动大小 1
    remoteSort:false,  //1
    singleSelect:true,//1
    pagination:true,//分页控件1
    pageSize: 20,
    pageList: [20,30,40,50],
    fitColumns:true,//1
    nowrap:false,//1
    
    columns : columns,
    rownumbers:true,//行号 1
    //双击事件
    onDblClickRow:function(){
    	//加载一行
    	editNotice();
    },
    toolbar: [{ 
        text: '添加', 
        iconCls: 'icon-add', 
        handler: function() { 
        	var iHeight = 600;
            var iWidth = 1085;
            var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
            var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
            var win = window.open("${base}/ddwl/noticeCapacity?noticeType="+noticeTyp, "_", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
        	} 
        } , '-', { 
        text: '修改', 
        iconCls: 'icon-edit', 
        handler: editNotice
	}, '-',{ 
        text: '停用', 
        iconCls: 'icon-remove', 
        handler: function(){ 
        	var allCheck=$("#list_data").datagrid('getSelected');
        	if (allCheck==null) {
	            $.messager.alert("提示", "请选择要停用的行！", "info");
	            return;
	        }else{
	        	if(allCheck.noticeStatus==0){
	        		$.messager.alert("友情提示","停用失败，该数据已经停用","error");
	        		return;
	        	}
	        	var noticeId = allCheck.id;
	            
         		$.messager.confirm('提示', '是否停用选中数据?', function (r) {
            		if (!r) {
                 		return;
                  	}
            	 	$.ajax({//异步提交删除
                		type: "POST",
                		url: "${base}/notice/deleteStatusByid",
                		data: {'noticeId':noticeId},
                 		success: function (result) {
                 			if(result.data.success){
                 				$("#list_data").datagrid('reload');
                 			}
                 			$.messager.alert("友情提示",result.data.msg,"info");
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
	        	if(allCheck.noticeStatus==1){
        		$.messager.alert("友情提示","启动失败，该数据已经启动","error");
        		return;
        	}
	        	var noticeId = allCheck.id;
	            //活动状态，原来是1，改为0，原来是0改为1
	            var noticeRunning = "";
	            if(allCheck.noticeRunning==''||allCheck.noticeRunning==null||allCheck.noticeRunning=='0'){
	            	noticeRunning = '1';
	            }else{
	            	noticeRunning = '0';
	            }
         		$.messager.confirm('提示', '是否将选中数据修改成启用状态?', function (r) {
            		if (!r) {
                 		return;
                  	}
                	$.ajax({//异步提交删除
                    	type: "POST",
                    	url: "${base}/notice/updateStatusByid",
                    	data: {'noticeId':noticeId},
                     	success: function (data) {
                     		if(data.data.success){
                     			$("#list_data").datagrid('reload');
                     		}
                     		$.messager.alert("友情提示",data.data.msg,"info");
                     	}
              		  }); 
        		})
	        }
        }
    },"-",{
    	text: '删除', 
        iconCls: 'icon-remove', 
        handler: function(){
        	//请选择一行
			var allCheck=$("#list_data").datagrid('getSelected');
        	
			if (allCheck==null) {
	            $.messager.alert("提示", "请选择要删除的行！", "info");
	            return;
	        }
			$.messager.confirm("提示","您确定要删除该行数据吗！",function(data){
				if(!data){
					return;
				}
				var noticeId = allCheck.id; 
				 $.ajax({
					type:"POST",
					url:"${base}/notice/deleteNoticeById",
					data:{'noticeId':noticeId},
					success:function(data){
						if(data.data.success){
							$("#list_data").datagrid('reload');
						}else{
							$.messager.alert("友情提示","删除失败","info");
						}
					}
				}); 
			});
		}
    }] 
});
function editNotice() { 
	//请选择一行
	var allCheck=$("#list_data").datagrid('getSelected');
	if (allCheck==null) {
        $.messager.alert("提示", "请选择要修改的行！", "info");
        return;
    }	
	var iHeight = 600;
    var iWidth = 1085;
    var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
    //toolbar  第一个参数表示去Controller中的ddwl中找noticeCapacity 是否显示工具条,menubar、scrollbars是否显示菜单和滚动栏,resizable是否允许改变窗体大小,localion是否显示地址栏,status是否显示状态栏内的信息,alwaysRaised窗口浮动于浏览器之上,depended是否和父窗口同时关闭
    var win = window.open("${base}/ddwl/noticeCapacity?noticeId="+allCheck.id+"&noticeType="+allCheck.noticeType, "弹出窗口", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
} 
</script>
</body>
