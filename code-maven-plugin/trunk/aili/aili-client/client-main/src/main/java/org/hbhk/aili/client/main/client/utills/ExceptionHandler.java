package org.hbhk.aili.client.main.client.utills;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.client.core.commons.exception.IErrorHandler;
import org.hbhk.aili.client.core.commons.exception.NormalException;
import org.hbhk.aili.client.core.component.exception.ErrorHandler;
import org.hbhk.aili.client.core.component.remote.BusinessException;
import org.hbhk.aili.client.core.core.application.IApplication;
import org.hbhk.aili.client.core.core.context.ApplicationContext;
import org.hbhk.aili.client.core.widget.msgbox.MsgBox;

/**
 * 
 * 异常处理类
 */
public class ExceptionHandler {

	/**
	 * 
	 * 提示异常信息
	 */
	public static void alert(Exception ex) {
	    	/**
	    	 * 判断参数ex对象是否是BusinessException的实例
	    	 */
		if(ex instanceof BusinessException){
			//业务异常直接显示
		    	/**
		    	 * 判断错误信息ex.getMessage()是否为空，然后显示错误信息
		    	 */
			if(StringUtils.isEmpty(ex.getMessage())){			    	
				MsgBox.showError(((BusinessException)ex).getMessage());
			}else{
				MsgBox.showError(ex.getMessage());
			}
		/**
		 * 判断参数ex对象是否是NormalException的实例
		 */
		}else if(ex instanceof NormalException){
			//GUI提示异常直接显示
			MsgBox.showError(ex.getMessage());
		}else{
			//未知异常采取框架提供的统一方式提示
			IApplication application = ApplicationContext.getApplication();
			/**
			 * 创建IErrorHandler对象errorHandler
			 */
			IErrorHandler errorHandler = new ErrorHandler(application);
			/**
			 * 调用异常处理接口的handle方法，可以根据传入的Throwable类型进行选择性的处理
			 */
			errorHandler.handle(ex);
		}
		
	}

}