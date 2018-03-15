$(document).ready(function() {
var columns = [[{
		field : 'appKey',
		title : '应用编码'
	}, {
		field : 'appName',
		title : '应用名称'
	}, {
		field : 'version',
		title : '版本号'
	}, {
		field : 'versionName',
		title : '版本名称'
	}, {
		field : 'forceUpdate',
		title : '强制更新',
		formatter : function(val){
			if(val=="1"){
				return "是";
			}else{
				return "否";
			}
        	
		}
	}, {
		field : 'releaseTime',
		title : '发布时间',
		formatter : function(date){
        	return aili.formatTime(date);
		}
	}, {
		field : 'filePath',
		title : '文件路径'
	}, {
		field : 'downloadUrl',
		title : '下载链接'
	}, {
		field : 'createUser',
		title : '创建人'
	},{
		field : 'createTime',
		title : '创建时间',
		formatter : function(date){
        	return aili.formatTime(date);
		}
	}, {
		field : 'updateUser',
		title : '更新人'
	}, {
		field : 'updateTime',
		title : '更新时间',
		formatter : function(date){
        	return aili.formatTime(date);
		}
	},{
		field : 'id',
		title : '操作列',
		formatter : function(id,obj){
			var toolmenu =  
				'<div class="btn-group btn-group-xs"> '+
				'<button class="upload" data-pid="'+obj.id+'">上传应用</button>'+
				'<button class="copy" data-pid="'+obj.id+'">升级</button>'+
				'<button class="update" data-pid="'+obj.id+'">修改</button>'+
	       		'<button class="delete" data-pid="'+obj.id+'">删除</button>'+
	       		'</div>';
			return toolmenu;
		}
	}]];
    $('#dg').datagrid({columns:columns});
    //查询方法
    function queryData(){
	    var params = $('.query-form').getFormObj();
	    aili.initPage(base + "/client/getPagination",{},params,'#dg',columns);	
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
		aili.sendPost(base+'/client/get/'+id,null,{successHandler:function(data, textStatus, jqXHR){
			if(data !=null){
				if(data.success){
					$("#edit-form").form("load",data.data);
					$("#edit-form").find(".appKey").textbox('readonly');
					$("#edit-form").find("#radio").radio("check",data.data.forceUpdate);
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
				}else{
					$.messager.alert("提示信息",data.msg);
				}
			}
		}});
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
		aili.sendPost(base + "/client/edit",form,{successHandler:function(data, textStatus, jqXHR){
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