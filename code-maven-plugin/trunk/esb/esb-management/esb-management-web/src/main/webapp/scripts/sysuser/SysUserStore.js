//model
Ext.define('SysUserInfoModel', {
			extend : 'Ext.data.Model',
			fields : [{
		 name: 'id',
		 type: 'string'
	 },{
		 name: 'sysUserName',
		 type: 'string'
	 },{
		 name: 'userName',
		 type: 'string'
	 },{
		 name: 'password',
		 type: 'string'
	 },{
		 name: 'telPhone',
		 type: 'string'
	 },{
		 name: 'mobilePhone',
		 type: 'string'
	 },{
		 name: 'email',
		 type: 'string'
	 }]
		});
//系统用户store
Ext.define('sysUserStore', {
	extend : 'Ext.data.Store',
	autoLoad : true,
	model : 'SysUserInfoModel',
	pageSize : 20, // 页大小
    proxy: {
        type: 'ajax',
        url : 'querySysUsers.action',
        reader: {
            type: 'json',
            root: 'sysUserInfoList',
            totalProperty : 'resultCount'// 总的数据条数
        }
    }
});