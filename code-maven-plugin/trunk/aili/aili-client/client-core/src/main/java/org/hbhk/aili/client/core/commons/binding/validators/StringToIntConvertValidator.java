package org.hbhk.aili.client.core.commons.binding.validators;

import org.hbhk.aili.client.core.commons.conversion.IConverter;
import org.hbhk.aili.client.core.commons.converters.StringToIntConverter;
/**
 * String类型转换到int类型的转换校验器
 */
public class StringToIntConvertValidator extends AbstractConvertValidator {
	// 转换器
	private IConverter converter;
	
	@Override
	public String getErrorMessage() {
		return String.format("%s must be an int.", getArgs().get("fieldName"));
	}

	@Override
	public IConverter getConverter() {
		if (converter == null) {
			converter = new StringToIntConverter();
		}
		
		return converter;
	}
}
