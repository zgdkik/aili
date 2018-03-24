$(document).ready(function() {
	
	var umn = [[{
		field : 'userName', 
		title : '用户名' 
		},{
		field : 'modifiedTime', 
		title : '修改时间',
		formatter : function(modifiedTime,obj){
			return ym.formatTime(modifiedTime);
		}
		},{
		field : 'pwdModidiedTime', 
		title : '密码修改时间',
		formatter : function(pwdModidiedTime,obj){
			return ym.formatTime(pwdModidiedTime);
		}
		},{
		field : 'compCode', 
		title : '公司'
		},{
		field : 'status', 
		title : '状态',
		formatter : function(status,obj){
			if("enable"==status){
				return "启用";
			}else{
				return "禁用";
			}
		}
		},{
		field : 'id',
		title : '操作列',
		formatter : function(id,obj){
			var toolmenu =  
				'<div class="btn-group btn-group-xs"> '+
				'<button class="update" data-pid="'+obj.userName+'">授权</button>'+
	       		'</div>';
			return toolmenu;
		}
		}]];
	
		//显示字段
		$('#dg').datagrid({columns:umn});
	
		//查询
		$('.query-btn').on("click",function(){
				var params =$(".query-form").getFormObj();
		    	var opts={};
		    	opts.onLoadSuccess=function() {
		    		            $('#dg').datagrid('statistics');
		    		     }
				ym.initPage(base + "/user/getuserlist",opts,params,'#dg',umn);
		});
	
		var col = [[{
			field : 'state',
			checkbox:true
		}, {
			field : 'roleName',
			title : '角色名称'
		}, {
			field : 'compCode',
			title : '角色公司'
		}, {
			field : 'roleDesc',
			title : '角色描述'
		}]];
		
		$('#dg-role').datagrid({columns:col});
		
		$("body").on("click",'.update',function(){
			var userName = $(this).data("pid");
	    	ym.sendPost(base+"/user/grain/"+userName,null,{successHandler:function(data, textStatus, jqXHR){
				if (data != null) {
					if(data.success){
					var params = {};
			    	var opts={};
			    	var roleCodes = data.data;
			    	opts.onLoadSuccess=function(data) {
			    		var rows = data.list;
						if(rows!=null){
							for (var i = 0; i < rows.length; i++) {
								var r1 = rows[i].roleCode;
								for (var j = 0; j < roleCodes.length; j++) {
	    							 var r2 = roleCodes[j];
	    							 if(r1==r2){
	    								 $('#dg-role').datagrid('checkRow', i);
	    							 }
								}
							}
						}
			    	}
					ym.initPage(base + "/role/getRoleList",opts,params,'#dg-role',col);
					$('#dlg').dialog({
						title: '用户授权',
						resizable: true,
						width: 500,
						height: 350,
						modal: true,
						buttons: [{
							text: '保存',
							iconCls: 'icon-save',
							handler: function () {
								$('#dlg').find(".userName").val(userName);
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
		
		 function save(){
				var selections = $('#dg-role').datagrid("getChecked");
				var roleCodes = "";
			    for (var i = 0; i < selections.length; i++) {
					var n = selections[i];
					if(selections.length==(i+1)){
						roleCodes  = roleCodes+n.roleCode+"";
					}else{
						roleCodes  = roleCodes+n.roleCode+",";
					}
				 }
			   var userName = $('#dlg').find(".userName").val();
		    	ym.sendPost(base+"/user/grain/",{userName:userName,roleCodes:roleCodes},{successHandler:function(data, textStatus, jqXHR){
					if (data != null) {
						if(data.success){
							$("#company-form").form("load",data.data);
							$('#dg').datagrid('reload');
							$('#dlg').dialog("close");
							$.messager.alert("提示信息",data.msg);
						}else{
							$.messager.alert("提示信息",data.msg);
						}
					}
				}});
		    }

});
