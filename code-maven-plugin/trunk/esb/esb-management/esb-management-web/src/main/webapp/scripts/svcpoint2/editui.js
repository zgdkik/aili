// ==============svcpoint EDT UI================
Ext.define('Esb2.svcpoint.editPanel', {
	extend : 'Ext.form.Panel',
	frame : true,
	//renderTo : Ext.getBody(),
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
				fieldLabel : '服务名称',
				name : 'name',
				allowBlank : false
			}, {
				fieldLabel : '服务编码',
				name : 'code',
				allowBlank : false
			}, {
				fieldLabel : '相关系统ID',
				name : 'sysid',
				xtype : 'combo',
				store : Ext.create('Esb2.svcpoint.SysIDStore'),
				displayField : 'value',
				editable : false,
				// style:'margin-left:30px;',
				valueField : 'value',
				emptyText : '---请选择---',
				forceSelection : true,
				allowBlank : false
			}, {
				fieldLabel : '协议',
				name : 'agrmt',
				xtype : 'combo',
				store : Ext.create('Esb2.svcpoint.AgrmtStore'),
				displayField : 'value',
				editable : false,
				valueField : 'value',
				emptyText : '---请选择---',
				forceSelection : true,
				allowBlank : false
			}, {
				fieldLabel : '前端or后端',
				name : 'frontOrBack',
				xtype : 'combo',
				store : Ext.create('Esb2.svcpoint.FrontOrBackStore'),
				displayField : 'name',
				editable : false,
				valueField : 'value',
				emptyText : '---请选择---',
				forceSelection : true,
				allowBlank : false
			}, {
				fieldLabel : '服务模式',
				name : 'expattern',
				xtype : 'combo',
				store : Ext.create('Esb2.svcpoint.ExpatternStore'),
				displayField : 'name',
				editable : false,
				valueField : 'value',
				emptyText : '---请选择---',
				forceSelection : true,
				allowBlank : false
			}, {
				fieldLabel : '消息格式',
				name : 'messageFormat',
				allowBlank : false
			}, {
				fieldLabel : 'ESB处理请求地址',
				name : 'esbRequestAddr',
				colspan : 2
			}, {
				fieldLabel : 'ESB处理响应地址',
				name : 'esbResponseAddr',
				colspan : 2
			}/*, {
				fieldLabel : '超时（单位:秒）',
				name : 'timeout',
				regex : /^\d*$/,
				regexText : '请输入数字',
				colspan : 2
			}*/, {
				name : 'id',//主键ID
				xtype : 'hiddenfield'
			}],
	bbar : [{
		xtype : 'button',
		text : '提交',
		width : 60,
		handler : function() {
			var form = this.up('form').getForm();
			/** trim() method for String */
			String.prototype.trim = function() {
				return this.replace(/(^\s*)|(\s*$)/g, '');
			};
			if (form.isValid()) {
				this.up('form').getForm().submit({
					params : {
						'info.code' : form.findField('code').getValue().trim(),
						'info.name' : form.findField('name').getValue().trim(),
						'info.sysid' : form.findField('sysid').getValue()
								.trim(),
						'info.agrmt' : form.findField('agrmt').getValue()
								.trim(),
						'info.frontOrBack' : form.findField('frontOrBack')
								.getValue().trim(),
						'info.esbRequestAddr' : form
								.findField('esbRequestAddr').getValue().trim(),
						'info.esbResponseAddr' : form
								.findField('esbResponseAddr').getValue().trim(),
						'info.expattern' : form.findField('expattern')
								.getValue().trim(),
						'info.messageFormat' : form.findField('messageFormat')
								.getValue().trim(),
						'info.id' : form.findField('id').getValue()
								.trim()
					},
					success : function(backServiceForm, action) {
						Ext.MessageBox.alert('server', action.result.message);
					},
					failure : function(backServiceForm, action) {
						Ext.MessageBox.alert('server', action.result.message);
					},
					waitMsg : '保存数据...'
				});
			}

		}
	}, {
		text : '重置',
		handler : function() {
			//重置表单
			var form = this.up('form').getForm();
			form.reset();
			//如果是更新表单，则回复到初始表单记录。
			if (form.url.indexOf('update.action') > 0) {
				var selection = Ext.getCmp('Esb2_Svcpoint_Grid_Id')
						.getSelectionModel().getSelection();
				form.loadRecord(selection[0]);
			}

		}
	}, {
		text : '删除',
		handler : function() {
			var form = this.up('form').getForm();
			// 如果是修改界面点击删除按钮：删除list界面记录，发请求删除后台记录，关闭窗口(如果是新增服务配置界面点击删除按钮不做任何处理)
			if (form.url.indexOf('update.action') > 0) {
				Ext.MessageBox.confirm('client', '你确认删除该记录？', function(btn) {
					if (btn == "yes") {
						Ext.Ajax.request({
									url : '../svcpoint2/delete.action',
									params : {
										codes : form.findField('code')
												.getValue().trim()
									},
									success : function(response) {
										var store = Ext.data.StoreManager
												.lookup('Esb2_svcpoint_Store_Id');
										store.load();
										//关闭窗口
										var win = Ext.getCmp('esb2_svpoint_win');
										win.close();
										//重新加载数据
									},
									failure : function(response) {
										var result = Ext
												.decode(respone.responseText);
										Ext.MessageBox.alert('server', result);
									}
								});
					}
				});
			}
		}
	}]
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