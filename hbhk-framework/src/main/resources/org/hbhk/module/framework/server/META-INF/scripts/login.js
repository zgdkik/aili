$(function() {
	
		$("#loginForm").validate({
			rules:{
			username:"required",
			password:"required",
			validateCode:"required"
			},
			messages:{
				username:framework.hbhk.i18n('hbhk.user.inputusername'),
				password:framework.hbhk.i18n('hbhk.user.inputpass'),
				validateCode:framework.hbhk.i18n('hbhk.user.inputcode')
		    }
		});
		
});