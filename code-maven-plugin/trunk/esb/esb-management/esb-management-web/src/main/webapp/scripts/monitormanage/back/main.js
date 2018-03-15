Ext.onReady(function() {
			var backServicePanel = Ext.create('Ext.Viewport', {
						frame : true,
						renderTo : Ext.getBody(),
						layout : 'fit',
						items : {
							xype : 'panel',
							layout : 'border',
							items : [querySvcpointForm,
									Ext.create('backServiceGrid', {
												id : 'backServiceGrid',
												region : 'center'
											})]
						}
					});

		})