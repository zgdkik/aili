<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}-用户信息</title>
<style type="text/css">
.lrborder {
	border: 1px solid #999999;
	margin-top: 30px;
}

.setting-font {
	font-family: '宋体 Bold', '宋体';
	font-weight: 700;
	font-style: normal;
	height: 40px;
	line-height: 40px;
	border-bottom: 2px solid #FF9900;
}
.setting-font1 {
	font-family: '宋体 Bold', '宋体';
	font-style: normal;
	height: 60px;
	line-height: 60px;
}

.setting-font1 input{
	height: 30px;
	float: left;
	width: 200px;
	margin-top: 5px;
}
</style>
<script src="${scripts}/myaccount/data.js"></script>
<script type="text/javascript">
	var seesionid = "${pageContext.session.id}";
</script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- 中间部分 -->
	<div class="row-fluid" style="min-height: 500px">
		<div class="span1" style="margin-left: 60px;"></div>

		<div class="span2" style="margin-left: 0px;"></div>
		<div class="span6" style="margin-left: 0px;">
			<div class="lrborder" style="width: 100%;float: left;">
				<div class="setting-font" style="">
				<div style="margin-left: 3px;background-color: #F2F2F2;width: 30%">账号设置</div>
				</div>
			
				
			</div>
		</div>

		<div class="span2" style="margin-left: 0px;"></div>

		<div class="span1"></div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
