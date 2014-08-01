$j(document).ready(function() {
	// 1.禁止使用全局变量,可以使用module标签生成的模块名的object对象{}
	// 用法：模块名.自定义变量
	// 2.对象都是用Ext.define定义的方式声明
	$j("#login").click(function() {
		var email = $j("#email").val();
		var pwd = $j("#pwd").val();
		if (email == null || email=="") {
			$j.toast("用户名为空");
			return;
		}
		if (pwd == null||pwd=="") {
			$j.toast("密码为空");
			return;
		}
		var data = {
				'email' : email,
				'pwd' : pwd
			};
		$j.ajax({
			url :  base + "backend/login.htm",
			type : "POST",
			data : data,
			success : function(data, textStatus) {
				window.location.href = base + "backend/main.htm";
			},
			exception : function(data, textStatus) {
				$j.toast(data.msg);
			}
		});
	});
});