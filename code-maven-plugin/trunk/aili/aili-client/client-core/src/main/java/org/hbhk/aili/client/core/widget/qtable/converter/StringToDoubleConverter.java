package org.hbhk.aili.client.core.widget.qtable.converter;

import org.hbhk.aili.client.core.commons.conversion.ConversionException;
import org.hbhk.aili.client.core.commons.conversion.IConverter;

public class StringToDoubleConverter implements IConverter {

	@Override
	public Object to(Object source) throws ConversionException {
		try {
			return Double.parseDouble((String)source);
		} catch (Exception e) {
			throw new ConversionException("Not a double number.",e);
		}
	}

	@Override
	public Object from(Object target) throws ConversionException {
		return target.toString();
	}

}
