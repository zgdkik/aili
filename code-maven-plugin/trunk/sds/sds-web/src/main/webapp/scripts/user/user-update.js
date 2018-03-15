$(document).ready(function() {
	 function randomNumber(min, max) {
	        return Math.floor(Math.random() * (max - min + 1) + min);
	    };
	    $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));

	    $('#defaultForm').bootstrapValidator({
//	        live: 'disabled',
	        message: 'This value is not valid',
	        fields: {
	            roles: {
	                validators: {
	                    notEmpty: {
	                        message: '用户角色不能为空'
	                    }
	                }
	            }
	         }
	    });

    $('#resetBtn').click(function() {
        $('#defaultForm').data('bootstrapValidator').resetForm(true);
    });
    
       $("#updateuser").click(function(){
    	var data = $('#defaultForm').getFormObj();
    	var roleids = new Array();
    	$("#keepRenderingSort_to").find("option").each(function(){
    		roleids.push($(this).val());
    	})
    	data.roleids=roleids;
    	var validate = $('#defaultForm').data('bootstrapValidator').isValid();
    	if(!validate){
    		return;
    	}
    	sds.sendJson(base+'/user/updateuser',data,{
    		successHandler:function(data,textStatus, jqXHR){
    			bootbox.alert(data.msg,function(){
    				window.location.href=base+"/user/userlist";
    			});
    		}});
    });
       $('#keepRenderingSort').multiselect({
   		keepRenderingSort: true
   	});
    

    

});