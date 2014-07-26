<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="common.jsp"/>
<link href="${styles}/mainnew.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${scripts}/main.js"></script>
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
					<div style="float:left; "><label class="labTitle">主题名称:</label><input type="text" name="blogtitle" class="blogTitle"></div>
					<div style="float:left;"><label class="labText">主题内容:</label><textarea id="blogText" class="blogText"></textarea></div>
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
							<span  id="blog_contenttop1" onclick="backColor('blog_contenttop1');" >&nbsp;全部&nbsp;</span>
							<span id="blog_contenttop2" onclick="backColor('blog_contenttop2');"  style="margin-left: 20px;">&nbsp;自己&nbsp;</span>
						</div>
					</div>
					<!-- 微博内容区 -->
					<div id="home_blog_main">
						<ul id="theme_list" style="list-style-type:none">
		          			<li class="theme" style="border:#666 1px solid;height:230px; border-left:0;border-right:0;">
		          			<div class="vline"><img id="head_portrait" height="50px" width="50px" 
		          				src="${base}images/user/homeblog/friends_img1.png">
		          			</div>
		          			<div class="vline">
		          				<div class="context">今天心情特别好</div>
		          				<div class="imgs">
			          				<img id="head_portrait" height="50px" width="50px" src="${base}images/user/homeblog/friends_img1.png">
			          				<img id="head_portrait" height="50px" width="50px" src="${base}images/user/homeblog/friends_img1.png">
		          				</div>
		          			</div>
		          			</li>
		          			<li class="theme" style="height:230px;border:#666 1px solid; border-left:0;border-right:0;">
			          			<div class="vline"><img id="head_portrait" height="50px" width="50px" 
		          				src="${base}images/user/homeblog/friends_img1.png">
		          			</div>
		          			<div class="vline">
		          				<div class="context">今天心情特别好</div>
		          				<div class="imgs">
			          				<img id="head_portrait" height="50px" width="50px" src="${base}images/user/homeblog/friends_img1.png">
			          				<img id="head_portrait" height="50px" width="50px" src="${base}images/user/homeblog/friends_img1.png">
		          				</div>
		          			</div>
		          			</li>
		          				<li class="theme" style="height:230px;border:#666 1px solid; border-left:0;border-right:0;">
			          			<div class="vline"><img id="head_portrait" height="50px" width="50px" 
		          				src="${base}images/user/homeblog/friends_img1.png">
		          			</div>
		          			<div class="vline">
		          				<div class="context">今天心情特别好</div>
		          				<div class="imgs">
			          				<img id="head_portrait" height="50px" width="50px" src="${base}images/user/homeblog/friends_img1.png">
			          				<img id="head_portrait" height="50px" width="50px" src="${base}images/user/homeblog/friends_img1.png">
		          				</div>
		          			</div>
		          			</li>
		          				<li class="theme" style="height:230px;border:#666 1px solid; border-left:0;border-right:0;">
			          			<div class="vline"><img id="head_portrait" height="50px" width="50px" 
		          				src="${base}images/user/homeblog/friends_img1.png">
		          			</div>
		          			<div class="vline">
		          				<div class="context">今天心情特别好</div>
		          				<div class="imgs">
			          				<img id="head_portrait" height="50px" width="50px" src="${base}images/user/homeblog/friends_img1.png">
			          				<img id="head_portrait" height="50px" width="50px" src="${base}images/user/homeblog/friends_img1.png">
		          				</div>
		          			</div>
		          			</li>
		          				<li class="theme" style="height:230px;border:#666 1px solid; border-left:0;border-right:0;">
			          			<div class="vline"><img id="head_portrait" height="50px" width="50px" 
		          				src="${base}images/user/homeblog/friends_img1.png">
		          			</div>
		          			<div class="vline">
		          				<div class="context">今天心情特别好</div>
		          				<div class="imgs">
			          				<img id="head_portrait" height="50px" width="50px" src="${base}images/user/homeblog/friends_img1.png">
			          				<img id="head_portrait" height="50px" width="50px" src="${base}images/user/homeblog/friends_img1.png">
		          				</div>
		          			</div>
		          			</li>
		          				<li class="theme" style="height:230px;border:#666 1px solid; border-left:0;border-right:0;">
			          			<div class="vline"><img id="head_portrait" height="50px" width="50px" 
		          				src="${base}images/user/homeblog/friends_img1.png">
		          			</div>
		          			<div class="vline">
		          				<div class="context">今天心情特别好</div>
		          				<div class="imgs">
			          				<img id="head_portrait" height="50px" width="50px" src="${base}images/user/homeblog/friends_img1.png">
			          				<img id="head_portrait" height="50px" width="50px" src="${base}images/user/homeblog/friends_img1.png">
		          				</div>
		          			</div>
		          			</li>
		          				<li class="theme" style="height:230px;border:#666 1px solid; border-left:0;border-right:0;">
			          			<div class="vline"><img id="head_portrait" height="50px" width="50px" 
		          				src="${base}images/user/homeblog/friends_img1.png">
		          			</div>
		          			<div class="vline">
		          				<div class="context">今天心情特别好</div>
		          				<div class="imgs">
			          				<img id="head_portrait" height="50px" width="50px" src="${base}images/user/homeblog/friends_img1.png">
			          				<img id="head_portrait" height="50px" width="50px" src="${base}images/user/homeblog/friends_img1.png">
		          				</div>
		          			</div>
		          			</li>
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
		
	</div>
</div>
<input type="hidden" class="imgurl"/>
</body>
</html>