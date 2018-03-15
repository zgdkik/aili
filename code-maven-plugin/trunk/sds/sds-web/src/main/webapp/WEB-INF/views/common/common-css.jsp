<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:if test="${env == 'dev'}">
 <%@include file="common-css2.jsp"%>
</c:if>
<c:if test="${env != 'dev'}">
	<link href="${base}/wro/base-css.css?${version}" rel="stylesheet" />
</c:if>

