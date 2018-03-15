// MqChannelGrid
var MqChannelGrid = Ext.create('Ext.grid.Panel', {	
	//height : 550,
	region : 'center',
	title : '通道连接列表',
	frame : true,
	autoScroll : true, // 自动添加滚动条
	stripeRows : true, // 交替行效果
	// selModel : Ext.create('Ext.selection.CheckboxModel'),
	store : Ext.create('MqChannelBeanStore', {
		'id' : 'mqChannelBeanStoreId'
	}),
	columns : [ {
		id : 'qmgrName',
		name : 'qmgrName',
		text : '队列管理器名',
		dataIndex : 'qmgrName'
	}, {
		id : 'name',
		name : 'name',
		text : '通道名',
		dataIndex : 'name'
	}, {
		id : 'type',
		name : 'type',
		text : '通道类型',
		dataIndex : 'type'
	}, {
		id : 'state',
		name : 'state',
		text : '通道状态',
		dataIndex : 'state'
	}, {
		id : 'connectionName',
		name : 'connectionName',
		text : '连接名',
		dataIndex : 'connectionName'
	}, {
		id : 'startTime',
		name : 'startTime',
		text : '启动时间',
		dataIndex : 'startTime',
		renderer : function(value) {
			var date = new Date(value); 
			return Ext.Date.format(date, 'Y-m-d H:i:s');
		}
	}, {
		id : 'lastGetMsgTime',
		name : 'lastGetMsgTime',
		text : '最后获取消息时间',
		dataIndex : 'lastGetMsgTime',
		renderer : function(value) {
			var date = new Date(value); 
			return Ext.Date.format(date, 'Y-m-d H:i:s');
		}
	}, {
		id : 'msgs',
		name : 'msgs',
		text : '消息数',
		dataIndex : 'msgs'
	}, {
		id : 'sentBytes',
		name : 'sentBytes',
		text : '发送字节数',
		dataIndex : 'sentBytes'
	}, {
		id : 'receivedBytes',
		name : 'receivedBytes',
		text : '接收字节数',
		dataIndex : 'receivedBytes'
	}, {
		id : 'maxConversations',
		name : 'maxConversations',
		text : '最大对话数',
		dataIndex : 'maxConversations'
	}, {
		id : 'currentConversations',
		name : 'currentConversations',
		text : '当前对话数',
		dataIndex : 'currentConversations'
	} ],
	// 顶部工具栏
	tbar : [ {
		xtype : 'button',
		text : '刷新',
		handler : function() {
			Ext.data.StoreManager.lookup('mqChannelBeanStoreId').load();
		}
	} ]
});
// MqChannelTypeGrid
var MqChannelTypeGrid = Ext.create('Ext.grid.Panel', {
	width : '30%',
	region : 'west',
	// minwidth : window.screen.availWidth * 0.3,
	// height : window.screen.availWidth * 0.3,

	//width : 500,
	//height : 550,
	title : '通道类型列表',
	frame : true,
	autoScroll : true, // 自动添加滚动条
	stripeRows : true, // 交替行效果
	store : Ext.create('MqChannelTypeStore', {
		'id' : 'MqChannelTypeStoreId'
	}),
	columns : [ {
		id : 'tpQmgrName',
		name : 'tpQmgrName',
		text : '队列管理器名',
		dataIndex : 'qmgrName'
	}, {
		id : 'tpName',
		name : 'tpName',
		text : '通道名',
		dataIndex : 'name'
	}, {
		id : 'tpType',
		name : 'tpType',
		text : '通道类型',
		dataIndex : 'type'
	} ],
	// 顶部工具栏
	tbar : [ {
		xtype : 'button',
		text : '刷新',
		handler : function() {
			Ext.data.StoreManager.lookup('MqChannelTypeStoreId').load();
		}
	} ]
});