function isEmail(str){ 
	if(str==null || str==""){
		return false;
	}
	var reg =/^[\w\.-]+?@([\w\-]+\.){1,2}[a-zA-Z]{2,3}$/; 
	return str.match(reg);
};
$(document).ready(function() {

$("body").on("click",".reg",function(){
	 var username =  $(".username").val();
	 var email =  $(".email").val();
	 var pwd1 =  $(".pwd1").val();
	 var pwd2 =  $(".pwd2").val();
	 var code =  $(".code").val();
	
	 if(checkALlEmpty(".required","必填项") == false){
		 return ;
	 }
	 if(pwd1 != pwd2){
		 tips(".email","两次密码不一致");
		 return;
	 }
	 if(pwd1.length<6){
		 tips(".reg","密码至少要6位");
		 return;
		 
	 }
	 if(isEmail(email)==false){
		 tips(".email","邮箱格式不正确");
		 return;
	 }
	 var url =  base+"security/regist.htm";
	 var data = {
   		"mail":username,
   		"name":email,
   		"code":code,
   		"password":pwd1
   	};
	 $.ailiAjax({
		url: url,
		type:"POST",
		data:data,
		success: function(data, textStatus){
			if(returnUrl!=null && returnUrl!=""){
	    		window.location.href=returnUrl;
	    	}else{
	    		window.location.href="http://"+host;
	    	}
		},
		exception:function(data, textStatus){
			tips(".reg",data.msg);
		}
	 });
});

});