
var task = { // Ext的定时器，每隔300秒刷新store。
	run : function() {
		Ext.data.StoreManager.lookup('mqChannelBeanStoreId2').load();
	},
	interval : 300000
// 300 second
};
var runner = new Ext.util.TaskRunner();

// MqChannelGrid
var MqChannelGrid2 = Ext.create('Ext.grid.Panel', {
	width : 500,
	height : 550,
	title : '通道连接列表',
	frame : true,
	autoScroll : true, // 自动添加滚动条
	stripeRows : true, // 交替行效果
	// selModel : Ext.create('Ext.selection.CheckboxModel'),
	store : Ext.create('MqChannelBeanStore2', {
		'id' : 'mqChannelBeanStoreId2'
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
	} ],
	// 顶部工具栏
	tbar : [ {
		xtype : 'button',
		text : '刷新',
		handler : function() {
			Ext.data.StoreManager.lookup('mqChannelBeanStoreId2').load();
			if( Ext.getCmp('autoFlush').getValue()){
				runner.start(task);
			} else {
				runner.stop(task);
			}
		}
	},{
		xtype : 'checkboxfield',
		id : 'autoFlush',
		boxLabel  : '自动刷新',
	} ],
});