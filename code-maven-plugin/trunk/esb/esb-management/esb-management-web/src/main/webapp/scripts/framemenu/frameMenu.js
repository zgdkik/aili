Ext.Loader.setConfig({
	    enabled : true
    });

Ext.application({
	    name : 'esbApp',
	    appFolder : 'scripts/framemenu',

	     autoCreateViewport : true,
	    stores : ['FrameMenus'],
	    controllers : ['FrameMenu'],

	    launch : function() {
		    setTimeout(function() {
			        Ext.get('loading').remove();
			        Ext.get('loading-mask').fadeOut({
				            remove : true
			            });
		        }, 1000);
	    }
    });
