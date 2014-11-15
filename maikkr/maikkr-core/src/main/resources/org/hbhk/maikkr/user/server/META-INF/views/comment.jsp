<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}-主题评论</title>
<script type="text/javascript">
	var host = window.location.host;
	var seesionid = "${pageContext.session.id}";
	var url = window.location.href;
	var name = "${theme.carType}";
	var context = "${theme.plannTime}";
	var area = "${theme.area}";
	var bdPic="${file_server}${theme.userHeadImg}";
	with (document)
		0[(getElementsByTagName('head')[0] || body)
				.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='
				+ ~(-new Date() / 36e5)];
</script>
<script type="text/javascript" src="${scripts}/comment.js"></script>
<style type="text/css">
.lrborder {
	border-left: 1px solid #F2F2F2;
	border-right: 1px solid #F2F2F2;
}

.title {
	font-family: '宋体 Bold', '宋体';
	font-weight: 700;
	font-style: normal;
	font-size: 20px;
	color: #666666;
	margin-left: 30px;
}

.theme-font {
	font-family: '宋体 Bold', '宋体';
	font-weight: 700;
	font-style: normal;
	font-size: 20px;
	color: #000000;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- 中间部分 -->
	<div class="row-fluid" style="min-height: 500px">
		<div class="span1" style="margin-left: 60px;"></div>

		<div class="span2 lrborder" style="margin-left: 0px;">
			<jsp:include page="menu.jsp" />
		</div>
		<div class="span6" style="margin-left: 0px;">
			<div class="ctheme">
				<fmt:formatDate var="date" value="${theme.createTime}"
					pattern="yyyy-MM-dd HH:mm:ss" type="time"></fmt:formatDate>
				<div style="border-bottom: 1px solid #999999;">
					<img id="head_portrait" height="50px" width="50px"
						src="${file_server}${theme.userHeadImg}"> <span
						class="title">${theme.blogUser}发布时间:<c:out value="${date}" /></span>
				</div>
			</div>
			<div style="border-bottom: 1px solid #999999;">
				<div style="margin-left: 10px" class="dxx">
					<img tid="${theme.id}" class="collect" style="float: right;" title="删除" alt="删除" width="20px" height="20px" src="${images}/mytheme/u316.png">
					<label class="theme-font">喜欢车型:${theme.carType}</label>
					<label class="theme-font">计划时间:<fmt:formatDate
							value="${theme.plannTime}" /></label> 
					<label class="theme-font">所在地区:${theme.area}</label>
					
				</div>
			</div>
			<c:forEach items="${comments}" var="c">
				<fmt:formatDate var="time" value="${theme.createTime}"
					pattern="yyyy-MM-dd HH:mm:ss" type="time"></fmt:formatDate>
				<div style="border: 1px solid gray; margin-top: 3px;font-size: 16px;">
					<div style="font-family: '宋体 Bold', '宋体';font-weight: 700;font-style: normal;"><img width="50px" height="50px" alt="" src="${file_server}${c.commentHeadImg}"> 
					 <span>${c.commentUser}</span> ${time}</div>
					 <div style="border: 1px solid ;margin-left:5px;margin-right:5px;margin-bottom:5px; word-wrap:break-word; background-color: #F2F2F2"><xmp>${c.commentConcent}</xmp></div>
				</div>
			</c:forEach>
			<div class="content" style="margin-top:3px; background-color: #F2F2F2;border: 1px solid gray; ">
				<div class="panel panel-info">
				   <div class="panel-body">
				    <textarea placeholder="请输入评论内容"   id="editorText" style="width: 60%; margin-left: 20%;height: 120px;resize:none;margin-top: 10px" ></textarea>
					<input style="background-color:#FEB252;border:0; margin-left: 60%;margin-bottom: 10px;margin-top: 10px" type="button" value="发表评论" id="sendComment" title="发表评论">
					</div>
				</div>
			
			</div>
		</div>

		<div class="span2 lrborder" style="margin-left: 0px;">
			<jsp:include page="userHeader.jsp" />
		</div>

		<div class="span1"></div>
	</div>
	<jsp:include page="footer.jsp" />
	<input type="hidden" id="blogId" value="${theme.id}"/>
</body>
</html>
