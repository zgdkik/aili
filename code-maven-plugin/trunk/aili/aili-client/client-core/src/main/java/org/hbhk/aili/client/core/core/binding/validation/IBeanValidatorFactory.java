package org.hbhk.aili.client.core.core.binding.validation;

import org.hbhk.aili.client.core.commons.validation.IConvertValidator;
import org.hbhk.aili.client.core.commons.validation.IValidator;

/**
 *   bean级别的校验器工厂接口
 */
public interface IBeanValidatorFactory {
	/**
	 * 
	 * <p>Title: getBeanValidator</p>
	 * <p>Description: 获取Bean校验器</p>
	 * @return
	 */
	IValidator getBeanValidator();
	
	/**
	 * 
	 * <p>Title: addConvertValidator</p>
	 * <p>Description: 添加转换校验器</p>
	 * @param propertyName 属性名
	 * @param validator 
	 */
	void addConvertValidator(String propertyName, IConvertValidator validator);
	
	/**
	 * 
	 * <p>Title: addPropertyValidator</p>
	 * <p>Description: 添加属性校验器</p>
	 * @param propertyName 属性
	 * @param validator 校验器
	 */
	void addPropertyValidator(String propertyName, IValidator validator);
	
	/**
	 * 
	 * <p>Title: getConvertValidator</p>
	 * <p>Description: 获取转换校验器</p>
	 * @param propertyName 属性名
	 * @return
	 */
	IConvertValidator getConvertValidator(String propertyName);
	
	/**
	 * 
	 * <p>Title: getPropertyValidator</p>
	 * <p>Description: 获取属性校验器</p>
	 * @param propertyName 属性名
	 * @return
	 */
	IValidator getPropertyValidator(String propertyName);
	
	/**
	 * 
	 * <p>Title: addGlobalValidator</p>
	 * <p>Description: 添加全局校验器</p>
	 * @param validator 校验器
	 */
	void addGlobalValidator(IValidator validator);
}