/** 新增窗体标识 */
authorization.functions.STATE_ADD = 'add';
/** 修改窗体标识 */
authorization.functions.STATE_UPDATE = 'update';
/** 左边树的ROOT_ID */
authorization.functions.ROOT_ID = '0';
/** 检查权限 */
authorization.functions.CHECKABLE_YES = "Y";
authorization.functions.CHECKABLE_NO = "N";
/** 权限类型 */
authorization.functions.DPAP_AUTH_FUNCTIONTYPE = "DPAP_AUTH_FUNCTIONTYPE";
/** 此模块主页面的ID */
authorization.functions.CONTENT_ID = 'T_authorization-functionsIndex_content';

/** 是否检查权限Store */
authorization.functions.checkableStore = Ext.create('Ext.data.Store', {
    fields: ['name', 'value'],
    data : [
        {"name":authorization.functions.i18n('dpap.authorization.yes'), "value": authorization.functions.CHECKABLE_YES},
        {"name":authorization.functions.i18n('dpap.authorization.no'), "value": authorization.functions.CHECKABLE_NO}
    ]
});

/** 通过功能编码删除功能 */
authorization.functions.deleteFunction = function(functionData, successHanlder) {
	Ext.Msg.show({
	    title: authorization.functions.i18n('dpap.authorization.msginfo'),
	    msg: authorization.functions.i18n('dpap.authorization.confirmMessage'),
	    buttons: Ext.Msg.YESNO,
	    icon: Ext.Msg.QUESTION,
	    fn: function(buttonId) {
	    	if (buttonId != 'yes') {
	    		return;
	    	}
	    	
	    	var functionVo = new Object();
	    	functionVo.functionCode = functionData.functionCode;
	    	
			Ext.Ajax.request({
			    url : authorization.realPath('deleteFunction'),
			    jsonData: functionVo,
			    success : function(response) {
			    	successHanlder(response);
			    },
				exception : function(response) {
					var json = Ext.decode(response.responseText); 
					Ext.ux.Toast.msg(
							authorization.functions.i18n('dpap.authorization.msginfo'), 
							json.msg, 'error');
			    }
			}); 
	    }
	});
};

/** 权限功能的Model */
Ext.define('Dpap.authorization.FunctionModel', {
	extend : 'Ext.data.Model',
	fields : [{
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
		name : 'functionDesc'
	}, {
		name : 'systemType'
	}, {
		name : 'parentName'
	}]
});

/** 定义"权限-新增和修改表单" */
Ext.define('Dpap.authorization.EditFunctionForm',{
	extend: 'Ext.form.Panel', 
	frame: true,
	operatorType: authorization.functions.STATE_ADD,
	getOperatorType: function() {
		return this.operatorType;
	},
	/** 根据新增还是修改按钮改变窗体的设置值 */
	setOperatorType: function(state, record) {
		this.operatorType = state;
		var form = this.getForm();
		form.reset();
		
		var functionSeletorModel = Ext.create('Dpap.commonSelector.FunctionModel');
		if (state == authorization.functions.STATE_ADD) {
			this.setFormFieldsReadOnly(false);
			var functionTree = Ext.getCmp(authorization.functions.CONTENT_ID).getFunctionTree(),
				selectionModel = functionTree.getSelectionModel().getSelection(),
				
			record = Ext.create('Dpap.authorization.FunctionModel');
			
			if (selectionModel.length > 0) {
				functionSeletorModel.data.functionCode = (selectionModel[0]).data.id;
				functionSeletorModel.data.functionName = (selectionModel[0]).data.text;
			} else {
				functionSeletorModel.data.functionCode = authorization.functions.ROOT_ID;
				functionSeletorModel.data.functionName = authorization.functions.i18n('dpap.authorization.Function.app');
			}
			
			record.data.checkable = authorization.functions.CHECKABLE_YES;
		} else {
			this.setFormFieldsReadOnly(true);
			functionSeletorModel.data.functionCode = record.data.parentCode;
			functionSeletorModel.data.functionName = record.data.parentName;
		}
		record.data.parentCode = functionSeletorModel;
		
		form.loadRecord(record);
	},
	setFormFieldsReadOnly: function(flag) {
		var form = this.getForm();
		form.findField('functionCode').setReadOnly(flag);
		form.findField('functionType').setReadOnly(flag);
	},
	defaults:{
		margin : '5 20 5 10',
		labelWidth : 130,
		colspan : 1,
		allowBlank: false,
	    validateOnBlur: true,
	    validateOnChange: false
	},
	defaultType:'textfield',
	layout:{
		type : 'table',
		columns : 2
	},
	remoteValidate: function(value, url) {
		var me = this;
		if (Ext.isEmpty(value) || me.getOperatorType() != authorization.functions.STATE_ADD) {
    		return true;
    	}
    	
    	var params = {'functionVo.functionCode': value}; 
    	var resultFlag = 'false';
        Ext.Ajax.request({
            url : authorization.realPath(url),
            params: params,
			async: false,
            success : function(response) {
            	var json = Ext.decode(response.responseText);
            	// 存在
            	if (json.flag) {
            		resultFlag = json.data;
            	} else {
            		resultFlag = true;
            	}
            },
            exception : function(response) {
            	var json = Ext.decode(response.responseText); 
	        	Ext.ux.Toast.msg(
	        			authorization.functions.i18n('dpap.authorization.msginfo'), 
	        			json.msg, 'error');
            }
        });
        
        return resultFlag;
	},
	initComponent: function() {
		var me = this;
		me.items = [{
			name: 'functionCode',
		    fieldLabel: authorization.functions.i18n('dpap.authorization.Function.functionCode'),
		    maxLength:50
		}, {
			xtype:'dictcombo',
		 	dictType: authorization.functions.DPAP_AUTH_FUNCTIONTYPE,
			name: 'functionType',
			fieldLabel: authorization.functions.i18n('dpap.authorization.Function.functionType'),
		    editable: false
		}, {
			name: 'functionName',
		    fieldLabel: authorization.functions.i18n('dpap.authorization.Function.functionName'),
		    maxLength:100
		}, {
			name: 'uri',
		    fieldLabel: authorization.functions.i18n('dpap.authorization.Function.URI'),
		    maxLength:500
		}, {
			name:'parentCode',
			xtype:'commonfunctionselector',
			fieldLabel : authorization.functions.i18n('dpap.authorization.Function.parentName'),
			typeMode: 5
		}, {
			xtype: 'numberfield',
			name: 'displayOrder',
		    fieldLabel: authorization.functions.i18n('dpap.authorization.Function.displayOrder'),
		    minValue: 1,
	        maxValue: 999,
	        value: 1
		}, {
			name: 'functionDesc',
		    fieldLabel: authorization.functions.i18n('dpap.authorization.Function.functionDesc'),
		    maxLength:1000,
		    allowBlank: true
		}, {
			xtype: 'checkboxfield',
			name: 'checkable',
			boxLabel: authorization.functions.i18n('dpap.authorization.Function.check'),
		    inputValue: 'Y',
		    checked:true
		}];
		me.callParent();
	}
});

/** 定义权限-新增和修改窗体 */
Ext.define('Dpap.authorization.FunctionInfoWindow', {
	extend: 'Ext.window.Window',
	title: authorization.functions.i18n('dpap.authorization.add') 
		+ authorization.functions.i18n('dpap.authorization.Function.info'),
	width: 700,
	modal: true,
	closeAction: 'hide',
	// 表单
	editFunctionForm: null,
	getEditFunctionForm: function(){
		if (Ext.isEmpty(this.editFunctionForm)) {
			this.editFunctionForm = Ext.create("Dpap.authorization.EditFunctionForm");
		}
		
		return this.editFunctionForm;
	},
	// 取消按钮
	cancelButton : null,
	getCancelButton:function(){
		var me = this;
		if (Ext.isEmpty(this.cancelButton)) {
			this.cancelButton = Ext.create('Ext.button.Button',{
				text: authorization.functions.i18n('dpap.authorization.cancel'),
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
	        url : authorization.realPath(action),
	        jsonData: params,
	        success : function(response) {
	        	Ext.ux.Toast.msg(
	        			authorization.functions.i18n('dpap.authorization.msginfo'), 
	        			authorization.functions.i18n('dpap.authorization.saveSuccess'));
	        	me.hide();
	        	
	        	// 刷新树，并展开
	        	var treePanel = Ext.getCmp(authorization.functions.CONTENT_ID).getFunctionTree(),
	        		result = Ext.decode(response.responseText);
	        	treePanel.getStore().load({
	        	    scope: this,
	        	    node : treePanel.getStore().getNodeById(authorization.functions.ROOT_ID),
	        	    callback: function(records, operation, success) {
	        	    	treePanel.expandPath(result.data.parentCode.functionSeq, 'id', '/', function(success, lastNode){
	        				if (success) {
	        					var result = Ext.decode(response.responseText);
	        					treePanel.getSelectionModel().select(treePanel.getStore().getNodeById(result.functionEntity.functionCode));
	        				}
	        			});	   
	        	    }
	        	});
	        },
	        exception : function(response) {
	        	var json = Ext.decode(response.responseText); 
	        	Ext.ux.Toast.msg(
	        			authorization.functions.i18n('dpap.authorization.msginfo'), 
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
				text: authorization.functions.i18n('dpap.authorization.save'),
				handler: function() {
					var form = me.getEditFunctionForm().getForm();
					if (!form.isValid()) {
						return;
					}
					
					if (me.getEditFunctionForm().getOperatorType() == authorization.functions.STATE_ADD) {
						me.submitFunction(form, 'insertFunction');
					} else {
						me.submitFunction(form, 'updateFunction');
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
			me.getEditFunctionForm()
	    ];
		me.buttons = [
			me.getCancelButton(),'->',
			me.getSaveButton()
		];
		me.callParent([cfg]);
	}
});

/** 定义"页面元素-新增和修改表单" */
Ext.define('Dpap.authorization.EditPageFunctionForm',{
	extend: 'Ext.form.Panel', 
	frame: true,
	defaultType : 'textfield',
	/** 操作类型，新增OR修改 */
	operatorType: authorization.functions.STATE_ADD,
	getOperatorType: function() {
		return this.operatorType;
	},
	/** 根据新增还是修改按钮改变窗体的设置值 */
	setOperatorType: function(state, record) {
		this.operatorType = state;
		var form = this.getForm();
		form.reset();
		
		if (state == authorization.functions.STATE_ADD) {
			this.setFormFieldsReadOnly(false);
			record = Ext.create('Dpap.authorization.FunctionModel');
		} else {
			this.setFormFieldsReadOnly(true);
		}
		
		form.loadRecord(record);
	},
	setFormFieldsReadOnly: function(flag) {
		var form = this.getForm();
		form.findField('functionCode').setReadOnly(flag);
	},
	defaults:{
		margin : '5 20 5 10',
		labelWidth : 130,
		colspan : 1,
		allowBlank: false,
	    validateOnBlur: true,
	    validateOnChange: false
	},
	defaultType:'textfield',
	layout:{
		type : 'table',
		columns : 2
	},
	initComponent: function() {
		var me = this;
		me.items = [{
			name: 'functionCode',
		    fieldLabel: authorization.functions.i18n('dpap.authorization.Function.functionCode'),
		    maxLength:50
		}, {
			name: 'functionName',
		    fieldLabel: authorization.functions.i18n('dpap.authorization.Function.functionName'),
		    maxLength:100
		}, {
			name: 'uri',
		    fieldLabel: authorization.functions.i18n('dpap.authorization.Function.URI'),
		    maxLength:500
		}, {
			name: 'functionDesc',
		    fieldLabel: authorization.functions.i18n('dpap.authorization.Function.functionDesc'),
		    maxLength:1000,
		    allowBlank: true
		}, {
			xtype: 'checkboxfield',
			name: 'checkable',
			boxLabel: authorization.functions.i18n('dpap.authorization.Function.check'),
		    inputValue: 'Y',
		    checked:true
		}];
		me.callParent();
	}
});

/** 定义页面-新增和修改窗体 */
Ext.define('Dpap.authorization.PageFunctionWindow', {
	extend: 'Ext.window.Window',
	title: authorization.functions.i18n('dpap.authorization.add') 
		+ authorization.functions.i18n('dpap.authorization.Function.pageElement'),
	width: 700,
	modal: true,
	closeAction: 'hide',
	// 表单
	editFunctionForm: null,
	getEditFunctionForm: function(){
		if (Ext.isEmpty(this.editFunctionForm)) {
			this.editFunctionForm = Ext.create("Dpap.authorization.EditPageFunctionForm");
		}
		
		return this.editFunctionForm;
	},
	// 取消按钮
	cancelButton : null,
	getCancelButton:function(){
		var me = this;
		if (Ext.isEmpty(this.cancelButton)) {
			this.cancelButton = Ext.create('Ext.button.Button',{
				text: authorization.functions.i18n('dpap.authorization.cancel'),
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

		var parentRecord = Ext.getCmp(authorization.functions.CONTENT_ID).getFunctionForm().getRecord();
		formValue.parentCode = parentRecord.data.functionCode;
		formValue.functionType = 'BUTTON';
		formValue.displayOrder = '1';
		
		var params = formValue;
	    Ext.Ajax.request({
	        url : authorization.realPath(action),
	        jsonData: params,
	        success : function(response) {
	        	Ext.ux.Toast.msg(
	        			authorization.functions.i18n('dpap.authorization.msginfo'), 
	        			authorization.functions.i18n('dpap.authorization.saveSuccess'));
	        	me.hide();
	        	// 刷新表格
	        	var pageElementGrid = Ext.getCmp(authorization.functions.CONTENT_ID).getPageElementGrid();
    			pageElementGrid.loadFunctionStore(parentRecord.data.functionCode);
	        },
	        exception : function(response) {
	        	var json = Ext.decode(response.responseText); 
	        	Ext.ux.Toast.msg(
	        			authorization.functions.i18n('dpap.authorization.msginfo'), 
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
				text: authorization.functions.i18n('dpap.authorization.save'),
				handler: function() {
					var form = me.getEditFunctionForm().getForm();
					if (!form.isValid()) {
						return;
					}
					
					if (me.getEditFunctionForm().getOperatorType() == authorization.functions.STATE_ADD) {
						me.submitFunction(form, 'insertFunction');
					} else {
						me.submitFunction(form, 'updateFunction');
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
			me.getEditFunctionForm()
	    ];
		me.buttons = [
			me.getCancelButton(), '->', me.getSaveButton()
		];
		me.callParent([cfg]);
	}
});

/**
 * 定义功能树的store
 */
Ext.define('Dpap.Authorization.FunctionTreeStore', {
	extend : 'Ext.data.TreeStore',
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : authorization.realPath('loadManagerFunctionTree')
	},
	root : {
		id : authorization.functions.ROOT_ID,
		text : authorization.functions.i18n('dpap.authorization.Function.app'),
		expanded : true
	}
});

/**
 * 定义功能树的面板
 */
Ext.define('Dpap.authorization.FunctionTreePanel',{
	extend : 'Ext.tree.Panel',
	useArrows: true,
	frame: true,
	title: authorization.functions.i18n('dpap.authorization.functionTree'),
	queryText: null,
	getQueryText: function() {
		if (Ext.isEmpty(this.queryText)) {
			this.queryText = Ext.create('Ext.form.field.Text',{
				fieldLabel : '',
				name : 'functionCode',
				width : 150,
				maxLength: 50
			});
		}
		
		return this.queryText;
	},
	expandNodes: [],
	expandPaths: function(pathArray) {
		var me = this;
		me.collapseAll();
		var path;
		for (var i = 0; i < pathArray.length; i++) {
			path = pathArray[i];
			me.expandPath(path, 'id', '/', function(success, lastNode){
				if (success) {
					me.expandNodes.push(lastNode);
				}
			});	    						
		}
	},
	onRefreshTreeNodes: function(treeStore,parentId){
		var node = treeStore.getNodeById(parentId); 
		treeStore.load({
			node : node
		});
	},
	/**
	 * 刷新树的节点
	 */
	reFresh : function() {
		var selects = this.getSelectionModel().getSelection();
		if(selects.length==0){
			return;
		}
		
		var record = selects[0];
		// 如果是root节点
		if (record.data.root) {
			this.onRefreshTreeNodes(this.getStore(), record.data.id);
			return;
		}
		
		if(record.raw.entity != null){
			var functionCode = record.raw.entity.functionCode;
			this.onRefreshTreeNodes(this.getStore(), functionCode);
		}
	},
	/**
	 * 树的节点左键单击事件
	 * 
	 * @param {}
	 *            view
	 * @param {}
	 *            _node 节点
	 */
	treeLeftKeyAction : function(node, record, index, e) {
//		e.preventDefault();// 阻止浏览器默认行为处理事件
		var content = Ext.getCmp(authorization.functions.CONTENT_ID);
		var functionForm = content.getFunctionForm();
		var pageElementGrid = content.getPageElementGrid();
		// ROOT节点直接禁用按钮
		if(record.data.root){
			functionForm.getForm().reset();
			functionForm.disabledButtons(true);
			pageElementGrid.disabledButtons(true);
			return;
		}
		
		functionForm.disabledButtons(false);
		functionForm.loadFormDataFromTree(record);
		// 只有是模块功能的时候,才进行模块功能下的页面元素功能项的加载
		if (record.raw.entity.functionType == 'MENU') {
			pageElementGrid.disabledButtons(false);
			pageElementGrid.loadFunctionStore(record.raw.entity.functionCode);
		} else {
			pageElementGrid.disabledButtons(true);
			pageElementGrid.getStore().removeAll();
		}
	},
	constructor : function(config) {
		var me = this, 
			cfg = Ext.apply({}, config);
		me.store = Ext.create('Dpap.Authorization.FunctionTreeStore');
		// 监听事件
		me.listeners = {
			select : me.treeLeftKeyAction,
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
			}
		};
		me.tbar = [ me.getQueryText(), {
			text : authorization.functions.i18n('dpap.authorization.find'),
			plugins: {
		        ptype: 'buttondisabledplugin',
		        seconds: 2
		    },
			handler : function() {
				var textValue = me.getQueryText().getValue();
				textValue = Ext.String.trim(textValue);
				
				var params = {'functionVo.functionCode': textValue}; 
		        Ext.Ajax.request({
		            url : authorization.realPath('findFunctionSeqs'),
		            params: params,
					async: false,
		            success : function(response) {
		            	var result = Ext.decode(response.responseText);
		            	me.expandPaths(result.data);
		            },
		            exception : function(response) {
		            	var json = Ext.decode(response.responseText); 
			        	Ext.ux.Toast.msg(
			        			authorization.functions.i18n('dpap.authorization.msginfo'), 
			        			json.msg, 'error');
		            }
		        });
			}
		}, {
			text : authorization.functions.i18n('dpap.authorization.refresh'),
			handler : me.reFresh,
			scope : this
		} ];
		me.callParent([cfg]);
	}
});

/**
 * 定义右上功能表单详情
 */
Ext.define('Dpap.authorization.FunctionForm', {
	extend : 'Ext.form.Panel',
	title: authorization.functions.i18n('dpap.authorization.Function.info'),
    frame: true,
    defaultType: 'textfield',
    defaults: {
    	columnWidth:.5,
    	margin:'5 10 5 10',
    	readOnly: true
    },
	layout:'column',
	items: [{
			name: 'functionCode',
	        fieldLabel: authorization.functions.i18n('dpap.authorization.Function.functionCode')
		},{
			name: 'functionName',
	        fieldLabel: authorization.functions.i18n('dpap.authorization.Function.functionName')
		},{
			name: 'uri',
	        fieldLabel: authorization.functions.i18n('dpap.authorization.Function.URI')
		},{
	        name: 'parentName',
	        fieldLabel: authorization.functions.i18n('dpap.authorization.Function.parentName')
		},{
			xtype: "combo",
			name: 'checkable',
			fieldLabel: authorization.functions.i18n('dpap.authorization.Function.check'),
			store: authorization.functions.checkableStore,
		    queryMode: 'local',
		    displayField: 'name',
		    valueField: 'value'
		},{
			xtype:'dictcombo',
		 	dictType: authorization.functions.DPAP_AUTH_FUNCTIONTYPE,
			name: 'functionType',
			fieldLabel: authorization.functions.i18n('dpap.authorization.Function.functionType'),
		    editable: false
		},{
			name: 'displayOrder',
			fieldLabel: authorization.functions.i18n('dpap.authorization.Function.displayOrder')
		},{
			name: 'functionDesc',
	        fieldLabel: authorization.functions.i18n('dpap.authorization.Function.functionDesc')
	}],
    functionWindow: null,
	getFunctionWindow: function(){
		if (Ext.isEmpty(this.functionWindow)) {
			this.functionWindow = Ext.create('Dpap.authorization.FunctionInfoWindow');			
		}
		
		return this.functionWindow;
	},
	disabledButtons: function(flag) {
		this.getUpdateButton().setDisabled(flag);
		this.getDeleteButton().setDisabled(flag);
	},
	// 新增按钮
	addButton: null,
	getAddButton: function() {
		var me = this;
		if(Ext.isEmpty(me.addButton)){
			me.addButton = Ext.create('Ext.Button', {
				text : authorization.functions.i18n('dpap.authorization.add'),
				handler : function() {
					var window = me.getFunctionWindow();
					window.setTitle(
							authorization.functions.i18n('dpap.authorization.add') 
							+ authorization.functions.i18n('dpap.authorization.Function.info'));
					window.getEditFunctionForm().setOperatorType(authorization.functions.STATE_ADD);
					window.show();
				}
			});
		}
		return this.addButton;

	},
	// 修改按钮
	updateButton: null,
	getUpdateButton: function() {
		var me = this;
		if(Ext.isEmpty(me.updateButton)){
			me.updateButton = Ext.create('Ext.Button', {
				text : authorization.functions.i18n('dpap.authorization.update'),
				disabled: true,
				handler : function() {
					var window = me.getFunctionWindow(),
						record = me.getRecord();
					window.setTitle(
							authorization.functions.i18n('dpap.authorization.update') 
							+ authorization.functions.i18n('dpap.authorization.Function.info'));
					window.getEditFunctionForm().setOperatorType(authorization.functions.STATE_UPDATE, record);
					window.show();
				}
			});
		}
		return this.updateButton;

	},
	// 删除按钮
	deleteButton: null,
	getDeleteButton: function() {
		var me = this;
		if (Ext.isEmpty(me.deleteButton)) {
			me.deleteButton = Ext.create('Ext.Button', {
				text : authorization.functions.i18n('dpap.authorization.delete'),
				disabled: true,
				handler : function() {
		    		var functionData = me.getRecord().getData();
		    		authorization.functions.deleteFunction(functionData, function(){
				    	var treePanel = Ext.getCmp(authorization.functions.CONTENT_ID).getFunctionTree();
				    	treePanel.onRefreshTreeNodes(treePanel.getStore(), functionData.parentCode);
				    	Ext.ux.Toast.msg(
				    			authorization.functions.i18n('dpap.authorization.msginfo'), 
				    			authorization.functions.i18n('dpap.authorization.deleteSuccess'));
		    		});
				}
			});
		}
		
		return this.deleteButton;
	},
	/**
	 * 从功能树中加载数据到form表单中
	 */
	loadFormDataFromTree : function(record) {
		var me = this,
			form = me.getForm(),
			functionType = form.findField('functionType');
		// 如果父节点为空，那么当前节点就是功能树的根节点，不可以做修改
		if (record.raw.entity.parentCode == null) {
			return;
		}
		
		var functionModel = Ext.create('Dpap.authorization.FunctionModel', record.raw.entity);
		functionModel.data.parentCode = functionModel.data.parentCode.functionCode;
		functionModel.data.parentName = record.raw.entity.parentCode.functionName;
		me.loadRecord(functionModel);
		if (record.raw.entity.parentCode != null) {
			form.findField("parentName")
				.setValue(record.raw.entity.parentCode.functionName);
		}
	},
	constructor : function(config) {
		var cfg = Ext.apply({}, config);
		this.tbar = [this.getAddButton(), this.getUpdateButton(), this.getDeleteButton()];
		this.callParent([cfg]); 
	}
});

/**
 * FunctionPageElementStore
 */
Ext.define('Dpap.authorization.FunctionPageElementStore',{
	extend: 'Ext.data.Store',
	model : 'Dpap.authorization.FunctionModel',
	proxy : {
		url : base+'/authorization/findFunctionPageElementByParentCode',
		type : 'ajax',
		reader : {
			type : 'json',
			root : 'data'
		}
	}
});

/**
 * 模块功能页面元素的权限信息
 */
Ext.define('Dpap.authorization.FunctionPageElementGridPanel', {
	extend: 'Ext.grid.Panel',
	title : authorization.functions.i18n('dpap.authorization.Function.pageElement'),
	frame: true,
	sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
	selType : "rowmodel", // 选择类型设置为：行选择
	columns : [{
		xtype:'actioncolumn',
		text: authorization.functions.i18n('dpap.authorization.operate'),
        width:50,
        items: [{
        	iconCls: 'deppon_icons_edit',
            tooltip: authorization.functions.i18n('dpap.authorization.update'),
            handler: function(grid, rowIndex, colIndex) {
                var rec = grid.getStore().getAt(rowIndex);
                var pageElementGrid = Ext.getCmp(authorization.functions.CONTENT_ID).getPageElementGrid();
                var window = pageElementGrid.getFunctionWindow();
				window.setTitle(
						authorization.functions.i18n('dpap.authorization.update') 
						+ authorization.functions.i18n('dpap.authorization.Function.pageElement'));
				window.getEditFunctionForm().setOperatorType(authorization.functions.STATE_UPDATE, rec);
				window.show();
                
            }
        }, {
        	iconCls: 'deppon_icons_delete',
            tooltip: authorization.functions.i18n('dpap.authorization.delete'),
            handler: function(grid, rowIndex, colIndex) {
                var rec = grid.getStore().getAt(rowIndex);
                var functionData = rec.getData();
	    		authorization.functions.deleteFunction(functionData, function(){
	    			var pageElementGrid = Ext.getCmp(authorization.functions.CONTENT_ID).getPageElementGrid();
	    			pageElementGrid.loadFunctionStore(functionData.parentCode.functionCode);
			    	Ext.ux.Toast.msg(
			    			authorization.functions.i18n('dpap.authorization.msginfo'), 
			    			authorization.functions.i18n('dpap.authorization.deleteSuccess'));
	    		});
            }
        }]
	}, {
		id : 'id',
		hidden : true,
		dataIndex : 'id'
	}, {
		text : authorization.functions.i18n('dpap.authorization.Function.functionCode'),
		width: 100,
		dataIndex : 'functionCode'
	}, {
		text : authorization.functions.i18n('dpap.authorization.Function.functionName'),
		width:60,
		dataIndex : 'functionName'
	}, {
		text : authorization.functions.i18n('dpap.authorization.Function.checkable'),
		width: 60,
		dataIndex : 'checkable',
		renderer:  function(value) {
			if(value == null){
				return '';
			}
			
		    if (value == authorization.functions.CHECKABLE_YES) {
		        return authorization.functions.i18n('dpap.authorization.yes');
		    } else {
		        return authorization.functions.i18n('dpap.authorization.no');
		    }
		}
	}, {
		text : authorization.functions.i18n('dpap.authorization.Function.pageElementURI'),
		width:210,
		dataIndex : 'uri'
			
	}, {
		text : authorization.functions.i18n('dpap.authorization.Function.functionDesc'),
		flex: 1,
		dataIndex : 'functionDesc'
	} ],
	disabledButtons: function(flag) {
		this.getAddButton().setDisabled(flag);
	},
	// 编辑窗体
    functionWindow: null,
	getFunctionWindow: function(){
		if (Ext.isEmpty(this.functionWindow)) {
			this.functionWindow = Ext.create('Dpap.authorization.PageFunctionWindow');			
		}
		
		return this.functionWindow;
	},
	// 新增按钮
	addButton: null,
	getAddButton: function() {
		var me = this;
		if(Ext.isEmpty(me.addButton)){
			me.addButton = Ext.create('Ext.Button', {
				text : authorization.functions.i18n('dpap.authorization.add'),
				disabled: true,
				handler : function() {
					var window = me.getFunctionWindow();
					window.setTitle(
							authorization.functions.i18n('dpap.authorization.add') 
							+ authorization.functions.i18n('dpap.authorization.Function.pageElement'));
					window.getEditFunctionForm().setOperatorType(authorization.functions.STATE_ADD);
					window.show();
				}
			});
		}
		return this.addButton;

	},
	loadFunctionStore: function(parentCode) {
		this.getStore().load({
			params : {
				'functionVo.parentCode' : parentCode
			}
		});
	},
	constructor : function(config) {
		var me = this;
		me.store = Ext.create('Dpap.authorization.FunctionPageElementStore');
		me.tbar = [me.getAddButton()];
		var cfg = Ext.apply({}, config);
		me.callParent([cfg]);
	}
});

/**
 * @description 权限管理主页
 */
Ext.onReady(function() {
	Ext.QuickTips.init();
	if (Ext.getCmp(authorization.functions.CONTENT_ID)) {
		return;
	}
	// 左边的树对象
	var functionTree = Ext.create('Dpap.authorization.FunctionTreePanel', {
		height: 717,
		columnWidth: .3
	});
	// 右上的功能信息
	var functionForm = Ext.create('Dpap.authorization.FunctionForm');
	// 右下的页面元素列表
	var pageElementGrid = Ext.create('Dpap.authorization.FunctionPageElementGridPanel');
	
	var content = Ext.create('Ext.panel.Panel', {
		id : authorization.functions.CONTENT_ID,
		cls : "panelContentNToolbar",
		bodyCls : 'panelContentNToolbar-body',
		layout : 'column',
		getFunctionForm : function() {
			return functionForm;
		},
		getPageElementGrid : function() {
			return pageElementGrid;
		},
		getFunctionTree : function() {
			return functionTree;
		},
		items : [ functionTree, {
			xtype : 'container',
			margin : '0 0 0 15',
			columnWidth: .7,
			items : [ functionForm, pageElementGrid ]
		}]
//		renderTo : 'T_authorization-functionsIndex-body'
	});
	Ext.getCmp('T_authorization-functionsIndex').add(content);
	
});