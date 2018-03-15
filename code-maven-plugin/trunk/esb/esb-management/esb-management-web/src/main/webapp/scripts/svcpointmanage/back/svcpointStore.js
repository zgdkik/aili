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
					}, {
						'id' : 7,
						'name' : 'CC'
					},{
						'id' : 8,
						'name' : 'FOSS'
					}],
			autoLoad : true,
			filters:[Ext.create('Ext.util.Filter', {filterFn: function(item) { return item.get("name") !='ESB'; }, root: 'data'})]
			
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
//服务store
Ext.define('serviceStore', {
			extend : 'Ext.data.Store',
			autoLoad : true,
			model : 'SvcPointInfoModel',
			pageSize : 20, // 页大小
			proxy : {
				type : 'ajax',
				url : 'queryBackSvcpoints.action',
				reader : {
					type : 'json',
					root : 'svcPointInfolist',
					totalProperty : 'totalCount'// 总的数据条数
				}
			}
		});