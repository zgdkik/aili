$(document).ready(function() {
var columns = [[{
		field : 'leavePerson', 
		title : '申请人' 
		},{
		field : 'startTime', 
		title : '开始时间' 
		},{
		field : 'endTime', 
		title : '结束时间' 
		},{
		field : 'leaveReasons', 
		title : '请假原因' 
		},{
		field : 'createUser', 
		title : '创建人' 
		},{
		field : 'createTime', 
		title : '创建时间' ,
		formatter:function(createTime){
			return aili.formatTime(createTime);
			}
		},{
		field : 'updateUser', 
		title : '修改人' 
		},{
		field : 'updateTime', 
		title : '修改时间' 
		},{
		field : 'status', 
		title : '状态' 
		},{
		field : 'id',
		title : '操作列',
		formatter : function(id,obj){
			var toolmenu =  
				'<div class="btn-group btn-group-xs"> '+
				'<button class="update" data-pid="'+obj.id+'">查看流程图</button>'+
	       		'</div>';
			return toolmenu;
		}
	}]];
	$('#fileupload').filebox({    
	    buttonText: '选择文件'
	})
    $('#dg').datagrid({columns:columns});
    //查询方法
    function queryData(){
	    var params = $('.query-form').getFormObj();
	    aili.initPage(base + "/leave/queryPage",{},params,'#dg',columns);	
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
			height: 350,
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
			height: 350,
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