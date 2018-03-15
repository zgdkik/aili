/*删除的角色数组*/
authorization.role.deleteResourceCodes = new Array();
/*临时角色数组*/
authorization.role.tempResourceCodes = new Array();
/* 当前模式  0.查看，1.修改，2.新增 */
authorization.role.isModify = 1;

/*字符串数组操作*/
authorization.role.removeStr = function(p_array, str) {
	if (p_array == null || p_array.length == 0) {
		return p_array;
	}
	for (var i = 0, l = p_array.length; i < l; i++) {
		if (p_array[i] == str) {
			p_array.splice(i, 1);
		}
	}
	return p_array;
};

/*扩展方法去掉数组指定元素*/
Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
Array.prototype.remove = function(val) {
	var index = 0 ;
	while((index = this.indexOf(val))!=-1){
		this.splice(index, 1);
	}
};

/* 角色查询条件 */
Ext.define('Dpap.authorization.QueryRoleConditionForm',{
	extend:'Ext.form.Panel',
	id: 'Dpap_authorization_QueryRoleConditionForm_ID',
	frame : true,
	title: authorization.role.i18n('dpap.authorization.role.rolequeryform'),
	defaults: {
		margin:'5 10 5 10'
	},
	layout : 'column',	
	defaultType : 'textfield',
	initComponent: function(){
		var me = this;
		me.items = [{
    		name: 'name',
    		fieldLabel: authorization.role.i18n('dpap.authorization.role.rolename'),
    		labelWidth: 70,					
    		columnWidth: .3
    	}, {
    		xtype : 'button',
			cls: 'yellow_button',
			text: authorization.role.i18n('dpap.authorization.query'),
			width : 70,
			// 查询按钮的响应事件：
			handler: function() {
				var selectResultPanel = Ext.getCmp("Dpap_authorization_QueryRoleResultGrid_ID");
				selectResultPanel.setVisible(true);
				selectResultPanel.getPagingToolbar().moveFirst();
			}
		}];
		me.callParent();
	}
});

// 角色Model
Ext.define('Dpap.authorization.role.RoleModel', {
    extend: 'Ext.data.Model',
    fields : [{
        name : 'roleCode',
        type : 'string'
    },{
        name : 'roleName',
        type : 'string'
    },{
        name : 'roleDesc',
        type : 'string'
    }]
});

// 角色Store 
Ext.define('Dpap.authorization.role.RoleStore',{
	extend:'Ext.data.Store',
	model: 'Dpap.authorization.role.RoleModel',
	pageSize: 10,
	autoLoad: false,
	proxy: {
		type: 'ajax',
		actionMethods: 'POST',
		url : authorization.realPath("findAllRoleByCondition"),
		reader: {
			type: 'json',
			root: 'data',
			totalProperty : 'totalCount'
		}
	},
	listeners: {
		beforeload : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp("Dpap_authorization_QueryRoleConditionForm_ID");
			if (queryForm != null) {
				var a_name = queryForm.getForm().findField('name').getValue();
				Ext.apply(operation, {
					params : {
						'roleVo.roleEntityDetail.roleName' : a_name
					}
				});	
			}
		}
	}
});

// 定义权限树的TreeStore
Ext.define('Dpap.authorization.role.RoleTreeStore',{
  	extend: 'Ext.data.TreeStore',
  	root: {
		text: authorization.role.i18n('dpap.authorization.role.appsystem'),
		id : '0'
	},
  	proxy: {
		type : 'ajax',
		actionMethods : 'POST',
		url : authorization.realPath('loadFunctionChooseAllTree'),
        reader: {
             type: 'json',
             root: 'data'
        }
  	}	
}); 

//checked所有父结点
authorization.role.checkParent = function(node,checked){
	var parentNode = node.parentNode;
	if(parentNode){
		/*父节点没选中*/
		if(parentNode.data.checked==false){
			authorization.role.removeStr(authorization.role.deleteResourceCodes,parentNode.data.id);
			parentNode.set('checked',true);
		}
		authorization.role.checkParent(parentNode,checked);
	}
};

//定义权限树结构 xyk10
Ext.define('Dpap.authorization.role.RoleTree', {
	extend:'Ext.tree.Panel',
	id: 'Dpap_authorization_role_RoleTree_ID',
	autoScroll: true,
	animate: false,
	useArrows: true,
	frame: true,
	rootVisible: true,
	border: true,
	height : 450,
	expandNodes: [],
	getCheckedRoleCodes: function() {
		var checkedNodes = this.getChecked(),
			ids = [];
		for (var t = 0; t < checkedNodes.length; t++) {
			ids[t] = checkedNodes[t].data.id;
		}
		
		return ids;
	},
	expandPaths: function(pathArray) {
		var me = this;
		me.collapseAll();
		var path;
		for (var i = 0; i < pathArray.length; i++) {
			path = pathArray[i];
			me.expandPath(path, 'id', '/', function(success, lastNode){
				if(success){
					me.expandNodes.push(lastNode);
					me.selectPath(path);
				}
			});	    						
		}
	},
	/** 当所有子节点没有选中时候，取消选择父节点 */
	deSelectParentFunction : function(node) {
		//根节点ID
		if (node.data.id == this.store.root.id)
			return;
		
		var parentNode = node.parentNode;
		if (parentNode.hasChildNodes()) {
			for (var i = 0; i < parentNode.childNodes.length; i++) {
				var childNode = parentNode.childNodes[i];
				if (childNode.data.checked == true) {
					return;
				}
			}
		}
		if(parentNode.data.id != this.store.root.id){
			var a_code=parentNode.data.id;
			authorization.role.deleteResourceCodes.push(a_code);
			parentNode.set("checked", false);		
		}
		this.deSelectParentFunction(parentNode);
	},
	checkChage : function(node, checked){
		var a_code = node.data.id;
		var roleCode = Ext.getCmp('Dpap_authorization_role_RoleForm_ID').roleCode;
		
		if (checked == true) {
			authorization.role.tempResourceCodes.push(a_code);
			authorization.role.checkParent(node, true);
			authorization.role.removeStr(authorization.role.deleteResourceCodes, a_code);	
		} else {
		    //去除用户取消的编码
		    authorization.role.tempResourceCodes.remove(a_code);
			// 判断父节点下是否还有子节点是选中状态
			this.deSelectParentFunction(node);
			authorization.role.deleteResourceCodes.push(a_code);
			
		}
	},
	clearFields: function() {
		this.down('rolenames').setValue(null);
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.store = Ext.create('Dpap.authorization.role.RoleTreeStore',{
			listeners : {
				beforeload : function(store, operation, eOpts) {
					var searchParams = operation.params;
					if (authorization.role.isModify == 2) {
						Ext.apply(operation, {
							params : {'node':searchParams.node, 'modify':2}
						});
					} else {
						var roleCode = Ext.getCmp('Dpap_authorization_role_RoleForm_ID').roleCode;
						Ext.apply(operation, {
							params : {'node':searchParams.node, 'roleId': roleCode, 'modify': authorization.role.isModify}
						});
					}
				}
			}
		});
		me.tbar = [{
			xtype: 'textfield',
			name:'rolenames',
			fieldLabel: authorization.role.i18n('dpap.authorization.role.resourceName'),
			labelWidth: 70
		},{
			text: authorization.role.i18n('dpap.authorization.query'),
			margin:'5 10 5 5',
			// 查询按钮的响应事件：
			handler: function() {
				// 根据权限名称查询树结构
				var a_resourceTree = Ext.getCmp("Dpap_authorization_role_RoleTree_ID");
				var a_name = document.getElementsByName('rolenames')[0].value;
				var params = {'functionVo.functionCode':  a_name}; 
				if(a_name == ''){
					Ext.ux.Toast.msg('', 
							authorization.role.i18n('dpap.authorization.role.functionnamenotnull'), 'error');
					return ; 
				}
		        Ext.Ajax.request({
		            url : authorization.realPath('findFunctionSeqs'),
		            params: params,
					async: false,
		            success : function(response) {
		            	var result = Ext.decode(response.responseText); 
		            	me.expandPaths(result.functionSeqs);
		            },
		            exception : function(response) {
		            	var json = Ext.decode(response.responseText); 
			        	Ext.ux.Toast.msg(
			        			authorization.role.i18n('dpap.authorization.msginfo'), 
			        			json.msg, 'error');
		            }
		        });
			}
		}];
		// 监听鼠标事件
		me.listeners = {
			checkchange: me.checkChage,
			/*颜色*/
			afteritemexpand: function(node, index, item, eOpts){
				var expandNodes = me.expandNodes,
					flag = true,
					view = me.getView();
				if(expandNodes.length==0){
					return;
				}
				for(var i=0;i<expandNodes.length;i++){
					if(expandNodes[i]==null){
						flag = false;
						continue;
					}
					var nodeHtmlEl = me.getView().getNode(expandNodes[i]),
						nodeEl = Ext.get(nodeHtmlEl);
					if(Ext.isEmpty(nodeEl)){
						continue;
					}
					var divHtmlEl = nodeEl.query('div')[0],
					    divEl = Ext.get(divHtmlEl);
					divEl.highlight("ff0000", { attr: 'color', duration: 5000 });
				}
				if(flag){
					me.expandNodes = [];
				}
		}};
		me.callParent([cfg]);
	}
});

// 编辑窗口上面角色详情
Ext.define('Dpap.authorization.role.RoleForm', {
	extend:'Ext.form.Panel',
	id:'Dpap_authorization_role_RoleForm_ID',
	defaultType : 'textfield',
	layout:'column',
	defaults: {
		margin:'5 5 5 5',
		readOnly: true,
		labelWidth: 60,
		allowBlank: false
	},
	roleId:'',
	roleCode:'',
	setFormFieldsReadOnly: function(flag) {
		var form = this.getForm();
		form.findField('roleName').setReadOnly(flag);
		form.findField('roleDesc').setReadOnly(flag);
		form.findField('roleCode').setReadOnly(flag);
	},
	setRoleFormDatas : function(roleDesc, roleName, roleId, roleCode) {
		this.getForm().findField('roleName').setValue(roleName);
		this.getForm().findField('roleDesc').setValue(roleDesc);
		this.getForm().findField('roleCode').setValue(roleCode);
		this.roleId = roleId;
		this.roleCode = roleCode;
	},
	initComponent: function(){
		var me = this;
		me.items = [{
			name:'roleCode',
			fieldLabel: authorization.role.i18n('dpap.authorization.role.rolecode'),
			columnWidth: .3
		}, {
			name: 'roleName',
			fieldLabel: authorization.role.i18n('dpap.authorization.role.rolename'),
			columnWidth: .3
		},{
			name: 'roleDesc',
			fieldLabel: authorization.role.i18n('dpap.authorization.role.notes'),
			allowBlank: true,
			columnWidth: .3
		}];
		me.callParent();
	}
});

/*角色修改窗口*/
Ext.define('Dpap.authorization.role.RoleWindow',{
	extend: 'Ext.window.Window',
	title: authorization.role.i18n('dpap.authorization.role.updateroleresource'),
	width: 700,
	height: 600,
	modal: true,
	closeAction: 'hide',
	roleForm: null,
	windowState: 1,
	setWindowTitle: function(prefix) {
		this.setTitle(prefix + authorization.role.i18n('dpap.authorization.role.rolefunction'));
	},
	setWindowState: function(state) {
		var me = this;
		me.windowState = state;
		me.getRoleForm().getForm().reset();
		if (state == 1 || state == 0) {
			me.getRoleForm().setFormFieldsReadOnly(true);
		} else {
			me.getRoleForm().setFormFieldsReadOnly(false);
		}
	},
	showEditButtons: function(flag) {
		if (flag) {
			this.resetButton.show();
        	this.saveButton.show();
        	this.cancelButton.hide();
		} else {
			this.resetButton.hide();
        	this.saveButton.hide();
        	this.cancelButton.show();
		}
	},
	getWindowState: function() {
		return this.windowState;
	},
	getRoleForm: function(){
		if(this.roleForm==null){
			this.roleForm = Ext.create("Dpap.authorization.role.RoleForm");
		}
		
		return this.roleForm;
	},
	roleFunctionTree: null,
	getRoleFunctionTree: function(){
		var me = this;
		if(Ext.isEmpty(me.roleFunctionTree)){
			me.roleFunctionTree = Ext.create('Dpap.authorization.role.RoleTree');
		}
		
		return me.roleFunctionTree;
	},
	/** 新增角色，验证通过返回true */
	saveRole: function() {
		var me = this;
		var form = me.getRoleForm().getForm();
		if (!form.isValid()) {
			return false;
		}
		
		Ext.Ajax.request({
	        url : authorization.realPath('saveRole'),
	        jsonData: {
	        		"roleEntityDetail": form.getValues(), 
	        		"resourceCodes": Ext.getCmp('Dpap_authorization_role_RoleTree_ID').getCheckedRoleCodes()
	        		},
	        success : function(response) {
	        	Ext.ux.Toast.msg(
	        			authorization.role.i18n('dpap.authorization.msginfo'), 
	        			authorization.role.i18n('dpap.authorization.saveSuccess'), 'ok');
	        	me.close();
	        	// 刷新表格
	        	Ext.getCmp("Dpap_authorization_QueryRoleResultGrid_ID").getPagingToolbar().moveFirst();
	        },
	        exception : function(response) {
	        	var json = Ext.decode(response.responseText); 
	        	Ext.ux.Toast.msg(
	        			authorization.role.i18n('dpap.authorization.msginfo'), 
	        			json.msg, 'error');
	        }
	    });
		
		return true;
	},
	updateRole: function() {
		var me = this;
		//将角色编码及包含的权限发送到后台：
		Ext.Ajax.request({
			method:'POST',
			url: authorization.realPath("updateRoleFunction"),
			jsonData: {
					'resourceCodes': Ext.getCmp('Dpap_authorization_role_RoleTree_ID').getCheckedRoleCodes(),
					'deleteResourceCodes': authorization.role.deleteResourceCodes,
					'roleEntityDetail': {
						'roleCode': Ext.getCmp('Dpap_authorization_role_RoleForm_ID').roleCode
				}
			},
			success:function(response){
				me.close();
				Ext.ux.Toast.msg(
						authorization.role.i18n('dpap.authorization.msginfo'), 
						authorization.role.i18n('dpap.authorization.saveSuccess'), 'ok', 1000);
			},
	        exception : function(response) {
	        	var json = Ext.decode(response.responseText),
	        		errorconflicts = json.data;
	        	if (Ext.isEmpty(errorconflicts)) {
	        		Ext.ux.Toast.msg(
	        			authorization.role.i18n('dpap.authorization.msginfo'), 
	        			json.msg, 'error');
				} else {
					json.msg = json.msg + ":</br>";
					var conflictEntity = null;
	        		for (var i = 0; i < errorconflicts.length; i++) {
	        			conflictEntity = errorconflicts[i];
	        			json.msg = json.msg + "[" + conflictEntity.firstCodeName + "--" + conflictEntity.secondCodeName + "]";
	        			if ((i+1) % 3 == 0) {
	        				json.msg = json.msg + "</br>";
	        			} else {
	        				json.msg = json.msg + ",";
	        			}
					}
	        		Ext.Msg.alert(authorization.role.i18n('dpap.authorization.msginfo'), json.msg);
				}
	        }
		});
	},
	cancelButton: null, // 关闭按钮
	getCancelButton: function() {
		var me = this;
		if (Ext.isEmpty(me.cancelButton)) {
			me.cancelButton = Ext.create('Ext.button.Button', {
				text: authorization.role.i18n('dpap.authorization.close'),
				handler: function() {
					me.close();
				}
			});
		}
		
		return me.cancelButton;
	},
	resetButton : null, // 重置按钮
	getResetButton : function() {
		var me = this;
		if(Ext.isEmpty(me.resetButton)){
			me.resetButton = Ext.create('Ext.button.Button', {
				name: 'reset',
				text: authorization.role.i18n('dpap.authorization.reset'),
				handler: function() {
					authorization.role.deleteResourceCodes = [];
					authorization.role.tempResourceCodes =[];
					Ext.getCmp('Dpap_authorization_role_RoleTree_ID').getStore().load();
					
					if (me.getWindowState() == 2) {
						me.getRoleForm().getForm().reset();
					}
				}
			});
		}
		
		return me.resetButton; 
	},
	saveButton : null, // 保存按钮
	getSaveButton : function(){
		var me = this;
		if (Ext.isEmpty(me.saveButton)) {
			me.saveButton = Ext.create('Ext.button.Button', {
				cls:'yellow_button',
				text: authorization.role.i18n('dpap.authorization.save'),
				handler: function() { // 点击保存
					if (me.getWindowState() == 2) {
						me.saveRole();
					} else {
						me.updateRole();
					}
				}
			});
		}
		
		return this.saveButton;
	},
	constructor: function(config){
		var me = this,
		cfg = Ext.apply({}, config);
		me.items = [
			me.getRoleForm(), me.getRoleFunctionTree()
	    ];
		me.buttons = [
			me.getResetButton(), '->',
			me.getCancelButton(), '->',
			me.getSaveButton()
	 	];
		me.callParent([cfg]);
	}
});

/** ******************************************* */
/*角色查询结果Panel*/
Ext.define('Dpap.authorization.QueryRoleResultGrid',{
	extend: 'Ext.grid.Panel',
	id: 'Dpap_authorization_QueryRoleResultGrid_ID',
	title: authorization.role.i18n('dpap.authorization.role.rolequery'),
	cls: 'autoHeight',
	bodyCls: 'autoHeight',
	// 设置表格不可排序
	sortableColumns: false,
	// 去掉排序的倒三角
    enableColumnHide: false,
    // 设置“如果查询的结果为空，则提示”
    emptyText: authorization.role.i18n('dpap.authorization.role.noqueryresult'),
	stripeRows : true, // 交替行效果
	collapsible: true,
    animCollapse: true,
    frame: true,
    updateRoleWindow: null,
	getUpdateRoleWindow: function(){
		var me = this;
		if(me.updateRoleWindow == null){
			me.updateRoleWindow = Ext.create('Dpap.authorization.role.RoleWindow');
			authorization.role.updateRoleWindow = me.updateRoleWindow;
		}
		
		return this.updateRoleWindow;
	},
	pagingToolbar : null,
	getPagingToolbar : function() {
		var me = this;
		if (me.pagingToolbar == null) {
			me.pagingToolbar = Ext.create('Deppon.StandardPaging', {
				store : me.store
			});
		}
		
		return me.pagingToolbar;
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.store = Ext.create('Dpap.authorization.role.RoleStore');
		me.tbar = [{
			text: authorization.role.i18n('dpap.authorization.add'),
			//hidden: !authorization.role.isPermission('/authorization/saveRole'),
			handler: function(){ // 新增按钮
				var xWindow = me.getUpdateRoleWindow();
				authorization.role.isModify = 2;
				authorization.role.deleteResourceCodes = [];
				authorization.role.tempResourceCodes = [];
				
				xWindow.setWindowState(authorization.role.isModify);
				xWindow.setWindowTitle(authorization.role.i18n('dpap.authorization.add'));
				xWindow.showEditButtons(true);
				xWindow.getRoleFunctionTree().getStore().load();
				xWindow.show();
			}
		}];
		me.bbar = me.getPagingToolbar();
		me.columns = [{
            xtype:'actioncolumn',
            flex: 1,
			text: authorization.role.i18n('dpap.authorization.operate'),
			align: 'center',
            items: [{
		    	iconCls:'deppon_icons_delete',
		    	//hidden: !authorization.role.isPermission('/authorization/deleteRole'),
		        tooltip: authorization.role.i18n('dpap.authorization.delete'),
		        handler: function(gridView, rowIndex, colIndex) {
		        	/*删除*/
		        	var rowInfo = Ext.getCmp('Dpap_authorization_QueryRoleResultGrid_ID').store.getAt(rowIndex);
		        	var params = {
			        	'roleVo.roleEntityDetail.id': rowInfo.data.id,
			        	'roleVo.roleEntityDetail.roleCode': rowInfo.data.roleCode
		        	};
					Ext.Msg.confirm(authorization.role.i18n('dpap.authorization.msginfo'),
						authorization.role.i18n('dpap.authorization.confirmMessage'),
						function(btn){
							if(btn=='yes'){
								Ext.Ajax.request({
			     		            url : authorization.realPath('deleteRole'),
			     		            params: params,
			     					async: false,
			     		            success : function(response) {
			     		            	var json = Ext.decode(response.responseText); 
			     		            	Ext.ux.Toast.msg(
			     		            			authorization.role.i18n('dpap.authorization.msginfo'),
			     			        			json.msg, 'success');
										var selectResultPanel = Ext.getCmp("Dpap_authorization_QueryRoleResultGrid_ID");
										selectResultPanel.getPagingToolbar().moveFirst();
			     		            },
			     		            exception : function(response) {
			     		            	var json = Ext.decode(response.responseText); 
			     			        	Ext.ux.Toast.msg(
			     			        			authorization.role.i18n('dpap.authorization.msginfo'), 
			     			        			json.msg, 'error');
			     		            }
			     		        });
							}
						}
					);
		        }
		    }, {
		    	iconCls:'deppon_icons_edit',
		    	//hidden: !authorization.role.isPermission('/authorization/updateRoleFunction'),
		        tooltip: authorization.role.i18n('dpap.authorization.update'),
		        handler: function(gridView, rowIndex, colIndex) { // 修改按钮
		        	var xWindow = me.getUpdateRoleWindow(),
		        		rowInfo = gridView.up('grid').getStore().getAt(rowIndex);
					authorization.role.isModify = 1;
					authorization.role.deleteResourceCodes = [];
					authorization.role.tempResourceCodes = [];
					
					xWindow.setWindowState(authorization.role.isModify);
					xWindow.setWindowTitle(authorization.role.i18n('dpap.authorization.update'));
					xWindow.showEditButtons(true);
					xWindow.getRoleForm().setRoleFormDatas(
							rowInfo.data.roleDesc, rowInfo.data.roleName, rowInfo.data.id, rowInfo.data.roleCode);
					xWindow.getRoleFunctionTree().getStore().load();
					xWindow.show();
		        }
		    }, {
		    	iconCls:'deppon_icons_showdetail',
		        tooltip: authorization.role.i18n('dpap.authorization.role.see'),
		        handler: function(gridView, rowIndex, colIndex) { // 查看
		        	var xWindow = me.getUpdateRoleWindow(),
		        		rowInfo = gridView.up('grid').getStore().getAt(rowIndex);
		        	authorization.role.isModify = 0;
		        	authorization.role.deleteResourceCodes = [];
					authorization.role.tempResourceCodes = [];
					
		        	xWindow.setWindowState(authorization.role.isModify);
		        	xWindow.setWindowTitle(authorization.role.i18n('dpap.authorization.role.see'));
		        	xWindow.showEditButtons(false);
					xWindow.getRoleForm().setRoleFormDatas(
							rowInfo.data.roleDesc, rowInfo.data.roleName, rowInfo.data.id, rowInfo.data.roleCode);
					xWindow.getRoleFunctionTree().getStore().load();
					xWindow.show();
		        }
		    }]
        },{
			text : authorization.role.i18n('dpap.authorization.role.rolecode'),
			align: 'center',
			flex: 2,
			dataIndex :'roleCode'
		},{
			text : authorization.role.i18n('dpap.authorization.role.rolename'),
			align: 'center',
			flex: 2,
			dataIndex : 'roleName'
		},{
			text: authorization.role.i18n('dpap.authorization.role.notes'),
			flex: 5,
			// 让表格可以自动换行
			xtype:'linebreakcolumn',
			dataIndex: 'roleDesc'
		}];
		me.callParent(cfg);
	}
});

/**
 * 程序启动的初始化方法：
 */
Ext.onReady(function() {
	Ext.QuickTips.init();
	if (Ext.getCmp('T_authorization-roleIndex_content')) {
		return;
	};
	
	var queryRoleConditionForm = Ext.create('Dpap.authorization.QueryRoleConditionForm');
	var queryRoleResultGrid = Ext.create('Dpap.authorization.QueryRoleResultGrid');
	
	/*角色查询 主界面*/
	var content = Ext.create('Ext.panel.Panel', {
		id: 'T_authorization-roleIndex_content',
		cls: "panelContentNToolbar",
		bodyCls: 'panelContentNToolbar-body',
		getRoleConditionForm: function() {
			return queryRoleConditionForm;
		},
		getRoleResultGrid: function() {
			return queryRoleResultGrid;
		},
		items: [
			queryRoleConditionForm,
			queryRoleResultGrid
		]
	});
	Ext.getCmp('T_authorization-roleIndex').add(content);
});