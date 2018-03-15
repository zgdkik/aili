$(document).ready(function() {
var columns = [[{
		field : 'executionId', 
		title : 'executionId' 
		},{
		field : 'procinstId', 
		title : 'procinstId' 
		},{
		field : 'prodefId', 
		title : 'prodefId' 
		},{
		field : 'assignee', 
		title : '审核人' 
		},{
		field : 'userName', 
		title : '提交人' 
		},{
		field : 'recordId', 
		title : 'recordId' 
		},{
		field : 'opinion', 
		title : 'opinion' 
		},{
		field : 'definitionId', 
		title : 'definitionId' 
		},{
		field : 'instanceId', 
		title : 'instanceId' 
		},{
		field : 'createUser', 
		title : 'createUser' 
		},{
		field : 'updateUser', 
		title : 'updateUser' 
		},{
		field : 'updateTime', 
		title : 'updateTime' 
		},{
		field : 'createTime', 
		title : 'createTime' 
		},{
		field : 'id',
		title : '操作列',
		formatter : function(id,obj){
			var toolmenu =  
				'<div class="btn-group btn-group-xs"> '+
				'<button class="update" data-pid="'+obj.id+'">受理</button>'+
	       		'</div>';
			return toolmenu;
		}
	}]];
    $('#dg').datagrid({columns:columns});
    //查询方法
    function queryData(){
	    var params = $('.query-form').getFormObj();
	    aili.initPage(base + "/workflow/queryUsertask",{},params,'#dg',columns);	
    }
	//查询
	$('.query-btn').on("click",function(){
		queryData();
	});
	//新增
	$('.add-btn').click(function(){
    	$("#edit-form").form("reset");
    	$("#edit-form").find("#edit-id").val(null);
    	$("#edit-form").find(".appKey").textbox('readonly',false);
		$("#dlg").dialog({
			title: '数据维护',
			width: 300,
			height: 300,
			buttons: [{
                text: '保存',
                iconCls: 'icon-save',
                handler: function () {
                	save();
                }
            }]
		});	
    	  
    });
	//修改
	$("body").on("click",'.update',function(){
		var id=$(this).data("pid");
		$("#edit-form").find("#edit-id").val(id);
		$("#dlg").dialog({
			title: '数据维护',
			width: 300,
			height: 150,
			buttons: [{
                text: '保存',
                iconCls: 'icon-save',
                handler: function () {
                	save();
                }
            }]
		});	
		
//		aili.sendPost(base+'/client/get/'+id,null,{successHandler:function(data, textStatus, jqXHR){
//			if(data !=null){
//				if(data.success){
//			
//				}else{
//					$.messager.alert("提示信息",data.msg);
//				}
//			}
//		}});
	});
	
	//升级
	$("body").on("click",'.copy',function(){
		var id=$(this).data("pid");
		aili.sendPost(base+'/client/get/'+id,null,{successHandler:function(data, textStatus, jqXHR){
			if(data !=null){
				if(data.success){ 
					data.data.id=null;
					data.data.filePath=null; 
					$("#edit-form").form("load",data.data);
					$("#edit-form").find(".appKey").textbox("readonly");
					$("#dlg").dialog({
						title: '数据维护',
						resizable: true,
						width: 300,
						height: 300,
						model: true,
						buttons: [{
		                    text: '保存',
		                    iconCls: 'icon-save',
		                    handler: function () {
		                    	save();
		                    }
		                }]
					});	
				}else{
					$.messager.alert("提示信息",data.msg);
				}
			}
		}});
	});
	
	 $('#fileupload').filebox({    
		    buttonText: '选择文件'
	})

	//上传
	$("body").on("click",'.upload',function(){
		var id=$(this).data("pid");
		$("#edit-form-upload").find("#upload-edit-id").val(id);
		$("#dlg-upload").dialog({
			title: '上传应用',
			resizable: true,
			width: 450,
			height: 120,
			model: true,
			buttons: [{
                text: '上传',
                iconCls: 'icon-save',
                handler: function () {
                	 var id= $("#upload-edit-id").val(); 
                	 var data = {'id':id}
                	 $.ajaxFileUpload({
                         url: base+'/client/upload', 
                         type: 'post',
                         secureuri: false,
                         fileElementId: 'filebox_file_id_1',
                         dataType: 'json', 
                         data: data, 
                         success: function(data, status){  
                         	if(data !=null){
                				if(data.success){
                					$("#dg").datagrid("reload");
                					$("#dlg-upload").dialog('close');
                					$.messager.alert("提示信息",data.msg);
                				}else{
                					$.messager.alert("提示信息",data.msg);
                				}
                			}
                         },
                         error: function(data, status, e){ 
                        	 $.messager.alert("提示信息",status);
                         }
                     });
                }
            }]
		});	
	});
	
	queryData();
	//保存
	function save(){
		var validate=$("#edit-form").form('validate');
		if(!validate){
			return;
		}
		var form=$("#edit-form").getFormObj();
		aili.sendPost(base + "/workflow/handlUserTask",form,{successHandler:function(data, textStatus, jqXHR){
			if(data !=null){
				if(data.success){
					$("#dg").datagrid("reload");
					$('#dlg').dialog('close');
					$.messager.alert("提示信息",data.msg);
				}else{
					$.messager.alert("提示信息",data.msg);
				}
			}
		}});
	}
	//删除
	$('body').on("click",'.delete',function(){
		var id=$(this).data("pid");
		$.messager.confirm("提示信息","您确定要删除吗",function(data){
			if(data){
				aili.sendPost(base+ "/client/updateStatus/" +id,null,{successHandler:function(data, textStatus, jqXHR){
					if(data !=null){
						if(data.success){
							$('#dg').datagrid('reload');
							$.messager.alert("提示信息",data.msg);
						}else{
							$.messager.alert("提示信息",data.msg);
						}
					}
				}});
			}
		});
	}); 
	
});