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
function ctips(obj, msg) {
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
		window.location.href = base;
	});
	$("body").on("click", ".jhs", function() {
		window.location.href = base + "user/jyh.htm";
	});
	$("body").on("click", ".about", function() {
		window.location.href = base+ "user/aboutus.htm";
	});
	$("body").on("click", ".myTheme", function() {
		window.location.href = base+"user/myTheme.htm";
	});
	$("body .fb").mouseover(function() {
		$(this).css("background-color", "#F2F2F2");
	}).mouseout(function() {
		$(this).css("background-color", "");
	});
	
	$('body').on("click",".care_user",function(){
		var me = $(this);
		if(UserContext.user==null || UserContext.user==""){
			ctips(me,"你需要登陆才能关注!");
			return ;
		}
		var user= me.attr("tuser");
		$.ailiAjax({
			url : base + "user/care.htm",
			type : "POST",
			data:{'user':user},
			success : function(data, textStatus) {
				ctips(me,"关注成功");
			},
			exception : function(data, textStatus) {
				ctips(me,data.msg);
			}
		});
	});
	
	$('body').on("click",".user-commemt",function(){
		var me = $(this);
		window.location.href=me.attr("burl");
	});
	
	$('body').on("click",".mycare",function(){
		var me = $(this);
		window.location.href= base+"user/myCare.htm"
	});
});
