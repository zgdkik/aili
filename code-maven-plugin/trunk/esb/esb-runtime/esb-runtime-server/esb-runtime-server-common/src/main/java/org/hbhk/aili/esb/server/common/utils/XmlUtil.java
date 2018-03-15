package org.hbhk.aili.esb.server.common.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.builder.ToStringBuilder;

public class XmlUtil {

	@SuppressWarnings("unchecked")
	public static <T> T toObj(String str,Class<?> cls) {
		try {
			JAXBContext context = JAXBContext.newInstance(cls);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(new StringReader(str));
		} catch (Exception e) {
			throw new RuntimeException("序列化失:" + str, e);
		}
	}

	public static String toStr(Object obj,Class<?> cls) {
		StringWriter stringWriter = new StringWriter();
		try {
			JAXBContext context = JAXBContext.newInstance(cls);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(obj, System.out);
		} catch (Exception e) {
			throw new RuntimeException("反序列化失败:"
					+ ToStringBuilder.reflectionToString(obj), e);
		}
		return stringWriter.getBuffer().toString();
	}

}
