<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<style type="text/css">
body{
	background-image:url(bg.png);
	font-family: 'Oleo Script', cursive;
}

.lg-container{
	width:275px;
	margin:100px auto;
	padding:20px 40px;
	border:1px solid #f4f4f4;
	background:rgba(255,255,255,.5);
	-webkit-border-radius:10px;
	-moz-border-radius:10px;

	-webkit-box-shadow: 0 0 2px #aaa;
	-moz-box-shadow: 0 0 2px #aaa;
}
.lg-container h1{
	font-size:40px;
	text-align:center;
}
#lg-form > div {
	margin:10px 5px;
	padding:5px 0;
}
#lg-form label{
	display: none;
	font-size: 20px;
	line-height: 25px;
}
#lg-form input[type="text"],
#lg-form input[type="password"]{
	border:1px solid rgba(51,51,51,.5);
	-webkit-border-radius:10px;
	-moz-border-radius:10px;
	padding: 5px;
	font-size: 16px;
	line-height: 20px;
	width: 100%;
	font-family: 'Oleo Script', cursive;
	text-align:center;
}
#lg-form div:nth-child(3) {
	text-align:center;
}
#lg-form button{
	font-family: 'Oleo Script', cursive;
	font-size: 18px;
	border:1px solid #000;
	padding:5px 10px;
	border:1px solid rgba(51,51,51,.5);
	-webkit-border-radius:10px;
	-moz-border-radius:10px;

	-webkit-box-shadow: 2px 1px 1px #aaa;
	-moz-box-shadow: 2px 1px 1px #aaa;
	cursor:pointer;
}
#lg-form button:active{
	-webkit-box-shadow: 0px 0px 1px #aaa;
	-moz-box-shadow: 0px 0px 1px #aaa;
}
#lg-form button:hover{
	background:#f4f4f4;
}
#message{width:100%;text-align:center}
.success {
	color: green;
}
.error {
	color: red;
}
</style>
<script type="text/javascript" src="${base}/scripts/core/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#login").click(function(){

		var action = '${base}/login';
		var form_data = {
			userName: $("#username").val(),
			pwd: $("#password").val()
		};
		$.ajax({
			type: "POST",
			url: action,
			data: form_data,
			success: function(response)
			{
				if(response == "success"){
					window.location.href='${base}/list';
				}else{
					$("#message").html('<p class="error">错误: 用户名或密码错误.</p>');
				}
			}
		});
		return false;
	});
});
	
</script>
</head>
<body>
	<div class="lg-container">
		<h1>管理员登录</h1>
		<form id="lg-form" name="lg-form" >

			<div>
				<label for="username">用户名:</label> <input type="text"
					name="userName" id="username" placeholder="用户名" />
			</div>

			<div>
				<label for="password">密  码:</label> <input type="password"
					name="pwd" id="password" placeholder="密码" />
			</div>

			<div>
				<button type="button" id="login">登录</button>
			</div>

		</form>
		<div id="message"></div>
	</div>
</body>
</html>