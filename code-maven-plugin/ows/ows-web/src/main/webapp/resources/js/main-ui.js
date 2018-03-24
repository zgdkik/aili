 String.prototype.trim=function(){
	 return this.replace(/(^\s*)|(\s*$)/g, "");
 }
String.prototype.ltrim=function(){
 return this.replace(/(^\s*)/g,"");
}
String.prototype.rtrim=function(){
   return this.replace(/(\s*$)/g,"");
  }
function HashMap()  
{  
    /** Map 大小 **/  
    var size = 0;  
    /** 对象 **/  
    var entry = new Object();  
      
    /** 存 **/  
    this.put = function (key , value)  
    {  
        if(!this.containsKey(key))  
        {  
            size ++ ;  
        }  
        entry[key] = value;  
    }  
      
    /** 取 **/  
    this.get = function (key)  
    {  
        return this.containsKey(key) ? entry[key] : null;  
    }  
      
    /** 删除 **/  
    this.remove = function ( key )  
    {  
        if( this.containsKey(key) && ( delete entry[key] ) )  
        {  
            size --;  
        }  
    }  
      
    /** 是否包含 Key **/  
    this.containsKey = function ( key )  
    {  
        return (key in entry);  
    }  
      
    /** 是否包含 Value **/  
    this.containsValue = function ( value )  
    {  
        for(var prop in entry)  
        {  
            if(entry[prop] == value)  
            {  
                return true;  
            }  
        }  
        return false;  
    }  
      
    /** 所有 Value **/  
    this.values = function ()  
    {  
        var values = new Array();  
        for(var prop in entry)  
        {  
            values.push(entry[prop]);  
        }  
        return values;  
    }  
      
    /** 所有 Key **/  
    this.keys = function ()  
    {  
        var keys = new Array();  
        for(var prop in entry)  
        {  
            keys.push(prop);  
        }  
        return keys;  
    }  
      
    /** Map Size **/  
    this.size = function ()  
    {  
        return size;  
    }  
      
    /* 清空 */  
    this.clear = function ()  
    {  
        size = 0;  
        entry = new Object();  
    }  
} ;

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
		tooltip:function (value, row, index) {
			var content = '';
			var abValue = value + '';
			if (value != undefined) {
				if (value.length >= 22) {
					abValue = value.substring(0, 19) + "...";
					content = '<a href="javascript:;" style="text-decoration:none;color:#000000;"  title="' + value
							+ '" class="easyui-tooltip">' + abValue + '</a>';
				} else {
					content = '<a href="javascript:;" style="text-decoration:none;color:#000000;"   title="' + abValue
							+ '" class="easyui-tooltip">' + abValue + '</a>';
				}
			}
			return content;
		},
		initPage : function(url, opts, params,tblId, columns) {
			if ($.cookie('SESSION_COOKIE_KYE') == null
					|| $.cookie('SESSION_COOKIE_KYE') == undefined) {
				 $.messager.alert("提示信息", "会话已失效,请重新登录", "info", function () {  
					 parent.location.href = base + "/user/login";
			      });  
				return;
			}
			opts.url = url;
			opts.queryParams = params;
			opts.columns=columns;
			return $(tblId).datagrid(opts);
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
					$.messager.alert("提示信息", "会话已失效,请重新登录", "info", function () {  
						parent.location.href = base + "/user/login";
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
							$.messager.alert("提示信息", data.msg);  
						}
					} else if (this.failHandler) {
						hitch(this, "failHandler")(data, textStatus, jqXHR);
					} else if (data) {
						$.messager.alert("提示信息", data.msg);  
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					if (this.errorHandler) {
						hitch(this, "errorHandler")(jqXHR, textStatus,
								errorThrown);
					} else {
						switch (jqXHR.status) {
						case 403:
							$.messager.alert("提示信息", "权限不足");  
							break;
						case 404:
							$.messager.alert("提示信息", "请求不存在");  
							break;
						default:
							$.messager.alert("提示信息", "系统内部错误");  
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
			if(strTime.constructor==String){
				if(strTime.indexOf("单页合计")){
					return strTime;
				}
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
			if(strTime.constructor==String){
				if(strTime.indexOf("单页合计")){
					return strTime;
				}
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
		if($('#dg').length>0&& $('#tblDiv').length>0){
			var  tblh =  $(window).height() - $('#tblDiv').offset().top;
			$('#dg').datagrid('resize', {
				//定义表格高度
				height : tblh
			});
		}
		
		setTimeout(function(){
			var form =$("form");
//			if(form.length>0){
//				if(form.attr("data-scroll")){
//					var pbw = form.parent(".panel-body").css("width");
//					pbw= pbw.replace("px","");
//					var lw = form.attr("data-scroll-x-w");
//					if(pbw<1600){
//						form.find(".sfwl-panel-context").css("width",lw);
//					}
//				}
//			}
			
//			$(".datebox").css("width","120px");
//			$(".textbox").css("width","120px");
			
		},1);
		
		$("body").on("click",".expand-collapse",function(){
			var me  = $(this);
			var id = me.attr("data-href");
			var tblId = me.attr("data-tbl");
			var  iconCls = me.attr("iconCls");
			if(iconCls=="accordion-expand"){
				$(id).panel("expand");
				me.attr("iconCls","accordion-collapse");
				var ae = me.find(".accordion-expand");
				ae.removeClass("accordion-expand");
				ae.addClass("accordion-collapse");
			}else{
				$(id).panel("collapse");
				 me.attr("iconCls","accordion-expand");
				 var  ac = me.find(".accordion-collapse");
				 ac.removeClass("accordion-collapse");
				 ac.addClass("accordion-expand");
			}
			$(tblId).datagrid('resize', {
				//定义表格高度
				height : $(window).height() - $('#tblDiv').offset().top
			});
		});
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

// 根据名称获得一个函数。函数名中可以用".’去调用context中子对象的方法，context默认是页面对象
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
//$(document).mask('正在加载，请等待...');
//window.onload = function() {
//	$(document).unmask();
//}
var idTmr;
function  getExplorer() {
	var explorer = window.navigator.userAgent ;
	//ie 
	if (explorer.indexOf("MSIE") >= 0) {
		return 'ie';
	}
	//firefox 
	else if (explorer.indexOf("Firefox") >= 0) {
		return 'Firefox';
	}
	//Chrome
	else if(explorer.indexOf("Chrome") >= 0){
		return 'Chrome';
	}
	//Opera
	else if(explorer.indexOf("Opera") >= 0){
		return 'Opera';
	}
	//Safari
	else if(explorer.indexOf("Safari") >= 0){
		return 'Safari';
	}
}
//整个表格拷贝到EXCEL中
function excelExport(tableid) {
	if(getExplorer()=='ie')
	{
		var curTbl = $(tableid);
		var oXL = new ActiveXObject("Excel.Application");
		
		//创建AX对象excel 
		var oWB = oXL.Workbooks.Add();
		//获取workbook对象 
		var xlsheet = oWB.Worksheets(1);
		//激活当前sheet 
		var sel = document.body.createTextRange();
		sel.moveToElementText(curTbl);
		//把表格中的内容移到TextRange中 
		sel.select();
		//全选TextRange中内容 
		sel.execCommand("Copy");
		//复制TextRange中内容  
		xlsheet.Paste();
		//粘贴到活动的EXCEL中       
		oXL.Visible = true;
		//设置excel可见属性

		try {
			var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
		} catch (e) {
			print("Nested catch caught " + e);
		} finally {
			oWB.SaveAs(fname);

			oWB.Close(savechanges = false);
			//xls.visible = false;
			oXL.Quit();
			oXL = null;
			//结束excel进程，退出完成
			//window.setInterval("Cleanup();",1);
			idTmr = window.setInterval("Cleanup();", 1);

		}
		
	}
	else
	{
		tableToExcel(tableid)
	}
};
function Cleanup() {
    window.clearInterval(idTmr);
    CollectGarbage();
};
var tableToExcel = (function() {
	  var uri = 'data:application/vnd.ms-excel;base64,',
	  template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
		base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
		format = function(s, c) {
			return s.replace(/{(\w+)}/g,
			function(m, p) { return c[p]; }) }
		return function(table, name) {
		if (!table.nodeType) {
			//table = document.getElementById(table)
		}
		var ctx = {worksheet: name || 'Worksheet', table: table}
		window.location.href = uri + base64(format(template, ctx))
	  }
})();


//console.log($(this).html());
tableExport=function(columns,datas){
	columns = columns[0];
	var excel = "<table>";
	// Header
	excel += "<tr>";
	for (var i = 0; i < columns.length; i++) {
		if(columns[i].hidden || columns[i].checkbox){
			continue;
		}
		
		var st ='style=mso-number-format:"\@"';
		excel += "<td "+st+">  "+columns[i].title + "</td>";
	}
	excel += '</tr>';
	// Row Vs Column
	for (var i = 0; i < datas.length; i++) {
		var data = datas[i];
		excel += "<tr>";
		for (var j = 0; j < columns.length; j++) {
			if(columns[j].hidden || columns[j].checkbox){
				continue;
			}
			var val =data[columns[j].field] ;
			if(val==null){
				val = "";
			}
			var st ='style=mso-number-format:"\@"';
			//是否为数字
			excel += "<td "+st+">" + val + "</td>";
		}
		excel += '</tr>';
	}
	excel += '</table>'
	excelExport(excel);
};



