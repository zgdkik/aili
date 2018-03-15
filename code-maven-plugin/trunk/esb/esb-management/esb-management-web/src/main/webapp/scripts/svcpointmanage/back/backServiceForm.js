// ==============backsvcpointForm================
Ext.define('svcpointForm', {
			extend : 'Ext.form.Panel',
			frame : true,
			// width:520,
			// width : window.screen.availWidth * 0.3,
			// height : window.screen.availHeight * 0.2,
			renderTo : Ext.getBody(),
			layout : {
				type : 'table',
				columns : 2
			},
			defaults : {
				width : 250,
				labelAlign : 'left',
				margin : '4 5 4 4'
			},
			defaultType : 'textfield',
			items : [{
						fieldLabel : '服务代码',
						width : 250,
						name : 'svcCode',
						id : 'svcode1'

					}, {
						fieldLabel : '服务名称',
						width : 250,
						name : 'svcName',
						id : 'serviceName'
					}, {
						xtype : 'combo',
						width : 250,
						store : Ext.create('serviceProviderStore'),
						displayField : 'name',
						editable : false,
						// style:'margin-left:30px;',
						valueField : 'name',
						fieldLabel : '服务提供方',
						name : 'svcProvdId',
						id : 'servicerProvider1',
						emptyText : '---请选择---',
						forceSelection : true
					}, {
						xtype : 'combo',
						width : 250,
						store : Ext.create('serviceProtocolStore'),
						displayField : 'name',
						editable : false,
						valueField : 'name',
						fieldLabel : '服务接入协议',
						name : 'svcAgrmt',
						id : 'serviceProtocol1',
						emptyText : '---请选择---',
						forceSelection : true
					}, {
						fieldLabel : '服务接入地址',
						width : 500,
						name : 'svcAddr',
						id : 'serviceAddress',
						colspan : 2
					}, {
						fieldLabel : '返回类型',
						name : 'responseType',
						colspan : 2
					}, {
						xtype : 'checkbox',
						fieldLabel : '是否自动重发',
						name : 'isAutoResend'
					}, {
						xtype : 'checkbox',
						fieldLabel : '是否保存原始消息',
						name : 'isRcdOrgBody'
					}, {
						xtype : 'checkbox',
						fieldLabel : '是否记录性能日志',
						name : 'isRcdPfmcLog'
					}, {
						xtype : 'checkbox',
						fieldLabel : '是否记录异常日志',
						name : 'isRcdErrLog'
					}, {
						xtype : 'checkbox',
						fieldLabel : '异常是否立即通知',
						name : 'promptlyNotify'
					}],
			bbar : [{
				xtype : 'button',
				text : '提交',
				width : 60,
				handler : function() {
					var form = Ext.getCmp('svcpointForm').getForm();
					if (form.isValid()) {
						Ext.getCmp('svcpointForm').getForm().submit({
							url : Ext.getCmp('svcpointForm').url,
							params : {
								'svcPointInfo.svcCode' : Ext.getCmp('svcode1')
										.getRawValue(),
								'svcPointInfo.svcName' : Ext
										.getCmp('serviceName').getRawValue(),
								'svcPointInfo.svcProvdId' : Ext
										.getCmp('servicerProvider1')
										.getRawValue(),
								'svcPointInfo.svcAgrmt' : Ext
										.getCmp('serviceProtocol1')
										.getRawValue(),
								'svcPointInfo.svcAddr' : Ext
										.getCmp('serviceAddress').getRawValue(),
								'svcPointInfo.frntOrBck' : 'B',
								'svcPointInfo.responseType' : form
										.findField('responseType').getRawValue(),
								'svcPointInfo.isAutoResend' : form
										.findField('isAutoResend').value,
								'svcPointInfo.isRcdOrgBody' : form
										.findField('isRcdOrgBody').value,
								'svcPointInfo.isRcdPfmcLog' : form
										.findField('isRcdPfmcLog').value,
								'svcPointInfo.isRcdErrLog' : form
										.findField('isRcdErrLog').value,
								'svcPointInfo.promptlyNotify' : form
										.findField('promptlyNotify').value
							},
							success : function(backServiceForm, action) {
								Ext.MessageBox.alert('提示',
										action.result.message);
							},
							failure : function(backServiceForm, action) {
								Ext.MessageBox.alert('提示',
										action.result.message);
							},
							waitMsg : '保存数据...'
						});
					}

				}
			}/*,{
				text:'重置',
				handler:function(){
					Ext.getCmp('svcpointForm').reset();
				}
			}*/]
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
var querySvcpointForm = Ext.create('Ext.form.Panel', {
	frame : true,
	title : '查询条件',
	region : 'north',
	// width : window.screen.availWidth * 0.84,
	// height : window.screen.availHeight * 0.1,
	layout : {
		type : 'table',
		columns : 3
	},
	defaults : {
		width : 250,
		labelAlign : 'left',
		margin : '4 5 4 4'
	},
	defaultType : 'textfield',
	items : [{
				fieldLabel : '服务名称',
				width : 250,
				name : 'serviceName',
				id : 'serviceName2',
				colspan : 1
			}, {
				xtype : 'combo',
				width : 250,
				store : Ext.create('serviceProviderStore'),
				displayField : 'name',

				valueField : 'name',
				fieldLabel : '服务提供方',
				editable : false,
				name : 'servicerProvider',
				id : 'servicerProvider2',
				emptyText : '---请选择---',
				colspan : 1

			}, {
				xtype : 'combo',
				width : 250,
				store : Ext.create('serviceProtocolStore'),
				displayField : 'name',
				valueField : 'name',
				fieldLabel : '服务接入协议',
				name : 'serviceProtocol',
				id : 'serviceProtocol2',
				emptyText : '---请选择---',
				editable : false,
				colspan : 1
			}, {
				fieldLabel : '服务接入地址',
				width : 500,
				name : 'serviceAddress',
				id : 'serviceAddress2',
				colspan : 2
			}, {
				xtype : 'buttongroup',
				width : 150,
				// columns : 2,
				// frame:false,
				items : [{
					xtype : 'button',
					text : '查询',
					style : 'margin-left:0px;',
					width : 60,
					handler : function() {
						var data = querySvcpointForm.getForm().getFieldValues();
						Ext.data.StoreManager.lookup('backsvcpointStore').load(
								{
									params : {
										'svcPointInfo.svcName' : data.serviceName,
										'svcPointInfo.svcProvdId' : data.servicerProvider,
										'svcPointInfo.svcAgrmt' : data.serviceProtocol,
										'svcPointInfo.svcAddr' : data.serviceAddress
									}

								});
					}
				}, {
					xtype : 'button',
					text : '清空查询条件',
					width : 80,
					handler : function() {
						querySvcpointForm.getForm().reset();
					}
				}]
			}]
});