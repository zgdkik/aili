<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>HBHK</title>
</head>
<body>
	<h1>我是B系统</h1>
	<a href="http://120.27.115.240:8080/cas/logout">退出</a>
	<a href="http://139.196.180.16:8080/aili-sso/index.jsp?ticket=${ticket}" target="view_window">A系统</a>
</body>
</html>