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
function createPieChart(){
	var pieChart = Ext.create('Ext.chart.Chart', {
		//region : 'east',
		layout : 'fit',
		id:'pieChart',
		width : '50%',
		height: '100%',
		region : 'east',
		animate : true,
        shadow: true,
        legend: {
        	position: 'right'
        },
		store : pieStore,
		showInLegend : true,
		theme : 'Base:gradients',
		series : [{
			type : 'pie',
			field : 'exceptionCount',
			//donut : 30,
			showInLegend : true,
			tips : {
				trackMouse : true,
				width : 280,
				height : 80,
				renderer : function(storeItem, item) {
					var total = 0;
					pieStore.each(function(rec) {
								total += rec.get('exceptionCount');
							});
					
					this.setTitle(
							'服务名:' + storeItem.get('svcName') + '<br>' +
							'服务编码:' + storeItem.get('svcCode') + '<br>' +
							'总调用次数:' + storeItem.get('totalCount') + '次<br>' +
							'异常发生次数:' + storeItem.get('exceptionCount') + '次'
//							+ Math.round(storeItem.get('exceptionCount') / total * 100) + '%'
							);
				}
			},
			highlight : {
				segment : {
					margin : 20
				}
			},
			label : {
				field : 'svcName',
				display : 'rotate',
				contrast : true,
				font : '15px Arial',
//				renderer:function(v){
//					if(v.length > 10){
//						return v.substring(0,7) + '...';
//					}
//					return v;
//				}
			}
		}]
	});
	return pieChart;
}
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
			axes : [{
						title : '时间',
						label : {
							rotate : {
								degrees : 90
							}
						},//轴上的文字旋转315°
						type : 'Category',
						position : 'bottom',
						fields : ['statisticDate'],
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
						fields : ['calledCount']
						/*				renderer : function(lbl) {//这边是需求 太多了 然后少显示些y坐标轴的值得                                    
						 var count = statisticStore.data.items.length;
						 var num1 = parseInt(count / 2);
						 count % 2 > 0 ? num1++ : '';
						 return lbl % num1 == 0 ? lbl : '';
						 },*/
					//	minimum : 0,
					//	maximum : 100
					}],
			series : [{
				type : 'line',
				smooth : true,
				axis : 'left',
				highlight : true,// 鼠标选中高亮
				xField : 'statisticDate',
				yField : 'calledCount',
				markerConfig : {
					type : 'circle',
					size : 4,
					radius : 4,
					'stroke-width' : 0
				},
				tips : {
					trackMouse : true,
					width : 300,
					renderer : function(storeItem, item) {
						this.setTitle("时间：" + storeItem.get('statisticDate')
								+ " 调用次数:" + storeItem.get('calledCount')
								+ "异常次数:" + storeItem.get('excptnCount'));
					}
				}
			}]

		});

// -----------饼图----------------
var pieChart = Ext.create('Ext.chart.Chart', {
			//region : 'east',
			layout : 'fit',
			id:'pieChart',
			width : '20%',
			region : 'east',
			animate : true,
            shadow: true,
            legend: {
                position: 'right'
            },
			store : pieStore,
			showInLegend : true,
			theme : 'Base:gradients',
			series : [{
				type : 'pie',
				field : 'exceptionCount',
				//donut : 30,
				showInLegend : true,
				tips : {
					trackMouse : true,
					width : 300,
					height : 100,
					renderer : function(storeItem, item) {
						var total = 0;
						pieStore.each(function(rec) {
									total += rec.get('exceptionCount');
								});
						
						this.setTitle(storeItem.get('svcCode')
								+ ':'
								+ Math.round(storeItem.get('exceptionCount') / total
										* 100) + '%');
					}
				},
				highlight : {
					segment : {
						margin : 20
					}
				},
				label : {
					field : 'svcCode',
					display : 'rotate',
					contrast : true,
					font : '18px Arial',
					renderer:function(v){
						return v.substring(1,3);
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
									var data = Ext.getCmp('queryForm')
											.getForm().getFieldValues();
									Ext.apply(operation, {
												params : {
													'svcProvdId' : data.svcProvdId
												}
											});
								}
							}
						}
					}),
			columns : [{
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
					}],
			listeners : {
				itemdblclick : function(me, record) {
					var selection = svcQueryPanel.getSelectionModel()
							.getSelection();
					if (selection.length != 1) {
						Ext.MessageBox.alert("提示", "请选中一条记录！");
						return;
					}
					var form = Ext.getCmp('queryForm').getForm();
					form.findField('svcCode')
							.setValue(selection[0].data.svcCode);
					form.findField('svcName')
							.setValue(selection[0].data.svcName);
					svcQuerywin.hide();
				}
			},
			// 顶部工具栏
			tbar : [{
				xtype : 'button',
				text : '确定',
				handler : function() {
					var selection = svcQueryPanel.getSelectionModel()
							.getSelection();
					if (selection.length != 1) {
						Ext.MessageBox.alert("提示", "请选中一条记录！");
						return;
					}
					var form = Ext.getCmp('queryForm').getForm();
					form.findField('svcCode')
							.setValue(selection[0].data.svcCode);
					form.findField('svcName')
							.setValue(selection[0].data.svcName);
					svcQuerywin.hide();
					/*
					 * var id = Ext.getCmp('svcQueryPanel').parentId;
					 * Ext.getCmp(id).hide();
					 */
				}
			}],
			bbar : Ext.create('Ext.PagingToolbar', {
						store : Ext.data.StoreManager.lookup('serviceStore'),
						displayInfo : true,
						displayMsg : 'Displaying topics {0} - {1} of {2}',
						emptyMsg : "No topics to display"
					})
		});
// 查询条件
Ext.define('queryForm', {
	extend : 'Ext.form.Panel',
	frame : true,
	region : 'north',
	title : '查询条件',
	layout : {
		type : 'column',
		columns:3
	},
	items : [{
				xtype : 'datetimefield',
				format : 'Y-m-d H:i',
				editable : false,
				name : 'startTime',
				fieldLabel : '开始时间',
				columnWidth:'0.3',
				labelWidth:55,
				listeners : {
					select : function(th) {
						var fromDate = th.getValue();
					}
				}
			}, {
				xtype : 'datetimefield',
				format : 'Y-m-d H:i',
				editable : false,
				name : 'endTime',
				margin : '0 0 0 10',
				fieldLabel : '结束时间',
				columnWidth:'0.3',
				labelWidth:55,
				listeners : {
					select : function(th) {
					}
				}
			}, {
				xtype : 'combo',
				width : 250,
				store : Ext.create('systemStore'),
				margin : '0 0 0 10',
				displayField : 'name',
				editable : false,
				valueField : 'name',
				fieldLabel : '客户端接入系统',
				columnWidth:'0.3',
				labelWidth:90,
				name : 'svcProvdId',
				emptyText : '---请选择---',
				forceSelection : true,
				listeners : {
					select : function(combo, record, opts) {
						Ext.getCmp('queryForm').getForm().findField('svcCode').setValue(null);
						Ext.data.StoreManager.lookup('serviceStore').load();
					}
				}
			},{
				xtype : 'combo',
				fieldLabel : '接口服务',
				name : 'svcCode',
				tpl : new Ext.XTemplate(//渲染接口服务combox
						'<tpl for=".">',
						'<div class="list-ct x-body-masked" style="overflow: auto; ">',
						'<li role="option" class="x-boundlist-item" id="ext-gen1170"> {[values.svcName +values.svcCode ]}</li>'
								+ '</div>', '</tpl>'),
				columnWidth : 0.4,
				labelWidth:55,
				margin : '0 0 0 10',
				valueField : 'svcCode',
				displayField : 'svcName',
				emptyText : '---请选择---',
				editable : false,
				listConfig : {//取消加载提示框
						loadMask : false
				},
				store : Ext.create('serviceStore', {
							'id' : 'serviceStore',
							listeners : {
								beforeload : function(store, operation, e) {
									if (Ext.getCmp('queryForm')) {
										var data = Ext.getCmp('queryForm')
												.getForm().getFieldValues();
										Ext.apply(operation, {
													params : {
														'svcProvdId' : data.svcProvdId
													}
												});
									}
								}
							}
						})
			},{
				xtype : 'combo',
				fieldLabel : '异常类型',
				margin : '0 0 0 10',
				labelWidth:55,
				name : 'exceptionType',
				emptyText : '全部',
				editable : false,
				valueField : 'exceptionType',
				displayField : 'exceptionName',
				store : Ext.create('Ext.data.Store',{
					fields : ['exceptionType','exceptionName'],
					data : [
					        {exceptionType:'1',exceptionName:'系统异常'},
					        {exceptionType:'2',exceptionName:'业务异常'}
					       ],
					autoLoad : true
				}),
			}, {
				xtype : 'radiogroup',
				fieldLabel : '报表类型',
				layout : 'table',
				vertical : true,
				name : 'reportType',
				items : [{
					boxLabel : '日报表',
					name : 'report',
					inputValue : '1',
					checked : true
						// 默认选择日报表
					}, {
					boxLabel : '月报表',
					name : 'report',
					inputValue : '2'
				}, {
					boxLabel : '年报表',
					name : 'report',
					inputValue : '3'
				}]
			}]
});
// 统计结果
Ext.define('statisticGrid', {
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
			tbar:[
			{
				xtype : 'button',
				margin : '0 0 0 0',
				text : '查询',
				handler : function() {
					// 检查查询条件
					var svcCode = Ext.getCmp('queryForm').getForm()
							.findField('svcCode').getValue();
					
/*					  if(svcCode == null || svcCode ==""){
					  Ext.MessageBox.alert('', '请选择一项服务'); return; }*/

					Ext.getCmp('statisticGrid').getStore().load();
					if(pieChart ){
						pieChart.destroy( );
					}
					pieStore.removeAll();
					pieStore.load();
					pieChart = createPieChart();
					Ext.getCmp('chartPanel').removeAll();
					Ext.getCmp('chartPanel').add(pieChart);
					//new piechart
					//Ext.getCmp('pieChart').refresh();
				}
			}, {
				xtype : 'button',
				margin : '0 0 0 -200',
				text : '导出',
				handler : function() {
					if (statisticStore.count() != 0) {
						var form = Ext.getCmp('queryForm').getForm();
						var data = form.getFieldValues();
						var checkedArray = form.findField('reportType')
								.getChecked();
						var startTimeStr = getTimeStr(data.startTime);
						var endTimeStr = getTimeStr(data.endTime);
						var windowId = "showException";
						Ext.create('Ext.window.Window', {
							id : windowId,
							title : '下载页面',
							width : window.screen.availWidth * 0.5,
							height : window.screen.availHeight * 0.3,
							html : '<iframe src ="../statistic/export2csv.action?bean.startTime='+ startTimeStr
									+"&bean.endTime="+endTimeStr
									+"&bean.svcCode="+(data.svcCode==null?"":data.svcCode)
									+"&bean.type="+checkedArray[0].inputValue
									+ '" width="100%" height="100%" frameborder="no" border="0" framespacing="0" align="left"></iframe>'
						}).show();
					}
					else{
						Ext.MessageBox.alert("提示", "没有数据可导出！");
					}
				}
			},{
				xtype:'button',
				text:'打印',
				margin:'0 0 0 0',
				handler:function(){
			//	window.print();
				DP();
				}
			}
			],
			getColumns : function() {
				return [{
							hidden : true,
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
							header : '发生异常次数',
							name : 'excptnCount',
							dataIndex : 'excptnCount'
						}, {
							header : '成功率(%)',
							name : 'percents',
							dataIndex : 'percents',
							renderer : function(value) {
								return Ext.util.Format.number(value * 100,
										'0.00')
										+ '%';
							}
						}]
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
			items : [svcQueryPanel]
		});

Ext.onReady(function() {
			Ext.create('Ext.Viewport', {
						frame : true,
						layout : 'border',
						items : [Ext.create('queryForm', {
											id : 'queryForm',
											region : 'north',
											title : '查询条件'
										}), Ext.create('statisticGrid', {
											id : 'statisticGrid'

										}), Ext.create('Ext.panel.Panel', {
											frame : true,
											id:'chartPanel',
											region : 'south',
											height : '50%',
											layout : 'fit',
											title : '统计报表',
											collapsible : true,
											split : true,
											items : [pieChart]
										})]

					});
		});
function DP() {
	if (window.print) {
		var Div1 = document.all.statisticGrid.innerHTML;
		var Div2 = document.all.chartPanel.innerHTML;
		// *****************************************************
		// Div1、Div2即为你在打印的区域
		// 这里根据你要打印的哪些内容，从原显示页面中用
		// ＜div id=Div1＞Div1....＜/div＞＜div id=Div2＞Div2...＜/div＞
		// 等标示出来,要打印多少项目就标示多少
		// *****************************************************
		var css = '＜style type="text/css" media=all＞'
				+ 'p { line-height: 120%}'
				+ '.ftitle { line-height: 120%; font-size: 18px; color: #000000}'
				+ 'td { font-size: 10px; color: #000000}' + '＜/style＞';
		// *****************************************************
		// 定义打印用的CSS，具体你想打印出什么样的格式全看你自己
		// 了，但要注意：如果此处有什么同网页中不一致的，可能打印
		// 出来的页面同网页格式、字体可能会有所不同
		// *****************************************************

		var body = '＜table width="640" border="0" cellspacing="0" cellpadding="5"＞'
				+ ' ＜tr＞ '
				+ ' ＜td class="fbody"＞ '
				+ ' ＜div align="center" class=ftitle＞'
				+ Div1
				+ '＜/div＞'
				+ Div2
				+ ' ＜/td＞' + ' ＜/tr＞' + '＜/table＞';
		// ******************************************************
		// 在此处重新设置的打印格式，根据你的打印要求，将原显示的
		// 网页的DIV内容重新组合，可以根据你原来的表格内容，去掉
		// 不要打印的，你也可以能下面定义的noprint忽略掉你不想打
		// 印的东西，只调用你要打印的内容，但这样被忽略掉的地方将
		// 打印出空，不是很美观。表格宽度要同打印的纸张宽度匹配。
		// ******************************************************

		document.body.innerHTML = '＜center＞' + css + body + '＜/center＞';
		// ******************************************************
		// 重设document.body，打印文档准备就绪
		// ******************************************************

		window.print();
		window.history.go(0);
		// ******************************************************
		// 调用打印命令，打印当前窗口内容。当你打印时其实是一张新
		// 的网页了，但网页文件还是原先的。紧接着调用
		// window.history.go(0)，再回到打印前的页面，效果相当不差
		// ******************************************************
	}
}