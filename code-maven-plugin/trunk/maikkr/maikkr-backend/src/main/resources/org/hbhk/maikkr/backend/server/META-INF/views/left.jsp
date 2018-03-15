<%@ page language="java" pageEncoding="UTF-8" info="买客网"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(${images}/main/left.gif);
}
</style>
<link href="${styles}/top.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
	function tupian(idt) {
		var nametu = "xiaotu" + idt;
		var tp = document.getElementById(nametu);
		tp.src = "${images}/main/ico05.gif";//图片ico04为白色的正方形

		for ( var i = 1; i < 30; i++) {

			var nametu2 = "xiaotu" + i;
			if (i != idt * 1) {
				var tp2 = document.getElementById('xiaotu' + i);
				if (tp2 != undefined) {
					tp2.src = "${images}/main/ico06.gif";
				}//图片ico06为蓝色的正方形
			}
		}
	}

	function list(idstr) {
		var name1 = "subtree" + idstr;
		var name2 = "img" + idstr;
		var objectobj = document.all(name1);
		var imgobj = document.all(name2);
		if (objectobj.style.display == "none") {
			for (i = 1; i < 10; i++) {
				var name3 = "img" + i;
				var name = "subtree" + i;
				var o = document.all(name);
				if (o != undefined) {
					o.style.display = "none";
					var image = document.all(name3);
					//alert(image);
					image.src = "${images}/main/ico04.gif";
				}
			}
			objectobj.style.display = "";
			imgobj.src = "${images}/main/ico03.gif";
		} else {
			objectobj.style.display = "none";
			imgobj.src = "${images}/main/ico04.gif";
		}
	}
</script>
<body>
	<table width="198" border="0" cellpadding="0" cellspacing="0"
		class="left-table01">
		<tr>
			<TD>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="207" height="55" background="${images}/main/nav01.gif">
							<table width="90%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="25%" rowspan="2"><img
										src="${images}/main/ico02.gif" width="35" height="35" /></td>
									<td width="75%" height="22" class="left-font01">您好，<span
										class="left-font02">${becuserName}</span></td>
								</tr>
								<tr>
									<td height="22" class="left-font01">[&nbsp;<a
										href="${base}backend/logout.htm" target="_top" class="left-font01">退出</a>&nbsp;]
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table> <!--  用户管理   -->
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img8" id="img8"
										src="${images}/main/ico04.gif" width="8" height="11" /></td>
									<td width="92%"><a href="javascript:" target="mainFrame"
										class="left-font03" onClick="list('8');">用户管理 </a></td>
								</tr>
							</table>
						</td>
					</tr>
				</TABLE>
				<table id="subtree8" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr>
						<td width="9%" height="20"><img id="xiaotu20"
							src="${images}/main/ico06.gif" width="8" height="12" /></td>
						<td width="91%"><a href="${base}backend/userlist.htm" target="mainFrame"
							class="left-font03" onClick="tupian('20');">用户管理</a></td>
					</tr>
					<tr>
						<td width="9%" height="21"><img id="xiaotu21"
							src="${images}/main/ico06.gif" width="8" height="12" /></td>
						<td width="91%"><a href="${base}backend/adminlist.htm" target="mainFrame"
							class="left-font03" onClick="tupian('21');">系统用户管理</a></td>
					</tr>
					<tr>
						<td width="9%" height="21"><img id="xiaotu21"
							src="${images}/main/ico06.gif" width="8" height="12" /></td>
						<td width="91%"><a href="${base}backend/loglist.htm" target="mainFrame"
							class="left-font03" onClick="tupian('21');">用户登陆日志管理</a></td>
					</tr>
				</table>
				<!--  用户管理结束    --> 
				<!--  主题管理   -->
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img7" id="img7"
										src="${images}/main/ico04.gif" width="8" height="11" /></td>
									<td width="92%"><a href="javascript:" target="mainFrame"
										class="left-font03" onClick="list('7');">主题管理</a></td>
								</tr>
							</table>
						</td>
					</tr>
				</TABLE>
				<table id="subtree7" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr>
						<td width="9%" height="20"><img id="xiaotu17"
							src="${images}/main/ico06.gif" width="8" height="12" /></td>
						<td width="91%"><a href="${base}backend/sitelist.htm" target="mainFrame"
							class="left-font03" onClick="tupian('17');">网站管理</a></td>
					</tr>
					<tr>
						<td width="9%" height="20"><img id="xiaotu17"
							src="${images}/main/ico06.gif" width="8" height="12" /></td>
						<td width="91%"><a href="${base}backend/bloglist.htm" target="mainFrame"
							class="left-font03" onClick="tupian('17');">主题管理</a></td>
					</tr>
					<tr>
						<td width="9%" height="20"><img id="xiaotu17"
							src="${images}/main/ico06.gif" width="8" height="12" /></td>
						<td width="91%"><a href="${base}backend/commentList.htm" target="mainFrame"
							class="left-font03" onClick="tupian('17');">评论管理</a></td>
					</tr>
					
					<tr>
						<td width="9%" height="20"><img id="xiaotu17"
							src="${images}/main/ico06.gif" width="8" height="12" /></td>
						<td width="91%"><a href="${base}backend/themelist.htm" target="mainFrame"
							class="left-font03" onClick="tupian('17');">通用主题管理</a></td>
					</tr>
					<tr>
						<td width="9%" height="20"><img id="xiaotu17"
							src="${images}/main/ico06.gif" width="8" height="12" /></td>
						<td width="91%"><a href="${base}backend/bizlist.htm" target="mainFrame"
							class="left-font03" onClick="tupian('17');">商家信息管理</a></td>
					</tr>
				</table> 
				<!--  主题结束    -->
				
			</TD>
		</tr>

	</table>
</body>
</html>
