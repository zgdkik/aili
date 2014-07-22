$j(document).ready(function() {
	init();
	$j(".top_span").mouseover(function() {
		$j(this).css("background-color", "");
	});
	$j(".top_span").mouseout(function() {
		$j(this).css("background-color", "#000000");
	});
	$j("#comment_span").mouseover(function() {
		$j("#comment_span").css("background-color", "#FFFFFF");
		$j("#comment_top").css("display" , 'inline');
		$j("#comment_top_img").attr("src",getImageSrc("comment_top2.png"));
	});
		
	$j("#comment_span").mouseout(function() {
		$j("#comment_span").css("background-color", "");
		$j("#comment_top").css("display" , 'none');
		$j("#comment_top_img").attr("src",getImageSrc("comment_top1.png"));
	});
	$j("#userset_span").mouseover(function() {
		$j("#userset_span").css("background-color", "#FFFFFF");
		$j("#userset_top").css("display" , 'inline');
		$j("#userset_top_img").attr("src",getImageSrc('userset_top1.png'));
	});
	$j("#userset_span").mouseout(function() {
		$j("#userset_span").css("background-color", "");
		$j("#userset_top").css("display" , 'none');
		$j("#userset_top_img").attr("src",getImageSrc('userset_top2.png'));
	});
	$j("#comment_top").mouseover(function() {
		$j("#comment_span").css("background-color", "#FFFFFF");
		$j("#comment_top").css("display" , 'inline');
		$j("#comment_top_img").attr("src",getImageSrc("comment_top2.png"));
	});
		
	$j("#comment_top").mouseout(function() {
		$j("#comment_span").css("background-color", "");
		$j("#comment_top").css("display" , 'none');
		$j("#comment_top_img").attr("src",getImageSrc("comment_top1.png"));
	});
	$j("#userset_top").mouseover(function() {
		$j("#userset_span").css("background-color", "#FFFFFF");
		$j("#userset_top").css("display" , 'inline');
		$j("#userset_top_img").attr("src",getImageSrc('userset_top1.png'));
	});
	$j("#userset_top").mouseout(function() {
		$j("#userset_span").css("background-color", "");
		$j("#userset_top").css("display" , 'none');
		$j("#userset_top_img").attr("src",getImageSrc('userset_top2.png'));
	});
	
})
function getImageSrc(img){
	var src = base+"images/user/homeblog/"+img;
	return src;
}

function init() {
	reSize();
	AttentionInit();
}
function reSize() {
	var width = document.documentElement.offsetWidth;
	if (width > 980) {
		var left = (width - 980) / 2;
		$j("#toptitle").css("marginLeft", left);
		$j("#center").css("marginLeft", left);
	} else {
		$j("#toptitle").css("marginLeft", 0);
		$j("#center").css("marginLeft", 0);
	}
}

// 改变颜色
function updateColor(obj, color) {
	var value = obj.style.backgroundColor;
	if (value == "rgb(250, 250, 250)") {
		return;
	}
	obj.style.backgroundColor = color;
}
/** ******************当鼠标移进去时，改背景颜色，并更改图标************ */
// 关注
function changeAttention(obj) {
	$j("#attention").css("backgroundImage", 'url(images/image/3.jpg)');
}
function recoverAttention() {
	$j("#imgAttention").css("marginLeft", "0px");
	$j("#attention").css("backgroundImage", 'url(images/image/1.jpg)');
}
function attentionChange() {

	$j("#imgAttention").css("marginLeft",  "-25px");
}
// 粉丝
function changeFan(obj) {
	$j("#fan").css("backgroundImage", 'url(images/image/3.jpg)');
	$j("#imgFan").css("marginLeft", "-25px");
}
function recoverFan() {
	$j("#fan").css("backgroundImage", 'url(images/image/1.jpg)');
	$j("#imgFan").css("marginLeft", "0px");
}
// 找人
function changeFind(obj) {
	$j("#find").css("backgroundImage", 'url(images/image/3.jpg)');
	$j("#imgFind").css("marginLeft","-25px");
}
function recoverFind() {
	$j("#find").css("backgroundImage", 'url(images/image/1.jpg)');
	$j("#imgFind").css("marginLeft", "0px");
}
// 关注全部
function changeAttentionAll(obj) {
	$j("#attentionAll").css("backgroundImage", 'url(images/image/3.jpg)');
}
function recoverAttentionAll() {
	$j("#attentionAll").css("backgroundImage", 'url(images/image/1.jpg)');
}
// 关注人框变颜色，加载数据
function AttentionInit() {
	$j("#attention").attr("backgroundColor","white");
}
// 粉丝框变颜色，加载数据
function FansInit() {
	$j("#fan").attr("backgroundColor","white");;
}