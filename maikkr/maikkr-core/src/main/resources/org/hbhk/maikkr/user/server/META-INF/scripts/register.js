$j(document).ready(function() {
	
	getId("pwdId").focus(function(){
		claimEmail(getId("emailId"));
	});
	
	getId("pwdId").focus(function(){
		claimPwd(getId("pwdId"));
	});
	
	getId("nameId").focus(function(){
		claimName(getId("nameId"));
	});
	getId("virfyId").focus(function(){
		claimVirfy(getId("virfyId"));
	});
	getId("emailId").blur(function(){
		validateEmail(getId("emailId"));
	});
	
    getId("pwdId").blur(function(){
		validatePwd(getId("pwdId"));
    });
	getId("nameId").blur(function(){
		//昵称不做验证
		//validateUserName(getId("nameId"));
	});
	getId("virfyId").blur(function(){
		validateVirfy(getId("virfyId"));
	});
});
var arr={
		"emailState":"",
		"pwdState":"",
		"nameState":"",
		"virfyState":""
}
function claimEmail(obj){
	//clear(getId("emailId"));
	getId("emailNotice").css('display',"block");
	getId("emailError").css('display',"none");
	getId("emailTrue").css('display',"none");
	getId("emailLogin").css('display',"none");
}
function claimPwd(obj){
	 var cpwd = obj.val();
	if(cpwd==""){
		getId("pwdNotice").css('display',"block");
		getId("pwdError").css('display',"none");
		getId("pwdMedium").css('display',"none");
		getId("pwdGood").css('display',"none");
		
	}else if(cpwd.length<6&&cpwd.length>0&&cpwd.length>17){
		getId("pwdNotice").css('display',"none");
		getId("pwdError").css('display',"block");
		getId("pwdMedium").css('display',"none");
		getId("pwdGood").css('display',"none");
		return false;
	}else if(cpwd.length>6&&cpwd.length<12){
		getId("pwdNotice").css('display',"none");
		getId("pwdError").css('display',"none");
		getId("pwdMedium").css('display',"block");
		getId("pwdGood").css('display',"none");
		return true;
	}else if(cpwd.length>=12&&cpwd.length<=16){
		getId("pwdNotice").css('display',"none");
		getId("pwdError").css('display',"none");
		getId("pwdMedium").css('display',"none");
		getId("pwdGood").css('display',"block");
		return true;
	}
}
function claimName(obj){
	getId("nameNotice").css('display',"block");
	getId("nameError").css('display',"none");
	getId("nameNull").css('display',"none");
	getId("nameExist").css('display',"none");
	getId("nameTrue").css('display',"none");
	}
function claimVirfy(){
	getId("virfyNotice").css('display',"block");
	getId("virfyNull").css('display',"none");
	getId("virfyError").css('display',"none");
	}
function validateEmail(obj){
	var mail = obj.val();
	if(mail==""){
		getId("emailNotice").css('display',"none");
		getId("emailError").css('display',"block");
		getId("emailTrue").css('display',"none");
		getId("emailLogin").css('display',"none");
		arr.emailState="false";
	}else if(isEmail(mail)){
		checkEmailOut(mail);
	}else if(!isEmail(mail)){
		getId("emailNotice").css('display',"none");
		getId("emailError").css('display',"block");
		getId("emailTrue").css('display',"none");
		getId("emailLogin").css('display',"none");
		arr.emailState="false";
	}
}
function checkEmailOut(text){
	//以post的形式把用户输入的邮箱发到后台校验是否已存在
	//var url = "http://localhost:8080/blog/checkEmailServlet";
	var url = base+"security/validateEmail.htm";
	var mess={
		"mail":text
	};
	//var message = JSON.stringify(mess);
	//调用Ajax方法
	$j.ajax({
		url: url,
		type:"POST",
		data:mess,
		success: function(data, textStatus){
			getId("emailNotice").css('display',"none");
			getId("emailError").css('display',"none");
			getId("emailTrue").css('display',"block");
			getId("emailLogin").css('display',"none");
			arr.emailState="true";
		},
		exception:function(data, textStatus){
			getId("emailNotice").css('display',"none");
			getId("emailError").css('display',"none");
			getId("emailTrue").css('display',"none");
			getId("emailLogin").css('display',"block");
			arr.emailState="false";
		}
	});
	
}
function validateUserName(obj){
	var usermail = obj.val();
	if(usermail==""){
		getId("nameNotice").css('display',"none");
		getId("nameError").css('display',"none");
		getId("nameNull").css('display',"block");
		getId("nameExist").css('display',"none");
		getId("nameTrue").css('display',"none");
		arr.nameState="false";
	}else{
		if(usermail.length<4&&usermail.length>24){
			getId("nameNotice").css('display',"none");
			getId("nameError").css('display',"block");
			getId("nameNull").css('display',"none");
			getId("nameExist").css('display',"none");
			getId("nameTrue").css('display',"none");
		}else{
			for(i=0;i<usermail.length;i++){
				var temp = usermail.toLowerCase();
				var ch = temp.charAt(i);
				if((ch>='a'&&ch<='z')||(ch>=0&&ch<=9)||(ch=='_')||(ch=='-')){
					checkEmailOut(usermail);
				}else{
					getId("nameNotice").css('display',"none");
					getId("nameError").css('display',"block");
					getId("nameNull").css('display',"none");
					getId("nameExist").css('display',"none");
					getId("nameTrue").css('display',"none");
				}
			}
		}
	}
}

function callback2(text){
	//将json格式的text转化为js
	var object = JSON.parse(text);
	var obj = object.notice;
	if(obj=="canNot"){
		getId("nameNotice").css('display',"none");
		getId("nameNull").css('display',"none");
		getId("nameExist").css('display',"block");
		getId("nameTrue").css('display',"none");
		arr.nameState="false";
	}else if(obj=="can"){
		getId("nameNotice").css('display',"none");
		getId("nameNull").css('display',"none");
		getId("nameExist").css('display',"none");
		getId("nameTrue").css('display',"block");
		arr.nameState="true";
	}
}
function validatePwd(obj){
	var pwd = obj.val();
	if(pwd==""){
		getId("pwdNotice").css('display',"none");
		getId("pwdError").css('display',"block");
		getId("pwdMedium").css('display',"none");
		getId("pwdGood").css('display',"none");
		arr.pwdState="false";
	}else if(pwd.length<6&&pwd.length>0&&pwd.length>17){
		getId("pwdNotice").css('display',"none");
		getId("pwdError").css('display',"block");
		getId("pwdMedium").css('display',"none");
		getId("pwdGood").css('display',"none");
		arr.pwdState="false";
	}else if(pwd.length >= 6&&pwd.length<12){
		getId("pwdNotice").css('display',"none");
		getId("pwdError").css('display',"none");
		getId("pwdMedium").css('display',"block");
		getId("pwdGood").css('display',"none");
		arr.pwdState="true";
	}else if(pwd.length>=12&&pwd.length<=16){
		getId("pwdNotice").css('display',"none");
		getId("pwdError").css('display',"none");
		getId("pwdMedium").css('display',"none");
		getId("pwdGood").css('display',"block");
		arr.pwdState="true";
	}
}
function validateVirfy(obj){
	var code = obj.val();
	if(code==""){
		getId("virfyNotice").css('display',"none");
		getId("virfyNull").css('display',"block");
		getId("virfyError").css('display',"none");
		arr.virfyState="false";
	}else if(code.length==4){
		getId("virfyNotice").css('display',"none");
		getId("virfyNull").css('display',"none");
		getId("virfyError").css('display',"none");
		arr.virfyState="true";
	}
}

function click_register(){
	if(arr.emailState && arr.pwdState && arr.virfyState){
	  var registerMass={
			  "mail":getId("emailId").val(),
	          "name":getId("nameId").val(),
	          "password":getId("pwdId").val(),
	          "code":getId("virfyId").val()
	  };
	  var url =  base+"security/regist.htm";
	  $j.ajax({
			url: url,
			type:"POST",
			data:registerMass,
			success: function(data, textStatus){
				getId("emailId").val(null);
		        getId("nameId").val(null);
		        getId("pwdId").val(null);
		        getId("virfyId").val(null);
				window.location.href=base+"user/main.htm";
			},
			exception:function(data, textStatus){
				$j.toast(data.msg);
				getId("virfyNotice").css('display',"none");
				getId("virfyNull").css('display',"none");
				getId("virfyError").css('display',"block");
			}
		});
	}
}
function getId(id){
	return $j("#"+id);
}

function clear(obj){
	obj.val(null);
}
function isEmail(str){ 
	var reg =/^[\w\.-]+?@([\w\-]+\.){1,2}[a-zA-Z]{2,3}$/; 
	return str.match(reg);
} 
