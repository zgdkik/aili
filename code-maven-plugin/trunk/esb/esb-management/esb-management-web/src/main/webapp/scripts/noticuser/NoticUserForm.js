// ==============backsvcpointForm================
Ext.define('noticUserForm', {
			extend : 'Ext.form.Panel',
			frame : true,
			renderTo : Ext.getBody(),
			defaults : {
				// width : 250,
				labelAlign : 'left',
				margin : '4 5 4 4'
			},
			defaultType : 'textfield',
			items : [{
						name : 'id',
						id : 'editform_id',
						hidden : true
					}, {
						fieldLabel : '姓名',
						name : 'userName',
						id : 'editform_userName',
						allowBlank : false
					}, {
						fieldLabel : '电话',
						name : 'telPhone',
						id : 'editform_telPhone',
						regex : /^\d*$/,
						regexText : '电话号码',
						allowBlank : false
					}, {
						fieldLabel : '移动电话',
						name : 'mobilePhone',
						id : 'editform_mobilePhone',
						regex : /^1(\d{10})$/,
						regexText : '手机号码',
						allowBlank : false
					}, {
						fieldLabel : '邮件地址',
						name : 'email',
						id : 'editform_email',
						colspan : 2,
						vtype : "email",
						allowBlank : false
					}, {
						xtype : 'combo',
						//width : 250,
						store : Ext.create('pjVersionStore'),
						displayField : 'pjVersion',
						editable : false,
						valueField : 'pjVersion',
						fieldLabel : '版本号',
						name : 'pjVersion',
						id:'pjVersion',
						emptyText : '---请选择---',
						forceSelection : true,
						colspan : 2,
						allowBlank : false
					}, {
						xtype : 'button',
						id : 'editform_select',
						text : '从系统用户中选择',
						width : 110,
						handler : function() {
							openSelectSysUserGrid();
						}
					}, {
						xtype : 'button',
						text : '提交',
						width : 60,
						handler : function() {
							if (Ext.getCmp('noticUserForm').getForm().isValid()) {
								Ext.getCmp('noticUserForm').getForm().submit({
											url : Ext.getCmp('noticUserForm').url,
											params : {
												'noticUserInfo.id' : Ext.getCmp('editform_id').getRawValue(),
												'noticUserInfo.userName' : Ext.getCmp('editform_userName').getRawValue(),
												'noticUserInfo.telPhone' : Ext.getCmp('editform_telPhone').getRawValue(),
												'noticUserInfo.mobilePhone' : Ext.getCmp('editform_mobilePhone').getRawValue(),
												'noticUserInfo.email' : Ext.getCmp('editform_email').getRawValue(),
												'noticUserInfo.pjVersion' : Ext.getCmp('pjVersion').getRawValue()
											},
											success : function(backServiceForm, action) {
												Ext.MessageBox.alert('提交结果反馈', action.result.message);
												noticUserGrid.getStore().load();
												noticUserGrid.getView().refresh();
												Ext.getCmp('win_addNoticUser').close();
											},
											failure : function(backServiceForm, action) {
												Ext.MessageBox.alert('提交结果反馈', action.result.message);
											},
											waitMsg : 'Saving Data...'
										});
							} else {
								Ext.MessageBox.alert("数据验证失败", '数据不符合要求！');
								return;
							}
						}
					}]
		});
// function submit(url, param) {
// Ext.Ajax.request({
// url : url,
// success : function(response) {
// // var result = Ext.decode(respone.responseText);
// // Ext.MessageBox.alert('提交结果反馈', result);
// // noticUserGrid.getStore().load();
// // noticUserGrid.getView().refresh();
// },
// failure : function(response) {
// // var result = Ext.decode(respone.responseText);
// // Ext.MessageBox.alert('提交结果反馈', result);
// }
// });
// }
// ==============================
var queryNoticUserForm = Ext.create('Ext.form.Panel', {
			frame : true,
			title : '查询条件',
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
						fieldLabel : '姓名',
						width : 250,
						name : 'userName',
						id : 'form_userName'
					}, {
						fieldLabel : '电话',
						width : 250,
						name : 'telPhone',
						id : 'form_telPhone'
					}, {
						fieldLabel : '移动电话',
						width : 250,
						name : 'mobilePhone',
						id : 'form_mobilePhone'
					}, {
						fieldLabel : '邮件地址',
						width : 250,
						name : 'email',
						id : 'form_email',
						colspan : 2
					}, {
						xtype : 'button',
						text : '查询',
						width : 60,
						handler : function() {
							noticUserGrid.store.currentPage = 1;
							noticUserGrid.store.load({
										params : {
											userName : window.Ext.getCmp('form_userName').getValue(),
											telPhone : window.Ext.getCmp('form_telPhone').getValue(),
											mobilePhone : window.Ext.getCmp('form_mobilePhone').getValue(),
											email : window.Ext.getCmp('form_email').getValue()
										}
									});
						}
					}]
		});