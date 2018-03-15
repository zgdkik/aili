Ext.onReady(function() {
			var backServicePanel = Ext.create('Ext.Viewport', {
						frame : true,
						width : window.screen.availWidth,
						height : window.screen.availHeight,
						renderTo : Ext.getBody(),
						layout : 'border',
						items : [querySvcpointForm,
								Ext.create('backServiceGrid', {
											id : 'backServiceGrid',
											region : 'center'
										})]
					});

		})