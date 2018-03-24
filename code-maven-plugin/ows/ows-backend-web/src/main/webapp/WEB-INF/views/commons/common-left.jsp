<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!-- left side start-->
<div class="left-side sticky-left-side">

	<!--logo and iconic logo start-->
	<div class="logo">
		<a href="${base}"><img src="${base}/images/home/icon/logo.png"
			alt=""></a>
	</div>

	<div class="logo-icon text-center">
		<a href="${base}"><img
			src="${base}/images/home/icon/logo_icon.png" alt=""></a>
	</div>
	<!--logo and iconic logo end-->

	<div class="left-side-inner">
		<!--sidebar nav start-->
		<ul class="nav nav-pills nav-stacked custom-nav">
			<c:set value="0" var="num"/>
			<c:forEach items="${userMenuList}" var="m">
				<li class="menu-list"><a href=""><i class="fa fa-laptop"></i>
						<span>${m.name}</span></a>
					<c:if test="${m.childList !=null}">
							<ul class="sub-menu-list">
							<c:forEach items="${m.childList}" var="sm">
								<li><a href="#" mid="${num}" url="${base}${sm.url}">${sm.name}</a></li>
								<c:set value="${num+1}" var="num"/>
								</c:forEach>
							</ul>
						
					</c:if>
				</li>
			</c:forEach>
		</ul>
		<!--sidebar nav end-->
	</div>
</div>
<!-- left side end-->