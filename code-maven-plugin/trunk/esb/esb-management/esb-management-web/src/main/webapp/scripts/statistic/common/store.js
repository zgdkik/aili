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
// 异常情况
Ext.define('store1', {
			extend : 'Ext.data.Store',
			fields : ['svcCode', 'exceptionCode', 'statisticDate', 'count'],
			data : [{
						'svcCode' : 'ESB2CRM_ADDORDER',
						'exceptionCode' : 'ESBServerBusinessExeption',
						'statisticDate' : '2012-08-09',
						'count' : '20'
					}, {
						'svcCode' : 'ESB2CRM_ADDORDER',
						'exceptionCode' : 'ESBServerBusinessExeption',
						'statisticDate' : '2012-08-09',
						'count' : '20'
					}, {
						'svcCode' : 'ESB2ERP_ADDRECGOODSBILL',
						'exceptionCode' : 'ESBServerBusinessExeption',
						'statisticDate' : '2012-08-09',
						'count' : '20'
					}, {
						'svcCode' : 'ESB2CRM_ADDORDER',
						'exceptionCode' : 'ESBServerBusinessExeption',
						'statisticDate' : '2012-08-09',
						'count' : '20'
					}, {
						'svcCode' : 'ESB2ERP_ADDRECGOODSBILL',
						'exceptionCode' : 'ESBServerBusinessExeption',
						'statisticDate' : '2012-08-09',
						'count' : '20'
					}],
			autoLoad : true
		});

// 最近两天接口调用统计
Ext.define('statisticModel', {
			extend : 'Ext.data.Model',
			fields : ['svcCode', 'statisticDate', 'totalMinCost',
					'totalMaxCost', 'totalAvgCost', 'totalCalls',
					'normalMaxCost', 'normalMinCost', 'normalAvgCost',
					'normalCalls', 'excpCalls']
		});
Ext.define('statisticStore', {
			extend : 'Ext.data.Store',
			model : 'statisticModel',
			pageSize : 20, // 页大小
			proxy : {
				type : 'ajax',
				url : '../statistic/getRecentlyStatistics.action',
				reader : {
					type : 'json',
					root : 'statisticList'
				}
			}
		});
function  format(value){
	if(value){
		var d = new Date(value);
		var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
		return str;
	}
}
Ext.define('baseModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'date',
						convert:function(value){
							return format(value);
						}
					}, {
						name : 'count'
					}, {
						name : 'increment'			
						}]
		});
// 接口调用统计
Ext.define('interfaceCountModel', {
			extend : 'baseModel'
		});
// 异常统计
Ext.define('exceptionCountModel', {
			extend : 'baseModel'
		});

// 接口调用次数
Ext.define('interfaceCountStore', {
			extend : 'Ext.data.Store',
			model : 'interfaceCountModel',
			// pageSize : 20, // 页大小
			autoLoad : true,
			proxy : {
				type : 'ajax',
				url : '../statistic/getInterfaceCallCount.action',
				reader : {
					type : 'json',
					root : 'callCountList'
				}
			}
		});

// 异常出现次数
Ext.define('exceptionCountStore', {
			extend : 'Ext.data.Store',
			model : 'exceptionCountModel',
			//pageSize : 20, // 页大小
			autoLoad : true,
			proxy : {
				type : 'ajax',
				url : '../statistic/getExceptionCount.action',
				reader : {
					type : 'json',
					root : 'exceptionCountList'
				}
			}
		});
interfaceCountStore = Ext.create('interfaceCountStore');
exceptionCountStore = Ext.create('exceptionCountStore');
