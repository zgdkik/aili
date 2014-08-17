$j(document).ready(function() {
	$j("#main").scroll(function() {
		$j("#bottom_top").css("marginTop",$j('#main')
			.css("scrollTop")+ document.body.offsetHeight - 180);
	});
	$j("#sendComment").click(function(){
		if(UserContext.user==null || UserContext.user==""){
			$j.toast("你需要登陆才能发表评论!");
			return ;
		}
		var editorText  = $j("#editorText").val();
		var blogId = $j("#blogId").val();
		$j.ajax({
			url : base + "user/sendComment.htm",
			type : "POST",
			data:{'commentConcent':editorText,'blogId':blogId},
			success : function(data, textStatus) {
				$j.toast("评论成功");
				
			},
			exception : function(data, textStatus) {
				$j.toast("评论失败");
			}
			});
	});
	updateHeight();
	$j(window).resize(function() { 
		updateHeight();
	});
	$j("body").on('click',".left_class",function(){
		$j(".showInfo").hide();
		var type = $j(this).attr("type");
		$j("."+type).show();
	});
	$j(".updatePwd").click(function(){
		updatePwd();
	});
	$j(".nickname_btn").click(function(){
		updateNickname();
	});
	$j("#uploadHeadImg").uploadify({
		'swf' : base + 'uploadify/uploadify.swf',
		'uploader' : base + 'core/upload.htm;jsessionid='+seesionid,
		'auto' : true,
		'simUploadLimit':5,
		'buttonImage' : base + 'images/user/homeblog/file-upload.png',
		'buttonText' : '浏览',
		'fileTypeDesc' : '图片',
		// 允许上传的文件后缀
		'fileTypeExts' : '*.gif; *.jpg; *.png',
		'multi' : false,
		'width' : 60,
		'height' : 20,
		'uploadLimit':5,
		'queueSizeLimit':5,
		'onUploadStart':function(){
			if(UserContext.user==null || UserContext.user==""){
				$j.toast("你需要登陆才能上传图片!");
				return false;
			}
		},
		'onSelectError' : function() {
			$j.toast("支持的文件格式:*.gif; *.jpg; *.png");
		},
		'onUploadSuccess' : function(file, data, response) {
			var json = $j.parseJSON(data);
			if (json.success) {
				var url = json.result;
				uploadheadImg(url);
			} else {
				$j.toast("上传失败");
			}
		}
	});
});


function updateHeight() {
	var height = document.getElementById("main").scrollHeight;
	$j("#center").css("height" , height);
	$j("#ct_left").css("height ",height - 10);
	$j("#ct_right").css("height" , height);
	//$j("#ct_bottom").css("marginTop" , height + 30);
};

function uploadheadImg(imgurl) {
	$j.ajax({
		url : base + "user/updateHeadImg.htm",
		type : "POST",
		async : false, 
		data:{'url':imgurl},
		success : function(data, textStatus) {
			$j.toast("上传成功");
			window.location.reload();
		},
		exception : function(data, textStatus) {
			$j.toast("上传失败");
		}
		});
};
function updateNickname() {
	var nickname = $j('.nickname').val();
	if(nickname==null || nickname==""){
		$j.toast("昵称为空");
		return;
	}
	$j.ajax({
		url : base + "user/updateNickname.htm",
		type : "POST",
		async : false, 
		data:{'nickname':nickname},
		success : function(data, textStatus) {
			$j.toast("修改成功");
			window.location.reload();
		},
		exception : function(data, textStatus) {
			$j.toast("修改失败");
		}
		});
};
function updatePwd() {
	var rpwd= $j('#rpwd').val();
	var pwd= $j('#pwd').val();
	var cpwd= $j('#cpwd').val();
	if(validate()==false){
		return ;
	}
	if(pwd!=cpwd){
		$j.toast("两次密码不一致");
	}
	$j.ajax({
		url : base + "user/updatePwd.htm",
		type : "POST",
		async : false, 
		data:{'pwd':pwd,'rpwd':rpwd},
		success : function(data, textStatus) {
			$j.toast("修改成功");
		},
		exception : function(data, textStatus) {
			$j.toast("修改失败");
		}
		});
};
