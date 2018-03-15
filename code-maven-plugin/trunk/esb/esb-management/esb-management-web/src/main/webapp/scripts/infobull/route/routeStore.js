//model
Ext.define('RouteInfoModel', {
			extend : 'Ext.data.Model',
			fields : [{
		 name: 'esbServiceCode',
	 },{
		 name: 'targetServiceCode',
	 }]
		});

//RouteInfostore
Ext.define('RouteInfoStore', {
	extend : 'Ext.data.Store',
	autoLoad : true,
	model : 'RouteInfoModel',
	pageSize : 20, // 页大小
    proxy: {
        type: 'ajax',
        url : 'queryAllRoute.action',
        reader: {
            type: 'json',
            root: 'routeInfoList',
            totalProperty : 'resultCount'// 总的数据条数
        }
    }
});