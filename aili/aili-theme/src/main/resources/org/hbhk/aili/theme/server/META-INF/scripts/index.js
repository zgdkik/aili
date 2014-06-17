$(function() {
	$('#myCarousel').carousel('next');
	
	$('#sidebar .list-group').click(function(){
		$('#sidebar .list-group').toggleClass("active");
	  });
});