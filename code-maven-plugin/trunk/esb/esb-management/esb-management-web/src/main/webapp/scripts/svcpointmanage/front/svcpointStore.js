//model
Ext.define('SvcPointInfoModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id',
						type : 'string'
					}, {
						name : 'svcName',//服务名称
						type : 'string'
					}, {
						name : 'svcCode',//服务 编码
						type : 'string'
					}, {
						name : 'svcProvdId',//服务提供方
						type : 'string'
					}, {
						name : 'svcAgrmt',//服务协议
						type : 'string'
					}, {
						name : 'svcAddr',//服务 接入地址
						type : 'string'
					}, {
						name : 'frntOrBck',//前端或后端
						type : 'string'
					}, {
						name : 'backSvcId',//前端或后端
						type : 'string'
					}, {
						name : 'backSvcName',//前端或后端
						type : 'string'
					},, {
						name : 'responseType',//返回类型
						type : 'string'
					}, {
						name : 'isAutoResend',//是否自动重发
						type : 'bool'
					}, {
						name : 'isRcdOrgBody',//性能日志是否保存原始消息
						type : 'bool'
					}, {
						name : 'isRcdPfmcLog',//是否记录性能日志
						type : 'bool'
					}, {
						name : 'isRcdErrLog',//是否记录异常日志
						type : 'bool'
					}, {
						name : 'promptlyNotify',//出现异常是否需要立即通知
						type : 'bool'
					}]
		});
// 服务提供方store
Ext.define('serviceProviderStore', {
			extend : 'Ext.data.Store',
			fields : ['id', 'name'],
			data : [{
						'id' : 1,
						'name' : 'ESB'
					}, {
						'id' : 2,
						'name' : 'OA'
					}, {
						'id' : 3,
						'name' : 'CRM'
					}, {
						'id' : 4,
						'name' : 'EBM'
					}, {
						'id' : 5,
						'name' : 'EHO'
					}, {
						'id' : 6,
						'name' : 'ERP'
					},{
						'id':7,
						'name':'CC'
					}],
			autoLoad : true/*,
			filters:[ Ext.create('Ext.util.Filter', {property: "name", value: 'ESB', root: 'data'})]*/
		});
// 服务协议store
Ext.define('serviceProtocolStore', {
			extend : 'Ext.data.Store',
			fields : ['id', 'name'],
			data : [{
						'id' : 1,
						'name' : 'WS'
					}, {
						'id' : 2,
						'name' : 'MQ'
					}],
			autoLoad : true
		});
//后端服务store
Ext.define('serviceStore', {
	extend : 'Ext.data.Store',
	autoLoad : false,
	model : 'SvcPointInfoModel',
	pageSize : 20, // 页大小
    proxy: {
        type: 'ajax',
        url : 'queryBackSvcpoints.action',
        reader: {
            type: 'json',
            root: 'svcPointInfolist',
            totalProperty : 'totalCount'// 总的数据条数
        }
    }
});

//前端服务store

Ext.define('frontSvcpointStore', {
	extend : 'Ext.data.Store',
	autoLoad : true,
	model : 'SvcPointInfoModel',
	pageSize : 20, // 页大小
    proxy: {
        type: 'ajax',
        url : 'queryFrontSvcpoints.action',
        reader: {
            type: 'json',
            root: 'svcpointBeanlist',
            totalProperty : 'totalCount'// 总的数据条数
        }
    }
});

//
//model
Ext.define('svcpointRelationModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id',
						type : 'string'
					}, {
						name : 'frontSvcCode',//服务 编码
						type : 'string'
					}, {
						name : 'backSvcCode',//服务提供方
						type : 'string'
					}]
		});
Ext.define('svcpointRelationStore', {
	extend : 'Ext.data.Store',
	//autoLoad : true,
	model : 'svcpointRelationModel',
	//pageSize : 20, // 页大小
    proxy: {
        type: 'ajax',
        url : '../svcpoint/queryRelation.action',
        reader: {
            type: 'json',
            root: 'relationList'
           // totalProperty : 'totalCount'// 总的数据条数
        }
    }
});