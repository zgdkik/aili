	var domain = document.domain;
	function getReqUrl(url){
		var  base_url  = domain;
		if(base_url == 'localhost' || base_url == '127.0.0.1' ){
			base_url = "http://"+url+":5416/aili/"
		}else{
			base_url = "http://"+base_url+"/"
		} 
		return	base_url+url;
	}
