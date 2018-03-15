package org.hbhk.aili.client.core.commons.validation;

import java.util.ArrayList;
import java.util.List;
/**
* <b style="font-family:微软雅黑"><small>Description:校验错误事件类</small></b>   </br>
 */
public class ValidationErrorEvent {
	// error list
	private List<ValidationError> errors;
	
	/**
	 * 
	 * <p>Title: ValidationErrorEvent</p>
	 * <p>Description: 构造方法</p>
	 */
	public ValidationErrorEvent() {
		this(new ArrayList<ValidationError>());
	}
	
	/**
	 * 
	 * <p>Title: ValidationErrorEvent</p>
	 * <p>Description: 构造方法</p>
	 * @param errors 验证错误列表
	 */
	public ValidationErrorEvent(List<ValidationError> errors) {
		this.errors = errors;
	}
	
	/**
	 * 
	 * <p>Title: getErrors</p>
	 * <p>Description: 获取错误列表</p>
	 * @return
	 */
	public List<ValidationError> getErrors() {
		return errors;
	}
}