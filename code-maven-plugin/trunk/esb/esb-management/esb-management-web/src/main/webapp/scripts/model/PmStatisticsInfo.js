Ext.define('esbApp.model.SvcThresholdInfo',{
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
		 name: 'minRspTime',
		 type: 'int'
	 },{
		 name: 'maxRspTime',
		 type: 'int'
	 },{
		 name: 'avgRspTime',
		 type: 'int'
	 },{
		 name: 'startTime',
		 type: 'date'
	 },{
		 name: 'endTime',
		 type: 'date'
	 }]
});