/**
 * EDIT UI
 */ 
Ext.define('EditPanel', {
	extend : 'Ext.form.Panel',
	frame : true,
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
				fieldLabel : '代理名称',
				width : 250,
				name : 'agentName',
				allowBlank : false
			}, {
				fieldLabel : '代理编码',
				width : 250,
				name : 'userName',
				allowBlank : false
			},{
				fieldLabel : '状态',
				width : 250,
				name : 'status',
				allowBlank : false
			},{
				fieldLabel : '密码',
				width : 250,
				name : 'passwd',
				allowBlank : false
			},{
				fieldLabel : '确认密码',
				width : 250,
				name : 'confirePasswd',
				allowBlank : false
			},{
				fieldLabel : 'id',
				width : 250,
				name : 'id',
				xtype:'hiddenfield'
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
						'info.agentName' : form.findField('agentName').getValue().trim(),
						'info.userName' : form.findField('userName').getValue().trim(),
						'info.passwd' : form.findField('passwd').getValue()
								.trim(),
						'info.id' : form.findField('id').getValue()
								.trim(),
						'info.status' : form.findField('status').getValue()
								.trim()
					},
					success : function(backServiceForm, action) {
						Ext.MessageBox.alert('server', action.result.message);
						var store = Ext.data.StoreManager.lookup(CONSTANTS_AGENT_STORE_ID);
						store.load();
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
				var selection = Ext.getCmp(CONSTANTS_LIST_GRID_ID)
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
									url : CONSTANTS_OPERATION_DELETE_URL,
									params : {
										codes : form.findField('code')
												.getValue().trim()
									},
									success : function(response) {
										var store = Ext.data.StoreManager
												.lookup(CONSTANTS_AGENT_STORE_ID);
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