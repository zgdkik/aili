<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="common.jsp"/>
<html>
<head>
	<title>${siteInfo.title}-我的消息</title>
	<link href="${styles}/mainnew.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${scripts}/msg.js"></script>
	<script type="text/javascript">
	var seesionid="${pageContext.session.id}";
	</script>
</head>
<body>
<jsp:include page="tool.jsp"/>
<div id="main" >
    <div id="center" >
		<!-- 左边部分 -->
		<jsp:include page="left.jsp"/>
		<!--  -->
		<div id="ct_center" >
			<h3 style="color: blue;">我的消息</h3>
	 		<h3 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h3>
			<div class="row-fluid">
				<div class="span12">
				<table class="table">
				<thead>
					<tr>
						<th>发送者</th>
						<th>消息内容</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${msgList}" var="item">
						<tr class="info">
							<td>${item.sendUser}</td>
							<td>${item.msg}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			</div>
		</div>
		<!-- 右边部分 -->
		<jsp:include page="right.jsp"/>
		<jsp:include page="footer.jsp"/>
	</div>
</div>
<input type="hidden" class="imgurl"/>
</body>
</html>