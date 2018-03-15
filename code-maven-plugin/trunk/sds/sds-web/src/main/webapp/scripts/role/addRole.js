var setting = {
	check : {
		enable : true,
		chkboxType : { "Y" : "p", "N" : "s" }
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : onClick
	}
};
function onClick(event, treeId, treeNode, clickFlag) {
	alert();
}

function filter(treeId, parentNode, childNodes) {
	if (!childNodes)
		return null;
	for ( var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}
function filter(treeId, parentNode, childNodes) {
	if (!childNodes)
		return null;
	for ( var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}
$(document).ready(function() {
	    
	    
	    $('#addRole').bootstrapValidator({
//	        live: 'disabled',
	        message: 'This value is not valid',
	        fields: {
	        	roleCode: {
	                validators: {
	                    notEmpty: {
	                        message: '角色编码不能为空'
	                    },stringLength: {
	                        min: 3,
	                        max: 30,
	                        message: '角色编码必须在3-30个字符之间'
	                    },regexp: {
	                        regexp: /^[a-zA-Z0-9_\.]+$/,
	                        message: '角色编码必须由字母、数字或字母和数字组成'
	                    }, remote: {
	                    	  url: base+"/role/checkRoleCode",
	                    	  data: { roleCode:$("#roleCode").val(),id:$("#roleId").val()}
	                    }
	                }
	            },
	            roleName: {
	                validators: {
	                    notEmpty: {
	                        message: '角色名称不能为空'
	                    },stringLength: {
	                        min: 3,
	                        max: 30,
	                        message: '角色名称必须在3-30个字符之间'
	                    }
	                }
	            }, roleType: {
	                validators: {
	                    notEmpty: {
	                        message: '角色类型必须选择一个'
	                    }
	                }
	            }, roleDesc: {
	                validators: {
	                    notEmpty: {
	                        message: '角色描述不能为空'
	                    }
	                }
	            },
	            captcha: {
	                validators: {
	                    callback: {
	                        message: '答案为空或错误',
	                        callback: function(value, validator) {
	                            var items = $('#captchaOperation').html().split(' '), sum = parseInt(items[0]) + parseInt(items[2]);
	                            return value == sum;
	                        }
	                    }
	                }
	            }
	        }
	    });

	$.fn.zTree.init($("#fun-tree"), setting,funs);
	
    $('#save').click(function() {
    	$('#addRole').data('bootstrapValidator').validate();
    	var validate = $('#addRole').data('bootstrapValidator').isValid();
    	if(!validate){
    		return;
    	}
    	 var treeObj = $.fn.zTree.getZTreeObj("fun-tree");
         var nodes = treeObj.getCheckedNodes(true);
         var funCodes = "";
         for (var i = 0; i < nodes.length; i++) {
        	 if((i+1) == nodes.length){
        		 funCodes += nodes[i].id;
        	 }else{
        		 funCodes += nodes[i].id+",";
        	 }
         }
    	sds.sendPost(base+"/role/addRole", {
    		id:$("#roleId").val(),
    		roleCode:$("#roleCode").val(),
    		roleName:$("#roleName").val(),
    		roleType:$("#roleType").val(),
    		status:$("input[name=status]:checked").val(),
    		deptCode:$("#deptCode").val(),
    		roleDesc:$("#roleDesc").val(),
    		funCodes:funCodes
    	},{successHandler:function(data,
				 textStatus, jqXHR){
    		if(data.data!=null){
    			$("#roleId").val(data.data);
    			bootbox.alert(data.msg, function(result) {
    				location.href=base+"/role/roleList";
        	    });
    		}else{
    			bootbox.alert(data.msg);
    		}
						
		}});
   	
   });
 
    $('#resetBtn').click(function() {
        $('#addRole').data('bootstrapValidator').resetForm(true);
    });  
    $('.roleType').on('change', function(){
//    	  var selected = $('.selectpicker option:selected').val();
//    	  if("common"==selected){
//    		  $(".dept-group").hide();
//    	  }else{
//    		  $(".dept-group").show();
//    	  }
    });
    
});