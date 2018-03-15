<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="container-header" role="banner">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-6">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">客户管理</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-6">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${base}">主页</a></li>
					<li><a href="${base}/cus/list">客户列表</a></li>
					<li><a href="${base}/cus/edit">新增客户</a></li>
					<li><a href="${base}/logout">退出</a></li>
				</ul>
			</div>
		</div>
	</nav>
</header>

