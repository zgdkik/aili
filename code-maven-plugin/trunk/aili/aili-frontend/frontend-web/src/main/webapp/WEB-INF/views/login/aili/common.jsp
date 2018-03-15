<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/hbhk" prefix="hbhk" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<spring:eval expression="@mainConfigService.findMainConfig().title" var="title"/>
	<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
	<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
	<link rel="shortcut icon" href="${images}/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${resources}/${theme}/styles/ext-fssc-min.css">
	<link rel="stylesheet" type="text/css" href="${resources}/${theme}/styles/fssc-all-min.css">
	<%-- <hbhk:resource module="common" resource="common.css"/> --%>
	<script type="text/javascript" src="${resources}/${theme}/scripts/bootstrap.js"></script>
	<script type="text/javascript" src="${resources}/${theme}/scripts/ext-lang-${lang}.js"></script>
	<script type="text/javascript" src="${resources}/${theme}/components/my97DatePicker/WdatePicker.js"></script>
	<%-- <hbhk:resource module="login" resource="common.js"/>
	<hbhk:resource module="common" resource="common.js"/> --%>
	<title>${title}</title>
</head>