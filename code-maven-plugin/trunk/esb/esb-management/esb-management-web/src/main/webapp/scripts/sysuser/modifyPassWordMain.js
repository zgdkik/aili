Ext.onReady(function() {
			var NoticUserPanel = Ext.create('Ext.Viewport', {
						frame : true,
						// width : window.screen.availWidth,
						// height : window.screen.availHeight,
						id : 'modifyPassWord_mainPanel',
						renderTo : Ext.getBody(),
						title : '系统用户密码修改',
						layout : 'border',
						items : [{
									region : 'center',
									items : [modifyPassWordForm]
								}]
					});
			
		})