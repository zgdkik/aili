Ext.onReady(function() {
	//实例store
	var viewStore = Ext.create('WsAddressStatusStore', {
		'id' : 'wsAddressStatusStoreId'
	});
	//实例grid
	var wsAddressStatusGrid= Ext.create('wsAddressStatusGrid',{
		store:viewStore,
		id:'wsAddressStatusGrid'
	});
	Ext.create('Ext.Viewport', {
				frame : true,
				// width : window.screen.availWidth,
				// height : window.screen.availHeight,
				id : 'verify_mainPanel',
				title : '资源连接验证',
				layout : 'border',
				items : [wsAddressStatusGrid]
			});
});