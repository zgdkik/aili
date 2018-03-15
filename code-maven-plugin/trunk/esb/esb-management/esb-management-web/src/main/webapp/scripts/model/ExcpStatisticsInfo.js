Ext.define('esbApp.model.ExcpStatisticsInfo',{
	 extend: 'Ext.data.Model',
	 fields: [{
		 name: 'id',
		 type: 'string'
	 },{
		 name: 'svcCode',
		 type: 'string'
	 },{
		 name: 'calledCount',
		 type: 'int'
	 },{
		 name: 'excptnCount',
		 type: 'int'
	 },{
		 name: 'startTime',
		 type: 'date'
	 },{
		 name: 'endTime',
		 type: 'date'
	 }]
});