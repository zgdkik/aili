$(document).ready(function(){
	//选择送货时效
	$("#demandForm label").click(function(){
		$(this).addClass("cut").siblings().removeClass("cut");	
	});
	//选择车型
	$(".cartModels ul li.cart1").click(function(){
		if($(this).hasClass("cut")){
			$(this).removeClass("cut");	
			$("input#cart_models1").val("");
		}else{
			$(this).addClass("cut");	
	  		var contact=$(this).attr("date-name");
	  		var a = $(this).parents().find("input#cart_models1").val();
			$(this).parents().find("input#cart_models1").val(contact);
		}
		
	});
	$(".cartModels ul li.cart2").click(function(){
		if($(this).hasClass("cut")){
			$(this).removeClass("cut");	
			$("input#cart_models2").val("");
		}else{
			$(this).addClass("cut");	
	  		var contact=$(this).attr("date-name");
	  		var a = $(this).parents().find("input#cart_models2").val();
			$(this).parents().find("input#cart_models2").val(contact);
		}
		
	});
	$(".cartModels ul li.cart3").click(function(){
		if($(this).hasClass("cut")){
			$(this).removeClass("cut");	
			$("input#cart_models3").val("");
		}else{
			$(this).addClass("cut");	
	  		var contact=$(this).attr("date-name");
	  		var a = $(this).parents().find("input#cart_models3").val();
			$(this).parents().find("input#cart_models3").val(contact);
		}
		
	});
	$(".cartModels ul li.cart4").click(function(){
		if($(this).hasClass("cut")){
			$(this).removeClass("cut");	
			$("input#cart_models4").val("");
		}else{
			$(this).addClass("cut");	
	  		var contact=$(this).attr("date-name");
	  		var a = $(this).parents().find("input#cart_models4").val();
			$(this).parents().find("input#cart_models4").val(contact);
		}
		
	});
	$(".cartModels ul li.cart5").click(function(){
		if($(this).hasClass("cut")){
			$(this).removeClass("cut");	
			$("input#cart_models5").val("");
		}else{
			$(this).addClass("cut");	
	  		var contact=$(this).attr("date-name");
	  		var a = $(this).parents().find("input#cart_models5").val();
			$(this).parents().find("input#cart_models5").val(contact);
		}
		
	});
	$(".cartModels ul li.cart6").click(function(){
		if($(this).hasClass("cut")){
			$(this).removeClass("cut");	
			$("input#cart_models6").val("");
		}else{
			$(this).addClass("cut");	
	  		var contact=$(this).attr("date-name");
	  		var a = $(this).parents().find("input#cart_models6").val();
			$(this).parents().find("input#cart_models6").val(contact);
		}
		
	});
	$(".cartModels ul li.cart7").click(function(){
		if($(this).hasClass("cut")){
			$(this).removeClass("cut");	
			$("input#cart_models7").val("");
		}else{
			$(this).addClass("cut");	
	  		var contact=$(this).attr("date-name");
	  		var a = $(this).parents().find("input#cart_models7").val();
			$(this).parents().find("input#cart_models7").val(contact);
		}
		
	});
	$(".cartModels ul li.cart8").click(function(){
		if($(this).hasClass("cut")){
			$(this).removeClass("cut");	
			$("input#cart_models8").val("");
		}else{
			$(this).addClass("cut");	
	  		var contact=$(this).attr("date-name");
	  		var a = $(this).parents().find("input#cart_models8").val();
			$(this).parents().find("input#cart_models8").val(contact);
		}
		
	});
	$(".cartModels ul li.cart9").click(function(){
		if($(this).hasClass("cut")){
			$(this).removeClass("cut");	
			$("input#cart_models9").val("");
		}else{
			$(this).addClass("cut");	
	  		var contact=$(this).attr("date-name");
	  		var a = $(this).parents().find("input#cart_models9").val();
			$(this).parents().find("input#cart_models9").val(contact);
		}
		
	});
	
	//额外要求
	$(".cartModels_c ul li.cart1").click(function(){
		if($(this).hasClass("cut")){
			$(this).removeClass("cut");	
			$("input#cart_models_g").val("");
		}else{
			$(this).addClass("cut");	
	  		var contact=$(this).attr("date-name");
	  		var a = $(this).parents().find("input#cart_models").val();
			$(this).parents().find("input#cart_models_g").val(contact);
		}
		
	});
	$(".cartModels_c ul li.cart2").click(function(){
		if($(this).hasClass("cut")){
			$(this).removeClass("cut");	
			$("input#cart_models_b").val("");
		}else{
			$(this).addClass("cut");	
	  		var contact=$(this).attr("date-name");
	  		var a = $(this).parents().find("input#cart_models").val();
			$(this).parents().find("input#cart_models_b").val(contact);
		}
	});
	$(".cartModels_c ul li.cart3").click(function(){
		if($(this).hasClass("cut")){
			$(this).removeClass("cut");	
			$("input#cart_models_c").val("");
		}else{
			$(this).addClass("cut");	
	  		var contact=$(this).attr("date-name");
	  		var a = $(this).parents().find("input#cart_models").val();
			$(this).parents().find("input#cart_models_c").val(contact);
		}
	});
	$(".cartModels_c ul li.cart4").click(function(){
		if($(this).hasClass("cut")){
			$(this).removeClass("cut");	
			$("input#cart_models_h").val("");
		}else{
			$(this).addClass("cut");	
	  		var contact=$(this).attr("date-name");
	  		var a = $(this).parents().find("input#cart_models").val();
			$(this).parents().find("input#cart_models_h").val(contact);
		}
	});
	
	$('body').on('ifClicked', ".routeType",function(event){
		var selected = $(".radio").find("input[type='radio']:checked").attr("id");
		if("optionsRadios2"==selected){
			$(".radio").find("#optionsRadios1lb").addClass("cut");
			$(".radio").find("#optionsRadios2lb").removeClass("cut");
		}else{
			$(".radio").find("#optionsRadios2lb").addClass("cut");
			$(".radio").find("#optionsRadios1lb").removeClass("cut");
		}
	});
	//登录界面获取光标 加边框
	$(".login-page input").focus(function(){
		  $(this).addClass("insertLine");
		});
	$(".login-page input").blur(function(){
		$(this).removeClass('insertLine');
	});
});