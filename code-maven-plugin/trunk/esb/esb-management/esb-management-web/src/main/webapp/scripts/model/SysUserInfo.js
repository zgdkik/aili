Ext.define('esbApp.model.SysUserInfo',{
	 extend: 'Ext.data.Model',
	 fields: [{
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