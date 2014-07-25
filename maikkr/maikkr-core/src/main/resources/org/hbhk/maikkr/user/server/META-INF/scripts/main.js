$j(document).ready(function() {
	$j("#main").scroll(function() {
		$j("#bottom_top").css("marginTop",
			$j('#main').css("scrollTop")+document.body.offsetHeight-180);
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
		$j("#bottom_top1").attr("src", base+"images/user/homeblog/bottom_top2.png");
	});
	$j("#bottom_top").mouseover(function() {
		$j("#bottom_top1").attr("src", base+"images/user/homeblog/bottom_top1.png");
	});
	
	$j("#addImg").mouseover(function() {
		$j("#picture").css("display" , 'inline');
	});
		
	$j("#addImg").mouseout(function() {
		$j("#picture").css("display" , 'none');
	});
	
	$j("#picture").mouseover(function() {
		$j(this).css("display" , 'inline');
	});
		
	$j("#picture").mouseout(function() {
		$j(this).css("display" , 'none');
	});
	
	$j("#uploadImg").uploadify({
			'swf' : base + 'uploadify/uploadify.swf',
			'uploader' :base + 'core/upload.htm',
			'auto' : true,
			'buttonImage':base + 'images/user/homeblog/uploadpicture.png',
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
				if(json.success){
					$j.toast("上传成功");
					$j('.imgurl').val(data.result)
					alert($j('.imgurl').val());
				}else{
					$j.toast("上传失败");
				}
			}
	});	
   
});

function updateImgSize() {
	 var width=obj.style.width;
     if(width=="360px"){
     		var height=$("main").scrollHeight;
         	$("main").scrollHeight=height-470;
         	 $("center").style.height=height-470;
	               $("ct_left").style.height=height-10-470;
	               $("ct_right").style.height=height-470;
	               $("ct_bottom").style.marginTop=height+30-470;
     	obj.style.width="120px";
     	obj.style.height="100px";
     }else {
     	obj.style.width="360px";
     	obj.style.height="300px";
     	 var height=$("main").scrollHeight ;
		   $("center").style.height=height-100;
          $("ct_left").style.height=height-10-100;
          $("ct_right").style.height=height-100;
          $("ct_bottom").style.marginTop=height+30-100;
     }
}