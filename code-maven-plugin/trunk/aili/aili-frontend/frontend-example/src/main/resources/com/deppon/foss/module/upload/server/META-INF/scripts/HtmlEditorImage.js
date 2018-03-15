 
Ext.define('Ext.ux.form.HtmlEditor.imageUpload', {
	alias: 'plugin.imageUpload',
    /**
     * @cfg {Array} options
     * Associative array with all the strings.
     * If not specified it will show all the strings in english
     */
    lang: {
        'Insert/Edit Image': '插入图片',
        'Upload Image...': '浏览',
        'Uploading your photo...': '上传中...',
        'Error': 'Error',
        'Focus first': '请设置要插入图片的位置',
        'OK': '保存',
        'Cancel': '关闭',
        'Confirmation': '',
        'Are you sure you want to delete this image?': '',
        'Your photo has been uploaded.': '上传成功'
    },

    /**
     * @cfg {String} submitUrl
     * Path to the upload script.
     * Default 'htmlEditorImageUpload.php'
     */
    submitUrl: 'htmlEditorImageUpload.php',
	
	/**
     * @cfg {String} serverSideEdit
     * Enables/disables server side image editing buttons.
     * Default false
     */
    disableServerSideEdit: false,
	
	/**
     * @cfg {String} serverSideEdit
     * Enables/disables server side image deletion.
     * Default false
     */
    disableDelete: false,
	
	/**
     * @cfg {String} styling
     * Enables/disables image css styling.
     * Default false
     */
    disableStyling: false,

    /**
     * @cfg {String} mamangerUrl
     * Path to the image manager script.
     * Default 'htmlEditorImageManager.php'
     */
    managerUrl: 'htmlEditorImageUpload.php',


    /**
     * @cfg {integer} pageSize
     * Number of images to show on the list.
     * Default 6
     */
    pageSize: 6,

  /**
   * @cfg {Boolean} values are:
   * true : Default
   * Allows the user to resize an image clicking on it and dragging with the mouse. (Only WebKit browsers)
   * false 
   * The image wont be resized if the user drags on it
   */
    dragResize: true,
	
	  /**
   * @cfg {Boolean} values are:
   * false : Default
   * Context menu for images enabled
   * true 
   * Context menu will not be avaible
   */
    enableContextMenu: false,

/**
   * @cfg {Boolean} values are:
   * true : Default
   * Allows the user to resize an image clicking on it and using the mousewheel. (Only WebKit browsers & Opera)
   * 
   * false 
   * The image wont be resized if the user uses mousewheel on it
   */
    wheelResize: true,

    /**
     * @cfg {String} iframeCss
     * Path to the iframe css file. 
     * It's important to do not merge this css with other CSS files, because it will be applied to the htmleditor 
     * iframe head. If more css rules are included, it can suffer undesired effects
     * Default 'css/iframe_styles.css'
     */
    iframeCss: 'css/iframe_styles.css',
    t: function (string) {
        return this.lang[string] ? this.lang[string] : string;
    },
	
    constructor: function (config) {
        Ext.apply(this, config);
        this.callParent(arguments);
    },
	
    init: function (panel) {
        this.cmp = panel;
        this.cmp.on('render', this.onRender, this);
        this.cmp.on('initialize', this.initialize, this);
        this.cmp.on('beforedestroy', this.beforeDestroy, this);
    },
	
    initialize: function () {
        var me = this;
        var cmpDoc = this.cmp.getDoc();
        me.flyDoc = Ext.fly(cmpDoc);

        // Inject custom css file to iframe's head in order to simulate image control selector on click, over webKit and Opera browsers
//        if ((Ext.isWebKit || Ext.isOpera)) 
//        	me._injectCss(me.cmp, me.iframeCss);

        // attach context menu
        if(me.enableContextMenu)
        	//me._contextMenu();

		// attach events to control when the user interacts with an image
		me.cmp.mon(me.flyDoc, 'dblclick', me._dblClick, me, {delegate : "img"});
		me.cmp.mon(me.flyDoc, 'mouseup', me._docMouseUp, me);
		me.cmp.mon(me.flyDoc, 'paste', me._removeSelectionHelpers, me);
		
        // mousewheel resize event
        if ((Ext.isWebKit || Ext.isOpera) && me.wheelResize) {	
			me.cmp.mon(me.flyDoc, 'mousewheel', me._wheelResize, me, {delegate : "img"});
        }

        // mouse drag resize event
        if (Ext.isWebKit && me.dragResize) {	
			me.cmp.mon(me.flyDoc, 'drag', me._dragResize, me, {delegate : "img"});
        }
    },
	
    beforeDestroy: function () {
        var me = this;
        if (me.uploadDialog) me.uploadDialog.destroy();
        if (me.contextMenu) me.contextMenu.destroy();
    },
	
    onRender: function () {

        var me = this;
		var imageButton = Ext.create('Ext.button.Button', {
            iconCls: 'x-htmleditor-imageupload',
            handler: me._openImageDialog,
            scope: me,
            tooltip: me.t('Insert/Edit Image'),
            overflowText: me.t('Insert/Edit Image')
        });

        // we save a reference to this button to use it later
        me.imageButton = imageButton;

        me.cmp.getToolbar().add(imageButton);

    },
	
    //private
    // instead of overriding the htmleditor header method we just append another css file to it's iframe head
    _injectCss: function (cmp, cssFile) {
        var frameName = cmp.iframeEl.dom.name;
        var iframe;

        if (document.frames) iframe = document.frames[frameName];
        else iframe = window.frames[frameName];

        // we have to add our custom css file to the iframe
        var ss = iframe.document.createElement("link");
        ss.type = "text/css";
        ss.rel = "stylesheet";
        ss.href = cssFile;

        if (document.all) iframe.document.createStyleSheet(ss.href);
        else iframe.document.getElementsByTagName("head")[0].appendChild(ss);

    },
	
    // private
    _dblClick: function (evt) {
        var me = this;
        var target = evt.getTarget();

        if (target.tagName == "IMG") {
            me._openImageDialog();
        }
    },
	
    //private
    _openImageDialog: function () {

        var me = this;
        var doc = this.cmp.getDoc();
        var win = this.cmp.win;
        var sel = "";
        var range = "";
        var image = "";
        var imagesList = doc.body.getElementsByTagName("IMG");
        var imagesListLength = imagesList.length;

        //insertAtCursor function is completely useless for this purpose, so I need to write all this stuff to insert html at caret position	
        // I need to know if the browser uses the W3C way or the Internet Explorer method
        var ieBrowser = doc.selection && doc.selection.createRange ? true : false;
        var nonIeBrowser = win.getSelection && win.getSelection().getRangeAt ? true : false;

        if (nonIeBrowser) {
            sel = win.getSelection();
            // if focus is not in htmleditor area
            try {
                range = sel.getRangeAt(0);
            } catch (err) {
            	Ext.Msg.alert('', me.t('Focus first'));
//                win.focus();
//                range = sel.getRangeAt(0);
                return;
            }

        } else if (ieBrowser) {
            //it's compulsory to get the focus before creating the range, if not we'll lose the caret position
            win.focus();
            sel = doc.selection;
            range = sel.createRange();
        }
        
        me.uploadDialog = Ext.create('Ext.ux.form.HtmlEditor.ImageDialog', {
            lang: me.lang,
            t: me.t,
            submitUrl: me.submitUrl,
            managerUrl: me.managerUrl,
            iframeDoc: doc,
            imageToEdit: image,
            pageSize: me.pageSize,
            imageButton: me.imageButton,
			disableServerSideEdit: me.serverSideEdit,
			disableStyling: me.styling,
			disableDelete : me.disableDelete
        });

        me.uploadDialog.on('close', function () {
            if (Ext.isIE) {
                me.imageButton.toggle(false);
                me._removeSelectionHelpers();
            }
        }, me);

        // custom event that fires when the user presses the ok button on the dialog
        me.uploadDialog.on('imageloaded', function () {

            var newImage = this.getImage();

            // if it's an edited image, we have to replace it with the new values
            if (image != "") {
                for (var i = 0; i < imagesListLength; i++) {
                    if (parseInt(imagesList[i].getAttribute('iu_edit')) > 0) {
                        if (nonIeBrowser) {
                            imagesList[i].parentNode.replaceChild(newImage, imagesList[i]);
                            try {
                                if (sel) {
                                    sel.selectAllChildren(doc.body);
                                    sel.collapseToStart();
                                }

                            } catch (ex) {};
                        } else if (ieBrowser) {
                            imagesList[i].outerHTML = newImage.outerHTML;
                        }
                        break;
                    }
                }
            }
            // if not we just insert a new image on the document
            else {
                if (nonIeBrowser) {
                    range.insertNode(newImage);
                } else if (ieBrowser) {
                    win.focus();
                    range.select();
                    range.pasteHTML(newImage.outerHTML);
                }
            }

            me.imageToEdit = "";
            this.close();
            me.imageButton.toggle(false);
        });

        me.uploadDialog.show();
    },
	
    //private
	//Remove custom image attrs from the iframe body DOM
    _removeSelectionHelpers: function () {
        var me = this;
        var imagesList = me.cmp.getDoc().body.getElementsByTagName("IMG");
        var imagesListLength = imagesList.length;

        for (var i = 0; i < imagesListLength; i++) {
            imagesList[i].removeAttribute('iu_edit');
        }
    },
	
	//private
	//When user uses mousewheel over an image
    _wheelResize: function (e) {
        var target = e.getTarget();
        if (target.tagName == "IMG" && target.getAttribute('iu_edit') == 1) {
            var delta = e.getWheelDelta();
            var width = target.style.width ? parseInt(target.style.width.replace(/[^\d.]/g, "")) : target.width;

            target.removeAttribute('height');
            target.style.removeProperty('height');

            // change just width to keep aspect ratio
            target.style.width = (delta < 1) ? width - 10 : width + 10;

            e.preventDefault();
        } else return;
    },
	
	//private
	//When user drags over an image
    _dragResize: function (e) {

        var target = e.getTarget();

        if (target.tagName == "IMG" && (target.getAttribute('iu_edit') == 1)) {
            var width = e.getX() - target.offsetLeft;
            var height = e.getY() - target.offsetTop;
            target.style.width = width + "px";
            target.style.height = height + "px";
            e.preventDefault();
        } else return;
    },
    
	//private
	//When user clicks on content editable area
    _docMouseUp: function (evt) {

        var me = this;
        var target = evt.getTarget();

        me._removeSelectionHelpers();

        if (target.tagName == "IMG") {
            me.imageButton.toggle(true);
            if ((me.wheelResize || me.dragResize) && (Ext.isWebKit || Ext.isOpera)) target.setAttribute('iu_edit', '1');
            else target.setAttribute('iu_edit', '2');

            // select image. 
            // On safari if we copy and paste the image, class attrs are converted to inline styles. It's a browser bug.
            if (Ext.isWebKit) {
                var sel = this.cmp.getWin().getSelection ? this.cmp.getWin().getSelection() : this.cmp.getWin().document.selection;
                sel.setBaseAndExtent(target, 0, target, 1);
            }
        } else me.imageButton.toggle(false);
    }
});

/** 上传窗体 */
Ext.define('Ext.ux.form.HtmlEditor.ImageDialog', {
    extend: 'Ext.window.Window',
    lang: null,
    lang: null,
    t: null,
    submitUrl: null,
    managerUrl: null,
    iframeDoc: null,
    pageSize: null,
    imageToEdit: '',
    closeAction: 'destroy',
    width: 460,
    modal: true,
    resizable: false,
    relativePath: null,
    layout: {
        type: 'fit'
    },
    title: '',
    listeners: {
        show: function (panel) {
            // we force the focus on the dialog window to avoid control artifacts on IE
            //panel.down('[name=src]').focus();
        },
        resize: function (panel) {
            panel.center();
        }
    },
	
    initComponent: function () {
        var me = this;
        me.items = [{
            xtype: 'form',
            name: 'imageUploadForm',
            bodyPadding: 10,
            items: [{
                xtype: 'filefield',
                buttonOnly: false,
                msgTarget: 'side',
                readOnly:true,
                readOnlyCls:'disable',
                allowBlank: false,
                name: 'file',
                anchor: '100%',
                buttonText: me.t('Upload Image...')
            }],
			dockedItems: [{
                xtype: 'container',
                dock: 'bottom',
                padding: 4,
                items: [{
                    xtype: 'button',
                    style: {
                        'float': 'right'
                    },
                    text: me.t('Cancel'),
                    handler: me.close,
                    scope: me
                }, {
                    xtype: 'button',
                    style: {
                        'float': 'right',
                        'margin-right': '8px'
                    },
                    text: me.t('OK'),
                    formBind: true,
                    handler: me._uploadImage,
                    scope: me
                }]
            }]
        }];
		
        me.callParent(arguments);
        me.setTitle(me.t('Insert/Edit Image'));
    },
	
	/**
     * Returns the current image with all the data specified in the form. (Size, borders, padding e.t.c)
     * @return {HTMLImageObject} 
     */
    getImage: function () {
    	var me = this;
    	var image = this.iframeDoc.createElement("img");
    	image.setAttribute('src', me.managerUrl + '?relativePath=' + me.relativePath);
    	
        return image;
    },
	
	//private
	_deleteImage:function (ev, a) {
		
		var me = this;
		
		if (!me.disableDelete){
			Ext.Msg.show({
				title: me.t('Confirmation'),
				msg: me.t('Are you sure you want to delete this image?'),
				buttons: Ext.Msg.YESNO,
				closable: false,
				fn: function (btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
							url: me.managerUrl,
							method: 'POST',
							params: {
								'action': 'delete',
								'image': a.getAttribute ? a.getAttribute('img_fullname') : a
							},
							success: function (response) {
								
								var result = Ext.JSON.decode(response.responseText);
								if(result.success)
								{
									// delete the image from the list
									var combo = me.down('[name=src]');

									//if I do here a combo.store.load() to refresh, the paging toolbar disappears
									// so I'll do it on combo expand event
									combo.needsRefresh = true;

									combo.setValue('');
									me.down('form').getForm().reset();
								}else{
									Ext.Msg.alert(me.t('Error'), 'Error: ' + result.errors);
								}
							}
						});
					}
				}
			});
		}
	},
	
	//private
	//method to upload the image to the server
    _uploadImage: function (button) {
        var me = this;

        var form = button.up('form').getForm();
        if (form.isValid()) {
            form.submit({
                url: me.submitUrl + '?action=upload',
                waitMsg: me.t('Uploading your photo...'),
                success: function (fp, o) {
                    me.relativePath = o.result.attachement.relativePath;
                    me.fireEvent('imageloaded');
                },
                failure: function (form, action) {
                    if (action.result) Ext.Msg.alert(me.t('Error'), 'Error: ' + action.result.errors);
                    me.down('[name=file]').reset();
                }
            });
        }
    },
	
	//private
	_serverAction: function (params) {

        var me = this;

		Ext.Ajax.request({
			url: me.managerUrl,
			method: 'POST',
			params: params,
			success: function (response) {
				
				var result = Ext.JSON.decode(response.responseText);
				
				if(result.success)
				{
					var combo = me.down('[name=src]');
                    combo.needsRefresh = true;
                    combo.setRawValue(result.data['src']);
				}else{
					Ext.Msg.alert('Error', 'Error: ' + result.errors);
				}
			}
		});
	},
	
	//private
	_deleteImageClick: function(){
		var me = this;
		var src = me.down('[name=src]').getValue();
		var imageName = src.substring(src.lastIndexOf('/')+1);
		me._deleteImage(null,imageName); 
	},

});