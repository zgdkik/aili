$(function() {
	//图片轮播
	$('#myCarousel').carousel('next');
	$('#sidebar .list-group a').mouseenter(function(obj){
		$(this).toggleClass("active");
	 });

	$('#sidebar .list-group a').mouseleave(function(obj){
		$(this).toggleClass("active");
	 });
	var uername = $.cookie("uername");
	if(typeof(uername)!='undefined'){
		$('#login_form').hide()
		$('#user_info').show()
		
	}else{
		$('#login_form').show()
		$('#user_info').hide()
	}
	$('.btn-primary').click(function(){
		 $.post("/aili/security/login.htm",
			  {
			    username:"Donald Duck",
			    password:"Duckburg"
			  },
			  function(data,status){
				$.cookie("uername", "uername");  
				window.location.reload()
			  }
		 );
	 });
	
	$('#logout').click(function(){
		 $.post("/aili/security/login.htm",
			  {
			    username:"Donald Duck",
			    password:"Duckburg"
			  },
			  function(data,status){
				$.removeCookie("uername");  
				window.location.reload()
			  }
		 );
	 });
	
});