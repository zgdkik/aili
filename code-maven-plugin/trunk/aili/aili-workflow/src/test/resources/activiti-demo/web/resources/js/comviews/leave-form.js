/**
 * @author yangjie
 * @date 2012-11-19
 */
var grid;
var params;
$(function() {
	// 获取URL中的参数
	params = getURLParameter();
	init();
});

function init() {
	grid = $("#maingrid").ligerGrid( {
		// 数据加载地址
		url : window.ctxPaths + "/activiti/process/listLeave.action",
		// 表头数据
		columns : [ {
			display : '请假ID',
			name : 'ID',
			hide : true,
			align : 'left',
			width : 220,
			minWidth : 30
		}, {
			display : '请假人',
			name : 'leavePerson',
			align : 'left',
			width : 130,
			minWidth : 30
		}, {
			display : '上级领导',
			name : 'superior',
			align : 'left',
			width : 150,
			minWidth : 30
		}, {
			display : '开始日期',
			name : 'startTime',
			align : 'left',
			width : 220,
			minWidth : 30
		}, {
			display : '结束时间',
			name : 'endTime',
			align : 'left',
			width : 220,
			minWidth : 30
		}, {
			display : '状态',
			name : 'domStatus',
			align : 'left',
			width : 130,
			minWidth : 30,
			render : function(rowdata, rowindex, value) {
				var html = "";
				if (rowdata.domStatus == "1") {
					html = "已保存";
				} else if (rowdata.domStatus == "2") {
					html = "审核中";
				} else if (rowdata.domStatus == "3") {
					html = "审批通过";
				} else if (rowdata.domStatus == "4") {
					html = "审批拒绝";
				}
				return html;
			}
		}, {
			display : '操作',
			name : 'opt',
			align : 'left',
			width : 200,
			minWidth : 30,
			render : function(rowdata, rowindex, value) {
				var html = "";
				if (rowdata.domStatus == "2" || rowdata.domStatus == "3") {
					html += '修改&nbsp;&nbsp;';
				} else {
					html += '<a href="javascript:optMgr(\'' + rowdata.ID + '\',\'' + 1 + '\');">修改</a>&nbsp;&nbsp;';
				}
				html += '<a href="javascript:optMgr(\'' + rowdata.ID + '\',\'' + 2 + '\');">查看详情</a>&nbsp;&nbsp;';
				// html += '<a href="javascript:optMgr(\'' + rowdata.ID +
				// '\',\'' + 3 + '\');">流程详情</a>&nbsp;&nbsp;';
				return html;
			}
		} ],
		pageSize : 30,
		width : getWidth(0.999),
		height : getHeight(0.85),
		enabledSort : false,
		toolbar : createToolbar(),
		parms : {
			"className" : params.className
		},
		root : 'rows', // 数据源字段名
		record : 'total' // 数据源记录数字段名
	});
}

/**
 * 打开模态窗口
 * 
 * @param ID
 * @param optStatus
 */
function optMgr(ID, optStatus) {
	if (ID != null) {
		window.open("leave-mgr.jsp?ID=" + ID + "&opt=" + optStatus, "_self");
		return;
	}
	window.open("leave-mgr.jsp?&opt=" + optStatus, "_self");
}

/**
 * 创建工具条
 * 
 * @returns Object
 */
function createToolbar() {
	var items = [];
	items.push( {
		text : "增加",
		click : function() {
			optMgr(null, 0);
		},
		img : window.ctxPaths + "/resources/ligerui/skins/icons/add.gif"
	});
	return {
		items : items
	}
}
