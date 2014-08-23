$j(document).ready(function() {
	 $j("#main").scroll(function(){
         if ($j("#main").scrollTop()>300){
             $j("#back-to-top").fadeIn(1500);
         }else{
             $j("#back-to-top").fadeOut(1500);
         }
     });
     //当点击跳转链接后，回到页面顶部位置
     $j("#back-to-top").click(function(){
         $j('#main').animate({scrollTop:0},1000);
         return false;
     });
	
});

