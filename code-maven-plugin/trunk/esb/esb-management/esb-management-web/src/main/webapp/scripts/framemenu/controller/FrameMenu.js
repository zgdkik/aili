if (!window.console || !console.firebug)
{
    var names = ["log", "debug", "info", "warn", "error", "assert", "dir", "dirxml",
    "group", "groupEnd", "time", "timeEnd", "count", "trace", "profile", "profileEnd"];

    window.console = {};
    for (var i = 0; i < names.length; ++i)
        window.console[names[i]] = function() {}
}
Ext.define('esbApp.controller.FrameMenu', {
	    extend : 'Ext.app.Controller',
	    init : function() {
		    this.control({
			        'viewport > treepanel' : {
				        itemclick : treeClickAction
			        }
		        });
	    }
    });

function treeClickAction(node, record) {
	if (record.get('leaf')) {
		var panel = Ext.getCmp(record.id);
		if (!panel) {
			panel = {
				title : record.get('text'),
				iconCls : 'treeNodeLeafIcon',
				html : '<iframe id="iframe_'
				    + record.id
				    + '" src="'
				    + getAppName()
				    + record.data.url
				    + ' " width=100% height=100% marginwidth="0" framespacing="0" marginheight="0" frameborder="0" ></iframe>',
				closable : true
			};
			console.debug(getAppName() + record.data.url);
			openTab(panel, record.id);
		} else {
			var tabpanel = Ext.getCmp('menutab');
			tabpanel.setActiveTab(panel);
		}
	}
};

function openTab(panel, id) {
	var o = (typeof panel == "string" ? panel : id || panel.id);
	var tabpanel = Ext.getCmp('menutab');
	var tab = tabpanel.getComponent(o);
	if (tab) {
		this.getMenuTab().setActiveTab(tab);
	} else if (typeof panel != "string") {
		panel.id = o;
		var p = tabpanel.add(panel);
		tabpanel.setActiveTab(p);
	}
};

function getAppName() {
	var protocol = window.location.protocol;
	var host = window.location.host;
	var str = (window.location.pathname).split("/");
	var count = str.length;
	if (count == 2) {
		return protocol + "//" + host;
	} else {
		return protocol + "//" + host + "/" + str[1];
	}
}

Ext.override(Ext.view.AbstractView, {
	getNode : function(nodeInfo) {
		if (!this.rendered) {
			return null;
		}
		if (Ext.isString(nodeInfo)) {
			return window.document.getElementById(nodeInfo);
		}
		if (Ext.isNumber(nodeInfo)) {
			return this.all.elements[nodeInfo];
		}
		if (nodeInfo instanceof Ext.data.Model) {
			return this.getNodeByRecord(nodeInfo);
		}
		if (window.tabPanel != null) {
			var activeTabId = window.tabPanel.activeTab.id;
			var index = Ext.Array.indexOf(window.tabPanel.items.keys, activeTabId, 1);
			if (Ext.isIE) {
				var frame = window.frames[index];
			} else {
				var frame = window.frames[1 + index];
			}

			if (frame != null && frame.Ext != undefined) {
				if (nodeInfo instanceof frame.Ext.data.Model) {
					return this.getNodeByRecord(nodeInfo);
				}
			}
		}
		return nodeInfo;
	}
});
