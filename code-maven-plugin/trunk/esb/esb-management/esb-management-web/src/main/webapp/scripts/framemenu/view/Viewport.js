//定义顶部 
var topPanel = Ext.create('Ext.panel.Panel',{
	margin : '0,5,0,5',
	region : 'north',
	height : 58,
	border:false,
	html : '<iframe src ="common/topRegion.action" width="100%" frameborder="no" border="0" framespacing="0" align="left"></iframe>'

});


//定义工作区 
var tabPanel = Ext.create('Ext.tab.Panel', {
	alias: 'widget.menutab',
	id: 'menutab',
    style : {
    	'background-color' : 'rgb(223, 223, 246)'
    },
    border : false,
	region : 'center',
	items : [{
		iconCls : 'home',
		cls : "main",
		title : '首页',
		html : "<h1>欢迎进入ESB服务平台</h1>"
	}]
});

//定义底部Panel
var bottomPanel = Ext.create('Ext.panel.Panel',{
	region : "south", // 南方布局
	height : 28,// 高度
	border:false,
	html : '<iframe src ="common/bottomRegion.action" width="100%" frameborder="no" border="0" framespacing="0" align="left"></iframe>'
});

// 菜单区
var accordion = Ext.create('Ext.tree.Panel', {
	alias: 'widget.menutree',
	id: 'menutree',
	region : 'west',
	title : '系统功能',
	iconCls : "sysmanagemenu",
	collapsible : true,
	split : true,
	width : 200,
	rootVisible : false,
	useArrows: true,
	store: 'FrameMenus'
});

Ext.define('esbApp.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: 'border',
    border: 'false',
    initComponent: function() {
    	this.items =[topPanel, accordion, tabPanel, bottomPanel];
        this.callParent();
    }
});



