<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="common.jsp"/>
<head>
	<title>买客买家网</title>
	<meta name ="keywords" content="买客买家网,买客,买家,无尽分享,交流">
	<meta name ="description" content="买客买家网,买客,买家,无尽分享,交流">
	<link href="${styles}/mainnew.css" rel="stylesheet" type="text/css" />
	<link href="${styles}/backTop.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${scripts}/myTheme.js"></script>
	<script type="text/javascript" src="${scripts}/backTop.js"></script>
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
					<ul id="theme_list" style="list-style-type:none">
	          		
          			</ul>
				</div>
			</div>
		</div>
		<!-- 右边部分 -->
		<div id="ct_right" style="margin-left: 750px;">
			<div id="userInfo" style="height: 145px;border-bottom-width: 2px;
			width: 230px;display: block;">
			<a>
			 <c:if test="${userInfo == null}">
				 <img style="border-bottom-style: solid;border-bottom-width: 1px;"
				 id="head_portrait_right" height="80px" width="80px" 
				 src="${base}images/security/default_head.png">
			 </c:if>
			 <c:if test="${userInfo != null}">
				 <img style="border-bottom-style: solid;border-bottom-width: 1px;"
				 id="head_portrait_right" height="80px" width="80px" 
				 src="${base}${userInfo.userHeadImg}">
			 </c:if>
			</a>
			<a style="display: inline-block;overflow: hidden;font-size: 16px;font-weight: bold;word-wrap: break-word;color: black;" >${cuserName}</a>
			<ul  style="margin: 20px 20px;display: block;">
   			<li style="display: block;float: left;margin: 0 5px 0 0;padding: 0 5px 0 0;border-right-width: 1px;border-right-style: solid;border-color: #e6e6e6;">
   			<a href="" ><strong style="display: block;margin: 0 0 4px;font-weight: 400;line-height: 15px;font-family: tahoma;" 
   			 node-type="theme">${tc}</strong><span>主题</span></a></li>
   			 <li style="display: block; float: left;margin: 0 5px 0 0;padding: 0 5px 0 0;border-right-width: 1px;border-right-style: solid;border-color: #e6e6e6;" >
         	<a href=""><strong style="display: block;margin: 0 0 4px;font-weight: 400;line-height: 15px;font-family: tahoma;" 
         	node-type="attention">${ac}</strong><span>关注 </span></a></li>
    		</ul>
			</div>
			<div style="margin-top: 3px">
	          	<ul class="nav nav-pills nav-stacked">
	          		<li class="active"><a>主题推荐</a></li>
	          		<li><a href="${base}user/newhit.htm">热门主题</a></li>
	          		<li><a href="${base}user/newest.htm">最新主题</a></li>
	          	</ul>
	         </div>
		</div>
		
		
	</div>
</div>
<p id="back-to-top"><a href="#top"><span></span>返回顶部</a></p>
<input type="hidden" class="imgurl"/>
</body>
</html>