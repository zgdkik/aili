window.onload=processUserName;
 //onload="processUserName();"
function processUserName(){
	$("emailId").onfocus=function(){
		clear($("emailId"));
		$("nameOrPwdId").style.display="none";
		$("notExistId").style.display="none";
	}
	$("emailId").onblur=function(){
		validateEmailName($("emailId"));
		
	}
	$("pwdId").onfocus=function(){
		clear($("pwdId"));
		$("nameOrPwdId").style.display="none";
		$("notExistId").style.display="none";
	}
	$("pwdId").onblur=function(){
		validatePwd($("pwdId"));
	}
}
function validateEmailName(obj){
	if(obj.value==""){
		$("emailId").value="请输入邮箱账号";
	}
}
function validatePwd(obj){
	if(obj.value==""){
		$("pwdId").value="请输入密码";
	}
}

function click_btn(){
	//alert("jflkasdjflka");
    if($("emailId").value!=""&&$("pwdId").value!=""){
    	var url = "/blog/loginAction.do";
    	var message = {
        		"userEmail":$("emailId").value,
        		"userPwd":$("pwdId").value
        	};
    	var  str=JSON.stringify(message);
    	Ajax.request(url, "POST", str, "TEXT", callback);
    	
    }else{
    	window.location("../jsp/login.jsp");
    }	
}
function callback(text){
	//alert(text);//这里
	var backMessage = JSON.parse(text);
	if(backMessage.loginBack=="NotExist"){
		
		$("notExistId").style.display="block";
		$("nameOrPwdId").style.display="none";
	}
	if(backMessage.loginBack=="Error"){
		$("nameOrPwdId").style.display="block";
		$("notExistId").style.display="none";
	}
	if(backMessage.loginBack=="YES"){
		window.location.href=backMessage.address;
//		window.location = "../bloghomepage.html";
	}
}
function clear(obj){
	obj.value="";
}
function $(id){
	return document.getElementById(id);
}
