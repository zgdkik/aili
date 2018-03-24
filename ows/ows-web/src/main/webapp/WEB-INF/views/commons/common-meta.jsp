<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="/ymdd" prefix="ymdd" %>
<meta charset="utf-8">
<META http-equiv="keywords" content="零担|快递|速递|速运|壹米滴答|壹米|滴答|YMDD|ymdd|零担快运|零担快递|大道物流"/> 
<META http-equiv="description" content="零担|快递|速递|速运|壹米滴答|壹米|滴答|YMDD|ymdd|零担快运|零担快递|大道物流"/>
<meta name="description" content="">
<meta name="author" content="RHB">
<link rel="shortcut icon"  href="${base}/images/fav.ico"/>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<script type="text/javascript">
	var base = "${base}";
	var title = "${title}";
	var staticbase = "${staticbase}";
	try {
		var userContext = eval('(' + '${userContextJson}' + ')');
	} catch (e) {
	}
	var jsessionid = "${pageContext.session.id}";
</script>
<title>大道物流</title>