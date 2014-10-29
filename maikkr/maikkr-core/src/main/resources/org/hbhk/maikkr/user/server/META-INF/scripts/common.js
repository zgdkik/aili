function tips(selector, msg) {
	$(selector).attr("title", msg);
	var tips = $(selector).tooltip({
		position : {
			my : "left top",
			at : "right+5 top-5"
		}
	});
	tips.tooltip("open");
	setTimeout(function() {
		tips.tooltip("destroy");
		$(selector).removeAttr("title");
	}, 3000);
};
function checkEmpty(selector, msg) {
	var val = $(selector).val();
	if (val == null || val == "") {
		tips(selector, msg);
		return false;
	}
	return true;
}

function allTips(obj, msg) {
	obj.attr("title", msg);
	var tips = obj.tooltip({
		position : {
			my : "left top",
			at : "right+5 top-5"
		}
	});
	tips.tooltip("open");
	setTimeout(function() {
		tips.tooltip("destroy");
		obj.removeAttr("title");
	}, 3000);
};
function checkALlEmpty(selector, msg) {
	var obj = $(selector);
	for ( var i = 0; i < obj.length; i++) {
		var me = obj.eq(i);
		var val = me.val();
		if (val == null || val == "") {
			allTips(me, msg);
			return false;
		}
	}
	return true;
}
$(document).ready(function() {
	$("body").on("click", ".home", function() {
		window.location.href = "http://" + host;
	});
	$("body").on("click", ".jhs", function() {
		window.location.href = "http://" + host + "/user/jyh.htm";
	});
	$("body").on("click", ".about", function() {
		window.location.href = "http://" + host + "/user/aboutus.htm";
	});
	$("body .fb").mouseover(function() {
		$(this).css("background-color", "#F2F2F2");
	}).mouseout(function() {
		$(this).css("background-color", "");
	});
});
