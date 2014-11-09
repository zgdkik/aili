<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}-用户登陆</title>
<meta name="keywords" content="sports, recreation, outdoor">
<meta name="description" content="sports, recreation, outdoor">
<link href="${scripts}/login/styles.css" type="text/css"
	rel="stylesheet" />
<script src="${scripts}/login/data.js"></script>
</head>
<body>
	<div id="base" class="">

		<!-- Unnamed (Shape) -->
		<div id="u0" class="ax_shape">
			<img id="u0_img" class="img " src="${images}/login/u0.png" />
			<!-- Unnamed () -->
			<div id="u1" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u2" class="ax_shape">
			<img id="u2_img" class="img " src="${images}/reg/u2.png" />
			<!-- Unnamed () -->
			<div id="u3" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u4" class="ax_paragraph">
			<img id="u4_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u5" class="text">
				<p>
					<span>用户名:</span>
				</p>
			</div>
		</div>

		<!-- userName2 (Text Field) -->
		<div id="u6" class="ax_text_field" data-label="userName2">
			<input id="u6_input" class="email" title="请输入您注册的邮箱" type="text" value="1024784402@qq.com" />
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u7" class="ax_paragraph">
			<img id="u7_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u8" class="text">
				<p>
					<span>密码:</span><span>：</span>
				</p>
			</div>
		</div>

		<!-- userEmail2 (Text Field) -->
		<div id="u9" class="ax_text_field" data-label="userEmail2">
			<input id="u9_input" class="password" type="password" title="请输入密码" value="as135246" />
		</div>
		
		<!-- Unnamed (Checkbox) -->
		<div id="u13" class="ax_checkbox" style="top:400px">
			<label for="u13_input"> <!-- Unnamed () -->
				<div id="u14" class="text">
					<p>
						<span>记住密码</span>
					</p>
				</div>
			</label> <input id="u13_input" type="checkbox" value="checkbox" checked />
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u15" class="ax_paragraph"  style="top:400px">
			<img id="u15_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u16" class="text">
				<p>
					<span style="text-decoration: underline;">忘记密码？</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u17" class="ax_shape" style="top:450px">
			<img id="u17_img" class="img login" src="${images}/reg/u26.png" />
			<!-- Unnamed () -->
			<div id="u18" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u19" class="ax_paragraph login"  style="top:455px">
			<img id="u19_img" class="img"  src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u20" class="text">
				<p>
					<span >登录</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u21" class="ax_paragraph" style="top:500px">
			<img id="u21_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u22" class="text">
				<p>
					<span style="text-decoration: underline;"><a href="${base}user/register.htm">我要注册</a></span>
				</p>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
		<jsp:include page="header.jsp" />
	</div>
</body>
</html>
