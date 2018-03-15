//=======画界面开始======
Ext.define('SvcPoint2Model', {
			extend : 'Ext.data.Model',
			fields : ['id', 'name', 'code', 'sysid', 'agrmt', 'frontOrBack',
					  'appRequestAddr', 'appResponseAddr', 'expattern',
					'messageFormat','reqConvertorClass','resConvertorClass']
		});
// 服务配置store
Ext.define('Esb2.svcpoint.SvcPoint2Store', {
			extend : 'Ext.data.Store',
			model:'SvcPoint2Model',
			proxy : {
				type : 'ajax',
				url : '../extend/query.action',
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
//系统编码store
Ext.define("Esb2.svcpoint.SysIDStore",{
			extend : 'Ext.data.Store',
			fields:['value'],
			autoLoad :true,
/*			listeners:{
				//把后台传过来的数据进行解析并放到store中
				load:function(store,records,successful, operation, eOpts){
					var a =store.data.items;
					for (var i = 0; i < a.length; i++) {
						a[i].data.sysid=a[i].raw;
					}
				}
			},*/
			proxy : {
				type : 'ajax',
				url : '../extend/querySysIds.action',
/*				actionMethods : {
					read : 'POST'
				},*/
				reader : {
					type : 'json',
					root : 'sysIds'
				}
			}
});
Ext.define('Esb2.common.BaseModel',{
	extend : 'Ext.data.Model',
	fields:['name','value']
});
//前端、后端store
Ext.define("Esb2.svcpoint.FrontOrBackStore",{
			extend : 'Ext.data.Store',
			fields:['name','value'],
			data:[{'name':'前端','value':'F'},
				{'name':'后端','value':'B'}]
});

//接入协议store
Ext.define("Esb2.svcpoint.AgrmtStore",{
			extend : 'Ext.data.Store',
			autoLoad :true,
			fields:['value'],
			proxy : {
				type : 'ajax',
				url : '../extend/queryAgrmts.action',
				reader : {
					type : 'json',
					root : 'agrmts'
				}
			}
});
//交互模式Store
Ext.define("Esb2.svcpoint.ExpatternStore",{
			extend : 'Ext.data.Store',
			fields:['name','value'],
			data:[{'name':'请求响应','value':'1'},
				{'name':'请求回调','value':'2'},
				{'name':'单向','value':'3'}]
});