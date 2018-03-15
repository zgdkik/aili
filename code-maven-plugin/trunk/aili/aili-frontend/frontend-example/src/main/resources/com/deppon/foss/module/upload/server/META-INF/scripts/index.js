Ext.onReady(function() {
	Ext.tip.QuickTipManager.init();
	
	var richEditor = Ext.create('Ext.form.Panel', {
		title: 'Image Upload Plugin Demo',
		bodyPadding: 5,
		height: 500,
		frame: true,
		items: [{
            xtype: 'htmleditor',
			plugins: [{ 
				ptype: 'imageUpload',
				enableContextMenu:true,
				submitUrl: upload.realPath('uploadFile.action'),
				managerUrl: upload.realPath('reviewImg.action')
			}],
            height: 400,
            style: 'background-color: white;',
            anchor: '100%',
			value: '<h1>Welcome to Image Upload Plugin Demo </h1>'
        }]
    });
	
	var content = Ext.create('Ext.panel.Panel', {
		cls : "panelContentNToolbar",
		bodyCls : 'panelContentNToolbar-body',
		items: [ richEditor ]
	});
	
	Ext.getCmp('T_upload-index').add(content);
	
});