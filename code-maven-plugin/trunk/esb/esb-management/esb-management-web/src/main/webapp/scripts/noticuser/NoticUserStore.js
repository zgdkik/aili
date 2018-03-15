//model预警人员模块
Ext.define('NoticUserInfoModel', {
			extend : 'Ext.data.Model',
			fields : [{
		 name: 'id',
		 type: 'string'
	 },{
		 name: 'userName',
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
	 },{
		 name: 'pjVersion',
		 type: 'string'
	 }]
		});
//预警用户store
Ext.define('noticUserStore', {
	extend : 'Ext.data.Store',
	autoLoad : true,
	model : 'NoticUserInfoModel',
	pageSize : 20, // 页大小
    proxy: {
        type: 'ajax',
        url : 'queryNoticUsers.action',
        reader: {
            type: 'json',
            root: 'noticUserInfoList',
            totalProperty : 'resultCount'// 总的数据条数
        }
    }
});
//系统用户store
Ext.define('systemUserStore', {
	extend : 'Ext.data.Store',
	autoLoad : true,
	model : 'NoticUserInfoModel',
	pageSize : 20, // 页大小
    proxy: {
        type: 'ajax',
        url : 'querySystemUsers.action',
        reader: {
            type: 'json',
            root: 'noticUserInfoList',
            totalProperty : 'resultCount'// 总的数据条数
        }
    }
});


//版本号选择
Ext.define('pjVersionStore', {
	extend : 'Ext.data.Store',
	fields:['pjVersion'],
	data:[{'pjVersion':'ESB1'},{'pjVersion':'ESB2'}]
});