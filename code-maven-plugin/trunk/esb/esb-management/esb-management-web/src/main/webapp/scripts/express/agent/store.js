// 快递代理用户模型
Ext.define('ExpressAgentModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id'
					}, {
						name : 'agentName'
					}, {
						name : 'userName'
					}, {
						name : 'passwd'
					},  {
						name : 'status'
					},  {
						name : 'createTime'
					}]
		});
// 模拟代理用户store
/*Ext.define('ExpressAgentStore', {
			extend : 'Ext.data.Store',
			autoLoad : true,
			model : 'ExpressAgentModel',
			data : [{
						fid : '1',
						agentName:'腾达',
						userName : 'agent1',
						passwd : '****',
						status : 'Y',
						createTime : new Date()
					}, {
						fid : '2',
						agentName:'飞速快递',
						username : 'agent2',
						passwd : '****',
						status : 'N',
						createTime : new Date()
					}]
		});*/
// 配置编码store
Ext.define('ExpressAgentStore', {
			extend : 'Ext.data.Store',
			//autoLoad : true,
			model:'ExpressAgentModel',
			actionMethods : {
					read : 'POST'
				},
			proxy : {
				type : 'ajax',
				url : CONSTANTS_OPERATION_QUERY_URL,
				reader : {
					type : 'json',
					root : 'list'
				}
			}
		});