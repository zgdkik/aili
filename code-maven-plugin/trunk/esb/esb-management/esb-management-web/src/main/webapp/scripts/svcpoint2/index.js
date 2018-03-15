//=======画界面开始======
Ext.define('SvcPoint2Model', {
			extend : 'Ext.data.Model',
			fields : ['id', 'name', 'code', 'sysid', 'agrmt', 'frontOrBack',
					  'esbRequestAddr', 'esbResponseAddr', 'expattern',
					'messageFormat', 'timeout']
		});
// 服务配置store
Ext.define('Esb2.svcpoint.SvcPoint2Store', {
			extend : 'Ext.data.Store',
			model:'SvcPoint2Model',
			proxy : {
				type : 'ajax',
				url : '../svcpoint2/query.action',
				actionMethods : {
					read : 'POST'
				},
				reader : {
					type : 'json',
					root : 'list',
					totalProperty : 'resultCount'// 总的数据条数
				}
			}
		});
//系统编码store
Ext.define("Esb2.svcpoint.SysIDStore",{
			extend : 'Ext.data.Store',
			fields:['value'],
			autoLoad :true,
/*			listeners:{
				//把后台传过来的数据进行解析并放到store中
				load:function(store,records,successful, operation, eOpts){
					var a =store.data.items;
					for (var i = 0; i < a.length; i++) {
						a[i].data.sysid=a[i].raw;
					}
				}
			},*/
			proxy : {
				type : 'ajax',
				url : '../svcpoint2/querySysIds.action',
/*				actionMethods : {
					read : 'POST'
				},*/
				reader : {
					type : 'json',
					root : 'sysIds'
				}
			}
});
Ext.define('Esb2.common.BaseModel',{
	extend : 'Ext.data.Model',
	fields:['name','value']
});
//前端、后端store
Ext.define("Esb2.svcpoint.FrontOrBackStore",{
			extend : 'Ext.data.Store',
			fields:['name','value'],
			data:[{'name':'前端','value':'F'},
				{'name':'后端','value':'B'}]
});

//接入协议store
Ext.define("Esb2.svcpoint.AgrmtStore",{
			extend : 'Ext.data.Store',
			autoLoad :true,
			fields:['value'],
			proxy : {
				type : 'ajax',
				url : '../svcpoint2/queryAgrmts.action',
				reader : {
					type : 'json',
					root : 'agrmts'
				}
			}
});
//交互模式Store
Ext.define("Esb2.svcpoint.ExpatternStore",{
			extend : 'Ext.data.Store',
			fields:['name','value'],
			data:[{'name':'请求响应','value':'1'},
				{'name':'请求回调','value':'2'},
				{'name':'单向','value':'3'}]
});
// 查询panel
Ext.define('Esb2.svcpoint.QueryForm', {
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
				fieldLabel : '服务名称',
				name:'name'
			}, {
				fieldLabel : '服务编码',
				name:'code'
			}, {
				xtype : 'combo',
				width : 250,
				store : Ext.create('Esb2.svcpoint.SysIDStore'),
				displayField : 'value',
				editable : false,
				// style:'margin-left:30px;',
				valueField : 'value',
				fieldLabel : '系统编码',
				name : 'sysid',
				emptyText : '---请选择---',
				forceSelection : true
			}, {
				fieldLabel : '服务接入协议',
				name : 'agrmt',
				xtype : 'combo',
				width : 250,
				store : Ext.create('Esb2.svcpoint.AgrmtStore'),
				displayField : 'value',
				editable : false,
				// style:'margin-left:30px;',
				valueField : 'value',
				emptyText : '---请选择---',
				forceSelection : true
			},{
				fieldLabel : '前端或后端',
				name : 'frontOrBack',
				xtype : 'combo',
				width : 250,
				store : Ext.create('Esb2.svcpoint.FrontOrBackStore'),
				displayField : 'name',
				editable : false,
				// style:'margin-left:30px;',
				valueField : 'value',
				emptyText : '---请选择---',
				forceSelection : true
			}, {
				fieldLabel : '服务模式',
				name : 'expattern',
				xtype : 'combo',
				width : 250,
				store : Ext.create('Esb2.svcpoint.ExpatternStore'),
				displayField : 'name',
				editable : false,
				// style:'margin-left:30px;',
				valueField : 'value',
				emptyText : '---请选择---',
				forceSelection : true
			}, {
				fieldLabel : 'ESB处理请求地址',
				name:'esbRequestAddr'
			}, {
				fieldLabel : 'ESB处理响应地址',
				name:'esbResponseAddr'
			}],
			bbar:[
			{
				xtype : 'button',
				text : '查询',
				style : 'margin-left:0px;',
				width : 60,
				handler : function() {
					var store = Ext.data.StoreManager.lookup('Esb2_svcpoint_Store_Id');
							store.loadPage(1, {
								callback : function(records, operations,
										success) {
									var rawData = Ext.getCmp('Esb2_Svcpoint_Grid_Id').store.proxy.reader.rawData;
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
					var form = Ext.getCmp('Esb2_Svcpoint_QueryForm_Id');
					form.getForm().reset();
				}
			}
			]
		});
/**
 * 
 */
Ext.define('Esb2.svcpoint.SvcpointListPanel', {
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
					ediSvcpointForm('../svcpoint2/add.action');
				}
			}, {
				xtype : 'button',
				text : '删除',
				handler : function() {
					var selection = Ext.getCmp('Esb2_Svcpoint_Grid_Id')
							.getSelectionModel().getSelection();
					if (selection.length == 0) {
						Ext.MessageBox.alert("client", "请选中至少一条记录！");
						return;
					}
					var svcCodes = "";
					for (var i = 0; i < selection.length; i++) {
						svcCodes = svcCodes + selection[i].get('code') + ",";
					}
					Ext.MessageBox.confirm('client', '你确认删除选择的记录？', function(btn) {
						if (btn == "yes") {
							Ext.Ajax.request({
										url : '../svcpoint2/delete.action',
										params : {
											codes : svcCodes.substr(0,svcCodes.length-1)
										},
										success : function(response) {
/*											var result = Ext
													.decode(response.responseText);
											Ext.MessageBox.alert('server',
													result.message);*/
											var store = Ext.data.StoreManager.lookup('Esb2_svcpoint_Store_Id');
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
					var selection = Ext.getCmp('Esb2_Svcpoint_Grid_Id')
							.getSelectionModel().getSelection();
					if (selection.length != 1) {
						Ext.MessageBox.alert("client", "请在修改配置时有且选中一行记录！");
						return;
					}
					var record = selection[0];
					var url = '../svcpoint2/update.action';
					ediSvcpointForm(url, record);
				}
			},{
				xtype : 'button',
				text : '刷新',
				handler : function() {
					var store = Ext.data.StoreManager.lookup('Esb2_svcpoint_Store_Id');
					store.load();
				}
			}],
	columns : [{
				header : 'id',
				name:'id',
				hidden:true,
				dataIndex : 'id'
			},{
				header : '服务名称',
				name:'name',
				dataIndex : 'name'
			}, {
				header : '服务编码',
				name:'code',
				dataIndex : 'code'
			}, {
				header : '系统编码',
				name:'sysid',
				dataIndex : 'sysid'
			}, {
				header : '服务接入协议',
				name:'agrmt',
				dataIndex : 'agrmt'
			}, {
				header : '前端或后端',
				name:'frontOrBack',
				dataIndex : 'frontOrBack'
			}, {
				header : '交互模式',
				name:'expattern',
				width : 60,
				dataIndex : 'expattern',
				renderer : function(value, celldata, record) {
					switch(value){
						case 1:
							return "请求响应";
						case 2:
							return "请求回调";
						case 3:
							return "单向";
						default:
							return value;
					}
				}
			}, {
				header : '消息格式',
				name:'messageFormat',
				width : 60,
				dataIndex : 'messageFormat'
			}, {
				header : 'ESB处理请求地址',
				name:'esbRequestAddr',
				width : 250,
				dataIndex : 'esbRequestAddr'
			}, {
				header : 'ESB处理响应地址',
				name:'esbResponseAddr',
				width : 250,
				dataIndex : 'esbResponseAddr'
			}]
});
//=======画界面结束======
Ext.onReady(function() {
			var store = Ext.create(
			'Esb2.svcpoint.SvcPoint2Store', {
				id:'Esb2_svcpoint_Store_Id',
				listeners : {
					beforeload : function(store, operation, eOpts) {
						var form = Ext
								.getCmp('Esb2_Svcpoint_QueryForm_Id');
							var params = {
								'queryBean.name' : form.getForm()
									.findField('name').getValue(),
								'queryBean.code':form.getForm()
									.findField('code').getValue(),
								'queryBean.sysid':form.getForm()
									.findField('sysid').getValue(),
								'queryBean.frontOrBack':form.getForm()
									.findField('frontOrBack').getValue(),
								'queryBean.esbRequestAddr':form.getForm()
									.findField('esbRequestAddr').getValue(),
								'queryBean.esbResponseAddr':form.getForm()
									.findField('esbResponseAddr').getValue(),
								'queryBean.agrmt':form.getForm()
									.findField('agrmt').getValue(),
								'queryBean.expattern':form.getForm()
									.findField('expattern').getValue()
							};
							Ext.apply(operation, {
										params : params
									});
						}
					}
			});
	//初始化查询控件
	var queryForm = Ext.create('Esb2.svcpoint.QueryForm',{
		id:'Esb2_Svcpoint_QueryForm_Id',
		region : 'north'
	});
	//第一次点开页面，初始加载数据
	store.load({params:{'queryBean.start':0,'queryBean.limit':100}}); 
	//初始化列表控件
	var listPanel = Ext.create('Esb2.svcpoint.SvcpointListPanel', {
				id:'Esb2_Svcpoint_Grid_Id',
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
						id:'esb2_svcpoint_',
						title : '查询ESB2期服务配置',
						layout : 'border',
						items:[queryForm,listPanel]
					});

		});
//===========函数定义开始=============
// 打开svcpoint编辑界面
function ediSvcpointForm(submitURL, record) {
	//窗口ID
	var windID= "esb2_svpoint_win";
	//表单ID
	var formID="esb2_svcpoint_editPanel_id";
	if (!record) {
		record = new SvcPoint2Model();
	}
	if (!Ext.getCmp(windID)) {
		Ext.create('Ext.window.Window', {
					title : 'svcpoint编辑',
					//resizable : false,// 窗口大小不可变
					modal : true,// 模态对话框
					id : windID,
					closeAction : 'hide',
					width : window.screen.availWidth * 0.43,
					height : window.screen.availHeight * 0.25,
					layout : 'border',
					items : [Ext.create('Esb2.svcpoint.editPanel', {
								region:'center',
								 'id' : formID
							})]/*,
					listeners:{
						hide:function(){
							var form = Ext.getCmp(formId);
							form.getForm().reset();
						}
					}*/
				});
	}
	Ext.getCmp(windID).show();
	//清空表单
	Ext.getCmp(formID).getForm().reset();
	//再load数据
	Ext.getCmp(formID).getForm().loadRecord(record);
/*	if (parm) {
		Ext.getCmp('svcode1').setDisabled(true);
	} else {
		Ext.getCmp('svcode1').setDisabled(false);
	}*/
	Ext.getCmp(formID).getForm().url = submitURL;
}		

/** trim() method for String */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, '');
};
//===========函数定义结束=============