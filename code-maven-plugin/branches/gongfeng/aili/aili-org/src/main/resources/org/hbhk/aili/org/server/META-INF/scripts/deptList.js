function menuClick(event, treeId, treeNode, clickFlag) {
	 var params = {};
	 params.q_str_parentDeptCode = treeNode.id;
	 aili.initPage(base + "/dept/page",{},params,'#dg',columns);	
}
var columns = null;
$(document).ready(function() {
	
	  columns = [[{
		field : 'deptCode', 
		title : '部门编码' 
		},{
		field : 'deptName', 
		title : '部门名称' 
		}
//		,{
//		field : 'parentDeptCode', 
//		title : 'parentDeptCode' 
//		},{
//		field : 'pinyin', 
//		title : 'pinyin' 
//		}
		,{
		field : 'leader', 
		title : '负责人' 
		}
		,{
			field : 'deptTelephone', 
			title : '电话' 
			}
		,{
			field : 'address', 
			title : '地址' 
			}
//		,{
//		field : 'deptTelephone', 
//		title : 'deptTelephone' 
//		},{
//		field : 'deptFax', 
//		title : 'deptFax' 
//		},{
//		field : 'zipCode', 
//		title : 'zipCode' 
//		},{
//		field : 'address', 
//		title : 'address' 
//		},{
//		field : 'beginTime', 
//		title : 'beginTime' 
//		},{
//		field : 'endTime', 
//		title : 'endTime' 
//		}
//		,{
//		field : 'provCode', 
//		title : '省份' 
//		},{
//		field : 'cityCode', 
//		title : '城市' 
//		},{
//		field : 'countyCode', 
//		title : '区县' 
//		}
//		,{
//		field : 'deptCoordinate', 
//		title : 'deptCoordinate' 
//		},{
//		field : 'orgSimpleName', 
//		title : 'orgSimpleName' 
//		},{
//		field : 'fullPath', 
//		title : 'fullPath' 
//		},{
//		field : 'versionNo', 
//		title : 'versionNo' 
//		},{
//		field : 'areaCode', 
//		title : 'areaCode' 
//		},{
//		field : 'deptEmail', 
//		title : 'deptEmail' 
//		},{
//		field : 'deptAttribute', 
//		title : 'deptAttribute' 
//		},{
//		field : 'canceledSystems', 
//		title : 'canceledSystems' 
//		},{
//		field : 'ehrDeptCode', 
//		title : 'ehrDeptCode' 
//		},{
//		field : 'orgEnSimple', 
//		title : 'orgEnSimple' 
//		},{
//		field : 'orgLevel', 
//		title : 'orgLevel' 
//		},{
//		field : 'deptArea', 
//		title : 'deptArea' 
//		},{
//		field : 'displayOrder', 
//		title : 'displayOrder' 
//		},{
//		field : 'deptLevel', 
//		title : 'deptLevel' 
//		},{
//		field : 'isLeaf', 
//		title : 'isLeaf' 
//		},{
//		field : 'deptDesc', 
//		title : 'deptDesc' 
//		},{
//		field : 'costCenterCode', 
//		title : 'costCenterCode' 
//		},{
//		field : 'isDivision', 
//		title : 'isDivision' 
//		},{
//		field : 'isBigRegion', 
//		title : 'isBigRegion' 
//		},{
//		field : 'isSmallRegion', 
//		title : 'isSmallRegion' 
//		},{
//		field : 'changeType', 
//		title : 'changeType' 
//		},{
//		field : 'changeDate', 
//		title : 'changeDate' 
//		}
		,{
		field : 'createUser', 
		title : '创建人' 
		},{
			field : 'createTime', 
			title : '创建时间',
			formatter:function(val){
				return aili.formatTime(val);
			}
		},{
		field : 'updateUser', 
		title : '修改人' 
		},{
		field : 'updateTime', 
		title : '修改时间' ,
		formatter:function(val){
			return aili.formatTime(val);
		}
		},{
		field : 'status', 
		title : '状态' ,
		formatter:function(val){
			if(val=="1"){
				return "有效";
			}
			return "无效";
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
	    aili.initPage(base + "/dept/page",{},params,'#dg',columns);	
    }
	//查询
	$('.query-btn').on("click",function(){
		queryData();
	});
	//新增
	$('.add-btn').click(function(){
    	$("#edit-form").form("reset");
    	$("#edit-form").find("#edit-id").val(null);
    	$("#edit-form").find(".deptCode").textbox('readonly',false);
		$("#dlg").dialog({
			title: '数据维护',
			width: 300,
			height: 300,
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
		aili.sendPost(base+'/dept/getById/'+id,null,{successHandler:function(data, textStatus, jqXHR){
			if(data !=null){
				if(data.success){
					$("#edit-form").form("load",data.data);
					$("#edit-form").find(".deptCode").textbox('readonly');
					$("#dlg").dialog({
						title: '数据维护',
						width: 300,
						height: 300,
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
		aili.sendPost(base + "/dept/edit",form,{successHandler:function(data, textStatus, jqXHR){
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
				aili.sendPost(base+ "/dept/updateStatus/",{"id":id,"status":0},{successHandler:function(data, textStatus, jqXHR){
					if(data !=null){
						if(data.success){
							$('#dg').datagrid('reload');
							$.messager.alert("提示信息","删除成功");
						}else{
							$.messager.alert("提示信息",data.msg);
						}
					}
				}});
			}
		});
	}); 
	
	
	var setting = {
			async : {
				enable : true,
				url : base + "/dept/getTree",
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
	var zTree  = $.fn.zTree.init($("#depttree"), setting);
	
	setTimeout(function(){
		var treenode = zTree.getNodeByParam("id", "root", null);
		zTree.expandNode(treenode, true, true, null, false);
	},100);
	
	aili.deptSelector("#parentDeptCode");
	aili.empSelector("#leader");
});