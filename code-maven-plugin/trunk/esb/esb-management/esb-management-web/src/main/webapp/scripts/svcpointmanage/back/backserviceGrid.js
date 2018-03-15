function setGridCellValue(value) {
	if (value) {
		return '是'
	} else {
		return '否';
	}
}
// 服务Grid
Ext.define('backServiceGrid', {
	extend : 'Ext.grid.Panel',
	// width : window.screen.availWidth * 0.84,
	// height : window.screen.availHeight * 0.67,
	title : '查询结果',
	frame : true,
	selModel : Ext.create('Ext.selection.CheckboxModel'),
	store : Ext.create('serviceStore', {
				'id' : 'backsvcpointStore'
			}),
	columns : [{
				id : 'serviceId',
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
				id : 'svcProvdId',
				name : 'svcProvdId',
				text : '服务提供者',
				dataIndex : 'svcProvdId'
			}, {
				id : 'svcAgrmt',
				name : 'svcAgrmt',
				text : '服务接入协议',
				dataIndex : 'svcAgrmt'
			}, {
				id : 'svcAddr',
				name : 'svcAddr',
				text : '服务接入地址',
				dataIndex : 'svcAddr',
				width : 300
			}, {
				header : '返回类型',
				dataIndex : 'responseType',
				name : 'responseType'
			}, {
				header : '异常消息是否自动重发',
				dataIndex : 'isAutoResend',
				name : 'isAutoResend',
				renderer : function(value) {
					return  setGridCellValue(value);
				}
			}, {
				header : '性能日志是否记录原始消息',
				dataIndex : 'isRcdOrgBody',
				name : 'isRcdOrgBody',
				renderer : function(value) {
					return  setGridCellValue(value);
				}
			}, {
				header : '是否记录性能日志',
				dataIndex : 'isRcdPfmcLog',
				name : 'isRcdPfmcLog',
				renderer : function(value) {
					return  setGridCellValue(value);
				}
			}, {
				header : '是否记录异常日志',
				dataIndex : 'isRcdErrLog',
				name : 'isRcdErrLog',
				renderer : function(value) {
					return  setGridCellValue(value);
				}
			}, {
				header : '异常是否立即通知',
				dataIndex : 'promptlyNotify',
				name : 'promptlyNotify',
				renderer : function(value) {
					return  setGridCellValue(value);
				}
			}],
	// 顶部工具栏
	tbar : [{
		xtype : 'button',
		text : '删除',
		handler : function() {
			var selection = Ext.getCmp('backsvcGrid1').getSelectionModel()
					.getSelection();
			if (selection.length == 0) {
				Ext.MessageBox.alert("提示", "请选中至少一条记录！");
				return;
			}
			var svcCodes = "";
			for (var i = 0; i < selection.length; i++) {
				svcCodes = svcCodes + selection[i].get('svcCode') + ",";
			}
			Ext.MessageBox.confirm('确认框', '你确认删除选择的记录？' , function(
							btn) {
						if (btn == "yes") {
							Ext.Ajax.request({
										url : 'deleteSvcpoint.action',
										params : {
											svcCodes : svcCodes
										},
										success : function(response) {
											var result = Ext
													.decode(response.responseText);
											Ext.MessageBox.alert('',
													result.message);
											Ext.data.StoreManager
													.lookup('backsvcpointStore')
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
			var selection = Ext.getCmp('backsvcGrid1').getSelectionModel()
					.getSelection();
			if (selection.length != 1) {
				Ext.MessageBox.alert("提示", "请选中一条记录进行更新！");
				return;
			}
			var record = selection[0];
			var url = 'updateSvcpoint.action';
			openSvcpointForm(url, record);
		}
	}, {
		xtype : 'button',
		text : '新增',
		handler : function() {
			openSvcpointForm('addSvcpoint.action');
		}
	}],
	/**
	 * 分页工具栏
	 */
	bbar : Ext.create('Ext.PagingToolbar', {
				store : Ext.data.StoreManager.lookup('backsvcpointStore'),
				displayInfo : true,
				displayMsg : 'Displaying topics {0} - {1} of {2}',
				emptyMsg : "No topics to display"
			})
});
// 打开svcpoint编辑界面
function openSvcpointForm(url, parm) {
	var record = parm;
	if (!record) {
		record = new SvcPointInfoModel();
	}
	if (!Ext.getCmp('win_addsvcpoint')) {
		Ext.create('Ext.window.Window', {
					title : 'svcpoint编辑',
					//resizable : false,// 窗口大小不可变
					modal : true,// 模态对话框
					id : 'win_addsvcpoint',
					closeAction : 'hide',
					width : window.screen.availWidth * 0.5,
					height : window.screen.availHeight * 0.3,
					
					layout : 'border',
					items : [Ext.create('svcpointForm', {
								region:'center',
								'id' : 'svcpointForm',
								'record' : record,
								'url' : url
							})]
				});
	}
	Ext.getCmp('win_addsvcpoint').show();
	Ext.getCmp('svcpointForm').getForm().loadRecord(new SvcPointInfoModel());
	Ext.getCmp('svcpointForm').getForm().loadRecord(record);
	if (parm) {
		Ext.getCmp('svcode1').setDisabled(true);
	} else {
		Ext.getCmp('svcode1').setDisabled(false);
	}
	Ext.getCmp('svcpointForm').url = url;
}