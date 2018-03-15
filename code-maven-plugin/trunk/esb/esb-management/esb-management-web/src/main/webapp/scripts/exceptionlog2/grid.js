/**
 * 异常记录查询模块
 */
Ext.onReady(function() {
			// 开启动态加载
			Ext.Loader.setConfig({
						enabled : true
					});
			// 画界面
			Ext.create('Ext.Viewport', {
						layout : 'border',
						items : [Ext.create('exceptionQueryForm2', {
											id : 'exceptionQueryForm2',
											region : 'north'
										}), Ext.create('exceptionLogPanel2', {
											id : 'exceptionLogPanel2',
											region : 'center'
										})]
					});
		});
// 查询panel  For 2.0 异常日志查询 
Ext.define('exceptionQueryForm2', {
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
						var toDate = Ext.getCmp('exceptionQueryForm2').getForm()
								.findField('endTime').getValue();
						if (!isValidStartDataAndEndDate(fromDate, toDate)) {
							showMessage("开始时间必须小于结束时间,结束时间小于当前时间。");
							th.reset();
							Ext.getCmp('exceptionQueryForm2').getForm()
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
						var startTime = Ext.getCmp('exceptionQueryForm2').getForm()
								.findField('startTime').getValue();
						if (!isValidStartDataAndEndDate(startTime, endTime)) {
							showMessage("开始时间必须小于结束时间,结束时间小于当前时间。");
							th.reset();
							Ext.getCmp('exceptionQueryForm2').getForm()
									.findField('startTime').reset();
						}
					}
				}
			}, /*{
				xtype:'combo',
				fieldLabel:'异常类型',
				labelWidth : 60,
				store:Ext.create('exceptionTypeStore'),
				displayField:'name',
				valueField:'id',
				name:'exceptionCode',
				colspan:1
			},*/{
				fieldLabel : '服务编码',
				labelWidth : 60,
				xtype:'combo',
				store:Ext.create('codeStore'),
				displayField:'svcCode',
				valueField:'svcCode',
				width : 250,
				name : 'svcCode',
				colspan : 1
			}, {
				fieldLabel : '相关系统',
				width : 250,
				name : 'sourceSystem',
				colspan : 1
			},{
				fieldLabel : '协议',
				width : 250,
				name : 'messageFormat',
				colspan : 1
			},{
				fieldLabel : '关键业务信息',
				width : 250,
				name : 'biz',
				colspan : 1
			},{
				fieldLabel : '业务辅助字段1',
				width : 250,
				name : 'businessDesc1',
				colspan : 1
			}, {
				fieldLabel : '业务辅助字段2',
				width : 250,
				name : 'businessDesc2',
				colspan : 1
			}, {
				fieldLabel : '业务辅助字段3',
				width : 250,
				name : 'businessDesc3',
				colspan : 1
			}, {
				xtype : 'button',
				text : '查询',
				style : 'margin-left:0px;',
				width : 60,
				handler : function() {
					var form = Ext.getCmp('exceptionQueryForm2').getForm();
					Ext.data.StoreManager.lookup('exceptionLogStore2').load();
					Ext.create('codeStore').load();
				}
			}]
		});

// 异常日志panel   For 2.0 显示异常日志信息
Ext.define('exceptionLogPanel2', {
	extend : 'Ext.grid.Panel',
	// width : window.screen.availWidth * 0.84,
	// height : window.screen.availHeight * 0.67,
	title : '查询结果',
	frame : true,
	selModel : Ext.create('Ext.selection.CheckboxModel'),
	store : Ext.create('exceptionLogStore2', {
				'id' : 'exceptionLogStore2',
								listeners : {
					beforeload : function(store, operation, opts) {
						var form = Ext.getCmp('exceptionQueryForm2').getForm();
						Ext.apply(operation, {
									params : {
										'bean.startTime' : form
												.findField('startTime')
												.getValue(),
										'bean.endTime' : form
												.findField('endTime')
												.getValue(),
										'bean.svcCode' : form
												.findField('svcCode')
												.getValue(),
										'bean.sourceSystem' : form.findField('sourceSystem')
												.getValue(),
										'bean.messageFormat' : form.findField('messageFormat')
												.getValue(),
										'bean.biz' : form.findField('biz')
												.getValue(),
										'bean.businessDesc1' : form.findField('businessDesc1')
												.getValue(),
										'bean.businessDesc2' : form.findField('businessDesc2')
												.getValue(),
										'bean.businessDesc3' : form.findField('businessDesc3')
												.getValue(),
										/*'bean.exceptionCode' : form
												.findField('exceptionCode')
												.getValue()*/
									}
								});
					}
				}
			}),
	columns : [{
				id : 'id',
				name : 'id',
				hidden : true,
				dataIndex : 'id'
			}, {
				id : 'version',
				name : 'version',
				header : '接口版本',
				dataIndex : 'version'
			}, {
				id : 'businessId',
				name : 'businessId',
				header : '业务关键信息',
				dataIndex : 'businessId'
			},{
				id : 'businessDesc1',
				name : 'businessDesc1',
				header : '业务辅助字段1',
				dataIndex : 'businessDesc1'
			},{
				id : 'businessDesc2',
				name : 'businessDesc2',
				header : '业务辅助字段2',
				dataIndex : 'businessDesc2'
			},{
				id : 'businessDesc3',
				name : 'businessDesc3',
				header : '业务辅助字段3',
				dataIndex : 'businessDesc3'
			},{
				id : 'requestId',
				name : 'requestId',
				header : '请求ID',
				dataIndex : 'requestId'
			},{
				id : 'responseId',
				name : 'responseId',
				header : '响应ID',
				dataIndex : 'responseId'
			},{
				id : 'esbServiceCode',
				name : 'esbServiceCode',
				header : 'ESB服务编码',
				dataIndex : 'esbServiceCode'
			},{
				id : 'backServiceCode',
				name : 'backServiceCode',
				header : '后端服务编码',
				dataIndex : 'backServiceCode'
			},{
				id : 'messageFormat',
				name : 'messageFormat',
				header : '消息格式',
				dataIndex : 'messageFormat'
			},{
				id : 'exchangePattern',
				name : 'exchangePattern',
				header : '交互模式',
				dataIndex : 'exchangePattern'
			},{
				id : 'message',
				name : 'message',
				header : '异常信息',
				dataIndex : 'message'
			},
			{
				header : '详细异常信息',
				dataIndex : 'exceptionCode',
				renderer : function(value, celldata, record) {
					var id = record.data.id;
					return " <button type='button' id='save' onclick='showException(\""
							+ id + "\");'>查看异常 </button>";
				}
			}],
	bbar : Ext.create('Ext.PagingToolbar', {
				store : Ext.data.StoreManager.lookup('exceptionLogStore2'),
				displayInfo : true,
				displayMsg : '显示第 {0} 到- {1}条记录，一共  {2}条记录',
				emptyMsg : "没有记录"
			})
});
// --------------------------------------------界面完成 ----------------------
/**
 * 显示详细异常信息
 * 
 * @param {}
 *            id
 */
function showException(id) {
	var sid = id;
	var windowId = "showException";

	Ext.create('Ext.window.Window', {
		id : windowId,
		title : '详细异常信息',
		width : window.screen.availWidth * 0.5,
		height : window.screen.availHeight * 0.3,
		html : '<iframe src ="../exceptionlog2/queryExceptionStrace2.action?bean.id='
				+ sid
				+ '" width="100%" height="100%" frameborder="no" border="0" framespacing="0" align="left"></iframe>'
	}).show();
};
/**
 * 起始时间和终止时间是否符合校验（起始时间小于终止时间，终止时间小于当前时间）
 * 
 * @param {}
 *            startDate
 * @param {}
 *            endDate
 * @return {Boolean}
 */
function isValidStartDataAndEndDate(startDate, endDate) {
	if (Ext.isEmpty(startDate) || Ext.isEmpty(endDate)) {
		return true;
	}
	var startDateTime = startDate.getTime();
	var endDateTime = endDate.getTime();
	var d = new Date();
	if (startDateTime <= endDateTime && endDateTime <= d.getTime()) {
		return true;
	} else {
		return false;
	}
};
/**
 * 提示框
 * 
 * @param {}
 *            message
 * @param {}
 *            fun
 */
function showMessage(message, fun) {
	var len = message.length;
	Ext.Msg.show({
				title : '提示',
				msg : message,
				width : 110 + len * 15,
				msg : '<div id="message">' + message + '</div>',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.WARNING,
				callback : function(e) {
					if (!Ext.isEmpty(fun)) {
						if (e == 'ok') {
							fun();
						}
					}
				}
			});

};