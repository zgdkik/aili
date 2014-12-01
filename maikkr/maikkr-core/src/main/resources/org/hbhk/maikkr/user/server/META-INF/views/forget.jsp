<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}-忘记密码</title>
<link href="${scripts}/mytheme/styles.css" type="text/css"
	rel="stylesheet" />
<script src="${scripts}/forget.js"></script>
<style type="text/css">
.f-top {
	font-family: '宋体 Bold', '宋体';
	font-weight: 700;
	font-style: normal;
	font-size: 18px;
	text-align: left;
	border-bottom: 2px solid #FF9900;
}

.forget {
	border: 1px solid #999999;
}

.f-body input {
	width: 200px;
	height: 30px;
}

.f-body table {
	margin-left: 29%;
	widows: 60%
}

.find {
	width: 100px;
	height: 30px;
	margin-left: 42%;
}
</style>
<script type="text/javascript">
        function refreshimg(){
          document.getElementById("virfyCode").src="${base}core/getRandcode.htm?code="+Math.random();
          return true;
        }
    </script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- 中间部分 -->
	<div class="row-fluid" style="min-height: 500px">
		<div class="span1" style="margin-left: 60px;"></div>

		<div class="span2 lrborder" style="margin-left: 0px;"></div>
		<div class="span6" style="margin-left: 0px;">

			<div class="forget" style="margin-top: 30px;">
				<div class="f-top">
					<div
						style="background-color: #F2F2F2; width: 100px; height: 40px; padding-top: 10px;">
						<div>忘记密码</div>
					</div>
				</div>
				<div class="f-body">
					<div style="margin-top: 50px;">
						<table>
							<tr>
								<td><span>用户名:</span></td>
								<td><input id="name" type="text"></td>
							</tr>
							<tr>
								<td><span>邮箱:</span></td>
								<td><input id="email"  type="text"></td>
							</tr>
							<tr>
								<td><span>验证码:</span></td>
								<td><input id="code"  type="text">
								 <img alt="" id="virfyCode" onclick="javascript:refreshimg()" src="${base}core/getRandcode.htm"/>
								</td>
							</tr>
						</table>
					</div>
					<div style="margin-bottom: 50px;margin-top: 30px;">
						<input class="find" type="button" value="找回密码" style="width: 100px;background-color: #FF9900;">
					</div>
				</div>

			</div>
		</div>
		<div class="span2 lrborder" style="margin-left: 0px;"></div>

		<div class="span1"></div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
