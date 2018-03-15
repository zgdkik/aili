// queue Model
Ext.define('queueModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id'
					},{
						name : 'svcCode'
					},{
						name : 'qmgr'
					}, {
						name : 'queue'
					}, {
						name : 'threshold'
					}, {
						name : 'channelId'
					}, {
						name : 'personId'
					}, {
						name : 'clusters'
					},{
						name : 'personName'
					}, {
						name : 'comparetime'
					}, {
						name : 'volatility'
					}, {
						name : 'maxDepth'
					},{
						name : 'pjVersion'
					}]
		});

// queue store
/*Ext.define('queueStore', {
			extend : 'Ext.data.Store',
			model : 'queueModel',
			data : [{
						id : '1',
						svcCode : 'AddOrder',
						qmgr : 'ESB_QMGR',
						queue : 'CRM2ERP_ADDORDER',
						threshold : 500000,
						channel : 'Fetion',
						personId : '1,2,3,4',
						personList : 'personList',
						personName : '大王,小鬼'
					}],
					autoLoad : true
		});*/
Ext.define('queueStore', {
			extend : 'Ext.data.Store',
			autoLoad : true,
			model : 'queueModel',
		//	pageSize : 20, // 页大小
			proxy : {
				type : 'ajax',
				url : 'queryQueueMonitor.action',
				reader : {
					type : 'json',
					root : 'queueList',
					totalProperty : 'totalCount'// 总的数据条数
				}
			}
		});		

// 队列管理器model
Ext.define('qmgrModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id'
					}, {
						name : 'name'
					}]
		});
// 队列管理器store
//Ext.define('qmgrStore', {
//			extend : 'Ext.data.Store',
//			fields : ['id', 'name'],
//			data : [{
//						'id' : 1,
//						'name' : 'MQ1'
//					}, {
//						'id' : 2,
//						'name' : 'MQ2'
//					}],
//			autoLoad : true
//		});

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
					}],
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
			pageSize : 20, // 页大小
			proxy : {
				type : 'ajax',
				url : '../svcpoint/queryBackSvcpoints.action',
				reader : {
					type : 'json',
					root : 'svcPointInfolist',
					totalProperty : 'totalCount'// 总的数据条数
				}
			}
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
			proxy : {
				type : 'ajax',
				url : '../monitor/queryFullTrheshold.action',
				reader : {
					type : 'json',
					root : 'thresholdList'
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
					}, {
						name : 'pjVersion',
						type : 'string'
					}]
		});
// 预警人员store
Ext.define('LeftNoticUserInfoStore', {
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

/**
 * 隐藏详细地址信息Model
 */
Ext.define('RightNoticUserInfoStore', {
	extend : 'Ext.data.Store',
	model:'NoticUserInfoModel'
});

//版本号选择
Ext.define('pjVersionStore', {
	extend : 'Ext.data.Store',
	fields:['pjVersion'],
	data:[{'pjVersion':'ESB1'},{'pjVersion':'ESB2'}]
});

//是否集群
Ext.define('clustersStore',{
	extend : 'Ext.data.Store',
	fields : ['clusters'],
	data:[{'clusters':'false'},{'clusters':'true'}]
});
