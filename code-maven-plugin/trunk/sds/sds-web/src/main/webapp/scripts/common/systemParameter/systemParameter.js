/** *************1. 基本信息的设定********************** */

/** *************2. 页面加载时设定********************** */

$(document).ready(function() {
	var columns = [ {
		checkbox : true
	}, {
		field : 'sysKey',
		title : '键'
	}, {
		field : 'sysValue',
		title : '值'
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
	}, {
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
	       		'<a type="button" class="btn btn-default update" data-id="'+id+'"> <i class="fa fa-update"></i>修改</a>'+
	       		'<a type="button" class="btn btn-default delete" data-id="'+id+'"> <i class="fa fa-detele"></i>删除</a>'+
	       		'</div>';
			return toolmenu;
		}
	} ];	
	
	
    $('#addForm').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        fields: {
        	sysKey: {
                validators: {
                    notEmpty: {
                        message: '系统参数的键不能为空'
                    },stringLength: {
                        min: 3,
                        max: 30,
                        message: '系统参数的键必须在3-30个字符之间'
                    }/*,remote: {
                    	  url: base+"/systemParameter/checkSysKey",
                    	  data: { sysKey:$("#sysKey1").val(),id:$("#sysId").val()}
                    }*/
                }
            },
            sysValue: {
                validators: {
                    notEmpty: {
                        message: '系统参数的值不能为空'
                    },stringLength: {
                        min: 1,
                        max: 30,
                        message: '系统参数的值必须在1-30个字符之间'
                    },different: {
                        field: 'sysKey',
                        message: '系统参数的键和系统参数的值不同相同'
                    }
                }
            }
        }
    });
	
	sds.initPage(base + "/common/getSystemParameterList", {
		queryParams : function(params) {
			params.q_str_name=$(".query-form").find(".q_str_name").val();
			return params;
		},
	}, "#table", columns);
	
	
	
	$('.btn-search').on("click",function(){
		sds.initPage(base + "/common/getSystemParameterList", {
			queryParams : function(params) {
				//全查询
				params.q_str_sysKey=$(".query-form").find("#sysKey").val();
				params.q_str_sysValue=$(".query-form").find("#sysValue").val();
				return params;
			},
		}, "#table", columns);
	});
	
	// 添加的保存
	$('#save').click(function() {
    	$('#addForm').data('bootstrapValidator').validate();
    	var validate = $('#addForm').data('bootstrapValidator').isValid();
    	if(!validate){
    		return;
    	}
    	sds.sendPost(base+"/common/addSystemParameter", {
    		id:$("#sysId").val(),
    		sysKey:$("#sysKey1").val(),
    		sysValue:$("#sysValue1").val()
    		//status:$("input[name=status]:checked").val()
    	},{successHandler:function(data,
				 textStatus, jqXHR){
    		if(data.data!=null){
    			$("#sysId").val(data.data);
    			$('#myModal').modal('hide');
    			bootbox.alert(data.msg, function(result) {
    				location.href=base+"/common/systemParameterList";
        	    });
    		}else{
    			bootbox.alert(data.msg);
    		}
						
		}});
   });
//修改页面	
	$('#table').on("click",".update",function(){
		var id=$(this).data("id");
		$('#addForm').bootstrapValidator('validate');
		var validate = $('#addForm').data('bootstrapValidator').isValid();
		if(validate==true){
			sds.sendPost(base+"/common/showSystemParameterForm?id="+id,null,{successHandler:function(data,
					 textStatus, jqXHR){
				$("#sysId").val(data.data.id);
				$("#sysKey1").val(data.data.sysKey);
				$("#sysValue1").val(data.data.sysValue);
				$("#updatetitle").html("修改参数");
				$("#update").show();
				$("#save").show();
				$("#sysKey1").attr("readonly","readonly");
				$('#myModal').modal('show');
				
				$("#id").attr("true",true);
			}});
		}
	});

	
	
//单个删除	
	$('#table').on("click",".delete",function(){
		var id=$(this).data("id");
		var tr =$(this).parents("tr");
		bootbox.confirm("确定要删除吗?", function(result) {
			if(result){
				sds.sendPost(base+'/common/deleteSystemParameter/'+id,null);
				tr.remove();
	          }
			
		});
	});

	
});

//进入新增页面
function showAdd(){
	$("#sysId").val("");
	$("#sysKey1").val("");
	$("#sysValue1").val("");
	$("#updatetitle").html("新增参数");
	$("#sysKey1").removeAttr("readonly");
	$('#myModal').modal('show');
}

