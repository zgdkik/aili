/**
 * 数据字典维护界面
 */
// 数据字典类型变量
dict.dictType = null;

Ext.define('Deppon.dict.DictionaryDataModel', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'id',
        type: 'string'
    }, {
        name: 'dictType',
        type: 'string'
    }, {
        name: 'valueName',
        type: 'string'
    }, {
        name: 'valueCode',
        type: 'string'
    }, {
        name : 'valueOrder',
        type : 'string'
    }, {
        name: 'language',
        type: 'string'
    }, {
        name: 'active',
        type: 'string'
    }, {
        name: 'noteInfo',
        type: 'string'
    }]
});

Ext.define('Deppon.dict.DictionaryTypeModel', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'id',
        type: 'string'
    }, {
        name: 'typeName',
        type: 'string'
    }, {
        name: 'typeAlias',
        type: 'string'
    }, {
        name: 'parentCode',
        type: 'string'
    }]
});

Ext.define('Deppon.dict.DictionaryDataStore', {
	extend: 'Ext.data.Store',
	model: 'Deppon.dict.DictionaryDataModel',
	proxy: {
		type: 'ajax',
		actionMethods: 'post',
		url: dict.realPath('queryDictionaryData'),
		reader: {
			type: 'json',
			root: 'data',
			totalProperty: 'totalCount'
		}
	}
});

Ext.define('Deppon.dict.DictionaryDataForm', {
	extend: 'Ext.form.Panel',
	defaultType: 'textfield',
	defaults: {
		labelWidth: 80,
		width: 320
	},
	initComponent: function() {
		var me = this;
		me.items = [{
			fieldLabel: dict.i18n('dpap.dict.form.type.valueCode'),
			name: 'valueCode',
			allowBlank: false,
			readOnly: true
		}, {
			fieldLabel: dict.i18n('dpap.dict.form.type.valueName'),
			name: 'valueName',
			allowBlank: false
		}, {
			fieldLabel: dict.i18n('dpap.dict.form.type.valueOrder'),
			name: 'valueOrder',
			xtype: 'numberfield',
			allowBlank: false
		}, {
			fieldLabel: dict.i18n('dpap.dict.form.type.noteInfo'),
			name: 'noteInfo',
			xtype: 'textarea'
		}];
		me.callParent();
	}
});

Ext.define('Deppon.dict.DictionaryDataWin', {
	extend: 'Ext.window.Window',
	width: 380,
	height: 280,
	closable: true,
	resizable: false,
	closeAction: 'hide',
	modal: true,
	layout: 'fit',
	operationUrl: null,
	dictionaryDataMode: null,
	initComponent: function() {
		var me = this;
		me.items = [ Ext.create('Deppon.dict.DictionaryDataForm') ],
		me.buttons = [{
			text: dict.i18n('dpap.dict.btn.cancle'),
			handler: function() {
				me.close();
			}
		}, '->', {
			text: dict.i18n('dpap.dict.btn.save'),
			cls:'yellow_button',
			handler: me.onSaveRecord,
			scope: me
		}]
		me.callParent();
	},
	getDictionaryDataModel: function() {
		if (null == this.dictionaryDataMode) {
			this.dictionaryDataMode = Ext.create("Deppon.dict.DictionaryDataModel");
		}
		return this.dictionaryDataMode;
	},
	loadDictionaryData: function(record) {
		this.down('form').getForm().loadRecord(record);
		this.dictionaryDataMode = record;
	},
	setOperationUrl: function(url) {
		this.operationUrl = url;
	},
	onSaveRecord: function() {
		var me = this,
			dictionaryDataForm = me.down('form').getForm();
		if (dictionaryDataForm.isValid()) {
			dictionaryDataForm.updateRecord(me.getDictionaryDataModel());
			Ext.Ajax.request({
				url: me.operationUrl.toString(),
				jsonData: {
						'dictionaryDataEntity': me.getDictionaryDataModel().data
				},
				success: function(response) {
					var josn = Ext.JSON.decode(response.responseText);
					if(Ext.isEmpty(josn.msg)) {
						/*Ext.getCmp('Deppon.dict.DictionaryGrid_ID').store.load({
							params: {
								code: me.getDictionaryDataModel().data.dictType
							}
						});*/
						dict.dictType = me.getDictionaryDataModel().data.dictType;
						Ext.getCmp('Deppon.dict.DictionaryGrid_ID').store.load();
						Ext.ux.Toast.msg(dict.i18n('dpap.dict.msg.notice'), dict.i18n('dpap.dict.msg.save.success'));
						me.hide();
					} else {
						Ext.ux.Toast.msg(dict.i18n('dpap.dict.msg.notice'), josn.msg, "error", 3000);
					}
				},
				exception : function(response) {
					var json = Ext.decode(response.responseText); 
					Ext.ux.Toast.msg(
							dict.i18n('dpap.dict.msg.notice'), 
							json.msg, 'error', 3000);
			    }
			});
		}
	}
});

Ext.define('Deppon.dict.DictionaryGrid', {
	extend: 'Ext.grid.Panel',
	title: dict.i18n('dpap.dict.grid.title'),
	frame: true,
    sortableColumns: false,
    enableColumnHide: false,
    enableColumnMove: false,
    height: 480,
    dictionaryDataWin: null,
    initComponent: function() {
    	var me = this;
    	me.columns = [
            { xtype: 'rownumberer', width: 40 }, 
            {
				xtype: 'actioncolumn',
				width: 60,
				align: 'center',
				text: dict.i18n('dpap.dict.grid.column.operate'),
				items: [{
					iconCls: 'deppon_icons_edit',
	                tooltip: dict.i18n('dpap.dict.btn.modify'),
	                handler: me.onModifyRecord,
	                scope: me
				}, {
					iconCls: 'deppon_icons_cancel',
	                tooltip: dict.i18n('dpap.dict.btn.abolish'),
	                handler: me.onDeleteRecord,
				}]
	    	}, 
	    	{ text: dict.i18n('dpap.dict.grid.column.valueCode'), dataIndex: 'valueCode', flex: 2 }, 
			{ text: dict.i18n('dpap.dict.grid.column.valueName'), dataIndex: 'valueName', flex: 2 }, 
			{ text: dict.i18n('dpap.dict.grid.column.valueOrder'), dataIndex: 'valueOrder', flex: 1 }, 
			{ text: dict.i18n('dpap.dict.grid.column.noteInfo'), dataIndex: 'noteInfo', width: 180 }
		];
    	me.store = Ext.create('Deppon.dict.DictionaryDataStore', {
			autoLoad: false,
			pageSize: 20,
			listeners: {
				beforeload: function(store, operation, eOpts) {
					Ext.apply(operation, {
						params: {
							'code': dict.dictType
						}
					});
				}
			}
		});
    	me.tbar = [{
    		text: dict.i18n('dpap.dict.btn.add'),
    		handler: me.onAddRecord,
    		scope: me
    	}]
		me.bbar = Ext.create('Deppon.StandardPaging', {
			store: me.store
		});
    	me.callParent();
    },
	getOperationUrl: function(actionType) {
		var operationUrl = null;
		if (actionType === 'add') {
			operationUrl = dict.realPath('addDictionaryData');
		} else if (actionType === 'update') {
			operationUrl = dict.realPath('modifyDictionaryData');
		}
		return operationUrl;
	},
	getDictionaryDataWin: function(title, actionType) {
		var me = this,
			dictionaryDataForm;
		if (!me.dictionaryDataWin) {
			me.dictionaryDataWin = Ext.create('Deppon.dict.DictionaryDataWin');
		}
		dictionaryDataForm = me.dictionaryDataWin.down('form').getForm()
		me.dictionaryDataWin.setOperationUrl(me.getOperationUrl(actionType));
		me.dictionaryDataWin.setTitle(title);
		if (actionType) {
			if (actionType === "add") {
				dictionaryDataForm.findField('valueCode').setReadOnly(false);
				me.dictionaryDataWin.down('form').getForm().reset();
			} else if (actionType === "update") {
				dictionaryDataForm.findField('valueCode').setReadOnly(true);
			}
		}
		return me.dictionaryDataWin;
	},
    onAddRecord: function() {
    	var me = this,
    		tree = Ext.getCmp('Deppon.dict.DictionaryTypeTreePanel_ID'),
    		record = tree.getSelectNodeRecord(),
    		dictWin, id;
    	if(record == null) {
    		Ext.Msg.alert(dict.i18n('dpap.dict.msg.notice'), dict.i18n('dpap.dict.msg.select.type'));
    		return;
    	}
    	if(record.data.leaf == false) {
    		Ext.Msg.alert(dict.i18n('dpap.dict.msg.notice'), dict.i18n('dpap.dict.msg.select.parent'));
    		return;
    	}
    	dictWin = me.getDictionaryDataWin(dict.i18n('dpap.dict.form.title.add'), "add");
    	dictWin.getDictionaryDataModel().set('dictType', record.data.id);
    	dictWin.show();
    },
    onModifyRecord: function(grid, rowIndex, colIndex) {
    	var me = this,
    		record = grid.store.getAt(rowIndex),
    		dictionaryWin;
    	dictionaryWin = me.getDictionaryDataWin(dict.i18n('dpap.dict.form.title.edit'), "update");
    	dictionaryWin.loadDictionaryData(record);
    	dictionaryWin.show();
    },
    onDeleteRecord: function(grid, rowIndex, colIndex) {
    	Ext.Msg.confirm(dict.i18n('dpap.dict.msg.notice'), dict.i18n('dpap.dict.msg.confirm.delete'), function(btn) {
    		if(btn == "yes") {
    			var record = grid.store.getAt(rowIndex),
    				id = record.get('id'),
    				dictType = record.get('dictType');
    			Ext.Ajax.request({
    				url: dict.realPath('abolishDictionaryData'),
    				params: {
    					code: id,
    					dictType:dictType
    				},
    				success: function(response) {
    					/*grid.store.load({
							params: {
								code: dictType
							}
    					});*/
						dict.dictType = dictType;
						grid.store.load();
    				}
    			});
    		}
    	});
    }
});

Ext.define('Deppon.dict.DictionaryTypeForm', {
	extend: 'Ext.form.Panel',
	defaultType: 'textfield',
	defaults: {
		labelWidth: 80,
		width: 320
	},
	initComponent: function() {
		var me = this;
		me.items = [{
			fieldLabel: dict.i18n('dpap.dict.form.field.typeName'),
			name: 'typeName',
			allowBlank: false
		}, {
			fieldLabel: dict.i18n('dpap.dict.form.field.typeAlias'),
			name: 'typeAlias',
			allowBlank: false,
			readOnly: true
		}];
		me.callParent();
	}
});

Ext.define('Deppon.dict.DictionaryTypeWin', {
	extend: 'Ext.window.Window',
	width: 380,
	height: 170,
	closable: true,
	resizable: false,
	closeAction: 'hide',
	modal: true,
	layout: 'fit',
	operationUrl: null,
	dictionaryTypeMode: null,
	initComponent: function() {
		var me = this;
		me.items = [ Ext.create('Deppon.dict.DictionaryTypeForm') ],
		me.buttons = [{
			text: dict.i18n('dpap.dict.btn.cancle'),
			handler: function() {
				me.close();
			}
		}, '->', {
			text: dict.i18n('dpap.dict.btn.save'),
			cls:'yellow_button',
			handler: me.onSaveRecord,
			scope: me
		}]
		me.callParent();
	},
	getDictionaryTypeModel: function() {
		if (null == this.dictionaryTypeMode) {
			this.dictionaryTypeMode = Ext.create("Deppon.dict.DictionaryTypeModel");
		}
		return this.dictionaryTypeMode;
	},
	loadDictionaryType: function(record) {
		this.down('form').getForm().loadRecord(record);
		this.dictionaryTypeMode = record;
	},
	setOperationUrl: function(url) {
		this.operationUrl = url;
	},
	onSaveRecord: function() {
		var me = this,
		dictionaryTypeForm = me.down('form').getForm();
		if (dictionaryTypeForm.isValid()) {
			dictionaryTypeForm.updateRecord(me.getDictionaryTypeModel());
			Ext.Ajax.request({
				url: me.operationUrl.toString(),
				jsonData: {
						'dictionaryTypeEntity': me.getDictionaryTypeModel().data
				},
				success: function(response) {
					var josn = Ext.JSON.decode(response.responseText);
					if(Ext.isEmpty(josn.msg)) {
						Ext.getCmp('Deppon.dict.DictionaryTypeTreePanel_ID').store.load();
						Ext.ux.Toast.msg(dict.i18n('dpap.dict.msg.notice'), dict.i18n('dpap.dict.msg.save.success'));
						me.hide();
					} else {
						Ext.ux.Toast.msg(dict.i18n('dpap.dict.msg.notice'), josn.msg, "error", 3000);
					}
				},
				exception : function(response) {
					var json = Ext.decode(response.responseText); 
					Ext.ux.Toast.msg(
							dict.i18n('dpap.dict.msg.notice'), 
							json.msg, 'error', 3000);
			    }
			});
		}
	}
});

Ext.define('Deppon.dict.DictionaryTypeTreeStore',{
	extend: 'Ext.data.TreeStore',
	proxy: {
		type: 'ajax',
		url: dict.realPath('loadDictionaryTypeTree'),
		reader: {
            type: 'json',
            root: 'data'
        }
	},
	root: {
		id: '000000',
		text: dict.i18n('dpap.dict.tree.root.text'),
		expanded: true
	},
	nodeParam: 'code',
	sorters: [{
		property: 'text',
		direction: 'ASC'
	}]
});

Ext.define('Deppon.dict.DictionaryTypeTreePanel', {
	extend: 'Ext.tree.Panel',
	height: 480,
	title: dict.i18n('dpap.dict.tree.title'),
	frame: true,
	autoScroll: true,
	useArrows: true,
	animate: true,
	rootVisible: true,
	operationMenu: null,
	selectNodeRecord: null,
	dictionaryTypeWin: null,
	initComponent: function() {
		var me = this;
		me.store = Ext.create('Deppon.dict.DictionaryTypeTreeStore');
		me.listeners = {
		  	scrollershow: function(scroller) {
		  		if (scroller && scroller.scrollEl) {
	  				scroller.clearManagedListeners();
	  				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller);
		  		}
		  	},
	    	itemclick: me.onClickNode
	    };
		me.on('itemcontextmenu', me.onCreateMenu, me);
		me.callParent();
	},
	onClickNode: function(node, record, item, index, e) {
		var me = this,
			dictGrid = Ext.getCmp('Deppon.dict.DictionaryGrid_ID');
		me.selectNodeRecord = record;
    	if(record.data.leaf != true) {
    		return;
    	}
    	/*
		dictGrid.store.load({
			params: {
				code: record.data.id
			}
		});*/
		dict.dictType = record.data.id;
		dictGrid.store.loadPage(1);
		//dictGrid.store.load();
		
	},
	getSelectNodeRecord: function() {
		return this.selectNodeRecord;
	},
	createCellItem: function() {
		var me = this;
		if(me.operationMenu == null) {
			me.operationMenu = Ext.create('Ext.menu.Menu', {
				items: [{
					itemId: 'add',
					text: dict.i18n('dpap.dict.btn.type.add'),
					handler: me.onAddNode,
					scope: me
				}, {
					itemId: 'modify',
					text: dict.i18n('dpap.dict.btn.type.modify'),
					handler: me.onModifyNode,
					scope: me
				}, {
					itemId: 'delete',
					text: dict.i18n('dpap.dict.btn.type.abolish'),
					handler: me.onDeleteNode,
					scope: me
				}]
			});
		}
		return me.operationMenu;
	},
	onCreateMenu: function(view, record, item, index, e) {
		var me = this,
			operationMenu = me.createCellItem();
		e.preventDefault();
		if(record.raw) {
			if(record.raw.leaf == true) {
				operationMenu.child('#modify').setVisible(true);
				operationMenu.child('#delete').setVisible(true);
			} else {
				operationMenu.child('#modify').setVisible(true);
				operationMenu.child('#delete').setVisible(false);
			}
		} else{
			operationMenu.child('#modify').setVisible(false);
			operationMenu.child('#delete').setVisible(false);
		}
		operationMenu.showAt(e.getXY());
		me.selectNodeRecord = record;
	},
	getOperationUrl: function(actionType) {
		var operationUrl = null;
		if (actionType === 'add') {
			operationUrl = dict.realPath('addDictionaryType');
		} else if (actionType === 'update') {
			operationUrl = dict.realPath('modifyDictionaryType');
		}
		return operationUrl;
	},
	getDictionaryTypeWin: function(title, actionType) {
		var me = this,
			dictionaryTypeForm;
		if (!me.dictionaryTypeWin) {
			me.dictionaryTypeWin = Ext.create('Deppon.dict.DictionaryTypeWin');
		}
		dictionaryTypeForm = me.dictionaryTypeWin.down('form').getForm()
		me.dictionaryTypeWin.setOperationUrl(me.getOperationUrl(actionType));
		me.dictionaryTypeWin.setTitle(title);
		if (actionType) {
			if (actionType === "add") {
				dictionaryTypeForm.findField('typeAlias').setReadOnly(false);
				me.dictionaryTypeWin.down('form').getForm().reset();
			} else if (actionType === "update") {
				dictionaryTypeForm.findField('typeAlias').setReadOnly(true);
			}
		}
		return me.dictionaryTypeWin;
	},
	showAddNodeWin: function(record) {
		typeWin = this.getDictionaryTypeWin(dict.i18n('dpap.dict.form.title.type.add'), "add");
		typeWin.getDictionaryTypeModel().set('parentCode', record.data.id);
		typeWin.show();
	},
	onAddNode: function() {
		var me = this,
			record = me.getSelectNodeRecord(),
			typeWin;
		if(!Ext.isEmpty(record.raw)) {
			Ext.Ajax.request({
				url: dict.realPath('hasDictionaryData'),
				params: {
					code: record.get('id')
				},
				success: function(response) {
					var josn = Ext.JSON.decode(response.responseText);
					if(Ext.isEmpty(josn.msg)) {
						me.showAddNodeWin(record);
					} else {
				    	Ext.Msg.confirm(dict.i18n('dpap.dict.msg.notice'), josn.msg, function(btn) {
				    		if(btn == "yes") {
				    			me.showAddNodeWin(record);
				    		}
				    	});
					}
				},
				exception : function(response) {
					var json = Ext.decode(response.responseText); 
					Ext.ux.Toast.msg(
							dict.i18n('dpap.dict.msg.notice'), 
							json.msg, 'error');
			    }
			});
			return;
		}
		me.showAddNodeWin(record);
	},
	onModifyNode: function() {
		var me = this,
			record = me.getSelectNodeRecord(),
			formModel, dictWin;
		if(Ext.isEmpty(record.raw)) {
			return;
		}
		formModel = Ext.create('Deppon.dict.DictionaryTypeModel', record.raw.entity);
		dictWin = me.getDictionaryTypeWin(dict.i18n('dpap.dict.form.title.type.edit'), "update");
		dictWin.loadDictionaryType(formModel);
		dictWin.show();
	},
	onDeleteNode: function() {
		var me = this;
    	Ext.Msg.confirm(dict.i18n('dpap.dict.msg.notice'), dict.i18n('dpap.dict.msg.confirm.type.delete'), function(btn) {
    		if(btn == "yes") {
    			var record = me.getSelectNodeRecord();
    				id = record.get('id');
    			Ext.Ajax.request({
    				url: dict.realPath('abolishDictionaryType'),
    				params: {
    					'dictionaryVo.dictionaryTypeEntity.id': id,
    					'dictionaryVo.dictionaryTypeEntity.parentCode': record.get('parentId')
    				},
    				success: function(response) {
    					Ext.getCmp('Deppon.dict.DictionaryTypeTreePanel_ID').store.load();
    					/*Ext.getCmp('Deppon.dict.DictionaryGrid_ID').store.load({
    						params: {
    							code: id
    						}
    					});*/
    					dict.dictType = id;
    					Ext.getCmp('Deppon.dict.DictionaryGrid_ID').store.load();
    				}
    			});
    		}
    	});
	}
});

Ext.onReady(function() {
	Ext.tip.QuickTipManager.init();
	
	var dictionaryGrid = Ext.create('Deppon.dict.DictionaryGrid', {
		id: 'Deppon.dict.DictionaryGrid_ID',
		columnWidth: 0.7
	});
	var dictionaryTypeTreePanel = Ext.create('Deppon.dict.DictionaryTypeTreePanel', {
		id: 'Deppon.dict.DictionaryTypeTreePanel_ID',
		columnWidth: 0.3
	});
	
	var content = Ext.create('Ext.panel.Panel', {
		id:'T_dict-index_content',
		cls : "panelContentNToolbar",
		bodyCls : 'panelContentNToolbar-body',
		layout: 'column',
		items: [ dictionaryTypeTreePanel, dictionaryGrid ]
	});
	
	Ext.getCmp('T_dict-index').add(content);
	
});
