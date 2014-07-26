<%@ page language="java" pageEncoding="UTF-8" info="买客网"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link href="${styles}/tool.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${scripts}/tool.js"></script>
</head>
<body>
	<div id="top">
		<!-- 工具栏 -->
		<div id="toptitle" style="border-width: 0px;">
			<a href="/blog/bloghomepage.html"> <img
				src="${base}images/user/homeblog/logo-top2.png"
				onmouseover="{this.src='${base}images/user/homeblog/logo-top1.png';}"
				onmouseout="{this.src='${base}images/user/homeblog/logo-top2.png';}" />
			</a> <span class="top_span" onclick="showPage('home');"> 
			<font class="top_font">首页</font>
			</span> <span class="top_span" style="margin-left: 60px;"> <font
				class="top_font">应用</font>
			</span> <span class="top_span" style="margin-left: 120px;"> <font
				class="top_font">微主题</font>
			</span> <span class="top_span" style="margin-left: 180px;"> <font
				class="top_font">游戏</font>
			</span> <span class="top_span" style="margin-left: 490px; width: 120px;"> <font
				id="userName" class="top_font">${cuserName}</font>
			</span> <span id="comment_span" class="top_span"
				style="margin-left: 610px; height: 45px; width: 50px; z-index: 1200"> <font
				class="top_font"> <img id="comment_top_img"
					src="${base}images/user/homeblog/comment_top1.png" /></font>
			</span> <span id="userset_span" class="top_span"
				style="margin-left: 670px; height: 45px; width: 50px; z-index: 1200"> <font
				class="top_font"> <img id="userset_top_img"
					src="${base}images/user/homeblog/userset_top1.png" /></font>
			</span>
			<div id="comment_top" 
				style="position: absolute; width: 80px; height: 90px; margin-left: 559px; 
				border: 1px solid gray; background-color: #FFFFFF; z-index: 1100; margin-top: 35px; 
				display: none; line-height: 1.5; font-size: 13px; padding: 10px;"
				>
				<a>查看评论</a><br /> <a>查看共同评论</a><br /> <a>查看@我</a>
			</div>
			<div id="userset_top"
				style="position: absolute; width: 80px; height: 60px; margin-left: 619px; 
				border: 1px solid gray; background-color: #FFFFFF; z-index: 1100; margin-top: 35px; 
				display: none; line-height: 1.5; font-size: 13px; padding: 10px;">
				<a href="#">账号设置</a><br /><a href="#">模板设置</a>
			</div>
			<div id="exist" class="top_span"
				style="margin-left: 910px; top: 0px; border: 0px;">
				<a target="_top" class="" href="${base}security/logout.htm">
				<font class="top_font" >退出</font></a>
			</div>
		</div>
	</div>
</body>
</html>
