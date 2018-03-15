/**
 * svcpointstore
 */
// 服务配置store
/*Ext.define('Esb2.svcpoint.SvcPoint2Store', {
			extend : 'Ext.data.Store',
			model:'SvcPoint2Model',
			proxy : {
				type : 'ajax',
				url : '../svcpoint2/query.action',
				actionMethods : {
					read : 'POST'
				},
				reader : {
					type : 'json',
					root : 'list',
					totalProperty : 'resultCount'// 总的数据条数
				}
			}
		});*/
/**
 * statistic store
 */
Ext.define('Esb2.statistic.StatisticViewModel', {
			extend : 'Ext.data.Model',
			fields : ['fid','esbSvcCode','totalCount'
			,'successCount','exceptionCount','statisticDate','createTime']
		});
Ext.define('Esb2.statistic.StatisticViewStore', {
			extend : 'Ext.data.Store',
			model:'Esb2.statistic.StatisticViewModel',
			autoLoad:false,
			proxy : {
				type : 'ajax',
				url : '../statistic2/queryStatisticView.action',
				actionMethods : {
					read : 'POST'
				},
				reader : {
					type : 'json',
					root : 'list',
					totalProperty : 'resultCount'// 总的数据条数
				}
			}
		});