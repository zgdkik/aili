$(document).ready(function() {
	 $(window).scroll(function(){
         if ($(window).scrollTop()>300){
             $("#back-to-top").fadeIn(1500);
         }else{
             $("#back-to-top").fadeOut(1500);
         }
     });
     //当点击跳转链接后，回到页面顶部位置
     $("#back-to-top").click(function(){
         $(window).animate({scrollTop:0},1000);
         return false;
     });
	
});

