/**
 * String对象的trim方法在某些版本浏览器下不兼容
 */
if(typeof String.prototype.trim !== 'function') {
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g, ''); 
	}
}

//取得cookie
function getCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');    //把cookie分割成组
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];                      //取得字符串
		while (c.charAt(0)==' ') {          //判断一下字符串有没有前导空格
			c = c.substring(1,c.length);      //有的话，从第二位开始取
		}
		if (c.indexOf(nameEQ) == 0) {       //如果含有我们要的name
			return unescape(c.substring(nameEQ.length,c.length));    //解码并截取我们要值
		}
	}
	return false;
}

//设置cookie
function setCookie(name, value, days) {
	days = days || 0;   //days有值就直接赋值，没有为0，这个根php不一样。
	var expires = "";
	if (days != 0 ) {      //设置cookie生存时间
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		expires = "; expires="+date.toGMTString();
	}
	document.cookie = name+"="+escape(value)+expires+"; path=/";   //转码并赋值
}

function constructDate(){
	var today = new Date(),
		day = today.getDay(),
		dd = today.getDate(),
		mm = today.getMonth()+1, //January is 0!
		yyyy = today.getFullYear();
	if(dd<10){dd='0'+dd;} 
	if(mm<10){mm='0'+mm;} 
	var today = yyyy+'-'+mm+'-'+dd;
	var dateString = today+'  ';
	switch(day){
	  case 0: dateString = dateString + login.i18n('dpap.login.Sunday');break;
	  case 1: dateString = dateString + login.i18n('dpap.login.Monday');break;
	  case 2: dateString = dateString + login.i18n('dpap.login.Tuesday');break;
	  case 3: dateString = dateString + login.i18n('dpap.login.Wednesday');break;
	  case 4: dateString = dateString + login.i18n('dpap.login.Thursday');break;
	  case 5: dateString = dateString + login.i18n('dpap.login.Friday');break;
	  case 6: dateString = dateString + login.i18n('dpap.login.Saturday');break;
	}
	return dateString;
}
function bodyReady(){
	var dateTime = document.getElementById('dateTime'),
		error = document.getElementById('error'),
		loginName = document.getElementById('loginName'),
		loginNameValue = getCookie('loginName');
	if(loginNameValue){
		loginName.value = loginNameValue;		
	}
	writeErrorMessage(error.innerHTML);
	dateTime.innerHTML = constructDate();
	document.onkeydown = function(evt){
		if(window.event){
			evt = window.event;
		}
		if(evt.keyCode==13){
			loginHandler();
		}
	};
}

function loginHandler(){
	var loginName = document.getElementById('loginName'),
		password = document.getElementById('password'),
		loginForm = document.getElementById('loginForm'),
		loginNameValue = loginName.value.trim(),
		passwordValue = password.value.trim();
	var verifyCodeStyle = getEyeJsStyle("verifyCodeDiv","display");
	if(verifyCodeStyle=="none"){
		loginName.value=loginNameValue;
		password.value=passwordValue;
		var message = check(loginNameValue,passwordValue);
		setCookie('loginName',loginName.value,6);
		if (!message) {
			loginForm.submit();
		}else{
			writeErrorMessage(message);
		}
	}else{
		loginName.value=loginNameValue;
		password.value=passwordValue;
		var message = check(loginNameValue,passwordValue);
		var verifyCode = document.getElementById('verifyCode').value.trim();
		if(message==null){
			if (verifyCode == '' || verifyCode == null || verifyCode == undefined) {
				message = login.i18n('dpap.login.VerifyCodeIsNullException');
			}
		}
		if(message!=null){
			writeErrorMessage(message);
		}else{
			ajax(verifyCode);//检查验证码是否正确
		}
	}
	
}
function ajax(verifyCode){
	var request;
	try {
		request = new XMLHttpRequest();
	} catch (trymicrosoft) {
		try {
			  request = new ActiveXObject("Msxml2.XMLHTTP");
			 } catch (othermicrosoft) {
			     try {
			     request = new ActiveXObject("Microsoft.XMLHTTP");
			      } catch (failed) {
			       request = false;
			      }
			 }
	}
	if (!request)
			alert("Error initializing XMLHttpRequest!");
	var queryParams = "verifyCode="+verifyCode;
	var url="../config/validateVerifyCode?i="+Math.random();
	request.open('POST', url, true);
	request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	request.onreadystatechange = function(){
		if(request.readyState==4){
			if(request.status==200){
				if(request.responseText=="failure"){
					var message = login.i18n('dpap.login.VerifyCodeErrorException');
					writeErrorMessage(message);
				}else{
					setCookie('loginName',loginName.value,6);
					loginForm.submit();
				}
			}
		}
	};
	request.send(queryParams);
}


function writeErrorMessage(message){
	var errorLi = document.getElementById('errorLi'),
		error = document.getElementById('error');
	if(message!=''){
		errorLi.style.display='inline';
		error.innerHTML = message;
	}else{
		errorLi.style.display='none';
	}
}

/**
 * 验证方法
 * @param logName 登录名
 * @param logPwd 登录密码
 * @returns message 验证信息字符串
 */
function check(logName,logPwd) {
	var message = null;
	if (logName == "" || logName == null || logName == undefined) {
		message = login.i18n('dpap.login.UserNameIsNullException');
	}
	if (logPwd == '' || logPwd == null || logPwd == undefined) {
		if (message != null) {
			message = message + ','
					+ login.i18n('dpap.login.LoginPasswordIsNullException');
		} else {
			message = login.i18n('dpap.login.LoginPasswordIsNullException');
		}
	}
	return message;
};
//更换验证码图片
function changeImg(){
	var imgSrc = document.getElementById("verifyCodeImgId");
	var src = imgSrc.getAttribute("src");
	imgSrc.setAttribute("src",changeUrl(src));
}
function changeUrl(url){
	var timestamp = (new Date()).valueOf();
	if(url.indexOf("&") >= 0){
		url += "&timestamp="+timestamp;
	}else{
		url += "?timestamp="+timestamp;
	}
	return url;
}


var $=function(id){return document.getElementById(id) }; 
/* 
* @string id 
* @string styleName 样式名 
*/ 
function getEyeJsStyle(id,styleName){ 
	if($(id).currentStyle){//ie 
		return $(id).currentStyle[styleName]; 
	}else{ //ff 
		var $arr=$(id).ownerDocument.defaultView.getComputedStyle($(id), null); 
		return $arr[styleName]; 
	} 
} 
