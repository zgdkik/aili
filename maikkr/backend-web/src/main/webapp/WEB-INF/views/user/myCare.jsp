<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="common.jsp"/>
<head>
	<title>${siteInfo.title}-我的关注</title>
	<link href="${styles}/mainnew.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${scripts}/myTheme.js"></script>
	<script type="text/javascript">
		var seesionid="${pageContext.session.id}";
	</script>
</head>
<body>
<jsp:include page="indexTool.jsp"/>
<div id="main" >
    <div id="center" >
		<!-- 左边部分 -->
		<jsp:include page="left.jsp"/>
		<!-- 中间 -->
		<div id="ct_center" >
				<!-- 微博主体内容 -->
			<div id="home_blog" style="float:left;">
				<!-- 微博内容区 -->
				<div id="home_blog_main" style="height: auto;">
				<h3 style="color: blue;">我的主题</h3>
			 		<h3 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h3>
					<ul id="theme_list" style="list-style-type:none">
	          		
          			</ul>
				</div>
			</div>
		</div>
		<!-- 右边部分 -->
		<jsp:include page="right.jsp"/>
		
	</div>
</div>
<p id="back-to-top"><a href="#top"><span></span>返回顶部</a></p>
<input type="hidden" class="imgurl"/>
</body>
</html>