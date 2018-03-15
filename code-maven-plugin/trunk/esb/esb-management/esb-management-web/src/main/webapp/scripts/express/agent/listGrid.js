//

//=======panel定义开始======
// 查询panel
Ext.define('QueryForm', {
			extend : 'Ext.form.Panel',
			frame : true,
			title : '查询条件',
			layout : {
				type : 'table',
				columns : 4
			},
			defaults : {
				width : 250,
				labelAlign : 'left',
				margin : '4 5 4 4'
			},
			defaultType : 'textfield',
			items : [{
				fieldLabel : '代理名称',
				name:'agentName'
			}, {
				fieldLabel : '代理编码',
				name:'userName'
			}, {
				fieldLabel : '开始时间',
				name:'startTime'
			}, {
				fieldLabel : '结束时间',
				name:'endTime'
			}, {
				fieldLabel : '是否激活',
				name:'status'
			}],
			bbar:[
			{
				xtype : 'button',
				text : '查询',
				style : 'margin-left:0px;',
				width : 60,
				handler : function() {
					var store = Ext.data.StoreManager.lookup(CONSTANTS_AGENT_STORE_ID);
							store.loadPage(1, {
								callback : function(records, operations,
										success) {
									var rawData = Ext.getCmp(CONSTANTS_LIST_GRID_ID).store.proxy.reader.rawData;
									if (!success) {
										Ext.Msg.alert("查询失败",rawData.tips);
									}
								}
							});
				}
			},{
				xtype:'button',
				text:'清空条件',
				style : 'margin-left:0px;',
				width : 60,
				handler : function() {
					var form = Ext.getCmp(CONSTANTS_QUERY_FORM_ID);
					form.getForm().reset();
				}
			}
			]
		});
/**
 * 
 */
Ext.define('ListGrid', {
	extend : 'Ext.grid.Panel',
	title : '查询结果',
	emptyText:'查询结果为空',
	frame : true,
	selModel : Ext.create('Ext.selection.CheckboxModel'),
	//新增、删除、修改、刷新
	tbar : [{
				xtype : 'button',
				text : '新增',
				handler : function() {
					openEditForm(CONSTANTS_OPERATION_ADD_URL);
				}
			}, {
				xtype : 'button',
				text : '删除',
				handler : function() {
					var selection = Ext.getCmp(CONSTANTS_LIST_GRID_ID)
							.getSelectionModel().getSelection();
					if (selection.length == 0) {
						Ext.MessageBox.alert("client", "请选中至少一条记录！");
						return;
					}
					var svcCodes = "";
					for (var i = 0; i < selection.length; i++) {
						svcCodes = svcCodes + selection[i].get('id') + ",";
					}
					Ext.MessageBox.confirm('client', '你确认删除选择的记录？', function(btn) {
						if (btn == "yes") {
							Ext.Ajax.request({
										url : CONSTANTS_OPERATION_DELETE_URL,
										params : {
											ids : svcCodes.substr(0,svcCodes.length-1)
										},
										success : function(response) {
/*											var result = Ext
													.decode(response.responseText);
											Ext.MessageBox.alert('server',
													result.message);*/
											var store = Ext.data.StoreManager.lookup(CONSTANTS_AGENT_STORE_ID);
											store.remove(selection);
										},
										failure : function(response) {
											var result = Ext
													.decode(respone.responseText);
											Ext.MessageBox.alert('server', result);
										}
									});
						}
					});

				}
			}, {
				xtype : 'button',
				text : '修改',
				handler : function() {
					var selection = Ext.getCmp(CONSTANTS_LIST_GRID_ID)
							.getSelectionModel().getSelection();
					if (selection.length != 1) {
						Ext.MessageBox.alert("client", "请在修改配置时有且选中一行记录！");
						return;
					}
					var record = selection[0];
					var url = CONSTANTS_OPERATION_UPDATE_URL;
					openEditForm(url, record);
				}
			},{
				xtype : 'button',
				text : '刷新',
				handler : function() {
					var store = Ext.data.StoreManager.lookup(CONSTANTS_AGENT_STORE_ID);
					store.load();
				}
			}],
	columns : [{
				header : 'id',
				name:'id',
				hidden:true,
				dataIndex : 'id'
			},{
				header : '代理名称',
				name:'agentName',
				dataIndex : 'agentName'
			}, {
				header : '代理编码',
				name:'userName',
				dataIndex : 'userName'
			}, {
				header : '代理密码',
				name:'passwd',
				dataIndex : 'passwd'
			}, {
				header : '状态',
				name:'status',
				dataIndex : 'status'
			}, {
				header : '创建时间',
				name:'createTime',
				width:200,
				dataIndex : 'createTime',
				renderer : function(value) {
					var date = new Date(value); 
					return Ext.Date.format(date, 'Y-m-d H:i:s');
				}
			}]
});
//=======panel定义结束======

//初始化窗口
Ext.onReady(function() {
			var store = Ext.create(
			'ExpressAgentStore', {
				id:CONSTANTS_AGENT_STORE_ID,
				listeners : {
					beforeload : function(store, operation, eOpts) {
						var form = Ext
								.getCmp(CONSTANTS_QUERY_FORM_ID);
							var params = {
								'bean.agentName' : form.getForm()
									.findField('agentName').getValue(),
								'bean.userName':form.getForm()
									.findField('userName').getValue(),
								'bean.status':form.getForm()
									.findField('status').getValue()/*,
								'bean.startTime':form.getForm()
									.findField('startTime').getValue(),
								'bean.endTime':form.getForm()
									.findField('endTime').getValue()*/
							};
							Ext.apply(operation, {
										params : params
									});
						}
					}
			});
	//初始化查询控件
	var queryForm = Ext.create('QueryForm',{
		id:CONSTANTS_QUERY_FORM_ID,
		region : 'north'
	});
	//第一次点开页面，初始加载数据
	store.load({params:{'start':0,'limit':25}}); 
	//初始化列表控件
	var listPanel = Ext.create('ListGrid', {
				id:CONSTANTS_LIST_GRID_ID,
				region : 'center',
				store : store,
				bbar : Ext.create('Ext.PagingToolbar', {
							store : store,
							displayInfo : true,
							displayMsg : '显示第 {0} 到- {1}条记录，一共  {2}条记录',
							emptyMsg : "没有记录"
						})
			});	
	Ext.create('Ext.Viewport', {
						frame : true,
						title : '落地配置代理管理',
						layout : 'border',
						items:[queryForm,listPanel]
					});

		});
//===========函数定义开始=============
// 打开svcpoint编辑界面
function openEditForm(submitURL, record) {
	//窗口ID
	if (!record) {
		record = new ExpressAgentModel();
	}
	if (!Ext.getCmp(CONSTANTS_EDIT_WINDOW_ID)) {
		Ext.create('Ext.window.Window', {
					title : 'agentUser编辑',
					//resizable : false,// 窗口大小不可变
					modal : true,// 模态对话框
					id : CONSTANTS_EDIT_WINDOW_ID,
					closeAction : 'hide',
					width : window.screen.availWidth * 0.43,
					height : window.screen.availHeight * 0.15,
					layout : 'border',
					items : [Ext.create('EditPanel', {
								region:'center',
								 'id' : CONSTANTS_EDIT_FORM_ID
							})]/*,
					listeners:{
						hide:function(){
							var form = Ext.getCmp(formId);
							form.getForm().reset();
						}
					}*/
				});
	}
	Ext.getCmp(CONSTANTS_EDIT_WINDOW_ID).show();
	//清空表单
	Ext.getCmp(CONSTANTS_EDIT_FORM_ID).getForm().reset();
	//再load数据
	Ext.getCmp(CONSTANTS_EDIT_FORM_ID).getForm().loadRecord(record);
/*	if (parm) {
		Ext.getCmp('svcode1').setDisabled(true);
	} else {
		Ext.getCmp('svcode1').setDisabled(false);
	}*/
	Ext.getCmp(CONSTANTS_EDIT_FORM_ID).getForm().url = submitURL;
}		

/** trim() method for String */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, '');
};
//===========函数定义结束=============