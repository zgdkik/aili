$(document).ready(function() {
	 function randomNumber(min, max) {
	        return Math.floor(Math.random() * (max - min + 1) + min);
	    };
	    $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));

	    $('#defaultForm').bootstrapValidator({
//	        live: 'disabled',
	        message: 'This value is not valid',
	        fields: {
	            userName: {
	                message: 'The username is not valid',
	                validators: {
	                    notEmpty: {
	                        message: '用户名不能为空'
	                    },
	                    remote: {
	                    	  url: base+"/user/checkUserName",
	                    	  data: { userName:$("#userName").val()}
	                    }
	                }
	            }
/*	           roleids: {
	                validators: {
	                    choice: {
	                        min: 1,
	                        message: '至少选择一个角色'
	                    }
	                }
	            }*/
	        }
	    });

    $('#validateBtn').click(function() {
        $('#defaultForm').bootstrapValidator('validate');
        var validate = $('#defaultForm').validate().form();
    });

    $('#resetBtn').click(function() {
        $('#defaultForm').data('bootstrapValidator').resetForm(true);
    });
    
   $("#adduser").click(function(){
	var data = $('#defaultForm').getFormObj();
	var validate = $('#defaultForm').data('bootstrapValidator').isValid();
	if(!validate){
		return;
	}
	delete data.captcha;
	delete data.confirmPassword;
	data = propertyToArray(data, "roleids");
	sds.sendJson(base+'/user/adduser',data,{
		successHandler:function(data,textStatus, jqXHR){
			if(!data.exception){
				bootbox.alert(data.msg,function(){
					window.location.href=base+"/user/userlist";
				});
			}else{
				bootbox.alert(data.msg);
			}
			
		}});
    });
   $('#keepRenderingSort').multiselect({
		keepRenderingSort: true
	});
   
   //BD搜索
   sds.selectAjax(base+"/user/getuserbyemplist");
   	
   /*$(".selectpicker-ajax").on("change",function(){
   	try {
   		 var data = $(".selectpicker-ajax").select2("data");
   		 if(data!=null && data.length>0){
    			var phone = data[0].descp;
    	    	var name = data[0].name;
    	    	$("#empName").val(name);
    	    	$("#empPhone").val(phone);
    	        $('#defaultForm').bootstrapValidator('validate');
    	 }
		} catch (e) {
		}
   });*/
   
//	$('#userName').autocomplete({
//        source:function(query,process){
//            $.post(base+"/user/getEmpList",{"name":query},function(respData){
//                return process(respData);
//            });
//        },
//        formatItem:function(item){
//            return  item["name"]+" - "+item["workeCode"];
//        },
//        setValue:function(item){
//            return {'data-value':item["name"]+" - "+item["workeCode"],'real-value':item["workeCode"]};
//        }
//    });

});