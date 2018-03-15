Ext.onReady(function() {
	// var MqPanel = Ext.create('Ext.Viewport', {
	Ext.create('Ext.Viewport', {
		frame : true,
		// width : window.screen.availWidth,
		// height : window.screen.availHeight,
		id : 'MqLocalQueue_mainPanel',
		renderTo : Ext.getBody(),
		title : '自定义本地队列状态',
		layout : 'border',
		items : [ MqLocalQueueGrid ]
	});
});