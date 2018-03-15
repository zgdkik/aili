// ==============后端服务监控编辑form================
Ext.define('monitor_thresholdForm', {
	extend : 'Ext.form.Panel',
	frame : true,
	layout : {
		type : 'table',
		columns : 2
	},
	defaults : {
		width : 250,
		labelAlign : 'left',
		margin : '4 5 4 4'
	},
	defaultType : 'textfield',
	items : [{
		xtype : 'combo',
		width : 250,
		store : Ext.create('serviceProviderStore', {
					id : 'serviceProviderStore1'
				}),
		displayField : 'name',
		editable : false,
		valueField : 'name',
		fieldLabel : '服务提供方',
		name : 'svcProvdId',
		id : 'servicerProvider1',
		emptyText : '---请选择---',
		colspan : 1,
		forceSelection : true,
		listeners : {
			select : function() {
				Ext.getCmp('monitor_thresholdForm').getForm()
						.findField('svcCode').setValue(null);
				Ext.getCmp('monitor_thresholdForm').getForm()
						.findField('svcName').setValue(null);
			}
		}
	}, {
		xtype : 'button',
		id : 'querySvcpointButton',
		style : 'margin-bottom:5px;margin-left:2px',
		colspan : 1,
		width : 80,
		text : '选择服务',
		handler : function() {
			queryBackServce();
		}
	}, {
		fieldLabel : '服务代码',
		width : 250,
		name : 'svcCode',
		emptyText : '请点击选择服务',
		colspan : 2,
		disabled : true,
		editable : false
	}, {
		fieldLabel : '服务名称',
		emptyText : '请点击选择服务',
		disabled : true,
		width : 250,
		colspan : 2,
		disabled : true,
		name : 'svcName'
	}, {
		fieldLabel : '响应时间阀值(ms)',
		width : 250,
		colspan : 2,
		allowBlank : false,
		regex : /^\d*$/,
		regexText : '请输入数字',
		emptyText : '请输入预警阈值',
		name : 'threshold'

	}, {
		xtype : 'combo',
		width : 250,
		colspan : 2,
		store : Ext.create('warningChannelStore'),
		displayField : 'channelName',
		editable : false,
		valueField : 'channelName',
		fieldLabel : '预警渠道',
		name : 'svcAgrmt',
		id : 'channel',
		name : 'channel',
		emptyText : '---请选择---',
		forceSelection : true,
		allowBlank : false
	}, {
		xtype : 'field',
		fieldLabel : '预警通知人员',
		editable : false,
		width : 300,
		name : 'personName',
		id : 'noticeUser',
		allowBlank : false,
		emptyText : '请点击选择服务',
		disabled : true
	}, {
		xtype : 'button',
		width : 80,
		text : '选择预警人员',
		handler : function() {
			var windowId = 'wind_NoticeUser';
			if (!Ext.getCmp(windowId)) {
				Ext.create('monitor_noticeUserWindow', {
							id : windowId
						});
			}
			Ext.getCmp(windowId).show();
		}
	},
			//隐藏域：监控设置记录id、预警人员id列表
			{
				xtype : 'field',
				hidden : true,
				name : 'id',
				id : 'thresholdId'
			}, {
				xtyp : 'field',

				hidden : true,
				name : 'personId',
				id : 'noticeList'
			}],
	bbar : [{
		xtype : 'button',
		text : '提交',
		width : 80,
		handler : function() {
			var form = Ext.getCmp('monitor_thresholdForm').getForm();
			var list = form.findField('noticeList').getValue();
			if (!form.isValid()) {
				Ext.MessageBox.alert("", "请检查输入是否完全或正确");
			} else {

				Ext.Ajax.request({
							url : Ext.getCmp('monitor_thresholdForm').url,
							params : {
								'thresholdInfo.svcCode' : form
										.findField('svcCode').getValue(),
								'thresholdInfo.channelId' : form
										.findField('channel').getValue(),
								'thresholdInfo.threshold' : form
										.findField('threshold').getValue(),
								'thresholdInfo.personId' : form
										.findField('personId').getValue(),
								'thresholdInfo.id' : form.findField('id')
										.getValue(),
								'thresholdInfo.type' : 0
							},
							success : function(response) {
								var result = Ext.decode(response.responseText);
								Ext.MessageBox.alert('', result.message);
								Ext.getCmp('backServiceGrid').getStore().load();
							},
							failure : function(response) {
								var result = Ext.decode(respone.responseText);
								Ext.MessageBox.alert('', result);
							}
						});
			}
		}
	}, {
		xtype : 'button',
		width : 80,
		text : '重置',
		handler : function() {
			Ext.getCmp('monitor_thresholdForm').getForm().reset();
		}
	}]
});

function submit(url, param) {
	Ext.Ajax.request({
				url : url,
				params : {
					'message' : 'test111',
					'test' : '123'
				},
				success : function(response) {
					var result = Ext.decode(respone.responseText);
					Ext.MessageBox.alert('', result);
				},
				failure : function(response) {
					var result = Ext.decode(respone.responseText);
					Ext.MessageBox.alert('', result);
				}
			});
}
// ==============================
var querySvcpointForm = Ext.create('Ext.form.Panel', {
			frame : true,
			title : '查询条件',
			region : 'north',
			//width : window.screen.availWidth * 0.84,
			//height : window.screen.availHeight * 0.08,
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
						width : 250,
						name : 'serviceName',
						colspan : 1
					}, {
						xtype : 'combo',
						width : 250,
						store : Ext.create('serviceProviderStore'),
						displayField : 'name',

						valueField : 'name',
						fieldLabel : '服务提供方',
						editable : false,
						name : 'servicerProvider',
						emptyText : '---请选择---',
						colspan : 1

					}, {
						xtype : 'button',
						text : '查询',
						style : 'margin-left:0px;',
						width : 60,
						handler : function() {
							Ext.data.StoreManager.lookup('fullThresholdStore')
									.load();
						}
					}, {
						xtype : 'button',
						text : '清空查询条件',
						width : 80,
						handler : function() {
							querySvcpointForm.getForm().reset();
						}
					}]
		});

// 打开后端服务界面
function queryBackServce() {
	var provider = Ext.getCmp('monitor_thresholdForm').getForm()
			.findField('servicerProvider1').getValue();
	if (provider == null) {
		Ext.MessageBox.alert('提示', '请选择服务提供方 ');
		return;
	}
	var winId = "monitor_selectSvcpoint";
	if (!Ext.getCmp(winId)) {
		Ext.create('Ext.window.Window', {
					resizable : false,// 窗口大小不可变
					modal : true,// 模态对话框
					id : winId,
					closeAction : 'hide',
					// width : window.screen.availWidth * 0.4,
					// height : window.screen.availHeight * 0.5,
					// layout : 'fit',
					items : [Ext.create('monitor_svcppointPanel', {
								'id' : 'monitor_svcppointPanel',
								'parentId' : winId
							})]
				});
	}
	Ext.getCmp('monitor_svcppointPanel').parentId = winId;
	Ext.data.StoreManager.lookup('monitor_backsvcpointStore').load({
				params : {
					'svcPointInfo.svcProvdId' : provider
				}
			});
	Ext.getCmp(winId).show();
}

// 后端服务配置panel
Ext.define('monitor_svcppointPanel', {
			extend : 'Ext.grid.Panel',
			width : window.screen.availWidth * 0.5,
			height : window.screen.availHeight * 0.4,
			title : '后端服务配置',
			frame : true,
			selModel : Ext.create('Ext.selection.CheckboxModel'),
			store : Ext.create('serviceStore', {
				'id' : 'monitor_backsvcpointStore'
					/*			listener:{
				beforeload:function(store,operation,e){
				alert(123);
				Ext.apply(operation,{
				params : {'queryBean.frntorbck':'B'}
				});
				}
				}*/
					/*		listeners : {
				beforeload : function() {
					Ext.apply(provinceStore.proxy.extraParams, {
								parentId : "0"
							});
				}
				}*/
				}),
			columns : [{
						// id : 'serviceId',
						name : 'id',
						hidden : true,
						width : 200,
						dataIndex : 'id'
					}, {
						// id : 'svcName',
						name : 'svcName',
						text : '服务名称',
						dataIndex : 'svcName'
					}, {
						// id : 'svcCode',
						name : 'svcCode',
						text : '服务编码',
						width : 200,
						dataIndex : 'svcCode'
					}, {
						// id : 'svcProvdId',
						name : 'svcProvdId',
						text : '服务提供者',
						dataIndex : 'svcProvdId'
					}, {
						// id : 'svcAgrmt',
						name : 'svcAgrmt',
						text : '服务接入协议',
						dataIndex : 'svcAgrmt'
					}, {
						// id : 'svcAddr',
						name : 'svcAddr',
						text : '服务接入地址',
						dataIndex : 'svcAddr',
						width : 300
					}],
			listeners : {
				itemdblclick : function(me, record) {
					var form = Ext.getCmp('monitor_thresholdForm').getForm();
					form.findField('svcCode').setValue(record.data.svcCode);
					form.findField('svcName').setValue(record.data.svcName);
					var id = Ext.getCmp('monitor_svcppointPanel').parentId;
					Ext.getCmp(id).hide();
				}
			},
			// 顶部工具栏
			tbar : [{
				xtype : 'button',
				text : '确定',
				handler : function() {
					var selection = Ext.getCmp('monitor_svcppointPanel')
							.getSelectionModel().getSelection();
					if (selection.length != 1) {
						Ext.MessageBox.alert("提示", "请选中一条记录！");
						return;
					}
					var form = Ext.getCmp('monitor_thresholdForm').getForm();
					form.findField('svcCode').setValue(selection[0]
							.get('svcCode'));
					form.findField('svcName').setValue(selection[0]
							.get('svcName'));
					var id = Ext.getCmp('monitor_svcppointPanel').parentId;
					Ext.getCmp(id).hide();
				}
			}],
			/**
			 * 分页工具栏
			 */
			bbar : Ext.create('Ext.PagingToolbar', {
						store : Ext.data.StoreManager
								.lookup('monitor_backsvcpointStore'),
						displayInfo : true,
						displayMsg : 'Displaying topics {0} - {1} of {2}',
						emptyMsg : "No topics to display"
					})
		});
// 预警人员查询窗口
Ext.define('monitor_noticeUserWindow', {
			width : window.screen.availWidth * 0.4,
			height : window.screen.availHeight * 0.6,
			closeAction : 'hide',
			modal : true,
			extend : 'Ext.window.Window',
			title : '预警人员',
			layout : 'vbox',
			items : [{
				xtype : 'grid',
				id : 'noticePanelA',
				store : Ext.create('noticUserStore', {
							id : 'noticUserStore1'
						}),
				title : '查询预警人员列表',
				width : window.screen.availWidth * 0.39,
				height : window.screen.availHeight * 0.3,
				selModel : Ext.create('Ext.selection.CheckboxModel'),
				frame : true,
				autoScroll : true, // 自动添加滚动条
				stripeRows : true, // 交替行效果
				columns : [{
							//id : 'noticUserId',
							name : 'noticUserId',
							hidden : true,
							text : 'noticUserId',
							dataIndex : 'id'
						}, {
							//id : 'userName',
							name : 'userName',
							text : '姓名',
							dataIndex : 'userName'
						}, {
							//id : 'telPhone',
							name : 'telPhone',
							text : '电话',
							dataIndex : 'telPhone'
						}, {
							//id : 'mobilePhone',
							name : 'mobilePhone',
							text : '移动电话',
							dataIndex : 'mobilePhone'
						}, {
							//id : 'email',
							name : 'email',
							text : '邮件地址',
							dataIndex : 'email'
						}],
				// 顶部工具栏
				tbar : [{
					xtype : 'button',
					text : '确定',
					handler : function() {
						var models = Ext.getCmp('noticePanelA')
								.getSelectionModel().getSelection();
						var store = Ext.data.StoreManager
								.lookup('noticUserStore2');
						//检查是否重复插值
						for (var i = 0; i < models.length; i++) {
							var selectRecord = models[i];
							var index = store.indexOf(selectRecord);
							if (index < 0) {
								store.add(selectRecord);
							}
						}

					}
				}],
				// 分页工具栏
				bbar : Ext.create('Ext.PagingToolbar', {
							store : Ext.data.StoreManager
									.lookup('noticUserStore1'),
							pageSize : 20,
							displayInfo : true,
							displayMsg : '当前记录数为从 第{0}条 - 第{1}条 ，共 {2}条',
							emptyMsg : "无记录"
						})
			}, {
				xtype : 'grid',
				id : 'noticePanelB',
				title : '已选中预警人员列表',
				width : window.screen.availWidth * 0.39,
				height : window.screen.availHeight * 0.25,
				store : Ext.create('noticUserStore', {
							id : 'noticUserStore2',
							autoLoad : false
						}),
				selModel : Ext.create('Ext.selection.CheckboxModel'),
				frame : true,
				autoScroll : true, // 自动添加滚动条
				stripeRows : true, // 交替行效果
				columns : [{
							//id : 'noticUserId',
							name : 'noticUserId',
							hidden : true,
							text : 'noticUserId',
							dataIndex : 'id'
						}, {
							//id : 'userName',
							name : 'userName',
							text : '姓名',
							dataIndex : 'userName'
						}, {
							//id : 'telPhone',
							name : 'telPhone',
							text : '电话',
							dataIndex : 'telPhone'
						}, {
							//id : 'mobilePhone',
							name : 'mobilePhone',
							text : '移动电话',
							dataIndex : 'mobilePhone'
						}, {
							//id : 'email',
							name : 'email',
							text : '邮件地址',
							dataIndex : 'email'
						}],
				// 顶部工具栏
				tbar : [{
					xtype : 'button',
					text : '确定',
					handler : function() {
						var store = Ext.data.StoreManager
								.lookup('noticUserStore2');
						var idList = "";
						var nameList = "";
						//遍历store获取人员名单
						store.each(function(record) {
									idList = idList + record.data.id + ",";
									nameList = nameList + record.data.userName
											+ ",";
								}, null);
						Ext.getCmp('monitor_thresholdForm').getForm()
								.findField('noticeList').setValue(idList);
						Ext.getCmp('monitor_thresholdForm').getForm()
								.findField('noticeUser').setValue(nameList);
						store.removeAll();
						Ext.getCmp('wind_NoticeUser').hide();
					}
				}]
			}]
		});