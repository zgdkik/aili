$(document).ready(function() {
	//树基本属性
	var setting = {
		async : {
			enable : true,
			url : base+"/dict/getDictTree",
			autoParam : [ "id", "name" ],
			dataFilter : filter
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : menuClick
		}
	};
	//选择的树节点
	var treeNodeId = null;
	//过滤
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for ( var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	//点击事件
	function menuClick(event, treeId, treeNode, clickFlag) {
		treeNodeId = treeNode.id;
		queryData(treeNodeId);
	}
	//加载树
	$.fn.zTree.init($("#treeDict"), setting);
	//显示新增窗口
	$("#addDict").on("click",function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDict");
		var nodes = zTree.getSelectedNodes();
		//判断是都选择父节点
		if(nodes.length!=1){
			$.messager.alert("提示信息","请选择一个父节点");
        	return;
        }
		var treeNode = nodes[0];
		$("#dict-form").form("reset");
		$('#dict-form').find("#parentDictCode").val(treeNode.id);//设置父节点
    	$("#dict-form").find("#edit-id").val(null);
		$("#myDictModal").dialog({
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
    	  
	});
	
	//保存操作
	function save(){
		var validate=$("#dict-form").form('validate');
		if(!validate){
			return;
		}
		//获取参数
		var param = $('#dict-form').getFormObj();
		var id = param.id;
		var url = "/dict/addDict/";
		if(id!=null && id!=""){
			url = "/dict/editDict/";
		}
		aili.sendPost(base+url,param,{successHandler:function(data, textStatus, jqXHR){
			if (data != null) {
				if(data.success){
					//成功之后自己组装节点，然后增加的父节点下面
					$('#myDictModal').dialog('close');
					var node = {id:param.dictCode,pId:param.parentDictCode,name:param.dictName};
					node.icon=base+"/images/home/icon/jd.png";
					if(id!=null && id!=""){
						edit(node);
					}else{
						add(node);
					}
					$.messager.alert("提示信息",data.msg);
				}else{
					$.messager.alert("提示信息",data.msg);
				}
			}
		}});
		
	};
	
	


	//显示修改窗口
	$("#updateDict").on("click",function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDict");
		var nodes = zTree.getSelectedNodes();
		if(nodes.length!=1){
			$.messager.alert("提示信息","请选择一个节点");
        	return;
        }
		var treeNode = nodes[0];
		var dictCode=treeNode.id;
		if(dictCode=="dict"){
			$.messager.alert("提示信息","根节点不允许修改");
        	return;
		}
		//请求修改的数据
		aili.sendPost(base+"/dict/showEditDictForm/"+dictCode,null,{successHandler:function(data, textStatus, jqXHR){
			if (data != null) {
				if(data.success){
					if (data != null) {
						$("#dict-form").form("load",data.data);
						$("#edit-form").find(".dictCode").textbox('readonly');
						$("#myDictModal").dialog({
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
					}
				}else{
					$.messager.alert("提示信息",data.msg);
				}
			}
		}});
	});
	
	//删除数据字典类型
	$("#deleteDict").on("click",function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDict");
		var nodes = zTree.getSelectedNodes();
		if(nodes.length!=1){
        	$.messager.alert("提示信息","请选择一个要删除的节点");
        	return;
        }
		var treeNode = nodes[0];
		var dictCode=treeNode.id;
		//删除
		aili.sendPost(base+"/dict/deleteDict/"+dictCode,null,{successHandler:function(data, textStatus, jqXHR){
			if (data != null) {
				if(data.success){
					remove();
				}else{
					$.messager.alert("提示信息",data.msg);
				}
			}
		}});
	});
	
	
	//-------------------右边GRID（数据字典）JS维护start----------------------
	//数据字典列
	var columns =[[{
		field : 'key',
		title : '关键字'
	},{
		field : 'value',
		title : '值'
	},{
		field : 'orderNo',
		title : '序号'
	},
//	{
//		field : 'paramOne',
//		title : '扩展参数一'
//	},{
//		field : 'paramTwo',
//		title : '扩展参数二'
//	},{
//		field : 'paramThree',
//		title : '扩展参数三'
//	},
	{
		field : 'remark',
		title : '备注'
	},
	{
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
    function queryData(treeId){
	    var params = {};
		params.q_str_dictCode=treeId; 
	    aili.initPage(base + "/dict/getDictValueList",{},params,'#dg',columns);	
    }
	//新增数据字典，显示窗口
    $('.addDictValue').click(function(){
    	var zTree = $.fn.zTree.getZTreeObj("treeDict");
		var nodes = zTree.getSelectedNodes();
		//判断是都选择父节点
		if(nodes.length!=1){
			$.messager.alert("提示信息","请选择一个节点");
        	return;
        }
		var treeNode = nodes[0];
    	$("#dict-value-form").form("reset");
		$('#dict-value-form').find("#dictCode").val(treeNode.id);//设置父节点
    	$("#dict-value-form").find("#edit-id").val(null);
    	$("#dict-value-form").find(".key").textbox('readonly',false);
		$("#myDictValueModal").dialog({
			title: '数据维护',
			width: 300,
			height: 200,
			buttons: [{
                text: '保存',
                iconCls: 'icon-save',
                handler: function () {
                	saveDictValue();
                }
            }]
		});	
    });
	//保存操作
	function saveDictValue(){
		//form表单校验
		var validate=$("#dict-value-form").form('validate');
		if(!validate){
			return;
		}
		//获取参数
		var param = $('#dict-value-form').getFormObj();
		var id = param.id;
		var url ="/dict/addDictValue/"
		if(id!=null && id!=""){
			url = "/dict/updateDictValue/";
		}
		aili.sendPost(base+url,param,{successHandler:function(data, textStatus, jqXHR){
			if (data != null) {
				if(data.success){
					//成功之后自己组装节点，然后增加的父节点下面
					$('#myDictValueModal').dialog('close');
					queryData(treeNodeId);
				}
				$.messager.alert("提示信息",data.msg);
			}
		}});
		
	};
    //根据ID查询出数据，填充到form中然后进行修改
	$('body').on("click",".update",function(){
		var id=$(this).data("pid");
		//请求修改的数据
		aili.sendPost(base+"/dict/showEditDictValueForm/"+id,null,{successHandler:function(data, textStatus, jqXHR){
			if (data != null) {
				if(data.success){
					$("#dict-value-form").form("load",data.data);
					$("#dict-value-form").find(".key").textbox('readonly');
					$("#myDictValueModal").dialog({
						title: '数据维护',
						width: 300,
						height: 200,
						buttons: [{
		                    text: '保存',
		                    iconCls: 'icon-save',
		                    handler: function () {
		                    	saveDictValue();
		                    }
		                }]
					});	
				}else{
					$.messager.alert("提示信息",data.msg);
				}
			}
		}});
	});
	
	//根据ID删除单条数据	
	$('body').on("click",".delete",function(){
		var id=$(this).data("pid");
		$.messager.confirm("提示信息","您确定要删除吗",function(data){
			if(data){
				aili.sendPost(base+'/dict/deleteDictValue/'+id,null,{
		    		successHandler:function(data,textStatus, jqXHR){
		    			if(data.success){
		    				queryData(treeNodeId);
		    				$.messager.alert("提示信息",data.msg);
		    			}
		    		}});
			}
		});
	});
	
   
});


//删除节点
function remove() {
	var zTree = $.fn.zTree.getZTreeObj("treeDict");
	nodes = zTree.getSelectedNodes();
	treeNode = nodes[0];
	if (nodes.length == 0) {
		alert("请先选择一个节点");
		return;
	}
	zTree.removeNode(treeNode);
};
// 新增树节点
function add(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDict");
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
//修改节点
function edit(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDict");
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