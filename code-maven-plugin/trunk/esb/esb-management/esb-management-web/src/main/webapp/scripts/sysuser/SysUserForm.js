// ==============backsvcpointForm================
Ext.define('sysUserForm', {
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
						fieldLabel : '用户名',
						name : 'sysUserName',
						id : 'editform_sysUserName',
						allowBlank : false
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
						xtype : 'button',
						text : '提交',
						width : 60,
						handler : function() {
							if (Ext.getCmp('sysUserForm').getForm().isValid()) {
								Ext.getCmp('sysUserForm').getForm().submit({
											url : Ext.getCmp('sysUserForm').url,
											params : {
												'sysUserInfo.id' : Ext.getCmp('editform_id').getRawValue(),
												'sysUserInfo.sysUserName' : Ext.getCmp('editform_sysUserName').getRawValue(),
												'sysUserInfo.userName' : Ext.getCmp('editform_userName').getRawValue(),
												'sysUserInfo.telPhone' : Ext.getCmp('editform_telPhone').getRawValue(),
												'sysUserInfo.mobilePhone' : Ext.getCmp('editform_mobilePhone').getRawValue(),
												'sysUserInfo.email' : Ext.getCmp('editform_email').getRawValue()
											},
											success : function(backServiceForm, action) {
												Ext.MessageBox.alert('提交结果反馈', action.result.message);
												sysUserGrid.getStore().load();
												sysUserGrid.getView().refresh();
												Ext.getCmp('win_addSysUser').close();
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

// 主页查询form
var querySysUserForm = Ext.create('Ext.form.Panel', {
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
						fieldLabel : '用户名',
						width : 250,
						name : 'sysUserName',
						id : 'form_sysUserName'
					}, {
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
						id : 'form_email'
					}, {
						xtype : 'button',
						text : '查询',
						width : 60,
						handler : function() {
							sysUserGrid.store.currentPage = 1;
							sysUserGrid.store.load({
										params : {
											'sysUserInfo.sysUserName' : window.Ext.getCmp('form_sysUserName').getValue(),
											'sysUserInfo.userName' : window.Ext.getCmp('form_userName').getValue(),
											'sysUserInfo.telPhone' : window.Ext.getCmp('form_telPhone').getValue(),
											'sysUserInfo.mobilePhone' : window.Ext.getCmp('form_mobilePhone').getValue(),
											'sysUserInfo.email' : window.Ext.getCmp('form_email').getValue()
										}
									});
						}
					}]
		});