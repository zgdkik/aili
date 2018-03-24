function replaceSrc() {
	$(".news-content img").each(function() {
		var str = $(this).attr("src");
		if (!(str.indexOf(base) > -1)) {
			$(this).attr("src", base + str);
		}
	});
	$(".contentList img").each(function() {
		var str = $(this).attr("src");
		if (!(str.indexOf(base) > -1)) {
			$(this).attr("src", base + str);
		}
	});
	$(".bannerReplace img").each(function() {
		var str = $(this).attr("src");
		if (!(str.indexOf(base) > -1)) {
			$(this).attr("src", base + str);
		}
	});
	$(".home-news li img").each(function() {
		var str = $(this).attr("src");
		if (!(str.indexOf(base) > -1)) {
			$(this).attr("src", base + str);
		}
	});
	$(".home-product li img").each(function() {
		var str = $(this).attr("src");
		if (!(str.indexOf(base) > -1)) {
			$(this).attr("src", base + str);
		}
	});
}
Date.prototype.Format = function(fmt) { // author: meizz
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
}

var bannerIds=0;
// 首页的banner展示
function loadHomeBanner(type) {// 根据传过来的type进行各张页面的展示
		$.ajax({
			url : base + '/homePage/getHomeBanner',
			type : "post",
			data : {
				bannerType : type
			},
			dataType : "json",
			success : function(data) {
				data = data.data;
				// 返回到页面是个list集合,如何将它挨个展示
				var html = "";
				for (var i = 0; i < data.length; i++) {
					if (i==0) {
						html += "<div class=\"item bannerReplace active\"><img src="
								+ data[i].banner_pic
								+ " alt=\"...\" style=\"width: 100%;cursor: pointer;\" onclick=\"bannerClick('"+data[i].banner_url+"')\"></div> ";
					} else{
						html += "<div class=\"item bannerReplace\"><img src="
								+ data[i].banner_pic
								+ " alt=\"...\" style=\"width: 100%;cursor: pointer;\" onclick=\"bannerClick('"+data[i].banner_url+"')\"></div> ";
					}
				}
				$('#homeBanner').html(html);
				
				replaceSrc();// 替换路径
			}
		});
}




function bannerClick(url){
	window.location.href=base+url;
}

// 其他页面banner的展示-一张
function loadNextBanner(type) {
	$.ajax({
		url : base + '/homePage/getHomeBanner',
		type : 'post',
		dataType : "json",
		data : {
			bannerType : type
		},
		success : function(data) {
			data = data.data;
			var html = "";
			html = "<img src=" + data[0].banner_pic + " alt=\"\"/> ";
			$('#child_banner').html(html);
			replaceSrc();// 替换路径
		}
	});
}
function noNumbers(e) {
	var keynum
	var keychar
	var numcheck
	if (window.event) // IE
	{
		keynum = e.keyCode
	} else if (e.which) // Netscape/Firefox/Opera
	{
		keynum = e.which
	}
	keychar = String.fromCharCode(keynum);
	// 判断是数字,且小数点后面只保留两位小数
	if (!isNaN(keychar)) {
		var index = e.currentTarget.value.indexOf(".");
		if (index >= 0 && e.currentTarget.value.length - index > 2) {
			return false;
		}
		return true;
	}
	// 如果是小数点 但不能出现多个 且第一位不能是小数点
	if ("." == keychar) {
		if (e.currentTarget.value == "") {
			return false;
		}
		if (e.currentTarget.value.indexOf(".") >= 0) {
			return false;
		}
		return true;
	}
	return false;
}
