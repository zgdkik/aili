package org.hbhk.aili.client.core.commons.validation;

import java.util.List;

/**
 *	校验器，定义了进行校验的方法。
 */
public interface IValidator {
	/**
	 * 
	 * validate
	 * @return List<ValidationError>
	 * 如果校验出错返回一个ValidationError的list集合，表示出错信息
	 * @since:0.6
	 */
	List<ValidationError> validate();
}
