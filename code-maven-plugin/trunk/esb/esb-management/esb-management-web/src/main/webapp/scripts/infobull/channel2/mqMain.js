Ext.onReady(function() {
	// var MqPanel = Ext.create('Ext.Viewport', {
	Ext.create('Ext.Viewport', {
		frame : true,
		// width : window.screen.availWidth,
		// height : window.screen.availHeight,
		id : 'MqChannel_mainPanel2',
		renderTo : Ext.getBody(),
		title : '通道状态',
		layout : 'border',
		items : [ {
			region : 'center',
			items : [ MqChannelGrid2 ]
		}, ]
	});

});