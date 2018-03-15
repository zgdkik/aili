$(document).ready(function() {
	 $("body").on("click",".edit",function(){
		 var me  = $(this);
		 var email =  $(".email").val()
		 var nick =  $(".nickname").val()
		 var area =  $(".area").val()
		 if(checkALlEmpty(".required","必填项")==false){
			 return;
		 }
		 var url = base + "user/updateNickname.htm";
   	     var data = {
       		"name":email,
       		"nickname":nick,
       		"area":area
       	};
	   	$.ailiAjax({
	   		url: url,
	   		type:"POST",
	   		data:data,
	   		success: function(data, textStatus){
	   			ctips(me,"修改成功");
	   		},
	   		exception:function(data, textStatus){
	   			ctips(me,"修改失败");
	   		}
	   	 });
	 });
	 
	 $("#uploadimg").uploadify({
			'swf' : base + 'uploadify/uploadify.swf',
			'uploader' : base + 'core/upload.htm;jsessionid='+seesionid,
			'auto' : true,
			'simUploadLimit':5,
			//'buttonImage' : base + 'images/user/homeblog/file-upload.png',
			'buttonText' : '上传头像',
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
				var json = $.parseJSON(data);
				if (json.success) {
					var url = json.result;
					uploadheadImg(url);
				} else {
					tips("#u51","上传失败");
				}
			}
		});
	
});

function uploadheadImg(imgurl) {
	$.ailiAjax({
		url : base + "user/updateHeadImg.htm",
		type : "POST",
		async : false,
		data : {
			'url' : imgurl
		},
		success : function(data, textStatus) {
			tips("#u51","上传成功");
			window.location.reload();
		},
		exception : function(data, textStatus) {
			tips("#u51","上传失败");
		}
	});
};

function updateNickname() {
	var nickname = $j('.nickname').val();
	if(nickname==null || nickname==""){
		$j.toast("昵称为空");
		return;
	}
	var email = $j('.email').val();
	if(email==null || email==""){
		$j.toast("昵称为空");
		return;
	}
	$.ailiAjax({
		url : base + "user/updateNickname.htm",
		type : "POST",
		async : false, 
		data:{'nickname':nickname,"email":email},
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
	$.ailiAjax({
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

