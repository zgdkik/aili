<%@ page language="java" pageEncoding="UTF-8" info="买客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="${base}favicon.png">
<link rel="Bookmark" href="${base}favicon.png">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="keywords" content="${siteInfo.keywords}">
<meta name="description" content="${siteInfo.description}">
<link href="${styles}/jquery-ui.css" type="text/css" rel="stylesheet" />
<%-- <link href="${styles}/jquery-ui-themes.css" type="text/css" rel="stylesheet" /> --%>
<link href="${styles}/axure_rp_page.css" type="text/css"
	rel="stylesheet" />
<link href="${styles}/chosen.css" type="text/css" rel="stylesheet" />
<link href="${scripts}/data/styles.css" type="text/css" rel="stylesheet" />
<%-- <script src="${scripts}/jquery-ui-1.8.10.custom.min.js"></script> --%>
<link href="${styles}/bootstrap-combined.min.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript">
	var host = window.location.host;
	var file_server = "${file_server}";
	//var $j = jQuery.noConflict();
	var base = "${base}";
	var returnUrl = '${returnUrl}';
	var UserContext = {
		'user' : '${cuser}',
		'name' : '${cuserName}',
		'head' : '${head}'
	};
</script>
<script src="${scripts}/jquery-1.9.1.js"></script>
<script src="${scripts}/bootstrap.min.js"></script>
<script src="${scripts}/jquery-ui.js"></script>
<script src="${scripts}/chosen.jquery.js"></script>
<script src="${scripts}/jquery-exp.js"></script>
<script src="${scripts}/common.js"></script>
<link href="${styles}/backTop.css" type="text/css" rel="stylesheet" />
<script src="${scripts}/backTop.js"></script>
</head>