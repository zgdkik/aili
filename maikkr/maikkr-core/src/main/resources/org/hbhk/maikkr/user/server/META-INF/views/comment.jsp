<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp"/>
<link href="${styles}/comment.css" rel="stylesheet" type="text/css" />
<script src="${scripts}/fckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${scripts}/comment.js"></script>
<script type="text/javascript">
 var seesionid="${pageContext.session.id}";
 var url ="${theme.blogUrl}"; 
 var name = "${theme.blogTitle}";
</script>
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
				<span style="margin-top: 5px;margin-left: 10px;font-size:  14px;color: #445522;"><a href="${base}user/main.htm">主题</a></span>
			</div>
			<%-- <div id="message" class="left_class" >
				<img  id="message_img" style="margin-top: 5px;margin-left: 20px;"src="${base}images/user/homeblog/message_back1.png">
				<span style="margin-top: 5px;margin-left: 10px;font-size:  14px;color: #445522;">消息</span>
			</div> --%>
			<div id="collect" class="left_class">
				<img id="collect_img" style="margin-top: 5px;margin-left: 20px;" src="${base}images/user/homeblog/collect_back1.png">
				<span style="margin-top: 5px;margin-left: 10px;font-size:  14px;color: #445522;"><a href="${base}user/collect.htm">收藏</a></span>
			</div>
			<div class="left_class">
				<img id="collect_img" style="margin-top: 5px;margin-left: 20px;" src="${base}images/user/homeblog/friends_img1.png">
				<span  style="margin-top: 5px;margin-left: 10px;">
				<a style="color:#333333;text-decoration:none;font-size:  14px;color: #445522;" href="${base}user/friends.htm">好友管理</a></span>
			</div>
		</div>
		<!--  -->
		<div id="ct_center" >
			<div id="userTheme" style="width: 600px;">
				<div style="margin-left: 25px;margin-right: 25px">
				<ul style="list-style-type:none">
				 <li class="theme">
				 <fmt:formatDate var="date" value="${theme.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="time"></fmt:formatDate>
				 <div class="vline"><img id="head_portrait" height="50px" width="50px" 
				 src="${base}${userInfo.userHeadImg}">${theme.blogUser} <span style="float: right;">时间:<c:out value="${date}"/></span> </div>
				 <div class="vline"><div class="context"><a href="${base}user/${theme.blogUrl}"><h3 style="margin-left: 25px">${theme.blogTitle}</h3></a></div></div>
				 <div class="vline"><div class="context">${theme.blogContent}</div></div>
				 <div class="context_imgs" align="center">
				 <c:forTokens items="${theme.blogLink}" delims="," var="imgurl">
				  <img id="context_img"  src="${base}${imgurl}"><br>
				 </c:forTokens>
                 </div>
                 </li>
				 </ul>
				</div>
			
			</div>
			<h1 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h1>
			<div id="thmenInfo" style="height: 20px;">
				<a style="margin-left: 100px">热度 ${theme.blogHit}</a>
				 <a style="margin-left: 10px">评论 ${theme.blogReview}</a>
				 <a style="margin-left: 10px"><span id="collect">收藏 ${theme.blogCollect}</span></a>
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
			   <textarea  id="editorText" style="width: 400px; margin-left: 75px;height: 150px;" ></textarea>
				<input style="margin-left: 400px;margin-bottom: 10px;margin-top: 10px" type="button" value="发表评论" id="sendComment" title="发表评论">
				</div>
			</div>
			</div>
		</div>
		<!-- 右边部分 -->
		<div id="ct_right" style="margin-left: 750px;">
			<div id="userInfo" style="height: 145px;border-bottom-width: 2px;
			width: 230px;display: block;">
			<a>
			 <c:if test="${userInfo eq null}">
				 <img style="border-bottom-style: solid;border-bottom-width: 1px;"
				 id="head_portrait_right" height="80px" width="80px" 
				 src="${base}images/security/default_head.png">
			 </c:if>
			 <c:if test="${userInfo ne null}">
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
<input type="hidden" id="blogId" value="${theme.id}"/>
</body>
</html>