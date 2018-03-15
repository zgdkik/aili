Ext.define('esbApp.model.SvcPointInfo',{
	extend: 'Ext.data.Model',
	fields: [{
		name: 'id',
		type: 'string'
	},{
		name: 'svcName',
		type: 'string'
	},{
		name: 'svcCode',
		type: 'string'
	},{
		name: 'svcProvdId',
		type: 'string'
	},{
		name: 'svcAgrmt',
		type: 'string'
	},{
		name: 'svcAddr',
		type: 'string'
	},{
		name: 'frntOrBck',
		type: 'string'
	},{
		name: 'isAutoResend',
		type: 'boolean'
	},{
		name: 'isSaveOrgMsg',
		type: 'boolean'
	}]
});