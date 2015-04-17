$(function() {
	var setting = {
		edit : {
			enable : true
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

	$.fn.zTree.init($("#treeDemo"), setting, treeNodes);
	if (treeNodes == null || treeNodes.length == 0) {
		$('#treeDemo').html("没查询到数据");
	}
	$('.update-zk').click(function() {
		var path = $('.zk-path').val();
		if (path == null || path == "") {
			alert("目录为空");
			return;
		}
		if (flag) {
			alert("此目录不支持修改");
			return;
		}
		if (confirm("确定要修改？")) {
			var data = $('.zk-data').val();
			$.ajax({
				url : base + '/updateZkData',
				dataType : 'text json',
				data : {
					"path" : path,
					"data" : data
				},
				type : 'post',
				success : function(data) {
					if (data != null) {
						if (data.success == "true" || data.success == true) {
							alert("修改成功");
						} else {
							alert("修改失败");
						}
					} else {
						alert("修改失败");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("修改失败");
				}
			});
		}
	});

	$('.set-zkhost').click(function() {
		var path = $('.zk-host').val();
		var data = $('.zk-root').val();
		if (path == null || path == "") {
			alert("zk地址为空");
			return;
		}
		if (data == null || data == "") {
			alert("根节点为空");
			return;
		}
		if (confirm("确定要更换？")) {
			$('input').attr("disabled", "disabled");
			$.ajax({
				url : base + '/setZkHost',
				dataType : 'text json',
				data : {
					"zkHost" : path,
					"root" : data
				},
				type : 'post',
				success : function(data) {
					if (data.success == "true" || data.success == true) {
						window.location.href = base;

					} else {
						$('input').removeAttr("disabled");
						alert(data.msg);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('input').removeAttr("disabled");
					alert("更换失败");
				}
			});
		}

	});

	$('.create-path').click(function() {
				var path = $('.zk-path').val();
				if (path == null || path == "") {
					alert("目录为空");
					return;
				}
				var data = $('.zk-data').val();
				$.ajax({
							url : base + '/createPath',
							dataType : 'text json',
							data : {
								"path" : path,
								"data":data
							},
							type : 'post',
							success : function(data) {
								if (data != null) {
									if (data.success == "true"
											|| data.success == true) {
										window.location.href = base;
//										alert("创建成功");
//										var zTree = $.fn.zTree
//												.getZTreeObj("treeDemo");
//										var pid = delNode.id;
//										var name = path.substring(path
//												.lastIndexOf("/") + 1,
//												path.length);
//										var node = {
//											"id" : data.msg,
//											"pId" : pid,
//											"name" : name,
//											"open" : true,
//											"title" : name
//										};
//										zTree.addNodes(delNode, node);
									} else {
										alert(data.msg);
									}
								} else {
									alert("创建失败");
								}
							},
							error : function(XMLHttpRequest, textStatus,
									errorThrown) {
								alert("创建失败");
							}
						});

			});

	$('.del-path').click(function() {
		var path = $('.zk-path').val();
		if (path == null || path == "") {
			alert("请选择要删除的目录");
			return;
		}
		if (confirm("确定要删除？")) {
			$.ajax({
				url : base + '/delPath',
				dataType : 'text json',
				data : {
					"path" : path
				},
				type : 'post',
				success : function(data) {
					if (data != null) {
						if (data.success == "true" || data.success == true) {
							alert("删除成功");
							var zTree = $.fn.zTree.getZTreeObj("treeDemo");
							zTree.removeNode(delNode);
						} else {
							alert(data.msg);
						}
					} else {
						alert("删除失败");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("删除失败");
				}
			});
		}

	});
	var upUrl = base + "/upload;JSESSIONID=" + sid;
	
	$('.import-data').click(function() {
		var path = $('.zk-path').val();
		if (path == null || path == "") {
			alert("请选择要上传的父目录");
			return;
		}
		var data = {'path' : path,
				'JSESSIONID' : sid};
		$.ajaxFileUpload({
			url : upUrl,
			secureuri : false,
			fileElementId : 'uploadify',
			data:data,
			dataType : 'json',
			success : function(data, status) {
				if (data.success) {
					alert("上传成功");
					window.location.href = base;
				} else {
					alert("上传失败:" + data.msg);
				}
			},
			error : function(data, status, e) {
				alert(e);
			}
		});
	});
	$('.reload-users').click(function() {
		// reloadUsers
		if (confirm("确定要重新加载用户？")) {
			$.ajax({
				url : base + '/reloadUsers',
				dataType : 'text json',
				type : 'post',
				success : function(data) {
					alert("加载成功");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("加载失败");
				}
			});
		}
	});

	$('.logout').click(function() {
		// reloadUsers
		if (confirm("确定要退出？")) {
			$.ajax({
				url : base + '/logout',
				dataType : 'text json',
				type : 'post',
				success : function(data) {
					window.location.href = base + "/login";
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}
	});
	$('.export-data').click(function() {
		var path = $('.zk-path').val();
		if (path == null || path == "") {
			alert("请选择要导出的目录");
			return;
		}
		if (confirm("确定要导出？")) {
			window.open(base + "/zkConfigExport?path=" + path);
			// window.location.href = base + "/zkConfigExport?path="+path;
		}
	});
});
var delNode = null;
var flag = false;
function onClick(event, treeId, treeNode, clickFlag) {
	var path = "/" + treeNode.name;
	var id = treeNode.id;
	delNode = treeNode;
	while (treeNode.getParentNode() != null) {
		var p = treeNode.getParentNode();
		if (p.name == null || p.name == "") {
			path = p.name + path;
		} else {
			path = "/" + p.name + path;
		}
		treeNode = p;
	}

	$.post(base + "/getZkData", {
		"path" : path
	}, function(data, status) {
		if (data != null && data != "") {
			$(".zk-path").val(data.path);
			$(".zk-path").attr("zkid", id);
			$(".zk-data").val(data.data);
			flag = false;
		} else {
			$(".zk-path").val(path);
			$(".zk-path").attr("zkid", "");
			$(".zk-data").val("数据序列化错误,不是基本类型,不能修改");
			flag = true;
		}
	});
};

