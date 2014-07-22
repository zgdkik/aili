var Ajax={
//		createXhr:function(){
//			var xhr;
//			if(window.XMLHttpRequest){
//				xhr=new XMLHttpRequest();
//			}else if(window.ActiveXObject){
//				xhr=new ActiveXObject("Microsoft.XMLHTTP");
//			}
//			return xhr;
//		},
		createXhr:function(){
	        var xhr=null;
	        try{
	        	// Firefox, Opera 8.0+, Safari
	        	xhr=new XMLHttpRequest();
	        }catch (e){
	        	// Internet Explorer
	        	try{
	        		xhr=new ActiveXObject("Msxml2.XMLHTTP");
	        	}catch (e){
	        		try{
	        			xhr=new ActiveXObject("Microsoft.XMLHTTP");
	        		}catch (e){
	        			alert("不能创建XMLHttpRequest对象");
	        		}
	        	}
	        }
	        return xhr;

        },
		request:function(url,method,message,type,callback){
			var xhr=this.createXhr();
			if(method=="GET"){
				xhr.open(method,url+"?"+message);
				xhr.send(null);
			}else if(method=="POST"){
				xhr.open(method,url);
				xhr.setRequestHeader("enctype","application/x-www-form-urlencoded");
				xhr.send(message);
			}
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4&&xhr.status==200){
					if(type=="TEXT"){
						callback(xhr.responseText);
					}else if(type="XML"){
						callback(xhr.responseXML);
					}
				}
			}
		}
}