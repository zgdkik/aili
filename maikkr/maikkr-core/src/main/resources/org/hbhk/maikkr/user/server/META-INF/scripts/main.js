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