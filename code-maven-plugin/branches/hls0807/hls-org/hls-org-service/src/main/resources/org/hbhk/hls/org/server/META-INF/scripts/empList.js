function menuClick(event, treeId, treeNode, clickFlag) {
	 var params = {};
	 params.q_str_deptCode = treeNode.id;
	 aili.initPage(base + "/emp/page",{},params,'#dg',columns);	
}
var columns = null;
$(document).ready(function() {
	 columns = [[{
		field : 'id',
		title : '操作列',
		formatter : function(id,obj){
			if(obj.status==0){
				return ""
			}
			var toolmenu =  
				'<div class="btn-group btn-group-xs"> '+
				'<button class="update" data-pid="'+obj.id+'">修改</button>'+
	       		'<button class="delete" data-pid="'+obj.id+'">离职</button>'+
	       		'</div>';
			return toolmenu;
		}
	},{
		field : 'empCode', 
		title : '工号' 
		},{
			field : 'empName', 
			title : '姓名' 
		},{
		field : 'deptCode', 
		title : '部门' 
		},{
		field : 'gender', 
		title : '性别' ,
		formatter:function(val){
			if(val=="1"){
				return "男";
			}
			return "女";
		}
		},{
		field : 'position', 
		title : '职位' 
		},{
		field : 'birthDate', 
		title : '出生日期' ,
		formatter:function(val){
			return aili.formatTime(val);
		}
		},{
		field : 'inDate', 
		title : '入职日期' ,
		formatter:function(val){
			return aili.formatTime(val);
		}
		},{
		field : 'outDate', 
		title : '离职日期' ,
		formatter:function(val){
			return aili.formatTime(val);
		}
		},{
		field : 'identityCard', 
		title : '身份证号' 
		},{
		field : 'mobileNumber', 
		title : '手机号码' 
		},{
		field : 'createUser', 
		title : '创建人' 
		},{
			field : 'createTime', 
			title : '创建时间' ,
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
				return "在职";
			}
			return "离职";
		}
		}
//		,{
//			field : 'offerTel', 
//			title : 'offerTel' 
//			},{
//			field : 'offerAddress', 
//			title : 'offerAddress' 
//			},{
//			field : 'offerZipCode', 
//			title : 'offerZipCode' 
//			},{
//			field : 'offerEmail', 
//			title : 'offerEmail' 
//			},{
//			field : 'homeTel', 
//			title : 'homeTel' 
//			},{
//			field : 'homeAddress', 
//			title : 'homeAddress' 
//			},{
//			field : 'homeZipCode', 
//			title : 'homeZipCode' 
//			},{
//			field : 'personEmail', 
//			title : 'personEmail' 
//			},{
//			field : 'workExp', 
//			title : 'workExp' 
//			},{
//			field : 'remark', 
//			title : 'remark' 
//			},{
//			field : 'pinyin', 
//			title : 'pinyin' 
//			}
			
		]];

    $('#dg').datagrid({columns:columns});
    //查询方法
    function queryData(){
	    var params = $('.query-form').getFormObj();
	    aili.initPage(base + "/emp/page",{},params,'#dg',columns);	
    }
	//查询
	$('.query-btn').on("click",function(){
		queryData();
	});
	//新增
	$('.add-btn').click(function(){
//		var zTree = $.fn.zTree.getZTreeObj("depttree"),
//		nodes = zTree.getSelectedNodes();
//		if (nodes.length == 0) {
//			$.messager.alert("提示信息","请先选择一个部门");
//			return;
//		}
    	$("#edit-form").form("reset");
    	$("#edit-form").find("#edit-id").val(null);
    	$("#edit-form").find(".empCode").textbox('readonly',false);
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
		aili.sendPost(base+'/emp/getById/'+id,null,{successHandler:function(data, textStatus, jqXHR){
			if(data !=null){
				if(data.success){
					$("#edit-form").form("load",data.data);
					$("#edit-form").find(".empCode").textbox('readonly');
					$("#edit-form").find("#radio").radio("check",data.data.gender);
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
		aili.sendPost(base + "/emp/edit",form,{successHandler:function(data, textStatus, jqXHR){
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
		$.messager.confirm("提示信息","您确定要让他(她)离职吗",function(data){
			if(data){
				aili.sendPost(base+ "/emp/updateStatus/",{"id":id,"status":0},{successHandler:function(data, textStatus, jqXHR){
					if(data !=null){
						if(data.success){
							$('#dg').datagrid('reload');
							$.messager.alert("提示信息","操作成功");
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
		var treenode = zTree.getNodeByParam("id", "yimidida", null);
		zTree.expandNode(treenode, true, true, null, false);
	},100);
	
	aili.deptSelector("#deptCode");
	
});