<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<c:import url="../../commons/common-meta.jsp" />
<title>壹米滴答</title>
<link href="${staticbase}/easyui-home/css/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${staticbase}/easyui-home/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="${staticbase}/easyui-home/themes/gray/easyui.css" />
	
<link rel="stylesheet" type="text/css"
	href="${base}/styles/home/custom-ui.css" />
	<script type="text/javascript" src="${staticbase}/easyui-home/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${staticbase}/easyui-home/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${staticbase}/easyui-home/js/jquery.cookies.js"></script>
	<script type="text/javascript" src="${base}/scripts/home/plugins.js"></script>
	<script type="text/javascript" src="${base}/scripts/home/home-ui.js"></script>
</head>
<script type="text/javascript">
	var _menus={};
	_menus.menus = eval("(" + '${menus}' + ")");
	//移除菜单的跳动事件
	window.addEventListener('load', function () {
		   $('a').unbind("mouseleave"); 
		   $('a').unbind("mouseenter"); 
		   $('a').unbind("mouselout"); 
		   $('a').unbind("mouselover"); 
	});
</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden;" scroll="no" >
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<!-- 顶部 -->
	<div region="north" class="content" border="false"
		style="overflow: hidden; height: 40px; background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%; line-height: 40px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">
		<span style="margin-left: 10px;display: block;float: left;"><img
			src="${base}/images/logo.png" width="140" height="40"/>
		</span>
		<span style="margin-left: 29px;border-left: 1px solid #fcb814;font-size: 32px;height: 40px;display: block;float: left;">
			<label style="margin-left: 10px; font: 25px fontNameRegular, Arial, sans-serif;color: #FCB714">${app_title}</label>
		</span>
		<span style="float: right;margin-right: 10px; line-height: 40px;">
			<a href="javascript:void(0)" title="全屏" class="fullscreen-f-e" iconCls="1" ><img  src="${base}/images/home/icon/fullscreen.png" alt="全屏" style="margin-top: 10px;" width="20" height="20"/></a>
		</span> 
		
		<span style="float: right;margin-right: 10px; line-height: 40px;" class="head">欢迎
			${userContext.userName} <a href="#" id="loginOut">安全退出</a>
		</span> 
	</div>
	<!-- 底部 -->
	<div region="south" 
		style="height: 30px; background: #D2E0F2;">
	<!-- 	<div class="footer">Copyright © 2015-2020 上海壹米滴答供应链管理有限公司. All
			rights reserved.沪ICP备15046748号</div> -->
		<div class="footer">公司:${userContext.compName} 部门:${userContext.deptName} 姓名:${userContext.name} 工号:${userContext.userName} </div>
	</div>
	<!--菜单  -->
	<div region="west" hide="true" class="nav-menu"  title="导航菜单"
		style="width: 180px;" id="west">
		<div id="nav" class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->

		</div>
	</div>
	<!-- 展示区域 -->
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden !important;">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="主页"
				style="padding: 20px; overflow: hidden; color: #383635;">
				<h1 style="font-size: 24px;">欢迎使用壹米滴答平台管理系统</h1>
			</div>
		</div>
	</div>
	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
	</div>
</body>
</html>
