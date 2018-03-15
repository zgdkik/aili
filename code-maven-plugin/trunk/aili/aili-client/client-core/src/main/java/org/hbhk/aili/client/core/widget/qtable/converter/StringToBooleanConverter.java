package org.hbhk.aili.client.core.widget.qtable.converter;

import org.hbhk.aili.client.core.commons.conversion.ConversionException;
import org.hbhk.aili.client.core.commons.conversion.IConverter;

public class StringToBooleanConverter implements IConverter {

	@Override
	public Object to(Object source) throws ConversionException {
		String s = (String)source;
		
		if ("f".equals(s) || "n".equals(s) ||
				"no".equals(s) || "false".equals(s)) {
			return Boolean.FALSE;
		} else if ("t".equals(s) || "y".equals(s) ||
				"yes".equals(s) || "true".equals(s)) {
			return Boolean.TRUE;
		} else {
			throw new ConversionException(String.format("Unknown boolean string[%s]", s));
		}
	}

	@Override
	public Object from(Object target) throws ConversionException {
		Boolean b = (Boolean)target;
		if (b.equals(Boolean.FALSE)) {
			return "f";
		} else {
			return "t";
		}
	}

}
