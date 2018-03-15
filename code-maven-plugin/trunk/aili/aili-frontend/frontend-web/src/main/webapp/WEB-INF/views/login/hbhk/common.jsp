<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/hbhk" prefix="hbhk" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:eval expression="@mainConfigService.findMainConfig().title" var="title"/>
<spring:eval expression="@mainConfigService.findMainConfig().theme" var="theme"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
	<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
	<link rel="shortcut icon" href="${base}/images/login/logo.png"/>
	<link rel="stylesheet" type="text/css" href="${resources}//styles/ext-${theme}-min.css">
	<link rel="stylesheet" type="text/css" href="${base}/styles/login/common.css">
	<script type="text/javascript">
		var base="${base}";
		var resourcesPath="${resources}";
	</script>
	<script type="text/javascript" src="${resources}/scripts/bootstrap.js"></script>
	<script type="text/javascript" src="${resources}/scripts/ext-lang-${lang}.js"></script>
	<script type="text/javascript" src="${resources}/components/my97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${base}/scripts/login/commSelectors.js"></script>
	<script type="text/javascript" src="${base}/scripts/login/dictionary.js"></script>
	<script type="text/javascript" src="${base}/scripts/login/frontendContext.js"></script>
	<script type="text/javascript" src="${base}/scripts/login/common.js"></script>
	<script type="text/javascript" src="${base}/scripts/common/common.js"></script>
	<title>${title}</title>
</head>