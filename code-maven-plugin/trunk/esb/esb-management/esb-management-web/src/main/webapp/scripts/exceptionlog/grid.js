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
						items : [Ext.create('exceptionQueryForm', {
											id : 'exceptionQueryForm',
											region : 'north'
										}), Ext.create('exceptionLogPanel', {
											id : 'exceptionLogPanel',
											region : 'center'
										})]
					});
		});
// 查询panel
Ext.define('exceptionQueryForm', {
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
						var toDate = Ext.getCmp('exceptionQueryForm').getForm()
								.findField('endTime').getValue();
						if (!isValidStartDataAndEndDate(fromDate, toDate)) {
							showMessage("开始时间必须小于结束时间,结束时间小于当前时间");
							th.reset();
							Ext.getCmp('exceptionQueryForm').getForm()
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
						var startTime = Ext.getCmp('exceptionQueryForm').getForm()
								.findField('startTime').getValue();
						if (!isValidStartDataAndEndDate(startTime, endTime)) {
							showMessage("开始时间必须小于结束时间,结束时间小于当前时间");
							th.reset();
							Ext.getCmp('exceptionQueryForm').getForm()
									.findField('startTime').reset();
						}
					}
				}
			}, {
				xtype:'combo',
				fieldLabel:'异常类型',
				labelWidth : 60,
				store:Ext.create('exceptionTypeStore'),
				displayField:'name',
				valueField:'id',
				name:'exceptionCode',
				colspan:1
			},{
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
				fieldLabel : '关键业务信息',
				width : 250,
				name : 'biz',
				colspan : 1
			}, {
				xtype : 'button',
				text : '查询',
				style : 'margin-left:0px;',
				width : 60,
				handler : function() {
					var form = Ext.getCmp('exceptionQueryForm').getForm();
					Ext.data.StoreManager.lookup('exceptionLogStore').load();
					Ext.create('codeStore').load();
				}
			}]
		});

// 异常日志panel
Ext.define('exceptionLogPanel', {
	extend : 'Ext.grid.Panel',
	// width : window.screen.availWidth * 0.84,
	// height : window.screen.availHeight * 0.67,
	title : '查询结果',
	frame : true,
	selModel : Ext.create('Ext.selection.CheckboxModel'),
	store : Ext.create('exceptionLogStore', {
				'id' : 'exceptionLogStore',
								listeners : {
					beforeload : function(store, operation, opts) {
						var form = Ext.getCmp('exceptionQueryForm').getForm();
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
										'bean.biz' : form.findField('biz')
												.getValue(),
										'bean.exceptionCode' : form
												.findField('exceptionCode')
												.getValue()
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
				name : 'msgId',
				header : '消息编码',
				dataIndex : 'msgId'
			}/*, {
				id : 'svcName',
				name : 'svcName',
				header : 'ESB服务名称',
				dataIndex : 'svcName'
			}*/, {
				id : 'svcCode',
				name : 'svcCode',
				header : 'ESB服务编码',
				dataIndex : 'svcCode',
				width : 200
			}, {
				id : 'createTime',
				name : 'createTime',
				header : '异常发生时间',
				dataIndex : 'createTime',
				renderer : function(value) {
					var date = new Date(value); 
					return Ext.Date.format(date, 'Y-m-d H:i:s');
				},
				width : 150
			}, {
				id : 'biz',
				name : 'biz',
				header : '业务关键信息',
				dataIndex : 'biz'
			}, {
				header : '异常类型',
				dataIndex : 'exceptionCode',
				name : 'exceptionCode',
				renderer:function(value){
					var a = "";
					if(vlaue ="com.deppon.esb.common.exception.ESBServerBusinessExeption"){
						a="后端业务异常";
					}
					else if(value ="com.deppon.esb.common.exception.ESBServerSystemExeption"){
						a="后端系统异常"
					}
					else{
						a="未知异常类型";
					}
					return a;
				}
			}, {
				header : '查看详细异常信息',
				dataIndex : 'exceptionCode',
				renderer : function(value, celldata, record) {
					var id = record.data.id;
					return " <button type='button' id='save' onclick='showException(\""
							+ id + "\");'>查看异常 </button>";
				}
			},{
				header:'esbIP',
				dataIndex:'esbIP'
			},{
				header:'是否允许重发',
				dataIndex:'isAutoResend'
			},{
				header:'是否重发成功',
				dataIndex:'isSuccess'
			},{
				header:'重发次数',
				dataIndex:'retryCount'
			},{
				header:'是否系统',
				dataIndex:'isSystemSend'
			}],
	bbar : Ext.create('Ext.PagingToolbar', {
				store : Ext.data.StoreManager.lookup('exceptionLogStore'),
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
		html : '<iframe src ="../exceptionlog/queryExceptionStrace.action?bean.id='
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