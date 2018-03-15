Ext.onReady(function() {
			var backServicePanel = Ext.create('Ext.Viewport', {
						frame : true,
						width : window.screen.availWidth,
						height : window.screen.availHeight,
						renderTo : Ext.getBody(),
						title : '后端服务管理1',
						layout : 'border',
						items : [querySvcpointForm, frontServiceGrid]
					});

		})