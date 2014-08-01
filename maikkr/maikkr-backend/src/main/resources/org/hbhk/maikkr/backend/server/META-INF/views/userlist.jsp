<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="/theme/common.htm" />
<link href="${styles}/userlist.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${scripts}/userlist.js"></script>
<body>
	<div align="center" style="height: auto; width: 1000px;">
		<div align="center" style="height: auto; width: auto;border: 2px solid #F8B74B;margin-top: 15px;">
			<table  id="querycondition" style="margin-top: 15px;margin-bottom: 15px;">
				<tr >
					<td><label>名称</label><input type="text"></td>
					<td><label>内容</label><input type="text"></td>
					<td><label>用户</label><input type="text"></td>
				</tr>
				<tr><td></td><td></td><td><input style="float: right;" type="button" value="查询"></td></tr>
			</table>
		</div>
		<div align="center" style="height: auto; width: 1000px;">
			<table id="list"></table>
			<div id="gridPager"></div>
		</div>
	</div>
</body>
</html>