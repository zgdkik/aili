config.systemConfig.CONTENT_ID = 'T_config-index_content';

/** 系统参数MODEL */
Ext.define('Dpap.config.configModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'string'
	}, {
		name : 'configKey',
		type : 'string'
	}, {
		name : 'configValue',
		type : 'string'
	}, {
		name : 'createUser',
		type : 'string'
	}, {
		name : 'createDate',
		type : 'Date',
		convert : dateConvert,
		defaultValue : new Date()
	}, {
		name : 'modifyUser',
		type : 'string'
	}, {
		name : 'modofyDate',
		type : 'Date',
		convert : dateConvert,
		defaultValue : new Date()
	} ]
});

/**
 * 定义员工信息Store
 */
Ext.define('Dpap.config.configStore', {
	extend : 'Ext.data.Store',
	pageSize : 15,
	model : 'Dpap.config.configModel',
	proxy : {
		type : 'ajax',
		actionMethods : 'post',
		url : config.realPath("findAllConfig"),
		reader : {
			type : 'json',
			root : 'data',
			totalProperty : 'totalCount'
		}
	},
	listeners : {
		// 在pageStore加载前，向其传参
		beforeload : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp(config.systemConfig.CONTENT_ID).getQueryForm();
			
			if (queryForm != null) {
				var queryParams = queryForm.getValues();
				Ext.apply(operation, {
					params : {
						'entity.configKey' : queryParams.configKey,
						'entity.configValue' : queryParams.configValue
					}
				});
			}
		}
	}
});

/** 查询表单 */
Ext.define('Dpap.config.queryForm', {
	extend  : 'Ext.form.Panel',
	width: 500,
	defaultType : 'textfield',
	layout : 'column',
	defaults : {
		margin : '5 10 5 10',
		columnWidth : .5,
		labelWidth: 50
	},
	items : [{
		fieldLabel : config.systemConfig.i18n('dpap.config.configKey'),
		name : 'configKey'
	}, {
		fieldLabel : config.systemConfig.i18n('dpap.config.configValue'),
		name : 'configValue'
	}],
	buttons:[{
		text : config.systemConfig.i18n('dpap.config.reset'),
		handler : function() {
			this.up('form').getForm().reset();
		}
	}, '->', {
		text : config.systemConfig.i18n('dpap.config.find'),
		cls:'yellow_button',
		handler : function() {
			Ext.getCmp(config.systemConfig.CONTENT_ID).getPageElementGrid()
				.getPagingToolbar().moveFirst();
		}
	}],
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.callParent([cfg]);
	}
});

/** 表单FORM */
Ext.define('Dpap.config.configForm', {
	extend: 'Ext.form.Panel',
	defaultType: 'textfield',
	defaults: {
		labelWidth: 50,
		width: 320
	},
	operatorType: null,
	getOperatorType: function() {
		return this.operatorType;
	},	
	/**
	 * 根据新增还是修改按钮改变窗体的设置值,
	 */
	setOperatorType: function(state, record) {
		this.operatorType = state;
		var form = this.getForm();
		form.reset();
		
		if (state == 'add') {
			this.setFormFieldsReadOnly(false);
			record = Ext.create('Dpap.config.configModel');
		} else {
			this.setFormFieldsReadOnly(true);
		}
		
		form.loadRecord(record);
	},
	setFormFieldsReadOnly: function(flag) {
		var form = this.getForm();
		form.findField('configKey').setReadOnly(flag);
	},
	initComponent: function() {
		var me = this;
		me.items = [{
			fieldLabel: config.systemConfig.i18n('dpap.config.configKey'),
			name: 'configKey',
			allowBlank: false
		}, {
			fieldLabel: config.systemConfig.i18n('dpap.config.configValue'),
			name: 'configValue',
			allowBlank: false
		}];
		me.callParent();
	}
});

/** 定义权限-新增和修改窗体 */
Ext.define('Dpap.config.configWindow', {
	extend: 'Ext.window.Window',
	width: 380,
	height: 180,
	closable: true,
	resizable: false,
	closeAction: 'hide',
	modal: true,
	layout: 'fit',
	// 表单
	editFunctionForm: null,
	getEditFunctionForm: function(){
		if (Ext.isEmpty(this.editFunctionForm)) {
			this.editFunctionForm = Ext.create("Dpap.config.configForm");
		}
		
		return this.editFunctionForm;
	},
	// 取消按钮
	cancelButton : null,
	getCancelButton:function(){
		var me = this;
		if (Ext.isEmpty(this.cancelButton)) {
			this.cancelButton = Ext.create('Ext.button.Button',{
				text: config.systemConfig.i18n('dpap.config.cancel'),
				handler: function(){
					me.hide();
				}
			});
		}
		
		return this.cancelButton;
	},
	// 提交
	submitFunction: function(form, action) {
		var me = this;
		// 当前表单中的数据：
		var formValue = form.getValues(); 
		
		var params = formValue;
	    Ext.Ajax.request({
	        url : config.realPath(action),
	        jsonData: params,
	        success : function(response) {
	        	Ext.ux.Toast.msg(
	        			config.systemConfig.i18n('dpap.config.msg.notice'), 
	        			config.systemConfig.i18n('dpap.config.saveSuccess'));
	        	me.hide();
	        	
	        	if (me.getEditFunctionForm().getOperatorType() == 'add') {
	        		Ext.getCmp(config.systemConfig.CONTENT_ID).getPageElementGrid().getPagingToolbar().moveFirst();
				} else {
					Ext.getCmp(config.systemConfig.CONTENT_ID).getPageElementGrid().getPagingToolbar().moveFirst();
				}
	        },
	        exception : function(response) {
	        	var json = Ext.decode(response.responseText); 
	        	Ext.ux.Toast.msg(
	        			config.systemConfig.i18n('dpap.config.msg.notice'), 
	        			json.msg, 'error');
	        }
	    }); 
	},
	// 保存按钮
	saveButton : null,
	getSaveButton: function(){
		var me = this;
		if (Ext.isEmpty(this.saveButton)) {
			this.saveButton = Ext.create('Ext.button.Button',{
				cls:'yellow_button',
				text: config.systemConfig.i18n('dpap.config.save'),
				handler: function() {
					var form = me.getEditFunctionForm().getForm();
					if (!form.isValid()) {
						return;
					}
					
					if (me.getEditFunctionForm().getOperatorType() == 'add') {
						me.submitFunction(form, 'saveConfig');
					} else {
						me.submitFunction(form, 'updateConfig');
					}
				}
			});
		}
		return this.saveButton;
	},
	initComponent: function() {
		var me = this;
		me.items = [
			me.getEditFunctionForm()
	    ];
		me.buttons = [
			me.getCancelButton(),'->',
			me.getSaveButton()
		];
		me.callParent();
	}
});

/**
 * 系统参数配置
 */
Ext.define('Dpap.config.configGrid', {
	extend : 'Ext.grid.Panel',
	title : config.systemConfig.i18n('dpap.config.Girdconfig'),
	frame : true,
	sortableColumns : false,
	enableColumnHide : false,
	enableColumnMove : false,
	columnLines : true,
	pagingToolbar : null,
	getPagingToolbar : function() {
		if (Ext.isEmpty(this.pagingToolbar)) {
			this.pagingToolbar = 
				Ext.create('Deppon.StandardPaging', {
					store : this.store,
					pageSize : 15
				});
		}
		
		return this.pagingToolbar;
	},
	mewindow : null,
	getMewindow : function(){
		if (Ext.isEmpty(this.mewindow)) {
			this.mewindow = Ext.create('Dpap.config.configWindow');
			config.systemConfig.configWindow = this.mewindow;
		}
		
		return this.mewindow;
	},
	// 新增按钮
	addButton: null,
	getAddButton: function() {
		var me = this;
		if (Ext.isEmpty(me.addButton)) {
			me.addButton = Ext.create('Ext.Button', {
				text : config.systemConfig.i18n('dpap.config.insert'),
				handler : me.onInsertRecord,
				scope: me
			});
		}
		
		return this.addButton;

	},
	onInsertRecord: function() {
		var me = this,
			window = me.getMewindow();
		window.setTitle(
				config.systemConfig.i18n('dpap.config.insert') 
				+ config.systemConfig.i18n('dpap.config.Girdconfig'));
		window.getEditFunctionForm().setOperatorType('add');
		window.show();
	},
    onDeleteRecord: function(grid, rowIndex, colIndex) {
    	var me = this;
    	Ext.Msg.confirm(
    			config.systemConfig.i18n('dpap.config.msg.notice'), 
    			config.systemConfig.i18n('dpap.config.msg.confirm.delete'), 
    		function(btn) {
	    		if(btn == "yes") {
	    			var record = grid.store.getAt(rowIndex),
	    				configKey = record.get('configKey');
	    			Ext.Ajax.request({
	    				url: config.realPath('delelteByKey'),
	    				params: {
	    					"configKey": configKey
	    				},
	    				success: function(response) {
	    					Ext.getCmp(config.systemConfig.CONTENT_ID).getPageElementGrid().getPagingToolbar().moveFirst();
	    					
	    					Ext.ux.Toast.msg(
	    		        			config.systemConfig.i18n('dpap.config.msg.notice'), 
	    		        			config.systemConfig.i18n('dpap.config.deleteSuccess'));
	    				}
	    			});
	    		}
    		});
    },
    onModifyRecord: function(grid, rowIndex, colIndex) {
    	var	me = this,
    		record = grid.store.getAt(rowIndex),
    		window = me.getMewindow();
    	window.setTitle(
				config.systemConfig.i18n('dpap.config.edit') 
				+ config.systemConfig.i18n('dpap.config.Girdconfig'));
    	window.getEditFunctionForm().setOperatorType('edit', record);
		window.show();
    },
	initComponent: function() {
		var me = this;
		me.store = Ext.create('Dpap.config.configStore');

		me.columns = [{ xtype: 'rownumberer', width: 40 }, 
		{
			xtype: 'actioncolumn',
			width: 60,
			align: 'center',
			text: config.systemConfig.i18n('dpap.config.operate'),
			items: [
			{
				iconCls: 'deppon_icons_edit',
                tooltip: config.systemConfig.i18n('dpap.config.edit'),
                handler: me.onModifyRecord,
                scope: me
			}, {
				iconCls: 'deppon_icons_cancel',
                tooltip: config.systemConfig.i18n('dpap.config.delete'),
                handler: me.onDeleteRecord,
			}]
    	}, {
			header : config.systemConfig.i18n('dpap.config.configKey'),
			dataIndex : 'configKey',
			flex: 1
		}, {
			header : config.systemConfig.i18n('dpap.config.configValue'),
			dataIndex : 'configValue',
			flex: 1
		}];
		me.bbar = me.getPagingToolbar();
    	me.tbar = [ me.getAddButton() ];
		me.callParent();
	}
});

/**
 * @description 权限管理主页
 */
Ext.onReady(function() {
	Ext.QuickTips.init();
		
	var pageElementGrid = Ext.create('Dpap.config.configGrid');
	
	var queryForm = Ext.create('Dpap.config.queryForm');
	
	var content = Ext.create('Ext.panel.Panel', {
		id : config.systemConfig.CONTENT_ID,
		cls : "panelContentNToolbar",
		bodyCls : 'panelContentNToolbar-body',
		getPageElementGrid : function() {
			return pageElementGrid;
		},
		getQueryForm : function() {
			return queryForm;
		},
		items : [{
			title : config.systemConfig.i18n('dpap.config.findMsg'),
			frame : true,
			items: [queryForm]
		}, pageElementGrid ]
	});
	
	Ext.getCmp('T_config-systemConfigIndex').add(content);
});