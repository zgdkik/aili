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
				$(".query-form").form("load",data.data);
			}
		}
	});

}

$(document).ready(function() {
	
	setting.callback = {onClick : menuClick};
	
	$.fn.zTree.init($("#treeDemo"), setting);
	
	$(".addnew").on("click",function(){
    	$("#edit-form").form("reset");
    	$("#edit-form").find("#edit-id").val(null);
    	$("#edit-form").find('.privilegeCode').textbox('readonly',false);
		$("#dlg").dialog({
			title: '数据维护',
			width: 470,
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
	
	$(".update").on("click",function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes();
		if ( nodes.length == 0) {
			$.messager.alert("提示信息","请先选择一个节点");
			return;
		}
		var nodeId = nodes[0].id;
		aili.sendPost(base + "/privilege/getMenuInfo/" + nodeId, null, {
			successHandler : function(data, textStatus, jqXHR) {
				if(data !=null){
					if(data.success){
						$("#edit-form").form("load",data.data);
						$("#edit-form").find(".privilegeCode").textbox('readonly');
						$("#dlg").dialog({
							title: '数据维护',
							width: 470,
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
			}
		});
	});
	
	$(".remove").on("click",function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes();
		if ( nodes.length == 0) {
			$.messager.alert("提示信息","请先选择一个节点");
			return;
		}
		var nodeId = nodes[0].id;
		$.messager.confirm("提示信息","您确定要删除吗",function(data){
			if(data){
				aili.sendPost(base + "/privilege/deleteMenu/" + nodeId,null, {
					successHandler : function(data, textStatus, jqXHR) {
						if (data != null) {
							if(data.success){
								remove();
							}
							$.messager.alert("提示信息",data.msg);
						}
					}
				});
			}
		});
		
	});
	
	function save(){
		var validate=$("#edit-form").form('validate');
		if(!validate){
			return;
		}
		var param=$("#edit-form").getFormObj();
		var id  =$('#edit-form').find("input[name='id']").val();
		aili.sendPost(base+"/privilege/editMenu/",param, {
			successHandler : function(data, textStatus, jqXHR) {
				if (data != null) {
					if(data.success){
						$('#dlg').dialog('close');
						var node = {id:param.privilegeCode,pId:param.parentCode,name:param.privilegeName};
						node.icon=base+"/images/home/icon/jd.png";
						if(id=="" || id==null){
							add(node);
						}else{
							edit(node);
						}
					}
					$.messager.alert("提示信息",data.msg);
				}
			}
		});
	};
	
});



