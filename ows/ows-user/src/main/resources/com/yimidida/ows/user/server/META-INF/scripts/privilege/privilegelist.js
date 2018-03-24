/**
 * 页面初始化处理
 */
$(function(){
	$('#div1').panel({
		title : '权限树'
	});
	$('#div2').panel({
		title : '权限信息'
	});
});

//新增树节点
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
	ym.sendPost(base + "/privilege/getMenuInfo/" + treeNode.id, null, {
		successHandler : function(data, textStatus, jqXHR) {
			if (data != null) {
				$("#privilege-info").form('load',data.data)
			}
		}
	});

}

$(document).ready(function() {
	setting.callback = {onClick : menuClick};
	$.fn.zTree.init($("#treeDemo"), setting);
	
	$(".addnew").on("click",function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes();
		if ( nodes.length == 0) {
			$.messager.alert("提示信息","请先选择一个节点");
			return;
		}
		$("#privilege-form").form("reset");
		var nodeId = nodes[0].id;
		$('#dlg').find('input[name="id"]').val(null);
		$('#dlg').find('input[name="parentCode"]').val(nodeId);
		$('#code').textbox('textbox').attr('readonly',false);
		$("#dlg").dialog({
			title: '新增权限',
			top:100,
			buttons: [{
                text: '保存',
                iconCls: 'icon-save',
                handler: function () {
                	saveOrUpdate()
                }
            }]
		});	
	});
	
	$(".update").on("click",function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes();
		if ( nodes.length == 0) {
			layer.alert("提示信息","请先选择一个节点");
			return;
		}
		var nodeId = nodes[0].id;
		ym.sendPost(base + "/privilege/getMenuInfo/" + nodeId, null, {
			successHandler : function(data, textStatus, jqXHR) {
				if (data != null) {
					$('#code').textbox('textbox').attr('readonly',true)
					$("#privilege-form").form('load',data.data)
					$("#dlg").dialog({
						title: '编辑权限',
						top:100,
						buttons: [{
			                text: '保存',
			                iconCls: 'icon-save',
			                handler: function () {
			                	saveOrUpdate()
			                }
			            }]
					});	
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
		$.messager.confirm("提示信息","您确定要删除吗",function(result){
			if(result){
				ym.sendPost(base + "/privilege/deleteMenu/" + nodeId,null, {
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
	
});

function saveOrUpdate(){
	var validate=$("#privilege-form").form('validate');
	if(!validate){
		return;
	}
	var param = $('#privilege-form').getFormObj();
	var id  =$('#privilege-form').find("input[name='id']").val();
	ym.sendPost(base+"/privilege/editMenu/",param, {
		successHandler : function(data, textStatus, jqXHR) {
			if (data != null) {
				if(data.success){
					$('#dlg').dialog('close');
					var node = {id:param.privilegeCode,pId:param.parentCode,name:param.functionName};
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
}
