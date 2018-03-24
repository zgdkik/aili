var addTab = function(obj) {
	var id = "tab_" + obj.id;
	var tabp = $(".main-content").find(".tabpanel");
	tabp.find(".active").removeClass("active");
	// 如果TAB不存在，创建一个新的TAB
	if (!$("#" + id)[0]) {
		// 固定TAB中IFRAME高度
		// mainHeight = "100%";
		// 创建新TAB的title
		title = '<li role="presentation" id="tab_' + id + '"><a href="#' + id
				+ '" aria-controls="' + id + '" role="tab" data-toggle="tab">'
				+ obj.title;
		// 是否允许关闭
		if (obj.close) {
			title += ' <i class="tabclose" tabclose="' + id
					+ '"><span class="glyphicon glyphicon-remove"></span></i>';
		}
		title += '</a></li>';
		// 是否指定TAB内容
		if (obj.content) {
			content = '<div role="tabpanel" class="tab-pane" id="' + id + '">'
					+ obj.content + '</div>';
		} else { // 没有内容，使用IFRAME打开链接
			content = '<div role="tabpanel" height="100%" width="100%" class="tab-pane" id="'
					+ id
					+ '"><iframe src="'
					+ obj.url
					+ '" width="100%" height="'
					+ mainHeight
					+ '"  id="'
					+ id
					+ 'ifr"'
					+ '%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe></div>';
		}
		// 加入TABS
		tabp.find(".nav-tabs").append(title);
		tabp.find(".tab-content").append(content);
		// iFrameHeight(id);
	}
	// 激活TAB
	$("#tab_" + id).addClass('active');
	$("#" + id).addClass("active");
};

function iFrameHeight(id) {
	id = id + "ifr";
	var ifm = document.getElementById(id);
	var subWeb = document.frames ? document.frames[id].document
			: ifm.contentDocument;
	if (ifm != null && subWeb != null) {
		ifm.height = subWeb.body.scrollHeight;
	}
};
var closeTab = function(id) {
	// 如果关闭的是当前激活的TAB，激活他的前一个TAB
	if ($("li.active").attr('id') == "tab_" + id) {
		$("#tab_" + id).prev().addClass('active');
		$("#" + id).prev().addClass('active');
	}
	// 关闭TAB
	$("#tab_" + id).remove();
	if ($("#" + (id + 1))[0]) {
		$("#tab_" + (id + 1)).addClass('active');
	} else {
		$("#tab_" + (id - 1)).addClass('active');
	}
	$("#" + id).remove();

};
var h = 110;
$(function() {
	mainHeight = window.innerHeight - h;
	$('.main-left,.main-right').height(mainHeight);
	$(".nav-tabs").on("click", "[tabclose]", function(e) {
		id = $(this).attr("tabclose");
		closeTab(id);
	});
	$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
		console.log($(e.target));
	})
});
$(window).resize(function() {
	mainHeight = window.innerHeight - h;
	$("iframe").attr("height",mainHeight);
});
$(function() {
	$(".custom-nav").on(
			"click",
			"a",
			function() {
				if ($.cookie('SESSION_COOKIE_KYE') == null
						|| $.cookie('SESSION_COOKIE_KYE') == undefined) {
					bootbox.confirm("会话已失效,是否重新登录?", function(result) {
						if (result) {
							location.href = base + "/user/login";
						}
					});
					return;
				}
				var me = $(this);
				var title = me.html();
				var url = me.attr("url");
				var menuid = me.attr("mid");
				addTab({
					'id' : menuid,
					'title' : title,
					'url' : url,
					close : true
				});
			})
});