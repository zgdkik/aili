// noticUserGrid
var noticUserGrid = Ext.create('Ext.grid.Panel', {
			// minwidth : window.screen.availWidth * 0.5,
			// height : window.screen.availWidth * 0.5,
			title : '预警人员列表',
			frame : true,
			autoScroll : true, // 自动添加滚动条
			stripeRows : true, // 交替行效果
			selModel : Ext.create('Ext.selection.CheckboxModel'),
			store : Ext.create('noticUserStore', {
						'id' : 'noticUserStore1'
					}),
			columns : [{
						id : 'noticUserId',
						name : 'noticUserId',
						hidden : true,
						text : 'noticUserId',
						dataIndex : 'id'
					}, {
						id : 'userName',
						name : 'userName',
						text : '姓名',
						dataIndex : 'userName'
					}, {
						id : 'telPhone',
						name : 'telPhone',
						text : '电话',
						dataIndex : 'telPhone'
					}, {
						id : 'mobilePhone',
						name : 'mobilePhone',
						text : '移动电话',
						dataIndex : 'mobilePhone'
					}, {
						id : 'email',
						name : 'email',
						text : '邮件地址',
						dataIndex : 'email'
					}, {
						name : 'pjVersion',
						text : '版本号',
						dataIndex : 'pjVersion'
					}],
			// 顶部工具栏
			tbar : [{
						xtype : 'button',
						text : '删除',
						handler : function() {
							var selection = noticUserGrid.getSelectionModel().getSelection();
							if (selection.length == 0) {
								Ext.MessageBox.alert("温馨提醒", "请选中至少一条记录！");
								return;
							}
							var noticUserIds = "";
							var noticUserNames = "";
							for (var i = 0; i < selection.length; i++) {
								noticUserIds = noticUserIds + selection[i].get('id') + ",";
								noticUserNames = noticUserNames + selection[i].get('userName') + ",";
							}
							Ext.MessageBox.confirm('确认框', '你确认删除以下配置 信息:' + noticUserNames, function(btn) {
										if (btn == "yes") {
											Ext.Ajax.request({
														url : 'deleteNoticUsersByIds.action',
														params : {
															noticUserIds : noticUserIds
														},
														success : function(response) {
															var result = Ext.decode(response.responseText);
															Ext.MessageBox.alert('执行结果', result.message);
															Ext.data.StoreManager.lookup('noticUserStore1').remove(selection);
														},
														failure : function(response) {
															var result = Ext.decode(respone.responseText);
															Ext.MessageBox.alert('执行结果', result);
														}
													});
										}
									});
							
						}
					}, {
						xtype : 'button',
						text : '修改',
						handler : function() {
							var selection = noticUserGrid.getSelectionModel().getSelection();
							if (selection.length != 1) {
								Ext.MessageBox.alert("温馨提醒", "请选中一条记录进行修改！");
								return;
							}
							var record = selection[0];
							var url = 'updateNoticUser.action';
							openNoticUserForm(url, record);
							Ext.data.StoreManager.lookup('noticUserStore1').load();
						}
					}, {
						xtype : 'button',
						text : '新增',
						handler : function() {
							var url = 'addNoticUser.action';
							openNoticUserForm(url);
							Ext.data.StoreManager.lookup('noticUserStore1').load();
						}
					}],
			// 分页工具栏
			bbar : Ext.create('Ext.PagingToolbar', {
						store : Ext.data.StoreManager.lookup('noticUserStore1'),
						pageSize : 20,
						displayInfo : true,
						displayMsg : '当前记录数为从 第{0}条 - 第{1}条 ，共 {2}条',
						emptyMsg : "无记录"
					})
		});
// 打开NoticUser编辑界面
function openNoticUserForm(url, parm) {
	var record = parm;
	if (!record) {
		record = new NoticUserInfoModel();
	}
	if (!Ext.getCmp('win_addNoticUser')) {
		Ext.create('Ext.window.Window', {
					title : '预警用户编辑',
					resizable : false,// 窗口大小不可变
					modal : true,// 模态对话框
					id : 'win_addNoticUser',
					closeAction : 'hide',
					// width : window.screen.availWidth * 0.4,
					// height : window.screen.availHeight * 0.3,
					// layout : 'fit',
					items : [Ext.create('noticUserForm', {
								'id' : 'noticUserForm',
								'record' : record,
								'url' : url
							})]
				});
	}
	Ext.getCmp('win_addNoticUser').show();
	Ext.getCmp('noticUserForm').getForm().loadRecord(record);
	Ext.getCmp('noticUserForm').url = url;
	if (parm) {
		Ext.getCmp('editform_select').setDisabled(true);
	} else {
		Ext.getCmp('editform_select').enable(true);
	}
}

function openSelectSysUserGrid() {
	Ext.create('Ext.window.Window', {
				id : 'win_selectSysUser',
				closeAction : 'hide',
				title : '系统用户列表',
				layout : 'border',
				modal : true,// 模态对话框
				width : window.screen.availWidth * 0.4,
				height : window.screen.availWidth * 0.3,
				items : [{
							region : 'north',
							items : [Ext.create('Ext.form.Panel', {
										frame : true,
										title : '查询条件',
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
													xtype : 'textfield',
													id : 'select_form_userName',
													width : 250,
													fieldLabel : '姓名'
												}, {
													xtype : 'button',
													text : '查询',
													width : 100,
													hight : 25,
													handler : function() {
														Ext.getCmp('selectSysUserGrid').store.load({
																	params : {
																		userName : Ext.getCmp('select_form_userName').getValue()
																	}
																});
													}
												}]
									})]
						}, {
							region : 'center',
							items : [Ext.create('Ext.grid.Panel', {
										id : 'selectSysUserGrid',
										frame : true,
										autoScroll : true, // 自动添加滚动条
										stripeRows : true, // 交替行效果
										title : '查询结果',
										selModel : Ext.create('Ext.selection.CheckboxModel'),
										store : Ext.create('systemUserStore', {
													'id' : 'select_systemUserStore'
												}),
										columns : [{
													id : 'select_noticUserId',
													name : 'noticUserId',
													hidden : true,
													text : 'noticUserId',
													dataIndex : 'id'
												}, {
													id : 'select_userName',
													name : 'userName',
													text : '姓名',
													dataIndex : 'userName'
												}, {
													id : 'select_telPhone',
													name : 'telPhone',
													text : '电话',
													dataIndex : 'telPhone'
												}, {
													id : 'select_mobilePhone',
													name : 'mobilePhone',
													text : '移动电话',
													dataIndex : 'mobilePhone'
												}, {
													id : 'select_email',
													name : 'email',
													text : '邮件地址',
													dataIndex : 'email'
												}],
										listeners : {
											'itemdblclick' : {
												fn : function(grid, rowIndex, e) {
													var record = grid.getSelectionModel().getSelection()[0];
													Ext.getCmp('noticUserForm').getForm().loadRecord(record);
													Ext.getCmp('win_selectSysUser').close();
												}
											}
										}
									})]
						}]
			});
	// Ext.getCmp('selectSysUserGrid').store.load();
	Ext.getCmp('win_selectSysUser').show();
}