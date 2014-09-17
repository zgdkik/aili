<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp"/>
<head>
	<title>买客买家网-${theme.blogTitle}</title>
	<meta name ="keywords" content="${theme.blogTitle},买客买家网,买客,买家">
	<meta name ="description" content="${theme.blogTitle},买客买家网,买客,买家">
	<link href="${styles}/comment.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
	 var host = window.location.host;
	 var seesionid="${pageContext.session.id}";
	 var url = window.location.href; 
	 var name = "${theme.blogTitle}";
	 var context = "${theme.blogContent}";
	 with(document)0[(getElementsByTagName('head')[0]||body)
	   .appendChild(createElement('script'))
	   .src='http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='+~(-new Date()/36e5)];
	</script>
	<script type="text/javascript" src="${scripts}/comment.js"></script>
</head>
<body>
<jsp:include page="tool.jsp"/>
<div id="main">
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
			<div id="userTheme" style="width: 600px;">
				<div style="margin-left: 25px;margin-right: 25px">
				<ul style="list-style-type:none">
				 <li class="theme">
				 <fmt:formatDate var="date" value="${theme.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="time"></fmt:formatDate>
				 <div class="vline">
				 <img id="head_portrait" height="50px" width="50px" 
				 src="${base}${theme.userHeadImg}">${theme.blogUser} <span style="float: right;">时间:<c:out value="${date}"/></span> </div>
				 <div class="vline"><div class="context">
				 <a href="${base}user/${theme.blogUrl}" title="${theme.blogTitle}">
				 <h3 style="margin-left: 25px">${theme.blogTitle}</h3></a></div></div>
				 <div class="vline"><div class="context">${theme.blogContent}</div></div>
				 <div class="context_imgs" align="center">
				 <c:forTokens items="${theme.blogLink}" delims="," var="imgurl">
				  <img id="context_img" style="margin-bottom: 5px" width="250" height="250"   src="${base}${imgurl}"><br>
				 </c:forTokens>
                 </div>
                 </li>
				 </ul>
				</div>
			
			</div>
			<h1 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h1>
			<div id="thmenInfo" style="height: 20px;">
				<a style="margin-left: 100px;text-decoration: none;">热度 ${theme.blogHit}</a>
				<a style="margin-left: 10px;text-decoration: none;">评论 ${theme.blogReview}</a>
				<a style="margin-left: 10px;text-decoration: none;"><span id="collect">收藏 ${theme.blogCollect}</span></a>
			</div>
			<h1 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h1>
			<div  style="width: 600px;">
			<div  style="margin-left: 25px;margin-right: 25px">
				<ul id="userComment"  style="list-style-type:none">
				 </ul>
				</div>
			</div>
			<h1 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h1>
			<div id="fckEditor" style="width: 550px;margin-left: 25px;" >
			<div class="panel panel-info">
			   <div class="panel-heading">
			      <h3 class="panel-title">说出你的看法?</h3>
			   </div>
			   <div class="panel-body">
			   <textarea  id="editorText" style="width: 400px; margin-left: 75px;height: 150px;resize:none;" ></textarea>
				<input style="margin-left: 400px;margin-bottom: 10px;margin-top: 10px" type="button" value="发表评论" id="sendComment" title="发表评论">
				</div>
			</div>
			</div>
		</div>
		<!-- 右边部分 -->
		<jsp:include page="right.jsp"/>
		<jsp:include page="footer.jsp"/>
	</div>
</div>
<div class="bdsharebuttonbox" data-tag="share_1">
	<a class="bds_mshare" data-cmd="mshare"></a>
	<a class="bds_qzone" data-cmd="qzone" href="#"></a>
	<a class="bds_tsina" data-cmd="tsina"></a>
	<a class="bds_baidu" data-cmd="baidu"></a>
	<a class="bds_renren" data-cmd="renren"></a>
	<a class="bds_tqq" data-cmd="tqq"></a>
	<a class="bds_more" data-cmd="more">更多</a>
	<a class="bds_count" data-cmd="count"></a>
</div>
<p id="back-to-top"><a href="#top"><span></span>返回顶部</a></p>
<input type="hidden" id="blogId" value="${theme.id}"/>
</body>
</html>