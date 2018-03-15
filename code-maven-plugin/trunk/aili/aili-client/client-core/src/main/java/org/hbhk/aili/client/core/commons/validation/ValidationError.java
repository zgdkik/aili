package org.hbhk.aili.client.core.commons.validation;

/**
* <b style="font-family:微软雅黑"><small>Description:校验错误实体类，定义了异常key和异常信息</small></b>   </br>
 */
public class ValidationError {
	// 信息
	private String message;
	// 键
	private Object key;
	
	/**
	 * 
	 * <p>Title: ValidationError</p>
	 * <p>Description: 构造方法</p>
	 * @param message 信息
	 */
	public ValidationError(String message) {
		this(message, null);
	}
	
	/**
	 * 
	 * <p>Title: ValidationError</p>
	 * <p>Description: 构造方法</p>
	 * @param message 信息
	 * @param key 键
	 */
	public ValidationError(String message, Object key) {
		this.key = key;
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the key
	 */
	public Object getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(Object key) {
		this.key = key;
	}
}