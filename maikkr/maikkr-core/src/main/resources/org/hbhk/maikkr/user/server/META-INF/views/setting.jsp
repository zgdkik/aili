<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="common.jsp"/>
<link href="${styles}/comment.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${scripts}/setting.js"></script>
<script type="text/javascript">
var seesionid="${pageContext.session.id}";
</script>
<body>
<jsp:include page="tool.jsp"/>
<div id="main" >
    <div id="center" >
		<!-- 左边部分 -->
			<div id="ct_left">
			<div  class="left_class" type="1">
				<img id="home_img" style="margin-top: 5px;margin-left: 20px;" src="${base}images/user/touxiang.png">
				<span style="margin-top: 5px;margin-left: 10px;font-size:  14px;color: #445522;">
				<a style="color:#333333;text-decoration:none;font-size:14px;color:#445522;"  href="javascript:void(0)">基本信息</a>
				</span>
			</div>
			<div class="left_class" type="2">
				<img id="collect_img" style="margin-top: 5px;margin-left: 20px;" src="${base}images/user/touxiang1.png">
				<span style="margin-top: 5px;margin-left: 10px;font-size:  14px;color: #445522;">
				<a style="color:#333333;text-decoration:none;font-size:14px;color:#445522;" href="javascript:void(0)" >修改头像</a>
				</span>
			</div>
			<div class="left_class"  type="3">
				<img id="collect_img" style="margin-top: 5px;margin-left: 20px;" src="${base}images/user/touxiang3.png">
				<span  style="margin-top: 5px;margin-left: 10px;">
				<a style="color:#333333;text-decoration:none;font-size:14px;color:#445522;" href="javascript:void(0)">修改密码</a></span>
			</div>
		</div>
		<!--  -->
		<div id="ct_center" >
			<div id="commonInfo"  align="center" class="1 showInfo">
			 <h3 style="color: blue;">基本信息</h3>
			 <h3 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h3>
			 <div align="left" style="margin-left: 30px">
			 	<span style="margin-right: 28px">账号</span><input type="text" style="width: 200px;margin-left: 10px" value="${cuser}" disabled="disabled"><br>
			 	<span style="margin-right: 28px">昵称</span><input type="text" class="nickname" style="width: 200px;margin-left: 10px;margin-top: 5px" value="${cuserName}"><br>
			 	<span>绑定邮箱</span><input type="text" class="email" placeholder="方便找回你的密码" style="width: 200px;margin-left: 10px;margin-top: 5px" value="${uc.remail}">
			 </div>
			  <h3 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h3>
			 <div>
			  <input type="button" class="nickname_btn" value="修改">
			 </div>
			</div>
			<div id="updateImg" style="display: none" align="center"  class="2 showInfo">
			 <h3 style="color: blue;">修改头像</h3>
			 <h3 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h3>
			 <div align="left" style="margin-left: 30px">
			 	<span>原始头像</span> <c:if test="${userInfo eq null}">
				 <img height="80px" width="80px" 
				 src="${base}images/security/default_head.png">
			    </c:if>
			   <c:if test="${userInfo ne null}">
				 <img  height="80px" width="80px" 
				 src="${base}${userInfo.userHeadImg}">
			    </c:if>
			 </div>
			  <h3 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h3>
			 <div>
			  	<div id="uploadHeadImg"></div>
			 </div> 
			</div>
			<div id="updatePwd" align="center"  style="display: none" class="3 showInfo">
				<h3 style="color: blue;">修改密码</h3>
			    <h3 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h3>
			<div align="left" style="margin-left: 30px">
			<span style="width: 60px">原始密码</span><input id="rpwd" class="non-empty" type="password" style="width: 200px;margin-left: 10px;margin-top: 5px"><br>
			<span style="width: 60px">新密码</span><input id="pwd" class="non-empty" type="password" style="width: 200px;margin-left: 24px;margin-top: 5px"><br>
			<span style="width: 60px">确认密码</span><input id="cpwd"  class="non-empty" type="password" style="width: 200px;margin-left: 10px;margin-top: 5px">
			</div>
			 <h3 style="border-bottom:1px solid #D9D9D9; height:1px; margin-left: 2px;margin-right: 2px"></h3>
			 <div>
			  <input type="button" class="updatePwd" value="修改">
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
<input type="hidden" class="imgurl"/>
</body>
</html>