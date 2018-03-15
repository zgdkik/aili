// 显示右键菜单
function showRMenu(type, x, y) {
	$("#rMenu ul").show();
	if (type == "root") {
		$("#m_del").hide();
		$("#m_check").hide();
		$("#m_unCheck").hide();
	}
	$("#rMenu").css({
		"top" : y + "px",
		"left" : x + "px",
		"display" : "block"
	});
}
// 新增树节点
function add(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	isParent = e.isParent;
	id = e.id;
	pId = e.pId;
	name = e.name;
	icon = e.icon;
	nodes = zTree.getNodeByParam("id", pId);
	treeNode = zTree.addNodes(nodes, {
		id : id,
		pId : pId,
		icon : icon,
		open : true,
		isParent : isParent,
		name : name
	});
};

// 删除节点
function remove() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	nodes = zTree.getSelectedNodes();
	treeNode = nodes[0];
	zTree.removeNode(treeNode);
};

// 修改节点
function edit(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	name = e.name;
	id = e.id;
	pId = e.pId;
	nodes = zTree.getSelectedNodes();
	nodes2 = zTree.getNodeByParam("id", pId, null);
	treeNode = nodes[0];
	treeNode.name = name;
	treeNode.pId = pId;
	treeNode.open = true;
	// 先更新
	zTree.updateNode(treeNode);

};
function menuClick(event, treeId, treeNode, clickFlag) {
	aili.sendPost(base + "/privilege/getMenuInfo/" + treeNode.id, null, {
		successHandler : function(data, textStatus, jqXHR) {
			if (data != null) {
				aili.setFormObj("privilege-info",data.data);
				$('#privilege-info').find("select[name='appType']").val(data.data.appType).trigger("change");
				$('#privilege-info').find("select[name='moduleType']").val(data.data.moduleType).trigger("change");
			}
		}
	});

}

$(document).ready(function() {
	
	setting.callback = {onClick : menuClick};
	
	$.fn.zTree.init($("#treeDemo"), setting);
	$('#privilege-form').bootstrapValidator({
		fields : {
			appType : {
				validators : {
					notEmpty : {
						message : '系统类型不能为空'
					}
				}
			},
			moduleCode : {
				validators : {
					notEmpty : {
						message : '权限编码不能为空'
					}
				}
			},
			moduleType : {
				validators : {
					notEmpty : {
						message : '权限类型不能为空'
					}
				}
			},
			moduleName : {
				validators : {
					notEmpty : {
						message : '权限名称不能为空'
					}
				}
			},
			actionUrl : {
				validators : {
					notEmpty : {
						message : '权限URL不能为空'
					}
				}
			},
			sort : {
				validators : {
					notEmpty : {
						message : '显示顺序不能为空'
					}
				}
			}
		}
	});
	
	$(".addnew").on("click",function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes();
		if ( nodes.length == 0) {
			layer.alert("请先选择一个节点");
			return;
		}
		var nodeId = nodes[0].id;
		$('#mailiodal').find('input[name="id"]').val(null);
		$('#mailiodal').find('input[name="parentCode"]').val(nodeId);
		$('#mailiodal').find('input[name="privilegeCode"]').removeAttr("readonly");
		$('#mailiodal').modal('show');
		
	});
	
	$(".update").on("click",function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes();
		if ( nodes.length == 0) {
			layer.alert("请先选择一个节点");
			return;
		}
		var nodeId = nodes[0].id;
		aili.sendPost(base + "/privilege/getMenuInfo/" + nodeId, null, {
			successHandler : function(data, textStatus, jqXHR) {
				if (data != null) {
					$('#mailiodal').find('input[name="privilegeCode"]').attr("readonly","readonly");
					aili.setFormObj("privilege-form",data.data);
					$('#mailiodal').find("select[name='appType']").val(data.data.appType).trigger("change");
					$('#mailiodal').find("select[name='moduleType']").val(data.data.moduleType).trigger("change");
					$('#mailiodal').modal('show');
				}
			}
		});
	});
	
	$(".remove").on("click",function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes();
		if ( nodes.length == 0) {
			layer.alert("请先选择一个节点");
			return;
		}
		var nodeId = nodes[0].id;
		bootbox.confirm("确定要删除吗?", function(result) {
			if(result){
				aili.sendPost(base + "/privilege/deleteMenu/" + nodeId,null, {
					successHandler : function(data, textStatus, jqXHR) {
						if (data != null) {
							if(data.success){
								remove();
							}
							layer.alert(data.msg);
						}
					}
				});
			}
		});
		
	});
	
	$(".save").on("click",function(){
		$('#privilege-form').bootstrapValidator('validate');
		var validate = $('#privilege-form').data('bootstrapValidator').isValid();
		if(!validate){
			return;
		}
		var param = $('#privilege-form').getFormObj();
		var id  =$('#privilege-form').find("input[name='id']").val();
		aili.sendPost(base+"/privilege/editMenu/",param, {
			successHandler : function(data, textStatus, jqXHR) {
				if (data != null) {
					if(data.success){
						$('#mailiodal').modal('hide');
						var node = {id:param.privilegeCode,pId:param.parentCode,name:param.functionName};
						node.icon=base+"/images/home/icon/jd.png";
						if(id=="" || id==null){
							add(node);
						}else{
							edit(node);
						}
					}
					layer.alert(data.msg);
				}
			}
		});
	});
	
});



