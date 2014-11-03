<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}</title>
<link href="${scripts}/home_login_/styles.css" type="text/css"
	rel="stylesheet" />
<script src="${scripts}/home_login_/data.js"></script>
<script type="text/javascript">
	var pageNum = "${pageNum}";
</script>
<style type="text/css">
.lrborder {
	border-left: 1px solid #F2F2F2;
	border-right: 1px solid #F2F2F2;
	min-height: 1000px;
}

.daoyu {
	font-family: '宋体 Bold', '宋体';
	font-weight: 700;
	font-size: 36px;
	height: 90px;
	background-image: url('/images/user/home__nologin_/u12.png');
	color: #FFFFFF;
	text-align: left;
	height: 90px;
}

.theme {
	background-color: #F2F2F2;
	height: 150px;
	font-size: 20px;
}

.hhrx {
	width: 600px;
	border: 1px dashed;
}

.dxx {
	border-bottom: 1px dashed #000;
	width: 500px
}
.dxxh {
	border-bottom: 1px solid #FF6600;
	border-top-color: white;
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
			<div class="daoyu" style="">
				<p style="height: 10px"></p>
				<p>一起买车,与小伙伴分享互动!</p>
			</div>
			<div class="theme" style="">
				<div style="float: left; margin-left: 50px">
					<div style="margin-top: 10px">
						<span>选择车型:</span> <select id="u94_input"
							style="width: 200px; height: 30px">
							<c:forEach items="${carType}" var="c">
								<option value="${c.id}">${c.name}</option>
							</c:forEach>
						</select>
					</div>
					<div style="margin-top: 10px">
						<span>计划时间:</span> <input style="width: 188px; height: 30px"
							id="u186_input" type="text" value="">
					</div>
					<div style="margin-top: 10px">
						<span>所在地区:</span> <select id="u99_input"
							style="width: 200px; height: 30px">
							<c:forEach items="${ps}" var="p">
								<option value="${p.id}">${p.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div style="float: left;">
					<input class="fb" type="button"
						style="margin-top: 110px;margin-left: 50px;width: 150px;height: 30px;background-image: url('${images}/fb.png');">
				</div>
			</div>
			<c:forEach items="${bs}" var="b">
				<div   class="dxxh" style="margin-top: 5px; margin-left: 3px">
					<div style="float: left; margin-top: 3px;">
						<img width="50px" height="50px"
							src="${file_server}${b.userHeadImg}">
					</div>
					<div style="float: left; margin-left: 5x">
						<label style="margin-left: 10px"><a href="${base}user/${b.blogUrl}"
							style="text-decoration: none;">${b.blogUser}的主题</a></label>
						<div style="margin-left: 10px" class="dxx">
							<label class="theme-font">喜欢车型:${b.carType}</label><label
								class="theme-font">计划时间:<fmt:formatDate
									value="${b.plannTime}" /></label> <label class="theme-font">所在地区:${b.area}</label>
						</div>
						<div style="margin-left: 10px" class="dxx">
							<div>
								<span class="pl" style="height: 25px; width: 50px">关注(${b.blogCollect})</span>
								<img tuser="${b.blogUser}" class="care_user" style="margin-left: 5px" width="50px" height="25px"
									src="${images}/home_login_/gz.png">
							</div>
							<div style="margin-top: 5px">
								<c:forEach items="${b.careList}" var="user">
									<img width="30px" height="30px" src="${file_server}${user.userHeadImg}">
								</c:forEach>
							</div>
						</div>
						<div  style="margin-bottom: 5px;margin-left: 10px">
							<div>
								<span class="pl" style="height: 25px; width: 50px">评论(${b.blogReview})</span>
								<img class="user-commemt" burl="${base}user/${b.blogUrl}" style="margin-left: 5px" width="50px" height="25px"
									src="${images}/home_login_/pl.jpg">
							</div>
							<div class="pltext">
							  <c:forEach items="${b.commentList}" var="comm">
									<p>
										<span>${comm.commentUser}:</span>
									</p>
									<p>
										<span>${comm.commentConcent}</span>
									</p>
							  </c:forEach>
							</div>
						</div>
					</div>

				</div>
				<HR id="hx" style="margin; border;border-top ;border-bottom;" width="100%" color="#FF6600">
			</c:forEach>
		</div>

		<div class="span2 lrborder" style="margin-left: 0px;">
			<jsp:include page="userHeader.jsp" />
		</div>

		<div class="span1"></div>
	</div>
	<jsp:include page="footer.jsp" />
	<jsp:include page="ad.jsp" />
</body>
</html>
