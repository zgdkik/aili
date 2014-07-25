<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="common.jsp"/>
<link href="${styles}/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${scripts}/main.js"></script>
<body>
<jsp:include page="tool.jsp"/>
<div id="main" >
	<div style="background-image: url('${base}images/user/back_img.png');width: 100%;
		    height: 100%;float: left;z-index: -1;"></div>
    <div id="center">
		<!-- 左边部分 -->
		<div id="ct_left">
			<div id="home" class="left_class" type="home">
				<img id="home_img" style="margin-top: 5px;margin-left: 20px;" src="${base}images/user/homeblog/home_back1.png">
				<span style="position:absolute;margin-top: 10px;margin-left: 10px;font-size:  14px;color: #445522;">首页</span>
			</div>
			<div id="message" class="left_class" type="message;">
				<img  id="message_img" style="margin-top: 5px;margin-left: 20px;"src="${base}images/user/homeblog/message_back1.png">
				<span style="position:absolute;margin-top: 10px;margin-left: 10px;font-size:  14px;color: #445522;">消息</span>
			</div>
			<div id="collect" class="left_class" type="collect">
				<img id="collect_img" style="margin-top: 5px;margin-left: 20px;" src="${base}images/user/homeblog/collect_back1.png">
				<span style="position:absolute;margin-top: 10px;margin-left: 10px;font-size:  14px;color: #445522;">收藏</span>
			</div>
			<div class="left_class">
				<img id="collect_img" style="margin-top: 5px;margin-left: 20px;" src="${base}images/user/homeblog/friends_img1.png">
				<span  style="position:absolute;margin-top: 10px;margin-left: 10px;">
				<a style="color:#333333;text-decoration:none;font-size:  14px;color: #445522;" href="/blog/relationCenter.html">好友管理</a></span>
			</div>
		</div>
		<!-- 中间部分 -->
		<div id="ct_center" style="float: ">
			<!-- 首页 -->
			<div id="ct_center_home">
				<div id="home_top">
					<div id="home_top_top1">
						有什么新鲜的主题告诉大家?
					</div>
					<div id="home_top_top2">
						发言请遵守社区公约，最多可以输入
						<span>500</span>个字
					</div>
					<div><label class="labTitle">主题名称:</label><input type="text" name="blogtitle" class="blogTitle"></div>
					<div><label class="labText">主题内容:</label><textarea id="blogText" class="blogText"></textarea></div>
					<div  style="border-width:0px;width:540px;height:50px;position:absolute;margin-top: 200px; margin-left: 20px;">
					  <span  id="uploadImg"></span>
					  <input type="button" value="发布"
						 style="width: 80px; height: 30; margin-left: 460px; margin-top:-30px;" />
					</div>
				</div>
				<!-- 微博主体内容 -->
				<div id="home_blog">
					<!-- 头部信息 -->
					<div id="home_blog_top">
						<div style="margin-left: 30px; border-bottom: 0px solid red;">
							<span><b>主题</b> </span>
							<span style="margin-left: 20px;">动态</span>
						</div>
						<img  style="margin-left: 20px;" src="${base}images/user/homeblog/blog_main_img1.png">
						<div style="border-bottom: 2px solid red;margin-left: 65px;width: 510px;
						     margin-top: -4px;"></div>
						<div style="margin-top: 10px; margin-left: 20px;">
							<span  id="blog_contenttop1" onclick="backColor('blog_contenttop1');" >&nbsp;全部&nbsp;</span>
							<span id="blog_contenttop2" onclick="backColor('blog_contenttop2');"  style="margin-left: 20px;">&nbsp;原创&nbsp;</span>
							<span id="blog_contenttop3" onclick="backColor('blog_contenttop3');" style="margin-left: 20px;">&nbsp;图片&nbsp;</span>
						</div>
					</div>
					<!-- 微博内容区 -->
					<div id="home_blog_main">
						<ul>
		          		<li><a>热门主题1</a></li>
		          		<li><a>热门主题2</a></li>
		          		<li><a>热门主题3</a></li>
		          		<li><a>热门主题4</a></li>
	          			</ul>
					</div>
				</div>
			</div>
			<!--消息-->
			<div id="ct_center_message" style="display: none;">
			   <div style=" font-size:12px;position: absolute;width: 560px;height:30px;margin:20px;border-bottom: 2px solid red;">
			       <span>@我的微博</span> <span>@我的评论的</span>
			    </div>
			    <div id="ct_center_message_main" style=" position: absolute;width: 600px;margin-top:50px;">
				</div>
			</div>
			<!-- 收藏 -->
			<div id="ct_center_collect"  style="display: none;">
			    <div style=" position: absolute;width: 560px;height:30px;margin:20px;border-bottom: 2px solid red;">
			       <b>我的收藏</b>
			     </div>
			    <div id="ct_center_collect_main" style=" position: absolute;width: 600px;margin-top:50px;">
				</div>
			</div>
		</div>
		
		<!-- 右边部分 -->
		<div id="ct_right">
	         <%--  <img  src="${base}images/user/homeblog/right2.png" style="margin-left: 10px;"> --%>
	          <h3>热门主题</h3>
	          	<ul>
	          		<li>热门主题1</li>
	          		<li>热门主题2</li>
	          		<li>热门主题3</li>
	          		<li>热门主题4</li>
	          	</ul>
	          <h3>最新主题</h3>
	          <ul>
	          		<li>最新主题1</li>
	          		<li>最新主题2</li>
	          		<li>最新主题3</li>
	          		<li>最新主题4</li>
	          	</ul>   
		</div>
		<!-- 返回顶部 -->
		<div id="bottom_top"  style="position: absolute;width: 20px;height: 80px;margin-top:460px;font-size:12px;
				      margin-left: 980px;background-color: #EEEEEE;color:#808080;border: 1px solid #DDDDDD; "  >
		      <img id="bottom_top1" src="${base}images/user/homeblog/bottom_top1.png" />
		      <center><span class="returnTop">返回顶部</span></center> 
		</div>
		<!--底部背景 -->   
		<%-- <div id="ct_bottom"  style="margin-bottom: 0px;margin-top:640px;
			 background-color: white;text-align: center;
			 background-image: url('${base}images/user/footer_top_bg.jpg');">
			<span  style="color:#808080;width: 100%" >Copyright © 1996-2014 SINA HBHK网络技术有限公司</span>
		</div> --%>
	</div>
</div>
<input type="hidden" class="imgurl"/>
</body>
</html>