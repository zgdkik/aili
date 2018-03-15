package org.hbhk.aili.client.core.commons.validation;

import java.util.ArrayList;
import java.util.List;

/**
* <b style="font-family:微软雅黑"><small>Description:数据校验工具类</small></b>   </br>
 */
public final class ValidationUtils {
	
	private ValidationUtils(){
		
	}
	/**
	 * 
	 * @Title:createErrors
	 * @Description:根据错误信息构造错误
	 * @param @param message
	 * @param @return
	 * @return List<ValidationError>
	 * @throws
	 */
	public static List<ValidationError> createErrors(String message) {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		return addError(errors, message, null);
	}
	
	/**
	 * 
	 * @Title:createErrors
	 * @Description:根据错误信息和错误键值构造错误
	 * @param @param message
	 * @param @return
	 * @return List<ValidationError>
	 * @throws
	 */
	public static List<ValidationError> createErrors(String message, Object key) {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		return addError(errors, message, key);
	}
	
	/**
	 * 
	 * @Title:createErrors
	 * @Description:根据错误信息构造错误，并添加到errors中
	 * @param @param message
	 * @param @return
	 * @return List<ValidationError>
	 * @throws
	 */
	public static List<ValidationError> addError(List<ValidationError> errors, String message) {
		return addError(errors, message, null);
	}
	
	/**
	 * 
	 * @Title:createErrors
	 * @Description:根据错误信息和键值构造错误，并添加到errors中
	 * @param @param message
	 * @param @return
	 * @return List<ValidationError>
	 * @throws
	 */
	public static List<ValidationError> addError(List<ValidationError> errors, String message, Object key) {
		errors.add(new ValidationError(message, key));
		
		return errors;
	}
}