$(document).ready(function() {
	var columns = [ {
		field : 'state',
		radio:true
	}, {
		field : 'roleName',
		title : '角色名称'
	}, {
		field : 'roleCode',
		title : '角色编码'
	}, {
		field : 'compCode',
		title : '角色公司'
	}, {
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
	       		'<a type="button" class="btn btn-default update" data-pid="'+obj.id+'"> <i class="fa fa-edit"></i>修改</a>'+
	       		'<a type="button" class="btn btn-default delete" data-pid="'+obj.id+'"> <i class="fa fa-edit"></i>复制</a>'+
	       		'<a type="button" class="btn btn-default delete" data-pid="'+obj.id+'"> <i class="fa fa-edit"></i>删除</a>'+
	       		'</div>';
			return toolmenu;
		}
	} ];
	
	queryData();
	
	$('.btn-search').on("click",function(){
		queryData();
	});
	//根据ID删除单挑数据	
	$('#table').on("click",".delete",function(){
		var id=$(this).data("pid");
		bootbox.confirm("确定要删除吗?", function(result) {
			if(result){
				aili.sendPost(base+'/user/deleteuser/'+id,null,{
		    		successHandler:function(data,textStatus, jqXHR){
		    			if(data.success){
		    				queryData();
		    				bootbox.alert(data.msg);
		    			}
		    		}});
			}
		});
	});
    
	
	//根据ID删除单挑数据	
	$('#table').on("click",".update",function(){
		var id=$(this).data("pid");
		aili.sendPost(base+'/role/getRoleByid/'+id,null,{
    		successHandler:function(data,textStatus, jqXHR){
    			if(data.success){
    				var  modal = $('#mailiodal');
    				aili.setFormObj("edit-form",data.data);
    				modal.find("select[name='compCode']").val(data.data.compCode).trigger("change");
    				modal.modal('show');
    			}
    	}});
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
    $("#table").on('dbl-click-row.bs.table', function (e, row) {
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
    })
    function queryData(){
		
    	aili.initPage(base + "/role/getRoleList", {
    		queryParams : function(params) {
    			 var formObj = $("#role-form").getFormObj();
    			for(var p in formObj) {
    				//属性名称 
	    			var name=p;
	    			//属性对应的值 
	    			var val=formObj[p];
	    			params[name]=val; 
    			} 
    			return params;
    		}
    	}, "#table", columns);
    }
    $(".save").on("click",function(){
		$('#edit-form').bootstrapValidator('validate');
		var validate = $('#edit-form').data('bootstrapValidator').isValid();
		if(!validate){
			return;
		}
		var param = $('#edit-form').getFormObj();
		aili.sendPost(base+"/role/editRole/",param, {
			successHandler : function(data, textStatus, jqXHR) {
				if (data != null) {
					if(data.success){
						$('#mailiodal').modal('hide');
						queryData();
					}
					layer.alert(data.msg);
				}
			}
		});
	});
    
    $(".save-pri").on("click",function(){
        var roleCode =  $("#table").bootstrapTable('getSelections')[0].roleCode;
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    	var nodes = zTree.getCheckedNodes(true);
    	if(nodes==null ||nodes.length==0){
    		layer.alert("请选择权限");
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
					layer.alert(data.msg);
				}
			}
		});
	});
    
    $("#table").on('check.bs.table', function (e, row) {
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

