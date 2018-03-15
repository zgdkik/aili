Ext.onReady(function() {
			var NoticUserPanel = Ext.create('Ext.Viewport', {
						frame : true,
//						width : window.screen.availWidth,
//						height : window.screen.availHeight,
						id:'sysUser_mainPanel',
						renderTo : Ext.getBody(),
						title : '系统用户管理',
						layout : 'border',
						items:[{
							region : 'north',
							items : [querySysUserForm]
						},sysUserGrid]
					});
					
		})