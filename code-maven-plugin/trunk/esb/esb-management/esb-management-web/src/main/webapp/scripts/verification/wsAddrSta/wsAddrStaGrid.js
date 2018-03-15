/*
 * 了解更多，请查看extjsapi文档：Windows->Messagebox
*/
Ext.ux.Toast = function(){
    var msgCt;

    function createBox(t, s){
       return '<div class="msg"><h3>' + t + '</h3><p>' + s + '</p></div>';
    }
    return {
        msg : function(title, format){
            if(!msgCt){
                msgCt = Ext.DomHelper.insertFirst(document.body, {id:'msg-div'}, true);
            }
            var s = Ext.String.format.apply(String, Array.prototype.slice.call(arguments, 1));
            var m = Ext.DomHelper.append(msgCt, createBox(title, s), true);
            m.hide();
            m.slideIn('t').ghost("t", { delay: 1000, remove: true});
        },

        init : function(){ }
    };
}();
// wsAddressStatusGrid
Ext.define('wsAddressStatusGrid', {
	extend:'Ext.grid.Panel',
	//title : '自定义本地队列状态列表',
	frame : true,
	region : 'center',
	//stripeRows : true, // 交替行效果
	autoScroll : true, // 自动添加滚动条
/*	store : Ext.create('WsAddressStatusStore', {
		'id' : 'wsAddressStatusStoreId'
	}),*/
	columns : [{
		//id : 'serviceCode',
		name : 'serviceName',
		text : '服务名字',
		width:150,
		dataIndex : 'serviceName'
	}, {
		//id : 'serviceCode',
		name : 'serviceCode',
		text : '服务编码',
		width:300,
		dataIndex : 'serviceCode'
	}, {
		//id : 'serviceAddr',
		name : 'serviceAddr',
		text : '服务地址',
		width:300,
		dataIndex : 'serviceAddr'
	}, {
		//id : 'status',
		name : 'status',
		text : '状态',
		width:150,
		dataIndex : 'status'
	}, {
				header : '点击查看状态',
				width : 100,
				dataIndex : '',
				renderer : function(value, celldata, record) {
					var id = record.index;
					return " <button type='button' id='save' onclick='getAddressStatus(\""
							+ id + "\");'>查看状态 </button>";
				}
			}],
	// 顶部工具栏
	tbar : [ {
		xtype : 'button',
		text : '查询WS配置',
		handler : function() {
			Ext.data.StoreManager.lookup('wsAddressStatusStoreId').load();
		}
	}, {
		xtype : 'button',
		text : '一键查询状态',
		handler : function() {
			processAllAddressStatus();
		}
	}, {
		xtype : 'button',
		text : '定时刷新状态',
		enableToggle: true,
		toggleHandler:function(button,state){
			if(state){
				var refreshTime = Ext.getCmp('refreshTime').getValue();
				if(!refreshTime){
					refreshTime=5;
				}
				refreshTime=refreshTime*1000*60;
				 Interval_ID = window.setInterval(processAllAddressStatus,refreshTime);
				 var refreshTime = Ext.getCmp('refreshTime').getValue();
				 Ext.ux.Toast.msg('提示', '设定定时刷新状态，刷新时间：'+refreshTime+'分钟', 'ab');
			}else{
				if(Interval_ID){
					window.clearInterval(Interval_ID);
				Ext.ux.Toast.msg('提示', '取消定时刷新状态', 'ab');
					Interval_ID=-1;
				}
			}
			
		}
	},{
            xtype: 'numberfield',
            name: 'refreshTime',
            id:'refreshTime',
            fieldLabel: '刷新时间(min)',
            //width:100,
            anchor: '50%',
            value: 5,
            editable:false,
            minValue: 5,
            maxValue: 20
        }]
});
//全局变量，刷新WS状态定时器ID
var Interval_ID=-1;
function processAllAddressStatus(){
	var store = Ext.data.StoreManager.lookup('wsAddressStatusStoreId');
	store.each(getAddressStatusByRecord);
}

function getAddressStatus(index) {
	var store = Ext.data.StoreManager.lookup('wsAddressStatusStoreId');
	var record = store.getAt(index);
	getAddressStatusByRecord(record);
}

function getAddressStatusByRecord(record){
		Ext.Ajax.request({
				url : '../wsAddrSta/querySingleWsAddressStatus.action',
				timeout : 60000,
				params : {
					url : record.data.serviceAddr
				},
				success : function(response) {
					var text = response.responseText;
					var status = Ext.JSON.decode(text).urlStatus;
					record.data.status=status;
					record.commit();
				}
			});
}