<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}-我的关注</title>
<link href="${scripts}/mytheme/styles.css" type="text/css"
	rel="stylesheet" />
<link href="${scripts}/home_login_/styles.css" type="text/css"
	rel="stylesheet" />
<script src="${scripts}/myjoin/data.js"></script>
<style type="text/css">
.lrborder {
	border-left: 1px solid #F2F2F2;
	border-right: 1px solid #F2F2F2;
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
		<div class="span6 lrborder" style="margin-left: 0px;">
			<c:forEach items="${bs}" var="b">
				<div class="dxxh"  style="margin-top: 5px; float: left;margin-left: 3px;width: 100%">
					<div style="float: left; margin-top: 3px;">
						<img width="50px" height="50px"
							src="${file_server}${b.userHeadImg}">
					</div>
					<div style="float: left; margin-left: 5x">
						<img tid="${b.blogUser}" class="blog_del" style="float: right;" title="删除" alt="删除" width="20px" height="20px" src="/images/user/mytheme/image_u318.png">
						<label style="margin-left: 10px"><a class="ttitle" href="${base}user/${b.blogUrl}"
							style="text-decoration: none;">${b.blogUser}的主题</a></label>
						<div style="margin-left: 10px" class="dxx">
							<label class="theme-font ttype">喜欢车型:${b.carType}</label><label
								class="theme-font ttime"  >计划时间:<fmt:formatDate
									value="${b.plannTime}" /></label> 
								<label class="theme-font  tarea">所在地区:${b.area}</label>
						</div>
						<div style="margin-left: 10px" class="dxx">
							<div>
								<span class="pl gz" style="height: 25px; width: 50px">关注(${b.careCount})</span>
								<img tuser="${b.blogUser}" class="care_user" style="margin-left: 5px" width="50px" height="25px"
									src="${images}/home_login_/gz.png">
							</div>
							<div style="margin-top: 5px">
								<c:forEach items="${b.careList}" var="user">
									<img width="30px" class="gz-h" height="30px" src="${file_server}${user.userHeadImg}">
								</c:forEach>
							</div>
						</div>
						<div  style="margin-bottom: 5px;margin-left: 10px">
							<div>
								<span class="pl pl-user" style="height: 25px; width: 50px">评论(${b.blogReview})</span>
								<img class="user-commemt" burl="${base}user/${b.blogUrl}" style="margin-left: 5px" width="50px" height="25px"
									src="${images}/home_login_/pl.jpg">
							</div>
							<div class="pltext">
							  <c:forEach items="${b.commentList}" var="comm">
									<p>
										<span class="comm-user" >${comm.commentUser}用户说:</span>
									</p>
									<p>
										<span class="comm-text" >${comm.commentConcent}</span>
									</p>
							  </c:forEach>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<div class="span2 lrborder" style="margin-left: 0px;">
		<jsp:include page="userHeader.jsp" />
		</div>

		<div class="span1"></div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
