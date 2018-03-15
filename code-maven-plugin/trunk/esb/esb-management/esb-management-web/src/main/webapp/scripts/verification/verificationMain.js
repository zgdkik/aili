Ext.onReady(function() {
	Ext.create('Ext.Viewport', {
		frame : true,
		// width : window.screen.availWidth,
		// height : window.screen.availHeight,
		id : 'verify_mainPanel',
		renderTo : Ext.getBody(),
		title : '资源连接验证',
		layout : 'border',
		items : [ {
			region : 'center',
			items : [ {
				xtype : 'button',
				text : '验证MQ连接',
				width : 120,
				handler : function() {
					Ext.Ajax.request({
						url : 'verifyMq.action',
						params : {},
						success : function(response, action) {
							var result = Ext.decode(response.responseText);
							Ext.MessageBox.alert('结果反馈', result.msg);
						},
						failure : function(response, action) {
							var result = Ext.decode(response.responseText);
							Ext.MessageBox.alert('结果反馈', result.msg);
						},
						waitMsg : '验证中...'
					});
				}
			}, {
				xtype : 'button',
				text : '验证DB连接',
				width : 120,
				handler : function() {
					Ext.Ajax.request({
						url : 'verifyDb.action',
						params : {},
						success : function(response, action) {
							var result = Ext.decode(response.responseText);
							Ext.MessageBox.alert('结果反馈', result.msg);
						},
						failure : function(response, action) {
							var result = Ext.decode(response.responseText);
							Ext.MessageBox.alert('结果反馈', result.msg);
						},
						waitMsg : '验证中...'
					});
				}
			} ]
		} ]
	});
})