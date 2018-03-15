$(document).ready(function() {
var columns = [[{
		field : 'name',
		title : '名称'
	}, {
		field : 'code',
		title : '编码'
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
				'<button class="update" data-pid="'+obj.id+'" data-pname="'+obj.name+'">修改</button>'+
	       		'<button class="delete" data-pid="'+obj.id+'">删除</button>'+
	       		'</div>';
			return toolmenu;
		}
	}]];
    $('#dg').datagrid({columns:columns});
    //查询方法
    function queryData(){
	    var params = $('.query-form').getFormObj();
	    aili.initPage(base + "/crud/pagination",{},params,'#dg',columns);	
    }
	//查询
	$('.query-btn').on("click",function(){
		queryData();
	});
	//新增
	$('.add-btn').click(function(){
		var name=$(this).data("pname");
		parent.addTab("新增动态CRUD", base+"/crud/edit", "icon icon-nav");// 增加tab
//		
//    	$("#edit-form").form("reset");
//    	$("#edit-form").find("#edit-id").val(null);
//    	$("#edit-form").find(".appKey").textbox('readonly',false);
//		$("#dlg").dialog({
//			title: '数据维护',
//			width: 650,
//			height: 500,
//			buttons: [{
//                text: '保存',
//                iconCls: 'icon-save',
//                handler: function () {
//                	save();
//                }
//            }]
//		});	
		
    	  
    });
	//修改
	$("body").on("click",'.update',function(){
		var id=$(this).data("pid");
		parent.addTab("修改动态CURD", base+"/crud/edit?id="+id, "icon icon-nav");// 增加tab
	});
	queryData();
	//保存
	function save(){
		var validate=$("#edit-form").form('validate');
		if(!validate){
			return;
		}
		var form=$("#edit-form").getFormObj();
		aili.sendPost(base + "/report/edit",form,{successHandler:function(data, textStatus, jqXHR){
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
				aili.sendPost(base+ "/crud/updateStatus/" +id,null,{successHandler:function(data, textStatus, jqXHR){
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

