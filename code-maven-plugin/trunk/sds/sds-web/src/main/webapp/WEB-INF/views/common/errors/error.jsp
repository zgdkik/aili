<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>智送叫车-错误页面</title>
<%@include file="../common-meta.jsp"%>
<%@include file="../common-css.jsp"%>
<%@include file="../common-icon.jsp"%>

</head>
<body class="fixed-left" locale="${lang}">
	<%@include file="../common-layer.jsp"%>
	<!-- Begin page -->
	<div id="wrapper">
		<%@include file="../common-top.jsp"%>
		<div class="content-page">
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}">智送系统</a></li>
				<li class="active"><a href="#">error</a></li>
			</ol>
			<div class="content">
				<!--主要内容开始部分 START-->
				<div class="row">
					<c:if test="${statusCode != null}">
						<p>
							[${statusCode}] [${msg}] <a id="error" errorCode="${statusCode}"
								error="${msg}"></a>
						</p>

						<div class="error-detail">${stackTrace}</div>
					</c:if>
					<c:if test="${statusCode == null}">
						<p>
							[system error] <a id="error" errorCode="1" error="system error"></a>
						</p>
						<div class="error-detail">
							<div class="clear-line height1"></div>
							${pageContext.exception}
						</div>
					</c:if>
				</div>
				<!--主要内容开始部分 END-->

			</div>
		</div>

	</div>
	<%@include file="../common-footer.jsp"%>
	<%@include file="../common-script.jsp"%>

</body>
</html>