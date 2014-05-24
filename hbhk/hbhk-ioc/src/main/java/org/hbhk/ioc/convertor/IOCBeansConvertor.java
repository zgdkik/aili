package org.hbhk.ioc.convertor;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.transform.stream.StreamSource;

import org.hbhk.ioc.domain.Bean;
import org.hbhk.ioc.domain.Beans;
import org.hbhk.ioc.domain.Property;
import org.hbhk.ioc.util.JAXBContextUtil;

public class IOCBeansConvertor {

	/** The Constant CLZZ. */
	private static final Class<Beans> CLZZ = Beans.class;

	/** The log. */

	/** The context. */
	private static JAXBContext context = JAXBContextUtil.initContext(CLZZ);

	public Beans toMessage(String string) throws UnsupportedEncodingException {
		if (context == null) {
			JAXBContextUtil.initContext(CLZZ);// 再次尝试一次
			if (context == null) {
			}
		}
		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(
					string.getBytes("UTF-8"));
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JAXBElement<Beans> element = unmarshaller.unmarshal(
					new StreamSource(stream), CLZZ);
			return element.getValue();
		} catch (JAXBException e) {
		}
		return null;
	}

	public String fromMessage(Beans value) {
		if (context == null) {
			JAXBContextUtil.initContext(CLZZ);// 再次尝试一次
			if (context == null) {
			}
		}
		if (value == null) {
			return null;
		}
		try {
			StringWriter stringWriter = new StringWriter();
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING,
					"UTF-8");
			QName q = new QName("http://www.hbhk.org/ioc/domain/", "beans");

			JAXBElement<Beans> element = new JAXBElement<Beans>(q, Beans.class,
					null, value);
			marshaller.marshal(element, stringWriter);
			stringWriter.flush();
			return stringWriter.toString();
		} catch (PropertyException e) {
		} catch (FactoryConfigurationError e) {
		} catch (JAXBException e) {
		}
		return null;
	}

	public static void main(String[] args) {
		Beans value = new Beans();
		Bean b = new Bean();
		Property p = new Property();
		p.setName("name");
		p.setValue("hbhk");
		List<Property> ps = new ArrayList<Property>();
		ps.add(p);
		b.setProperties(ps);
		b.setId("id");
		b.setClazz("class");
		List<Bean> bs = new ArrayList<Bean>();
		bs.add(b);
		IOCBeansConvertor ioct = new IOCBeansConvertor();
		value.setBeans(bs);
		String ss = ioct.fromMessage(value);
		System.out.println(ss);
	}
}
