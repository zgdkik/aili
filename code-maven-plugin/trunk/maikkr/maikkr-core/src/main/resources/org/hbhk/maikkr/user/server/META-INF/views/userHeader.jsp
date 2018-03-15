<%@ page language="java" pageEncoding="UTF-8" info="买客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.menu {
	font-size: 14px;
	color: #000000;
	margin-right: 25px;
	list-style-type: none;
}

.user-header {
	border: 1px solid #999999;
}
</style>
<c:if test="${userInfo != null}">
	<div class="user-header">

		<div style="margin-left: 25%;">
			<img width="100px" height="100px" alt=""
				src="${file_server}${userInfo.userHeadImg}">
		</div>
		<div style="margin-left: 45%;">
			<span>${cuser}</span>
		</div>

	</div>
</c:if>