//model
Ext.define('WsAddressStatusModel', {
			extend : 'Ext.data.Model',
			fields : [{
			name:'serviceName'
			},{
		 name: 'serviceCode'
	 },{
		 name: 'serviceAddr'
	 },{
		 name: 'status'
	 } ]
		});

//WsAddressStatusStore
Ext.define('WsAddressStatusStore', {
	extend : 'Ext.data.Store',
	autoLoad : true,
	model : 'WsAddressStatusModel',
    proxy: { 
        type: 'ajax',
        url : 'queryWsAddressStatus.action',
        reader: {
            type: 'json',
            root: 'addrStatusList'
        }
    }
});