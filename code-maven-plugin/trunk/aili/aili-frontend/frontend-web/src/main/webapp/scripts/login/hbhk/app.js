/*-----------------------------全局属性与方法定义--------------------------------*/
//当前页面是否使用全屏（菜单上收）
var isFullScreen=false;
//root专用 CSS class,在展开状态下height=0，请谨慎使用。
var lvl0Style="ye1-node-lvl0";
var baseImagePath=base+'/images/login/';
var imagePath=base+'/images/login/dpap';
var topLeftPanelWidth=306;

//生成当前日期
function constructDateTime(today){
	var dd = today.getDate(),
		mm = today.getMonth()+1, //January is 0!
		yyyy = today.getFullYear(),
		hh = today.getHours(),
		minutes = today.getMinutes(),
		ss=today.getSeconds();
	if(dd<10){dd='0'+dd;} 
	if(mm<10){mm='0'+mm;} 
	if(hh<10){hh='0'+hh;}
	if(minutes<10){minutes='0'+minutes;}
	if(ss<10){ss='0'+ss;}
	today = yyyy+'-'+mm+'-'+dd+' '+hh+':'+minutes+':'+ss;
	return today;
}

//页面滚轮事件handler
function onScroll(){
	//当前标签页
	var curTab=Ext.getCmp('mainAreaPanel').getActiveTab(),
		//header高度
		frameTopHeight=71,
		//标签页的悬浮工具条
		curToolbarID=curTab.id,
		curToolbar=Ext.getCmp(curToolbarID+'_toolbar'),
		sTop=window.pageYOffset|document.documentElement.scrollTop;
	//确认主菜单是否算在宽度内
	var hasNav=1;
	if(isFullScreen==true){
		hasNav=0;
		frameTopHeight=0;
	}else{
		hasNav=1;
	}
	if(curToolbar){
		hasToolbar=1;
	}
	//标签页悬浮工具条位置控制：
	if(sTop>frameTopHeight+63){
		if(curToolbar){
			curToolbar.el.dom.style.position='fixed';
			curToolbar.el.dom.style.top='0px';
			curToolbar.el.dom.style.left=((186*(hasNav))+6)+'px';
			curToolbar.el.dom.style.zIndex=30;
		}
	}else{	
		if(curToolbar){
			curToolbar.el.dom.style.position='absolute';
			curToolbar.el.dom.style.top='18px';
			curToolbar.el.dom.style.left='0px';
			curToolbar.el.dom.style.zIndex=5;
		}
	}
}
//弹出新窗口
function openMsgMenu(id,title,url,winFormType){
	Ext.getCmp('M_announce').getMsgRemindWindow().hide();
	(id,title,url);
}

/**
 * resource的Model
 */
Ext.define('Dpap.main.ResourceModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'functionCode'
	}, {
		name : 'functionName'
	}, {
		name : 'uri'
	}, {
		name : 'functionLevel'
	}, {
		name : 'parentCode'
	}, {
		name : 'active'
	}, {
		name : 'displayOrder'
	}, {
		name : 'checkable'
	}, {
		name : 'functionType'
	}, {
		name : 'leaf'
	}, {
		name : 'iconCls'
	}, {
		name : 'cls'
	}, {
		name : 'functionDesc'
	}, {
		name : 'functionSeq'
	}, {
		name : 'systemType'
	}, {
		name : 'createUser'
	}, {
		name : 'createDate',
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'number'
	}, {
		name : 'modifyUser'
	}, {
		name : 'modifyDate',
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'number'
	} ]
});

//常用功能设置窗口
Ext.define('Dpap.main.NavConfigWindow', {
	extend: 'Ext.window.Window',
	title: login.i18n('dpap.login.NavConfigWindowTitle'),
	height: 600,
	width: 700,
	modal:true,
	closeAction: 'hide',
	layout: {
		type:'column'
	},
	//菜单树
	resourceTreePanel : null,
	getResourceTreePanel : function(){
		if(this.resourceTreePanel==null){
		    this.resourceTreePanel = Ext.create('Ext.tree.Panel', {
		    	frame: true,
		    	title: login.i18n('dpap.login.app.treeMenu'),
		    	width: 250,
		    	height: 470,
		    	animate: true,
		    	useArrows: true,
		    	autoScroll: true,
		        store: Ext.create('Ext.data.TreeStore', {
					root: {
						id: '0',
						text: document.title
					},
					proxy : {
						type : 'ajax',
						actionMethods : 'POST',
						url : base+'/user/loadMenuTree',
						reader : {
							type : 'json',
							root : 'data'
						}
					}
			    }),
		        viewConfig: {
		            plugins: {
		            	ddGroup: 'userMenuGrid',
		                ptype: 'treeviewdragdrop',
		                dragText: login.i18n('dpap.login.app.userMenuGrid'),
		                containerScroll: true
		            }
		        }
		    });
		}
		return this.resourceTreePanel;
	},
	//用户常用菜单列表
	userMenuGridPanel : null,
	getUserMenuGridPanel : function(){
		if(this.userMenuGridPanel==null){
			Ext.create('Ext.data.Store', {
				model: 'Dpap.main.ResourceModel',
			    storeId:'currentDeptStore',
			  	proxy: {
			         type: 'ajax',
			         actionMethods : 'POST',
			         url: base+'/user/currentUserMeun',
			         reader: {
			             type: 'json',
			             root: 'data'
			         }
			     }
			});
			this.userMenuGridPanel = Ext.create('Ext.grid.Panel', {
				frame: true,
		    	title: login.i18n('dpap.login.app.favoriteMenu'),
		    	columnWidth : 1,
		    	height: 470,
		    	autoScroll: true,
		    	viewConfig: {
		            plugins: {
		            	ddGroup: 'resourceTree',
		                ptype: 'gridviewdragdrop',
		                dragText: login.i18n('dpap.login.app.resourceTree'),
		                containerScroll: true
		            }
		        },
			    store: Ext.data.StoreManager.lookup('currentDeptStore'),
			    columns: [
			        { header: login.i18n('dpap.login.resCode'), dataIndex: 'functionCode' },
			        { header: login.i18n('dpap.login.resName'),  dataIndex: 'functionName', flex: 1 }
			    ]
			});
		}
		return this.userMenuGridPanel;
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.items = [me.getResourceTreePanel(),me.getUserMenuGridPanel()];
		me.buttons = [{
	    	text: login.i18n('dpap.login.save'),
	    	handler : function(){
	    		var submitData = new Array(); 
	    		me.getUserMenuGridPanel().getStore().each(function(record,index,length){
	    			submitData.push(record.get('functionCode'));
	    		});
	    		//Ajax请求保存用户常用菜单设置
	    		Ext.Ajax.request({
	    			url : base+'/user/saveUserMenus',
	    			jsonData: submitData,
	    			//保存成功
	    			success : function(response, opts) {
	    				var result = Ext.decode(response.responseText),
	    					mainNav = Ext.getCmp('mainNav'),
	    					treeStore = mainNav.getStore(),
	    					userMenuNode = mainNav.getRootNode().getChildAt(0);
	    				Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'), result.msg, 'ok', 1000);
	    				//得到菜单树中的常用菜单节点，并进行数据的重新加载
	    				treeStore.load({ 
	    				    node: userMenuNode
	    				});
	    				me.close();
	    			},
	    			//保存失败
	    			exception : function(response, opts) {
	    				var result = Ext.decode(response.responseText);
	    				Ext.MessageBox.show({
	    	                title: login.i18n('dpap.login.messageTitle'),
	    	                msg: result.msg,
	    	                buttons: Ext.MessageBox.OK,
	    	                icon: Ext.MessageBox.ERROR
	    	            });
	    			}
	    		});
			}
	    }],
		me.listeners = {
			//恢复overflow:auto
			/*beforeclose:function(){
				document.documentElement.style.overflow='auto';
				document.body.style.overflow='auto';
			},*/
			boxready: function(){
				var userMenuGridPanel = me.getUserMenuGridPanel(),
					resourceTreePanel = me.getResourceTreePanel(),
		    		userMenuGridPanelDropTargetEl = userMenuGridPanel.body.dom,
		    		resourceTreePanelDropTargetEl = resourceTreePanel.body.dom,
		    		expend = function(parentNode,selectedRecord,parentCode,userMenuGridPanel){
						parentNode.expand(false,function(){
			            	var length = parentNode.childNodes.length,
			            	displayOrder = selectedRecord.get('displayOrder')==0?0:selectedRecord.get('displayOrder')-1,
			            	index = length;
			            	if(parentNode.data.leaf){
			            		parentNode.data.leaf = false;
			            	}
			            	var node = {
			            			'id': selectedRecord.get('functionCode'),
			            			'text': selectedRecord.get('functionName'),
			            			'leaf': true,
			            			'parentId': parentCode,
			            			'entity': selectedRecord.data
			            	};
			            	if(length>displayOrder){
			            		index = displayOrder;
			            	}
			            	parentNode.insertChild(index, node );
			            	parentNode.getChildAt(index).raw = node;
			            	userMenuGridPanel.getStore().remove(selectedRecord);			            	
			            });
					};
			    Ext.create('Ext.dd.DropTarget', userMenuGridPanelDropTargetEl, {
			        ddGroup: 'userMenuGrid',
			        notifyEnter: function(ddSource, e, data) {
			        	userMenuGridPanel.body.stopAnimation();
			        	userMenuGridPanel.body.highlight();
			        },
			        notifyDrop  : function(ddSource, e, data){
			            var node = ddSource.dragData.records[0],
			            	parentNode = node.parentNode,
			            	record;
			            if(!node.isLeaf()){
			            	Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'),login.i18n('dpap.login.nonLeafNodesOfTheNode'), 'error', 1000);
			            	return false;
			            }
			            record = Ext.ModelManager.create(node.raw.entity, 'Dpap.main.ResourceModel');
			            userMenuGridPanel.getStore().insert(0,record);
			            parentNode.removeChild(node);
			            return true;
			        }
			    });
			    Ext.create('Ext.dd.DropTarget', resourceTreePanelDropTargetEl, {
			        ddGroup: 'resourceTree',
			        notifyEnter: function(ddSource, e, data) {
			        	userMenuGridPanel.body.stopAnimation();
			        	userMenuGridPanel.body.highlight();
			        },
			        notifyDrop  : function(ddSource, e, data){
			            var selectedRecord = ddSource.dragData.records[0],
			            	treeStore = resourceTreePanel.getStore(),
			            	expendPath = selectedRecord.get('functionSeq');
			            	parentCode = selectedRecord.get('parentCode').functionCode;
			            var parentNode = treeStore.getNodeById(parentCode);
			            //如果异步树节点未加载完成，那么就要请求后台，把树展开到这个节点下
			            if(typeof(parentNode) == "undefined" || parentNode == null){
        					resourceTreePanel.expandPath(expendPath,'id','/',function(success, lastNode){
        						if(lastNode.get('id')==parentCode){
        							expend(lastNode,selectedRecord,parentCode,userMenuGridPanel);        							
        						}
        					});
			            }else{
			            	expend(parentNode,selectedRecord,parentCode,userMenuGridPanel);
			            }
			            return true;
			        }
			    });
			}
		},
		me.callParent([cfg]);
	}
});

Ext.define('Dpap.main.CurrentUserInfoForm',{
	extend: 'Ext.form.Panel',
	frame: true,
	defaultType : 'textfield',
	layout:'column',
	defaults: {
		readOnly : true,
		margin:'5 5 5 5',
		columnWidth:.5,
		labelWidth: 80
	},
	initComponent: function(config){
		var me = this,
				cfg = Ext.apply({}, config);
		me.items = [{
			name: 'empCode',
		    fieldLabel: login.i18n('dpap.login.jobNumber')
		},{
			name: 'empName',
		    fieldLabel: login.i18n('dpap.login.employeeName')
		},{
			name: 'deptName',
		    fieldLabel: login.i18n('dpap.login.orgName')
		},{
			name: 'position',
		    fieldLabel: login.i18n('dpap.login.position')
		},{
			name: 'offerTel',
		    fieldLabel: login.i18n('dpap.login.phoneCode')
		},{
			xtype: 'datefield',
			format: 'Y-m-d',
			name: 'inDate',
		    fieldLabel: login.i18n('dpap.login.entryDate')
		},{
			name: 'mobileNumber',
		    fieldLabel: login.i18n('dpap.login.customer.phoneNo')
		},{
			name: 'personEmail',
		    fieldLabel: login.i18n('dpap.login.electronicMailBox')
		},{
			name: 'password',
			inputType: 'password',
			readOnly: false,
			allowBlank: false,
		    fieldLabel: login.i18n('dpap.login.loginPassword')
		},{},{
			name: 'newPassword',
			allowBlank: false,
			inputType: 'password',
			readOnly : false,
		    fieldLabel: login.i18n('dpap.login.newPassword')
		},{
			name: 'checkPassword',
			allowBlank: false,
			inputType: 'password',
			readOnly : false,
		    fieldLabel: login.i18n('dpap.login.checkPassword')
		}];
	  	me.callParent([cfg]);
	}
});

//当前用户信息的窗口
Ext.define('Dpap.main.CurrentUserInfoWindow', {
	extend: 'Ext.window.Window',
	title: login.i18n('dpap.login.CurrentUserInfoWindowTitle'),
	height: 350,
	width: 600,
	modal:true,
	closeAction: 'hide',
	currentUserInfoForm: null,
	getCurrentUserInfoForm: function(){
		var me = this;
		if(me.currentUserInfoForm==null){
			me.currentUserInfoForm = Ext.create('Dpap.main.CurrentUserInfoForm');
		}
		return me.currentUserInfoForm;
	},
	updatePasswordButton : null,
	getUpdatePasswordButton:function(){
		var me = this;
		if(me.updatePasswordButton==null){
			me.updatePasswordButton = Ext.create('Ext.button.Button',{
				cls:'yellow_button',
				text: login.i18n('dpap.login.updatePassword'),
			    plugins: {
			        ptype: 'buttondisabledplugin',
			        seconds: 2
			    },
				handler: function(){
					var form = me.getCurrentUserInfoForm().getForm(),
						password = form.findField('password').getValue(),
						checkPassword = form.findField('checkPassword').getValue(),
						newPassword = form.findField('newPassword').getValue(),
						currentUser = DpapUserContext.getCurrentUser();
					//验证密码是否为空
					if(me.getCurrentUserInfoForm().getForm().isValid()){
						//两次输入的密码不一样
						if(newPassword!=checkPassword){
							Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'),login.i18n('dpap.login.twoPasswordNotSame'), 'error', 1000);
							return;
						}
						//Ajax验证登录密码是否正确
						Ext.Ajax.request({
							url: base+'/user/validatePassword',
							method: 'POST',
							params : {
								'newPassword' : password
							},
							success : function(response, opts) {
								//Ajax请求修改当前用户密码
								Ext.Ajax.request({
									url: base+'/user/updateCurrentUserPassword',
									method: 'POST',
									params : {
										'newPassword' : newPassword
									},
									success : function(response, opts) {
										var result = Ext.decode(response.responseText);
										//设置当前登录用户的密码信息
										login.currentUser.password = result.newPassword;
										Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'),login.i18n('dpap.login.updatePasswordSuc'), 1000);
										me.hide();
									},
									exception: function(response, opts){
										Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'),login.i18n('dpap.login.passwordError'), 'error', 1000);
									}
								});	
							},
							exception: function(response, opts){
								var result = Ext.decode(response.responseText);
								Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'),result.msg, 'error', 1000);
							}
						});	
					}else{
						Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'),login.i18n('dpap.login.mustInput'), 'error', 1000);
						return;
					}
				}
			});
		}
		return me.updatePasswordButton;
	},
	closeButton : null,
	getCloseButton:function(){
		var me = this;
		if(me.cancelButton==null){
			me.cancelButton = Ext.create('Ext.button.Button',{
				text: login.i18n('dpap.login.closeTabText'),
				handler: function(){
					me.hide();
				}
			});
		}
		return me.cancelButton;
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.items = [me.getCurrentUserInfoForm()];
		me.buttons = [me.getCloseButton(),'->',me.getUpdatePasswordButton()];
		me.callParent([cfg]);
	}
});

Ext.define('Dpap.main.CurrentUserDeptForm',{
	extend: 'Ext.form.Panel',
	frame: true,
	defaultType : 'textfield',
	layout:'column',
	defaults: {
		readOnly : true,
		margin:'5 5 5 5',
		columnWidth:.5,
		labelWidth: 80
	},
	initComponent: function(config){
		var me = this,
				cfg = Ext.apply({}, config);
		me.items = [{
			name : 'deptCode',
			fieldLabel : login.i18n('dpap.login.deptCode')
		},{
			name : 'deptName',
			fieldLabel : login.i18n('dpap.login.deptName')
		},{
			name : 'deptTelephone',
			fieldLabel : login.i18n('dpap.login.phone')
		},{
			name : 'deptFax',
			fieldLabel : login.i18n('dpap.login.fax')
		},{
			name: 'subsidiaryCode',
			fieldLabel : login.i18n('dpap.login.companyName')
		},{
			name : 'zipCode',
			fieldLabel : login.i18n('dpap.login.zipCode')
		},{
			name : 'address',
			fieldLabel : login.i18n('dpap.login.address'),
			xtype : 'textfield',
			columnWidth: 1
		},{
			name : 'deptDesc',
			fieldLabel : login.i18n('dpap.login.deptDesc'),
			xtype : 'textarea',
			columnWidth : 1
		} ];
	  	me.callParent([cfg]);
	}
});

//当前部门的窗口
Ext.define('Dpap.main.CurrentDeptWindow', {
	extend: 'Ext.window.Window',
	title: login.i18n('dpap.login.CurrentDeptWindowTitle'),
	height: 350,
	width: 600,
	modal:true,
	closeAction: 'hide',
	userDeptForm: null,
	getUserDeptForm: function(){
		if(this.userDeptForm==null){
			this.userDeptForm = Ext.create('Dpap.main.CurrentUserDeptForm');
		}
		return this.userDeptForm;
	},
	closeButton : null,
	getCloseButton:function(){
		var me = this;
		if(me.cancelButton==null){
			me.cancelButton = Ext.create('Ext.button.Button',{
				text: login.i18n('dpap.login.closeTabText'),
				handler: function(){
					me.hide();
				}
			});
		}
		return me.cancelButton;
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.items = [me.getUserDeptForm()];
		me.buttons = [me.getCloseButton()];
		me.callParent([cfg]);
	}
});

/*-----------------------------页面的头部中的工具栏--------------------------------*/
Ext.define('Dpap.main.TopButton',{
	extend: 'Ext.Button',
	height: 68,
	width: 100,
	icon : baseImagePath + 'b1.png',
	iconAlign : 'top',
	autoScroll: false
});

//xtype-通知按钮
Ext.define('Dpap.main.AnnounceLink', {
	extend: 'Dpap.main.TopButton',
	alias: 'widget.mannounceLink',
	icon : baseImagePath + 'b3.png',
	id:'M_announce',
	text: '<span style="font-size: 22px; font-weight: bold;">0 </span><b>'+login.i18n('dpap.login.app.noReadNotice')+'</b>',
	msgRemindWindow: null,
	getMsgRemindWindow: function() {
		if (Ext.isEmpty(this.msgRemindWindow)) {
			this.msgRemindWindow = Ext.create('Dpap.main.MsgRemindWindow');
		}
		
		return this.msgRemindWindow;
	},
	winFormSetting: null,
	getWinFormSetting: function() {
		if (Ext.isEmpty(this.winFormSetting)) {
			this.winFormSetting = Ext.create('Dpap.main.winFormSetting');
		}
		
		return this.winFormSetting;
	},
	handler: function(){
		scroll(0,0);
		var me = this;
		me.getMsgRemindWindow().show();
	},
	initComponent: function() {
		var me = this;
		me.listeners = {
			afterrender: function(cmp, eOpts) {
				me.msgTodoData();
			}
		};
		me.callParent();
    },
    msgTodoData:function (){
    	//更新数据
    	updateMsgData();
    	if(login.fun){
    		Ext.Ajax.request({
        		url : base+'/message/queryWinFormSettingInfo', 
        		params:{
        			'msgVo.winEntity.userCode': login.currentUser.empEntity.empCode
        		},
        		success : function(response) { 
        			var json = Ext.decode(response.responseText);   
        			var winEntity=json.data.winEntity;
        			var intervalTime=winEntity.intervalTime;  
        			login.msg.intervalTime = intervalTime;
        			var autoAlertFlag=winEntity.autoAlertFlag;
        			var showMessagePanel = Ext.getCmp('M_announce').getMsgRemindWindow().getShowMessagesPanel();
        			if(autoAlertFlag == 'Y'){
        				var toDoCount=showMessagePanel.getToDoMsgGrid().store.data.length;
        				var normalMsgCount=showMessagePanel.getNormalMsgGrid().store.data.length;
        				if(toDoCount > 0 || normalMsgCount > 0 ){
        					Ext.getCmp('M_announce').getMsgRemindWindow().show(); 
        					showMessagePanel.changeBottomAreaCheckBox(autoAlertFlag,intervalTime);
        				}
        			} 
        			showMessagePanel.changeCheckBox(autoAlertFlag);	
        		},
        		exception : function(response) {
        		  var json = Ext.decode(response.responseText); 
        		}
        	}); 
    	}
    }
});

//xtype-主页按钮
Ext.define('Dpap.main.HomeLink', {
	extend: 'Dpap.main.TopButton',
	alias: 'widget.mhomeLink',
	id:'M_home',
	icon : baseImagePath + 'home.png',
	text: login.i18n('dpap.login.homeLink'), 
	handler: function(arg){
		var tabPanel = Ext.getCmp('mainAreaPanel');
		scroll(0,0);
		var TabID='#T_';
		for (var j=2; j<arg['id'].length; j++){
			TabID=TabID+arg['id'][j];
		}
		var newActiveTab=tabPanel.child(TabID);
		tabPanel.setActiveTab(newActiveTab);
	}
});

//xtype-常用功能按钮
Ext.define('Dpap.main.NavConfigLink', {
	extend: 'Dpap.main.TopButton',
	alias: 'widget.mnavConfigLink',
	//id:'M_navConfig',
	icon : baseImagePath + 'b4.png',
	text: login.i18n('dpap.login.NavConfigWindowTitle'), 
	navConfigWindow : null,
	getNavConfigWindow : function(){
		var me = this;
		if(me.navConfigWindow == null){
			me.navConfigWindow = Ext.create('Dpap.main.NavConfigWindow');
		}
		
		return me.navConfigWindow;
	},
	handler: function(){
		scroll(0,0);
		var me = this;
		var tree = me.getNavConfigWindow().getResourceTreePanel(),
			treeStore = tree.getStore();
		treeStore.load({ 
		    node: treeStore.getRootNode()
		});
		me.getNavConfigWindow().getUserMenuGridPanel().getStore().load(function(records, operation, success) {
			if(success == true){
				me.getNavConfigWindow().show();				
			}
		});
		//console.log(window);
		//console.log(document.getElementsByTagName('HTML'));
		//console.log(Ext.getBody());
		//锁定背景overflow(跨浏览器：FF IE Chrome)
		//document.body.style.overflow='hidden';
		//document.documentElement.style.overflow='hidden';
		//document.getElementsByTagName('HTML').style.overflow='hidden';
	},
	autoScroll: false	
});

//xtype-用户信息按钮
Ext.define('Dpap.main.UserInfoSpace', {
	extend: 'Dpap.main.TopButton',
	alias: 'widget.muserinfospace',
	icon : baseImagePath + 'user.png',
	//id: 'userInfoSpace',
	text: DpapUserContext.getCurrentUserEmp().userName,
	//textAlign:'left',
	currentUserInfoWindow:null,
	getcurrentUserInfoWindow: function() {
		var me = this;
		if(me.currentUserInfoWindow == null){
			me.currentUserInfoWindow = Ext.create('Dpap.main.CurrentUserInfoWindow');
		}
		
		return me.currentUserInfoWindow;
	},
	handler: function(){
		scroll(0,0);
		var me = this;
		var	form = me.getcurrentUserInfoWindow().getCurrentUserInfoForm().getForm(),
			emp = DpapUserContext.getCurrentUserEmp(),
			dept = DpapUserContext.getCurrentUserDept();
		form.reset();
		form.setValues(emp);
		form.findField('deptName').setValue(dept.deptName);
		if(emp.entryDate!=null){
			form.findField('inDate').setValue(new Date(emp.entryDate));			
		}
		me.getcurrentUserInfoWindow().show();		    	
		//锁定背景overflow(跨浏览器：FF IE Chrome)
		/*document.body.style.overflow='hidden';
		document.documentElement.style.overflow='hidden';*/
	}
});


//xtype-当前时间
Ext.define('Dpap.main.DateTimeSpace', {
	extend: 'Dpap.main.TopButton',
	alias: 'widget.mdatetimespace',
	id: 'dateTimeSpace',
	icon: '',
	padding: '27 5 5 5',
	width: 130,
	html:constructDateTime(login.currentServerTime),
	//cls:'frametoolbar_content'
});

//xtype-登出按钮
Ext.define('Dpap.main.Logout', {
	extend: 'Dpap.main.TopButton',
	alias: 'widget.mlogout',
	//id:'M_logout',
	text:'注销',
	icon : baseImagePath + 'b6.png',
	autoScroll: false,
	handler: function(){
		Ext.MessageBox.confirm(login.i18n('dpap.login.logout'), login.i18n('dpap.login.sureLogout'),function(btn){
			if(btn=='yes'){
				Ext.Ajax.request({
					url : base+'/user/logout',
					success : function(response, opts) {
						window.location = base+'/user/login';
					},
					exception : function(response, opts) {
						var result = Ext.decode(response.responseText);
						Ext.MessageBox.show({
							buttons: Ext.MessageBox.OK,
			                msg: result.msg,
			                title: login.i18n('dpap.login.messageTitle'),
			                icon: Ext.MessageBox.ERROR
			            });
					}
				});
			}
		});
	}
});

Ext.define('Dpap.main.Toptoolbar', {
	extend: 'Ext.container.Container',
	//height:36,
	id:"frameTopToolbarPanel",
	bodyBorder:false,
	layout:'hbox',
	defaultItems: function() {
		return [
		        { xtype:'mannounceLink' },
		        { xtype:'mnavConfigLink' },
		        //{ xtype:'mhomeLink' },
				{ xtype:'muserinfospace' },
				//{ xtype:'mdatetimespace'},
				{ xtype:'mlogout' }
		];
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.items = [{ xtype: 'tbfill' }];//[ me.getLogoSpace() ];
		var itemArray = [];
		try {
			itemArray = getFrameToolbarItems();
		} catch (exception) {
			itemArray = me.defaultItems();
		}
		// 动态加载
		me.listeners = { "afterrender":function(thiscomponent, eOpts){
				thiscomponent.add(itemArray);
			}
		};
		me.callParent([cfg]);
	}
});

/*--------------------------------页面的头部---------------------------------------*/
/** 头panel 信息 * */
Ext.define('Dpap.main.topPanel', {
	extend : 'Ext.panel.Panel',
	id : "topPanel",
	height : 71,
	layout : 'hbox',
	cls : 'topPanel',
	topLeftPanel : null,
	getTopLeftPanel : function() {
		if (this.topLeftPanel == null) {
			this.topLeftPanel = Ext.create('Ext.panel.Panel',{
				bodyBorder : false,
				width : topLeftPanelWidth,
				height : 68,
				html : '<a href="#" class="frameTopPanel"></a>'
			});
		}
		return this.topLeftPanel;
	},
	topRightPanel : null,
	getTopRightPanel : function() {
		if (this.topRightPanel == null) {
			this.topRightPanel = Ext.create('Dpap.main.Toptoolbar',{
				flex: 1
			});
		}
		return this.topRightPanel;
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.items = [ me.getTopLeftPanel(), me.getTopRightPanel() ];
		me.callParent([ cfg ]);
	}
});

/*----------------------------------主导航-----------------------------------------*/
//定义一个功能菜单store
Ext.define('Dpap.main.NavStore', {
	extend: 'Ext.data.TreeStore',
	root: {
		id: '0',
		text: document.title,
		cls:lvl0Style,
		expanded: true,
		iconCls:'dpap_icons_emp'
	},
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : base+'/user/loadTree',
		reader : {
			type : 'json',
			root : 'nodes'
		}
	},
	listeners: {
	   	'beforeload': function(store, operation, eOpts){
	   		var userMenuNode = store.getRootNode().firstChild;
	   		if(!userMenuNode){
	   			return;
	   		}
	   		if(operation.params.node==userMenuNode.data.id){
	   			Ext.apply(operation.params, {
	   				'checkUserMenu' : true
	   			});	   			
	   		}
	    },
	    'load': function( store, node, records, successful, eOpts ){
	    	if(Ext.isEmpty(records)){
	    		var domId = 'nodeSpan'+'_'+node.data.id,
	    			dom = Ext.get('nodeSpan'+'_'+node.data.id);
	    		if(records==null || records.length==0){
	    			if(!Ext.isEmpty(dom)){
	    				dom.setStyle('display','inline-block');
	    				return;
	    			}
	    			node.data.text = "<span id='nodeSpan"+'_'+node.data.id+"' style='display: inline-block;width:40px;height:0px'>&nbsp;</span>"+node.data.text;
	    			//只有set loaded为ture才可以更新页面
	    			node.set('loaded', true);	    		
	    		}else{
	    			if(!Ext.isEmpty(dom)){
	    				dom.setStyle('display','none');
	    				return;
	    			}
	    		}		
	    	}
	    	if(node.isRoot()){
	    		node.appendChild({
	    			id: '99',
	    			iconCls: 'foss_icons_emp',
	    			cls: 'ye1-node-lvl1',
	    			leaf: true
	    		});
	    	}
	    }
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.callParent([cfg]);
	}
});

//定义一个导航树
Ext.define('Dpap.main.MainNav', {
	extend: 'Ext.tree.Panel',
	id:'mainNav',//菜单CSS Class以此id为准
	componentCls:'ye1',
	cls:'ye1',
	bodyCls:'ye1-body',
	width:186,
	store:Ext.create('Dpap.main.NavStore'),
	rootVisible:false,
	titleCollapse:true,
	animate:true,
	useArrows:true,
	autoScroll:false,
	resTextfield: null,
	getResTextfield: function(){
		var me = this;
		if(this.resTextfield==null){
			this.resTextfield = Ext.create('Ext.form.field.Text',{
				height:25,
				columnWidth: 0.6,
		        emptyText: login.i18n('dpap.login.writeFunName'),//'输入功能名',
		        margin:'0 0 0 19',
		        name: 'name',
		        regex:  /^(\w|[\u4E00-\u9FA5])*$/,
		        regexText: login.i18n('dpap.login.illegalCharacter'),
		        listeners: {
		        	specialkey: function(field, e){
	                    if (e.getKey() == e.ENTER) {
	                        me.getQueryButton().handler();
	                    }
	                }
		        }
			});
		}
		return this.resTextfield;
	},
	expandNodes: [],
	queryButton: null,
	getQueryButton: function(){
		var me = this;
		if(this.queryButton==null){
			this.queryButton = Ext.create('Ext.button.Button',{ 
		    	height:25,
		    	margin:'0 0 0 3',
		    	columnWidth: 0.3,
		    	text: login.i18n('dpap.login.queryMenu'),
		    	handler: function(){
		    		var field = me.getResTextfield(),
		    			queryValue = field.getValue();
		    		if(!field.validate()){
		    			Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'),login.i18n('dpap.login.illegalCharacter'), 'error', 1000);
		    			return;
		    		}
		    		if(!Ext.isEmpty(queryValue)){
		    			//Ajax请求得到所有查询到的节点全路径
		    			Ext.Ajax.request({
		    				url: base+'/user/queryTreePathForName',
		    				async: false,
		    				params : {
		    					'node' : queryValue
		    				},
		    				//得到路径成功
		    				success : function(response, opts) {
		    					var result = Ext.decode(response.responseText),
		    						view = me.getView(),
		    						pathList = result.pathList;
		    					me.expandNodes = [];
		    					me.collapseAll();
		    					if(pathList.length==0){
		    						Ext.ux.Toast.msg(login.i18n('dpap.login.messageTitle'),login.i18n('dpap.login.notFindMenu'), 'error', 1000);
		    						return;
		    					}
		    					for(var i=0;i<pathList.length;i++){
		    						me.expandPath(pathList[i],'id','/',function(success, lastNode){
		    							if(success){
		    								var nodeHtmlEl = view.getNode(lastNode),
		    									nodeEl = Ext.get(nodeHtmlEl);
		    								if(Ext.isEmpty(nodeEl)){
		    									me.expandNodes.push(lastNode);
		    									return;
		    								}
		    								var divHtmlEl = nodeEl.query('div')[0],
		    								    divEl = Ext.get(divHtmlEl);
		    								divEl.highlight("ff0000", { attr: 'color', duration: 5000 });
		    							}
		    						});	    						
		    					}
		    				},
		    				//得到路径失败
		    				exception : function(response, opts) {
		    					var result = Ext.decode(response.responseText);
		    					Ext.MessageBox.show({
		    						title: login.i18n('dpap.login.messageTitle'),
		    						msg: result.message,
		    						buttons: Ext.MessageBox.OK,
		    						icon: Ext.MessageBox.ERROR
		    					});
		    				}
		    			});
		    		}
		    	}
		    });
		}
		return this.queryButton;
	},
	initListeners : function(){
		var mainNav = this;
		mainNav.listeners = {
			//点击主菜单节点：
			itemclick: function(view, node, c, o, e) {			
				view.toggleOnDblClick = false;
				var tabPanel = Ext.getCmp('mainAreaPanel');
				if(node.isLeaf()) {	
					//生成tab相关数据：
					var href=node.raw.uri,
						tID='T_',//tab ID
						tLoc= base+href,
						hrefLength=href.length;
					for (var h=1; h<hrefLength; h++){
						if(href.charAt(h)!='/'){
							tID=tID+href.charAt(h);
						}else{
							tID=tID+'-';
						}
					}
					//tID = tID.substring(0, tID.lastIndexOf('.'));
					//console.log(tID);
					//确认该tab是否已经打开：
					var fTab=tabPanel.child('#'+tID);
					//var tabContent=null;
					//如果未打开则添加：
					if(fTab==null){
						addTab(tID, node.data.text, tLoc);
					}else{
						tabPanel.show(fTab);
						tabPanel.setActiveTab(fTab);
					}
				}else{
					view.toggle(node);
				}
			},
			afteritemexpand: function(node, index, item, eOpts){
				var expandNodes = mainNav.expandNodes,
					flag = true,
					view = mainNav.getView();
				if(expandNodes.length==0){
					return;
				}
				for(var i=0;i<expandNodes.length;i++){
					if(expandNodes[i]==null){
						flag = false;
						continue;
					}
					var nodeHtmlEl = mainNav.getView().getNode(expandNodes[i]),
						nodeEl = Ext.get(nodeHtmlEl);
					if(Ext.isEmpty(nodeEl)){
						continue;
					}
					var divHtmlEl = nodeEl.query('div')[0],
					    divEl = Ext.get(divHtmlEl);
					divEl.highlight("ff0000", { attr: 'color', duration: 5000 });
				}
				if(flag){
					mainNav.expandNodes = [];
				}
			}
		};
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.dockedItems = [{
		    xtype: 'toolbar',
		    dock: 'top',
		    layout: 'column',
		    id: 'mainNavToolbar',
		    items: [me.getResTextfield(),me.getQueryButton()]
		}];
		me.initListeners();
		me.callParent([cfg]);
	}
});

/*---------------------------------工作区------------------------------------------*/	
//定义一个标签页
Ext.define('Dpap.main.TabPanel', {
	extend: 'Ext.tab.Panel',
	id:"mainAreaPanel",
	columnWidth:1,
	plain:true,
	cls:'autoWidth',
	bodyCls:"tabFrame",
	componentCls:"mainArea",
	params: null,
	//右手overflow下拉插件
	plugins: [{
		ptype: 'tabscrollermenu',
		id:'OFB_menu',
		maxText  : 40,
		pageSize : 100
	},{
		ptype: 'tabclosemenu',
		closeTabText: login.i18n('dpap.login.closeTabText'),
		closeOthersTabsText: login.i18n('dpap.login.closeOthersTabsText'),
		closeAllTabsText: login.i18n('dpap.login.closeAllTabsText')
	},{
		ptype: 'tabpanelfullscreen'
	}],
	items:[{
		title: login.i18n('dpap.login.homeLink'), 
		tabConfig:{width:150}, 
		closable:false,
		id:'T_home', 
		layout:'fit',
		cls: 'autoHeight',
		bodyCls: 'autoHeight'
			//,
//		loader: {
//			scripts: true,
//			autoLoad: true,
//			url: login.realPath('home')
//		}
	}],
	initListeners : function(){
		var me = this;
		me.listeners = {
			//关闭前清内存里相应项目
			beforeremove: function(panel, tab){
				//清理组件
				var tabId = tab.id,
					conId = tabId+'_content',
					toolbarId = tabId+'_toolbar';
				//判断是否存在tab的主页面，如果不存在就无需清理
				if(Ext.getCmp(conId)==null){
					return;
				}
				//清空ComponentManager内的注册项
				var cmpArray = Ext.getCmp(conId).removeAll();
				if(Ext.getCmp(toolbarId)!=null){
					var cmpArrayToolbar = Ext.EventManager.removeAll(toolbarId);
					cmpArray = Ext.Array.merge(cmpArray,cmpArrayToolbar);
				}
				//清空store
				for(var i=0;i<cmpArray.length;i++){
					if(cmpArray[i]){
						if(cmpArray[i].store){
							cmpArray[i].store.destroyStore();
							Ext.data.StoreManager.unregister(cmpArray[i].store);
						}						
					}
				}
				cmpArray = null;
				Ext.ComponentManager.unregister(Ext.getCmp(conId));
				if(Ext.getCmp(toolbarId)!=null){
					Ext.ComponentManager.unregister(Ext.getCmp(toolbarId));
				}
				
				//清理模块下变量和方法
				var lineIndex = tabId.lastIndexOf("-"),
					moduleName = tabId.substring(2, lineIndex),
					endIndex = tabId.length,
					pointIndex = tabId.lastIndexOf("."),
					module = eval(moduleName);
				if(pointIndex!=-1){
					endIndex = pointIndex;
				}
				var childmoduleName = conId.substring(lineIndex+1, endIndex);
				if(childmoduleName == "index") {
					if(module != null) {
						 for(var prop in module) {
							 if(!Ext.isObject(prop)) {
								 try{
									 if(module[prop].id){
										 cleanComponent(module[prop].id);
									 }else{
										 module[prop] = null;
									 }
									 delete module[prop];
								 }
								 catch(err){
									 module[prop] = null;
									 delete module[prop];
								 }
							 }
						 }
						 module = null;
						 delete window[moduleName];
					}
				} else {
					var childmoduleName = childmoduleName.slice(0, -5),childmodule = module[childmoduleName];
					 for(var prop in childmodule) {
						 if(!Ext.isObject(prop)) {
							 try{
								 if(childmodule[prop].id){
									 cleanComponent(childmodule[prop].id);
								 }else{
									 childmodule[prop] = null;
								 }
								 delete childmodule[prop];
							 }
							 catch(err){
								 childmodule[prop] = null;
								 delete childmodule[prop];
							 }
						 }
					 }
					 childmodule = null;
					 delete module[childmoduleName];
				}
			}
		};
	},
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.initListeners();
		me.callParent([cfg]);
	}
});

// ------------------------ 首次登录待办弹出框配置 START ------------------------------
// 待办事项Model
Ext.define('Dpap.main.ToDoMsgModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'businessType', // 待办类型
		type : 'string'
	}, {
		name : 'noDealNum', // 未处理数量
		type : 'int'
	} ]
});
// 站内消息Model
Ext.define('Dpap.main.InstationMsgModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'msgType', // 待办类型
		type : 'string'
	}, {
		name : 'noDealNum', // 未处理数量
		type : 'int'
	} ]
});

// 待办数据Store
Ext.define('Dpap.main.ToDoMsgStore', {
	extend : 'Ext.data.Store',
	model : 'Dpap.main.ToDoMsgModel'
});

// 站内消息Store
Ext.define('Dpap.main.NormalInstationMsgStore', {
	extend : 'Ext.data.Store',
	model : 'Dpap.main.InstationMsgModel'
});

// 全网消息Store
//Ext.define('Dpap.main.NetInstationMsgStore', {
//	extend : 'Ext.data.Store',
//	model : 'Dpap.main.InstationMsgModel'
//});

// 待办表格
Ext.define('Dpap.main.ToDoMsgGrid', {
	extend : 'Ext.grid.Panel',
	title : login.i18n('dpap.login.app.toDoItem'),
	store : Ext.create('Dpap.main.ToDoMsgStore'),
	columnWidth : 1,
	stripeRows : true,
	columnLines : true,
	collapsible : false,
	bodyCls : 'autoHeight',
	frame : true,
	// 增加表格列的分割线
	cls : 'autoHeight',
	autoScroll : true,
	height : 'autoHeight',
	listeners:{
		itemclick:function(_this, _record, _item, _index, _e, _eOpts ){
			login.msg.bisType = _record.get('businessType');
			openMsgMenu('T_message-toDoMsgInit',login.i18n('dpap.login.app.toDoItem'),base+'/message/toDoMsgInit',null); 
		}
	},
	columns : [ {
		text : login.i18n('dpap.login.app.businessType'),
		align : 'center',
		flex : 1,
		dataIndex : 'businessType',
		renderer:function(value){
			var displayField = Deppon.Dictionary.getDictNameByCode(value,'DPAP_MSG_BUSITYPE');
			return displayField;
		}
	}, {
		text : login.i18n('dpap.login.app.noDealNum'),
		align : 'center',
		flex : 1,
		dataIndex : 'noDealNum'
	} ],
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.callParent([ cfg ]);
	}
});

// 普通消息
Ext.define('Dpap.main.NormalInstationMsgGrid', {
	extend : 'Ext.grid.Panel',
	title : login.i18n('dpap.login.app.instationMsg'),
	store : Ext.create('Dpap.main.NormalInstationMsgStore'),
	columnWidth : 1,
	stripeRows : true,
	columnLines : true,
	collapsible : false,
	bodyCls : 'autoHeight',
	frame : true,
	// 增加表格列的分割线
	cls : 'autoHeight',
	autoScroll : true,
	height : 'autoHeight',
	listeners:{
		itemclick:function(_this, _record, _item, _index, _e, _eOpts ){
			login.msg.msgType = _record.get('msgType');
			openMsgMenu('T_message-instationMsgInit',login.i18n('dpap.login.app.instationMsg'),base+'/message/instationMsgInit',null); 
		}
	},
	columns : [ {
		text : login.i18n('dpap.login.app.msgType'),
		align : 'center',
		flex : 1,
		dataIndex : 'msgType',
		renderer:function(value){
			var displayField = Deppon.Dictionary.getDictNameByCode(value,'DPAP_MSG_TYPE');
			return displayField;
		}
	}, {
		text : login.i18n('dpap.login.app.noReadNum'),
		align : 'center',
		flex : 1,
		dataIndex : 'noDealNum'
	} ],
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.callParent([ cfg ]);
	}
});
//更新弹出框数据
function updateMsgData(){
	if(login.fun){
		Ext.Ajax.request({
			url : base+'/message/queryMsgTotal', 
			async: false,
			success : function(response) {
				var result = Ext.decode(response.responseText);
				
				var normalMsgList = result.data.normalMsgList;
				var msgVo=result.data;
				//var netMsgList = msgVo.netMsgList;
				var toDoMsgList = msgVo.toDoMsgList;
				
				var noDealTotal=msgVo.noDealtotal;
				
				var showMessagePanel = Ext.getCmp('M_announce').getMsgRemindWindow().getShowMessagesPanel();
				if(!Ext.isEmpty(toDoMsgList)){
					showMessagePanel.getToDoMsgGrid().store.loadData(toDoMsgList);
				}
				if(!Ext.isEmpty(normalMsgList)){
					showMessagePanel.getNormalMsgGrid().store.loadData(normalMsgList);
				}
				var text = '<span style="font-size: 22px; font-weight: bold;">'+noDealTotal+'</span><b>'+login.i18n('dpap.login.app.noReadNotice')+'</b>';
				var announce=Ext.getCmp('M_announce');
				if(announce){
					announce.setText(text);
				}else{
					setTimeout(function(){
						var announce=Ext.getCmp('M_announce');
						if(announce){
							announce.setText(text);
						}
			   	 	}, 1000 )
				}
			},
			exception : function(response) {
				var result = Ext.decode(response.responseText);
				Ext.MessageBox.alert(login.i18n('dpap.login.app.prompt'), result.msg);
			}
		});
	}
}
//弹出框口时间间隔设置页面
Ext.define('Dpap.main.opAlertSettingForm',{
	extend:'Ext.form.Panel',
	defaults:{
		margin :'5 5 5 0', 
		colspan :1 
	},
	defaultType:'textfield',
	layout:{
		type :'table',
		columns : 2
	},
	items:[{	
		name:'id',
		hidden:true
	},{	
	    xtype: 'numberfield',
		name:'intervalTime',
		fieldLabel:login.i18n('dpap.login.app.popupInterval'), 
		value:10,
		allowBlank: false,
		allowDecimals:false, 
        minValue: 8 ,
        maxValue: 90
	},{
		xtype: 'displayfield',
		fieldLabel: '&nbsp;'+login.i18n('dpap.login.app.min'),
		labelSeparator:'',
		allowBlank: true
	},{
 		xtype:'radiogroup',
 		fieldLabel:login.i18n('dpap.login.app.whetherAutoAlert'),
 		name:'autoAlertFlag',  
 		allowBlank:true,
 		colspan:2,
 		defaultType:'radio',
 		layout:'table',
 		isFormField: true,
 		items:[{
 			boxLabel:login.i18n('dpap.login.app.yes'),
 			name:'autoAlertFlag',
 			inputValue:'Y' 
 		},{
 			boxLabel:login.i18n('dpap.login.app.no'),
 			name:'autoAlertFlag',
 			inputValue:'N' 
 		}]
  	 }],
  	  bbar: [{
		  text:login.i18n('dpap.login.app.cancel'),
		  handler: function(){
			  Ext.getCmp('M_announce').getWinFormSetting().close();
		  }
	  	},'->',{
			text:login.i18n('dpap.login.app.okButton'),
			handler:function(){
				var form = this.up('form').getForm();   
				if(form.isValid()){
					var id=form.findField("id").getValue();  
					var autoAlertFlag=form.getValues().autoAlertFlag; 
					var intervalTime=form.findField("intervalTime").getValue();  
					var userCode=login.currentUser.empEntity.empCode;
					 //保存弹出框设置项 
					var params={
							'msgVo.winEntity.id' : id,
							'msgVo.winEntity.userCode' : userCode,
							'msgVo.winEntity.intervalTime' : intervalTime,
							'msgVo.winEntity.autoAlertFlag' : autoAlertFlag
					};
			        Ext.Ajax.request({
			            url : base+'/message/updateSetting',
			            params:params,
			            success : function(response) { 
			              var json = Ext.decode(response.responseText); 
			              Ext.MessageBox.alert(login.i18n('dpap.login.app.prompt'), json.message);
			              Ext.getCmp('M_announce').getMsgRemindWindow().getShowMessagesPanel().changeBottomAreaCheckBox(autoAlertFlag,intervalTime);
			              Ext.getCmp('M_announce').getWinFormSetting().close();
			            },
			            exception : function(response) {
			              var json = Ext.decode(response.responseText);
			              Ext.MessageBox.alert(login.i18n('dpap.login.app.prompt'), json.msg);
			              Ext.getCmp('M_announce').getWinFormSetting().close();
			            }
			        }); 
				}else{
					 Ext.MessageBox.alert(login.i18n('dpap.login.app.prompt'),login.i18n('dpap.login.app.inputParamerIllegal'));
				}
			}
	  	}]
});

Ext.define('Dpap.main.winFormSetting', {
	extend: 'Ext.window.Window', 
	title: login.i18n('dpap.login.app.toDoAndSystemMsgConfig'),
	width: 350,
	modal:true,
	closeAction: 'hide',
	settingForm:null,
	getSettingForm: function () {
		if(this.settingForm == null){
			this.settingForm = Ext.create("Dpap.main.opAlertSettingForm");
		}
		return this.settingForm;
	},
	bindData: function(autoAlertFlag,id,intervalTime){
		var form = this.getSettingForm().getForm(),
      		groupRadio=form.findField('autoAlertFlag').items;
      	for(var i=0;i<groupRadio.items.length;i++){
      		if(groupRadio.items[i].inputValue==autoAlertFlag){
      			groupRadio.items[i].setValue(true);
      		}else if(groupRadio.items[i].inputValue==autoAlertFlag){
      			groupRadio.items[i].setValue(true);
      		}
      	}
      	form.findField('id').setValue(id);
      	form.findField("intervalTime").setValue(intervalTime);  
	},
	listeners:{
    	beforeshow:function(me){
    		me.getSettingForm().getForm().reset(); 
    	}
    },
	constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.items = [
		    me.getSettingForm()    
		];
		me.callParent([cfg]);
	}
});
/** 获取消息列表，并弹出消息窗口 */

Ext.define('Dpap.main.ShowMessagesPanel',{
	extend : 'Ext.panel.Panel',
	title : '',
	columnWidth : 1,
	cls : 'autoHeight',
	bodyCls : 'autoHeight',
	frame : false,
	toDoMsgGrid : null,
	getToDoMsgGrid : function() {
		if (this.toDoMsgGrid == null) {
			this.toDoMsgGrid = Ext
					.create('Dpap.main.ToDoMsgGrid');
		}
		return this.toDoMsgGrid;
	},
	normalMsgGrid : null,
	getNormalMsgGrid : function() {
		if (this.normalMsgGrid == null) {
			this.normalMsgGrid = Ext
					.create('Dpap.main.NormalInstationMsgGrid');
		}
		return this.normalMsgGrid;
	},
	netMsgGrid : null,
	changeBottomAreaCheckBox:function(value,intervalTime){
		var autoFlag=true;
		if(value=='Y'){
			autoFlag=false;
		} 
		login.msg.remindInterval(value,intervalTime);
		login.msg.intervalTime=intervalTime;
		this.getBottomArea().items.items[0].setValue(autoFlag);
	},
	changeCheckBox:function(value){
		var autoFlag=true;
		if(value=='Y'){
			autoFlag=false;
		} 
		this.getBottomArea().items.items[0].setValue(autoFlag);
	},
	bottomArea : null,
	openAlertSetting: function (){
		var userCode=login.currentUser.empEntity.empCode;
		 //保存弹出框设置项
		var params={'msgVo':{'winEntity':{'userCode':userCode}}};
		if(login.fun){
			 Ext.Ajax.request({
			      url : base+'/message/queryWinFormSettingInfo',
			      jsonData:params,
			      success : function(response) { 
			      	var json = Ext.decode(response.responseText); 
			      	var form=Ext.getCmp('M_announce').getWinFormSetting().getSettingForm().getForm();//得到form对象
			      	
			      	//从后台获取值
			       	var winEntity=json.data.winEntity;
			      	var id=winEntity.id;
			      	var autoAlertFlag=winEntity.autoAlertFlag;
			      	var intervalTime=winEntity.intervalTime,
			      		win = Ext.getCmp('M_announce').getWinFormSetting();
			      	login.msg.intervalTime=intervalTime;
			      	login.msg.remindInterval(autoAlertFlag,login.msg.intervalTime);
			      	//窗口弹出
			      	win.show();
			      	win.bindData(autoAlertFlag,id,intervalTime);
			      	
			      },
			      exception : function(response) {
			        var json = Ext.decode(response.responseText);
			        Ext.MessageBox.alert(login.i18n('dpap.login.app.prompt'), json.msg);
			      } 
			  }); 
		}
	},
	getBottomArea : function() {
		var me = this;
		if (me.bottomArea == null) {
			me.bottomArea = Ext.create('Ext.panel.Panel',{
				frame : false,
				columnWidth : 1,
				height : 38,
				layout : 'column',
				items: [{
							xtype : 'checkboxfield',
							width : 200,
							boxLabel : login.i18n('dpap.login.app.noAutoAlertMsg'),
							name : 'notAutoMessage',
							inputValue : 'N',
							listeners:{
								change:function(_this, newValue, oldValue, eOpts){
									var autoAlertFlag='Y';
									if(newValue){
										autoAlertFlag='N';
									} 
									login.msg.remindInterval(autoAlertFlag,login.msg.intervalTime);
									 //保存弹出框设置项 
									var params={
											'msgVo.winEntity.autoAlertFlag' : autoAlertFlag
									};
							        Ext.Ajax.request({
							            url : base+'/message/uptAutoAlertFlag',
							            params:params,
							            success : function(response) { 
							              var json = Ext.decode(response.responseText); 
							            },
							            exception : function(response) {
							              var json = Ext.decode(response.responseText);
							            }
							        });  
								}
							}
							
						},{
							xtype : 'container',
							width : 240,
							html: '&nbsp;'
						},{
							xtype : 'container',
							margin:'5 0 0 0',
							id : 'alert_settings',
							width : 80,
							html:'<span style = "text-decoration:underline;color:#3d74b7">'+login.i18n('dpap.login.app.advancedSettings')+'</span>',
							listeners : {
						         render : function() {
						        	 Ext.fly(this.el).on('click',
						        	    function(e, t) {
						        		 	me.openAlertSetting();
						        	    });
						         }
						    }
						},{
							xtype : 'button',
							text : login.i18n('dpap.login.app.okButton'),
							width : 100,
							cls : 'yellow_button',
							handler : function() {
								 Ext.getCmp('M_announce').getMsgRemindWindow().hide();
							}
						}]
					});
		}
		return this.bottomArea;
	},
	bodyStyle : 'padding:0px 0px 0px 2px',
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.items = [ me.getToDoMsgGrid(),
				me.getNormalMsgGrid(),// me.getNetMsgGrid(),
				me.getBottomArea() ];
		me.callParent([ cfg ]);
	}
});

// 当用户第一次登时弹出消息提醒窗口
Ext.define('Dpap.main.MsgRemindWindow', {
	extend : 'Ext.Window',
	closeAction : 'hide',
	title : login.i18n('dpap.login.app.msgRemind'),
	modal : true,
	height : 'autoHeight',
	width : 700,
	resizable : false,
	layout : 'column',
	showMessagesPanel : null,
	getShowMessagesPanel : function() {
		if (this.showMessagesPanel == null) {
			this.showMessagesPanel = Ext.create('Dpap.main.ShowMessagesPanel');
		}
		return this.showMessagesPanel;
	},
	listeners:{
		beforeshow:function(){
			updateMsgData();
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.items = [ me.getShowMessagesPanel() ];
		me.callParent([ cfg ]);
	}
});

//动态设置弹出间隔
login.msg.remindInterval=function(autoFlag,intervalTime){
	if(autoFlag=='Y'){
		if(!Ext.isEmpty(login.msg.intervalId)){
			window.clearInterval(login.msg.intervalId);
		}
		var showMessagePanel = Ext.getCmp('M_announce').getMsgRemindWindow().getShowMessagesPanel();
		
		var toDoCount=showMessagePanel.getToDoMsgGrid().store.data.length;
		var normalMsgCount=showMessagePanel.getNormalMsgGrid().store.data.length;
		//var netMsgCount=showMessagePanel.getNetMsgGrid().store.data.length;
		
		if(toDoCount > 0 || normalMsgCount > 0){
			var minIntervalTime=1000*60*intervalTime;
			login.msg.intervalId = setInterval(function(){
				Ext.getCmp('M_announce').getMsgRemindWindow().show(); 
	   	 	},minIntervalTime);
		}  
	}else{
		if(!Ext.isEmpty(login.msg.intervalId)){
			//动态清除弹出的设置
			window.clearInterval(login.msg.intervalId);
		}
	}
}
//  ------------------------ 首次登录待办弹出框配置 END ------------------------------------

Ext.define('Dpap.ux.TabPanelFullScreenPlugin', {
	alias: ['plugin.tabpanelfullscreen'],
	constructor: function(config) {
		config = config || {};
		Ext.apply(this, config);
	},
	//全屏模式下主导航关闭控制：
	fullScreenNav : function(c){
		//记录点击点
		var mouseX=c.browserEvent.clientX;
		var mouseY=c.browserEvent.clientY;
		//记录页面srollTop
		var sTop=window.pageYOffset|document.documentElement.scrollTop;
		//横向186未固定导航区域
		var areaX=186;
		//计算纵向区域,32为root本身的高度,勿做改动。
		var areaY=Ext.getCmp('mainNav').el.getY()-sTop+Ext.getCmp('mainNav').getHeight()+32;
		//如果超出区域则上收菜单
		if(mouseX>areaX||mouseY>areaY){
			Ext.getCmp('mainNav').getRootNode().collapse();
		}else{
			Ext.getCmp('mainNav').getRootNode().expand();
		}
	},
	afterFullScreen: function(){
		var me = this;
		scroll(0,0);
		//找取header备用
		var tP=Ext.getCmp('topPanel');
		//如果切换到主页
		if (isFullScreen){
			tP.animate({
			   to:{
				   height:0
			   }
		   });
		//一般页面：
		}else{
			//header隐藏：
			tP.animate({
			   to:{
				   height:71
			   }
		   });
		}
	},
	//切换及激活标签页前进行的调整：
	doFullScreen: function(tab){
		var me = this,
			tabPanel = me.tabPanel,
			tabBar = me.tabBar,
			mN=Ext.getCmp('mainNav');
		me.afterFullScreen(tab);
		//主导航宽度归0，使tabPanel左移动（主导航依然显示，因为底层element overflow=visible）
		mN.el.dom.style.width=0;
		//上收到root
		mN.getRootNode().collapse();
		tabPanel.setWidth(tabPanel.getWidth()+186);
		tabPanel.doLayout();
		//tabBar向右移动
		tabBar.addClass('tabbarDisplace');
		//主工作区container float定义清空，用浏览器默认。
		Ext.getCmp('container').addClass('clearFloat');
		//添加fullScreenNav handler，在全屏模式下点击主导航外区域会自动关闭主导航。
		Ext.getBody().on('click', me.fullScreenNav);
		//修改查询工具条的上偏移
		//Ext.getCmp('mainNavToolbar').el.dom.style.paddingTop='0px';
	},
	exitFullScreen: function(tab){
		var me = this,
			tabPanel = me.tabPanel,	
			tabBar = me.tabBar,
			mN=Ext.getCmp('mainNav');
		me.afterFullScreen(tab);
		//移除fullScreenNav handler以及相关listener
		Ext.getBody().removeListener('click', me.fullScreenNav);
		//重设主导航宽度
		mN.setWidth(186);
		//mN.doLayout;
		//主导航展开
		mN.getRootNode().expand();
		//重设tabPanel宽度（因ExtJS自身的宽度计算机制，这里不做减法。
		tabPanel.setWidth(tabPanel.getWidth());
		tabPanel.doLayout();
		//tabBar归位
		tabBar.removeCls('tabbarDisplace');
		//主工作区归位
		Ext.getCmp('container').removeCls('clearFloat');
		//修改查询工具条的上偏移
		//Ext.getCmp('mainNavToolbar').el.dom.style.paddingTop='5px';
	},
	fullScreenButton: null,
	getFullScreenButton: function(){
		var me = this;
		if(me.fullScreenButton==null){
			me.fullScreenButton = Ext.create('Ext.Button', {
				text: '显示全屏',
				handler: function(){
					var tab = me.tabPanel.getActiveTab();
					if(isFullScreen){
						isFullScreen = false;
						me.exitFullScreen(tab);
						me.getFullScreenButton().setText('显示全屏');
					}else{
						isFullScreen = true;
						me.doFullScreen(tab);
						me.getFullScreenButton().setText('退出全屏');
					}
				}
			});
		}
		return me.fullScreenButton;
	},
	init: function(tabPanel) {
		var me = this;
        Ext.apply(tabPanel, me.parentOverrides);
        me.tabPanel = tabPanel;
		me.tabBar = me.tabPanel.tabBar;
		me.tabBar.add([Ext.widget('tbfill'),me.getFullScreenButton()]);
	}
});

/*--------------------------------框架整合-----------------------------------------*/
Ext.application({
    name: document.title,
    appFolder: '..',
    launch: function() {
		Ext.QuickTips.init();
		//页面的头部
		var topPanel = Ext.create('Dpap.main.topPanel'),
			//导航树
			mainNav = Ext.create('Dpap.main.MainNav'),
			//工作区标签页
			tabPanel = Ext.create('Dpap.main.TabPanel'),
			//页脚
			footer=Ext.create('Ext.panel.Panel',{
				id:'footer',
				columnWidth:1,
				html:'Copyright © ' + login.currentServerTime.getFullYear() + ' HBHK科技有限公司. All rights reserved.',
				height:30
			 });
        Ext.create('Ext.container.Viewport', {
			id:'dpViewport',
			listeners:{
				//渲染结束后开始右上时间更新。每一秒更新一次。
				afterrender : function(){
					var dateTimeSpace =  Ext.getCmp('dateTimeSpace');
					if(!Ext.isEmpty(dateTimeSpace)){
						setInterval(
								function(){
									Ext.getCmp('dateTimeSpace').update(constructDateTime(login.currentServerTime));
									login.currentServerTime = new Date((login.currentServerTime).getTime()+1000);
								},1000
						);						
					}
				}
			},
			minWidth:1220,
			autoScroll: false,
			items: [topPanel,{
				id: 'w_stripe',
				height:12,
				html: '<div class="dpap_icon_wstripeNavTop"></div>'
					
			},{	
				xtype:'panel',
				id:'col_cont',
				cls:'autoHeight',
				bodyCls:'autoHeight',
				layout: 'column',
				items:[
			   		mainNav,{	
		   			xtype:'panel',
					id:'container',
					cls:'autoHeight',
					bodyCls:'autoDim',
					columnWidth:1,
					items: [tabPanel,footer]
				}]
			
			}]
        });
		//添加滚动事件监控(因浏览器兼容缘故，window和document上都要添加)
		document.onscroll=onScroll;
		window.onscroll=onScroll;
		scroll(0,0);
    }
});