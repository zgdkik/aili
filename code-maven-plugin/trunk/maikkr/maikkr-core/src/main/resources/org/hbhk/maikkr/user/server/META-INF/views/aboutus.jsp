<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}-关于我们</title>
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

.setting-font1 input {
	height: 30px;
	float: left;
	width: 30%;
	margin-top: 5px;
}

</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- 中间部分 -->
	<div class="row-fluid" style="min-height: 500px">
		<div class="span1" style="margin-left: 60px;"></div>

		<div class="span1" style="margin-left: 0px;"></div>
		<div class="span8" style="margin-left: 0px;font-size: 16px;">
			<div class="lrborder" style="width: 100%; float: left;">
				<div class="setting-font" style="">
					<div
						style="margin-left: 3px; background-color: #F2F2F2; width: 30%">米客网</div>
				</div>
				
				<div class="setting-font1" style="width: 100%">
					<div style="margin-left: 3px;width: 15%;border-bottom: 1px solid #FF9900;">服务</div>
				</div>
				<div class="fuwu" style="width: 100%">
					<div style="margin-top: 20px;margin-left: 7%;width: 93%;float: left;">
						<div style="float: left;"><img alt="" src="${images}/aboutus/u20.gif"></div> 
						<div style="float: left;margin-left: 5px;">米客网是生活汽车类主题的分享社区，服务于打算买车的网友。</div>
					</div>
					<div style="margin-top: 20px;margin-left: 7%;width: 93%;float: left;">
						<div style="float: left;"><img alt="" src="${images}/aboutus/u20.gif"></div> 
						<div style="float: left;margin-left: 5px;">米客网可以帮助您快捷发布汽车微主题，与好友分享互动，方便生活。</div>
					</div>
					<div style="margin-top: 20px;margin-left: 7%;width: 93%;float: left;">
						<div style="float: left;"><img alt="" src="${images}/aboutus/u20.gif"></div> 
						<div style="float: left;margin-left: 5px;">米客网可以帮助您更快找到一起买车的小伙伴，共同交流，减少您在买车过程中的烦恼，做出更好的决策。</div>
					</div>
				</div>
				
				<div class="setting-font1" style="width: 100%;float: left;">
					<div style="margin-left: 3px;width: 15%;border-bottom: 1px solid #FF9900;">愿景</div>
				</div>
				
				 <div class="yuanjing" style="width: 100%;float: left;">
					<div style="margin-top: 20px;margin-left: 7%;width: 93%;float: left;">
						<div style="float: left;"><img alt="" src="${images}/aboutus/u20.gif"></div> 
						<div style="float: left;margin-left: 5px;">微主题，惠生活！</div>
					</div>
					<div style="margin-top: 20px;margin-left: 7%;width: 93%;float: left;">
						<div style="float: left;"><img alt="" src="${images}/aboutus/u20.gif"></div> 
						<div style="float: left;margin-left: 5px;">热情分享，惠聚你我！</div>
					</div>
				</div> 
				
				<div class="setting-font1" style="width: 100%;float: left;">
					<div style="margin-left: 3px;width: 15%;border-bottom: 1px solid #FF9900;">联系</div>
				</div>
				
				 <div class="yuanjing" style="width: 100%;float: left;margin-bottom: 50px;">
					<div style="margin-top: 20px;margin-left: 7%;width: 93%;float: left;">
						<div style="float: left;"><img alt="" src="${images}/aboutus/u20.gif"></div> 
						<div style="float: left;margin-left: 5px;">邮箱：3128701406@qq.com</div>
					</div>
					<div style="margin-top: 20px;margin-left: 7%;width: 93%;float: left;">
						<div style="float: left;"><img alt="" src="${images}/aboutus/u20.gif"></div> 
						<div style="float: left;margin-left: 5px;">QQ:3128701406</div>
					</div>
				</div> 
				
			</div>
		</div>

		<div class="span1" style="margin-left: 0px;"></div>

		<div class="span1"></div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
