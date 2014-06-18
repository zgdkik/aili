	var domain = "http://"+document.domain;
	
	if(domain == 'localhost' || domain == '127.0.0.1' ){
		domain = domain+":5416/aili/"
	}else{
		domain = domain+"/"
	}
	
	function getReqUrl(url){
		return	domain+url;
	}
