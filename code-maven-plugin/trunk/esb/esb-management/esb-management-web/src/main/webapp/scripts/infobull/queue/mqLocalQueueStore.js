//model
Ext.define('MqLocalQueueBeanModel', {
			extend : 'Ext.data.Model',
			fields : [{
		 name: 'name',
	 },{
		 name: 'qmgrName',
	 },{
		 name: 'inputCount',
	 },{
		 name: 'outputCount',
	 },{
		 name: 'maxDepth',
	 },{
		 name: 'currentDepth',
	 },{
		 name: 'allowPut',
	 },{
		 name: 'allowGet',
	 },{
		 name: 'queueCreateTime',
	 }]
		});

//MqChannelBeanstore
Ext.define('MqLocalQueueBeanStore', {
	extend : 'Ext.data.Store',
	autoLoad : true,
	model : 'MqLocalQueueBeanModel',
	//pageSize : 20, // 页大小
    proxy: {
        type: 'ajax',
        url : 'listLocalQueues.action',
        reader: {
            type: 'json',
            root: 'resultLocalQueueList',
            //totalProperty : 'resultCount'// 总的数据条数
        }
    }
});