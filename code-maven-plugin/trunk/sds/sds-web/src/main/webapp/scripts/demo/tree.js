/** *************1. 基本信息的设定********************** */

var setting = {
	check : {
		enable : true
	},
	async : {
		enable : true,
		url : base+"/demo/getTree",
		autoParam : [ "id", "name" ],
		dataFilter : filter
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : onClick
	}
};
function onClick(event, treeId, treeNode, clickFlag) {
	alert();
}

function filter(treeId, parentNode, childNodes) {
	if (!childNodes)
		return null;
	for ( var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}
function filter(treeId, parentNode, childNodes) {
	if (!childNodes)
		return null;
	for ( var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}

$(document).ready(function() {

	$.fn.zTree.init($("#treeDemo"), setting);
});
