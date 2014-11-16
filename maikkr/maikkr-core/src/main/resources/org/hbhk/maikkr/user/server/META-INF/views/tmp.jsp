<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}-用户注册</title>
<link href="${scripts}/mytheme/styles.css" type="text/css"
	rel="stylesheet" />
<script src="${scripts}/mytheme/data.js"></script>
<style type="text/css">
.lrborder {
	border-left: 1px solid #F2F2F2;
	border-right: 1px solid #F2F2F2;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- 中间部分 -->
	<div class="row-fluid" style="min-height: 500px">
		<div class="span1" style="margin-left: 60px;"></div>

		<div class="span2 lrborder" style="margin-left: 0px;">
			<jsp:include page="menu.jsp" />
		</div>
		<div class="span6" style="margin-left: 0px;">
			<div class="center-body" style="width: 100%">
			
			
			</div>
		</div>

		<div class="span2 lrborder" style="margin-left: 0px;">
			<jsp:include page="userHeader.jsp" />
		</div>

		<div class="span1"></div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
