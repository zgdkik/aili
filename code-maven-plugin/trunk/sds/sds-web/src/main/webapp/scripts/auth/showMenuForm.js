var setting = {
			async : {
				enable : true,
				url : base+"/auth/getTree",
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

function menuClick(event, treeId, treeNode, clickFlag) {
	$("#authId").val(treeNode.id);
	sds.sendPost(base+"/auth/getMenuInfo/"+treeNode.id,null,{successHandler:function(data,
			 textStatus, jqXHR){
				if(data!=null){
					document.getElementById("functionCodeSpan").innerHTML=(data.data.functionCode);
					document.getElementById("functionNameSpan").innerHTML=(data.data.functionName);
					document.getElementById("uriSpan").innerHTML=(data.data.uri);
					document.getElementById("parentCodeSpan").innerHTML=(data.data.parentCode);
					document.getElementById("activeSpan").innerHTML=(data.data.active);
					document.getElementById("displayOrderSpan").innerHTML=(data.data.displayOrder);
					document.getElementById("functionTypeSpan").innerHTML=(data.data.functionType);
					document.getElementById("functionDescSpan").innerHTML=(data.data.functionDesc);
					
					$("#id").val(data.data.id);
				}
	}});
	
	if($("#authId").val()=="0"){
		$("#update").attr("disabled",true);
		$("#delete").attr("disabled",true);
	}else{
		$("#update").attr("disabled",false);
		$("#delete").attr("disabled",false);
	}
	
}

function filter(treeId, parentNode, childNodes) {
	if (!childNodes)
		return null;
	for ( var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}

//新增树节点
function add(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	isParent = e.isParent;
	id=e.id;
	pId=e.pId;
	name=e.name;
	icon=e.icon;
	nodes = zTree.getNodeByParam("id",pId);
	treeNode = zTree.addNodes(nodes, {id:id, pId:pId,icon:icon,open:true,isParent:isParent, name:name});
};

//删除节点
function remove() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	nodes = zTree.getSelectedNodes();
	treeNode = nodes[0];
	if (nodes.length == 0) {
		alert("请先选择一个节点");
		return;
	}
	zTree.removeNode(treeNode);
};

//修改节点
function edit(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	name=e.name;
	id=e.id;
	pId=e.pId;
	nodes = zTree.getSelectedNodes();
	nodes2 = zTree.getNodeByParam("id",pId,null);
	treeNode = nodes[0];
	if (nodes.length == 0) {
		alert("请先选择一个节点");
		return;
	}else{
		treeNode.name=name;
		treeNode.pId=pId;
		treeNode.open=true;
	}
	//先更新
	zTree.updateNode(treeNode);
	//再删除
	zTree.removeNode(treeNode);
	//再添加
	zTree.addNodes(nodes2, treeNode);
	
};



$(document).ready(function() {

	$.fn.zTree.init($("#treeDemo"), setting);

	$('#menuForm').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        fields: {
        	functionCode: {
                validators: {
                    notEmpty: {
                        message: '功能编码不能为空'
                    }
                }
            },
            functionName: {
                validators: {
                    notEmpty: {
                        message: '功能名称不能为空'
                    }
                }
            },
            uri: {
                validators: {
                    notEmpty: {
                        message: '功能入口URI不能为空'
                    }
                }
            },
            displayOrder: {
                validators: {
                    notEmpty: {
                        message: '排序不能为空'
                    }
                }
            }
        }
    });


$('#save').click(function() {
	$('#menuForm').bootstrapValidator('validate');
	var validate = $('#menuForm').data('bootstrapValidator').isValid();
	if(validate==true){
		sds.sendPost(base+"/auth/addMenu", {
			functionCode:$("#functionCode").val(),
			functionName:$("#functionName").val(),
			uri:$("#uri").val(),
			parentCode:$("#parentCode").val(),
			active:$("#active").val(),
			displayOrder:$("#displayOrder").val(),
			//checkable:$("input[name=checkable]:checked").val(),
			functionType:$("#functionType").val(),
			//leaf:$("input[name=leaf]:checked").val(),
			iconCls:$("#iconCls").val(),
			cls:$("#cls").val(),
			functionDesc:$("#functionDesc").val()
		},{successHandler:function(data,
				 textStatus, jqXHR){
			bootbox.alert(data.msg);
			$('#myModal').modal('hide');
			
			if(data.data=="0"){
				if($("#functionType").val()=="Y"){
					add({id:$("#functionCode").val(),pId:$("#parentCode").val(),icon:base+"/images/home/icon/1_close.png",isParent:true,name:$("#functionName").val(),pId:$("#parentCode").val()});
				}else{
					add({id:$("#functionCode").val(),pId:$("#parentCode").val(),icon:base+"/images/home/icon/jd.png",isParent:false,name:$("#functionName").val(),pId:$("#parentCode").val()});
				}
			}
		}});
	}
	
	
});


$('#revise').click(function() {
	$('#menuForm').bootstrapValidator('validate');
	var validate = $('#menuForm').data('bootstrapValidator').isValid();
	if(validate==true){
		sds.sendPost(base+"/auth/updateMenu", {
			id:$("#id").val(),
			functionCode:$("#functionCode").val(),
			functionName:$("#functionName").val(),
			uri:$("#uri").val(),
			parentCode:$("#parentCode").val(),
			active:$("#active").val(),
			displayOrder:$("#displayOrder").val(),
			//checkable:$("input[name=checkable]:checked").val(),
			functionType:$("#functionType").val(),
			//leaf:$("input[name=leaf]:checked").val(),
			iconCls:$("#iconCls").val(),
			cls:$("#cls").val(),
			functionDesc:$("#functionDesc").val()
		},{successHandler:function(data,
				 textStatus, jqXHR){
			    
				bootbox.alert(data.msg);	
				
				//更新完成重新加载数据
				sds.sendPost(base+"/auth/getMenuInfo/"+$("#functionCode").val(),null,{successHandler:function(data,
						 textStatus, jqXHR){
							if(data!=null){
								document.getElementById("functionCodeSpan").innerHTML=(data.data.functionCode);
								document.getElementById("functionNameSpan").innerHTML=(data.data.functionName);
								document.getElementById("uriSpan").innerHTML=(data.data.uri);
								document.getElementById("parentCodeSpan").innerHTML=(data.data.parentCode);
								document.getElementById("activeSpan").innerHTML=(data.data.active);
								document.getElementById("displayOrderSpan").innerHTML=(data.data.displayOrder);
								//document.getElementById("checkableSpan").innerHTML=(data.data.checkable);
								document.getElementById("functionTypeSpan").innerHTML=(data.data.functionType);
								//document.getElementById("leafSpan").innerHTML=(data.data.leaf);
								document.getElementById("functionDescSpan").innerHTML=(data.data.functionDesc);
							}
				}});
				
				$('#myModal').modal('hide');
				
				edit({id:$("#functionCode").val(),pId:$("#parentCode").val(),name:$("#functionName").val()});
		}});
	}
});


$('#delete').click(function() {
	
	bootbox.confirm("确定要删除吗?", function(result) {
		  if(result){
				sds.sendPost(base+"/auth/deleteMenu",{
					id:$("#id").val(),
					functionCode:$("#authId").val()
				},{successHandler:function(data,
						 textStatus, jqXHR){
					 bootbox.alert(data.msg);
					 if(data.data=="0"){
						 remove();
					 }
				}});
		  }
			
	});
	
});

	


});


function showUpdate(){
	$("#functionCode").val("");
	$("#functionName").val("");
	$("#uri").val("");
	$("#parentCode").val("");
	$("#displayOrder").val("");
	$("#iconCls").val("");
	$("#cls").val("");
	$("#functionDesc").val("");
	$('#menuForm').data('bootstrapValidator').resetForm(true);
	sds.sendPost(base+"/auth/showUpdateMenuForm/"+$("#authId").val(),null,{successHandler:function(data,
			 textStatus, jqXHR){
				if(data!=null){
					$("#functionCode").val(data.data.functionEntity.functionCode);
					$("#functionName").val(data.data.functionEntity.functionName);
					$("#uri").val(data.data.functionEntity.uri);
					$("#parentCode").val(data.data.functionEntity.parentCode);
					$("#displayOrder").val(data.data.functionEntity.displayOrder);
					$("#iconCls").val(data.data.functionEntity.iconCls);
					$("#cls").val(data.data.functionEntity.cls);
					$("#functionDesc").val(data.data.functionEntity.functionDesc);
					 
				    
				   /* if(data.data.functionEntity.checkable=="Y"){
				        $("#checkable1").attr("checked",true);
				    }else{
				    	$("#checkable2").attr("checked",true);
				    }*/
				    
				   /* if(data.data.functionEntity.leaf=="Y"){
				        $("#leaf1").attr("checked",true);
				        $("#leaf2").attr("checked",false);
				    }else{
				    	$("#leaf2").attr("checked",true);
				        $("#leaf1").attr("checked",false);
				    }*/
				    
				    if(data.data.functionEntity.functionType!=""){
				    	$('#functionType').val(data.data.functionEntity.functionType).trigger("change");
				    }
				    
				    if(data.data.functionEntity.active!=""){
				    	$('#active').val(data.data.functionEntity.active).trigger("change");
				    }
				    
				    if(data.data.flag=="2"){
				    	document.getElementById("title").innerHTML=("修改权限菜单");
				    	$("#save").hide();
				    	$("#revise").show();
				    }else{
				    	document.getElementById("title").innerHTML=("新增权限菜单");
				    	$("#save").show();
				    	$("#revise").hide();
				    }
				    
				    //功能编码不能修改
				    $("#functionCode").attr("disabled",true);
				   //功能类型不能修改
				    $("#functionType").attr("disabled",true);
				    
				    if($("#authId").val()!="0"){
	                    var resultList=data.data.functionEntityList;
					    
					    var parentCodes = $("#parentCode");
					    parentCodes.empty();
					    for(var i=0;i<resultList.length;i++) {
					        var option = $("<option>").text(resultList[i].functionName).val(resultList[i].functionCode);
					        parentCodes.append(option);
					    }
				    }
				    
				    $('#parentCode').val(data.data.functionEntity.parentCode).trigger("change");
				}
				$('#myModal').modal('show');
				
	}});
}

function showAdd(){
	sds.sendPost(base+"/auth/showAddMenuForm/"+$("#authId").val(),null,{successHandler:function(data,
			 textStatus, jqXHR){
				if(data!=null){
					$("#functionCode").val("");
					$("#functionName").val("");
					$("#uri").val("");
					$("#parentCode").val("");
					$("#displayOrder").val("");
					$("#iconCls").val("");
					$("#cls").val("");
					$("#functionDesc").val("");
				    if(data.data.flag=="2"){
				    	document.getElementById("title").innerHTML=("修改权限菜单");
				    	$("#save").hide();
				    	$("#revise").show();
				    }else{
				    	document.getElementById("title").innerHTML=("新增权限菜单");
				    	$("#save").show();
				    	$("#revise").hide();
				    }
				    
				    //功能编码能修改
				    $("#functionCode").attr("disabled",false);
				    //功能类型能修改
				    $("#functionType").attr("disabled",false);
				    
				    var resultList=data.data.functionEntityList;
				    
				    var parentCodes = $("#parentCode");
				    parentCodes.empty();
				    for(var i=0;i<resultList.length;i++) {
				        var option = $("<option>").text(resultList[i].functionName).val(resultList[i].functionCode);
				        parentCodes.append(option);
				    }
				    
				    $('#parentCode').val(data.data.functionEntity.functionCode).trigger("change");
				}
				
				$('#myModal').modal('show');
	}});
}


