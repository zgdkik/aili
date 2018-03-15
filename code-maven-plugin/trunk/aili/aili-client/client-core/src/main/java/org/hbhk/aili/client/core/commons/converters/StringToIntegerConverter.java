package org.hbhk.aili.client.core.commons.converters;

import org.hbhk.aili.client.core.commons.conversion.ConversionException;
import org.hbhk.aili.client.core.commons.conversion.IConverter;

/**
 * 
 *String与Integer的转换器。
 */
public class StringToIntegerConverter implements IConverter {
	/**
	 * 把String转换成Integer类型的对象，
	 * 如果传入的参数为空值或者不是String类型或者其中包括非数字字符那么
	 * 都会抛出转换异常的ConversionException信息
	 * @see org.hbhk.aili.client.core.commons.conversion.IConverter#to(java.lang.Object)
	 * to
	 * @param source
	 * @return
	 * @throws ConversionException
	 * @since:
	 */
	@Override
	public Object to(Object source) throws ConversionException {
		if (source == null || "".equals(source)){
			return null;
		}
			
		if (source instanceof String) {
			try {
				return Integer.parseInt((String)source);
			} catch (Exception e) {
				throw new ConversionException("Can't convert to integer.",e);
			}
		}
		
		throw new ConversionException("Can't convert to integer.");
	}

	/**
	 * 把一个Integer类型的参数转换成String类型，
	 * 如果传入参数为null，那么返回一个不包含任何内容的非NULL值的String
	 * @see org.hbhk.aili.client.core.commons.conversion.IConverter#from(java.lang.Object)
	 * from
	 * @param target
	 * @return
	 * @throws ConversionException
	 * @since:
	 */
	@Override
	public Object from(Object target) throws ConversionException {
		if (target == null){
			return "";
		}
			
		return String.valueOf((Integer)target);
	}

}
