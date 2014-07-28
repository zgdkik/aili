<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="common.jsp"/>
<link href="${styles}/comment.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${scripts}/comment.js"></script>
<body>
<jsp:include page="tool.jsp"/>
<div id="main" >
	<%-- <div style="background-image: url('${base}images/user/back_img.png');width: 100%;
		    height: 100%;float: left;z-index: -1;"></div> --%>
    <div id="center" >
		<!-- 左边部分 -->
		<div id="ct_left">
			<div id="home" class="left_class">
				<img id="home_img" style="margin-top: 5px;margin-left: 20px;" src="${base}images/user/homeblog/home_back1.png">
				<span style="margin-top: 5px;margin-left: 10px;font-size:  14px;color: #445522;">主题</span>
			</div>
			<div id="message" class="left_class" >
				<img  id="message_img" style="margin-top: 5px;margin-left: 20px;"src="${base}images/user/homeblog/message_back1.png">
				<span style="margin-top: 5px;margin-left: 10px;font-size:  14px;color: #445522;">消息</span>
			</div>
			<div id="collect" class="left_class">
				<img id="collect_img" style="margin-top: 5px;margin-left: 20px;" src="${base}images/user/homeblog/collect_back1.png">
				<span style="margin-top: 5px;margin-left: 10px;font-size:  14px;color: #445522;">收藏</span>
			</div>
			<div class="left_class">
				<img id="collect_img" style="margin-top: 5px;margin-left: 20px;" src="${base}images/user/homeblog/friends_img1.png">
				<span  style="margin-top: 5px;margin-left: 10px;">
				<a style="color:#333333;text-decoration:none;font-size:  14px;color: #445522;" href="/blog/relationCenter.html">好友管理</a></span>
			</div>
		</div>
		<!-- 评论区域 -->
		<div id="ct_center" >
			<!-- 评论区域 -->
			<!-- 标题 -->
			<!-- 正文 -->
			<!-- 图片 -->
			<!--所以评论 -->
		</div>
		
	</div>
</div>
</body>
</html>