<%@ page language="java" pageEncoding="UTF-8" info="买客网"%>
<style type="text/css">
.menu {
	font-size: 14px;
	color: #000000;
	margin-right: 25px;
	list-style-type: none;
}

.menu li {
	border-bottom: 1px solid #FF6600;
	height: 30px;
	margin-top: 10px;
}
.menu li a {
	text-decoration: none;
	color: black;
}
</style>
<ul class="menu" style="">
	<%-- <li class="fb home"><a href="${base}">首页</a></li> --%>
	<li class="fb myTheme"><a href="${base}user/myTheme.htm">我的主题</a></li>
	<li class="fb mycare"><a href="${base}user/myCare.htm">我的关注</a></li>
	<li class="fb collect-href"><a href="${base}user/collect.htm">收藏</a></li>
 	<li class="fb friends"><a href="${base}user/friends.htm">私信</a></li>
</ul>