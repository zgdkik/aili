	var domain = document.domain;
	if(domain == 'localhost' || domain == '127.0.0.1' ){
		domain ="http://"+ domain+":5416/aili/"
	}
	function getReqUrl(url){
		return	domain+url;
	}
