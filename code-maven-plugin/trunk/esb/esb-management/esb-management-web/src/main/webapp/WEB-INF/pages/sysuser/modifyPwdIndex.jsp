<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<title>ESB平台-系统用户管理-修改密码</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../styles/icon.css" rel="stylesheet" type="text/css" />
<link href="../styles/loading.css" rel="stylesheet" type="text/css" />
<link href="${extjs4}/resources/css/ext-all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${extjs4}/bootstrap.js"></script>
</head>

<body>
	<script type="text/javascript" src="<%=basePath%>scripts/sysuser/SysUserStore.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/sysuser/modifyPassWordMain.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/sysuser/modifyPassWordForm.js"></script>
</body>
</html>
