package org.hbhk.aili.client.core.commons.converters;

import org.hbhk.aili.client.core.commons.conversion.ConversionException;
import org.hbhk.aili.client.core.commons.conversion.IConverter;

/**
 * 
 *String与int类型之间的相互转换类，
 *to方法是把参数String转换成int类型，
 *from方法是把int类型转换成String类型。
 */
public class StringToIntConverter implements IConverter {

	/**
	 * 
	 * @see org.hbhk.aili.client.core.commons.conversion.IConverter#to(java.lang.Object)
	 * to
	 * @param source
	 * @return
	 * @throws ConversionException
	 * @since:
	 */
	@Override
	public Object to(Object source) throws ConversionException {
		if (source == null || "".equals(source))
			throw new ConversionException("Null int.");
		
		if (source instanceof String) {
			try {
				return Integer.parseInt((String)source);
			} catch (Exception e) {
				throw new ConversionException("Can't convert to int.");
			}
		}
		
		throw new ConversionException("Can't convert to int.");
	}

	/**
	 * 
	 * @see org.hbhk.aili.client.core.commons.conversion.IConverter#from(java.lang.Object)
	 * from
	 * @param target
	 * @return
	 * @throws ConversionException
	 * @since:
	 */
	@Override
	public Object from(Object target) throws ConversionException {
		return String.valueOf((Integer)target);
	}

}
