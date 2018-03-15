var interfaceCountChart = Ext.create('Ext.chart.Chart', {
			store : interfaceCountStore,
			axes : [{
						type : 'Category',
						position : 'bottom',
						fields : ['date'],
						title : '日期'
					}, {
						type : 'Category',
						position : 'left',
						title : '调用次数',
						fields : ['count']
					}],
			series : [{
						type : 'line',
						axis : 'left',
						xField : 'date',
						yField : 'count',
						grid:true
					}]
		});
		
var exceptionCountChart = Ext.create('Ext.chart.Chart', {
			store : exceptionCountStore,
			axes : [{
						type : 'Category',
						position : 'bottom',
						fields : ['date'],
						title : '日期'
					}, {
						type : 'Category',
						position : 'left',
						title : '异常次数',
						fields : ['count']
					}],
			series : [{
						type : 'line',
						axis : 'left',
						xField : 'date',
						yField : 'count',
						grid:true
					},{
						type : 'bar',
						axis : 'left',
						xField : 'date',
						yField : 'count',
						grid:true
					}]
		});