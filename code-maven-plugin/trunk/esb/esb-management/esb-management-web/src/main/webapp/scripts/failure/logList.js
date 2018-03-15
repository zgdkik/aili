
//========界面开始==========
// 查询panel
Ext.define('LogQueryForm', {
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
				xtype : 'datetimefield',
				format : 'Y-m-d H:i',
				editable : false,
				allowBlank : false,
				name : 'startTime',
				labelWidth : 100,
				fieldLabel : '开始时间:',
				labelSeparator : '',
				listeners : {
					select : function(th) {
						var fromDate = th.getValue();
						var toDate = Ext.getCmp('failureQueryForm').getForm()
								.findField('endTime').getValue();
						if (!isValidStartDataAndEndDate(fromDate, toDate)) {
							showMessage("开始时间必须小于结束时间,结束时间小于当前时间");
							th.reset();
							Ext.getCmp('failureQueryForm').getForm()
									.findField('endTime').reset();
						}
					}
				}
			}, {
				xtype : 'datetimefield',
				format : 'Y-m-d H:i',
				editable : false,
				allowBlank : false,
				name : 'endTime',
				labelWidth : 100,
				fieldLabel : '结束时间:',
				labelSeparator : '',
				listeners : {
					select : function(th) {
						var endTime = th.getValue();
						var startTime = Ext.getCmp('failureQueryForm').getForm()
								.findField('startTime').getValue();
						if (!isValidStartDataAndEndDate(startTime, endTime)) {
							showMessage("开始时间必须小于结束时间,结束时间小于当前时间");
							th.reset();
							Ext.getCmp('failureQueryForm').getForm()
									.findField('startTime').reset();
						}
					}
				}
			}, {
				fieldLabel : '服务编码',
				name:'esbServiceCode'
			}, {
				fieldLabel : '后端服务编码',
				name:'backServiceCode'
			}, {
				fieldLabel : '请求ID',
				name:'requestId'
			}, {
				fieldLabel : '响应ID',
				name:'responseId'
			}, {
				fieldLabel : '业务唯一标识',
				name:'businessId'
			}, {
				fieldLabel : '客户系统',
				name:'sourceSystem'
			}, {
				fieldLabel : '目标系统',
				name:'targetSystem'
			},{
				xtype : 'button',
				text : '查询',
				style : 'margin-left:0px;',
				width : 60,
				handler : function() {
					var store = Ext.data.StoreManager.lookup('Esb2_FailureLog_Store_Id');
							store.loadPage(1, {
								callback : function(records, operations,
										success) {
									var rawData = Ext.getCmp('Esb2_FailureLog_Grid_Id').store.proxy.reader.rawData;
									if (!success) {
										Ext.Msg.alert("查询失败",rawData.tips);
									}
								}
							});
				}
			}]
		});
Ext.define('FailureLogModel', {
			extend : 'Ext.data.Model',
			fields : ['fid', 'esbHeader.version', 'esbHeader.requestId',
					'esbHeader.responseId', 'esbHeader.businessId',
					'esbHeader.businessDesc1', 'esbHeader.businessDesc2',
					'esbHeader.businessDesc3', 'esbHeader.sourceSystem',
					'esbHeader.targetSystem', 'esbHeader.messageFormat',
					'esbHeader.exchangePattern', 'esbHeader.esbServiceCode',
					'esbHeader.backServiceCode', 'esbHeader.resultCode',
					'esbHeader.sentSequence', 'esbHeader.username',
					'esbHeader.password','createTime']
		});
/**
 * Example Data
 */
// 配置编码store
Ext.define('FailureLogStore', {
			extend : 'Ext.data.Store',
			//autoLoad : true,
			model:'FailureLogModel',
			proxy : {
				type : 'ajax',
				url : '../failure/queryFailureLogList.action',
				reader : {
					type : 'json',
					root : 'list',
					totalProperty : 'resultCount'// 总的数据条数
				}
			}
		});
/**
 * 失败日志panel
 */
Ext.define('ListPanel', {
	extend : 'Ext.grid.Panel',
	//layout:'fit',
	title : '查询结果',
	emptyText:'查询结果为空',
	frame : true,
	selModel : Ext.create('Ext.selection.CheckboxModel'),
	columns : [{
				name : 'id',
				hidden : true,
				dataIndex : 'fid'
			}, {
				name : 'version',
				header : '版本',
				dataIndex : 'esbHeader.version'
			}, {
				name : 'requestId',
				header : '请求唯一码',
				dataIndex : 'esbHeader.requestId'
			}, {
				name : 'responseId',
				header : '响应唯一码',
				dataIndex : 'esbHeader.responseId'
			}, {
				name : 'businessId',
				header : '业务码',
				dataIndex : 'esbHeader.businessId'
			}, {
				name : 'businessDesc1',
				header : '业务描述1',
				dataIndex : 'esbHeader.businessDesc1'
			}, {
				name : 'businessDesc2',
				header : '业务描述2',
				dataIndex : 'esbHeader.businessDesc2'
			}, {
				name : 'businessDesc3',
				header : '业务描述3',
				dataIndex : 'esbHeader.businessDesc3'
			}, {
				name : 'sourceSystem',
				header : '客户端',
				dataIndex : 'esbHeader.sourceSystem'
			}, {
				name : 'targetSystem',
				header : '请求目标',
				dataIndex : 'esbHeader.targetSystem'
			}, {
				name : 'messageFormat',
				header : '消息格式',
				dataIndex : 'esbHeader.messageFormat'
			}, {
				name : 'exchangePattern',
				header : '交互模式',
				dataIndex : 'esbHeader.exchangePattern'
			}, {
				name : 'esbServiceCode',
				header : 'ESB服务编码',
				dataIndex : 'esbHeader.esbServiceCode'
			}, {
				name : 'backServiceCode',
				header : '后端服务编码',
				dataIndex : 'esbHeader.backServiceCode'
			}, {
				name : 'sentSequence',
				header : '发送计数',
				dataIndex : 'esbHeader.sentSequence'
			}, {
				name : 'resultCode',
				header : '返回码',
				dataIndex : 'esbHeader.resultCode'
			}, {
				id : 'createTime',
				name : 'createTime',
				header : '时间',
				dataIndex : 'createTime',
				renderer : function(value) {
					var date = new Date(value); 
					return Ext.Date.format(date, 'Y-m-d H:i:s');
				},
				width : 150
			}, {
				header : '查看消息',
				dataIndex : 'msgBody',
				renderer : function(value, celldata, record) {
					var id = record.data.fid;
					return " <button type='button' id='save' onclick='showFailureLogBody(\""
							+ id + "\");'>失败日志 </button>";
				}
			}]
});
//============初始化界面=================
Ext.onReady(function() {
	Ext.QuickTips.init();
	var store = Ext.create(
			'FailureLogStore', {
				id:'Esb2_FailureLog_Store_Id',
				listeners : {
					beforeload : function(store, operation, eOpts) {
						//var a  = store.baseParams.start;
					//	var b  = store.baseParams.limit;
						var form = Ext
								.getCmp('Esb2_FailureLog_QueryForm_Id');
							var params = {
								'queryBean.startTime' : form.getForm()
									.findField('startTime').getValue(),
								'queryBean.endTime':form.getForm()
									.findField('endTime').getValue(),
								//'queryBean.start':store.baseParams.start,
								//'queryBean.limit':store.baseParams.limit,
								'queryBean.esbHeader.esbServiceCode':form.getForm()
									.findField('esbServiceCode').getValue(),
								'queryBean.esbHeader.backServiceCode':form.getForm()
									.findField('backServiceCode').getValue(),
								'queryBean.esbHeader.requestId':form.getForm()
									.findField('requestId').getValue(),
								'queryBean.esbHeader.responseId':form.getForm()
									.findField('responseId').getValue(),
									
								'queryBean.esbHeader.businessId':form.getForm()
									.findField('businessId').getValue(),
								'queryBean.esbHeader.sourceSystem':form.getForm()
									.findField('sourceSystem').getValue(),
								'queryBean.esbHeader.targetSystem':form.getForm()
									.findField('targetSystem').getValue()
								
							};
							Ext.apply(operation, {
										params : params
									});
						}
					}
			});
	var queryForm = Ext.create('LogQueryForm',{
		id:'Esb2_FailureLog_QueryForm_Id',
		region : 'north'
	});
	store.load({params:{'queryBean.start':0,'queryBean.limit':100}});  
	var listPanel = Ext.create('ListPanel', {
				id:'Esb2_FailureLog_Grid_Id',
				region : 'center',
				store : store,
				bbar : Ext.create('Ext.PagingToolbar', {
							store : store,
							displayInfo : true,
							displayMsg : '显示第 {0} 到- {1}条记录，一共  {2}条记录',
							emptyMsg : "没有记录"
						})
/*				dockedItems : [{
					xtype : 'toolbar',
					dock : 'bottom',
					layout : 'vbox',
					items : [{
								height : 5,
								width : 1600
							}, {
								xtype : 'panel',
								layout : 'hbox',
								items : [{
											xtype : 'container',
											border : false,
											html : '&nbsp;',
											width : 500
										}, {
											xtype : 'standardpaging',
											store : store,
											plugins : Ext.create(
													'Deppon.ux.PageSizePlugin',
													{
														maximumSize : 200
													})
										}]
							}]
				}]*/
			});
	Ext.create('Ext.Viewport', {
						frame : true,
						id:'sysUser_mainPanel',
						title : '查询失败日志',
						layout : 'border',
						items:[queryForm,listPanel]
					});

		});
//===========界面完成=============

		
//============函数==================
function showFailureLogBody(id) {
	//var sid = id;
	var windowId = "showFailureBody";
	Ext.create('Ext.window.Window', {
		id : windowId,
		title : '详细失败日志',
		width : window.screen.availWidth * 0.5,
		height : window.screen.availHeight * 0.3,
		/*html : '<iframe src ="http://www.baidu.com'
				+ ""
				+ '" width="100%" height="100%" frameborder="no" border="0" framespacing="0" align="left"></iframe>'*/
		html : '<iframe src ="../failure/queryFailureLogBody.action?queryBean.fid='
				+ id
				+ '" width="100%" height="100%" frameborder="no" border="0" framespacing="0" align="left"></iframe>'
	}).show();
};
