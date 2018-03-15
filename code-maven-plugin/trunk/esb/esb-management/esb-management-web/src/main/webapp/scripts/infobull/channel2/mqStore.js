//model
Ext.define('MqChannelBeanModel2', {
			extend : 'Ext.data.Model',
			fields : [{
		 name: 'name',
	 },{
		 name: 'qmgrName',
	 },{
		 name: 'type',
	 },{
		 name: 'state',
	 }]
		});

//MqChannelBeanstore
Ext.define('MqChannelBeanStore2', {
	extend : 'Ext.data.Store',
	autoLoad : true,
	model : 'MqChannelBeanModel2',
	//pageSize : 20, // 页大小
    proxy: {
        type: 'ajax',
        url : 'listChannelStatus2.action',
        reader: {
            type: 'json',
            root: 'resultChannelList',
            //totalProperty : 'resultCount'// 总的数据条数
        }
    }
});
