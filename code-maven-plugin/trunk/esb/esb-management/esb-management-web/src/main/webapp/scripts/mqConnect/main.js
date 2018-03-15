Ext.onReady(function() {
	 Ext.create('Ext.Viewport', {
		frame : true,
		id : 'mqConnect_mainPanel',
		renderTo : Ext.getBody(),
		title : '队列连接管理',
		layout : 'border',
		items : [ {
			region : 'north',
			items : [ queryMqConnectForm ]
		}, {
			region : 'center',
			autoScroll : true,
			items : [mqConnectGrid]
		} ]
	});
});