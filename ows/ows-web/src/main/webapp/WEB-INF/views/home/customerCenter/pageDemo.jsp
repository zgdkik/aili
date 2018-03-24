<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />
	<style type="text/css">
	  .billresult{margin-top:485px;background-color: #FFF}
	  .tb_title{border-collapse:collapse;}
	  /*.tb_title td{height:30px; font-size:12px; font-family:"黑体"; color:#666666; text-align: center;}*/
	  .tb_title tbody td{height:30px; font-size:12px; font-family:"黑体"; color:#666666; text-align: center;}
	 /* .tb_title thead td{height:35px; background-color:#FFCC00;color:#FFFFFF; text-align: center;border-right:#FFFFFF solid 2px;*/
	  .tb_title thead td{height:35px; background-color:#AB5B5A;color:#fff; text-align: center;border-right:#FFFFFF solid 2px;}
	  
	</style>
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
			<div class="banner ub bannerReplace" id="child_banner" style="width:885px;height:173px;"><img  src="${base}/images/ban16.jpg" alt=""/></div>
			<div class="contenr-right-bottom ub">
				<h4 id="h_title">${title}</h4>
				<div class="chanpinfuwu">
					
				</div>
			</div>	
		</div>
			
		</div>
	
	<!--焦点图滚动-->
    
	<c:import url="../../commons/common-footer.jsp" />
	<c:import url="../../commons/common-script.jsp" />

	
</body>
	<script type="text/javascript">
		$(function(){
			fn_get_menu("ddwl_khzx");
		
		});
		
		
	</script>
</html>
