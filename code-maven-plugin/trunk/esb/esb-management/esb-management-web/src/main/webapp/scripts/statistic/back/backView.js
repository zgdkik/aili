function getTimeStr(date){
	var timeStr = '';
	if(date != null){
		timeStr += date.getFullYear() + '-' + 
		((date.getMonth()+1) > 9 ? (date.getMonth()+1) : '0' + (date.getMonth()+1)) + '-' + 
		(date.getDate() > 9 ? date.getDate() : ('0' + date.getDate())) + ' ' + 
		(date.getHours() > 9 ? date.getHours() : ('0' + date.getHours())) + ':' + 
		(date.getMinutes() > 9 ? date.getMinutes() : ('0' + date.getMinutes())) + ':' + 
		(date.getSeconds() > 9 ? date.getSeconds() : ('0' + date.getSeconds()));
	}
	return timeStr;
}
var statisticStore = Ext.create('statisticStore', {
	id : 'statisticStore',
	autoload : true,
	listeners : {
		beforeload : function(store, operation, e) {
			if (Ext.getCmp('queryForm')) {
				var form = Ext.getCmp('queryForm').getForm();
				var data = Ext.getCmp('queryForm').getForm().getFieldValues();
				var checkedArray = form.findField('reportType').getChecked();
				// alert(checkedArray[0].inputValue);
				Ext.apply(operation, {
					params : {
						'bean.startTime' : data.startTime,
						'bean.endTime' : data.endTime,
						'bean.svcCode' : data.svcCode,
						'bean.type' : checkedArray[0].inputValue
					}
				});
			}
		}
	}
});
// --------------折线图----------------
var westChart = Ext.create('Ext.chart.Chart', {
	region : 'center',
	width : '80%',
	// chart三要素：store，axes，series
	store : statisticStore,
	// 图例
	legend : {
		position : 'top',
		boxStroke : 'border:0px;',
		labelFont : '11px Helvetica'
	},
	axes : [ {
		title : '日期',
		label : {
			rotate : {
				degrees : 90
			}
		},// 轴上的文字旋转315°
		type : 'Category',
		position : 'bottom',
		fields : [ 'statisticDate' ],
		// majorTickSteps : 24,// 大刻度个数
		// minorTickSteps : 1,
		minimum : 0
	}, {
		title : '调用次数',
		grid : {
			odd : {
				opacity : 1,
				fill : '#ddd',
				stroke : '#bbb',
				'stroke-width' : 1
			}
		},
		type : 'Numeric',
		position : 'left',
		fields : [ 'calledCount' ]/*,
		minimum : 0,
		maximum:90000*/
	}, {
		title : '响应时间',
		grid : {
			odd : {
				opacity : 1,
				fill : '#ddd',
				stroke : '#bbb',
				'stroke-width' : 1
			}
		},
		type : 'Numeric',
		position : 'right',
		fields : [ 'maxrspTime','avgrspTime'],
		minimum : 0/*,
		maximum : 40000*/
	} ],
	series : [ {
		type : 'line',
		smooth : true,
		axis : 'right',
		highlight : true,// 鼠标选中高亮
		xField : 'statisticDate',
		yField : 'avgrspTime',
		markerConfig : {
			type : 'circle',
			size : 4,
			radius : 4,
			'stroke-width' : 0
		},
		tips : {
			trackMouse : true,
			width : 150,
			renderer : function(storeItem, item) {
				this.setTitle("平均响应:"+ storeItem.get('avgrspTime')+"ms");
			}
		}
	},{
		type:'line',
		axis : 'right',
		showLegend:true,
		smooth:true,
		xField:'statisticDate',
		yField:'maxrspTime',
		tips : {
			trackMouse : true,
			width : 150,
			renderer : function(storeItem, item) {
				this.setTitle("最大响应:"+ storeItem.get('maxrspTime')+"ms");
			}
		}
	},{
		type:'line',
		axis : 'left',
		showLegend:true,
		smooth:true,
		xField:'statisticDate',
		yField:'calledCount',
		tips : {
			trackMouse : true,
			width : 150,
			renderer : function(storeItem, item) {
				this.setTitle("调用次数:"+ storeItem.get('calledCount'));
			}
		}
	}]

});

// 服务配置panel
var svcQueryPanel = Ext.create('Ext.grid.Panel', {
	frame : true,
	selModel : Ext.create('Ext.selection.CheckboxModel'),
	store : Ext.create('serviceStore', {
		'id' : 'serviceStore',
		listeners : {
			beforeload : function(store, operation, e) {
				if (Ext.getCmp('queryForm')) {
					var data = Ext.getCmp('queryForm').getForm()
							.getFieldValues();
					Ext.apply(operation, {
						params : {
							'svcProvdId' : data.svcProvdId
						}
					});
				}
			}
		}
	}),
	columns : [ {
		name : 'id',
		hidden : true,
		width : 200,
		dataIndex : 'id'
	}, {
		name : 'svcName',
		text : '服务名称',
		dataIndex : 'svcName'
	}, {
		name : 'svcCode',
		text : '服务编码',
		width : 200,
		dataIndex : 'svcCode'
	}, {
		name : 'calledCount',
		text : '总调用次数',
		width : 200,
		dataIndex : 'calledCount'
	}, {
		name : 'excptnCount',
		text : '出现异常次数',
		width : 200,
		dataIndex : 'excptnCount'
	}, {
		name : 'percents',
		text : '成功率',
		dataIndex : 'percents'
	} ],
	listeners : {
		itemdblclick : function(me, record) {
			var selection = svcQueryPanel.getSelectionModel().getSelection();
			if (selection.length != 1) {
				Ext.MessageBox.alert("提示", "请选中一条记录！");
				return;
			}
			var form = Ext.getCmp('queryForm').getForm();
			form.findField('svcCode').setValue(selection[0].data.svcCode);
			form.findField('svcName').setValue(selection[0].data.svcName);
			svcQuerywin.hide();
		}
	},
	// 顶部工具栏
	tbar : [ {
		xtype : 'button',
		text : '确定',
		handler : function() {
			var selection = svcQueryPanel.getSelectionModel().getSelection();
			if (selection.length != 1) {
				Ext.MessageBox.alert("提示", "请选中一条记录！");
				return;
			}
			var form = Ext.getCmp('queryForm').getForm();
			form.findField('svcCode').setValue(selection[0].data.svcCode);
			form.findField('svcName').setValue(selection[0].data.svcName);
			svcQuerywin.hide();
			/*
			 * var id = Ext.getCmp('svcQueryPanel').parentId;
			 * Ext.getCmp(id).hide();
			 */
		}
	} ],
	bbar : Ext.create('Ext.PagingToolbar', {
		store : Ext.data.StoreManager.lookup('serviceStore'),
		displayInfo : true,
		displayMsg : 'Displaying topics {0} - {1} of {2}',
		emptyMsg : "No topics to display"
	})
});
// 查询条件
Ext
		.define(
				'queryForm',
				{
					extend : 'Ext.form.Panel',
					frame : true,
					region : 'north',
					title : '查询条件',
					layout : {
						type : 'column'
					},
					items : [
							{
								xtype : 'datetimefield',
								format : 'Y-m-d H:i',
								editable : false,
								name : 'startTime',
								fieldLabel : '开始时间',
								columnWidth : '0.3',
								listeners : {
									select : function(th) {
										var fromDate = th.getValue();
									}
								}
							},
							{
								xtype : 'datetimefield',
								format : 'Y-m-d H:i',
								editable : false,
								name : 'endTime',
								margin : '0 0 0 10',
								fieldLabel : '结束时间',
								columnWidth : '0.3',
								listeners : {
									select : function(th) {
									}
								}
							},
							{
								xtype : 'combo',
								width : 250,
								store : Ext.create('systemStore'),
								margin : '0 0 0 20',
								displayField : 'name',
								editable : false,
								valueField : 'name',
								fieldLabel : '服务提供方',
								columnWidth : '0.3',
								name : 'svcProvdId',
								emptyText : '---请选择---',
								forceSelection : true,
								listeners : {
									select : function(combo, record, opts) {
										Ext.getCmp('queryForm').getForm().findField('svcCode').setValue(null);
										Ext.data.StoreManager.lookup(
												'serviceStore').load();
									}
								}
							},
							{
								xtype : 'combo',
								fieldLabel : '接口服务',
								name : 'svcCode',
								tpl : new Ext.XTemplate(
										// 渲染接口服务combox
										'<tpl for=".">',
										'<div class="list-ct x-body-masked" style="overflow: auto; ">',
										'<li role="option" class="x-boundlist-item" id="ext-gen1170"> {[values.svcName +values.svcCode ]}</li>'
												+ '</div>', '</tpl>'),
								columnWidth : 0.4,
								valueField : 'svcCode',
								displayField : 'svcName',
								emptyText : '---请选择---',
								editable : false,
								listConfig : {// 取消加载提示框
									loadMask : false
								},
								store : Ext.create('serviceStore',
												{
													'id' : 'serviceStore',
													listeners : {
														beforeload : function(store,operation, e) {
															
															if (Ext.getCmp('queryForm')) {
																var data = Ext.getCmp('queryForm').getForm().getFieldValues();
																Ext.apply(operation,{params : {'svcProvdId' : data.svcProvdId}});
															}
														}
													}
												})
							}, {
								xtype : 'radiogroup',
								fieldLabel : '报表类型',
								layout : 'table',
								vertical : true,
								name : 'reportType',
								items : [ {
									boxLabel : '时报表',
									name : 'report',
									inputValue : '1',
									checked : true
								// 默认选择月报表
								}, {
									boxLabel : '日报表',
									name : 'report',
									inputValue : '2'
								}, {
									boxLabel : '月报表',
									name : 'report',
									inputValue : '3'
								} ]
							} ]
				});
// 统计结果
Ext
		.define(
				'statisticGrid',
				{
					extend : 'Ext.grid.Panel',
					region : 'center',
					height : '30%',
					items : null,
					store : null,
					initComponent : function() {
						me = this;
						me.store = statisticStore;
						me.columns = me.getColumns();
						this.callParent();
					},
					listeners : {

					},
					tbar : [
							{
								xtype : 'button',
								margin : '0 0 0 0',
								text : '查询',
								handler : function() {
									// 检查查询条件
									var svcCode = Ext.getCmp('queryForm')
											.getForm().findField('svcCode')
											.getValue();

									if (svcCode == null || svcCode == "") {
										Ext.MessageBox.alert('', '请选择一项服务');
										return;
									}

									Ext.getCmp('statisticGrid').getStore()
											.load();
								}
							},
							{
								xtype : 'button',
								margin : '0 0 0 -200',
								text : '导出',
								handler : function() {
									if (statisticStore.count() != 0) {
										var form = Ext.getCmp('queryForm')
												.getForm();
										var data = form.getFieldValues();
										
										var startTimeStr = getTimeStr(data.startTime);
										var endTimeStr = getTimeStr(data.endTime);
										
										var checkedArray = form.findField(
												'reportType').getChecked();
										var windowId = "showException";
										Ext
												.create(
														'Ext.window.Window',
														{
															id : windowId,
															title : '下载页面',
															width : window.screen.availWidth * 0.5,
															height : window.screen.availHeight * 0.3,
															html : '<iframe src ="../statistic/pfmcBackExport2csv.action?bean.startTime='
																	+ startTimeStr
																	+ "&bean.endTime="
																	+ endTimeStr
																	+ "&bean.svcCode="
																	+ (data.svcCode == null ? ""
																			: data.svcCode)
																	+ "&bean.type="
																	+ checkedArray[0].inputValue
																	+ '" width="100%" height="100%" frameborder="no" border="0" framespacing="0" align="left"></iframe>'
														}).show();
									} else {
										Ext.MessageBox.alert("提示", "没有数据可导出！");
									}
								}
							}, {
								xtype : 'button',
								text : '打印',
								margin : '0 0 0 0',
								handler : function() {
									window.print();
								}
							} ],
					getColumns : function() {
						return [ {
							header : '服务提供方',
							name : '',
							dataIndex : '',
							hidden:true
						}, {
							header : '服务名称',
							name : 'svcName',
							dataIndex : 'svcName'
						}, {
							header : '服务编码',
							name : 'svcCode',
							width:200,
							dataIndex : 'svcCode'
						}, {
							header : '统计时间',
							name : 'statisticDate',
							dataIndex : 'statisticDate'
						}, {
							header : '总调用次数',
							name : 'calledCount',
							dataIndex : 'calledCount'
						}, {
							header : '平均响应时间(ms)',
							name : 'avgrspTime',
							dataIndex : 'avgrspTime'
						}, {
							header : '最大响应时间(ms)',
							name : 'maxrspTime',
							dataIndex : 'maxrspTime'
						} ]
					}
				});

var svcQuerywin = Ext.create('Ext.window.Window', {
	// id : winId,
	height : 600,
	width : 600,
	constrainHeader : true,
	title : '服务配置',
	layout : 'fit',
	closeAction : 'hide',
	items : [ svcQueryPanel ]
});

Ext.onReady(function() {
	Ext.create('Ext.Viewport', {
		frame : true,
		layout : 'border',
		items : [ Ext.create('queryForm', {
			id : 'queryForm',
			region : 'north',
			title : '查询条件'
		}), Ext.create('statisticGrid', {
			id : 'statisticGrid'

		}), Ext.create('Ext.panel.Panel', {
			frame : true,
			region : 'south',
			height : '50%',
			layout : 'fit',
			title : '统计报表',
			items : [ westChart ]
		}) ]

	});
});