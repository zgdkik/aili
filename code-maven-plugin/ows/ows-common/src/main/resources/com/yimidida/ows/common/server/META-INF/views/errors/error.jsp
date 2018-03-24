<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link href="${base}/styles/home/custom.css?${version}" rel="stylesheet">
</head>
<body class="fixed-left" locale="${lang}">
	<!-- Begin page -->
	<div id="wrapper">
		<div class="content-page">
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

</body>
</html>