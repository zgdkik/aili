(function($){  
    //备份jquery的ajax方法  
    var _ajax=$.ajax;  
    //重写jquery的ajax方法  
    $.ajax=function(opt){
        //备份opt中error和success方法  
        var fn = {  
            error:function(XMLHttpRequest, textStatus, errorThrown){},
            exception:function(data, textStatus){},  
            success:function(data, textStatus){}  
        };
        if(opt.error){  
            fn.error=opt.error;
        }  
        if(opt.success){ 
            fn.success=opt.success;  
        }  
        if(opt.exception){  
            fn.exception=opt.exception;
        }  
          
        //扩展增强处理  
        var _opt = $.extend(opt,{
            error:function(XMLHttpRequest, textStatus, errorThrown){  
                //错误方法增强处理  
            	if(opt.error){ 
            		fn.error(XMLHttpRequest, textStatus, errorThrown);
            	}else{
            		 alert(errorThrown);
            	}
            },  
            success:function(data, textStatus){
            	if(data.success){
            		 //成功回调方法增强处理  
                    fn.success(data, textStatus);
            	}else{
            		//业务异常调方法增强处理  
                    fn.exception(data, textStatus);  
            	}
            } 
        });  
        _ajax(_opt);  
    };  
})(jQuery);

