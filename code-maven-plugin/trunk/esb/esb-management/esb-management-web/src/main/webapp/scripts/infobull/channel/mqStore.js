//model
Ext.define('MqChannelBeanModel', {
			extend : 'Ext.data.Model',
			fields : [{
		 name: 'name',
	 },{
		 name: 'qmgrName',
	 },{
		 name: 'type',
	 },{
		 name: 'state',
	 },{
		 name: 'connectionName',
	 },{
		 name: 'startTime',
	 },{
		 name: 'lastGetMsgTime',
	 },{
		 name: 'msgs',
	 },{
		 name: 'sentBytes',
	 },{
		 name: 'receivedBytes',
	 },{
		 name: 'maxConversations',
	 },{
		 name: 'currentConversations',
	 }]
		});

//MqChannelBeanstore
Ext.define('MqChannelBeanStore', {
	extend : 'Ext.data.Store',
	autoLoad : true,
	model : 'MqChannelBeanModel',
	//pageSize : 20, // 页大小
    proxy: {
        type: 'ajax',
        url : 'listChannelStatus.action',
        reader: {
            type: 'json',
            root: 'resultChannelList',
            //totalProperty : 'resultCount'// 总的数据条数
        }
    }
});
//MqChannelTypestore
Ext.define('MqChannelTypeStore', {
	extend : 'Ext.data.Store',
	autoLoad : true,
	model : 'MqChannelBeanModel',
	//pageSize : 20, // 页大小
	proxy: {
		type: 'ajax',
		url : 'listAllChannelNamesAndTypes.action',
		reader: {
			type: 'json',
			root: 'resultChannelList',
			//totalProperty : 'resultCount'// 总的数据条数
		}
	}
});