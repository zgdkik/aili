package com.deppon.esb.management.excptn.transformer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;

import com.deppon.esb.management.common.exception.ESBConvertException;
import com.deppon.esb.management.common.transformer.IMessageTransform;
import com.deppon.esb.management.exptn.generate.CommonExceptionInfo;
import com.deppon.esb.management.exptn.generate.ObjectFactory;


/**
 * 公共异常转换类.
 * 
 * @author HuangHua
 * @date 2012-12-21 上午10:44:40
 */
public class CommonExceptionInfoTrans implements IMessageTransform {

	/**
	 * string 2 object.
	 * 
	 * @param string
	 *            the string
	 * @return the object
	 * @throws ESBConvertException
	 *             the eSB convert exception
	 * @author HuangHua
	 * @date 2012-12-21 上午10:44:54
	 * @see com.deppon.esb.server.common.transformer.IMessageTransform#toMessage(java.lang.String)
	 */
	@Override
	public Object toMessage(String string) throws ESBConvertException {
		try {
			String stringToUse = IOUtils.toString(IOUtils.toInputStream(string, "UTF-8"));
			JAXBContext context = JAXBContext.newInstance(CommonExceptionInfo.class);
			Unmarshaller u = context.createUnmarshaller();
			ByteArrayInputStream stream = new ByteArrayInputStream(stringToUse.getBytes());
			JAXBElement<CommonExceptionInfo> element = u.unmarshal(new StreamSource(stream), CommonExceptionInfo.class);
			return element.getValue();
		} catch (JAXBException e) {
			throw new ESBConvertException("序列化CommonExceptionInfo时失败！", e);
		} catch (IOException e) {
			throw new ESBConvertException("序列化CommonExceptionInfo时失败！", e);
		}
	}

	/**
	 * object 2 string.
	 * 
	 * @param message
	 *            the message
	 * @return the string
	 * @throws ESBConvertException
	 *             the eSB convert exception
	 * @author HuangHua
	 * @date 2012-12-21 上午10:45:02
	 * @see com.deppon.esb.server.common.transformer.IMessageTransform#fromMessage(java.lang.Object)
	 */
	@Override
	public String fromMessage(Object message) throws ESBConvertException {
		CommonExceptionInfo result = (CommonExceptionInfo) message;
		ObjectFactory objectfactory = new ObjectFactory();
		JAXBElement<CommonExceptionInfo> jaxbRetieveResponse = objectfactory.createCommonExceptionInfo(result);
		StringWriter stringWriter = new StringWriter();
		try {
			JAXBContext context = JAXBContext.newInstance(CommonExceptionInfo.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			marshaller.marshal(jaxbRetieveResponse, stringWriter);
		} catch (PropertyException e) {
			throw new ESBConvertException("序列化CommonExceptionInfo时失败！", e);
		} catch (FactoryConfigurationError e) {
			throw new ESBConvertException("序列化CommonExceptionInfo时失败！", e);
		} catch (JAXBException e) {
			throw new ESBConvertException("序列化CommonExceptionInfo时失败！", e);
		}
		return stringWriter.getBuffer().toString();
	}

}
