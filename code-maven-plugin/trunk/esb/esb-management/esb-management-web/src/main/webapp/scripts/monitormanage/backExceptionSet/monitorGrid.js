
// 异常监控设置panel
Ext.define('backServiceGrid', {
	extend : 'Ext.grid.Panel',
	// width : window.screen.availWidth * 0.84,
	// height : window.screen.availHeight * 0.67,
	title : '查询结果',
	frame : true,
	selModel : Ext.create('Ext.selection.CheckboxModel'),
	store : Ext.create('fullThresholdStore', {
				'id' : 'fullThresholdStore',
				listeners : {
					beforeLoad : function(store, operation, e) {

						var data = querySvcpointForm.getForm().getFieldValues();
						Ext.apply(operation, {
									params : {
										'queryBean.frntorbck' : 'B',
										'queryBean.svcName' : data.serviceName,
										'queryBean.svcProvdId' : data.servicerProvider
									}
								});
					}
				}
			}),
	columns : [{
				id : 'id',
				name : 'id',
				hidden : true,
				width : 200,
				dataIndex : 'id'
			}, {
				id : 'svcName',
				name : 'svcName',
				text : '服务名称',
				dataIndex : 'svcName'
			}, {
				id : 'svcCode',
				name : 'svcCode',
				text : '服务编码',
				width : 200,
				dataIndex : 'svcCode'
			}, {
				id : 'svcProvider',
				name : 'svcProvider',
				text : '服务提供方',
				dataIndex : 'svcProvdId'
			}/*, {
				id : 'threshold',
				name : 'threshold',
				text : '响应时间阀值',
				dataIndex : 'threshold'
			}*/, {
				id : 'channel',
				name : 'channel',
				text : '预警渠道',
				dataIndex : 'channel',
				width : 100
			}, {
				id : 'noticeUserList',
				name : 'noticeUserList',
				text : '预警通知人员',
				dataIndex : 'personName',
				// html:'testhtml',
				width : 300
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
			Ext.MessageBox.confirm('确认框', '你确认删除选择的记录？', function(
							btn) {
						if (btn == "yes") {
							Ext.Ajax.request({
										url : '../exceptionMonitor/deleteThreshold.action',
										params : {
											'queryBean.personId' : svcCodes
										},
										success : function(response) {
											var result = Ext
													.decode(response.responseText);
											Ext.MessageBox.alert('',
													result.message);
											Ext.data.StoreManager
													.lookup('fullThresholdStore')
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
			var url = '../exceptionMonitor/updateThreshold.action';
			openSvcpointForm(url, record);
		}
	}, {
		xtype : 'button',
		text : '新增',
		handler : function() {
			openSvcpointForm('../exceptionMonitor/addThreshold.action');
		}
	}],
/**
 * 分页工具栏
 */
	bbar : Ext.create('Ext.PagingToolbar', {
				store : Ext.data.StoreManager.lookup('fullThresholdStore'),
				displayInfo : true,
				displayMsg : '显示第{0}行到 {1} 行的记录，一共 {2}行',
				emptyMsg : "没有记录"
			})
});
// 打开性能监控编辑界面
function openSvcpointForm(url, parm) {
	var winID = "win_threshold";
	var record = parm;
	if (!record) {
		record = new fullThresholodModel();
	}
	if (!Ext.getCmp(winID)) {
		Ext.create('Ext.window.Window', {
					title : '服务性能监控',
					resizable : false,// 窗口大小不可变
					modal : true,// 模态对话框
					id : winID,
					closeAction : 'hide',
					width : window.screen.availWidth * 0.4,
					height : window.screen.availHeight * 0.3,
					layout : 'fit',
					items : [Ext.create('monitor_thresholdForm', {
								'id' : 'monitor_thresholdForm',
								'record' : record,
								'url' : url
							})]
				});
	}
	Ext.getCmp('monitor_thresholdForm').getForm().loadRecord(record);
	Ext.getCmp(winID).show();
	if (parm) {
		Ext.getCmp('monitor_thresholdForm').getForm().findField('svcProvdId')
				.setDisabled(true);
		// Ext.getCmp('monitor_thresholdForm').getForm().findField('svcName').setDisabled(true);
		// Ext.getCmp('monitor_thresholdForm').getForm().findField('svcCode').setDisabled(true);
		Ext.getCmp('querySvcpointButton').setDisabled(true);
	} else {
		Ext.getCmp('monitor_thresholdForm').getForm().findField('svcProvdId')
				.setDisabled(false);
		// Ext.getCmp('monitor_thresholdForm').getForm().findField('svcName').setDisabled(false);
		// Ext.getCmp('monitor_thresholdForm').getForm().findField('svcCode').setDisabled(false);
		Ext.getCmp('querySvcpointButton').setDisabled(false);
	}
	Ext.getCmp('monitor_thresholdForm').url = url;
}
