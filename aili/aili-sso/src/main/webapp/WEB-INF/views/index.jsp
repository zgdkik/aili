<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>HBHK</title>
</head>
<body>
	<h1>hbhk-sso</h1>
	<a href="/logout">退出</a>
	<a href="http://127.0.0.2:5417/index?ticket=${service-ticket}" target="">B系统</a>
</body>
</html>