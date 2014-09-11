<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="common.jsp" />
<link href="${styles}/userlist.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${scripts}/userlist.js"></script>
<body>
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 class="panel-title">用户管理</h3>
		</div>
		<div  class="panel-body tbl-list">
			<div align="center" style="height: auto;">
				<table id="list"></table>
				<div id="gridPager"></div>
			</div>
		</div>
			<!-- <div class="panel-footer"></div> -->
	</div>
		
</body>
</html>