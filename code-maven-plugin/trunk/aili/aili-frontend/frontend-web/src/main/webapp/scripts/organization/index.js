/** 部门树ROOT ID */
organization.DEPT_ROOT_ID = 'hbhk';
/** 主页面的ID */
organization.CONTENT_ID = 'T_organization-index_content';

/** 部门的Model */
Ext.define('Dpap.organization.DepartmentModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'deptCode'
	}, {
		name : 'deptName'
	}, {
		name : 'principalNo'
	}, {
		name : 'deptTelephone'
	}, {
		name : 'deptFax'
	}, {
		name : 'parentEntity'
	}, {
		name : 'subsidiaryCode'
	}, {
		name : 'zipCode'
	}, {
		name : 'address'
	}, {
		name : 'zipCode'
	}, {
		type : 'boolean',
		name : 'status'
	}, {
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'date',
		name : 'beginTime'
	}, {
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'date',
		name : 'endTime'
	}, {
		type : 'int',
		name : 'displayOrder'
	}, {
		type : 'int',
		name : 'deptLevel'
	}, {
		type : 'boolean',
		name : 'isLeaf'
	}, {
		name : 'deptDesc'
	}, {
		name : 'uumsId'
	}, {
		name : 'uumsParentId'
	}, {
		name : 'createUser'
	}, {
		name : 'createDate',
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'date'
	}, {
		name : 'modifyUser'
	}, {
		name : 'modifyDate',
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'date'
	} ]
});

/** 员工信息MODEL */
Ext.define('Dpap.organization.EmployeeModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'string'
	}, {
		name : 'deptEntity',
		type : 'Object'
	}, {
		name : 'empCode',
		type : 'string'
	}, {
		name : 'empName',
		type : 'string'
	},{
		name : 'pinyin',
		type : 'string'
	}, {
		name : 'gender',
		type : 'boolean'
	}, {
		name : 'position',
		type : 'string'
	}, {
		name : 'birthDate',
		type : 'Date',
		convert : dateConvert,
		defaultValue : new Date()
	}, {
		name : 'status',
		type : 'boolean'
	}, {
		name : 'inDate',
		type : 'Date',
		convert : dateConvert,
		defaultValue : new Date()
	}, {
		name : 'outDate',
		type : 'Date',
		convert : dateConvert,
		defaultValue : new Date()
	}, {
		name : 'offerTel',
		type : 'string'
	}, {
		name : 'offerAddress',
		type : 'string'
	}, {
		name : 'offerZipCode',
		type : 'string'
	}, {
		name : 'offerEmail',
		type : 'string'
	}, {
		name : 'mobileNumber',
		type : 'string'
	}, {
		name : 'homeTel',
		type : 'string'
	}, {
		name : 'homeAddress',
		type : 'string'
	}, {
		name : 'homeZipCode',
		type : 'string'
	}, {
		name : 'personEmail',
		type : 'string'
	}, {
		name : 'workExp',
		type : 'string'
	}, {
		name : 'remark',
		type : 'string'
	}, {
		name : 'createUser',
		type : 'string'
	}, {
		name : 'createDate',
		type : 'Date',
		convert : dateConvert,
		defaultValue : new Date()
	}, {
		name : 'modifyUser',
		type : 'string'
	}, {
		name : 'modofyDate',
		type : 'Date',
		convert : dateConvert,
		defaultValue : new Date()
	}]
});

/**
 * 员工信息查询form表单
 */
Ext.define('Dpap.organization.EmployeeForm', {
	extend : 'Ext.form.Panel',
	frame: true,
	defaultType : 'textfield',
	layout : {
		type : 'table',
		columns : 2
	},
	defaults : {
		margin : '5 20 5 10',
		labelWidth : 130,
		colspan : 1,
		readOnly: true
	},
	items : [ {
		fieldLabel : organization.i18n('dpap.organization.Department.id'),
		name : 'id',
		hidden : true
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.EMPCODE'),
		name : 'empCode',
		xtype : 'textfield'
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.EMPNAME'),
		name : 'empName',
		xtype : 'textfield'
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.GENDER'),
		name : 'gender',
		xtype : 'textfield'
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.deptId'),
		name : 'deptId',
		xtype : 'textfield'
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.POSITION'),
		name : 'position',
		xtype : 'textfield'
	}, {
		xtype : 'datefield',
		name : 'birthDate',
		fieldLabel : organization.i18n('dpap.organization.Employee.DATE'),
		format : 'Y-m-d'
	}, {
		name : 'status',
		fieldLabel : organization.i18n('dpap.organization.Employee.STATUS'),
		xtype : 'textfield'
	}, {
		xtype : 'datefield',
		name : 'inDate',
		fieldLabel : organization.i18n('dpap.organization.Employee.INDATE'),
		format : 'Y-m-d'
	}, {
		xtype : 'datefield',
		name : 'outDate',
		fieldLabel : organization.i18n('dpap.organization.Employee.OUTDATE'),
		format : 'Y-m-d'
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.OTEL'),
		name : 'offerTel',
		xtype : 'textfield'
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.OADDRESS'),
		name : 'offerAddress',
		xtype : 'textfield'
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.OZIPCODE'),
		name : 'offerZipCode',
		xtype : 'textfield'
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.OEMAIL'),
		name : 'offerEmail',
		xtype : 'textfield'
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.MOBILENO'),
		name : 'mobileNumber',
		xtype : 'textfield'
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.HTEL'),
		name : 'homeTel',
		xtype : 'textfield'
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.HADDRESS'),
		name : 'homeAddress',
		xtype : 'textfield'
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.HZIPCODE'),
		name : 'homeZipCode',
		xtype : 'textfield'
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.PEMAIL'),
		name : 'personEmail',
		xtype : 'textfield'
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.WORKEXP'),
		name : 'workExp',
		xtype : 'textarea',
		columnWidth : 1
	}, {
		fieldLabel : organization.i18n('dpap.organization.Employee.REMARK'),
		name : 'remark',
		xtype : 'textarea',
		columnWidth : 1
	} ]
});
/**
 * 员工信息查询窗口
 */
Ext.define('Dpap.organization.EmployeeWindow', {
	extend : 'Ext.window.Window',
	closeAction : 'hide',
	title : organization.i18n('dpap.organization.employeeMsg'),
	resizable : false,
	modal : true,
	width : 735,
	employeeForm : null,
	getEmployeeForm : function() {
		if (Ext.isEmpty(this.employeeForm)) {
			this.employeeForm = Ext.create('Dpap.organization.EmployeeForm');
		}
		
		return this.employeeForm;
	},
	constructor : function(config) {
		var me = this,
			cfg = Ext.apply({}, config);
		me.items = [ me.getEmployeeForm() ];
		me.listeners = {
			beforehide : function(win, Opts) {
				me.getEmployeeForm().getForm().reset();
			}
		};
		me.callParent([ cfg ]);
	}
});

/**
 * 定义员工信息Store
 */
Ext.define('Dpap.organization.EmpStore', {
	extend : 'Ext.data.Store',
	pageSize : 15,
	model : 'Dpap.organization.EmployeeModel',
	proxy : {
		type : 'ajax',
		actionMethods : 'post',
		url : organization.realPath("findAllEmployee"),
		reader : {
			type : 'json',
			root : 'data',
			totalProperty : 'totalCount'
		}
	},
	listeners : {
		// 在pageStore加载前，向其传参
		beforeload : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp(organization.CONTENT_ID).getQueryForm();
			
			if (queryForm != null) {
				var queryParams = queryForm.getValues();
				Ext.apply(operation, {
					params : {
						'employeeVo.deptName' : queryParams.deptName,
						'employeeVo.empCode' : queryParams.empCode,
						'employeeVo.empName' : queryParams.empName,
						'employeeVo.mobileNumber' : queryParams.mobileNumber,
						'employeeVo.position' :queryParams.position
					}
				});	
			}
		}
	}
});

/**
 * 员工信息表
 */
Ext.define('Dpap.organization.EmpGrid', {
	extend : 'Ext.grid.Panel',
	title : organization.i18n('dpap.organization.girdEmployee'),
	frame : true,
	sortableColumns : false,
	enableColumnHide : false,
	enableColumnMove : false,
	columnLines : true,
	columns : [{
		header : organization.i18n('dpap.organization.Employee.EMPCODE'),
		flex: 1,
		dataIndex : 'empCode'
	}, {
		header : organization.i18n('dpap.organization.Department.deptName'),
		dataIndex : 'deptEntity',
		flex: 1,
		renderer: function(value, metadata, record){
	        if (value != null) {
				metadata.tdAttr = 'data-qtip="' + value.deptName + '"'; 
	            return value.deptName;
	        }
	        
	        return null;
	    }
	}, {
		header : organization.i18n('dpap.organization.Employee.EMPNAME'),
		flex: 1,
		dataIndex : 'empName'
	}, {
		header : organization.i18n('dpap.organization.Employee.GENDER'),
		dataIndex : 'gender',
		flex: 1,
		renderer : function(value) {
			if (value == true) {
				return organization.i18n('dpap.organization.male');
			}

			return organization.i18n('dpap.organization.fmale');
		}
	}, {
		header : organization.i18n('dpap.organization.Employee.MOBILENO'),
		flex: 1,
		dataIndex : 'mobileNumber'
	}, {
		header : organization.i18n('dpap.organization.Employee.OTEL'),
		flex: 1,
		dataIndex : 'offerTel'
	}, {
		header : organization.i18n('dpap.organization.Employee.POSITION'),
		flex: 1,
		dataIndex : 'position'
	} ],
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
	empWindow : null,
	getEmpWindow : function(){
		if (Ext.isEmpty(this.empWindow)) {
			this.empWindow = Ext.create('Dpap.organization.EmployeeWindow');
		}
		
		return this.empWindow;
	},
	bindFormDataFromGrid : function(record){
		var empForm = this.getEmpWindow().getEmployeeForm();
		empForm.getForm().loadRecord(record);
		empForm.getForm().findField('deptId').setValue(record.data.deptEntity.deptName);
		empForm.getForm().findField('gender').setValue(
			record.data.gender
				?organization.i18n('dpap.organization.male')
				:organization.i18n('dpap.organization.fmale')
		);
		empForm.getForm().findField('status').setValue(
			record.data.status ? 
					organization.i18n('dpap.organization.onJob')
					:organization.i18n('dpap.organization.outJob')
		);
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Dpap.organization.EmpStore');
		me.bbar = me.getPagingToolbar();
		me.listeners = {
			itemdblclick : function(view, record, item, index, e, eOpts) {
				me.bindFormDataFromGrid(record);
				me.getEmpWindow().show();
			}
		};
		me.callParent([cfg]);
	}
});

/*------------------------------------树的相关功能级元素-----------------------------------------------------------*/

/** 定义部门树的store */
Ext.define('Dpap.organization.DepartmentTreeStore', {
	extend: 'Ext.data.TreeStore',
	proxy: {
		type : 'ajax',
		url : organization.realPath('loadTree')
	},
	root: {
		id : organization.DEPT_ROOT_ID,
		text : organization.i18n('dpap.organization.rootAndCompanyName'),
		expanded : true
	},
	constructor: function(config) {
		var me = this, 
			cfg = Ext.apply({}, config);
		me.callParent([cfg]);
	}
});


/**
 * 部门信息查询form表单
 */
Ext.define('Dpap.organization.DepartmentForm',{
	extend : 'Ext.form.Panel',
	frame : true,
	defaultType: 'textfield',
	defaults:{
		margin : '5 20 5 10',
		labelWidth : 130,
		colspan : 1,
		readOnly: true
	},
	layout:{
		type : 'table',
		columns : 2
	},
	initComponent: function() {
		var me = this;
		me.items = [{
			name : 'deptCode',
			fieldLabel : organization.i18n('dpap.organization.Department.deptCode')
		},{
			name : 'deptName',
			fieldLabel : organization.i18n('dpap.organization.Department.deptName')
		},{
			name : 'leader',
			fieldLabel : organization.i18n('dpap.organization.Department.principal')
		},{
			name : 'deptTelephone',
			fieldLabel : organization.i18n('dpap.organization.Department.phone')
		},{
			name : 'deptFax',
			fieldLabel : organization.i18n('dpap.organization.Department.fax')
		},{
			name : 'parentDeptName',
			fieldLabel : organization.i18n('dpap.organization.Department.parentName')
		},{
			name : 'subsidiaryCode',
			fieldLabel : organization.i18n('dpap.organization.Department.companyName')
		},{
			name : 'zipCode',
			fieldLabel : organization.i18n('dpap.organization.Department.zipCode')
		},{
			name : 'address',
			fieldLabel : organization.i18n('dpap.organization.Department.address'),
			xtype : 'textfield',
			width : window.screen.availWidth * 0.2344,
		}];
		me.callParent();
	}
});

/**
 * 部门信息查询窗口
 */
Ext.define('Dpap.organization.DepartmentWindow',{
	extend : 'Ext.window.Window',
	title : organization.i18n('dpap.organization.deptMsg'),
	modal : true,
	closeAction : 'hide',
	width : 700,
	departmentForm : null,
	getDepartmentForm : function() {
		if (Ext.isEmpty(this.departmentForm)) {
			this.departmentForm = Ext.create('Dpap.organization.DepartmentForm');
		}
		return this.departmentForm;
	},
	constructor : function(config) {
		var me = this, 
			cfg = Ext.apply({}, config);
		me.items = [ me.getDepartmentForm() ],
		// 监听事件
		me.listeners = {
			beforehide : function(win, Opts){
				me.getDepartmentForm().getForm().reset();
			}
		};
		me.callParent([cfg]);
	}
});

/**
 * 树的面板
 */
Ext.define('Dpap.organization.DepartmentTreePanel',{
	extend  : 'Ext.tree.Panel',
	title : organization.i18n('dpap.organization.deptTree'),
	frame : true,
	titleCollapse : true,
	useArrows : true,
	animate : true,
	// 部门详情
	deptWindow : null,
	getDeptWindow : function() {
		if (Ext.isEmpty(this.deptWindow)) {
			this.deptWindow = Ext.create('Dpap.organization.DepartmentWindow');
			organization.deptWindow = this.deptWindow;
		}
		return this.deptWindow;
	},
	// 部门详情按钮
	deptDetailButton: null,
	getDeptDetailButton: function() {
		var me = this;
		if(Ext.isEmpty(me.deptDetailButton)){
			me.deptDetailButton = Ext.create('Ext.Button', {
				text : organization.i18n('dpap.organization.viewDeptAll'),
				disabled: true,
				handler : function() {
					var	sm = me.getSelectionModel();
					if (sm.getSelection().length > 0) {
						var record = sm.getSelection()[0];
						
						if (record != null && record.data.id != 'root') {
							me.bindFormDataFromTree(record);
							me.getDeptWindow().show();
						}
					}
				}
			});
		}
		
		return this.deptDetailButton;
	},
	treeLeftKeyAction : function(node, record, item, index, e) {
		if(record.data.root){
			this.getDeptDetailButton().setDisabled(true);
			return;
		}

		this.getDeptDetailButton().setDisabled(false);
		
		Ext.getCmp(organization.CONTENT_ID).getQueryForm()
			.getForm().findField("deptName")
			.setValue(record.raw.entity.deptName);
		Ext.getCmp(organization.CONTENT_ID).getEmpGrid().getPagingToolbar().moveFirst();
		
	},
	treeDbLeftKeyAction : function(node, record, item, index, e) {
		if(record.data.root){
			return;
		}
		
		if (record.data.id != 'root') {
			this.bindFormDataFromTree(record);
			this.getDeptWindow().show();
		}
	},
	changeStatusDeptToString : function(value){
		if(value==true){
		   return value = organization.i18n('dpap.organization.work');
		}else if(value==false){
		   return value = organization.i18n('dpap.organization.unWork');
		}
	},
	bindFormDataFromTree: function(record) {
		var deptForm = this.getDeptWindow().getDepartmentForm();
		
		if (record.data.id != 'root') {
			var departmentModel = 
				Ext.create('Dpap.organization.DepartmentModel', record.raw.entity);
			
			deptForm.loadRecord(departmentModel);
			if (record.raw.entity.parentEntity != null) {
				deptForm.getForm().findField("parentDeptName")
					.setValue(record.raw.entity.parentEntity.deptName);
			}
		}
	},
	deptNameQueryField : null,
	getDeptNameQueryField : function() {
		if (Ext.isEmpty(this.deptNameQueryField)) {
			this.deptNameQueryField = Ext.create('Ext.form.field.Text', {
				xtype : 'textfield',
				fieldLabel : '',
				labelPad : 2,
				labelWidth : 40,
				name : 'deptNameQuery',
				width : 120
			});
		}

		return this.deptNameQueryField;
	},
	expandNodes: [],
	expandPaths: function(pathArray) {
		var me = this;
		me.collapseAll();
		var path;
		for (var i = 0; i < pathArray.length; i++) {
			path = pathArray[i];
			me.expandPath(path, 'id', '/', function(success, lastNode){
				if (success) {
					me.expandNodes.push(lastNode);
				}
			});	    						
		}
	},
	constructor : function(config) {
		var me = this, 
			cfg = Ext.apply({}, config);
		me.store = Ext.create('Dpap.organization.DepartmentTreeStore');
		// 监听事件
		me.listeners = {
			itemclick : me.treeLeftKeyAction,
			afteritemexpand: function(node, index, item, eOpts){
				var expandNodes = me.expandNodes,
					flag = true,
					view = me.getView();
				if(expandNodes.length==0){
					return;
				}
				for(var i=0;i<expandNodes.length;i++){
					if(expandNodes[i]==null){
						flag = false;
						continue;
					}
					var nodeHtmlEl = me.getView().getNode(expandNodes[i]),
						nodeEl = Ext.get(nodeHtmlEl);
					if(Ext.isEmpty(nodeEl)){
						continue;
					}
					var divHtmlEl = nodeEl.query('div')[0],
					    divEl = Ext.get(divHtmlEl);
					divEl.highlight("ff0000", { attr: 'color', duration: 5000 });
				}
				if(flag){
					me.expandNodes = [];
				}
			}
		};
		me.tbar = [me.getDeptNameQueryField(), {
			xtype : 'label',
			width : 3
		}, {
			text : organization.i18n('dpap.organization.find'),
			plugins: {
		        ptype: 'buttondisabledplugin',
		        seconds: 2
		    },
			handler : function() {
				var deptname = me.getDeptNameQueryField().getValue();
				if (deptname == null || Ext.String.trim(deptname) == '') {
					Ext.ux.Toast.msg(
							organization.i18n('dpap.organization.message'), 
							organization.i18n('dpap.organization.InvalidQueryParam'),
							'error');
					return;
				}
				if (!/^[^\|"'<>%]*$/.test(deptname)) {
					Ext.ux.Toast.msg(
							organization.i18n('dpap.organization.message'), 
							organization.i18n('dpap.organization.InvalidQueryParam'),
							'error');
					return;
				}
				Ext.Ajax.request({
					url : organization.realPath("querySeq"),
					jsonData : {'deptName' : deptname},
					success : function(response) {
						json = Ext.decode(response.responseText);
						me.expandPaths(json.seqList);
					},
		            exception : function(response) {
		            	var json = Ext.decode(response.responseText); 
			        	Ext.ux.Toast.msg(
			        			organization.i18n('dpap.organization.message'), 
			        			json.message, 'error');
		            }
				});
			}
		}, me.getDeptDetailButton() ];
		me.callParent([cfg]);
	}
});

/**
 * 查询表单
 */
Ext.define('Dpap.organization.QueryForm', {
	extend  : 'Ext.form.Panel',
	title : organization.i18n('dpap.organization.findMsg'),
	frame : true,
	defaultType : 'textfield',
	layout : 'column',
	defaults : {
		columnWidth : .5,
		margin : '5 10 5 10'
	},
	items: [{
		fieldLabel : organization.i18n('dpap.organization.Department.deptName'),
		name : 'deptName',
		xtype : 'textfield'
	},{
		fieldLabel : organization.i18n('dpap.organization.Employee.EMPCODE'),
		name : 'empCode',
		xtype : 'textfield'
	},{
		fieldLabel : organization.i18n('dpap.organization.Employee.EMPNAME'),
		name : 'empName',
		xtype : 'textfield'
	},{
		fieldLabel : organization.i18n('dpap.organization.Employee.POSITION'),
		name : 'position',
		xtype : 'textfield'
	},{
		fieldLabel : organization.i18n('dpap.organization.Employee.MOBILENO'),
		name : 'mobileNumber',
		xtype: 'textfield'  
	}],
	buttons: [{
		width : 70,
		text : organization.i18n('dpap.organization.reset'),
		handler : function() {
			this.up('form').getForm().reset();
		}
	}, '->', {
		width : 70,
		cls: 'yellow_button',
		text : organization.i18n('dpap.organization.find'),
		handler : function() {
			Ext.getCmp(organization.CONTENT_ID).getEmpGrid().getPagingToolbar().moveFirst();
		}
	}],
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.callParent([cfg]);
	}
});

/*---------------------------------启动项-------------------------------------------*/
/**
 * 启动加载的页面元素及布局
 */
Ext.onReady(function() {
	Ext.QuickTips.init();
	if (Ext.getCmp(organization.CONTENT_ID)) {
		return;
	}
	var deptTree = Ext.create('Dpap.organization.DepartmentTreePanel', {
		height: 717,
		columnWidth: .3
	});
	var queryForm = Ext.create('Dpap.organization.QueryForm');
	var empGrid = Ext.create('Dpap.organization.EmpGrid');
	var content = Ext.create('Ext.panel.Panel', {
		id : organization.CONTENT_ID,
		cls : "panelContentNToolbar",
		bodyCls : 'panelContentNToolbar-body',
		layout : 'column',
		getQueryForm : function() {
			return queryForm;
		},
		getEmpGrid : function() {
			return empGrid;
		},
		getDeptTree : function() {
			return deptTree;
		},
		items : [ deptTree, {
			xtype : 'container',
			columnWidth: .7,
			items : [ queryForm, empGrid ]
		} ]
	});
	
	Ext.getCmp('T_organization-index').add(content);
});