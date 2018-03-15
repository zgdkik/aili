Ext.define('exceptionCodeStore', {
	extend : 'Ext.data.Store',
	fields : ['name'],
	data : [{
				'name' : 'com.deppon.esb.common.exception.ESBServerBusinessExeption'
			}, {
				'name' : 'com.deppon.esb.common.exception.ESBServerSystemExeption'
			}, {
				'name' : 'com.deppon.esb.common.exception.ESBInternalExeption'
			}, {
				'name' : 'com.deppon.esb.common.exception.ESBIllegalArgumentException'
			}],
	autoLoad : true
});

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