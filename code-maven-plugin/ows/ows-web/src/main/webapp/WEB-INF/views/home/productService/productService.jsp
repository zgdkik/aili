<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />
</head>
<body style="width: 100%;" >
		<c:import url="../../commons/common-top.jsp" />
		<img alt="top" title="返回顶部" src="${base}/images/top.png"
			style="position: fixed; right: 10px; bottom: 100px; cursor: pointer;"
			onclick="scroll(0,0)" />
		<div class="clear"></div>
		<div class="container mg">
			<!--sidebar-->
			<c:import url="../../commons/childmenu.jsp" />
			<!--具体内容-->
			<div class="contenr-right">
				<div class="banner ub bannerReplace" id="child_banner" style="width:885px;height:173px;"><%-- <img src="${base}/images/cpwfBanner.png" alt=""/> --%></div>
				<div class="contenr-right-bottom ub">
					<h4 id="h_title">${title}</h4>
					<div class="chanpinfuwu">
							${htmlContent}
					</div>
				</div>
			</div>
		</div>
	
	<!--焦点图滚动-->
    
	<c:import url="../../commons/common-footer.jsp" />
	<c:import url="../../commons/common-script.jsp" />
	<script type="text/javascript">
		$(function(){
			fn_get_menu("cpfw");
		});
	
	</script>
	
</body>
<script type="text/javascript">
	var html5=loadNextBanner(3); // 产品服务
</script>
</html>
