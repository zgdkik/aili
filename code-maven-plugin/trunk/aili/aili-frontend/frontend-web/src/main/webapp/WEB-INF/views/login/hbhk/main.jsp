<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="common.jsp"%>
<!-- background-image:url(../config/findLogoImage); -->
<style type="text/css">
	.frameTopPanel {
		background-image:url('${base}/images/config/maintoplogo.png');
		background-repeat: no-repeat;
		background-position: left bottom;
		display: block;
		height: 71px;
		width: 306px;
	}
</style>
<body>
	<hbhk:module />
	<script type="text/javascript">
		login.fun = true;
	</script>
	<script type="text/javascript" src="${scripts}/${theme}/app.js"></script>
</body>
</html>
