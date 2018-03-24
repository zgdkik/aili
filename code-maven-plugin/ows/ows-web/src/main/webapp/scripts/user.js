$(function(){
	fn_get_menu("ddwl_zjdd");
})
var dx;
function send_msg(){

	if(ckeck_phone()==false || 
			yn_phone==false ||
			check_phonepwd()==false ||
			check_cphonepwd()==false ||
			check_phonecode()==false){
		return;
	} 
	
	$("#sendMsg").attr("disabled","disabled");
	clearInterval(dx);
	var i = 60;
	dx = setInterval(function() {  
		if(i -- > 0) {  
			$("#sendMsg").val("稍等"+i+"秒"); 
		}else{
			$("#sendMsg").attr("disabled",false);
			$("#sendMsg").val("发送短信");  
		}
	}, 1000);
	$.ajax({url:base+"/register/getPhoneCode",type:"post",data:{'phone':$("#phone").val(),'phonecode':$('#phonecode').val()},dataType:"json",
		success:function(data){ 
			if(data=="1"){
				alert("短信已发送");
			}else if(data=='2'){
				alert("短信发送失败");
				clearInterval(dx);
				$("#sendMsg").attr("disabled",false);
				$("#sendMsg").val("发送短信");  
			}else if(data=='3'){
				clearInterval(dx);
				alert("验证码错误");
				$("#sendMsg").attr("disabled",false);
				$("#sendMsg").val("发送短信");  
			}
		}            
	});
}
function login(){
	if($("#loginName").val().length<1 ||
			$("#password").val().length<1){
		alert("请填写会员帐号/密码");
		return;
	}
	$.ajax({  
		url:base+"/register/login",
		type:"post",
		data:$("#loginInput").serialize(),
		dataType:"json",
		success:function(data){ 
			if(data.data=="1"){
				if(title=="登录注册"){
					location.href=base+"/ddwlGw/home";
				}else{
					location.reload();
				}
			}else if(data.data=='0'){
				alert("用户名或密码输入不正确");
				$("#password").val("").focus();
			}
		}            
	});
}
function reloadphonecode(){
    var verify=document.getElementById('imgphonecode');
    verify.setAttribute('src',base+'/register/createCode/phonecertpic?it='+Math.random());
}
function reloadmailcode(){
    var verify=document.getElementById('imgmailcode');
    verify.setAttribute('src',base+'/register/createCode/mailcertpic?it='+Math.random());
}


//check username
var yn_username = false;
function ckeck_username(){
	var u = /^[a-zA-Z][a-zA-Z0-9]{5,15}$/;
	var username = $("#uname").val();
	if(username==''||!u.test(username)){
		$("#err_username").html("请输入正确的用户名");
		return false;
	}
	$.post(base+"/register/checkPhone",{"phone":username,"compCode":'ddwl'},function(rv){
		if(rv.data==0){
			$("#err_username").html("<img src='"+base+"/images/ok.png' />");
			yn_username = true;
		}else{
			$("#err_username").html("用户名已被注册");
			yn_username = false;
		}
	});	
}


//check phone
var yn_phone = false;
function ckeck_phone(){
	var p = /^1[2-9]{1}[0-9]{9}$/;
	var phone = $("#phone").val();
	if(phone==''||!p.test(phone)){
		$("#err_phone").html("请输入正确的手机号");
		return false;
	}
	$.post(base+"/register/checkPhone",{"phone":phone,"compCode":'ddwl'},function(rv){
		if(rv.data==0){
			$("#err_phone").html("<img src='"+base+"/images/ok.png' />");
			yn_phone = true;
		}else{
			$("#err_phone").html("手机号已被注册");
			yn_phone = false;
		}
	});	
}

function check_phonepwd(){
	var pwd = $("#phonepwd").val();
	if(pwd.length<6 || pwd.length>16){
		$("#err_phonepwd").html("密码为6-16位字符");
		return false;
	}else{
		$("#err_phonepwd").html("<img src='"+base+"/images/ok.png' />");
	}
}

function check_cphonepwd(){
	var pwd = $("#phonepwd").val();
	var rePwd = $("#cphonepwd").val();
	if(pwd!=rePwd ||pwd.length==0){
		$("#err_cphonepwd").html("两次密码不同");
		return false;
	}else{
		$("#err_cphonepwd").html("<img src='"+base+"/images/ok.png' />");
	}
}
/**
 * @author zb134373
 * @see 验证码
 * @returns {Boolean}
 */
function check_phonecode(){
	var code = $("#phonecode").val();
	if(code==''){
		$("#err_phonecode").html("请填写验证码");
		return false;
	}else{
		var params = {'phonecode':code};
		$.ajax({  
			url:base+"/register/checkPhoneCode",
			type:"post",
			data:params,
			dataType:"json",
			success:function(arr){ 
				if(arr.data==1){
					$("#err_phonecode").html("<img src='"+base+"/images/ok.png' />");
					$('#sendMsg').attr('disabled',false);
					$('#sendMsg').attr('style','width:75px;height: 34px;background-color:white;border: 1px solid #ccc;line-height:34px;color: black;');
				}else if(arr.data==0){
					$("#err_phonecode").html("验证码错误");
					return false;
				}
			}            
		});
	}
}

function check_msgcode(){
	var code = $("#msgcode").val();
	if(code==''){
		$("#err_msgcode").html("请填写手机校验码");
		return false;
	}else{
		var phoneNo=$("#phone").val();
		var params={phoneNo:phoneNo,phoneCode:code};
		$.ajax({  
			url:base+"/register/checkSendCode",
			type:"post",
			data:params,
			dataType:"json",
			success:function(arr){ 
//				alert(arr.data); //去掉调试alert()；
				if(arr.data==1){
					$("#err_msgcode").html("<img src='"+base+"/images/ok.png' />");
				}else if(arr.data==0){
					$("#err_msgcode").html("短信验证码错误");
					return false;
				}
			}            
		});
		$("#err_msgcode").html("");
	}
}


function check_mailpwd(){
	var pwd = $("#mailpwd").val();
	if(pwd.length<6 || pwd.length>16){
		$("#err_mailpwd").html("密码为6-16位字符");
		return false;
	}else{
		$("#err_mailpwd").html("<img src='"+base+"/images/ok.png'/>");
	}
}
function check_cmailpwd(){
	var pwd = $("#mailpwd").val();
	var rePwd = $("#cmailpwd").val();
	if(pwd!=rePwd ||pwd.length==0){
		$("#err_cmailpwd").html("两次密码不同");
		return false;
	}else{
		$("#err_cmailpwd").html("<img src='"+base+"/images/ok.png' />");
	}
}

var yn_mail =false;
function check_mail(){
	var m = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
	var mail = $("#mail").val();
	if(mail==''||!m.test(mail)){
		$("#err_mail").html("邮箱格式不正确");
		return false;
	}
	$.post(base+"/register/checkMail",{"mail":mail},function(rv){
		if(rv.data==0){
			$("#err_mail").html("<img src='"+base+"/images/ok.png' />");
			yn_mail = true;
		}else{
			$("#err_mail").html("邮箱已被使用");
			yn_mail =false;
		}
	});	
} 

function check_mailcode(){
	var code = $("#mailcode").val();
	if(code==''){
		$("#err_mailcode").html("请填写验证码");
		return false;
	}else{
		var params = {'mailCode':code};
		$.ajax({  
			url:base+"/register/checkMailCode",
			type:"post",
			data:params,
			dataType:"json",
			success:function(arr){ 
				if(arr.data==1){
					$("#err_mailcode").html("<img src='"+base+"/images/ok.png' />");
				}else if(arr.data==0){
					$("#err_mailcode").html("验证码错误");
				}
			}            
		});
		$("#err_mailcode").html("");
	}
}

function mail_reg(){
	if(ckeck_username()==false || 
			yn_username==false ||
			check_mailpwd()==false ||
			check_cmailpwd()==false ||
			check_mail()==false ||
			yn_mail==false ||
			check_mailcode()==false){
		return;
	} else{
		var password=$("#mailpwd").val();
		var userName=$("#uname").val();
		var compCode='ddwl';
		var email=$("#mail").val();
		var param={userName:userName,password:password,compCode:compCode,email:email};
		$.ajax({  
			url:base+"/register/mobileRegist",
			type:"post",
			data:param,
			dataType:"json",
			success:function(arr){ 
				if(arr.data==1){
					$("#mail_form")[0].reset();
					$(".label").html("");
					reloadmailcode();
					alert("注册完成");
				}else{
					alert("无法注册");
				}
			}            
		});
	}
	
}



/**
 * @author zb134373
 * @see 注册
 */
function phone_reg(){
	if(ckeck_phone()==false || 
			yn_phone==false ||
			check_phonepwd()==false ||
			check_cphonepwd()==false ||
			check_phonecode()==false ||
			check_msgcode()==false){
		return;
	} 
	var password=$("#phonepwd").val();
	var userName=$("#phone").val();
	var compCode='ddwl';
	var param={userName:userName,password:password,compCode:compCode}
	$.ajax({  
		url:base+"/register/mobileRegist",
		type:"post",
		data:param,
		dataType:"json",
		success:function(arr){ 
			//1成功  2手机存在 3验证码错误 4短信错误 5失败
			if(arr.data=="1"){
				$("#phone_form")[0].reset();
				$(".label").html("");
				reloadphonecode();
				alert("注册完成");
			}else if(arr=='2'){
				alert("注册失败");
			}
		}            
	});
}