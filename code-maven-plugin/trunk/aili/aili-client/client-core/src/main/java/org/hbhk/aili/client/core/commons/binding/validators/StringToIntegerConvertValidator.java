package org.hbhk.aili.client.core.commons.binding.validators;

import org.hbhk.aili.client.core.commons.conversion.IConverter;
import org.hbhk.aili.client.core.commons.converters.StringToIntegerConverter;
/**
*******************************************
* String类型转换到Integer类型的转换校验器
 */
public class StringToIntegerConvertValidator extends AbstractConvertValidator {
	// 转换器
	private IConverter converter;
	
	@Override
	public String getErrorMessage() {
		return String.format("%s must be an integer.", getArgs().get("fieldName"));
	}

	@Override
	public IConverter getConverter() {
		if (converter == null) {
			converter = new StringToIntegerConverter();
		}
		
		return converter;
	}
}