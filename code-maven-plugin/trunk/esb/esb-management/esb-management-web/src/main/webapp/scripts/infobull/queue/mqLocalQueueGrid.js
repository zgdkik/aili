var queueTask = { // Ext的定时器，每隔300秒刷新store。
	run : function() {
		Ext.data.StoreManager.lookup('mqLocalQueueBeanStoreId').load();
	},
	interval : 300000
// 300 second
};

var queueRunner = new Ext.util.TaskRunner();

// MqLocalQueueGrid
var MqLocalQueueGrid = Ext.create('Ext.grid.Panel', {
	//width : 800,
	//height : 550,
	title : '自定义本地队列状态列表',
	frame : true,
	region : 'center',
	autoScroll : true, // 自动添加滚动条
	stripeRows : true, // 交替行效果
	// selModel : Ext.create('Ext.selection.CheckboxModel'),
	store : Ext.create('MqLocalQueueBeanStore', {
		'id' : 'mqLocalQueueBeanStoreId'
	}),
	columns : [ {
		id : 'lqQmgrName',
		name : 'lqQmgrName',
		text : '队列管理器名',
		dataIndex : 'qmgrName'
	}, {
		id : 'lqName',
		name : 'lqName',
		text : '队列名',
		width:200,
		dataIndex : 'name'
	}, {
		id : 'lqInputCount',
		name : 'lqInputCount',
		text : '输入计数',
		dataIndex : 'inputCount'
	}, {
		id : 'lqOutputCount',
		name : 'lqOutputCount',
		text : '输出计数',
		dataIndex : 'outputCount'
	}, {
		id : 'lqMaxDepth',
		name : 'lqMaxDepth',
		text : '最大深度',
		dataIndex : 'maxDepth'
	}, {
		id : 'lqCurrentDepth',
		name : 'lqCurrentDepth',
		text : '当前深度',
		dataIndex : 'currentDepth'
	}, {
		id : 'lqAllowPut',
		name : 'lqAllowPut',
		text : '允许放入',
		dataIndex : 'allowPut'
		
	}, {
		id : 'lqAllowGet',
		name : 'lqAllowGet',
		text : '允许取出',
		dataIndex : 'allowGet'
	}, {
		id : 'lqQueueCreateTime',
		name : 'lqQueueCreateTime',
		text : '队列创建时间',
		dataIndex : 'queueCreateTime',
		renderer : function(value) {
			var date = new Date(value); 
			return Ext.Date.format(date, 'Y-m-d H:i:s');
		}
	} ],
	// 顶部工具栏
	tbar : [ {
		xtype : 'button',
		text : '刷新',
		handler : function() {
			Ext.data.StoreManager.lookup('mqLocalQueueBeanStoreId').load();
			if( Ext.getCmp('queueAutoFlush').getValue()){
				queueRunner.start(queueTask);
			} else {
				queueRunner.stop(queueTask);
			}
		}
	},{
		xtype : 'checkboxfield',
		id : 'queueAutoFlush',
		boxLabel  : '自动刷新',
	} ]
});