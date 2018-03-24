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
			<!--具体内容-->
		<div class="contenr-right">
			<div class="banner ub bannerReplace" id="child_banner" style="width:885px;height:173px;"></div>
			<div class="contenr-right-bottom ub">
				<div class="news-title ub" id="bannerTitle"></div>
				<div class="banner-content" id="bannerContent" style="z-index: 1">
				</div>
			</div>
		</div>
		</div>
	<!--焦点图滚动-->
	<c:import url="../../commons/common-script.jsp" />
	<c:import url="../../commons/common-footer.jsp" />
	<script type="text/javascript">
		//rowsId总条数
		$(function(){
			if(title=='通知公告'){
				fn_get_menu("ddwl_xwzx");
			}else if(title=='市场活动'){
				fn_get_menu("cpfw");
			}
			
			//rowsId总条数
		 	var bannerId = '${bannerId}';
		 	url = "${base}/homePage/getHomePageById";
		 	 $.ajax({
			        type: "POST",
			        url: url,
			        data: {
			        	'id':bannerId
			        },
			        dataType: "json",
			        success: function(data){
			        	//总页数
			        	$("#bannerTitle").html(data.data.bannerTitle);
			        	$("#bannerContent").html(data.data.content);
			        	replaceSrc();
			        },
			        error: function(data){
			        	alert("系统异常！");
			        }
			    });
		})
	</script>
</body>
<script type="text/javascript">
	var html4=loadNextBanner(4);  //新闻资讯
</script>	
</html>
