package org.hbhk.aili.route.jms.server.common.transformer;

import org.hbhk.aili.route.jms.server.common.exception.ESBConvertException;



/**
 * 转换类接口.
 * 
 * @author HuangHua
 * @date 2012-12-21 上午10:44:23
 */
public interface IMessageTransform {
	
	
	/**
	 * 把XML转换为Object.
	 * 
	 * @param string
	 *            the string
	 * @return the object
	 * @throws ESBConvertException
	 *             the eSB convert exception
	 */
	public Object toMessage(String string) throws ESBConvertException;

	
	/**
	 * 把baseDomain的子类转换Object.
	 * 
	 * @param message
	 *            the message
	 * @return the string
	 * @throws ESBConvertException
	 *             the eSB convert exception
	 */
	public String fromMessage(Object message) throws ESBConvertException;
	
}
