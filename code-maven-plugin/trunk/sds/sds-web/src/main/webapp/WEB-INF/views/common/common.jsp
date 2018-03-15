<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="/sds" prefix="sds" %>
<c:if test="${base==null}">
	<c:set var="base" scope="application" value="/"></c:set>
</c:if>
<c:set var="staticbase" scope="request" value="${base}/resources/static"></c:set>
<c:set var="version" scope="request">version=${version}</c:set>
