//model队列连接信息
Ext.define('MqConnectInfoModel',{
	extend : 'Ext.data.Model',
	fields: [{
		name : 'id'
	},{
		name : 'ip'
	},{
		name : 'port'
	},{
		name : 'channel'
	},{
		name : 'queueNameReg'
	},{
		name : 'qmgr'
	}]
});

//连接信息的store
Ext.define('mqConnectStore',{
	extend : 'Ext.data.Store',
	autoLoad : true,
	model : 'MqConnectInfoModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : 'queryMqConnect.action',
		reader : {
			type : 'json',
			root : 'mqConnectInfoList',
			//总条数
			totalProperty: 'resultCount'
		}
	}
});

