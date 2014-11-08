$(document).ready(function() {
	$(".find").click(function(){
		var me = $(this);
		queryUser(me);
	});
});


function queryUser(me) {
	var nickname = $('#name').val();
	if(nickname==null || nickname==""){
		tips('#name',"用户名为空");
		return;
	}
	var email = $('#email').val();
	if(email==null || email==""){
		tips('#email',"邮箱为空");
		return;
	}
	
	var code = $('#code').val();
	if(code==null || code==""){
		tips('#code',"验证码为空");
		return;
	}
	$.ailiAjax({
		url : base + "user/findPwd.htm",
		type : "POST",
		async : false, 
		data:{'user':nickname,"email":email,"code":code},
		success : function(data, textStatus) {
			ctips(me,data.msg);
		},
		exception : function(data, textStatus) {
			ctips(me,data.msg);
		}
		});
};

