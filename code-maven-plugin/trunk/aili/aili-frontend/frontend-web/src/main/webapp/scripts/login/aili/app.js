var winWidth = 0;
var winHeight = 0;
var baseImagePath = '../images/login/';
var imagePath = baseImagePath + 'fssc/';
var loginurl =  base+'/user/login';
var logouturl =  base+'/user/logout';
//未处理
var noDealTotal =0;

function findDimensions()// 函数：获取尺寸
{
    window.onresize = null;
    // 获取窗口宽度
    if (window.innerWidth)
        winWidth = window.innerWidth;
    else if ((document.body) && (document.body.clientWidth))
        winWidth = document.body.clientWidth;
    // 获取窗口高度
    if (window.innerHeight)
        winHeight = window.innerHeight;
    else if ((document.body) && (document.body.clientHeight))
        winHeight = document.body.clientHeight;

    // 通过深入Document内部对body进行检测，获取窗口大小
    if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
        winHeight = document.documentElement.clientHeight;
        winWidth = document.documentElement.clientWidth;
    }
};

findDimensions();

var topHeight = 71;
var footerHeight = 25;
var menuWidth = 230;
var topLeftPanelWidth = 306;
var centerHeight = winHeight - (topHeight + footerHeight);

/*-----------------------------页面的头部中的工具栏--------------------------------*/
Ext.define('Dpap.main.TopButton',{
	extend: 'Ext.Button',
	height:68,
	icon : baseImagePath + 'b1.png',
	iconAlign : 'top',
	autoScroll: false
});

//xtype-主页按钮
Ext.define('Dpap.main.HomeLink', {
	extend: 'Dpap.main.TopButton',
	alias: 'widget.mhomeLink',
	id:'M_home',
	icon : baseImagePath + 'home.png',
	text: login.i18n('dpap.login.homeLink'), 
	handler: function(arg){
		var tabPanel = Ext.getCmp('mainAreaPanel');
		scroll(0,0);
		var TabID='#T_';
		for (var j=2; j<arg['id'].length; j++){
			TabID=TabID+arg['id'][j];
		}
		var newActiveTab=tabPanel.child(TabID);
		tabPanel.setActiveTab(newActiveTab);
	}
});

//xtype-常用功能按钮
Ext.define('Dpap.main.NavConfigLink', {
	extend: 'Dpap.main.TopButton',
	alias: 'widget.mnavConfigLink',
	//id:'M_navConfig',
	icon : baseImagePath + 'b4.png',
	text: login.i18n('dpap.login.NavConfigWindowTitle'), 
	navConfigWindow : null,
	getNavConfigWindow : function(){
		var me = this;
		if(me.navConfigWindow == null){
			me.navConfigWindow = Ext.create('Dpap.main.NavConfigWindow');
		}
		
		return me.navConfigWindow;
	},
	handler: function(){
		scroll(0,0);
		var me = this;
		var tree = me.getNavConfigWindow().getResourceTreePanel(),
			treeStore = tree.getStore();
		treeStore.load({ 
		    node: treeStore.getRootNode()
		});
		me.getNavConfigWindow().getUserMenuGridPanel().getStore().load(function(records, operation, success) {
			if(success == true){
				me.getNavConfigWindow().show();				
			}
		});
	},
	autoScroll: false	
});

//xtype-用户信息按钮
Ext.define('Dpap.main.UserInfoSpace', {
	extend: 'Dpap.main.TopButton',
	alias: 'widget.muserinfospace',
	icon : baseImagePath + 'user.png',
	//id: 'userInfoSpace',
	text: DpapUserContext.getCurrentUserEmp().empName,
	//textAlign:'left',
	currentUserInfoWindow:null,
	getcurrentUserInfoWindow: function() {
		var me = this;
		if(me.currentUserInfoWindow == null){
			me.currentUserInfoWindow = Ext.create('Dpap.main.CurrentUserInfoWindow');
		}
		
		return me.currentUserInfoWindow;
	},
	handler: function(){
		scroll(0,0);
		var me = this;
		var	form = me.getcurrentUserInfoWindow().getCurrentUserInfoForm().getForm(),
			emp = DpapUserContext.getCurrentUserEmp(),
			dept = DpapUserContext.getCurrentUserDept();
		form.reset();
		form.setValues(emp);
		form.findField('deptName').setValue(dept.deptName);
		if(emp.entryDate!=null){
			form.findField('inDate').setValue(new Date(emp.entryDate));			
		}
		me.getcurrentUserInfoWindow().show();		    	
	}
});

//生成当前日期
function constructDateTime(today){
	var dd = today.getDate(),
		mm = today.getMonth()+1, //January is 0!
		yyyy = today.getFullYear(),
		hh = today.getHours(),
		minutes = today.getMinutes(),
		ss=today.getSeconds();
	if(dd<10){dd='0'+dd;} 
	if(mm<10){mm='0'+mm;} 
	if(hh<10){hh='0'+hh;}
	if(minutes<10){minutes='0'+minutes;}
	if(ss<10){ss='0'+ss;}
	today = yyyy+'-'+mm+'-'+dd+' '+hh+':'+minutes+':'+ss;
	return today;
}

//xtype-当前时间
Ext.define('Dpap.main.DateTimeSpace', {
	extend: 'Dpap.main.TopButton',
	alias: 'widget.mdatetimespace',
	id: 'dateTimeSpace',
	padding: '29 5 5 5',
	width: 140,
	icon: '',
	text: constructDateTime(login.currentServerTime),
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.callParent([ cfg ]);
	}
});

//xtype-登出按钮
Ext.define('Dpap.main.Logout', {
	extend: 'Dpap.main.TopButton',
	alias: 'widget.mlogout',
	//id:'M_logout',
	text:'注销',
	icon : baseImagePath + 'b6.png',
	autoScroll: false,
	handler: function(){
		Ext.MessageBox.confirm(login.i18n('dpap.login.logout'), login.i18n('dpap.login.sureLogout'),function(btn){
			if(btn=='yes'){
				Ext.Ajax.request({
					url : '../user/logout',
					success : function(response, opts) {
						window.location = '../user/login';
					},
					exception : function(response, opts) {
						var result = Ext.decode(response.responseText);
						Ext.MessageBox.show({
							buttons: Ext.MessageBox.OK,
			                msg: result.message,
			                title: login.i18n('dpap.login.messageTitle'),
			                icon: Ext.MessageBox.ERROR
			            });
					}
				});
			}
		});
	}
});

Ext.define('Fssc.main.TopButton', {
	extend : 'Ext.button.Button',
	height : 68,
	icon : baseImagePath+'b1.png',
	iconAlign : 'top',
	functionCode : null,
	setFunctionCode : function(functionCode) {
		this.functionCode = functionCode;
	},
	setFunctionName : function(functionName) {
		this.text = functionName;
	},
	handler : function() {
		var me = this;
			mainNav = Ext.getCmp('mainNav');
		mainNav.setRootNode({
			id : me.functionCode,
			text : me.text,
			expand : false
		});
		mainNav.expandAll();
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.callParent([ cfg ]);
	}
});

Ext.define('Dpap.main.Toptoolbar', {
	extend: 'Ext.container.Container',
	id:"frameTopToolbarPanel",
	bodyBorder:false,
	layout:'hbox',
	defaultItems: function() {
		return [
		        { xtype:'mannounceLink' },
		        { xtype:'mnavConfigLink' },
				{ xtype:'mlogout' }
		];
	},
	/**
	 * 请求菜单信息，把toolBar上面添加按钮，然后根据toolbar上的按钮做leftpanel的变动
	 * 
	 * @param mainNav
	 *            mainmenu菜单
	 * @param topPanel
	 */
	requestFuntionInfo: function() {
		var me = this;
		Ext.Ajax.request({
			url : '../login/queryFunctionsByParentCode.action',
			params : {
				'node' : '0'
			},
			// 退出成功
			success : function(response, opts) {
				var json = Ext.decode(response.responseText),
					funs = json.functions,
					total = funs.length,
					buttonWidth = (winWidth - 250 - topLeftPanelWidth - 10) / total;
				if (buttonWidth > 120) {
					buttonWidth = 120;
				}
				var itemArray = [];
				try {
					itemArray = getFrameToolbarItems();
				} catch (exception) {
					itemArray = me.defaultItems();
				}
				var len = itemArray.length;
				var index = 2;
				for ( var i = 0; i < len; i++) {
					if(itemArray[i]=='muserinfospace'){
						index = i;
						break;
					}
				}
				for ( var i = 1; i < total; i++) {
					var functionCode = funs[i].functionCode;
					var functionName = funs[i].functionName;
					var functionIcon = baseImagePath+"b1.png";
					var functionButton = Ext.create('Fssc.main.TopButton');
					functionButton.setFunctionCode(functionCode);
					functionButton.setFunctionName(functionName);
					functionButton.setIcon(functionIcon);
					functionButton.setWidth(buttonWidth);			
					me.insert(index+i-1,functionButton);
				}
			}
		});
		me.doLayout();
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.items = [{xtype: 'tbfill'}];
		var itemArray = [];
		try {
			itemArray = getFrameToolbarItems();
		} catch (exception) {
			itemArray = me.defaultItems();
		}
		// 动态加载
		me.listeners = { 
			"afterrender": function(thiscomponent, eOpts){
				/** 请求菜单信息* */
				me.requestFuntionInfo();
				thiscomponent.add(itemArray);
			}
		};
		me.callParent([cfg]);
	}
});

/*--------------------------------页面的头部---------------------------------------*/

/** 头panel 信息 * */
Ext.define('Fssc.main.topPanel', {
	extend : 'Ext.panel.Panel',
	id : "topPanel",
	height : 71,
	layout : 'hbox',
	cls : 'topPanel',
	topLeftPanel : null,
	getTopLeftPanel : function() {
		if (this.topLeftPanel == null) {
			this.topLeftPanel = Ext.create('Ext.panel.Panel',{
				bodyBorder : false,
				width : topLeftPanelWidth,
				html : '<a href="#" class="logo"></a>'
			});
		}
		return this.topLeftPanel;
	},
	topRightPanel : null,
	getTopRightPanel : function() {
		if (this.topRightPanel == null) {
			this.topRightPanel = Ext.create('Dpap.main.Toptoolbar',{
				flex: 1
			});
		}
		return this.topRightPanel;
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.items = [ me.getTopLeftPanel(), me.getTopRightPanel() ];
		me.callParent([ cfg ]);
	}
});

/** 获取当前用户信息* */
viewCurrentUserInfo = function(startMsg, endMsg, viewPanel) {
	var htmlView = '';
	if (startMsg != null && startMsg != '') {
		htmlView += startMsg;
	}
	htmlView += '当前用户:'+'<a href="#" style="color:rgb(213,213,213);text-decoration:none" onclick=openUserDeptInfo("userInfo")>'
			+ login.currentUser.empEntity.empName
			+'</a>';
	htmlView += ' 工号:'
			+ login.currentUser.empEntity.empCode;
	htmlView += ' 部门:'+'<a href="#" style="color:rgb(213,213,213);text-decoration:none;" onclick=openUserDeptInfo("deptInfo")>'
			+ login.currentDept.deptName
	        +'</a>';
	if (endMsg != null && endMsg != '') {
		htmlView += endMsg;
	}
	viewPanel.update(htmlView);
};

//打开用户信息窗口
function  openUserDeptInfo(flag){
	//用户信息
	 var currentUserInfoWindow = Ext.getCmp('Dpap_main_CurrentUserInfoWindow_id');
	 if(currentUserInfoWindow==null){
		 currentUserInfoWindow = Ext.create('Dpap.main.CurrentUserInfoWindow');
	 }
	 //部门信息
     var currentDeptWindow = Ext.getCmp('Dpap_main_CurrentDeptWindow_id');
	 if(currentDeptWindow==null){
		 currentDeptWindow = Ext.create('Dpap.main.CurrentDeptWindow');
	 }
	 var emp = DpapUserContext.getCurrentUserEmp(),
	     dept = DpapUserContext.getCurrentUserDept();
	 if(flag=="userInfo"){
		 var form = currentUserInfoWindow.getCurrentUserInfoForm().getForm();
		 form.reset();
		 form.setValues(emp);
		 form.findField('deptName').setValue(dept.deptName);
		 if(emp.entryDate!=null){
			form.findField('inDate').setValue(new Date(emp.entryDate));			
		 }
		 currentUserInfoWindow.show()
	 }
	 if(flag=="deptInfo"){
		 var form = currentDeptWindow.getUserDeptForm().getForm();
	     form.reset();
	  	 form.setValues(dept);
		 currentDeptWindow.show()
	 }
	
}

/** 当前用户信息 * */
Ext.define('Fssc.main.bottomLeftPanel', {
	extend : 'Ext.panel.Panel',
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		viewCurrentUserInfo('', '', me);
		me.callParent([ cfg ]);
	}
});

/** 公司情况 * */
Ext.define('Fssc.main.bottomRightPanel',{
	extend : 'Ext.panel.Panel',
	html : '<div align=\'right\'><span>Copyright @ 2015 HBHK科技有限公司. All rights reserved.沪ICP备10005645</span></div>'
});

/** 页脚 部分* */
Ext.define('Fssc.main.footerPanel', {
	extend : 'Ext.panel.Panel',
	cls : 'footerPanel',
	id: 'footerPanel',
	height: footerHeight,
	width: winWidth,
	layout: 'column',
	defaults : {
		columnWidth : 0.5
	},
	bottomLeftPanel : null,
	getBottomLeftPanel : function() {
		if (this.bottomLeftPanel == null) {
			this.bottomLeftPanel = Ext.create('Fssc.main.bottomLeftPanel');
		}
		return this.bottomLeftPanel;
	},
	bottomRightPanel : null,
	getBottomRightPanel : function() {
		if (this.bottomRightPanel == null) {
			this.bottomRightPanel = Ext.create('Fssc.main.bottomRightPanel');
		}
		return this.bottomRightPanel;
	},
	
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.items = [ me.getBottomLeftPanel(), me.getBottomRightPanel() ];
		me.callParent([ cfg ]);
	}
});

/*----------------------------------主导航-----------------------------------------*/
// 定义一个功能菜单store
Ext.define('Fssc.main.NavStore', {
	extend : 'Ext.data.TreeStore',
	rootId : '01001',
	setRootId : function(id) {
		this.rootId = id;
	},
	rootText : '通用管理',
	setRootText : function(text) {
		this.rootText = text;
	},
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : '../login/loadTree.action',
		reader : {
			type : 'json',
			root : 'functions'
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.root = {
			id : me.rootId,
			text : me.rootText,
			expanded : true
		};
		me.callParent([ cfg ]);
	}
});

/** 菜单导航 * */
Ext.define('Fssc.main.menuPanel',{
	extend : 'Ext.tree.Panel',
	//title : '菜单导航',
	id: 'mainNav',
	cls : 'menu',
	width : menuWidth,
	height : centerHeight,
	bodyCls : 'menu_container',
	rootVisible : false,
	useArrows : true,
	autoScroll : false,
	collapsible : true,
	collapseMode : 'header',
	collapseDirection : 'left',
	border : false,
	// 主导航展开菜单的handler,与主导航本身的handler类似。
	onMClick : function(arg) {
		// console.log('BBBBBBBB'+arg['id']);
		var TabID = 'T_';
		var tLoc = '';
		for ( var j = 2; j < arg['id'].length; j++) {
			TabID = TabID + arg['id'].charAt(j);
			if (arg['id'].charAt(j) == '-') {
				tLoc = tLoc + '-';
			} else {
				tLoc = tLoc + arg['id'].charAt(j);
			}
		}
		var Panel = Ext.getCmp('mainAreaPanel');
		var fTab = Panel.child('#' + TabID);
		if (fTab == null) {
			this.addTab(Panel, TabID, arg['text'], tLoc);
		} else {
			Panel.show(fTab);
			Panel.setActiveTab(fTab);
		}
	},
	openMenuTabPanel : function(view, node, c, o, e) {
		view.toggleOnDblClick = false;
		var tabPanel = Ext.getCmp('mainAreaPanel');
		if(node.isLeaf()) {	
			//生成tab相关数据：
			var href=node.raw.uri,
				tID='T_',//tab ID
				tLoc='..'+href,
				hrefLength=href.length;
			for (var h=1; h<hrefLength; h++){
				if(href.charAt(h)!='/'){
					tID=tID+href.charAt(h);
				}else{
					tID=tID+'-';
				}
			}
			tID = tID.substring(0, tID.lastIndexOf('.'));
			//console.log(tID);
			//确认该tab是否已经打开：
			var fTab=tabPanel.child('#'+tID);
			//var tabContent=null;
			//如果未打开则添加：
			if(fTab==null){
				addTab(tID, node.data.text, tLoc);
			}else{
				tabPanel.show(fTab);
				tabPanel.setActiveTab(fTab);
			}
		}else{
			view.toggle(node);
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Fssc.main.NavStore');
		me.listeners = {
			// 点击主菜单节点：
			itemclick : me.openMenuTabPanel
		};
		me.callParent([ cfg ]);
	}
});
//弹出tab新窗口
function openMsgMenu(id,title,url,winFormType){
	login.msg.msgRemindWindow.hide();
	addTab(id,title,url);
}

/** 欢迎信息 * */
Ext.define('Fssc.welcome.BaseInfoPanel', {
	extend : 'Ext.panel.Panel',
	cls : 'welcome_user',
	id:'welcome_user_id',
	margin : '0 0 10 0',
   	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Fssc.main.NavStore');
		me.html='<h2>你好，欢迎使用'+document.title+'</h2>';
		me.callParent([ cfg ]);
	}
});

//快捷功能打开
function openTabPanel(tit, action) {
	var tabPanel = Ext.getCmp('mainAreaPanel');
	//生成tab相关数据：
	var href=action,
		tID='T_',//tab ID
		tLoc='..'+href,
		hrefLength=href.length;
	for (var h=1; h<hrefLength; h++){
		if(href.charAt(h)!='/'){
			tID=tID+href.charAt(h);
		}else{
			tID=tID+'-';
		}
	}
	tID = tID.substring(0, tID.lastIndexOf('.'));
	//console.log(tID);
	//确认该tab是否已经打开：
	var fTab=tabPanel.child('#'+tID);
	//var tabContent=null;
	//如果未打开则添加：
	if(fTab==null){
		addTab(tID, tit, tLoc);
	}else{
		tabPanel.show(fTab);
		tabPanel.setActiveTab(fTab);
	}
}

/** 快捷功能* */
Ext.define('Fssc.welcome.fastFuntionPanel',{
	extend : 'Ext.panel.Panel',
	title : '快捷功能',
	id: 'Fssc_welcome_fastFuntionPanel_id',
	cls: 'welcome_button autoHeight',
	autoScroll:true,
	height:500,
	refreshPanle:function(){
		var htmlView='<div class="img_list"><ul>';
		Ext.Ajax.request({
			async: false, 
			url : '../login/currentUserMeun.action',
			//保存成功
			success : function(response, opts) {
				var result = Ext.decode(response.responseText);
				var functions = result.functions;
				if(functions!=null){
				for(var i =0 ;i<functions.length;i++){
					var functionName=functions[i].functionName;
					//style="width:140px;float:left"
					var uri=functions[i].uri;
    					htmlView = htmlView
    					+'<li><a href="#" onclick=openTabPanel("'+functionName+'","'+uri+'")><img src="'+imagePath+'fun_img/sh1.png" />'
    					+functionName+'</a></li>';
				}
				}
			},
			//保存失败
			exception : function(response, opts) {
				var result = Ext.decode(response.responseText);
				Ext.MessageBox.show({
	                title: login.i18n('dpap.login.messageTitle'),
	                msg: result.message,
	                buttons: Ext.MessageBox.OK,
	                icon: Ext.MessageBox.ERROR
	            });
			}
		});
		htmlView=htmlView+'</ul></div>';
		return htmlView;
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		var htmlView = me.refreshPanle();
		me.update(htmlView);
		me.callParent([ cfg ]);
	}
});

/** 欢迎页 * */
Ext.define('Fssc.main.WelcomePanel',{
	extend : 'Ext.panel.Panel',
	id : 'T_home',
	title : '欢迎页',
	cls : 'welcomepanel',
	border : false,
	closable : false,
	tabConfig : {
		width : 150
	},
	baseInfo : null,
	getBaseInfo : function() {
		if (this.baseInfo == null) {
			this.baseInfo = Ext.create('Fssc.welcome.BaseInfoPanel', {
				height : 100,
			});
		}
		return this.baseInfo;
	},
	fastFunction : null,
	getFastFunction : function() {
		if (this.fastFunction == null) {
			this.fastFunction = Ext.create(
					'Fssc.welcome.fastFuntionPanel');
		}
		return this.fastFunction;
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		//通知公告,me.getWelNotice() 
		me.items = [ me.getBaseInfo(), me.getFastFunction()];
		me.callParent([ cfg ]);
	}
});

/** tab panel显示信息 ** */
Ext.define('Fssc.main.centerPanel', {
	extend : 'Ext.tab.Panel',
	id : "mainAreaPanel",
	cls : 'nav_tab',
	plain: true,
	autoScroll: true,
    defaults :{
        autoScroll: true
    },
	// 右手overflow下拉插件
	plugins : [ {
		ptype : 'tabscrollermenu',
		id : 'OFB_menu',
		maxText : 40,
		pageSize : 100
	}, {
		ptype : 'tabclosemenu',
		closeTabText : '关闭',
		closeOthersTabsText : '关闭其他',
		closeAllTabsText : '关闭所有'
	} ],
	beforeRemoveRubbish : function(panel, tab) {
		// 清理组件
		var tabId = tab.id, conId = tabId + '_content', toolbarId = tabId
				+ '_toolbar';
		// 判断是否存在tab的主页面，如果不存在就无需清理
		if (Ext.getCmp(conId) == null) {
			return;
		}
		// 清空ComponentManager内的注册项
		var cmpArray = Ext.getCmp(conId).removeAll();
		if (Ext.getCmp(toolbarId) != null) {
			var cmpArrayToolbar = Ext.EventManager.removeAll(toolbarId);
			cmpArray = Ext.Array.merge(cmpArray, cmpArrayToolbar);
		}
		// 清空store
		for ( var i = 0; i < cmpArray.length; i++) {
			if (cmpArray[i].store) {
				cmpArray[i].store.destroyStore();
				Ext.data.StoreManager.unregister(cmpArray[i].store);
			}
		}
		cmpArray = null;
		Ext.ComponentManager.unregister(Ext.getCmp(conId));
		if (Ext.getCmp(toolbarId) != null) {
			Ext.ComponentManager.unregister(Ext.getCmp(toolbarId));
		}

		// 清理模块下变量和方法
		var lineIndex = tabId.lastIndexOf("-"), moduleName = tabId.substring(2,
				lineIndex), endIndex = tabId.length, pointIndex = tabId
				.lastIndexOf("."), module = eval(moduleName);
		if (pointIndex != -1) {
			endIndex = pointIndex;
		}
		var childmoduleName = conId.substring(lineIndex + 1, endIndex);
		if (childmoduleName == "index") {
			if (module != null) {
				for ( var prop in module) {
					if (!Ext.isObject(prop)) {
						try {
							if (module[prop].id) {
								cleanComponent(module[prop].id);
							}
						} catch (err) {
							module[prop] = null;
						}
					}
				}
				module = null;
			}
		} else {
			var childmodule = module[childmoduleName.slice(0, -5)];
			for ( var prop in childmodule) {
				if (!Ext.isObject(prop)) {
					try {
						if (childmodule[prop].id) {
							cleanComponent(childmodule[prop].id);
						}
					} catch (err) {
						childmodule[prop] = null;
					}
				}
			}
			childmodule = null;
		}

	},
	beforetabchangeRst : function(panel, tab) {
	},
	welcomePanel : null,
	getWelcomePanel : function() {
		if (this.welcomePanel == null) {
			this.welcomePanel = Ext.create('Fssc.main.WelcomePanel');
		}
		return this.welcomePanel;
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.listeners = {
			// 关闭前清内存里相应项目
			beforeremove : me.beforeRemoveRubbish,
			// 切换及激活标签页前进行的调整：
			beforetabchange : me.beforetabchangeRst
		};
		me.items = [ me.getWelcomePanel() ];
		me.callParent([ cfg ]);
	}
});

/**
 * resource的Model
 */
Ext.define('Dpap.main.ResourceModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'functionCode'
	}, {
		name : 'functionName'
	}, {
		name : 'uri'
	}, {
		name : 'functionLevel'
	}, {
		name : 'parentCode'
	}, {
		name : 'active'
	}, {
		name : 'displayOrder'
	}, {
		name : 'checkable'
	}, {
		name : 'functionType'
	}, {
		name : 'leaf'
	}, {
		name : 'iconCls'
	}, {
		name : 'cls'
	}, {
		name : 'functionDesc'
	}, {
		name : 'functionSeq'
	}, {
		name : 'systemType'
	}, {
		name : 'createUser'
	}, {
		name : 'createDate',
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'number'
	}, {
		name : 'modifyUser'
	}, {
		name : 'modifyDate',
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'number'
	} ]
});
//常用功能Store
Ext.define('Dpap.main.currentDeptStore', {
	extend:'Ext.data.Store',
	model: 'Dpap.main.ResourceModel',
  	proxy: {
         type: 'ajax',
         actionMethods : 'POST',
         url: '../login/currentUserMeun.action',
         reader: {
             type: 'json',
             root: 'functions'
         }
     }
});

//常用功能设置窗口
Ext.define('Dpap.main.NavConfigWindow', {
	extend: 'Ext.window.Window',
	title: login.i18n('dpap.login.NavConfigWindowTitle'),
	height: 600,
	width: 700,
	modal:true,
	closeAction: 'hide',
	layout: {
		type:'column'
	},
	//菜单树
	resourceTreePanel : null,
	getResourceTreePanel : function(){
		if(this.resourceTreePanel==null){
		    this.resourceTreePanel = Ext.create('Ext.tree.Panel', {
		    	frame: true,
		    	title: login.i18n('dpap.login.app.treeMenu'),
		    	width: 250,
		    	height: 470,
		    	animate: true,
		    	useArrows: true,
		    	autoScroll: true,
		        store: Ext.create('Ext.data.TreeStore', {
					root: {
						id: '0',
						text: document.title
					},
					proxy : {
						type : 'ajax',
						actionMethods : 'POST',
						url : '../login/loadMenuTree.action',
						reader : {
							type : 'json',
							root : 'menuNodes'
						}
					}
			    }),
		        viewConfig: {
		            plugins: {
		            	ddGroup: 'userMenuGrid',
		                ptype: 'treeviewdragdrop',
		                dragText: login.i18n('dpap.login.app.userMenuGrid'),
		                containerScroll: true
		            }
		        }
		    });
		}
		return this.resourceTreePanel;
	},
	//用户常用菜单列表
	userMenuGridPanel : null,
	getUserMenuGridPanel : function(){
		if(this.userMenuGridPanel==null){
			this.userMenuGridPanel = Ext.create('Ext.grid.Panel', {
				frame: true,
		    	title: login.i18n('dpap.login.app.favoriteMenu'),
		    	columnWidth : 1,
		    	height: 470,
		    	autoScroll: true,
		    	viewConfig: {
		            plugins: {
		            	ddGroup: 'resourceTree',
		                ptype: 'gridviewdragdrop',
		                dragText: login.i18n('dpap.login.app.resourceTree'),
		                containerScroll: true
		            }
		        },
			    store: Ext.create('Dpap.main.currentDeptStore'),
			    columns: [
			        { header: login.i18n('dpap.login.resCode'), dataIndex: 'functionCode' },
			        { header: login.i18n('dpap.login.resName'),  dataIndex: 'functionName', flex: 1 }
			    ]
			});
		}
		return this.userMenuGridPanel;
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.items = [me.getResourceTreePanel(),me.getUserMenuGridPanel()];
		me.buttons = [{
	    	text: login.i18n('dpap.login.save'),
	    	handler : function(){
	    		var submitData = new Array(); 
	    		me.getUserMenuGridPanel().getStore().each(function(record,index,length){
	    			submitData.push(record.get('functionCode'));
	    		});
	    		//Ajax请求保存用户常用菜单设置
	    		Ext.Ajax.request({
	    			url : '../login/saveUserMenus.action',
	    			jsonData: {'userFuns': submitData},
	    			//保存成功
	    			success : function(response, opts) {
	    				var result = Ext.decode(response.responseText);
	    				Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'), result.message, 'ok', 1000);
	    				//刷新 快捷栏
	    	    		var fastFuntionPanel = Ext.getCmp('Fssc_welcome_fastFuntionPanel_id');
	    	    		if(fastFuntionPanel == null){
	    	    			fastFuntionPanel = Ext.create('Fssc.welcome.fastFuntionPanel');
	    	    		}
	    	    		var htmlview = fastFuntionPanel.refreshPanle();
	    	    		fastFuntionPanel.update(htmlview);
	    				me.close();
	    			},
	    			//保存失败
	    			exception : function(response, opts) {
	    				var result = Ext.decode(response.responseText);
	    				Ext.MessageBox.show({
	    	                title: login.i18n('dpap.login.messageTitle'),
	    	                msg: result.message,
	    	                buttons: Ext.MessageBox.OK,
	    	                icon: Ext.MessageBox.ERROR
	    	            });
	    			}
	    		});
			}
	    }],
		me.listeners = {
			//恢复overflow:auto
			/*beforeclose:function(){
				document.documentElement.style.overflow='auto';
				document.body.style.overflow='auto';
			},*/
			boxready: function(){
				var userMenuGridPanel = me.getUserMenuGridPanel(),
					resourceTreePanel = me.getResourceTreePanel(),
		    		userMenuGridPanelDropTargetEl = userMenuGridPanel.body.dom,
		    		resourceTreePanelDropTargetEl = resourceTreePanel.body.dom,
		    		expend = function(parentNode,selectedRecord,parentCode,userMenuGridPanel){
						parentNode.expand(false,function(){
			            	var length = parentNode.childNodes.length,
			            	displayOrder = selectedRecord.get('displayOrder')==0?0:selectedRecord.get('displayOrder')-1,
			            	index = length;
			            	if(parentNode.data.leaf){
			            		parentNode.data.leaf = false;
			            	}
			            	var node = {
			            			'id': selectedRecord.get('functionCode'),
			            			'text': selectedRecord.get('functionName'),
			            			'leaf': true,
			            			'parentId': parentCode,
			            			'entity': selectedRecord.data
			            	};
			            	if(length>displayOrder){
			            		index = displayOrder;
			            	}
			            	parentNode.insertChild(index, node );
			            	parentNode.getChildAt(index).raw = node;
			            	userMenuGridPanel.getStore().remove(selectedRecord);			            	
			            });
					};
			    Ext.create('Ext.dd.DropTarget', userMenuGridPanelDropTargetEl, {
			        ddGroup: 'userMenuGrid',
			        notifyEnter: function(ddSource, e, data) {
			        	userMenuGridPanel.body.stopAnimation();
			        	userMenuGridPanel.body.highlight();
			        },
			        notifyDrop  : function(ddSource, e, data){
			            var node = ddSource.dragData.records[0],
			            	parentNode = node.parentNode,
			            	record;
			            if(!node.isLeaf()){
			            	Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'),login.i18n('dpap.login.nonLeafNodesOfTheNode'), 'error', 1000);
			            	return false;
			            }
			            record = Ext.ModelManager.create(node.raw.entity, 'Dpap.main.ResourceModel');
			            userMenuGridPanel.getStore().insert(0,record);
			            parentNode.removeChild(node);
			            return true;
			        }
			    });
			    Ext.create('Ext.dd.DropTarget', resourceTreePanelDropTargetEl, {
			        ddGroup: 'resourceTree',
			        notifyEnter: function(ddSource, e, data) {
			        	userMenuGridPanel.body.stopAnimation();
			        	userMenuGridPanel.body.highlight();
			        },
			        notifyDrop  : function(ddSource, e, data){
			            var selectedRecord = ddSource.dragData.records[0],
			            	treeStore = resourceTreePanel.getStore(),
			            	expendPath = selectedRecord.get('functionSeq');
			            	parentCode = selectedRecord.get('parentCode').functionCode;
			            var parentNode = treeStore.getNodeById(parentCode);
			            //如果异步树节点未加载完成，那么就要请求后台，把树展开到这个节点下
			            if(typeof(parentNode) == "undefined" || parentNode == null){
        					resourceTreePanel.expandPath(expendPath,'id','/',function(success, lastNode){
        						if(lastNode.get('id')==parentCode){
        							expend(lastNode,selectedRecord,parentCode,userMenuGridPanel);        							
        						}
        					});
			            }else{
			            	expend(parentNode,selectedRecord,parentCode,userMenuGridPanel);
			            }
			            return true;
			        }
			    });
			}
		},
		me.callParent([cfg]);
	}
});

//当前用户信息的窗口
Ext.define('Dpap.main.CurrentUserInfoWindow', {
	extend: 'Ext.window.Window',
	id:'Dpap_main_CurrentUserInfoWindow_id',
	title: login.i18n('dpap.login.CurrentUserInfoWindowTitle'),
	height: 350,
	width: 600,
	modal:true,
	closeAction: 'hide',
	currentUserInfoForm: null,
	getCurrentUserInfoForm: function(){
		var me = this;
		if(me.currentUserInfoForm==null){
			me.currentUserInfoForm = Ext.create('Dpap.main.CurrentUserInfoForm');
		}
		return me.currentUserInfoForm;
	},
	updatePasswordButton : null,
	getUpdatePasswordButton:function(){
		var me = this;
		if(me.updatePasswordButton==null){
			me.updatePasswordButton = Ext.create('Ext.button.Button',{
				cls:'yellow_button',
				text: login.i18n('dpap.login.updatePassword'),
				handler: function(){
					var form = me.getCurrentUserInfoForm().getForm(),
						password = form.findField('password').getValue(),
						checkPassword = form.findField('checkPassword').getValue(),
						newPassword = form.findField('newPassword').getValue(),
						currentUser = DpapUserContext.getCurrentUser();
					//验证密码是否为空
					if(me.getCurrentUserInfoForm().getForm().isValid()){
						//两次输入的密码不一样
						if(newPassword!=checkPassword){
							Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'),login.i18n('dpap.login.twoPasswordNotSame'), 'error', 1000);
							return;
						}
						//Ajax验证登录密码是否正确
						Ext.Ajax.request({
							url: '../login/validatePassword.action',
							method: 'POST',
							params : {
								'newPassword' : password
							},
							success : function(response, opts) {
								//Ajax请求修改当前用户密码
								Ext.Ajax.request({
									url: '../login/updateCurrentUserPassword.action',
									method: 'POST',
									params : {
										'newPassword' : newPassword
									},
									success : function(response, opts) {
										var result = Ext.decode(response.responseText);
										//设置当前登录用户的密码信息
										login.currentUser.password = result.newPassword;
										Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'),login.i18n('dpap.login.updatePasswordSuc'), 1000);
										me.hide();
									},
									exception: function(response, opts){
										Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'),login.i18n('dpap.login.passwordError'), 'error', 1000);
									}
								});	
							},
							exception: function(response, opts){
								var result = Ext.decode(response.responseText);
								Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'),result.message, 'error', 1000);
							}
						});	
					}else{
						Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'),login.i18n('dpap.login.mustInput'), 'error', 1000);
						return;
					}
				}
			});
		}
		return me.updatePasswordButton;
	},
	closeButton : null,
	getCloseButton:function(){
		var me = this;
		if(me.cancelButton==null){
			me.cancelButton = Ext.create('Ext.button.Button',{
				text: login.i18n('dpap.login.closeTabText'),
				handler: function(){
					me.hide();
				}
			});
		}
		return me.cancelButton;
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.items = [me.getCurrentUserInfoForm()];
		me.buttons = [me.getCloseButton(),'->',me.getUpdatePasswordButton()];
		me.callParent([cfg]);
	}
});
//当前用户信息表单
Ext.define('Dpap.main.CurrentUserInfoForm',{
	extend: 'Ext.form.Panel',
	frame: true,
	defaultType : 'textfield',
	layout:'column',
	defaults: {
		readOnly : true,
		margin:'5 5 5 5',
		columnWidth:.5,
		labelWidth: 80
	},
	initComponent: function(config){
		var me = this,
				cfg = Ext.apply({}, config);
		me.items = [{
			name: 'empCode',
		    fieldLabel: login.i18n('dpap.login.jobNumber')
		},{
			name: 'empName',
		    fieldLabel: login.i18n('dpap.login.employeeName')
		},{
			name: 'deptName',
		    fieldLabel: login.i18n('dpap.login.orgName')
		},{
			name: 'position',
		    fieldLabel: login.i18n('dpap.login.position')
		},{
			name: 'offerTel',
		    fieldLabel: login.i18n('dpap.login.phoneCode')
		},{
			xtype: 'datefield',
			format: 'Y-m-d',
			name: 'inDate',
		    fieldLabel: login.i18n('dpap.login.entryDate')
		},{
			name: 'mobileNumber',
		    fieldLabel: login.i18n('dpap.login.customer.phoneNo')
		},{
			name: 'personEmail',
		    fieldLabel: login.i18n('dpap.login.electronicMailBox')
		},{
			name: 'password',
			inputType: 'password',
			readOnly: false,
			allowBlank: false,
		    fieldLabel: login.i18n('dpap.login.loginPassword')
		},{},{
			name: 'newPassword',
			allowBlank: false,
			inputType: 'password',
			readOnly : false,
		    fieldLabel: login.i18n('dpap.login.newPassword')
		},{
			name: 'checkPassword',
			allowBlank: false,
			inputType: 'password',
			readOnly : false,
		    fieldLabel: login.i18n('dpap.login.checkPassword')
		}];
	  	me.callParent([cfg]);
	}
});
//当前用户部门信息
Ext.define('Dpap.main.CurrentUserDeptForm',{
	extend: 'Ext.form.Panel',
	frame: true,
	defaultType : 'textfield',
	layout:'column',
	defaults: {
		readOnly : true,
		margin:'5 5 5 5',
		columnWidth:.5,
		labelWidth: 80
	},
	initComponent: function(config){
		var me = this,
				cfg = Ext.apply({}, config);
		me.items = [{
			name : 'deptCode',
			fieldLabel : login.i18n('dpap.login.deptCode')
		},{
			name : 'deptName',
			fieldLabel : login.i18n('dpap.login.deptName')
		},{
			name : 'deptTelephone',
			fieldLabel : login.i18n('dpap.login.phone')
		},{
			name : 'deptFax',
			fieldLabel : login.i18n('dpap.login.fax')
		},{
			name: 'subsidiaryCode',
			fieldLabel : login.i18n('dpap.login.companyName')
		},{
			name : 'zipCode',
			fieldLabel : login.i18n('dpap.login.zipCode')
		},{
			name : 'address',
			fieldLabel : login.i18n('dpap.login.address'),
			xtype : 'textfield',
			columnWidth: 1
		},{
			name : 'deptDesc',
			fieldLabel : login.i18n('dpap.login.deptDesc'),
			xtype : 'textarea',
			columnWidth : 1
		} ];
	  	me.callParent([cfg]);
	}
});
//部门窗口
Ext.define('Dpap.main.CurrentDeptWindow', {
	extend: 'Ext.window.Window',
	id:'Dpap_main_CurrentDeptWindow_id',
	title: login.i18n('dpap.login.CurrentDeptWindowTitle'),
	height: 350,
	width: 600,
	modal:true,
	closeAction: 'hide',
	userDeptForm: null,
	getUserDeptForm: function(){
		if(this.userDeptForm==null){
			this.userDeptForm = Ext.create('Dpap.main.CurrentUserDeptForm');
		}
		return this.userDeptForm;
	},
	closeButton : null,
	getCloseButton:function(){
		var me = this;
		if(me.cancelButton==null){
			me.cancelButton = Ext.create('Ext.button.Button',{
				text: login.i18n('dpap.login.closeTabText'),
				handler: function(){
					me.hide();
				}
			});
		}
		return me.cancelButton;
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.items = [me.getUserDeptForm()];
		me.buttons = [me.getCloseButton()];
		me.callParent([cfg]);
	}
});
//退出系统
Ext.define('Fssc.main.exitButton', {
	extend : 'Ext.button.Button',
	height : 68,
	width : 102,
	icon : imagePath+'menu_img/b6.png',
	iconAlign : 'top',
	text : '系统退出',
	handler : function() {
		Ext.MessageBox.confirm('提示', '是否确定退出!', function(btn) {
			console.log(btn);
			if (btn == 'yes') {
				// Ajax请求logout
				Ext.Ajax.request({
					url : logouturl,
					// 退出成功
					success : function(response, opts) {
						window.location = loginurl;
					},
					// 退出失败
					exception : function(response, opts) {
						var result = Ext.decode(response.responseText);
						Ext.MessageBox.show({
							title : '提示',
							msg : result.message,
							buttons : Ext.MessageBox.OK,
							icon : Ext.MessageBox.ERROR
						});
					}
				});
			}
		});
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.callParent([ cfg ]);
	}
});
/**以下是消息定时提醒功能 **/

//xtype-通知按钮
Ext.define('Dpap.main.AnnounceLink', {
	extend: 'Dpap.main.TopButton',
	alias: 'widget.mannounceLink',
	icon : baseImagePath + 'b3.png',
	//更新弹出框数据
	updateMsgData: function(){
		var text='';
		Ext.Ajax.request({
			url : '../message/queryMsgTotal.action', 
			async: false,
			success : function(response) {
				var result = Ext.decode(response.responseText);
				
				var normalMsgList = result.msgVo.normalMsgList;
				var msgVo=result.msgVo;
				var netMsgList = msgVo.netMsgList;
				var toDoMsgList = msgVo.toDoMsgList;
				noDealTotal=msgVo.noDealtotal;
				var showMessagePanel = login.msg.msgRemindWindow.getShowMessagesPanel();
				
				showMessagePanel.getToDoMsgGrid().store.loadData(toDoMsgList);
				showMessagePanel.getNormalMsgGrid().store.loadData(normalMsgList);
				text='<span style="font-size: 22px; font-weight: bold;">'+noDealTotal+'</span><b>'+login.i18n('dpap.login.app.noReadNotice')+'</b>'
			},
			exception : function(response) {
				var result = Ext.decode(response.responseText);
				Ext.MessageBox.alert(login.i18n('dpap.login.app.prompt'), result.message);
			}
		});
		return text;
	},
	//打开消息窗口
	openMSGWin: function(){
		var me = this;
		me.setText(me.updateMsgData());
		login.msg.msgRemindWindow.show(); 
	},
	handler: function(){
		scroll(0,0);
		var me = this;
		me.openMSGWin();
	},
   	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.text=me.updateMsgData();
		me.callParent([ cfg ]);
	},
	msgTodoData:function(){
		Ext.Ajax.request({
			url : '../message/queryWinFormSettingInfo.action', 
			params:{
				'msgVo.winEntity.userCode': login.currentUser.empEntity.empCode
			},
			success : function(response) { 
				var json = Ext.decode(response.responseText);   
				var winEntity=json.msgVo.winEntity;
				var intervalTime=winEntity.intervalTime;  
				login.msg.intervalTime = intervalTime;
				var autoAlertFlag=winEntity.autoAlertFlag;
				var showMessagePanel = login.msg.msgRemindWindow.getShowMessagesPanel();
				if(autoAlertFlag == 'Y'){
					var toDoCount=showMessagePanel.getToDoMsgGrid().store.data.length;
					var normalMsgCount=showMessagePanel.getNormalMsgGrid().store.data.length;
					if(toDoCount > 0 || normalMsgCount > 0 ){
						login.msg.msgRemindWindow.show(); 
						showMessagePanel.changeBottomAreaCheckBox(autoAlertFlag,intervalTime);
					}
				} 
				showMessagePanel.changeCheckBox(autoAlertFlag);	
			},
			exception : function(response) {
			  var json = Ext.decode(response.responseText); 
			}
		}); 
	},
	initComponent: function() {
		var me = this;
		me.listeners = {
			afterrender: function(cmp, eOpts) {
				me.msgTodoData();
			}
		};
		me.callParent();
    }
});

//待办事项Model
Ext.define('Dpap.main.ToDoMsgModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'businessType', // 待办类型
		type : 'string'
	}, {
		name : 'noDealNum', // 未处理数量
		type : 'int'
	} ]
});
//站内消息Model
Ext.define('Dpap.main.InstationMsgModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'msgType', // 待办类型
		type : 'string'
	}, {
		name : 'noDealNum', // 未处理数量
		type : 'int'
	} ]
});

//待办数据Store
Ext.define('Dpap.main.ToDoMsgStore', {
	extend : 'Ext.data.Store',
	model : 'Dpap.main.ToDoMsgModel'
});

//站内消息Store
Ext.define('Dpap.main.NormalInstationMsgStore', {
	extend : 'Ext.data.Store',
	model : 'Dpap.main.InstationMsgModel'
});

//待办表格
Ext.define('Dpap.main.ToDoMsgGrid', {
	extend : 'Ext.grid.Panel',
	title : login.i18n('dpap.login.app.toDoItem'),
	store : Ext.create('Dpap.main.ToDoMsgStore'),
	columnWidth : 1,
	stripeRows : true,
	columnLines : true,
	collapsible : false,
	bodyCls : 'autoHeight',
	frame : true,
	// 增加表格列的分割线
	cls : 'autoHeight',
	autoScroll : true,
	listeners:{
		itemclick:function(_this, _record, _item, _index, _e, _eOpts ){
			login.msg.bisType = _record.get('businessType');
			openMsgMenu('T_message-toDoMsgInit',login.i18n('dpap.login.app.toDoItem'),'../message/toDoMsgInit.action',null); 
		}
	},
	columns : [ {
		text : login.i18n('dpap.login.app.businessType'),
		align : 'center',
		flex : 1,
		dataIndex : 'businessType',
		renderer:function(value){
			var displayField = Deppon.Dictionary.getDictNameByCode(value,'DPAP_MSG_BUSITYPE');
			return displayField;
		}
	}, {
		text : login.i18n('dpap.login.app.noDealNum'),
		align : 'center',
		flex : 1,
		dataIndex : 'noDealNum'
	} ],
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.callParent([ cfg ]);
	}
});

//普通消息
Ext.define('Dpap.main.NormalInstationMsgGrid', {
	extend : 'Ext.grid.Panel',
	title : login.i18n('dpap.login.app.instationMsg'),
	store : Ext.create('Dpap.main.NormalInstationMsgStore'),
	columnWidth : 1,
	stripeRows : true,
	columnLines : true,
	collapsible : false,
	bodyCls : 'autoHeight',
	frame : true,
	// 增加表格列的分割线
	cls : 'autoHeight',
	autoScroll : true,
	listeners:{
		itemclick:function(_this, _record, _item, _index, _e, _eOpts ){
			login.msg.msgType = _record.get('msgType');
			openMsgMenu('T_message-instationMsgInit',login.i18n('dpap.login.app.instationMsg'),'../message/instationMsgInit.action',null); 
		}
	},
	columns : [ {
		text : login.i18n('dpap.login.app.msgType'),
		align : 'center',
		flex : 1,
		dataIndex : 'msgType',
		renderer:function(value){
			var displayField = Deppon.Dictionary.getDictNameByCode(value,'DPAP_MSG_TYPE');
			return displayField;
		}
	}, {
		text : login.i18n('dpap.login.app.noReadNum'),
		align : 'center',
		flex : 1,
		dataIndex : 'noDealNum'
	} ],
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.callParent([ cfg ]);
	}
});

//消息显示面板
Ext.define('Dpap.main.ShowMessagesPanel',{
	extend : 'Ext.panel.Panel',
	title : '',
	columnWidth : 1,
	cls : 'autoHeight',
	bodyCls : 'autoHeight',
	frame : false,
	toDoMsgGrid : null,
	getToDoMsgGrid : function() {
		if (this.toDoMsgGrid == null) {
			this.toDoMsgGrid = Ext
					.create('Dpap.main.ToDoMsgGrid');
		}
		return this.toDoMsgGrid;
	},
	normalMsgGrid : null,
	getNormalMsgGrid : function() {
		if (this.normalMsgGrid == null) {
			this.normalMsgGrid = Ext
					.create('Dpap.main.NormalInstationMsgGrid');
		}
		return this.normalMsgGrid;
	},
	changeBottomAreaCheckBox:function(value,intervalTime){
		var autoFlag=true,me=this;
		if(value=='Y'){
			autoFlag=false;
		} 
		me.remindInterval(value,intervalTime);
		login.msg.intervalTime=intervalTime;
		this.getBottomArea().items.items[0].setValue(autoFlag);
	},
	changeCheckBox:function(value){
		var autoFlag=true;
		if(value=='Y'){
			autoFlag=false;
		} 
		this.getBottomArea().items.items[0].setValue(autoFlag);
	},
	bottomArea : null,
	getBottomArea : function() {
		var me = this;
		if (me.bottomArea == null) {
			me.bottomArea = Ext.create('Ext.panel.Panel',{
				frame : false,
				columnWidth : 1,
				height : 38,
				layout : 'column',
				items: [{
							xtype : 'checkboxfield',
							width : 200,
							boxLabel : login.i18n('dpap.login.app.noAutoAlertMsg'),
							name : 'notAutoMessage',
							inputValue : 'N',
							listeners:{
								change:function(_this, newValue, oldValue, eOpts){
									var autoAlertFlag='Y';
									if(newValue){
										autoAlertFlag='N';
									} 
									me.remindInterval(autoAlertFlag,login.msg.intervalTime);
									 //保存弹出框设置项 
									var params={
											'msgVo.winEntity.autoAlertFlag' : autoAlertFlag
									};
							        Ext.Ajax.request({
							            url : '../message/uptAutoAlertFlag.action',
							            params:params,
							            success : function(response) { 
							              var json = Ext.decode(response.responseText); 
							            },
							            exception : function(response) {
							              var json = Ext.decode(response.responseText);
							            }
							        });  
								}
							}
							
						},{
							xtype : 'container',
							width : 240,
							html: '&nbsp;'
						},{
							xtype : 'container',
							margin:'5 0 0 0',
							id : 'alert_settings',
							width : 80,
							html:'<span style = "text-decoration:underline;color:#3d74b7">'+login.i18n('dpap.login.app.advancedSettings')+'</span>',
							listeners : {
						         render : function() {
						        	 Ext.fly(this.el).on('click',
						        	    function(e, t) {
						        		 	me.openAlertSetting();
						        	    });
						         }
						    }
						},{
							xtype : 'button',
							text : login.i18n('dpap.login.app.okButton'),
							width : 100,
							cls : 'yellow_button',
							handler : function() {
								 login.msg.msgRemindWindow.hide();
							}
						}]
					});
		}
		return this.bottomArea;
	},
	remindInterval:function(autoFlag,intervalTime){
		if(autoFlag=='Y'){
			if(!Ext.isEmpty(login.msg.intervalId)){
				window.clearInterval(login.msg.intervalId);
			}
			var showMessagePanel = login.msg.msgRemindWindow.getShowMessagesPanel();
			
			var toDoCount=showMessagePanel.getToDoMsgGrid().store.data.length;
			var normalMsgCount=showMessagePanel.getNormalMsgGrid().store.data.length;
			if(toDoCount > 0 || normalMsgCount > 0  ){
				var minIntervalTime=1000*60*intervalTime;
				login.msg.intervalId = setInterval(function(){
					login.msg.msgRemindWindow.show(); 
		   	 	},minIntervalTime);
			}  
		}else{
			if(!Ext.isEmpty(login.msg.intervalId)){
				//动态清除弹出的设置
				window.clearInterval(login.msg.intervalId);
			}
		}
	},
	openAlertSetting:function(){
		var me = this,
		userCode=login.currentUser.empEntity.empCode,
		 //保存弹出框设置项
		params={'msgVo':{'winEntity':{'userCode':userCode}}};
	    Ext.Ajax.request({
	      url : '../message/queryWinFormSettingInfo.action',
	      jsonData:params,
	      success : function(response) { 
	      	var json = Ext.decode(response.responseText),
	      	form=login.msg.winFormSetting.getSettingForm().getForm(),//得到form对象
	      	//从后台获取值
	       	winEntity=json.msgVo.winEntity,
	      	id=winEntity.id,
	      	autoAlertFlag=winEntity.autoAlertFlag,
	      	intervalTime=winEntity.intervalTime,
	      	win = login.msg.winFormSetting;
	      	login.msg.intervalTime=intervalTime;
	      	me.remindInterval(autoAlertFlag,intervalTime);
	      	//窗口弹出
	      	win.show();
	      	win.bindData(autoAlertFlag,id,intervalTime);
	      	
	      },
	      exception : function(response) {
	        var json = Ext.decode(response.responseText);
	        Ext.MessageBox.alert(login.i18n('dpap.login.app.prompt'), json.message);
	      } 
	  }); 
	},
	bodyStyle : 'padding:0px 0px 0px 2px',
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.items = [ me.getToDoMsgGrid(),
				me.getNormalMsgGrid(),// me.getNetMsgGrid(),
				me.getBottomArea() ];
		me.callParent([ cfg ]);
	}
});
//当用户第一次登时弹出消息提醒窗口
Ext.define('Dpap.main.MsgRemindWindow', {
	extend : 'Ext.Window',
	closeAction : 'hide',
	title : login.i18n('dpap.login.app.msgRemind'),
	modal : true,
	height : 'autoHeight',
	width : 700,
	resizable : false,
	layout : 'column',
	showMessagesPanel : null,
	getShowMessagesPanel : function() {
		if (this.showMessagesPanel == null) {
			this.showMessagesPanel = Ext.create('Dpap.main.ShowMessagesPanel');
		}
		return this.showMessagesPanel;
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.items = [ me.getShowMessagesPanel() ];
		me.callParent([ cfg ]);
	}
});

//创建弹出窗口
login.msg.msgRemindWindow = Ext.create('Dpap.main.MsgRemindWindow');

//弹出框口时间间隔设置页面
Ext.define('Dpap.main.opAlertSettingForm',{
	extend:'Ext.form.Panel',
	defaults:{
		margin :'5 5 5 0', 
		colspan :1 
	},
	defaultType:'textfield',
	layout:{
		type :'table',
		columns : 2
	},
	items:[{	
		name:'id',
		hidden:true
	},{	
	    xtype: 'numberfield',
		name:'intervalTime',
		fieldLabel:login.i18n('dpap.login.app.popupInterval'), 
		value:10,
		allowBlank: false,
		allowDecimals:false, 
        minValue: 8 ,
        maxValue: 90
	},{
		xtype: 'displayfield',
		fieldLabel: '&nbsp;'+login.i18n('dpap.login.app.min'),
		labelSeparator:'',
		allowBlank: true
	},{
 		xtype:'radiogroup',
 		fieldLabel:login.i18n('dpap.login.app.whetherAutoAlert'),
 		name:'autoAlertFlag',  
 		allowBlank:true,
 		colspan:2,
 		defaultType:'radio',
 		layout:'table',
 		isFormField: true,
 		items:[{
 			boxLabel:login.i18n('dpap.login.app.yes'),
 			name:'autoAlertFlag',
 			inputValue:'Y' 
 		},{
 			boxLabel:login.i18n('dpap.login.app.no'),
 			name:'autoAlertFlag',
 			inputValue:'N' 
 		}]
  	 }],
  	  bbar: [{
		  text:login.i18n('dpap.login.app.cancel'),
		  handler: function(){
			  login.msg.winFormSetting.close();
		  }
	  	},'->',{
			text:login.i18n('dpap.login.app.okButton'),
			handler:function(){
				var form = this.up('form').getForm();   
				if(form.isValid()){
					var id=form.findField("id").getValue();  
					var autoAlertFlag=form.getValues().autoAlertFlag; 
					var intervalTime=form.findField("intervalTime").getValue();  
					var userCode=login.currentUser.empEntity.empCode;
					 //保存弹出框设置项 
					var params={
							'msgVo.winEntity.id' : id,
							'msgVo.winEntity.userCode' : userCode,
							'msgVo.winEntity.intervalTime' : intervalTime,
							'msgVo.winEntity.autoAlertFlag' : autoAlertFlag
					};
			        Ext.Ajax.request({
			            url : '../message/updateSetting.action',
			            params:params,
			            success : function(response) { 
			              var json = Ext.decode(response.responseText); 
			              Ext.MessageBox.alert(login.i18n('dpap.login.app.prompt'), json.message);
			              login.msg.msgRemindWindow.getShowMessagesPanel().changeBottomAreaCheckBox(autoAlertFlag,intervalTime);
			              login.msg.winFormSetting.close();
			            },
			            exception : function(response) {
			              var json = Ext.decode(response.responseText);
			              Ext.MessageBox.alert(login.i18n('dpap.login.app.prompt'), json.message);
			              login.msg.winFormSetting.close();
			            }
			        }); 
				}else{
					 Ext.MessageBox.alert(login.i18n('dpap.login.app.prompt'),login.i18n('dpap.login.app.inputParamerIllegal'));
				}
			}
	  	}]
});

Ext.define('Dpap.main.winFormSetting', {
	extend: 'Ext.window.Window', 
	title: login.i18n('dpap.login.app.toDoAndSystemMsgConfig'),
	width: 350,
	modal:true,
	closeAction: 'hide',
	settingForm:null,
	getSettingForm: function () {
		if(this.settingForm == null){
			this.settingForm = Ext.create("Dpap.main.opAlertSettingForm");
		}
		return this.settingForm;
	},
	bindData: function(autoAlertFlag,id,intervalTime){
		var form = this.getSettingForm().getForm(),
      		groupRadio=form.findField('autoAlertFlag').items;
      	for(var i=0;i<groupRadio.items.length;i++){
      		if(groupRadio.items[i].inputValue==autoAlertFlag){
      			groupRadio.items[i].setValue(true);
      		}else if(groupRadio.items[i].inputValue==autoAlertFlag){
      			groupRadio.items[i].setValue(true);
      		}
      	}
      	form.findField('id').setValue(id);
      	form.findField("intervalTime").setValue(intervalTime);  
	},
	listeners:{
    	beforeshow:function(me){
    		me.getSettingForm().getForm().reset(); 
    	}
    },
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.items = [
		    me.getSettingForm()    
		];
		me.callParent([cfg]);
	}
});
login.msg.winFormSetting=Ext.create('Dpap.main.winFormSetting');
//设置弹出时间间隔

/** 对整个框架进行整合操作 * */
Ext.application({
	name : 'DpFoss',
	launch : function() {
		Ext.QuickTips.init();
		var topPanel = Ext.create('Fssc.main.topPanel', {
	            region : 'north',// 西
	        }),
	        menuPanel = Ext.create('Fssc.main.menuPanel', {
	            region : 'west',// 西
	        }),
	        footer = Ext.create('Fssc.main.footerPanel', {
	            region : 'south',// 南
	        }),
        	centerPanel = Ext.create('Fssc.main.centerPanel', {
	            region : 'center',// 中间
	        });
		Ext.create('Ext.container.Viewport', {
			id : 'dpViewport',
			layout : 'border', // border布局
			// 指定容器内的项是否可以收缩
            defaults : {
                split : false
            },
			items : [topPanel, menuPanel, centerPanel, footer]
		});
	}
});
