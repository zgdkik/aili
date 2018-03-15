Ext.define('esbApp.model.QuThresholdInfo',{
	 extend: 'Ext.data.Model',
	 fields: [{
		 name: 'id',
		 type: 'string'
	 },{
		 name: 'qmgr',
		 type: 'string'
	 },{
		 name: 'queue',
		 type: 'int'
	 },{
		 name: 'threshold',
		 type: 'string'
	 },{
		 name: 'channelId',
		 type: 'string'
	 },{
		 name: 'personId',
		 type: 'string'
	 },{
		 name: 'svcCode',
		 type: 'string'
	 }]
});