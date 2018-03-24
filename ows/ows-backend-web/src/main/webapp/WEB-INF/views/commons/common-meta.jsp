<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="/ymdd" prefix="ymdd" %>
<meta charset="utf-8">
<meta name="keywords" content="壹米滴答,壹米滴答供应链,供应链">
<meta name="description" content="">
<meta name="author" content="hbhk">
<link rel="shortcut icon"
	href="${base}/images/home/icon/favicon.ico?${version}">
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
