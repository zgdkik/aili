// ==============queue监控编辑form================
Ext.define('monitor_thresholdForm', {
	extend : 'Ext.form.Panel',
	frame : true,
	// width : window.screen.availWidth * 0.38,
	// height : window.screen.availHeight * 0.25,
	layout : 'border',
	items : [{
		xtype : 'panel',
		id : 'leftPanel',
		region : 'north',
		defaultType : 'textfield',
		frame : true,
		defaults : {
			anchor : '100%',
			width : 200
		},
		items : [{
					fieldLabel : 'id',
					width : 250,
					name : 'id',
					hidden : true
				},{
					fieldLabel : '队列管理器',
					width : 250,
					name : 'qmgr',
					emptyText : '请输入队列管理器名',
					allowBlank : false
				}, {
					fieldLabel : '队列名称',
					width : 250,
					name : 'queue',
					emptyText : '请输入队列名',
					allowBlank : false
				}, {
					fieldLabel : '队列深度',
					width : 250,
					regex : /^\d*$/,
					name : 'threshold',
					emptyText : '请输入深度阈值',
					allowBlank : false,
					id : 'threshold'
				}, /*{
					id : 'clusters',
					name : 'clusters',
					width : 250,
					fieldLabel : '是否集群',
					dataIndex : 'clusters'
				},*/{
					xtype : 'combo',
					width : 250,
					store : Ext.create('clustersStore'),
					displayField : 'clusters',
					editable : false,
					valueField : 'clusters',
					fieldLabel : '是否集群',
					emptyText : '---请选择---',
					id : 'clusters',
					name : 'clusters',
					allowBlank : false,
					forceSelection : true
				}, {
					id : 'comparetime',
					name : 'comparetime',
					width : 250,
					fieldLabel : '比较次数',
					emptyText : '请输入比较次数',
					dataIndex : 'comparetime'
				}, {
					id : 'volatility',
					name : 'volatility',
					width : 250,
					fieldLabel : '浮动值',
					emptyText : '请输入浮动',
					dataIndex : 'volatility'
				}, {
					id : 'maxDepth',
					name : 'maxDepth',
					width : 250,
					fieldLabel : '最大队列深度',
					emptyText : '请输入最大深度阀值',
					dataIndex : 'maxDepth'
				}, {
					xtype : 'combo',
					width : 250,
					store : Ext.create('warningChannelStore'),
					displayField : 'channelName',
					editable : false,
					valueField : 'channelName',
					fieldLabel : '预警渠道',
					id : 'channelId',
					name : 'channelId',
					emptyText : '---请选择---',
					allowBlank : false,
					forceSelection : true
				}, {
					xtype : 'combo',
					width : 250,
					store : Ext.create('pjVersionStore'),
					displayField : 'pjVersion',
					editable : false,
					valueField : 'pjVersion',
					fieldLabel : '版本号',
					name : 'pjVersion',
					id : 'pjVersion',
					emptyText : '---请选择---',
					forceSelection : true,
					allowBlank : false
				}, {
					xtype : 'button',
					text : '选择人员',
					width : 60,
					handler : function() {
						var windowId = 'wind_noticeUSer';
						if (!Ext.getCmp(windowId)) {
							Ext.create('Ext.window.Window', {
										resizable : false,
										layout : 'fit',
										title : '选择预警人员',
										id : windowId,
										closeAction : 'hide',
										width : window.screen.availWidth * 0.5,
										height : window.screen.availHeight
												* 0.5,
										items : [Ext
												.create('EsbPerformanceMonitorPanel')]
									});
						}
						Ext.getCmp(windowId).show();
					}

				},
				// 隐藏域：队列监控设置记录id、预警人员id列表
				{
					xtype : 'field',
					hidden : true,
					name : 'id',
					id : 'id'
				}, {
					xtyp : 'field',
					hidden : true,
					name : 'personId',
					id : 'personId'
				}]
	}, {
		xtype : 'panel',
		id : 'rightPanel',
		region : 'center',
		frame : true,
		title : '预警人员',
		layout : 'hbox',
		items : [{
					xtype : 'textarea',
					name : 'personName',
					width : window.screen.availWidth * 1,
					disabled : true,
					emptyText : '点击上面“选择人员”按钮添加人员 ',
					allowBlank : false,
					id : 'personName',
					editable : false
				}]
	}],
	bbar : [{
		xtype : 'button',
		border : true,
		text : '提交',
		width : 60,
		handler : function() {
			var form = Ext.getCmp('monitor_thresholdForm').getForm();
			if (!form.isValid()) {
				Ext.MessageBox.alert('', '校验失败，请检查输入是否正确');
			} else {
				Ext.Ajax.request({
							url : Ext.getCmp('monitor_thresholdForm').url,
							params : {
								/*
								 * 'queue.qmgr' : form.findField('qmgr')
								 * .getValue(),
								 */
								'queue.queue' : form.findField('queue')
										.getValue(),
								'queue.channelId' : form.findField('channelId')
										.getValue(),
								'queue.threshold' : form.findField('threshold')
										.getValue(),
								'queue.personId' : form.findField('personId')
										.getValue(),
								'queue.fid' : form.findField('id').getValue(),
								'queue.qmgr' : form.findField('qmgr').getValue(),
								'queue.clusters' : form.findField('clusters').getValue(),
								'queue.comparetime' : form.findField('comparetime').getValue(),
								'queue.volatility' : form.findField('volatility').getValue(),
								'queue.maxDepth' : form.findField('maxDepth').getValue(),
								'queue.pjVersion' : form.findField('pjVersion').getValue()
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
		border : true,
		width : 60,
		text : '重置',
		handler : function() {
			var model = Ext.getCmp('backServiceGrid').getSelectionModel()
					.getSelection()[0];
			Ext.getCmp('monitor_thresholdForm').getForm().loadRecord(model);
			
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
var queryForm = Ext.create('Ext.form.Panel', {
			id : 'queryForm',
			frame : true,
			region : 'north',
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
			items : [/*
						 * { xtype : 'combo', width : 250, store :
						 * Ext.create('qmgrStore'), displayField : 'name',
						 * valueField : 'name', fieldLabel : '队列管理器', editable :
						 * false, name : 'qmgr', emptyText : '---请选择---',
						 * colspan : 1 },
						 */{
						fieldLabel : '队列名称',
						width : 250,
						name : 'queue',
						colspan : 1
					}, {
						xtype : 'button',
						text : '查询',
						style : 'margin-left:0px;',
						width : 60,
						handler : function() {
							Ext.data.StoreManager.lookup('queueStore').load({
								params : {
									"bean.queue" : Ext.getCmp('queryForm')
											.getForm().findField('queue')
											.getValue()
/*
 * , "bean.qmgr" : Ext.getCmp('queryForm') .getForm().findField('qmgr')
 * .getValue()
 */
								}
							});
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
					/*
					 * listener:{ beforeload:function(store,operation,e){
					 * alert(123); Ext.apply(operation,{ params :
					 * {'queryBean.frntorbck':'B'} }); } }
					 */
					/*
					 * listeners : { beforeload : function() {
					 * Ext.apply(provinceStore.proxy.extraParams, { parentId :
					 * "0" }); } }
					 */
				}),
			columns : [{
						name : 'id',
						hidden : true,
						width : 200,
						dataIndex : 'id'
					}, {
						name : 'svcName',
						text : '服务名称',
						dataIndex : 'svcName'
					}, {
						name : 'svcCode',
						text : '服务编码',
						width : 200,
						dataIndex : 'svcCode'
					}, {
						name : 'svcProvdId',
						text : '服务提供者',
						dataIndex : 'svcProvdId'
					}, {
						name : 'svcAgrmt',
						text : '服务接入协议',
						dataIndex : 'svcAgrmt'
					}, {
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
							name : 'noticUserId',
							hidden : true,
							text : 'noticUserId',
							dataIndex : 'id'
						}, {
							name : 'userName',
							text : '姓名',
							dataIndex : 'userName'
						}, {
							name : 'telPhone',
							text : '电话',
							dataIndex : 'telPhone'
						}, {
							name : 'mobilePhone',
							text : '移动电话',
							dataIndex : 'mobilePhone'
						}, {
							name : 'email',
							text : '邮件地址',
							dataIndex : 'email'
						}, {
							name : 'pjVersion',
							text : '版本号',
							dataIndex : 'pjVersion'
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
						// 检查是否重复插值
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
							// id : 'noticUserId',
							name : 'noticUserId',
							hidden : true,
							text : 'noticUserId',
							dataIndex : 'id'
						}, {
							// id : 'userName',
							name : 'userName',
							text : '姓名',
							dataIndex : 'userName'
						}, {
							// id : 'telPhone',
							name : 'telPhone',
							text : '电话',
							dataIndex : 'telPhone'
						}, {
							// id : 'mobilePhone',
							name : 'mobilePhone',
							text : '移动电话',
							dataIndex : 'mobilePhone'
						}, {
							// id : 'email',
							name : 'email',
							text : '邮件地址',
							dataIndex : 'email'
						}, {
							// id : 'pjVersion',
							name : 'pjVersion',
							text : '版本号',
							dataIndex : 'pjVersion'
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
						// 遍历store获取人员名单
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