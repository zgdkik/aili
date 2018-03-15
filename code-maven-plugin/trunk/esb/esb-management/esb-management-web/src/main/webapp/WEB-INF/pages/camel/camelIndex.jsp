<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%
	String path = "/camelwatch";
	String camelWatchPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
    <head>
    <title>ESB平台-CAMEL</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../styles/icon.css" rel="stylesheet" type="text/css" />
  	<link href="../styles/loading.css" rel="stylesheet" type="text/css" />
  </head>

  <body>
  <br/>
  <br/>
  <br/>
	<center>
    <h3>欢迎进入<a href="<%=camelWatchPath%>" target="_blank">CAMEL WATCH</a> 的世界</h3>
    </center>
  </body>
</html>
