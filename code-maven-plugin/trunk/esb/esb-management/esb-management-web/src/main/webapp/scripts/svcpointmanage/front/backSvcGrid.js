
// 服务Grid
Ext.define('backServiceGrid', {
	extend : 'Ext.grid.Panel',
	// width : window.screen.availWidth * 0.4,
	// height : window.screen.availHeight * 0.6,
	title : '查询结果',
	frame : true,
	selModel : Ext.create('Ext.selection.CheckboxModel'),
	store : Ext.create('svcpointRelationStore', {
				'id' : 'svcpointRelationStore',
				listeners : {
					beforeload : function(store, records, options) {
						var svccode = Ext.getCmp('frontSvcpointForm').getForm()
								.findField('frontsvcCode').getValue();
						Ext.apply(store.proxy.extraParams, {'frontSvcCode' : svccode});
					}
				}
			}),
	columns : [{
				name : 'id',
				hidden : true,
				width : 200,
				dataIndex : 'id'
			}, {
				name : 'frontSvcCode',
				header : 'esb端服务编码',
				dataIndex : 'frontSvcCode'
			}, {
				name : 'backSvcCode',
				header : '后端服务编码',
				width : 200,
				dataIndex : 'backSvcCode'
			}],
	// 顶部工具栏
	tbar : [{
		xtype : 'button',
		text : '删除关联',
		handler : function() {
			var selection = Ext.getCmp('backServiceGrid').getSelectionModel().getSelection();
			if (selection.length == 0) {
				Ext.MessageBox.alert("提示", "请选中至少一条记录！");
				return;
			}
			var ids = "";
			for (var i = 0; i < selection.length; i++) {
				ids = ids + selection[i].get('id') + ",";
			}
			Ext.MessageBox.confirm('确认框', '你确认删除选择的记录?:', function(
							btn) {
						if (btn == "yes") {
							Ext.Ajax.request({
										url : '../svcpoint/deleteRelation.action',
										params : {
											'ids' : ids
										},
										success : function(response) {
											var result = Ext
													.decode(response.responseText);
											Ext.MessageBox.alert('',
													result.message);
											Ext.data.StoreManager
													.lookup('svcpointRelationStore')
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
		text : '新增关联',
		handler : function() {
			var svccode = Ext.getCmp('frontSvcpointForm').getForm()
					.findField('frontsvcCode').getValue();
			if (svccode == "") {
				Ext.MessageBox.alert('', "请输入前端服务编码");
			} else {
				addNewRelation(null);
			}
		}
	},{
		xtype:'button',
		text:'刷新',
		handler:function(){
			Ext.data.StoreManager.lookup('svcpointRelationStore').load();
		}
	}]
});

// 新增关联关系
function addNewRelation(svcCode) {
	var winId = "win_addRelation";
	if (!Ext.getCmp(winId)) {
		Ext.create('Ext.window.Window', {
			title : '查询后端配置',
			resizable : false,// 窗口大小不可变
			modal : true,// 模态对话框
			id : winId,
			closeAction : 'hide',
			items : [Ext.create('Ext.form.Panel', {
				id : 'backSvcForm1',
				frame : 'true',
				height : window.screen.availHeight * 0.15,
				width : window.screen.availWidth * 0.3,
				items : [{
					xtype : 'combo',
					store : Ext.create('serviceProviderStore',{
						filters:[Ext.create('Ext.util.Filter', {filterFn: function(item) { return item.get("name") !='ESB'; }, root: 'data'})]
					}),
					fieldLabel : '后端服务提供方',
					editable : false,
					displayField : 'name',
					valueField : 'name',
					emptyText : '---请选择---',
					name : 'backSvcprovider',
					listeners : {
						select : function(combo, record) {
							var svcProvdId = record[0].data.name;
							var store = Ext.getCmp('backSvcForm1').getForm()
									.findField('backSvcName').getStore();
							Ext.getCmp('backSvcForm1').getForm()
									.findField('backSvcName').setValue(null);
							store.removeAll(false);
							store.load({
										params : {
											'svcPointInfo.svcProvdId' : svcProvdId
										}
									});
						}
					}
				}, {
					xtype : 'combo',
					fieldLabel : '后端服务名称',
					displayField : 'svcName',
					valueField : 'svcCode',
					emptyText : '---请选择---',
					editable : false,
					store : Ext.create('serviceStore'),
					name : 'backSvcName',
					listConfig : {
						loadMask : false
					}
				}, {
					xtype : 'button',
					text : '保存',
					handler : function() {
						addRelation();
					}
				}, {
					xtype : 'button',
					text : '清空',
					handler : function() {
						Ext.getCmp('backSvcForm1').getForm().reset();
					}
				}]
			})]
		});
	}
	Ext.getCmp(winId).show();
}

function addRelation() {
	var frontSvccode = Ext.getCmp('frontSvcpointForm').getForm()
			.findField('frontsvcCode').getValue();
	var backSvccode = Ext.getCmp('backSvcForm1').getForm()
			.findField('backSvcName').getValue();
	Ext.Ajax.request({
		url : '../svcpoint/addRelation.action',
		params : {
			'svcPointRelationInfo.frontSvcCode' : frontSvccode,
			'svcPointRelationInfo.backSvcCode' : backSvccode
		},
		success : function(response) {
			var result = Ext.decode(response.responseText);
			Ext.MessageBox.alert('', result.message);
			Ext.data.StoreManager.lookup('svcpointRelationStore').load();
		},
		fail : function(response) {
			var result = Ext.decode(response.responseText);
			Ext.MessageBox.alert('', result.message);
		}
	});
}