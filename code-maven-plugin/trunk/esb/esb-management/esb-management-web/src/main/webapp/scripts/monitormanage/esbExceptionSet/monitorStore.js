// model
Ext.define('SvcPointInfoModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id',
						type : 'string'
					}, {
						name : 'svcName',// 服务名称
						type : 'string'
					}, {
						name : 'svcCode',// 服务 编码
						type : 'string'
					}, {
						name : 'svcProvdId',// 服务提供方
						type : 'string'
					}, {
						name : 'svcAgrmt',// 服务协议
						type : 'string'
					}, {
						name : 'svcAddr',// 服务 接入地址
						type : 'string'
					}, {
						name : 'frntOrBck',// 前端或后端
						type : 'string'
					}, {
						name : 'isAutoResend',// 是否自动重发
						type : 'bool'
					}, {
						name : 'isSaveOrgMsg',// 日志信息是否保存原始消息
						type : 'bool'
					}, {
						name : 'isRcdPfmcLog',// 日志信息是否保存原始消息
						type : 'bool'
					}, {
						name : 'isRcdErrLog',// 日志信息是否保存原始消息
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
					}/*, {
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
					}*/],
			autoLoad : true
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
// 服务store
Ext.define('serviceStore', {
			extend : 'Ext.data.Store',
			autoLoad : true,
			model : 'SvcPointInfoModel',
			//pageSize : 20, // 页大小
			proxy : {
				type : 'ajax',
				url : '../exceptionMonitor/queryEsbSvcpoint.action',
				reader : {
					type : 'json',
					root : 'svcPointInfolist',
					totalProperty : 'totalCount'// 总的数据条数
				}
			}
		});

// 预警人员
/*
 * Ext.define('noticeUserModel', { extend : 'Ext.data.Model', field : [{ name :
 * fid }, { name : userName }, { name : telPhone }, { name : mobilePhone }, {
 * name : email }] });
 */
Ext.define('noticeUserStore', {
			extend : 'Ext.data.Store',
			fields : ['id', 'userName'],
			data : [{
						'id' : 1,
						'userName' : '小王'
					}, {
						'id' : 2,
						'userName' : '小刘'
					}, {
						'id' : 3,
						'userName' : '小张'
					}, {
						'id' : 4,
						'userName' : '小李'
					}]
		});
// 预警渠道
Ext.define('warningChannelStore', {
			extend : 'Ext.data.Store',
			fields : ['id', 'channelName'],
			data : [{
						'id' : 1,
						'channelName' : 'Fetion'
					}, {
						'id' : 2,
						'channelName' : 'msg'
					}, {
						'id' : 3,
						'channelName' : 'Email'
					}]
		});
// fullThresholdInfo
Ext.define('fullThresholodModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id'
					}, {
						name : 'svcCode'
					}, {
						name : 'svcName'
					}, {
						name : 'svcProvdId'
					}, {
						name : 'threshold'
					}, {
						name : 'channel'
					}, {
						name : 'list'
					}, {
						name : 'personId'
					}, {
						name : 'personName'
					}]
		});
// 后端性能配置
Ext.define('fullThresholdStore', {
			extend : 'Ext.data.Store',
			model : 'fullThresholodModel',
			autoLoad : true,
			pageSize:10,
			proxy : {
				type : 'ajax',
				url : '../exceptionMonitor/queryFullTrheshold.action',
				reader : {
					type : 'json',
					root : 'thresholdList',
					totalProperty : 'totalCount'// 总的数据条数
				}
			}
		});
// 预警人员model
Ext.define('NoticUserInfoModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id',
						type : 'string'
					}, {
						name : 'userName',
						type : 'string'
					}, {
						name : 'telPhone',
						type : 'string'
					}, {
						name : 'mobilePhone',
						type : 'string'
					}, {
						name : 'email',
						type : 'string'
					}]
		});
// 预警人员store
Ext.define('noticUserStore', {
			extend : 'Ext.data.Store',
			autoLoad : true,
			model : 'NoticUserInfoModel',
			pageSize : 20, // 页大小
			proxy : {
				type : 'ajax',
				url : '../noticuser/queryNoticUsers.action',
				reader : {
					type : 'json',
					root : 'noticUserInfoList',
					totalProperty : 'resultCount'// 总的数据条数
				}
			}
		});