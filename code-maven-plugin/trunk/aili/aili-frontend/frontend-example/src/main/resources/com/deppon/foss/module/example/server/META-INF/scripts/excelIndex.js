example.currentTableBean=[];
example.loadTimeStore = null;
example.getLoadTimeStore = function(){
	if(example.loadTimeStore==null){
		example.loadTimeStore = Ext.create('Ext.data.Store',{
			autoLoad: false,
			fields: ['name','value'],
			proxy:{
				type : 'ajax',
				actionMethods : 'POST',
				url:example.realPath("queryHistoryLoadTime.action"),
				reader : {
					type : 'json',
					root : 'excelQueryData.loadTimeList',
				}
			},
			listeners : {
				// 在pageStore加载前，向其传参
				beforeload : function(store, operation, eOpts) {
					Ext.apply(operation, {
						params : {
							'currentTableBean.name': example.currentTableBean.name
						}
					});
				}
			}
		});
	}
	return example.loadTimeStore;
};
example.currentLoadTime = null;
//初始化分页
example.currentPage = 1;
example.totalPage = 1;
example.limit = 25;
example.navigate =  function(currentPage,totalPage,componentId){
	//构建分页脚本
	var navigator = "";
    if(totalPage>1){
    	example.totalPage = totalPage;
    	navigator = "<div style='text-align:right;'>";
    	if(currentPage-1<=0){
    	  navigator += "<span style=\"font-family: Arial;font-size:11px;\">首页</span>&nbsp;&nbsp;&nbsp;&nbsp;";
    	  navigator += "<span style=\"font-family: Arial;font-size:11px;\">上一页</span>&nbsp;&nbsp;&nbsp;&nbsp;";
    	}else{
    	  navigator += "<a style=\"font-family: Arial;font-size:11px;\" href=\"#\" onclick='example.currentPage=1;example.navigatePaging(\""+componentId+"\");' "+">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
    	  navigator += "<a style=\"font-family: Arial;font-size:11px;\" href=\"#\" onclick='example.currentPage=(example.currentPage-1<=0?1:example.currentPage-1);example.navigatePaging(\""+componentId+"\");' "+">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
    	}
    	if(currentPage+1>totalPage){
    	  navigator += "<span style=\"font-family: Arial;font-size:11px;\">下一页</span>&nbsp;&nbsp;&nbsp;&nbsp;";
    	  navigator += "<span style=\"font-family: Arial;font-size:11px;\">末页</span>&nbsp;&nbsp;&nbsp;&nbsp;";
    	}else{
    	  navigator += "<a style=\"font-family: Arial;font-size:11px;\" href=\"#\" onclick='example.currentPage=(example.currentPage+1>example.totalPage?example.totalPage:example.currentPage+1);example.navigatePaging(\""+componentId+"\");' "+">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
    	  navigator += "<a style=\"font-family: Arial;font-size:11px;\" href=\"#\" onclick='example.currentPage=example.totalPage;example.navigatePaging(\""+componentId+"\");' "+">末页</a>&nbsp;&nbsp;&nbsp;&nbsp;";
    	}
        navigator += "<span style=\"font-family: Arial;font-size:11px;\">页数：</span><select onchange='example.currentPage=this.value;example.navigatePaging(\""+componentId+"\");'>";
    	for(var i = 1 ; i<=totalPage ; i++){
    	  navigator += "<option value='"+i+"' "+(i==currentPage?"selected":"")+">"+i+"</option>"
    	}
    	navigator += "</select>/&nbsp;<span id='totalPage' style=\"font-family: Arial;font-size:13px;\">"+totalPage+"</span></div>";
    }
    return navigator;
};
example.navigatePaging = function(componentId){
	Ext.Ajax.request({
		url : example.realPath("navigatePaging.action"),
		jsonData : {
			'currentTableBean':example.currentTableBean,
			'excelQueryData':{'currentLoadTime':example.currentLoadTime,'currentPage':example.currentPage,'totalPage':example.totalPage},
			'limit':example.limit
			},
			success : function(response) {
				example.buildTable(response,componentId);
			},
			exception : function(response) {
            	var json = Ext.decode(response.responseText); 
	        	Ext.ux.Toast.msg(example.excel.i18n('dpap.excel.msginfo'), json.message, 'error');
            }
	});
}
/**
 * 构建数据表格
 */
example.buildTable =  function(response,componentId){
	var json = Ext.decode(response.responseText); 
	var tableStr = "<table width='100%' cellspacing='0' cellpadding='1' border='1'>";
	var headsArray = example.currentTableBean.headsArray;
	var rowNum = headsArray.length;
	var headFieldNum = 0;
	Ext.Array.forEach(headsArray,function(_item,index,allItems){
		tableStr += "<tr>";
		Ext.Array.forEach(_item,function(subItem,subIndex,subAllItems){
			if(subItem!=null){
				if(subItem.field==null){
					tableStr += "<td rowspan='"+subItem.rowspan+"' colspan='"+subItem.colspan+"' align='"+subItem.align+"'>";
					tableStr += subItem.text==null?"":subItem.text;
					tableStr += "</td>";
				}else{
					tableStr += "<td rowspan='"+subItem.rowspan+"' colspan='"+subItem.colspan+"' align='"+subItem.align+"'>";
					tableStr += "head_field_tag_"+headFieldNum;
					tableStr += "</td>";
					headFieldNum++;
				}
			}
		});
		tableStr += "</tr>";
	});
	
	var queryDataList = json.excelQueryData.queryDataList;
	var setFlg = false;
	Ext.Array.forEach(queryDataList,function(_item,index,allItems){
		tableStr += "<tr>";
		var tdNum = _item.length;
		if(!setFlg){
			var headField = new Array();
			var i = 0;
			Ext.Array.forEach(_item,function(subItem,subIndex,subAllItems){
				if(subIndex < tdNum-headFieldNum){
					if(subItem!=null){
						tableStr += "<td align='left' style='padding:1px 1px 1px 1px;'>"+subItem+"</td>";
					}else{
						tableStr += "<td align='left'>&nbsp;</td>";
					}
				}else{
					headField[i] = subItem;
					i++;
				}
			});
			for(var j = 0 ; j < headField.length ; j++ ){
				tableStr = tableStr.replace("head_field_tag_"+j, headField[j]);
			}
			setFlg = true;
		}else{
			Ext.Array.forEach(_item,function(subItem,subIndex,subAllItems){
				if(subIndex < tdNum-headFieldNum){
					if(subItem!=null){
						tableStr += "<td align='left' style='padding:1px 1px 1px 1px;'>"+subItem+"</td>";
					}else{
						tableStr += "<td align='left'>&nbsp;</td>";
					}
				}
			});
		}
		tableStr += "</tr>";
	});
	tableStr += "</table>";
	//分页导航Div
	var navigatorDiv = example.navigate(json.excelQueryData.currentPage,json.excelQueryData.totalPage,componentId);
	tableStr += navigatorDiv;
	var id = Ext.id();  
    var el = {  
        id : id,  
        tag : 'div',  
        html : tableStr,  
        style : 'width:100%'  
    }; 
    var queryResultPanel = Ext.getCmp(componentId);
    if(queryResultPanel.isHidden()){
    	queryResultPanel.show();
    }
    Ext.DomHelper.overwrite(queryResultPanel.body, el);
    //如果是数据删除panel，添加导出按钮
	if(componentId=='Dpap_excel_DataDeleteResultPanel_Id'){
		Ext.create('Ext.container.Container', {
        	style : 'width:100%',
        	layout:'column',
        	defaults : {
        		margin : '10 3 0 0'
        	},
        	renderTo:queryResultPanel.body,
        	items:[{
            		xtype:'button',
            		text:'删除',
            		columnWidth : .08,
            		plugins: {
        				ptype: 'buttonlimitingplugin',
        				seconds: 1
        			},
            		handler: function() {
            			Ext.Msg.confirm(example.excel.i18n('dpap.excel.msginfo'),'是否确认删除？',
    							function(btn){
    								if(btn=='yes'){
    									Ext.Ajax.request({
    			            				url : example.realPath("deleteDataByLoadTime.action"),
    			            				jsonData : {
    			            					'currentTableBean':{"name":example.currentTableBean.name},
    			            					'excelQueryData':{"currentLoadTime":example.currentLoadTime}
    			            					},
    			            				success : function(response) {
    			            					var json = Ext.decode(response.responseText); 
    			            					if(json.deleteFlg==true){
    			            						Ext.ux.Toast.msg(example.excel.i18n('dpap.excel.msginfo'), '删除成功！');
    			            						//隐藏此版本查询结构面板
    			            						queryResultPanel.hide();
    			            						//刷新combo历史版本时间
    			            						Ext.getCmp('Dpap_excel_DataDeletePanel_Id').getForm().findField('loadTime').setValue('');
    			            						Ext.getCmp('Dpap_excel_DataQueryPanel_Id').getForm().findField('loadTime').setValue('');
    			            						example.getLoadTimeStore().load();
    			            					}else{
    			            						Ext.ux.Toast.msg(example.excel.i18n('dpap.excel.msginfo'), '删除失败！');
    			            					}
    			            				},
    			            				exception : function(response) {
    			            	            	var json = Ext.decode(response.responseText); 
    			            		        	Ext.ux.Toast.msg(example.excel.i18n('dpap.excel.msginfo'), json.message, 'error');
    			            	            }
    			            			});
    								}
    					})
                    }
    			},{
            		border : false,
        			xtype : 'container',
        			columnWidth : .92,
        			html : '&nbsp;'
                }
               ]		             
        });
	}
}
/**
 * Td的Model
 */
Ext.define('Dpap.excel.TdModel', {
	extend : 'Ext.data.Model',
	fields : [{
			name : 'colspan'
		},{
			name: 'rowspan'
		},{
			name: 'align'
		},{
			name: 'field'
		},{
			name: 'fieldAlias'
		},{
			name: 'text'
		},{
			name: 'clazz'
		},{
			name: 'notNull',type:'boolean'
		},{
			name: 'nullFlag'
		}],
	belongsTo:'Dpap.excel.TrModel'
});
/**
 * Tr的Model
 */
Ext.define('Dpap.excel.TrModel',{
	extend : 'Ext.data.Model',
	hasMany:[{model:'Dpap.excel.TdModel',name:'tds'}],
	belongsTo:'Dpap.excel.TableModel'
});
/**
 * Field的Model
 */
Ext.define('Dpap.excel.FieldModel',{
	extend : 'Ext.data.Model',
	fields:[{
		name:'name'
	},{
		name:'alias'
	},{
		name:'description'
	},{
		name:'dataType'
	},{
		name:'joinIndex'
	},{
		name:'nullTag',type:'boolean'
	}],
	belongsTo:'Dpap.excel.TableModel'
});
/**
 * JoinTable的Model
 */
Ext.define('Dpap.excel.JoinTableModel', {
	extend : 'Ext.data.Model',
	fields:[{name:'index'
		},{
			name:'schema'
		},{
			name:'name'
		},{
			name:'alias'
		},{
			name:'description'
		},{
			name:'field'
		}],
	belongsTo:'Dpap.excel.TableModel'
});
/**
 * 
 * Table的Model
 */
Ext.define('Dpap.excel.TableModel', {
	extend : 'Ext.data.Model',
	fields : [{
			name : 'schema'
		},{
			name: 'name'
		},{
			name: 'alias'
		},{
			name: 'tableType',type:'string',convert:function(val){
				if(val=='1'){
					return example.excel.i18n('dpap.excel.ExcelTemplate.dataTable');
				}else{
					return example.excel.i18n('dpap.excel.ExcelTemplate.report');
				}
			}
		},{
			name: 'filePath',type:'string',convert:function(val){
				return "<a href='"+example.realPath('downLoadFile.action?fileName='+encodeURI(encodeURI(val)))+"'>"+val+"</a>"
			}
		},{
			name: 'description'
		},{
			name: 'role'
		}],
	hasMany:[{
			model:'Dpap.excel.TrModel',name:'headList'
		},{
			model:'Dpap.excel.FieldModel',name:'fieldList'
		},{
			model:'Dpap.excel.JoinTableModel',name:'joinTableList'
		}]
});
/**
 * 定义Table数据加载器
 */
Ext.define('Dpap.excel.TableStore', {
	extend : 'Ext.data.Store',
	pageSize : 20,
	model : 'Dpap.excel.TableModel',
	autoLoad : true,
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : example.realPath('getTemplateContent.action'),
		reader : {
			type : 'json',
			root : 'templateList',
			totalProperty : 'totalCount'
		}
	}
});
Ext.define('Dpap.excel.TableGrid',{
	extend:'Ext.grid.Panel',
    height: 200,
    title: example.excel.i18n('dpap.excel.ExcelTemplate.selector'),
    autoScroll: true,
    columnLines: true,
    frame : true,
	sortableColumns:false,
	enableColumnHide:false,
	enableColumnMove:false,
	stripeRows : true, // 交替行效果
    columns:[{
        text: example.excel.i18n('dpap.excel.ExcelTemplate.alias'),
        dataIndex: 'alias',
        flex:2.2,
        sortable: false
    },{
        text: example.excel.i18n('dpap.excel.ExcelTemplate.description'),
        dataIndex: 'description',
        flex:2.2,
        sortable: true
    },{
        text: example.excel.i18n('dpap.excel.ExcelTemplate.tableType'),
        dataIndex: 'tableType',
        flex:2.2,
        sortable: true
    },{
       text: example.excel.i18n('dpap.excel.ExcelTemplate.filePath'),
       dataIndex: 'filePath',
       flex:3.4,
       sortable: true
    }],
    selModel: Ext.create('Ext.selection.RadioModel'),
    beforeload: function(store, operation, eOpts){
    	Ext.apply(operation,{
			params : {
				'nodeName':Ext.get('nodeName').getValue(false)
			}
		});
    },
    constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Dpap.excel.TableStore',{
			listeners: {
				beforeload: me.beforeload
			}
		});
		me.callParent([cfg]);
	},
    listeners:{
    	itemclick:function(view, record, item, index, e, eOpts ){
    		var tableStr = "<table width='100%' cellspacing='0' cellpadding='1' border='1'>";
    		var headsArray = record.raw.headsArray;
    		Ext.Array.forEach(headsArray,function(_item,index,allItems){
    			tableStr += "<tr>";
    			Ext.Array.forEach(_item,function(subItem,subIndex,subAllItems){
    				if(subItem!=null){
    					tableStr += "<td rowspan='"+subItem.rowspan+"' colspan='"+subItem.colspan+"' align='"+subItem.align+"'>";
        				tableStr += subItem.text==null?"":subItem.text;
        				tableStr += "</td>";
    				}
    			});
    			tableStr += "</tr>";
    		});
    		tableStr += "</table>";
    		var id = Ext.id();  
            var el = {  
                id : id,  
                tag : 'div',  
                html : tableStr,  
                style : 'width:100%'  
            }; 
            var excelPanel = Ext.getCmp('T_example-excelIndex_content');
            var templateDetailPanel = excelPanel.getTemplateDetailPanel();
            if(templateDetailPanel.isHidden()){
            	templateDetailPanel.show();
            }
            Ext.DomHelper.overwrite(templateDetailPanel.body, el);  
            Ext.create('Ext.container.Container', {
            	style : 'width:100%',
            	layout:'column',
            	defaults : {
            		margin : '10 3 0 0'
            	},
            	renderTo:templateDetailPanel.body,
            	items:[{
            		border : false,
        			xtype : 'container',
        			columnWidth : .92,
        			html : '&nbsp;'
                    },{
            		xtype:'button',
            		cls : 'yellow_button',
            		text:example.excel.i18n('dpap.excel.button.next'),
            		columnWidth : .08,
            		plugins: {
        				ptype: 'buttonlimitingplugin',
        				seconds: 1
        			},
            		handler: function() {
            			example.currentTableBean = record.raw ;
            			templateDetailPanel.hide();
            			excelPanel.getTableGrid().hide();
            			
            			var exelTabPanel = excelPanel.getExcelTablePanel();
            			exelTabPanel.removeAll();
            			exelTabPanel.show();
            			var excelFileUploadPanel = Ext.create('Dpap.excel.ExcelFileUploadPanel',{
            				id:'Dpap_excel_ExcelFileUploadPanel_Id',
            				title:record.raw.alias+"-数据导入"
            			});
            			var excelDetailPanel = Ext.create('Dpap.excel.ExceptionDetailPanel',{
            				id:'Dpap_excel_ExceptionDetailPanel_Id'
            			});
            			var dataQueryPanel = Ext.create('Dpap.excel.DataQueryPanel',{
            				id:'Dpap_excel_DataQueryPanel_Id',
            				title:record.raw.alias+"-数据查询"
            			});
            			var queryResultPanel = Ext.create('Dpap.excel.DataQueryResultPanel',{
            				id:'Dpap_excel_DataQueryResultPanel_Id',
            			});
            			var deleteResultPanel = Ext.create('Dpap.excel.DataDeleteResultPanel',{
            				id:'Dpap_excel_DataDeleteResultPanel_Id'
            			});
            			var dataDeletePanel = Ext.create('Dpap.excel.DataDeletePanel',{
            				id:'Dpap_excel_DataDeletePanel_Id',
            				title:record.raw.alias+"-数据删除"
            			});
            			exelTabPanel.add({
            				title:'&nbsp;数据导入&nbsp;',
            				layout:'auto',
            				items:[excelFileUploadPanel,excelDetailPanel]
            			});
            			exelTabPanel.add({
            				title:'&nbsp;数据查询&nbsp;',
            				items:[dataQueryPanel,queryResultPanel]
            			});
            			exelTabPanel.add({
            				title:'&nbsp;数据删除&nbsp;',
            				items:[dataDeletePanel,deleteResultPanel]
            			});
            			exelTabPanel.setActiveTab(0);
            			excelPanel.add(exelTabPanel);
                    }
                   }]
            });
        }
    }
});
/**
 * Excel导入、查询、删除Tab
 */
Ext.define('Dpap.excel.ExcelTabPanel',{
	extend:'Ext.tab.Panel',
	cls : 'innerTabPanel',
	frame: false,
	layout: 'auto'
});
/**
 * Excel选择和导入 panel
 */
Ext.define('Dpap.excel.ExcelFileUploadPanel',{
	extend:'Ext.form.Panel',
	title:example.excel.i18n('dpap.excel.excelFileUploadPanel.title'),
    bodyPadding: 10,
    frame: true,
    hidden:false,
    items: [{
        xtype: 'filefield',
        name: 'excelFile',
        fieldLabel: 'Excel数据文件',
        readOnly:true,
        readOnlyCls:'disable',
        labelWidth: 100,
        msgTarget: 'side',
        allowBlank: false,
        anchor: '45%',
        buttonText: example.excel.i18n('dpap.excel.excelFileUploadPanel.button.choose'),
        validator:function(val){
        	if(val.indexOf('xlsx')==-1){
        		return example.excel.i18n('dpap.excel.notExcel');
        	}else{
        		return true;
        	}
        }
    },{
    	xtype:'container',
    	layout: 'anchor',
    	anchor:'45%',
    	style:'text-align:right;',
    	items:[{
    		xtype:'button',
    		cls : 'yellow_button',
    		width:70,
        	text: example.excel.i18n('dpap.excel.excelFileUploadPanel.button.import'),
        	listeners:{
        		click:function(){
        			Ext.Ajax.request({
        				url : example.realPath("currentTableBeanTransfer.action"),
        				jsonData : {'currentTableBean':example.currentTableBean},
        				success : function(response) {
        					var excelFileUploadPanel = Ext.getCmp('Dpap_excel_ExcelFileUploadPanel_Id');
        					var form = excelFileUploadPanel.getForm();
        		            if(form.isValid()){
        		                form.submit({
        		                    url: example.realPath("uploadExcel.action"),
        		                    waitMsg: 'check...',
        		                    success: function(fp, o) {
        		                    	var tableStr = "";
        		                    	//检查错误信息数组
        		                    	var checkErrorList =  o.result.checkErrorList;
        		                    	//异常的行数
        		                    	var errorRowSize = o.result.excelCheckData.errorRowSize;
        		                    	//导入信息
        		                    	var importMsg  = o.result.importMsg;
        		                    	var importFlg = false;
        		                    	if(checkErrorList!=null && checkErrorList.length>0){
        			                    	for(var i = 0 ; i < checkErrorList.length ; i++){
        			                    		if(i==0){
        			                    			tableStr = "<span style='color:red;font-weight:bold;'>"+checkErrorList[i]+"</span>";
        			                    		}else{
        			                    			tableStr += "</br><span style='color:red;font-weight:bold;'>"+checkErrorList[i]+"</span>"
        			                    		}
        			                    	}
        		                    	}else if(errorRowSize != null && errorRowSize > 0){
        			                    	var divStr = "<div style='width:100%;text-align:left;padding:2px 0px 8px 2px;font-size: 12px;'>" +
                            				"异常数据有<span style='color:red;font-weight:bold;'>"+errorRowSize+"</span>行(如果有异常类型的数据，请更正后导入)</div>";
        			                    	tableStr = divStr+"<table width='100%' cellspacing='0' cellpadding='1' border='1'>";
        			                    	//表头部分
        			                		var headsArray = o.result.currentTableBean.headsArray;
        			                		var columnSize = 0;
        			                		Ext.Array.forEach(headsArray,function(_item,index,allItems){
        			                			tableStr += "<tr>";
        			                			tableStr += "<td>"+(index+1)+"</td>";
        			                			if(columnSize==0)
        			                				columnSize = _item.length;
        			                			Ext.Array.forEach(_item,function(subItem,subIndex,subAllItems){
        			                				if(subItem!=null){
        			                					tableStr += "<td rowspan='"+subItem.rowspan+"' colspan='"+subItem.colspan+"' align='"+subItem.align+"'>";
        			                    				tableStr += subItem.text==null?"":subItem.text;
        			                    				tableStr += "</td>";
        			                				}
        			                			});
        			                			tableStr += "</tr>";
        			                		});
        			                		//数据部分
        			                		var rightDataList = o.result.excelCheckData.rightDataList;
        			                		var errorDataList = o.result.excelCheckData.errorDataList;
        			                		var errorDataMsgList = o.result.excelCheckData.errorDataMsgList;
        			                		var flag=0;
        			                		for(var index=0 ; index < errorDataList.length ; index++){
        			                			if(errorDataList[index]==null && flag==0){
        			                				tableStr += "<tr>";
        				                			tableStr += "<td>...</td>";
        				                			tableStr += "<td colspan='"+columnSize+"' align='center'>...</td>";
        				                			tableStr += "</tr>";
        				                			flag = 1;
        			                			}else if(errorDataList[index]!=null){
        			                				tableStr += "<tr>";
        			                				tableStr += "<td>"+(index+1)+"</td>";
        			                				for(var i = 0 ; i < errorDataList[index].length ; i++){
        			                					var isError = 0;
        			                					if(errorDataMsgList[index]!=null){
        			                						isError = 1;
        			                					}
        			                					if(isError == 1 && errorDataMsgList[index][i]!=null){
        			                						tableStr += "<td nowrap='nowrap' id='td"+index+"_"+i+"' style='background-color:#FF9797;cursor:pointer;' title='"+
        			                						errorDataMsgList[index][i]+"请更正Excel数据。'>"+errorDataList[index][i]+"</td>";
        			                					}else{
        			                						tableStr += "<td>"+errorDataList[index][i]+"</td>";
        			                					}
        			                				}
        			                				tableStr += "</tr>";
        			                				flag = 0;
        			                			}
        			                		}
        			                		tableStr += "</table>";
        		                    	}else{
        		                    		if(importMsg!=null && importMsg.indexOf("成功导入")>-1){
        		                    			Ext.ux.Toast.msg(example.excel.i18n('dpap.excel.msginfo'), importMsg);
        		                    			//刷新combo历史版本时间
			            						example.getLoadTimeStore().load();
        		                    		}else{
        		                    			Ext.ux.Toast.msg(example.excel.i18n('dpap.excel.msginfo'), importMsg,'error');
        		                    		}
        		                    		importFlg = true;
        		                    	}
        		                		var id = Ext.id();  
        		                        var el = {  
        		                            id : id,  
        		                            tag : 'div',  
        		                            html : tableStr,  
        		                            style : 'width:100%'  
        		                        }; 
        		                        var excelDetailPanel = Ext.getCmp('Dpap_excel_ExceptionDetailPanel_Id');
        		                        if(excelDetailPanel.isHidden() && !importFlg){
        		                        	excelDetailPanel.show();
        		                        }else if(importFlg){
        		                        	if(excelDetailPanel.isVisible()){
        		                        		excelDetailPanel.hide();
        		                        	}
        		                        }
        		                        Ext.DomHelper.overwrite(excelDetailPanel.body, el);
        		                    },
        		                    failure:function(fp, o) {
        		                    }
        		                });
        		            }
        				},
        				exception : function(response) {
        	            	var json = Ext.decode(response.responseText); 
        		        	Ext.ux.Toast.msg(example.excel.i18n('dpap.excel.msginfo'), json.message, 'error');
        	            }
        			});
        		}
        	}
    	},{
    		xtype:'button',
        	text: example.excel.i18n('dpap.excel.excelFileUploadPanel.button.return'),
        	width:70,
        	listeners:{
        		click:function(){
        			var excelPanel = Ext.getCmp('T_example-excelIndex_content');
        			excelPanel.getTemplateDetailPanel().show();
        			excelPanel.getTableGrid().show();
        			excelPanel.getExcelTablePanel().hide();
        		}
        	}
    	}]
    }],
});
/**
 * Excel模板明细
 */
Ext.define('Dpap.excel.TemplateDetailPanel',{
	extend:'Ext.panel.Panel',
	title: example.excel.i18n('dpap.excel.ExcelTemplate.preview'),
	frame : true,
	hidden: true,
    height : 400
});
/**
 * 异常明细Panel
 */
Ext.define('Dpap.excel.ExceptionDetailPanel',{
	extend:'Ext.panel.Panel',
	title:example.excel.i18n('dpap.excel.excelFileUploadPanel.import.result'),
	frame: true,
	autoScroll: true,
	hidden: true,
	height: 400
});
/**
 * 数据查询Panel
 */
Ext.define('Dpap.excel.DataQueryPanel',{
	extend:'Ext.form.Panel',
	title:'数据查询',
	frame:true,
	autoScroll: true,
	layout:'column',
	initComponent: function() {
		var me = this;
		me.items = [{
			xtype:'combo',
			allowBlank : false,
			editable:false,
			blankText : '导入时间不能为空',
			fieldLabel: '已经导入的历史版本时间选择',
			name:'loadTime',
			columnWidth:.5,
			labelWidth: 172,
			margin:'0 10 0 0',
			store: example.getLoadTimeStore(),
			queryMode : 'remote',
			displayField: 'name',
		    valueField: 'value'
		},{
			xtype:'button',
			text:'查询',
			minWidth:70,
			cls : 'yellow_button',
			handler: me.onQuery,
			plugins: {
				ptype: 'buttonlimitingplugin',
				seconds: 1
			},
			scope: me
		},{
			xtype:'button',
			text:'返回',
			minWidth:70,
			handler: me.onReturn,
			scope: me
		}];
		me.callParent();
	},
	onQuery: function() {
		var form = this.getForm();
		if(form.isValid()) {
			example.currentLoadTime = form.findField('loadTime').getValue();
			Ext.Ajax.request({
				url : example.realPath("queryHistoryData.action"),
				jsonData : {
					'currentTableBean':example.currentTableBean,
					'excelQueryData':{'currentLoadTime':example.currentLoadTime,'currentPage':1},
					'limit':example.limit
					},
				success : function(response) {
					example.buildTable(response,'Dpap_excel_DataQueryResultPanel_Id');
				},
				exception : function(response) {
	            	var json = Ext.decode(response.responseText); 
		        	Ext.ux.Toast.msg(example.excel.i18n('dpap.excel.msginfo'), json.message, 'error');
	            }
			});
		}else{
			Ext.ux.Toast.msg(
        			authorization.user.i18n('dpap.authorization.msginfo'), 
        			authorization.user.i18n('请选择版本时间'),'error');
		}
	},
	onReturn:function(){
		var excelPanel = Ext.getCmp('T_example-excelIndex_content');
		excelPanel.getTemplateDetailPanel().show();
		excelPanel.getTableGrid().show();
		excelPanel.getExcelTablePanel().hide();
	}
});
/**
 * 数据查询Tab的查询结果Panel
 */
Ext.define('Dpap.excel.DataQueryResultPanel',{
	extend:'Ext.panel.Panel',
	title: '查询结果',
	frame: true,
	autoScroll: true,
	hidden: true,
	height: 600
});
/**
 * 数据删除Tab的查询结果Panel
 */
Ext.define('Dpap.excel.DataDeleteResultPanel',{
	extend:'Ext.panel.Panel',
	title: '查询结果',
	frame: true,
	autoScroll: true,
	hidden: true,
	height: 600
});
/**
 * 数据删除Panel
 */
Ext.define('Dpap.excel.DataDeletePanel',{
	extend:'Ext.form.Panel',
	title:'数据删除',
	frame:true,
	autoScroll: true,
	layout:'column',
	initComponent: function() {
		var me = this;
		me.items = [{
			xtype:'combo',
			allowBlank : false,
			editable:false,
			blankText : '导入时间不能为空',
			fieldLabel: '已经导入的历史版本时间选择',
			name:'loadTime',
			columnWidth:.5,
			labelWidth: 172,
			margin:'0 10 0 0',
			store: example.getLoadTimeStore(),
			queryMode : 'remote',
			displayField: 'name',
		    valueField: 'value'
		},{
			xtype:'button',
			text:'查询',
			minWidth:70,
			cls : 'yellow_button',
			handler: me.onQuery,
			plugins: {
				ptype: 'buttonlimitingplugin',
				seconds: 1
			},
			scope: me
		},{
			xtype:'button',
			text:'返回',
			minWidth:70,
			handler: me.onReturn,
			scope: me
		}];
		me.callParent();
	},
	onQuery: function() {
		var form =  this.getForm();
		if(form.isValid()) {
			example.currentLoadTime = form.findField('loadTime').getValue();
			Ext.Ajax.request({
				url : example.realPath("queryHistoryData.action"),
				jsonData : {
					'currentTableBean':example.currentTableBean,
					'excelQueryData':{'currentLoadTime':example.currentLoadTime,'currentPage':1},
					'limit':example.limit
					},
				success : function(response) {
					example.buildTable(response,'Dpap_excel_DataDeleteResultPanel_Id');
				},
				exception : function(response) {
	            	var json = Ext.decode(response.responseText); 
		        	Ext.ux.Toast.msg(example.excel.i18n('dpap.excel.msginfo'), json.message, 'error');
	            }
			});
		}else{
			Ext.ux.Toast.msg(authorization.user.i18n('dpap.authorization.msginfo'), '请选择版本时间','error');
		}
	},
	onReturn:function(){
		var excelPanel = Ext.getCmp('T_example-excelIndex_content');
		excelPanel.getTemplateDetailPanel().show();
		excelPanel.getTableGrid().show();
		excelPanel.getExcelTablePanel().hide();
	}
});
Ext.onReady(function() {
	Ext.tip.QuickTipManager.init();
	var tableGrid = Ext.create('Dpap.excel.TableGrid');
	var templateDetailPanel = Ext.create('Dpap.excel.TemplateDetailPanel');  
	var exelTabPanel = Ext.create('Dpap.excel.ExcelTabPanel');
	
	var content = Ext.create('Ext.panel.Panel', {
		id: 'T_example-excelIndex_content',
		cls : 'panelContentNToolbar',
		bodyCls : 'panelContentNToolbar-body',
		layout: 'auto',
		getTableGrid:function(){
			return tableGrid;
		},
		getTemplateDetailPanel:function(){
			return templateDetailPanel;
		},
		getExcelTablePanel:function(){
			return exelTabPanel;
		},
		items: [ tableGrid,templateDetailPanel,exelTabPanel]
	});
	Ext.getCmp('T_example-excelIndex').add(content);
});