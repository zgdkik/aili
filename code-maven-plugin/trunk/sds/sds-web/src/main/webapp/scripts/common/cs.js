$(document).ready(function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function() {};
	var columns = [{
		field : 'num',
		title : '编号',formatter : function(id,obj,num){
			return num+1;
		}
	}, {
		field : 'city',
		title : '城市',
		align : 'center'
	}, {
		field : 'workCode',
		title : '客服',
		align : 'center'
	}, {
		field : 'phone',
		title : '电话',
		align : 'center'
	}, {
		field : 'descp',
		title : '备注',
		align : 'center',
		acronyms:true
	}, {
		field : 'id',
		title : '操作列',
		formatter : function(id,obj){
			var	toolmenu =  
					'<div class="btn-group btn-group-xs"> '+
		       		'<a type="button" class="btn btn-default remove" href="javascript:void(0)" data-id="'+id+'"  > <i class="fa fa-edit"></i>删除</a>'+
		       		'</div>';
			return toolmenu;
		}
	}
	];
	sds.initPage(base + "/cs/getPagination", {
		queryParams : function(params) {
			params.q_sl_city=$("#city").val();
			return params;
		},
	},"#adminDemandTable", columns);
	
	$('.btn-search').on("click",function(){
		sds.initPage(base + "/cs/getPagination", {
			queryParams : function(params) {
				//全查询
				params.q_sl_city=$("#city").val();
				return params;
			},
		}, "#adminDemandTable", columns);
	});
	$('#adminDemandTable').on("click",".remove",function(){
		var id= $(this).data("id");
		sds.sendPost(base+"/cs/remove", {"id":id},{successHandler:function(data,
				 textStatus, jqXHR){
   		if(data.success){
   			bootbox.alert("删除成功");
   			sds.initPage(base + "/cs/getPagination", {
					queryParams : function(params) {
						//全查询
						params.q_sl_city=$("#city").val();
						return params;
					},
				}, "#adminDemandTable", columns);
   		}else{
   			bootbox.alert(data.msg);
   		}
		}});
	});
	sds.selectAjax(base + "/user/getemplist");
	$('body').on("click","#add",function(){
		$("#my-modal").modal('show');
		$(".city").val("").trigger("change");
		$(".workCode").val("").trigger("change");
	});
	
	$('body').on("click",".save",function(){
		$('#cs-form').data('bootstrapValidator').validate();
    	var validate = $('#cs-form').data('bootstrapValidator').isValid();
    	if(!validate){
    		return;
    	}
    	var save = $(this);
    	$(this).attr("disabled","true");
		var param = $("#cs-form").getFormObj();
    	sds.sendPost(base+"/cs/save", param,{successHandler:function(data,
				 textStatus, jqXHR){
    		if(data.success){
    			$('#my-modal').modal('hide');
    			bootbox.alert(data.msg,function(){
    				
    				sds.initPage(base + "/cs/getPagination", {
       					queryParams : function(params) {
       						//全查询
       						params.q_sl_city=$("#city").val();
       						return params;
       					},
       				}, "#adminDemandTable", columns);
    			});
    		}else{
    			bootbox.alert(data.msg);
    		}
		}});
    	save.removeAttr("disabled");
	});
	
	 $('#cs-form').bootstrapValidator({
	        live: 'enabled',
	        message: '输入值没有验证通过',
	        fields: {
	        	workCode: {
	                validators: {
	                    notEmpty: {
	                    	  message: '请选择客服'
	                    }
	                }
	            },
	            city: {
	                validators: {
	                    notEmpty: {
	                        message: '请选择城市'
	                    }
	                }
	            },
	            phone: {
	                validators: {
	                    notEmpty: {
	                        message: '电话不能为空!'
	                    }
//	            		,regexp: {
//	                        regexp: /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/,
//	                        message: '请输入有效的电话号码！'
//	                    }
	                }
	            }, descp: {
	                validators: {
	                	stringLength : {
							min : 0,
							max : 200,
							message : '备注最长200个字符'
						}
	                }
	            }
	        }
	    });
});
