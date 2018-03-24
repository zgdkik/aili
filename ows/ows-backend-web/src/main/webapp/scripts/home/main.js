Date.prototype.format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/g, "");
};
//String.prototype.rtrim = function() {
//	return this.replace(/(\s*$)/g, "");
//}
(function($) {
	;
	var self = this;

	this.ym = {
		readyfunc : [],
		addReadyFunc : function(func) {
			if (isString(func))
				func = getFunction(func);
			this.readyfunc.push(func);
		},
		sumData:function (data) {
		    field = this.field;
		    return data.reduce(function(sum, row) { 
		    	sum = Math.round((sum+row[field])*100)/100;
		        return sum;
		    }, 0);
		},
		initPage : function(url, options, tblId, columns) {
			if ($.cookie('SESSION_COOKIE_KYE') == null
					|| $.cookie('SESSION_COOKIE_KYE') == undefined) {
				layer.alert("会话已失效,请重新登录", function(result) {
					location.href = base + "/user/login";
				});
				return;
			}
			if (options) {
				options.url = url;
				options.columns = columns;
			} else {
				options = {};
				options.url = url;
				options.columns = columns;
			}
			return $(tblId).bootstrapTable('destroy').bootstrapTable(options);
		},
		encodeURL : function(url, withTimeStamp) {
			if (withTimeStamp == undefined || withTimeStamp)
				return encURL(url, "tt");
			else
				return encURL(url);
		},
		async : function(url, data, args, submitType, dataType, async, login) {
			if (login) {
				if ($.cookie('SESSION_COOKIE_KYE') == null
						|| $.cookie('SESSION_COOKIE_KYE') == undefined) {
					layer.alert("会话已失效,请重新登录", function(result) {
						location.href = base + "/user/login";
					});
					return;
				}
			}
			url = this.encodeURL(url);
			if (dataType && "json" == dataType) {
				data = JSON.stringify(data);
			}
			var options = _ajaxOptions(url, data, args);
			if (submitType) {
				options.type = submitType;
			}
			if (dataType && "json" == dataType) {
				options.dataType = dataType;
				options.contentType = "application/json; charset=utf-8";
			}
			if (async) {
				options.async = false;
			}
			$.extend(options, {
				success : function(data, textStatus, jqXHR) {
					if (this.successHandler) {
						hitch(this, "successHandler")(data, textStatus, jqXHR);
					} else if (data && data.success) {
						if (data.msg != null && data.msg != "") {
							layer.alert(data.msg, {icon: 6});
						}
					} else if (this.failHandler) {
						hitch(this, "failHandler")(data, textStatus, jqXHR);
					} else if (data) {
						layer.alert(data.msg, {icon: 6});
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					if (this.errorHandler) {
						hitch(this, "errorHandler")(jqXHR, textStatus,
								errorThrown);
					} else {
						switch (jqXHR.status) {
						case 403:
							bootbox.alert("权限不足");
							break;
						case 404:
							bootbox.alert("请求不存在");
							break;
						default:
							bootbox.alert("系统内部错误");
						}
					}
				}
			});
			
			$.ajax(options);
		},
		formatDate : function(strTime) {
			if (strTime == null || strTime == "") {
				return "";
			}
			strTime = strTime + "";
			if (strTime.indexOf(".") > 0) {
				strTime = strTime.substr(0, strTime.indexOf("."))
			}
			var t = Date.parse(strTime.replace(/-/g, "/")) + "";
			if (t == "NaN") {
				t = parseInt(strTime);
			} else {
				t = parseInt(t);
			}
			var date = new Date(t).format("yyyy-MM-dd");
			return date;
		},
		formatTime : function(strTime) {
			if (strTime == null || strTime == "") {
				return "";
			}
			strTime = strTime + "";
			if (strTime.indexOf(".") > 0) {
				strTime = strTime.substr(0, strTime.indexOf("."))
			}
			var t = Date.parse(strTime.replace(/-/g, "/")) + "";
			if (t == "NaN") {
				t = parseInt(strTime);
			} else {
				t = parseInt(t);
			}
			var time = new Date(t).format("yyyy-MM-dd hh:mm:ss");
			return time;
		},
		sendGet : function(url, data, args, async, login) {
			this.async(url, data, args, "GET", null, async, login);
		},
		sendPost : function(url, data, args, async, login) {
			this.async(url, data, args, "POST", null, async, login);
		},
		sendJson : function(url, data, args, async, login) {
			this.async(url, data, args, "POST", "json", async, login);
		},
		initTable : function(context) {

		},
		selectAjax : function(url, selector) {
			if (!selector) {
				selector = ".selectpicker-ajax";
			}
			$(selector).select2({
				ajax : {
					url : url,// base+"/demo/searchSelect",
					dataType : 'json',
					type : 'POST',
					delay : 250,

					data : function(params) {
						return {
							'q_sl_name' : params.term, // search term
							'page.pageSize' : 10
						};
					},
					processResults : function(data, params) {
						data.pageSize = data.pageSize || 1;
						return {
							results : data.list,
							pagination : {
								more : (data.pageSize * 30) < data.count
							}
						};
					},
					cache : true
				},
				escapeMarkup : function(markup) {
					return markup;
				}, // let our
				// custom
				// formatter
				// work
				minimumInputLength : 1,
				templateResult : formatRepo, // omitted for brevity, see the
				// source of this page
				templateSelection : formatRepoSelection
			// omitted for brevity,
			// see the source of
			// this page
			});
		},
		setFormObj:function (formid,obj){
			 for ( var p in obj ){
				 $("#"+formid).find('input[type="text"][name="'+p+'"]').val(obj[p]);
				 $("#"+formid).find('input[type="hidden"][name="'+p+'"]').val(obj[p]);
				 $("#"+formid).find('textarea[name="'+p+'"]').html(obj[p]);
			 }
		},
		resetForm:function(form){
			$(form).find(":input").not(":button,:submit,:reset,:hidden").val(null).removeAttr("checked").removeAttr("selected");
		},
		getParamStr:function(formObj){
			var index = 0;
			paramStr = "";
			for ( var p in formObj) {
				var name = p;
				var val = formObj[p];
				if (val != null && val != '') {
					if (index == 0) {
						paramStr = paramStr + "?" + name + "=" + val;
					}
					else {
						paramStr = paramStr + "&" + name + "=" + val;
					}
					index++;
				}
			}
			 return paramStr;
		}
	};

	$(document).ready(function() {
		$('.modal').on('hide.bs.modal', function() {
			try {
				$('.modal').find("form").each(function() {
					try {
						$(this).data('bootstrapValidator').resetForm(true);
					} catch (e) {
						console.log(e);
					}
					try {
						$(this)[0].reset();
					} catch (e) {
						console.log(e);
					}
					try {
						$(this).find("textarea").html(null);
						ym.resetForm(this);			
					} catch (e) {
						console.log(e);
					}
					
				});
			} catch (e) {
				console.log(e);
			}
		});
		$('.modal').on('click.dismiss.modal', function() {
		});

		bootbox.setDefaults({
			locale : "zh_CN"
		});

		// SELECT
		$('.selectpicker').select2({});

		// ICHECK
		$('input:not(.ios-switch)').iCheck({
			checkboxClass : 'icheckbox_square-aero',
			radioClass : 'iradio_square-aero',
			increaseArea : '20%' // optional
		});

		// MULTI SELECT
		// $("select[multiple='multiple']").multiSelect();

		// TOOLTIP
		$('body').tooltip({
			selector : "[data-toggle=tooltip]",
			container : "body"
		});

		$("select").each(function(i, obj) {
			var me = $(this);
			var selected = me.data("selected");
			if (selected && selected != null && selected != "") {
				me.val(selected).trigger("change");
			}
		});

		// 初始化时间空间
		$('.form_datetime').datetimepicker({
			locale : 'zh-CN',
			format : 'YYYY-MM-DD HH:mm:ss',
			calendarWeeks : false
		});
		
		$('.form_datetime_d').datetimepicker({
			locale : 'zh-CN',
			format : 'YYYY-MM-DD HH:mm:ss',
			calendarWeeks : false,
			defaultDate:new Date()
		});
		$('.form-mm').datetimepicker({  
			locale : 'zh-CN',
			format : 'YYYY-MM',
			calendarWeeks : false,
			defaultDate:new Date()
		}); 
		$('.form_date_d').datetimepicker({
			locale : 'zh-CN',
			viewMode : 'days',
			format : 'YYYY-MM-DD',
			defaultDate:new Date()
		});

		$('.form_date').datetimepicker({
			locale : 'zh-CN',
			viewMode : 'days',
			format : 'YYYY-MM-DD'
		});
		
		$('.form_time').datetimepicker({
			format : 'LT',
			locale : 'zh-CN'
		});

		$.fn.getFormObj = function() {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		};

		$(".modal").draggable({
			handle : ".modal-header",
			cursor : 'move',
			refreshPositions : false
		});
//		if($(".panel").css("height")){
//			var qf = $(".panel").css("height").replace("px","");
//			var h2 =window.parent.innerHeight -qf-180;
//			var tbl = $("table").eq(0);
//			tbl.data("height",h2);
//			tbl.removeAttr("data-show-toggle");
//			tbl.removeAttr("data-show-columns");
//		}
	
		$("table").each(function(index,obj){
			if(!$(obj).data("height")){
				var qf = $(".panel").css("height").replace("px","");
				var h =window.parent.innerHeight -qf-180;
				$(obj).data("height",h);
			}
		});
	});

	//$('.startTime').data("DateTimePicker").defaultDate(new Date());
	$(".startTime").on("dp.change",function (e) {
        $('.endTime').data("DateTimePicker").minDate(e.date);
    });
	//$('.endTime').data("DateTimePicker").defaultDate(new Date());
    $(".endTime").on("dp.change",function (e) {
        $('.startTime').data("DateTimePicker").maxDate(e.date);
    });
    
})(jQuery);

var _self = this;
// 获得当前页面的Locale,或者设置Locale
function pageLocale(locale) {
	if (locale === undefined)
		return $("body").attr("locale") || "zh-CN";
	else
		$("body").attr("locale", locale);
}

// 首字母大写
function capitaliseFirstLetter(str) {
	return str.charAt(0).toUpperCase() + str.slice(1);
}

// 判断对象是否是字符串
function isString(obj) {
	return typeof obj === "string" || obj instanceof String;
}

// 根据名称获得一个函数。函数名中可以用‘.’去调用context中子对象的方法，context默认是页面对象
function getFunction(funcname, context) {
	context = context || _self;
	var namespaces = funcname.split(".");
	for (var i = 0; i < namespaces.length; i++) {
		context = context(namespaces[i]);
		if (context == undefined)
			throw "Function is undefined:" + funcname;
	}
	return context;
}

function integer(value) {
	var v = parseInt(value, 10);
	return isNaN(v) ? null : v;
}

function float(value) {
	var v = parseFloat(value);
	return isNaN(v) ? null : v;
}

// 调用方法
function hitch(scope, method) {
	if (!method) {
		method = scope;
		scope = null;
	}
	if (isString(method)) {
		scope = scope || _self;
		if (!scope[method]) {
			throw ([ 'hitch: scope["', method, '"] is null (scope="', scope,
					'")' ].join(''));
		}
		return function() {
			return scope[method].apply(scope, arguments || []);
		}; // Function
	}
	return !scope ? method : function() {
		return method.apply(scope, arguments || []);
	};
}

// 获取对象值
function getObject(propName, context) {
	context = context || _self;
	var parts = propName.split(".");
	for (var i = 0, pn; context && (pn = parts[i]); i++) {
		context = (pn in context ? context[pn] : undefined);
	}
	return context;
}

// 设置对象值
function setObject(propName, value, context) {
	context = context || _self;
	var parts = propName.split(".");
	var p = parts.pop();
	for (var i = 0, pn; context && (pn = parts[i]); i++) {
		context = (pn in context ? context[pn] : context[pn] = {});
	}
	return (context && p ? (context[p] = value) : undefined);
}

// 返回延时调用函数
function debounce(func, wait, immediate) {
	var timeout, result;
	return function() {
		var args = arguments;
		var later = function() {
			timeout = null;
			if (!immediate)
				result = func(args);
		};
		var callNow = immediate && !timeout;
		clearTimeout(timeout);
		timeout = setTimeout(later, wait);
		if (callNow)
			result = func(args);
		return result;
	};
}

// 为URL加上时间戳，时间戳的名称可以自己定义，默认是timeflag
function tURL(url, flag) {
	flag = flag || "timeflag";
	var iTime = (new Date()).getTime(), pattern = new RegExp(flag + "=\\d{13}");
	if (url.indexOf(flag + "=") >= 0) {
		url = url.replace(pattern, flag + "=" + iTime.toString());
		return url;
	}
	url += (/\?/.test(url)) ? "&" : "?";
	return (url + flag + "=" + iTime.toString());
}

// encode URL
function encURL(url, flag) {
	var index = url.indexOf("?");
	if (index === -1)
		return flag ? tURL(url, flag) : url;

	var result = url.substring(0, index + 1), params = url.substring(index + 1)
			.split("&");

	for (var i = 0; i < params.length; i++) {
		if (i > 0)
			result += "&";
		var param = params[i].split("=");
		result += param[0] + "=" + encodeURIComponent(param[1]);
	}

	return flag ? tURL(result, flag) : result;
}

/* compose ajax call options */
function _ajaxOptions(url, data, args) {
	var options = {};
	if (arguments.length === 1)
		options = url;
	else {
		options = args || {};
		options["url"] = url;
		if (data) {
			$.extend(options, {
				data : data
			});
		}
	}
	// console.dir(options);
	return options;
}

function propertyToArray(data, p) {
	var o = data[p];
	if (!(o instanceof Array)) {
		a = new Array();
		a.push(o);
		data[p] = a;
	}
	return data;
}

function formList(list, p) {
	var data = "";
	for (var i = 0; i < list.length; i++) {
		if (i == 0) {
			data = list[i][p];
		} else {
			data = data + "," + list[i][p];
		}
	}
	return data;
}
function formatRepo(repo) {
	if (repo.loading)
		return repo.text;
	var markup = "<div class='select2-result-repository clearfix'>"
			+ "<div class='select2-result-repository__meta'>"
			+ "<div class='select2-result-repository__title'>" + repo.name
			+ "</div>";

	if (repo.descp) {
		markup += "<div class='select2-result-repository__description'>"
				+ repo.descp + "</div>";
	}
	markup += "</div></div>";
	return markup;
}
function formatRepoSelection(repo) {
	return repo.name || repo.text;
}

(function() {
	"use strict";

	// Toggle Left Menu
	jQuery('.menu-list > a').click(function() {

		var parent = jQuery(this).parent();
		var sub = parent.find('> ul');

		if (!jQuery('body').hasClass('left-side-collapsed')) {
			if (sub.is(':visible')) {
				sub.slideUp(200, function() {
					parent.removeClass('nav-active');
					jQuery('.main-content').css({
						height : ''
					});
					mainContentHeightAdjust();
				});
			} else {
				visibleSubMenuClose();
				parent.addClass('nav-active');
				sub.slideDown(200, function() {
					mainContentHeightAdjust();
				});
			}
		}
		return false;
	});

	function visibleSubMenuClose() {
		jQuery('.menu-list').each(function() {
			var t = jQuery(this);
			if (t.hasClass('nav-active')) {
				t.find('> ul').slideUp(200, function() {
					t.removeClass('nav-active');
				});
			}
		});
	}

	function mainContentHeightAdjust() {
		// Adjust main content height
		var docHeight = jQuery(document).height();
		if (docHeight > jQuery('.main-content').height())
			jQuery('.main-content').height(docHeight-50);
	}

	// class add mouse hover
	jQuery('.custom-nav > li').hover(function() {
		jQuery(this).addClass('nav-hover');
	}, function() {
		jQuery(this).removeClass('nav-hover');
	});

	// Menu Toggle
	jQuery('.toggle-btn').click(function() {
		$(".left-side").getNiceScroll().hide();

		if ($('body').hasClass('left-side-collapsed')) {
			$(".left-side").getNiceScroll().hide();
		}
		var body = jQuery('body');
		var bodyposition = body.css('position');

		if (bodyposition != 'relative') {

			if (!body.hasClass('left-side-collapsed')) {
				body.addClass('left-side-collapsed');
				jQuery('.custom-nav ul').attr('style', '');

				jQuery(this).addClass('menu-collapsed');

			} else {
				body.removeClass('left-side-collapsed chat-view');
				jQuery('.custom-nav li.active ul').css({
					display : 'block'
				});

				jQuery(this).removeClass('menu-collapsed');

			}
		} else {

			if (body.hasClass('left-side-show'))
				body.removeClass('left-side-show');
			else
				body.addClass('left-side-show');

			mainContentHeightAdjust();
		}

	});

	searchform_reposition();

	jQuery(window).resize(function() {

		if (jQuery('body').css('position') == 'relative') {

			jQuery('body').removeClass('left-side-collapsed');

		} else {

			jQuery('body').css({
				left : '',
				marginRight : ''
			});
		}

		searchform_reposition();

	});

	function searchform_reposition() {
		if (jQuery('.searchform').css('position') == 'relative') {
			jQuery('.searchform').insertBefore('.left-side-inner .logged-user');
		} else {
			jQuery('.searchform').insertBefore('.menu-right');
		}
	}

	// panel collapsible
	$('.panel .tools .fa').click(function() {
		var el = $(this).parents(".panel").children(".panel-body");
		if ($(this).hasClass("fa-chevron-down")) {
			$(this).removeClass("fa-chevron-down").addClass("fa-chevron-up");
			el.slideUp(200);
		} else {
			$(this).removeClass("fa-chevron-up").addClass("fa-chevron-down");
			el.slideDown(200);
		}
	});

	$('.todo-check label').click(
			function() {
				$(this).parents('li').children('.todo-title').toggleClass(
						'line-through');
			});

	$(document).on('click', '.todo-remove', function() {
		$(this).closest("li").remove();
		return false;
	});

	$("#sortable-todo").sortable();

	// panel close
	$('.panel .tools .fa-times').click(function() {
		$(this).parents(".panel").parent().remove();
	});

	// tool tips

	$('.tooltips').tooltip();

	// popovers

	$('.popovers').popover();

})(jQuery);

var setting = {
		async : {
			enable : true,
			url : base + "/privilege/getTree",
			autoParam : [ "id", "name" ],
			dataFilter : filter
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : menuClick
		}
	};
function filter(treeId, parentNode, childNodes) {
	if (!childNodes)
		return null;
	for (var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}
function menuClick(event, treeId, treeNode, clickFlag) {

}
