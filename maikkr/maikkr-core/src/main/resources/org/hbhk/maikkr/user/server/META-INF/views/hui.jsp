<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}-聚优惠</title>
<link href="${scripts}/mytheme/styles.css" type="text/css"
	rel="stylesheet" />
<script src="${scripts}/mytheme/data.js"></script>
<style type="text/css">
.lrborder {
	border-left: 1px solid #F2F2F2;
	border-right: 1px solid #F2F2F2;
}

.jyh {
	font-family: '宋体 Bold', '宋体';
	font-weight: 700;
	font-style: normal;
	font-size: 16px;
	text-align: left;
	height: 40px;
	margin-top: 10px;
	border-bottom: 3px solid #FF9900;
}

.jyhc {
	font-family: '宋体 Regular', '宋体';
	font-weight: 400;
	font-style: normal;
	color: #333333;
}
.biz-text{
	border: 1px solid #999999;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- 中间部分 -->
	<div class="row-fluid" style="min-height: 500px">
		<div class="span2" style="margin-left: 0px; background-color: re"></div>
		<div class="span8" style="margin-left: 0px;">
			<div class="jyh">
				<div style="background-color: #F2F2F2;width:160px;height: 40px">
					<div style="padding-top: 9px; padding-left: 5px;">聚优惠</div>
				</div>
			</div>
			<div class="bizs jyhc">
				<c:forEach items="${bizs}" var="biz">
				<div class="biz" style="margin-top: 20px;width: 100%">
					<div style="width: 100%; margin-top: 10px" ><img style="" alt="" width="150" height="150" src="${file_server}${biz.imgUrl}"> </div>
					<div style="border-bottom: 1px solid #FF9900;width: 200px">
						<span>商家名称:</span><span>${biz.name}</span>
					</div>
					<div class="biz-text" style="margin-top: 10px;float: left;width: 100%;">
						<div style="width: 80%;float: left;">
							<div>
								<label>优惠:</label>
								<xmp>${biz.favorable}</xmp>
							</div>
							<div style="margin-top: 10px">
								<span>联系方式:${biz.contactWay}</span>
							</div>
							<div style="margin-top: 10px">
								<span>地址:${biz.location}</span>
							</div>
						</div>
					
					</div>
						
				</div>
				</c:forEach>
			</div>
		</div>
		<div class="span2" style="margin-left: 0px;"></div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
