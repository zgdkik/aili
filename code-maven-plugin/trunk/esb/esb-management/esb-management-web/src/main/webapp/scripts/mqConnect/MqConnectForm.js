//-------------------backMqConnectForm--------------
Ext
		.define(
				'mqConnectForm',
				{
					extend : 'Ext.form.Panel',
					frame : true,
					renderTo : Ext.getBody(),
					defaults : {
						labelAlign : 'left',
						margin : '4 5 4 4'
					},
					defaultType : 'textfield',
					items : [
							{
								name : 'id',
								id : 'editform_id',
								hidden : true
							},
							{
								fieldLabel : '服务地址',
								emptyText : '请输入服务地址',
								name : 'ip',
								id : 'editform_ip',
								allowBlank : false
							},
							{
								fieldLabel : '端口号',
								emptyText : '请输入端口号',
								name : 'port',
								id : 'editform_port',
								allowBlank : false
							},
							{
								fieldLabel : '通道',
								emptyText : '请输入通道名称',
								name : 'channel',
								id : 'editform_channel',
								allowBlank : false
							},
							{
								fieldLabel : '队列通配符',
								emptyText : '请输入相应的队列配置符',
								name : 'queueNameReg',
								id : 'editform_queueNameReg',
								allowBlank : false
							},
							{
								fieldLabel : '队列管理器',
								emptyText : '请输入队列管理器名称',
								name : 'qmgr',
								id : 'editform_qmgr',
								allowBlank : false
							},
							{
								xtype : 'button',
								text : '提交',
								width : 60,
								handler : function() {
									if (Ext.getCmp('mqConnectForm').getForm()
											.isValid()) {
										Ext
												.getCmp('mqConnectForm')
												.getForm()
												.submit(
														{
															url : Ext
																	.getCmp('mqConnectForm').url,
															params : {
																'queueManagerInfo.id' : Ext
																		.getCmp(
																				'editform_id')
																		.getRawValue(),
																'queueManagerInfo.ip' : Ext
																		.getCmp(
																				'editform_ip')
																		.getRawValue(),
																'queueManagerInfo.port' : Ext
																		.getCmp(
																				'editform_port')
																		.getRawValue(),
																'queueManagerInfo.channel' : Ext
																		.getCmp(
																				'editform_channel')
																		.getRawValue(),
																'queueManagerInfo.queueNameReg' : Ext
																		.getCmp(
																				'editform_queueNameReg')
																		.getRawValue(),
																'queueManagerInfo.qmgr' : Ext
																		.getCmp(
																				'editform_qmgr')
																		.getRawValue()
															},
															success : function(
																	backServiceForm,
																	action) {
																Ext.MessageBox
																		.alert(
																				'提交结果反馈',
																				action.result.message);
																mqConnectGrid
																		.getStore()
																		.load();
																mqConnectGrid
																		.getView()
																		.refresh();
																Ext
																		.getCmp(
																				'win_addMqConnect')
																		.close();
															},
															failure : function(
																	backServiceForm,
																	action) {
																Ext.MessageBox
																		.alert(
																				'提交结果反馈',
																				action.result.message);
															},
															waitMsg : 'Saving Data...'
														});
									} else {
										Ext.MessageBox.alert("数据验证失败",
												'数据不符合要求！');
										return;
									}
								}
							} ]
				});



//查询数据
var queryMqConnectForm = Ext.create('Ext.form.Panel',{
	frame : true ,
	title : '查询条件',
	layout : {
		type : 'table',
		columns : 3
	},
	defaults : {
		width : 75,
		labelAlign : 'left',
		margin : '4 5 4 4'
	},
	defaultType : 'textfield',
	items : [{
		fieldLabel : '服务地址',
		emptyText : '请输入服务地址',
		width : 250 ,
		name : 'ip',
		id : 'form_ip'
	},{
		fieldLabel : '端口号',
		emptyText : '请输入端口号',
		width : 250 ,
		name : 'port',
		id : 'form_port'
	},{
		fieldLabel : '通道名称',
		emptyText : '请输入通道名称',
		width : 250 ,
		name : 'channel',
		id : 'form_channel'
	},{
		fieldLabel : '队列通配符',
		emptyText : '请输入相应的队列配置符',
		width : 250 ,
		name : 'queueNameReg',
		id : 'form_queueNameReg'
	},{
		fieldLabel : '队列管理器名称',
		emptyText : '请输入队列管理器名称',
		width : 250 ,
		name : 'qmgr',
		id : 'form_qmgr'
	},{
		xtype : 'button',
		text : '查询',
		width : 60,
		handler : function (){
			mqConnectGrid.store.currentPage = 1;
//			this.up('form').getForm().findFiled('port').getValue();
			mqConnectGrid.store.load({
				params : {
					'queueManagerInfo.id' : window.Ext.getCmp("form_ip").getValue(),
					'queueManagerInfo.port' : window.Ext.getCmp("form_port").getValue(),
					'queueManagerInfo.channel' : window.Ext.getCmp("form_channel").getValue(),
					'queueManagerInfo.queueNameReg' : window.Ext.getCmp("form_queueNameReg").getValue(),
					'queueManagerInfo.qmgr' : window.Ext.getCmp("form_qmgr").getValue()
				}
			});
		}
	}]
});