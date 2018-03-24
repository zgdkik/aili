function fullscreen() {
	var el = document.documentElement;

	var rfs = el.requestFullScreen || el.webkitRequestFullScreen ||

	el.mozRequestFullScreen || el.msRequestFullScreen;

	if (typeof rfs != "undefined" && rfs) {

		rfs.call(el);

	} else if (typeof window.ActiveXObject != "undefined") {

		// for IE，这里其实就是模拟了按下键盘的F11，使浏览器全屏

		var wscript = new ActiveXObject("WScript.Shell");

		if (wscript != null) {

			wscript.SendKeys("{F11}");

		}

	}
}
function exitFullscreen() {
	var elem = document;
	if (elem.webkitCancelFullScreen) {
		elem.webkitCancelFullScreen();
	} else if (elem.mozCancelFullScreen) {
		elem.mozCancelFullScreen();
	} else if (elem.cancelFullScreen) {
		elem.cancelFullScreen();
	} else if (elem.exitFullscreen) {
		elem.exitFullscreen();
	} else {
		// 浏览器不支持全屏API或已被禁用
	}
}
$(function() {
	$(".fullscreen-f-e").bind("click", function() {
		var me = $(this);
		var iconCls = me.attr("iconcls");
		if (iconCls == "1") {
			me.attr("iconCls", "2");
			fullscreen();
		} else if(iconCls == "2") {
			me.attr("iconCls", "1");
			exitFullscreen();
		}
	});

	InitLeftMenu();
	tabClose();
	tabCloseEven();
	$('#editpass').click(function() {
		$('#w').window('open');
	});

	$('#btnEp').click(function() {
		serverLogin();
	})

	$('#btnCancel').click(function() {
		closePwd();
	})

	$('#loginOut').click(function() {
		$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
			if (r) {
				window.location.href = base + "/user/logout";
			}
		});
	})
	var sessionInterval = setInterval(function() {
		if ($.cookie('SESSION_COOKIE_KYE') == null
				|| $.cookie('SESSION_COOKIE_KYE') == undefined) {
			clearInterval(sessionInterval);
			$.messager.alert("提示信息", "会话已失效,请重新登录", "info", function() {
				location.href = base + "/user/login";
			});
		}
	}, 5000);
	setTimeout(function() {
		$(".content").css("top", "41px");
		$(".nav-menu").css("top", "41px");

	}, 1000);

})
// 设置登录窗口
function openPwd() {
	// $('#w').window({
	// title : '修改密码',
	// width : 300,
	// modal : true,
	// shadow : true,
	// closed : true,
	// height : 160,
	// resizable : false
	// });
}
// 关闭登录窗口
function closePwd() {
	$('#w').window('close');
}

// 修改密码
function serverLogin() {
	var $newpass = $('#txtNewPass');
	var $rePass = $('#txtRePass');

	if ($newpass.val() == '') {
		msgShow('系统提示', '请输入密码！', 'warning');
		return false;
	}
	if ($rePass.val() == '') {
		msgShow('系统提示', '请在一次输入密码！', 'warning');
		return false;
	}

	if ($newpass.val() != $rePass.val()) {
		msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
		return false;
	}

	$.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
		msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
		$newpass.val('');
		$rePass.val('');
		close();
	})

}
// 初始化左侧
function InitLeftMenu() {
	$("#nav").accordion({
		animate : false
	});// 为id为nav的div增加手风琴效果，并去除动态滑动效果
	$.each(_menus.menus, function(i, n) {// $.each 遍历_menu中的元素
		var menulist = '';
		menulist += '<ul>';
		if(n.menus){
			$.each(n.menus, function(j, o) {
				menulist += '<li><div><a ref="' + o.menuid + '" href="#" rel="'
						+ base + o.url + '" ><span class="icon ' + 'icon-nav'
						+ '" >&nbsp;</span><span class="nav">' + o.menuname
						+ '</span></a></div></li> ';
			})
		}
		menulist += '</ul>';

		$('#nav').accordion('add', {
			title : n.menuname,
			content : menulist,
			iconCls : 'icon ' + n.icon
		});

	});

	$('.easyui-accordion li a').click(function() {// 当单击菜单某个选项时，在右边出现对用的内容
		var tabTitle = $(this).children('.nav').text();// 获取超链里span中的内容作为新打开tab的标题

		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");// 获取超链接属性中ref中的内容
		var icon = getIcon(menuid, icon);

		addTab(tabTitle, url, icon);// 增加tab
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});

	// 选中第一个
//	var panels = $('#nav').accordion('panels');
//	var t = panels[0].panel('options').title;
//	$('#nav').accordion('select', t);
}
// 获取左侧导航的图标
function getIcon(menuid) {
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		if(n.menus!=null){
			$.each(n.menus, function(j, o) {
				if (o.menuid == menuid) {
					icon += "icon-nav";
				}
			})
		}
	})

	return icon;
}
function replaceAll(str) {
	if (str != null) {
		str = str.replace("/", "")
	}
	return str;
}
function addTab(subtitle, url, icon) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		// $("body").mask('正在加载，请等待...');
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			icon : icon
		});
		// $("body").unmask();
	} else {
		$('#tabs').tabs('select', subtitle);
		// $('#mm-tabupdate').click();
	}
	var panels = $('.tabs-panels').find('.panel');
	for (var i = 0; i < panels.length; i++) {
		panels.eq(i).children().css("overflow", "hidden");
	}
	tabClose();
}

function createFrame(url) {
	var strNew = url.replace(new RegExp("/", "gm"), "");
	var s = '<iframe scrolling="auto"  border="0" frameborder="0" id="'
			+ strNew + '"  src="' + url
			+ '" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	})
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : 41
		});

		var subtitle = $(this).children(".tabs-closable").text();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}
// 绑定右键菜单事件
function tabCloseEven() {
	// 刷新
	$('#mm-tabupdate').click(function() {
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update', {
			tab : currTab,
			options : {
				content : createFrame(url)
			}
		})
	})
	// 关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close', currtab_title);
	})
	// 全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			if (t != "主页") {
				$('#tabs').tabs('close', t);
			}
		});
	});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			if (t != "主页") {
				$('#tabs').tabs('close', t);
			}
		});
		return false;
	});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			if (t != "主页") {
				$('#tabs').tabs('close', t);
			}
		});
		return false;
	});

	// 退出
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	})
}

// 弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
