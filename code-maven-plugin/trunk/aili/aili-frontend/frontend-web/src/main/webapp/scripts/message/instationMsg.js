
//格式化日期字符串
message.instationMsg.FORMAT_DATE = 'Y-m-d'; 
//格式化时间字符串
message.instationMsg.FORMAT_TIME = 'Y-m-d H:i:s';
//Grid查询用URL
message.instationMsg.GridUrl='queryInstationMsg';
//外部链接用查询URL
message.instationMsg.AutoUrl='queryMsgByMsgType?msgVo.msgType='+login.msg.msgType;
//查询链接
message.instationMsg.QueryUrl=Ext.isEmpty(login.msg.msgType) ? message.instationMsg.GridUrl : message.instationMsg.AutoUrl; 

/**
  * 时间格式化
  *@param value 要格式化的时间
  *@format 格式化方式如  Y-m-d;Y-m-d H:i:s
  */

message.instationMsg.formatDefaultDate = function(isBegin,format) {
	var nowDate = new Date();
	if(isBegin) {
		nowDate.setHours(0);
		nowDate.setSeconds(0);
		nowDate.setMinutes(0);
	} else {
		nowDate.setHours(23);
		nowDate.setSeconds(59);
		nowDate.setMinutes(59);
	}
	return Ext.Date.format(nowDate,format);
};

/**
 * 标记信息读取状态
 */
message.instationMsg.markReadOperator=function(me){
	//获取选中的记录
	var rowObjs = me.getSelectionModel().getSelection();
	if(rowObjs.length>0){
		Ext.MessageBox.buttonText.yes = message.instationMsg.i18n('dpap.message.share.Ok');  
		Ext.MessageBox.buttonText.no = message.instationMsg.i18n('dpap.message.share.Cancel');  
		Ext.Msg.confirm(message.instationMsg.i18n('dpap.message.share.alertInfo'),message.instationMsg.i18n('dpap.message.instationMsg.confirmSelectRecord'), function(btn,text){
		if(btn == 'yes'){
			var ids=new Array();
			for(var i = 0; i< rowObjs.length; i++){
				var record=rowObjs[i].data;
				var isReaded=record.isReaded;
				var msgType=record.msgType;
				
				if( msgType =='ALLNET'){
					Ext.Msg.alert(message.instationMsg.i18n('dpap.message.share.alertInfo'),message.instationMsg.i18n('dpap.message.instationMsg.allNetNotRemark')); 
					return false;
				}
				
				if(isReaded!='N'){
					Ext.Msg.alert(message.instationMsg.i18n('dpap.message.share.alertInfo'),message.instationMsg.i18n('dpap.message.instationMsg.plsChoiceMsg')); 
					return false;
				}
				ids.push(record.id);
			} 
			if(ids.length>0){
				Ext.Ajax.request({
					url:message.realPath('updateMsgReadStatus'),
					params:{
						'msgVo.ids' : ids
					},
					success : function(response) { 
						var json = Ext.decode(response.responseText); 
						Ext.MessageBox.alert(message.instationMsg.i18n('dpap.message.share.alertInfo'), json.message);

						message.instationMsg.msgGrid.getPagingToolbar().moveFirst(); 
					},
					exception : function(response) {
						var json = Ext.decode(response.responseText);
						Ext.MessageBox.alert(message.instationMsg.i18n('dpap.message.share.alertInfo'), json.message);
					}
				});
			} 
		}
		});
	}else{
		Ext.Msg.alert(message.instationMsg.i18n('dpap.message.share.alertInfo'),message.instationMsg.i18n('dpap.message.instationMsg.plsChoiceRemarkRecord'));
		return false;
	}
}

/**
 * 双击标记信息读取状态
 */
message.instationMsg.dbMarkReadOperator=function(record){
	//获取选中的记录
	 var id=record.get('id');
	if(id){
		Ext.MessageBox.buttonText.yes = message.instationMsg.i18n('dpap.message.share.Ok');  
		Ext.MessageBox.buttonText.no = message.instationMsg.i18n('dpap.message.share.Cancel');  
		Ext.Msg.confirm(message.instationMsg.i18n('dpap.message.share.alertInfo'), message.instationMsg.i18n('dpap.message.instationMsg.confirmSelectRecord'), function(btn,text){
		if(btn == 'yes'){
			var ids=new Array();
				var isReaded=record.get('isReaded');
				var msgType=record.get('msgType');
				
				if( msgType =='ALLNET'){
					Ext.Msg.alert(message.instationMsg.i18n('dpap.message.share.alertInfo'),message.instationMsg.i18n('dpap.message.instationMsg.allNetNotRemark')); 
					return false;
				}
				
				if(isReaded!='N'){
					Ext.Msg.alert(message.instationMsg.i18n('dpap.message.share.alertInfo'),message.instationMsg.i18n('dpap.message.instationMsg.plsChoiceMsg')); 
					return false;
				}
				ids.push(id);
			if(ids.length>0){
				Ext.Ajax.request({
					url:message.realPath('updateMsgReadStatus'),
					params:{
						'msgVo.ids' : ids
					},
					success : function(response) { 
						var json = Ext.decode(response.responseText); 
						Ext.MessageBox.alert(message.instationMsg.i18n('dpap.message.share.alertInfo'), json.message);

						message.instationMsg.msgGrid.getPagingToolbar().moveFirst(); 
					},
					exception : function(response) {
						var json = Ext.decode(response.responseText);
						Ext.MessageBox.alert(message.instationMsg.i18n('dpap.message.share.alertInfo'), json.message);
					}
				});
			} 
		}
		});
	}else{
		Ext.Msg.alert(message.instationMsg.i18n('dpap.message.share.alertInfo'),message.instationMsg.i18n('dpap.message.instationMsg.plsChoiceRemarkRecord'));
		return false;
	}
}
/**
 * 站内消息查询
 */
message.instationMsg.search=function(){
	var form = message.instationMsg.msgForm.getForm();
	if(form.isValid()){
		var createStartTime=form.findField("msgVo.msgDto.createStartTime").getValue();
		var createEndTime=form.findField("msgVo.msgDto.createEndTime").getValue(); 
		
		if(Ext.isEmpty(createStartTime)){
			 Ext.MessageBox.alert(message.instationMsg.i18n('dpap.message.share.alertInfo'),message.instationMsg.i18n('dpap.message.instationMsg.plsChoiceMsgSendStartTime')); 
			 return false;
		}
		if(Ext.isEmpty(createEndTime)){
			 Ext.MessageBox.alert(message.instationMsg.i18n('dpap.message.share.alertInfo'),message.instationMsg.i18n('dpap.message.instationMsg.plsChoiceMsgSendEndTime')); 
			return false;
		}  
		var grid=message.instationMsg.msgGrid;
		//设置查询参数
		var params=form.getValues();
		grid.store.setSubmitParams(params);
		var msgType=login.msg.msgType;
		var queryStoreUrl=message.instationMsg.GridUrl;
		if(!Ext.isEmpty(msgType)){
			queryStoreUrl=message.instationMsg.AutoUrl
		}
		grid.store.proxy.url=message.realPath(queryStoreUrl);
		grid.store.loadPage(1,{
	      callback: function(records, operation, success) {
			//var result =   Ext.decode(operation.response.responseText);
			login.msg.msgType=null; 
	       }
	    });
	}else{
		Ext.Msg.alert(message.instationMsg.i18n('dpap.message.share.alertInfo'),message.instationMsg.i18n('dpap.message.instationMsg.plsCheckInputCondition'));
		return false;
	}
}
//站内消息Model
Ext.define('Dpap.messages.InstationMsgModel', {
			extend : 'Ext.data.Model', 
			fields : [ {
				name : 'id'
			}, {
				name : 'sendUserCode', //发送人员编码
				type : 'string'
			}, {
				name : 'sendUserName', //发送人员名称
				type : 'string'
			}, {
				name : 'sendOrgCode', //发送方组织编码
				type : 'string'
			}, {
				name : 'sendOrgName', //发送方组织名称
				type : 'string'
			}, {
				name : 'receiveUserCode', //接收人员编码
				type : 'string'
			}, {
				name : 'receiveUserName', //接收人员名称
				type : 'string'
			}, {
				name : 'context', //消息内容
				type : 'string'
			}, {
				name : 'msgType', //站内消息类型
				type : 'string'
			}, {
				name : 'active', //消息状态
				type : 'string'
			}, {
				name : 'isReaded', //是否已读
				type : 'string'
			}, {
				name : 'createTime', //创建时间
				type : 'date',
				convert:dateConvert
			}]
		});

//站内消息数据 
Ext.define('Dpap.messages.InstationMsgStore',{
	extend:'Ext.data.Store',
	model:'Dpap.messages.InstationMsgModel', 
	sorters: [{
        property: 'createTime',
        direction: 'DESC'
    }],
    submitParams:{},
    setSubmitParams:function(submitParams){
    	this.submitParams=submitParams;
    },
	pageSize:20,
	proxy : {
		type : 'ajax',
		actionMethods : 'post',
		url :message.realPath(message.instationMsg.QueryUrl),
		reader : {
			type : 'json',
			root : 'data',
			totalProperty : 'totalCount'
		}
	}, 
	autoLoad: true,
	constructor:function(config){
		var me = this, 
			cfg = Ext.apply({}, config);
		me.listeners = {
	   		'beforeload': function(store, operation, eOpts){
	   		 Ext.apply(me.submitParams, {
		          "limit":operation.limit,
		          "page":operation.page,
		          "start":operation.start
		          }); 
	   			Ext.apply(operation, {
	   				params : me.submitParams 
	   			});
	   		} 
		};
		me.callParent([ cfg ]);
	} 
}); 
 
//消息表格
Ext.define('Dpap.Messages.InstationMsgGrid',{
	extend: 'Ext.grid.Panel', 
	title: message.instationMsg.i18n('dpap.message.share.queryResult'),
	columnWidth: 1,
	stripeRows: true,
    columnLines: true,
	collapsible: false,
    bodyCls: 'autoHeight',
    frame: true,
    //增加表格列的分割线
	cls: 'autoHeight',
	store : Ext.create('Dpap.messages.InstationMsgStore'),
	autoScroll : true,
	height: 'autoHeight',
	emptyText: message.instationMsg.i18n('dpap.message.share.queryResultIsEmpty'),
	//分页
	pagingToolbar:null,
	viewConfig:{
	    	enableTextSelection : true//设置行可以选择，进而可以复制    
	},
	getPagingToolbar:function(){
		var me = this;
		if(Ext.isEmpty(me.pagingToolbar)){
			me.pagingToolbar = Ext.create('Deppon.StandardPaging',{
				store:me.store,
				pageSize:50,
				maximumSize:500,
				plugins:'pagesizeplugin'
			}); 
		}
       return me.pagingToolbar;
	},
	listeners: {
    	'itemdblclick': function(view, record, item, index, e, eOpts){
    		message.instationMsg.dbMarkReadOperator(record);
    	}
    },
	selModel : Ext.create('Ext.selection.CheckboxModel'),
	columns : [ 
	    { text: 'ID', dataIndex : 'id',hidden:true},
	    { text: message.instationMsg.i18n('dpap.message.instationMsg.sendUserCode'), dataIndex : 'sendUserCode',hidden:true},
	    { text: message.instationMsg.i18n('dpap.message.instationMsg.sendUserName'),  dataIndex : 'sendUserName' ,width:100},
	    { text: message.instationMsg.i18n('dpap.message.instationMsg.sendOrgCode'),  dataIndex : 'sendOrgCode' ,hidden:true},
	    { text: message.instationMsg.i18n('dpap.message.instationMsg.sendOrgName'),  dataIndex : 'sendOrgName' ,width:140},
		{ text: message.instationMsg.i18n('dpap.message.instationMsg.type'),  dataIndex : 'msgType' ,width:80
		,
			renderer:function(value){
				var displayField = Deppon.Dictionary.getDictNameByCode(value,'DPAP_MSG_TYPE');
				return displayField;
			}
	    },
		{ text: message.instationMsg.i18n('dpap.message.instationMsg.readStatus'),  dataIndex : 'isReaded' ,width:80
		,
			renderer:function(value){
				var displayField = Deppon.Dictionary.getDictNameByCode(value,'DPAP_MSG_NY');
				return displayField;
			}
	    }, 
		{ text: message.instationMsg.i18n('dpap.message.instationMsg.content'), 	xtype: 'linebreakcolumn', flex: 1, dataIndex: 'context',sortable:false},
		{ text: message.instationMsg.i18n('dpap.message.instationMsg.time'), dataIndex: 'createTime',width:140,
			renderer:function(value){
				if(value!=null){
					return Ext.Date.format(new Date(value), 'Y-m-d H:i:s');
				}else{
					return null;
				}
			}
		}
	], 
	constructor: function(config){
		var me = this, cfg = Ext.apply({}, config); 
		me.bbar = me.getPagingToolbar();
		me.getPagingToolbar().store = me.store; 
		me.dockedItems = [{
			xtype : 'toolbar',
			dock : 'top',
			layout : 'column',
			defaults : {
				margin : '0 0 5 3'
			},
			items : [{
				xtype : 'button',
				text : message.instationMsg.i18n('dpap.message.instationMsg.remarkReaded'),
				columnWidth : .1,
				handler : function() {
					message.instationMsg.markReadOperator(me);
				}
			}]
		}], 
		me.callParent([ cfg ]);
	} 
});

//验证日期
Ext.apply(Ext.form.VTypes, {  
         dateRange: function(val, field){  
             if(field.dateRange){  
                 var beginId = field.dateRange.begin;  
                 this.beginField = Ext.getCmp(beginId);  
                 var endId = field.dateRange.end;  
                 this.endField = Ext.getCmp(endId);  
                  beginDate = this.beginField.getValue();  
                 var endDate = this.endField.getValue();  
             }  
             if(beginDate <= endDate){  
                 return true;  
             }else{  
                 return false;  
             }  
         },  
         //验证失败信息  
         dateRangeText: message.instationMsg.i18n('dpap.message.share.validateDate')//'开始时间不可以大于结束时间'  
     }); 
     
//消息信息表单
Ext.define('Dpap.messages.InstationMsgForm',{
	extend:'Ext.form.Panel',
	title:message.instationMsg.i18n('dpap.message.share.queryCondition'),
	frame:true,
	collapsible:true,
	animcollapse:true,
	height:155,
	defaults:{
		margin : '5 10 5 10',
		labelWidth : 85
	},
	columnWidth:1,
	defaultType:'textfield',
	layout:'column',
	items:[
	   {
	   	id:'beginDate1',
	   	dateConfig: {
			el: 'beginDate1-inputEl',
			dateFmt: 'yyyy-MM-dd HH:mi:ss'
		},
		editable:'false',
		xtype:'datetimefield_date97',
		name:'msgVo.msgDto.createStartTime', 
		fieldLabel:message.instationMsg.i18n('dpap.message.instationMsg.msgSendTime'),
		format:'Y-m-d H:i:s',
		allowBlank:false,
		value:message.instationMsg.formatDefaultDate(true,message.instationMsg.FORMAT_TIME),
		dateRange: {begin: 'beginDate1', end: 'endDate1' },
		columnWidth:0.25,
        vtype: 'dateRange'  
	},{
		id:'endDate1',
		xtype:'datetimefield_date97',
		dateConfig: {
			el: 'endDate1-inputEl',
			dateFmt: 'yyyy-MM-dd HH:mi:ss'
		},
		editable:'false',
		name:'msgVo.msgDto.createEndTime',
		labelWidth:30,
	    labelSeparator:'',
		fieldLabel:message.instationMsg.i18n('dpap.message.instationMsg.arrive'),
		format:'Y-m-d H:i:s',
		allowBlank:false,
		value:message.instationMsg.formatDefaultDate(false,message.instationMsg.FORMAT_TIME) ,
		dateRange: {begin: 'beginDate1', end: 'endDate1' }, 
		columnWidth:0.25,
        vtype: 'dateRange'  
	},{
		xtype:'dictcombo',
	 	dictType: 'DPAP_MSG_TYPE',
	 	fieldLabel:message.instationMsg.i18n('dpap.message.instationMsg.msgType'),//'消息类型',
	 	name:'msgVo.msgDto.msgType',
	 	anyRecords:[{'valueCode':'ALL','valueName':'全部'}],
	 	columnWidth:0.25
	 	
	},{
		xtype:'radiogroup',
		fieldLabel:message.instationMsg.i18n('dpap.message.instationMsg.isNotRead'), 
		layout:'table',
		columnWidth:0.25,
		isFormField: true,
		defaults:{
			margin:'5 0 0 0',
			width:50
		},
		items:[{
			boxLabel:message.instationMsg.i18n('dpap.message.instationMsg.readed'),
			name:'msgVo.msgDto.isReaded',
			inputValue:'R'
		},{
			boxLabel:message.instationMsg.i18n('dpap.message.instationMsg.unread'),
			name:'msgVo.msgDto.isReaded',
			inputValue:'N',
			checked:true
		}]
		}],
		initComponent :function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.buttons = [{
				  xtype : 'button',
				  text:message.instationMsg.i18n('dpap.message.share.reset'),
				  columnWidth:.12,
				  handler:function(){
					 this.up('form').getForm().reset();
				  }
			},'->',{
				  xtype : 'button',
				  text:message.instationMsg.i18n('dpap.message.share.query'),
				  columnWidth:.12,
				  cls:'yellow_button',  
				  handler:function(){ 
					    login.msg.msgType=null;  
						message.instationMsg.search();
				  }
			}];
			me.callParent([cfg]);
		
		}
});

Ext.onReady(function() {
	Ext.QuickTips.init();
	if (Ext.getCmp('T_message-instationMsgInit_content')) {
		return;
	} 
	message.instationMsg.msgForm = Ext.create('Dpap.messages.InstationMsgForm');//查询FORM
	message.instationMsg.msgGrid = Ext.create('Dpap.Messages.InstationMsgGrid');//查询结果GRID
 
	
	Ext.getCmp('T_message-instationMsgInit').add(Ext.create('Ext.panel.Panel', {
		id : 'T_message-instationMsgInitIndex_content',
		cls:"panelContentNToolbar", //必须添加，内容定位用。
		bodyCls:'panelContentNToolbar-body', //必须添加，内容定位用。
		//获得查询FORM
		getQueryForm : function() {
			return message.instationMsg.msgForm; 
		},
		//获得查询结果GRID
		getMsgGridGrid : function() {
			return message.instationMsg.msgGrid;
		},
		items: [ message.instationMsg.msgForm,message.instationMsg.msgGrid]
	   }));

	
});

