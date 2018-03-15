// 一般的容器(无样式)，用于按钮容器继承
Ext.define('BasicPanel', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.basicpanel',
			border : false,
			autoScroll : true,
			layout : 'fit'
		});

Ext.define('ButtonPanelRole', {
			extend : 'BasicPanel',
			width : '100%',
			buttonAlign : 'center',
			layout : 'column',
			items : [{
				columnWidth : 1,
				xtype : 'container',
				style : 'padding-top:100px;border:none',
				width : '100%',
				items : [{
					xtype : 'button',
					text : '&gt;',
					width : 39,
					// 添加所选客户
					handler : function() {
						// 得到所选客户
						var selection = Ext.getCmp("leftGridPanel")
								.getSelectionModel().getSelection();
						// 已选择客户store
						var chooseStore = Ext.getCmp("rightGridPanel").store;
						for (var i = 0; i < selection.length; i++) {// 遍历所选客户
							if (!Ext.isEmpty(chooseStore.getById(selection[i]
									.get("id")))) {// 判断是否有重复
								return false;
							} else {
								Ext.getCmp("leftGridPanel").store
										.remove(selection[i]);
								// 添加到已选择客户store里
								chooseStore.insert(i, selection[i]);
							}
						}
					}
				}]
			}, {
				columnWidth : 1,
				width : '100%',
				xtype : 'container',
				style : 'padding-top:100px;border:none',
				items : [{
					xtype : 'button',
					text : '&lt;',
					width : 39,
					// 移除所选客户
					handler : function() {
						// 得到已选客户
						var selection = Ext.getCmp("rightGridPanel")
								.getSelectionModel().getSelection();
						// 待选择客户store
						var store = Ext.getCmp("leftGridPanel").store;
						for (var j = 0; j < selection.length; j++) {// 遍历所选客户
							Ext.getCmp("rightGridPanel").store
									.remove(selection[j]);
							// 添加到已选择客户store里
							store.insert(j, selection[j]);
						}
					}
				}]
			}]
		});

var selModel = Ext.create('Ext.selection.CheckboxModel', {
			mode : 'SIMPLE',
			id : 'selModelId'
		});
Ext.define('LeftGridPanel', {
			extend : 'Ext.grid.Panel',
			store : null,
			columns : null,
			selModel : selModel,
			viewConfig : {// 可拖动插件
				forceFit : true
			},
			initComponent : function() {
				var me = this;
				this.dockedItems = [{
							xtype : 'pagingtoolbar',
							store : me.store,
							dock : 'bottom',
							displayInfo : true,
							autoScroll : true
						}];
				this.callParent();
			}
		});

var chooseSelModel = Ext.create('Ext.selection.CheckboxModel', {
			mode : 'SIMPLE',
			id : 'chooseSelModelId'
		});
Ext.define('RightGridPanel', {
			extend : 'Ext.grid.Panel',
			store : null,
			columns : null,
			selModel : chooseSelModel,
			viewConfig : {// 可拖动插件
				forceFit : true
			}
		});

Ext.define('EsbPerformanceMonitorPanel', {
			extend : 'BasicPanel',
			layout : 'border',
			searchLeftResult : null, // 查询客户列表（左边Grid）
			searchRightResult : null, // 已选择客户列表（右边Grid）
			initComponent : function() {
				var me = this;
				var searchLeftStore = Ext.create('LeftNoticUserInfoStore');
				var searchRightStore = Ext.create('RightNoticUserInfoStore');
				me.searchLeftResult = Ext.create('LeftGridPanel', {
							id : 'leftGridPanel',
							heigth : '500',
							columns : me.getColumns(),
							store : searchLeftStore
						});
				me.searchRightResult = Ext.create('RightGridPanel', {
							id : 'rightGridPanel',
							heigth : '100',
							columns : me.getColumns(),
							store : searchRightStore
						});
				me.items = me.getItems();
				this.callParent();
			},
			getItems : function() {
				var me = this;
				return [{
					region : 'north',
					xtype : 'basicpanel',
					height : 90,
					layout : 'anchor',
					items : [{
						xtype : 'fieldset',
						title : '查询条件',
						height : 75,
						defaults : {
							labelAlign : 'right',
							labelWidth : 80,
							margin : '10 5 10 5',
							width : 240
						},
						layout : 'hbox',
						items : [{
									xtype : 'textfield',
									fieldLabel : '人员名称',
									name : 'userName',
									id : 'userName'
								}, {
									xtype : 'button',
									text : '查询',
									width : 65,
									handler : function() {
										Ext.getCmp('leftGridPanel').store.load(
												{
													params : {
														userName : Ext
																.getCmp('userName')
																.getValue()
													}
												});

									}
								}]
					}]
				}, {
					region : 'center',
					xtype : 'basicpanel',
					layout : 'border',
					items : [{
								region : 'center',
								xtype : 'panel',
								layout : 'border',
								flex : 1,
								items : [{
											region : 'center',
											xtype : 'panel',
											layout : 'fit',
											items : [me.searchLeftResult]
										}, {
											region : 'east',
											xtype : 'panel',
											width : 45,
											layout : 'fit',
											items : [Ext
													.create('ButtonPanelRole')]
										}]
							}, {
								region : 'east',
								xtype : 'panel',
								layout : 'fit',
								flex : 1,
								items : [me.searchRightResult]
							}],
					dockedItems : [{
								xtype : 'toolbar',
								layout : {
									type : 'hbox',
									pack : 'center',
									align : 'middle'
								},
								items : [{
											text : '确定',
											width : 60,
											style : 'margin-left:50px;',
											handler : function() {
												var store = Ext.getCmp("rightGridPanel").store;
												var names ="";
												var ids = "";
												store.each(function(record){
													names = names +record.get('userName')+",";
													ids = ids + record.get('id')+",";
												});
											//	Ext.getCmp('rightPanel').getChildByElement('personName').setValue(names);
								
												Ext.getCmp('personName').setValue(names);
												//Ext.getCmp('leftPanel').getChildByElement('personId').setValue(ids);
												Ext.getCmp('personId').setValue(ids);
											//	alert("names:"+names+"personId:"+ids);
												Ext.getCmp('wind_noticeUSer').hide();
											}
										}]
							}]
				}];
			},
			getColumns : function() {//查询结果列
				return [{
							header : 'id',
							dataIndex : 'id',
							hidden : true
						}, {
							header : '人员名称',
							dataIndex : 'userName'
						}, {
							header : '人员电话',
							dataIndex : 'telPhone'
						}, {
							header : '人员手机',
							dataIndex : 'mobilePhone'
						}, {
							header : 'Email',
							dataIndex : 'email',
							width : 150
						}, {
							header : '版本号',
							dataIndex : 'pjVersion'
						}];
			}
		});