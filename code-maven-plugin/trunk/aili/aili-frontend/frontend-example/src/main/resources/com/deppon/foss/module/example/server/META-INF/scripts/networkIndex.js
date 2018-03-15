Ext.define('Dpap.network.NearNetworkModel', {
	extend : 'Ext.data.Model',
	fields : [{
			name : 'id'
		},{
			name: 'name'
		},{
			name: 'address'
		},{
			name: 'description'
		},{
			name: 'phone'
		},{
			name: 'tel'
		},{
			name: 'type'
		},{
			name: 'code'
		},{
			name: 'nearDistance'
		}],
	hasMany:[{model:'Dpap.network.CoordinateModel',name:'coordinate'}],
});
Ext.define('Dpap.network.CoordinateModel',{
	extend : 'Ext.data.Model',
	fields : [{
		name: 'longitude',type: 'float'
	},{
		name: 'latitude',type: 'float'
	}]
});
Ext.define('Dpap.network.NearNetworkStore',{
	extend : 'Ext.data.Store',
	model : 'Dpap.network.NearNetworkModel',
	autoLoad : true,
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : example.realPath('queryNetworksByCoordinate.action'),
		reader : {
			type : 'json',
			root : 'nearNetworkList'
		}
	}
});
Ext.define('Dpap.network.NearNetworkGridPanel',{
	extend : 'Ext.grid.Panel',
	title:'网点列表',
	frame: true,
	cls: 'autoHeight',
	bodyCls: 'autoHeight',
	closeAction: 'hide',
	enableColumnHide: false,
	sortableColumns: false,
	viewConfig: {
        enableTextSelection: true
    },
    initComponent: function() {
		var me = this;
		me.columns =  [{xtype: 'rownumberer', width: 35 ,text:'序号'},
		   { text: '网点代码', flex: 1, dataIndex: 'code' ,xtype :'ellipsiscolumn'},
		   { text: '网点名称', flex: 1, dataIndex: 'name' ,xtype :'ellipsiscolumn'},
		   { text: '网点地址', flex: 1.5, dataIndex: 'address',xtype :'ellipsiscolumn' },
		   { text: '网点类型', flex: 0.6, dataIndex: 'type' ,xtype :'ellipsiscolumn'},
		   { text: '网点电话', flex: 1, dataIndex: 'phone' ,xtype :'ellipsiscolumn'},
		   { text: '相隔网点距离(km)', flex: 1, dataIndex: 'nearDistance' ,xtype :'ellipsiscolumn'}
		   ];
		me.store = Ext.create('Dpap.network.NearNetworkStore',{
			autoLoad:false
		});
		me.bbar = me.getPagingToolbar();
		me.callParent(arguments);
    },
    mewindow : null,
	getMewindow : function(){
		if (Ext.isEmpty(this.mewindow)) {
			this.mewindow = Ext.create('Dpap.monitor.ExceptionTypeWindow');
		}
		return this.mewindow;
	},
    pagingToolbar : null,
	getPagingToolbar : function() {
		if (Ext.isEmpty(this.pagingToolbar)) {
			this.pagingToolbar = 
				Ext.create('Deppon.StandardPaging', {
					store : this.store,
					pageSize : 15
				});
		}
		return this.pagingToolbar;
	},
});
/**定义查询表单**/
Ext.define('Dpap.network.NetworkQueryForm', {
	extend: 'Ext.form.Panel',
	frame: true,
	title: '查询条件',
	collapsible: true,
	defaults : {
		margin : '8 10 5 10',
		labelAlign: 'left',
	    labelWidth: 150
	},
	layout: {
	    type: 'column'
	},
	initComponent: function() {
		var me = this;
		me.items = [{
			fieldLabel:'客户所在位置的经度',
			xtype: 'textfield',
			columnWidth : .45,
            name:'longitude',
            validator:function(value){
            	//定义正则表达式部分
                var reg = /^\d+(\.\d+)?$/;
                var re = value.match(reg);
                if(re)
                	return true;
                else
                	return "请输入数字！";
            }
		},{
			fieldLabel:'客户所在位置的维度',
			xtype: 'textfield',
			columnWidth : .45,
            name:'latitude',
            validator:function(value){
            	//定义正则表达式部分
                var reg = /^\d+(\.\d+)?$/;
                var re = value.match(reg);
                if(re)
                	return true;
                else
                	return "请输入数字！";
            }
		},{
			fieldLabel:'附近网点的最大距离(km)',
			xtype: 'textfield',
			columnWidth : .45,
            name:'maxDistance',
            validator:function(value){
            	//定义正则表达式部分
                var reg = /^\d+(\.\d+)?$/;
                var re = value.match(reg);
                if(re)
                	return true;
                else
                	return "请输入数字！";
            }
		},{
			xtype:'button',
    		cls : 'yellow_button',
    		text: '重置',//重置
    		width:80,
    		handler: me.onReset,
    		scope: me
		},{
			xtype:'button',
    		cls : 'yellow_button',
    		text: '查询',//查询
    		width:80,
    		handler: me.onQuery,
			scope: me
		}];
		me.callParent();
	},
	onQuery: function() {
		if(this.getForm().isValid()) {
			var form = this.getForm();
			var longitude = form.findField('longitude').getValue();
			var latitude = form.findField('latitude').getValue();
			var maxDistance = form.findField('maxDistance').getValue();
			Ext.getCmp("T_example-networkQueryIndex_content").getNearNetworkGridPanel().getStore().load({
				params:{
					'coordinate.longitude':longitude,
					'coordinate.latitude':latitude,
					'distance':maxDistance
				}
			});
		}
	},
	onReset: function() {
		this.getForm().reset();
	}
});
Ext.onReady(function() {
	Ext.tip.QuickTipManager.init();
	var queryForm = Ext.create('Dpap.network.NetworkQueryForm');
	var nearNetworkGridPanel = Ext.create('Dpap.network.NearNetworkGridPanel');
	var content = Ext.create('Ext.panel.Panel', {
		id:'T_example-networkQueryIndex_content',
		cls : 'panelContentNToolbar',
		bodyCls : 'panelContentNToolbar-body',
		layout: 'auto',
		getNearNetworkGridPanel:function(){
			return nearNetworkGridPanel;
		},
		items: [queryForm,nearNetworkGridPanel]
	});
	Ext.getCmp('T_example-networkQueryIndex').add(content);
});

