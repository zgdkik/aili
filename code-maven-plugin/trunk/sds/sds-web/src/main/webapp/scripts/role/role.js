/** *************1. 基本信息的设定********************** */

/** *************2. 页面加载时设定********************** */

$(document).ready(function() {

	// sds.frame.notifyError("notifyError", "notifyError");
	// sds.frame.notifyWarning("notifyWarning", "notifyWarning");
	// sds.frame.notifyInfo("notifyInfo", "notifyInfo");
	// sds.frame.notifySuccess("notifySuccess", "notifySuccess");
	// 返回值为布尔类型，true或false
	// bootbox.confirm("确定要删除吗?", function(result) {
	// sds.frame.notifySuccess("notifySuccess", "notifySuccess");
	// });
	// bootbox.alert("确定要删除吗?", function(result) {
	// sds.frame.notifySuccess("notifySuccess", "notifySuccess");
	// });
	// bootbox.prompt("确定要删除吗?", function(result) {
	// sds.frame.notifySuccess("notifySuccess", "notifySuccess");
	// });

	// bootbox.dialog({
	// title : "修改密码",
	// message : "<div class='well ' style='margin-top:25px;'><form
	// class='form-horizontal' role='form'><div class='form-group'><label
	// class='col-sm-3 control-label no-padding-right'
	// for='txtOldPwd'>旧密码</label><div class='col-sm-9'><input type='text'
	// id='txtOldPwd' placeholder='请输入旧密码' class='col-xs-10 col-sm-5'
	// /></div></div><div class='space-4'></div><div class='form-group'><label
	// class='col-sm-3 control-label no-padding-right'
	// for='txtNewPwd1'>新密码</label><div class='col-sm-9'><input type='text'
	// id='txtNewPwd1' placeholder='请输入新密码' class='col-xs-10 col-sm-5'
	// /></div></div><div class='space-4'></div><div class='form-group'><label
	// class='col-sm-3 control-label no-padding-right'
	// for='txtNewPwd2'>确认新密码</label><div class='col-sm-9'><input type='text'
	// id='txtNewPwd2' placeholder='再次输入新密码' class='col-xs-10 col-sm-5'
	// /></div></div></form></div>",
	// buttons : {
	// "success" : {
	// "label" : "<i class='icon-ok'></i> 保存",
	// "className" : "btn-sm btn-success",
	// "callback" : function() {
	// var txt1 = $("#txtOldPwd").val();
	// var txt2 = $("#txtNewPwd1").val();
	// var txt3 = $("#txtNewPwd2").val();
	// if(txt1 == "" || txt2 == "" || txt3 == ""){
	// bootbox.alert("密码不能为空");
	// return false;
	// }
	// if(txt2 != txt3 ){
	// bootbox.alert("两次输入新密码不一致，请重新输入!");
	// return false;
	// }
	// var info =
	// {"opt":"changepassword","oldpwd":txt1,"newpwd1":txt2,"newpwd2":txt3};
	// bootbox.alert("密码更新成功");
	// }
	// },
	// "cancel" : {
	// "label" : "<i class='icon-info'></i> 取消",
	// "className" : "btn-sm btn-danger",
	// "callback" : function() { }
	// }
	// }
	// });
	// url 参数 请求属性
	// sds.asyncPost(base+"/demo/getDemo/1",null,{successHandler:function(data,
	// textStatus, jqXHR){
	// bootbox.alert(data.msg);
	// }});
	var columns = [  {
		field : 'roleCode',
		title : '角色编码'
	}, {
		field : 'roleName',
		title : '角色名称'
	},{
		field : 'roleType',
		title : '角色类型',
		formatter : function(data){
			if(data =="common"){
				return "通用";
			}else{
				return "客户";
			}
		}
	}, {
		field : 'roleDesc',
		title : '角色描述'
	}, {
		field : 'status',
		title : '是否有效',
		formatter:function(status){
			if(status=="1"){
				return "是";
			}else{
				return "否";
			}
		}
	},/*{
		field : 'deptCode',
		title : '归属部门'
	}, */{
		field : 'createTime',
		title : '创建时间',
        formatter : function(createTime){
        	return sds.formatDate(createTime);
		}
	}, {
		field : 'createUser',
		title : '创建人'
	}, {
		field : 'updateUser',
		title : '修改人'
	}, {
		field : 'updateTime',
		title : '修改时间',
		formatter:function(updateTime){
			return sds.formatDate(updateTime);
		}
	}, {
		field : 'id',
		title : '操作列',
		formatter : function(id){
			
			var toolmenu =  
				'<div class="btn-group btn-group-xs"> '+
				'<input type="hidden" value="'+id+'">'+
	       		'<a type="button" class="btn btn-default" href="'+base+'/role/showRoleForm?id='+id+'"> <i class="fa fa-edit"></i>修改</a>'+
	       		'<a type="button" class="btn btn-default delete" data-id="'+id+'"> <i class="fa fa-edit"></i>删除</a>'+
	       		'</div>';
			return toolmenu;
		}
	} ];	
	
	
	sds.initPage(base + "/role/getRoleList", {
		queryParams : function(params) {
			params.q_str_name=$(".query-form").find(".q_str_name").val();
			return params;
		},
	}, "#table", columns);
	
	
	
	$('.btn-search').on("click",function(){
		sds.initPage(base + "/role/getRoleList", {
			queryParams : function(params) {
				//全查询
				params.q_str_roleName=$(".query-form").find("#roleName").val();
				params.q_str_roleType=$(".query-form").find("#roleType").val();
				return params;
			},
		}, "#table", columns);
	});
	
//单个删除	
	$('#table').on("click",".delete",function(){
		var id=$(this).data("id");
		var tr =$(this).parents("tr");
		bootbox.confirm("确定要删除吗?", function(result) {
			if(result){
				sds.sendPost(base+'/role/deleteRole/'+id,null);
				tr.remove();
	          }
			
		});
	});

	// 批量删除
	$('#deletebatch').on("click",function(){
		var ids = $("input[name='btSelectItem']:checked");
		var data=new Object();
		var id="";
		$(ids).each(function(index){
			if(index==0){
				id=$(this).parent().parent().find('input').eq(1).val();
			}else{
				id=id+","+$(this).parent().parent().find('input').eq(1).val();
			}
		})
		if(id!=""){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result){
					sds.sendPost(base+'/role/deleteRole/'+id,null)
				}
			});
		}else{
			bootbox.alert("请选择要删除的记录");
		}
		
	});
	
    
	
	
});

