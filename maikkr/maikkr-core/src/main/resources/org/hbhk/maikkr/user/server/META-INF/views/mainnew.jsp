<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="common.jsp"/>
<link href="${styles}/mainnew.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${scripts}/main.js"></script>
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
				<span style="margin-top: 5px;margin-left: 10px;font-size:  14px;color: #445522;">主题</span>
			</div>
			<%-- <div id="message" class="left_class" >
				<img  id="message_img" style="margin-top: 5px;margin-left: 20px;"src="${base}images/user/homeblog/message_back1.png">
				<span style="margin-top: 5px;margin-left: 10px;font-size:  14px;color: #445522;">消息</span>
			</div> --%>
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
		<!--  -->
		<div id="ct_center" >
			<!-- 首页 -->
			<div id="ct_center_home">
				<div id="home_top">
					<div class="info">
						<div id="home_top_top1">
							有什么新鲜的主题告诉大家?
						</div>
						<div id="home_top_top2">
							发言请遵守社区公约，最多可以输入
							<span>500</span>个字
						</div>
					</div>
					<div style="float:left; "><label class="labTitle">输入或选择你喜欢的主题?分享你的生活经验!</label>
					<!-- <input type="text" name="blogtitle" class="blogTitle"> -->
					<div  class="blogTitle"  style="position:relative;"> 
				        <span style="margin-left:100px;width:18px;overflow:hidden;"> 
				            <select id="theme-select" style="width:300px;height:30px;margin-left:-100px" onchange="this.parentNode.nextSibling.value=this.value"> 
				            </select>
				        </span>
				        <input name="box" id="theme-select-text" style="width:270px;height:30px;position:absolute;left:0px;"> 
				    </div>
					</div>
					<div style="float:left;"><textarea id="blogText" class="blogText"></textarea></div>
					<div  style="border-width:0px;float:left;margin-left: 110px">
						<div id="uploadImg"></div>
					</div>
					<div  style="float:left;">
					  	<input type="button" value="发布" class="sendTheme" style="width: 80px; height: 30;margin-left: 450px" />
					</div>
				</div>
						<!-- 微博主体内容 -->
				<div id="home_blog" style="float:left;">
					<!-- 头部信息 -->
					<div id="home_blog_top">
						<div style="margin-left: 30px; border-bottom: 0px solid red;">
							<span><a style="font-size:16px;">主题</a> </span>
						</div>
						<img  style="margin-left: 20px;" src="${base}images/user/homeblog/blog_main_img1.png">
						<div style="border-bottom: 2px solid red;margin-left: 65px;width: 510px;
						     margin-top: -6px;"></div>
						<div style="margin-top: 10px; margin-left: 20px;">
							<span  class="blog_contenttop">&nbsp;全部&nbsp;</span>
							<span class="blog_contenttop" style="margin-left: 20px;">&nbsp;自己&nbsp;</span>
						</div>
					</div>
					<!-- 微博内容区 -->
					<div id="home_blog_main" style="height: auto;">
						<ul id="theme_list" style="list-style-type:none">
		          		
	          			</ul>
					</div>
			</div>
					<!--消息-->
			<div id="ct_center_message" style="float:left;display: none;">
			   <div style=" font-size:16px;width: 560px;height:30px;margin:20px;border-bottom: 2px solid red;">
			       <a>@我的主题</a> <a>@我的评论的</a>
			    </div>
			    <div id="ct_center_message_main" style=" width: 600px;margin-top:50px;">
				</div>
			</div>
			<!-- 收藏 -->
			<div id="ct_center_collect"  style="display: none;float:left;">
			    <div style="width: 560px;height:30px;margin:20px;border-bottom: 2px solid red;">
			       <a style="font-size:16px;">我的收藏</a>
			     </div>
			    <div id="ct_center_collect_main" style="width: 600px;margin-top:50px;">
				</div>
			</div>
			</div>
		</div>
		<!-- 右边部分 -->
		<div id="ct_right" style="margin-left: 750px;">
			<div id="userInfo" style="height: 145px;border-bottom-width: 2px;
			width: 230px;display: block;">
			<a>
			 <c:if test="${user == null}">
				 <img style="border-bottom-style: solid;border-bottom-width: 1px;"
				 id="head_portrait_right" height="80px" width="80px" 
				 src="/maikkr/images/security/default_head.png">
			 </c:if>
			 <c:if test="${user != null}">
				 <img style="border-bottom-style: solid;border-bottom-width: 1px;"
				 id="head_portrait_right" height="80px" width="80px" 
				 src="${base}${user.userHeadImg}">
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
	          		<li><a href="#">热门主题</a></li>
	          		<li><a href="#">最新主题</a></li>
	          	</ul>
	         </div>
		</div>
		
		
	</div>
</div>
<input type="hidden" class="imgurl"/>
</body>
</html>