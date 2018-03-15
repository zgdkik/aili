<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>日志平台</title>
<script src="scripts/jquery.js"></script>
<style>
html, body {
	height: 100%;
	width: 100%;
}
</style>
</head>
<body>
	<div id="log-header"
		style="height: 20px; background: #FFFFFF; padding: 5px;">
		<div>
			<button id="log1" onclick="taillog()"  value='disabled' >业务系统</button>
			<button id="log2"  onclick="openPage('log2','/usr/local/tomcat7/tomcat-1/logs/catalina.out')">平台系统</button>
			<button id="log3"   onclick="openPage('log3','/usr/local/tomcat7/tomcat-job/logs/catalina.out')">定时任务</button>
			<button id="view" onclick="taillog()">查看</button>
		</div>
	</div>
	<div id="log-container"
		style="height: 100%; overflow-y: scroll; background: #333; color: #ffffff; padding: 10px;">
		<div></div>
	</div>
</body>
<script>

    var f = true;
	function getParam(name){
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}
	function openPage(uid,file){
		var suid = getParam("uid");
		if(suid==uid){
			return;
		}
		//window.open ('/log/log.jsp?uid='+uid+"&file="+file,'newwindow','height=100%,width=100%,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')
		window.open ('/log/log.jsp?uid='+uid+"&file="+file);

	}
	function taillog() {
		//var websocket = new WebSocket('ws://10.10.0.13:8880/log/ws.do?uid='+log);
		if(!f){
			f =false;
			return;
		}
		var uid = getParam("uid");
		if(uid==null || uid ==""){
			uid = "log1";
		}
		var file = getParam("file");
		if(file==null || file ==""){
			file = "/usr/local/jboss-4.2.3.GA/server/app8180/log/server.log";
		}
		var websocket = new WebSocket('ws://10.10.0.13:8880/log/ws.do?uid='+uid+","+file);
		//var websocket = new WebSocket('ws://localhost:8081/log/ws.do?uid='+uid+","+file);
		websocket.onmessage=function(event){
			var data = event.data;
			//$("#log-container div").append(data+ "<p> </p>");
			$("#log-container div").append(data+"<br>");
			$("#log-container").scrollTop($("#log-container div").height()- $("#log-container").height());
		}
	};
	$(document).ready(function() {
		var uid = getParam("uid");
		if(uid==null || uid ==""){
			uid = "log1";
		}
		if(uid=="log2"){
			$("#view").html("查看平台系统日志");
			$("#"+uid).attr("disabled",false);
			$("#log3").attr("disabled",true);
		}
		if(uid=="log3"){
			$("#view").html("查看定时任务日志");
			$("#"+uid).attr("disabled",false);
			$("#log2").attr("disabled",true);
		}
	});
</script>
</body>
</html>