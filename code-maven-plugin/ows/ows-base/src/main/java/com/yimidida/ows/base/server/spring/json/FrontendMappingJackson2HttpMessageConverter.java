package com.yimidida.ows.base.server.spring.json;

import java.io.IOException;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.JavaType;

public class FrontendMappingJackson2HttpMessageConverter extends
		MappingJackson2HttpMessageConverter {

	private Log log = LogFactory.getLog(FrontendMappingJackson2HttpMessageConverter.class);

	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		JavaType javaType = getJavaType(clazz, null);
		return readJavaType(javaType, inputMessage);
	}
	@Override
	public Object read(Type type, Class<?> contextClass,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {

		JavaType javaType = getJavaType(type, contextClass);
		return readJavaType(javaType, inputMessage);
	}

	private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) {
		try {
			return getObjectMapper()
					.readValue(inputMessage.getBody(), javaType);
		} catch (IOException ex) {
			log.error("Could not read JSON: "
					+ ex.getMessage(), ex);
			throw new HttpMessageNotReadableException("Could not read JSON: "
					+ ex.getMessage(), ex);
		}
	}
}
