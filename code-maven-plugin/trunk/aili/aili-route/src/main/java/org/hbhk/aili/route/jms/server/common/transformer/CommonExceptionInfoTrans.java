package org.hbhk.aili.route.jms.server.common.transformer;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hbhk.aili.route.jms.server.common.CommonExceptionInfo;
import org.hbhk.aili.route.jms.server.common.ObjectFactory;
import org.hbhk.aili.route.jms.server.common.exception.ESBConvertException;


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
	 * @see org.hbhk.aili.route.jms.server.common.transformer.IMessageTransform#toMessage(java.lang.String)
	 */
	@Override
	public Object toMessage(String string) throws ESBConvertException {
		try {
			JAXBContext context = JAXBContext.newInstance(CommonExceptionInfo.class);
			Unmarshaller u = context.createUnmarshaller();
			ByteArrayInputStream stream = new ByteArrayInputStream(string.getBytes());
			JAXBElement<CommonExceptionInfo> element = u.unmarshal(new StreamSource(stream), CommonExceptionInfo.class);
			return element.getValue();
		} catch (JAXBException e) {
			throw new ESBConvertException("序列化CommonExceptionInfo时失败！source:" + string, e);
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
	 * @see org.hbhk.aili.route.jms.server.common.transformer.IMessageTransform#fromMessage(java.lang.Object)
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
			throw new ESBConvertException("反序列化CommonExceptionInfo时失败！source:" + ToStringBuilder.reflectionToString(message), e);
		} catch (FactoryConfigurationError e) {
			throw new ESBConvertException("反序列化CommonExceptionInfo时失败！source:" + ToStringBuilder.reflectionToString(message), e);
		} catch (JAXBException e) {
			throw new ESBConvertException("反序列化CommonExceptionInfo时失败！source:" + ToStringBuilder.reflectionToString(message), e);
		}
		return stringWriter.getBuffer().toString();
	}

}
