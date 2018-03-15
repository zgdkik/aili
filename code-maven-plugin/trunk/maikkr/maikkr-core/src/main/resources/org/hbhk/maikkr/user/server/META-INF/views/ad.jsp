<%@ page language="java" pageEncoding="UTF-8" info="买客网"%>
<html lang="zh-cn">
<head>
<style type="text/css">
.duilian {
	top: 100px;
	position: absolute;
	width: 102px;
	overflow: hidden;
	display: none;
}

.duilian_left {
	left: 6px;
}

.duilian_right {
	right: 6px;
}

.duilian_con {
	border: rgb(60, 32, 211) solid 1px;
	width: 100px;
	height: 300px;
	overflow: hidden;
}

.duilian_close {
	width: 100%;
	height: 24px;
	line-height: 24px;
	text-align: center;
	display: block;
	font-size: 13px;
	color: #555555;
	text-decoration: none;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var duilian = $("div.duilian");
		var duilian_close = $("a.duilian_close");
		var window_w = $(window).width();
		if (window_w > 1000) {
			duilian.show();
		}
		$(window).scroll(function() {
			var scrollTop = $(window).scrollTop();
			duilian.stop().animate({
				top : scrollTop + 100
			});
		});
		duilian_close.click(function() {
			$(this).parent().hide();
			return false;
		});
	});
</script>
</head>
<body>
	<!--下面是对联广告的html代码结构-->
	<div class="duilian duilian_left">
		<div class="duilian_con">欢迎访问米客网</div>
		<a href="#" class="duilian_close">X关闭</a>
	</div>
	<div class="duilian duilian_right">
		<div class="duilian_con">欢迎访问米客网</div>
		<a href="#" class="duilian_close">X关闭</a>
	</div>
</body>
</html>
