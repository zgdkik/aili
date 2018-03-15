Ext.onReady(function() {
			// 开启动态加载
			Ext.Loader.setConfig({
						enabled : true
					});
			// 画界面
			Ext.create('Ext.Viewport', {
						//width : window.screen.availWidth * 0.9,
						height : window.screen.availHeight * 0.1,
						frame:true,
						layout : 'hbox',
						items : [Ext.create('exceptionQueryForm', {
											id : 'exceptionQueryForm'
										})]
					});
		});
// 查询panel
Ext.define('exceptionQueryForm', {
			extend : 'Ext.form.Panel',
			// id : 'exceptionQueryForm',
			frame : true,
			title : '查询条件',
			layout : {
				type : 'table',
				columns : 2
			},
			defaults : {
				width : 250,
				labelAlign : 'left',
				margin : '4 5 4 4'
			},
			defaultType : 'textfield',
			items : [{
				xtype : 'datetimefield',
				format : 'Y-m-d H:i',
				editable : false,
				allowBlank : false,
				name : 'startTime',
				// width : 100,
				labelWidth : 100,
				fieldLabel : '开始时间:',
				labelSeparator : '',
				listeners : {
					select : function(th) {
						var fromDate = th.getValue();
						var toDate = Ext.getCmp('exceptionQueryForm').getForm()
								.findField('endTime').getValue();
						if (!isValidStartDataAndEndDate(fromDate, toDate)) {
							showMessage("开始时间必须小于结束时间,结束时间小于当前时间");
							th.reset();
							Ext.getCmp('exceptionQueryForm').getForm()
									.findField('endTime').reset();
						}
					}
				}
			}, {
				xtype : 'datetimefield',
				format : 'Y-m-d H:i',
				editable : false,
				allowBlank : false,
				name : 'endTime',
				// width : 100,
				labelWidth : 100,
				fieldLabel : '结束时间:',
				labelSeparator : '',
				listeners : {
					select : function(th) {
						var endTime = th.getValue();
						var startTime = Ext.getCmp('exceptionQueryForm').getForm()
								.findField('startTime').getValue();
						if (!isValidStartDataAndEndDate(startTime, endTime)) {
							showMessage("开始时间必须小于结束时间,结束时间小于当前时间");
							th.reset();
							Ext.getCmp('exceptionQueryForm').getForm()
									.findField('startTime').reset();
						}
					}
				}
			},{
				fieldLabel : '服务编码',
				labelWidth : 60,
				xtype:'combo',
				store:Ext.create('codeStore'),
				displayField:'svcCode',
				valueField:'svcCode',
				width : 250,
				name : 'svcCode',
				colspan : 1
			}/*, {
				xtype:'combo',
				store:Ext.create('exceptionCodeStore'),
				valueField:'name',
				displayField:'name',
				fieldLabel : '异常类型',
				width : 500,
				name : 'exceptionCode',
				colspan : 2
			}*/, {
				fieldLabel : 'fromURL',
				width : 250,
				name : 'fromURL',
				width : 500,
				colspan : 2
			}, {
				xtype : 'button',
				text : '导出',
				style : 'margin-left:0px;',
				width : 60,
				handler : function() {
					//window.open('http://www.baidu.com');
					var form = Ext.getCmp('exceptionQueryForm').getForm();
					var from = form.findField('startTime').getValue();
					var end = form.findField('endTime').getValue();
					var svcCode = form.findField('svcCode').getRawValue();
					//var exceptionCode = form.findField('exceptionCode').getRawValue();
					var fromURL = form.findField('fromURL').getRawValue();
					window.open('../exceptionlog/export.action?bean.from='+formatDate(from)+'&bean.end='+formatDate(end)+'&bean.svcCode='+svcCode+'&bean.fromUriEndpoint='+fromURL);
					//供测试用
					/*		Ext.create('Ext.window.Window', {
							title : '下载页面',
							width : window.screen.availWidth * 0.5,
							height : window.screen.availHeight * 0.3,
							html : '<iframe src ="export.action?bean.from='+ (from==null?"":from.getTime())
									+"&bean.end="+(end==null?"":end.getTime())
									+"&bean.svcCode="+(svcCode==null?"":svcCode)
									+"&bean.fromUriEndpoint="+fromURL
									+ '" width="100%" height="100%" frameborder="no" border="0" framespacing="0" align="left"></iframe>'
						}).show();*/
				}
			}]
		});
function isValidStartDataAndEndDate(startDate, endDate) {
	if (Ext.isEmpty(startDate) || Ext.isEmpty(endDate)) {
		return true;
	}
	var startDateTime = startDate.getTime();
	var endDateTime = endDate.getTime();
	var d = new Date();
	if (startDateTime <= endDateTime && endDateTime <= d.getTime()) {
		return true;
	} else {
		return false;
	}
};

function formatDate(a){
	if(a ==null){
		return "";
	}
	return (a.getFullYear()+"-"+(a.getMonth()+1)+"-"+a.getDate()+"T"+a.getHours()+":"+a.getMinutes()+":"+a.getSeconds());
}