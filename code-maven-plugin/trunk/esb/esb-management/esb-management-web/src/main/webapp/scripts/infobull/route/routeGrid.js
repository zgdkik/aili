// RouteListGrid
var RouteListGrid = Ext.create('Ext.grid.Panel', {
	title : '通道类型列表',
	frame : true,
	autoScroll : true, // 自动添加滚动条
	stripeRows : true, // 交替行效果
	forceFit : true,
	autoWidth : true,
	store : Ext.create('RouteInfoStore', {
		'id' : 'routeInfoStoreId'
	}),
	columns : [ {
		id : 'resbServiceCode',
		name : 'resbServiceCode',
		text : 'ESB服务编码',
		autoSizeColumns:'true',
		dataIndex : 'esbServiceCode'
	}, {
		id : 'rtargetServiceCode',
		name : 'rtargetServiceCode',
		text : '目标服务编码',
		autoSizeColumns:'true',
		dataIndex : 'targetServiceCode'
	}],
	// 顶部工具栏
	tbar : [ {
		xtype : 'button',
		text : '刷新',
		handler : function() {
			Ext.data.StoreManager.lookup('routeInfoStoreId').load();
			
		}
	} ],
	// 分页工具栏
	bbar : Ext.create('Ext.PagingToolbar', {
				store : Ext.data.StoreManager.lookup('routeInfoStoreId'),
				pageSize : 20,
				displayInfo : true,
				displayMsg : '当前记录数为从 第{0}条 - 第{1}条 ，共 {2}条',
				emptyMsg : "无记录"
			})
});