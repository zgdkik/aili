$(document).ready(function() {
	document.onkeydown = function(e){ 
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {
	    	$("#login-form").submit();
	     }
	}

});