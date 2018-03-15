var modifyPassWordForm = Ext.create('Ext.form.Panel', {
			frame : true,
			title : '密码修改',
			layout : {
				type : 'table',
				columns : 1
			},
			defaults : {
				width : 250,
				labelAlign : 'left',
				margin : '4 5 4 4'
			},
			defaultType : 'textfield',
			items : [{
						id : 'form_sysUserName',
						fieldLabel : '用户名',
						width : 250,
						name : 'sysUserName',
						allowBlank : false
					}, {
						id : 'form_password',
						fieldLabel : '旧密码',
						width : 250,
						name : 'password',
						inputType : 'password',
						allowBlank : false
					}, {
						id : 'form_newPassword',
						fieldLabel : '新密码',
						width : 250,
						name : 'newPassword',
						inputType : 'password',
						minLength : 6,
						minLengthText : '密码长度最少6位！',
						maxLength : 20,
						maxLengthText : '密码长度最多20位！',
						allowBlank : false
					}, {
						id : 'form_surePassword',
						fieldLabel : '重新键入新密码',
						width : 250,
						name : 'surePassword',
						inputType : 'password',
						vtype: 'repetition',  //自定义校验repetition(指定repetition验证类型)
                        repetition: { targetCmpId: 'form_newPassword' },  //配置repetition验证，提供目标组件（表单）ID
						allowBlank : false
					}, {
						xtype : 'button',
						text : '提交',
						width : 60,
						handler : function() {
							if (modifyPassWordForm.getForm().isValid()) {
								modifyPassWordForm.getForm().submit({
											url : 'modifyPassWord.action',
											params : {
												'sysUserName' : Ext.getCmp('form_sysUserName').getValue(),
												'password' : Ext.getCmp('form_password').getValue(),
												'newPassword' : Ext.getCmp('form_newPassword').getValue()
											},
											success : function(modifyPassWordForm, action) {
												Ext.MessageBox.alert('失败结果反馈', action.result.message);
												modifyPassWordForm.reset();
											},
											failure : function(modifyPassWordForm, action) {
												Ext.MessageBox.alert('成功结果反馈', action.result.message);
											},
											waitMsg : 'modify passWord...'
										});
							} else {
								Ext.MessageBox.alert("数据验证失败", '数据格式不符合要求！');
								return;
							}
						}
					}]
		});

// 自定义的检验函数
Ext.apply(Ext.form.VTypes, {
			// 密码检验
			repetition : function(val, field) { // 返回true，则验证通过，否则验证失败
				if (field.repetition) { // 如果表单有使用repetition配置，repetition配置是一个JSON对象，该对象提供了一个名为targetCmpId的字段，该字段指定了需要进行比较的另一个组件ID。
					var cmp = Ext.getCmp(field.repetition.targetCmpId); // 通过targetCmpId的字段查找组件
					if (Ext.isEmpty(cmp)) { // 如果组件（表单）不存在，提示错误
						Ext.MessageBox.show({
									title : '错误',
									msg : '发生异常错误，指定的组件未找到',
									icon : Ext.Msg.ERROR,
									buttons : Ext.Msg.OK
								});
						return false;
					}
					if (val == cmp.getValue()) { // 取得目标组件（表单）的值，与宿主表单的值进行比较。
						return true;
					} else {
						return false;
					}
				}
			},
			repetitionText : '密码不一致'
		});