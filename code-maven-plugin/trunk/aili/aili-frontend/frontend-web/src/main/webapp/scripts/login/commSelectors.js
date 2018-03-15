/** 公共选择器下拉单选框基类 */
Ext.define('Dpap.commonSelector.CommonCombSelector', {
	extend : 'Deppon.commonselector.DynamicComboSelector',
	minChars : 0,
	isPaging : true,// 分页
	isEnterQuery : true,// 回车查询
	listWidth : 200,// 设置下拉框宽度
	active : null,
	myActive : 'Y',
	record : null,
	displayField : null,
	valueField : null, 
	displayField : null,// 显示名称
	valueField : null,// 值
	queryParam : null,// 查询参数
	setCombValue : function(displayText, valueText) {
		var me = this, key = me.displayField + '', value = me.valueField
				+ '';
		var m = Ext.create(me.store.model.modelName);
		m.set(key, displayText);
		m.set(value, valueText);
		me.store.loadRecords([m]);
		me.setValue(valueText);
	},
	getBlur:function(ths, the, eOpts){
		if (Ext.isEmpty(ths.value)) {
			ths.setRawValue(null);
		}
	},
	getBeforeLoad : function(st, operation, e) {
		var me = this;
		var me = this, searchParams = operation.params;
		if (Ext.isEmpty(searchParams)) {
			searchParams = {};
			Ext.apply(operation, {
				params : searchParams
			});
		}
		var prefix = me.queryParam.substring(0, me.queryParam
						.lastIndexOf('.'))
				+ '.';
		var param = prefix + me.myQueryParam;
		if (!Ext.isEmpty(me.myQueryParam)) {
			searchParams[param] = me.getRawValue();
			searchParams[me.queryParam] = null;
		} else {
			searchParams[me.queryParam] = me.rawValue;
			if(!Ext.isEmpty(me.myQueryParam)){
				searchParams[param] = null;
			}
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.active = config.active; 
		me.store.addListener('select', function(comb, records, obs) {
			me.record = records[0];
		});
		me.callParent([cfg]);
		me.on('blur',me.getBlur,me); 
	},
	getRecord : function() {
		var me = this;
		return me.record;
	}
});

/* ***************************** 部门 *********************************/
/** 部门model */
Ext.define('Dpap.commonSelector.DepartmentModel', {
	extend : 'Ext.data.Model',
	fields :[{
			name: 'deptName',type:'string'
		},{
			name: 'deptCode',type:'string'
		},{
			name: 'pinyin',type: 'string'
		}]
});

/** 部门store */
Ext.define('Dpap.commonSelector.DepartmentStore',{
	extend:'Ext.data.Store',
	model: 'Dpap.commonSelector.DepartmentModel',
	pageSize:10,
	proxy:{
		type:"ajax",
		url:base+"/organization/findAllDepartmentBySelector",
		actionMethods:"POST",
		reader:{
			type:'json',
			root:'data',
			totalProperty : 'totalCount'
		}
	}
});

/** 部门单选公共选择器 */
Ext.define('Dpap.commonSelector.DepartmentSelector',{
	extend : 'Dpap.commonSelector.CommonCombSelector',
	alias : 'widget.departmentCommonSelector',
//	fieldLabel : '职员',
	listWidth : 200,// 设置下拉框宽度
	displayField : 'deptName',// 显示名称
	valueField : 'deptCode',// 值
	pinyin : null,//拼音
	deptName : null,//姓名
	queryParam : 'departmentVo.selectorParam',// 查询参数
	showContent : '{deptName}&nbsp;&nbsp;&nbsp;{deptCode}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.deptName = config.deptName;
		me.pinyin = config.pinyin;
		me.store = Ext.create('Dpap.commonSelector.DepartmentStore');
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.params;
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
				Ext.apply(operation, {
					params : searchParams
				});
			}
			if(!Ext.isEmpty(config.pinyin)){
				searchParams['departmentVo.pinyin'] = config.pinyin;	
			}
			if(!Ext.isEmpty(config.pinyin)){
				searchParams['departmentVo.deptName'] = config.deptName;	
			}
		});
		me.callParent([cfg]);
	}
});

/* ***************************** 职员 *********************************/
/** 职员model */
Ext.define('Dpap.commonSelector.EmployeeModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'empCode',
						type : 'string'
					}, {
						name : 'empName',
						type : 'string'
					}, {
						name : 'pinyin',
						type : 'string'
			}]
});
/** 职业store */
Ext.define('Dpap.commonSelector.EmployeeStore', {
			extend : 'Ext.data.Store',
			model : 'Dpap.commonSelector.EmployeeModel',
			pageSize : 10,
			proxy : {
				type : 'ajax',
				url : base+'/organization/queryEmpCommSelector',
				actionMethods : 'POST',// 否则可能会出现中文乱码
				reader : {
					type : 'json',
					root : 'data',
					totalProperty : 'totalCount'
				}
			}
});
/** 职员单选公共选择器 */
Ext.define('Dpap.commonSelector.EmployeeSelector', {
	extend : 'Dpap.commonSelector.CommonCombSelector',
	alias : 'widget.commonemployeeselector',
//	fieldLabel : '职员',
	listWidth : 200,// 设置下拉框宽度
	displayField : 'empName',// 显示名称
	valueField : 'empCode',// 值
	pinyin : null,//拼音
	empName : null,//姓名
	queryParam : 'employeeVo.selectorParam',// 查询参数
	showContent : '{empName}&nbsp;&nbsp;&nbsp;{empCode}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.empName = config.empName;
		me.pinyin = config.pinyin;
		me.store = Ext.create('Dpap.commonSelector.EmployeeStore');
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.params;
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
				Ext.apply(operation, {
					params : searchParams
				});
			}
			if(!Ext.isEmpty(config.pinyin)){
				searchParams['employeeVo.pinyin'] = config.pinyin;	
			}
			if(!Ext.isEmpty(config.pinyin)){
				searchParams['employeeVo.empName'] = config.empName;	
			}
		});
		me.callParent([cfg]);
	}
});

/* ***************************** 功能 作者:李光辉*********************************/
/** 功能model */
Ext.define('Dpap.commonSelector.FunctionModel', {
	extend : 'Ext.data.Model',
	fields : [{
				name : 'functionCode',
				type : 'string'
			}, {
				name : 'functionName',
				type : 'string'
			}, {
				name : 'functionType',
				type : 'string'
	}]
});
/** 功能store */
Ext.define('Dpap.commonSelector.FunctionStore', {
	extend : 'Ext.data.Store',
	model : 'Dpap.commonSelector.FunctionModel',
	pageSize : 10,
	proxy : {
		type : 'ajax',
		url : base+'/authorization/findAllFunctionBySelector',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			root : 'data',
			totalProperty : 'totalCount'
		}
	}
});
/** 功能单选公共选择器 */
Ext.define('Dpap.commonSelector.FunctionSelector', {
	extend : 'Dpap.commonSelector.CommonCombSelector',
	alias : 'widget.commonfunctionselector',
	listWidth : 200,// 设置下拉框宽度
	displayField : 'functionName',// 显示名称
	valueField : 'functionCode',// 值
	queryParam : 'functionName',// 查询参数
	showContent : '{functionName}&nbsp;&nbsp;&nbsp;{functionCode}',// 显示表格列
	typeMode: 1, // 1.显示菜单和页面元素(默认)；2.全部；3.显示菜单；4.不显示页面元素；
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Dpap.commonSelector.FunctionStore');
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = {
				'selectorParam.name': operation.params.functionName,
				'selectorParam.typeMode': me.typeMode
			};
			Ext.apply(operation, {
				params : searchParams
			});
		});
		me.callParent([cfg]);
	}
});


/**部门公共选择器（多选）xyk*/
Ext.define('Dpap.commonSelector.CommonMultiCombSelector', {
	extend : 'Deppon.commonselector.DynamicMultiSelectComboSelector',
	minChars : 0,
	isPaging : true,// 分页 
	listWidth : 600,// 设置下拉框宽度
	active : null,
	myActive : 'Y',
	record : null,
	displayField : null,
	valueField : null,
	displayField : null,// 显示名称
	valueField : null,// 值
	queryParam : null,// 查询参数
	triggerAction: 'query',
	setCombValue : function(displayText, valueText) {
		var me = this, key = me.displayField + '', value = me.valueField + '';
		var m = Ext.create(me.store.model.modelName);
		m.set(key, displayText);
		m.set(value, valueText);
		me.store.loadRecords([m]);
		me.setValue(valueText);
	},
	getBeforeLoad : function(st, operation, e) {
		var me = this;
		var me = this, searchParams = operation.params;
		if (Ext.isEmpty(searchParams)) {
			searchParams = {};
			Ext.apply(operation, {
				params : searchParams
			});
		}
		var prefix = me.queryParam.substring(0, me.queryParam
						.lastIndexOf('.'))
				+ '.';
		if (!Ext.isEmpty(me.myQueryParam)) {
			var param = prefix + me.myQueryParam;
			searchParams[param] = me.getRawValue();
		} else {
			searchParams[me.queryParam] = me.lastQuery;
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.active = config.active;
		me.mon(me.store,'beforeLoad',me.getBeforeLoad,me); 
		me.store.addListener('select', function(comb, records, obs) {
			me.record = records[0];
		});
		me.callParent([cfg]);
	},
	getRecord : function() {
		var me = this;
		return me.record;
	}
});

/*部门MODEL*/
Ext.define('Dpap.baseinfo.commonSelector.OrgModel', {
	extend : 'Ext.data.Model',
	fields : [{
				name : 'deptCode'
			}, {
				name : 'deptName'
			}, {
				name : 'pinYin'
			}]
});

/*部门STORE*/
Ext.define('Dpap.commonOrgSelector.OrgCombStore', {
	extend : 'Ext.data.Store',
	model : 'Dpap.baseinfo.commonSelector.OrgModel',
	pageSize : 10,
	proxy : {
		type : 'ajax',
		url : base+'/organization/findAllDepartmentBySelector',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			root : 'data',
			totalProperty : 'totalCount'
		}
	}
});

Ext.define('Dpap.commonOrgSelector.DynamicOrgMultiSelector', {
	extend : 'Dpap.commonSelector.CommonMultiCombSelector',
	alias : 'widget.dynamicorgmulticombselector',
	//fieldLabel : '动态部门单选',
	displayField : 'deptName',// 显示名称
	valueField : 'deptCode',// 值
	queryParam : 'departmentVo.selectorParam',// 查询参数
	showContent : '{deptName}&nbsp;&nbsp;&nbsp;{deptCode}',// 显示表格列
	constructor : function(config) {
	var me = this, cfg = Ext.apply({}, config);    
	me.store = Ext.create('Dpap.commonOrgSelector.OrgCombStore');
	me.store.addListener('beforeload', function(store, operation, eOpts) {
		var searchParams = operation.params;
		if (Ext.isEmpty(searchParams)) {
			searchParams = {};
			Ext.apply(operation, {
						params : searchParams
			});
		}
	})
	me.callParent([cfg]);
	}
});

/* ***************************** 角色 *********************************/
/**角色model */
Ext.define('Dpap.commonSelector.RoleModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'roleCode',
						type : 'string'
					}, {
						name : 'roleName',
						type : 'string'
					}]
});
/** 角色store */
Ext.define('Dpap.commonSelector.RoleStore', {
			extend : 'Ext.data.Store',
			model : 'Dpap.commonSelector.RoleModel',
			pageSize : 10,
			proxy : {
				type : 'ajax',
				url : base+'/authorization/queryRoleCommSelector',
				actionMethods : 'POST',// 否则可能会出现中文乱码
				reader : {
					type : 'json',
					root : 'data',
					totalProperty : 'totalCount'
				}
			}
});
/** 角色单选公共选择器 */
Ext.define('Dpap.commonSelector.RoleSelector', {
	extend : 'Dpap.commonSelector.CommonCombSelector',
	alias : 'widget.commonroleselector',
	listWidth : 200,// 设置下拉框宽度
	displayField : 'roleName',// 显示名称
	valueField : 'roleCode',// 值
	queryParam : 'roleVo.selectorParam',// 查询参数
	showContent : '{roleName}&nbsp;&nbsp;&nbsp;{roleCode}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Dpap.commonSelector.RoleStore');
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.params;
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
				Ext.apply(operation, {
					params : searchParams
				});
			}
		});
		me.callParent([cfg]);
	}
});

/* ***************************** 用户 *********************************/
/**用户model */
Ext.define('Dpap.commonSelector.UserModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'userName',
						type : 'string'
					}, {
						name : 'empEntity'
					}, {
						name : 'empName',
						type : 'string',
						convert:function(v,record){
						    return record.data.empEntity ? record.data.empEntity.empName:'';
						}
					}
					]
});
/** 用户store */
Ext.define('Dpap.commonSelector.UserStore', {
			extend : 'Ext.data.Store',
			model : 'Dpap.commonSelector.UserModel',
			pageSize : 10,
			proxy : {
				type : 'ajax',
				url : base+'/authorization/queryUserCommSelector',
				actionMethods : 'POST',// 否则可能会出现中文乱码
				reader : {
					type : 'json',
					root : 'data',
					totalProperty : 'totalCount'
				}
			}
});
/** 用户单选公共选择器 */
Ext.define('Dpap.commonSelector.UerSelector', {
	extend : 'Dpap.commonSelector.CommonCombSelector',
	alias : 'widget.commonUserSelector',
	listWidth : 200,// 设置下拉框宽度
	displayField : 'empName',// 显示名称
	valueField : 'userName',// 值
	queryParam : 'userVo.selectorParam',// 查询参数
	showContent : '{empName}&nbsp;&nbsp;&nbsp;{userName}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Dpap.commonSelector.UserStore');
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.params;
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
				Ext.apply(operation, {
					params : searchParams
				});
			}
		});
		me.callParent([cfg]);
	}
});
