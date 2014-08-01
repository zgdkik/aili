<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="/theme/common.htm" />
<link href="${styles}/mainnew.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${scripts}/bloglist.js"></script>
<body>
	<div align="center">
		<div>
			<table>
				<tr>
					<td><label>名称</label><input type="text"></td>
					<td><label>内容</label><input type="text"></td>
					<td><label>用户</label><input type="text"></td>
				</tr>
				<tr><td></td><td></td><td><input type="button" value="查询"></td></tr>
			</table>
		</div>
		<div>
			<table id="list"></table>
			<div id="gridPager"></div>
		</div>
	</div>
</body>
</html>