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
	autoLoad : true
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
			autoLoad : true,
			pageSize : 20, // 页大小
			proxy : {
				type : 'ajax',
				url : '../statistic/getSvcPoints.action',
				reader : {
					type : 'json',
					root : 'svcPointList',
					totalProperty : 'resultCount'// 总的数据条数
				}
			}
		});

// statistic Model
/*
 * Ext.define('statisticModel', { extend : 'Ext.data.Model', fields : [ 'id',
 * 'svcCode', 'svcName', 'statisticDate', 'calledCount', 'excptnCount',
 * 'percents' ] });
 * 
 * 
 * Ext.define('statisticStore', { model : 'statisticModel', autoLoad : true,
 * proxy : { type : 'ajax', url : '../statistic/getStatistics.action', reader : {
 * type : 'json', root : 'statisticList' } } });
 */

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
						name : 'excptnCount'
					}, {
						name : 'percents'
					}]
		});
//统计结果store
Ext.define('statisticStore', {
			extend : 'Ext.data.Store',
			model : 'statisticModel',
			autoload : true,
			proxy : {
				type : 'ajax',
				url : '../statistic/getStatistics.action',
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
 

//异常饼图统计（按服务编码分组统计）
Ext.define('pieStore', {
			extend : 'Ext.data.Store',
			fields:['svcCode','exceptionCount','totalCount','svcName'],
			autoload : true,
			proxy : {
				type : 'ajax',
				url : '../statistic/getExceptionPieStatistics.action',
				reader : {
					type : 'json',
					root : 'pieList'
				}
			}
		});
var pieStore = Ext.create('pieStore',{
			autoload : true,
			listeners : {
				beforeload : function(store, operation, e) {
					if (Ext.getCmp('queryForm')) {
						var form = Ext.getCmp('queryForm').getForm();
						var data = Ext.getCmp('queryForm').getForm()
								.getFieldValues();
						var checkedArray = form.findField('reportType')
								.getChecked();
						Ext.apply(operation, {
									params : {
										'bean.startTime' : data.startTime,
										'bean.endTime' : data.endTime,
										'bean.svcCode' : data.svcCode,
										'bean.svcprovdid':data.svcProvdId,
										'bean.exceptionType':data.exceptionType
									}
								});
					}
				}
			}
});
//pieStore.load();
var statisticStore = Ext.create('statisticStore', {
			id : 'statisticStore',
			autoload : true,
			listeners : {
				beforeload : function(store, operation, e) {
					if (Ext.getCmp('queryForm')) {
						var form = Ext.getCmp('queryForm').getForm();
						var data = Ext.getCmp('queryForm').getForm()
								.getFieldValues();
						var checkedArray = form.findField('reportType')
								.getChecked();
						Ext.apply(operation, {
									params : {
										'bean.startTime' : data.startTime,
										'bean.endTime' : data.endTime,
										'bean.svcCode' : data.svcCode,
										'bean.type' : checkedArray[0].inputValue,
										'bean.svcprovdid':data.svcProvdId,
										'bean.exceptionType':data.exceptionType
									}
								});
					}
				}
			}
		});