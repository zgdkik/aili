<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="/aili" prefix="aili" %>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="走路记">
<meta name="description" content="走路记">
<meta name="author" content="hbhk">
<link rel="shortcut icon" href="${base}/assets/images/favicon.png?${version}">
<script type="text/javascript">
	var base = "${base}";
	try {
		var userContext = eval('(' + '${userContextJson}' + ')');
	} catch (e) {
	}
	var jsessionid = "${pageContext.session.id}";
</script>
