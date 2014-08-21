$j(document).ready(function() {
	$j(".btn-default").click(function(){
		queryUser();
	});
});


function queryUser() {
	var nickname = $j('#name').val();
	if(nickname==null || nickname==""){
		$j.toast("用户名为空");
		return;
	}
	var email = $j('#email').val();
	if(email==null || email==""){
		$j.toast("邮箱为空");
		return;
	}
	
	var code = $j('#code').val();
	if(code==null || code==""){
		$j.toast("验证码为空");
		return;
	}
	$j.ajax({
		url : base + "user/findPwd.htm",
		type : "POST",
		async : false, 
		data:{'user':nickname,"email":email,"code":code},
		success : function(data, textStatus) {
			$j.toast(data.msg);
		},
		exception : function(data, textStatus) {
			$j.toast(data.msg);
		}
		});
};
function updatePwd() {
	var rpwd= $j('#rpwd').val();
	var pwd= $j('#pwd').val();
	var cpwd= $j('#cpwd').val();
	if(validate()==false){
		return ;
	}
	if(pwd!=cpwd){
		$j.toast("两次密码不一致");
	}
	$j.ajax({
		url : base + "user/updatePwd.htm",
		type : "POST",
		async : false, 
		data:{'pwd':pwd,'rpwd':rpwd},
		success : function(data, textStatus) {
			$j.toast("修改成功");
		},
		exception : function(data, textStatus) {
			$j.toast("修改失败");
		}
		});
};
