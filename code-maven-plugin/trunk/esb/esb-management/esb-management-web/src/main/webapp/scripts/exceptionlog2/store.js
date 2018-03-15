// exceptionModel
Ext.define('exceptionModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id'
					}, {
						name : 'version'
					}, {
						name : 'businessId'
					},  {
						name : 'businessDesc1'
					},  {
						name : 'businessDesc2'
					},  {
						name : 'businessDesc3'
					},  {
						name : 'requestId'
					},  {
						name : 'responseId'
					},  {
						name : 'esbServiceCode'
					},  {
						name : 'backServiceCode'
					},  {
						name : 'messageFormat'
					},  {
						name : 'exchangePattern'
					},  {
						name : 'message'
					}]
		});

Ext.define('exceptionLogStore2', {
			extend : 'Ext.data.Store',
			autoLoad : true,
			model : 'exceptionModel',
			pageSize : 18, // 页大小
			proxy : {
				type : 'ajax',
				url : '../exceptionlog2/queryExceptionBean.action',
				reader : {
					type : 'json',
					root : 'list',
					totalProperty : 'totalCount'// 总的数据条数
				}
			}
		});
// 异常类型store
Ext.define('exceptionTypeStore', {
	extend : 'Ext.data.Store',
	fields : ['name', 'id'],
	data : [{
				'name' : '后端业务异常',
				'id' : 'com.deppon.esb.common.exception.ESBServerBusinessExeption'
			}, {
				'name' : '后端系统异常',
				'id' : 'com.deppon.esb.common.exception.ESBServerSystemExeption'
			}, {
				'name' : 'ESB异常',
				'id' : '3'
			}]
});
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
// 配置编码store
Ext.define('codeStore', {
			extend : 'Ext.data.Store',
			autoLoad : true,
			model:'SvcPointInfoModel',
			proxy : {
				type : 'ajax',
				url : '../svcpoint/getAllSvcpoint.action',
				reader : {
					type : 'json',
					root : 'svcPointInfolist'
				}
			}
		});