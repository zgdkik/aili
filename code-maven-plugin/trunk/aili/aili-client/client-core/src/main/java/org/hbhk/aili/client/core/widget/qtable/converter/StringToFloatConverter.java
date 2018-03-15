package org.hbhk.aili.client.core.widget.qtable.converter;

import org.hbhk.aili.client.core.commons.conversion.ConversionException;
import org.hbhk.aili.client.core.commons.conversion.IConverter;

public class StringToFloatConverter implements IConverter {

	@Override
	public Object to(Object source) throws ConversionException {
		try {
			return Float.parseFloat((String)source);
		} catch (Exception e) {
			throw new ConversionException("Not a float number.",e);
		}
	}

	@Override
	public Object from(Object target) throws ConversionException {
		return target.toString();
	}

}
