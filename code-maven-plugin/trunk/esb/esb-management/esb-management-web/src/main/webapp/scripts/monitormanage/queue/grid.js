
// 队列监控panel
Ext.define('backServiceGrid', {
	extend : 'Ext.grid.Panel',
	title : '查询结果',
	frame : true,
	selModel : Ext.create('Ext.selection.CheckboxModel'),
	store : Ext.create('queueStore', {
				'id' : 'queueStore',
				listeners : {
					beforeload : function(store, operation, opts) {
						var form = Ext.getCmp('queryForm').getForm();
						if(form){
								Ext.apply(operation, {
									params : {
										"bean.queue" : form.findField('queue').getValue()
									}
								});
						}

					}
				}
			}),
	columns : [{
				id : 'id',
				name : 'id',
				hidden : true,
				text : '主键Id',
				dataIndex : 'id'
			}, { 
				id : 'qmgr',
				name:'qmgr',
				text:'队列管理器',
				dataIndex:'qmgr' 
			}, {
				id : 'queue',
				name : 'queue',
				text : '队列名称',
				dataIndex : 'queue'
			}, {
				id : 'threshold',
				name : 'threshold',
				text : '队列深度',
				dataIndex : 'threshold'
			}, {
				id : 'channelId',
				name : 'channelId',
				text : '预警渠道',
				dataIndex : 'channelId'
			}, {
				id : 'personName',
				name : 'personName',
				text : '预警通知人员',
				width : 137,
				dataIndex : 'personName'
			}, {
				id : 'personId',
				hidden : true,
				dataIndex : 'personId',
				name : 'personId'
			}, {
				id : 'clusters',
				name : 'clusters',
				text : '是否集群',
				dataIndex : 'clusters'
			}, {
				id : 'comparetime',
				name : 'comparetime',
				text : '比较次数',
				dataIndex : 'comparetime'
			}, {
				id : 'volatility',
				name : 'volatility',
				text : '浮动值',
				dataIndex : 'volatility'
			}, {
				id : 'maxDepth',
				name : 'maxDepth',
				text : '最大队列深度',
				dataIndex : 'maxDepth'
			}, {
				id : 'pjVersion',
				name : 'pjVersion',
				text : '项目版本',
				dataIndex : 'pjVersion'
			}],
	// 顶部工具栏
	tbar : [{
		xtype : 'button',
		text : '删除',
		handler : function() {
			var selection = Ext.getCmp('backServiceGrid').getSelectionModel()
					.getSelection();
			if (selection.length == 0) {
				Ext.MessageBox.alert("提示", "请选中至少一条记录！");
				return;
			}
			var svcCodes = "";
			for (var i = 0; i < selection.length; i++) {
				svcCodes = svcCodes + selection[i].get('id') + ",";
			}
			Ext.MessageBox.confirm('确认框', '你确认删除选择的记录？', function(btn) {
						if (btn == "yes") {
							Ext.Ajax.request({
										url : '../queue/deleteQueueMonitor.action',
										params : {
											ids : svcCodes
										},
										success : function(response) {
											var result = Ext
													.decode(response.responseText);
											Ext.MessageBox.alert('',
													result.message);
											Ext.data.StoreManager
													.lookup('queueStore')
													.remove(selection);
										},
										failure : function(response) {
											var result = Ext
													.decode(respone.responseText);
											Ext.MessageBox.alert('', result);
										}
									});
						}
					});

		}
	}, {
		xtype : 'button',
		text : '更新',
		handler : function() {
			var selection = Ext.getCmp('backServiceGrid').getSelectionModel()
					.getSelection();
			if (selection.length != 1) {
				Ext.MessageBox.alert("提示", "请选中一条记录进行更新！");
				return;
			}
			var record = selection[0];
			var url = '../queue/updateQueueMonitor.action';
			openSvcpointForm(url, record);
		}
	}, {
		xtype : 'button',
		text : '新增',
		handler : function() {
			openSvcpointForm('../queue/addQueueMonitor.action');
		}
	}],
	/**
	 * 分页工具栏
	 */
	bbar : Ext.create('Ext.PagingToolbar', {
				store : Ext.data.StoreManager.lookup('queueStore'),
				displayInfo : true,
				displayMsg : '显示第 {0} 条到第{1}记录，一共{2}条记录',
				emptyMsg : "没有记录"
			})
});
// 队列监控设置编辑界面
function openSvcpointForm(url, parm) {
	var winID = "win_threshold";
	var record = parm;
	if (!record) {
		record = new queueModel();
	}
	if (!Ext.getCmp(winID)) {
		Ext.create('Ext.window.Window', {
					title : '队列监控设置',
					resizable : false,// 窗口大小不可变
					modal : true,// 模态对话框
					id : winID,
					closeAction : 'hide',
					width : window.screen.availWidth * 0.4,
					height : window.screen.availHeight * 0.45,
					layout : 'fit',
					items : [Ext.create('monitor_thresholdForm', {
								'id' : 'monitor_thresholdForm',
								'record' : record,
								'url' : url
							})]
				});
	}
	Ext.getCmp(winID).show();
	Ext.getCmp('monitor_thresholdForm').getForm().loadRecord(record);
	if (parm) {
		Ext.getCmp('monitor_thresholdForm').getForm().findField('queue')
				.setDisabled(true);
		Ext.getCmp('monitor_thresholdForm').getForm().findField('qmgr')
				.setDisabled(true);
	} else {
		Ext.getCmp('monitor_thresholdForm').getForm().findField('queue')
				.setDisabled(false);
		Ext.getCmp('monitor_thresholdForm').getForm().findField('qmgr')
				.setDisabled(false);
	}
	Ext.getCmp('monitor_thresholdForm').url = url;
}
