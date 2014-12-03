<%@ page language="java" pageEncoding="UTF-8" info="买客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
.header a {
	text-decoration: none;
}

.header .header-b a {
	font-family: '宋体 Regular', '宋体';
	font-weight: 400;
	font-style: normal;
	font-size: 18px;
	text-decoration: none;
}

.header .share {
	font-family: '宋体 Bold', '宋体';
	font-weight: 700;
	font-style: normal;
	font-size: 20px;
	color: #666666;
}

.header .user {
	font-family: '宋体 Regular', '宋体';
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	color: #000000;
	text-align: center;
}
</style>

<div class="header" style="float: left; width: 100%">
		<div class="row-fluid"  style="height: 60px;line-height: 60px;width: 100%;float: left;">
			<div class="span1" style="margin-left: 60px;"></div>
			<div class="span10" style="margin-left: 0px; float: left;">
				<div class=""
					style="margin-left: 0px; float: left; width: 20%; max-width: 260px;">
					<img style="height: 58px; width: 258px;"
						src="${images}/mikrr-logo.jpg">
				</div>
				<div class=""
					style="margin-left: 20px; float: left; line-height: 60px;">
					<span class="share">汽车主题分享社区</span>
				</div>
				<div class=""
					style="margin-left: 0px; float: right; line-height: 60px;">
					<span class="user">${cuser}</span>
					<c:if test="${cuser != null}">
						<a href="${base}user/set.htm" style="margin-left: 30px;"><span
							class="user">账号设置</span></a>
					</c:if>
					<c:if test="${cuser == null}">
						<a href="${base}user/register.htm" style="margin-left: 30px;"><span
							class="user">注册</span></a>
					</c:if>
					<c:if test="${cuser != null}">
						<a class="user" style="margin-left: 30px;"
							href="${base}security/logout.htm">退出</a>
					</c:if>
					<c:if test="${cuser == null}">
						<a class="user" style="margin-left: 30px;"
							href="${base}user/loginpage.htm">登录</a>
					</c:if>
				</div>
			</div>
			<div class="span1"></div>

		</div>

		<div class="row-fluid header-b"  style="background-color: #F2F2F2;height: 40px;line-height: 40px;width: 100%;float: left;">
			<div class="span1" style="margin-left: 60px;"></div>
			<div class="span10" style="margin-left: 0px; float: left;">
				<div >
				<a class="home" href="${base}"  style="margin-left: 30px">首页</a> 
				<a class="jhs" href="${base}user/jyh.htm"  style="margin-left: 30px">聚优惠</a>
				<a class="about" href="${base}user/aboutus.htm" style="margin-left: 30px" >关于我们</a>
				</div>
			</div>
			<div class="span1"></div>

		</div>

</div>