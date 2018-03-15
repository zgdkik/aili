// ==============frontSvcpointForm================
Ext.define('frontSvcpointForm', {
			extend : 'Ext.form.Panel',
			frame : true,
			// width : window.screen.availWidth * 0.6,
			// height : window.screen.availHeight * 0.25,
			renderTo : Ext.getBody(),
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
						fieldLabel : '服务代码',
						width : 250,
						name : 'svcCode',
						id : 'frontsvcCode'
					}, {
						fieldLabel : '服务名称',
						width : 250,
						name : 'svcName',
						id : 'frontsvcName'
					}, {
						xtype : 'combo',
						width : 250,
						store : Ext.create('serviceProviderStore',{
						filters:[ Ext.create('Ext.util.Filter', {property: "name", value: 'ESB', root: 'data'})]
						}),
						displayField : 'name',
						editable : false,
						// style:'margin-left:30px;',
						valueField : 'name',
						fieldLabel : '服务提供者',
						name : 'svcProvdId',
						id : 'frontServicerProvider',
						emptyText : '---请选择---'
					}, {
						xtype : 'combo',
						width : 250,
						store : Ext.create('serviceProtocolStore'),
						displayField : 'name',
						editable : false,
						valueField : 'name',
						fieldLabel : '客户端接入协议',
						name : 'svcAgrmt',
						id : 'frontServiceProtocol',
						emptyText : '---请选择---'
					}, {
						fieldLabel : '客户端接入地址',
						width : 500,
						name : 'svcAddr',
						id : 'frontServiceAddress',
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
					}, {
						xtype : 'button',
						text : '提交',
						width : 60,
						handler : function() {
							var form = Ext.getCmp('frontSvcpointForm').getForm();
						//	if(form.isValid()){
								Ext.getCmp('frontSvcpointForm').getForm().submit({
								url : Ext.getCmp('frontSvcpointForm').url,
								params : {
									'svcPointInfo.svcCode' : Ext
											.getCmp('frontsvcCode')
											.getRawValue(),
									'svcPointInfo.svcName' : Ext
											.getCmp('frontsvcName')
											.getRawValue(),
									'svcPointInfo.svcProvdId' : Ext
											.getCmp('frontServicerProvider')
											.getRawValue(),
									'svcPointInfo.svcAgrmt' : Ext
											.getCmp('frontServiceProtocol')
											.getRawValue(),
									'svcPointInfo.svcAddr' : Ext
											.getCmp('frontServiceAddress')
											.getRawValue(),
									'svcPointInfo.frntOrBck' : 'F',
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
									// 'svcPointRelationInfo.frontSvcCode':Ext.getCmp('frontsvcCode').getRawValue(),
									// 'svcPointRelationInfo.backSvcCode':Ext.getCmp('backSvcName').getValue()
								},
								success : function(backServiceForm, action) {
									Ext.MessageBox.alert('提示',
											action.result.message);
								},
								failure : function(backServiceForm, action) {
									Ext.MessageBox.alert('提示',
											action.result.message);
								},
								waitMsg : 'Saving Data...'
							});
							}
					//	}
					}]
		});
// ==============================
var querySvcpointForm = Ext.create('Ext.form.Panel', {
	frame : true,
	region : 'north',
	title : '查询条件',
	// width : window.screen.availWidth * 0.84,
	// height : window.screen.availHeight * 0.1,
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
				fieldLabel : '服务名称',
				// width : 250,
				name : 'frontSvcName'
			}, {
				fieldLabel : '后端服务名称',
				// width : 250,
				name : 'backSvcName'
			}, {
				xtype : 'combo',
				width : 250,
				store : Ext.create('serviceProviderStore',{
					filters:[ Ext.create('Ext.util.Filter', {property: "name", value: 'ESB', root: 'data'})]
				}),
				displayField : 'name',
				editable : false,
				// style:'margin-left:30px;',
				valueField : 'name',
				fieldLabel : '服务提供方',
				name : 'clientSystem',
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
				emptyText : '---请选择---',
				forceSelection : true
			}, {
				fieldLabel : '服务接入地址',
				width : 500,
				name : 'svcAddr',
				colspan : 2
			}, {
				xtype : 'buttongroup',
				width : 150,
				items : [{
					xtype : 'button',
					text : '查询',
					style : 'margin-left:0px;',
					width : 60,
					handler : function() {
						var data = querySvcpointForm.getForm().getFieldValues();
						Ext.data.StoreManager.lookup('frontSvcpointStore')
								.load({
									params : {
										'bean.frontSvcName' : data.frontSvcName,
										'bean.backSvcName' : data.backSvcName,
										'bean.svcAgrmt' : data.svcAgrmt,
										'bean.svcAddr' : data.svcAddr,
										'bean.clientSystem' : data.clientSystem
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