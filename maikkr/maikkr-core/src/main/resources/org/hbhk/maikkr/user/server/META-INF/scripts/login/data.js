$(document).ready(function() {
	 var tooltips = $("input[title]").tooltip({
	      position: {
	        my: "left top",
	        at: "right+5 top-5"
	      },
	      open: function(event, ui) {
	    	  var val = $(this).val()
	    	  if(val != null &&  val!=""){
	    		  return;
	    	  }
	      }
	    });

	 $("body").on("click",".login",function(){
		 var email =  $(".email").val()
		 var pwd =  $(".password").val()
		 if(email=="" || pwd==""){
			 tooltips.tooltip("open");
			 return;
		 }
		 var url = base+"security/login.htm";
    	 var data = {
        		"email":email,
        		"pwd":pwd
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
    			tips(".login","用户名或密码错误");
    		}
    	 });
	 });
	 
	 $("body").on("click",".forget-pwd",function(){
		 window.location.href=base+"user/forget.htm"
	 });
});
