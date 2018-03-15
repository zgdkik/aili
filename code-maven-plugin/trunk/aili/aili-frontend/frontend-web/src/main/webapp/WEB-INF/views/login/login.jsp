<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/hbhk" prefix="hbhk" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta content="LOGIN_JSP">
	<link rel="shortcut icon" href="${images}/logo.png" /> 
	 <link rel="stylesheet" type="text/css" href="${base}/styles/config/login.css"/>
	 <!-- <link rel="stylesheet" type="text/css" href="../config/queryCssSource"/> -->
	<hbhk:module  />
	<script type="text/javascript" src="${base}/scripts/login/login.js"></script>
	<title>${title}</title>
</head>
<body onload='bodyReady()'>
	<div class="logo"><h1><hbhk:i18nForJsp key="dpap.login.logo"/></h1></div>
	<div class="layout">
		<div class="login_layout">
			<div class="login">
				<h2 id="dateTime"></h2>
				<ul>
					<form id='loginForm' action='${base}/user/login' method='post'>
						<input type="hidden" name="doLogin" value="true">
						<li><label><hbhk:i18nForJsp key="dpap.login.loginName"/></label><input id='loginName' name='loginName'/></li>
						<li><label><hbhk:i18nForJsp key="dpap.login.password"/></label><input id='password' name='password' type='password'/></li>
						<li><div id="verifyCodeDiv"><label><hbhk:i18nForJsp key="hb.login.verifyCode"/></label><input id='verifyCode' name='verifyCode'/>
							<img id="verifyCodeImgId"  src="${base}/config/queryVerifyCode"/>
							<a class="a_changeImg" href="#" onclick="changeImg()" style="font-size:10px;"><hbhk:i18nForJsp key="hbhk.login.changeAnother"/></a></div>
						</li>	
						<li id='errorLi'><label></label><span class="error" id='error'>${requestScope.message}</span></li>
						<li class="t-r"><a class="a_login" href="javascript:" onclick='loginHandler()'><hbhk:i18nForJsp key="dpap.login.submit"/></a></li>
					</form>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
