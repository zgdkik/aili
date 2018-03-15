Date.prototype.format = function(fmt)   {
  var o = {   
    "M+" : this.getMonth()+1,                 // 月份
    "d+" : this.getDate(),                    // 日
    "h+" : this.getHours(),                   // 小时
    "m+" : this.getMinutes(),                 // 分
    "s+" : this.getSeconds(),                 // 秒
    "q+" : Math.floor((this.getMonth()+3)/3), // 季度
    "S"  : this.getMilliseconds()             // 毫秒
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
};
String.prototype.trim=function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
 };
 String.prototype.ltrim=function(){
    return this.replace(/(^\s*)/g,"");
 };
 String.prototype.rtrim=function(){
    return this.replace(/(\s*$)/g,"");
 };
function centerModals() {   
    $('.modal').each(function(i) {   
        var $clone = $(this).clone().css('display', 'block').appendTo('body'); var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);   
        top = top > 0 ? top : 0;   
        $clone.remove();   
        $(this).find('.modal-content').css("margin-top", top);   
    });   
}   
(function($){;var self = this;

this.sds = {

    readyfunc: [],

    i18nResources: {},

    defaultErrors: {
        "e-1":"error-message-sys-1",
        "e-4":"error-message-sys-4",
        "e-5":"error-message-sys-5",
        "e-403":"error-message-sys-403",
        "e-404":"error-message-sys-404",
        "e-500":"error-message-sys-500"
    },

    addReadyFunc: function(func){
        if(isString(func)) func = getFunction(func);
        this.readyfunc.push(func);
    },

    documentReady: function(){
        for(var i=0; i< this.readyfunc.length; i++){
            this.readyfunc[i]();
        }
    },

    addResource: function(locale, res){
        if(res){
            if(sds.i18nResources[locale]){
                $.extend(sds.i18nResources[locale]["translation"],res);
            }else{
                sds.i18nResources[locale] = {"translation": res};
            }
        }
    },
    initPage:function (url,options,tblId,columns) {
    	if($.cookie('SEESION_CLUSTER_TOKEN') == null || $.cookie('SEESION_CLUSTER_TOKEN') == undefined){
			bootbox.confirm("会话已失效,是否重新登录?", function(result) {
				if(result){
					location.href = base + "/user/login";
				}
			});
			return;
		}
    	if(options){
    		options.url = url;
    		options.columns=columns;
    	}else{
    		options={};
    		options.url = url;
    		options.columns=columns;
    	}
    	$(tblId).bootstrapTable('destroy').bootstrapTable(options);
    },
    encodeURL: function(url, withTimeStamp){
        if(withTimeStamp == undefined || withTimeStamp)
            return encURL(url,"tt");
        else
            return encURL(url);
    },
    async: function(url, data, args, submitType,dataType,async){
		if($.cookie('SEESION_CLUSTER_TOKEN') == null || $.cookie('SEESION_CLUSTER_TOKEN') == undefined){
			bootbox.confirm("会话已失效,是否重新登录?", function(result) {
				if(result){
					location.href = base + "/user/login";
				}
			});
			return;
		}
    	url = this.encodeURL(url);
    	if(dataType && "json"==dataType){
    		 data = JSON.stringify(data);
    	}
        var options = _ajaxOptions(url, data, args);
        $.extend(options, {
            success: function( data, textStatus, jqXHR){
            	 if(this.successHandler){
                     hitch(this,"successHandler")(data, textStatus, jqXHR);
                 }else if(data && data.success){
                     if(data.msg!=null && data.msg!=""){
                     	sds.frame.notifySuccess("系统提示", data.msg);
                     }
                 }else if(this.failHandler){
                	 hitch(this,"failHandler")(data, textStatus, jqXHR);
                 }else if(data && data.exception){
                      sds.frame.notifyWarning("系统提示",data.msg);
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                if(this.errorHandler){
                    hitch(this,"errorHandler")(jqXHR, textStatus, errorThrown);
                }else{
                    switch(jqXHR.status){
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
        if(submitType){
            options.type = submitType;
        }
        if(dataType && "json"==dataType){
            options.dataType = dataType;
            options.contentType = "application/json; charset=utf-8";
        }
    	if(async){
    		options.async=async;
   	}
        $.ajax(options);
    },
    formatDate: function(strTime){
    	if(strTime==null || strTime==""){
    		return  "";
    	}
    	strTime=strTime+"";
    	if(strTime.indexOf(".")>0){
    		strTime = strTime.substr(0,strTime.indexOf("."))
    	}
    	var t = Date.parse(strTime.replace(/-/g, "/"))+"";
    	if(t=="NaN"){
    		t = parseInt(strTime);
    	}else{
    		t = parseInt(t);
    	}
    	var date = new Date(t).format("yyyy-MM-dd");
    	return date;
    },
    formatTime: function(strTime){
    	if(strTime==null || strTime==""){
    		return  "";
    	}
    	strTime=strTime+"";
    	if(strTime.indexOf(".")>0){
    		strTime = strTime.substr(0,strTime.indexOf("."))
    	}
    	var t = Date.parse(strTime.replace(/-/g, "/"))+"";
    	if(t=="NaN"){
    		t = parseInt(strTime);
    	}else{
    		t = parseInt(t);
    	}
    	var time = new Date(t).format("yyyy-MM-dd hh:mm:ss");
   	    return time;
    },
    sendGet: function(url, data, args,async){
        this.async(url,data,args,"GET",null,async);
    },
    sendPost: function(url, data, args,async){
        this.async(url,data,args,"POST",null,async);
    },
    sendJson: function(url, data, args,async){
    	this.async(url,data,args,"POST","json",async);
    },
    initTable: function(context){
    	
    },
    selectAjax:function(url,selector){
    	if(!selector){
    		selector = ".selectpicker-ajax";
    	}
    	 $(selector).select2({
	    	  ajax: {
	    	    url: url,// base+"/demo/searchSelect",
	    	    dataType: 'json',
	    	    type:'POST',
	    	    delay: 250,
	    	    
	    	    data: function (params) {
	    	      return {
	    	        'q_sl_name': params.term, // search term
	    	        'page.pageSize': 10
	    	      };
	    	    },
	    	    processResults: function (data, params) {
	    	    	data.pageSize = data.pageSize || 1;
	    	      return {
	    	        results: data.list,
	    	        pagination: {
	    	          more: (data.pageSize * 30) < data.count
	    	        }
	    	      };
	    	    },
	    	    cache: true
	    	  },
	    	  escapeMarkup: function (markup) { return markup; }, // let our
																	// custom
																	// formatter
																	// work
	    	  minimumInputLength: 1,
	    	  templateResult: formatRepo, // omitted for brevity, see the
											// source of this page
	    	  templateSelection: formatRepoSelection // omitted for brevity,
														// see the source of
														// this page
	    	});
    }
};

sds.addResource("zh-CN",{
    'error-message-sys-1': '系统异常，请稍候再试或联系系统管理员',
    'error-message-sys-4': '数据绑定异常',
    'error-message-sys-5': '数据验证失败，请检查输入后重试',
    'error-message-sys-403': '您无权访问，请联系系统管理员',
    'error-message-sys-404': '请求页面未找到',
    'error-message-sys-500': '系统异常，请稍候再试或联系系统管理员',
    'pager-first': '第一页',
    'pager-last': '末页',
    'pager-prev': '前一页',
    'pager-next': '后一页',
    'sortable-select-all': '全选',
    'sortable-unselect-all': '取消选择',
    'sortable-batch-action': '批量操作'
});

$(document).ready(function(){
	$('.modal').on('hide.bs.modal', function() {
		try {
			$('.modal').find("form").each(function(){
				$(this).data('bootstrapValidator').resetForm(true);
			});
		} catch (e) {
		}
	});
	$('.modal').on('click.dismiss.modal', function() {
	});
    if(pageRes = self.pageI18nResources){
        for(key in pageRes)
            sds.addResource(key, pageRes[key]);
    }
//    $(".modal").draggable({   
//        handle: ".modal-header",   
//        cursor: 'move',   
//        refreshPositions: false  
//    });
    centerModals();
    $(window).on('resize', centerModals);
    i18n.init({lng: pageLocale(), resStore: sds.i18nResources});
    bootbox.setDefaults({locale: "zh_CN"});

    if(self.loadMenu)
        self.loadMenu();

    FastClick.attach(document.body);
    
    sds.documentReady();
    
    
    $('.animate-number').each(function(){
        $(this).animateNumbers($(this).attr("data-value"), true, parseInt($(this).attr("data-duration")));
    });

    // SELECT
    $('.selectpicker').select2({
    });

    // ICHECK
    $('input:not(.ios-switch)').iCheck({
        checkboxClass: 'icheckbox_square-aero',
        radioClass: 'iradio_square-aero',
        increaseArea: '20%' // optional
    });

    // IOS7 SWITCH
    $(".ios-switch").each(function(){
        mySwitch = new Switch(this);
    });

    // MULTI SELECT
    // $("select[multiple='multiple']").multiSelect();

    // TOOLTIP
    $('body').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    });

    sds.initTable();

    $("select").each(function(i,obj){
    	var me = $(this);
    	var selected = me.data("selected");
    	if(selected && selected!=null && selected!=""){
    		me.val(selected).trigger("change");
    	}
    });
    /* 菜单初始化 */
    // 为有子菜单的菜单项添加'>'符号
    $('#menu>ul>li>ul').find('li:has(ul:not(:empty))>a').append("<span class='arrow'>></span>");
    
    $("#menu>ul>li").on('mouseenter',function(){
		// 顶级菜单项的鼠标移入操作
    	$(this).children('ul').slideDown('fast'); 
	}).on('mouseleave',function(){
    	// 顶级菜单项的鼠标移出操作
    	$(this).children('ul').slideUp('fast'); 
    });
    
    $('#menu>ul>li>ul li').on('mouseover',function(){
    	// 子菜单的鼠标移入操作
    	$(this).children('ul').slideDown('fast'); 
    }).on('mouseleave',function(){
    	// 子菜单的鼠标移出操作
    	$(this).children('ul').slideUp('fast'); 
    });
    //个人中心菜单效果
    $("#menu>ul>li").on('mouseover',function(){
		// 顶级菜单项的鼠标移入操作
    	$(this).children('ul').slideDown('fast'); 
	}).on('mouseleave',function(){
    	// 顶级菜单项的鼠标移出操作
    	$(this).children('ul').slideUp('fast'); 
    });
    $("#menu1>ul>li").on('mouseover',function(){
		// 顶级菜单项的鼠标移入操作
    	$(this).children('ul').slideDown('fast'); 
	}).on('mouseleave',function(){
    	// 顶级菜单项的鼠标移出操作
    	$(this).children('ul').slideUp('fast'); 
    });
    
    $('#pwd-change-form').bootstrapValidator({
		message : '输入值无效',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			password : {
				validators : {
					notEmpty : {
						message : '密码不能为空'
					},
					stringLength : {
						min : 6,
						max : 20,
						message : '密码6位到20之间'
					},
				}
			},
			newPassword : {
				validators : {
					notEmpty : {
						message : '新密码不能为空'
					},
					stringLength : {
						min : 6,
						max : 20,
						message : '密码6位到20之间'
					},
				}
			},
			confirmPassword : {
				validators : {
					notEmpty : {
						message : '确认输入不能为空'
					},
					identical : {
						field : 'newPassword',
						message : '确认密码与新密码不一致'
					},
					stringLength : {
						min : 6,
						max : 20,
						message : '密码6位到20之间'
					},
				}
			}
		}
	});
    
    $(".submit-pwd").on("click",function(){
    	$('#pwd-change-form').data('bootstrapValidator').validate();
    	var validate = $('#pwd-change-form').data('bootstrapValidator').isValid();
    	if(!validate){
    		return;
    	}
    	var param = $("#pwd-change-form").getFormObj();
    	sds.sendPost(base+"/user/changePassword",param,{
    		successHandler:function(data, textStatus, jqXHR){
    			if(data.success){
    				bootbox.alert(data.msg,function(){
        				$("#pwd-change").modal('hide');
        				$("#pwd-change").find("[name=password]").val(null);
        				$("#pwd-change").find("[name=newPassword]").val(null);
        				$("#pwd-change").find("[name=confirmPassword]").val(null);
        			});
    			}else{
    				bootbox.alert(data.msg);
    			}
    		}
    	});
    });
    $("body").on("click",".logout-close",function(){
    	$("#logout-modal").removeClass("md-show");
    });
    
});
var wfm = sds.frame = {
    w: 0,
    h: 0,
    dw: 0,
    dh: 0,
    resizefunc: [],
    notifyOptions: {
        style: 'metro',
        globalPosition:'top center',
        hideAnimation: "slideUp",
        showDuration: 0,
        hideDuration: 1000,
        autoHideDelay: 3000,
        autoHide: true,
        clickToHide: true
    },

    addResizeFunc: function(func){
        if(isString(func)) func = getFunction(func);
        this.resizefunc.push(func);
        return this;
    },

    afterResize: function(){
        // do resize
        for(var i=0; i< wfm.resizefunc.length; i++){
            wfm.resizefunc[i]();
        }
    },

    initscrolls: function(){
        if(jQuery.browser.mobile !== true){
            // SLIM SCROLL
            $('.slimscroller').slimscroll({
                height: 'auto',
                size: "5px"
            });

            $('.slimscrollleft').slimScroll({
                height: 'auto',
                position: 'left',
                size: "5px",
                color: '#7A868F'
            });
        }
    },

    togglescroll: function(item){
        if($("#wrapper").hasClass("enlarged")){
            $(item).css("overflow","inherit").parent().css("overflow","inherit");
            $(item). siblings(".slimScrollBar").css("visibility","hidden");
        }else{
            $(item).css("overflow","hidden").parent().css("overflow","hidden");
            $(item). siblings(".slimScrollBar").css("visibility","visible");
        }
    },

    frameChange: function(){
        wfm.w = $(window).width();
        wfm.h = $(window).height();
        wfm.dw = $(document).width();
        wfm.dh = $(document).height();

        if(jQuery.browser.mobile === true){
            $("body").addClass("mobile").removeClass("fixed-left");
        }

        if(!$("#wrapper").hasClass("forced")){
            if(wfm.w > 990){
                $("body").removeClass("smallscreen").addClass("widescreen");
                $("#wrapper").removeClass("enlarged");
            }else{
                $("body").removeClass("widescreen").addClass("smallscreen");
                $("#wrapper").addClass("enlarged");
                $(".left ul").removeAttr("style");
            }
            if($("#wrapper").hasClass("enlarged") && $("body").hasClass("fixed-left")){
                $("body").removeClass("fixed-left").addClass("fixed-left-void");
            }else if(!$("#wrapper").hasClass("enlarged") && $("body").hasClass("fixed-left-void")){
                $("body").removeClass("fixed-left-void").addClass("fixed-left");
            }

        }
        wfm.togglescroll(".slimscrollleft");
    },
    notifyError: function(title, text){
        if(text == undefined) {
            text = title;
            title = null;
        }
        var n = {
            text: text,
            image: "<i class='icon-alert'></i>"
        };
        if(title)
            $.extend(n,{title:title});
        $.notify(n, $.extend({className: 'error'},sds.frame.notifyOptions));
    },

    notifyWarning: function(title, text){
        if(text == undefined) {
            text = title;
            title = null;
        }
        var n = {
            text: text,
            image: "<i class='icon-attention'></i>"
        };
        if(title)
            $.extend(n,{title:title});
        $.notify(n, $.extend({className: 'warning'},sds.frame.notifyOptions));
    },

    notifyInfo: function(title, text){
        if(text == undefined) {
            text = title;
            title = null;
        }
        var n = {
            text: text,
            image: "<i class='icon-info-circled'></i>"
        };
        if(title)
            $.extend(n,{title:title});
        $.notify(n, $.extend({className: 'info'},sds.frame.notifyOptions));
    },

    notifySuccess: function(title, text){
        if(text == undefined) {
            text = title;
            title = null;
        }
        var n = {
            text: text,
            image: "<i class='icon-info-circled'></i>"
        };
        if(title)
            $.extend(n,{title:title});
        $.notify(n, $.extend({className: 'success'},sds.frame.notifyOptions));
        
    },

    blockUIItem: function(item) {
        item = item || "#wrapper";
        $(item).block({
            message: '<div class="loading"></div>',
            css: {
                border: 'none',
                width: '14px',
                backgroundColor: 'none'
            },
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.4,
                cursor: 'wait'
            }
        });
    },

    unblockUIItem: function(item) {
        item = item || "#wrapper";
        $(item).unblock();
    },

    // learn from http://davidwalsh.name/fullscreen
    fullScreen: function(element) {
        if(element.requestFullscreen) {
            element.requestFullscreen();
        } else if(element.mozRequestFullScreen) {
            element.mozRequestFullScreen();
        } else if(element.webkitRequestFullscreen) {
            element.webkitRequestFullscreen();
        } else if(element.msRequestFullscreen) {
            element.msRequestFullscreen();
        }
    },

    exitFullscreen: function() {
        if(document.exitFullscreen) {
            document.exitFullscreen();
        } else if(document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if(document.webkitExitFullscreen) {
            document.webkitExitFullscreen();
        }
    },

    toggleFullscreen: function(){
        if(this.isFullScreenEnabled()){
            if(!this.getFullScreenElement()) {
                this.fullScreen(document.documentElement);
            }else{
                this.exitFullscreen();
            }
        }
    },

    getFullScreenElement: function(){
        return document.fullscreenElement || document.mozFullScreenElement || document.webkitFullscreenElement || document.msFullscreenElement;
    },

    isFullScreenEnabled: function(){
        return document.fullscreenEnabled || document.mozFullScreenEnabled || document.webkitFullscreenEnabled;
    }

};

wfm.addResizeFunc(wfm.initscrolls).addResizeFunc(wfm.frameChange);

sds.addReadyFunc(function(){
    $(window).resize(debounce(wfm.afterResize,100));
    // 初始化时间空间
    $('.form_datetime').datetimepicker({
    	locale: 'zh-CN',
        format:'YYYY-MM-DD HH:mm:ss',
        calendarWeeks: false
    });
    
    $('.form_date').datetimepicker({
       locale: 'zh-CN',
       viewMode: 'days',
       format: 'YYYY-MM-DD'
    });
    
    $('.form_time').datetimepicker({
        format: 'LT',
        locale: 'zh-CN'
    });
	$('body').on('ifClicked',"[type='checkbox']", function(e) {
		$(this).parent().removeClass("hover");
	});
    // WIDGET ACTIONS
    $(".widget-header .widget-close").on("click",function(event){
        event.preventDefault();
        $item = $(this).parents(".widget:first");
        bootbox.confirm(i18n.t("widget-remove-confirm"), function(result) {
            if(result === true){
                $item.addClass("animated bounceOutUp");
                window.setTimeout(function () {
                    if($item.data("is-app")){

                        $item.removeClass("animated bounceOutUp");
                        if($item.hasClass("ui-draggable")){
                            $item.find(".widget-popout").click();
                        }
                        $item.hide();
                        $("a[data-app='"+$item.attr("id")+"']").addClass("clickable");
                    }else{
                        $item.remove();
                    }
                }, 300);
            }
        });
    });

    $(document).on("click", ".widget-header .widget-toggle", function(event){
        event.preventDefault();
        $(this).toggleClass("closed").parents(".widget:first").find(".widget-content").slideToggle();
    });

    $(document).on("click", ".widget-header .widget-popout", function(event){
        event.preventDefault();
        var widget = $(this).parents(".widget:first");
        if(widget.hasClass("modal-widget")){
            $("i",this).removeClass("icon-window").addClass("icon-publish");
            widget.removeAttr("style").removeClass("modal-widget");
            widget.find(".widget-maximize,.widget-toggle").removeClass("nevershow");
            widget.draggable("destroy").resizable("destroy");
        }else{
            widget.removeClass("maximized");
            widget.find(".widget-maximize,.widget-toggle").addClass("nevershow");
            $("i",this).removeClass("icon-publish").addClass("icon-window");
            var w = widget.width();
            var h = widget.height();
            widget.addClass("modal-widget").removeAttr("style").width(w).height(h);
            $(widget).draggable({ handle: ".widget-header",containment: ".content-page" }).css({"left":widget.position().left-2,"top":widget.position().top-2}).resizable({minHeight: 150,minWidth: 200});
        }
        window.setTimeout(function () {
            $("body").trigger("resize");
        },300);
    });

    $("a[data-app]").each(function(e){
        var app = $(this).data("app");
        var status = $(this).data("status");
        $("#"+app).data("is-app",true);
        if(status == "inactive"){
            $("#"+app).hide();
            $(this).addClass("clickable");
        }
    });

    $(document).on("click", "a[data-app].clickable", function(event){
        event.preventDefault();
        $(this).removeClass("clickable");
        var app = $(this).data("app");
        $("#"+app).show();
        $("#"+app+" .widget-popout").click();
        topd = $("#"+app).offset().top - $(window).scrollTop();
        $("#"+app).css({"left":"10","top":-(topd-60)+"px"}).addClass("fadeInDown animated");
        window.setTimeout(function () {
            $("#"+app).removeClass("fadeInDown animated");
        }, 300);
    });

    $(document).on("click", ".widget", function(){
        if($(this).hasClass("modal-widget")){
            $(".modal-widget").css("z-index",5);
            $(this).css("z-index",6);
        }
    });

    $(document).on("click", '.widget .reload', function (event) {
        event.preventDefault();
        var el = $(this).parents(".widget:first");
        wfm.blockUIItem(el);
        window.setTimeout(function () {
            wfm.unblockUIItem(el);
        }, 1000);
    });

    $(document).on("click", ".widget-header .widget-maximize", function(event){
        event.preventDefault();
        $(this).parents(".widget:first").removeAttr("style").toggleClass("maximized");
        $("i",this).toggleClass("icon-resize-full-1").toggleClass("icon-resize-small-1");
        $(this).parents(".widget:first").find(".widget-toggle").toggleClass("nevershow");
        $("body").trigger("resize");
        return false;
    });

    $( ".portlets" ).sortable({
        connectWith: ".portlets",
        handle: ".widget-header",
        cancel: ".modal-widget",
        opacity: 0.5,
        dropOnEmpty: true,
        forcePlaceholderSize: true,
        receive: function(event, ui) {$("body").trigger("resize")}
    });

    // Full Screen Toggle
    $("#fs-switch").click(function(evt){
        evt.preventDefault();
        wfm.toggleFullscreen();
    });
    
	$.fn.getFormObj = function()
	{
	   var o = {};
	   var a = this.serializeArray();
	   $.each(a, function() {
	       if (o[this.name]) {
	           if (!o[this.name].push) {
	               o[this.name] = [o[this.name]];
	           }
	           o[this.name].push(this.value || '');
	       } else {
	           o[this.name] = this.value || '';
	       }
	   });
	   return o;
	};

});;var wv = sds.validator = {
    SUCCESS: "validator.SUCCESS",
    ERROR: "validator.ERROR",

    options: {
        custom: {
            numberex: function(element, e){
                var $e = $(element), datatype = $e.data("numberex"),
                    min = hitch(datatype)($e.data("min")),
                    max = hitch(datatype)($e.data("max")), decimal = integer($e.data("decimal"));
                var result = sds.validator.numberValidator($e.val(),min,max,decimal);
                if(result.startsWith(sds.validator.SUCCESS)){
                    if(result.length > 18 && e.type != "input")
                        $e.val(result.substring(18));
                    return true;
                }else{
                    $(element).data("numberex-error",result);
                    return false;
                }
            },
            custom: function(element, e){
                if(!$.trim($(element).val())) return true;
                var chklist = $(element).data("custom").split(" "),
                    result = sds.validator.SUCCESS, newValue = null;
                if(chklist.length > 0){
                    for(var i=0; i< chklist.length; i++){
                        var f = sds.validator.getValidator(chklist[i]);
                        if(f != null)
                            result = f(element, newValue);
                        else
                            console.log("Definition of validator: " + chklist[i] + "is not found");
                        var cr = sds.validator.chkResult(result);
                        if(cr == null)
                            break;
                        if(cr.length > 0) newValue = cr;
                    }
                }else{
                    throw "Incorrect Custom Check";
                }
                var cr = sds.validator.chkResult(result);
                if(cr == null){
                    $(element).data("custom-error",result);
                    return false;
                }else if(cr.length > 0 && e.type !="input"){
                    $(element).val(cr);
                }
                return true;
            }
        },

        errors: {
            minlength: "validate-minlength",
            numberex: "error",
            custom: "error"
        }
    },

    numberValidator: function(value, min, max, decimal){
        var value = $.trim(value);
        if(!value) return sds.validator.SUCCESS;
        var prefix = value.charAt(0);
        if(prefix === "+" || prefix === "-"){
            value = value.substring(1);
        }else
            prefix = "";
        value = value.replace(/^(0(?=\d))+/,"");

        var regex = new RegExp("^\\d+$");
        if(decimal){
            if(decimal > 0)
                regex = new RegExp("^\\d+\\.?\\d{0," + decimal + "}$");
            else
                regex = new RegExp("^\\d+\\.?\\d*$");
        }else{
            decimal = 0;
        }
        if(!regex.test(value))
            return i18n.t('validate-invalid-number');

        value = value.replace(/^\./,"0.");
        value = value.replace(/\.$/,".0");
        value = prefix + value;

        var v = float(value);
        if((min != null && v < min) || (max != null && v > max))
            return i18n.t('validate-invalid-number-range',{ postProcess: 'sprintf', sprintf: [min, max] });

        if(decimal)
            return sds.validator.SUCCESS + "^" + v.toFixed(decimal);
        else
            return sds.validator.SUCCESS;
    },

    getValidator: function(name){
        if(!name) return null;
        var fname = "check" + capitaliseFirstLetter(name);
        if(sds.validator[fname])
            return sds.validator[fname];
        else if(self[fname])
            return self[fname];
        return null;
    },

    chkResult: function(result){
        if(result == undefined) return null;
        if(result.startsWith(sds.validator.SUCCESS)){
            if(result.length > 18){
                return result.substring(18);
            }else{
                return "";
            }
        }
        return null;
    },

    getOptions: function(options){
        options = options || {};
        $.extend(options, sds.validator.options);

        for(var k in options.errors){
            options.errors[k] = i18n.t(options.errors[k]);
        }
        return options;
    }

};


sds.addResource("zh-CN",{
    'validate-minlength': '输入长度不足',
    'validate-invalid-number': '不是一个合法的数字或精度要求不符合要求',
    'validate-invalid-number-range': '数据超出范围，只能在%s和%s之间'
});

sds.addReadyFunc(function(){
});;this["sds"]["hbs"] = this["sds"]["hbs"] || {};

this["sds"]["hbs"]["hbs/menu.hbs"] = Handlebars.template({"1":function(depth0,helpers,partials,data) {
    var stack1, helper, functionType="function", helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression, buffer = "    <li class='has_sub'><a href='javascript:void(0);'><i class='"
        + escapeExpression(((helper = (helper = helpers.icon || (depth0 != null ? depth0.icon : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"icon","hash":{},"data":data}) : helper)))
        + "'></i><span>"
        + escapeExpression(((helper = (helper = helpers.name || (depth0 != null ? depth0.name : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"name","hash":{},"data":data}) : helper)))
        + "</span> <span class=\"pull-right\"><i class=\"fa fa-angle-down\"></i></span></a>\n        <ul>\n";
    stack1 = helpers.each.call(depth0, (depth0 != null ? depth0.submenus : depth0), {"name":"each","hash":{},"fn":this.program(2, data),"inverse":this.noop,"data":data});
    if (stack1 != null) { buffer += stack1; }
    return buffer + "        </ul>\n    </li>\n";
},"2":function(depth0,helpers,partials,data) {
    var helper, functionType="function", helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression;
    return "            <li><a href='"
        + escapeExpression(((helper = (helper = helpers.url || (depth0 != null ? depth0.url : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"url","hash":{},"data":data}) : helper)))
        + "' mid='"
        + escapeExpression(((helper = (helper = helpers.menuId || (depth0 != null ? depth0.menuId : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"menuId","hash":{},"data":data}) : helper)))
        + "'><span>"
        + escapeExpression(((helper = (helper = helpers.name || (depth0 != null ? depth0.name : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"name","hash":{},"data":data}) : helper)))
        + "</span></a></li>\n";
},"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
	// <li><a href='index.html' mid=\"000\"><i
	// class='icon-home'></i><span>仪表盘</span></a></li>\n
    var stack1, buffer = "<ul>\n ";
    stack1 = helpers.each.call(depth0, depth0, {"name":"each","hash":{},"fn":this.program(1, data),"inverse":this.noop,"data":data});
    if (stack1 != null) { buffer += stack1; }
    return buffer + "</ul>";
},"useData":true});

var wmenu = sds.frame.menu = {

    loadMenu: function(data){
        $('#sidebar-menu').html(sds.hbs["hbs/menu.hbs"](data));
    }
};

sds.addReadyFunc(function(){

});;})(jQuery);;var _self = this;
// 获得当前页面的Locale,或者设置Locale
function pageLocale(locale){
    if(locale === undefined)
        return $("body").attr("locale")||"zh-CN";
    else
        $("body").attr("locale",locale);
}

// 首字母大写
function capitaliseFirstLetter(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
}

// 判断对象是否是字符串
function isString(obj){
    return typeof obj === "string" || obj instanceof String;
}

// 根据名称获得一个函数。函数名中可以用‘.’去调用context中子对象的方法，context默认是页面对象
function getFunction(funcname, context){
    context = context || _self;
    var namespaces = funcname.split(".");
    for(var i=0; i< namespaces.length; i++){
        context = context(namespaces[i]);
        if(context == undefined)
            throw "Function is undefined:" + funcname;
    }
    return context;
}

function integer(value){
    var v = parseInt(value,10);
    return isNaN(v)? null : v;
}

function float(value){
    var v = parseFloat(value);
    return isNaN(v)? null : v;
}

// 调用方法
function hitch(scope, method){
    if(!method){
        method = scope;
        scope = null;
    }
    if(isString(method)){
        scope = scope || _self;
        if(!scope[method]){ throw(['hitch: scope["', method, '"] is null (scope="', scope, '")'].join('')); }
        return function(){ return scope[method].apply(scope, arguments || []); }; // Function
    }
    return !scope ? method : function(){ return method.apply(scope, arguments || []); };
}

// 获取对象值
function getObject(propName, context){
    context = context || _self;
    var parts = propName.split(".");
    for(var i=0, pn; context &&(pn = parts[i]); i++){
        context = (pn in context ? context[pn] : undefined);
    }
    return context;
}

// 设置对象值
function setObject(propName, value, context){
    context = context || _self;
    var parts = propName.split(".");
    var p = parts.pop();
    for(var i=0, pn; context &&(pn = parts[i]); i++){
        context = (pn in context ? context[pn] : context[pn]={});
    }
    return (context && p ? (context[p]=value) : undefined);
}

// 返回延时调用函数
function debounce(func, wait, immediate) {
    var timeout, result;
    return function() {
        var args = arguments;
        var later = function() {
            timeout = null;
            if (!immediate) result = func(args);
        };
        var callNow = immediate && !timeout;
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
        if (callNow) result = func(args);
        return result;
    };
}

// 为URL加上时间戳，时间戳的名称可以自己定义，默认是timeflag
function tURL(url, flag){
    flag=flag||"timeflag";
    var iTime=(new Date()).getTime(), pattern=new RegExp(flag+"=\\d{13}");
    if (url.indexOf(flag + "=") >= 0 ){
        url = url.replace(pattern, flag+"="+iTime.toString());
        return url ;
    }
    url+=(/\?/.test(url)) ? "&" : "?";
    return (url+flag+"="+iTime.toString());
}

// encode URL
function encURL(url, flag){
    var index = url.indexOf("?");
    if (index === -1)
        return flag? tURL(url,flag): url;

    var result = url.substring(0, index + 1),
        params = url.substring(index + 1).split("&");

    for (var i=0; i < params.length; i++){
        if (i > 0) result += "&";
        var param = params[i].split("=");
        result += param[0] + "=" + encodeURIComponent(param[1]);
    }

    return flag? tURL(result,flag): result;
}

/* compose ajax call options */
function _ajaxOptions(url, data, args){
    var options = {};
    if(arguments.length === 1)
        options = url;
    else{
        options = args || {};
        options["url"] = url;
        if(data){
           $.extend(options,{data: data});
        }
    }
    // console.dir(options);
    return options;
}

function propertyToArray(data,p){
	var o = data[p];
	if(!(o instanceof Array)){
		a = new Array();
		a.push(o);
		data[p]=a;
	}
	return data;
}

function formList(list,p){
	var data = "";
	for(var i=0;i<list.length;i++){
		if(i==0){
			data=list[i][p];
		}else{
			data=data+","+list[i][p];
		}
	}
	return data;
}
function formatRepo (repo) {
    if (repo.loading) return repo.text;
    var markup = "<div class='select2-result-repository clearfix'>" +
      "<div class='select2-result-repository__meta'>" +
        "<div class='select2-result-repository__title'>" + repo.name + "</div>";

    if (repo.descp) {
      markup += "<div class='select2-result-repository__description'>" + repo.descp + "</div>";
    }
    markup += 
    "</div></div>";
    return markup;
  }
  function formatRepoSelection (repo) {
    return repo.name || repo.text;
  }
