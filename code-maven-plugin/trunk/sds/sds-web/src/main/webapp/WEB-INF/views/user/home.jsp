<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/common-meta.jsp"%>
<%@include file="../common/common-css.jsp"%>
<%@include file="../common/common-icon.jsp"%>
<title>壹米滴答-首页</title>
</head>
<body class="fixed-left" locale="${lang}">
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
				<li><a href="${base}">壹米滴答</a></li>
				<li class="active">首页</li>
			</ol>
			<div class="content">
				<!--主要内容开始部分 START-->
				<div class="col-ls-12" style="min-height: 600px;background-color: white;">
					<h1>欢迎使用壹米滴答平台系统</h1>
					<!-- 	<p>这是一个超大屏幕（Jumbotron）的实例。</p>
					<p>
						<a class="btn btn-primary btn-lg" role="button"> 学习更多</a>
					</p> -->


				</div>

				<!--主要内容开始部分 END-->
			</div>
			<!-- ============================================================== -->
			<!-- End content here -->
			<!-- ============================================================== -->

		</div>
	</div>
	<%@include file="../common/common-footer.jsp"%>
	<!-- End right content -->
	<!-- End of page -->
	<!-- the overlay modal element -->

	<%@include file="../common/common-modal-footer.jsp"%>
	<!-- End of eoverlay modal -->
	<script>
		var resizefunc = [];
	</script>
	<%@include file="../common/common-script.jsp"%>

	<!-- Page Specific JS Libraries -->
	<script src="${base}/scripts/home/home.js?${version}"></script>
</body>
</html>