<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../commons/common-meta.jsp" />
<c:import url="../commons/common-css.jsp" />
</head>
<body class="sticky-header" style="overflow: scroll; overflow-y: hidden;">
	<section>
		<c:import url="../commons/common-left.jsp" />
		<!-- main content start-->
		<div class="main-content" style="overflow-y: hidden">
			<c:import url="../commons/common-top.jsp" />
			<!--body wrapper start-->
			<div class="wrapper">
				<div class="row">
					<div role="tabpanel" class="tabpanel" style="">
						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist" id="myTab">
							<li role="presentation" class="active"><a href="#home"
								aria-controls="home" role="tab" data-toggle="tab">首页</a></li>
						</ul>
						<!-- Tab panes -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="home">
								<div class="col-xs-12" style="margin-top: 30px;font-weight: 100;font-size: 30px;color: #383635;">
									欢迎使用hbhk平台管理系统</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--body wrapper end-->
			
		</div>
		<!-- main content end-->
	</section>
	<c:import url="../commons/common-footer.jsp" />
	<c:import url="../commons/common-script.jsp" />
	<script type="text/javascript" src="${base}/scripts/home/home.js"></script>
</body>
</html>
