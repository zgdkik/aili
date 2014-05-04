<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<%@include file="common.jsp"%>
<script type="text/javascript">
	$(function() {
		$(".datepicker").datepicker();
	})
</script>

</head>
<body>

 <div style="width: 100;" align="right"><a href="${appcontext}/framework/logout">退出</a></div>
	<input type="text" class="datepicker" />
	<ol id="selectable">
		<li class="ui-widget-content">Item 1</li>
		<li class="ui-widget-content">Item 2</li>
		<li class="ui-widget-content">Item 3</li>
		<li class="ui-widget-content">Item 4</li>
		<li class="ui-widget-content">Item 5</li>
		<li class="ui-widget-content">Item 6</li>
		<li class="ui-widget-content">Item 7</li>
	</ol>
	
</body>
</html>
