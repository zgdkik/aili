window.onload=processUserName;
// onload="processUserName();"
function processUserName(){
	$("emailId").onfocus=function(){
		claimEmail($("emailId"));
	}
	$("pwdId").onfocus=function(){
		
		claimPwd($("pwdId"));
	}
	$("nameId").onfocus=function(){
		claimName($("nameId"));
	}
	$("virfyId").onfocus=function(){
		claimVirfy($("virfyId"));
	}
	$("emailId").onblur=function(){
		validateEmail($("emailId"));
	}
	
    $("pwdId").onblur=function(){
		validatePwd($("pwdId"));
	}
	$("nameId").onblur=function(){
		validateUserName($("nameId"));
	}
	$("virfyId").onblur=function(){
		validateVirfy($("virfyId"));
	}
}
arr={
		"emailState":"",
		"pwdState":"",
		"nameState":"",
		"virfyState":""
}
function claimEmail(obj){
	clear($("emailId"));
	$("emailNotice").style.display="block";
	$("emailError").style.display="none";
	$("emailTrue").style.display="none";
	$("emailLogin").style.display="none";
}
function claimPwd(obj){
	if(obj.value==""){
		$("pwdNotice").style.display="block";
		$("pwdError").style.display="none";
		$("pwdMedium").style.display="none";
		$("pwdGood").style.display="none";
		
	}else if(obj.value.length<6&&obj.value.length>0&&obj.value.length>17){
		$("pwdNotice").style.display="none";
		$("pwdError").style.display="block";
		$("pwdMedium").style.display="none";
		$("pwdGood").style.display="none";
		return false;
	}else if(obj.value.length>6&&obj.value.length<12){
		$("pwdNotice").style.display="none";
		$("pwdError").style.display="none";
		$("pwdMedium").style.display="block";
		$("pwdGood").style.display="none";
		return true;
	}else if(obj.value.length>=12&&obj.value.length<=16){
		$("pwdNotice").style.display="none";
		$("pwdError").style.display="none";
		$("pwdMedium").style.display="none";
		$("pwdGood").style.display="block";
		return true;
	}
}
function claimName(obj){
	$("nameNotice").style.display="block";
	$("nameError").style.display="none";
	$("nameNull").style.display="none";
	$("nameExist").style.display="none";
	$("nameTrue").style.display="none";
	}
function claimVirfy(){
	$("virfyNotice").style.display="block";
	$("virfyNull").style.display="none";
	$("virfyError").style.display="none";
	}
function validateEmail(obj){
	if(obj.value==""){
		$("emailNotice").style.display="none";
		$("emailError").style.display="block";
		$("emailTrue").style.display="none";
		$("emailLogin").style.display="none";
		arr.emailState="false";
	}else if(isEmail(obj)){
		checkEmailOut(obj.value);
	}else if(!isEmail(obj)){
		$("emailNotice").style.display="none";
		$("emailError").style.display="block";
		$("emailTrue").style.display="none";
		$("emailLogin").style.display="none";
		arr.emailState="false";
	}
}
function checkEmailOut(text){
	//以post的形式把用户输入的邮箱发到后台校验是否已存在
	//var url = "http://localhost:8080/blog/checkEmailServlet";
	var url = "../checkEmailAction.do";
	var mess={
		"userEmail":text
	};
	var message = JSON.stringify(mess);
	//调用Ajax方法
	Ajax.request(url, "POST", message, "TEXT", callback);
	
}
function callback(text){
	//将json格式的text转化为js
	var object = JSON.parse(text);
	var obj = object.notice;
	if(obj=="canNot"){
		$("emailNotice").style.display="none";
		$("emailError").style.display="none";
		$("emailTrue").style.display="none";
		$("emailLogin").style.display="block";
		arr.emailState="false";
	}else if(obj=="can"){
		$("emailNotice").style.display="none";
		$("emailError").style.display="none";
		$("emailTrue").style.display="block";
		$("emailLogin").style.display="none";
		arr.emailState="true";
	}
}
function validateUserName(obj){
	if(obj.value==""){
		$("nameNotice").style.display="none";
		$("nameError").style.display="none";
		$("nameNull").style.display="block";
		$("nameExist").style.display="none";
		$("nameTrue").style.display="none";
		arr.nameState="false";
	}else{
		if(obj.value.length<4&&obj.value.length>24){
			$("nameNotice").style.display="none";
			$("nameError").style.display="block";
			$("nameNull").style.display="none";
			$("nameExist").style.display="none";
			$("nameTrue").style.display="none";
		}else{
			for(i=0;i<obj.value.length;i++){
				var temp = obj.value.toLowerCase();
				var ch = temp.charAt(i);
				if((ch>='a'&&ch<='z')||(ch>=0&&ch<=9)||(ch=='_')||(ch=='-')){
					checkNameOut(obj.value);
				}else{
					$("nameNotice").style.display="none";
					$("nameError").style.display="block";
					$("nameNull").style.display="none";
					$("nameExist").style.display="none";
					$("nameTrue").style.display="none";
				}
			}
		}
	}
}
function checkNameOut(text){
	//以post的形式把用户输入的邮箱发到后台校验是否已存在
	var mes={
	    "userName":text
	};
	
	var message = JSON.stringify(mes);
	//var url = "http://localhost:8080/blog/checkUserNameServlet";
	var url = "../checkRegisterNameAction.do";
	//调用Ajax方法
	Ajax.request(url, "POST", message, "TEXT", callback2);
}
function callback2(text){
	//将json格式的text转化为js
	var object = JSON.parse(text);
	var obj = object.notice;
	if(obj=="canNot"){
		$("nameNotice").style.display="none";
		$("nameNull").style.display="none";
		$("nameExist").style.display="block";
		$("nameTrue").style.display="none";
		arr.nameState="false";
	}else if(obj=="can"){
		$("nameNotice").style.display="none";
		$("nameNull").style.display="none";
		$("nameExist").style.display="none";
		$("nameTrue").style.display="block";
		arr.nameState="true";
	}
}
function validatePwd(obj){
	if(obj.value==""){
		$("pwdNotice").style.display="none";
		$("pwdError").style.display="block";
		$("pwdMedium").style.display="none";
		$("pwdGood").style.display="none";
		arr.pwdState="false";
	}else if(obj.value.length<6&&obj.value.length>0&&obj.value.length>17){
		$("pwdNotice").style.display="none";
		$("pwdError").style.display="block";
		$("pwdMedium").style.display="none";
		$("pwdGood").style.display="none";
		arr.pwdState="false";
	}else if(obj.value.length >= 6&&obj.value.length<12){
		$("pwdNotice").style.display="none";
		$("pwdError").style.display="none";
		$("pwdMedium").style.display="block";
		$("pwdGood").style.display="none";
		arr.pwdState="true";
	}else if(obj.value.length>=12&&obj.value.length<=16){
		$("pwdNotice").style.display="none";
		$("pwdError").style.display="none";
		$("pwdMedium").style.display="none";
		$("pwdGood").style.display="block";
		arr.pwdState="true";
	}
}
function validateVirfy(obj){
	if(obj.value==""){
		$("virfyNotice").style.display="none";
		$("virfyNull").style.display="block";
		$("virfyError").style.display="none";
		arr.virfyState="false";
	}else if(obj.value.length==4){
		$("virfyNotice").style.display="none";
		$("virfyNull").style.display="none";
		$("virfyError").style.display="none";
		arr.virfyState="true";
	}
}

function click_register(){
	if(arr.nameState && arr.emailState && arr.pwdState && arr.virfyState){
	  var registerMass={
			  "userEmail":$("emailId").value,
	          "userName":$("nameId").value,
	          "userPwd":$("pwdId").value,
	          "virfyCode":$("virfyId").value
	  };
	  var message=JSON.stringify(registerMass);
	 // var url = "http://localhost:8080/blog/registerServlet";
	  var url = "../registerAction.do";
	  Ajax.request(url, "POST", message, "TEXT", result);
	}
}
function result(text){
	var backText = JSON.parse(text);
	if(backText.notice=="Not"){
		$("virfyNotice").style.display="none";
		$("virfyNull").style.display="none";
		$("virfyError").style.display="block";
	}
	if(backText.notice=="YES"){
//		window.location.href=backText.address;
		window.location ="../bloghomepage.html";
	}
	
}
function $(id){
	return document.getElementById(id);
}

function clear(obj){
	obj.value="";
}
function isEmail(str){ 
	var reg =/^[\w\.-]+?@([\w\-]+\.){1,2}[a-zA-Z]{2,3}$/; 
	//return str.match(reg);
	return true;
} 
