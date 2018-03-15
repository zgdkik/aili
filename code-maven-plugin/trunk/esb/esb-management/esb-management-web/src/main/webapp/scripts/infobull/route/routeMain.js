Ext.onReady(function() {
	// var RouteListPanel = Ext.create('Ext.Viewport', {
	Ext.create('Ext.Viewport', {
		frame : true,
		// width : window.screen.availWidth,
		// height : window.screen.availHeight,
		id : 'RouteList_mainPanel',
		renderTo : Ext.getBody(),
		title : '路由信息',
		layout : 'border',
		items : [ 
		{
			region : 'center',
			items : [ RouteListGrid ]
		}, ]
	});

});