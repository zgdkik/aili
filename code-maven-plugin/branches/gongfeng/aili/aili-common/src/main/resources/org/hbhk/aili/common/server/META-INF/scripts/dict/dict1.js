



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
		$('#myDictModal').modal('show');
	});
	
	//保存操作
	$("#saveDict").on("click",function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDict");
		var nodes = zTree.getSelectedNodes();
		//判断是都选择父节点
		if(nodes.length!=1){
        	alert("请选择一个父节点！");
        	return;
        }
		var treeNode = nodes[0];
		$('#dict-form').find("input[name='parentDictCode']").val(treeNode.id);//设置父节点
		$('#dict-form').bootstrapValidator('validate');
		//form表单校验
		var validate = $('#dict-form').data('bootstrapValidator').isValid();
		if(!validate){
			return;
		}
		//获取参数
		var param = $('#dict-form').getFormObj();
		aili.sendPost(base+"/dict/addDict/",param,{successHandler:function(data, textStatus, jqXHR){
			if (data != null) {
				if(data.success){
					//成功之后自己组装节点，然后增加的父节点下面
					$('#myDictModal').modal('hide');
					var node = {id:param.dictCode,pId:param.parentDictCode,name:param.dictName};
					node.icon=base+"/images/home/icon/jd.png";
					add(node);
				}else if(data.success===undefined){
					layer.alert("登陆超时，请刷新页面重新登录！");
				}else{
					layer.alert(data.msg);
				}
			}
		}});
		
	});
	
	
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

	//显示修改窗口
	$("#updateDict").on("click",function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDict");
		var nodes = zTree.getSelectedNodes();
		if(nodes.length!=1){
			layer.alert("请选择一个要修改的节点！");
        	return;
        }
		var treeNode = nodes[0];
		var dictCode=treeNode.id;
		if(dictCode=="dict"){
			layer.alert("根节点不允许修改！");
        	return;
		}
		//请求修改的数据
		aili.sendPost(base+"/dict/showEditDictForm/"+dictCode,null,{successHandler:function(data, textStatus, jqXHR){
			if (data != null) {
				if(data.success){
					if (data != null) {
						aili.setFormObj("dictUpdate-form",data.data);
						$('#myUpdateDictModal').modal('show');
					}
				}else if(data.success===undefined){
					layer.alert("登陆超时，请刷新页面重新登录！");
				}else{
					layer.alert(data.msg);
				}
			}
		}});
	});
	
	
	//保存操作
	$("#saveUpdateDict").on("click",function(){
		$('#dictUpdate-form').bootstrapValidator('validate');
		//form表单校验
		var validate = $('#dictUpdate-form').data('bootstrapValidator').isValid();
		if(!validate){
			return;
		}
		//获取参数
		var param = $('#dictUpdate-form').getFormObj();
		aili.sendPost(base+"/dict/editDict/",param,{successHandler:function(data, textStatus, jqXHR){
			if (data != null) {
				if(data.success){
					//成功之后自己组装节点，然后增加的父节点下面
					$('#myUpdateDictModal').modal('hide');
					var node = {id:param.dictCode,pId:param.parentDictCode,name:param.dictName};
					edit(node);
				}else if(data.success===undefined){
					layer.alert("登陆超时，请刷新页面重新登录！");
				}else{
					layer.alert(data.msg);
				}
			}
		}});
	});
	// 修改节点
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
	
	
	
	//删除数据字典类型
	$("#deleteDict").on("click",function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDict");
		var nodes = zTree.getSelectedNodes();
		if(nodes.length!=1){
        	alert("请选择一个要删除的节点！");
        	return;
        }
		var treeNode = nodes[0];
		var dictCode=treeNode.id;
		//删除
		aili.sendPost(base+"/dict/deleteDict/"+dictCode,null,{successHandler:function(data, textStatus, jqXHR){
			if (data != null) {
				if(data.success){
					remove();
				}else if(data.success===undefined){
					layer.alert("登陆超时，请刷新页面重新登录！");
				}else{
					layer.alert(data.msg);
				}
			}
		}});
	});
	
	// 删除节点
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
	
	
	//定义校验规则
	var validDictObj = {
			fields : {
				dictName : {
					validators : {
						notEmpty : {
							message : '数据字典分类名称不能为空'
						}
					}
				},
				dictCode : {
					validators : {
						notEmpty : {
							message : '数据字典分类编码不能为空'
						}
					}
				}
			}
		};
	$('#dictUpdate-form').bootstrapValidator(validDictObj);
	$('#dict-form').bootstrapValidator(validDictObj);
	
	//-------------------左边树（数据字典分类）JS维护end----------------------
	
	//-------------------右边GRID（数据字典）JS维护start----------------------
	//数据字典列
	var columns = [{
		field : 'key',
		title : '关键字'
	},{
		field : 'value',
		title : '值'
	},{
		field : 'orderNo',
		title : '序号'
	},{
		field : 'paramOne',
		title : '扩展参数一'
	},{
		field : 'paramTwo',
		title : '扩展参数二'
	},{
		field : 'paramThree',
		title : '扩展参数三'
	},{
		field : 'remark',
		title : '备注'
	},{
		field : 'userName',
		title : '操作列',
		formatter : function(id,obj){
			var toolmenu =  
				'<div class="btn-group btn-group-xs"> '+
				'<input type="hidden" value="'+obj.id+'"/>'+
	       		'<a type="button" class="btn btn-default update" data-pid="'+obj.id+'"> <i class="fa fa-edit"></i>修改</a>'+
	       		'<a type="button" class="btn btn-default delete" data-pid="'+obj.id+'"> <i class="fa fa-edit"></i>删除</a>'+
	       		'</div>';
			return toolmenu;
		}
	} ];
	//查询数据字典
	 function queryData(treeId){
    	aili.initPage(base + "/dict/getDictValueList", {
    		queryParams : function(params) {
    			params.q_str_dictCode=treeId; 
    			return params;
    		}
    	}, "#dictValueTable", columns);
    };
    
	//新增数据字典，显示窗口
    $('#addDictValue').click(function(){
    	$('#myDictValueModal').modal('show');
    });
	//保存操作
	$("#saveDictValue").on("click",function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDict");
		var nodes = zTree.getSelectedNodes();
		//判断是都选择父节点
		if(nodes.length!=1){
        	alert("请选择一个节点！");
        	return;
        }
		var treeNode = nodes[0];
		$('#dictValue-form').find("input[name='dictCode']").val(treeNode.id);//设置父节点
		$('#dictValue-form').bootstrapValidator('validate');
		
		//form表单校验
		var validate = $('#dictValue-form').data('bootstrapValidator').isValid();
		if(!validate){
			return;
		}
		//获取参数
		var param = $('#dictValue-form').getFormObj();
		aili.sendPost(base+"/dict/addDictValue/",param,{successHandler:function(data, textStatus, jqXHR){
			if (data != null) {
				if(data.success){
					//成功之后自己组装节点，然后增加的父节点下面
					$('#myDictValueModal').modal('hide');
					queryData(treeNode.id);
				}
				layer.alert(data.msg);
			}
		}});
		
	});
    //根据ID查询出数据，填充到form中然后进行修改
	$('#dictValueTable').on("click",".update",function(){
		var id=$(this).data("pid");
		//请求修改的数据
		aili.sendPost(base+"/dict/showEditDictValueForm/"+id,null,{successHandler:function(data, textStatus, jqXHR){
			if (data != null) {
				if(data.success){
					if (data != null) {
						aili.setFormObj("dictValueUpdate-form",data.data);
						$('#myDictValueUpdateModal').modal('show');
					}
				}else{
					layer.alert(data.msg);
				}
			}
		}});
	});
	//修改操作
	$("#updateDictValue").on("click",function(){
		$('#dictValueUpdate-form').bootstrapValidator('validate');
		var zTree = $.fn.zTree.getZTreeObj("treeDict");
		var nodes = zTree.getSelectedNodes();
		//判断是都选择父节点
		if(nodes.length!=1){
        	alert("请选择一个节点！");
        	return;
        }
		var treeNode = nodes[0];
		//form表单校验
		var validate = $('#dictValueUpdate-form').data('bootstrapValidator').isValid();
		if(!validate){
			return;
		}
		//获取参数
		var param = $('#dictValueUpdate-form').getFormObj();
		aili.sendPost(base+"/dict/updateDictValue/",param,{successHandler:function(data, textStatus, jqXHR){
			if (data != null) {
				if(data.success){
					//成功之后自己组装节点，然后增加的父节点下面
					$('#myDictValueUpdateModal').modal('hide');
					queryData(treeNode.id);
				}
				layer.alert(data.msg);
			}
		}});
		
	});
	
	
	//根据ID删除单条数据	
	$('#dictValueTable').on("click",".delete",function(){
		var id=$(this).data("pid");
		bootbox.confirm("确定要删除吗?", function(result) {
			if(result){
				aili.sendPost(base+'/dict/deleteDictValue/'+id,null,{
		    		successHandler:function(data,textStatus, jqXHR){
		    			if(data.success){
		    				queryData(treeNodeId);
		    				layer.alert(data.msg);
		    			}
		    		}});
			}
		});
	});
	//数据字典校验
    var validDictValueCode = {
    		fields : {
    			key : {
    				validators : {
    					notEmpty : {
    						message : '关键字为必填项'
    					}
    				}
    			},
    			value : {
    				validators : {
    					notEmpty : {
    						message : '值为必填项'
    					}
    				}
    			},
    			orderNo : {
    				validators : {
    					notEmpty : {
    						message : '序号为必填项'
    					}
    				}
    			}
    		}
    	};
	//定义校验规则
	$('#dictValue-form').bootstrapValidator(validDictValueCode);
    $('#dictValueUpdate-form').bootstrapValidator(validDictValueCode);
   
});







