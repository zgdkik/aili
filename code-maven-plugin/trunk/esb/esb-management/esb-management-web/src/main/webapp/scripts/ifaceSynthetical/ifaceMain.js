// =================定义组件======================


Ext.define('esbviewModel', {
			extend : 'Ext.data.Model',
			fields : ['fid', 'name', 'esbSvcCode', 'backSvcCode', 'totalCount',
					'successCount', 'exceptionCount', 'incompletestatuscount',
					'startDate', 'endDate','type']
		});

// 配置编码store
Ext.define('viewModelStore', {
			extend : 'Ext.data.Store',
			model : 'esbviewModel',
			proxy : {
				type : 'ajax',
				url : '../synthetical/viewList.action',
				reader : {
					type : 'json',
					root : 'viewList',
					totalProperty : 'resultCount'// 总的数据条数
				}
			}
		});

Ext.define('ESB2.type.store',{
	extend:'Ext.data.Store',
	fields:['type'],
	data:[{'type':'JMS'},{'type':'WebService'}]
});

Ext.define('ifaceSyntheticalQueryForm', {
	extend : 'Ext.form.Panel',
	frame : true,
	title : '查询条件',
	width : '100%',
	layout : {
		type : 'table',
		columns : 4
	},
	items : [{
				xtype : 'combo',
				fieldLabel : '前端服务编码',
				name : 'servCode',
				rowspan : 1,
				emptyText : 'esbSvcCode',
				queryMode: 'local',
				displayField: 'esbSvcCode',
    			valueField: 'esbSvcCode'
				//renderTo : Ext.getBody()
			}, {
				xtype : 'combo',
				fieldLabel : '后端服务编码',
				name : 'backCode',
				rowspan : 1,
				queryMode: 'local',
				emptyText : 'backSvcCode',
				valueField : 'esbSvcCode'
			}, {
						xtype : 'combo',
						width : 250,
						store : Ext.create('ESB2.type.store'),
						editable : false,
						displayField : 'type',
						valueField : 'type',
						fieldLabel : '协议类型',
						name : 'type',
						queryMode: 'local',
						emptyText : '---请选择---',
						forceSelection : true
			}, {
				xtype : 'datetimefield',
				fieldLabel : '开始时间',
				format : 'Y-m-d H:i',
				editable : false,
				allowBlank : false,
				name : 'startTime',
				rowspan : 1,
				listeners : {
					select : function(th) {
						var fromDate = th.getValue();
						var toDate = Ext.getCmp('ifaceSyntheticalQueryForm_Id')
								.getForm().findField('endTime').getValue();
						if (!isValidStartDataAndEndDate(fromDate, toDate)) {
							th.reset();
							alert("开始时间必须小于结束时间,结束时间小于当前时间");
							Ext.getCmp('ifaceSyntheticalQueryForm_Id')
									.getForm().findField('endTime').reset();
						}
					}
				}
			}, {
				xtype : 'datetimefield',
				fieldLabel : '结束时间',
				name : 'endTime',
				id : 'endTime',
				format : 'Y-m-d H:i',
				editable : false,
				allowBlank : false,
				rowspan : 1,
				listeners : {
					select : function(th) {
						var fromDate = th.getValue();
						var toDate = Ext.getCmp('ifaceSyntheticalQueryForm_Id')
								.getForm().findField('startTime').getValue();
						if (!isValidStartDataAndEndDate(fromDate, toDate)) {
							th.reset();
							alert("开始时间必须小于结束时间,结束时间小于当前时间");
							Ext.getCmp('ifaceSyntheticalQueryForm_Id')
									.getForm().findField('startTime').reset();
						}
					}
				}
			}, {
				xtype : 'button',
				text : '查询',
				rowspan : 1,
				listeners : {
					click : function() {
						var store = Ext.data.StoreManager
								.lookup('viewModelStore_id');
						store.loadPage(1, {
							callback : function(records, operations, success) {
								var rawData = Ext.data.StoreManager.lookup('viewModelStore_id').store.proxy.reader.rawData;
								if (!success) {
									Ext.Msg.alert("查询失败", rawData.tips);
								}
							}
						});
					}
				}
			}, {
				xtype : 'button',
				text : '清空查询条件',
				rowspan : 1,
				handler:function(){
					Ext.getCmp('ifaceSyntheticalQueryForm_Id').getForm().reset();
				}
			}]
});
Ext.define('ifaceSyntheticalGrid', {
			extend : 'Ext.grid.GridPanel',
			frame : true,
			title : '接口综合信息',
			width : '100%',
			selModel : Ext.create('Ext.selection.CheckboxModel'),
			columns : [{
						name : 'id',
						hidden : true,
						dataIndex : 'fid'
					}, {
						name : 'ifaceName',
						header : '接口名',
						width : 200,
						dataIndex : 'name'
					}, {
						name : 'esbSvcCode',
						header : '前端服务编码',
						width : 200,
						dataIndex : 'esbSvcCode'
					}, {
						name : 'backSvcCode',
						header : '后端服务编码',
						width : 200,
						dataIndex : 'backSvcCode'
					}, {
						name : 'type',
						header : '类型',
						width : 200,
						dataIndex : 'type'
					}, {
						name : 'totalCount',
						header : '调用次数',
						width : 80,
						dataIndex : 'totalCount'
					}, {
						name : 'successCount',
						header : '成功次数',
						width : 80,
						dataIndex : 'successCount'
					}, {
						name : 'exceptinCount',
						header : '异常次数',
						width : 80,
						dataIndex : 'exceptionCount'
					}, {
						name : 'isFullStatus',
						header : '状态不完整',
						width : 80,
						dataIndex : 'incompletestatuscount'
					}, {
						name : 'startDate',
						header : '统计开始时间',
						width : 200,
						dataIndex : 'startDate',
						renderer : function(value) {
							var date = new Date(value);
							return Ext.Date.format(date, 'Y-m-d');
						}
					}, {
						name : 'endDate',
						header : '统计截止时间',
						width : 200,
						dataIndex : 'endDate',
						renderer : function(value) {
							var date = new Date(value);
							return Ext.Date.format(date, 'Y-m-d');
						}
					}]

		});

// ====================定义结束=====================

// ====================函数定义=====================
function isValidStartDataAndEndDate(startDate, endDate) {
	if (Ext.isEmpty(startDate) || Ext.isEmpty(endDate)) {
		return true;
	}
	var d = new Date();
	var startDateTime = startDate.getTime();
	var endDateTime = endDate.getTime();
	if (startDateTime > d.getTime() || endDateTime > d.getTime()) {
		return true;
	}
	if (startDateTime < endDateTime) {
		return false;
	} else {
		return true;
	}
};

// ====================函数定结束=====================

// ====================加载界面=====================
Ext.onReady(function() {
	Ext.QuickTips.init();
	// 开启动态加载
	Ext.Loader.setConfig({
				enabled : true
			});
	var queryForm = Ext.create('ifaceSyntheticalQueryForm', {
				id : 'ifaceSyntheticalQueryForm_Id',
				region : 'north'
			});
	var mystore = Ext.create('viewModelStore', {
				id : 'viewModelStore_id',
				listeners : {
					beforeload : function(mystore, operation, eOpts) {
						var form = Ext.getCmp('ifaceSyntheticalQueryForm_Id');
						var params = {
							'statisticQueryBean.esbSvcCode' : form.getForm()
									.findField('servCode').getValue(),
							'statisticQueryBean.backSvcCode' : form.getForm()
									.findField('backCode').getValue(),
							'statisticQueryBean.startDate' : form.getForm()
									.findField('startTime').getValue(),
							'statisticQueryBean.endDate' : form.getForm()
									.findField('endTime').getValue(),
							'statisticQueryBean.type' : form.getForm()
									.findField('type').getValue()
						};
						Ext.apply(operation, {
									params : params
								});
					}
				}
			});
	//界面打开时默认查询前一天的统计数据
	var longValue =1000*60*60*24;
	var now = new Date();
	var yesterdayMoning = new Date(now.getTime()-longValue);
	yesterdayMoning.setHours(0);
	yesterdayMoning.setMinutes(0);
	yesterdayMoning.setSeconds(0);
	var yesterdaynight = new Date(now.getTime()-longValue);
	yesterdaynight.setHours(23);
	yesterdaynight.setMinutes(59);
	yesterdaynight.setSeconds(59);
	var form = Ext.getCmp('ifaceSyntheticalQueryForm_Id');
	form.getForm().findField('startTime').setValue(yesterdayMoning);
	form.getForm().findField('endTime').setValue(yesterdaynight);
	mystore.load({
				params : {
					'start' : 0,
					'limit' : 10
				}
			});

	Ext.create('Ext.Viewport', {
		layout : 'border',
		items : [queryForm, Ext.create('ifaceSyntheticalGrid', {
							id : 'ifaceSyntheticalGrid',
							store : mystore,
							region : 'center',
							bbar : Ext.create('Ext.PagingToolbar', {
										store : mystore,
										displayInfo : true,
										displayMsg : '显示第 {0} 到- {1}条记录，一共  {2}条记录',
										emptyMsg : "没有记录"
									})
						})]
	});

});
// ==========================加载结束=======================
