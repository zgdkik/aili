/** *************1. 基本信息的设定********************** */

/** *************2. 页面加载时设定********************** */

$(document).ready(function() {
	
	$('#dictKeyForm').bootstrapValidator({
//      live: 'disabled',
      message: 'This value is not valid',
      fields: {
    	  dictCode: {
              validators: {
                  notEmpty: {
                      message: '分类名称不能为空'
                  }
              }
          },
          dictName: {
              validators: {
                  notEmpty: {
                      message: '分类别名不能为空'
                  }
              }
          }
      }
  });
	
	
	$('#dictValueForm').bootstrapValidator({
//      live: 'disabled',
      message: 'This value is not valid',
      fields: {
    	  dataKey: {
              validators: {
                  notEmpty: {
                      message: '关键字不能为空'
                  }
              }
          },
          dataValue: {
              validators: {
                  notEmpty: {
                      message: '值不能为空'
                  }
              }
          },
          orderNo: {
              validators: {
                  notEmpty: {
                      message: '序号不能为空'
                  }
              }
          }
      }
  });
	
	var columns = [ {
		field : 'dictName',
		title : '分类名称'
	},{
		field : 'status',
		title : '状态',
		formatter:function(status){
			if(status=="1"){
				return "启用";
			}else{
				return "作废";
			}
		}
	},{
		field : 'dictCode',
		title : '操作列',
		formatter : function(dictCode,obj){
			if(obj.status==1){
				var toolmenu =  
					'<div class="btn-group btn-group-xs"> '+
					'<a type="button" class="btn btn-default info" data-id="'+dictCode+'"> <i class="fa fa-edit"></i>明细</a>'+
					'<a type="button" class="btn btn-default edit" data-id="'+dictCode+'"> <i class="fa fa-edit"></i>修改</a>'+
					'<a type="button" class="btn btn-default cancel" data-id="'+obj.id+","+dictCode+'"> <i class="fa fa-edit"></i>作废</a>'+
		       		'</div>';
			}else{
				var toolmenu =  
					'<div class="btn-group btn-group-xs"> '+
					'<a type="button" class="btn btn-default info" data-id="'+dictCode+'"> <i class="fa fa-edit"></i>明细</a>'+
					'<a type="button" class="btn btn-default edit" data-id="'+dictCode+'"> <i class="fa fa-edit"></i>修改</a>'+
					'<a type="button" class="btn btn-default start-using" data-id="'+obj.id+","+dictCode+'"> <i class="fa fa-edit"></i>启用</a>'+
		       		'</div>';
			}
			return toolmenu;
		}
	} ];
	sds.initPage(base + "/common/queryDictList", {
		queryParams : function(params) {
			return params;
		},
	}, "#KeyTable", columns);
	
	
	
	//显示明细
	$('#KeyTable').on("click",".info",function(){
		$("#dataInsert").attr("disabled",false);
		var dictCode=$(this).data("id");
		$("#dictCodeId").val(dictCode);
		communal(dictCode);
	});
	
	//显示修改页面
	$('#KeyTable').on("click",".edit",function(){
		var dictCode=$(this).data("id");
		$('#dictKeyForm').bootstrapValidator('validate');
		var validate = $('#dictKeyForm').data('bootstrapValidator').isValid();
		if(validate==true){
			sds.sendPost(base+"/common/showEditDictForm/"+dictCode,null,{successHandler:function(data,
					 textStatus, jqXHR){
				$("#dictId").val(data.data.id);
				$("#dictCode").val(data.data.dictCode);
				$("#dictName").val(data.data.dictName);
				document.getElementById("title").innerHTML=("修改分类");
				$("#edit").show();
				$("#save").hide();
				$('#myModal').modal('show');
				$("#dictCode").attr("disabled",true);
			}});
		}
	});
	
	$('#save').click(function() {
		$('#dictKeyForm').bootstrapValidator('validate');
		var validate = $('#dictKeyForm').data('bootstrapValidator').isValid();
		if(validate==true){
			sds.sendPost(base+"/common/addDict", {
				dictCode:$("#dictCode").val(),
				dictName:$("#dictName").val()
			},{successHandler:function(data,
					 textStatus, jqXHR){
				$('#myModal').modal('hide');
				bootbox.alert(data.msg, function(result) {
					if(result){
						location.href=base+"/common/showDictIndex";	
					}else{
						location.href=base+"/common/showDictIndex";	
					}
				});
							
			}});
		}
	});
	
	$('#edit').click(function() {
		$('#dictKeyForm').bootstrapValidator('validate');
		var validate = $('#dictKeyForm').data('bootstrapValidator').isValid();
		if(validate==true){
			sds.sendPost(base+"/common/editDict", {
				id:$("#dictId").val(),
				dictCode:$("#dictCode").val(),
				dictName:$("#dictName").val()
			},{successHandler:function(data,
					 textStatus, jqXHR){
					$('#myModal').modal('hide');
					bootbox.alert(data.msg, function(result) {
						if(result){
							location.href=base+"/common/showDictIndex";	
						}else{
							location.href=base+"/common/showDictIndex";	
						}
					});
			}});
		}
	});
	
	//作废
	$('#KeyTable').on("click",".cancel",function(){
		var parameter=$(this).data("id");
		var str=parameter.split(',');
		var id=str[0];
		var dictCode=str[1];
		bootbox.confirm("确定要作废吗?", function(result) {
			if(result){
				sds.sendPost(base+"/common/editDictStatus",{
					id:id,
					dictCode:dictCode,
					status:0
				},{successHandler:function(data,
						 textStatus, jqXHR){
					bootbox.alert(data.msg, function(result) {
						if(result){
							location.href=base+"/common/showDictIndex";	
						}else{
							location.href=base+"/common/showDictIndex";	
						}
					});
				}});
			}
		});
	});
	
	//启用
	$('#KeyTable').on("click",".start-using",function(){
		var parameter=$(this).data("id");
		var str=parameter.split(',');
		var id=str[0];
		var dictCode=str[1];
		sds.sendPost(base+"/common/editDictStatus",{
			id:id,
			dictCode:dictCode,
			status:1
		},{successHandler:function(data,
				 textStatus, jqXHR){
			bootbox.alert(data.msg, function(result) {
				if(result){
					location.href=base+"/common/showDictIndex";	
				}else{
					location.href=base+"/common/showDictIndex";	
				}
			});
		}});
	});
	
	
	$('#keep').click(function() {
		$('#dictValueForm').bootstrapValidator('validate');
		var validate = $('#dictValueForm').data('bootstrapValidator').isValid();
		if(validate==true){
			sds.sendPost(base+"/common/addDictValue", {
				dictCode:$("#dictCodeId").val(),
				key:$("#dataKey").val(),
				value:$("#dataValue").val(),
				orderNo:$("#orderNo").val(),
				remark:$("#remark").val()
			},{successHandler:function(data,
					 textStatus, jqXHR){
				$('#myModalValue').modal('hide');
				bootbox.alert(data.msg, function(result) {
					//重新填充数据
					communal($("#dictCodeId").val());
				});
							
			}});
		}
	});
	
	
	//显示数据修改页面
	$('#valueTable').on("click",".update",function(){
		var id=$(this).data("id");
		$('#dictValueForm').bootstrapValidator('validate');
		var validate = $('#dictValueForm').data('bootstrapValidator').isValid();
		if(validate==true){
			sds.sendPost(base+"/common/showUpdateForm/"+id,null,{successHandler:function(data,
					 textStatus, jqXHR){
				$("#valueId").val(data.data.id);
				$("#dataKey").val(data.data.key);
				$("#dataValue").val(data.data.value);
				$("#orderNo").val(data.data.orderNo);
				$("#remark").val(data.data.remark);
				document.getElementById("titleValue").innerHTML=("修改数据");
				$("#update").show();
				$("#keep").hide();
				$('#myModalValue').modal('show');
				$("#dataKey").attr("disabled",true);
			}});
		}
	});
	
	
	$('#update').click(function() {
		$('#dictValueForm').bootstrapValidator('validate');
		var validate = $('#dictValueForm').data('bootstrapValidator').isValid();
		if(validate==true){
			sds.sendPost(base+"/common/updateDict", {
				id:$("#valueId").val(),
				key:$("#dataKey").val(),
				value:$("#dataValue").val(),
				orderNo:$("#orderNo").val(),
				remark:$("#remark").val()
			},{successHandler:function(data,
					 textStatus, jqXHR){
					$('#myModalValue').modal('hide');
					bootbox.alert(data.msg, function(result) {
						//重新填充数据
						communal($("#dictCodeId").val());
					});
			}});
		}
	});
	
	
	//作废
	$('#valueTable').on("click",".delete",function(){
		var parameter=$(this).data("id");
		var str=parameter.split(',');
		var id=str[0];
		var dictCode=str[1];
		bootbox.confirm("确定要作废吗?", function(result) {
			if(result){
				sds.sendPost(base+"/common/editDictValueStatus",{
					id:id,
					dictCode:dictCode,
					status:0
				},{successHandler:function(data,
						 textStatus, jqXHR){
					bootbox.alert(data.msg, function(result) {
						//重新填充数据
						communal(dictCode);
					});
				}});
			}
		});
	});
	
	//启用
	$('#valueTable').on("click",".launch",function(){
		var parameter=$(this).data("id");
		var str=parameter.split(',');
		var id=str[0];
		var dictCode=str[1];
		sds.sendPost(base+"/common/editDictValueStatus",{
			id:id,
			dictCode:dictCode,
			status:1
		},{successHandler:function(data,
				 textStatus, jqXHR){
			bootbox.alert(data.msg, function(result) {
				//重新填充数据
				communal(dictCode);
			});
		}});
	});
	

});

//显示分类新增页面
function showInsert(){
	$("#dictCode").val("");
	$("#dictName").val("");
	$("#dictCode").attr("disabled",false);
	$("#edit").hide();
	$("#save").show();
	document.getElementById("title").innerHTML=("新增分类");
	$('#myModal').modal('show');
}



//显示分类数据新增页面
function showDataNew(){
	$("#dataKey").val("");
	$("#dataValue").val("");
	$("#orderNo").val("");
	$("#remark").val("");
	$("#update").hide();
	$("#keep").show();
	document.getElementById("titleValue").innerHTML=("新增数据");
	$('#myModalValue').modal('show');
}


function communal(dictCode){
	var columns = [{
		field : 'id',
		title : '操作列',
		formatter : function(id,obj){
			if(obj.status==1){
			var toolmenu =  
				'<div class="btn-group btn-group-xs"> '+
				'<a type="button" class="btn btn-default update" data-id="'+id+'"> <i class="fa fa-edit"></i>修改</a>'+
				'<a type="button" class="btn btn-default delete" data-id="'+id+","+dictCode+'"> <i class="fa fa-edit"></i>作废</a>'+
	       		'</div>';
			}else{
				var toolmenu =  
					'<div class="btn-group btn-group-xs"> '+
					'<a type="button" class="btn btn-default update" data-id="'+id+'"> <i class="fa fa-edit"></i>修改</a>'+
					'<a type="button" class="btn btn-default launch" data-id="'+id+","+dictCode+'"> <i class="fa fa-edit"></i>启用</a>'+
		       		'</div>';
			}
			return toolmenu;
		}
	},{
		field : 'key',
		title : '关键字'
	}, {
		field : 'value',
		title : '值',
	}, {
		field : 'status',
		title : '状态',
		formatter:function(status){
			if(status=="1"){
				return "启用";
			}else{
				return "作废";
			}
		}
	}, {
		field : 'orderNo',
		title : '显示顺序',
	}, {
		field : 'remark',
		title : '描述',
	}];

	sds.initPage(base + "/common/queryDictValueList", {
		queryParams : function(params) {
			params.q_str_dictCode=dictCode;
			return params;
		},
	}, "#valueTable", columns);
}






