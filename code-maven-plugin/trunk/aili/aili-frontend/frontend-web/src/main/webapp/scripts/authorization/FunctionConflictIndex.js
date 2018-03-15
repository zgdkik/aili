//==============================================================================================
// 权限互斥新增的窗口
//==============================================================================================
Ext.define('Dpap.authorization.conflict.UpdateConflictEntityWindow',{
	extend: 'Ext.window.Window',
	title: authorization.conflict.i18n('dpap.authorization.conflict.gui.add'),//'新增/修改权限互斥',
    width: 350,
    modal:true,
	closeAction:'hide',
	addResourceConflictForm:null,
	getAddResourceConflictForm:function(){
		if(this.addResourceConflictForm == null){
			this.addResourceConflictForm = Ext.create('Dpap.authorization.conflict.AddResourceConflictForm');
		}
		return this.addResourceConflictForm;
	},
	constructor: function(config){
		var me = this,
		cfg = Ext.apply({}, config);
		me.items = [
		    me.getAddResourceConflictForm()
		];
		me.callParent([cfg]);
	}
});
//// 权限互斥新增表单
Ext.define('Dpap.authorization.conflict.AddResourceConflictForm',{
	extend: 'Ext.form.Panel',
	defaultType : 'textfield',
	defaults: {
		readOnly : false,
		margin:'5 5 5 10',
		anchor: '90%',
		labelWidth: 60
	},
    //重置按钮
    resetButton : null,
    getResetButton : function(){
    	var me = this;
        if (Ext.isEmpty(me.resetButton)) {
        	me.resetButton = Ext.create('Ext.button.Button',{
				name: 'reset',
				text:authorization.conflict.i18n('dpap.authorization.conflict.gui.reset'),// '重置',
				handler: function() {
					me.getForm().reset();	
				}
			});
        }
        return this.resetButton;
    
    },
    //保存按钮
    saveButton : null,
    getSaveButton : function(){
    	var me = this;
        if(this.saveButton==null){
            this.saveButton = Ext.create('Ext.button.Button',{
				cls:'yellow_button',
				text:authorization.conflict.i18n('dpap.authorization.conflict.gui.save'),//  '保存',
				handler: function() {
					var form = me.getForm(), 
						window = me.up('window'),
						model = Ext.create('Dpap.authorization.conflict.conflictModel', form.getValues());
					// 请求合法性验证
					if(!form.isValid()){
						return;
					}
					if(model.get('firstCode') == model.get('secondCode')){
						Ext.ux.Toast.msg(authorization.conflict.i18n('dpap.authorization.conflict.gui.fail.msg')//'信息（失败）提示'
						, authorization.conflict.i18n('dpap.authorization.conflict.gui.function.equal')//'同一种权限不能互斥！'
						, 'error', 1000);
						return;
					}
					var params = {
						'functionConflictVo':{
							'functionConflictEntity': model.data
						}
					};
					var params = model.data
					//发送新增结点的Ajax请求.
					Ext.Ajax.request({
						url: authorization.realPath('addFunctionConflict'),
						jsonData: params,
						//作废成功
						success : function(response) {
			                var json = Ext.decode(response.responseText);
							authorization.conflict.pagingBar.moveFirst();
							window.hide();
							Ext.ux.Toast.msg(authorization.conflict.i18n('dpap.authorization.conflict.gui.success.msg') //'信息（成功）提示'
							, json.msg, 'ok', 1000);
						
			            },
			            //保存失败
		                exception : function(response) {
		                    var json = Ext.decode(response.responseText);
		                    Ext.ux.Toast.msg(authorization.conflict.i18n('dpap.authorization.conflict.gui.fail.msg') //'信息（失败）提示'
		                    , json.msg, 'error', 1000);
		                    window.close();
		                }
					});
					// 将表单中的数据清空：
					form.reset();
					window.close();
				}
            });
        }
        return this.saveButton;
    },
	initComponent: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.items = [{
			xtype:'commonroleselector',
			name:'roleCode',
			fieldLabel :authorization.conflict.i18n('dpap.authorization.conflict.gui.function.role') ,// '权限一',
		    allowBlank : false
	   },{
	   		xtype:'commonfunctionselector',
			name:'firstCode',
			fieldLabel : authorization.conflict.i18n('dpap.authorization.conflict.gui.function.one') ,//'权限一',
		    allowBlank : false
	    },{
		    xtype:'commonfunctionselector',
			name:'secondCode',
			fieldLabel : authorization.conflict.i18n('dpap.authorization.conflict.gui.function.two') ,//,'权限二',
		    allowBlank : false
		}];
	    me.buttons = [
	        me.getResetButton(), '->', me.getSaveButton()
	    ];	
		me.callParent([cfg]);
	}
});

/**
* 权限互斥 界面数据模型定义
*/
Ext.define('Dpap.authorization.conflict.conflictModel', {
    extend: 'Ext.data.Model',
    fields: [
      // ID
      {name: 'id', type: 'string'},
      // 虚拟编码
      {name: 'virtualCode', type: 'string'},
      // 权限编码一
      {name: 'firstCode', type: 'string'},
      // 权限编码一名称
      {name: 'firstCodeName', type: 'string'},
      // 权限编码二
      {name: 'secondCode', type: 'string'},
      // 权限编码二名称
      {name: 'secondCodeName', type: 'string'},
       // 角色编码
      {name: 'roleCode', type: 'string'},
      // 角色名称
      {name: 'roleName', type: 'string'},
      // 创建时间
      {name: 'createDate', type: 'date'},
      // 更新时间
      {name: 'modifyDate', type: 'date'},
      // 是否启用
      {name: 'active', type: 'string'},
      // 创建人
      {name: 'createUser', type: 'string'},
      // 更新人
      {name: 'modifyUser', type: 'string'}
    ]
});
Ext.define('Dpap.authorization.conflict.conflictStore',{
    extend:'Ext.data.Store',
	model: 'Dpap.authorization.conflict.conflictModel',
	pageSize: 10,
	proxy: {
		type: 'ajax',
		actionMethods: 'POST',
		url : authorization.realPath("queryFunctionConflictByEntity"),
		//定义一个读取器
		reader: {
			type: 'json',
			root: 'data',
			totalProperty : 'totalCount'
		}
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.callParent([cfg]);
	},
	listeners: {
		beforeload : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('Dpap_authorization_conflict_SelectConditionForm_ID');
			if (queryForm != null) {
				var queryParams = queryForm.getValues();
				Ext.apply(operation, {
					params : {
						'functionConflictVo.functionConflictEntity.firstCode' : queryParams.firstCode,
						'functionConflictVo.functionConflictEntity.secondCode' : queryParams.secondCode,
						'functionConflictVo.functionConflictEntity.roleCode' : queryParams.roleCode
					}
				});	
			}
		}
	}
});
//查询条件面板
Ext.define('Dpap.authorization.conflict.SelectConditionForm',{
	extend:'Ext.form.Panel',
	layout:'column',
	frame : true,
	title:authorization.conflict.i18n('dpap.authorization.conflict.gui.query.condition') ,// '查询条件',
	defaults: {
		xtype : 'textfield',
		margin: '5 10 5 10',
		labelWidth: 80,
	    columnWidth: .33
	}
	,
	initComponent: function(config){
	    var me = this;
	    var	cfg = Ext.apply({}, config);
		me.buttons = [{
			text :authorization.conflict.i18n('dpap.authorization.conflict.gui.reset') ,//  '重置',
			handler : function() {
				// 将表单中的数据清空：
				me.getForm().reset();
			}
		}, '->', {
			cls : 'yellow_button',
			text :authorization.conflict.i18n('dpap.authorization.conflict.gui.query') ,//  '查询',
			// 查询按钮的响应事件：
			handler : function() {
				authorization.conflict.pagingBar.moveFirst();
			}
		}];
	    me.items = [{
			xtype:'commonroleselector',
			name:'roleCode',
			fieldLabel :authorization.conflict.i18n('dpap.authorization.conflict.gui.function.role')// '权限一'
		},{
			xtype:'commonfunctionselector',
			name:'firstCode',
			fieldLabel :authorization.conflict.i18n('dpap.authorization.conflict.gui.function.one')// '权限一'
		},{
			xtype:'commonfunctionselector',
			name:'secondCode',
			fieldLabel : authorization.conflict.i18n('dpap.authorization.conflict.gui.function.two')//'权限二'
		}];
	    me.callParent([cfg]);
	}
});


//查询的显示表格：
Ext.define('Dpap.authorization.conflict.ConflictGrid',{
	extend: 'Ext.grid.Panel',
	title: authorization.conflict.i18n('dpap.authorization.conflict.list'),
	cls:'autoHeight',
	bodyCls:'autoHeight',
	// 设置表格不可排序
	sortableColumns:false,
	// 去掉排序的倒三角
    enableColumnHide:false,
    //增加滚动条
    autoScroll : false,
    frame: true,
	stripeRows : true, // 交替行效果
	collapsible: true,
    animCollapse: true,
	selType : "rowmodel", // 选择类型设置为：行选择
	store : null,
	columns : [{
            xtype:'actioncolumn',
            width:60,
			text: authorization.conflict.i18n('dpap.authorization.conflict.gui.operate') ,//'操作',
			align: 'center',
            items: [{
            	iconCls:'deppon_icons_cancel',
                tooltip: authorization.conflict.i18n('dpap.authorization.conflict.gui.delete') ,//'作废',
                handler: function(grid, rowIndex, colIndex, button, e, record) {
	                Ext.MessageBox.show({
		                title : authorization.conflict.i18n('dpap.authorization.conflict.gui.title.conformmsg') ,//'确认提示',
						msg : authorization.conflict.i18n('dpap.authorization.conflict.gui.conformmsg') ,//'作废（权限互斥）后不可恢复，确认是否继续？',
						buttons : Ext.Msg.YESNO,
						icon : Ext.MessageBox.QUESTION,
						fn : function (btn) {
							if (btn == 'yes') {
								// 获得当前选择的数据：
								Ext.Ajax.request({
									url : authorization.realPath('deleteFunctionConflict'),
									jsonData : {
										'functionConflictVo':{
											'functionConflictEntity':{
												"virtualCode": record.get('virtualCode')
											}
										}
									},
									//"作废"成功
									success : function (response) {
										authorization.conflict.pagingBar.moveFirst();
										var json = Ext.decode(response.responseText);
										Ext.ux.Toast.msg( authorization.conflict.i18n('dpap.authorization.conflict.gui.success.msg') ,//'信息（成功）提示', 
										json.msg, 'ok', 1000);
									},
									//"作废"失败
									exception : function (response) {
										var json = Ext.decode(response.responseText);
										Ext.ux.Toast.msg(authorization.conflict.i18n('dpap.authorization.conflict.gui.fail.msg') ,//'信息（成功）提示',
										json.msg, 'error', 1000);
									}
								});
							}
						}
	                });
                }
            }]
        },{
			hidden:true,
			dataIndex: 'virtualCode'
		},{
			dataIndex: 'roleCode',
			hidden:true
		},{
			dataIndex: 'roleName',
			text: authorization.conflict.i18n('dpap.authorization.conflict.gui.function.roleName') ,//'名称',
			align : 'center',
	        flex: 1
		},{
			dataIndex: 'firstCode',
			hidden:true
		},{
			dataIndex: 'firstCodeName',
			text: authorization.conflict.i18n('dpap.authorization.conflict.gui.functionName.one') ,//'权限名称一',
			align : 'center',
	        flex: 1
		},{
			dataIndex: 'secondCode',
			hidden:true
		},{
			dataIndex: 'secondCodeName',
			text: authorization.conflict.i18n('dpap.authorization.conflict.gui.functionName.two') ,//'权限名称二',
			align : 'center',
	        flex: 1
		}
	],
	updateConflictWindow: null,
	getUpdateConflictWindow: function(){
		var me = this;
		if(me.updateConflictWindow == null){
			me.updateConflictWindow= Ext.create('Dpap.authorization.conflict.UpdateConflictEntityWindow');
			authorization.conflict.updateConflictWindow = me.updateConflictWindow;
		}
		return me.updateConflictWindow;
	},
	getTbar:function(){
		var me = this;
		return [{
			//hidden:!authorization.conflict.isPermission('/authorization/addFunctionConflict'),
			text : authorization.conflict.i18n('dpap.authorization.conflict.gui.add') ,//'新增',								//新增
			handler :function(){
				me.getUpdateConflictWindow().show();
			} 
		},'-', {
			//hidden:!authorization.conflict.isPermission('/authorization/deleteFunctionConflictMore'),
			text : authorization.conflict.i18n('dpap.authorization.conflict.gui.delete') ,//'作废',								//作废
			handler :function(){
				me.deleteResourceConflict();	
			} 
		}];
	},
	deleteResourceConflict: function(){
		var a_grid=this;
		// 获取选中的记录
		var selectionRecord = a_grid.getSelectionModel().getSelection();
		var ids = '';
		if (selectionRecord && selectionRecord.length > 0) {
			// 将id通过,分隔：
			for ( var i = 0; i < selectionRecord.length; i++) {
				ids = ids + selectionRecord[i].data.virtualCode+ ",";
			}
			ids = ids.substring(0,ids.length-1);
			Ext.MessageBox.show({
				//hidden:!authorization.conflict.isPermission('/authorization/deleteFunctionConflictMore'),
				title : authorization.conflict.i18n('dpap.authorization.conflict.gui.title.conformmsg'),//'确认提示',
				msg :authorization.conflict.i18n('dpap.authorization.conflict.gui.conformmsg'),// '作废（权限互斥）后不可恢复，确认是否继续？',
				buttons : Ext.Msg.YESNO,
				icon : Ext.MessageBox.QUESTION,
				fn : function (btn) {
					if (btn == 'yes') {
						//获取结果表格对象
						var functionConflictVo = new Object();
						functionConflictVo.functionConflictEntity = new Object();
						functionConflictVo.functionConflictEntity.virtualCode = ids;
						var params = {'functionConflictVo':functionConflictVo};
						Ext.Ajax.request({
							url : authorization.realPath('deleteFunctionConflictMore'),
							jsonData :params,
							//"作废"成功
							success : function (response) {
								authorization.conflict.pagingBar.moveFirst();
								var json = Ext.decode(response.responseText);
								Ext.ux.Toast.msg(authorization.conflict.i18n('dpap.authorization.conflict.gui.success.msg')// '信息（成功）提示'
								, json.msg, 'ok', 1000);
							},
							//"作废"失败
							exception : function (response) {
								var json = Ext.decode(response.responseText);
								Ext.ux.Toast.msg(authorization.conflict.i18n('dpap.authorization.conflict.gui.fail.msg')// '信息（失败）提示'
								, json.msg, 'error', 1000);
							}
						});
					}
				}
			});		
		}else {
			Ext.ux.Toast.msg(authorization.conflict.i18n('dpap.authorization.conflict.gui.fail.msg')//'信息（失败）提示'
			, authorization.conflict.i18n('dpap.authorization.conflict.gui.noselected')//'无任何选中记录！'
			, 'error', 1000);
		}
	   
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.selModel= Ext.create('Ext.selection.CheckboxModel');
		me.store = Ext.create('Dpap.authorization.conflict.conflictStore');
		me.bbar = Ext.create('Deppon.StandardPaging',{
			store:me.store
		});
		//添加头部按钮
		me.tbar = me.getTbar();
		authorization.conflict.pagingBar = me.bbar;
		me.callParent([cfg]);
	}
});

/**
 * 程序入口方法
 */
Ext.onReady(function() {
	Ext.QuickTips.init();
	if (Ext.getCmp('T_authorization-functionConflictIndex_content')) {
		return;
	}
	var selectConditionForm = Ext.create('Dpap.authorization.conflict.SelectConditionForm');
	var conflictGrid = Ext.create('Dpap.authorization.conflict.ConflictGrid');
	Ext.getCmp('T_authorization-functionConflictIndex').add(Ext.create('Ext.panel.Panel',{
		id: 'T_authorization-functionConflictIndex_content',
		cls: "panelContentNToolbar",
		bodyCls: 'panelContentNToolbar-body',
		items: [
			selectConditionForm,
			conflictGrid
		] 
	}));
	
});