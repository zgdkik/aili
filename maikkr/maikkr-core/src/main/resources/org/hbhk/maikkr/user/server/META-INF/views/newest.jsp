<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="common.jsp"/>
<head>
	<title>买客买家网-最新主题</title>
	<meta name ="keywords" content="买客买家网,买客,买家,无尽分享,交流">
	<meta name ="description" content="买客买家网,买客,买家,无尽分享,交流">
	<link href="${styles}/mainnew.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${scripts}/newest.js"></script>
	<script type="text/javascript">
		var seesionid="${pageContext.session.id}";
	</script>
</head>
<body>
<jsp:include page="indexTool.jsp"/>
<div id="main" >
    <div id="center" >
		<!-- 左边部分 -->
				<div id="ct_left">
			<div id="home" class="left_class">
				<img id="home_img" style="margin-top: 5px;margin-left: 20px;" src="${base}images/user/homeblog/home_back1.png">
				<span style="margin-top: 5px;margin-left: 10px;font-size:  14px;color: #445522;"><a class="removeLie" href="${base}user/myTheme.htm">我的主题</a></span>
			</div>
			<%-- <div id="message" class="left_class" >
				<img  id="message_img" style="margin-top: 5px;margin-left: 20px;"src="${base}images/user/homeblog/message_back1.png">
				<span style="margin-top: 5px;margin-left: 10px;font-size:  14px;color: #445522;">消息</span>
			</div> --%>
			<div id="collect" class="left_class">
				<img id="collect_img" style="margin-top: 5px;margin-left: 20px;" src="${base}images/user/homeblog/collect_back1.png">
				<span style="margin-top: 5px;margin-left: 10px;font-size:  14px;color: #445522;"><a class="removeLie" href="${base}user/collect.htm">收藏</a></span>
			</div>
			<div class="left_class">
				<img id="collect_img" style="margin-top: 5px;margin-left: 20px;" src="${base}images/user/homeblog/friends_img1.png">
				<span  style="margin-top: 5px;margin-left: 10px;font-size:  14px;color: #445522;">
				<a class="removeLie" href="${base}user/friends.htm">好友管理</a></span>
			</div>
		</div>
		<!--  -->
		<div id="ct_center" >
				<!-- 微博主体内容 -->
			<div id="home_blog" style="float:left;">
				<!-- 微博内容区 -->
				<div id="home_blog_main" style="height: auto;">
					<h3 style="color: blue;">最新主题</h3>
			 		<h3 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h3>
					<ul id="theme_list" style="list-style-type:none">
	          		
          			</ul>
				</div>
			</div>
		</div>
		<!-- 右边部分 -->
		<jsp:include page="right.jsp"/>
		<jsp:include page="footer.jsp"/>
	</div>
</div>
<p id="back-to-top"><a href="#top"><span></span>返回顶部</a></p>
<input type="hidden" class="imgurl"/>
</body>
</html>