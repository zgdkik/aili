<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="common.jsp"/>
<link href="${styles}/comment.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${scripts}/comment.js"></script>
<script type="text/javascript">
var seesionid="${pageContext.session.id}";
</script>
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
		
		</div>
		<!-- 右边部分 -->
		<jsp:include page="right.jsp"/>
		
	</div>
</div>
<input type="hidden" class="imgurl"/>
</body>
</html>