<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}-用户信息</title>
<link href="${base}uploadify/uploadify.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${base}uploadify/jquery.uploadify.js"></script>
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

.setting-font1 input{
	height: 30px;
	float: left;
	width: 200px;
	margin-top: 5px;
}
</style>
<script src="${scripts}/myaccount/data.js"></script>
<script type="text/javascript">
	var seesionid = "${pageContext.session.id}";
</script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- 中间部分 -->
	<div class="row-fluid" style="min-height: 500px">
		<div class="span1" style="margin-left: 60px;"></div>

		<div class="span2" style="margin-left: 0px;"></div>
		<div class="span6" style="margin-left: 0px;">
			<div class="lrborder" style="width: 100%;float: left;">
				<div class="setting-font" style=""><div style="margin-left: 3px;background-color: #F2F2F2;width: 30%">账号设置</div></div>
			
				<div class="setting-font1">
					<div style="margin-left: 3px;width: 30%;border-bottom: 1px solid #FF9900;">我的信息</div>
				</div>
				<div class="setting-font1" style="font-weight: 700;">
					<div style="height: 40px;background-color:#F2F2F2;width: 100%;margin-top:5px; ">
					<div style="width: 80px;float: left;height: 40px;line-height: 40px;margin-left: 3px;">邮箱</div> 
					<input type="text" class="email" value="${uc.name}"></div>
				</div>
				<div class="setting-font1" style="font-weight: 700;">
					<div style="height: 40px;background-color:#F2F2F2;width: 100%;margin-top: 5px; ">
					<div style="width: 80px;float: left;height: 40px;line-height: 40px;margin-left: 3px;">昵称</div> 
					<input type="text" class="nickname" value="${uc.nickName}"></div>
				</div>
				<div class="setting-font1" style="font-weight: 700;">
					<div style="height: 40px;background-color:#F2F2F2;width: 100%;margin-top: 5px; ">
					<div style="width: 80px;float: left;height: 40px;line-height: 40px;margin-left: 3px;">所在城市</div> 
						<select class="area" style="margin-bottom: 5px;height: 30px;width: 200px;">
						<c:forEach items="${ps}" var="p">
							<option value="${p.id}">${p.name}</option>
						</c:forEach>
					</select>
					</div>
				</div>
				
				<div class="setting-font1" style="font-weight: 700;width: 100%;">
					<div style="width: 80px;height: 40px;line-height: 40px;margin-left: 3px;"> 
					<input type="button" class="edit" value="修改" style="width: 40px;"></div>
				</div>
				
				<div class="setting-font1" style="width: 100%">
					<div style="margin-left: 3px;width: 30%;border-bottom: 1px solid #FF9900;">我的头像</div>
				</div>
				<div style="width: 100%;margin-top: 8px; float: left;">
					<div style="float: left;">
						<img style="height: 100px;width: 100px;" src="${file_server}${userInfo.userHeadImg}" />
						<div style="height: 30px;line-height: 30px;width: 100px;text-align: center;">我的头像</div>
					</div>
					<div style="float: left; margin-left: 50px;">
						<div style="margin-top: 80px;"></div>
						<div id="uploadimg" class="uploadimg"  ></div>
					</div>
				</div>
			</div>
		</div>

		<div class="span2" style="margin-left: 0px;"></div>

		<div class="span1"></div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
