<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!-- Left Sidebar Start -->
<%-- <div class="left side-menu">
	<div class="sidebar-inner slimscrollleft">
		<!-- Search form -->
		<form role="search" class="navbar-form">
			<div class="form-group" style="line-height: 60px;text-align: center;">
				<!-- <input type="text" placeholder="搜索" class="form-control">
				<button type="submit" class="btn search-button">
					<i class="fa fa-search"></i>
				</button> -->
				<a href="${base}"><img
					src="${base}/images/home/icon/logo.png?${version}" alt="Logo"></a>
			</div>
		</form>
		<div class="clearfix"></div>
		<!--- Organization Picker -->
		<!--- Divider -->
		<!-- <div class="clearfix"></div>
		<hr class="divider" />
		<div class="clearfix"></div> -->
		<!--- Divider -->
		<div id="sidebar-menu">
		  <ul>
		  <c:forEach items="${userMenuList}"  var="m">
            <li class='has_sub'><a href='javascript:void(0);'><i class='{{icon}}'></i><span>${m.name}</span> <span class="pull-right"><i class="fa fa-angle-down"></i></span></a>
                <ul>
                 <c:forEach items="${m.childList}"  var="sm">
                    <li><a href='${base}${sm.url}' mid='${sm.id}' onclick='leftMenuClick(this)'<c:if test="${url==sm.url}">class="active"</c:if> ><span>${sm.name}</span></a>
                    	 <ul>
			                 <c:forEach items="${sm.childList}"  var="csm">
			                    <li><a href='${base}${csm.url}' mid='${csm.id}' onclick='leftMenuClick(this)' <c:if test="${url==csm.url}">class="active"</c:if> ><span>${csm.name}</span></a>
			                    </li>
			                 </c:forEach>
		                </ul>
                    </li>
                 </c:forEach>
                </ul>
            </li>
          </c:forEach>
        </ul>
			
		</div>
		<div class="clearfix"></div>
		<br> <br> <br>
	</div>
</div> --%>
<!-- Left Sidebar End -->
