// 表格显示信息
var mqConnectGrid = Ext
		.create(
				'Ext.grid.Panel',
				{
					title : '队列连接信息列表',
					frame : true,
					autoScroll : true,
					stripeRows : true,
					selModel : Ext.create('Ext.selection.CheckboxModel'),
					store : Ext.create('mqConnectStore', {
						'id' : 'mqConnectStoreId'
					}),
					columns: [{
						//id : 'id',
						name : 'id',
						hidden : true,
						header : 'Id',
						dataIndex : 'id'
					}, {
						//id : 'ip',
						name : 'ip',
						header : '服务地址',
						dataIndex : 'ip'
					}, {
						//id : 'port',
						name : 'port',
						header : '端口号',
						dataIndex : 'port'
					}, {
						//id : 'channel',
						name : 'channel',
						header : '通道名',
						dataIndex : 'channel'
					}, {
						//id : 'queueNameReg',
						name : 'queueNameReg',
						header : '队列通配符',
						dataIndex : 'queueNameReg'
					}, {
						//id : 'qmgr',
						name : 'qmgr',
						header : '队列管理器名',
						dataIndex : 'qmgr'
					} ],
					// 顶部工具
					tbar : [
							{
								xtype : 'button',
								text : '删除',
								handler : function() {
									var selection = mqConnectGrid
											.getSelectionModel().getSelection();
									if (selection.length == 0) {
										Ext.MessageBox.alert("温馨提醒",
												"请选中至少一条记录！");
										return;
									}
									var mqConnectIds = "";
									var hintSign = "";
									for ( var i = 0; i < selection.length; i++) {
										mqConnectIds += selection[i].get('id')
												+ ",";
										hintSign += "<br/>"+selection[i].get('ip')
												+ "/"
												+ selection[i].get('qmgr');
									}
									Ext.MessageBox
											.confirm(
													'确认框',
													'是否需要删除以下配置信息：' + hintSign,
													function(btn) {
														if (btn == "yes") {
															Ext.Ajax
																	.request({
																		url : 'deleteMqConnectByIds.action',
																		params : {
																			fid : mqConnectIds
																		},
																		success : function(
																				response) {
																			var result = Ext
																					.decode(response.responseText);
																			Ext.MessageBox
																					.alert(
																							'执行结果',
																							result.message);
																			Ext.data.StoreManager
																					.lookup(
																							'mqConnectStoreId')
																					.remove(
																							selection);
																		},
																		failure : function(
																				response) {
																			var result = Ext
																					.decode(respone.responseText);
																			Ext.MessageBox
																					.alert(
																							'执行结果',
																							result);
																		}
																	});
														}
													});
								}
							},
							{
								xtype : 'button',
								text : '修改',
								handler : function() {
									var selection = mqConnectGrid
											.getSelectionModel().getSelection();
									if (selection.length != 1) {
										Ext.MessageBox.alert("温馨提醒",
												"请选中一条记录进行修改！");
										return;
									}
									var record = selection[0];
									var url = 'updateMqConnect.action';
									openMqConnectForm(url, record);
									Ext.data.StoreManager.lookup(
											'mqConnectStoreId').load();
								}
							},
							{
								xtype : 'button',
								text : '新增',
								handler : function() {
									var url = 'addMqConnect.action';
									openMqConnectForm(url);
									Ext.data.StoreManager.lookup(
											'mqConnectStoreId').load();
								}
							} ],
					// 分页工具栏
					bbar : Ext.create('Ext.PagingToolbar', {
						store : Ext.data.StoreManager
								.lookup('mqConnectStoreId'),
						pageSize : 20,
						displayInfo : true,
						displayMsg : '当前记录数为从 第{0}条 - 第{1}条 ，共 {2}条',
						emptyMsg : "无记录"
					})
				});
// 打开MqConnect编辑界面
function openMqConnectForm(url, parm) {
	var record = parm ;
	if(!record){
		record = new MqConnectInfoModel();
	}
	if(!Ext.getCmp('win_addMqConnect')){
		Ext.create('Ext.window.Window',{
			title : '队列连接编辑',
			resizable : false,// 窗口大小不可变
			modal : true,// 模态对话框
			id : 'win_addMqConnect',
			closeAction : 'hide',
			// width : window.screen.availWidth * 0.4,
			// height : window.screen.availHeight * 0.3,
			// layout : 'fit',
			items : [Ext.create('mqConnectForm', {
						'id' : 'mqConnectForm',
						'record' : record,
						'url' : url
					})]
		});
	}
	Ext.getCmp('win_addMqConnect').show();
	Ext.getCmp('mqConnectForm').getForm().loadRecord(record);
	Ext.getCmp('mqConnectForm').url = url;
}