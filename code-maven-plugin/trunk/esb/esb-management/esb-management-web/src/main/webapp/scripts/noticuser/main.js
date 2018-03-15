Ext.onReady(function() {
			var NoticUserPanel = Ext.create('Ext.Viewport', {
						frame : true,
//						width : window.screen.availWidth,
//						height : window.screen.availHeight,
						id:'noticUser_mainPanel',
						renderTo : Ext.getBody(),
						title : '预警人员管理',
						layout : 'border',
						items:[{
							region : 'north',
							items : [queryNoticUserForm]
						},{//border布局一定要定义center
							region : 'center',
							autoScroll : true,
							items : [noticUserGrid]
						}]
					});
					
		})