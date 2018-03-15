<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}-用户私信</title>
<script src="${scripts}/friends.js"></script>
<style type="text/css">
.font-label {
	font-family: '宋体 Bold', '宋体';
	font-weight: 700;
	font-style: normal;
	font-size: 20px;
}
.msg-send{
	color: white;
	background-image: url("${images}/reg/u26.png");
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- 中间部分 -->
	<div class="row-fluid" style="min-height: 500px">
		<div class="span1" style="margin-left: 60px;"></div>

		<div class="span2 lrborder" style="margin-left: 0px;">
			<jsp:include page="msg.jsp" />
		</div>
		<div class="span6" style="margin-left: 0px;">
			<div class="center-body"
				style="width: 100%; float: left;">
				<div class="my-msg" style="width: 100%">
					<table class="table">
						<caption
							style="background-color: #B4CDE6; font-size: 20px; height: 30px; padding-top: 5px;">我的私信</caption>
						<thead>
							<tr>
								<th width="30%">发送人</th>
								<th width="60%">内容</th>
								<th width="10%">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${msgs}" var="msg">
								<tr>
									<th width="30%">${msg.sendUser}</th>
									<th width="60%">${msg.msg}</th>
									<th width="10%"><a msgid="${msg.id}" class="del-msg"
										href="javascript:void()" title="我的私信">删除</a></th>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="send-msg" style="display: none; width: 100%;margin-top: 10px;border: 1px solid #999999;float: left;">
					<div style="width: 100%; float: left; margin-top: 10px;">
						<label class="font-label"
							style="width: 70px; float: left; margin-left: 20%;font-size: 15px;">收信人:</label>
						<select class="receive-user" style="float: left;">
							<c:forEach items="${fs}" var="f">
								<c:if test="${f.nickName != null}">
									<option value="${f.mail}">${f.nickName}</option>
								</c:if>
								<c:if test="${f.nickName == null}">
									<option value="${f.mail}">${f.mail}</option>
								</c:if>

							</c:forEach>

						</select>
					</div>
					<div style="width: 100%; float: left;">
						<label class="font-label"
							style="width: 70px; float: left; margin-left: 20%;font-size: 15px;">内  容:</label>
						<textarea rows="" class="msg-content" cols=""
							style="width: 50%; float: left; resize: none; height: 150px;"></textarea>
					</div>
					<div style="width: 100%; float: left;">
						<input type="button" value="发送" class="msg-send font-label"
							style="margin-bottom: 10px; margin-left: 60%; background-color: #EF5C00; border: 0; margin-top: 5px; width: 50px; height: 30px">
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
</body>
</html>
