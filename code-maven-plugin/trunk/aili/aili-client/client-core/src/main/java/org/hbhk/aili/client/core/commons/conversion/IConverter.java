package org.hbhk.aili.client.core.commons.conversion;

/**
 *	类型转换接口，其中to方法是把参数转换为指定的类型from与to方法相反。
 */
public interface IConverter {
	/**
	 * 把source转换成指定的类型，比如string转换成int类型。
	 * to
	 * @param source 转换源
	 * @return
	 * @throws ConversionException
	 * @return Object
	 * @since:0.6
	 */
	Object to(Object source) throws ConversionException;
	
	/**
	 * 像相反的方向转转化，比如to方法把string转换成init，那么from方法就是把int转换成string类型
	 * from
	 * @param target 转换目标
	 * @return
	 * @throws ConversionException
	 * @return Object
	 * @since:0.6
	 */
	Object from(Object target) throws ConversionException;
}
