package org.hbhk.aili.client.core.commons.validation;

import org.hbhk.aili.client.core.commons.conversion.IConverter;

/**
 * 
 *	转换校验器，因为有的时候在进行数据转换的时候要金星校验的工作。
 *	如果校验失败就没有必要进行转换。
 */
public interface IConvertValidator extends IValidator {
	/**
	 * 获取转换器
	 * getConverter
	 * @return IConverter
	 * 返回转换器
	 * @since:0.6
	 */
	IConverter getConverter();
	
	/**
	 * 
	 * getToResult
	 * @return Object
	 * @since:0.6
	 */
	Object getToResult();
}