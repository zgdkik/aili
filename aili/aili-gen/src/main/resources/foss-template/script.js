[#ftl]
[#assign modulepre="${project}.${module}.${table.typeName}.${table.typeName}"]
[#assign modulepreid="${project}_${module}_${table.typeName}_${table.typeName}"]
[#assign voParams = "${table.typeName?uncap_first}Vo.${table.typeName?uncap_first}"]
[#assign delParams = "${table.typeName?uncap_first}"]
[#assign mosubpre = "${module}.${table.typeName?uncap_first}"]
${mosubpre}.addupdateFlag;
${mosubpre}.delMoreData = function(){
	var a_grid=Ext.getCmp('${modulepreid}Grid_Id');
	// 获取选中的记录
	var selectionRecord = a_grid.getSelectionModel().getSelection();
	var ids = '';
	if (selectionRecord && selectionRecord.length > 0) {
		// 将id通过,分隔：
		for ( var i = 0; i < selectionRecord.length; i++) {
			ids = ids + selectionRecord[i].data.id+ ",";
		}
		ids = ids.substring(0,ids.length-1);
		Ext.MessageBox.show({
			title : ${mosubpre}.i18n('dpap.code.gen.confirm.msg'),//'确认提示',
			msg :${mosubpre}.i18n('dpap.code.gen.confirm.iscontinue.msg'),//'作废数据后不可恢复，确认是否继续？',
			buttons : Ext.Msg.YESNO,
			icon : Ext.MessageBox.QUESTION,
			fn : function (btn) {
				if (btn == 'yes') {
					//获取结果表格对象
					var ${delParams}Vo = new Object();
					${delParams}Vo.ids = ids;
					var params = {'${delParams}Vo':${delParams}Vo};
					Ext.Ajax.request({
						url : ${module}.realPath('delete${table.typeName}.action'),
						jsonData :params,
						//"作废"成功
						success : function (response) {
							${mosubpre}.pagingBar.moveFirst();
							var json = Ext.decode(response.responseText);
							Ext.ux.Toast.msg(${mosubpre}.i18n('dpap.code.gen.title.success.msg')
							, json.message, 'ok', 1000);
						},
						//"作废"失败
						exception : function (response) {
							var json = Ext.decode(response.responseText);
							Ext.ux.Toast.msg(${mosubpre}.i18n('dpap.code.gen.titile.fail.msg')
							, json.message, 'error', 1000);
						}
					});
				}
			}
		});		
	}else {
		Ext.ux.Toast.msg(${mosubpre}.i18n('dpap.code.gen.titile.fail.msg')
		, ${mosubpre}.i18n('dpap.code.gen.no.select')
		, 'error', 1000);
	}
   
};
/**
 * ${table.typeName} Model
 */
Ext.define('${modulepre}Model', {
    extend: 'Ext.data.Model',
    fields: [
    [#list table.columnList as column]
      {name: '${column.columnName}', type: '${column.javaDataType}'}[#if column_has_next ],[/#if]
    [/#list]
    ]
});
/**
 * ${table.typeName} Store
 */
Ext.define('${modulepre}Store',{
            extend:'Ext.data.Store',
			model: '${project}.${module}.${table.typeName}.${table.typeName}Model',
			pageSize: 10,
			autoLoad: false,
			proxy: {
				type: 'ajax',
				actionMethods: 'POST',
				url : ${module}.realPath("queryBy${table.typeName}.action"),
				reader: {
					type: 'json',
					root: '${table.typeName?uncap_first}Vo.${table.typeName?uncap_first}s',
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
					var queryForm = Ext.getCmp('${modulepreid}Form_Id');
					if (queryForm != null) {
						var queryParams = queryForm.getValues();
						Ext.apply(operation, {
							params : {
								[#list table.columnList as column]
								'${voParams}.${column.columnName}' : queryParams.${column.columnName}[#if column_has_next],[/#if]
								[/#list]
							}
						});	
					}
				}
			}
});

//查询条件面板
Ext.define('${modulepre}QueryForm',{
	extend:'Ext.form.Panel',
	id: '${modulepreid}Form_Id',
	layout:'column',
	frame : true,
	columnWidth: 0.98,
	title:${mosubpre}.i18n('dpap.code.gen.query.condition') ,// '查询条件',
	defaults: {
		xtype : 'textfield',
		readOnly : false,
		margin:'5 5 5 10',
		anchor: '90%'
	},

  initComponent: function(config){
    var me = this,
			cfg = Ext.apply({}, config);
	me.buttons = [{
		xtype : 'button',
		text :${mosubpre}.i18n('dpap.code.gen.reset') ,//  '重置',
		width : 30,
		handler : function() {
			var conflictForm = Ext.getCmp("${modulepreid}Form_Id");
			// 将表单中的数据清空：
			conflictForm.getForm().reset();
		}
	}, '->', {
		xtype : 'button',
		cls : 'yellow_button',
		text :${mosubpre}.i18n('dpap.code.gen.query') ,//  '查询',
		width : 30,
		// 查询按钮的响应事件：
		handler : function() {
			${mosubpre}.pagingBar.moveFirst();
		}
	}];
    me.items = [[#list table.columnList as column]{
    			xtype:'${column.viewDataType}',
				name:'${column.columnName}',
				fieldLabel :'${column.columnName}',
				margin:'5 20 5 10',
				labelWidth:120,
			    columnWidth:.333
			    }[#if column_has_next],[/#if]
		       [/#list]
               ];
    me.callParent([cfg]);
  }
});
//查询的显示表格：
Ext.define('${modulepre}Grid',{
	extend: 'Ext.grid.Panel',
	id : '${modulepreid}Grid_Id',
	cls:'autoHeight',
	bodyCls:'autoHeight',
	title:'xxx详细信息',
	columnWidth:.99, 
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
			text: ${mosubpre}.i18n('dpap.code.gen.operate') ,//'操作',
			align: 'center',
            items: [{
	            	iconCls:'deppon_icons_edit',
	                tooltip: ${mosubpre}.i18n('dpap.code.gen.update') ,//'修改',
	                handler: function(grid, rowIndex, colIndex) {
	                	var addUpdateeWindow = Ext.getCmp('${modulepreid}AddUpdateWindow_Id');
						if(addUpdateeWindow == null ){
							addUpdateeWindow = Ext.create('${modulepre}AddUpdateWindow');
						}
						${mosubpre}.addupdateFlag='update';
						addUpdateeWindow.getAddUpdateForm().setTitle(${mosubpre}.i18n('dpap.code.gen.update'));
						// 获得当前选择的行的的数据
						var rowInfo = grid.getStore().getAt(rowIndex);
						addUpdateeWindow.getAddUpdateForm().getForm().reset();
						addUpdateeWindow.getAddUpdateForm().loadRecord(rowInfo);
						addUpdateeWindow.show();
	            }
	            },{
            	iconCls:'deppon_icons_cancel',
                tooltip: ${mosubpre}.i18n('dpap.code.gen.delete') ,//'作废',
                handler: function(grid, rowIndex, colIndex) {
                Ext.MessageBox.show({
	                title : ${mosubpre}.i18n('dpap.code.gen.confirm.msg') ,//'确认提示',
					msg : ${mosubpre}.i18n('dpap.code.gen.confirm.iscontinue.msg') ,
					buttons : Ext.Msg.YESNO,
					icon : Ext.MessageBox.QUESTION,
					fn : function (btn) {
						if (btn == 'yes') {
						// 获得当前选择的数据：
						var rowInfo = grid.getStore().getAt(rowIndex).data;
						//发送作废结点的Ajax请求.
						var ${delParams}Vo = new Object();
						${delParams}Vo.ids = rowInfo.id;
						var params = {'${delParams}Vo':${delParams}Vo};
							Ext.Ajax.request({
								url : ${module}.realPath('delete${table.typeName}.action'),
								jsonData : params,
								//"作废"成功
								success : function (response) {
									${mosubpre}.pagingBar.moveFirst();
									var json = Ext.decode(response.responseText);
									Ext.ux.Toast.msg(${mosubpre}.i18n('dpap.code.gen.title.success.msg'), 
									json.message, 'ok', 1000);
									},
								//"作废"失败
								exception : function (response) {
									var json = Ext.decode(response.responseText);
									Ext.ux.Toast.msg(${mosubpre}.i18n('dpap.code.gen.titile.fail.msg'),
									json.message, 'error', 1000);
									}
							});
				
                  }
            }
            });
            }
            }]
        },
        [#list table.columnList as column]
        {
          text:'${column.columnName}',
		  dataIndex:'${column.columnName}',
		  flex: 1
		}[#if column_has_next],[/#if]
		[/#list]
	],
	getTbar:function(){
		var me = this;
		return [{
			text : ${mosubpre}.i18n('dpap.code.gen.add') ,//'新增',								//新增
			handler :function(){
				var addUpdateeWindow = Ext.getCmp("${modulepreid}AddUpdateWindow_Id");
				if(addUpdateeWindow == null){
					addUpdateeWindow= Ext.create('${modulepre}AddUpdateWindow');
				}
				${mosubpre}.addupdateFlag='add';
				var rowInfo = Ext.create('${modulepre}Store');
				addUpdateeWindow.getAddUpdateForm().getForm().reset();
				addUpdateeWindow.getAddUpdateForm().setTitle(${mosubpre}.i18n('dpap.code.gen.add'));
				addUpdateeWindow.show();
			} 
		},'-', {
			text : ${mosubpre}.i18n('dpap.code.gen.delete') ,//'作废',								//作废
			handler :function(){
				${module}.${table.typeName?uncap_first}.delMoreData();	
			} 
		}];
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.selModel= Ext.create('Ext.selection.CheckboxModel');
		me.store = Ext.create('${modulepre}Store');
		me.bbar = Ext.create('Deppon.StandardPaging',{
				store:me.store
		});
		//添加头部按钮
		me.tbar = me.getTbar();
		${mosubpre}.pagingBar = me.bbar;
		me.callParent([cfg]);
	}
});
Ext.define('${modulepre}AddUpdateWindow',{
	extend: 'Ext.window.Window',
	id : '${modulepreid}AddUpdateWindow_Id',
	title: ${mosubpre}.i18n('dpap.code.gen.update.ui.msg'),//'编辑界面',
    width: 900,
    modal:true,
	closeAction:'hide',
	layout : 'column',
	addUpdateForm:null,
	getAddUpdateForm:function(){
		if(this.addUpdateForm == null){
			this.addUpdateForm = Ext.create('${modulepre}.AddUpdateForm');
		}
		return this.addUpdateForm;
	},
	constructor: function(config){
		var me = this,
		cfg = Ext.apply({}, config);
		me.items = [
		    me.getAddUpdateForm()
		];
		me.callParent([cfg]);
	}
});
Ext.define('${modulepre}.AddUpdateForm',{
	extend: 'Ext.form.Panel',
	id: '${modulepreid}addUpdateForm_Id',
	frame: true,
	hidden : false,
	defaultType : 'textfield',
	layout:'column',
	columnWidth: 0.99,
	defaults: {
		readOnly : false,
		margin:'5 5 5 10',
		anchor: '90%',
		columnWidth: 1,
		labelWidth: 60
	},
    //取消按钮
    cancelButton : null,
    getCancelButton:function(me){
        if(this.cancelButton==null){
          this.cancelButton = Ext.create('Ext.button.Button',{
            xtype : 'button',
            text: ${mosubpre}.i18n('dpap.code.gen.cancel'),//'取消',
            columnWidth:.12, 
            hidden : false,
            handler: function(){
	    		var a_window = Ext.getCmp('${modulepreid}AddUpdateWindow_Id');
	    		var addUpdateForm=Ext.getCmp('${modulepreid}addUpdateForm_Id');
	    		addUpdateForm.getForm().reset();
	    		a_window.hide();
            }
          });
        }
        return this.cancelButton;
    },
  //重置按钮
    resetButton : null,
    getResetButton : function(me){
        if(this.resetButton==null){
			this.resetButton = Ext.create('Ext.button.Button',{
				xtype : 'button',
				hidden : false,
				name: 'reset',
				text:${mosubpre}.i18n('dpap.code.gen.reset'),// '重置',
				columnWidth:.72, 
				width: 55,
				margin:'5 5 5 450', 
				handler: function() {
					var a_updateForm=Ext.getCmp('${modulepreid}addUpdateForm_Id');
					a_updateForm.getForm().reset();	
				}
			});
        }
        return this.resetButton;
    
    },
    //保存按钮
    saveButton : null,
    getSaveButton : function(){
        if(this.saveButton==null){
            this.saveButton = Ext.create('Ext.button.Button',{
				cls:'yellow_button',
				text:${mosubpre}.i18n('dpap.code.gen.save'),//  '保存',
				columnWidth:.12, 
				handler: function() { 
					var a_form = Ext.getCmp("${modulepreid}addUpdateForm_Id");
					var a_model = Ext.create('${modulepre}Model', a_form.getForm().getValues());
					// 请求合法性验证
					if(!a_form.getForm().isValid()){
						return;
					}
		    		
					var ${delParams}Vo = new Object();
					 ${delParams}Vo.${table.typeName?uncap_first} = new Object();
					 ${delParams}Vo.${table.typeName?uncap_first} = a_model.data
					var params = {'${delParams}Vo':${delParams}Vo};
					var  requrl ;
					 if(${mosubpre}.addupdateFlag =="add"){
						 requrl='insert${table.typeName}.action';
					 }else{
						 requrl='update${table.typeName}.action';
					 }
					//发送新增结点的Ajax请求.
					Ext.Ajax.request({
						url: ${module}.realPath(requrl),
						jsonData:params,
						//作废成功
						success : function(response) {
			                var json = Ext.decode(response.responseText);
			                ${module}.${table.typeName?uncap_first}.pagingBar.moveFirst();
							a_form.up('window').hide();
							Ext.ux.Toast.msg(${mosubpre}.i18n('dpap.code.gen.title.success.msg') //'信息（成功）提示'
							, json.message, 'ok', 1000);
						
			            },
			            //保存失败
		                exception : function(response) {
		                    var json = Ext.decode(response.responseText);
		                    Ext.ux.Toast.msg(${mosubpre}.i18n('dpap.code.gen.titile.fail.msg') //'信息（失败）提示'
		                    , json.message, 'error', 1000);
		                    a_form.up('window').hide();
		                }
					});

					var a_conditionForm=Ext.getCmp("${modulepreid}addUpdateForm_Id");
					// 将表单中的数据清空：
					a_conditionForm.getForm().reset();
					Ext.getCmp("${modulepreid}AddUpdateWindow_Id").setVisible(false);
				}
            });
        }
        return this.saveButton;
    },
	initComponent: function(config){
		var me = this,
				cfg = Ext.apply({}, config);
		me.items = [[#list table.columnList as column]{
					xtype:'${column.viewDataType}',
					name:'${column.columnName}',
					fieldLabel :'${column.columnName}' ,
					margin:'5 20 5 10',
					labelWidth:80,
				    columnWidth:.333
				    }[#if column_has_next],[/#if]
				    [/#list]
		];
	    me.buttons = [me.getCancelButton(me),
	                  '->', 
	                  me.getResetButton(me),
	                  '->', 
	                  me.getSaveButton()
	                  ];			    
		me.callParent([cfg]);
	}
});
/**
 * 程序入口方法
 */
Ext.onReady(function() {
	Ext.QuickTips.init();
	if (Ext.getCmp('T_${module}-${table.typeName}_content')) {
		return;
	}
	Ext.getCmp('T_${module}-${table.typeName?uncap_first}').add(Ext.create('Ext.panel.Panel',{
		id: 'T_${module}-${table.typeName}_content',
		cls: "panelContentNToolbar",
		bodyCls: 'panelContentNToolbar-body',
		layout: 'column',
		items: [
			Ext.create('${modulepre}QueryForm'),
			Ext.create('${modulepre}Grid')
			
		] 
	}));
	
});