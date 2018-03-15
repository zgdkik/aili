package org.hbhk.aili.client.core.commons.validation;

/**
* <b style="font-family:微软雅黑"><small>Description:校验监听器接口</small></b>   </br>
 */
public interface IValidationListener {
	/**
	 * 
	 * <p>Title: validationError</p>
	 * <p>Description: 校验错误</p>
	 * @param e 校验错误事件
	 */
	void validationError(ValidationErrorEvent e);
}
