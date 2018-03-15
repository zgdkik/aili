package org.hbhk.aili.route.jms.server.common.transformer;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.transform.stream.StreamSource;

import org.hbhk.aili.route.jms.server.common.exception.ESBConvertException;


/**
 * OXM通用类.
 * 
 * @param <T>
 *            the generic type
 * @author HuangHua
 */
public class OxmTrans<T> {

	/**
	 * if clzz is null,will return null. convert object 2 xml
	 * 
	 * @param <T>
	 *            the generic type
	 * @param string
	 *            the string
	 * @param clzz
	 *            the clzz
	 * @return the t
	 * @throws ESBConvertException
	 *             the eSB convert exception
	 */
	public static <T> T toMessage(String string, Class<T> clzz) throws ESBConvertException {
		if (clzz == null) {
			return null;
		}
		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(string.getBytes());
			JAXBContext context = JAXBContext.newInstance(clzz);
			Unmarshaller u = context.createUnmarshaller();
			JAXBElement<T> element = u.unmarshal(new StreamSource(stream), clzz);
			return element.getValue();
		} catch (JAXBException e) {
			throw new ESBConvertException("反序列化" + clzz.getName() + "时失败！", e);
		}
	}

	/**
	 * if value is null, will return directly. convert object 2 stringWritter
	 * 
	 * @param <T>
	 *            the generic type
	 * @param value
	 *            the value
	 * @param stringWriter
	 *            the string writer
	 * @throws ESBConvertException
	 *             the eSB convert exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> void fromMessage(T value, StringWriter stringWriter) throws ESBConvertException {
		if (value == null) {
			return;
		}
		try {
			Class<T> clzz = (Class<T>) value.getClass();
			JAXBContext context = JAXBContext.newInstance(clzz);
			Marshaller m = context.createMarshaller();
			m.setProperty("jaxb.formatted.output", true);
			QName name = new QName(clzz.getSimpleName());
			JAXBElement<T> element = new JAXBElement<T>(name, clzz, value);
			m.marshal(element, stringWriter);
		} catch (PropertyException e) {
			throw new ESBConvertException("序列化" + value.getClass().getName() + "时失败！", e);
		} catch (FactoryConfigurationError e) {
			throw new ESBConvertException("序列化" + value.getClass().getName() + "时失败！", e);
		} catch (JAXBException e) {
			throw new ESBConvertException("序列化" + value.getClass().getName() + "时失败！", e);
		}
	}

}
