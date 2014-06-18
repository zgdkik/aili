	var domain = document.domain;
	function getReqUrl(url){
		if(domain == 'localhost' || domain == '127.0.0.1' ){
			domain = "http://"+domain+":5416/aili/"
		}else{
			domain = "http://"+domain+"/"
		}
		return	domain+url;
	}
