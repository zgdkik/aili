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
</style>
<script type="text/javascript">
	$("body .fb").focus(function() {
		$(this).css("background-color", "red");
	}).blur(function() {
		$(this).css("background-color", "");
	});
</script>
<ul class="menu" style="">
	<li class="fb home" >首页</li>
	<li class="fb">微主题(我的主题)</li>
	<li class="fb">我的关注</li>
	<li class="fb">收藏</li>
	<li class="fb">好友</li>
</ul>