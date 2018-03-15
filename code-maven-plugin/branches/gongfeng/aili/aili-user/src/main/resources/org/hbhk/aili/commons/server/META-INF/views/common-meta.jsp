<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="/aili" prefix="aili" %>
<meta charset="utf-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" /> -->
<meta name="keywords" content="hbhk,hbhk-java,开发平台">
<meta name="description" content="">
<meta name="author" content="hbhk">
<link rel="shortcut icon"
	href="${base}/images/home/icon/fire1.png?${version}">
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<script type="text/javascript">
	var base = "${base}";
	var staticbase = "${staticbase}";
	try {
		var userContext = eval('(' + '${userContextJson}' + ')');
	} catch (e) {
	}
	var jsessionid = "${pageContext.session.id}";
</script>
