$(function() {

	$('.login').click(function() {
		var user = $('.user').val();
		var pwd = $('.pwd').val();
		$.ajax( {    
		    url:base+'/login',
		    dataType:'text json', 
		    data:{
		    	"user" : user,
				"pwd" : pwd
		    },    
		    type:'post',
		    success:function(data) {
		    	if(data.success=="true" ||data.success ==true){
		    		$('.error-info').html("");
		    		if(base==""|| base==null){
		    			base = "/";
		    		}
					window.location.href = base;
				}else{
					$('.error-info').html("用户名或密码错误");
				}
		    },    
		    error : function(XMLHttpRequest, textStatus, errorThrown) {
		    }    
	}); 
	});
});
document.onkeydown = function(e) {
	var theEvent = window.event || e;
	var code = theEvent.keyCode || theEvent.which;
	if (code == 13) {
		$(".login").click();
	}
};
