// 客户接入系统store
Ext.define('systemStore', {
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
			}],
	autoLoad : true,
	filters:[Ext.create('Ext.util.Filter', {filterFn: function(item) { return item.get("name") !='ESB'; }, root: 'data'})]
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
						name : 'responseType',// 返回类型
						type : 'string'
					}, {
						name : 'isAutoResend',// 是否自动重发
						type : 'bool'
					}, {
						name : 'isRcdOrgBody',// 性能日志是否保存原始消息
						type : 'bool'
					}, {
						name : 'isRcdPfmcLog',// 是否记录性能日志
						type : 'bool'
					}, {
						name : 'isRcdErrLog',// 是否记录异常日志
						type : 'bool'
					}, {
						name : 'promptlyNotify',// 出现异常是否需要立即通知
						type : 'bool'
					}]
		});
// 服务配置store
Ext.define('serviceStore', {
			extend : 'Ext.data.Store',
			model : 'SvcPointInfoModel',
			//autoLoad : true,
			pageSize : 20, // 页大小
			proxy : {
				type : 'ajax',
				url : '../statistic/getBackSvcPoints.action',
				reader : {
					type : 'json',
					root : 'svcPointList',
					totalProperty : 'resultCount'// 总的数据条数
				}
			}
		});

Ext.define('statisticModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'svcCode'
					}, {
						name : 'svcName'
					}, {
						name : 'statisticDate'
					}, {
						name : 'calledCount'
					}, {
						name : 'avgrspTime'
					}, {
						name : 'maxrspTime'
					}]
		});
//统计结果store
Ext.define('statisticStore', {
			extend : 'Ext.data.Store',
			model : 'statisticModel',
			autoload : true,
			proxy : {
				type : 'ajax',
				url : '../statistic/getBackStatistics.action',
				reader : {
					type : 'json',
					root : 'statisticList'
				}
			},
			sortOnLoad : true,
			sorters : [{
						sorterFn : function(o1, o2) {
							return	o1.get('statisticDate')>o2.get('statisticDate');
						}
					}]
		});
 
//成功失败饼图store
var pieStore = Ext.create('Ext.data.JsonStore', {
        fields: ['name', 'data'],
        //data: [{name:'success','data':100},{name:'fail','data':100}]
        data: [{name:'success','data':100},{name:'fail','data':0}]
    });	  