$(document).ready(function() {
	var columns = [ [ {
		field : 'state',
		radio:true
	}, {
		field : 'roleName',
		title : '角色名称'
	}, {
		field : 'roleCode',
		title : '角色编码'
	}
//	, {
//		field : 'compCode',
//		title : '角色公司'
//	}
	, {
		field : 'roleDesc',
		title : '角色描述'
	}
	, 
	{
		field : 'createUser',
		title : '创建人'
	},{
		field : 'createTime',
		title : '创建时间',
		formatter : function(date){
        	return aili.formatDate(date);
		}
	}, {
		field : 'updateUser',
		title : '更新人'
	}, {
		field : 'updateTime',
		title : '更新时间',
		formatter : function(date){
        	return aili.formatDate(date);
		}
	}
	,{
		field : 'id',
		title : '操作列',
		formatter : function(id,obj){
			var menu ="";
			var toolmenu =  
				'<div class="btn-group btn-group-xs"> '+
	       		'<button class="update" data-pid="'+obj.id+'">修改</button>'+
	       		'<button class="delete" data-pid="'+obj.id+'">删除</button>'+
	       		//'<button class="copy" data-pid="'+obj.id+'">复制</button>'+
	       		'</div>';
			return toolmenu;
		}
	} ]];

    $('#dg').datagrid({columns:columns});
    //查询方法
    function queryData(){
	    var params = $('.query-form').getFormObj();
	    aili.initPage(base + "/role/getRoleList",{},params,'#dg',columns);	
    }
	//查询
	$('.query-btn').on("click",function(){
		queryData();
	});
	//新增
	$('.add-btn').click(function(){
    	$("#edit-form").form("reset");
    	$("#edit-form").find("#edit-id").val(null);
    	$("#edit-form").find('.roleCode').textbox('readonly',false);
		$("#dlg").dialog({
			title: '数据维护',
			width: 280,
			height: 200,
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
		aili.sendPost(base+'/role/getRoleByid/'+id,null,{successHandler:function(data, textStatus, jqXHR){
			if(data !=null){
				if(data.success){
					$("#edit-form").form("load",data.data);
					$("#edit-form").find(".roleCode").textbox('readonly');
					$("#dlg").dialog({
						title: '数据维护',
						width: 280,
						height: 200,
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
		var validate=$("#edit-form").form('validate');
		if(!validate){
			return;
		}
		var form=$("#edit-form").getFormObj();
		aili.sendPost(base + "/role/editRole/",form,{successHandler:function(data, textStatus, jqXHR){
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
				aili.sendPost(base+'/role/deleteRole/'+id,null,{
		    		successHandler:function(data,textStatus, jqXHR){
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
	
	$('#dg').datagrid({"onSelect":function(index,row){
		var roleCode = row.roleCode;
    	aili.sendPost(base+"/role/getRolePrivilege/"+roleCode,null, {
			successHandler : function(data, textStatus, jqXHR) {
				if (data != null) {
					if(data.success){
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						zTree.checkAllNodes(false);
						for (var i = 0; i < data.data.length; i++) {
							var node = zTree.getNodeByParam("id", data.data[i],null);
							if(node){
								zTree.checkNode(node); 
								zTree.expandNode(node, true, true, null, false);
							}
							
						}
					}
				}
			}
		});
	}});
	   $(".save-pri").on("click",function(){
		   if($('#dg').datagrid('getSelected')==null){
			   $.messager.alert("提示信息","请选择角色");
	    		return;
		   }
	        var roleCode =  $('#dg').datagrid('getSelected').roleCode;
	        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	    	var nodes = zTree.getCheckedNodes(true);
	    	if(nodes==null ||nodes.length==0){
	    		$.messager.alert("提示信息","请选择权限");
	    		return;
	    	}
	    	var privilegeCodes = "";
	    	for (var i = 0; i < nodes.length; i++) {
				var n = nodes[i];
				if(nodes.length==(i+1)){
					privilegeCodes  = privilegeCodes+n.id+"";
				}else{
					privilegeCodes  = privilegeCodes+n.id+",";
				}
			}
			var param = {'roleCode':roleCode,'privilegeCodes':privilegeCodes};
			aili.sendPost(base+"/role/saveRole/",param, {
				successHandler : function(data, textStatus, jqXHR) {
					if (data != null) {
						$.messager.alert("提示信息",data.msg);
					}
				}
			});
		});
	var setting = {
			data : {
				simpleData : {
					enable : true
				}
			}
		};
		setting.check = {
			enable : true
		}
		setting.callback = {
			onCheck : onCheck
		}
		$.fn.zTree.init($("#treeDemo"), setting,tree);
	    $('.addnew').on("click",function(){
	    	aili.sendPost(base+'/role/getTree/',null,{
	    		successHandler:function(data,textStatus, jqXHR){
	    			if(data.success){
	    				$('#mailiodal').modal('show');
	    				$.fn.zTree.init($("#treeDemo"), setting,data.data.tree);
	    			}
	    	}});
	    })
	
});
function onCheck(e, treeId, treeNode) {
	expandNode(treeNode);
}

function expandNode(treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	nodes = zTree.getCheckedNodes();
	var result = new Array();
	getAllChildrenNodes(treeNode,result);
	if(result.length>0){
		for (var i = 0; i < result.length; i++) {
			zTree.expandNode(result[i], true, true, null, false);
			zTree.checkNode(result[i], treeNode.checked);
		}
	}
}

function getAllChildrenNodes(treeNode,result){
	 result.push(treeNode);
    if (treeNode.isParent) {
      var childrenNodes = treeNode.children;
      if (childrenNodes) {
          for (var i = 0; i < childrenNodes.length; i++) {
              result.push(childrenNodes[i]);
              //getAllChildrenNodes(childrenNodes[i], result);
          }
      }
  }
  return result;
}