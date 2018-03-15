<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>智送叫车-首页</title>
<%@include file="../common/common-meta.jsp"%>
<%@include file="../common/common-css.jsp"%>
<%@include file="../common/common-icon.jsp"%>
</head>
<body class="fixed-left" locale="zh-cn">
	<%@include file="../common/common-layer.jsp"%>
	<!-- Begin page -->
	<div id="wrapper">
		<%@include file="../common/common-top.jsp"%>
		<jsp:include page="../common/common-left.jsp">
			<jsp:param name="pagecode" value="index" />
		</jsp:include>
		<!-- Start right content -->
		<div class="content-page">
			<ol class="breadcrumb">
				<li><a href="${base}">智送系统</a></li>
				<li class="active">列表</li>
			</ol>
			<div class="content">
				<!--主要内容开始部分 START-->
			<div class="row">
		        <div class="col-md-12">
		            <div class="panel panel-default">
		                <div class="panel-heading">
		                    <h2>权限菜单管理</h2>
		                </div>
		                <div class="panel-body">
		                    <div class="row">
		                        <div class="col-md-12">
		                            <ul id="treeDemo" class="ztree"></ul>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
				<!--主要内容开始部分 END-->
			</div>
			<!-- ============================================================== -->
			<!-- End content here -->
			<!-- ============================================================== -->
			<%@include file="../common/common-footer.jsp"%>
		</div>
		<!-- End right content -->

	</div>

	<!-- End of page -->
	<!-- the overlay modal element -->

	<%@include file="../common/common-modal-footer.jsp"%>
	<!-- End of eoverlay modal -->
	<script>
		var resizefunc = [];
	</script>
	<%@include file="../common/common-script.jsp"%>

	<!-- Page Specific JS Libraries -->
	<script src="${base}/scripts/demo/tree.js?${version}"></script>

</body>
</html>