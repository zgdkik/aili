<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/hbhk" prefix="hbhk" %>
<hbhk:module  />
<link rel="stylesheet" type="text/css" href="${styles}/home.css">
<img src="${images}/homebg.png" width="100%" height="100%"></img>
<div class="homeMsg">
	<ul>
		<li>
			<p>公共的JS代码和CSS代码写到哪里？</p>
			#公共的JS代码放在web工程的webapp/scripts/common/common.js里面<br>
			#公共的CSS代码放在web工程的webapp/styles/common/common.css里面
		</li><br>
		<li>
			<p>怎么配置主页的链接？</p>
			 进入(系统配置-系统参数)<br>
			 点击新增：键输入 dpap.homeaction,值输入自定义的action的路径(例如：/home/myindex.action)
		</li><br>
		<li>
			<p>如何自定义顶部工具栏的组件？</p>
			在web工程的webapp/scripts/common/common.js里面，添加如下方法<br>
			// 此函数返回顶部工具栏控件的组件数组，数组的顺序就是组件显示的顺序<br>
			function getFrameToolbarItems() {<br>
			&nbsp;return [ { xtype:'muserinfospace' }, // 用户信息按钮<br>
			&nbsp;&nbsp;{ xtype:'mannounceLink' }, // 通知按钮<br>
			&nbsp;&nbsp;{ xtype:'mhomeLink' }, // 主页按钮<br>
			&nbsp;&nbsp;{ xtype:'mnavConfigLink' }, // 常用功能按钮<br>
			&nbsp;&nbsp;{ xtype:'mlogout' } // 登出按钮<br>
			&nbsp;&nbsp;// { xtype:'mybutton' } // 添加自定义的组件<br>
			&nbsp;];}
		</li>
	</ul>
</div>
