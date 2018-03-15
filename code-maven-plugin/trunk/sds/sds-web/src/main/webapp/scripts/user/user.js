$(document).ready(function() {
	var columns = [  {
		field : 'userName',
		title : '用户名'
	}, {
		field : 'type',
		title : '用户类型',
		formatter:function(type){
			if(type==1){
				return "系统用户";
			}else if(type==2){
				return "企业用户";
			}else{
				return "企业子账号";
			}
		}
	}, {
		field : 'beginTime',
		title : '开始时间',
		formatter : function(date){
        	return sds.formatDate(date);
		}
	}, {
		field : 'endTime',
		title : '结束时间',
		formatter : function(date){
        	return sds.formatDate(date);
		}
	}, {
		field : 'status',
		title : '状态',
		formatter : function(data){
			if(data==1){
				return '正常';
			}else if(data==2){
				return '禁用';
			}
		}
	}, {
		field : 'createUser',
		title : '创建人'
	},{
		field : 'createTime',
		title : '创建时间',
		formatter : function(date){
        	return sds.formatDate(date);
		}
	}, {
		field : 'updateUser',
		title : '更新人'
	}, {
		field : 'updateTime',
		title : '更新时间',
		formatter : function(date){
        	return sds.formatDate(date);
		}
	}, {
		field : 'lastLogin',
		title : '最后登录',
		formatter : function(date){
        	return sds.formatDate(date);
		}
	},{
		field : 'userName',
		title : '操作列',
		formatter : function(id,obj){
			var menu ="";
			if(obj.status==1){
				menu='<a type="button" class="btn btn-default lock" data-pid="'+obj.id+'"> <i class="fa fa-edit"></i>禁用</a>'
			}else if(obj.status==2){
				menu='<a type="button" class="btn btn-default unlock" data-pid="'+obj.id+'"> <i class="fa fa-edit"></i>启用</a>'
			}
			var toolmenu =  
				'<div class="btn-group btn-group-xs"> '+
				'<input type="hidden" value="'+obj.id+'"/>'+
	       		'<a type="button" class="btn btn-default" href="'+base+'/user/toupdateuser?id='+obj.id+'"> <i class="fa fa-edit"></i>修改</a>'+
	       		'<a type="button" class="btn btn-default delete" data-pid="'+obj.id+'"> <i class="fa fa-edit"></i>删除</a>'+
	       		menu+
	       		'</div>';
			return toolmenu;
		}
	} ];
	
	queryData();
	
	$('.btn-search').on("click",function(){
		queryData();
	});
	//根据ID删除单挑数据	
	$('#table').on("click",".delete",function(){
		var id=$(this).data("pid");
		bootbox.confirm("确定要删除吗?", function(result) {
			if(result){
				sds.sendPost(base+'/user/deleteuser/'+id,null,{
		    		successHandler:function(data,textStatus, jqXHR){
		    			if(data.success){
		    				queryData();
		    				bootbox.alert(data.msg);
		    			}
		    		}});
			}
		});
	});
    
	
	$('#table').on("click",".lock",function(){
		var id=$(this).data("pid");
		bootbox.confirm("确定要禁用吗?", function(result) {
			if(result){
				sds.sendPost(base+'/user/lockuser/'+id,null,{
		    		successHandler:function(data,textStatus, jqXHR){
		    			if(data.success){
		    				queryData();
		    				bootbox.alert(data.msg);
		    			}
		    		}});
			}
		});
	})
	
	$('#table').on("click",".unlock",function(){
		var id=$(this).data("pid");
		bootbox.confirm("确定要解禁吗?", function(result) {
			if(result){
				sds.sendPost(base+'/user/unlockuser/'+id,null,{
		    		successHandler:function(data,textStatus, jqXHR){
		    			if(data.success){
		    				queryData();
		    				bootbox.alert(data.msg);
		    			}
		    		}});
			}
		});
	})
	
    $('#adduser').click(function(){
    	location.href=base+"/user/toadduser";
    })
    
    function queryData(){
    	sds.initPage(base + "/user/getuserlist", {
    		queryParams : function(params) {
    			 var formObj = $("#role-form").getFormObj();
    			for(var p in formObj) {
    				//属性名称 
	    			var name=p;
	    			//属性对应的值 
	    			var val=formObj[p];
	    			params[name]=val; 
    			} 
    			return params;
    		}
    	}, "#table", columns);
    }

});
