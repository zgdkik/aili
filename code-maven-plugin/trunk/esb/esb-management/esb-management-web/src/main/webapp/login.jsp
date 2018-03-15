<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>ESB管理平台</title>
<link rel="shortcut icon" href="images/favicon.ico">
<link href="styles/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="scripts/md5.js"></script>
<script type="text/javascript" src="scripts/Login.js"></script>
</head>

<body>
	<dir style="text-align: center; color: red">
	</dir>
	<form id="login" action="login.action" method="post"
		onsubmit="return _submit()">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="278" align="center" valign="top"
					background="images/Login2_bg.jpg"><img
					src="images/Login2_ad.jpg" width="764" height="278" /></td>
			</tr>
			<tr>
				<td>
					<table width="730" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="left" valign="top">
								<table border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td height="21" align="left">&nbsp;</td>
										<td align="left">&nbsp;</td>
										<td align="left">&nbsp;</td>
										<td align="left">&nbsp;</td>
										<td align="left">&nbsp;</td>
										<td align="right">&nbsp;</td>
									</tr>
									<tr>
										<td height="29" align="left">&nbsp;</td>
										<td align="left" valign="top"><img
											src="images/Login2_info.jpg" width="163" height="22" /></td>
										<td align="left">&nbsp;</td>
										<td align="left"><font color="#747170" size="2px" id="message">初始密码默认为用户名</font></td>
										<td align="left">&nbsp;</td>
										<td align="right">&nbsp;</td>
									</tr>
									<tr>
										<td width="10" align="left"><img
											src="images/Login2_left.jpg" width="10" height="45" /></td>
										<td align="left" background="images/Login2_middle.jpg"><input
											id="loginName" name="loginName" type="text"
											class="formTextareaLogin2" value="用户名"
											onfocus="eraseUsername()" /></td>
										<td align="left" background="images/Login2_middle.jpg">&nbsp;</td>
										<td align="left" background="images/Login2_middle.jpg"><input
											id="pwd" name="password" type="password" id="pwd"
											class="formTextareaLogin2" value='密码' onfocus="erasepwd()" /></td>
										<td align="left" background="images/Login2_middle.jpg">&nbsp;</td>
										<td width="78" align="right"><input type="image" id="t"
											src="images/Login2_in.jpg" width="78" height="45" border="0" /></td>
									</tr>
									<tr>
										<td colspan="4" align="center">&nbsp;</td>
									</tr>
									<tr>
										<td colspan="4" align="center"><font color="red"
											size="2px" id="message">请输入用户名和密码&nbsp;</font></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="53" align="left" valign="top">&nbsp;</td>
						</tr>
						<tr>
							<td height="27" align="left" valign="top"><img
								src="images/Login2_line.jpg" width="730" height="11" /></td>
						</tr>
						<tr>
							<td height="30" align="center" valign="middle"><span
								class="LoginbottomText">德邦物流-ESB平台&copy; 2012</span></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
