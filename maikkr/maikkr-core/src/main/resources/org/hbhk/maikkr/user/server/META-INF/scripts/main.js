$j(document).ready(function() {
	$j("#main").scroll(function() {
		$j("#bottom_top").css("marginTop",$j('#main')
			.css("scrollTop")+ document.body.offsetHeight - 180);
	});
	$j(".left_class").mouseout(function() {
		$j(this).css("background-color", "");
	});
	$j(".left_class").mouseover(function() {
		$j(this).css("background-color", "#E6E6E6");
	});

	$j(".returnTop").click(function() {
		$j("#mian").scrollTop(0);
	});
	$j("#bottom_top").mouseout(function() {
		$j("#bottom_top1").attr("src",base + "images/user/homeblog/bottom_top2.png");
	});
	$j("#bottom_top").mouseover(function() {
		$j("#bottom_top1").attr("src",base + "images/user/homeblog/bottom_top1.png");
	});

	$j("#bottom_top").click(function() {
		$j("#main").css("scroll-top",0);
	});
	$j("#addImg").mouseover(function() {
		$j("#picture").css("display", 'inline');
	});

	$j("#addImg").mouseout(function() {
		$j("#picture").css("display", 'none');
	});

	$j("#picture").mouseover(function() {
		$j(this).css("display", 'inline');
	});

	$j("#picture").mouseout(function() {
		$j(this).css("display", 'none');
	});

	$j("#uploadImg").uploadify({
		'swf' : base + 'uploadify/uploadify.swf',
		'uploader' : base + 'core/upload.htm',
		'auto' : true,
		'buttonImage' : base + 'images/user/homeblog/file-upload.png',
		'buttonText' : '浏览',
		'fileTypeDesc' : '图片',
		// 允许上传的文件后缀
		'fileTypeExts' : '*.gif; *.jpg; *.png',
		'multi' : false,
		'width' : 60,
		'height' : 20,
		'onSelectError' : function() {
			$j.toast("支持的文件格式:*.gif; *.jpg; *.png");
		},
		'onUploadSuccess' : function(file, data, response) {
			var json = $j.parseJSON(data);
			if (json.success) {
				$j.toast("上传成功");
				$j('.imgurl').val(data.result)
				alert($j('.imgurl').val());
			} else {
				$j.toast("上传失败");
			}
		}
	});
	updateHeight();
});

// 动态改变div高度
var preHiget;// 上次的高度
var difference;
function updateHeight(a) {
	var height = get("main").scrollHeight;
	if (a == 1) {
		preHiget = height - difference - 160;
		$j("#center").css("height", preHiget);
		$j("#ct_left").css("height", preHiget - 10);
		$j("#ct_right").css("height", preHiget);
		$j("#ct_bottom").css("margin-top", preHiget + 30);
	} else if (a == 2) {
		$j("#center").css("height",800);
		$j("#ct_left").css("height", 800 - 10);
		$j("#ct_right").css("height", 800);
		$j("#ct_bottom").css("marginTop", 800);
	} else {
		$j("#center").css("height" , height);
		$j("#ct_left").css("height ",height - 10);
		$j("#ct_right").css("height" , height);
		$j("#ct_bottom").css("marginTop" , height + 30);
		difference = height - preHiget;
	}
}

function get(id){
	return document.getElementById(id);
}