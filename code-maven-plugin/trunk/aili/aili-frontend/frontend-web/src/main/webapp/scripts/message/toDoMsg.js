

message.toDoMsgIndex.FORMAT_DATE = 'Y-m-d'; //格式化日期字符串
message.toDoMsgIndex.FORMAT_TIME = 'Y-m-d H:i:s'; //格式化时间字符串
message.toDoMsgIndex.STATUS_PROCESSING= 'G'; //未处理
message.toDoMsgIndex.STATUS_PROCESSED= 'D'; //已处理

//Grid查询用URL
message.toDoMsgIndex.GridUrl='queryToDoMsg';
//外部链接用查询URL
message.toDoMsgIndex.AutoUrl='queryToDoMsgByBisType?msgVo.bisType='+login.msg.bisType;
//查询链接
message.toDoMsgIndex.QueryUrl=Ext.isEmpty(login.msg.bisType) ? message.toDoMsgIndex.GridUrl : message.toDoMsgIndex.AutoUrl; 
/**
  * 时间格式化
  *@param value 要格式化的时间
  *@format 格式化方式如  Y-m-d;Y-m-d H:i:s
  */
message.toDoMsgIndex.formatDefaultDate = function(isBegin,format) {
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
 * 待办处理
 * @param grid
 * @param rowIndex
 * @param colIndex
 */
message.toDoMsgIndex.toDodeal=function(grid, rowIndex, colIndex){
	var record=grid.getStore().getAt(rowIndex);
	var status=record.get('status');
	if(status==message.toDoMsgIndex.STATUS_PROCESSED){
		Ext.MessageBox.alert(message.toDoMsgIndex.i18n('dpap.message.share.alertInfo'), message.toDoMsgIndex.i18n('dpap.message.toDoMsg.plsChoiceNoDealMsg')); 
	}else{
		var dealUrls=record.get('dealUrl').split('|');
		var pageIndex=dealUrls[0];
		var menuName=dealUrls[1];
		var url=dealUrls[2];
		var param='?serialNumber='+record.get('serialNumber');
		openDealMenu(pageIndex,menuName,url); 
	}
}

/**
 * 待办查询
 */
message.toDoMsgIndex.search=function(){
	var form = message.toDoMsgIndex.msgForm.getForm();
	if(form.isValid()){
		var createStartTime=form.findField("msgVo.toDoDto.createStartTime").getValue();
		var createEndTime=form.findField("msgVo.toDoDto.createEndTime").getValue(); 
		
		if(Ext.isEmpty(createStartTime)){
			 Ext.MessageBox.alert(message.toDoMsgIndex.i18n('dpap.message.share.alertInfo'), common.toDoMsg.i18n('dpap.message.toDoMsg.plsChoiceTodoStartDate')); 
			 return false;
		}
		if(Ext.isEmpty(createEndTime)){
			 Ext.MessageBox.alert(message.toDoMsgIndex.i18n('dpap.message.share.alertInfo'), common.toDoMsg.i18n('dpap.message.toDoMsg.plsChoiceTodoEndDate')); 
			return false;
		}  
		var grid=message.toDoMsgIndex.msgGrid;
		//设置查询参数
		var params=form.getValues();
		grid.store.setSubmitParams(params);
		var bisType=login.msg.bisType;
		var queryStoreUrl=message.toDoMsgIndex.GridUrl;
		if(!Ext.isEmpty(bisType)){
			queryStoreUrl=message.toDoMsgIndex.AutoUrl
		}
		grid.store.proxy.url=message.realPath(queryStoreUrl);
		grid.store.loadPage(1,{
	      callback: function(records, operation, success) { 
			login.msg.bisType=null; 
	       }
	    });
	}else{
		Ext.Msg.alert(message.toDoMsgIndex.i18n('dpap.message.share.alertInfo'), message.toDoMsgIndex.i18n('dpap.message.instationMsg.plsCheckInputCondition'));
		return false;
	}
}
//弹出新窗口
function openDealMenu(id,title,url){ 
	addTab(id,title,url);
}
//待办事项 Model
Ext.define('Dpap.messages.toDoMsgModel', {
			extend : 'Ext.data.Model', 
			fields : [ {
				name : 'id'
			}, {
				name : 'title', //待办标题
				type : 'string'
			}, {
				name : 'receiveOrgCode', //接收方组织编码
				type : 'string'
			}, {
				name : 'receiveSubOrgCode', //接收方下级组织编码
				type : 'string'
			}, {
				name : 'receiveSubOrgName', //接收方下级组织名称
				type : 'string'
			}, {
				name : 'receiveRoleCode', // 接收方角色编码
				type : 'string'
			}, {
				name : 'businessType', //业务类型
				type : 'string'
			}, {
				name : 'businessNo', //业务单号
				type : 'string'
			}, {
				name : 'dealUrl', //处理的址
				type : 'string'
			}, {
				name : 'serialNumber', //待办流水号
				type : 'string'
			}, {
				name : 'status', //待办状态
				type : 'string'
			}, {
				name : 'createUserName', //创建人名称
				type : 'string'
			}, {
				name : 'createUserCode', //创建人名称
				type : 'string'
			}, {
				name : 'createTime', //创建时间
				type : 'date',
				convert:dateConvert
			}]
		});

//站内消息数据 
Ext.define('Dpap.messages.toDoMsgStore',{
	extend:'Ext.data.Store',
	model:'Dpap.messages.toDoMsgModel', 
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
		actionMethods : 'POST',
		url :message.realPath(message.toDoMsgIndex.QueryUrl),
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
 
//系统消息表格
Ext.define('Dpap.messages.toDoMsgGrid',{
	extend: 'Ext.grid.Panel', 
	title: message.toDoMsgIndex.i18n('dpap.message.share.queryResult'),
	columnWidth: 1,
	stripeRows: true,
    columnLines: true,
	collapsible: false,
    bodyCls: 'autoHeight',
    frame: true,
    //增加表格列的分割线
	cls: 'autoHeight',
	store :  Ext.create('Dpap.messages.toDoMsgStore'),
	autoScroll : true,
	height: 'autoHeight',
	emptyText: message.toDoMsgIndex.i18n('dpap.message.share.queryResultIsEmpty'),
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
	columns : [{
		xtype:'actioncolumn',
		width:100,
		text: message.toDoMsgIndex.i18n('dpap.message.toDoMsg.domain'),
		align: 'center',
		items: [{
                tooltip: message.toDoMsgIndex.i18n('dpap.message.toDoMsg.deal'),
				iconCls:'deppon_icons_dispose',align : 'center',
				width:30,
                handler: function(grid, rowIndex, colIndex) {
                		message.toDoMsgIndex.toDodeal(grid, rowIndex, colIndex);  
                	}
            }]
		},   
		{ text: message.toDoMsgIndex.i18n('dpap.message.toDoMsg.title'),align : 'center',dataIndex : 'title' ,hidden:true},
		{ text: message.toDoMsgIndex.i18n('dpap.message.toDoMsg.receiveOrgCode'),align : 'center', dataIndex : 'receiveOrgCode' ,hidden:true},
		{ text: message.toDoMsgIndex.i18n('dpap.message.toDoMsg.receiveSubOrgCode'),align : 'center', dataIndex : 'receiveSubOrgCode' ,hidden:true},
		{ text: message.toDoMsgIndex.i18n('dpap.message.toDoMsg.receiveOrgName'),align : 'center', dataIndex : 'receiveSubOrgName' ,width:140}, 
		{ text: message.toDoMsgIndex.i18n('dpap.message.toDoMsg.busType'),align : 'center',  dataIndex: 'businessType' ,width:140
		,renderer:function(value){
				var displayField = Deppon.Dictionary.getDictNameByCode(value,'DPAP_MSG_BUSITYPE');
				return displayField;
			}
		},
		{ text: message.toDoMsgIndex.i18n('dpap.message.toDoMsg.busNo'), align : 'center', dataIndex : 'businessNo' ,width:120},
		{ text: message.toDoMsgIndex.i18n('dpap.message.toDoMsg.serialNumber'),align : 'center', dataIndex : 'serialNumber' ,width:160}, 
		{ text: message.toDoMsgIndex.i18n('dpap.message.toDoMsg.toDoStatus'), align : 'center', dataIndex: 'status' ,width:80
		,renderer:function(value){
				var displayField = Deppon.Dictionary.getDictNameByCode (value,'DPAP_MSG_STATUS');
				return displayField;
			}
			},
		{ text: message.toDoMsgIndex.i18n('dpap.message.toDoMsg.createUserName'), align : 'center', dataIndex: 'createUserName' ,width:120},
		{ text: message.toDoMsgIndex.i18n('dpap.message.toDoMsg.createTime'),flex:1,align : 'center', dataIndex: 'createTime',width:140,
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
         dateRangeText: message.toDoMsgIndex.i18n('dpap.message.share.validateDate')
     });  

//查询待办消息表单
Ext.define('Dpap.messages.toDoMsgForm',{
	extend:'Ext.form.Panel',
	title:message.toDoMsgIndex.i18n('dpap.message.share.queryCondition'),
	frame:true,
	collapsible:true,
	animcollapse:true,
	columnWidth:1,
	defaults:{
		xtype : 'textfield',
		margin : '5 10 5 10',
		labelWidth : 85
	},
	defaultType:'textfield',
	layout: 'column',
	initComponent :function(config) {
		var me = this, cfg = Ext.apply({}, config);
			me.items = [{
			    id:'beginDate',
				xtype:'datetimefield_date97',
				name:'msgVo.toDoDto.createStartTime', 
				fieldLabel:message.toDoMsgIndex.i18n('dpap.message.toDoMsg.toDoStartDate'),
				format:'Y-m-d H:i:s',
				dateConfig: {
					el: 'beginDate-inputEl',
					dateFmt: 'yyyy-MM-dd HH:mi:ss'
				},
				editable:'false',
				allowBlank:false ,
				time:true,
				value:message.toDoMsgIndex.formatDefaultDate(true,message.toDoMsgIndex.FORMAT_TIME),
				dateRange: {begin: 'beginDate', end: 'endDate' },  
		        vtype: 'dateRange' ,
		        columnWidth:0.33
			 
		    },{
			 	id:'endDate',
				xtype:'datetimefield_date97',
				name:'msgVo.toDoDto.createEndTime',
				labelWidth:30,
			    labelSeparator:'',
				fieldLabel:message.toDoMsgIndex.i18n('dpap.message.instationMsg.arrive'),
				format:'Y-m-d H:i:s',
				dateConfig: {
					el: 'endDate-inputEl',
					dateFmt: 'yyyy-MM-dd HH:mi:ss'
				},
				editable:'false',
				allowBlank:false,
				value:message.toDoMsgIndex.formatDefaultDate(false,message.toDoMsgIndex.FORMAT_TIME) ,
				dateRange: {begin: 'beginDate', end: 'endDate' },  
		        vtype: 'dateRange' ,
		        columnWidth:0.33
		    },{
			 	xtype:'dictcombo',
			 	dictType: 'DPAP_MSG_BUSITYPE',
			 	fieldLabel:message.toDoMsgIndex.i18n('dpap.message.toDoMsg.busType'),//'业务类型',
			 	name:'msgVo.toDoDto.businessType',
			 	anyRecords:[{'valueCode':'ALL','valueName':'全部'}],
			 	columnWidth:0.33
		 	
			 },{
			 	xtype:'dictcombo',
			 	dictType: 'DPAP_MSG_STATUS',
			 	fieldLabel:message.toDoMsgIndex.i18n('dpap.message.toDoMsg.toDoStatus'),//'待办状态',
			 	name:'msgVo.toDoDto.status',
			 	anyRecords:[{'valueCode':'ALL','valueName':'全部'}],
			 	columnWidth:0.33
		 	
		    },{
			 	fieldLabel:message.toDoMsgIndex.i18n('dpap.message.toDoMsg.busNo'),//'业务单号',
			 	name:'msgVo.toDoDto.businessNo',
			 	columnWidth:0.33
		 	
		    },{
			 	fieldLabel:message.toDoMsgIndex.i18n('dpap.message.toDoMsg.serialNumber'),//'待办流水号',
			 	name:'msgVo.toDoDto.serialNumber',
			 	columnWidth:0.33
		 	
		    }];
			me.buttons = [{
				  xtype : 'button',
				  text:message.toDoMsgIndex.i18n('dpap.message.share.reset'),   
				  columnWidth:.12,
				  handler:function(){
					 this.up('form').getForm().reset();
				  }
			},'->',{
				  xtype : 'button',
				  text:message.toDoMsgIndex.i18n('dpap.message.share.query'),
				  columnWidth:.12,
				  cls:'yellow_button',  
				  handler:function(){ 
					    login.msg.bisType=null;  
					    message.toDoMsgIndex.search();
				  }
			}];
			me.callParent([cfg]);
	 }	
});

Ext.onReady(function() {
	Ext.QuickTips.init();
	if (Ext.getCmp('T_message-toDoMsgInit_content')) {
		return;
	} 
	message.toDoMsgIndex.msgForm = Ext.create('Dpap.messages.toDoMsgForm');//查询FORM
	message.toDoMsgIndex.msgGrid = Ext.create('Dpap.messages.toDoMsgGrid');//查询结果GRID
	
	Ext.getCmp('T_message-toDoMsgInit').add(Ext.create('Ext.panel.Panel', {
		id : 'T_message-toDoMsgInitIndex_content',
		cls : 'panelContentNToolbar',
		bodyCls : 'panelContentNToolbar-body',
		//获得查询FORM
		getQueryForm : function() {
			return message.toDoMsgIndex.msgForm;
		},
		//获得查询结果GRID
		getMsgGridGrid : function() {
			return message.toDoMsgIndex.msgGrid;
		},
		items: [ message.toDoMsgIndex.msgForm,message.toDoMsgIndex.msgGrid]
	   }));
});



