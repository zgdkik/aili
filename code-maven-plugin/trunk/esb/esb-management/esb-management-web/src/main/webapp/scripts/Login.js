/**
 * 提交方法
 * 
 * @returns {Boolean}
 */
function _submit() {
	var pwdtext = document.getElementById('pwd');
	if (check()) {
//		var md5 = hex_md5(pwdtext.value);
//		if (pwdtext != "" && pwdtext != null && pwdtext != undefined) {
//			pwdtext.value = md5;
//		}
		document.getElementById('login').submit();
	}
	return false;
}

/**
 * 路径跳转
 * 
 * @param url
 */
function go(url) {
	if (this.parent != this) {
		top.location = url;
	}
}
// 路径跳转
go(window.location.pathname);

function eraseUsername() {
	document.getElementById('loginName').value = '';
	usernamefirst = true;
}

function erasepwd() {
	document.getElementById('pwd').value = '';
	pwdfirst = true;
}

/**
 * 验证方法
 * 
 * @returns {Boolean}
 */
function check() {
	var logName = document.getElementById('loginName').value;
	var logPwd = document.getElementById('pwd').value;
	var message = null;
	var flag = true;
	if (logName == "" || logName == null || logName == undefined) {
		document.getElementById('message').style.display = "";
		message = '用户名不能为空！';
		document.getElementById('loginName').value = '用户名';
		flag = false;
	}
	if (logPwd == '' || logPwd == null || logPwd == undefined) {
		document.getElementById('message').style.display = "";
		if (message != null) {
			message = message + ','
					+ '密码不能为空！';
		} else {
			message = '密码不能为空！';
		}
		document.getElementById('pwd').value = '密码';
		flag = false;
	}
	if (!flag) {
		document.getElementById('message').innerHTML = message;
	}
	return flag;
}