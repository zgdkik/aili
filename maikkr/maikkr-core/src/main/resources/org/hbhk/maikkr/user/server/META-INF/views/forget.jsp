<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="common.jsp"/>
<html>
<head>
<script type="text/javascript">
</script>
</head>
<body>
<jsp:include page="tool.jsp"/>
<div  style="margin-top: 100px;width: 300px;margin-left: auto;margin-right: auto;">
<div class="panel panel-info">
   <div class="panel-heading">
      <h3 class="panel-title">密码找回 </h3>
   </div>
   <div class="panel-body">
	   <div class="form-group">
	      <label>登录名</label>
	      <input type="text" class="form-control" id="name"  placeholder="请输入登录名">
	   </div>
	   <div class="form-group">
	      <label>绑定邮箱</label>
	      <input type="text" class="form-control" id="name"  placeholder="请输入绑定邮箱">
	   </div>
	   <div class="form-group" >
	       <button type="button" class="btn btn-default">提交</button>
	   </div>
	  </div>
	</div>
   </div>
</body>
</html>