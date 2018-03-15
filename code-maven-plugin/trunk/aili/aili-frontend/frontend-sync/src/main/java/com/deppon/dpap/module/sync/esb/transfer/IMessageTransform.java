package com.deppon.dpap.module.sync.esb.transfer;

import java.io.UnsupportedEncodingException;

import com.deppon.dpap.module.sync.esb.exception.ConvertException;

public interface IMessageTransform<T> {
	
	
	/**
	 * 把字符串转换为POJO.
	 * 
	 * @param string
	 *            the string
	 * @return the t
	 * @throws UnsupportedEncodingException 
	 * @throws ESBConvertException
	 *             the eSB convert exception
	 */
	T toMessage(String string) throws ConvertException, UnsupportedEncodingException;

	
	/**
	 * 把POJO转换为字符串.
	 * 
	 * @param message
	 *            the message
	 * @return the string
	 * @throws ESBConvertException
	 *             the eSB convert exception
	 */
	String fromMessage(T message) throws ConvertException;
	
}
