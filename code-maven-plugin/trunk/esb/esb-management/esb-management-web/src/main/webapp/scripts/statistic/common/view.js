Ext.onReady(function() {
	Ext.create('Ext.Viewport', {
				frame : true,
				layout : 'border',
				items : [Ext.create('Ext.grid.Panel', {
									collapsible : true,// 可收缩
									split : true,
									region : 'north',
									frame : true,
									height : '20%',
									title : '最近两天服务调用情况',
									store : Ext.create('statisticStore'),
									columns : [{
												header : '服务编码',
												name : 'svcCode',
												dataIndex : 'svcCode'
											}, {
												header : '统计日期',
												name : 'statisticDate',
												dataIndex : 'statisticDate'
											}, {
												header : '总调用最小耗时',
												name : 'totalMinCost',
												dataIndex : 'totalMinCost'
											}, {
												header : '总调用最大耗时',
												name : 'totalMaxCost',
												dataIndex : 'totalMaxCost'
											}, {
												header : '总调用平均耗时',
												name : 'totalAvgCost',
												dataIndex : 'totalAvgCost'
											}, {
												header : '总调用次数',
												name : 'totalCalls',
												dataIndex : 'totalCalls'
											}, {
												header : '正常调用最大耗时',
												name : 'normalMaxCost',
												dataIndex : 'normalMaxCost'
											}, {
												header : '正常调用最小耗时',
												name : 'normalMinCost',
												dataIndex : 'normalMinCost'
											}, {
												header : '正常调用平均耗时',
												name : 'normalAvgCost',
												dataIndex : 'normalAvgCost'
											}, {
												header : '正常调用次数',
												name : 'normalCalls',
												dataIndex : 'normalCalls'
											}, {
												header : '异常调用次数',
												name : 'excpCalls',
												dataIndex : 'excpCalls'
											}]
								}), Ext.create('Ext.panel.Panel', {
									tbar:[
									{text:'开始时间'},{text:'结束时间'},{text:'查询'}
									],
									region : 'center',
									title : '接口统计',
									collapsible : true,
									split : true,
									layout : 'fit',
									items : [interfaceCountChart]
								}),Ext.create('Ext.panel.Panel', {
									region : 'south',
									title : '异常统计',
									collapsible : true,
									split : true,
									layout : 'fit',
									height : '40%',
									items : [exceptionCountChart]
								})/*
									 Ext.create('Ext.grid.Panel', { region :
									 'center', collapsible : true, // split :
									 true, store : interfaceCountStore, height :
									 '20%', minHeight : 200, defaultType :
									 'text', title : '接口调用统计', columns : [{
									 header : '时间', name : 'date', dataIndex :
									 'date', renderer : function(value) { var
									 date = new Date(value); return
									 Ext.Date.format( date, 'Y-m-d'); } }, {
									 header : '接口调用次数', name : 'count',
									 dataIndex : 'count' }, { header : '增量',
									 name : 'increment', dataIndex :
									 'increment' }] })
									, Ext.create('Ext.grid.Panel', {
									region : 'south',
									collapsible : true,
									split : true,
									store : exceptionCountStore,
									height : '35%',
									minHeight : 200,
									defaultType : 'text',
									title : '异常统计',
									columns : [{
										header : '时间',
										name : 'date',
										dataIndex : 'date',
										renderer : function(value) {
											var date = new Date(value);
											return Ext.Date.format(date,
													'Y-m-d');
										}
									}, {
										header : '接口调用次数',
										name : 'count',
										dataIndex : 'count'
									}, {
										header : '增量',
										name : 'increment',
										dataIndex : 'increment'
									}]
								})*/
								]

			});
});
