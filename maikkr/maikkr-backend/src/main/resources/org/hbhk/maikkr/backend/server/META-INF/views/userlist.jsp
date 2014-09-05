<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="/theme/common.htm" />
<link href="${styles}/userlist.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${scripts}/userlist.js"></script>
<body>
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 class="panel-title">用户列表</h3>
		</div>
		<div class="panel-body">
			<div class="panel panel-default">
				<div class="panel-body">
					<table id="querycondition"
						style="margin-top: 15px; margin-bottom: 15px;height:100% ; width:100%;">
						<tr style="float: left;">
							<td><label>名称</label><input type="text"></td>
							<td><label>内容</label><input type="text"></td>
							<td><label>用户</label><input type="text"></td>
						</tr>
						<tr>
							<td colspan="2">
							<input style="float: right;" type="button" value="查询">
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="panel panel-default">
				<div  class="panel-body tbl-list">
					<div align="center" style="height: auto;">
						<table id="list"></table>
						<div id="gridPager"></div>
					</div>
				</div>
			</div>
		</div>
		<!-- <div class="panel-footer"></div> -->
	</div>
</body>
</html>