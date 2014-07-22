$j(document).ready(function() {
	$j("#emailId").focus(function(){
		$j("#nameOrPwdId").css('display',"none");
		$j("#notExistId").css('display',"none");
	});
	$j("#emailId").blur(function(){
		validateEmailName($j("#emailId"));
		
	});
	$j("#pwdId").focus(function(){
		$j("#nameOrPwdId").css('display',"none");
		$j("#notExistId").css('display',"none");
	});
	$j("#pwdId").blur(function(){
		validatePwd($j("#pwdId"));
	});
	
	$j(".f_btn").click(function(){
		 if($j("#emailId").val()!=""&&$j("#pwdId").val()!=""){
		    	var url = base+"security/login.htm";
		    	var data = {
		        		"email":$j("#emailId").val(),
		        		"pwd":$j("#pwdId").val()
		        	};
		    	$j.ajax({
		    		url: url,
		    		type:"POST",
		    		data:data,
		    		success: function(data, textStatus){
		    			window.location.href=base+"user/main.htm";
		    		},
		    		exception:function(data, textStatus){
		    			$j("#notExistId").css('display',"block");
		    			$j("#nameOrPwdId").css('display',"block");
		    		}
		    	});
		    	
		    }else{
		    	window.location(base+"user/loginpage.htm");
		    }	
		
	});
});
function validateEmailName(obj){
	if(obj.val()==""){
		$j("#emailId").val("请输入邮箱账号");
	}
}
function validatePwd(obj){
	if(obj.val()==""){
		$j("#pwdId").val("请输入密码");
	}
}



