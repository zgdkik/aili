package com.deppon.esb.management.common.transformer;

import com.deppon.esb.management.common.exception.ESBConvertException;



/**
 * 转换类接口.
 * 
 * @author HuangHua
 * @date 2012-12-21 上午10:44:23
 */
public interface IMessageTransform {
	
	
	/**
	 * 把XML转换为baseDomain的子类(JSON暂时不考虑).
	 * 
	 * @param string
	 *            the string
	 * @return the object
	 * @throws ESBConvertException
	 *             the eSB convert exception
	 */
	public Object toMessage(String string) throws ESBConvertException;

	
	/**
	 * 把baseDomain的子类转换为XML(JSON暂时不考虑).
	 * 
	 * @param message
	 *            the message
	 * @return the string
	 * @throws ESBConvertException
	 *             the eSB convert exception
	 */
	public String fromMessage(Object message) throws ESBConvertException;
	
}
