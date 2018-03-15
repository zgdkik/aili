Ext.define('esbApp.store.FrameMenus', {
	extend : 'Ext.data.TreeStore',
	fields : [ 'text', 'url' ],
	root : {
		expanded : true,
		children : [ {
			text : '服务管理',
			url : '',
			expanded : false,
			children : [ {
				text : '后端服务管理',
				url : '/svcpoint/backIndex.action',
				leaf : true
			}, {
				text : 'ESB服务管理',
				url : '/svcpoint/esbIndex.action',
				leaf : true
			}, {
				text : 'ESB2服务管理',
				url : '/svcpoint2/index.action',
				leaf : true
			} , {
				text : 'ESB扩展管理',
				url : '/extend/index.action',
				leaf : true
			}  ]
		},{
			text : '审计日志',
			url : '',
			expanded : false,
			children : [ {
				text : '查询审计日志',
				url : '/audit/auditIndex.action',
				leaf : true
			}]
		}, {
			text : '监控管理',
			expanded : false,
			children : [ {
				text : '后端服务性能监控',
				url : '/monitor/backIndex.action',
				leaf : true
			}, {
				text : 'ESB服务性能监控',
				url : '/monitor/frontIndex.action',
				leaf : true
			}, {
				text : '队列监控',
				url : '/queue/index.action',
				leaf : true
			},{
				text : '后端异常监控设置',
				url : '/exceptionMonitor/backIndex.action',
				leaf : true
			},{
				text : 'ESB端异常监控设置',
				url : '/exceptionMonitor/esbIndex.action',
				leaf : true
			}, {
				text : '异常日志查询',
				url : '/exceptionlog/index.action',
				leaf : true
			}, {
				text : '异常日志查询2.0',
				url : '/exceptionlog2/index.action',
				leaf : true
			},{
				text : '失败日志查询',
				url : '/failure/failureIndex.action',
				leaf : true
			}, {
				text : '异常信息导出',
				url : '/exceptionlog/exportIndex.action',
				leaf : true
			}, {
				text : '综合查询',
				url : '/synthetical/syntheticalIndex.action',
				leaf : true
			} ]
		}, {
			text : '统计报表',
			expanded : false,
			children : [ {
				text : '接口综合统计',
				url : '/synthetical/ifaceSyntheticalAction.action',
				leaf : true
			},{
				text : '常用报表',
				url : '/statistic/statisticIndex.action',
				leaf : true
			},{
				text : '后端服务性能报表',
				url : '/statistic/backIndex.action',
				leaf : true
			}, {
				text : 'ESB服务性能报表',
				url : '/statistic/frontIndex.action',
				leaf : true
			}, {
				text : 'ESB服务异常报表',
				url : '/statistic/index.action',
				leaf : true
			} ]
		}, {
			text : '预警管理',
			expanded : false,
			children : [ {
				text : '预警人员管理',
				url : '/noticuser/index.action',
				leaf : true
			}, {
				text : '队列连接管理',
				url : '/mqConnect/index.action',
				leaf : true
			} ]
		}, {
			text : '系统管理',
			expanded : false,
			children : [ {
				text : '系统用户管理',
				url : '/sysuser/index.action',
				leaf : true
			}, {
				text : '更改密码',
				url : '/sysuser/modifyPwdIndex.action',
				leaf : true
			} ]
		}, {
			text : 'CAMEL',
			expanded : false,
			children : [ {
				text : 'CAMEL WATCH传送门',
				url : '/camel/index.action',
				leaf : true
			} ]
		}, {
			text : '资料连接验证',
			expanded : false,
			children : [ {
				text : '资料连接验证',
				url : '/verification/index.action',
				leaf : true
			}, {
				text : 'WS连接状态',
				url : '/verification/wsAddrSta/index.action',
				leaf : true
			} ]
		}, {
			text : '信息公告',
			expanded : false,
			children : [ {
				text : 'MQ通道信息',
				url : '/infobull/channelIndex.action',
				leaf : true
			},{
				text : 'MQ通道信息(状态)',
				url : '/infobull/channelIndex2.action',
				leaf : true
			}, {
				text : 'MQ队列信息',
				url : '/infobull/mqLocalQueueIndex.action',
				leaf : true
			}, {
				text : '路由信息',
				url : '/infobull/routeIndex.action',
				leaf : true
			} ]
		}, {
			text : '接口权限管理',
			expanded : false,
			children : [ {
				text : '落地配代理权限管理',
				url : '/expressAgent/agentIndex.action',
				leaf : true
			} ]
		} ]
	}
});