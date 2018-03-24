$(document).ready(function() {
var columns = [[{
		field : 'sysKey',
		title : '系统参数关键字'
	}, {
		field : 'sysValue',
		title : '系统参数值',
	}, {
		field : 'createUser',
		title : '创建人'
	},{
		field : 'createTime',
		title : '创建时间',
		formatter : function(date){
        	return ym.formatDate(date);
		}
	}, {
		field : 'updateUser',
		title : '更新人'
	}, {
		field : 'updateTime',
		title : '更新时间',
		formatter : function(date){
        	return ym.formatDate(date);
		}
	},{
		field : 'id',
		title : '操作列',
		formatter : function(id,obj){
			var toolmenu =  
				'<div class="btn-group btn-group-xs"> '+
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
	    ym.initPage(base + "/common/getSystemParameterList",{},params,'#dg',columns);	
    }
	//查询
	$('.query-btn').on("click",function(){
		queryData();
	});
	//新增
	$('.add-btn').click(function(){
    	$("#company-form").form("reset");
		$("#dlg").dialog({
			title: '系统参数',
			resizable: true,
			width: 300,
			height: 150,
			model: true,
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
		ym.sendPost(base+'/common/showSystemParameterForm',{'id':id},{successHandler:function(data, textStatus, jqXHR){
			if(data !=null){
				if(data.success){ 
					$("#company-form").form("load",data.data);
					$("#dlg").dialog({
						title: '系统参数',
						resizable: true,
						width: 300,
						height: 150,
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
	queryData();
	//保存
	function save(){
		var validate=$("#company-form").getFormObj('validate');
		if(!validate){
			return;
		}
		var form=$("#company-form").getFormObj();
		ym.sendPost(base + "/common/addSystemParameter",form,{successHandler:function(data, textStatus, jqXHR){
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
				ym.sendPost(base+ "/common/deleteSystemParameter/" +id,null,{successHandler:function(data, textStatus, jqXHR){
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
	//拼装url参数
	function crateParam(param){
		var index = 0;
		var paramStr = "";
		for ( var p in param) {
			var name = p;
			var val = param[p];
			if (val != null && val != '') {
				if (index == 0) {
					paramStr = paramStr + "?" + name + "=" + val;
				}
				else {
					paramStr = paramStr + "&" + name + "=" + val;
				}
				index++;
			}
		}
		return paramStr;
	}
	
});