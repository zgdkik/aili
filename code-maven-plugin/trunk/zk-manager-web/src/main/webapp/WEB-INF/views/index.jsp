<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="common.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>hbhk zookeeper 管理</title>
<link rel="stylesheet" href="${base}/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css">
<script src="${base}/scripts/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<link rel="stylesheet" href="${base}/scripts/zTree_v3/css/demo.css">
<%-- <link rel="stylesheet" href="${base}/scripts/uploadify/uploadify.css">
<script src="${base}/scripts/uploadify/jquery.uploadify.min.js"></script> --%>
<link rel="stylesheet" href="${base}/scripts/ajaxFileUploader/ajaxfileupload.css">
<script src="${base}/scripts/ajaxFileUploader/ajaxfileupload.js"></script>
<script src="${base}/scripts/zk/index.js"></script>
<script type="text/javascript">
var json = '${ztrees}';
var treeNodes = eval('(' + json + ')');

</script>
<style type="text/css">

input {
	height: 30px;
	font-size: 20px;
}
</style>
</head>

<body>
<div class="content_wrap" style="margin-left: 10%;width: 1500px;">
	<div class="" style="background-color:#f0f6e4;;float: left;margin-top: 10px;width: 100%;min-height: 100px;">
		<div style="float: left;margin-top: 10px;width: 100%;">
			<div style="float: left;width: 100%;">
			 	<!-- <input style="float: right;" type="button" class="reload-users"  value="重新加载用户">  -->
			 	<a href="javascript:void(0)" style="float: right;margin-left: 10px;margin-right: 10px;" class="logout">退出</a>
			 	<a  style="float: right;margin-left: 10px;" href="javascript:void(0)" class="reload-users">重新加载用户</a>
			 	<!-- <input style="float: right;" type="button" class=logout  value="退出">  -->
			</div>
			
		</div>
		<div style="float: left;width: 100%;">
			<h1 style="margin-top: 10px;margin-bottom: 20px;font-size: 40px;font-weight: 500px;">zookeeper 管理</h1>
		</div>
	</div>
	<div class="" style="background-color:#f0f6e4;;float: left;margin-top: 10px;width: 100%;min-height: 100px;">
		<div style="float: left;margin-top: 10px;width: 100%;">
			<div style="float: left;margin-top: 10px; margin-left: 20%;">
			<label>zk地址</label>
			<input type="text" value="${zkHost}" class="zk-host">
			</div>
			<div style="float: left;margin-top: 10px;margin-left: 10px;">
			<label>根节点</label>
			<input type="text" value="${zkRoot}" class="zk-root">
			</div>
		</div>
		<div style="float: left;margin-top: 10px;width: 100%;margin-bottom: 20px;">
			<div style="float: left;margin-top: 10px; margin-left:60%;width: 100%;">
			<input style="" type="button" class="set-zkhost" value="更换">
			</div>
			
		</div>
	</div>
	<div class="zTreeDemoBackground left" style="width: 375">
		${msg}
		<ul id="treeDemo" class="ztree" style="width: 100%;height: 500px"></ul>
	</div>
	<div class="" style="background-color:#f0f6e4;;height: 513px;float: left;margin-top: 10px;width: 1102px;margin-left: 23px;">
		<div class="" style="float: left;margin-top: 10px;width: 100%;">
			<div style="float: left;margin-top: 10px;width: 100%;font-size: 24px;">
				<div style="float: left;margin-top: 10px; margin-left: 10%;width: 100%;">
				<label>目录</label>
				<input type="text" zkid="" class="zk-path" style="width: 800px;height: 30px;">
				</div>
				<div style="float: left;margin-top: 10px;margin-left: 10%;width: 100%;line-height: 100px;">
				<label>数据</label>
				<textarea cols="10" rows="10" class="zk-data"  style="width: 500px;height: 80px;"></textarea>
				</div>
			</div>
			<div style="float: left;margin-top: 10px;width: 100%;">
				<div style="float: left;margin-top: 10px; margin-left: 30%;"><input type="button" class="update-zk" value="修改数据"></div>
				<div style="float: left;margin-top: 10px; margin-left: 10px;"><input type="button" value="创建目录" class="create-path"></div>
				<div style="float: left;margin-top: 10px; margin-left: 10px;"><input type="button" value="删除目录" class="del-path"></div>
			</div>
			<div style="float: left;margin-top: 10px;width: 100%;">
			<div style="float: left;margin-top: 10px; margin-left: 30%;"><input type="file" name="Filedata" id="uploadify" /></div>
				<div style="float: left;margin-top: 10px; margin-left: 10px;">
				<input type="button" value="导入"  style="height: 30px;" class="import-data">
				<input type="button" value="导出"  style="height: 30px;" class="export-data">
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
